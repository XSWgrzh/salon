package com.salon.auth.service.impl;

import com.salon.auth.oauth2.OAuth2AuthenticationProvider;
import com.salon.auth.service.SysUserService;
import com.salon.common.core.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author：xieshaowei
 * @Package：com.salon.auth.service
 * @Project：salon
 * @name：UserServiceImpl
 * @Date：2023/10/26 15:38
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {


    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private OAuth2AuthenticationProvider oAuth2AuthenticationProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser sysUser = sysUserService.selectByUsername(username);
//
//        logger.info("==================================================");
//        logger.info(sysUser.toString());
//
//        List<SimpleGrantedAuthority> grantedAuthorityList = Arrays.asList("USER").stream()
//                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//        logger.info(new User(username, sysUser.getPassword(), grantedAuthorityList).toString());
//        logger.info("==================================================");
//        return new User(username, sysUser.getPassword(), grantedAuthorityList);
        HttpServletRequest request = RequestUtil.getHttpServletRequest();
        String password = request.getParameter("password");
        return (UserDetails) oAuth2AuthenticationProvider.authenticationToken(username, password, request).getPrincipal();
    }
}
