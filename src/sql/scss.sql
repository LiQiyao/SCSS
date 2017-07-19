/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50634
Source Host           : 127.0.0.1:3306
Source Database       : scss

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-07-19 14:57:50
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
-- Table structure for advertisement
-- ----------------------------
DROP TABLE IF EXISTS `advertisement`;
CREATE TABLE `advertisement` (
  `adv_id` int(20) NOT NULL AUTO_INCREMENT,
  `adv_content` varchar(600) NOT NULL,
  PRIMARY KEY (`adv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertisement
-- ----------------------------

-- ----------------------------
-- Table structure for adv_flag
-- ----------------------------
DROP TABLE IF EXISTS `adv_flag`;
CREATE TABLE `adv_flag` (
  `flag_id` int(20) NOT NULL,
  `adv_id` int(20) NOT NULL,
  KEY `flag_id` (`flag_id`),
  KEY `adv_id` (`adv_id`),
  CONSTRAINT `adv_flag_ibfk_1` FOREIGN KEY (`flag_id`) REFERENCES `flag` (`flag_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `adv_flag_ibfk_2` FOREIGN KEY (`adv_id`) REFERENCES `advertisement` (`adv_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adv_flag
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat_log
-- ----------------------------
INSERT INTO `chat_log` VALUES ('1', '1', '1', '1', '0', 'hhhh', '122222', '1');
INSERT INTO `chat_log` VALUES ('2', '5', '1', '0', '0', '涨价了吗?', '1500097538209', '1');
INSERT INTO `chat_log` VALUES ('4', '5', '1', '0', '0', '涨价了吗?', '1500097884460', '1');
INSERT INTO `chat_log` VALUES ('5', '5', '1', '0', '0', '涨价了吗?', '1500098363680', '1');
INSERT INTO `chat_log` VALUES ('6', '5', '0', '1', '0', '涨价了', '1500098363923', '0');
INSERT INTO `chat_log` VALUES ('7', '5', '1', '0', '0', '房价涨价了吗?', '1500098403365', '1');
INSERT INTO `chat_log` VALUES ('8', '5', '0', '1', '0', '涨价了', '1500098403699', '0');
INSERT INTO `chat_log` VALUES ('9', '5', '1', '0', '0', '涨价了吗?', '1500167869468', '1');
INSERT INTO `chat_log` VALUES ('10', '5', '1', '0', '0', '涨价了吗?', '1500168036494', '1');
INSERT INTO `chat_log` VALUES ('11', '5', '0', '1', '0', '涨价了', '1500168036869', '0');
INSERT INTO `chat_log` VALUES ('12', '5', '1', '0', '0', '转接到人工客服', '1500168145476', '1');
INSERT INTO `chat_log` VALUES ('13', '5', '0', '1', '0', null, '1500168145664', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', null, null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of conversation
-- ----------------------------
INSERT INTO `conversation` VALUES ('1', '1', '1', '1499443200000', '199999', '3');
INSERT INTO `conversation` VALUES ('2', '1', '0', '1499011200000', '1499013200000', '4');
INSERT INTO `conversation` VALUES ('3', '1', '1', '1499616000000', '1499616999000', '5');
INSERT INTO `conversation` VALUES ('4', '1', '0', '1499991300000', '1499991500000', null);
INSERT INTO `conversation` VALUES ('5', '1', '0', '1499991000000', '1500168287499', null);

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
  `is_dimission` int(20) NOT NULL DEFAULT '0',
  `password` varchar(255) NOT NULL DEFAULT '888888',
  PRIMARY KEY (`service_id`),
  UNIQUE KEY `service_id` (`service_id`) USING BTREE,
  UNIQUE KEY `employee_id` (`employee_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `customer_service_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `service_group` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_service
-- ----------------------------
INSERT INTO `customer_service` VALUES ('0', '机器人', '1', '小机器人', 'CCC', 'Hello，我是机器人客服', '0', '');
INSERT INTO `customer_service` VALUES ('1', '李', '1', '小李', 'ABC', '你好', '0', 'ABC');

-- ----------------------------
-- Table structure for flag
-- ----------------------------
DROP TABLE IF EXISTS `flag`;
CREATE TABLE `flag` (
  `flag_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`flag_id`),
  UNIQUE KEY `tag_id` (`flag_id`) USING BTREE,
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flag
-- ----------------------------

-- ----------------------------
-- Table structure for group_word
-- ----------------------------
DROP TABLE IF EXISTS `group_word`;
CREATE TABLE `group_word` (
  `group_id` int(11) NOT NULL,
  `word` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_word
-- ----------------------------
INSERT INTO `group_word` VALUES ('1', '退款');
INSERT INTO `group_word` VALUES ('2', '要买房');
INSERT INTO `group_word` VALUES ('2', '想买房');
INSERT INTO `group_word` VALUES ('3', '没钱');
INSERT INTO `group_word` VALUES ('2', '买房');
INSERT INTO `group_word` VALUES ('1', '买了房');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of join_up
-- ----------------------------

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
INSERT INTO `keyword_heat` VALUES ('7', '1500097538384');
INSERT INTO `keyword_heat` VALUES ('7', '1500098363854');
INSERT INTO `keyword_heat` VALUES ('7', '1500098403498');
INSERT INTO `keyword_heat` VALUES ('3', '1500098403539');
INSERT INTO `keyword_heat` VALUES ('7', '1500167869962');
INSERT INTO `keyword_heat` VALUES ('7', '1500168036629');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification_object_type
-- ----------------------------
INSERT INTO `notification_object_type` VALUES ('1', '全体客服');
INSERT INTO `notification_object_type` VALUES ('2', '客服组');
INSERT INTO `notification_object_type` VALUES ('3', '客服个人');

-- ----------------------------
-- Table structure for notification_type
-- ----------------------------
DROP TABLE IF EXISTS `notification_type`;
CREATE TABLE `notification_type` (
  `nt_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`nt_id`),
  UNIQUE KEY `nt_id` (`nt_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification_type
-- ----------------------------
INSERT INTO `notification_type` VALUES ('1', '聊天转接通知');
INSERT INTO `notification_type` VALUES ('2', '系统通知');
INSERT INTO `notification_type` VALUES ('3', '普通通知');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operation_user
-- ----------------------------
INSERT INTO `operation_user` VALUES ('1', 'xxx', 'xxx');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_group
-- ----------------------------
INSERT INTO `service_group` VALUES ('1', '售后组');
INSERT INTO `service_group` VALUES ('2', '售前组');
INSERT INTO `service_group` VALUES ('3', '贷款咨询组');

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
