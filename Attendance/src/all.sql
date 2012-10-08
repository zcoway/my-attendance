/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50147
Source Host           : localhost:3306
Source Database       : attendance_db

Target Server Type    : MYSQL
Target Server Version : 50147
File Encoding         : 65001

Date: 2012-10-07 15:52:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `att_clas`
-- ----------------------------
DROP TABLE IF EXISTS `att_clas`;
CREATE TABLE `att_clas` (
  `clas_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_dept_id` int(11) DEFAULT NULL,
  `clas_name` varchar(100) NOT NULL,
  `clas_grade` varchar(20) NOT NULL,
  `clas_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`clas_id`),
  KEY `pk_dept_id` (`pk_dept_id`),
  CONSTRAINT `att_clas_ibfk_1` FOREIGN KEY (`pk_dept_id`) REFERENCES `att_department` (`dept_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_clas
-- ----------------------------
INSERT INTO `att_clas` VALUES ('1', '10', '网络工程2班', '2008级', '2008级网络工程2班');
INSERT INTO `att_clas` VALUES ('6', '10', '网络工程1班', '2008级', '2008级网络工程1班');
INSERT INTO `att_clas` VALUES ('7', '10', '计算机1班', '2008级', '2008级计算机1班');
INSERT INTO `att_clas` VALUES ('8', '10', '计算机2班', '2008级', '2008级计算机1班');
INSERT INTO `att_clas` VALUES ('9', '10', '网络工程', '2009级', '2008级网络工程');
INSERT INTO `att_clas` VALUES ('10', '10', '计算机', '2009级', '2009级计算机');
INSERT INTO `att_clas` VALUES ('11', '10', '物联网', '2010级', '2010级物联网');
INSERT INTO `att_clas` VALUES ('12', '10', '计算机', '2010级', '2010级计算机');
INSERT INTO `att_clas` VALUES ('13', '15', '英语1班', '2008级', '2008级英语1班');
INSERT INTO `att_clas` VALUES ('14', '15', '英语2班', '2008级', '2008级英语2班');
INSERT INTO `att_clas` VALUES ('15', '15', '日语1班', '2008级', '2008级日语1班');
INSERT INTO `att_clas` VALUES ('16', '15', '日语2班', '2008级', '2008级日语2班');
INSERT INTO `att_clas` VALUES ('17', '11', '机械1班', '2008级', '2008级机械1班');
INSERT INTO `att_clas` VALUES ('18', '12', '材料1班', '2008级', '2008级材料1班');
INSERT INTO `att_clas` VALUES ('19', '13', '电子1班', '2008级', '2008级电子1班');
INSERT INTO `att_clas` VALUES ('20', '14', '环境1班', '2008级', '2008级环境1班');
INSERT INTO `att_clas` VALUES ('21', '16', '艺术1班', '2008级', '2008级艺术1班');
INSERT INTO `att_clas` VALUES ('22', '17', '空间信息1班', '2008级', '2008级空间信息1班');
INSERT INTO `att_clas` VALUES ('23', '18', '土木1班', '2008级', '2008级土木1班');
INSERT INTO `att_clas` VALUES ('24', '19', '数理1班', '2008级', '2008级数理1班');
INSERT INTO `att_clas` VALUES ('25', '20', '管理1班', '2008级', '2008级管理1班');
INSERT INTO `att_clas` VALUES ('26', '21', '文化1班', '2008级', '2008级文化1班');
INSERT INTO `att_clas` VALUES ('27', '22', '商学1班', '2008级', '2008级商学1班');

-- ----------------------------
-- Table structure for `att_comment`
-- ----------------------------
DROP TABLE IF EXISTS `att_comment`;
CREATE TABLE `att_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_user_id` int(11) NOT NULL,
  `comment_time` datetime NOT NULL,
  `comment_content` varchar(1000) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK1026DEC18684AD22` (`pk_user_id`),
  CONSTRAINT `FK1026DEC18684AD22` FOREIGN KEY (`pk_user_id`) REFERENCES `att_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_comment
-- ----------------------------
INSERT INTO `att_comment` VALUES ('1', '165', '2012-05-25 21:25:45', '大家好！');

-- ----------------------------
-- Table structure for `att_department`
-- ----------------------------
DROP TABLE IF EXISTS `att_department`;
CREATE TABLE `att_department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(200) NOT NULL,
  `dept_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_department
-- ----------------------------
INSERT INTO `att_department` VALUES ('10', '计算机科学与技术系', '计算机科学与技术系');
INSERT INTO `att_department` VALUES ('11', '机械工程系', '机械工程系');
INSERT INTO `att_department` VALUES ('12', '材料科学与工程系', '材料科学与工程系');
INSERT INTO `att_department` VALUES ('13', '电子与电气工程系 ', '电子与电气工程系');
INSERT INTO `att_department` VALUES ('14', '环境工程系', '环境工程系');
INSERT INTO `att_department` VALUES ('15', '外语系 ', '外语系 ');
INSERT INTO `att_department` VALUES ('16', '设计艺术系', '设计艺术系');
INSERT INTO `att_department` VALUES ('17', '空间信息科学与工程系 ', '空间信息科学与工程系');
INSERT INTO `att_department` VALUES ('18', '土木工程与建筑系', '土木工程与建筑系');
INSERT INTO `att_department` VALUES ('19', '数理系', '数理系');
INSERT INTO `att_department` VALUES ('20', '管理科学系', '管理科学系');
INSERT INTO `att_department` VALUES ('21', '文化传播系', '文化传播系');
INSERT INTO `att_department` VALUES ('22', '商学系', '商学系');

-- ----------------------------
-- Table structure for `att_detail`
-- ----------------------------
DROP TABLE IF EXISTS `att_detail`;
CREATE TABLE `att_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_user_id` int(11) NOT NULL,
  `pk_week_id` int(11) NOT NULL,
  `detail_late` int(11) DEFAULT NULL,
  `detail_early` int(11) DEFAULT NULL,
  `detail_affair` int(11) DEFAULT NULL,
  `detail_ill` int(11) DEFAULT NULL,
  `detail_pub` int(11) DEFAULT NULL,
  `detail_quit` int(11) DEFAULT NULL,
  `detail_clear` varchar(20) DEFAULT NULL,
  `detail_time` datetime NOT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `pk_user_id` (`pk_user_id`),
  KEY `pk_week_id` (`pk_week_id`),
  CONSTRAINT `att_detail_ibfk_1` FOREIGN KEY (`pk_user_id`) REFERENCES `att_user` (`user_id`),
  CONSTRAINT `att_detail_ibfk_2` FOREIGN KEY (`pk_week_id`) REFERENCES `att_week` (`week_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_detail
-- ----------------------------
INSERT INTO `att_detail` VALUES ('1', '21', '1', '2', '0', '0', '0', '0', '0', '是', '2012-02-13 00:00:00');
INSERT INTO `att_detail` VALUES ('2', '21', '1', '0', '0', '0', '2', '0', '0', '否', '2012-02-13 00:00:00');
INSERT INTO `att_detail` VALUES ('3', '21', '2', null, '2', null, null, null, null, null, '2012-02-14 10:47:29');
INSERT INTO `att_detail` VALUES ('5', '165', '1', '1', '0', '0', '0', '0', '0', '', '2012-02-14 00:00:00');
INSERT INTO `att_detail` VALUES ('45', '167', '6', '1', '1', '1', '0', '0', '0', '是', '2012-05-15 17:03:33');
INSERT INTO `att_detail` VALUES ('46', '167', '6', '0', '0', '2', '0', '0', '0', '否', '2012-05-15 17:03:33');
INSERT INTO `att_detail` VALUES ('47', '167', '6', '0', '0', '0', '1', '1', '1', '', '2012-05-15 17:03:33');
INSERT INTO `att_detail` VALUES ('48', '167', '6', '1', '1', '1', '0', '0', '0', '是', '2012-05-15 17:05:02');
INSERT INTO `att_detail` VALUES ('49', '167', '6', '0', '0', '2', '0', '0', '0', '否', '2012-05-15 17:05:02');
INSERT INTO `att_detail` VALUES ('50', '167', '6', '0', '0', '0', '1', '1', '1', '', '2012-05-15 17:05:02');
INSERT INTO `att_detail` VALUES ('51', '165', '1', '1', '1', '1', '0', '0', '0', '是', '2012-05-25 00:00:00');
INSERT INTO `att_detail` VALUES ('52', '170', '1', '0', '0', '2', '0', '0', '0', '否', '2012-05-25 00:00:00');
INSERT INTO `att_detail` VALUES ('53', '170', '1', '0', '0', '0', '1', '1', '1', '是', '2012-05-25 00:00:00');
INSERT INTO `att_detail` VALUES ('54', '165', '1', '1', '1', '1', '0', '0', '0', '是', '2012-05-25 22:05:48');
INSERT INTO `att_detail` VALUES ('55', '170', '1', '0', '0', '2', '0', '0', '0', '否', '2012-05-25 22:05:48');
INSERT INTO `att_detail` VALUES ('57', '165', '1', '1', '1', '1', '0', '0', '0', '是', '2012-05-25 22:07:09');
INSERT INTO `att_detail` VALUES ('60', '165', '1', '1', '1', '1', '0', '0', '0', '是', '2012-05-25 00:00:00');
INSERT INTO `att_detail` VALUES ('61', '170', '1', '0', '0', '2', '0', '0', '0', '否', '2012-05-25 22:07:43');
INSERT INTO `att_detail` VALUES ('62', '170', '1', '0', '0', '0', '1', '1', '1', '', '2012-05-25 22:07:43');

-- ----------------------------
-- Table structure for `att_leave`
-- ----------------------------
DROP TABLE IF EXISTS `att_leave`;
CREATE TABLE `att_leave` (
  `leave_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_user_self` int(11) NOT NULL,
  `pk_user_delegate` int(11) NOT NULL,
  `pk_user_teacher` int(11) NOT NULL,
  `leave_info` varchar(200) NOT NULL,
  `leave_reason` varchar(500) NOT NULL,
  `leave_time` datetime NOT NULL,
  `leave_status` bit(1) DEFAULT b'0',
  `leave_flag` bit(1) DEFAULT b'0',
  PRIMARY KEY (`leave_id`),
  KEY `pk_user_self` (`pk_user_self`),
  KEY `pk_user_delegate` (`pk_user_delegate`),
  KEY `pk_user_teacher` (`pk_user_teacher`),
  CONSTRAINT `att_leave_ibfk_1` FOREIGN KEY (`pk_user_self`) REFERENCES `att_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `att_leave_ibfk_2` FOREIGN KEY (`pk_user_delegate`) REFERENCES `att_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `att_leave_ibfk_3` FOREIGN KEY (`pk_user_teacher`) REFERENCES `att_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_leave
-- ----------------------------
INSERT INTO `att_leave` VALUES ('12', '21', '165', '20', '5月5号至6号 共12节课', 'xx事情，情况紧急，望批假！', '2012-05-09 14:17:17', '', '');
INSERT INTO `att_leave` VALUES ('15', '21', '165', '20', 'Test', 'Test', '2012-05-10 19:43:21', '', '');
INSERT INTO `att_leave` VALUES ('18', '21', '165', '269', '共2节课', '不明', '2012-05-25 09:47:14', '', '');
INSERT INTO `att_leave` VALUES ('19', '21', '229', '20', 'sdf', 'sdfsdfsf', '2012-05-25 21:38:27', '', '');
INSERT INTO `att_leave` VALUES ('21', '165', '21', '20', '111', '111', '2012-05-26 09:34:54', '', '');

-- ----------------------------
-- Table structure for `att_message`
-- ----------------------------
DROP TABLE IF EXISTS `att_message`;
CREATE TABLE `att_message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_user_sender` int(11) NOT NULL,
  `pk_user_receiver` int(11) NOT NULL,
  `message_time` datetime NOT NULL,
  `message_title` varchar(200) NOT NULL,
  `message_content` varchar(1000) NOT NULL,
  `message_status` bit(1) DEFAULT b'0',
  PRIMARY KEY (`message_id`),
  KEY `pk_user_sender` (`pk_user_sender`),
  KEY `pk_user_receiver` (`pk_user_receiver`),
  CONSTRAINT `att_message_ibfk_1` FOREIGN KEY (`pk_user_sender`) REFERENCES `att_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `att_message_ibfk_2` FOREIGN KEY (`pk_user_receiver`) REFERENCES `att_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_message
-- ----------------------------
INSERT INTO `att_message` VALUES ('25', '21', '20', '2012-05-09 14:17:20', '请假申请', 'student 同学发来请假申请，请处理。', '');
INSERT INTO `att_message` VALUES ('26', '21', '165', '2012-05-09 14:17:24', '请假代理', 'student 同学发来请假代理，请处理。', '');
INSERT INTO `att_message` VALUES ('27', '21', '20', '2012-05-10 19:35:07', '请假申请', 'student 同学发来请假申请，请处理。', '');
INSERT INTO `att_message` VALUES ('28', '21', '165', '2012-05-10 19:35:07', '请假代理', 'student 同学发来请假代理，请处理。', '');
INSERT INTO `att_message` VALUES ('29', '21', '20', '2012-05-10 19:36:58', '请假申请', 'student 同学发来请假申请，请处理。', '');
INSERT INTO `att_message` VALUES ('30', '21', '20', '2012-05-10 19:43:30', '请假申请', 'student 同学发来请假申请，请处理。', '');
INSERT INTO `att_message` VALUES ('31', '21', '165', '2012-05-10 19:43:38', '请假代理', 'student 同学发来请假代理，请处理。', '');
INSERT INTO `att_message` VALUES ('32', '21', '20', '2012-05-10 19:44:49', '请假申请', 'student 同学发来请假申请，请处理。', '');
INSERT INTO `att_message` VALUES ('34', '165', '170', '2012-05-24 22:12:28', '哈哈哈', '你是吴斌！', '');
INSERT INTO `att_message` VALUES ('35', '165', '170', '2012-05-24 22:15:41', 'abc', 'cba', '');
INSERT INTO `att_message` VALUES ('36', '21', '269', '2012-05-25 09:49:27', '请假申请', '2008级网络工程2班谢国锋 同学发来请假申请，请处理。', '');
INSERT INTO `att_message` VALUES ('37', '21', '165', '2012-05-25 09:49:57', '请假代理', '2008级网络工程2班谢国锋 同学发来请假代理，请处理。', '');
INSERT INTO `att_message` VALUES ('38', '269', '21', '2012-05-25 12:56:20', 'Java Test', 'Java Test', '');
INSERT INTO `att_message` VALUES ('39', '21', '21', '2012-05-25 13:33:10', '回复请假代理', '未说明请假时间！', '');
INSERT INTO `att_message` VALUES ('40', '269', '21', '2012-05-25 13:53:39', '回复请假申请', '未说明请假原因！', '');
INSERT INTO `att_message` VALUES ('41', '21', '20', '2012-05-25 21:38:30', '请假申请', '2008级-网络工程2班谢国锋 同学发来请假申请，请处理。', '');
INSERT INTO `att_message` VALUES ('42', '21', '229', '2012-05-25 21:38:31', '请假代理', '2008级-网络工程2班谢国锋 同学发来请假代理，请处理。', '');
INSERT INTO `att_message` VALUES ('43', '229', '21', '2012-05-25 21:53:14', '回复请假代理', 'dsfsdfdf', '');
INSERT INTO `att_message` VALUES ('44', '165', '20', '2012-05-26 09:33:21', '请假申请', '2008级-网络工程2班林灿煌 同学发来请假申请，请处理。', '');
INSERT INTO `att_message` VALUES ('45', '165', '21', '2012-05-26 09:33:48', '请假代理', '2008级-网络工程2班林灿煌 同学发来请假代理，请处理。', '');
INSERT INTO `att_message` VALUES ('46', '165', '20', '2012-05-26 09:35:00', '请假申请', '2008级-网络工程2班林灿煌 同学发来请假申请，请处理。', '');
INSERT INTO `att_message` VALUES ('47', '165', '21', '2012-05-26 09:35:02', '请假代理', '2008级-网络工程2班林灿煌 同学发来请假代理，请处理。', '');

-- ----------------------------
-- Table structure for `att_resource`
-- ----------------------------
DROP TABLE IF EXISTS `att_resource`;
CREATE TABLE `att_resource` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `resource_name` varchar(200) NOT NULL,
  `resource_type` varchar(200) NOT NULL,
  `resource_value` varchar(300) NOT NULL,
  `resource_desc` varchar(300) DEFAULT NULL,
  `resource_enabled` bit(1) NOT NULL,
  `resource_isLeaf` bit(1) NOT NULL,
  PRIMARY KEY (`resource_id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `att_resource_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `att_resource` (`resource_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_resource
-- ----------------------------
INSERT INTO `att_resource` VALUES ('1', null, '主菜单', 'att_menu', '', '主菜单', '', '');
INSERT INTO `att_resource` VALUES ('3', '40', '考勤信息录入', 'att_menu_category_item', 'javascript:addTab(\'考勤信息录入\',\'../attendance/common/week_input.action\');', '周考勤信息录入', '', '');
INSERT INTO `att_resource` VALUES ('4', '40', '考勤表管理', 'att_menu_category_item', 'javascript:addTab(\'周考勤表管理\',\'../attendance/common/week_manager.action\');', '周考勤表管理', '', '');
INSERT INTO `att_resource` VALUES ('6', '1', '首页', 'url', '/index/*.action', '首页', '', '');
INSERT INTO `att_resource` VALUES ('7', '1', '系统管理', 'att_menu_category', 'javascript:addTab()', '系统管理', '', '');
INSERT INTO `att_resource` VALUES ('8', '7', '用户管理', 'att_menu_category_item', 'javascript:addTab(\'用户管理\',\'../system/user_manager.action\');', '用户管理', '', '');
INSERT INTO `att_resource` VALUES ('9', '7', '角色管理', 'att_menu_category_item', 'javascript:addTab(\'角色管理\',\'../system/role_manager.action\');', '角色管理', '', '');
INSERT INTO `att_resource` VALUES ('10', '7', '资源管理', 'att_menu_category_item', 'javascript:addTab(\'资源管理\',\'../system/resource_manager.action\');', '资源管理', '', '');
INSERT INTO `att_resource` VALUES ('11', '7', '用户角色管理', 'att_menu_category_item', 'javascript:addTab(\'用户角色管理\',\'../system/user_role_manager.action\');', '用户角色管理', '', '');
INSERT INTO `att_resource` VALUES ('12', '7', '角色资源管理', 'att_menu_category_item', 'javascript:addTab(\'角色资源管理\',\'../system/role_resource_manager.action\');', '角色资源管理', '', '');
INSERT INTO `att_resource` VALUES ('13', '40', '查看考勤信息', 'att_menu_category_item', 'javascript:addTab(\'查看考勤信息\',\'../attendance/common/week_view.action\');', '查看考勤信息', '', '');
INSERT INTO `att_resource` VALUES ('15', '1', '假日考勤', 'att_menu_category', 'javascript:addTab()', '假日考勤', '', '');
INSERT INTO `att_resource` VALUES ('16', '15', '假日考勤表管理', 'att_menu_category_item', 'javascript:addTab()', '假日考勤表管理', '', '');
INSERT INTO `att_resource` VALUES ('17', '15', '填写假日考勤信息', 'att_menu_category_item', 'javascript:addTab()', '填写假日考勤信息', '', '');
INSERT INTO `att_resource` VALUES ('18', '15', '查看假日考勤表', 'att_menu_category_item', 'javascript:addTab()', '查看假日考勤表', '', '');
INSERT INTO `att_resource` VALUES ('19', '1', '网上办事', 'att_menu_category', 'javascript:addTab()', '网上办事', '', '');
INSERT INTO `att_resource` VALUES ('20', '19', '请假申请', 'att_menu_category_item', 'javascript:addTab(\'请假申请\',\'../leave/leave_manager.action\');', '请假申请', '', '');
INSERT INTO `att_resource` VALUES ('21', '19', '审核/代理确认', 'att_menu_category_item', 'javascript:addTab(\'审核/代理确认\',\'../leave/leave_auth.action\');', '审核/代理确认', '', '');
INSERT INTO `att_resource` VALUES ('22', '19', '站内信', 'att_menu_category_item', 'javascript:addTab(\'站内信管理\',\'../message/message_manager.action\');', '站内信', '', '');
INSERT INTO `att_resource` VALUES ('23', '1', '留言板', 'att_menu_category', 'javascript:addTab();', '留言板', '', '');
INSERT INTO `att_resource` VALUES ('24', '23', '留言列表', 'att_menu_category_item', 'javascript:addTab(\'留言\',\'../comment/comment_manager.action\');', '留言列表', '', '');
INSERT INTO `att_resource` VALUES ('25', '1', '信息数据管理', 'att_menu_category', 'javascript:addTab()', '信息数据管理', '', '');
INSERT INTO `att_resource` VALUES ('26', '7', '系部管理', 'att_menu_category_item', 'javascript:addTab(\'系部管理\',\'../system/dept_manager.action\');', '系部管理', '', '');
INSERT INTO `att_resource` VALUES ('27', '7', '班级管理', 'att_menu_category_item', 'javascript:addTab(\'班级管理\',\'../system/clas_manager.action\');', '班级管理', '', '');
INSERT INTO `att_resource` VALUES ('28', '1', '系统维护', 'att_menu_category', 'javascript:addTab()', '系统维护', '', '');
INSERT INTO `att_resource` VALUES ('29', '28', '数据库备份/还原', 'att_menu_category_item', 'javascript:addTab()', '数据库备份/还原', '', '');
INSERT INTO `att_resource` VALUES ('30', '28', '登陆日志', 'att_menu_category_item', 'javascript:addTab()', '登陆日志', '', '');
INSERT INTO `att_resource` VALUES ('39', '1', '测试5', 'att_menu_category', '测试5', '测试5', '', '');
INSERT INTO `att_resource` VALUES ('40', '1', '日常考勤', 'att_menu_category', '', '日常考勤', '', '');
INSERT INTO `att_resource` VALUES ('41', null, '厦门理工学院', 'att_tree', '-1', '厦门理工学院', '', '');
INSERT INTO `att_resource` VALUES ('42', '41', '计算机科学与技术系', 'att_tree_dept', '10', '计算机科学与技术系', '', '');
INSERT INTO `att_resource` VALUES ('43', '41', '机械工程系', 'att_tree_dept', '11', '机械工程系', '', '');
INSERT INTO `att_resource` VALUES ('44', '41', '材料科学与工程系', 'att_tree_dept', '12', '材料科学与工程系', '', '');
INSERT INTO `att_resource` VALUES ('45', '41', '电子与电气工程系', 'att_tree_dept', '13', '电子与电气工程系', '', '');
INSERT INTO `att_resource` VALUES ('46', '41', '环境工程系', 'att_tree_dept', '14', '环境工程系', '', '');
INSERT INTO `att_resource` VALUES ('47', '41', '外语系', 'att_tree_dept', '15', '外语系', '', '');
INSERT INTO `att_resource` VALUES ('48', '41', '设计艺术系', 'att_tree_dept', '16', '设计艺术系', '', '');
INSERT INTO `att_resource` VALUES ('49', '41', '空间信息科学与工程系', 'att_tree_dept', '17', '空间信息科学与工程系', '', '');
INSERT INTO `att_resource` VALUES ('50', '41', '土木工程与建筑系', 'att_tree_dept', '18', '土木工程与建筑系', '', '');
INSERT INTO `att_resource` VALUES ('51', '41', '数理系', 'att_tree_dept', '19', '数理系', '', '');
INSERT INTO `att_resource` VALUES ('52', '41', '管理科学系', 'att_tree_dept', '20', '管理科学系', '', '');
INSERT INTO `att_resource` VALUES ('53', '41', '文化传播系', 'att_tree_dept', '21', '文化传播系', '', '');
INSERT INTO `att_resource` VALUES ('54', '41', '商学系', 'att_tree_dept', '22', '商学系', '', '');
INSERT INTO `att_resource` VALUES ('55', '42', '计算机系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('56', '42', '计算机系2009级', 'att_tree_dept_grade', '0', '2009级', '', '');
INSERT INTO `att_resource` VALUES ('57', '42', '计算机系2010级', 'att_tree_dept_grade', '0', '2010级', '', '');
INSERT INTO `att_resource` VALUES ('58', '42', '计算机系2011级', 'att_tree_dept_grade', '0', '2011级', '', '');
INSERT INTO `att_resource` VALUES ('59', '55', '2008级网络工程2班', 'att_tree_dept_grade_clas', '1', '网络工程2班', '', '');
INSERT INTO `att_resource` VALUES ('60', '55', '2008级网络工程1班', 'att_tree_dept_grade_clas', '6', '网络工程1班', '', '');
INSERT INTO `att_resource` VALUES ('61', '55', '2008级计算机1班', 'att_tree_dept_grade_clas', '7', '计算机1班', '', '');
INSERT INTO `att_resource` VALUES ('62', '55', '2008级计算机2班', 'att_tree_dept_grade_clas', '8', '计算机2班', '', '');
INSERT INTO `att_resource` VALUES ('63', '47', '外语系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('64', '63', '2008级英语1班', 'att_tree_dept_grade_clas', '13', '英语1班', '', '');
INSERT INTO `att_resource` VALUES ('65', '63', '2008级英语2班', 'att_tree_dept_grade_clas', '14', '英语2班', '', '');
INSERT INTO `att_resource` VALUES ('66', '63', '2008级日语1班', 'att_tree_dept_grade_clas', '15', '日语1班', '', '');
INSERT INTO `att_resource` VALUES ('67', '63', '2008级日语2班', 'att_tree_dept_grade_clas', '16', '日语2班', '', '');
INSERT INTO `att_resource` VALUES ('68', '7', '学年管理', 'att_menu_category_item', 'javascript:addTab(\'学年管理\',\'../year/year_manager.action\');', '学年管理', '', '');
INSERT INTO `att_resource` VALUES ('69', '59', '计算机系-08级网络工程2班-2012-2013学年', 'att_tree_dept_grade_clas_year', '1', '2012-2013学年', '', '');
INSERT INTO `att_resource` VALUES ('70', '59', '计算机系-08级网络工程2班-2011-2012学年', 'att_tree_dept_grade_clas_year', '1', '2011-2012学年', '', '');
INSERT INTO `att_resource` VALUES ('71', '69', '计算机科学与技术系-2008级网络工程2班-2012-2013学年上学期', 'att_tree_dept_grade_clas_year_semester', '3', '上学期', '', '');
INSERT INTO `att_resource` VALUES ('72', '69', '计算机科学与技术系-2008级网络工程2班-2012-2013学年下学期', 'att_tree_dept_grade_clas_year_semester', '4', '下学期', '', '');
INSERT INTO `att_resource` VALUES ('73', '71', '计算机科学与技术系-2008级网络工程2班-2012-2013学年上学期-1', 'att_tree_dept_grade_clas_year_semester_week', '1', '第1周', '', '');
INSERT INTO `att_resource` VALUES ('74', '71', '计算机科学与技术系-2008级网络工程2班-2012-2013学年上学期-2', 'att_tree_dept_grade_clas_year_semester_week', '2', '第2周', '', '');
INSERT INTO `att_resource` VALUES ('75', '60', '计算机系-08级网络工程1班-2012-2013学年', 'att_tree_dept_grade_clas_year', '6', '2012-2013学年', '', '');
INSERT INTO `att_resource` VALUES ('76', '60', '计算机系-08级网络工程1班-2011-2012学年', 'att_tree_dept_grade_clas_year', '6', '2011-2012学年', '', '');
INSERT INTO `att_resource` VALUES ('77', '75', '计算机系-08级网络工程1班-2012-2013学年-上学期', 'att_tree_dept_grade_clas_year_semester', '10', '上学期', '', '');
INSERT INTO `att_resource` VALUES ('78', '75', '计算机系-08级网络工程1班-2012-2013学年-下学期', 'att_tree_dept_grade_clas_year_semester', '11', '下学期', '', '');
INSERT INTO `att_resource` VALUES ('79', '77', '计算机系-08级网络工程1班-2012-2013学年-上学期-第一周', 'att_tree_dept_grade_clas_year_semester_week', '6', '第一周', '', '');
INSERT INTO `att_resource` VALUES ('80', null, 'wwwww', 'qqqqqq', 'qqqqq', 'fsdfsdf', '', '');
INSERT INTO `att_resource` VALUES ('81', '43', '机械系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('82', '44', '材料科学与工程系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('83', '45', '电子与电气工程系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('84', '46', '环境工程系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('85', '48', '设计艺术系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('86', '49', '空间信息科学与工程系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('87', '50', '土木工程系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('88', '51', '数理系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('89', '52', '管理科学系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('90', '53', '文化传播系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('91', '54', '商学系2008级', 'att_tree_dept_grade', '0', '2008级', '', '');
INSERT INTO `att_resource` VALUES ('92', '81', '机械1班', 'att_tree_dept_grade_clas', '17', '机械1班', '', '');
INSERT INTO `att_resource` VALUES ('93', '82', '材料1班', 'att_tree_dept_grade_clas', '18', '材料1班', '', '');
INSERT INTO `att_resource` VALUES ('94', '83', '电子1班', 'att_tree_dept_grade_clas', '19', '电子1班', '', '');
INSERT INTO `att_resource` VALUES ('95', '84', '环境1班', 'att_tree_dept_grade_clas', '20', '环境1班', '', '');
INSERT INTO `att_resource` VALUES ('96', '85', '艺术1班', 'att_tree_dept_grade_clas', '21', '艺术1班', '', '');
INSERT INTO `att_resource` VALUES ('97', '86', '空间信息1班', 'att_tree_dept_grade_clas', '22', '空间信息1班', '', '');
INSERT INTO `att_resource` VALUES ('98', '87', '土木1班', 'att_tree_dept_grade_clas', '23', '土木1班', '', '');
INSERT INTO `att_resource` VALUES ('99', '88', '数理1班', 'att_tree_dept_grade_clas', '24', '数理1班', '', '');
INSERT INTO `att_resource` VALUES ('100', '89', '管理1班', 'att_tree_dept_grade_clas', '25', '管理1班', '', '');
INSERT INTO `att_resource` VALUES ('101', '90', '文化1班', 'att_tree_dept_grade_clas', '26', '文化1班', '', '');
INSERT INTO `att_resource` VALUES ('102', '91', '商学1班', 'att_tree_dept_grade_clas', '27', '商学1班', '', '');

-- ----------------------------
-- Table structure for `att_role`
-- ----------------------------
DROP TABLE IF EXISTS `att_role`;
CREATE TABLE `att_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  `role_enabled` bit(1) NOT NULL,
  `role_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_role
-- ----------------------------
INSERT INTO `att_role` VALUES ('1', 'ROLE_ADMIN', '', '系统管理员');
INSERT INTO `att_role` VALUES ('2', 'ROLE_PRESIDENT', '', '学院管理员');
INSERT INTO `att_role` VALUES ('3', 'ROLE_DEPT_CS', '', '计算机系管理员');
INSERT INTO `att_role` VALUES ('6', 'ROLE_DEPT_CS_08', '', '计算机系08级管理员');
INSERT INTO `att_role` VALUES ('7', 'ROLE_DEPT_CS_09', '', '计算机系09级管理员');
INSERT INTO `att_role` VALUES ('36', 'ROLE_DEPT_CS_10', '', '计算机系10级管理员');
INSERT INTO `att_role` VALUES ('37', 'ROLE_DEPT_CS_11', '', '计算机系11级管理员');
INSERT INTO `att_role` VALUES ('38', 'ROLE_DEPT_CS_08_NETWORK_1', '', '计算机系08级网络工程1班');
INSERT INTO `att_role` VALUES ('39', 'ROLE_DEPT_CS_08_NETWORK_2', '', '计算机系08级网络工程2班');
INSERT INTO `att_role` VALUES ('40', 'ROLE_DEPT_CS_08_COMPUTER_1', '', '计算机系08级计算机1班');
INSERT INTO `att_role` VALUES ('41', 'ROLE_DEPT_CS_08_COMPUTER_2', '', '计算机系08级计算机2班');

-- ----------------------------
-- Table structure for `att_user`
-- ----------------------------
DROP TABLE IF EXISTS `att_user`;
CREATE TABLE `att_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_clas_id` int(11) DEFAULT NULL,
  `pk_dept_id` int(11) DEFAULT NULL,
  `user_name` varchar(100) NOT NULL,
  `user_num` varchar(20) NOT NULL,
  `user_pwd` varchar(100) NOT NULL,
  `user_gender` bit(1) DEFAULT NULL,
  `user_birthday` date DEFAULT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_qq` varchar(20) DEFAULT NULL,
  `user_portrait` varchar(200) DEFAULT NULL,
  `user_tel` varchar(20) NOT NULL,
  `user_hobby` varchar(100) DEFAULT NULL,
  `user_intro` varchar(500) DEFAULT NULL,
  `user_enabled` bit(1) NOT NULL,
  `user_flag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `pk_dept_id` (`pk_dept_id`),
  KEY `pk_clas_id` (`pk_clas_id`),
  CONSTRAINT `att_user_ibfk_2` FOREIGN KEY (`pk_dept_id`) REFERENCES `att_department` (`dept_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `att_user_ibfk_3` FOREIGN KEY (`pk_clas_id`) REFERENCES `att_clas` (`clas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_user
-- ----------------------------
INSERT INTO `att_user` VALUES ('19', null, null, '系统管理员', '007', 'd033e22ae348aeb5660fc2140aec35850c4da997', '', '1989-09-02', 'efanhome@qq.com', '253606840', null, '13860136921', '音乐', '请勿删除该用户！', '', null);
INSERT INTO `att_user` VALUES ('20', null, '10', '吴芸', '001', 'd033e22ae348aeb5660fc2140aec35850c4da997', '', '2012-05-03', 'yw@xmut.edu.cn', '253606840', null, '13950164836', '篮球', 'abc', '', '');
INSERT INTO `att_user` VALUES ('21', '1', '10', '谢国锋', '0807022228', 'd033e22ae348aeb5660fc2140aec35850c4da997', '', '1988-09-17', 'efanhome@qq.com', '1320613713', null, '13860136921', '绘画', null, '', null);
INSERT INTO `att_user` VALUES ('165', '1', '10', '林灿煌', '0807022248', 'd033e22ae348aeb5660fc2140aec35850c4da997', '', '1989-09-02', 'efanhome@qq.com', '253606840', null, '13860136921', '书法', '不想说。。', '', null);
INSERT INTO `att_user` VALUES ('167', '6', '10', '张三', '0807022101', 'd033e22ae348aeb5660fc2140aec35850c4da997', '', '1989-04-05', 'efanhome@qq.com', '254606840', null, '13860136921', null, null, '', null);
INSERT INTO `att_user` VALUES ('168', '6', '10', '李四', '0807022102', 'd033e22ae348aeb5660fc2140aec35850c4da997', '', '1989-02-04', 'efanhome@qq.com', '253506840', null, '1386036921', null, null, '', null);
INSERT INTO `att_user` VALUES ('170', '1', '10', '吴斌', '0807022230', 'd033e22ae348aeb5660fc2140aec35850c4da997', '', '1989-05-25', '222@qq.com', '2222', '', '13860130119', 'aaaa', 'aaaa', '', null);
INSERT INTO `att_user` VALUES ('221', '1', '10', '李河山', '0807022208', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-08-21', '478990002@qq.com', null, null, '15859281352', null, null, '', null);
INSERT INTO `att_user` VALUES ('222', '1', '10', '钟旭平', '0807022247', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-02-10', '283548107@qq.com', null, null, '13599518622', null, null, '', null);
INSERT INTO `att_user` VALUES ('223', '1', '10', '郑兰晶', '0807022243', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1984-04-15', '181413889@qq.com', null, null, '15960827698', null, null, '', null);
INSERT INTO `att_user` VALUES ('224', '1', '10', '张瑞', '0807022250', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-12-03', '476663840@qq.com', null, null, '13720889476', null, null, '', null);
INSERT INTO `att_user` VALUES ('225', '1', '10', '陈少斌', '0807022235', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-08-21', '544280652@qq.com', null, null, '15959341626', null, null, '', null);
INSERT INTO `att_user` VALUES ('226', '1', '10', '戴立滨', '0807022209', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-01-28', '459913004@qq.com', null, null, '18959291345', null, null, '', null);
INSERT INTO `att_user` VALUES ('227', '1', '10', '黄晨暄', '0807022223', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-01-08', 'hcxuhuaea@Yahoo.com.cn', null, null, '15880222443', null, null, '', null);
INSERT INTO `att_user` VALUES ('228', '1', '10', '叶细锋', '0807022225', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1987-10-21', '280520854@qq.com', null, null, '15859289879', null, null, '', null);
INSERT INTO `att_user` VALUES ('229', '1', '10', '李芬芳', '0807022221', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-05-15', '308017183@qq.com', null, null, '15985853386', null, null, '', null);
INSERT INTO `att_user` VALUES ('230', '1', '10', '汪延杰', '0807022226', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-08-07', '451082173@qq.com', null, null, '15959367095', null, null, '', null);
INSERT INTO `att_user` VALUES ('231', '1', '10', '唐凯', '0807022218', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1983-09-02', '234786609@qq.com', null, null, '15159288500', null, null, '', null);
INSERT INTO `att_user` VALUES ('232', '1', '10', '谢斌', '0807022219', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-12-22', '251263640@qq.com', null, null, '18959265491', null, null, '', null);
INSERT INTO `att_user` VALUES ('233', '1', '10', '方令', '0807022205', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-07-04', '731803256@qq.com', null, null, '18959262641', null, null, '', null);
INSERT INTO `att_user` VALUES ('234', '1', '10', '黄晓淳', '0807022238', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-05-05', '513227970@qq.com', null, null, '15859281710', null, null, '', null);
INSERT INTO `att_user` VALUES ('235', '1', '10', '陆志道', '0807022242', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-03-20', '843034984@qq.com', null, null, '15985850549', null, null, '', null);
INSERT INTO `att_user` VALUES ('236', '1', '10', '李晴', '0807022251', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-10-19', '414587336@qq.com', null, null, '15859209605', null, null, '', null);
INSERT INTO `att_user` VALUES ('237', '1', '10', '周渊彬', '0807022231', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-05-25', '454173048@qq.com', null, null, '15105960509', null, null, '', null);
INSERT INTO `att_user` VALUES ('238', '1', '10', '赖晓伟', '0807022229', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-09-17', '1014776675@qq.com', null, null, '15880294205', null, null, '', null);
INSERT INTO `att_user` VALUES ('239', '1', '10', '钱玉昕', '0807022246', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-07-27', '385675924@qq.com', null, null, '15959365316', null, null, '', null);
INSERT INTO `att_user` VALUES ('240', '1', '10', '李世炯', '0807022222', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1991-09-25', '511411973@qq.com', null, null, '18959265953', null, null, '', null);
INSERT INTO `att_user` VALUES ('241', '1', '10', '陈德原', '0807022224', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-09-30', '747390841@qq.com', null, null, '18959265982', null, null, '', null);
INSERT INTO `att_user` VALUES ('242', '1', '10', '柯盛兵', '0807022207', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', null, '996325359@qq.com', null, null, '15980997261', null, null, '', null);
INSERT INTO `att_user` VALUES ('243', '1', '10', '陈雪淋', '0807022216', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-08-10', '290244755@qq.com', null, null, '15985835240', null, null, '', null);
INSERT INTO `att_user` VALUES ('244', '1', '10', '苏雅静', '0807022241', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-05-10', '1017124487@qq.com', null, null, '15980999281', null, null, '', null);
INSERT INTO `att_user` VALUES ('245', '1', '10', '林耀东', '0807022245', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-12-14', '445421625@qq.com', null, null, '15859248683', null, null, '', null);
INSERT INTO `att_user` VALUES ('246', '1', '10', '叶龙英', '0807022232', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-01-17', '358938025@qq.com', null, null, '15105981949', null, null, '', null);
INSERT INTO `att_user` VALUES ('247', '1', '10', '王荣跃', '0807022252', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-03-12', '416848259@qq.com', null, null, '13656029028', null, null, '', null);
INSERT INTO `att_user` VALUES ('248', '1', '10', '杨宾', '0807022240', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-12-12', '457734511@qq.com', null, null, '15859202317', null, null, '', null);
INSERT INTO `att_user` VALUES ('249', '1', '10', '谢桂珍', '0807022236', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-10-24', '469732699@qq.com', null, null, '13799260311', null, null, '', null);
INSERT INTO `att_user` VALUES ('250', '1', '10', '王伟峰', '0807022244', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-07-26', '835620912@qq.com', null, null, '13696947443', null, null, '', null);
INSERT INTO `att_user` VALUES ('251', '1', '10', '陈伟军', '0807022201', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-06-02', '771328668@qq.com', null, null, '18059833059', '测试2', null, '', null);
INSERT INTO `att_user` VALUES ('252', '1', '10', '卢龙祥', '0807022217', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-01-12', '32741220@qq.com', null, null, '18606091658', null, null, '', null);
INSERT INTO `att_user` VALUES ('253', '1', '10', '潘进源', '0807022220', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-10-06', '380943942@qq.com', null, null, '15985854851', null, null, '', null);
INSERT INTO `att_user` VALUES ('254', '1', '10', '吴彦森', '0807022255', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-10-11', '461762386@qq.com', null, null, '15985852270', null, null, '', null);
INSERT INTO `att_user` VALUES ('255', '1', '10', '林国顺', '0807022249', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1987-01-07', '275922225@qq.com', null, null, '15060703107', null, null, '', null);
INSERT INTO `att_user` VALUES ('256', '1', '10', '陈勤', '0807022214', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-06-01', '86518379@qq.com', null, null, '15080319037', null, null, '', null);
INSERT INTO `att_user` VALUES ('257', '1', '10', '李佳莉', '0807022237', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-10-24', '540880160@qq.com', null, null, '18959292656', null, null, '', null);
INSERT INTO `att_user` VALUES ('258', '1', '10', '郑光辉', '0807022212', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', null, '935484915@qq.com', null, null, '13559211137', null, null, '', null);
INSERT INTO `att_user` VALUES ('259', '1', '10', '刘加鸿', '0807022234', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-11-08', '630526826@qq.com', null, null, '15880226442', null, null, '', null);
INSERT INTO `att_user` VALUES ('260', '1', '10', '江泽峰', '0807022213', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-03-03', '346079990@qq.com', null, null, '18059230683', null, null, '', null);
INSERT INTO `att_user` VALUES ('261', '1', '10', '吴静茜', '0807022227', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-02-09', '825520252@qq.com', null, null, '15080316264', null, null, '', null);
INSERT INTO `att_user` VALUES ('262', '1', '10', '丰奇炜', '0807022239', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-08-09', '398127816@qq.com', null, null, '15985839812', null, null, '', null);
INSERT INTO `att_user` VALUES ('263', '1', '10', '王文涛', '0807022254', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-09-18', '349293999@qq.com', null, null, '15859289831', null, null, '', null);
INSERT INTO `att_user` VALUES ('264', '1', '10', '梁海舰', '0807022215', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-03-05', '125353769@qq.com', null, null, '15080316454', null, null, '', null);
INSERT INTO `att_user` VALUES ('265', '1', '10', '李勇', '0807022202', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-10-23', '582947753@qq.com', null, null, '15985854936', null, null, '', null);
INSERT INTO `att_user` VALUES ('266', '1', '10', '蔡艺强', '0807022204', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1989-02-13', '505434271@qq.com', null, null, '15985854936', null, null, '', null);
INSERT INTO `att_user` VALUES ('267', '1', '10', '任新伟', '0807022210', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1990-10-22', '362922604@qq.com', null, null, '15080319051', null, null, '', null);
INSERT INTO `att_user` VALUES ('268', '1', '10', '林剑影', '0807022203', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', '', '1988-10-24', '510221875@qq.com', null, null, '18059833059', '测试4', null, '', null);
INSERT INTO `att_user` VALUES ('269', null, '10', '姜春艳', '002', 'd033e22ae348aeb5660fc2140aec35850c4da997', '', '1973-03-01', 'efanhome@qq.com', null, null, '13860136921', null, null, '', '');

-- ----------------------------
-- Table structure for `att_week`
-- ----------------------------
DROP TABLE IF EXISTS `att_week`;
CREATE TABLE `att_week` (
  `week_id` int(11) NOT NULL AUTO_INCREMENT,
  `week_time` datetime NOT NULL,
  `pk_dept_id` int(11) NOT NULL,
  `pk_user_id` int(11) NOT NULL,
  `pk_year_id` int(11) NOT NULL,
  `pk_clas_id` int(11) NOT NULL,
  `week_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`week_id`),
  KEY `pk_dept_id` (`pk_dept_id`),
  KEY `pk_user_id` (`pk_user_id`),
  KEY `pk_year_id` (`pk_year_id`),
  KEY `pk_clas_id` (`pk_clas_id`),
  CONSTRAINT `att_week_ibfk_1` FOREIGN KEY (`pk_dept_id`) REFERENCES `att_department` (`dept_id`),
  CONSTRAINT `att_week_ibfk_2` FOREIGN KEY (`pk_user_id`) REFERENCES `att_user` (`user_id`),
  CONSTRAINT `att_week_ibfk_3` FOREIGN KEY (`pk_year_id`) REFERENCES `att_year` (`year_id`),
  CONSTRAINT `att_week_ibfk_4` FOREIGN KEY (`pk_clas_id`) REFERENCES `att_clas` (`clas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_week
-- ----------------------------
INSERT INTO `att_week` VALUES ('1', '2012-02-13 23:36:05', '10', '21', '3', '1', '2');
INSERT INTO `att_week` VALUES ('2', '2012-05-21 23:37:23', '10', '21', '3', '1', '16');
INSERT INTO `att_week` VALUES ('4', '2012-02-20 14:22:19', '10', '21', '3', '1', '3');
INSERT INTO `att_week` VALUES ('6', '2012-02-07 15:34:38', '10', '167', '10', '6', '1');
INSERT INTO `att_week` VALUES ('8', '2012-05-27 00:00:00', '10', '21', '3', '1', '17');
INSERT INTO `att_week` VALUES ('9', '2012-02-27 00:00:00', '10', '21', '3', '1', '4');
INSERT INTO `att_week` VALUES ('10', '2012-03-05 00:00:00', '10', '21', '3', '1', '5');
INSERT INTO `att_week` VALUES ('11', '2012-02-07 00:00:00', '10', '21', '3', '1', '1');
INSERT INTO `att_week` VALUES ('12', '2012-03-12 00:00:00', '10', '21', '3', '1', '6');
INSERT INTO `att_week` VALUES ('13', '2012-03-26 00:00:00', '10', '21', '3', '1', '8');
INSERT INTO `att_week` VALUES ('14', '2012-03-19 00:00:00', '10', '21', '3', '1', '7');

-- ----------------------------
-- Table structure for `att_year`
-- ----------------------------
DROP TABLE IF EXISTS `att_year`;
CREATE TABLE `att_year` (
  `year_id` int(11) NOT NULL AUTO_INCREMENT,
  `year_name` varchar(100) NOT NULL,
  `year_detail` varchar(100) NOT NULL,
  `year_desc` varchar(500) DEFAULT NULL,
  `year_time` datetime NOT NULL,
  `pk_clas_id` int(11) NOT NULL,
  PRIMARY KEY (`year_id`),
  KEY `FK200A431BC2226322` (`pk_clas_id`),
  CONSTRAINT `FK200A431BC2226322` FOREIGN KEY (`pk_clas_id`) REFERENCES `att_clas` (`clas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_year
-- ----------------------------
INSERT INTO `att_year` VALUES ('1', '2011-2012学年', '下学期', '计算机系08网络工程2班-2011-2012学年 下学期', '2011-09-05 15:22:09', '1');
INSERT INTO `att_year` VALUES ('2', '2011-2012学年', '上学期', '计算机系08网络工程2班-2011-2012学年 上学期', '2011-02-07 19:54:46', '1');
INSERT INTO `att_year` VALUES ('3', '2012-2013学年', '上学期', '计算机系08网络工程2班-2012-2013学年 上学期', '2012-02-06 21:48:53', '1');
INSERT INTO `att_year` VALUES ('4', '2012-2013学年', '下学期', '计算机系08网络工程2班-2012-2013学年 下学期', '2012-09-03 21:49:20', '1');
INSERT INTO `att_year` VALUES ('10', '2012-2013学年', '上学期', '计算机系08网络工程1班-2012-2013学年 上学期', '2012-02-06 15:32:43', '6');
INSERT INTO `att_year` VALUES ('11', '2012-2013学年', '下学期', '计算机系08网络工程1班-2012-2013学年 下学期', '2012-09-03 20:47:19', '6');

-- ----------------------------
-- Table structure for `re_resource_role`
-- ----------------------------
DROP TABLE IF EXISTS `re_resource_role`;
CREATE TABLE `re_resource_role` (
  `resource_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`resource_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `re_resource_role_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `att_resource` (`resource_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `re_resource_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `att_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_resource_role
-- ----------------------------
INSERT INTO `re_resource_role` VALUES ('1', '1');
INSERT INTO `re_resource_role` VALUES ('3', '1');
INSERT INTO `re_resource_role` VALUES ('4', '1');
INSERT INTO `re_resource_role` VALUES ('6', '1');
INSERT INTO `re_resource_role` VALUES ('7', '1');
INSERT INTO `re_resource_role` VALUES ('8', '1');
INSERT INTO `re_resource_role` VALUES ('9', '1');
INSERT INTO `re_resource_role` VALUES ('10', '1');
INSERT INTO `re_resource_role` VALUES ('11', '1');
INSERT INTO `re_resource_role` VALUES ('12', '1');
INSERT INTO `re_resource_role` VALUES ('13', '1');
INSERT INTO `re_resource_role` VALUES ('15', '1');
INSERT INTO `re_resource_role` VALUES ('16', '1');
INSERT INTO `re_resource_role` VALUES ('17', '1');
INSERT INTO `re_resource_role` VALUES ('18', '1');
INSERT INTO `re_resource_role` VALUES ('19', '1');
INSERT INTO `re_resource_role` VALUES ('20', '1');
INSERT INTO `re_resource_role` VALUES ('21', '1');
INSERT INTO `re_resource_role` VALUES ('22', '1');
INSERT INTO `re_resource_role` VALUES ('23', '1');
INSERT INTO `re_resource_role` VALUES ('24', '1');
INSERT INTO `re_resource_role` VALUES ('26', '1');
INSERT INTO `re_resource_role` VALUES ('27', '1');
INSERT INTO `re_resource_role` VALUES ('28', '1');
INSERT INTO `re_resource_role` VALUES ('29', '1');
INSERT INTO `re_resource_role` VALUES ('30', '1');
INSERT INTO `re_resource_role` VALUES ('40', '1');
INSERT INTO `re_resource_role` VALUES ('41', '1');
INSERT INTO `re_resource_role` VALUES ('42', '1');
INSERT INTO `re_resource_role` VALUES ('43', '1');
INSERT INTO `re_resource_role` VALUES ('44', '1');
INSERT INTO `re_resource_role` VALUES ('45', '1');
INSERT INTO `re_resource_role` VALUES ('46', '1');
INSERT INTO `re_resource_role` VALUES ('47', '1');
INSERT INTO `re_resource_role` VALUES ('48', '1');
INSERT INTO `re_resource_role` VALUES ('49', '1');
INSERT INTO `re_resource_role` VALUES ('50', '1');
INSERT INTO `re_resource_role` VALUES ('51', '1');
INSERT INTO `re_resource_role` VALUES ('52', '1');
INSERT INTO `re_resource_role` VALUES ('53', '1');
INSERT INTO `re_resource_role` VALUES ('54', '1');
INSERT INTO `re_resource_role` VALUES ('55', '1');
INSERT INTO `re_resource_role` VALUES ('59', '1');
INSERT INTO `re_resource_role` VALUES ('60', '1');
INSERT INTO `re_resource_role` VALUES ('63', '1');
INSERT INTO `re_resource_role` VALUES ('64', '1');
INSERT INTO `re_resource_role` VALUES ('65', '1');
INSERT INTO `re_resource_role` VALUES ('66', '1');
INSERT INTO `re_resource_role` VALUES ('67', '1');
INSERT INTO `re_resource_role` VALUES ('68', '1');
INSERT INTO `re_resource_role` VALUES ('69', '1');
INSERT INTO `re_resource_role` VALUES ('71', '1');
INSERT INTO `re_resource_role` VALUES ('73', '1');
INSERT INTO `re_resource_role` VALUES ('74', '1');
INSERT INTO `re_resource_role` VALUES ('75', '1');
INSERT INTO `re_resource_role` VALUES ('77', '1');
INSERT INTO `re_resource_role` VALUES ('79', '1');
INSERT INTO `re_resource_role` VALUES ('81', '1');
INSERT INTO `re_resource_role` VALUES ('82', '1');
INSERT INTO `re_resource_role` VALUES ('83', '1');
INSERT INTO `re_resource_role` VALUES ('84', '1');
INSERT INTO `re_resource_role` VALUES ('85', '1');
INSERT INTO `re_resource_role` VALUES ('86', '1');
INSERT INTO `re_resource_role` VALUES ('87', '1');
INSERT INTO `re_resource_role` VALUES ('88', '1');
INSERT INTO `re_resource_role` VALUES ('89', '1');
INSERT INTO `re_resource_role` VALUES ('90', '1');
INSERT INTO `re_resource_role` VALUES ('91', '1');
INSERT INTO `re_resource_role` VALUES ('1', '2');
INSERT INTO `re_resource_role` VALUES ('3', '2');
INSERT INTO `re_resource_role` VALUES ('4', '2');
INSERT INTO `re_resource_role` VALUES ('6', '2');
INSERT INTO `re_resource_role` VALUES ('7', '2');
INSERT INTO `re_resource_role` VALUES ('13', '2');
INSERT INTO `re_resource_role` VALUES ('15', '2');
INSERT INTO `re_resource_role` VALUES ('16', '2');
INSERT INTO `re_resource_role` VALUES ('17', '2');
INSERT INTO `re_resource_role` VALUES ('18', '2');
INSERT INTO `re_resource_role` VALUES ('19', '2');
INSERT INTO `re_resource_role` VALUES ('20', '2');
INSERT INTO `re_resource_role` VALUES ('21', '2');
INSERT INTO `re_resource_role` VALUES ('22', '2');
INSERT INTO `re_resource_role` VALUES ('23', '2');
INSERT INTO `re_resource_role` VALUES ('24', '2');
INSERT INTO `re_resource_role` VALUES ('40', '2');
INSERT INTO `re_resource_role` VALUES ('3', '3');
INSERT INTO `re_resource_role` VALUES ('4', '3');
INSERT INTO `re_resource_role` VALUES ('6', '3');
INSERT INTO `re_resource_role` VALUES ('13', '3');
INSERT INTO `re_resource_role` VALUES ('19', '3');
INSERT INTO `re_resource_role` VALUES ('20', '3');
INSERT INTO `re_resource_role` VALUES ('21', '3');
INSERT INTO `re_resource_role` VALUES ('22', '3');
INSERT INTO `re_resource_role` VALUES ('23', '3');
INSERT INTO `re_resource_role` VALUES ('24', '3');
INSERT INTO `re_resource_role` VALUES ('40', '3');
INSERT INTO `re_resource_role` VALUES ('42', '3');
INSERT INTO `re_resource_role` VALUES ('55', '3');
INSERT INTO `re_resource_role` VALUES ('59', '3');
INSERT INTO `re_resource_role` VALUES ('60', '3');
INSERT INTO `re_resource_role` VALUES ('69', '3');
INSERT INTO `re_resource_role` VALUES ('71', '3');
INSERT INTO `re_resource_role` VALUES ('73', '3');
INSERT INTO `re_resource_role` VALUES ('74', '3');
INSERT INTO `re_resource_role` VALUES ('75', '3');
INSERT INTO `re_resource_role` VALUES ('77', '3');
INSERT INTO `re_resource_role` VALUES ('79', '3');
INSERT INTO `re_resource_role` VALUES ('3', '38');
INSERT INTO `re_resource_role` VALUES ('4', '38');
INSERT INTO `re_resource_role` VALUES ('6', '38');
INSERT INTO `re_resource_role` VALUES ('7', '38');
INSERT INTO `re_resource_role` VALUES ('8', '38');
INSERT INTO `re_resource_role` VALUES ('9', '38');
INSERT INTO `re_resource_role` VALUES ('10', '38');
INSERT INTO `re_resource_role` VALUES ('11', '38');
INSERT INTO `re_resource_role` VALUES ('12', '38');
INSERT INTO `re_resource_role` VALUES ('13', '38');
INSERT INTO `re_resource_role` VALUES ('15', '38');
INSERT INTO `re_resource_role` VALUES ('16', '38');
INSERT INTO `re_resource_role` VALUES ('17', '38');
INSERT INTO `re_resource_role` VALUES ('18', '38');
INSERT INTO `re_resource_role` VALUES ('19', '38');
INSERT INTO `re_resource_role` VALUES ('20', '38');
INSERT INTO `re_resource_role` VALUES ('21', '38');
INSERT INTO `re_resource_role` VALUES ('22', '38');
INSERT INTO `re_resource_role` VALUES ('23', '38');
INSERT INTO `re_resource_role` VALUES ('24', '38');
INSERT INTO `re_resource_role` VALUES ('26', '38');
INSERT INTO `re_resource_role` VALUES ('27', '38');
INSERT INTO `re_resource_role` VALUES ('40', '38');
INSERT INTO `re_resource_role` VALUES ('60', '38');
INSERT INTO `re_resource_role` VALUES ('68', '38');
INSERT INTO `re_resource_role` VALUES ('75', '38');
INSERT INTO `re_resource_role` VALUES ('77', '38');
INSERT INTO `re_resource_role` VALUES ('79', '38');
INSERT INTO `re_resource_role` VALUES ('3', '39');
INSERT INTO `re_resource_role` VALUES ('4', '39');
INSERT INTO `re_resource_role` VALUES ('6', '39');
INSERT INTO `re_resource_role` VALUES ('8', '39');
INSERT INTO `re_resource_role` VALUES ('13', '39');
INSERT INTO `re_resource_role` VALUES ('19', '39');
INSERT INTO `re_resource_role` VALUES ('20', '39');
INSERT INTO `re_resource_role` VALUES ('21', '39');
INSERT INTO `re_resource_role` VALUES ('22', '39');
INSERT INTO `re_resource_role` VALUES ('23', '39');
INSERT INTO `re_resource_role` VALUES ('24', '39');
INSERT INTO `re_resource_role` VALUES ('40', '39');
INSERT INTO `re_resource_role` VALUES ('59', '39');
INSERT INTO `re_resource_role` VALUES ('69', '39');
INSERT INTO `re_resource_role` VALUES ('71', '39');
INSERT INTO `re_resource_role` VALUES ('73', '39');
INSERT INTO `re_resource_role` VALUES ('74', '39');
INSERT INTO `re_resource_role` VALUES ('60', '40');
INSERT INTO `re_resource_role` VALUES ('75', '40');
INSERT INTO `re_resource_role` VALUES ('77', '40');
INSERT INTO `re_resource_role` VALUES ('79', '40');

-- ----------------------------
-- Table structure for `re_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `re_user_role`;
CREATE TABLE `re_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `re_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `att_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `re_user_role_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `att_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_user_role
-- ----------------------------
INSERT INTO `re_user_role` VALUES ('19', '1');
INSERT INTO `re_user_role` VALUES ('19', '2');
INSERT INTO `re_user_role` VALUES ('19', '3');
INSERT INTO `re_user_role` VALUES ('20', '3');
INSERT INTO `re_user_role` VALUES ('269', '3');
INSERT INTO `re_user_role` VALUES ('167', '38');
INSERT INTO `re_user_role` VALUES ('168', '38');
INSERT INTO `re_user_role` VALUES ('21', '39');
INSERT INTO `re_user_role` VALUES ('165', '39');
INSERT INTO `re_user_role` VALUES ('170', '39');
INSERT INTO `re_user_role` VALUES ('221', '39');
INSERT INTO `re_user_role` VALUES ('222', '39');
INSERT INTO `re_user_role` VALUES ('223', '39');
INSERT INTO `re_user_role` VALUES ('224', '39');
INSERT INTO `re_user_role` VALUES ('225', '39');
INSERT INTO `re_user_role` VALUES ('226', '39');
INSERT INTO `re_user_role` VALUES ('227', '39');
INSERT INTO `re_user_role` VALUES ('228', '39');
INSERT INTO `re_user_role` VALUES ('229', '39');
INSERT INTO `re_user_role` VALUES ('230', '39');
INSERT INTO `re_user_role` VALUES ('231', '39');
INSERT INTO `re_user_role` VALUES ('232', '39');
INSERT INTO `re_user_role` VALUES ('233', '39');
INSERT INTO `re_user_role` VALUES ('234', '39');
INSERT INTO `re_user_role` VALUES ('235', '39');
INSERT INTO `re_user_role` VALUES ('236', '39');
INSERT INTO `re_user_role` VALUES ('237', '39');
INSERT INTO `re_user_role` VALUES ('238', '39');
INSERT INTO `re_user_role` VALUES ('239', '39');
INSERT INTO `re_user_role` VALUES ('240', '39');
INSERT INTO `re_user_role` VALUES ('241', '39');
INSERT INTO `re_user_role` VALUES ('242', '39');
INSERT INTO `re_user_role` VALUES ('243', '39');
INSERT INTO `re_user_role` VALUES ('244', '39');
INSERT INTO `re_user_role` VALUES ('245', '39');
INSERT INTO `re_user_role` VALUES ('246', '39');
INSERT INTO `re_user_role` VALUES ('247', '39');
INSERT INTO `re_user_role` VALUES ('248', '39');
INSERT INTO `re_user_role` VALUES ('249', '39');
INSERT INTO `re_user_role` VALUES ('250', '39');
INSERT INTO `re_user_role` VALUES ('251', '39');
INSERT INTO `re_user_role` VALUES ('252', '39');
INSERT INTO `re_user_role` VALUES ('253', '39');
INSERT INTO `re_user_role` VALUES ('254', '39');
INSERT INTO `re_user_role` VALUES ('255', '39');
INSERT INTO `re_user_role` VALUES ('256', '39');
INSERT INTO `re_user_role` VALUES ('257', '39');
INSERT INTO `re_user_role` VALUES ('258', '39');
INSERT INTO `re_user_role` VALUES ('259', '39');
INSERT INTO `re_user_role` VALUES ('260', '39');
INSERT INTO `re_user_role` VALUES ('261', '39');
INSERT INTO `re_user_role` VALUES ('262', '39');
INSERT INTO `re_user_role` VALUES ('263', '39');
INSERT INTO `re_user_role` VALUES ('264', '39');
INSERT INTO `re_user_role` VALUES ('265', '39');
INSERT INTO `re_user_role` VALUES ('266', '39');
INSERT INTO `re_user_role` VALUES ('267', '39');
INSERT INTO `re_user_role` VALUES ('268', '39');
