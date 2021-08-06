/*
Navicat MySQL Data Transfer

Source Server         : 1
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : ssm_system

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2021-08-06 22:40:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_tbl
-- ----------------------------
DROP TABLE IF EXISTS `admin_tbl`;
CREATE TABLE `admin_tbl` (
  `admin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `adminpswd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admin_tbl
-- ----------------------------
INSERT INTO `admin_tbl` VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for deal_record
-- ----------------------------
DROP TABLE IF EXISTS `deal_record`;
CREATE TABLE `deal_record` (
  `deal_id` int NOT NULL AUTO_INCREMENT,
  `task_id` int NOT NULL,
  `time` datetime NOT NULL,
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` int NOT NULL,
  `hash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`deal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of deal_record
-- ----------------------------
INSERT INTO `deal_record` VALUES ('1', '498951', '2021-05-24 17:25:35', '123', '121', '300', '0x5cf4c1fa733b52a313dc862bc623e8612e70049bbee50b95118e277f1b6e476b');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `from` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` datetime NOT NULL,
  `type` int NOT NULL,
  `active` int NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('admin', '123', '我会尽快处理！', '2021-05-07 01:05:55', '0', '0');
INSERT INTO `message` VALUES ('123', 'admin', '你好', '2021-05-10 00:02:06', '0', '0');
INSERT INTO `message` VALUES ('admin', '123', '这是留言！', '2021-05-13 08:26:20', '0', '0');
INSERT INTO `message` VALUES ('121', '123', '快上线！', '2021-05-13 08:31:44', '1', '0');
INSERT INTO `message` VALUES ('123', '121', '明天上线！', '2021-05-13 09:17:22', '1', '0');
INSERT INTO `message` VALUES ('123', '121', '好的！', '2021-08-03 19:29:13', '1', '0');
INSERT INTO `message` VALUES ('121', '123', '在吗？', '2021-08-03 23:21:24', '1', '0');
INSERT INTO `message` VALUES ('123', '121', '在的！', '2021-08-03 23:21:43', '1', '0');
INSERT INTO `message` VALUES ('121', '123', '抓紧时间上线！', '2021-08-03 23:27:23', '1', '0');
INSERT INTO `message` VALUES ('123', 'admin', '我有一些问题想要反馈', '2021-08-04 09:52:56', '0', '0');
INSERT INTO `message` VALUES ('123', 'admin', '请问你现在有空吗', '2021-08-04 09:53:13', '0', '0');
INSERT INTO `message` VALUES ('123', 'admin', '我的问题是：......', '2021-08-04 09:53:40', '0', '0');
INSERT INTO `message` VALUES ('admin', '111', '11', '2021-08-04 11:06:46', '0', '0');

-- ----------------------------
-- Table structure for recharge_record
-- ----------------------------
DROP TABLE IF EXISTS `recharge_record`;
CREATE TABLE `recharge_record` (
  `recharge_id` bigint NOT NULL,
  `user` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `sum` int NOT NULL,
  PRIMARY KEY (`recharge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of recharge_record
-- ----------------------------
INSERT INTO `recharge_record` VALUES ('1', '123', '2021-05-07 01:12:54', '30');
INSERT INTO `recharge_record` VALUES ('2', '123', '2021-05-07 01:13:04', '30');
INSERT INTO `recharge_record` VALUES ('3', '121', '2021-05-13 13:59:54', '80');
INSERT INTO `recharge_record` VALUES ('4', '123', '2021-05-13 15:55:23', '22');
INSERT INTO `recharge_record` VALUES ('5', '123', '2021-05-13 16:00:39', '22');
INSERT INTO `recharge_record` VALUES ('6', '123', '2021-08-05 18:00:04', '200');
INSERT INTO `recharge_record` VALUES ('7', '123', '2021-08-05 18:03:58', '11');
INSERT INTO `recharge_record` VALUES ('8', '123', '2021-08-05 18:05:43', '333');
INSERT INTO `recharge_record` VALUES ('9', '123', '2021-08-05 18:07:07', '123');
INSERT INTO `recharge_record` VALUES ('10', '123', '2021-08-05 18:16:25', '1');
INSERT INTO `recharge_record` VALUES ('11', '123', '2021-08-05 18:17:33', '1');
INSERT INTO `recharge_record` VALUES ('12', '123', '2021-08-05 18:23:05', '2');
INSERT INTO `recharge_record` VALUES ('1628255908052', '123', '2021-08-06 21:19:15', '123');

-- ----------------------------
-- Table structure for taskinfo_tbl
-- ----------------------------
DROP TABLE IF EXISTS `taskinfo_tbl`;
CREATE TABLE `taskinfo_tbl` (
  `task_id` int NOT NULL,
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `task_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `publish_time` datetime NOT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `pay` int NOT NULL,
  KEY `task_id` (`task_id`),
  KEY `publisher` (`publisher`),
  KEY `receiver` (`receiver`),
  CONSTRAINT `taskinfo_tbl_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task_tbl` (`task_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of taskinfo_tbl
-- ----------------------------
INSERT INTO `taskinfo_tbl` VALUES ('647796', '121', '本人因为一些原因，现在需要一张关于“人与自然”的海报。', '2021-04-20 05:59:39', null, null, '80');
INSERT INTO `taskinfo_tbl` VALUES ('498951', '123', '本公司现在需要一份关于\"交通运输\"的文案。', '2021-03-22 14:06:13', '121', '2021-05-24 17:25:35', '300');
INSERT INTO `taskinfo_tbl` VALUES ('572531', '121', '本人想给新开的烧烤店设计一个广告招牌，有能力者接。', '2020-06-27 13:07:31', null, null, '200');
INSERT INTO `taskinfo_tbl` VALUES ('555838', '121', '本公司需要一份关于\"生活\"的文案设计。', '2021-04-10 19:59:46', '123', null, '500');
INSERT INTO `taskinfo_tbl` VALUES ('922668', '123', '测试', '2021-05-13 15:23:33', null, null, '10');
INSERT INTO `taskinfo_tbl` VALUES ('1', '11', '本公司需要一份装修设计', '2021-05-12 15:26:01', null, null, '200');
INSERT INTO `taskinfo_tbl` VALUES ('2', 'gh', '本公司现在需要为自己的广告设计公司创建一个网站。', '2021-01-21 22:28:28', null, null, '5000');
INSERT INTO `taskinfo_tbl` VALUES ('3', '11', '本人需要设计一张关于\"父亲\"的插图，有能力者联系。', '2021-01-05 10:21:42', null, null, '30');

-- ----------------------------
-- Table structure for task_tbl
-- ----------------------------
DROP TABLE IF EXISTS `task_tbl`;
CREATE TABLE `task_tbl` (
  `task_id` int NOT NULL AUTO_INCREMENT,
  `task_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `check_status` int NOT NULL DEFAULT '0',
  `check_result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=997138 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of task_tbl
-- ----------------------------
INSERT INTO `task_tbl` VALUES ('1', '装修设计', '0', '0', '');
INSERT INTO `task_tbl` VALUES ('2', '软件设计', '0', '0', '');
INSERT INTO `task_tbl` VALUES ('3', '插图设计', '0', '1', '');
INSERT INTO `task_tbl` VALUES ('498951', '文案设计', '1', '1', '');
INSERT INTO `task_tbl` VALUES ('555838', '文案设计', '0', '1', '');
INSERT INTO `task_tbl` VALUES ('572531', '广告设计', '0', '1', '');
INSERT INTO `task_tbl` VALUES ('647796', '海报设计', '0', '1', '');
INSERT INTO `task_tbl` VALUES ('922668', '测试', '0', '2', '标题混乱');

-- ----------------------------
-- Table structure for userinfo_tbl
-- ----------------------------
DROP TABLE IF EXISTS `userinfo_tbl`;
CREATE TABLE `userinfo_tbl` (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `balance` int(10) unsigned zerofill NOT NULL,
  KEY `username` (`username`),
  CONSTRAINT `userinfo_tbl_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user_tbl` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of userinfo_tbl
-- ----------------------------
INSERT INTO `userinfo_tbl` VALUES ('gh', '龚豪', '男', '1998-01-28', '0000000000');
INSERT INTO `userinfo_tbl` VALUES ('123', '海鸡', '男', '2004-11-16', '0000000898');
INSERT INTO `userinfo_tbl` VALUES ('121', '陈兵', '男', '1994-10-21', '0000000380');
INSERT INTO `userinfo_tbl` VALUES ('11', '齐尔', '女', '1994-11-18', '0000000000');

-- ----------------------------
-- Table structure for user_tbl
-- ----------------------------
DROP TABLE IF EXISTS `user_tbl`;
CREATE TABLE `user_tbl` (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_tbl
-- ----------------------------
INSERT INTO `user_tbl` VALUES ('11', '11');
INSERT INTO `user_tbl` VALUES ('121', '121');
INSERT INTO `user_tbl` VALUES ('1211', '1211');
INSERT INTO `user_tbl` VALUES ('123', '123');
INSERT INTO `user_tbl` VALUES ('1234', '1234');
INSERT INTO `user_tbl` VALUES ('12345', '12345');
INSERT INTO `user_tbl` VALUES ('gh', 'gh');
