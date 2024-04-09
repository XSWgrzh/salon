package com.salon.common.redis.annotation;

import com.salon.common.redis.config.RedisAutoConfig;
import com.salon.common.redis.config.RedissonConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.redis.annotation
 * @Project：salon
 * @name：EnableRedisRepository
 * @Date：2024/4/8 9:07
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RedisAutoConfig.class, RedissonConfig.class})
public @interface EnableRedisRepository {
}
