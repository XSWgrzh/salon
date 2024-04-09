package com.salon.filter;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.salon.utils.EncryptUtil;
import com.salon.utils.UserDetail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Author：xieshaowei
 * @Package：com.salon.filter
 * @Project：salon
 * @name：TokenAuthenticationFilter
 * @Date：2024/4/3 16:05
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization")
                .replace("Bearer ", "");
        if (token != null){
            //1.解析token
            String json = EncryptUtil.decodeUTF8StringBase64(token);
            JSONObject userJson = JSONUtil.parseObj(json);
            UserDetail user = new UserDetail();
            user.setUsername(userJson.getStr("principal"));
            JSONArray authoritiesArray = userJson.getJSONArray("authorities");
            String  [] authorities = authoritiesArray.toArray( new
                    String[authoritiesArray.size()]);
            //2.新建并填充authentication
            UsernamePasswordAuthenticationToken authentication = new
                    UsernamePasswordAuthenticationToken(
                    user, null, AuthorityUtils.createAuthorityList(authorities));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                    httpServletRequest));
            //3.将authentication保存进安全上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
