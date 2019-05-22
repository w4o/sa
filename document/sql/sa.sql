/*
 Navicat Premium Data Transfer

 Source Server         : docker-mysql56
 Source Server Type    : MySQL
 Source Server Version : 50644
 Source Host           : localhost:3306
 Source Schema         : sa

 Target Server Type    : MySQL
 Target Server Version : 50644
 File Encoding         : 65001

 Date: 22/05/2019 12:10:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` VALUES (1, 'admin', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'https://avatars0.githubusercontent.com/u/13061300?s=400', 'Admin 备注信息1', NULL, NULL, 0);
INSERT INTO `admin` VALUES (2, 'test1', '$2a$10$nzSNrbTeL0hRhtcnOX1eO.ciyS3MrAjvHPtTnyp6KkveIebns6pqO', NULL, 'test1 备注信息', NULL, NULL, 0);
INSERT INTO `admin` VALUES (3, 'test2', '$2a$10$Taf3xdFbhLhgndKTq.MX.eRGphcfQm1A59yawQ17emNwl8AZpqWFu', NULL, 'test2 备注信息 备注信息修改', NULL, NULL, 1);
INSERT INTO `admin` VALUES (4, 'test3', '$2a$10$VrAKmEa9uXg3PHqMO0thruso6o4f5zMs.iC7k8cIr91TAAGkq5Gc2', NULL, 'test3', '2019-05-17 06:27:52', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for admin_log
-- ----------------------------
DROP TABLE IF EXISTS `admin_log`;
CREATE TABLE `admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin` varchar(20) DEFAULT NULL COMMENT '管理员',
  `path` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `action` varchar(50) DEFAULT NULL COMMENT '动作',
  `type` tinyint(1) DEFAULT NULL COMMENT '日志类型',
  `result` varchar(200) DEFAULT NULL COMMENT '结果',
  `ip` varchar(30) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_log
-- ----------------------------
BEGIN;
INSERT INTO `admin_log` VALUES (1, 'admin', 'http://localhost:8081', '2019-05-17 07:51:06', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (2, 'admin', 'http://localhost:8081', '2019-05-17 07:51:09', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (3, 'admin', '/auth/logout', '2019-05-17 08:33:04', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (4, 'admin', '/auth/login', '2019-05-20 07:52:36', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (5, 'admin', '/auth/login', '2019-05-20 07:56:30', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (6, 'admin', '/auth/login', '2019-05-20 07:57:23', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (7, 'admin', '/auth/login', '2019-05-20 08:07:18', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (8, 'admin', '/auth/login', '2019-05-20 08:43:22', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (9, 'admin', '/auth/login', '2019-05-21 07:07:33', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (10, 'admin', '/auth/logout', '2019-05-21 07:31:45', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (11, 'test3', '/auth/login', '2019-05-21 07:31:48', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (12, 'test3', '/auth/login', '2019-05-21 07:32:40', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (13, 'test3', '/auth/login', '2019-05-21 07:32:50', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (14, 'test3', '/auth/login', '2019-05-21 07:33:50', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (15, 'test3', '/auth/login', '2019-05-21 07:34:37', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (16, 'test3', '/auth/login', '2019-05-21 07:37:19', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (17, 'test3', '/auth/login', '2019-05-21 07:37:21', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (18, 'test3', '/auth/login', '2019-05-21 07:39:19', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (19, 'test3', '/auth/login', '2019-05-21 07:41:12', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (20, 'test3', '/auth/login', '2019-05-21 08:02:17', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (21, 'test3', '/auth/login', '2019-05-21 08:04:09', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (22, 'admin', '/auth/login', '2019-05-21 08:06:23', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (23, 'admin', '/auth/logout', '2019-05-21 08:06:27', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (24, 'test3', '/auth/login', '2019-05-21 08:06:42', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (25, 'test3', '/auth/login', '2019-05-21 08:07:21', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (26, 'test3', '/auth/login', '2019-05-21 08:07:22', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (27, 'test3', '/auth/login', '2019-05-21 08:07:23', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (28, 'test3', '/auth/login', '2019-05-21 08:07:23', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (29, 'test3', '/auth/login', '2019-05-21 08:07:24', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (30, 'test3', '/auth/login', '2019-05-21 08:07:24', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (31, 'test3', '/auth/login', '2019-05-21 08:07:24', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (32, 'test3', '/auth/login', '2019-05-21 08:07:25', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (33, 'test3', '/auth/login', '2019-05-21 08:07:25', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (34, 'admin', '/auth/login', '2019-05-21 08:07:47', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (35, 'admin', '/auth/logout', '2019-05-21 08:07:51', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (36, 'admin', '/auth/login', '2019-05-21 08:07:53', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (37, 'admin', '/auth/logout', '2019-05-21 09:13:33', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (38, 'test3', '/auth/login', '2019-05-21 09:13:39', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (39, 'test1', '/auth/login', '2019-05-21 09:14:05', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (40, 'test1', '/auth/login', '2019-05-21 09:16:00', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (41, 'test1', '/auth/login', '2019-05-21 09:16:04', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (42, 'test1', '/auth/login', '2019-05-21 09:17:12', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (43, 'admin', '/auth/login', '2019-05-22 02:22:52', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (44, 'admin', '/auth/login', '2019-05-22 02:23:16', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (45, 'admin', '/auth/login', '2019-05-22 02:23:40', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (46, 'admin', '/auth/login', '2019-05-22 02:24:00', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (47, 'admin', '/auth/login', '2019-05-22 02:24:26', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (48, 'admin', '/auth/login', '2019-05-22 02:24:30', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (49, 'admin', '/auth/login', '2019-05-22 02:27:46', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (50, 'admin', '/auth/login', '2019-05-22 02:28:03', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (51, 'admin', '/auth/login', '2019-05-22 02:28:30', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (52, 'admin', '/auth/login', '2019-05-22 02:28:51', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (53, 'admin', '/auth/login', '2019-05-22 02:29:20', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (54, 'admin', '/auth/login', '2019-05-22 02:29:25', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (55, 'admin', '/auth/login', '2019-05-22 02:36:10', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (56, 'admin', '/auth/login', '2019-05-22 02:40:30', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (57, 'admin', '/auth/login', '2019-05-22 02:40:54', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (58, 'admin', '/auth/login', '2019-05-22 02:41:18', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (59, 'admin', '/auth/login', '2019-05-22 02:42:12', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (60, 'admin', '/auth/login', '2019-05-22 02:42:52', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (61, 'admin', '/auth/login', '2019-05-22 02:43:49', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (62, 'test1', '/auth/logout', '2019-05-22 02:56:37', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (63, 'admin', '/auth/login', '2019-05-22 02:56:39', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (64, 'admin', '/auth/logout', '2019-05-22 04:09:38', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (65, 'test1', '/auth/login', '2019-05-22 04:09:41', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (66, 'test1', '/auth/logout', '2019-05-22 04:09:56', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
COMMIT;

-- ----------------------------
-- Table structure for admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_relation`;
CREATE TABLE `admin_role_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `admin_role_relation` VALUES (1, 1, 1);
INSERT INTO `admin_role_relation` VALUES (2, 2, 2);
INSERT INTO `admin_role_relation` VALUES (3, 3, 2);
INSERT INTO `admin_role_relation` VALUES (4, 3, 3);
INSERT INTO `admin_role_relation` VALUES (5, 4, 3);
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, '所有权限', '*', 0, 0);
INSERT INTO `permission` VALUES (2, '系统管理', 'sys', 0, 1);
INSERT INTO `permission` VALUES (3, '管理员', 'sys:admin', 2, 1);
INSERT INTO `permission` VALUES (4, '管理员列表', 'sys:admin:list', 3, 2);
INSERT INTO `permission` VALUES (5, '查看管理员信息', 'sys:admin:read', 3, 2);
INSERT INTO `permission` VALUES (6, '创建管理员', 'sys:admin:create', 3, 2);
INSERT INTO `permission` VALUES (7, '修改管理员', 'sys:admin:update', 3, 2);
INSERT INTO `permission` VALUES (8, '删除管理员', 'sys:admin:delete', 3, 2);
INSERT INTO `permission` VALUES (9, '日志', 'sys:log', 2, 1);
INSERT INTO `permission` VALUES (10, '日志列表', 'sys:log:list', 9, 2);
INSERT INTO `permission` VALUES (11, 'Example', 'example', 0, 1);
INSERT INTO `permission` VALUES (12, 'Table', 'example:table', 11, 1);
INSERT INTO `permission` VALUES (13, 'Table列表', 'example:table:list', 12, 2);
INSERT INTO `permission` VALUES (14, 'Tree', 'example:tree', 11, 1);
INSERT INTO `permission` VALUES (15, 'Tree列表', 'example:tree:list', 14, 2);
INSERT INTO `permission` VALUES (16, 'Menu', 'menu', 0, 1);
INSERT INTO `permission` VALUES (17, 'Menu1', 'menu:menu1', 16, 1);
INSERT INTO `permission` VALUES (18, 'Menu1-1', 'menu:menu1:1', 17, 1);
INSERT INTO `permission` VALUES (19, 'Menu1-2', 'menu:menu1:2', 17, 1);
INSERT INTO `permission` VALUES (20, 'Menu1-2-1', 'menu:menu1:2:1', 19, 1);
INSERT INTO `permission` VALUES (21, 'Menu1-2-2', 'menu:menu1:2:2', 19, 1);
INSERT INTO `permission` VALUES (22, 'Menu1-3', 'menu:menu1:3', 17, 1);
INSERT INTO `permission` VALUES (23, 'Menu2', 'menu:menu2', 16, 1);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, '系统管理员', '系统管理员，具有系统全部权限', '1900-01-01 00:00:00', 0, NULL);
INSERT INTO `role` VALUES (2, '角色2', '具有系统部分权限', '1900-01-01 00:00:00', 0, NULL);
INSERT INTO `role` VALUES (3, '角色3', '具有系统部分权限', '1900-01-01 00:00:00', 0, NULL);
INSERT INTO `role` VALUES (4, 'role test1', 'role test1 description 11', '2019-05-21 02:57:10', 0, '2019-05-21 03:18:46');
INSERT INTO `role` VALUES (5, 'role test2', '', '2019-05-21 02:58:39', 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_relation`;
CREATE TABLE `role_permission_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role_permission_relation
-- ----------------------------
BEGIN;
INSERT INTO `role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `role_permission_relation` VALUES (8, NULL, NULL);
INSERT INTO `role_permission_relation` VALUES (14, 2, 2);
INSERT INTO `role_permission_relation` VALUES (15, 2, 3);
INSERT INTO `role_permission_relation` VALUES (16, 2, 4);
INSERT INTO `role_permission_relation` VALUES (17, 2, 7);
INSERT INTO `role_permission_relation` VALUES (18, 2, 9);
INSERT INTO `role_permission_relation` VALUES (19, 2, 10);
COMMIT;

-- ----------------------------
-- Table structure for table_list
-- ----------------------------
DROP TABLE IF EXISTS `table_list`;
CREATE TABLE `table_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `views` int(11) DEFAULT NULL COMMENT '阅读数',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of table_list
-- ----------------------------
BEGIN;
INSERT INTO `table_list` VALUES (1, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (2, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (3, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (4, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (5, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (6, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (7, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (8, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (9, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (10, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (11, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (12, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (13, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (14, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (15, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (16, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (17, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (18, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (19, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (20, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (21, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (22, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (23, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (24, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (25, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
INSERT INTO `table_list` VALUES (26, 'Title1', 'name', 20, 1, '1900-01-01 00:00:00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
