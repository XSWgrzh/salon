package com.salon.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salon.admin.DTO.RoleListQry;
import com.salon.admin.service.RolesService;
import com.salon.common.core.VO.OptionVO;
import com.salon.common.core.VO.RoleVO;
import com.salon.common.core.model.admin.SysRole;
import com.salon.common.core.model.api.Result;
import com.salon.common.log.annotation.OperateLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.controller
 * @Project：salon
 * @name：RolesController
 * @Date：2024/3/19 16:43
 */
@RestController
@Tag(name = "RolesController", description = "角色管理")
@RequiredArgsConstructor
@RequestMapping("roles")
public class RolesController {

    @Autowired
    private final RolesService rolesService;

    @PostMapping("list")
    @Operation(summary = "角色管理", description = "查询角色列表")
    @OperateLog(module = "角色管理", operation = "查询角色列表")
    public Result<Page<SysRole>> list(@RequestBody RoleListQry qry) {
        return Result.success(rolesService.list(qry));

    }

    @GetMapping("option-list")
    @Operation(summary = "角色管理", description = "下拉列表")
    @OperateLog(module = "角色管理", operation = "下拉列表")
    public Result<List<OptionVO>> optionList() {
        return Result.success(rolesService.optionList());
    }

    @GetMapping("{id}")
    @Operation(summary = "角色管理", description = "查看角色")
    @OperateLog(module = "角色管理", operation = "查看角色")
    public Result<RoleVO> getById(@PathVariable("id") Long id) {
        return Result.success(rolesService.getById(id));
    }

    @PostMapping
    @Operation(summary = "角色管理", description = "新增角色")
    @OperateLog(module = "角色管理", operation = "新增角色")
    public Result<Boolean> insert(@RequestBody RoleVO role) {
        return Result.success(rolesService.insert(role));
    }


    @PutMapping
    @Operation(summary = "角色管理", description = "修改角色")
    @OperateLog(module = "角色管理", operation = "修改角色")
    public Result<Boolean> update(@RequestBody RoleVO role) {
        return Result.success(rolesService.update(role));
    }


    @DeleteMapping("{id}")
    @Operation(summary = "角色管理", description = "删除角色")
    @OperateLog(module = "角色管理", operation = "删除角色")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.success(rolesService.deleteById(id));
    }
    
}
