package com.salon.admin.controller;

import com.salon.admin.service.MonitorsService;
import com.salon.common.core.VO.RedisCacheVO;
import com.salon.common.core.VO.ServerVO;
import com.salon.common.core.model.api.Result;
import com.salon.common.log.annotation.OperateLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.controller
 * @Project：salon
 * @name：MonitorsController
 * @Date：2024/4/26 15:18
 */
@RestController
@Tag(name = "MonitorsController", description = "监控管理")
@RequiredArgsConstructor
@RequestMapping("monitors")
public class MonitorsController {

    @Resource
    private MonitorsService monitorService;

    @GetMapping("server")
    @Operation(summary = "监控管理", description = "主机监控")
    @OperateLog(module = "监控管理", operation = "主机监控")
    public Result<ServerVO> findServerInfo() {
        return Result.success(monitorService.findServerInfo());
    }

    @GetMapping("cache")
    @Operation(summary = "监控管理", description = "缓存监控")
    @OperateLog(module = "监控管理", operation = "缓存监控")
    public Result<RedisCacheVO> findCacheInfo() {
        return Result.success(monitorService.findCacheInfo());
    }
}
