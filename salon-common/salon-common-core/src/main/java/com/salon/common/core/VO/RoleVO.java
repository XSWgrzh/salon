package com.salon.common.core.VO;

import lombok.Data;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.common.core.VO
 * @Project：salon
 * @name：RoleVO
 * @Date：2024/3/19 16:48
 */
@Data
public class RoleVO {

    private String id;

    private String name;

    private Integer sort;

    private List<String> menuIds;

}
