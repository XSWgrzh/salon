package com.salon.common.core.request;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core
 * @Project：salon
 * @name：RequestSingleParam
 * @Date：2024/2/21 17:10
 * 自定义注解接收单个参数
 */

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestSingleParam {

    String value();

}
