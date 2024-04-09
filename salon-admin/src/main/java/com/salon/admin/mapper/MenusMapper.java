package com.salon.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.salon.common.core.model.admin.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.user.mapper
 * @Project：salon
 * @name：MenusMapper
 * @Date：2024/1/16 16:21
 */
@Mapper
public interface MenusMapper extends BaseMapper<SysMenu> {

    List<SysMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询权限标识
     * @return List<String>
     */
    List<String> getPermissions();

    List<String> getMenuIdsByRoleId(Long roleId);
}
