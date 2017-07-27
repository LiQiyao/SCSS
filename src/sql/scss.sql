/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50634
Source Host           : 127.0.0.1:3306
Source Database       : scss

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-07-22 09:11:48
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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

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
INSERT INTO `chat_log` VALUES ('14', '6', '2', '0', '0', '我有3.5个亿，房价涨价了吗', '1500518170038', '1');
INSERT INTO `chat_log` VALUES ('15', '6', '0', '2', '0', '涨价了', '1500518170210', '0');
INSERT INTO `chat_log` VALUES ('16', '7', '2', '0', '0', '哈哈哈我有3.5个亿，房价涨价了吗', '1500518403279', '1');
INSERT INTO `chat_log` VALUES ('17', '7', '0', '2', '0', '涨价了', '1500518403319', '0');
INSERT INTO `chat_log` VALUES ('18', '7', '1', '2', '0', '你好', '1500518653196', '0');
INSERT INTO `chat_log` VALUES ('19', '7', '2', '1', '0', '后哈哈哈哈哈哈', '1500518742145', '1');
INSERT INTO `chat_log` VALUES ('20', '7', '1', '2', '0', '我是客服张三！', '1500519073147', '0');
INSERT INTO `chat_log` VALUES ('21', '8', '2', '0', '0', '后哈哈哈哈哈哈', '1500523966557', '1');
INSERT INTO `chat_log` VALUES ('22', '8', '0', '2', '0', null, '1500523966668', '0');
INSERT INTO `chat_log` VALUES ('23', '9', '1', '2', '0', '你好', '1500524136221', '0');
INSERT INTO `chat_log` VALUES ('24', '10', '2', '0', '0', '后哈哈哈哈哈哈', '1500524635514', '1');
INSERT INTO `chat_log` VALUES ('25', '10', '0', '2', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1500524635532', '0');
INSERT INTO `chat_log` VALUES ('26', '10', '2', '0', '0', '转接到人工客服', '1500524649447', '1');
INSERT INTO `chat_log` VALUES ('27', '10', '1', '2', '0', '你好', '1500524649467', '0');
INSERT INTO `chat_log` VALUES ('28', '10', '2', '1', '0', '客服你好', '1500524666347', '1');
INSERT INTO `chat_log` VALUES ('29', '16', '2', '0', '0', '哈哈哈', '1500534191006', '1');
INSERT INTO `chat_log` VALUES ('30', '16', '0', '2', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1500534191022', '0');
INSERT INTO `chat_log` VALUES ('31', '16', '2', '0', '0', '转接到人工客服', '1500534202613', '1');
INSERT INTO `chat_log` VALUES ('32', '16', '1', '2', '0', '你好', '1500534202628', '0');
INSERT INTO `chat_log` VALUES ('33', '17', '2', '0', '0', 'hhh', '1500534766623', '1');
INSERT INTO `chat_log` VALUES ('34', '17', '0', '2', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1500534766642', '0');
INSERT INTO `chat_log` VALUES ('35', '17', '2', '0', '0', '转接到人工客服', '1500534775341', '1');
INSERT INTO `chat_log` VALUES ('36', '17', '1', '2', '0', '你好', '1500534775359', '0');
INSERT INTO `chat_log` VALUES ('37', '18', '2', '0', '0', 'hhh', '1500534994061', '1');
INSERT INTO `chat_log` VALUES ('38', '18', '0', '2', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1500534994078', '0');
INSERT INTO `chat_log` VALUES ('39', '18', '2', '0', '0', '转接到人工客服', '1500534997727', '1');
INSERT INTO `chat_log` VALUES ('40', '18', '1', '2', '0', '你好', '1500534997743', '0');
INSERT INTO `chat_log` VALUES ('41', '19', '2', '0', '0', '转接到人工客服', '1500535066578', '1');
INSERT INTO `chat_log` VALUES ('42', '19', '1', '2', '0', '你好', '1500535066596', '0');
INSERT INTO `chat_log` VALUES ('43', '20', '2', '0', '0', '转接到人工客服', '1500535199881', '1');
INSERT INTO `chat_log` VALUES ('44', '20', '1', '2', '0', '你好', '1500535199902', '0');
INSERT INTO `chat_log` VALUES ('45', '20', '1', '2', '0', '测试', '1500535207611', '0');
INSERT INTO `chat_log` VALUES ('46', '21', '2', '0', '0', '转接到人工客服', '1500535447200', '1');
INSERT INTO `chat_log` VALUES ('47', '21', '1', '2', '0', '你好', '1500535447213', '0');
INSERT INTO `chat_log` VALUES ('48', '21', '1', '2', '0', '测试', '1500535456804', '0');
INSERT INTO `chat_log` VALUES ('49', '21', '2', '1', '0', 'hhh', '1500535472326', '1');
INSERT INTO `chat_log` VALUES ('50', '22', '2', '0', '0', '转接到人工客服', '1500535675383', '1');
INSERT INTO `chat_log` VALUES ('51', '22', '1', '2', '0', '你好', '1500535675387', '0');
INSERT INTO `chat_log` VALUES ('52', '22', '1', '2', '0', '测试111', '1500535685314', '0');
INSERT INTO `chat_log` VALUES ('53', '23', '2', '0', '0', '转接到人工客服', '1500685510862', '1');
INSERT INTO `chat_log` VALUES ('54', '23', '1', '2', '0', '你好', '1500685510952', '0');
INSERT INTO `chat_log` VALUES ('55', '24', '2', '0', '0', '转接到人工客服', '1500685695718', '1');
INSERT INTO `chat_log` VALUES ('56', '24', '1', '2', '0', '你好', '1500685695735', '0');
INSERT INTO `chat_log` VALUES ('57', '25', '2', '0', '0', '转接到人工客服', '1500685887367', '1');
INSERT INTO `chat_log` VALUES ('58', '25', '1', '2', '0', '你好', '1500685887379', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', null, null, null, null, null);
INSERT INTO `client` VALUES ('2', null, null, null, null, '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of conversation
-- ----------------------------
INSERT INTO `conversation` VALUES ('1', '1', '1', '1499443200000', '199999', '3');
INSERT INTO `conversation` VALUES ('2', '1', '0', '1499011200000', '1499013200000', '4');
INSERT INTO `conversation` VALUES ('3', '1', '1', '1499616000000', '1499616999000', '5');
INSERT INTO `conversation` VALUES ('4', '1', '0', '1499991300000', '1499991500000', '4');
INSERT INTO `conversation` VALUES ('5', '1', '0', '1499991000000', '1500168287499', null);
INSERT INTO `conversation` VALUES ('6', '2', '0', '1500518098602', null, null);
INSERT INTO `conversation` VALUES ('7', '2', '1', '1500518362645', '1500518362649', null);
INSERT INTO `conversation` VALUES ('8', '2', '0', '1500523956671', null, null);
INSERT INTO `conversation` VALUES ('9', '2', '1', '1500524087661', '1500524179283', null);
INSERT INTO `conversation` VALUES ('10', '2', '1', '1500524619044', '1500524684077', null);
INSERT INTO `conversation` VALUES ('11', '2', '0', '1500524802896', null, null);
INSERT INTO `conversation` VALUES ('12', '2', '0', '1500524813836', null, null);
INSERT INTO `conversation` VALUES ('13', '2', '0', '1500524976193', null, null);
INSERT INTO `conversation` VALUES ('14', '2', '0', '1500525268658', null, null);
INSERT INTO `conversation` VALUES ('15', '2', '0', '1500525561131', null, '4');
INSERT INTO `conversation` VALUES ('16', '2', '1', '1500533728186', null, null);
INSERT INTO `conversation` VALUES ('17', '2', '1', '1500534756465', null, null);
INSERT INTO `conversation` VALUES ('18', '2', '1', '1500534990824', null, null);
INSERT INTO `conversation` VALUES ('19', '2', '1', '1500535064139', null, null);
INSERT INTO `conversation` VALUES ('20', '2', '1', '1500535197136', null, null);
INSERT INTO `conversation` VALUES ('21', '2', '1', '1500535440903', null, null);
INSERT INTO `conversation` VALUES ('22', '2', '1', '1500535665659', '1500535688779', null);
INSERT INTO `conversation` VALUES ('23', '2', '1', '1500685496105', null, null);
INSERT INTO `conversation` VALUES ('24', '2', '1', '1500685693702', null, null);
INSERT INTO `conversation` VALUES ('25', '2', '1', '1500685884974', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of join_up
-- ----------------------------
INSERT INTO `join_up` VALUES ('1', '1', '2', '1500518098575', '7113404510');
INSERT INTO `join_up` VALUES ('2', '1', '2', '1500518362628', '7113404510');
INSERT INTO `join_up` VALUES ('3', '1', '2', '1500523956605', '7113404510');
INSERT INTO `join_up` VALUES ('4', '1', '2', '1500524087641', '7113404510');
INSERT INTO `join_up` VALUES ('5', '1', '2', '1500524619011', '7113404510');
INSERT INTO `join_up` VALUES ('6', '1', '2', '1500524802882', '7113404510');
INSERT INTO `join_up` VALUES ('7', '1', '2', '1500524813815', '7113404510');
INSERT INTO `join_up` VALUES ('8', '1', '2', '1500524976170', '7113404510');
INSERT INTO `join_up` VALUES ('9', '1', '2', '1500525268630', '7113404510');
INSERT INTO `join_up` VALUES ('10', '1', '2', '1500525561092', '7113404510');
INSERT INTO `join_up` VALUES ('11', '1', '2', '1500533728175', '7113404510');
INSERT INTO `join_up` VALUES ('12', '1', '2', '1500534756452', '7113404510');
INSERT INTO `join_up` VALUES ('13', '1', '2', '1500534990813', '7113404510');
INSERT INTO `join_up` VALUES ('14', '1', '2', '1500535064125', '7113404510');
INSERT INTO `join_up` VALUES ('15', '1', '2', '1500535197115', '7113404510');
INSERT INTO `join_up` VALUES ('16', '1', '2', '1500535440888', '7113404510');
INSERT INTO `join_up` VALUES ('17', '1', '2', '1500535665643', '7113404510');
INSERT INTO `join_up` VALUES ('18', '1', '2', '1500685496009', '7113404510');
INSERT INTO `join_up` VALUES ('19', '1', '2', '1500685693687', '7113404510');
INSERT INTO `join_up` VALUES ('20', '1', '2', '1500685884965', '7113404510');

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
INSERT INTO `keyword_heat` VALUES ('7', '1500518170123');
INSERT INTO `keyword_heat` VALUES ('3', '1500518170190');
INSERT INTO `keyword_heat` VALUES ('7', '1500518403311');
INSERT INTO `keyword_heat` VALUES ('3', '1500518403316');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES ('1', '1', '3', '1', '编号为2的客户接入到会话中', '1500518653282');
INSERT INTO `notification` VALUES ('2', '1', '3', '1', '编号为2的客户接入到会话中', '1500524136350');
INSERT INTO `notification` VALUES ('3', '1', '3', '1', '编号为2的客户接入到会话中', '1500524649669');
INSERT INTO `notification` VALUES ('4', '1', '3', '1', '编号为2的客户接入到会话中', '1500534202767');
INSERT INTO `notification` VALUES ('5', '1', '3', '1', '编号为2的客户接入到会话中', '1500534775376');
INSERT INTO `notification` VALUES ('6', '1', '3', '1', '编号为2的客户接入到会话中', '1500534997758');
INSERT INTO `notification` VALUES ('7', '1', '3', '1', '编号为2的客户接入到会话中', '1500535066614');
INSERT INTO `notification` VALUES ('8', '1', '3', '1', '编号为2的客户接入到会话中', '1500535200012');
INSERT INTO `notification` VALUES ('9', '1', '3', '1', '编号为2的客户接入到会话中', '1500535447235');
INSERT INTO `notification` VALUES ('10', '1', '3', '1', '编号为2的客户接入到会话中', '1500535675396');
INSERT INTO `notification` VALUES ('11', '1', '3', '1', '编号为2的客户接入到会话中', '1500685511000');
INSERT INTO `notification` VALUES ('12', '1', '3', '1', '编号为2的客户接入到会话中', '1500685695915');
INSERT INTO `notification` VALUES ('13', '1', '3', '1', '编号为2的客户接入到会话中', '1500685887403');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_time
-- ----------------------------
INSERT INTO `work_time` VALUES ('1', '1', '1500533736282', '1500533798477');
INSERT INTO `work_time` VALUES ('2', '1', '1500533798920', '1500533960218');
INSERT INTO `work_time` VALUES ('3', '1', '1500533960639', '1500533989333');
INSERT INTO `work_time` VALUES ('4', '1', '1500533989821', '1500534150306');
INSERT INTO `work_time` VALUES ('5', '1', '1500534150773', '1500534660282');
INSERT INTO `work_time` VALUES ('6', '1', '1500534664562', '1500534674876');
INSERT INTO `work_time` VALUES ('7', '1', '1500534692019', '1500534748546');
INSERT INTO `work_time` VALUES ('8', '1', '1500534748963', '1500534983399');
INSERT INTO `work_time` VALUES ('9', '1', '1500534983826', '1500535053972');
INSERT INTO `work_time` VALUES ('10', '1', '1500535054443', '1500535192042');
INSERT INTO `work_time` VALUES ('11', '1', '1500535192485', '1500535437838');
INSERT INTO `work_time` VALUES ('12', '1', '1500535438311', '1500535668896');
INSERT INTO `work_time` VALUES ('13', '1', '1500535669377', '1500535755856');
INSERT INTO `work_time` VALUES ('14', '1', '1500685486292', '1500685511209');
INSERT INTO `work_time` VALUES ('15', '1', '1500685688869', '1500685696371');
INSERT INTO `work_time` VALUES ('16', '1', '1500685883399', '1500685888111');
