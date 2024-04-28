package com.salon.admin.controller;

import com.salon.common.core.model.api.Result;
import com.salon.common.log.annotation.OperateLog;
import com.salon.utils.UserDetail;
import com.salon.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.controller
 * @Project：salon
 * @name：TestController
 * @Date：2024/3/28 17:15
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class TestController {

    @OperateLog(module = "测试模块",operation = "testget")
    @GetMapping
    public Result get() {
        Authentication authentication = UserUtil.getAuthentication();
        String userId = UserUtil.getUserId();
        String userName = UserUtil.getUserName();
//        throw new GlobalException(StatusCode.UNKNOWN.getcode(), StatusCode.UNKNOWN.getdesc());
        return Result.success(userName);

    }

    @GetMapping("2")
    public Result get2() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (authentication.isAuthenticated()) {
            log.info("已认证");
        }
        if (principal instanceof UserDetail) {
            return Result.success("UserDetail");
        }
        if (principal instanceof Principal) {
            return Result.success("Principal");
        }
        if (principal instanceof Jwt) {
            return Result.success("jwt");
        }
        return Result.success(false);


    }
}
