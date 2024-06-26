package com.salon.common.core;

import com.salon.common.core.exception.GlobalException;
import com.salon.common.core.model.StatusCode;
import com.salon.common.core.model.api.Result;
import jakarta.security.auth.message.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core
 * @Project：salon
 * @name：GlobalExceptionHandler
 * @Date：2024/3/21 14:01
 */
@Slf4j
@RestControllerAdvice
@ResponseBody
@Component
public class GlobalExceptionHandler {

    /**
     * 异常处理并响应.
     *
     * @param ex 异常
     * @return 响应结果
     */
    @ExceptionHandler({GlobalException.class, AuthException.class})
    public Result<?> handle(GlobalException ex) {
        log.error(String.valueOf(ex));
        log.error("错误码：{}，错误信息：{}", ex.getCode(), ex.getMessage());
        return Result.failed(ex.getCode(), ex.getMessage());
    }

//    @ExceptionHandler({OAuth2AuthenticationException.class})
//    public Result<?> handleOAuth2AuthenticationException(OAuth2AuthenticationException ex) {
////        log.error(String.valueOf(ex));
//        log.error(ex.getMessage(), ex);
//        log.error("Exception错误码：{}，Exception错误信息：{}", StatusCode.UNAUTHORIZED, ex.getMessage());
//        return Result.failed(StatusCode.UNAUTHORIZED.getcode(), ex.getMessage());
//    }

    @ExceptionHandler({Exception.class})
    public Result<?> handleException(Exception ex) {
//        log.error(String.valueOf(ex));
        log.error(ex.getMessage(), ex);
        log.error("Exception错误码：{}，Exception错误信息：{}", StatusCode.INTERNAL_SERVER_ERROR, ex.getMessage());
        return Result.failed(StatusCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

}
