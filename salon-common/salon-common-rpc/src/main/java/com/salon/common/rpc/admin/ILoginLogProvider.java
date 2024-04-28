package com.salon.common.rpc.admin;

import com.salon.common.core.model.log.SysLoginLog;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.rpc.admin
 * @Project：salon
 * @name：LoginLogService
 * @Date：2024/4/25 10:58
 */
public interface ILoginLogProvider {

    boolean insert(SysLoginLog sysLoginLog);

}
