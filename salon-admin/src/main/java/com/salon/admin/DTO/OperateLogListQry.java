package com.salon.admin.DTO;

import com.salon.common.core.model.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.DTO
 * @Project：salon
 * @name：OperateLogListQry
 * @Date：2024/4/26 9:12
 */
@Data
@Schema(name = "LoginLogListQry", description = "登录日志列表查询参数")
public class OperateLogListQry extends PageQuery {

    @Schema(name = "moduleName", description = "操作的模块名称")
    private String moduleName;

    @Schema(name = "status", description = "登录状态 0登录成功 1登录失败")
    private Integer status;

}
