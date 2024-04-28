package com.salon.admin.DTO;

import com.salon.common.core.model.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.DTO
 * @Project：salon
 * @name：LoginLogListQry
 * @Date：2024/4/26 9:13
 */
@Data
@Schema(name = "LoginLogListQry", description = "登录日志列表查询参数")
public class LoginLogListQry extends PageQuery {

    @Schema(name = "username", description = "登录的用户名")
    private String username;

    @Schema(name = "status", description = "登录状态 0登录成功 1登录失败")
    private Integer status;

}
