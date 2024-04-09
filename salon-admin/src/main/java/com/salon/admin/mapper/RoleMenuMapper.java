package com.salon.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salon.common.core.model.admin.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.mapper
 * @Project：salon
 * @name：RoleMenuMapper
 * @Date：2024/3/20 14:06
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<SysRoleMenu> {

    Integer insertBatchSomeColumn(Collection<SysRoleMenu> entityList);

}
