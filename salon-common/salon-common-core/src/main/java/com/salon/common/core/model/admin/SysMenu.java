package com.salon.common.core.model.admin;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.user
 * @Project：salon
 * @name：SysMenu
 * @Date：2024/1/16 14:45
 */
@Data
@TableName("salon_sys_menu")
//@Schema(name = "RoleMenuDO", description = "菜单")
public class SysMenu extends BaseDO{

    private static final long serialVersionUID = -5855413730985647400L;

    @Schema(name = "pid", description = "父ID", example = "0")
    private String pid;

    @Schema(name = "name", description = "名称", example = "用户管理")
    private String name;

    @Schema(name = "url", description = "路径", example = "/v1/users/profile")
    private String url;

    @Schema(name = "permission", description = "权限标识", example = "users:list")
    private String permission;

    @Schema(name = "icon", description = "图标", example = "user")
    private String icon;

    @Schema(name = "sort", description = "排序", example = "1")
    private Integer sort;

    @Schema(name = "type", description = "类型 0菜单 1按钮", example = "0")
    private Integer type;

    @Schema(name = "visible", description = "状态 0显示 1隐藏", example = "0")
    private Integer visible;

}
