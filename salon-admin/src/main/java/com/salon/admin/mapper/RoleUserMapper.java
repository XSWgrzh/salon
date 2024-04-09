package com.salon.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salon.common.core.model.admin.SysRoleUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.mapper
 * @Project：salon
 * @name：RoleUserMapper
 * @Date：2024/3/26 11:17
 */
@Mapper
public interface RoleUserMapper extends BaseMapper<SysRoleUser> {
    Integer insertList(List<SysRoleUser> list);
}
