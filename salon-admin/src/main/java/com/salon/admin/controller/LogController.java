package com.salon.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.salon.admin.DTO.LoginLogListQry;
import com.salon.admin.DTO.OperateLogListQry;
import com.salon.admin.service.LogsService;
import com.salon.common.core.VO.LoginLogVO;
import com.salon.common.core.VO.OperateLogVO;
import com.salon.common.core.model.api.Result;
import com.salon.common.log.annotation.OperateLog;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.controller
 * @Project：salon
 * @name：LogController
 * @Date：2024/4/26 9:00
 */
@RestController
@RequestMapping("logs")
public class LogController {
    @Resource
    private LogsService logsService;

    @PostMapping("operate-list")
    @Operation(summary = "日志管理", description = "查询操作日志列表")
    @OperateLog(module = "日志管理", operation = "查询操作日志列表")
//    @PreAuthorize("hasAuthority('logs:operate-list')")
    public Result<Page<OperateLogVO>> findOperateList(@RequestBody OperateLogListQry qry) {
        return Result.success(logsService.findOperateList(qry));
    }

//    @PostMapping("export-operate")
//    @Operation(summary = "日志管理", description = "导出操作日志")
//    @PreAuthorize("hasAuthority('logs:export-operate')")
//    @OperateLog(module = "日志管理", operation = "导出操作日志")
//    public void exportOperate(@RequestBody OperateLogExportCmd cmd, HttpServletResponse response) {
//        logsService.exportOperate(cmd.response(response));
//    }

    @PostMapping("login-list")
    @Operation(summary = "日志管理", description = "查询登录日志列表")
    @OperateLog(module = "日志管理", operation = "查询登录日志列表")
//    @PreAuthorize("hasAuthority('logs:login-list')")
    public Result<Page<LoginLogVO>> findLoginList(@RequestBody LoginLogListQry qry) {
        return Result.success(logsService.loginList(qry));
    }

//    @PostMapping("export-login")
//    @Operation(summary = "日志管理", description = "导出登录日志")
//    @PreAuthorize("hasAuthority('logs:export-login')")
//    @OperateLog(module = "日志管理", operation = "导出登录日志")
//    public void exportLogin(@RequestBody LoginLogExportCmd cmd, HttpServletResponse response) {
//        logsService.exportLogin(cmd.response(response));
//    }

}
