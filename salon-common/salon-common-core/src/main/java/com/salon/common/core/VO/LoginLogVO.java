package com.salon.common.core.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.VO
 * @Project：salon
 * @name：LoginLogVO
 * @Date：2024/4/26 9:10
 */
@Data
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Schema(name = "LoginLogVO", description = "登录日志")
public class LoginLogVO {

    @Schema(name = "id", description = "ID")
    private Long id;

    @Schema(name = "username", description = "登录的用户名")
    private String username;

    @Schema(name = "ip", description = "登录的IP地址")
    private String ip;

    @Schema(name = "address", description = "登录的归属地")
    private String address;

    @Schema(name = "browser", description = "登录的浏览器")
    private String browser;

    @Schema(name = "os", description = "登录的操作系统")
    private String os;

    @Schema(name = "status", description = "登录状态 0登录成功 1登录失败")
    private Integer status;

    @Schema(name = "message", description = "登录信息")
    private String message;

    @Schema(name = "type", description = "登录类型")
    private String type;

    @Schema(name = "createTime", description = "创建时间")
    private LocalDateTime createTime;

}
