package com.salon.admin.controller;

import com.salon.admin.service.LogoutService;
import com.salon.common.core.dto.LogoutDTO;
import com.salon.common.core.model.api.Result;
import com.salon.common.log.annotation.OperateLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：xieshaowei
 * @Package：com.salon.user.controller
 * @Project：salon
 * @name：UserController
 * @Date：2024/2/20 16:04
 */
@RestController
@Tag(name = "LogoutController", description = "退出登录")
@RequiredArgsConstructor
@RequestMapping("logouts")
public class LogoutController {

    @Autowired
    private final LogoutService logoutService;

    @PostMapping
    @Operation(summary = "退出", description = "退出登录")
    @OperateLog(module = "退出", operation = "退出登录")
    public Result<Boolean> logouts(LogoutDTO logoutDTO) {
        return Result.success(logoutService.logouts(logoutDTO));
    }

}
