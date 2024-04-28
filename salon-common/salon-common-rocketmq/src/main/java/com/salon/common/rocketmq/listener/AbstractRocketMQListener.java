package com.salon.common.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;

import java.nio.charset.StandardCharsets;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.rocketmq.listener
 * @Project：salon
 * @name：AbstractDomainEventRocketMQListener
 * @Date：2024/4/24 15:04
 */
@Slf4j
public abstract class AbstractRocketMQListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        handleDomainEvent(msg);
    }

    protected abstract void handleDomainEvent(String msg);

}
