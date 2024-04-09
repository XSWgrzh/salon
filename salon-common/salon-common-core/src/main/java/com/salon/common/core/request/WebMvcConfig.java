package com.salon.common.core.request;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.request
 * @Project：salon
 * @name：WebMvcConfig
 * @Date：2024/2/21 17:16
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册自定义的参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(new RequestStringParamHandlerMethodArgumentResolver());
        WebMvcConfigurer.super.addArgumentResolvers(argumentResolvers);
    }


}
