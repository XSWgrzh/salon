package com.salon.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author：xieshaowei
 * @Package：com.salon.utils
 * @Project：salon
 * @name：UserUtil
 * @Date：2024/3/28 17:00
 */
public class UserUtil {

    public static UserDetail user() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof UserDetail) {
            return (UserDetail) principal;
        }
        return UserDetail.builder().build();

    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 用户ID.
     *
     * @return Long
     */
    public static String getUserId() {
        return user().getId();
    }

    /**
     * 用户名.
     *
     * @return String
     */
    public static String getUserName() {
        return user().getUsername();
    }

//    /**
//     * 部门ID.
//     * @return Long
//     */
//    public static Long getDeptId() {
//        return user().getDeptId();
//    }
//
//    /**
//     * 部门PATH.
//     * @return Long
//     */
//    public static String getDeptPath() {
//        return user().getDeptPath();
//    }
//
//    /**
//     * 租户ID.
//     * @return Long
//     */
//    public static Long getTenantId() {
//        return user().getTenantId();
//    }
//
//    /**
//     * 数据源名称.
//     * @return String
//     */
//    public static String getSourceName() {
//        return user().getSourceName();
//    }

}
