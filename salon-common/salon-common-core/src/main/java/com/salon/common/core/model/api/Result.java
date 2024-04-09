package com.salon.common.core.model.api;

import com.salon.common.core.model.StatusCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.api
 * @Project：salon
 * @name：Result
 * @Date：2023/10/25 11:46
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    private T data;
    private Integer code;
    private String message;

    public Result(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.message = msg;
    }

    public static <T> Result<T> of(T data, Integer code, String msg) {
        return new Result(data, code, msg);
    }

    public static <T> Result<T> success(T data, String msg) {
        return of(data, ResultCode.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> success(T model) {
        return of(model, ResultCode.SUCCESS.getCode(), "");
    }

    public static <T> Result<T> failed(String msg) {
        return of(null, ResultCode.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(T data, String msg) {
        return of(data, ResultCode.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed(Integer code, String msg) {
        return of(null, code, msg);
    }

    public static <T> Result<T> failed(StatusCode statusCode) {
        return of(null, statusCode.getcode(), statusCode.getdesc());
    }
}
