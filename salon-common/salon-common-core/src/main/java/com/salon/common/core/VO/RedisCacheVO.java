package com.salon.common.core.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.VO
 * @Project：salon
 * @name：RedisCacheVO
 * @Date：2024/4/26 15:21
 */
@Data
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Schema(name = "RedisCacheVO", description = "Redis缓存")
public class RedisCacheVO implements Serializable {


    @Schema(name = "keysSize", description = "RedisKey大小")
    private Long keysSize;

    @Schema(name = "info", description = "Redis信息")
    private Map<String, String> info;

    @Schema(name = "commandStats", description = "Redis命令统计信息")
    private List<Map<String, String>> commandStats;

}
