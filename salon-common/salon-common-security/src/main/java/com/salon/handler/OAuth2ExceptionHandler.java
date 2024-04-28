package com.salon.handler;

import cn.hutool.json.JSONUtil;
import com.salon.common.core.model.StatusCode;
import com.salon.common.core.model.api.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

import static com.salon.common.core.constant.OAuth2Constants.ERROR_URL;

/**
 * @Author：xieshaowei
 * @Package：com.salon.handler
 * @Project：salon
 * @name：OAuth2ExceptionHandler
 * @Date：2024/3/28 16:30
 */
public class OAuth2ExceptionHandler {

    public static OAuth2AuthenticationException getException(String errorCode, String description, String uri) {
        return new OAuth2AuthenticationException(new OAuth2Error(errorCode, description, uri));
    }

    public static OAuth2AuthenticationException getException(int errorCode, String description) {
        return getException(String.valueOf(errorCode), description, ERROR_URL);
    }

    @SneakyThrows
    public static void handleAccessDenied(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
        response.setStatus(StatusCode.SUCCESS.getcode());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        Result<Object> failed = Result.failed(StatusCode.TOKEN_FAILURE, ex.getMessage());
        failed.setData(false);
        response.getWriter().write(JSONUtil.toJsonStr(failed));
    }

    @SneakyThrows
    public static void handleAuthentication(HttpServletRequest request, HttpServletResponse response, Throwable ex) {
        response.setStatus(StatusCode.SUCCESS.getcode());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        Result<Object> failed = Result.failed(StatusCode.TOKEN_FAILURE.getcode(), ex.getMessage());
        failed.setData(false);
        response.getWriter().write(JSONUtil.toJsonStr(failed));
//        if (ex instanceof OAuth2AuthenticationException) {
//            OAuth2AuthenticationException oAuth2AuthenticationException = (OAuth2AuthenticationException) ex;
//            String message = oAuth2AuthenticationException.getError().getDescription();
////            int errorCode = Integer.parseInt(oAuth2AuthenticationException.getError().getErrorCode());
//            Result.of(response, StatusCode.UNAUTHORIZED.getcode(), message);
//            return;
//        }
//        if (ex instanceof InsufficientAuthenticationException) {
//            Result.of(response, StatusCode.UNAUTHORIZED.getcode(), "拒绝访问，没有权限");
//        }
    }

}
