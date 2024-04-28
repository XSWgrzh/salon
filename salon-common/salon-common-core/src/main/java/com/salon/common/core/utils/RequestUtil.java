package com.salon.common.core.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.utils
 * @Project：salon
 * @name：RequestUtil
 * @Date：2024/3/29 17:13
 */
public class RequestUtil {

    /**
     * 获取请求对象.
     * @return 请求对象
     */
    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Assert.notNull(requestAttributes, "requestAttributes not be null");
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public static UserAgent getUserAgent(HttpServletRequest request) {

        return UserAgentUtil.parse(request.getHeader(USER_AGENT));
    }


}
