/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50634
Source Host           : 127.0.0.1:3306
Source Database       : scss

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-08-03 22:43:48
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertisement
-- ----------------------------
INSERT INTO `advertisement` VALUES ('1', '黑曜石房地产集团新楼盘盛大开业，请拨打电话 82131234 咨询我们！');
INSERT INTO `advertisement` VALUES ('2', '江滨别墅第三期隆重开业，只要200万起，请拨打电话 82131235 咨询我们！');

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
INSERT INTO `adv_flag` VALUES ('3', '1');
INSERT INTO `adv_flag` VALUES ('5', '1');
INSERT INTO `adv_flag` VALUES ('9', '2');
INSERT INTO `adv_flag` VALUES ('3', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=577 DEFAULT CHARSET=utf8;

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
INSERT INTO `chat_log` VALUES ('318', '127', '22', '0', '0', '你好啊', '1501678664868', '1');
INSERT INTO `chat_log` VALUES ('319', '127', '0', '22', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501678665493', '0');
INSERT INTO `chat_log` VALUES ('320', '127', '22', '0', '0', '<p>转接到人工客服</p>', '1501678706338', '1');
INSERT INTO `chat_log` VALUES ('321', '127', '0', '22', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501678706347', '0');
INSERT INTO `chat_log` VALUES ('322', '127', '22', '0', '0', '转接到人工客服', '1501678721430', '1');
INSERT INTO `chat_log` VALUES ('323', '127', '1', '22', '0', '你好', '1501678751857', '0');
INSERT INTO `chat_log` VALUES ('325', '131', '23', '0', '0', 'nihao', '1501680004468', '1');
INSERT INTO `chat_log` VALUES ('326', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680004482', '0');
INSERT INTO `chat_log` VALUES ('327', '131', '23', '0', '0', '转接到人工客服', '1501680068349', '1');
INSERT INTO `chat_log` VALUES ('328', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680068370', '0');
INSERT INTO `chat_log` VALUES ('329', '131', '23', '0', '0', '转接到人工客服', '1501680071086', '1');
INSERT INTO `chat_log` VALUES ('330', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680071106', '0');
INSERT INTO `chat_log` VALUES ('331', '131', '23', '0', '0', '转接到人工客服', '1501680071832', '1');
INSERT INTO `chat_log` VALUES ('332', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680071855', '0');
INSERT INTO `chat_log` VALUES ('333', '131', '23', '0', '0', '转接到人工客服', '1501680072574', '1');
INSERT INTO `chat_log` VALUES ('334', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680072589', '0');
INSERT INTO `chat_log` VALUES ('335', '131', '23', '0', '0', '转接到人工客服', '1501680072788', '1');
INSERT INTO `chat_log` VALUES ('336', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680072809', '0');
INSERT INTO `chat_log` VALUES ('337', '131', '23', '0', '0', '转接到人工客服', '1501680073113', '1');
INSERT INTO `chat_log` VALUES ('338', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680073130', '0');
INSERT INTO `chat_log` VALUES ('339', '131', '23', '0', '0', '转接到人工客服', '1501680073442', '1');
INSERT INTO `chat_log` VALUES ('340', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680073532', '0');
INSERT INTO `chat_log` VALUES ('341', '131', '23', '0', '0', '转接到人工客服', '1501680073682', '1');
INSERT INTO `chat_log` VALUES ('342', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680073700', '0');
INSERT INTO `chat_log` VALUES ('343', '131', '23', '0', '0', '转接到人工客服', '1501680073894', '1');
INSERT INTO `chat_log` VALUES ('344', '131', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680073915', '0');
INSERT INTO `chat_log` VALUES ('345', '131', '1', '23', '0', '你好', '1501680077634', '0');
INSERT INTO `chat_log` VALUES ('346', '131', '1', '23', '0', '<p>你好？</p><p><br></p>', '1501680099113', '0');
INSERT INTO `chat_log` VALUES ('348', '132', '23', '0', '0', '说到底', '1501680219071', '1');
INSERT INTO `chat_log` VALUES ('349', '132', '0', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680219076', '0');
INSERT INTO `chat_log` VALUES ('350', '132', '23', '0', '0', '转接到人工客服', '1501680220518', '1');
INSERT INTO `chat_log` VALUES ('351', '132', '1', '23', '0', '你好', '1501680220525', '0');
INSERT INTO `chat_log` VALUES ('352', '132', '1', '23', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680220555', '0');
INSERT INTO `chat_log` VALUES ('354', '133', '24', '0', '0', 'DSAD&nbsp;', '1501680880976', '1');
INSERT INTO `chat_log` VALUES ('355', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680880992', '0');
INSERT INTO `chat_log` VALUES ('356', '133', '24', '0', '0', '买房', '1501680885515', '1');
INSERT INTO `chat_log` VALUES ('357', '133', '0', '24', '0', '不知道', '1501680886151', '0');
INSERT INTO `chat_log` VALUES ('358', '133', '24', '0', '0', '涨价', '1501680890796', '1');
INSERT INTO `chat_log` VALUES ('359', '133', '0', '24', '0', '涨价了', '1501680890812', '0');
INSERT INTO `chat_log` VALUES ('360', '133', '24', '0', '0', '房价', '1501680908894', '1');
INSERT INTO `chat_log` VALUES ('361', '133', '0', '24', '0', '涨价了', '1501680908908', '0');
INSERT INTO `chat_log` VALUES ('362', '133', '24', '0', '0', '我要买房价买房，多少钱一平方啊？', '1501680909872', '1');
INSERT INTO `chat_log` VALUES ('363', '133', '0', '24', '0', '不知道', '1501680909894', '0');
INSERT INTO `chat_log` VALUES ('364', '133', '24', '0', '0', '最近的房价涨价了吗', '1501680912115', '1');
INSERT INTO `chat_log` VALUES ('365', '133', '0', '24', '0', '涨价了', '1501680912136', '0');
INSERT INTO `chat_log` VALUES ('366', '133', '24', '0', '0', '我要买房价买房，多少钱一平方啊？', '1501680913125', '1');
INSERT INTO `chat_log` VALUES ('367', '133', '0', '24', '0', '不知道', '1501680913146', '0');
INSERT INTO `chat_log` VALUES ('368', '133', '24', '0', '0', 'sd', '1501680918122', '1');
INSERT INTO `chat_log` VALUES ('369', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680918129', '0');
INSERT INTO `chat_log` VALUES ('370', '133', '24', '0', '0', '转接到人工客服', '1501680919022', '1');
INSERT INTO `chat_log` VALUES ('371', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680919055', '0');
INSERT INTO `chat_log` VALUES ('372', '133', '24', '0', '0', '转接到人工客服', '1501680934595', '1');
INSERT INTO `chat_log` VALUES ('373', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680934612', '0');
INSERT INTO `chat_log` VALUES ('374', '133', '24', '0', '0', '转接到人工客服', '1501680950079', '1');
INSERT INTO `chat_log` VALUES ('375', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680950095', '0');
INSERT INTO `chat_log` VALUES ('376', '133', '24', '0', '0', '转接到人工客服', '1501680955367', '1');
INSERT INTO `chat_log` VALUES ('377', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680955411', '0');
INSERT INTO `chat_log` VALUES ('378', '133', '24', '0', '0', '转接到人工客服', '1501680956282', '1');
INSERT INTO `chat_log` VALUES ('379', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680956297', '0');
INSERT INTO `chat_log` VALUES ('380', '133', '24', '0', '0', '转接到人工客服', '1501680956781', '1');
INSERT INTO `chat_log` VALUES ('381', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680956794', '0');
INSERT INTO `chat_log` VALUES ('382', '133', '24', '0', '0', '转接到人工客服', '1501680957148', '1');
INSERT INTO `chat_log` VALUES ('383', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680957163', '0');
INSERT INTO `chat_log` VALUES ('384', '133', '24', '0', '0', '转接到人工客服', '1501680957373', '1');
INSERT INTO `chat_log` VALUES ('385', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680957389', '0');
INSERT INTO `chat_log` VALUES ('386', '133', '24', '0', '0', '转接到人工客服', '1501680957787', '1');
INSERT INTO `chat_log` VALUES ('387', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680957799', '0');
INSERT INTO `chat_log` VALUES ('388', '133', '24', '0', '0', '转接到人工客服', '1501680958179', '1');
INSERT INTO `chat_log` VALUES ('389', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680958194', '0');
INSERT INTO `chat_log` VALUES ('390', '133', '24', '0', '0', '转接到人工客服', '1501680958449', '1');
INSERT INTO `chat_log` VALUES ('391', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680958466', '0');
INSERT INTO `chat_log` VALUES ('392', '133', '24', '0', '0', '转接到人工客服', '1501680958883', '1');
INSERT INTO `chat_log` VALUES ('393', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680958895', '0');
INSERT INTO `chat_log` VALUES ('394', '133', '24', '0', '0', '转接到人工客服', '1501680959442', '1');
INSERT INTO `chat_log` VALUES ('395', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680959454', '0');
INSERT INTO `chat_log` VALUES ('396', '133', '24', '0', '0', '转接到人工客服', '1501680960318', '1');
INSERT INTO `chat_log` VALUES ('397', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680960331', '0');
INSERT INTO `chat_log` VALUES ('398', '133', '24', '0', '0', '转接到人工客服', '1501680960930', '1');
INSERT INTO `chat_log` VALUES ('399', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680960944', '0');
INSERT INTO `chat_log` VALUES ('400', '133', '24', '0', '0', 'das', '1501680963835', '1');
INSERT INTO `chat_log` VALUES ('401', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680963841', '0');
INSERT INTO `chat_log` VALUES ('402', '133', '24', '0', '0', '转接到人工客服', '1501680966630', '1');
INSERT INTO `chat_log` VALUES ('403', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680966646', '0');
INSERT INTO `chat_log` VALUES ('404', '133', '24', '0', '0', '转接到人工客服', '1501680967418', '1');
INSERT INTO `chat_log` VALUES ('405', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680967432', '0');
INSERT INTO `chat_log` VALUES ('406', '133', '24', '0', '0', '转接到人工客服', '1501680968109', '1');
INSERT INTO `chat_log` VALUES ('407', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680968121', '0');
INSERT INTO `chat_log` VALUES ('408', '133', '24', '0', '0', '转接到人工客服', '1501680968839', '1');
INSERT INTO `chat_log` VALUES ('409', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680968853', '0');
INSERT INTO `chat_log` VALUES ('410', '133', '24', '0', '0', 'asd', '1501680970389', '1');
INSERT INTO `chat_log` VALUES ('411', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501680970394', '0');
INSERT INTO `chat_log` VALUES ('412', '133', '24', '0', '0', '转接到人工客服', '1501681024551', '1');
INSERT INTO `chat_log` VALUES ('413', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681024570', '0');
INSERT INTO `chat_log` VALUES ('414', '133', '24', '0', '0', '转接到人工客服', '1501681028855', '1');
INSERT INTO `chat_log` VALUES ('415', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681028868', '0');
INSERT INTO `chat_log` VALUES ('416', '133', '24', '0', '0', '转接到人工客服', '1501681028976', '1');
INSERT INTO `chat_log` VALUES ('417', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681028995', '0');
INSERT INTO `chat_log` VALUES ('418', '133', '24', '0', '0', '转接到人工客服', '1501681029179', '1');
INSERT INTO `chat_log` VALUES ('419', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681029196', '0');
INSERT INTO `chat_log` VALUES ('420', '133', '24', '0', '0', '转接到人工客服', '1501681029383', '1');
INSERT INTO `chat_log` VALUES ('421', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681029401', '0');
INSERT INTO `chat_log` VALUES ('422', '133', '24', '0', '0', '转接到人工客服', '1501681029561', '1');
INSERT INTO `chat_log` VALUES ('423', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681029581', '0');
INSERT INTO `chat_log` VALUES ('424', '133', '24', '0', '0', '转接到人工客服', '1501681029749', '1');
INSERT INTO `chat_log` VALUES ('425', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681029767', '0');
INSERT INTO `chat_log` VALUES ('426', '133', '24', '0', '0', '转接到人工客服', '1501681029933', '1');
INSERT INTO `chat_log` VALUES ('427', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681029948', '0');
INSERT INTO `chat_log` VALUES ('429', '133', '24', '0', '0', '转接到人工客服', '1501681058074', '1');
INSERT INTO `chat_log` VALUES ('430', '133', '0', '24', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681058088', '0');
INSERT INTO `chat_log` VALUES ('431', '143', '25', '0', '0', 'sad', '1501681337653', '1');
INSERT INTO `chat_log` VALUES ('432', '143', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681337667', '0');
INSERT INTO `chat_log` VALUES ('433', '143', '25', '0', '0', '转接到人工客服', '1501681339758', '1');
INSERT INTO `chat_log` VALUES ('434', '143', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681339781', '0');
INSERT INTO `chat_log` VALUES ('435', '143', '25', '0', '0', '转接到人工客服', '1501681406382', '1');
INSERT INTO `chat_log` VALUES ('436', '143', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681406401', '0');
INSERT INTO `chat_log` VALUES ('437', '143', '25', '0', '0', '转接到人工客服', '1501681434422', '1');
INSERT INTO `chat_log` VALUES ('438', '143', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681434440', '0');
INSERT INTO `chat_log` VALUES ('439', '145', '25', '0', '0', 'sda', '1501681456107', '1');
INSERT INTO `chat_log` VALUES ('440', '145', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681456112', '0');
INSERT INTO `chat_log` VALUES ('441', '145', '25', '0', '0', '转接到人工客服', '1501681458539', '1');
INSERT INTO `chat_log` VALUES ('442', '145', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681458558', '0');
INSERT INTO `chat_log` VALUES ('443', '145', '25', '0', '0', '<p><span>转接到人工客服</span></p>', '1501681512652', '1');
INSERT INTO `chat_log` VALUES ('444', '145', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681512660', '0');
INSERT INTO `chat_log` VALUES ('445', '146', '25', '0', '0', 'sddas', '1501681695507', '1');
INSERT INTO `chat_log` VALUES ('446', '146', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681695528', '0');
INSERT INTO `chat_log` VALUES ('447', '146', '25', '0', '0', '转接到人工客服', '1501681696901', '1');
INSERT INTO `chat_log` VALUES ('448', '146', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501681696994', '0');
INSERT INTO `chat_log` VALUES ('449', '146', '1', '25', '0', '你好', '1501681702480', '0');
INSERT INTO `chat_log` VALUES ('451', '148', '25', '0', '0', '<p>dx</p>', '1501682184640', '1');
INSERT INTO `chat_log` VALUES ('452', '148', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501682184652', '0');
INSERT INTO `chat_log` VALUES ('453', '148', '25', '0', '0', '转接到人工客服', '1501682185771', '1');
INSERT INTO `chat_log` VALUES ('454', '148', '1', '25', '0', '你好', '1501682185779', '0');
INSERT INTO `chat_log` VALUES ('455', '148', '1', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501682185815', '0');
INSERT INTO `chat_log` VALUES ('457', '150', '25', '0', '0', 'sad', '1501682466801', '1');
INSERT INTO `chat_log` VALUES ('458', '150', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501682466814', '0');
INSERT INTO `chat_log` VALUES ('459', '150', '25', '0', '0', '转接到人工客服', '1501682468543', '1');
INSERT INTO `chat_log` VALUES ('460', '150', '1', '25', '0', '你好', '1501682468553', '0');
INSERT INTO `chat_log` VALUES ('461', '150', '1', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501682468580', '0');
INSERT INTO `chat_log` VALUES ('463', '152', '25', '0', '0', 'sdas', '1501682964354', '1');
INSERT INTO `chat_log` VALUES ('464', '152', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501682964369', '0');
INSERT INTO `chat_log` VALUES ('465', '152', '25', '0', '0', '转接到人工客服', '1501682965383', '1');
INSERT INTO `chat_log` VALUES ('466', '156', '26', '0', '0', 'sdas', '1501683041896', '1');
INSERT INTO `chat_log` VALUES ('467', '156', '0', '26', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501683041902', '0');
INSERT INTO `chat_log` VALUES ('468', '156', '26', '0', '0', '转接到人工客服', '1501683043173', '1');
INSERT INTO `chat_log` VALUES ('469', '156', '26', '0', '0', '转接到人工客服', '1501683122892', '1');
INSERT INTO `chat_log` VALUES ('470', '156', '26', '0', '0', '转接到人工客服', '1501683125522', '1');
INSERT INTO `chat_log` VALUES ('471', '156', '26', '0', '0', '转接到人工客服', '1501683125706', '1');
INSERT INTO `chat_log` VALUES ('472', '156', '26', '0', '0', '转接到人工客服', '1501683125873', '1');
INSERT INTO `chat_log` VALUES ('473', '160', '27', '0', '0', 'asd', '1501683221090', '1');
INSERT INTO `chat_log` VALUES ('474', '160', '0', '27', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501683221106', '0');
INSERT INTO `chat_log` VALUES ('475', '160', '27', '0', '0', '转接到人工客服', '1501683228448', '1');
INSERT INTO `chat_log` VALUES ('476', '160', '1', '27', '0', '你好', '1501683249697', '0');
INSERT INTO `chat_log` VALUES ('477', '161', '27', '0', '0', 'da', '1501683387984', '1');
INSERT INTO `chat_log` VALUES ('478', '161', '0', '27', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501683387999', '0');
INSERT INTO `chat_log` VALUES ('479', '161', '27', '0', '0', '转接到人工客服', '1501683411701', '1');
INSERT INTO `chat_log` VALUES ('480', '161', '1', '27', '0', '你好', '1501683411714', '0');
INSERT INTO `chat_log` VALUES ('481', '161', '1', '27', '0', '转接到人工客服', '1501683411753', '0');
INSERT INTO `chat_log` VALUES ('482', '162', '27', '0', '0', '<p>最近房价涨价了吗？</p>', '1501683628504', '1');
INSERT INTO `chat_log` VALUES ('483', '162', '0', '27', '0', '涨价了', '1501683628563', '0');
INSERT INTO `chat_log` VALUES ('484', '162', '27', '0', '0', '我要买房价买房，多少钱一平方啊？', '1501683645496', '1');
INSERT INTO `chat_log` VALUES ('485', '162', '0', '27', '0', '不知道', '1501683645523', '0');
INSERT INTO `chat_log` VALUES ('486', '162', '27', '0', '0', 'ds', '1501683652122', '1');
INSERT INTO `chat_log` VALUES ('487', '162', '0', '27', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501683652129', '0');
INSERT INTO `chat_log` VALUES ('488', '162', '27', '0', '0', '转接到人工客服', '1501683655406', '1');
INSERT INTO `chat_log` VALUES ('489', '162', '1', '27', '0', '你好', '1501683655414', '0');
INSERT INTO `chat_log` VALUES ('490', '162', '1', '27', '0', '正在为您转接人工客服，请稍后……', '1501683655439', '0');
INSERT INTO `chat_log` VALUES ('491', '162', '1', '27', '0', 'dasdasd', '1501683661348', '0');
INSERT INTO `chat_log` VALUES ('492', '168', '25', '0', '0', 'dfds', '1501724655539', '1');
INSERT INTO `chat_log` VALUES ('493', '168', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501724656631', '0');
INSERT INTO `chat_log` VALUES ('494', '168', '25', '0', '0', '转接到人工客服', '1501724659548', '1');
INSERT INTO `chat_log` VALUES ('495', '168', '0', '25', '0', '正在为您转接人工客服，请稍后……', '1501724661486', '0');
INSERT INTO `chat_log` VALUES ('496', '168', '1', '25', '0', '你好', '1501724668618', '0');
INSERT INTO `chat_log` VALUES ('498', '169', '25', '0', '0', 'sadsadsa', '1501725114894', '1');
INSERT INTO `chat_log` VALUES ('499', '169', '0', '25', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501725114911', '0');
INSERT INTO `chat_log` VALUES ('500', '169', '25', '0', '0', '转接到人工客服', '1501725115987', '1');
INSERT INTO `chat_log` VALUES ('501', '169', '1', '25', '0', '你好', '1501725115996', '0');
INSERT INTO `chat_log` VALUES ('502', '169', '1', '25', '0', '正在为您转接人工客服，请稍后……', '1501725116041', '0');
INSERT INTO `chat_log` VALUES ('504', '170', '28', '0', '0', 'sdasd', '1501725199264', '1');
INSERT INTO `chat_log` VALUES ('505', '170', '0', '28', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501725199273', '0');
INSERT INTO `chat_log` VALUES ('506', '170', '28', '0', '0', '转接到人工客服', '1501725200951', '1');
INSERT INTO `chat_log` VALUES ('507', '170', '0', '28', '0', '正在为您转接人工客服，请稍后……', '1501725200976', '0');
INSERT INTO `chat_log` VALUES ('508', '170', '1', '28', '0', '你好', '1501725203958', '0');
INSERT INTO `chat_log` VALUES ('510', '171', '28', '0', '0', '<p>涨价</p>', '1501725261148', '1');
INSERT INTO `chat_log` VALUES ('511', '171', '0', '28', '0', '涨价了', '1501725261158', '0');
INSERT INTO `chat_log` VALUES ('512', '171', '28', '0', '0', '房子涨价了吗', '1501725278415', '1');
INSERT INTO `chat_log` VALUES ('513', '171', '0', '28', '0', '涨价了', '1501725278424', '0');
INSERT INTO `chat_log` VALUES ('514', '171', '28', '0', '0', '你知道房子 涨价了吗', '1501725295824', '1');
INSERT INTO `chat_log` VALUES ('515', '171', '0', '28', '0', '涨价了', '1501725295832', '0');
INSERT INTO `chat_log` VALUES ('516', '173', '28', '0', '0', 'sdad', '1501726875092', '1');
INSERT INTO `chat_log` VALUES ('517', '173', '0', '28', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501726875103', '0');
INSERT INTO `chat_log` VALUES ('518', '173', '28', '0', '0', '转接到人工客服', '1501726877146', '1');
INSERT INTO `chat_log` VALUES ('519', '173', '1', '28', '0', '你好', '1501726877155', '0');
INSERT INTO `chat_log` VALUES ('520', '173', '1', '28', '0', '正在为您转接人工客服，请稍后……', '1501726877184', '0');
INSERT INTO `chat_log` VALUES ('521', '173', '28', '1', '0', 'sdada', '1501726891964', '1');
INSERT INTO `chat_log` VALUES ('522', '173', '1', '28', '0', 'sadas', '1501726895534', '0');
INSERT INTO `chat_log` VALUES ('523', '173', '28', '1', '0', 'sadas', '1501726901384', '1');
INSERT INTO `chat_log` VALUES ('524', '173', '28', '1', '0', 'dsad', '1501726940887', '1');
INSERT INTO `chat_log` VALUES ('525', '174', '28', '0', '0', 'sadsa', '1501727054841', '1');
INSERT INTO `chat_log` VALUES ('526', '174', '0', '28', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501727054873', '0');
INSERT INTO `chat_log` VALUES ('527', '174', '28', '0', '0', '转接到人工客服', '1501727056252', '1');
INSERT INTO `chat_log` VALUES ('528', '174', '1', '28', '0', '你好', '1501727056260', '0');
INSERT INTO `chat_log` VALUES ('529', '174', '1', '28', '0', '正在为您转接人工客服，请稍后……', '1501727056329', '0');
INSERT INTO `chat_log` VALUES ('530', '174', '28', '1', '0', 'sads', '1501727086835', '1');
INSERT INTO `chat_log` VALUES ('531', '175', '28', '0', '0', 'sda', '1501727095948', '1');
INSERT INTO `chat_log` VALUES ('532', '175', '0', '28', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501727095954', '0');
INSERT INTO `chat_log` VALUES ('533', '175', '28', '0', '0', '转接到人工客服', '1501727096894', '1');
INSERT INTO `chat_log` VALUES ('534', '175', '1', '28', '0', '你好', '1501727096899', '0');
INSERT INTO `chat_log` VALUES ('535', '175', '1', '28', '0', '正在为您转接人工客服，请稍后……', '1501727096926', '0');
INSERT INTO `chat_log` VALUES ('536', '175', '28', '1', '0', 'sdasa', '1501727216270', '1');
INSERT INTO `chat_log` VALUES ('537', '175', '28', '1', '0', 'sddad', '1501727250736', '1');
INSERT INTO `chat_log` VALUES ('538', '175', '28', '1', '0', 'sdaas', '1501727267373', '1');
INSERT INTO `chat_log` VALUES ('539', '176', '28', '0', '0', 'dsasa', '1501728459440', '1');
INSERT INTO `chat_log` VALUES ('540', '176', '0', '28', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501728459451', '0');
INSERT INTO `chat_log` VALUES ('541', '176', '28', '0', '0', '转接到人工客服', '1501728460831', '1');
INSERT INTO `chat_log` VALUES ('542', '176', '1', '28', '0', '你好', '1501728460842', '0');
INSERT INTO `chat_log` VALUES ('543', '176', '1', '28', '0', '正在为您转接人工客服，请稍后……', '1501728460879', '0');
INSERT INTO `chat_log` VALUES ('544', '177', '28', '0', '0', 'dsas', '1501728933021', '1');
INSERT INTO `chat_log` VALUES ('545', '177', '0', '28', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501728933030', '0');
INSERT INTO `chat_log` VALUES ('546', '177', '28', '0', '0', '转接到人工客服', '1501728934166', '1');
INSERT INTO `chat_log` VALUES ('547', '177', '1', '28', '0', '你好', '1501728934176', '0');
INSERT INTO `chat_log` VALUES ('548', '177', '1', '28', '0', '正在为您转接人工客服，请稍后……', '1501728934227', '0');
INSERT INTO `chat_log` VALUES ('549', '178', '28', '0', '0', 'hgyjg', '1501729625827', '1');
INSERT INTO `chat_log` VALUES ('550', '178', '0', '28', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501729625838', '0');
INSERT INTO `chat_log` VALUES ('551', '178', '28', '0', '0', '转接到人工客服', '1501729638490', '1');
INSERT INTO `chat_log` VALUES ('552', '178', '0', '28', '0', '正在为您转接人工客服，请稍后……', '1501729638501', '0');
INSERT INTO `chat_log` VALUES ('553', '178', '1', '28', '0', '你好', '1501729638517', '0');
INSERT INTO `chat_log` VALUES ('554', '178', '1', '28', '0', 'sadsa', '1501729710980', '0');
INSERT INTO `chat_log` VALUES ('555', '178', '28', '1', '0', 'sadsa', '1501729715178', '1');
INSERT INTO `chat_log` VALUES ('556', '178', '28', '1', '0', 'sad', '1501729774315', '1');
INSERT INTO `chat_log` VALUES ('557', '178', '28', '1', '0', 'dsadsa', '1501729782531', '1');
INSERT INTO `chat_log` VALUES ('558', '178', '28', '1', '0', 'sdasd', '1501729784553', '1');
INSERT INTO `chat_log` VALUES ('559', '178', '28', '1', '0', 'dsadsad', '1501729786387', '1');
INSERT INTO `chat_log` VALUES ('560', '179', '29', '0', '0', 'dsdsa', '1501733321058', '1');
INSERT INTO `chat_log` VALUES ('561', '179', '0', '29', '0', '不好意思喔，小Robot没有听懂您说的话，如果有需要的话您可以转接人工客服喔！', '1501733321069', '0');
INSERT INTO `chat_log` VALUES ('562', '179', '29', '0', '0', '转接到人工客服', '1501733322363', '1');
INSERT INTO `chat_log` VALUES ('563', '179', '0', '29', '0', '正在为您转接人工客服，请稍后……', '1501733322374', '0');
INSERT INTO `chat_log` VALUES ('564', '179', '1', '29', '0', '你好', '1501733326175', '0');
INSERT INTO `chat_log` VALUES ('565', '179', '29', '1', '0', 'dsad', '1501733335763', '1');
INSERT INTO `chat_log` VALUES ('566', '179', '1', '29', '0', '你好', '1501733346166', '0');
INSERT INTO `chat_log` VALUES ('567', '179', '29', '1', '0', 'sadas', '1501733361024', '1');
INSERT INTO `chat_log` VALUES ('568', '179', '29', '1', '0', 'sdsa', '1501733378266', '1');
INSERT INTO `chat_log` VALUES ('569', '180', '25', '0', '0', '<p class=\"MsoNormal\"><span>我想知道一下你们公司的</span><span><font face=\"宋体\">楼盘</font> <font face=\"宋体\">信息</font></span><span>？</span></p>', '1501742653979', '1');
INSERT INTO `chat_log` VALUES ('570', '180', '0', '25', '0', '您好，我们是黑曜石售楼部的，我们这里有不限购不限贷的，70年产权的多层电梯洋房，自住投资都可以，低至8000元/平方喔。', '1501742654457', '0');
INSERT INTO `chat_log` VALUES ('571', '180', '25', '0', '0', '我们公司楼盘的具体位置？', '1501742663790', '1');
INSERT INTO `chat_log` VALUES ('572', '180', '0', '25', '0', '在北京南路与绕城南路交叉口，这个位置很好找的，地段很好！', '1501742663869', '0');
INSERT INTO `chat_log` VALUES ('573', '180', '25', '0', '0', '<p class=\"MsoNormal\"><span>我</span><span>想</span><span><font face=\"宋体\">买房，</font> <font face=\"宋体\">首付</font></span><span>多少啊？</span></p>', '1501742677466', '1');
INSERT INTO `chat_log` VALUES ('574', '180', '0', '25', '0', '不同户型的首付不同的哦，我们的首付最低十几万起喔，很实惠。', '1501742677493', '0');
INSERT INTO `chat_log` VALUES ('575', '180', '25', '0', '0', '<p class=\"MsoNormal\"><span>你们楼盘都有哪些</span><span>户型</span><span>啊，有</span><span>大户型</span><span>的吗</span></p>', '1501742688698', '1');
INSERT INTO `chat_log` VALUES ('576', '180', '0', '25', '0', '我们目前在售户型是78方（小户型）、120方（中户型）、135（大户型）方的都有，户型很方正的。', '1501742688717', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

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
INSERT INTO `client` VALUES ('18', '王先生', '江苏', '821321@163.com', '89320102', '1');
INSERT INTO `client` VALUES ('19', '张先生', '杭州', 'zhangsd@163.com', '82321991', '1');
INSERT INTO `client` VALUES ('20', '李女士', '江西', 'lylyly@qq.com', '67673456', '2');
INSERT INTO `client` VALUES ('21', null, null, null, null, '0');
INSERT INTO `client` VALUES ('22', null, null, null, null, '0');
INSERT INTO `client` VALUES ('23', null, null, null, null, '0');
INSERT INTO `client` VALUES ('24', null, null, null, null, '0');
INSERT INTO `client` VALUES ('25', null, null, null, null, '0');
INSERT INTO `client` VALUES ('26', null, null, null, null, '0');
INSERT INTO `client` VALUES ('27', null, null, null, null, '0');
INSERT INTO `client` VALUES ('28', null, null, null, null, '0');
INSERT INTO `client` VALUES ('29', null, null, null, null, '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of common_language
-- ----------------------------
INSERT INTO `common_language` VALUES ('1', null, '你好，请问我能帮助您什么？', '11');
INSERT INTO `common_language` VALUES ('2', null, '您好!很高兴为您服务，有什么可以为您效劳的。 ', '13');
INSERT INTO `common_language` VALUES ('3', null, '亲，您说的我的确无法办到。希望我下次能帮到您。', '12');
INSERT INTO `common_language` VALUES ('4', null, '呵呵，这真的让我很为难，我请示一下组长，看能不能给您一些折扣，不过估计有点难，亲，请您稍等! ', '1');
INSERT INTO `common_language` VALUES ('5', null, '好的！谢谢咨询！', '1');
INSERT INTO `common_language` VALUES ('6', null, '我们的楼盘在北京南路与绕城南路交叉口，这个位置很好找的，地段很好！', '1');
INSERT INTO `common_language` VALUES ('7', null, '再见', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of conversation
-- ----------------------------
INSERT INTO `conversation` VALUES ('1', '1', '1', '1499443200000', '1501755003778', '3');
INSERT INTO `conversation` VALUES ('2', '1', '0', '1499011200000', '1499013200000', '4');
INSERT INTO `conversation` VALUES ('3', '1', '1', '1499616000000', '1501755003778', '5');
INSERT INTO `conversation` VALUES ('4', '1', '0', '1499991300000', '1499991500000', '4');
INSERT INTO `conversation` VALUES ('5', '1', '0', '1499991000000', '1500168287499', null);
INSERT INTO `conversation` VALUES ('6', '2', '0', '1500518098602', null, null);
INSERT INTO `conversation` VALUES ('7', '2', '1', '1500518362645', '1501755003778', null);
INSERT INTO `conversation` VALUES ('8', '2', '0', '1500523956671', null, null);
INSERT INTO `conversation` VALUES ('9', '2', '1', '1500524087661', '1501755003778', null);
INSERT INTO `conversation` VALUES ('10', '2', '1', '1500524619044', '1501755003778', null);
INSERT INTO `conversation` VALUES ('11', '2', '0', '1500524802896', null, null);
INSERT INTO `conversation` VALUES ('12', '2', '0', '1500524813836', null, null);
INSERT INTO `conversation` VALUES ('13', '2', '0', '1500524976193', null, null);
INSERT INTO `conversation` VALUES ('14', '2', '0', '1500525268658', null, null);
INSERT INTO `conversation` VALUES ('15', '2', '0', '1500525561131', null, '4');
INSERT INTO `conversation` VALUES ('16', '2', '1', '1500533728186', '1501755003778', null);
INSERT INTO `conversation` VALUES ('17', '2', '1', '1500534756465', '1501755003778', null);
INSERT INTO `conversation` VALUES ('18', '2', '1', '1500534990824', '1501755003778', null);
INSERT INTO `conversation` VALUES ('19', '2', '1', '1500535064139', '1501755003778', null);
INSERT INTO `conversation` VALUES ('20', '2', '1', '1500535197136', '1501755003778', null);
INSERT INTO `conversation` VALUES ('21', '2', '1', '1500535440903', '1501755003778', null);
INSERT INTO `conversation` VALUES ('22', '2', '1', '1500535665659', '1501755003778', null);
INSERT INTO `conversation` VALUES ('23', '2', '1', '1500685496105', '1501755003778', null);
INSERT INTO `conversation` VALUES ('24', '2', '1', '1500685693702', '1501755003778', null);
INSERT INTO `conversation` VALUES ('25', '2', '1', '1500685884974', '1501755003778', null);
INSERT INTO `conversation` VALUES ('26', '2', '1', '1500686086898', '1501755003778', null);
INSERT INTO `conversation` VALUES ('27', '2', '1', '1500686219648', '1501755003778', null);
INSERT INTO `conversation` VALUES ('28', '2', '0', '1500686695117', null, null);
INSERT INTO `conversation` VALUES ('29', '3', '0', '1500692316501', null, null);
INSERT INTO `conversation` VALUES ('30', '3', '1', '1500692614959', '1501755003778', null);
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
INSERT INTO `conversation` VALUES ('65', '19', '1', '1501158555605', '1501755003778', null);
INSERT INTO `conversation` VALUES ('66', '18', '1', '1501158632106', '1501755003778', null);
INSERT INTO `conversation` VALUES ('67', '18', '1', '1501159545755', '1501755003778', null);
INSERT INTO `conversation` VALUES ('68', '18', '1', '1501159651788', '1501755003778', null);
INSERT INTO `conversation` VALUES ('69', '18', '0', '1501218452825', '1501218455327', null);
INSERT INTO `conversation` VALUES ('70', '18', '1', '1501218455715', '1501755003778', null);
INSERT INTO `conversation` VALUES ('71', '18', '1', '1501218650020', '1501755003778', null);
INSERT INTO `conversation` VALUES ('72', '18', '1', '1501219258799', '1501755003778', null);
INSERT INTO `conversation` VALUES ('73', '18', '2', '1501221904813', '1501231865006', null);
INSERT INTO `conversation` VALUES ('74', '18', '1', '1501222553686', '1501755003778', null);
INSERT INTO `conversation` VALUES ('75', '18', '1', '1501223918589', '1501755003778', null);
INSERT INTO `conversation` VALUES ('76', '18', '1', '1501223940345', '1501755003778', null);
INSERT INTO `conversation` VALUES ('77', '18', '0', '1501224114568', '1501224173193', null);
INSERT INTO `conversation` VALUES ('78', '18', '1', '1501224173630', '1501755003778', null);
INSERT INTO `conversation` VALUES ('79', '18', '1', '1501226190655', '1501755003778', null);
INSERT INTO `conversation` VALUES ('80', '18', '1', '1501226658447', '1501755003778', null);
INSERT INTO `conversation` VALUES ('81', '18', '1', '1501226780999', '1501755003778', null);
INSERT INTO `conversation` VALUES ('82', '18', '0', '1501226946852', '1501227116388', null);
INSERT INTO `conversation` VALUES ('83', '18', '1', '1501227531313', '1501755003778', null);
INSERT INTO `conversation` VALUES ('84', '18', '1', '1501228175019', '1501755003778', null);
INSERT INTO `conversation` VALUES ('85', '18', '1', '1501228284399', '1501755003778', null);
INSERT INTO `conversation` VALUES ('86', '18', '1', '1501228920648', '1501755003778', null);
INSERT INTO `conversation` VALUES ('87', '20', '2', '1501228967471', '1501231865006', null);
INSERT INTO `conversation` VALUES ('88', '20', '1', '1501229852203', '1501755003778', null);
INSERT INTO `conversation` VALUES ('89', '20', '1', '1501229988209', '1501755003778', null);
INSERT INTO `conversation` VALUES ('90', '20', '0', '1501230447981', '1501230552623', null);
INSERT INTO `conversation` VALUES ('91', '20', '1', '1501230552949', '1501755003778', null);
INSERT INTO `conversation` VALUES ('92', '20', '1', '1501230605056', '1501755003778', null);
INSERT INTO `conversation` VALUES ('93', '20', '2', '1501231293206', '1501231865006', null);
INSERT INTO `conversation` VALUES ('94', '20', '2', '1501231537499', '1501231865006', null);
INSERT INTO `conversation` VALUES ('95', '20', '1', '1501231832074', '1501755003778', null);
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
INSERT INTO `conversation` VALUES ('110', '18', '1', '1501324011563', '1501755003778', null);
INSERT INTO `conversation` VALUES ('111', '18', '1', '1501324452071', '1501755003778', null);
INSERT INTO `conversation` VALUES ('112', '18', '1', '1501324809523', '1501755003778', null);
INSERT INTO `conversation` VALUES ('113', '18', '1', '1501325039435', '1501755003778', null);
INSERT INTO `conversation` VALUES ('114', '18', '1', '1501325126666', '1501755003778', null);
INSERT INTO `conversation` VALUES ('115', '18', '1', '1501325345674', '1501755003778', null);
INSERT INTO `conversation` VALUES ('116', '18', '1', '1501325560694', '1501755003778', null);
INSERT INTO `conversation` VALUES ('117', '18', '1', '1501326050516', '1501755003778', null);
INSERT INTO `conversation` VALUES ('118', '18', '1', '1501327149559', '1501755003778', null);
INSERT INTO `conversation` VALUES ('119', '18', '1', '1501327327073', '1501755003778', null);
INSERT INTO `conversation` VALUES ('120', '21', '0', '1501676030727', '1501676813427', null);
INSERT INTO `conversation` VALUES ('121', '21', '0', '1501676814064', '1501676840843', null);
INSERT INTO `conversation` VALUES ('122', '21', '0', '1501676840941', '1501676931715', null);
INSERT INTO `conversation` VALUES ('123', '21', '0', '1501676931827', '1501676988276', null);
INSERT INTO `conversation` VALUES ('124', '21', '0', '1501676949103', '1501676950909', null);
INSERT INTO `conversation` VALUES ('125', '18', '0', '1501677769204', '1501677848509', null);
INSERT INTO `conversation` VALUES ('126', '18', '0', '1501677848654', '1501677897196', null);
INSERT INTO `conversation` VALUES ('127', '22', '1', '1501678652582', '1501755003778', null);
INSERT INTO `conversation` VALUES ('128', '18', '0', '1501679552539', '1501679591346', null);
INSERT INTO `conversation` VALUES ('129', '18', '0', '1501679591615', '1501679748714', null);
INSERT INTO `conversation` VALUES ('130', '23', '0', '1501679826469', '1501679991561', null);
INSERT INTO `conversation` VALUES ('131', '23', '1', '1501679991998', '1501755003778', null);
INSERT INTO `conversation` VALUES ('132', '23', '1', '1501680208999', '1501755003778', null);
INSERT INTO `conversation` VALUES ('133', '24', '0', '1501680852125', '1501681093389', null);
INSERT INTO `conversation` VALUES ('134', '18', '0', '1501680884292', '1501680924016', null);
INSERT INTO `conversation` VALUES ('135', '18', '0', '1501680924247', '1501681024865', null);
INSERT INTO `conversation` VALUES ('136', '18', '0', '1501681025090', '1501681073579', null);
INSERT INTO `conversation` VALUES ('137', '18', '0', '1501681073835', '1501681093488', null);
INSERT INTO `conversation` VALUES ('138', '18', '0', '1501681126858', '1501681160730', null);
INSERT INTO `conversation` VALUES ('139', '18', '0', '1501681160891', '1501681186144', null);
INSERT INTO `conversation` VALUES ('140', '18', '0', '1501681186341', '1501681188145', null);
INSERT INTO `conversation` VALUES ('141', '18', '0', '1501681188344', '1501681231122', null);
INSERT INTO `conversation` VALUES ('142', '18', '0', '1501681231359', '1501681297277', null);
INSERT INTO `conversation` VALUES ('143', '25', '0', '1501681312955', '1501681439673', null);
INSERT INTO `conversation` VALUES ('144', '25', '0', '1501681439942', '1501681448653', null);
INSERT INTO `conversation` VALUES ('145', '25', '0', '1501681448885', '1501681449432', null);
INSERT INTO `conversation` VALUES ('146', '25', '1', '1501681662531', '1501755003778', null);
INSERT INTO `conversation` VALUES ('147', '25', '0', '1501682152494', '1501682157807', null);
INSERT INTO `conversation` VALUES ('148', '25', '1', '1501682174985', '1501755003778', null);
INSERT INTO `conversation` VALUES ('149', '18', '0', '1501682365233', '1501682899190', null);
INSERT INTO `conversation` VALUES ('150', '25', '1', '1501682384086', '1501755003778', null);
INSERT INTO `conversation` VALUES ('151', '18', '0', '1501682899432', '1501682936923', null);
INSERT INTO `conversation` VALUES ('152', '25', '0', '1501682952701', '1501683010401', null);
INSERT INTO `conversation` VALUES ('153', '18', '0', '1501682954348', '1501683006897', null);
INSERT INTO `conversation` VALUES ('154', '18', '0', '1501683007234', '1501683056334', null);
INSERT INTO `conversation` VALUES ('155', '25', '0', '1501683010579', '1501683012960', null);
INSERT INTO `conversation` VALUES ('156', '26', '0', '1501683036385', '1501683143315', null);
INSERT INTO `conversation` VALUES ('157', '18', '0', '1501683056738', '1501683081584', null);
INSERT INTO `conversation` VALUES ('158', '18', '0', '1501683081948', '1501683105891', null);
INSERT INTO `conversation` VALUES ('159', '18', '0', '1501683106406', '1501683143251', null);
INSERT INTO `conversation` VALUES ('160', '27', '1', '1501683210626', '1501755003778', null);
INSERT INTO `conversation` VALUES ('161', '27', '1', '1501683313958', '1501755003778', null);
INSERT INTO `conversation` VALUES ('162', '27', '1', '1501683609594', '1501755003778', null);
INSERT INTO `conversation` VALUES ('163', '18', '0', '1501683696810', '1501683762399', null);
INSERT INTO `conversation` VALUES ('164', '18', '0', '1501683762719', '1501684124976', null);
INSERT INTO `conversation` VALUES ('165', '18', '0', '1501684125420', '1501684163127', null);
INSERT INTO `conversation` VALUES ('166', '18', '0', '1501684163458', '1501684206476', null);
INSERT INTO `conversation` VALUES ('167', '18', '0', '1501684206821', '1501684306213', '4');
INSERT INTO `conversation` VALUES ('168', '25', '1', '1501724634172', '1501755003778', null);
INSERT INTO `conversation` VALUES ('169', '25', '1', '1501725072271', '1501755003778', null);
INSERT INTO `conversation` VALUES ('170', '28', '1', '1501725185917', '1501755003778', null);
INSERT INTO `conversation` VALUES ('171', '28', '0', '1501725246796', '1501726750982', null);
INSERT INTO `conversation` VALUES ('172', '28', '0', '1501726765243', '1501726867620', null);
INSERT INTO `conversation` VALUES ('173', '28', '1', '1501726867715', '1501755003778', null);
INSERT INTO `conversation` VALUES ('174', '28', '1', '1501726996219', '1501755003778', null);
INSERT INTO `conversation` VALUES ('175', '28', '1', '1501727090039', '1501755003778', null);
INSERT INTO `conversation` VALUES ('176', '28', '1', '1501728452892', '1501755003778', null);
INSERT INTO `conversation` VALUES ('177', '28', '1', '1501728919628', '1501755003778', null);
INSERT INTO `conversation` VALUES ('178', '28', '1', '1501729608949', '1501755003778', null);
INSERT INTO `conversation` VALUES ('179', '29', '1', '1501733295890', '1501755003778', '5');
INSERT INTO `conversation` VALUES ('180', '25', '0', '1501742622899', '1501742634334', null);
INSERT INTO `conversation` VALUES ('181', '25', '0', '1501742627727', '1501742634121', null);
INSERT INTO `conversation` VALUES ('182', '25', '0', '1501744245243', '1501755004013', null);

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
INSERT INTO `customer_service` VALUES ('0', '智能客服', '1', '智能客服', 'HYS1000', 'Hello，我是智能客服，请问有什么可以帮您得吗', '0', '888888');
INSERT INTO `customer_service` VALUES ('1', '李四', '2', '小李', 'HYS1001', 'Hello，我是客服小李，请问有什么可以帮您得吗', '0', '888888');
INSERT INTO `customer_service` VALUES ('2', '张三', '1', '小张', 'HYS1002', 'Hello，我是客服小张，请问有什么可以帮您得吗', '0', '888888');
INSERT INTO `customer_service` VALUES ('3', '王五', '4', '小王', 'HYS1003', 'Hello，我是客服小王，请问有什么可以帮您得吗', '0', '888888');

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flag
-- ----------------------------
INSERT INTO `flag` VALUES ('4', '中户型');
INSERT INTO `flag` VALUES ('6', '全款');
INSERT INTO `flag` VALUES ('9', '别墅');
INSERT INTO `flag` VALUES ('2', '大户型');
INSERT INTO `flag` VALUES ('1', '小户型');
INSERT INTO `flag` VALUES ('3', '想买房');
INSERT INTO `flag` VALUES ('5', '要买房');

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
INSERT INTO `group_word` VALUES ('1', '质量问题');
INSERT INTO `group_word` VALUES ('1', '退钱');
INSERT INTO `group_word` VALUES ('2', '想买房');
INSERT INTO `group_word` VALUES ('2', '要买房');
INSERT INTO `group_word` VALUES ('2', '买房');
INSERT INTO `group_word` VALUES ('1', '买了房');
INSERT INTO `group_word` VALUES ('3', '咨询');
INSERT INTO `group_word` VALUES ('4', '资金');
INSERT INTO `group_word` VALUES ('4', '钱');
INSERT INTO `group_word` VALUES ('4', '按揭');
INSERT INTO `group_word` VALUES ('4', '贷款');
INSERT INTO `group_word` VALUES ('4', '首付');
INSERT INTO `group_word` VALUES ('5', '水电');
INSERT INTO `group_word` VALUES ('5', '安全');

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
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;

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
INSERT INTO `join_up` VALUES ('118', '1', '21', '1501676030693', '4207701004');
INSERT INTO `join_up` VALUES ('119', '1', '21', '1501676813435', '4207701004');
INSERT INTO `join_up` VALUES ('120', '1', '21', '1501676840936', '4207701004');
INSERT INTO `join_up` VALUES ('121', '1', '21', '1501676931817', '4207701004');
INSERT INTO `join_up` VALUES ('122', '1', '21', '1501676949098', '4207701004');
INSERT INTO `join_up` VALUES ('123', '1', '18', '1501677769145', '1378112422');
INSERT INTO `join_up` VALUES ('124', '1', '18', '1501677848633', '1378112422');
INSERT INTO `join_up` VALUES ('125', '1', '22', '1501678652576', '2496534588');
INSERT INTO `join_up` VALUES ('126', '1', '18', '1501679552479', '1378112422');
INSERT INTO `join_up` VALUES ('127', '1', '18', '1501679591544', '1378112422');
INSERT INTO `join_up` VALUES ('128', '1', '23', '1501679826251', '7238421777');
INSERT INTO `join_up` VALUES ('129', '1', '23', '1501679991974', '7238421777');
INSERT INTO `join_up` VALUES ('130', '1', '23', '1501680208993', '7238421777');
INSERT INTO `join_up` VALUES ('131', '1', '24', '1501680852085', '9787791840');
INSERT INTO `join_up` VALUES ('132', '1', '18', '1501680884268', '1378112422');
INSERT INTO `join_up` VALUES ('133', '1', '18', '1501680924234', '1378112422');
INSERT INTO `join_up` VALUES ('134', '1', '18', '1501681025085', '1378112422');
INSERT INTO `join_up` VALUES ('135', '1', '18', '1501681073814', '1378112422');
INSERT INTO `join_up` VALUES ('136', '1', '18', '1501681126806', '1378112422');
INSERT INTO `join_up` VALUES ('137', '1', '18', '1501681160873', '1378112422');
INSERT INTO `join_up` VALUES ('138', '1', '18', '1501681186328', '1378112422');
INSERT INTO `join_up` VALUES ('139', '1', '18', '1501681188326', '1378112422');
INSERT INTO `join_up` VALUES ('140', '1', '18', '1501681231349', '1378112422');
INSERT INTO `join_up` VALUES ('141', '1', '25', '1501681312928', '7880625495');
INSERT INTO `join_up` VALUES ('142', '1', '25', '1501681439928', '7880625495');
INSERT INTO `join_up` VALUES ('143', '1', '25', '1501681448861', '7880625495');
INSERT INTO `join_up` VALUES ('144', '1', '25', '1501681662453', '7880625495');
INSERT INTO `join_up` VALUES ('145', '1', '25', '1501682152439', '7880625495');
INSERT INTO `join_up` VALUES ('146', '1', '25', '1501682174977', '7880625495');
INSERT INTO `join_up` VALUES ('147', '1', '18', '1501682365180', '1378112422');
INSERT INTO `join_up` VALUES ('148', '1', '25', '1501682384065', '7880625495');
INSERT INTO `join_up` VALUES ('149', '1', '18', '1501682899416', '1378112422');
INSERT INTO `join_up` VALUES ('150', '1', '25', '1501682952672', '7880625495');
INSERT INTO `join_up` VALUES ('151', '1', '18', '1501682954328', '1378112422');
INSERT INTO `join_up` VALUES ('152', '1', '18', '1501683007224', '1378112422');
INSERT INTO `join_up` VALUES ('153', '1', '25', '1501683010552', '7880625495');
INSERT INTO `join_up` VALUES ('154', '1', '26', '1501683036380', '3865366230');
INSERT INTO `join_up` VALUES ('155', '1', '18', '1501683056714', '1378112422');
INSERT INTO `join_up` VALUES ('156', '1', '18', '1501683081940', '1378112422');
INSERT INTO `join_up` VALUES ('157', '1', '18', '1501683106352', '1378112422');
INSERT INTO `join_up` VALUES ('158', '1', '27', '1501683210482', '7529884261');
INSERT INTO `join_up` VALUES ('159', '1', '27', '1501683313907', '7529884261');
INSERT INTO `join_up` VALUES ('160', '1', '27', '1501683609578', '7529884261');
INSERT INTO `join_up` VALUES ('161', '1', '18', '1501683696789', '1378112422');
INSERT INTO `join_up` VALUES ('162', '1', '18', '1501683762710', '1378112422');
INSERT INTO `join_up` VALUES ('163', '1', '18', '1501684125411', '1378112422');
INSERT INTO `join_up` VALUES ('164', '1', '18', '1501684163442', '1378112422');
INSERT INTO `join_up` VALUES ('165', '1', '18', '1501684206806', '1378112422');
INSERT INTO `join_up` VALUES ('166', '1', '25', '1501724633419', '7880625495');
INSERT INTO `join_up` VALUES ('167', '1', '25', '1501725072249', '7880625495');
INSERT INTO `join_up` VALUES ('168', '1', '28', '1501725185913', '5778290431');
INSERT INTO `join_up` VALUES ('169', '1', '28', '1501725246790', '5778290431');
INSERT INTO `join_up` VALUES ('170', '1', '28', '1501726765173', '5778290431');
INSERT INTO `join_up` VALUES ('171', '1', '28', '1501726867703', '5778290431');
INSERT INTO `join_up` VALUES ('172', '1', '28', '1501726996212', '5778290431');
INSERT INTO `join_up` VALUES ('173', '1', '28', '1501727090031', '5778290431');
INSERT INTO `join_up` VALUES ('174', '1', '28', '1501728452870', '5778290431');
INSERT INTO `join_up` VALUES ('175', '1', '28', '1501728919612', '5778290431');
INSERT INTO `join_up` VALUES ('176', '1', '28', '1501729608927', '5778290431');
INSERT INTO `join_up` VALUES ('177', '1', '29', '1501733295884', '9711833392');
INSERT INTO `join_up` VALUES ('178', '1', '25', '1501742622839', '7880625495');
INSERT INTO `join_up` VALUES ('179', '1', '25', '1501742627704', '7880625495');
INSERT INTO `join_up` VALUES ('180', '1', '25', '1501744245236', '7880625495');

-- ----------------------------
-- Table structure for keyword
-- ----------------------------
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword` (
  `keyword_id` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`keyword_id`),
  UNIQUE KEY `key_word` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keyword
-- ----------------------------
INSERT INTO `keyword` VALUES ('61', '一期');
INSERT INTO `keyword` VALUES ('55', '中户型');
INSERT INTO `keyword` VALUES ('39', '买房');
INSERT INTO `keyword` VALUES ('52', '买房的首付问题？');
INSERT INTO `keyword` VALUES ('44', '位置');
INSERT INTO `keyword` VALUES ('42', '信息');
INSERT INTO `keyword` VALUES ('58', '别墅');
INSERT INTO `keyword` VALUES ('47', '哪个地方');
INSERT INTO `keyword` VALUES ('46', '哪儿');
INSERT INTO `keyword` VALUES ('45', '哪里');
INSERT INTO `keyword` VALUES ('38', '售楼');
INSERT INTO `keyword` VALUES ('51', '多少钱');
INSERT INTO `keyword` VALUES ('53', '大户型');
INSERT INTO `keyword` VALUES ('54', '小户型');
INSERT INTO `keyword` VALUES ('59', '小高层');
INSERT INTO `keyword` VALUES ('56', '平方');
INSERT INTO `keyword` VALUES ('48', '我们公司楼盘的具体位置？');
INSERT INTO `keyword` VALUES ('50', '户型');
INSERT INTO `keyword` VALUES ('37', '房产');
INSERT INTO `keyword` VALUES ('60', '排屋');
INSERT INTO `keyword` VALUES ('43', '本公司的楼盘信息？');
INSERT INTO `keyword` VALUES ('41', '楼盘');
INSERT INTO `keyword` VALUES ('57', '楼盘有哪些户型？');
INSERT INTO `keyword` VALUES ('40', '洋房');
INSERT INTO `keyword` VALUES ('9', '转接到人工客服');
INSERT INTO `keyword` VALUES ('62', '这一期楼盘有别墅吗');
INSERT INTO `keyword` VALUES ('49', '首付');

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
INSERT INTO `keyword_heat` VALUES ('9', '1501733322370');
INSERT INTO `keyword_heat` VALUES ('42', '1501742654002');
INSERT INTO `keyword_heat` VALUES ('41', '1501742654059');
INSERT INTO `keyword_heat` VALUES ('48', '1501742663797');
INSERT INTO `keyword_heat` VALUES ('44', '1501742663802');
INSERT INTO `keyword_heat` VALUES ('41', '1501742663865');
INSERT INTO `keyword_heat` VALUES ('39', '1501742677484');
INSERT INTO `keyword_heat` VALUES ('49', '1501742677490');
INSERT INTO `keyword_heat` VALUES ('53', '1501742688710');
INSERT INTO `keyword_heat` VALUES ('50', '1501742688712');
INSERT INTO `keyword_heat` VALUES ('41', '1501742688715');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of knowledge
-- ----------------------------
INSERT INTO `knowledge` VALUES ('4', '转接到人工客服', '正在为您转接人工客服，请稍后……', '1', '0', '1');
INSERT INTO `knowledge` VALUES ('24', '本公司的楼盘信息？', '您好，我们是黑曜石售楼部的，我们这里有不限购不限贷的，70年产权的多层电梯洋房，自住投资都可以，低至8000元/平方喔。', '1', '1', '0');
INSERT INTO `knowledge` VALUES ('25', '我们公司楼盘的具体位置？', '在北京南路与绕城南路交叉口，这个位置很好找的，地段很好！', '1', '1', '0');
INSERT INTO `knowledge` VALUES ('26', '买房的首付问题？', '不同户型的首付不同的哦，我们的首付最低十几万起喔，很实惠。', '1', '1', '0');
INSERT INTO `knowledge` VALUES ('27', '楼盘有哪些户型？', '我们目前在售户型是78方（小户型）、120方（中户型）、135（大户型）方的都有，户型很方正的。', '1', '1', '0');
INSERT INTO `knowledge` VALUES ('28', '这一期楼盘有别墅吗', '我们这期的楼盘有别墅、小高层和排屋，而且设计风格都很美观喔。', '1', '1', '0');

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
INSERT INTO `knowledge_keyword` VALUES ('9', '4');
INSERT INTO `knowledge_keyword` VALUES ('37', '24');
INSERT INTO `knowledge_keyword` VALUES ('38', '24');
INSERT INTO `knowledge_keyword` VALUES ('39', '24');
INSERT INTO `knowledge_keyword` VALUES ('39', '26');
INSERT INTO `knowledge_keyword` VALUES ('40', '24');
INSERT INTO `knowledge_keyword` VALUES ('41', '24');
INSERT INTO `knowledge_keyword` VALUES ('41', '25');
INSERT INTO `knowledge_keyword` VALUES ('41', '28');
INSERT INTO `knowledge_keyword` VALUES ('42', '24');
INSERT INTO `knowledge_keyword` VALUES ('43', '24');
INSERT INTO `knowledge_keyword` VALUES ('44', '25');
INSERT INTO `knowledge_keyword` VALUES ('45', '25');
INSERT INTO `knowledge_keyword` VALUES ('46', '25');
INSERT INTO `knowledge_keyword` VALUES ('47', '25');
INSERT INTO `knowledge_keyword` VALUES ('48', '25');
INSERT INTO `knowledge_keyword` VALUES ('49', '26');
INSERT INTO `knowledge_keyword` VALUES ('50', '26');
INSERT INTO `knowledge_keyword` VALUES ('50', '27');
INSERT INTO `knowledge_keyword` VALUES ('51', '26');
INSERT INTO `knowledge_keyword` VALUES ('52', '26');
INSERT INTO `knowledge_keyword` VALUES ('53', '27');
INSERT INTO `knowledge_keyword` VALUES ('54', '27');
INSERT INTO `knowledge_keyword` VALUES ('55', '27');
INSERT INTO `knowledge_keyword` VALUES ('56', '27');
INSERT INTO `knowledge_keyword` VALUES ('57', '27');
INSERT INTO `knowledge_keyword` VALUES ('58', '28');
INSERT INTO `knowledge_keyword` VALUES ('59', '28');
INSERT INTO `knowledge_keyword` VALUES ('60', '28');
INSERT INTO `knowledge_keyword` VALUES ('61', '28');
INSERT INTO `knowledge_keyword` VALUES ('62', '28');

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
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

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
INSERT INTO `notification` VALUES ('83', '1', '3', '1', '编号为22的客户接入到会话中', '1501678751882');
INSERT INTO `notification` VALUES ('84', '1', '3', '1', '编号为23的客户接入到会话中', '1501680077717');
INSERT INTO `notification` VALUES ('85', '1', '3', '1', '编号为23的客户接入到会话中', '1501680220538');
INSERT INTO `notification` VALUES ('86', '1', '3', '1', '编号为25的客户接入到会话中', '1501681702492');
INSERT INTO `notification` VALUES ('87', '1', '3', '1', '编号为25的客户接入到会话中', '1501682185791');
INSERT INTO `notification` VALUES ('88', '1', '3', '1', '编号为25的客户接入到会话中', '1501682468565');
INSERT INTO `notification` VALUES ('89', '1', '3', '1', '编号为27的客户接入到会话中', '1501683249817');
INSERT INTO `notification` VALUES ('90', '1', '3', '1', '编号为27的客户接入到会话中', '1501683411730');
INSERT INTO `notification` VALUES ('91', '1', '3', '1', '编号为27的客户接入到会话中', '1501683655425');
INSERT INTO `notification` VALUES ('92', '1', '3', '1', '编号为25的客户接入到会话中', '1501724668622');
INSERT INTO `notification` VALUES ('93', '1', '3', '1', '编号为25的客户接入到会话中', '1501725116017');
INSERT INTO `notification` VALUES ('94', '1', '3', '1', '编号为28的客户接入到会话中', '1501725203962');
INSERT INTO `notification` VALUES ('95', '1', '3', '1', '编号为28的客户接入到会话中', '1501726877166');
INSERT INTO `notification` VALUES ('96', '1', '3', '1', '编号为28的客户接入到会话中', '1501727056315');
INSERT INTO `notification` VALUES ('97', '1', '3', '1', '编号为28的客户接入到会话中', '1501727096906');
INSERT INTO `notification` VALUES ('98', '1', '3', '1', '编号为28的客户接入到会话中', '1501728460857');
INSERT INTO `notification` VALUES ('99', '1', '3', '1', '编号为28的客户接入到会话中', '1501728934190');
INSERT INTO `notification` VALUES ('100', '1', '3', '1', '编号为28的客户接入到会话中', '1501729638539');
INSERT INTO `notification` VALUES ('101', '1', '3', '1', '编号为29的客户接入到会话中', '1501733326181');
INSERT INTO `notification` VALUES ('102', '2', '1', '1', '今天晚上8点-12点系统维护。', '1501758354164');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service_group
-- ----------------------------
INSERT INTO `service_group` VALUES ('1', '楼盘售后组');
INSERT INTO `service_group` VALUES ('2', '楼盘售前组');
INSERT INTO `service_group` VALUES ('3', '广告投送组');
INSERT INTO `service_group` VALUES ('4', '贷款咨询组');
INSERT INTO `service_group` VALUES ('5', '物业管理组');

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
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8;

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
INSERT INTO `work_time` VALUES ('127', '1', '1501675953917', '1501677633705');
INSERT INTO `work_time` VALUES ('128', '1', '1501677643054', '1501677741657');
INSERT INTO `work_time` VALUES ('129', '1', '1501678616527', '1501679748479');
INSERT INTO `work_time` VALUES ('130', '1', '1501679838950', '1501679993751');
INSERT INTO `work_time` VALUES ('131', '1', '1501679994396', '1501680758116');
INSERT INTO `work_time` VALUES ('132', '1', '1501680872044', '1501680931912');
INSERT INTO `work_time` VALUES ('133', '1', '1501680932212', '1501681017099');
INSERT INTO `work_time` VALUES ('134', '1', '1501681021342', '1501681051454');
INSERT INTO `work_time` VALUES ('135', '1', '1501681056227', '1501681093244');
INSERT INTO `work_time` VALUES ('136', '1', '1501681332125', '1501681416214');
INSERT INTO `work_time` VALUES ('137', '1', '1501681416888', '1501681442657');
INSERT INTO `work_time` VALUES ('138', '1', '1501681446230', '1501681518379');
INSERT INTO `work_time` VALUES ('139', '1', '1501681678347', '1501682116473');
INSERT INTO `work_time` VALUES ('140', '1', '1501682179452', '1501682185807');
INSERT INTO `work_time` VALUES ('141', '1', '1501682427392', '1501682936681');
INSERT INTO `work_time` VALUES ('142', '1', '1501683000794', '1501683002438');
INSERT INTO `work_time` VALUES ('143', '1', '1501683002693', '1501683002759');
INSERT INTO `work_time` VALUES ('144', '1', '1501683203955', '1501683295546');
INSERT INTO `work_time` VALUES ('145', '1', '1501683322081', '1501683324144');
INSERT INTO `work_time` VALUES ('146', '1', '1501683324434', '1501683635793');
INSERT INTO `work_time` VALUES ('147', '1', '1501683637728', '1501684302955');
INSERT INTO `work_time` VALUES ('148', '1', '1501723748317', '1501723908704');
INSERT INTO `work_time` VALUES ('149', '1', '1501724665801', '1501725041979');
INSERT INTO `work_time` VALUES ('150', '1', '1501725063830', '1501725169284');
INSERT INTO `work_time` VALUES ('151', '1', '1501725170878', '1501726750863');
INSERT INTO `work_time` VALUES ('152', '1', '1501726860922', '1501726925954');
INSERT INTO `work_time` VALUES ('153', '1', '1501726986969', '1501727056308');
INSERT INTO `work_time` VALUES ('154', '1', '1501727080101', '1501727081072');
INSERT INTO `work_time` VALUES ('155', '1', '1501727081678', '1501727106162');
INSERT INTO `work_time` VALUES ('156', '1', '1501728442857', '1501728922369');
INSERT INTO `work_time` VALUES ('157', '1', '1501728924291', '1501729574159');
INSERT INTO `work_time` VALUES ('158', '1', '1501729612015', '1501733168399');
INSERT INTO `work_time` VALUES ('159', '1', '1501733306045', '1501735057658');
INSERT INTO `work_time` VALUES ('160', '1', '1501742635942', '1501744233586');
INSERT INTO `work_time` VALUES ('161', '1', '1501744252859', '1501755003497');
