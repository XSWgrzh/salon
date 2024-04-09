package com.salon.common.core.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.VO
 * @Project：salon
 * @name：UserVO
 * @Date：2024/3/21 14:38
 */
@Data
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Schema(name = "UserVO", description = "用户")
public class UserVO {
    @Schema(name = "id", description = "ID")
    private String id;

    @Schema(name = "username", description = "用户名")
    private String username;

    @Schema(name = "status", description = "用户状态 0正常 1锁定")
    private Integer status;

    @Schema(name = "roleIds", description = "角色IDS")
    private List<String> roleIds;

    @Schema(name = "password", description = "密码")
    private String password;

    @Schema(name = "avatar", description = "头像")
    private String avatar;

    @Schema(name = "mail", description = "邮箱")
    private String mail;

    @Schema(name = "mobile", description = "手机号")
    private String mobile;

    @Schema(name = "editor", description = "编辑人")
    private Long editor;

    @Schema(name = "createDate", description = "创建时间")
    private LocalDateTime createTime;

    @Schema(name = "superAdmin", description = "超级管理员标识 0否 1是")
    private Integer superAdmin;
}
