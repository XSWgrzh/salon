package com.salon.common.core.constant;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.constant
 * @Project：salon
 * @name：Constants
 * @Date：2024/4/8 9:01
 */
@Schema(name = "Constants", description = "字符串常量")
public class Constants {

    @Schema(name = "RISK", description = "分割参数")
    public static final String RISK = ":";
}
