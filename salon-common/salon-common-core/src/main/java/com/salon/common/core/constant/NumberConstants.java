package com.salon.common.core.constant;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.constant
 * @Project：salon
 * @name：NumberConstants
 * @Date：2024/4/24 12:02
 */
public class NumberConstants {

    private NumberConstants() {
    }

    @Schema(name = "DEFAULT", description = "默认")
    public static final int DEFAULT = 0;

    @Schema(name = "SUCCESS", description = "成功")
    public static final int SUCCESS = 0;

    @Schema(name = "FAIL", description = "失败")
    public static final int FAIL = 1;

}
