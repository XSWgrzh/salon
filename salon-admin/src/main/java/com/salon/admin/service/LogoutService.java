package com.salon.admin.service;

import com.salon.common.core.dto.LogoutDTO;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service
 * @Project：salon
 * @name：LogoutService
 * @Date：2024/4/9 14:01
 */
public interface LogoutService {
    boolean logouts(LogoutDTO logoutDTO);
}
