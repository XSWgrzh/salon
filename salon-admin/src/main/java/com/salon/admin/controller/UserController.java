package com.salon.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salon.admin.DTO.UserListQry;
import com.salon.admin.DTO.UserProfileDTO;
import com.salon.admin.service.UserService;
import com.salon.common.core.VO.UserVO;
import com.salon.common.core.model.api.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：xieshaowei
 * @Package：com.salon.user.controller
 * @Project：salon
 * @name：UserController
 * @Date：2024/2/20 16:04
 */
@RestController
@Tag(name = "UserController", description = "用户管理")
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    @Autowired
    private final UserService usersService;

    @GetMapping("profile")
    @Operation(summary = "个人中心", description = "查看个人信息")
    public Result<UserProfileDTO> getProfile() {
        return Result.success(usersService.getProfile());
    }

    @PostMapping("list")
    @Operation(summary = "用户管理", description = "查询用户列表")
    public Result<Page<UserVO>> findList(@RequestBody UserListQry qry) {
        return Result.success(usersService.findList(qry));
    }

    @PostMapping
    @Operation(summary = "用户管理", description = "新增用户")
    public Result<Boolean> create(@RequestBody UserVO userVO) {
        return Result.success(usersService.createUser(userVO));
    }

    @GetMapping("{id}")
    @Operation(summary = "用户管理", description = "查看用户")
    public Result<UserVO> findById(@PathVariable("id") Long id) {
        return Result.success(usersService.findById(id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "用户管理", description = "删除用户")
    public Result<Boolean> remove(@PathVariable("id") String id) {
        return Result.success(usersService.removeUser(id.split(",")));
    }

    @DeleteMapping
    @Operation(summary = "用户管理", description = "删除用户")
    public Result<Boolean> remove(@RequestBody String[] ids) {
        return Result.success(usersService.removeUser(ids));
    }

    @PutMapping
    @Operation(summary = "用户管理", description = "修改用户")
    public Result<Boolean> modify(@RequestBody UserVO userVO) {
        return Result.success(usersService.modifyUser(userVO));
    }

    @PutMapping("reset-password")
    @Operation(summary = "用户管理", description = "重置密码")
    public void resetPassword(@RequestBody UserVO userVO) {
        usersService.resetPassword(userVO.getId(), userVO.getPassword());
    }

    @PutMapping("status")
    @Operation(summary = "用户管理", description = "修改用户状态")
    public void modifyStatus(@RequestBody UserVO userVO) {
        usersService.modifyStatus(userVO.getId(), userVO.getStatus());
    }

}
