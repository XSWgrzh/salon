package com.salon.admin.service.impl;

import com.salon.admin.service.LogoutService;
import com.salon.common.core.dto.LogoutDTO;
import com.salon.common.redis.utils.RedisKeyUtil;
import com.salon.common.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service.impl
 * @Project：salon
 * @name：LogoutServiceImpl
 * @Date：2024/4/9 14:01
 */
@Service
public class LogoutServiceImpl implements LogoutService {
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public boolean logouts(LogoutDTO logoutDTO) {
        String userInfoKey = RedisKeyUtil.getUserInfoKey(logoutDTO.getToken());
        boolean delete = redisUtil.delete(userInfoKey);
        return delete;
    }
}
