package com.salon.common.core.constant;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.constant
 * @Project：salon
 * @name：PropertiesConstants
 * @Date：2024/4/24 11:28
 */
@Schema(name = "PropertiesConstants", description = "Properties常量")
public final class PropertiesConstants {

    private PropertiesConstants() {
    }

    @Schema(name = "SPRING_APPLICATION_NAME", description = "应用名称")
    public static final String SPRING_APPLICATION_NAME = "spring.application.name";


}
