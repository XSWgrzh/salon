package com.salon.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salon.admin.DTO.RoleListQry;
import com.salon.common.core.VO.OptionVO;
import com.salon.common.core.VO.RoleVO;
import com.salon.common.core.model.admin.SysRole;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service
 * @Project：salon
 * @name：RolesService
 * @Date：2024/3/19 16:47
 */
public interface RolesService {
    Page<SysRole> list(RoleListQry qry);

    RoleVO getById(Long id);

    Boolean insert(RoleVO role);

    Boolean update(RoleVO role);

    Boolean deleteById(Long id);

    List<OptionVO> optionList();
}
