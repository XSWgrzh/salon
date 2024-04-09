package com.salon.common.core.model.admin;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.user
 * @Project：salon
 * @name：SysRoleMenu
 * @Date：2024/1/16 14:45
 */
@Data
@TableName("salon_sys_role_menu")
@Schema(name = "RoleMenuDO", description = "角色菜单")
public class SysRoleMenu extends BaseDO{

    private static final long serialVersionUID = -5855413730985647400L;

    @Schema(name = CREATOR, description = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long creator;

    @Schema(name = EDITOR, description = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long editor;

    @Schema(name = DEL_FLAG, description = "删除标识 0未删除 1已删除")
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @Schema(name = "menuId", description = "菜单ID")
    private String menuId;

    @Schema(name = "roleId", description = "角色ID")
    private String roleId;

}
