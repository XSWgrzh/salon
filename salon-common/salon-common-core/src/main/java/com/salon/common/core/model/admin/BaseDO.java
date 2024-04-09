package com.salon.common.core.model.admin;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.user
 * @Project：salon
 * @name：BaseDO
 * @Date：2024/1/16 14:53
 */
@Data
//@Schema(name = "BaseDO", description = "映射基类")
public class BaseDO {

    public static final String ID = "id";

    public static final String CREATOR = "creator";

    public static final String EDITOR = "editor";

    public static final String CREATE_TIME = "createTime";

    public static final String UPDATE_Time = "updateTime";

    public static final String DEL_FLAG = "delFlag";

    public static final String VERSION = "version";

    public static final String DEPT_ID = "deptId";

    public static final String DEPT_PATH = "deptPath";

    public static final String TENANT_ID = "tenantId";

    @TableId(type = IdType.INPUT)
    @Schema(name = ID, description = "ID")
    private String id;

//    @Schema(name = CREATOR, description = "创建人")
//    @TableField(fill = FieldFill.INSERT)
//    private Long creator;

//    @Schema(name = EDITOR, description = "修改人")
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private Long editor;

    @Schema(name = CREATE_TIME, description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(name = UPDATE_Time, description = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

//    @Schema(name = DEL_FLAG, description = "删除标识 0未删除 1已删除")
//    @TableField(fill = FieldFill.INSERT)
//    @TableLogic
//    private Integer delFlag;

//    @Version
//    @Schema(name = VERSION, description = "版本号")
//    @TableField(fill = FieldFill.INSERT)
//    private Integer version;
//
//    @Schema(name = DEPT_ID, description = "部门ID")
//    @TableField(fill = FieldFill.INSERT)
//    private Long deptId;
//
//    @Schema(name = DEPT_PATH, description = "部门PATH")
//    @TableField(fill = FieldFill.INSERT)
//    private String deptPath;
//
//    @Schema(name = TENANT_ID, description = "租户ID")
//    @TableField(fill = FieldFill.INSERT)
//    private Long tenantId;

}
