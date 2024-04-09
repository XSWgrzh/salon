package com.salon.common.redis.config;

import cn.hutool.core.util.ObjectUtil;
import com.salon.common.redis.utils.RedisKeyUtil;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.salon.common.core.constant.Constants.RISK;
import static com.salon.common.redis.constant.RedisConstants.REDISS_PROTOCOL_PREFIX;
import static com.salon.common.redis.constant.RedisConstants.REDIS_PROTOCOL_PREFIX;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.redis
 * @Project：salon
 * @name：configRedissonConfig
 * @Date：2024/4/8 8:56
 */
@ConditionalOnClass(Redisson.class)
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedissonConfig {

    @Bean
    public RBloomFilter<String> bloomFilter(RedissonClient redisson) {
        RBloomFilter<String> bloomFilter = redisson.getBloomFilter(RedisKeyUtil.getBloomFilterKey());
        bloomFilter.tryInit(10000, 0.01);
        return bloomFilter;
    }

    /**
     * redisson配置.
     * @param properties redis配置文件
     * @return RedissonClient
     */
    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redisClient(RedisProperties properties) {
        Config config = new Config();
        int timeout = (int) properties.getTimeout().toMillis();
        int connectTimeout = (int) properties.getConnectTimeout().toMillis();
        boolean isSsl = properties.isSsl();
        if (ObjectUtil.isNotNull(properties.getSentinel())) {
            config.useSentinelServers()
                    .setMasterName(properties.getSentinel().getMaster())
                    .addSentinelAddress(convertNodes(isSsl, properties.getSentinel().getNodes()))
                    .setDatabase(properties.getDatabase())
                    .setTimeout(timeout)
                    .setConnectTimeout(connectTimeout)
                    .setPassword(properties.getPassword());
        }
        else if (ObjectUtil.isNotNull(properties.getCluster())) {
            config.useClusterServers()
                    .addNodeAddress(convertNodes(isSsl, properties.getCluster().getNodes()))
                    .setPassword(properties.getPassword())
                    .setTimeout(timeout)
                    .setConnectTimeout(connectTimeout);
        }
        else {
            config.useSingleServer()
                    .setAddress(convertAddress(isSsl, properties.getHost(), properties.getPort()))
                    .setDatabase(properties.getDatabase())
                    .setPassword(properties.getPassword())
                    .setConnectTimeout(connectTimeout)
                    .setTimeout(timeout);
        }
        // 使用json序列化方式
        config.setCodec(GlobalJsonJacksonCodec.INSTANCE);
        return Redisson.create(config);
    }

    private String getProtocolPrefix(boolean isSsl) {
        return isSsl ? REDISS_PROTOCOL_PREFIX : REDIS_PROTOCOL_PREFIX;
    }

    private String convertAddress(boolean isSsl, String host, int port) {
        return getProtocolPrefix(isSsl) + host + RISK + port;
    }

    private String[] convertNodes(boolean isSsl, List<String> nodeList) {
        String[] nodes = new String[nodeList.size()];
        for (int i = 0; i < nodeList.size(); i++) {
            String node = nodeList.get(i);
            if (node.startsWith(REDISS_PROTOCOL_PREFIX) || node.startsWith(REDIS_PROTOCOL_PREFIX)) {
                nodes[i] = node;
            }
            else {
                nodes[i] = getProtocolPrefix(isSsl) + node;
            } 
        }
        return nodes;
    }

}
