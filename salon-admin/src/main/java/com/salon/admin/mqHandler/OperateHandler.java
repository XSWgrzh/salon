package com.salon.admin.mqHandler;

import cn.hutool.json.JSONUtil;
import com.salon.admin.service.OperateLogService;
import com.salon.common.core.constant.RocketMqConstants;
import com.salon.common.core.model.log.SysOperateLog;
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
 * @Package：com.salon.admin.mqHandler
 * @Project：salon
 * @name：OperateEventHandler
 * @Date：2024/4/24 15:15
 */
@Slf4j
@Component
@NonNullApi
@RocketMQMessageListener(consumerGroup = RocketMqConstants.SALON_OPERATE_CONSUMER_GROUP,
        topic = RocketMqConstants.SALON_OPERATE_TOPIC,
        messageModel = CLUSTERING, consumeMode = ORDERLY)
public class OperateHandler extends AbstractRocketMQListener {

    @Autowired
    private OperateLogService operateLogService;

    @Override
    protected void handleDomainEvent(String msg) {
        SysOperateLog sysOperateLog = JSONUtil.toBean(msg, SysOperateLog.class);
        operateLogService.insert(sysOperateLog);
    }
}
