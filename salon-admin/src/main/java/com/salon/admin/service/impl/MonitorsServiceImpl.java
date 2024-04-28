package com.salon.admin.service.impl;

import com.salon.admin.service.MonitorsService;
import com.salon.common.core.VO.RedisCacheVO;
import com.salon.common.core.VO.ServerVO;
import com.salon.common.redis.utils.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service.impl
 * @Project：salon
 * @name：MonitorsServiceImpl
 * @Date：2024/4/26 15:24
 */
@Service
public class MonitorsServiceImpl implements MonitorsService {
    @Resource
    private RedisUtil redisUtil;

    @Override
    public ServerVO findServerInfo() {
        ServerVO serverVO = new ServerVO();
        serverVO.copyTo();
        return serverVO;
    }

    @Override
    public RedisCacheVO findCacheInfo() {
        return RedisCacheVO.builder()
                .commandStats(redisUtil.getCommandStatus())
                .info(redisUtil.getInfo())
                .keysSize(redisUtil.getKeysSize())
                .build();
    }
}
