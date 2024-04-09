package com.salon.auth.service;

import com.salon.common.core.model.admin.SysUser;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.service
 * @Project：salon
 * @name：SysUserService
 * @Date：2023/11/2 15:27
 */
public interface SysUserService {

    SysUser selectByUsername(String username);

}
