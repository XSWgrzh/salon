package com.salon.common.core.model.log;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.log
 * @Project：salon
 * @name：OperateLog
 * @Date：2024/4/24 10:56
 */
@Data
@SuperBuilder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@TableName("salon_sys_operate_log")
@Schema(name = "SysOperateLog", description = "操作日志")
public class SysOperateLog {

    @TableId(type = IdType.INPUT)
    @Schema(name = "id", description = "ID")
    private Long id;

    @Schema(name = "name", description = "操作名称")
    private String name;

    @Schema(name = "moduleName", description = "操作的模块名称")
    private String moduleName;

    @Schema(name = "uri", description = "操作的URI")
    private String uri;

    @Schema(name = "methodName", description = "操作的方法名")
    private String methodName;

    @Schema(name = "requestType", description = "操作的请求类型")
    private String requestType;

    @Schema(name = "requestParams", description = "操作的请求参数")
    private String requestParams;

    @Schema(name = "userAgent", description = "操作的浏览器")
    private String userAgent;

    @Schema(name = "ip", description = "操作的IP地址")
    private String ip;

    @Schema(name = "address", description = "操作的归属地")
    private String address;

    @Schema(name = "status", description = "操作状态 0成功 1失败")
    private Integer status;

    @Schema(name = "operator", description = "操作人")
    private String operator;

    @Schema(name = "errorMessage", description = "错误信息")
    private String errorMessage;

    @Schema(name = "takeTime", description = "操作的消耗时间(毫秒)")
    private Long takeTime;

    @Schema(name = "", description = "事件ID")
    private Long eventId;

    @Schema(name = "createTime", description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(name = "updateTime", description = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
