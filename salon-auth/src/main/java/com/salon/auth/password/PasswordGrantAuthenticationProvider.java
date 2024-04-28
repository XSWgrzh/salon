package com.salon.auth.password;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.useragent.UserAgent;
import com.salon.auth.oauth2.OAuth2AuthenticationProvider;
import com.salon.common.core.constant.NumberConstants;
import com.salon.common.core.constant.PropertiesConstants;
import com.salon.common.core.constant.RocketMqConstants;
import com.salon.common.core.model.StatusCode;
import com.salon.common.core.model.log.SysLoginLog;
import com.salon.common.core.utils.IpUtil;
import com.salon.common.core.utils.RequestUtil;
import com.salon.common.redis.utils.RedisKeyUtil;
import com.salon.common.redis.utils.RedisUtil;
import com.salon.common.rocketmq.template.RocketMqTemplate;
import com.salon.utils.UserDetail;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.password
 * @Project：salon
 * @name：PasswordGrantAuthenticationProvider
 * @Date：2024/1/22 14:35
 */
@Slf4j
public class PasswordGrantAuthenticationProvider implements AuthenticationProvider {

    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";
    @Resource
    private RocketMqTemplate rocketMqTemplate;
    @Resource
    private Environment environment;

    @Resource
    private OAuth2AuthenticationProvider oAuth2AuthenticationProvider;

    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    @Resource
    private RedisUtil redisUtil;

    public PasswordGrantAuthenticationProvider(OAuth2AuthorizationService authorizationService, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator) {
        Assert.notNull(authorizationService, "authorizationService cannot be null");
        Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PasswordGrantAuthenticationToken passwordGrantAuthenticationToken = (PasswordGrantAuthenticationToken) authentication;

        Map<String, Object> additionalParameters = passwordGrantAuthenticationToken.getAdditionalParameters();
        // Ensure the client is authenticated
        OAuth2ClientAuthenticationToken clientPrincipal = getAuthenticatedClientElseThrowInvalidClient(passwordGrantAuthenticationToken);
        RegisteredClient registeredClient = clientPrincipal.getRegisteredClient();
        //授权类型
        AuthorizationGrantType authorizationGrantType = passwordGrantAuthenticationToken.getGrantType();
        //用户名
        String username = (String) additionalParameters.get(OAuth2ParameterNames.USERNAME);
        //密码
        String password = (String) additionalParameters.get(OAuth2ParameterNames.PASSWORD);

        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        try {

            // Ensure the client is configured to use this authorization grant type
            if (!registeredClient.getAuthorizationGrantTypes().contains(authorizationGrantType)) {
                throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
            }

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = oAuth2AuthenticationProvider.authenticationToken(username, password, request);

            // Generate the access token
            OAuth2TokenContext tokenContext = DefaultOAuth2TokenContext.builder().registeredClient(registeredClient).principal(usernamePasswordAuthenticationToken).authorizationServerContext(AuthorizationServerContextHolder.getContext()).tokenType(OAuth2TokenType.ACCESS_TOKEN).authorizationGrantType(authorizationGrantType).authorizationGrant(passwordGrantAuthenticationToken).build();

            OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
            if (generatedAccessToken == null) {
                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR, "The token generator failed to generate the access token.", null);
                throw new OAuth2AuthenticationException(error);
            }
            OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(), generatedAccessToken.getExpiresAt(), null);

            // Initialize the OAuth2Authorization
            OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient).principalName(usernamePasswordAuthenticationToken.getCredentials().toString()).authorizationGrantType(authorizationGrantType);
            if (generatedAccessToken instanceof ClaimAccessor) {
                authorizationBuilder.token(accessToken, (metadata) -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims()))
                        // admin后台管理需要token，解析token获取用户信息，因此将用户信息存在数据库，下次直接查询数据库就可以获取用户信息
                        .attribute(Principal.class.getName(), usernamePasswordAuthenticationToken);
            } else {
                authorizationBuilder.accessToken(accessToken);
            }

            // ----- Refresh token -----
//        OAuth2RefreshToken refreshToken = null;
//        if (registeredClient.getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
//                // Do not issue refresh token to public client
//                !clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {
//
//            tokenContext = tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
//            OAuth2Token generatedRefreshToken = this.tokenGenerator.generate(tokenContext);
//            if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
//                OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR,
//                        "The token generator failed to generate the refresh token.", ERROR_URI);
//                throw new OAuth2AuthenticationException(error);
//            }
//
//            refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
//            authorizationBuilder.refreshToken(refreshToken);
//        }

            OAuth2Authorization authorization = authorizationBuilder.build();

            // Save the OAuth2Authorization
            this.authorizationService.save(authorization);

            String userInfoKey = RedisKeyUtil.getUserInfoKey(accessToken.getTokenValue());
            //用户信息存到Redis
            UserDetail userDetail = (UserDetail) usernamePasswordAuthenticationToken.getPrincipal();
            Instant expiresAt = accessToken.getExpiresAt();
            Instant nowAt = Instant.now();
            long expireTime = ChronoUnit.SECONDS.between(nowAt, expiresAt);
            redisUtil.set(userInfoKey, userDetail, expireTime - 1);

            this.handleLoginLog(username, request, authorizationGrantType.getValue(), null);
            return new OAuth2AccessTokenAuthenticationToken(registeredClient, clientPrincipal, accessToken);
        } catch (Exception e) {
            this.handleLoginLog(username, request, authorizationGrantType.getValue(), e);
            throw e;
        } finally {
            // 清空上下文
            SecurityContextHolder.clearContext();
        }
    }

    public void handleLoginLog(String username, HttpServletRequest request, String authType, Exception e) {
        UserAgent userAgent = RequestUtil.getUserAgent(request);
        String os = userAgent.getOs().getName();
        String browser = userAgent.getBrowser().getName();
        SysLoginLog sysLoginLog = SysLoginLog.builder()
                .ip(IpUtil.getIpAddr(request))
                .username(username)
                .os(os).
                browser(browser)
                .type(authType)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        if (ObjectUtil.isEmpty(e)) {
            sysLoginLog.setStatus(NumberConstants.SUCCESS);
            sysLoginLog.setMessage(StatusCode.LOGIN_SUCCESS.getdesc());
        } else {
            sysLoginLog.setMessage(e.getMessage());
            sysLoginLog.setStatus(NumberConstants.FAIL);
        }
        String property = environment.getProperty(PropertiesConstants.SPRING_APPLICATION_NAME);
        rocketMqTemplate.sendSyncOrderlyMessage(RocketMqConstants.SALON_LOGIN_TOPIC, sysLoginLog, property);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PasswordGrantAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private static OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
        OAuth2ClientAuthenticationToken clientPrincipal = null;
        if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
            clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
        }
        if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
            return clientPrincipal;
        }
        throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
    }

}
