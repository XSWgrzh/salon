package com.salon.admin.provider;

import com.salon.admin.service.LoginLogService;
import com.salon.common.core.model.log.SysLoginLog;
import com.salon.common.rpc.admin.ILoginLogProvider;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.provide
 * @Project：salon
 * @name：LoginLogProvider
 * @Date：2024/4/25 11:04
 */
@Service
public class LoginLogProvider implements ILoginLogProvider {

    @Resource
    private LoginLogService loginLogService;

    @Override
    public boolean insert(SysLoginLog sysLoginLog) {
        return loginLogService.insert(sysLoginLog);
    }
}
