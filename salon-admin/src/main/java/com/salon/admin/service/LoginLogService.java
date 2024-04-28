package com.salon.admin.service;

import com.salon.common.core.model.log.SysLoginLog;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service
 * @Project：salon
 * @name：OperateLogService
 * @Date：2024/4/24 15:31
 */
public interface LoginLogService {
    boolean insert(SysLoginLog sysLoginLog);
}
