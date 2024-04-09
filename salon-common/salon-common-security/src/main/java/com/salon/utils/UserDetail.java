package com.salon.utils;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author：xieshaowei
 * @Package：com.salon.utils
 * @Project：salon
 * @name：UserDetail
 * @Date：2024/3/29 16:25
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "UserDetail", description = "用户详细信息")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public class UserDetail implements UserDetails, OAuth2AuthenticatedPrincipal {

    @Schema(name = "id", description = "id")
    private String id;

    @Schema(name = "username", description = "用户名")
    private String username;

    @Schema(name = "password", description = "登录密码")
    private String password;

    @Schema(name = "nickname", description = "用户昵称")
    private String nickname;

    @Schema(name = "permissions", description = "菜单权限标识集合")
    private String avatar;

    @Schema(name = "mobile", description = "手机号")
    private String mobile;

    @Schema(name = "mail", description = "邮箱")
    private String mail;

    @Schema(name = "sex", description = "性别 0女 1男")
    private int sex;

    @Schema(name = "status", description = "是否开启")
    private int status;

    @Schema(name = "company", description = "公司")
    private String company;

    @Schema(name = "permissions", description = "菜单权限标识集合")
    private Set<String> permissions;

    @Schema(name = "creatorId", description = "创建人id")
    private int creatorId;

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(1, this.status);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return Collections.emptyMap();
    }
}
