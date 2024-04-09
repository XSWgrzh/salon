package com.salon.auth.password;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.password
 * @Project：salon
 * @name：mobile
 * @Date：2024/3/29 15:54
 */
public class mobile implements AuthenticationProvider {

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }


    public boolean supports(Class<?> authentication) {
        return false;
    }
}
