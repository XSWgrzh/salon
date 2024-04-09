package com.salon.admin.controller;

import com.salon.common.core.model.api.Result;
import com.salon.admin.DTO.MenuListQry;
import com.salon.admin.service.MenusService;
import com.salon.common.core.VO.MenuVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.user.controller
 * @Project：salon
 * @name：MenusController
 * @Date：2024/1/16 15:14
 */
@RestController
@Tag(name = "MenusController", description = "菜单管理")
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenusController {

    @Autowired
    private MenusService menusService;

    @GetMapping("/tree-list")
    @Operation(summary = "菜单管理", description = "树形菜单列表（用户）")
    public Result<MenuVO> treeList() {
        return Result.success(this.menusService.treeList());
    }

    @PostMapping("list")
    @Operation(summary = "菜单管理", description = "查询菜单列表")
//    @PreAuthorize("hasAuthority('menus:list')")
    public Result<List<MenuVO>> list(@RequestBody MenuListQry query) {
        return Result.success(menusService.list(query.getName()));
    }

    @GetMapping("tree")
    @Operation(summary = "菜单管理", description = "树形菜单列表")
    public Result<MenuVO> tree() {
        return Result.success(menusService.tree());
    }

    @GetMapping("{id}")
    @Operation(summary = "菜单管理", description = "查看菜单")
    public Result<MenuVO> getById(@PathVariable("id") Long id) {
        return Result.success(menusService.getById(id));
    }

    @PutMapping
    @Operation(summary = "菜单管理", description = "修改菜单")
    public Result<Boolean> update(@RequestBody MenuVO menuVO) {
        return Result.success(menusService.update(menuVO));
    }


    @PostMapping
    @Operation(summary = "菜单管理", description = "新增菜单")
    public Result<Boolean> insert(@RequestBody MenuVO menuVO) {
        return Result.success(menusService.insert(menuVO));
    }


    @DeleteMapping("{id}")
    @Operation(summary = "菜单管理", description = "删除菜单")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return Result.success(menusService.deleteById(id));
    }

    @GetMapping("{roleId}/ids")
    @Operation(summary = "菜单管理", description = "菜单树IDS")
    public Result<List<String>> ids(@PathVariable(value = "roleId") Long roleId) {
        return Result.success(menusService.ids(roleId));
    }


}
