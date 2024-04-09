package com.salon.common.core.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

import static lombok.AccessLevel.PRIVATE;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.VO
 * @Project：salon
 * @name：OptionVO
 * @Date：2024/3/26 10:39
 */
@Data
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
@Schema(name = "OptionCO", description = "下拉框选择参数项参数")
public class OptionVO {

    @Serial
    private static final long serialVersionUID = -4146348495335527374L;

    @Schema(name = "label", description = "标签")
    private String label;

    @Schema(name = "value", description = "值")
    private String value;

}
