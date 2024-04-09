package com.salon.auth.password;

import com.salon.auth.constant.OAuth2Constant;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.password
 * @Project：salon
 * @name：PasswordGrantAuthenticationToken
 * @Date：2024/1/22 14:25
 */
public class PasswordGrantAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {
    public PasswordGrantAuthenticationToken(Authentication clientPrincipal,
                                            @Nullable Map<String, Object> additionalParameters) {
        super(new AuthorizationGrantType(OAuth2Constant.GRANT_TYPE_PASSWORD),
                clientPrincipal, additionalParameters);
    }

}
