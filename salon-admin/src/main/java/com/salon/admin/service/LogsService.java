package com.salon.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salon.admin.DTO.LoginLogListQry;
import com.salon.admin.DTO.OperateLogListQry;
import com.salon.common.core.VO.LoginLogVO;
import com.salon.common.core.VO.OperateLogVO;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service
 * @Project：salon
 * @name：LogsService
 * @Date：2024/4/26 9:14
 */
public interface LogsService {
    Page<OperateLogVO> findOperateList(OperateLogListQry qry);

    Page<LoginLogVO> loginList(LoginLogListQry qry);
}
