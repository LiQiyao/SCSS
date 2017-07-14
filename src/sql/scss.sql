/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50634
Source Host           : 127.0.0.1:3306
Source Database       : scss

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-07-14 13:56:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for access
-- ----------------------------
DROP TABLE IF EXISTS `access`;
CREATE TABLE `access` (
  `access_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`access_id`),
  UNIQUE KEY `access_id` (`access_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of access
-- ----------------------------
INSERT INTO `access` VALUES ('1', '网页');
INSERT INTO `access` VALUES ('2', 'QQ');
INSERT INTO `access` VALUES ('3', '微信');
INSERT INTO `access` VALUES ('4', '微博');

-- ----------------------------
-- Table structure for chat_log
-- ----------------------------
DROP TABLE IF EXISTS `chat_log`;
CREATE TABLE `chat_log` (
  `chat_log_id` int(20) NOT NULL AUTO_INCREMENT,
  `conversation_id` int(20) NOT NULL,
  `sender_id` int(20) NOT NULL,
  `receiver_id` int(20) NOT NULL,
  `content_type` int(20) NOT NULL DEFAULT '0',
  `content` varchar(5000) DEFAULT NULL,
  `time` bigint(20) NOT NULL,
  `from_client` int(20) NOT NULL COMMENT '若为1，则本消息来自客户，0 则反之',
  PRIMARY KEY (`chat_log_id`),
  UNIQUE KEY `chat_log_id` (`chat_log_id`) USING BTREE,
  KEY `conversation_id` (`conversation_id`) USING BTREE,
  CONSTRAINT `chat_log_ibfk_1` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat_log
-- ----------------------------
INSERT INTO `chat_log` VALUES ('1', '1', '0', '1', '0', 'hhhh', '122222', '1');

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `client_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` bigint(20) DEFAULT NULL,
  `sex` int(20) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `client_id` (`client_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', null, null, null, null, null);
INSERT INTO `client` VALUES ('2', null, null, null, null, '0');
INSERT INTO `client` VALUES ('5', null, null, null, null, '0');
INSERT INTO `client` VALUES ('6', null, null, null, null, '0');
INSERT INTO `client` VALUES ('7', null, null, null, null, '0');

-- ----------------------------
-- Table structure for client_flag
-- ----------------------------
DROP TABLE IF EXISTS `client_flag`;
CREATE TABLE `client_flag` (
  `client_id` int(20) NOT NULL,
  `flag_id` int(20) NOT NULL,
  KEY `client_id` (`client_id`),
  KEY `tag_id` (`flag_id`),
  CONSTRAINT `client_flag_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `client_tag_ibfk_2` FOREIGN KEY (`flag_id`) REFERENCES `flag` (`flag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client_flag
-- ----------------------------

-- ----------------------------
-- Table structure for common_language
-- ----------------------------
DROP TABLE IF EXISTS `common_language`;
CREATE TABLE `common_language` (
  `common_language_id` int(20) NOT NULL AUTO_INCREMENT,
  `service_id` int(20) DEFAULT NULL,
  `content` varchar(5000) NOT NULL,
  `frequency` int(20) NOT NULL,
  PRIMARY KEY (`common_language_id`),
  UNIQUE KEY `common_language_id` (`common_language_id`) USING BTREE,
  KEY `group_id` (`service_id`),
  CONSTRAINT `common_language_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `customer_service` (`service_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of common_language
-- ----------------------------

-- ----------------------------
-- Table structure for conversation
-- ----------------------------
DROP TABLE IF EXISTS `conversation`;
CREATE TABLE `conversation` (
  `conversation_id` int(20) NOT NULL AUTO_INCREMENT,
  `client_id` int(20) NOT NULL,
  `service_id` int(20) NOT NULL,
  `start_time` bigint(20) NOT NULL,
  `stop_time` bigint(20) DEFAULT NULL,
  `score` int(20) DEFAULT NULL,
  PRIMARY KEY (`conversation_id`),
  UNIQUE KEY `conversation_id` (`conversation_id`) USING BTREE,
  KEY `client_id` (`client_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `conversation_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `conversation_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `customer_service` (`service_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of conversation
-- ----------------------------
INSERT INTO `conversation` VALUES ('1', '1', '1', '100000', '199999', '3');
INSERT INTO `conversation` VALUES ('4', '5', '0', '1500005602109', null, null);
INSERT INTO `conversation` VALUES ('5', '6', '0', '1500005679217', null, null);
INSERT INTO `conversation` VALUES ('6', '7', '0', '1500005718001', null, null);
INSERT INTO `conversation` VALUES ('7', '7', '0', '1500005814656', null, null);
INSERT INTO `conversation` VALUES ('8', '7', '0', '1500005989078', null, null);

-- ----------------------------
-- Table structure for customer_service
-- ----------------------------
DROP TABLE IF EXISTS `customer_service`;
CREATE TABLE `customer_service` (
  `service_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `group_id` int(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `employee_id` varchar(20) NOT NULL,
  `auto_message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `service_id` (`service_id`) USING BTREE,
  UNIQUE KEY `employee_id` (`employee_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `customer_service_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `service_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_service
-- ----------------------------
INSERT INTO `customer_service` VALUES ('0', '机器人', '1', '小Robot', '000', '我是000号机器人');
INSERT INTO `customer_service` VALUES ('1', '李', '1', '小李', 'ABC', '你好');

-- ----------------------------
-- Table structure for flag
-- ----------------------------
DROP TABLE IF EXISTS `flag`;
CREATE TABLE `flag` (
  `flag_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`flag_id`),
  UNIQUE KEY `tag_id` (`flag_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flag
-- ----------------------------

-- ----------------------------
-- Table structure for join_up
-- ----------------------------
DROP TABLE IF EXISTS `join_up`;
CREATE TABLE `join_up` (
  `join_up_id` int(20) NOT NULL AUTO_INCREMENT,
  `access_id` int(20) NOT NULL,
  `client_id` int(20) NOT NULL,
  `time` bigint(20) NOT NULL,
  `account` varchar(20) NOT NULL,
  PRIMARY KEY (`join_up_id`),
  UNIQUE KEY `join_up_id` (`join_up_id`) USING BTREE,
  KEY `client_id` (`client_id`),
  KEY `access_id` (`access_id`) USING BTREE,
  CONSTRAINT `join_up_ibfk_1` FOREIGN KEY (`access_id`) REFERENCES `access` (`access_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `join_up_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of join_up
-- ----------------------------
INSERT INTO `join_up` VALUES ('3', '1', '1', '1500005601934', '0453363425');
INSERT INTO `join_up` VALUES ('4', '1', '6', '1500005679098', '7817187968');
INSERT INTO `join_up` VALUES ('5', '1', '7', '1500005717890', '5284207158');

-- ----------------------------
-- Table structure for keyword
-- ----------------------------
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword` (
  `keyword_id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`keyword_id`),
  UNIQUE KEY `key_word` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keyword
-- ----------------------------
INSERT INTO `keyword` VALUES ('1', '买房');
INSERT INTO `keyword` VALUES ('2', '平方');
INSERT INTO `keyword` VALUES ('4', '我要买房价买房，多少钱一平方啊？');
INSERT INTO `keyword` VALUES ('3', '房价');
INSERT INTO `keyword` VALUES ('8', '最近的房价涨价了吗');
INSERT INTO `keyword` VALUES ('7', '涨价');

-- ----------------------------
-- Table structure for keyword_heat
-- ----------------------------
DROP TABLE IF EXISTS `keyword_heat`;
CREATE TABLE `keyword_heat` (
  `keyword_id` int(11) NOT NULL,
  `heat_time` bigint(20) NOT NULL,
  KEY `keyword_id` (`keyword_id`),
  CONSTRAINT `keyword_heat_ibfk_1` FOREIGN KEY (`keyword_id`) REFERENCES `keyword` (`keyword_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keyword_heat
-- ----------------------------

-- ----------------------------
-- Table structure for knowledge
-- ----------------------------
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `knowledge_id` int(20) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `answer` varchar(5000) NOT NULL,
  `level` int(20) NOT NULL,
  `author` int(20) NOT NULL,
  `time` bigint(20) NOT NULL,
  PRIMARY KEY (`knowledge_id`),
  UNIQUE KEY `knowledge_id` (`knowledge_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of knowledge
-- ----------------------------
INSERT INTO `knowledge` VALUES ('1', '我要买房价买房，多少钱一平方啊？', '不知道', '1', '1', '1');
INSERT INTO `knowledge` VALUES ('3', '最近的房价涨价了吗', '涨价了', '1', '1', '1');

-- ----------------------------
-- Table structure for knowledge_keyword
-- ----------------------------
DROP TABLE IF EXISTS `knowledge_keyword`;
CREATE TABLE `knowledge_keyword` (
  `keyword_id` int(20) NOT NULL,
  `knowledge_id` int(20) NOT NULL,
  PRIMARY KEY (`keyword_id`,`knowledge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of knowledge_keyword
-- ----------------------------
INSERT INTO `knowledge_keyword` VALUES ('1', '1');
INSERT INTO `knowledge_keyword` VALUES ('2', '1');
INSERT INTO `knowledge_keyword` VALUES ('3', '1');
INSERT INTO `knowledge_keyword` VALUES ('3', '3');
INSERT INTO `knowledge_keyword` VALUES ('4', '1');
INSERT INTO `knowledge_keyword` VALUES ('7', '3');
INSERT INTO `knowledge_keyword` VALUES ('8', '3');

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `notification_id` int(20) NOT NULL AUTO_INCREMENT,
  `nt_id` int(20) NOT NULL,
  `not_id` int(20) NOT NULL,
  `object_id` int(20) NOT NULL DEFAULT '0',
  `content` varchar(5000) DEFAULT NULL,
  `time` bigint(20) NOT NULL,
  PRIMARY KEY (`notification_id`),
  UNIQUE KEY `notification_id` (`notification_id`) USING BTREE,
  KEY `nt_id` (`nt_id`),
  KEY `not_id` (`not_id`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`nt_id`) REFERENCES `notification_type` (`nt_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`not_id`) REFERENCES `notification_object_type` (`not_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification
-- ----------------------------

-- ----------------------------
-- Table structure for notification_object_type
-- ----------------------------
DROP TABLE IF EXISTS `notification_object_type`;
CREATE TABLE `notification_object_type` (
  `not_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`not_id`),
  UNIQUE KEY `not_id` (`not_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification_object_type
-- ----------------------------

-- ----------------------------
-- Table structure for notification_type
-- ----------------------------
DROP TABLE IF EXISTS `notification_type`;
CREATE TABLE `notification_type` (
  `nt_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`nt_id`),
  UNIQUE KEY `nt_id` (`nt_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification_type
-- ----------------------------

-- ----------------------------
-- Table structure for operation_user
-- ----------------------------
DROP TABLE IF EXISTS `operation_user`;
CREATE TABLE `operation_user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation_user
-- ----------------------------

-- ----------------------------
-- Table structure for question_push
-- ----------------------------
DROP TABLE IF EXISTS `question_push`;
CREATE TABLE `question_push` (
  `from_id` int(11) NOT NULL,
  `to_id` int(11) NOT NULL,
  PRIMARY KEY (`from_id`,`to_id`),
  KEY `to_id` (`to_id`),
  CONSTRAINT `question_push_ibfk_1` FOREIGN KEY (`from_id`) REFERENCES `knowledge` (`knowledge_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `question_push_ibfk_2` FOREIGN KEY (`to_id`) REFERENCES `knowledge` (`knowledge_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_push
-- ----------------------------

-- ----------------------------
-- Table structure for service_group
-- ----------------------------
DROP TABLE IF EXISTS `service_group`;
CREATE TABLE `service_group` (
  `group_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_group
-- ----------------------------
INSERT INTO `service_group` VALUES ('1', '售后组');

-- ----------------------------
-- Table structure for work_time
-- ----------------------------
DROP TABLE IF EXISTS `work_time`;
CREATE TABLE `work_time` (
  `work_time_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_id` int(11) NOT NULL,
  `start_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`work_time_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `work_time_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `customer_service` (`service_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_time
-- ----------------------------
