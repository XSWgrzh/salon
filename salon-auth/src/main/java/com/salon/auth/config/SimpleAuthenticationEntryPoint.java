package com.salon.auth.config;

import cn.hutool.json.JSONUtil;
import com.salon.common.core.model.StatusCode;
import com.salon.common.core.model.api.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.config
 * @Project：salon
 * @name：SimpleAuthenticationEntryPoint
 * @Date：2024/1/9 16:11
 * 认证失败处理器
 */
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(SimpleAuthenticationEntryPoint.class);

    @SneakyThrows
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        if (authException instanceof InvalidBearerTokenException) {
            Result<Object> failed = Result.failed(StatusCode.TOKEN_FAILURE);
            failed.setData(false);
            response.getWriter().write(JSONUtil.toJsonStr(failed));
        }
        if (authException instanceof UsernameNotFoundException || authException instanceof OAuth2AuthenticationException) {
            Result<Object> failed = Result.failed(StatusCode.Username_And_PasswordError);
            failed.setData(false);
            response.getWriter().write(JSONUtil.toJsonStr(failed));
        }else{
            Result<Object> failed = Result.failed(StatusCode.Username_And_PasswordError);
            failed.setData(false);
            response.getWriter().write(JSONUtil.toJsonStr(failed));
        }

    }
}
