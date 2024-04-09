package com.salon.admin.service;

import com.salon.common.core.VO.MenuVO;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.user.controller.service
 * @Project：salon
 * @name：MenusService
 * @Date：2024/1/16 15:37
 */
public interface MenusService {

    MenuVO treeList();

    List<MenuVO> list(String name);

    boolean insert(MenuVO cmd);

    boolean update(MenuVO cmd);

    boolean deleteById(Long id);

    MenuVO tree();

    MenuVO getById(Long id);

    List<String> ids(Long roleId);
}
