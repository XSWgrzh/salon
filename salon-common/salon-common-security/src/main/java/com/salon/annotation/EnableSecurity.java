package com.salon.annotation;

import com.salon.config.GlobalOpaqueTokenIntrospector;
import com.salon.config.OAuth2ResourceServerAutoConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author：xieshaowei
 * @Package：com.salon.annotation
 * @Project：salon
 * @name：EnableSecurity
 * @Date：2024/3/28 15:48
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Import({OAuth2ResourceServerAutoConfig.class, GlobalOpaqueTokenIntrospector.class})
@Import({OAuth2ResourceServerAutoConfig.class, GlobalOpaqueTokenIntrospector.class})
public @interface EnableSecurity {
}
