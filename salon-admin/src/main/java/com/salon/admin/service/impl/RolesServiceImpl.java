package com.salon.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salon.admin.DTO.RoleListQry;
import com.salon.admin.mapper.RoleMapper;
import com.salon.admin.mapper.RoleMenuMapper;
import com.salon.admin.service.RolesService;
import com.salon.common.core.VO.OptionVO;
import com.salon.common.core.VO.RoleVO;
import com.salon.common.core.exception.GlobalException;
import com.salon.common.core.model.admin.SysRole;
import com.salon.common.core.model.admin.SysRoleMenu;
import com.salon.common.core.model.admin.SysUser;
import com.salon.common.core.utils.ConvertUtil;
import com.salon.common.core.utils.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service.impl
 * @Project：salon
 * @name：RolesServiceImpl
 * @Date：2024/3/19 16:50
 */
@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Page<SysRole> list(RoleListQry qry) {
        Page<SysRole> page = new Page<>(qry.getPageNum(), qry.getPageSize());
        Page<SysRole> sysRolePage = this.roleMapper.selectPage(page, new LambdaQueryWrapper<SysRole>().like(SysRole::getName, qry.getName()));
        page.setRecords(ConvertUtil.sourceToTarget(sysRolePage.getRecords(), SysRole.class));
        page.setTotal(sysRolePage.getTotal());
        return page;
    }

    @Override
    public List<OptionVO> optionList() {
        List<SysRole> list = roleMapper.selectList(
                new LambdaQueryWrapper<SysRole>().select(SysRole::getId,SysRole::getName));

        return list.stream().map(item->convert(item)).collect(Collectors.toList());
    }

    private OptionVO convert(SysRole sysRole){
        return OptionVO.builder().label(sysRole.getName()).value(sysRole.getId()).build();
    }

    @Override
    public RoleVO getById(Long id) {
        SysRole sysRole = this.roleMapper.selectById(id);
        return ConvertUtil.sourceToTarget(sysRole, RoleVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insert(RoleVO role) {
        Long count = this.roleMapper.selectCount(new LambdaQueryWrapper<SysRole>().eq(SysRole::getName, role.getName()));
        if (count > 0) {
            throw new GlobalException("角色已存在，请重新填写");
        }
        role.setId(IdGenerator.defaultSnowflakeId());
        boolean result = this.roleMapper.insert(ConvertUtil.sourceToTarget(role, SysRole.class)) > 0;
        result = result && insertRoleMenu(role.getId(), role.getMenuIds(),new SysUser());
        return result;
    }

    public Boolean insertRoleMenu(String roleId, List<String> menuIds, SysUser user) {
        if (CollectionUtil.isNotEmpty(menuIds)) {
//            List<SysRoleMenu> list = new ArrayList<>(menuIds.size());
            for (String menuId : menuIds) {
                this.roleMenuMapper.insert(toRoleMenu(roleId, menuId, user));
//                list.add(toRoleMenu(roleId, deptId, user));
            }

            return true;
        }
        return false;
    }

    private SysRoleMenu toRoleMenu(String roleId, String menuId, SysUser user) {
        SysRoleMenu roleMenu = new SysRoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);
        roleMenu.setCreator(1L);
        roleMenu.setEditor(0L);
        roleMenu.setDelFlag(0);
        roleMenu.setId(IdGenerator.defaultSnowflakeId());
        return roleMenu;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(RoleVO role) {
        boolean flag;
        flag = this.roleMapper.updateById(ConvertUtil.sourceToTarget(role, SysRole.class)) > 0;
        flag = flag && roleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId,role.getId())) >=0;
        return flag && insertRoleMenu(role.getId(), role.getMenuIds(), new SysUser());
    }

    @Override
    public Boolean deleteById(Long id) {
        this.roleMapper.deleteById(id);
        return this.roleMapper.deleteById(id) > 0;
    }
}
