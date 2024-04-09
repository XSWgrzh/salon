package com.salon.config;

import com.salon.handler.OAuth2ExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                                                   HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        //所有的访问都需要通过身份认证
                        .anyRequest().authenticated()
                )
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

}
