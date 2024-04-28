package com.salon.common.rocketmq.contants;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.rocketmq.contants
 * @Project：salon
 * @name：RocketMqConstants
 * @Date：2024/4/24 10:37
 */
public class RocketMqConstants {

    @Schema(name = "TOPIC_TAG", description = "主题与标签的分隔符")
    public static final String TOPIC_TAG = "%s:%s";

    @Schema(name = "TRACE_ID", description = "分布式链路ID")
    public static final String TRACE_ID = "trace-id";

}
