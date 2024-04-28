package com.salon.admin.service.impl;

import com.salon.admin.mapper.LoginLogMapper;
import com.salon.admin.service.LoginLogService;
import com.salon.common.core.model.log.SysLoginLog;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service.impl
 * @Project：salon
 * @name：OperateLogService
 * @Date：2024/4/24 15:33
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public boolean insert(SysLoginLog sysLoginLog) {
        int insert = loginLogMapper.insert(sysLoginLog);
        return insert > 0;
    }
}
