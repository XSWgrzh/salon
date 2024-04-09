package com.salon.common.core.model.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.user
 * @Project：salon
 * @name：SysRole
 * @Date：2023/10/25 9:28
 */
@Data
@TableName("salon_sys_role")
public class SysRole extends BaseDO implements Serializable {


    /**
     * 角色code
     */
    private String code;

    /**
     * 角色名
     */
    private String name;

    /**
     * 数据权限范围配置：ALL/全部权限，CREATOR/创建者权限
     */
    private String dataScope;

    /**
     * 创建人id
     */
    private int creatorId;


}
