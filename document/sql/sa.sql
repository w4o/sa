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

 Date: 17/05/2019 16:15:51
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
INSERT INTO `admin` VALUES (2, 'test1', '$2a$10$nzSNrbTeL0hRhtcnOX1eO.ciyS3MrAjvHPtTnyp6KkveIebns6pqO', NULL, 'test1 备注信息', NULL, NULL, 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_log
-- ----------------------------
BEGIN;
INSERT INTO `admin_log` VALUES (1, 'admin', 'http://localhost:8081', '2019-05-17 07:51:06', '登出', 1, '成功', '0:0:0:0:0:0:0:1');
INSERT INTO `admin_log` VALUES (2, 'admin', 'http://localhost:8081', '2019-05-17 07:51:09', '登陆', 1, '成功', '0:0:0:0:0:0:0:1');
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
