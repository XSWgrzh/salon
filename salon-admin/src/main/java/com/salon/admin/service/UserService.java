package com.salon.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salon.admin.DTO.UserListQry;
import com.salon.admin.DTO.UserProfileDTO;
import com.salon.common.core.VO.UserVO;

/**
 * @Author：xieshaowei
 * @Package：com.salon.user.controller.service
 * @Project：salon
 * @name：MenusService
 * @Date：2024/1/16 15:37
 */
public interface UserService {

    UserProfileDTO getProfile();

    Page<UserVO> findList(UserListQry qry);

    UserVO findById(Long id);

    Boolean removeUser(String[] ids);;

    Boolean createUser(UserVO userVO);

    Boolean modifyUser(UserVO userVO);

    Boolean resetPassword(String id, String password);

    void modifyStatus(String id, Integer status);
}
