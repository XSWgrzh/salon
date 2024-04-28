package com.salon.admin.service.impl;

import com.salon.admin.mapper.OperateLogMapper;
import com.salon.common.core.model.log.SysOperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service.impl
 * @Project：salon
 * @name：OperateLogService
 * @Date：2024/4/24 15:33
 */
@Service
public class OperateLogServiceImpl implements com.salon.admin.service.OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public boolean insert(SysOperateLog sysOperateLog) {
        int insert = operateLogMapper.insert(sysOperateLog);
        return insert > 0;
    }
}
