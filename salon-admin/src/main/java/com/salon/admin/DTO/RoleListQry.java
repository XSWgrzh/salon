package com.salon.admin.DTO;

import com.salon.common.core.model.query.PageQuery;
import lombok.Data;

/**
 * @Author：xieshaowei
 * @Package：com.salon.admin.DTO
 * @Project：salon
 * @name：RoleListQry
 * @Date：2024/3/19 16:55
 */
@Data
public class RoleListQry extends PageQuery {
    private String name;
}
