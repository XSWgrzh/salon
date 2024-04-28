package com.salon.common.core.constant;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.constant
 * @Project：salon
 * @name：RocketMqConstants
 * @Date：2024/4/24 14:19
 */
@Schema(name = "RocketMqConstants", description = "RocketMQ消息常量")
public class RocketMqConstants {

    @Schema(name = "SALON_OPERATE_TOPIC", description = "操作日志主题")
    public static final String SALON_OPERATE_TOPIC = "salon_operate_topic";

    @Schema(name = "SALON_OPERATE_TOPIC", description = "登录日志主题")
    public static final String SALON_LOGIN_TOPIC = "salon_login_topic";

    @Schema(name = "SALON_OPERATE_EVENT_CONSUMER_GROUP", description = "操作事件消费者组")
    public static final String SALON_OPERATE_CONSUMER_GROUP = "salon_operate_consumer_group";

    @Schema(name = "SALON_LOGIN_CONSUMER_GROUP", description = "登录日志消费者组")
    public static final String SALON_LOGIN_CONSUMER_GROUP = "salon_login_consumer_group";
}
