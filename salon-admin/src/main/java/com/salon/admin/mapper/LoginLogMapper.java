package com.salon.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salon.common.core.model.log.SysLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.mapper
 * @Project：salon
 * @name：OperateLogMapper
 * @Date：2024/4/24 15:30
 */
@Mapper
public interface LoginLogMapper extends BaseMapper<SysLoginLog> {
    List<SysLoginLog> findLoginLogList(@Param("username")String username, @Param("status")Integer status, @Param("pageIndex")Integer pageIndex, @Param("pageSize")Integer pageSize);

    int findLoginLogCount(@Param("username")String username, @Param("status")Integer status);
}
