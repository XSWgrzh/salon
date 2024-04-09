package com.salon.common.core;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core
 * @Project：salon
 * @name：EnableException
 * @Date：2024/4/8 14:38
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(GlobalExceptionHandler.class)
public @interface EnableException {
}
