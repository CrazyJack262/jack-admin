SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_system_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_system_menu`;
CREATE TABLE `t_system_menu`
(
    `id`         bigint(20)          NOT NULL AUTO_INCREMENT,
    `menu_name`  varchar(45)         NOT NULL DEFAULT '',
    `menu_url`   varchar(100)        NOT NULL DEFAULT '',
    `parent_id`  bigint(20) unsigned NOT NULL DEFAULT '0',
    `menu_order` int(11) unsigned    NOT NULL DEFAULT '0',
    `menu_icon`  varchar(32)         NOT NULL DEFAULT '',
    `level`      int(11) unsigned    NOT NULL DEFAULT '0',
    `show_flag`  int(11) unsigned    NOT NULL DEFAULT '1',
    `menu_type`  int(11) unsigned    NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_system_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_system_menu`
VALUES (1, '系统管理', '/manage', 0, 1, 'setting', 0, 1, 0);
INSERT INTO `t_system_menu`
VALUES (9, '用户管理', 'manage-user', 1, 1, '', 0, 1, 0);
INSERT INTO `t_system_menu`
VALUES (10, '资源管理', 'manage-resource', 1, 1, '', 0, 1, 0);
INSERT INTO `t_system_menu`
VALUES (14, '用户列表', 'user', 9, 0, '', 0, 1, 0);
INSERT INTO `t_system_menu`
VALUES (15, '部门架构', 'org', 9, 0, '', 0, 1, 0);
INSERT INTO `t_system_menu`
VALUES (16, '权限管理', 'manage-role', 1, 0, '', 0, 1, 0);
INSERT INTO `t_system_menu`
VALUES (18, '资源配置', 'config', 10, 0, '', 0, 1, 0);
INSERT INTO `t_system_menu`
VALUES (19, '角色管理', 'role-config', 16, 1, '', 0, 1, 0);
INSERT INTO `t_system_menu`
VALUES (20, '菜单权限', 'menu-config', 16, 1, '', 0, 1, 0);
INSERT INTO `t_system_menu`
VALUES (21, '角色配置', 'role-user-config', 16, 1, '', 0, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_system_menu_res
-- ----------------------------
DROP TABLE IF EXISTS `t_system_menu_res`;
CREATE TABLE `t_system_menu_res`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `menu_id` bigint(20) DEFAULT NULL,
    `res_id`  bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_system_menu_res
-- ----------------------------
BEGIN;
INSERT INTO `t_system_menu_res`
VALUES (5, 6, 7);
COMMIT;

-- ----------------------------
-- Table structure for t_system_org
-- ----------------------------
DROP TABLE IF EXISTS `t_system_org`;
CREATE TABLE `t_system_org`
(
    `id`             int(11)          NOT NULL AUTO_INCREMENT,
    `org_name`       varchar(32)      NOT NULL DEFAULT '',
    `org_pid`        int(11) unsigned NOT NULL DEFAULT '0',
    `org_all_code`   varchar(100)     NOT NULL DEFAULT '',
    `create_user_id` int(11) unsigned NOT NULL DEFAULT '0',
    `create_time`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `version`        int(11) unsigned NOT NULL DEFAULT '0',
    `del_flag`       int(11) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_system_org
-- ----------------------------
BEGIN;
INSERT INTO `t_system_org`
VALUES (1, '顶级部门', 0, '1', 0, '2020-06-22 16:49:28', '2020-06-22 16:49:28', 0, 0);
INSERT INTO `t_system_org`
VALUES (2, '二级部门', 1, '1.2', 0, '2020-06-22 16:49:50', '2020-06-22 16:49:50', 11, 0);
INSERT INTO `t_system_org`
VALUES (3, '三级部门', 2, '1.2.3', 0, '2020-06-22 16:50:17', '2020-06-22 16:50:17', 2, 0);
INSERT INTO `t_system_org`
VALUES (4, '次级部门', 1, '1.4', 0, '2020-06-23 13:39:40', '2020-06-23 13:39:40', 1, 0);
INSERT INTO `t_system_org`
VALUES (5, '测试部门', 4, '1.4.5', 0, '2020-06-23 13:40:46', '2020-06-23 13:40:46', 0, 0);
INSERT INTO `t_system_org`
VALUES (6, '次级部门01', 4, '1.4.6', 0, '2020-06-23 13:43:29', '2020-06-23 13:43:29', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_system_org_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_org_user`;
CREATE TABLE `t_system_org_user`
(
    `id`      int(11)          NOT NULL AUTO_INCREMENT,
    `org_id`  int(11) unsigned NOT NULL DEFAULT '0',
    `user_id` int(11) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_system_org_user
-- ----------------------------
BEGIN;
INSERT INTO `t_system_org_user`
VALUES (12, 4, 2);
INSERT INTO `t_system_org_user`
VALUES (13, 2, 18);
COMMIT;

-- ----------------------------
-- Table structure for t_system_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_system_resources`;
CREATE TABLE `t_system_resources`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT,
    `resource_name` varchar(45)      NOT NULL DEFAULT '',
    `resource_url`  varchar(128)     NOT NULL DEFAULT '',
    `permission`    varchar(64)      NOT NULL DEFAULT '',
    `resource_type` int(11) unsigned NOT NULL DEFAULT '0',
    `create_time`   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_system_resources
-- ----------------------------
BEGIN;
INSERT INTO `t_system_resources`
VALUES (7, '0007', '000', '000', 0, '2020-06-29 12:04:50');
COMMIT;

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role`
(
    `id`             int(11)          NOT NULL AUTO_INCREMENT,
    `role_name`      varchar(32)      NOT NULL DEFAULT '',
    `role_desc`      varchar(128)     NOT NULL DEFAULT '',
    `create_user_id` int(11) unsigned NOT NULL DEFAULT '0',
    `create_time`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `version`        int(11) unsigned NOT NULL DEFAULT '0',
    `del_flag`       int(11) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_system_role
-- ----------------------------
BEGIN;
INSERT INTO `t_system_role`
VALUES (6, '管理员', '管理人员', 0, '2020-06-29 14:18:52', '2020-06-29 14:18:52', 0, 0);
INSERT INTO `t_system_role`
VALUES (7, '普通用户', '普通用户', 0, '2020-06-29 14:20:31', '2020-06-29 14:20:31', 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for t_system_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_menu`;
CREATE TABLE `t_system_role_menu`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) DEFAULT NULL,
    `menu_id` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 122
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_system_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_system_role_menu`
VALUES (108, 6, 1);
INSERT INTO `t_system_role_menu`
VALUES (109, 6, 9);
INSERT INTO `t_system_role_menu`
VALUES (110, 6, 14);
INSERT INTO `t_system_role_menu`
VALUES (111, 6, 15);
INSERT INTO `t_system_role_menu`
VALUES (112, 6, 10);
INSERT INTO `t_system_role_menu`
VALUES (113, 6, 18);
INSERT INTO `t_system_role_menu`
VALUES (114, 6, 16);
INSERT INTO `t_system_role_menu`
VALUES (115, 6, 19);
INSERT INTO `t_system_role_menu`
VALUES (116, 6, 20);
INSERT INTO `t_system_role_menu`
VALUES (117, 6, 21);
INSERT INTO `t_system_role_menu`
VALUES (118, 7, 9);
INSERT INTO `t_system_role_menu`
VALUES (119, 7, 14);
INSERT INTO `t_system_role_menu`
VALUES (120, 7, 15);
INSERT INTO `t_system_role_menu`
VALUES (121, 7, 1);
COMMIT;

-- ----------------------------
-- Table structure for t_system_role_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_user`;
CREATE TABLE `t_system_role_user`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `role_id` int(11) DEFAULT NULL,
    `user_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1475
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_system_role_user
-- ----------------------------
BEGIN;
INSERT INTO `t_system_role_user`
VALUES (1468, 6, 1);
INSERT INTO `t_system_role_user`
VALUES (1473, 7, 18);
COMMIT;

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user`
(
    `id`             int(11)          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `login_name`     varchar(20)      NOT NULL DEFAULT '' COMMENT '登录名称',
    `login_password` varchar(32)      NOT NULL DEFAULT '' COMMENT '密码',
    `user_name`      varchar(24)      NOT NULL DEFAULT '' COMMENT '用户名',
    `avatar_icon`    varchar(512)     NOT NULL DEFAULT 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif' COMMENT '头像图标',
    `salt`           varchar(32)      NOT NULL DEFAULT '' COMMENT '盐值',
    `user_status`    int(11)          NOT NULL DEFAULT '0' COMMENT '用户状态 0 正常 1 异常',
    `user_phone`     varchar(16)      NOT NULL DEFAULT '' COMMENT '电话号码',
    `login_time`     datetime                  DEFAULT NULL COMMENT '最后登录时间',
    `remark`         varchar(64)      NOT NULL DEFAULT '' COMMENT '备注',
    `fail_count`     int(11)          NOT NULL DEFAULT '0' COMMENT '失败次数',
    `create_user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建者id',
    `create_time`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `version`        int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
    `del_flag`       int(11) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除位 0 正常 1 删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 20
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_system_user
-- ----------------------------
BEGIN;
INSERT INTO `t_system_user`
VALUES (1, 'admin', '852ad19e1006bbb19a7085d2279076b1', '超级管理员',
        'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '5f901ba13dc84d6d8afdc8447f105f14', 0,
        '13966610126', '2020-06-16 15:45:50', '超级管理员', 0, 1, '2020-06-05 10:37:29', '2020-06-18 15:06:24', 1, 0);
INSERT INTO `t_system_user`
VALUES (2, 'test', '31384d45d1f5368be7323f867a38e5c0', '测试人员',
        'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'test', 0, '13977710124111',
        '2020-06-16 16:57:23', '测试人员', 0, 2, '2020-06-16 16:56:34', '2020-07-04 19:22:47', 4, 0);
INSERT INTO `t_system_user`
VALUES (12, 'test01', '086d6b484127c697538e6d1fd945c13a', '测试01',
        'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'test01', 0, '1396662221', NULL, '测试年',
        0, 0, '2020-06-18 14:27:12', '2020-06-18 14:27:12', 0, 1);
INSERT INTO `t_system_user`
VALUES (13, '99999', '5ec3a75ff4c4a92d063274eafc41ab0f', '2222',
        'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '99999', 0, '19999916', NULL, '7777', 0,
        13, '2020-06-18 14:35:13', '2020-06-18 15:06:33', 13, 1);
INSERT INTO `t_system_user`
VALUES (16, 'test999', '90ce94f04447182b05b8ee962d5e5e57', '测试999号',
        'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'test999', 0, '13699910126', NULL, '999',
        0, 16, '2020-06-18 15:46:21', '2020-06-18 15:46:45', 1, 1);
INSERT INTO `t_system_user`
VALUES (17, 'test666', '32b84776365a279866e038a94a44ef9c', '123456',
        'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'deac86edf2624afea9dd2ca7de53b563', 0,
        '123456', NULL, '123456', 0, 17, '2020-06-18 15:49:22', '2020-06-18 15:52:41', 3, 1);
INSERT INTO `t_system_user`
VALUES (18, 'jack', 'e9e984dda934704eeb32aa762a4aa203', 'jack',
        'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 'f703dcb5186f4f54b4b2820873c711fd', 0,
        '1329996666', NULL, 'jack', 0, 18, '2020-06-24 09:42:36', '2020-07-06 12:13:38', 1, 0);
INSERT INTO `t_system_user`
VALUES (19, '999', '4064eb1d61c9197bfdd0f602fa78ce35', '999',
        'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '4f917b75605d484281d37d5d0bb766e1', 0,
        '999', NULL, '999', 0, 19, '2020-06-24 11:44:26', '2020-07-02 15:40:32', 1, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
