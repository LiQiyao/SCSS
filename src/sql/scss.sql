/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50634
Source Host           : 127.0.0.1:3306
Source Database       : scss

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-08-01 10:37:37
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
) ENGINE=InnoDB AUTO_INCREMENT=318 DEFAULT CHARSET=utf8;

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
INSERT INTO `chat_log` VALUES ('59', '26', '2', '0', '0', '转接到人工客服', '1500686088908', '1');
INSERT INTO `chat_log` VALUES ('60', '26', '1', '2', '0', '你好', '1500686088922', '0');
INSERT INTO `chat_log` VALUES ('61', '27', '2', '0', '0', '转接到人工客服', '1500686223256', '1');
INSERT INTO `chat_log` VALUES ('62', '27', '1', '2', '0', '你好', '1500686223276', '0');
INSERT INTO `chat_log` VALUES ('65', '30', '3', '0', '0', '转接到人工客服', '1500692635889', '1');
INSERT INTO `chat_log` VALUES ('66', '30', '1', '3', '0', '你好', '1500692635903', '0');
INSERT INTO `chat_log` VALUES ('76', '43', '14', '0', '0', '转接到人工客服', '1500697669457', '1');
INSERT INTO `chat_log` VALUES ('77', '44', '15', '0', '0', '转接到人工客服', '1500697875508', '1');
INSERT INTO `chat_log` VALUES ('78', '44', '1', '15', '0', '你好', '1500697887590', '0');
INSERT INTO `chat_log` VALUES ('79', '45', '16', '0', '0', '转接到人工客服', '1500869535302', '1');
INSERT INTO `chat_log` VALUES ('80', '45', '1', '16', '0', '你好', '1500869541888', '0');
INSERT INTO `chat_log` VALUES ('81', '47', '17', '0', '0', '转接到人工客服', '1500871617752', '1');
INSERT INTO `chat_log` VALUES ('82', '47', '1', '17', '0', '你好', '1500871624901', '0');
INSERT INTO `chat_log` VALUES ('83', '47', '17', '1', '0', '服', '1500871648553', '1');
INSERT INTO `chat_log` VALUES ('84', '47', '1', '17', '0', '666', '1500871653828', '0');
INSERT INTO `chat_log` VALUES ('85', '48', '18', '0', '0', '转接到人工客服', '1501151560727', '1');
INSERT INTO `chat_log` VALUES ('86', '48', '1', '18', '0', '你好', '1501151565714', '0');
INSERT INTO `chat_log` VALUES ('87', '48', '1', '18', '0', '请问有什么可以帮您的吗', '1501151587868', '0');
INSERT INTO `chat_log` VALUES ('88', '48', '18', '1', '0', '没有', '1501151598880', '1');
INSERT INTO `chat_log` VALUES ('89', '49', '18', '0', '0', '转接到人工客服', '1501151892136', '1');
INSERT INTO `chat_log` VALUES ('90', '49', '1', '18', '0', '你好', '1501151895940', '0');
INSERT INTO `chat_log` VALUES ('91', '50', '18', '0', '0', '转接到人工客服', '1501152035629', '1');
INSERT INTO `chat_log` VALUES ('92', '50', '1', '18', '0', '你好', '1501152127390', '0');
INSERT INTO `chat_log` VALUES ('93', '51', '18', '0', '0', '转接到人工客服', '1501152902749', '1');
INSERT INTO `chat_log` VALUES ('94', '51', '1', '18', '0', '你好', '1501152905284', '0');
INSERT INTO `chat_log` VALUES ('95', '51', '18', '1', '0', '转接到人工客服', '1501153222485', '1');
INSERT INTO `chat_log` VALUES ('96', '52', '18', '0', '0', '转接到人工客服', '1501153237318', '1');
INSERT INTO `chat_log` VALUES ('97', '52', '1', '18', '0', '你好', '1501153240157', '0');
INSERT INTO `chat_log` VALUES ('98', '53', '18', '0', '0', '转接到人工客服', '1501153292881', '1');
INSERT INTO `chat_log` VALUES ('99', '53', '1', '18', '0', '你好', '1501153297320', '0');
INSERT INTO `chat_log` VALUES ('100', '53', '1', '18', '0', '可以，很酷。', '1501153491049', '0');
INSERT INTO `chat_log` VALUES ('101', '53', '18', '1', '0', '可以，很酷。', '1501153502899', '1');
INSERT INTO `chat_log` VALUES ('102', '53', '18', '1', '0', '我要买房，房价涨价了吗', '1501153579872', '1');
INSERT INTO `chat_log` VALUES ('103', '54', '18', '0', '0', '转接到人工客服', '1501154244763', '1');
INSERT INTO `chat_log` VALUES ('104', '54', '1', '18', '0', '你好', '1501154247835', '0');
INSERT INTO `chat_log` VALUES ('106', '55', '18', '0', '0', '转接到人工客服', '1501154613706', '1');
INSERT INTO `chat_log` VALUES ('107', '55', '1', '18', '0', '你好', '1501154619490', '0');
INSERT INTO `chat_log` VALUES ('108', '55', '18', '1', '0', '我要买房，房价涨价了吗', '1501154629498', '1');
INSERT INTO `chat_log` VALUES ('109', '56', '18', '0', '0', '转接到人工客服', '1501154989500', '1');
INSERT INTO `chat_log` VALUES ('110', '56', '1', '18', '0', '你好', '1501154991869', '0');
INSERT INTO `chat_log` VALUES ('111', '56', '18', '1', '0', '我要买房，房价涨价了吗', '1501155000426', '1');
INSERT INTO `chat_log` VALUES ('113', '57', '18', '0', '0', '转接到人工客服', '1501155877231', '1');
INSERT INTO `chat_log` VALUES ('114', '58', '19', '0', '0', '我想买房', '1501157200761', '1');
INSERT INTO `chat_log` VALUES ('115', '58', '0', '19', '0', '不知道', '1501157200786', '0');
INSERT INTO `chat_log` VALUES ('116', '58', '19', '0', '0', '转接到人工客服', '1501157225413', '1');
INSERT INTO `chat_log` VALUES ('117', '58', '2', '19', '0', '您好 我是小二', '1501157242394', '0');
INSERT INTO `chat_log` VALUES ('118', '58', '2', '19', '0', '你好啊', '1501157268299', '0');
INSERT INTO `chat_log` VALUES ('119', '58', '19', '2', '0', '我要咨询', '1501157285691', '1');
INSERT INTO `chat_log` VALUES ('120', '59', '19', '0', '0', '转接到人工客服', '1501157327337', '1');
INSERT INTO `chat_log` VALUES ('121', '59', '2', '19', '0', '您好 我是小二', '1501157327358', '0');
INSERT INTO `chat_log` VALUES ('122', '60', '19', '0', '0', '转接到人工客服', '1501157525110', '1');
INSERT INTO `chat_log` VALUES ('123', '60', '2', '19', '0', '您好 我是小二', '1501157525120', '0');
INSERT INTO `chat_log` VALUES ('126', '61', '19', '0', '0', '转接到人工客服', '1501158065161', '1');
INSERT INTO `chat_log` VALUES ('127', '61', '2', '19', '0', '您好 我是小二', '1501158065174', '0');
INSERT INTO `chat_log` VALUES ('128', '61', '2', '19', '0', 'see you', '1501158075491', '0');
INSERT INTO `chat_log` VALUES ('130', '65', '19', '0', '0', '转接到人工客服', '1501158557552', '1');
INSERT INTO `chat_log` VALUES ('131', '65', '1', '19', '0', '你好', '1501158562017', '0');
INSERT INTO `chat_log` VALUES ('132', '66', '19', '0', '0', '转接到人工客服', '1501158634921', '1');
INSERT INTO `chat_log` VALUES ('133', '66', '1', '19', '0', '你好', '1501158634934', '0');
INSERT INTO `chat_log` VALUES ('134', '67', '19', '0', '0', '转接到人工客服', '1501159549440', '1');
INSERT INTO `chat_log` VALUES ('135', '67', '1', '19', '0', '你好', '1501159549455', '0');
INSERT INTO `chat_log` VALUES ('136', '68', '19', '0', '0', '转接到人工客服', '1501159653972', '1');
INSERT INTO `chat_log` VALUES ('137', '68', '1', '19', '0', '你好', '1501159653982', '0');
INSERT INTO `chat_log` VALUES ('138', '70', '18', '0', '0', '转接到人工客服', '1501218482751', '1');
INSERT INTO `chat_log` VALUES ('139', '70', '1', '18', '0', '你好', '1501218482846', '0');
INSERT INTO `chat_log` VALUES ('140', '70', '1', '18', '0', 'hh', '1501218589257', '0');
INSERT INTO `chat_log` VALUES ('141', '70', '1', '18', '0', '你好', '1501218599259', '0');
INSERT INTO `chat_log` VALUES ('142', '71', '18', '0', '0', '转接到人工客服', '1501218661010', '1');
INSERT INTO `chat_log` VALUES ('143', '71', '1', '18', '0', '你好', '1501218661026', '0');
INSERT INTO `chat_log` VALUES ('144', '72', '18', '0', '0', '转接到人工客服', '1501219265150', '1');
INSERT INTO `chat_log` VALUES ('145', '72', '1', '18', '0', '你好', '1501219265166', '0');
INSERT INTO `chat_log` VALUES ('146', '73', '18', '0', '0', '转接到人工客服', '1501221924876', '1');
INSERT INTO `chat_log` VALUES ('147', '73', '1', '18', '0', '你好', '1501221924888', '0');
INSERT INTO `chat_log` VALUES ('148', '73', '1', '18', '0', '你好', '1501221954216', '0');
INSERT INTO `chat_log` VALUES ('149', '73', '2', '18', '0', '您好 我是小二', '1501222031752', '0');
INSERT INTO `chat_log` VALUES ('150', '74', '18', '0', '0', '转接到人工客服', '1501222555792', '1');
INSERT INTO `chat_log` VALUES ('151', '74', '1', '18', '0', '你好', '1501222555813', '0');
INSERT INTO `chat_log` VALUES ('152', '74', '18', '1', '0', '我要买房，房价涨价了吗', '1501222732694', '1');
INSERT INTO `chat_log` VALUES ('153', '75', '18', '0', '0', '我要买房，房价涨价了吗', '1501223921508', '1');
INSERT INTO `chat_log` VALUES ('154', '75', '0', '18', '0', '涨价了', '1501223921657', '0');
INSERT INTO `chat_log` VALUES ('155', '75', '18', '0', '0', '转接到人工客服', '1501223924852', '1');
INSERT INTO `chat_log` VALUES ('156', '75', '1', '18', '0', '你好', '1501223924865', '0');
INSERT INTO `chat_log` VALUES ('157', '76', '18', '0', '0', '转接到人工客服', '1501223941877', '1');
INSERT INTO `chat_log` VALUES ('158', '76', '1', '18', '0', '你好', '1501223941888', '0');
INSERT INTO `chat_log` VALUES ('159', '77', '18', '0', '0', '转接到人工客服', '1501224117500', '1');
INSERT INTO `chat_log` VALUES ('161', '78', '18', '0', '0', '转接到人工客服', '1501224175668', '1');
INSERT INTO `chat_log` VALUES ('162', '78', '1', '18', '0', '你好', '1501224175685', '0');
INSERT INTO `chat_log` VALUES ('163', '78', '18', '1', '0', '我要买房，房价涨价了吗', '1501224215933', '1');
INSERT INTO `chat_log` VALUES ('165', '79', '18', '0', '0', '转接到人工客服', '1501226194227', '1');
INSERT INTO `chat_log` VALUES ('166', '79', '1', '18', '0', '你好', '1501226194236', '0');
INSERT INTO `chat_log` VALUES ('167', '79', '18', '1', '0', '我要买房，房价涨价了吗', '1501226228345', '1');
INSERT INTO `chat_log` VALUES ('168', '79', '18', '1', '0', '我要买房，房价涨价了吗', '1501226234425', '1');
INSERT INTO `chat_log` VALUES ('169', '79', '18', '1', '0', '我要买房，房价涨价了吗', '1501226239477', '1');
INSERT INTO `chat_log` VALUES ('170', '79', '18', '1', '0', '我要买房，房价涨价了吗', '1501226243090', '1');
INSERT INTO `chat_log` VALUES ('171', '79', '18', '1', '0', '我要买房，房价涨价了吗', '1501226255427', '1');
INSERT INTO `chat_log` VALUES ('172', '79', '18', '1', '0', '我要买房，房价涨价了吗', '1501226259195', '1');
INSERT INTO `chat_log` VALUES ('173', '79', '18', '1', '0', '我要买房，房价涨价了吗', '1501226260404', '1');
INSERT INTO `chat_log` VALUES ('174', '79', '18', '1', '0', '我要买房，房价涨价了吗', '1501226261005', '1');
INSERT INTO `chat_log` VALUES ('175', '79', '18', '1', '0', '我要买房，房价涨价了吗', '1501226261595', '1');
INSERT INTO `chat_log` VALUES ('177', '80', '18', '0', '0', '转接到人工客服', '1501226665038', '1');
INSERT INTO `chat_log` VALUES ('178', '80', '1', '18', '0', '你好', '1501226665043', '0');
INSERT INTO `chat_log` VALUES ('179', '81', '18', '0', '0', '转接到人工客服', '1501226782563', '1');
INSERT INTO `chat_log` VALUES ('180', '81', '1', '18', '0', '你好', '1501226782699', '0');
INSERT INTO `chat_log` VALUES ('182', '81', '18', '1', '0', '我要买房，房价涨价了吗', '1501226866314', '1');
INSERT INTO `chat_log` VALUES ('183', '82', '18', '0', '0', '转接到人工客服', '1501226949812', '1');
INSERT INTO `chat_log` VALUES ('185', '83', '18', '0', '0', '我要买房，房价涨价了吗', '1501227538319', '1');
INSERT INTO `chat_log` VALUES ('186', '83', '0', '18', '0', '涨价了', '1501227538350', '0');
INSERT INTO `chat_log` VALUES ('187', '83', '18', '0', '0', '转接到人工客服', '1501227543597', '1');
INSERT INTO `chat_log` VALUES ('188', '83', '1', '18', '0', '你好', '1501227543620', '0');
INSERT INTO `chat_log` VALUES ('189', '83', '18', '1', '0', '转', '1501227571063', '1');
INSERT INTO `chat_log` VALUES ('190', '83', '1', '18', '0', 'see you', '1501227577648', '0');
INSERT INTO `chat_log` VALUES ('191', '83', '1', '18', '0', 'see youhello', '1501227585267', '0');
INSERT INTO `chat_log` VALUES ('192', '83', '1', '18', '0', '你好', '1501227602552', '0');
INSERT INTO `chat_log` VALUES ('193', '83', '1', '18', '0', 'see youhello', '1501227622672', '0');
INSERT INTO `chat_log` VALUES ('194', '83', '18', '1', '0', '转', '1501227627669', '1');
INSERT INTO `chat_log` VALUES ('195', '83', '18', '1', '0', '转接到人工客服', '1501227636085', '1');
INSERT INTO `chat_log` VALUES ('196', '83', '1', '18', '0', 'see youhello', '1501227641864', '0');
INSERT INTO `chat_log` VALUES ('197', '83', '2', '18', '0', '您好 我是小二', '1501227690653', '0');
INSERT INTO `chat_log` VALUES ('198', '83', '2', '18', '0', 'see you', '1501227710946', '0');
INSERT INTO `chat_log` VALUES ('199', '83', '18', '2', '0', '转接到人工客服', '1501227723902', '1');
INSERT INTO `chat_log` VALUES ('200', '83', '2', '18', '0', 'see youhello', '1501227728955', '0');
INSERT INTO `chat_log` VALUES ('201', '83', '1', '18', '0', '你好', '1501227742966', '0');
INSERT INTO `chat_log` VALUES ('202', '84', '18', '0', '0', '转接到人工客服', '1501228196416', '1');
INSERT INTO `chat_log` VALUES ('203', '84', '1', '18', '0', '你好', '1501228200114', '0');
INSERT INTO `chat_log` VALUES ('204', '84', '1', '18', '0', '66', '1501228266001', '0');
INSERT INTO `chat_log` VALUES ('205', '85', '18', '0', '0', '转接到人工客服', '1501228287299', '1');
INSERT INTO `chat_log` VALUES ('206', '85', '1', '18', '0', '你好', '1501228287318', '0');
INSERT INTO `chat_log` VALUES ('207', '86', '18', '0', '0', '转接到人工客服', '1501228926424', '1');
INSERT INTO `chat_log` VALUES ('208', '86', '1', '18', '0', '你好', '1501228933449', '0');
INSERT INTO `chat_log` VALUES ('209', '87', '20', '0', '0', '机器人你好', '1501228990951', '1');
INSERT INTO `chat_log` VALUES ('210', '87', '0', '20', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501228990975', '0');
INSERT INTO `chat_log` VALUES ('211', '87', '20', '0', '0', '转接到人工客服', '1501229004481', '1');
INSERT INTO `chat_log` VALUES ('212', '87', '1', '20', '0', '你好', '1501229065726', '0');
INSERT INTO `chat_log` VALUES ('213', '87', '2', '20', '0', '您好 我是小二', '1501229105769', '0');
INSERT INTO `chat_log` VALUES ('214', '88', '20', '0', '0', '转接到人工客服', '1501229857090', '1');
INSERT INTO `chat_log` VALUES ('215', '88', '1', '20', '0', '你好', '1501229879125', '0');
INSERT INTO `chat_log` VALUES ('216', '88', '20', '1', '0', '转接到人工客服', '1501229893338', '1');
INSERT INTO `chat_log` VALUES ('217', '89', '20', '0', '0', '转接到人工客服', '1501229990149', '1');
INSERT INTO `chat_log` VALUES ('218', '89', '1', '20', '0', '你好', '1501229990158', '0');
INSERT INTO `chat_log` VALUES ('219', '90', '20', '0', '0', '转接到人工客服', '1501230455124', '1');
INSERT INTO `chat_log` VALUES ('220', '91', '20', '0', '0', '机器人你好', '1501230556755', '1');
INSERT INTO `chat_log` VALUES ('221', '91', '0', '20', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501230556807', '0');
INSERT INTO `chat_log` VALUES ('222', '91', '20', '0', '0', '转接到人工客服', '1501230558840', '1');
INSERT INTO `chat_log` VALUES ('223', '91', '1', '20', '0', '你好', '1501230570453', '0');
INSERT INTO `chat_log` VALUES ('224', '92', '20', '0', '0', '转接到人工客服', '1501230611142', '1');
INSERT INTO `chat_log` VALUES ('225', '92', '2', '20', '0', '您好 我是小二', '1501230611156', '0');
INSERT INTO `chat_log` VALUES ('226', '92', '1', '20', '0', '你好', '1501230671998', '0');
INSERT INTO `chat_log` VALUES ('227', '93', '20', '0', '0', '转接到人工客服', '1501231304413', '1');
INSERT INTO `chat_log` VALUES ('228', '93', '1', '20', '0', '你好', '1501231304422', '0');
INSERT INTO `chat_log` VALUES ('229', '93', '2', '20', '0', '您好 我是小二', '1501231326767', '0');
INSERT INTO `chat_log` VALUES ('230', '94', '20', '0', '0', '转接到人工客服', '1501231539633', '1');
INSERT INTO `chat_log` VALUES ('231', '94', '1', '20', '0', '你好', '1501231539646', '0');
INSERT INTO `chat_log` VALUES ('232', '94', '2', '20', '0', '您好 我是小二', '1501231555758', '0');
INSERT INTO `chat_log` VALUES ('233', '95', '20', '0', '0', '转接到人工客服', '1501231834033', '1');
INSERT INTO `chat_log` VALUES ('234', '95', '1', '20', '0', '你好', '1501231834042', '0');
INSERT INTO `chat_log` VALUES ('235', '95', '2', '20', '0', '您好 我是小二', '1501231844074', '0');
INSERT INTO `chat_log` VALUES ('236', '95', '1', '20', '0', '你好', '1501231854649', '0');
INSERT INTO `chat_log` VALUES ('237', '103', '18', '0', '0', '机器人你好', '1501323490710', '1');
INSERT INTO `chat_log` VALUES ('238', '103', '0', '18', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501323490953', '0');
INSERT INTO `chat_log` VALUES ('239', '104', '18', '0', '0', '真的？', '1501323612612', '1');
INSERT INTO `chat_log` VALUES ('240', '104', '0', '18', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501323612624', '0');
INSERT INTO `chat_log` VALUES ('241', '104', '18', '0', '0', '<p>房价涨价了吗</p>', '1501323629640', '1');
INSERT INTO `chat_log` VALUES ('242', '104', '0', '18', '0', '涨价了', '1501323629888', '0');
INSERT INTO `chat_log` VALUES ('243', '104', '18', '0', '0', '我要买房价买房，多少钱一平方啊？', '1501323634064', '1');
INSERT INTO `chat_log` VALUES ('244', '104', '0', '18', '0', '不知道', '1501323634097', '0');
INSERT INTO `chat_log` VALUES ('245', '106', '18', '0', '0', '<p><span>我要买房价买房，多少钱一平方啊？</span></p>', '1501323691478', '1');
INSERT INTO `chat_log` VALUES ('246', '106', '0', '18', '0', '不知道', '1501323691534', '0');
INSERT INTO `chat_log` VALUES ('247', '106', '18', '0', '0', '最近的房价涨价了吗', '1501323693631', '1');
INSERT INTO `chat_log` VALUES ('248', '106', '0', '18', '0', '涨价了', '1501323693788', '0');
INSERT INTO `chat_log` VALUES ('249', '106', '18', '0', '0', '我要买房价买房，多少钱一平方啊？', '1501323698766', '1');
INSERT INTO `chat_log` VALUES ('250', '106', '0', '18', '0', '不知道', '1501323698810', '0');
INSERT INTO `chat_log` VALUES ('251', '106', '18', '0', '0', '最近的房价涨价了吗', '1501323704112', '1');
INSERT INTO `chat_log` VALUES ('252', '106', '0', '18', '0', '涨价了', '1501323704124', '0');
INSERT INTO `chat_log` VALUES ('253', '106', '18', '0', '0', '我要买房价买房，多少钱一平方啊？', '1501323709870', '1');
INSERT INTO `chat_log` VALUES ('254', '106', '0', '18', '0', '不知道', '1501323709896', '0');
INSERT INTO `chat_log` VALUES ('255', '106', '18', '0', '0', '最近的房价涨价了吗', '1501323712345', '1');
INSERT INTO `chat_log` VALUES ('256', '106', '0', '18', '0', '涨价了', '1501323712363', '0');
INSERT INTO `chat_log` VALUES ('257', '108', '18', '0', '0', '<p><span>最近的房价涨价了吗</span></p>', '1501323855662', '1');
INSERT INTO `chat_log` VALUES ('258', '108', '0', '18', '0', '涨价了', '1501323855673', '0');
INSERT INTO `chat_log` VALUES ('259', '109', '18', '0', '0', '<p><span>最近的房价涨价了吗</span></p>', '1501323901649', '1');
INSERT INTO `chat_log` VALUES ('260', '109', '0', '18', '0', '涨价了', '1501323901668', '0');
INSERT INTO `chat_log` VALUES ('261', '109', '18', '0', '0', '我要买房价买房，多少钱一平方啊？', '1501323919649', '1');
INSERT INTO `chat_log` VALUES ('262', '109', '0', '18', '0', '不知道', '1501323919807', '0');
INSERT INTO `chat_log` VALUES ('263', '109', '18', '0', '0', '我要买房价买房，多少钱一平方啊？', '1501323919868', '1');
INSERT INTO `chat_log` VALUES ('264', '109', '0', '18', '0', '不知道', '1501323919994', '0');
INSERT INTO `chat_log` VALUES ('265', '110', '18', '0', '0', '<p><span>我要买房价买房，多少钱一平方啊？</span></p>', '1501324019300', '1');
INSERT INTO `chat_log` VALUES ('266', '110', '0', '18', '0', '不知道', '1501324019334', '0');
INSERT INTO `chat_log` VALUES ('267', '110', '18', '0', '0', '最近的房价涨价了吗', '1501324021992', '1');
INSERT INTO `chat_log` VALUES ('268', '110', '0', '18', '0', '涨价了', '1501324022026', '0');
INSERT INTO `chat_log` VALUES ('269', '110', '18', '0', '0', '转接到人工客服', '1501324193694', '1');
INSERT INTO `chat_log` VALUES ('270', '110', '1', '18', '0', '你好', '1501324193756', '0');
INSERT INTO `chat_log` VALUES ('272', '110', '18', '1', '0', '你好啊·', '1501324287902', '1');
INSERT INTO `chat_log` VALUES ('273', '111', '18', '0', '0', '转接到人工客服', '1501324490478', '1');
INSERT INTO `chat_log` VALUES ('274', '111', '1', '18', '0', '你好', '1501324490493', '0');
INSERT INTO `chat_log` VALUES ('275', '111', '18', '1', '0', '有人吗', '1501324521537', '1');
INSERT INTO `chat_log` VALUES ('277', '112', '18', '0', '0', '<p><span>转接到人工客服</span></p>', '1501324824196', '1');
INSERT INTO `chat_log` VALUES ('278', '112', '0', '18', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501324824215', '0');
INSERT INTO `chat_log` VALUES ('279', '112', '18', '0', '0', '<p>转接到人工客服</p>', '1501324839132', '1');
INSERT INTO `chat_log` VALUES ('280', '112', '0', '18', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501324839142', '0');
INSERT INTO `chat_log` VALUES ('281', '112', '18', '0', '0', '转接到人工客服', '1501324857661', '1');
INSERT INTO `chat_log` VALUES ('282', '112', '1', '18', '0', '你好', '1501324857706', '0');
INSERT INTO `chat_log` VALUES ('283', '112', '1', '18', '0', '<p>你真的好 吗</p>', '1501324879052', '0');
INSERT INTO `chat_log` VALUES ('284', '112', '18', '1', '0', '好！', '1501324890764', '1');
INSERT INTO `chat_log` VALUES ('285', '112', '18', '1', '0', '我要咨询房价！', '1501324902748', '1');
INSERT INTO `chat_log` VALUES ('286', '112', '1', '18', '0', '涨价了', '1501324907062', '0');
INSERT INTO `chat_log` VALUES ('287', '112', '1', '18', '0', '你好see yousee yousee you', '1501324913637', '0');
INSERT INTO `chat_log` VALUES ('288', '112', '18', '1', '0', '666，很牛逼。', '1501324970327', '1');
INSERT INTO `chat_log` VALUES ('289', '113', '18', '0', '0', '转接到人工客服', '1501325047882', '1');
INSERT INTO `chat_log` VALUES ('290', '113', '1', '18', '0', '你好', '1501325048012', '0');
INSERT INTO `chat_log` VALUES ('292', '114', '18', '0', '0', '<p>转接到人工客服</p>', '1501325163593', '1');
INSERT INTO `chat_log` VALUES ('293', '114', '0', '18', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501325163600', '0');
INSERT INTO `chat_log` VALUES ('294', '114', '18', '0', '0', '转接到人工客服', '1501325174675', '1');
INSERT INTO `chat_log` VALUES ('295', '114', '1', '18', '0', '你好', '1501325174690', '0');
INSERT INTO `chat_log` VALUES ('297', '115', '18', '0', '0', '转接到人工客服', '1501325357284', '1');
INSERT INTO `chat_log` VALUES ('298', '115', '1', '18', '0', '你好', '1501325357299', '0');
INSERT INTO `chat_log` VALUES ('299', '115', '1', '18', '0', '666666', '1501325380320', '0');
INSERT INTO `chat_log` VALUES ('300', '115', '1', '18', '0', '这非常牛逼1', '1501325402298', '0');
INSERT INTO `chat_log` VALUES ('301', '115', '18', '1', '0', '我很酷！', '1501325414530', '1');
INSERT INTO `chat_log` VALUES ('302', '116', '18', '0', '0', '转接到人工客服', '1501325570336', '1');
INSERT INTO `chat_log` VALUES ('303', '116', '1', '18', '0', '你好', '1501325570352', '0');
INSERT INTO `chat_log` VALUES ('305', '117', '18', '0', '0', '转接到人工客服', '1501326078470', '1');
INSERT INTO `chat_log` VALUES ('306', '117', '1', '18', '0', '你好', '1501326078485', '0');
INSERT INTO `chat_log` VALUES ('307', '117', '1', '18', '0', '啊啊啊', '1501326085309', '0');
INSERT INTO `chat_log` VALUES ('308', '118', '18', '0', '0', '转接到人工客服', '1501327175030', '1');
INSERT INTO `chat_log` VALUES ('309', '118', '1', '18', '0', '你好', '1501327175046', '0');
INSERT INTO `chat_log` VALUES ('310', '118', '1', '18', '0', '额', '1501327180448', '0');
INSERT INTO `chat_log` VALUES ('311', '118', '18', '1', '0', '哈哈哈哈', '1501327188408', '1');
INSERT INTO `chat_log` VALUES ('312', '118', '18', '1', '0', '666666', '1501327207692', '1');
INSERT INTO `chat_log` VALUES ('313', '118', '18', '1', '0', '转接到人工客服', '1501327312727', '1');
INSERT INTO `chat_log` VALUES ('314', '119', '18', '0', '0', '转接到人工客服', '1501327388277', '1');
INSERT INTO `chat_log` VALUES ('315', '119', '1', '18', '0', '你好', '1501327388287', '0');
INSERT INTO `chat_log` VALUES ('316', '119', '18', '1', '0', '我很帅', '1501327403337', '1');
INSERT INTO `chat_log` VALUES ('317', '119', '18', '1', '0', '很帅的', '1501327416238', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES ('1', null, null, null, null, null);
INSERT INTO `client` VALUES ('2', null, null, null, null, '0');
INSERT INTO `client` VALUES ('3', null, null, null, null, '0');
INSERT INTO `client` VALUES ('4', null, null, null, null, '0');
INSERT INTO `client` VALUES ('5', null, null, null, null, '0');
INSERT INTO `client` VALUES ('6', null, null, null, null, '0');
INSERT INTO `client` VALUES ('7', null, null, null, null, '0');
INSERT INTO `client` VALUES ('8', null, null, null, null, '0');
INSERT INTO `client` VALUES ('9', null, null, null, null, '0');
INSERT INTO `client` VALUES ('10', null, null, null, null, '0');
INSERT INTO `client` VALUES ('11', null, null, null, null, '0');
INSERT INTO `client` VALUES ('12', null, null, null, null, '0');
INSERT INTO `client` VALUES ('13', null, null, null, null, '0');
INSERT INTO `client` VALUES ('14', null, null, null, null, '0');
INSERT INTO `client` VALUES ('15', null, null, null, null, '0');
INSERT INTO `client` VALUES ('16', null, null, null, null, '0');
INSERT INTO `client` VALUES ('17', null, null, null, null, '0');
INSERT INTO `client` VALUES ('18', '测试者1', null, null, null, '1');
INSERT INTO `client` VALUES ('19', '测试者2', '西湖区', 'abc@123.com', '88886666', '1');
INSERT INTO `client` VALUES ('20', null, null, null, null, '0');

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
INSERT INTO `client_flag` VALUES ('19', '4');
INSERT INTO `client_flag` VALUES ('18', '4');
INSERT INTO `client_flag` VALUES ('18', '2');
INSERT INTO `client_flag` VALUES ('18', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of common_language
-- ----------------------------
INSERT INTO `common_language` VALUES ('1', null, '你好', '10');
INSERT INTO `common_language` VALUES ('2', null, 'hello', '12');
INSERT INTO `common_language` VALUES ('3', null, 'see you', '12');

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
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of conversation
-- ----------------------------
INSERT INTO `conversation` VALUES ('1', '1', '1', '1499443200000', '1501327552055', '3');
INSERT INTO `conversation` VALUES ('2', '1', '0', '1499011200000', '1499013200000', '4');
INSERT INTO `conversation` VALUES ('3', '1', '1', '1499616000000', '1501327552055', '5');
INSERT INTO `conversation` VALUES ('4', '1', '0', '1499991300000', '1499991500000', '4');
INSERT INTO `conversation` VALUES ('5', '1', '0', '1499991000000', '1500168287499', null);
INSERT INTO `conversation` VALUES ('6', '2', '0', '1500518098602', null, null);
INSERT INTO `conversation` VALUES ('7', '2', '1', '1500518362645', '1501327552055', null);
INSERT INTO `conversation` VALUES ('8', '2', '0', '1500523956671', null, null);
INSERT INTO `conversation` VALUES ('9', '2', '1', '1500524087661', '1501327552055', null);
INSERT INTO `conversation` VALUES ('10', '2', '1', '1500524619044', '1501327552055', null);
INSERT INTO `conversation` VALUES ('11', '2', '0', '1500524802896', null, null);
INSERT INTO `conversation` VALUES ('12', '2', '0', '1500524813836', null, null);
INSERT INTO `conversation` VALUES ('13', '2', '0', '1500524976193', null, null);
INSERT INTO `conversation` VALUES ('14', '2', '0', '1500525268658', null, null);
INSERT INTO `conversation` VALUES ('15', '2', '0', '1500525561131', null, '4');
INSERT INTO `conversation` VALUES ('16', '2', '1', '1500533728186', '1501327552055', null);
INSERT INTO `conversation` VALUES ('17', '2', '1', '1500534756465', '1501327552055', null);
INSERT INTO `conversation` VALUES ('18', '2', '1', '1500534990824', '1501327552055', null);
INSERT INTO `conversation` VALUES ('19', '2', '1', '1500535064139', '1501327552055', null);
INSERT INTO `conversation` VALUES ('20', '2', '1', '1500535197136', '1501327552055', null);
INSERT INTO `conversation` VALUES ('21', '2', '1', '1500535440903', '1501327552055', null);
INSERT INTO `conversation` VALUES ('22', '2', '1', '1500535665659', '1501327552055', null);
INSERT INTO `conversation` VALUES ('23', '2', '1', '1500685496105', '1501327552055', null);
INSERT INTO `conversation` VALUES ('24', '2', '1', '1500685693702', '1501327552055', null);
INSERT INTO `conversation` VALUES ('25', '2', '1', '1500685884974', '1501327552055', null);
INSERT INTO `conversation` VALUES ('26', '2', '1', '1500686086898', '1501327552055', null);
INSERT INTO `conversation` VALUES ('27', '2', '1', '1500686219648', '1501327552055', null);
INSERT INTO `conversation` VALUES ('28', '2', '0', '1500686695117', null, null);
INSERT INTO `conversation` VALUES ('29', '3', '0', '1500692316501', null, null);
INSERT INTO `conversation` VALUES ('30', '3', '1', '1500692614959', '1501327552055', null);
INSERT INTO `conversation` VALUES ('31', '3', '0', '1500693172586', null, null);
INSERT INTO `conversation` VALUES ('32', '4', '0', '1500693191041', null, null);
INSERT INTO `conversation` VALUES ('33', '5', '0', '1500693366792', null, null);
INSERT INTO `conversation` VALUES ('34', '5', '0', '1500693497198', null, null);
INSERT INTO `conversation` VALUES ('35', '6', '0', '1500693742563', null, null);
INSERT INTO `conversation` VALUES ('36', '7', '0', '1500693978355', null, null);
INSERT INTO `conversation` VALUES ('37', '8', '0', '1500694214698', null, null);
INSERT INTO `conversation` VALUES ('38', '9', '0', '1500694355252', null, null);
INSERT INTO `conversation` VALUES ('39', '10', '0', '1500694809684', null, null);
INSERT INTO `conversation` VALUES ('40', '11', '0', '1500696874864', null, null);
INSERT INTO `conversation` VALUES ('41', '12', '0', '1500697057001', null, null);
INSERT INTO `conversation` VALUES ('42', '13', '0', '1500697202135', null, null);
INSERT INTO `conversation` VALUES ('43', '14', '0', '1500697663924', null, null);
INSERT INTO `conversation` VALUES ('44', '15', '0', '1500697872575', null, null);
INSERT INTO `conversation` VALUES ('45', '16', '0', '1500869464121', null, null);
INSERT INTO `conversation` VALUES ('46', '17', '0', '1500871583947', null, null);
INSERT INTO `conversation` VALUES ('47', '17', '0', '1500871588257', '1500871680075', null);
INSERT INTO `conversation` VALUES ('48', '18', '0', '1501151541693', '1501151854108', null);
INSERT INTO `conversation` VALUES ('49', '18', '0', '1501151886797', '1501152032301', null);
INSERT INTO `conversation` VALUES ('50', '18', '0', '1501152032572', '1501152899208', null);
INSERT INTO `conversation` VALUES ('51', '18', '0', '1501152899471', '1501153235160', null);
INSERT INTO `conversation` VALUES ('52', '18', '0', '1501153235448', '1501153290672', null);
INSERT INTO `conversation` VALUES ('53', '18', '0', '1501153290973', '1501154185460', null);
INSERT INTO `conversation` VALUES ('54', '18', '0', '1501154238451', '1501154266932', null);
INSERT INTO `conversation` VALUES ('55', '18', '0', '1501154609034', '1501154987103', null);
INSERT INTO `conversation` VALUES ('56', '18', '0', '1501154987507', '1501155873620', null);
INSERT INTO `conversation` VALUES ('57', '18', '0', '1501155874039', '1501156985110', null);
INSERT INTO `conversation` VALUES ('58', '19', '2', '1501157107035', '1501231865006', null);
INSERT INTO `conversation` VALUES ('59', '19', '2', '1501157322262', '1501231865006', null);
INSERT INTO `conversation` VALUES ('60', '19', '2', '1501157522561', '1501231865006', null);
INSERT INTO `conversation` VALUES ('61', '19', '2', '1501158055900', '1501231865006', null);
INSERT INTO `conversation` VALUES ('62', '19', '0', '1501158278778', '1501158289551', null);
INSERT INTO `conversation` VALUES ('63', '19', '0', '1501158289941', '1501158386770', null);
INSERT INTO `conversation` VALUES ('64', '19', '0', '1501158387092', '1501158504810', null);
INSERT INTO `conversation` VALUES ('65', '19', '1', '1501158555605', '1501327552055', null);
INSERT INTO `conversation` VALUES ('66', '18', '1', '1501158632106', '1501327552055', null);
INSERT INTO `conversation` VALUES ('67', '18', '1', '1501159545755', '1501327552055', null);
INSERT INTO `conversation` VALUES ('68', '18', '1', '1501159651788', '1501327552055', null);
INSERT INTO `conversation` VALUES ('69', '18', '0', '1501218452825', '1501218455327', null);
INSERT INTO `conversation` VALUES ('70', '18', '1', '1501218455715', '1501327552055', null);
INSERT INTO `conversation` VALUES ('71', '18', '1', '1501218650020', '1501327552055', null);
INSERT INTO `conversation` VALUES ('72', '18', '1', '1501219258799', '1501327552055', null);
INSERT INTO `conversation` VALUES ('73', '18', '2', '1501221904813', '1501231865006', null);
INSERT INTO `conversation` VALUES ('74', '18', '1', '1501222553686', '1501327552055', null);
INSERT INTO `conversation` VALUES ('75', '18', '1', '1501223918589', '1501327552055', null);
INSERT INTO `conversation` VALUES ('76', '18', '1', '1501223940345', '1501327552055', null);
INSERT INTO `conversation` VALUES ('77', '18', '0', '1501224114568', '1501224173193', null);
INSERT INTO `conversation` VALUES ('78', '18', '1', '1501224173630', '1501327552055', null);
INSERT INTO `conversation` VALUES ('79', '18', '1', '1501226190655', '1501327552055', null);
INSERT INTO `conversation` VALUES ('80', '18', '1', '1501226658447', '1501327552055', null);
INSERT INTO `conversation` VALUES ('81', '18', '1', '1501226780999', '1501327552055', null);
INSERT INTO `conversation` VALUES ('82', '18', '0', '1501226946852', '1501227116388', null);
INSERT INTO `conversation` VALUES ('83', '18', '1', '1501227531313', '1501327552055', null);
INSERT INTO `conversation` VALUES ('84', '18', '1', '1501228175019', '1501327552055', null);
INSERT INTO `conversation` VALUES ('85', '18', '1', '1501228284399', '1501327552055', null);
INSERT INTO `conversation` VALUES ('86', '18', '1', '1501228920648', '1501327552055', null);
INSERT INTO `conversation` VALUES ('87', '20', '2', '1501228967471', '1501231865006', null);
INSERT INTO `conversation` VALUES ('88', '20', '1', '1501229852203', '1501327552055', null);
INSERT INTO `conversation` VALUES ('89', '20', '1', '1501229988209', '1501327552055', null);
INSERT INTO `conversation` VALUES ('90', '20', '0', '1501230447981', '1501230552623', null);
INSERT INTO `conversation` VALUES ('91', '20', '1', '1501230552949', '1501327552055', null);
INSERT INTO `conversation` VALUES ('92', '20', '1', '1501230605056', '1501327552055', null);
INSERT INTO `conversation` VALUES ('93', '20', '2', '1501231293206', '1501231865006', null);
INSERT INTO `conversation` VALUES ('94', '20', '2', '1501231537499', '1501231865006', null);
INSERT INTO `conversation` VALUES ('95', '20', '1', '1501231832074', '1501327552055', null);
INSERT INTO `conversation` VALUES ('96', '18', '0', '1501322507462', '1501322566458', null);
INSERT INTO `conversation` VALUES ('97', '18', '0', '1501322566651', '1501323081758', null);
INSERT INTO `conversation` VALUES ('98', '18', '0', '1501323081933', '1501323116540', null);
INSERT INTO `conversation` VALUES ('99', '18', '0', '1501323116708', '1501323188843', null);
INSERT INTO `conversation` VALUES ('100', '18', '0', '1501323188998', '1501323261679', null);
INSERT INTO `conversation` VALUES ('101', '18', '0', '1501323261861', '1501323295175', null);
INSERT INTO `conversation` VALUES ('102', '18', '0', '1501323295341', '1501323460301', null);
INSERT INTO `conversation` VALUES ('103', '18', '0', '1501323460462', '1501323604444', null);
INSERT INTO `conversation` VALUES ('104', '18', '0', '1501323604597', '1501323634154', null);
INSERT INTO `conversation` VALUES ('105', '18', '0', '1501323634290', '1501323634339', null);
INSERT INTO `conversation` VALUES ('106', '18', '0', '1501323685030', '1501323846307', null);
INSERT INTO `conversation` VALUES ('107', '18', '0', '1501323846464', '1501323847321', null);
INSERT INTO `conversation` VALUES ('108', '18', '0', '1501323847510', '1501323897550', null);
INSERT INTO `conversation` VALUES ('109', '18', '0', '1501323897740', '1501324011364', null);
INSERT INTO `conversation` VALUES ('110', '18', '1', '1501324011563', '1501327552055', null);
INSERT INTO `conversation` VALUES ('111', '18', '1', '1501324452071', '1501327552055', null);
INSERT INTO `conversation` VALUES ('112', '18', '1', '1501324809523', '1501327552055', null);
INSERT INTO `conversation` VALUES ('113', '18', '1', '1501325039435', '1501327552055', null);
INSERT INTO `conversation` VALUES ('114', '18', '1', '1501325126666', '1501327552055', null);
INSERT INTO `conversation` VALUES ('115', '18', '1', '1501325345674', '1501327552055', null);
INSERT INTO `conversation` VALUES ('116', '18', '1', '1501325560694', '1501327552055', null);
INSERT INTO `conversation` VALUES ('117', '18', '1', '1501326050516', '1501327552055', null);
INSERT INTO `conversation` VALUES ('118', '18', '1', '1501327149559', '1501327552055', null);
INSERT INTO `conversation` VALUES ('119', '18', '1', '1501327327073', '1501327552055', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer_service
-- ----------------------------
INSERT INTO `customer_service` VALUES ('0', '机器人', '1', '小机器人', 'CCC', 'Hello，我是机器人客服', '0', '');
INSERT INTO `customer_service` VALUES ('1', '李', '1', '小李', 'ABC', '你好', '0', 'ABC');
INSERT INTO `customer_service` VALUES ('2', '2号客服', '2', '小二', 'CBA', '您好 我是小二', '0', '888888');
INSERT INTO `customer_service` VALUES ('3', '3号客服', '1', '小三', 'AAA', '您好 我是小三', '0', '888888');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flag
-- ----------------------------
INSERT INTO `flag` VALUES ('2', '很帅');
INSERT INTO `flag` VALUES ('3', '很牛逼');
INSERT INTO `flag` VALUES ('1', '很酷');
INSERT INTO `flag` VALUES ('4', '非常牛逼');

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
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

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
INSERT INTO `join_up` VALUES ('21', '1', '2', '1500686086886', '7113404510');
INSERT INTO `join_up` VALUES ('22', '1', '2', '1500686219644', '7113404510');
INSERT INTO `join_up` VALUES ('23', '1', '2', '1500686695109', '7113404510');
INSERT INTO `join_up` VALUES ('24', '1', '3', '1500692316481', '5289964052');
INSERT INTO `join_up` VALUES ('25', '1', '3', '1500692614944', '5289964052');
INSERT INTO `join_up` VALUES ('26', '1', '3', '1500693172564', '5289964052');
INSERT INTO `join_up` VALUES ('27', '1', '4', '1500693190673', '1458117387');
INSERT INTO `join_up` VALUES ('28', '1', '5', '1500693366785', '6936161764');
INSERT INTO `join_up` VALUES ('29', '1', '5', '1500693497173', '6936161764');
INSERT INTO `join_up` VALUES ('30', '1', '6', '1500693742402', '2480123965');
INSERT INTO `join_up` VALUES ('31', '1', '7', '1500693978339', '1546425541');
INSERT INTO `join_up` VALUES ('32', '1', '8', '1500694214547', '6214491558');
INSERT INTO `join_up` VALUES ('33', '1', '9', '1500694355232', '3400730152');
INSERT INTO `join_up` VALUES ('34', '1', '10', '1500694809679', '0899464778');
INSERT INTO `join_up` VALUES ('35', '1', '11', '1500696874519', '5484922116');
INSERT INTO `join_up` VALUES ('36', '1', '12', '1500697056996', '3412682951');
INSERT INTO `join_up` VALUES ('37', '1', '13', '1500697202131', '5141843132');
INSERT INTO `join_up` VALUES ('38', '1', '14', '1500697663792', '1030955346');
INSERT INTO `join_up` VALUES ('39', '1', '15', '1500697872570', '5488677277');
INSERT INTO `join_up` VALUES ('40', '1', '16', '1500869464105', '1481688969');
INSERT INTO `join_up` VALUES ('41', '1', '17', '1500871583940', '1378914674');
INSERT INTO `join_up` VALUES ('42', '1', '17', '1500871588246', '1378914674');
INSERT INTO `join_up` VALUES ('43', '1', '18', '1501151541688', '1378112422');
INSERT INTO `join_up` VALUES ('44', '1', '18', '1501151886780', '1378112422');
INSERT INTO `join_up` VALUES ('45', '1', '18', '1501152032567', '1378112422');
INSERT INTO `join_up` VALUES ('46', '1', '18', '1501152899467', '1378112422');
INSERT INTO `join_up` VALUES ('47', '1', '18', '1501153235441', '1378112422');
INSERT INTO `join_up` VALUES ('48', '1', '18', '1501153290967', '1378112422');
INSERT INTO `join_up` VALUES ('49', '1', '18', '1501154238425', '1378112422');
INSERT INTO `join_up` VALUES ('50', '1', '18', '1501154609024', '1378112422');
INSERT INTO `join_up` VALUES ('51', '1', '18', '1501154987500', '1378112422');
INSERT INTO `join_up` VALUES ('52', '1', '18', '1501155874034', '1378112422');
INSERT INTO `join_up` VALUES ('53', '1', '19', '1501157107032', '6583943510');
INSERT INTO `join_up` VALUES ('54', '1', '19', '1501157322255', '6583943510');
INSERT INTO `join_up` VALUES ('55', '1', '19', '1501157522557', '6583943510');
INSERT INTO `join_up` VALUES ('56', '1', '19', '1501158055882', '6583943510');
INSERT INTO `join_up` VALUES ('57', '1', '19', '1501158278773', '6583943510');
INSERT INTO `join_up` VALUES ('58', '1', '19', '1501158289934', '6583943510');
INSERT INTO `join_up` VALUES ('59', '1', '19', '1501158387089', '6583943510');
INSERT INTO `join_up` VALUES ('60', '1', '19', '1501158555590', '6583943510');
INSERT INTO `join_up` VALUES ('61', '2', '19', '1501158619584', '894561230');
INSERT INTO `join_up` VALUES ('62', '3', '19', '1501158619593', 'wxid_11111');
INSERT INTO `join_up` VALUES ('63', '4', '19', '1501158619602', 'wwww');
INSERT INTO `join_up` VALUES ('64', '1', '19', '1501158632095', '6583943510');
INSERT INTO `join_up` VALUES ('65', '1', '19', '1501159545743', '6583943510');
INSERT INTO `join_up` VALUES ('66', '1', '19', '1501159651781', '6583943510');
INSERT INTO `join_up` VALUES ('67', '1', '18', '1501218452704', '1378112422');
INSERT INTO `join_up` VALUES ('68', '1', '18', '1501218455703', '1378112422');
INSERT INTO `join_up` VALUES ('69', '1', '18', '1501218650014', '1378112422');
INSERT INTO `join_up` VALUES ('70', '1', '18', '1501219258784', '1378112422');
INSERT INTO `join_up` VALUES ('71', '1', '18', '1501221904737', '1378112422');
INSERT INTO `join_up` VALUES ('72', '1', '18', '1501222553670', '1378112422');
INSERT INTO `join_up` VALUES ('73', '1', '18', '1501223918579', '1378112422');
INSERT INTO `join_up` VALUES ('74', '1', '18', '1501223940336', '1378112422');
INSERT INTO `join_up` VALUES ('75', '1', '18', '1501224114553', '1378112422');
INSERT INTO `join_up` VALUES ('76', '1', '18', '1501224173622', '1378112422');
INSERT INTO `join_up` VALUES ('77', '1', '18', '1501226190650', '1378112422');
INSERT INTO `join_up` VALUES ('78', '1', '18', '1501226658438', '1378112422');
INSERT INTO `join_up` VALUES ('79', '1', '18', '1501226780991', '1378112422');
INSERT INTO `join_up` VALUES ('80', '1', '18', '1501226946849', '1378112422');
INSERT INTO `join_up` VALUES ('81', '1', '18', '1501227531285', '1378112422');
INSERT INTO `join_up` VALUES ('82', '1', '18', '1501228174937', '1378112422');
INSERT INTO `join_up` VALUES ('83', '1', '18', '1501228284389', '1378112422');
INSERT INTO `join_up` VALUES ('84', '1', '18', '1501228920622', '1378112422');
INSERT INTO `join_up` VALUES ('85', '1', '20', '1501228967467', '8298883200');
INSERT INTO `join_up` VALUES ('86', '1', '20', '1501229852143', '8298883200');
INSERT INTO `join_up` VALUES ('87', '1', '20', '1501229988197', '8298883200');
INSERT INTO `join_up` VALUES ('88', '1', '20', '1501230447961', '8298883200');
INSERT INTO `join_up` VALUES ('89', '1', '20', '1501230552941', '8298883200');
INSERT INTO `join_up` VALUES ('90', '1', '20', '1501230605048', '8298883200');
INSERT INTO `join_up` VALUES ('91', '1', '20', '1501231293197', '8298883200');
INSERT INTO `join_up` VALUES ('92', '1', '20', '1501231537483', '8298883200');
INSERT INTO `join_up` VALUES ('93', '1', '20', '1501231832057', '8298883200');
INSERT INTO `join_up` VALUES ('94', '1', '18', '1501322507204', '1378112422');
INSERT INTO `join_up` VALUES ('95', '1', '18', '1501322566630', '1378112422');
INSERT INTO `join_up` VALUES ('96', '1', '18', '1501323081917', '1378112422');
INSERT INTO `join_up` VALUES ('97', '1', '18', '1501323116697', '1378112422');
INSERT INTO `join_up` VALUES ('98', '1', '18', '1501323188988', '1378112422');
INSERT INTO `join_up` VALUES ('99', '1', '18', '1501323261844', '1378112422');
INSERT INTO `join_up` VALUES ('100', '1', '18', '1501323295331', '1378112422');
INSERT INTO `join_up` VALUES ('101', '1', '18', '1501323460454', '1378112422');
INSERT INTO `join_up` VALUES ('102', '1', '18', '1501323604588', '1378112422');
INSERT INTO `join_up` VALUES ('103', '1', '18', '1501323634248', '1378112422');
INSERT INTO `join_up` VALUES ('104', '1', '18', '1501323685022', '1378112422');
INSERT INTO `join_up` VALUES ('105', '1', '18', '1501323846457', '1378112422');
INSERT INTO `join_up` VALUES ('106', '1', '18', '1501323847489', '1378112422');
INSERT INTO `join_up` VALUES ('107', '1', '18', '1501323897733', '1378112422');
INSERT INTO `join_up` VALUES ('108', '1', '18', '1501324011556', '1378112422');
INSERT INTO `join_up` VALUES ('109', '1', '18', '1501324452062', '1378112422');
INSERT INTO `join_up` VALUES ('110', '1', '18', '1501324809489', '1378112422');
INSERT INTO `join_up` VALUES ('111', '1', '18', '1501325039419', '1378112422');
INSERT INTO `join_up` VALUES ('112', '1', '18', '1501325126659', '1378112422');
INSERT INTO `join_up` VALUES ('113', '1', '18', '1501325345656', '1378112422');
INSERT INTO `join_up` VALUES ('114', '1', '18', '1501325560663', '1378112422');
INSERT INTO `join_up` VALUES ('115', '1', '18', '1501326050497', '1378112422');
INSERT INTO `join_up` VALUES ('116', '1', '18', '1501327149542', '1378112422');
INSERT INTO `join_up` VALUES ('117', '1', '18', '1501327327058', '1378112422');

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
INSERT INTO `keyword_heat` VALUES ('3', '1501153579897');
INSERT INTO `keyword_heat` VALUES ('7', '1501153579967');
INSERT INTO `keyword_heat` VALUES ('1', '1501153579972');
INSERT INTO `keyword_heat` VALUES ('3', '1501154629508');
INSERT INTO `keyword_heat` VALUES ('7', '1501154629513');
INSERT INTO `keyword_heat` VALUES ('1', '1501154629516');
INSERT INTO `keyword_heat` VALUES ('3', '1501155000436');
INSERT INTO `keyword_heat` VALUES ('7', '1501155000440');
INSERT INTO `keyword_heat` VALUES ('1', '1501155000443');
INSERT INTO `keyword_heat` VALUES ('1', '1501155125217');
INSERT INTO `keyword_heat` VALUES ('7', '1501155137756');
INSERT INTO `keyword_heat` VALUES ('3', '1501155243993');
INSERT INTO `keyword_heat` VALUES ('1', '1501157200779');
INSERT INTO `keyword_heat` VALUES ('3', '1501222732826');
INSERT INTO `keyword_heat` VALUES ('7', '1501222733130');
INSERT INTO `keyword_heat` VALUES ('1', '1501222733132');
INSERT INTO `keyword_heat` VALUES ('3', '1501223921518');
INSERT INTO `keyword_heat` VALUES ('7', '1501223921523');
INSERT INTO `keyword_heat` VALUES ('1', '1501223921649');
INSERT INTO `keyword_heat` VALUES ('3', '1501224215954');
INSERT INTO `keyword_heat` VALUES ('7', '1501224215959');
INSERT INTO `keyword_heat` VALUES ('1', '1501224215962');
INSERT INTO `keyword_heat` VALUES ('3', '1501224453576');
INSERT INTO `keyword_heat` VALUES ('3', '1501224454559');
INSERT INTO `keyword_heat` VALUES ('3', '1501224455119');
INSERT INTO `keyword_heat` VALUES ('3', '1501224455877');
INSERT INTO `keyword_heat` VALUES ('3', '1501224456578');
INSERT INTO `keyword_heat` VALUES ('3', '1501224458294');
INSERT INTO `keyword_heat` VALUES ('3', '1501224458684');
INSERT INTO `keyword_heat` VALUES ('3', '1501224459301');
INSERT INTO `keyword_heat` VALUES ('3', '1501226207538');
INSERT INTO `keyword_heat` VALUES ('3', '1501226208066');
INSERT INTO `keyword_heat` VALUES ('3', '1501226208619');
INSERT INTO `keyword_heat` VALUES ('3', '1501226210045');
INSERT INTO `keyword_heat` VALUES ('3', '1501226210861');
INSERT INTO `keyword_heat` VALUES ('3', '1501226211441');
INSERT INTO `keyword_heat` VALUES ('3', '1501226211909');
INSERT INTO `keyword_heat` VALUES ('3', '1501226212476');
INSERT INTO `keyword_heat` VALUES ('3', '1501226217085');
INSERT INTO `keyword_heat` VALUES ('3', '1501226219998');
INSERT INTO `keyword_heat` VALUES ('3', '1501226228353');
INSERT INTO `keyword_heat` VALUES ('7', '1501226228357');
INSERT INTO `keyword_heat` VALUES ('1', '1501226228361');
INSERT INTO `keyword_heat` VALUES ('3', '1501226234435');
INSERT INTO `keyword_heat` VALUES ('7', '1501226234543');
INSERT INTO `keyword_heat` VALUES ('1', '1501226234546');
INSERT INTO `keyword_heat` VALUES ('3', '1501226239484');
INSERT INTO `keyword_heat` VALUES ('7', '1501226239489');
INSERT INTO `keyword_heat` VALUES ('1', '1501226239604');
INSERT INTO `keyword_heat` VALUES ('3', '1501226243095');
INSERT INTO `keyword_heat` VALUES ('7', '1501226243099');
INSERT INTO `keyword_heat` VALUES ('1', '1501226243103');
INSERT INTO `keyword_heat` VALUES ('3', '1501226255434');
INSERT INTO `keyword_heat` VALUES ('7', '1501226255441');
INSERT INTO `keyword_heat` VALUES ('1', '1501226255445');
INSERT INTO `keyword_heat` VALUES ('3', '1501226259202');
INSERT INTO `keyword_heat` VALUES ('7', '1501226259205');
INSERT INTO `keyword_heat` VALUES ('1', '1501226259208');
INSERT INTO `keyword_heat` VALUES ('3', '1501226260409');
INSERT INTO `keyword_heat` VALUES ('7', '1501226260412');
INSERT INTO `keyword_heat` VALUES ('1', '1501226260415');
INSERT INTO `keyword_heat` VALUES ('3', '1501226261012');
INSERT INTO `keyword_heat` VALUES ('7', '1501226261017');
INSERT INTO `keyword_heat` VALUES ('1', '1501226261021');
INSERT INTO `keyword_heat` VALUES ('3', '1501226261603');
INSERT INTO `keyword_heat` VALUES ('7', '1501226261606');
INSERT INTO `keyword_heat` VALUES ('1', '1501226261609');
INSERT INTO `keyword_heat` VALUES ('3', '1501226641702');
INSERT INTO `keyword_heat` VALUES ('3', '1501226731736');
INSERT INTO `keyword_heat` VALUES ('3', '1501226792677');
INSERT INTO `keyword_heat` VALUES ('7', '1501226797787');
INSERT INTO `keyword_heat` VALUES ('3', '1501226866328');
INSERT INTO `keyword_heat` VALUES ('7', '1501226866334');
INSERT INTO `keyword_heat` VALUES ('1', '1501226866339');
INSERT INTO `keyword_heat` VALUES ('3', '1501227538339');
INSERT INTO `keyword_heat` VALUES ('7', '1501227538342');
INSERT INTO `keyword_heat` VALUES ('1', '1501227538346');
INSERT INTO `keyword_heat` VALUES ('3', '1501323629652');
INSERT INTO `keyword_heat` VALUES ('7', '1501323629882');
INSERT INTO `keyword_heat` VALUES ('4', '1501323634079');
INSERT INTO `keyword_heat` VALUES ('3', '1501323634084');
INSERT INTO `keyword_heat` VALUES ('2', '1501323634088');
INSERT INTO `keyword_heat` VALUES ('1', '1501323634092');
INSERT INTO `keyword_heat` VALUES ('3', '1501323691490');
INSERT INTO `keyword_heat` VALUES ('2', '1501323691495');
INSERT INTO `keyword_heat` VALUES ('1', '1501323691500');
INSERT INTO `keyword_heat` VALUES ('4', '1501323691528');
INSERT INTO `keyword_heat` VALUES ('7', '1501323693639');
INSERT INTO `keyword_heat` VALUES ('3', '1501323693642');
INSERT INTO `keyword_heat` VALUES ('8', '1501323693683');
INSERT INTO `keyword_heat` VALUES ('4', '1501323698773');
INSERT INTO `keyword_heat` VALUES ('3', '1501323698777');
INSERT INTO `keyword_heat` VALUES ('2', '1501323698781');
INSERT INTO `keyword_heat` VALUES ('1', '1501323698806');
INSERT INTO `keyword_heat` VALUES ('7', '1501323704116');
INSERT INTO `keyword_heat` VALUES ('3', '1501323704119');
INSERT INTO `keyword_heat` VALUES ('8', '1501323704121');
INSERT INTO `keyword_heat` VALUES ('4', '1501323709879');
INSERT INTO `keyword_heat` VALUES ('3', '1501323709882');
INSERT INTO `keyword_heat` VALUES ('2', '1501323709887');
INSERT INTO `keyword_heat` VALUES ('1', '1501323709890');
INSERT INTO `keyword_heat` VALUES ('7', '1501323712351');
INSERT INTO `keyword_heat` VALUES ('3', '1501323712355');
INSERT INTO `keyword_heat` VALUES ('8', '1501323712359');
INSERT INTO `keyword_heat` VALUES ('3', '1501323855666');
INSERT INTO `keyword_heat` VALUES ('8', '1501323855669');
INSERT INTO `keyword_heat` VALUES ('7', '1501323855671');
INSERT INTO `keyword_heat` VALUES ('3', '1501323901654');
INSERT INTO `keyword_heat` VALUES ('8', '1501323901656');
INSERT INTO `keyword_heat` VALUES ('7', '1501323901662');
INSERT INTO `keyword_heat` VALUES ('4', '1501323919658');
INSERT INTO `keyword_heat` VALUES ('3', '1501323919781');
INSERT INTO `keyword_heat` VALUES ('2', '1501323919791');
INSERT INTO `keyword_heat` VALUES ('1', '1501323919801');
INSERT INTO `keyword_heat` VALUES ('4', '1501323919873');
INSERT INTO `keyword_heat` VALUES ('3', '1501323919876');
INSERT INTO `keyword_heat` VALUES ('2', '1501323919977');
INSERT INTO `keyword_heat` VALUES ('1', '1501323919985');
INSERT INTO `keyword_heat` VALUES ('3', '1501324019313');
INSERT INTO `keyword_heat` VALUES ('2', '1501324019318');
INSERT INTO `keyword_heat` VALUES ('1', '1501324019324');
INSERT INTO `keyword_heat` VALUES ('4', '1501324019329');
INSERT INTO `keyword_heat` VALUES ('7', '1501324022010');
INSERT INTO `keyword_heat` VALUES ('3', '1501324022016');
INSERT INTO `keyword_heat` VALUES ('8', '1501324022021');
INSERT INTO `keyword_heat` VALUES ('3', '1501324902763');

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
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

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
INSERT INTO `notification` VALUES ('14', '1', '3', '1', '编号为2的客户接入到会话中', '1500686089048');
INSERT INTO `notification` VALUES ('15', '1', '3', '1', '编号为2的客户接入到会话中', '1500686223340');
INSERT INTO `notification` VALUES ('17', '1', '3', '1', '编号为3的客户接入到会话中', '1500692635914');
INSERT INTO `notification` VALUES ('18', '1', '3', '1', '编号为15的客户接入到会话中', '1500697887598');
INSERT INTO `notification` VALUES ('19', '1', '3', '1', '编号为16的客户接入到会话中', '1500869541904');
INSERT INTO `notification` VALUES ('20', '1', '3', '1', '编号为17的客户接入到会话中', '1500871624915');
INSERT INTO `notification` VALUES ('21', '1', '3', '1', '编号为18的客户接入到会话中', '1501151565724');
INSERT INTO `notification` VALUES ('22', '1', '3', '1', '编号为18的客户接入到会话中', '1501151895960');
INSERT INTO `notification` VALUES ('23', '1', '3', '1', '编号为18的客户接入到会话中', '1501152127393');
INSERT INTO `notification` VALUES ('24', '1', '3', '1', '编号为18的客户接入到会话中', '1501152905295');
INSERT INTO `notification` VALUES ('25', '1', '3', '1', '编号为18的客户接入到会话中', '1501153240162');
INSERT INTO `notification` VALUES ('26', '1', '3', '1', '编号为18的客户接入到会话中', '1501153297330');
INSERT INTO `notification` VALUES ('27', '1', '3', '1', '编号为18的客户接入到会话中', '1501154247848');
INSERT INTO `notification` VALUES ('28', '1', '3', '1', '编号为18的客户接入到会话中', '1501154619496');
INSERT INTO `notification` VALUES ('29', '1', '3', '1', '编号为18的客户接入到会话中', '1501154991876');
INSERT INTO `notification` VALUES ('30', '1', '3', '2', '编号为19的客户接入到会话中', '1501157242403');
INSERT INTO `notification` VALUES ('31', '1', '3', '2', '编号为19的客户接入到会话中', '1501157327375');
INSERT INTO `notification` VALUES ('32', '1', '3', '2', '编号为19的客户接入到会话中', '1501157525134');
INSERT INTO `notification` VALUES ('33', '1', '3', '2', '编号为19的客户接入到会话中', '1501158065186');
INSERT INTO `notification` VALUES ('34', '1', '3', '1', '编号为19的客户接入到会话中', '1501158562028');
INSERT INTO `notification` VALUES ('35', '1', '3', '1', '编号为19的客户接入到会话中', '1501158634945');
INSERT INTO `notification` VALUES ('36', '1', '3', '1', '编号为19的客户接入到会话中', '1501159549464');
INSERT INTO `notification` VALUES ('37', '1', '3', '1', '编号为19的客户接入到会话中', '1501159653997');
INSERT INTO `notification` VALUES ('38', '1', '3', '1', '编号为18的客户接入到会话中', '1501218482863');
INSERT INTO `notification` VALUES ('39', '1', '3', '1', '编号为18的客户接入到会话中', '1501218599264');
INSERT INTO `notification` VALUES ('40', '1', '3', '1', '编号为18的客户接入到会话中', '1501218661038');
INSERT INTO `notification` VALUES ('41', '1', '3', '1', '编号为18的客户接入到会话中', '1501219265187');
INSERT INTO `notification` VALUES ('42', '1', '3', '1', '编号为18的客户接入到会话中', '1501221924900');
INSERT INTO `notification` VALUES ('43', '1', '3', '1', '编号为18的客户接入到会话中', '1501221954280');
INSERT INTO `notification` VALUES ('44', '1', '3', '2', '编号为18的客户接入到会话中', '1501222031757');
INSERT INTO `notification` VALUES ('45', '1', '3', '1', '编号为18的客户接入到会话中', '1501222555860');
INSERT INTO `notification` VALUES ('46', '1', '3', '1', '编号为18的客户接入到会话中', '1501223924885');
INSERT INTO `notification` VALUES ('47', '1', '3', '1', '编号为18的客户接入到会话中', '1501223941903');
INSERT INTO `notification` VALUES ('48', '1', '3', '1', '编号为18的客户接入到会话中', '1501224175697');
INSERT INTO `notification` VALUES ('49', '1', '3', '1', '编号为18的客户接入到会话中', '1501226194257');
INSERT INTO `notification` VALUES ('50', '1', '3', '1', '编号为18的客户接入到会话中', '1501226665048');
INSERT INTO `notification` VALUES ('51', '1', '3', '1', '编号为18的客户接入到会话中', '1501226782723');
INSERT INTO `notification` VALUES ('52', '1', '3', '1', '编号为18的客户接入到会话中', '1501227543637');
INSERT INTO `notification` VALUES ('53', '1', '3', '1', '编号为18的客户接入到会话中', '1501227602558');
INSERT INTO `notification` VALUES ('54', '1', '3', '2', '编号为18的客户接入到会话中', '1501227690712');
INSERT INTO `notification` VALUES ('55', '1', '3', '1', '编号为18的客户接入到会话中', '1501227742971');
INSERT INTO `notification` VALUES ('56', '1', '3', '1', '编号为18的客户接入到会话中', '1501228200123');
INSERT INTO `notification` VALUES ('57', '1', '3', '1', '编号为18的客户接入到会话中', '1501228287452');
INSERT INTO `notification` VALUES ('58', '1', '3', '1', '编号为18的客户接入到会话中', '1501228933570');
INSERT INTO `notification` VALUES ('59', '1', '3', '1', '编号为20的客户接入到会话中', '1501229065733');
INSERT INTO `notification` VALUES ('60', '1', '3', '2', '编号为20的客户接入到会话中', '1501229105777');
INSERT INTO `notification` VALUES ('61', '1', '3', '1', '编号为20的客户接入到会话中', '1501229879133');
INSERT INTO `notification` VALUES ('62', '1', '3', '1', '编号为20的客户接入到会话中', '1501229990171');
INSERT INTO `notification` VALUES ('63', '1', '3', '1', '编号为20的客户接入到会话中', '1501230570462');
INSERT INTO `notification` VALUES ('64', '1', '3', '2', '编号为20的客户接入到会话中', '1501230611169');
INSERT INTO `notification` VALUES ('65', '1', '3', '1', '编号为20的客户接入到会话中', '1501230672003');
INSERT INTO `notification` VALUES ('66', '1', '3', '1', '编号为20的客户接入到会话中', '1501231304633');
INSERT INTO `notification` VALUES ('67', '1', '3', '2', '编号为20的客户接入到会话中', '1501231326827');
INSERT INTO `notification` VALUES ('68', '1', '3', '1', '编号为20的客户接入到会话中', '1501231539655');
INSERT INTO `notification` VALUES ('69', '1', '3', '2', '编号为20的客户接入到会话中', '1501231555764');
INSERT INTO `notification` VALUES ('70', '1', '3', '1', '编号为20的客户接入到会话中', '1501231834052');
INSERT INTO `notification` VALUES ('71', '1', '3', '2', '编号为20的客户接入到会话中', '1501231844215');
INSERT INTO `notification` VALUES ('72', '1', '3', '1', '编号为20的客户接入到会话中', '1501231854711');
INSERT INTO `notification` VALUES ('73', '1', '3', '1', '编号为18的客户接入到会话中', '1501324193770');
INSERT INTO `notification` VALUES ('74', '1', '3', '1', '编号为18的客户接入到会话中', '1501324490508');
INSERT INTO `notification` VALUES ('75', '1', '3', '1', '编号为18的客户接入到会话中', '1501324857739');
INSERT INTO `notification` VALUES ('76', '1', '3', '1', '编号为18的客户接入到会话中', '1501325048041');
INSERT INTO `notification` VALUES ('77', '1', '3', '1', '编号为18的客户接入到会话中', '1501325174767');
INSERT INTO `notification` VALUES ('78', '1', '3', '1', '编号为18的客户接入到会话中', '1501325357320');
INSERT INTO `notification` VALUES ('79', '1', '3', '1', '编号为18的客户接入到会话中', '1501325570369');
INSERT INTO `notification` VALUES ('80', '1', '3', '1', '编号为18的客户接入到会话中', '1501326078507');
INSERT INTO `notification` VALUES ('81', '1', '3', '1', '编号为18的客户接入到会话中', '1501327175069');
INSERT INTO `notification` VALUES ('82', '1', '3', '1', '编号为18的客户接入到会话中', '1501327388305');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_group
-- ----------------------------
INSERT INTO `service_group` VALUES ('1', '售后组');
INSERT INTO `service_group` VALUES ('2', '售前组');

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
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;

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
INSERT INTO `work_time` VALUES ('17', '1', '1500686084837', '1500686089569');
INSERT INTO `work_time` VALUES ('18', '1', '1500686196501', '1500686223803');
INSERT INTO `work_time` VALUES ('19', '1', '1500686699566', '1500686704533');
INSERT INTO `work_time` VALUES ('20', '1', '1500686705009', '1500686712843');
INSERT INTO `work_time` VALUES ('21', '1', '1500692601116', '1500692603626');
INSERT INTO `work_time` VALUES ('22', '1', '1500692604109', '1500692636116');
INSERT INTO `work_time` VALUES ('23', '1', '1500693207715', '1500693335866');
INSERT INTO `work_time` VALUES ('24', '1', '1500693373875', '1500693447194');
INSERT INTO `work_time` VALUES ('25', '1', '1500693507693', '1500693700761');
INSERT INTO `work_time` VALUES ('26', '1', '1500697682160', '1500697688124');
INSERT INTO `work_time` VALUES ('27', '1', '1500697883664', '1500697887755');
INSERT INTO `work_time` VALUES ('28', '1', '1500869505813', '1500869513701');
INSERT INTO `work_time` VALUES ('29', '1', '1500869514069', '1500869542303');
INSERT INTO `work_time` VALUES ('30', '1', '1500871468337', '1500876189927');
INSERT INTO `work_time` VALUES ('31', '1', '1500877970320', '1500877989171');
INSERT INTO `work_time` VALUES ('32', '1', '1500877989551', '1500878128403');
INSERT INTO `work_time` VALUES ('33', '1', '1500878854583', '1500879008689');
INSERT INTO `work_time` VALUES ('34', '1', '1500879014735', '1500879114425');
INSERT INTO `work_time` VALUES ('35', '1', '1500879120025', '1500881831877');
INSERT INTO `work_time` VALUES ('36', '1', '1501151467883', '1501151541286');
INSERT INTO `work_time` VALUES ('37', '1', '1501151479334', '1501151541286');
INSERT INTO `work_time` VALUES ('38', '1', '1501151549018', '1501151853917');
INSERT INTO `work_time` VALUES ('39', '1', '1501151881301', '1501152022917');
INSERT INTO `work_time` VALUES ('40', '1', '1501152028940', '1501152120167');
INSERT INTO `work_time` VALUES ('41', '1', '1501152120548', '1501152894328');
INSERT INTO `work_time` VALUES ('42', '1', '1501152894609', '1501153216220');
INSERT INTO `work_time` VALUES ('43', '1', '1501153216554', '1501153287704');
INSERT INTO `work_time` VALUES ('44', '1', '1501153288041', '1501154185322');
INSERT INTO `work_time` VALUES ('45', '1', '1501154228787', '1501154239798');
INSERT INTO `work_time` VALUES ('46', '1', '1501154240314', '1501154588655');
INSERT INTO `work_time` VALUES ('47', '1', '1501154605948', '1501154981998');
INSERT INTO `work_time` VALUES ('48', '1', '1501154985369', '1501155280058');
INSERT INTO `work_time` VALUES ('49', '1', '1501155280569', '1501155291521');
INSERT INTO `work_time` VALUES ('50', '1', '1501155296775', '1501155865692');
INSERT INTO `work_time` VALUES ('51', '1', '1501155870455', '1501156984981');
INSERT INTO `work_time` VALUES ('52', '1', '1501157040045', '1501157767435');
INSERT INTO `work_time` VALUES ('53', '2', '1501157153169', '1501157514828');
INSERT INTO `work_time` VALUES ('54', '2', '1501157515245', '1501157938803');
INSERT INTO `work_time` VALUES ('55', '1', '1501157778037', '1501157779745');
INSERT INTO `work_time` VALUES ('56', '1', '1501157972593', '1501157984680');
INSERT INTO `work_time` VALUES ('57', '1', '1501157985154', '1501158003961');
INSERT INTO `work_time` VALUES ('58', '1', '1501158007294', '1501158048634');
INSERT INTO `work_time` VALUES ('59', '2', '1501158042388', '1501158265363');
INSERT INTO `work_time` VALUES ('60', '1', '1501158052031', '1501158252731');
INSERT INTO `work_time` VALUES ('61', '1', '1501158256419', '1501158257182');
INSERT INTO `work_time` VALUES ('62', '1', '1501158257646', '1501158294282');
INSERT INTO `work_time` VALUES ('63', '2', '1501158273834', '1501158504852');
INSERT INTO `work_time` VALUES ('64', '1', '1501158379230', '1501158504706');
INSERT INTO `work_time` VALUES ('65', '1', '1501158529185', '1501158539452');
INSERT INTO `work_time` VALUES ('66', '1', '1501158624720', '1501159500841');
INSERT INTO `work_time` VALUES ('67', '1', '1501159539089', '1501159641510');
INSERT INTO `work_time` VALUES ('68', '1', '1501159641947', '1501159649308');
INSERT INTO `work_time` VALUES ('69', '1', '1501159649817', '1501164967646');
INSERT INTO `work_time` VALUES ('70', '1', '1501218440521', '1501218696409');
INSERT INTO `work_time` VALUES ('71', '1', '1501218696881', '1501219222755');
INSERT INTO `work_time` VALUES ('72', '1', '1501219243500', '1501221864996');
INSERT INTO `work_time` VALUES ('73', '1', '1501221874411', '1501221875870');
INSERT INTO `work_time` VALUES ('74', '1', '1501221913733', '1501221984002');
INSERT INTO `work_time` VALUES ('75', '2', '1501222014295', '1501224092576');
INSERT INTO `work_time` VALUES ('76', '1', '1501222346799', '1501222547725');
INSERT INTO `work_time` VALUES ('77', '1', '1501222548198', '1501223912993');
INSERT INTO `work_time` VALUES ('78', '1', '1501223913246', '1501223932255');
INSERT INTO `work_time` VALUES ('79', '1', '1501223932782', '1501223933552');
INSERT INTO `work_time` VALUES ('80', '1', '1501223934020', '1501224088735');
INSERT INTO `work_time` VALUES ('81', '1', '1501224089251', '1501224090017');
INSERT INTO `work_time` VALUES ('82', '1', '1501224090557', '1501224133667');
INSERT INTO `work_time` VALUES ('83', '2', '1501224092785', '1501224111786');
INSERT INTO `work_time` VALUES ('84', '1', '1501224170455', '1501225878739');
INSERT INTO `work_time` VALUES ('85', '1', '1501226188335', '1501226267809');
INSERT INTO `work_time` VALUES ('86', '1', '1501226639244', '1501226649642');
INSERT INTO `work_time` VALUES ('87', '1', '1501226654361', '1501226776072');
INSERT INTO `work_time` VALUES ('88', '1', '1501226776209', '1501226844042');
INSERT INTO `work_time` VALUES ('89', '1', '1501226955701', '1501226957428');
INSERT INTO `work_time` VALUES ('90', '1', '1501227520363', '1501228029400');
INSERT INTO `work_time` VALUES ('91', '2', '1501227682562', '1501228029514');
INSERT INTO `work_time` VALUES ('92', '1', '1501228170128', '1501228273113');
INSERT INTO `work_time` VALUES ('93', '2', '1501228227738', '1501228885801');
INSERT INTO `work_time` VALUES ('94', '1', '1501228273639', '1501228885688');
INSERT INTO `work_time` VALUES ('95', '1', '1501228916261', '1501229529603');
INSERT INTO `work_time` VALUES ('96', '2', '1501229092416', '1501229529516');
INSERT INTO `work_time` VALUES ('97', '2', '1501229867046', '1501229886583');
INSERT INTO `work_time` VALUES ('98', '1', '1501229875999', '1501229882098');
INSERT INTO `work_time` VALUES ('99', '1', '1501229882621', '1501229972324');
INSERT INTO `work_time` VALUES ('100', '2', '1501229886935', '1501230276038');
INSERT INTO `work_time` VALUES ('101', '1', '1501229972742', '1501229973773');
INSERT INTO `work_time` VALUES ('102', '1', '1501229974214', '1501230023693');
INSERT INTO `work_time` VALUES ('103', '1', '1501230024117', '1501230275642');
INSERT INTO `work_time` VALUES ('104', '2', '1501230468559', '1501230593136');
INSERT INTO `work_time` VALUES ('105', '1', '1501230566471', '1501231260542');
INSERT INTO `work_time` VALUES ('106', '2', '1501230593494', '1501231260404');
INSERT INTO `work_time` VALUES ('107', '1', '1501231286341', '1501231289716');
INSERT INTO `work_time` VALUES ('108', '2', '1501231299041', '1501231507173');
INSERT INTO `work_time` VALUES ('109', '1', '1501231528803', '1501231533963');
INSERT INTO `work_time` VALUES ('110', '2', '1501231545186', '1501231802291');
INSERT INTO `work_time` VALUES ('111', '1', '1501231819723', '1501231865056');
INSERT INTO `work_time` VALUES ('112', '2', '1501231829275', '1501231864937');
INSERT INTO `work_time` VALUES ('113', '1', '1501324180278', '1501324241283');
INSERT INTO `work_time` VALUES ('114', '1', '1501324459589', '1501324539964');
INSERT INTO `work_time` VALUES ('115', '1', '1501324814308', '1501325035854');
INSERT INTO `work_time` VALUES ('116', '1', '1501325036171', '1501325066368');
INSERT INTO `work_time` VALUES ('117', '1', '1501325121589', '1501325124210');
INSERT INTO `work_time` VALUES ('118', '1', '1501325124548', '1501325187606');
INSERT INTO `work_time` VALUES ('119', '1', '1501325342095', '1501325557079');
INSERT INTO `work_time` VALUES ('120', '1', '1501325557413', '1501325575118');
INSERT INTO `work_time` VALUES ('121', '1', '1501326046863', '1501326172730');
INSERT INTO `work_time` VALUES ('122', '1', '1501326173067', '1501327123776');
INSERT INTO `work_time` VALUES ('123', '1', '1501327146721', '1501327199029');
INSERT INTO `work_time` VALUES ('124', '1', '1501327199313', '1501327303359');
INSERT INTO `work_time` VALUES ('125', '1', '1501327303662', '1501327324820');
INSERT INTO `work_time` VALUES ('126', '1', '1501327325184', '1501327551966');
