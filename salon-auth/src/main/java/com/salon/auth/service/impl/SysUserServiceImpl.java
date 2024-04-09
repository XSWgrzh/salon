package com.salon.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salon.auth.mapper.SysUserMapper;
import com.salon.auth.service.SysUserService;
import com.salon.common.core.model.admin.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.service.impl
 * @Project：salon
 * @name：SysUserServiceImpl
 * @Date：2023/11/2 15:28
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser selectByUsername(String username) {

        return sysUserMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));

    }
}
