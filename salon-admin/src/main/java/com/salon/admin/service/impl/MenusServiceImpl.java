package com.salon.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.salon.admin.mapper.MenusMapper;
import com.salon.admin.service.MenusService;
import com.salon.common.core.VO.MenuVO;
import com.salon.common.core.exception.GlobalException;
import com.salon.common.core.model.admin.SysMenu;
import com.salon.common.core.utils.ConvertUtil;
import com.salon.common.core.utils.IdGenerator;
import com.salon.common.core.utils.TreeUtil;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.user.controller.service.impl
 * @Project：salon
 * @name：MenusServiceImpl
 * @Date：2024/1/16 16:00
 */
@Slf4j
@Service
public class MenusServiceImpl implements MenusService {

    @Autowired
    private MenusMapper menusMapper;

    @Override
    public MenuVO treeList() {

        List<SysMenu> menuListByRoleId = this.menusMapper.getMenuListByRoleId(139167754288778857L);
//        List<SysMenu> menuListByRoleId = this.menusMapper.selectList(new LambdaQueryWrapper<>());
        List<MenuVO> menuVOS = ConvertUtil.sourceToTarget(menuListByRoleId, MenuVO.class);
        MenuVO menuVO = TreeUtil.buildTreeNode(menuVOS, MenuVO.class);
        return menuVO;
    }

    @Override
    public List<MenuVO> list(String name) {
        LambdaQueryWrapper<SysMenu> sysMenuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(!StringUtil.isBlank(name)){
            sysMenuLambdaQueryWrapper.like(SysMenu::getName, name);
        }
        List<SysMenu> sysMenus = this.menusMapper.selectList(sysMenuLambdaQueryWrapper);
        List<MenuVO> menuVOS = ConvertUtil.sourceToTarget(sysMenus, MenuVO.class);
        return menuVOS;
    }

    @Override
    public MenuVO tree() {
        List<SysMenu> sysMenus = this.menusMapper.selectList(new LambdaQueryWrapper<>());
        List<MenuVO> menuVOS = ConvertUtil.sourceToTarget(sysMenus, MenuVO.class);
        MenuVO menuVO = TreeUtil.buildTreeNode(menuVOS, MenuVO.class);
        return menuVO;
    }

    @Override
    public MenuVO getById(Long id) {
        SysMenu sysMenu = this.menusMapper.selectById(id);
        return ConvertUtil.sourceToTarget(sysMenu, MenuVO.class);
    }

    @Override
    public boolean insert(MenuVO menuVO) {
        Long count = this.menusMapper.selectCount(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getName, menuVO.getName()));
        if(count > 0){
            throw new GlobalException("菜单已存在，请重新填写");
        }
        menuVO.setId(IdGenerator.defaultSnowflakeId());
        int insert = this.menusMapper.insert(ConvertUtil.sourceToTarget(menuVO, SysMenu.class));
        return insert > 0;
    }

    @Override
    public boolean update(MenuVO cmd) {

        return this.menusMapper.updateById(ConvertUtil.sourceToTarget(cmd, SysMenu.class)) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        return this.menusMapper.deleteById(id) > 0;
    }

    @Override
    public List<String> ids(Long roleId) {
        return this.menusMapper.getMenuIdsByRoleId(roleId);
    }
}
