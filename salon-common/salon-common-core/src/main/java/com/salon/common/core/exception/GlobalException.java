package com.salon.common.core.exception;

import com.salon.common.core.model.StatusCode;
import lombok.Data;

import java.io.Serial;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.exception
 * @Project：salon
 * @name：GlobalException
 * @Date：2024/1/17 16:21
 */
@Data
public class GlobalException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 4102669900127613541L;

    private Integer code;

    private String message;

    public GlobalException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public GlobalException(StatusCode statusCode) {
        this.code = statusCode.getcode();
        this.message = statusCode.getdesc();
    }
    public GlobalException(String message) {
        super(message);
    }

}
