package com.salon.admin.DTO;

import com.salon.common.core.model.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.DTO
 * @Project：salon
 * @name：UserListQry
 * @Date：2024/3/21 14:33
 */
@Data
@Schema(name = "UserListQry", description = "用户列表查询参数")
public class UserListQry extends PageQuery {

    @Schema(name = "username", description = "用户名")
    private String username;
}
