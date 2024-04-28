package com.salon.config;

import com.salon.handler.OAuth2ExceptionHandler;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Author：xieshaowei
 * @Package：com.salon.config
 * @Project：salon
 * @name：OAuth2ResourceServerAutoConfig
 * @Date：2024/3/28 15:29
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class OAuth2ResourceServerAutoConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(GlobalOpaqueTokenIntrospector globalOpaqueTokenIntrospector,
                                                   Environment env,
                                                   OAuth2ResourceServerProperties oAuth2ResourceServerProperties,
                                                   HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/logouts*","/logouts/**","/logouts","test","/test","/test/**")
                        .permitAll()
                        //所有的访问都需要通过身份认证
                        .anyRequest().authenticated()
                )
//                .authorizeHttpRequests(customizer(env, oAuth2ResourceServerProperties))
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .oauth2ResourceServer(
//                        oauth2 -> oauth2.jwt(Customizer.withDefaults())
                    resource -> resource.opaqueToken(token -> token.introspector(globalOpaqueTokenIntrospector))
                    //认证失败处理器
                    .accessDeniedHandler(OAuth2ExceptionHandler::handleAccessDenied)
                    .authenticationEntryPoint(OAuth2ExceptionHandler::handleAuthentication)
                );
        return http.build();

    }

    @NotNull
    public static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> customizer(
            Environment env, OAuth2ResourceServerProperties oAuth2ResourceServerProperties) {
        return request -> request.
                requestMatchers("/logouts*","/logouts/**","/logouts","test","/test","/test/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

}
