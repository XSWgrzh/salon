package com.salon.common.redis.config;

import com.salon.common.redis.utils.RedisUtil;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static com.salon.common.redis.config.GlobalJsonJacksonCodec.getJsonRedisSerializer;
import static com.salon.common.redis.config.GlobalJsonJacksonCodec.getStringRedisSerializer;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.redis.config
 * @Project：salon
 * @name：RedisAutoConfig
 * @Date：2024/4/8 9:05
 * redis配置
 */
@AutoConfiguration
@ConditionalOnClass(LettuceConnectionFactory.class)
public class RedisAutoConfig {

    /**
     * 自定义RedisTemplate.
     * @param lettuceConnectionFactory 工厂
     * @return RedisTemplate
     */
    @Bean("redisTemplate")
    @ConditionalOnMissingBean(RedisTemplate.class)
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = getJsonRedisSerializer();
        // string序列化
        StringRedisSerializer stringRedisSerializer = getStringRedisSerializer();
        // key
        redisTemplate.setKeySerializer(stringRedisSerializer);
        // value
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // hash-key
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        // hash-value
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // 初始化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisUtil redisUtil(RedissonClient redissonClient, RedisTemplate<String, Object> redisTemplate) {
        return new RedisUtil(redisTemplate, redissonClient);
    }

}
