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
public class StringConstants {

    @Schema(name = "RISK", description = "分割参数")
    public static final String RISK = ":";

    @Schema(name = "DOT", description = "分割参数")
    public static final String DOT = ".";

    @Schema(name = "COMMA", description = "分割参数")
    public static final String COMMA = ",";

    @Schema(name = "LEFT", description = "左括号")
    public static final String LEFT = "(";

    @Schema(name = "RIGHT", description = "右括号")
    public static final String RIGHT = ")";
}
