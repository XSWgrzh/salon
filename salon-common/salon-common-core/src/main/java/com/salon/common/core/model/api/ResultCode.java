package com.salon.common.core.model.api;

import java.io.Serializable;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.api
 * @Project：salon
 * @name：ResultCode
 * @Date：2023/10/25 11:48
 */
public enum ResultCode  {

    SUCCESS(200),
    ERROR(500);

    private Integer code;

    ResultCode(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
