package com.salon.common.core.model.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.user
 * @Project：salon
 * @name：SysRoleUser
 * @Date：2023/10/25 9:32
 */
@Data
@TableName("salon_sys_role_user")
@AllArgsConstructor
public class SysRoleUser implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;
}
