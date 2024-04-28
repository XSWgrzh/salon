package com.salon.common.log.aop;


import cn.hutool.json.JSONUtil;
import com.salon.common.core.constant.NumberConstants;
import com.salon.common.core.constant.PropertiesConstants;
import com.salon.common.core.constant.RocketMqConstants;
import com.salon.common.core.constant.StringConstants;
import com.salon.common.core.model.log.SysOperateLog;
import com.salon.common.core.utils.IdGenerator;
import com.salon.common.core.utils.IpUtil;
import com.salon.common.core.utils.RequestUtil;
import com.salon.common.log.annotation.OperateLog;
import com.salon.common.rocketmq.template.RocketMqTemplate;
import com.salon.utils.UserUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.log.aop
 * @Project：salon
 * @name：OperateLogAop
 * @Date：2024/4/15 11:44
 */
@Component
@Aspect
@RequiredArgsConstructor
public class OperateLogAop {

    private static final ThreadLocal<Long> TASK_TIME_LOCAL = new NamedThreadLocal<>("耗时");

    private final Environment environment;

    private final RocketMqTemplate rocketMqTemplate;

    @Before("@annotation(com.salon.common.log.annotation.OperateLog)")
    public void doBefore() {
        TASK_TIME_LOCAL.set(IdGenerator.SystemClock.now());
    }

    @AfterReturning("@annotation(com.salon.common.log.annotation.OperateLog)")
    public void doAfterReturning(JoinPoint joinPoint) {
        this.handleLog(joinPoint, null);
    }

    @AfterThrowing(value = "@annotation(com.salon.common.log.annotation.OperateLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        this.handleLog(joinPoint, e);
    }

    public void handleLog(JoinPoint joinPoint, Exception e) {
        String appName = environment.getProperty(PropertiesConstants.SPRING_APPLICATION_NAME);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        OperateLog annotation = AnnotationUtils.findAnnotation(method, OperateLog.class);
        Assert.isTrue(!ObjectUtils.isEmpty(annotation), "@OperateLog is null");
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        SysOperateLog sysOperateLog = SysOperateLog.builder()
                .name(annotation.operation())
                .moduleName(annotation.module())
                .methodName(className + StringConstants.DOT + methodName + StringConstants.LEFT + StringConstants.RIGHT)
                .requestParams(JSONUtil.toJsonStr(args))
                .uri(request.getRequestURI())
                .requestType(request.getMethod())
                .userAgent(request.getHeader(HttpHeaders.USER_AGENT))
                .ip(IpUtil.getIpAddr(request))
                .takeTime(IdGenerator.SystemClock.now() - TASK_TIME_LOCAL.get())
                .operator(UserUtil.getUserName())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        if (ObjectUtils.isEmpty(e)) {
            sysOperateLog.setStatus(NumberConstants.SUCCESS);
        } else {
            sysOperateLog.setErrorMessage(e.getMessage());
            sysOperateLog.setStatus(NumberConstants.FAIL);
        }

        rocketMqTemplate.sendSyncOrderlyMessage(RocketMqConstants.SALON_OPERATE_TOPIC, sysOperateLog, appName);

    }

}
