CREATE DATABASE IF NOT EXISTS `salon-user` DEFAULT CHARACTER SET = utf8;
Use`salon-user`;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `salon_sys_user`;
CREATE TABLE `salon_sys_user`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `username`     varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
    `password`     varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '登录密码',
    `nickname`     varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '用户昵称',
    `avatar` varchar(1024) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '头像',
    `mobile`       varchar(11) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '手机号',
    `mail`         varchar(11) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '邮箱',
    `sex`          tinyint(1) NULL DEFAULT NULL COMMENT '性别 0女 1男',
    `enabled`      tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否开启',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `company`      varchar(255) CHARACTER SET utf8mb4 NULL COMMENT '公司',
    `del_flag`     tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 0未删除 1已删除',
    `creator_id`   int(11) COMMENT '创建人id',
    PRIMARY KEY (`id`),
    KEY            `idx_username` (`username`),
    KEY            `idx_mobile` (`mobile`)
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `salon_sys_user`
VALUES (1, 'admin', '$2a$10$TJkwVdlpbHKnV45.nBxbgeFHmQRmyWlshg94lFu2rKxVtT2OMniDO', '管理员',
        'http://pkqtmn0p1.bkt.clouddn.com/头像.png', '18888888888','1234@qq.com', 0, 1, '2023-11-17 16:56:59',
        '2023-01-08 17:05:47', 'ENGJ', 0, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `salon_sys_role`;
CREATE TABLE `salon_sys_role`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `code`        varchar(32) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色code',
    `name`        varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色名',
    `data_scope`  varchar(32) DEFAULT 'ALL' comment '数据权限范围配置：ALL/全部权限，CREATOR/创建者权限',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `creator_id`  int(11) COMMENT '创建人id',
    `del_flag`     tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 0未删除 1已删除',
    PRIMARY KEY (`id`),
    KEY           `idx_code` (`code`)
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `salon_sys_role`
VALUES (1, 'ADMIN', '管理员', 'ALL', '2023-11-17 16:56:59', '2023-11-17 16:56:59', 1, 0);


-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `salon_sys_role_user`;
CREATE TABLE `salon_sys_role_user`
(
    `user_id` int(11) NOT NULL,
    `role_id` int(11) NOT NULL,
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `salon_sys_role_user`
VALUES (1, 1);


DROP TABLE IF EXISTS `salon_sys_operate_log`;
CREATE TABLE `salon_sys_operate_log`  (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 0未删除 1已删除',
     `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户ID',
     `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作名称',
     `module_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作的模块名称',
     `uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作的URI',
     `method_name` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作的方法名',
     `request_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作的请求类型',
     `request_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作的请求参数',
     `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作的浏览器',
     `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作的IP地址',
     `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作的归属地',
     `status` tinyint NOT NULL DEFAULT 0 COMMENT '操作状态 0成功 1失败',
     `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作人',
     `error_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '错误信息',
     `take_time` bigint NOT NULL COMMENT '操作的消耗时间(毫秒)',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

CREATE TABLE IF NOT EXISTS `salon_sys_login_log`(
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
     `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标识 0未删除 1已删除',
     `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户ID',
     `username` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录的用户名',
     `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录的IP地址',
     `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录的归属地',
     `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录的浏览器',
     `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录的操作系统',
     `status` tinyint NOT NULL DEFAULT '0' COMMENT '登录状态 0登录成功 1登录失败',
     `message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录信息',
     `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录类型',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='登录日志';

#
# -- ----------------------------
# -- Table structure for sys_menu
# -- ----------------------------
# DROP TABLE IF EXISTS `sys_menu`;
# CREATE TABLE `sys_menu`
# (
#     `id`          int(11) NOT NULL AUTO_INCREMENT,
#     `parent_id`   int(11) NOT NULL,
#     `name`        varchar(64) CHARACTER SET utf8mb4 NOT NULL,
#     `url`         varchar(1024) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
#     `path`        varchar(1024) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
#     `path_method` varchar(10) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
#     `css`         varchar(32) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
#     `sort`        int(11) NOT NULL,
#     `create_time` datetime(0) NULL,
#     `update_time` datetime(0) NULL,
#     `type`        tinyint(1) NOT NULL,
#     `hidden`      tinyint(1) NOT NULL DEFAULT 0,
#     `tenant_id`   varchar(32) DEFAULT '' COMMENT '租户字段',
#     `creator_id`  int(11) COMMENT '创建人id',
#     PRIMARY KEY (`id`),
#     KEY           `idx_parent_id` (`parent_id`),
#     KEY           `idx_tenant_id` (`tenant_id`)
# ) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
#
# -- ----------------------------
# -- Records of sys_menu
# -- ----------------------------
# INSERT INTO `sys_menu`
# VALUES (2, 12, '用户管理', '#!user', 'system/user.html', NULL, 'layui-icon-friends', 2, '2017-11-17 16:56:59',
#         '2018-09-19 11:26:14', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (3, 12, '角色管理', '#!role', 'system/role.html', NULL, 'layui-icon-user', 3, '2017-11-17 16:56:59',
#         '2019-01-14 15:34:40', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (4, 12, '菜单管理', '#!menus', 'system/menus.html', NULL, 'layui-icon-menu-fill', 4, '2017-11-17 16:56:59',
#         '2018-09-03 02:23:47', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (9, 37, '文件中心', '#!files', 'files/files.html', NULL, 'layui-icon-file', 3, '2017-11-17 16:56:59',
#         '2019-01-17 20:18:44', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (10, 37, '文档中心', '#!swagger', 'http://127.0.0.1:9900/doc.html', NULL, 'layui-icon-app', 4,
#         '2017-11-17 16:56:59', '2019-01-17 20:18:48', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (11, 12, '我的信息', '#!myInfo', 'system/myInfo.html', NULL, 'layui-icon-login-qq', 10, '2017-11-17 16:56:59',
#         '2018-09-02 06:12:24', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (12, -1, '认证管理', 'javascript:;', '', NULL, 'layui-icon-set', 1, '2017-11-17 16:56:59', '2018-12-13 15:02:49',
#         1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (35, 12, '应用管理', '#!app', 'attestation/app.html', NULL, 'layui-icon-link', 5, '2017-11-17 16:56:59',
#         '2019-01-14 15:35:15', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (37, -1, '系统管理', 'javascript:;', '', NULL, 'layui-icon-set', 2, '2018-08-25 10:41:58', '2019-01-23 14:01:58',
#         1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (62, 63, '应用监控', '#!admin', 'http://127.0.0.1:6500/#/wallboard', NULL, 'layui-icon-chart-screen', 4,
#         '2019-01-08 15:32:19', '2019-01-17 20:22:44', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (63, -1, '系统监控', 'javascript:;', '', NULL, 'layui-icon-set', 2, '2019-01-10 18:35:05', '2019-01-10 18:35:05',
#         1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (64, 63, '系统日志', '#!sysLog', 'log/sysLog.html', NULL, 'layui-icon-file-b', 1, '2019-01-10 18:35:55',
#         '2019-01-12 00:27:20', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (65, 37, '代码生成器', '#!generator', 'generator/list.html', NULL, 'layui-icon-template', 2,
#         '2019-01-14 00:47:36', '2019-01-23 14:06:31', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (66, 63, '慢查询SQL', '#!slowQueryLog', 'log/slowQueryLog.html', NULL, 'layui-icon-snowflake', 2,
#         '2019-01-16 12:00:27', '2019-01-16 15:32:31', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (67, -1, '任务管理', '#!job', 'http://127.0.0.1:8081/', NULL, 'layui-icon-date', 3, '2019-01-17 20:18:22',
#         '2019-01-23 14:01:53', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (68, 63, '应用吞吐量监控', '#!sentinel', 'http://127.0.0.1:6999', NULL, 'layui-icon-chart', 5,
#         '2019-01-22 16:31:55', '2019-01-22 16:34:03', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (69, 37, '配置中心', '#!nacos', 'http://127.0.0.1:8848/nacos', NULL, 'layui-icon-tabs', 1, '2019-01-23 14:06:10',
#         '2019-01-23 14:06:10', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (70, 63, 'APM监控', '#!apm', 'http://127.0.0.1:8080', null, 'layui-icon-engine', 6, '2019-02-27 10:31:55',
#         '2019-02-27 10:31:55', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (71, -1, '搜索管理', 'javascript:;', '', NULL, 'layui-icon-set', 3, '2018-08-25 10:41:58', '2019-01-23 15:07:07',
#         1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (72, 71, '索引管理', '#!index', 'search/index_manager.html', NULL, 'layui-icon-template', 1,
#         '2019-01-10 18:35:55', '2019-01-12 00:27:20', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (73, 71, '用户搜索', '#!userSearch', 'search/user_search.html', NULL, 'layui-icon-user', 2,
#         '2019-01-10 18:35:55', '2019-01-12 00:27:20', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (74, 12, 'Token管理', '#!tokens', 'system/tokens.html', NULL, 'layui-icon-unlink', 6, '2019-07-11 16:56:59',
#         '2019-07-11 16:56:59', 1, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (75, 2, '用户列表', '/api-user/users', 'user-list', 'GET', null, 1, '2019-07-29 16:56:59', '2019-07-29 16:56:59',
#         2, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (76, 2, '查询用户角色', '/api-user/roles', 'user-roles', 'GET', null, 2, '2019-07-29 16:56:59',
#         '2019-07-29 16:56:59', 2, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (77, 2, '用户添加', '/api-user/users/saveOrUpdate', 'user-btn-add', 'POST', null, 3, '2019-07-29 16:56:59',
#         '2019-07-29 16:56:59', 2, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (78, 2, '用户导出', '/api-user/users/export', 'user-btn-export', 'POST', null, 4, '2019-07-29 16:56:59',
#         '2019-07-29 16:56:59', 2, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (79, 2, '用户导入', '/api-user/users/import', 'user-btn-import', 'POST', null, 5, '2019-07-29 16:56:59',
#         '2019-07-29 16:56:59', 2, 0, 'webApp', 1);
# INSERT INTO `sys_menu`
# VALUES (80, -1, '用户管理', '#!user', '', NULL, NULL, 1, '2019-08-06 20:02:12.604', '2019-08-06 20:02:12.604', 1, 0,
#         'zlt', 1);
# INSERT INTO `sys_menu`
# VALUES (81, -1, '商品管理', '#!product', '', NULL, NULL, 2, '2019-08-06 20:02:12.604', '2019-08-06 20:02:12.604', 1, 0,
#         'zlt', 1);
# INSERT INTO `sys_menu`
# VALUES (82, -1, '支付管理', '#!pay', '', NULL, NULL, 3, '2019-08-06 20:02:12.604', '2019-08-06 20:02:12.604', 1, 0,
#         'zlt', 1);
# INSERT INTO `sys_menu`
# VALUES (83, -1, '交易管理', '#!trading', '', NULL, NULL, 4, '2019-08-06 20:02:12.604', '2019-08-06 20:02:12.604', 1, 0,
#         'zlt', 1);
# INSERT INTO `sys_menu`
# VALUES (84, -1, '系统管理', '#!system', '', NULL, NULL, 1, '2019-08-06 20:02:12.604', '2019-08-06 20:02:12.604', 1, 0,
#         'app', 1);
# INSERT INTO `sys_menu`
# VALUES (85, 63, '审计日志', '#!auditLog', 'log/auditLog.html', NULL, 'layui-icon-file-b', 3, '2020-02-04 12:00:27',
#         '2020-02-04 15:32:31', 1, 0, 'webApp', 1);
#
# -- ----------------------------
# -- Table structure for sys_role_menu
# -- ----------------------------
# DROP TABLE IF EXISTS `sys_role_menu`;
# CREATE TABLE `sys_role_menu`
# (
#     `role_id` int(11) NOT NULL,
#     `menu_id` int(11) NOT NULL,
#     PRIMARY KEY (`role_id`, `menu_id`)
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
#
# -- ----------------------------
# -- Records of sys_role_menu
# -- ----------------------------
# INSERT INTO `sys_role_menu`
# VALUES (1, 2);
# INSERT INTO `sys_role_menu`
# VALUES (1, 3);
# INSERT INTO `sys_role_menu`
# VALUES (1, 4);
# INSERT INTO `sys_role_menu`
# VALUES (1, 9);
# INSERT INTO `sys_role_menu`
# VALUES (1, 10);
# INSERT INTO `sys_role_menu`
# VALUES (1, 11);
# INSERT INTO `sys_role_menu`
# VALUES (1, 12);
# INSERT INTO `sys_role_menu`
# VALUES (1, 35);
# INSERT INTO `sys_role_menu`
# VALUES (1, 37);
# INSERT INTO `sys_role_menu`
# VALUES (1, 62);
# INSERT INTO `sys_role_menu`
# VALUES (1, 63);
# INSERT INTO `sys_role_menu`
# VALUES (1, 64);
# INSERT INTO `sys_role_menu`
# VALUES (1, 65);
# INSERT INTO `sys_role_menu`
# VALUES (1, 66);
# INSERT INTO `sys_role_menu`
# VALUES (1, 67);
# INSERT INTO `sys_role_menu`
# VALUES (1, 68);
# INSERT INTO `sys_role_menu`
# VALUES (1, 69);
# INSERT INTO `sys_role_menu`
# VALUES (1, 70);
# INSERT INTO `sys_role_menu`
# VALUES (1, 71);
# INSERT INTO `sys_role_menu`
# VALUES (1, 72);
# INSERT INTO `sys_role_menu`
# VALUES (1, 73);
# INSERT INTO `sys_role_menu`
# VALUES (1, 74);
# INSERT INTO `sys_role_menu`
# VALUES (1, 75);
# INSERT INTO `sys_role_menu`
# VALUES (1, 76);
# INSERT INTO `sys_role_menu`
# VALUES (1, 77);
# INSERT INTO `sys_role_menu`
# VALUES (1, 78);
# INSERT INTO `sys_role_menu`
# VALUES (1, 79);
# INSERT INTO `sys_role_menu`
# VALUES (1, 85);
# INSERT INTO `sys_role_menu`
# VALUES (2, 2);
# INSERT INTO `sys_role_menu`
# VALUES (2, 3);
# INSERT INTO `sys_role_menu`
# VALUES (2, 4);
# INSERT INTO `sys_role_menu`
# VALUES (2, 11);
# INSERT INTO `sys_role_menu`
# VALUES (2, 12);
# INSERT INTO `sys_role_menu`
# VALUES (2, 35);
# INSERT INTO `sys_role_menu`
# VALUES (3, 2);
# INSERT INTO `sys_role_menu`
# VALUES (3, 3);
# INSERT INTO `sys_role_menu`
# VALUES (3, 4);
# INSERT INTO `sys_role_menu`
# VALUES (3, 12);
# INSERT INTO `sys_role_menu`
# VALUES (4, 80);
# INSERT INTO `sys_role_menu`
# VALUES (4, 81);
# INSERT INTO `sys_role_menu`
# VALUES (4, 82);
# INSERT INTO `sys_role_menu`
# VALUES (4, 83);
# INSERT INTO `sys_role_menu`
# VALUES (5, 84);


-- ----------------------------
-- Table structure for salon_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `salon_sys_menu`;
CREATE TABLE `salon_sys_menu`  (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                  `creator` bigint NOT NULL DEFAULT 0 COMMENT '创建人',
                                  `editor` bigint NOT NULL DEFAULT 0 COMMENT '编辑人',
                                  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                  `del_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标识 0未删除 1已删除',
                                  `version` int NOT NULL DEFAULT 0 COMMENT '版本号',
                                  `dept_id` bigint NOT NULL DEFAULT 0 COMMENT '部门ID',
                                  `dept_path` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '部门PATH',
                                  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户ID',
                                  `pid` bigint NOT NULL COMMENT '菜单父节点',
                                  `permission` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单权限标识',
                                  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '菜单类型 0菜单 1按钮',
                                  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单名称',
                                  `url` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单URL',
                                  `icon` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单图标',
                                  `sort` int NOT NULL DEFAULT 1 COMMENT '菜单排序',
                                  `visible` tinyint(1) NOT NULL DEFAULT 0 COMMENT '菜单状态 0显示 1隐藏',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  INDEX `idx_type_visible`(`type` ASC, `visible` ASC) USING BTREE COMMENT '类型_可见_索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1564996817056710717 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;


INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564214128678539265, 1341620898007281665, 1341620898007281665, '2022-08-29 19:31:25', '2023-09-23 11:27:00', 0, 2, 0, '0', 0, 1560530418819862529, 'resource:sync', 1, '同步资源', '/v1/resource/sync', 'sync', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710679, 1341620898007281665, 1341620898007281665, '2023-02-07 22:51:24', '2023-09-12 16:45:51', 0, 1, 0, '0', 0, 1564996817056710672, 'tenants:update', 1, '修改租户', '/v1/tenants/update', 'edit', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710678, 1341620898007281665, 1341620898007281665, '2023-02-07 22:50:42', '2023-09-12 16:47:23', 0, 2, 0, '0', 0, 1564996817056710672, 'tenants:delete', 1, '删除租户', '/v1/tenants/delete', 'delete', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710677, 1341620898007281665, 1341620898007281665, '2023-02-06 22:36:42', '2023-02-06 22:36:42', 0, 0, 0, '0', 0, 1564996817056710673, 'sources:delete', 1, '删除数据源', '/v1/sources/delete', 'delete', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710676, 1341620898007281665, 1341620898007281665, '2023-02-06 22:35:59', '2023-09-12 16:47:38', 0, 2, 0, '0', 0, 1564996817056710673, 'sources:insert', 1, '新增数据源', '/v1/sources/insert', 'plus', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710675, 1341620898007281665, 1341620898007281665, '2023-02-06 22:35:11', '2023-09-12 16:47:44', 0, 2, 0, '0', 0, 1564996817056710673, 'sources:update', 1, '修改数据源', '/v1/sources/update', 'edit', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710674, 1341620898007281665, 1341620898007281665, '2023-02-06 22:34:29', '2023-09-12 16:46:04', 0, 1, 0, '0', 0, 1564996817056710673, 'sources:list', 1, '查询数据源列表', '/v1/sources/list', 'search', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710673, 1341620898007281665, 1341620898007281665, '2023-02-06 20:59:42', '2023-09-09 18:44:12', 0, 2, 0, '0', 0, 1535878154046939137, 'sources:view', 0, '数据源管理', '/v1/sources/view', 'database', 997, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710672, 1341620898007281665, 1341620898007281665, '2023-02-06 20:51:49', '2023-09-09 18:44:05', 0, 2, 0, '0', 0, 1535878154046939137, 'tenants:view', 0, '租户管理', '/v1/tenants/view', 'usergroup-add', 998, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710666, 1341620898007281665, 1341620898007281665, '2022-12-11 11:50:45', '2023-09-19 07:55:10', 0, 1, 0, '0', 0, 1539396453854629890, 'logs:login-export', 1, '导出登录日志', '/v1/logs/login-export', 'export', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710665, 1341620898007281665, 1341620898007281665, '2022-12-11 11:32:53', '2023-09-19 07:54:40', 0, 1, 0, '0', 0, 1539251440843857922, 'logs:operate-export', 1, '导出操作日志', '/v1/logs/operate-export', 'export', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710657, 1341620898007281665, 1341620898007281665, '2022-08-31 23:21:32', '2023-09-23 11:31:31', 0, 2, 0, '0', 0, 1564228322215927810, 'resource:search', 1, '搜索资源', '/v1/resource/search', 'search', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564228322215927810, 1341620898007281665, 1341620898007281665, '2022-08-29 20:27:49', '2023-09-23 11:31:56', 0, 2, 0, '0', 0, 1560528267620061186, 'resource:search:view', 0, '资源搜索', '/v1/resource/search/view', 'loading', 100, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710680, 1341620898007281665, 1341620898007281665, '2023-02-07 22:51:58', '2023-09-12 16:47:29', 0, 1, 0, '0', 0, 1564996817056710672, 'tenants:insert', 1, '新增租户', '/v1/tenants/insert', 'plus', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1563333871266885634, 1341620898007281665, 1341620898007281665, '2022-08-27 09:16:33', '2023-09-23 11:26:38', 0, 1, 0, '0', 0, 1560530418819862529, 'resource:detail', 1, '查看资源', '/v1/resource/detail', 'eyeOpen', 50, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1563104888692600833, 1341620898007281665, 1341620898007281665, '2022-08-27 09:08:46', '2023-09-23 11:26:51', 0, 2, 0, '0', 0, 1560530418819862529, 'resource:audit-log', 1, '查询审批日志列表', '/v1/resource/audit-log', 'file', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1562972785472630785, 1537114827246292994, 1341620898007281665, '2022-08-26 09:18:45', '2023-09-23 02:42:36', 0, 0, 0, '0', 0, 1560530418819862529, 'resource:diagram', 1, '流程图', '/v1/resource/diagram', 'gold', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1562972411969859586, 1537114827246292994, 1341620898007281665, '2022-08-26 09:17:16', '2023-09-23 11:26:26', 0, 1, 0, '0', 0, 1560530418819862529, 'resource:delete', 1, '删除资源', '/v1/resource/delete', 'delete', 60, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1562972243434336257, 1537114827246292994, 1341620898007281665, '2022-08-26 09:16:36', '2023-09-23 11:25:47', 0, 1, 0, '0', 0, 1560530418819862529, 'resource:update', 1, '修改资源', '/v1/resource/update', 'edit', 70, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1562971829607526401, 1537114827246292994, 1341620898007281665, '2022-08-26 09:14:57', '2023-09-23 11:25:41', 0, 1, 0, '0', 0, 1560530418819862529, 'resource:insert', 1, '新增资源', '/v1/resource/insert', 'plus', 80, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1562971590226014209, 1537114827246292994, 1341620898007281665, '2022-08-26 09:14:00', '2023-09-23 11:25:28', 0, 1, 0, '0', 0, 1560530418819862529, 'resource:list', 1, '查询资源列表', '/v1/resource/list', 'search', 90, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1560530418819862529, 1341620898007281665, 1341620898007281665, '2022-08-19 15:34:18', '2023-09-23 11:25:07', 0, 2, 0, '0', 0, 1560528267620061186, 'resource:view', 0, '资源天堂', '/v1/resource/view', 'cloud', 300, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1560528267620061186, 1341620898007281665, 1341620898007281665, '2022-08-23 12:03:22', '2022-08-23 12:03:22', 0, 0, 0, '0', 0, 1535878154046939137, 'sys:resource:view', 0, '资源管理', '/sys/resource/view', 'folder', 510, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1552526314178142209, 1341620898007281665, 1341620898007281665, '2022-07-28 23:30:04', '2023-02-26 20:46:22', 0, 1, 0, '0', 0, 1537444981390794754, 'monitors:server:view', 0, '主机监控', '/v1/monitors/server/view', 'hdd', 1000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1552525480564416514, 1341620898007281665, 1341620898007281665, '2022-07-28 23:30:24', '2023-02-26 20:46:06', 0, 1, 0, '0', 0, 1537444981390794754, 'monitors:cache:view', 0, '缓存监控', '/v1/monitors/cache/view', 'redis', 2000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710699, 1341620898007281665, 1341620898007281665, '2023-02-25 14:55:30', '2023-02-25 14:55:30', 0, 0, 0, '0', 0, 1535881096963563522, 'users:status', 1, '修改用户状态', '/v1/users/status', 'password', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710713, 1341620898007281665, 1341620898007281665, '2023-04-09 15:01:10', '2023-09-19 15:59:50', 0, 4, 0, '0', 0, 1564996817056710711, 'users:online-list', 1, '查询在线用户列表', '/v1/users/online-list', 'search', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710712, 1341620898007281665, 1341620898007281665, '2023-04-09 14:58:21', '2023-09-19 15:59:58', 0, 3, 0, '0', 0, 1564996817056710711, 'users:online-kill', 1, '强踢在线用户', '/v1/users/online-kill', 'delete', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710711, 1341620898007281665, 1341620898007281665, '2023-04-09 11:35:58', '2023-09-20 14:30:26', 0, 6, 0, '0', 0, 1537444981390794754, 'user:online:view', 0, '在线用户', '/v1/users/online/view', 'online', 5000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710710, 1341620898007281665, 1537114827246292994, '2023-03-15 22:11:04', '2023-06-23 21:44:45', 0, 4, 0, '0', 0, 1535878154046939137, 'monitors:seata:view', 0, '事务管理', 'https://127.0.0.1:7091', 'cascader', 403, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710708, 1341623527018004481, 1341620898007281665, '2023-03-08 07:51:53', '2023-09-23 11:36:10', 0, 2, 0, '0', 0, 1545037580289044482, 'resource:delegate-task', 1, '委派任务', '/v1/resource/delegate-task', 'user-add', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710707, 1341623527018004481, 1341620898007281665, '2023-03-08 07:46:11', '2023-09-23 11:35:38', 0, 9, 0, '0', 0, 1545037580289044482, 'resource:transfer-task', 1, '转办任务', '/v1/resource/transfer-task', 'solution', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710706, 1341623527018004481, 1341620898007281665, '2023-03-08 07:45:38', '2023-09-23 11:35:12', 0, 3, 0, '0', 0, 1545037580289044482, 'resource:resolve-task', 1, '处理任务', '/v1/resource/resolve-task', 'like', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710705, 1341620898007281665, 1341620898007281665, '2023-03-02 07:52:36', '2023-09-21 04:26:11', 0, 3, 0, '0', 0, 1545036486288732162, 'definitions:template', 1, '流程模板', '/v1/definitions/template', 'documentation', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710702, 1341620898007281665, 1341620898007281665, '2023-03-01 09:52:08', '2023-09-23 11:27:13', 0, 3, 0, '0', 0, 1560530418819862529, 'resource:download', 1, '下载资源', '/v1/resource/download', 'download', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710701, 1341620898007281665, 1341620898007281665, '2023-02-26 21:09:04', '2023-06-23 17:34:44', 0, 3, 0, '0', 0, 1535878154046939137, 'configs:nacos:view', 0, '配置中心', 'https://127.0.0.1:8848/nacos', 'appstore', 404, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710700, 1341620898007281665, 1341620898007281665, '2023-02-26 21:05:30', '2023-02-26 21:05:30', 0, 0, 0, '0', 0, 1537444981390794754, 'monitors:sentinel:view', 0, '流量监控', 'http://127.0.0.1:8081', 'dashboard', 4000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1552305016701284353, 1341620898007281665, 1341620898007281665, '2022-07-27 22:48:51', '2022-07-27 22:48:51', 0, 0, 0, '0', 0, 1551957039155638274, 'depts:delete', 1, '删除部门', '/v1/depts/delete', 'delete', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710695, 1341620898007281665, 1341620898007281665, '2023-02-11 13:47:42', '2023-09-12 16:47:00', 0, 1, 0, '0', 0, 1564996817056710688, 'oss:insert', 1, '新增存储', '/v1/oss/insert', 'plus', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710694, 1341620898007281665, 1341620898007281665, '2023-02-11 13:47:03', '2023-02-11 13:47:03', 0, 0, 0, '0', 0, 1564996817056710688, 'oss:delete', 1, '删除存储', '/v1/oss/delete', 'delete', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710693, 1341620898007281665, 1341620898007281665, '2023-02-11 13:46:28', '2023-09-12 16:47:06', 0, 1, 0, '0', 0, 1564996817056710688, 'oss:update', 1, '修改存储', '/v1/oss/update', 'edit', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710692, 1341620898007281665, 1341620898007281665, '2023-02-11 13:45:24', '2023-09-12 16:46:53', 0, 2, 0, '0', 0, 1564996817056710688, 'oss:list', 1, '查询存储列表', '/v1/oss/list', 'search', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710688, 1341620898007281665, 1341620898007281665, '2023-02-10 22:43:52', '2023-02-11 18:47:28', 0, 3, 0, '0', 0, 1535878154046939137, 'oss:view', 0, '存储管理', '/v1/oss/view', 'aliyun', 901, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710686, 1341620898007281665, 1341620898007281665, '2023-02-08 22:33:02', '2023-09-12 16:46:31', 0, 1, 0, '0', 0, 1564996817056710682, 'packages:list', 1, '查询套餐列表', '/v1/packages/list', 'search', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710685, 1341620898007281665, 1341620898007281665, '2023-02-08 22:32:32', '2023-09-12 16:46:46', 0, 1, 0, '0', 0, 1564996817056710682, 'packages:update', 1, '修改套餐', '/v1/packages/update', 'edit', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710684, 1341620898007281665, 1341620898007281665, '2023-02-08 22:31:54', '2023-09-12 16:46:40', 0, 1, 0, '0', 0, 1564996817056710682, 'packages:insert', 1, '新增套餐', '/v1/packages/insert', 'plus', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710683, 1341620898007281665, 1341620898007281665, '2023-02-08 22:31:13', '2023-02-08 22:31:13', 0, 0, 0, '0', 0, 1564996817056710682, 'packages:delete', 1, '删除套餐', '/v1/packages/delete', 'delete', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710682, 1341620898007281665, 1341620898007281665, '2023-02-08 11:30:10', '2023-09-09 18:44:21', 0, 2, 0, '0', 0, 1535878154046939137, 'packages:view', 0, '套餐管理', '/v1/packages/view', 'pay-circle', 996, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1564996817056710681, 1341620898007281665, 1341620898007281665, '2023-02-07 22:52:35', '2023-09-12 16:45:39', 0, 1, 0, '0', 0, 1564996817056710672, 'tenants:list', 1, '查询租户列表', '/v1/tenants/list', 'search', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535887779873955841, 1341620898007281665, 1341620898007281665, '2022-06-22 07:10:19', '2023-09-12 16:45:03', 0, 1, 0, '0', 0, 1535881096963563522, 'users:list', 1, '查询用户列表', '/v1/users/list', 'search', 60, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1539989085181972481, 1341620898007281665, 1341620898007281665, '2022-06-24 07:01:55', '2023-09-09 18:44:27', 0, 2, 0, '0', 0, 1535878154046939137, 'dicts:view', 0, '字典管理', '/v1/dicts/view', 'dict', 900, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1539615028590673921, 1341620898007281665, 1341620898007281665, '2022-06-23 06:22:41', '2023-09-12 16:45:26', 0, 2, 0, '0', 0, 1535881096963563522, 'users:reset-password', 1, '重置密码', '/v1/users/reset-password', 'key', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1539402478934646785, 1341620898007281665, 1341620898007281665, '2022-06-24 06:45:53', '2023-09-23 11:22:55', 0, 2, 0, '0', 0, 1539396453854629890, 'logs:login-list', 1, '查询登录日志列表', '/v1/logs/login-list', 'search', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1539396453854629890, 1341620898007281665, 1341620898007281665, '2022-06-24 06:46:01', '2023-09-19 16:02:02', 0, 1, 0, '0', 0, 1539250224424394753, 'logs:login:view', 0, '登录日志', '/v1/logs/login/view', 'logininfor', 100, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1539265093588545538, 1341620898007281665, 1341620898007281665, '2022-06-24 06:46:11', '2023-09-23 11:23:02', 0, 3, 0, '0', 0, 1539251440843857922, 'logs:operate-list', 1, '查询操作日志列表', '/v1/logs/operate-list', 'search', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1539251440843857922, 1341620898007281665, 1341620898007281665, '2022-06-24 06:45:42', '2023-09-19 16:01:46', 0, 1, 0, '0', 0, 1539250224424394753, 'logs:operate:view', 0, '操作日志', '/v1/logs/operate/view', 'form', 200, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1539250224424394753, 1341620898007281665, 1341620898007281665, '2022-08-23 12:04:04', '2022-08-23 12:04:04', 0, 0, 0, '0', 0, 1535878154046939137, 'logs:view', 0, '日志管理', '/v1/logs/view', 'log', 500, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1538469199368712193, 1341620898007281665, 1341620898007281665, '2022-07-17 17:39:45', '2023-07-08 15:20:23', 0, 6, 0, '0', 0, 1537444981390794754, 'monitors:service:view', 0, '服务监控', 'https://127.0.0.1:5000', 'server', 3000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1537444981390794754, 1341620898007281665, 1341620898007281665, '2022-10-26 19:26:30', '2022-10-26 19:26:30', 0, 0, 0, '0', 0, 0, 'monitors:view', 0, '系统监控', '/v1/monitors/view', 'monitor', 8000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535888341252186114, 1341620898007281665, 1341620898007281665, '2022-06-12 23:37:47', '2023-09-12 16:45:21', 0, 1, 0, '0', 0, 1535881096963563522, 'users:delete', 1, '删除用户', '/v1/users/delete', 'delete', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535888146045083649, 1341620898007281665, 1341620898007281665, '2022-06-20 01:45:37', '2023-09-21 04:24:02', 0, 1, 0, '0', 0, 1535881096963563522, 'users:update', 1, '修改用户', '/v1/users/update', 'edit', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535887940687765505, 1341620898007281665, 1341620898007281665, '2022-07-23 15:34:06', '2023-09-12 16:45:09', 0, 1, 0, '0', 0, 1535881096963563522, 'users:insert', 1, '新增用户', '/v1/users/insert', 'plus', 50, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1540000886082768897, 1341620898007281665, 1341620898007281665, '2022-06-24 07:30:24', '2023-09-12 16:48:13', 0, 1, 0, '0', 0, 1539989085181972481, 'dicts:list', 1, '查询字典列表', '/v1/dicts/list', 'search', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535887450809835521, 1341620898007281665, 1341620898007281665, '2022-06-12 15:31:18', '2022-06-12 15:31:18', 0, 0, 0, '0', 0, 1535881356595175426, 'roles:delete', 1, '删除角色', '/v1/roles/delete', 'delete', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535887276649750530, 1341620898007281665, 1341620898007281665, '2022-06-20 01:45:31', '2023-09-12 16:44:42', 0, 1, 0, '0', 0, 1535881356595175426, 'roles:update', 1, '修改角色', '/v1/roles/update', 'edit', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535887129341599746, 1341620898007281665, 1341620898007281665, '2022-06-12 15:30:02', '2023-09-12 16:44:37', 0, 1, 0, '0', 0, 1535881356595175426, 'roles:insert', 1, '新增角色', '/v1/roles/insert', 'plus', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535886982654205953, 1341620898007281665, 1341620898007281665, '2022-06-22 07:10:10', '2023-09-12 16:44:32', 0, 1, 0, '0', 0, 1535881356595175426, 'roles:list', 1, '查询角色列表', '/v1/roles/list', 'search', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535881356595175426, 1341620898007281665, 1341620898007281665, '2022-06-12 15:07:05', '2023-09-09 18:43:55', 0, 2, 0, '0', 0, 1535878154046939137, 'roles:view', 0, '角色管理', '/v1/roles/view', 'peoples', 2000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535881096963563522, 1341620898007281665, 1341620898007281665, '2022-06-16 04:04:56', '2023-09-09 18:44:00', 0, 2, 0, '0', 0, 1535878154046939137, 'users:view', 0, '用户管理', '/v1/users/view', 'user', 1000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535878154046939137, 1341620898007281665, 1341620898007281665, '2022-10-26 19:26:21', '2023-09-09 19:06:51', 0, 21, 0, '0', 0, 0, 'view', 0, '系统管理', '/v1/view', 'system', 9000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535859588534923265, 1341620898007281665, 1341620898007281665, '2022-06-12 13:40:35', '2022-06-12 13:40:35', 0, 0, 0, '0', 0, 1391677542887788567, 'menus:delete', 1, '删除菜单', '/v1/menus/delete', 'delete', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535859326311231489, 1341620898007281665, 1341620898007281665, '2022-06-20 01:45:22', '2023-09-12 16:44:00', 0, 1, 0, '0', 0, 1391677542887788567, 'menus:update', 1, '修改菜单', '/v1/menus/update', 'edit', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535859148908949506, 1341620898007281665, 1341620898007281665, '2022-06-12 21:59:41', '2023-09-12 16:43:56', 0, 1, 0, '0', 0, 1391677542887788567, 'menus:insert', 1, '新增菜单', '/v1/menus/insert', 'plus', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1535858679453085698, 1341620898007281665, 1341620898007281665, '2022-06-22 07:09:59', '2023-09-15 18:01:54', 0, 4, 0, '0', 0, 1391677542887788567, 'menus:list', 1, '查询菜单列表', '/v1/menus/list', 'search', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545444197799067650, 1537114827246292994, 1341620898007281665, '2022-08-27 09:16:34', '2023-09-21 04:25:52', 0, 0, 0, '0', 0, 1545036486288732162, 'definitions:diagram', 1, '流程图', '/v1/definitions/diagram', 'eyeOpen', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1552304865207218177, 1341620898007281665, 1341620898007281665, '2022-07-27 22:48:15', '2023-09-12 16:44:24', 0, 1, 0, '0', 0, 1551957039155638274, 'depts:update', 1, '修改部门', '/v1/depts/update', 'edit', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1552304713121755138, 1341620898007281665, 1341620898007281665, '2022-07-27 22:47:39', '2023-09-12 16:44:18', 0, 2, 0, '0', 0, 1551957039155638274, 'depts:insert', 1, '新增部门', '/v1/depts/insert', 'plus', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1552304553893392386, 1341620898007281665, 1341620898007281665, '2022-08-25 16:32:48', '2023-09-12 16:44:13', 0, 1, 0, '0', 0, 1551957039155638274, 'depts:list', 1, '查询部门列表', '/v1/depts/list', 'search', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1551957039155638274, 1341620898007281665, 1341620898007281665, '2022-07-26 23:46:07', '2023-09-09 18:43:50', 0, 5, 0, '0', 0, 1535878154046939137, 'depts:view', 0, '部门管理', '/v1/depts/view', 'team', 2500, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1550117550498086913, 1341620898007281665, 1341620898007281665, '2022-07-21 21:56:38', '2022-07-21 21:56:38', 0, 0, 0, '0', 0, 1549758203624480770, 'messages:detail', 1, '查看消息', '/v1/messages/detail', 'eyeOpen', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1550117239402364930, 1341620898007281665, 1341620898007281665, '2022-08-29 19:19:22', '2023-09-12 16:48:53', 0, 1, 0, '0', 0, 1549758203624480770, 'messages:insert', 1, '新增消息', '/v1/messages/insert', 'plus', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1550116610713944065, 1341620898007281665, 1341620898007281665, '2022-08-25 16:32:35', '2023-09-12 16:48:48', 0, 1, 0, '0', 0, 1549758203624480770, 'messages:list', 1, '查询消息列表', '/v1/messages/list', 'search', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1549758203624480770, 1341620898007281665, 1341620898007281665, '2022-07-20 22:08:44', '2023-09-09 18:44:32', 0, 1, 0, '0', 0, 1535878154046939137, 'messages:view', 0, '消息管理', '/v1/messages/view', 'message', 800, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1547109188256260097, 1341620898007281665, 1341620898007281665, '2022-07-17 17:04:18', '2023-06-23 18:12:34', 0, 2, 0, '0', 0, 1535878154046939137, 'apidoc:view', 0, '接口文档', 'https://127.0.0.1:5555/swagger-ui.html', 'row', 400, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545445208781520897, 1537114827246292994, 1341620898007281665, '2022-08-25 13:27:25', '2023-09-23 11:33:57', 0, 3, 0, '0', 0, 1545037580289044482, 'resource:audit-task', 1, '审批任务', '/v1/resource/audit-task', 'audit', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545444853809184770, 1537114827246292994, 1341620898007281665, '2022-08-25 13:27:40', '2023-09-23 11:33:29', 0, 2, 0, '0', 0, 1545037580289044482, 'resource:task-list', 1, '查询任务列表', '/v1/resource/task-list', 'search', 50, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1391677542887788567, 1341620898007281665, 1341620898007281665, '2022-06-12 23:36:44', '2023-09-09 18:43:45', 0, 2, 0, '0', 0, 1535878154046939137, 'menus:view', 0, '菜单管理', '/v1/menus/view', 'treeTable', 3000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545443828113113090, 1537114827246292994, 1341620898007281665, '2022-07-09 00:24:56', '2023-09-21 04:25:06', 0, 1, 0, '0', 0, 1545036486288732162, 'definitions:delete', 1, '删除流程', '/v1/definitions/delete', 'delete', 30, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545443597204094977, 1537114827246292994, 1341620898007281665, '2022-07-09 00:24:01', '2023-09-21 04:25:21', 0, 1, 0, '0', 0, 1545036486288732162, 'definitions:suspend', 1, '挂起流程', '/v1/definitions/suspend', 'pause-circle', 50, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545443357407346689, 1537114827246292994, 1341620898007281665, '2022-07-09 00:23:04', '2023-09-21 04:25:24', 0, 1, 0, '0', 0, 1545036486288732162, 'definitions:activate', 1, '激活流程', '/v1/definitions/activate', 'play-circle', 40, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545442932918616065, 1537114827246292994, 1341620898007281665, '2022-08-25 16:31:34', '2023-09-21 04:21:11', 0, 1, 0, '0', 0, 1545036486288732162, 'definitions:list', 1, '查询流程列表', '/v1/definitions/list', 'search', 70, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545442687073681410, 1537114827246292994, 1341620898007281665, '2022-07-09 00:20:24', '2023-09-21 04:20:07', 0, 1, 0, '0', 0, 1545036486288732162, 'definitions:insert', 1, '新增流程', '/v1/definitions/insert', 'plus', 60, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545037580289044482, 1341620898007281665, 1341620898007281665, '2022-07-07 21:34:30', '2023-09-23 11:36:54', 0, 2, 0, '0', 0, 1560528267620061186, 'resource:workflow:view', 0, '资源流程', '/v1/resource/workflow/view', 'timeRange', 99, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545036486288732162, 1341620898007281665, 1341620898007281665, '2022-07-07 21:27:18', '2023-09-22 16:43:36', 0, 2, 0, '0', 0, 1545035717690912769, 'definitions:view', 0, '流程定义', '/v1/definitions/view', 'guide', 5000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1545035717690912769, 1341620898007281665, 1341620898007281665, '2022-10-26 19:26:25', '2023-09-22 11:23:14', 0, 1, 0, '0', 0, 0, 'workflow:view', 0, '工作流程', '/v1/workflow/view', 'tree', 7000, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1540001992288505857, 1341620898007281665, 1341620898007281665, '2022-06-24 07:33:59', '2022-06-24 00:01:26', 0, 0, 0, '0', 0, 1539989085181972481, 'dicts:delete', 1, '删除字典', '/v1/dicts/delete', 'delete', 10, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1540001809446211586, 1341620898007281665, 1341620898007281665, '2022-06-24 00:00:18', '2023-09-12 16:48:19', 0, 1, 0, '0', 0, 1539989085181972481, 'dicts:update', 1, '修改字典', '/v1/dicts/update', 'edit', 20, 0);
INSERT INTO `salon_sys_menu` (`id`, `creator`, `editor`, `create_date`, `update_date`, `del_flag`, `version`, `dept_id`, `dept_path`, `tenant_id`, `pid`, `permission`, `type`, `name`, `url`, `icon`, `sort`, `visible`) VALUES (1540001496240754689, 1341620898007281665, 1341620898007281665, '2022-06-24 07:32:06', '2023-09-12 16:48:06', 0, 1, 0, '0', 0, 1539989085181972481, 'dicts:insert', 1, '新增字典', '/v1/dicts/insert', 'plus', 30, 0);



INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216322, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1391677542887788567);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216323, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535858679453085698);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216324, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535859148908949506);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216325, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535859326311231489);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216326, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535859588534923265);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216327, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535878154046939137);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216328, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535881096963563522);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216329, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535881356595175426);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216330, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535886982654205953);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216331, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535887129341599746);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216332, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535887276649750530);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216333, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535887450809835521);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216334, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535887779873955841);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216335, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535887940687765505);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216336, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535888146045083649);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216337, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1535888341252186114);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216338, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1537444981390794754);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216339, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1538469199368712193);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216340, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1539250224424394753);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216341, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1539251440843857922);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216342, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1539265093588545538);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216343, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1539396453854629890);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216344, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1539402478934646785);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216345, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1539615028590673921);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216346, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1539989085181972481);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216347, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1540000886082768897);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216348, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1540001496240754689);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216349, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1540001809446211586);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216350, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1540001992288505857);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216351, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545035717690912769);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216352, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545036486288732162);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216353, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545037580289044482);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216354, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545442687073681410);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216355, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545442932918616065);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216356, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545443357407346689);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216357, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545443597204094977);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216358, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545443828113113090);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216359, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545444197799067650);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216360, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545444853809184770);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216361, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1545445208781520897);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216362, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1547109188256260097);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216363, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1549758203624480770);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216364, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1550116610713944065);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216365, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1550117239402364930);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216366, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1550117550498086913);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216367, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1551957039155638274);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216368, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1552304553893392386);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216369, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1552304713121755138);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216370, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1552304865207218177);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216371, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1552305016701284353);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216372, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1552525480564416514);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216373, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1552526314178142209);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216374, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1560528267620061186);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216375, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1560529603300364290);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216376, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1560530192751071234);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216377, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1560530418819862529);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216378, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562969136453316610);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216379, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562969406365167618);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216380, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562969780283174914);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216381, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562970001914392577);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216382, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562970341317472258);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216383, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562971590226014209);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216384, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562971829607526401);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216385, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562972243434336257);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216386, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562972411969859586);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216387, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562972785472630785);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216388, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562973157121519617);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216389, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562973326504292353);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216390, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562973502765723650);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216391, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562973726783500290);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216392, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1562973963560349698);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216393, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1563103435471122433);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216394, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1563104611147116545);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216395, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1563104888692600833);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216396, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1563333871266885634);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216397, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1563334139421323265);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216398, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1563334404471975938);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216399, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564214128678539265);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216400, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564214393074880513);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216401, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564227439767920641);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216402, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564228322215927810);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216403, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710657);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216404, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710664);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216405, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710665);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216406, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710666);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216407, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710672);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216408, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710673);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216409, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710674);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216410, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710675);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216411, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710676);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216412, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710677);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216413, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710678);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216414, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710679);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216415, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710680);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216416, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710681);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216417, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710682);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216418, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710683);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216419, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710684);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216420, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710685);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216421, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710686);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216422, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710688);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216423, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710692);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216424, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710693);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216425, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710694);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216426, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710695);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216427, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710699);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216428, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710700);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216429, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710701);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216430, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710702);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216431, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710703);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216432, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710704);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216433, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710705);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216434, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710706);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216435, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710707);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216436, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710708);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216437, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710709);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216438, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710710);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216439, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710711);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216440, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710712);
INSERT INTO `salon_sys_role_menu` VALUES (1702912877356216441, 1341620898007281665, 0, '2023-09-16 06:18:23', '2023-09-16 06:18:23', 0, 0, 0, '0', 0, 139167754288778857, 1564996817056710713);