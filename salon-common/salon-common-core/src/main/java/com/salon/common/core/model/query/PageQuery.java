package com.salon.common.core.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.model.query
 * @Project：salon
 * @name：PageQuery
 * @Date：2024/3/19 16:55
 */
@Data
public class PageQuery {

    @NotNull(message = "显示页码不为空")
    @Min(value = 1)
    @Schema(name = "pageNum", description = "页码")
    private Integer pageNum = 1;

    @NotNull(message = "显示条数不为空")
    @Schema(name = "pageSize", description = "条数")
    @Min(value = 1)
    private Integer pageSize = 10;

    @Schema(name = "startTime", description = "开始时间")
    private String startTime;

    @Schema(name = "endTime", description = "结束时间")
    private String endTime;

}
