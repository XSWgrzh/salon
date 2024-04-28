package com.salon.common.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core
 * @Project：salon
 * @name：LogoutCmd
 * @Date：2024/4/9 14:04
 */
@Data
@Schema(name = "LogoutCmd", description = "退出登录命令请求")
public class LogoutDTO {
    @Schema(name = "token", description = "令牌")
    private String token;
}
