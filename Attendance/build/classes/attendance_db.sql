/*
Navicat MySQL Data Transfer

Source Server         : efanhome
Source Server Version : 50147
Source Host           : localhost:3306
Source Database       : attendance_db

Target Server Type    : MYSQL
Target Server Version : 50147
File Encoding         : 65001

Date: 2012-04-04 22:34:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `att_classes`
-- ----------------------------
DROP TABLE IF EXISTS `att_classes`;
CREATE TABLE `att_classes` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_dept_id` int(11) DEFAULT NULL,
  `class_name` varchar(100) NOT NULL,
  `class_grade` varchar(20) NOT NULL,
  `class_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  KEY `pk_dept_id` (`pk_dept_id`),
  CONSTRAINT `att_classes_ibfk_1` FOREIGN KEY (`pk_dept_id`) REFERENCES `att_department` (`dept_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_classes
-- ----------------------------
INSERT INTO `att_classes` VALUES ('1', null, '网络工程2班', '2008', null);

-- ----------------------------
-- Table structure for `att_department`
-- ----------------------------
DROP TABLE IF EXISTS `att_department`;
CREATE TABLE `att_department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(200) NOT NULL,
  `dept_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_department
-- ----------------------------
INSERT INTO `att_department` VALUES ('1', '计算机科学与技术系', null);

-- ----------------------------
-- Table structure for `att_detail`
-- ----------------------------
DROP TABLE IF EXISTS `att_detail`;
CREATE TABLE `att_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_user_id` int(11) NOT NULL,
  `detail_type` varchar(30) NOT NULL,
  `detail_num` decimal(10,0) NOT NULL,
  `detail_time` date NOT NULL,
  `detail_clear` bit(1) DEFAULT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `pk_user_id` (`pk_user_id`),
  CONSTRAINT `att_detail_ibfk_1` FOREIGN KEY (`pk_user_id`) REFERENCES `att_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `att_resource`
-- ----------------------------
DROP TABLE IF EXISTS `att_resource`;
CREATE TABLE `att_resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_type` varchar(200) NOT NULL,
  `resource_value` varchar(300) NOT NULL,
  `resource_desc` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_resource
-- ----------------------------
INSERT INTO `att_resource` VALUES ('1', 'URL', '/admin/*', '系统模块');
INSERT INTO `att_resource` VALUES ('2', 'URL', '/student/*', '学生模块');

-- ----------------------------
-- Table structure for `att_role`
-- ----------------------------
DROP TABLE IF EXISTS `att_role`;
CREATE TABLE `att_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `role_desc` varchar(200) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_role
-- ----------------------------
INSERT INTO `att_role` VALUES ('1', 'admin', '系统管理员');
INSERT INTO `att_role` VALUES ('2', 'attPerson', '考勤人员');
INSERT INTO `att_role` VALUES ('3', 'student', '学生');
INSERT INTO `att_role` VALUES ('4', 'teacher', '教师');

-- ----------------------------
-- Table structure for `att_user`
-- ----------------------------
DROP TABLE IF EXISTS `att_user`;
CREATE TABLE `att_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_class_id` int(11) DEFAULT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_num` varchar(20) NOT NULL,
  `user_pwd` varchar(30) NOT NULL,
  `user_gender` bit(1) DEFAULT NULL,
  `user_birthday` date DEFAULT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_qq` varchar(20) DEFAULT NULL,
  `user_portrait` varchar(200) DEFAULT NULL,
  `user_tel` varchar(20) DEFAULT NULL,
  `user_hobby` varchar(100) DEFAULT NULL,
  `user_intro` varchar(500) DEFAULT NULL,
  `user_enabled` bit(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `pk_class_id` (`pk_class_id`),
  CONSTRAINT `att_user_ibfk_1` FOREIGN KEY (`pk_class_id`) REFERENCES `att_classes` (`class_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_user
-- ----------------------------
INSERT INTO `att_user` VALUES ('1', null, 'admin', '0807022248', 'admin', '', '2012-04-03', 'efanhome@qq.com', null, null, null, null, null, '');
INSERT INTO `att_user` VALUES ('2', null, 'user', '08070222xx', 'user', '', '2012-04-03', 'XXX@XXX.com', null, null, null, null, null, '');

-- ----------------------------
-- Table structure for `re_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `re_role_resource`;
CREATE TABLE `re_role_resource` (
  `rr_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_role_id` int(11) NOT NULL,
  `pK_resource_id` int(11) NOT NULL,
  PRIMARY KEY (`rr_id`),
  KEY `pk_role_id` (`pk_role_id`),
  KEY `pK_resource_id` (`pK_resource_id`),
  CONSTRAINT `re_role_resource_ibfk_1` FOREIGN KEY (`pk_role_id`) REFERENCES `att_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `re_role_resource_ibfk_2` FOREIGN KEY (`pK_resource_id`) REFERENCES `att_resource` (`resource_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_role_resource
-- ----------------------------
INSERT INTO `re_role_resource` VALUES ('1', '1', '1');
INSERT INTO `re_role_resource` VALUES ('2', '1', '2');
INSERT INTO `re_role_resource` VALUES ('3', '2', '2');
INSERT INTO `re_role_resource` VALUES ('4', '3', '2');
INSERT INTO `re_role_resource` VALUES ('5', '4', '2');

-- ----------------------------
-- Table structure for `re_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `re_user_role`;
CREATE TABLE `re_user_role` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_user_id` int(11) NOT NULL,
  `pk_role_id` int(11) NOT NULL,
  PRIMARY KEY (`ur_id`),
  KEY `pk_user_id` (`pk_user_id`),
  KEY `pk_role_id` (`pk_role_id`),
  CONSTRAINT `re_user_role_ibfk_1` FOREIGN KEY (`pk_user_id`) REFERENCES `att_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `re_user_role_ibfk_2` FOREIGN KEY (`pk_role_id`) REFERENCES `att_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_user_role
-- ----------------------------
INSERT INTO `re_user_role` VALUES ('1', '1', '1');
INSERT INTO `re_user_role` VALUES ('2', '2', '3');
