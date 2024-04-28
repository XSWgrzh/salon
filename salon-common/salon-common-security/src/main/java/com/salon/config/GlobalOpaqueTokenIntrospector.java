package com.salon.config;

import cn.hutool.core.util.ObjectUtil;
import com.salon.common.core.model.StatusCode;
import com.salon.common.redis.utils.RedisKeyUtil;
import com.salon.common.redis.utils.RedisUtil;
import com.salon.handler.OAuth2ExceptionHandler;
import com.salon.utils.UserDetail;
import io.micrometer.common.lang.NonNullApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author：xieshaowei
 * @Package：com.salon.config
 * @Project：salon
 * @name：GlobalOpaqueTokenIntrospector
 * @Date：2024/4/3 13:52
 */
@Slf4j
@NonNullApi
@AutoConfiguration
@RequiredArgsConstructor
public class GlobalOpaqueTokenIntrospector implements OpaqueTokenIntrospector, WebMvcConfigurer {

    private final RedisUtil redisUtil;

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        // 用户相关数据，低命中率且数据庞大放redis稳妥，分布式集群需要通过redis实现数据共享
        String userInfoKey = RedisKeyUtil.getUserInfoKey(token);
        Object obj = redisUtil.get(userInfoKey);
        if (ObjectUtil.isNotNull(obj)) {
            // 解密
            return decryptInfo((UserDetail) obj);
        }
        throw OAuth2ExceptionHandler.getException(StatusCode.UNAUTHORIZED.getcode(), StatusCode.UNAUTHORIZED.getdesc());
//        throw new GlobalException(StatusCode.UNAUTHORIZED.getcode(), StatusCode.UNAUTHORIZED.getdesc());
//        throw OAuth2ExceptionHandler.getException(StatusCode.UNAUTHORIZED.getcode(), StatusCode.UNAUTHORIZED.getdesc());

    }
    // @formatter:on

    /**
     * 解密字段.
     *
     * @param userDetail 用户信息
     * @return UserDetail
     */
    private UserDetail decryptInfo(UserDetail userDetail) {
        // 解密
        //userDetail.decrypt();
        return userDetail;
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(userContextInterceptor);
//    }
}
