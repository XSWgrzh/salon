package com.salon.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salon.common.core.model.admin.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.mapper
 * @Project：salon
 * @name：RoleMapper
 * @Date：2024/3/19 17:02
 */
@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {
}
