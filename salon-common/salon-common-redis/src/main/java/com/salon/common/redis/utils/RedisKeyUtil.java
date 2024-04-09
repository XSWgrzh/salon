package com.salon.common.redis.utils;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.utils
 * @Project：salon
 * @name：RedisKeyUtil
 * @Date：2024/4/3 14:52
 */
public class RedisKeyUtil {

    /**
     * 验证码Key.
     * @param uuid 唯一标识
     */
    public static String getUserCaptchaKey(String uuid) {
        return "user:captcha:" + uuid;
    }

    /**
     * 菜单树Key.
     * @param userId 用户ID
     */
    public static String getMenuTreeKey(Long userId) {
        return "menu:tree:" + userId;
    }

    /**
     * 用户信息Key.
     * @param token 令牌
     */
    public static String getUserInfoKey(String token) {
        return "user:info:" + token;
    }

    /**
     * 布隆过滤器Key.
     */
    public static String getBloomFilterKey() {
        return "bloom:filter";
    }

    /**
     * OSS配置Key.
     * @param tenantId 租户ID
     */
    public static String getOssConfigKey(Long tenantId) {
        return "oss:config:" + tenantId;
    }

    /**
     * 手机验证码Key.
     * @param mobile 手机号
     */
    public static String getMobileCodeKey(String mobile) {
        return getUserCaptchaKey(mobile);
    }

    /**
     * 邮箱验证码Key.
     * @param mail 邮箱
     */
    public static String getMailCodeKey(String mail) {
        return getUserCaptchaKey(mail);
    }

    /**
     * 接口幂等性Key.
     * @param token 令牌
     */
    public static String getApiIdempotentKey(String token) {
        return "api:idempotent:" + token;
    }

    /**
     * 动态路由Key.
     */
    public static String getRouteDefinitionHashKey() {
        return "route:definition";
    }

    /**
     * 租户域名Key.
     */
    public static String getTenantDomainNameHashKey() {
        return "tenant:domain-name";
    }

    /**
     * IP缓存Key.
     * @param type 类型
     */
    public static String getIpCacheHashKey(String type) {
        return "ip:cache:" + type;
    }

}
