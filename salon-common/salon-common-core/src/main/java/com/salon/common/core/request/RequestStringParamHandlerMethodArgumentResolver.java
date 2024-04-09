package com.salon.common.core.request;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.BufferedReader;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.request
 * @Project：salon
 * @name：RequestStringParamHandlerMethodArgumentResolver
 * @Date：2024/2/21 17:14
 */
public class RequestStringParamHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestSingleParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestSingleParam requestSingleParam = parameter.getParameterAnnotation(RequestSingleParam.class);
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int rd;
        while ((rd = reader.read(buf)) != -1) {
            sb.append(buf, 0, rd);
        }
        JSONObject jsonObject = JSONUtil.parseObj(sb.toString());
        String value = requestSingleParam.value();
        return jsonObject.get(value);
    }

}
