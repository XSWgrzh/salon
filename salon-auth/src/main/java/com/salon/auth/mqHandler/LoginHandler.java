package com.salon.auth.mqHandler;

import cn.hutool.json.JSONUtil;
import com.salon.auth.consumer.LoginLogConsumer;
import com.salon.common.core.constant.RocketMqConstants;
import com.salon.common.core.model.log.SysLoginLog;
import com.salon.common.rocketmq.listener.AbstractRocketMQListener;
import io.micrometer.common.lang.NonNullApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.rocketmq.spring.annotation.ConsumeMode.ORDERLY;
import static org.apache.rocketmq.spring.annotation.MessageModel.CLUSTERING;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.mqHandler
 * @Project：salon
 * @name：LoginHandler
 * @Date：2024/4/25 10:33
 */
@Slf4j
@Component
@NonNullApi
@RocketMQMessageListener(consumerGroup = RocketMqConstants.SALON_LOGIN_CONSUMER_GROUP,
        topic = RocketMqConstants.SALON_LOGIN_TOPIC,
        messageModel = CLUSTERING, consumeMode = ORDERLY)
public class LoginHandler extends AbstractRocketMQListener {

    @Autowired
    private LoginLogConsumer loginLogConsumer;

    @Override
    protected void handleDomainEvent(String msg) {
        SysLoginLog loginLog = JSONUtil.toBean(msg, SysLoginLog.class);
        loginLogConsumer.insertLoginLog(loginLog);
    }
}
