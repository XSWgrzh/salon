package com.salon.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salon.admin.DTO.LoginLogListQry;
import com.salon.admin.DTO.OperateLogListQry;
import com.salon.admin.mapper.LoginLogMapper;
import com.salon.admin.mapper.OperateLogMapper;
import com.salon.admin.service.LogsService;
import com.salon.common.core.VO.LoginLogVO;
import com.salon.common.core.VO.OperateLogVO;
import com.salon.common.core.model.log.SysLoginLog;
import com.salon.common.core.model.log.SysOperateLog;
import com.salon.common.core.utils.ConvertUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service.impl
 * @Project：salon
 * @name：LogsServiceImpl
 * @Date：2024/4/26 9:21
 */
@Service
public class LogsServiceImpl implements LogsService {
    @Resource
    private OperateLogMapper operateLogMapper;
    @Resource
    private LoginLogMapper loginLogMapper;

    @Override
    public Page<OperateLogVO> findOperateList(OperateLogListQry qry) {

        List<SysOperateLog> operateList = operateLogMapper.findOperateList(qry.getModuleName(), qry.getStatus(), qry.getPageIndex(), qry.getPageSize());
        int count = operateLogMapper.findOperateCount(qry.getModuleName(), qry.getStatus());
        Page<OperateLogVO> operateLogVOPage = new Page<>(qry.getPageNum(), qry.getPageSize());
        operateLogVOPage.setRecords(ConvertUtil.sourceToTarget(operateList, OperateLogVO.class));
        operateLogVOPage.setTotal(count);
        return operateLogVOPage;
    }

    @Override
    public Page<LoginLogVO> loginList(LoginLogListQry qry) {
        List<SysLoginLog> loginLogList = loginLogMapper.findLoginLogList(qry.getUsername(), qry.getStatus(), qry.getPageIndex(), qry.getPageSize());
        int count = loginLogMapper.findLoginLogCount(qry.getUsername(), qry.getStatus());
        Page<LoginLogVO> loginLogVOPage = new Page<>(qry.getPageNum(), qry.getPageSize());
        loginLogVOPage.setRecords(ConvertUtil.sourceToTarget(loginLogList, LoginLogVO.class));
        loginLogVOPage.setTotal(count);
        return loginLogVOPage;
    }
}
