/*
 Navicat Premium Data Transfer

 Source Server         : 47.106.146.227_3306
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 47.106.146.227:3306
 Source Schema         : oauth2

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 29/08/2021 16:48:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用于唯一标识每一个客户端(client)，实际应用中的另一个名称叫appKey，在注册时必须生成',
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端所能访问的资源id集合，注册客户端时,根据实际需要可赋予对应的资源id',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用于指定客户端(client)的访问密匙，注册时必须生成，与appSecret是一个概念',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '指定客户端申请的权限范围',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '指定客户端所拥有的Spring Security的权限值',
  `access_token_validity` int DEFAULT NULL COMMENT '设定客户端的access_token的有效时间值',
  `refresh_token_validity` int DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('demo-client', NULL, '$2a$10$tj/PXVj9MBRdyuBKq99zeOw6oGkPVe7HNOxjmBWh.hsRmaU4IT2Ba', 'all', 'authorization_code,refresh_token', 'http://localhost:8090/res/getMsg', NULL, 3600, 36000, NULL, '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descritpion` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `pid` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, 'ROLE_HOME', 'home', '/', NULL);
INSERT INTO `sys_permission` VALUES (2, 'ROLE_ADMIN', 'ABel', '/admin', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role` (
  `id` int NOT NULL,
  `role_id` int DEFAULT NULL,
  `permission_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_permission_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission_role` VALUES (1, 1, 1);
INSERT INTO `sys_permission_role` VALUES (2, 1, 2);
INSERT INTO `sys_permission_role` VALUES (3, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES (2, 'ROLE_USER');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` int NOT NULL,
  `Sys_User_id` int DEFAULT NULL,
  `Sys_Role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_user` VALUES (1, 1, 1);
INSERT INTO `sys_role_user` VALUES (2, 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$mqU45qHkZe1MRrGrORcd.uj9UuA82.rxeqX0kpGq1WYKTNanO6hua');
INSERT INTO `sys_user` VALUES (2, 'abel', 'abel');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
