package com.salon.common.rocketmq.template;

import com.salon.common.rocketmq.contants.RocketMqConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static org.apache.rocketmq.client.producer.SendStatus.SEND_OK;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.rocketmq.template
 * @Project：salon
 * @name：RocketMqTemplate
 * @Date：2024/4/24 10:16
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RocketMqTemplate {

    private final RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送.
     * @param topic 主题
     * @param payload 消息
     * @param timeout 超时时间
     * @param <T> 泛型
     * @return 发送结果
     */
    public <T> boolean sendSyncMessage(String topic, T payload, long timeout) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        return rocketMQTemplate.syncSend(topic, message, timeout).getSendStatus().equals(SEND_OK);
    }

    /**
     * 同步发送.
     * @param topic 主题
     * @param payload 消息
     * @param timeout 超时时间
     * @param <T> 泛型
     * @param delayLevel 延迟等级
     * @return 发送结果
     */
    public <T> boolean sendSyncMessage(String topic, T payload, long timeout, int delayLevel) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        return rocketMQTemplate.syncSend(topic, message, timeout, delayLevel).getSendStatus().equals(SEND_OK);
    }

    /**
     * 同步发送消息.
     * @param topic 主题
     * @param payload 消息
     * @param <T> 泛型
     */
    public <T> boolean sendSyncMessage(String topic, T payload) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        return rocketMQTemplate.syncSend(topic, message).getSendStatus().equals(SEND_OK);
    }

    /**
     * 异步发送消息.
     * @param topic 主题
     * @param payload 消息
     * @param <T> 泛型
     */
    public <T> void sendAsyncMessage(String topic, T payload) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("RocketMQ消息发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("RocketMQ消息发送失败，报错信息", throwable);
            }
        });
    }

    /**
     * 异步发送消息.
     * @param topic 主题
     * @param tag 标签
     * @param payload 消息
     * @param <T> 泛型
     */
    public <T> void sendAsyncMessage(String topic, String tag, T payload) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        sendAsyncMessage(topic, tag, message);
    }

    /**
     * 异步发送消息.
     * @param topic 主题
     * @param tag 标签
     * @param payload 消息
     * @param traceId 链路ID
     * @param <T> 泛型
     */
    public <T> void sendAsyncMessage(String topic, String tag, T payload, String traceId) {
        Message<T> message = MessageBuilder.withPayload(payload)
                .setHeader(RocketMqConstants.TRACE_ID, traceId).build();
        sendAsyncMessage(topic, tag, message);
    }

    /**
     * 异步发送消息.
     * @param topic 主题
     * @param payload 消息
     * @param <T> 泛型
     * @param timeout 超时时间
     */
    public <T> void sendAsyncMessage(String topic, T payload, long timeout) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        rocketMQTemplate.asyncSend(topic, message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("RocketMQ消息发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("RocketMQ消息发送失败，报错信息", throwable);
            }
        }, timeout);
    }

    /**
     * 单向发送消息.
     * @param topic 主题
     * @param payload 消息
     * @param <T> 泛型
     */
    public <T> void sendOneWayMessage(String topic, T payload) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        // 单向发送，只负责发送消息，不会触发回调函数，即发送消息请求不等待
        // 适用于耗时短，但对可靠性不高的场景，如日志收集
        rocketMQTemplate.sendOneWay(topic, message);
    }

    /**
     * 延迟消息.
     * @param topic 主题
     * @param delay 延迟时间
     * @param payload 消息
     * @param <T> 泛型
     */
    public <T> boolean sendDelayMessage(String topic, long delay, T payload) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        return rocketMQTemplate.syncSendDelayTimeSeconds(topic, payload, delay).getSendStatus().equals(SEND_OK);
    }

    /**
     * 同步发送顺序消息.
     * @param topic 主题
     * @param payload 消息
     * @param <T> 泛型
     * @param id 标识
     */
    public <T> boolean sendSyncOrderlyMessage(String topic, T payload, String id) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        return rocketMQTemplate.syncSendOrderly(topic, message, id).getSendStatus().equals(SEND_OK);
    }

    /**
     * 异步发送顺序消息.
     * @param topic 主题
     * @param payload 消息
     * @param <T> 泛型
     * @param id 标识
     */
    public <T> void sendAsyncOrderlyMessage(String topic, T payload, String id) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        rocketMQTemplate.asyncSendOrderly(topic, message, id, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("RocketMQ消息发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("RocketMQ消息发送失败，报错信息：{}，详情见日志", throwable.getMessage(), throwable);
            }
        });
    }

    /**
     * 单向发送顺序消息.
     * @param topic 主题
     * @param payload 消息
     * @param <T> 泛型
     * @param id 标识
     */
    public <T> void sendOneWayOrderlyMessage(String topic, T payload, String id) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        // 单向发送，只负责发送消息，不会触发回调函数，即发送消息请求不等待
        // 适用于耗时短，但对可靠性不高的场景，如日志收集
        rocketMQTemplate.sendOneWayOrderly(topic, message, id);
    }

    /**
     * 事务消息.
     * @param topic 主题
     * @param payload 消息
     * @param transactionId 事务ID
     * @param <T> 泛型
     * @return 发送结果
     */
    public <T> boolean sendTransactionMessage(String topic, T payload, Long transactionId) {
        Message<T> message = MessageBuilder.withPayload(payload)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                .build();
        return rocketMQTemplate.sendMessageInTransaction(topic, message, null).getSendStatus().equals(SEND_OK);
    }

    /**
     * 转换并发送.
     * @param topic 主题
     * @param payload 消息内容
     * @param <T> 泛型
     */
    public <T> void convertAndSendMessage(String topic, T payload) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        rocketMQTemplate.convertAndSend(topic, message);
    }

    /**
     * 发送并接收.
     * @param topic 主题
     * @param payload 内容
     * @param clazz 类型
     * @param <T> 泛型
     */
    public <T> Object sendAndReceiveMessage(String topic, T payload, Class<?> clazz) {
        Message<T> message = MessageBuilder.withPayload(payload).build();
        return rocketMQTemplate.sendAndReceive(topic, message, clazz);
    }

    private <T> void sendAsyncMessage(String topic, String tag, Message<T> message) {
        rocketMQTemplate.asyncSend(String.format(RocketMqConstants.TOPIC_TAG, topic, tag), message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("RocketMQ消息发送成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("RocketMQ消息失败，报错信息", throwable);
            }
        });
    }

}
