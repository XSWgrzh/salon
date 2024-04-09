package com.salon.common.core.model.admin;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.user
 * @Project：salon
 * @name：sys_user
 * @Date：2023/10/25 9:18
 */
@Data
@TableName("salon_sys_user")
public class SysUser extends BaseDO implements Serializable {

//    /**
//     * 主键id
//     */
//    @TableId(type = IdType.AUTO)
//    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 性别 0女 1男
     */
    private int sex;

    /**
     * 是否开启
     */
    private int status;

    /**
     * 公司
     */
    private String company;


    /**
     * 创建人id
     */
    private int creatorId;

}
