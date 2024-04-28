package com.salon.common.log.annotation;

import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.*;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.log
 * @Project：salon
 * @name：OperateLog
 * @Date：2024/4/15 11:41
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Schema(name = "OperateLog", description = "操作日志注解")
public @interface OperateLog {
    @Schema(name = "module", description = "模块")
    String module();

    @Schema(name = "operation", description = "操作")
    String operation();
}
