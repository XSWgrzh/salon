package com.salon.common.core.model.log;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.log
 * @Project：salon
 * @name：SysLoginLog
 * @Date：2024/4/25 9:17
 */
@Data
@TableName("salon_sys_login_log")
@Schema(name = "LoginLog", description = "登录日志")
@SuperBuilder
@NoArgsConstructor
public class SysLoginLog implements Serializable {

    @TableId(type = IdType.INPUT)
    @Schema(name = "primary_key", description = "ID")
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

    @Schema(name = "eventId", description = "事件ID")
    private Long eventId;

    @Schema(name = "create_time", description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(name = "update_time", description = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

}
