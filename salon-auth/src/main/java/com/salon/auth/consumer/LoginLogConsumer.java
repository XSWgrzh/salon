package com.salon.auth.consumer;

import com.salon.common.core.model.log.SysLoginLog;
import com.salon.common.rpc.admin.ILoginLogProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.consumer
 * @Project：salon
 * @name：LoginLogConsumer
 * @Date：2024/4/25 11:36
 */
@Slf4j
@Service
public class LoginLogConsumer {

    @Reference(check = false)
    private ILoginLogProvider loginLogProvider;

    public void insertLoginLog(SysLoginLog sysLoginLog) {
        try {
            loginLogProvider.insert(sysLoginLog);
        } catch (Exception e) {
            log.info("调用失败");
            log.error(e.toString());
        }
    }

}
