package com.salon.common.log.annotation;

import com.salon.common.log.aop.OperateLogAop;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.log.annotation
 * @Project：salon
 * @name：EnableOperateLog
 * @Date：2024/4/24 16:36
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(OperateLogAop.class)
public @interface EnableOperateLog {
}
