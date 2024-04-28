package com.salon.common.core.model;

import java.io.Serializable;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.exception
 * @Project：salon
 * @name：StatusCode
 * @Date：2024/1/17 16:22
 */
public enum StatusCode implements Code, Serializable {

    SUCCESS(200, "请求成功"),
    UNAUTHORIZED(401, "未认证"),
    FORBIDDEN(403, "拒绝访问，没有权限"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误，无法完成请求"),
    Username_And_PasswordError(10001, "账号密码错误"),
    AUTHENTICATION_FAILED(10002, "认证失败"),
    LOGIN_SUCCESS(10003, "登录成功"),
    TOKEN_FAILURE(10003, "token失效"),

    UNKNOWN(999999, "未知错误");

    private final Integer code;
    private final String desc;

    StatusCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getcode() {
        return this.code;
    }

    @Override
    public String getdesc() {
        return this.desc;
    }
}
