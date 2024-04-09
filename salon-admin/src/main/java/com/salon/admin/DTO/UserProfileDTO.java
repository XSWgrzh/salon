package com.salon.admin.DTO;

import lombok.Data;

import java.util.List;

/**
 * @Author：xieshaowei
 * @Package：com.salon.user.VO
 * @Project：salon
 * @name：UserProfileVO
 * @Date：2024/2/20 16:08
 */
@Data
public class UserProfileDTO {

    private static final long serialVersionUID = 5297753219988591611L;

    private Long id;

    private String avatar;

    private String username;

    private String mobile;

    private String mail;

    private List<String> permissionList;

    private Long tenantId;

    private Integer superAdmin;
}
