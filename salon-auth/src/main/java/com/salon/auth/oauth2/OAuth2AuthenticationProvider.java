package com.salon.auth.oauth2;

import com.salon.auth.service.SysUserService;
import com.salon.common.core.model.admin.SysUser;
import com.salon.utils.UserDetail;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.oauth2
 * @Project：salon
 * @name：OAuth2AuthenticationProvider
 * @Date：2024/3/29 16:19
 */
@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationProvider {

    @Autowired
    private SysUserService sysUserService;
    @Resource
    private PasswordEncoder passwordEncoder;

    public UsernamePasswordAuthenticationToken authenticationToken(String username, String password, HttpServletRequest request) {
        SysUser sysUser = sysUserService.selectByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户未找到");
        }
        if (!passwordEncoder.matches(password, sysUser.getPassword())) {
            throw new UsernameNotFoundException("账号密码不正确！");
        }
        UserDetail user = this.convert(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(), new HashSet<>());

        return new UsernamePasswordAuthenticationToken(user, user.getUsername(), user.getAuthorities());
    }

    private UserDetail convert(String id, String username, String password, Set<String> permissions) {
        return UserDetail.builder()
                .id(id)
                .username(username)
                .password(password)
                .permissions(permissions)
                .build();
    }

}
