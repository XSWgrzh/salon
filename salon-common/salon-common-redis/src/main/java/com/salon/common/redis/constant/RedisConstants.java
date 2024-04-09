package com.salon.common.redis.constant;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.redis.constant
 * @Project：salon
 * @name：RedisConstants
 * @Date：2024/4/8 9:00
 */
@Schema(name = "SysConstants", description = "redis变量")
public class RedisConstants {

    @Schema(name = "REDIS_PROTOCOL_PREFIX", description = "Redis未加密连接")
    public static final String REDIS_PROTOCOL_PREFIX = "redis://";

    @Schema(name = "REDISS_PROTOCOL_PREFIX", description = "Redis加密连接")
    public static final String REDISS_PROTOCOL_PREFIX = "rediss://";
}
