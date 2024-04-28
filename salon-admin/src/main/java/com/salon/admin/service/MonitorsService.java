package com.salon.admin.service;

import com.salon.common.core.VO.RedisCacheVO;
import com.salon.common.core.VO.ServerVO;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.service
 * @Project：salon
 * @name：monitorsService
 * @Date：2024/4/26 15:19
 */
public interface MonitorsService {
    ServerVO findServerInfo();

    RedisCacheVO findCacheInfo();
}
