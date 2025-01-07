create database yyzxl;
use yyzxl;
/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 80032
Source Host           : localhost:3306
Source Database       : yyzx

Target Server Type    : MYSQL
Target Server Version : 80032
File Encoding         : 65001

Date: 2024-09-09 22:25:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for back_down
-- ----------------------------
DROP TABLE IF EXISTS `back_down`;
CREATE TABLE `back_down` (
                             `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `remarks` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
                             `isDeleted` int NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
                             `customerId` int NOT NULL COMMENT '客户ID',
                             `retreatTime` datetime NOT NULL COMMENT '退住时间',
                             `retreatType` int NOT NULL COMMENT '退住类型 0-正常退住  1-死亡退住 2-保留床位',
                             `retreatReason` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '退住原因',
                             `auditStatus` int NOT NULL COMMENT '审批状态  0-已提交 1-同意  2-拒绝',
                             `auditPerson` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '审批人',
                             `auditTime` datetime DEFAULT NULL COMMENT '审批时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of back_down
-- ----------------------------
INSERT INTO `back_down` VALUES ('1', '暂无', '0', '40', '2023-12-17 00:00:00', '2', '临时出院', '2', '1', '2024-08-31 08:00:00');
INSERT INTO `back_down` VALUES ('2', null, '0', '39', '2023-12-19 00:00:00', '0', '康复出院', '2', 'admin', '2024-09-03 08:00:00');
INSERT INTO `back_down` VALUES ('3', null, '0', '33', '2023-12-20 00:00:00', '0', '', '1', 'admin', '2024-09-03 08:00:00');

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed` (
                       `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                       `roomNo` int NOT NULL COMMENT '房间号',
                       `status` tinyint NOT NULL COMMENT '床位状态 1: 未使用 2: 已占用 3: 外出',
                       `remarks` int DEFAULT NULL COMMENT '备注',
                       `bedNo` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '床位号',
                       `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                       `createBy` int DEFAULT NULL COMMENT '创建者',
                       `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                       `updateBy` int DEFAULT NULL COMMENT '更新者',
                       `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of bed
-- ----------------------------
INSERT INTO `bed` VALUES ('6', '1001', '1', null, '1001-1', '2023-03-21 15:34:00', '1', '2024-08-29 18:53:17', '1', '0');
INSERT INTO `bed` VALUES ('7', '1001', '1', null, '1001-2', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('8', '1002', '1', null, '1002-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('9', '1002', '2', null, '1002-2', '2023-03-21 15:34:00', '1', '2024-08-29 18:53:17', '1', '0');
INSERT INTO `bed` VALUES ('10', '1003', '1', null, '1003-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('11', '1004', '1', null, '1004-1', '2023-03-21 15:34:00', '1', '2024-08-30 10:59:02', '1', '0');
INSERT INTO `bed` VALUES ('12', '1005', '1', null, '1005-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('13', '1005', '2', null, '1005-2', '2023-03-21 15:34:00', '1', '2024-08-30 12:45:51', '1', '0');
INSERT INTO `bed` VALUES ('14', '1006', '1', null, '1006-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('15', '1006', '1', null, '1006-2', '2023-03-21 15:34:00', '1', '2024-08-30 12:53:10', '1', '0');
INSERT INTO `bed` VALUES ('16', '1007', '1', null, '1007-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('17', '1007', '1', null, '1007-2', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('18', '1008', '1', null, '1008-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('19', '1009', '1', null, '1009-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('21', '1009', '1', null, '1009-2', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('22', '1010', '1', null, '1010-1', '2023-03-21 15:34:00', '1', '2024-08-30 13:00:45', '1', '0');
INSERT INTO `bed` VALUES ('23', '1010', '1', null, '1010-2', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('24', '1011', '1', null, '1011-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('25', '1012', '1', null, '1012-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('26', '1012', '2', null, '1012-2', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('27', '1013', '1', null, '1013-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('28', '1014', '2', null, '1014-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('29', '1015', '1', null, '1015-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('30', '1015', '1', null, '1015-2', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('31', '1016', '1', null, '1016-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('32', '1017', '2', null, '1017-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('33', '1018', '1', null, '1018-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('34', '2001', '1', null, '2001-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('35', '2002', '1', null, '2002-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('36', '2003', '1', null, '2003-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('37', '2004', '1', null, '2004-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('38', '2005', '2', null, '2005-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('39', '2006', '1', null, '2006-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('40', '2007', '1', null, '2007-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('41', '2008', '1', null, '2008-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('42', '2009', '1', null, '2009-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('43', '2009', '1', null, '2009-2', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('44', '2010', '1', null, '2010-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('45', '2011', '1', null, '2011-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('46', '2012', '1', null, '2012-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('47', '2013', '1', null, '2013-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('48', '2014', '2', null, '2014-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('49', '2015', '1', null, '2015-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('50', '2016', '1', null, '2016-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('51', '2017', '2', null, '2017-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');
INSERT INTO `bed` VALUES ('52', '2018', '2', null, '2018-1', '2023-03-21 15:34:00', '1', '2023-03-21 15:34:00', '1', '0');

-- ----------------------------
-- Table structure for bed_details
-- ----------------------------
DROP TABLE IF EXISTS `bed_details`;
CREATE TABLE `bed_details` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `startDate` datetime NOT NULL COMMENT '开始日期',
                               `endDate` datetime NOT NULL COMMENT '结束日期',
                               `bedDetails` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
                               `customerId` int NOT NULL COMMENT '客户id',
                               `bedId` int NOT NULL COMMENT '床位id',
                               `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of bed_details
-- ----------------------------
INSERT INTO `bed_details` VALUES ('45', '2023-11-17 00:00:00', '2025-01-30 00:00:00', '606#1006-2', '27', '15', '0');
INSERT INTO `bed_details` VALUES ('46', '2023-11-17 00:00:00', '2025-11-30 00:00:00', '606#1001-1', '28', '6', '0');
INSERT INTO `bed_details` VALUES ('49', '2023-11-17 00:00:00', '2023-11-23 00:00:00', '606#2001-1', '30', '34', '1');
INSERT INTO `bed_details` VALUES ('50', '2023-11-17 00:00:00', '2025-11-27 00:00:00', '606#2002-1', '29', '35', '0');
INSERT INTO `bed_details` VALUES ('51', '2023-11-17 00:00:00', '2025-11-29 00:00:00', '606#2017-1', '31', '51', '0');
INSERT INTO `bed_details` VALUES ('52', '2023-11-17 00:00:00', '2023-11-21 00:00:00', '606#2015-1', '32', '49', '1');
INSERT INTO `bed_details` VALUES ('53', '2023-11-17 00:00:00', '2024-11-29 00:00:00', '606#2014-1', '33', '48', '0');
INSERT INTO `bed_details` VALUES ('54', '2023-11-17 00:00:00', '2024-11-18 00:00:00', '606#2002-1', '34', '35', '0');
INSERT INTO `bed_details` VALUES ('55', '2023-11-17 00:00:00', '2024-11-30 00:00:00', '606#1017-1', '35', '32', '0');
INSERT INTO `bed_details` VALUES ('57', '2023-11-17 00:00:00', '2025-11-20 00:00:00', '606#1012-2', '37', '26', '0');
INSERT INTO `bed_details` VALUES ('58', '2023-11-17 00:00:00', '2025-11-29 00:00:00', '606#2005-1', '38', '38', '0');
INSERT INTO `bed_details` VALUES ('59', '2023-11-17 00:00:00', '2024-11-30 00:00:00', '606#1014-1', '39', '28', '0');
INSERT INTO `bed_details` VALUES ('60', '2023-11-17 00:00:00', '2025-11-29 00:00:00', '606#2012-1', '40', '46', '0');
INSERT INTO `bed_details` VALUES ('61', '2023-11-21 00:00:00', '2024-01-15 00:00:00', '606#2016-1', '32', '50', '1');
INSERT INTO `bed_details` VALUES ('62', '2023-11-23 00:00:00', '2025-12-31 00:00:00', '606#2018-1', '30', '52', '0');
INSERT INTO `bed_details` VALUES ('63', '2024-01-03 00:00:00', '2024-02-03 00:00:00', '606#1004-1', '41', '11', '0');
INSERT INTO `bed_details` VALUES ('64', '2024-01-03 00:00:00', '2024-02-16 00:00:00', '606#1005-1', '41', '12', '0');
INSERT INTO `bed_details` VALUES ('67', '2024-08-31 00:00:00', '2024-09-01 00:00:00', '606#1004-1', '42', '11', '0');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `isDeleted` int DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
                            `customerName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '客户姓名',
                            `customerAge` int NOT NULL COMMENT '年龄',
                            `customerSex` int NOT NULL COMMENT '性别  0：男  1：女',
                            `idcard` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '身份证号',
                            `roomNo` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '房间号',
                            `buildingNo` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '所属楼房',
                            `checkinDate` datetime NOT NULL COMMENT '入住时间',
                            `expirationDate` datetime NOT NULL COMMENT '合同到期时间',
                            `contactTel` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '联系电话',
                            `bedId` int NOT NULL COMMENT '床号',
                            `psychosomaticState` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '身心状况',
                            `attention` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '注意事项',
                            `birthday` date DEFAULT NULL COMMENT '出生日期',
                            `height` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '身高',
                            `weight` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '体重',
                            `bloodType` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '血型',
                            `filepath` varchar(50) DEFAULT '@/assets/tou.png' COMMENT '头像路径',
                            `userId` int DEFAULT NULL COMMENT '关联系统健康管家(护工)  无管家设置  -1',
                            `levelId` int DEFAULT NULL COMMENT '护理等级',
                            `familyMember` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '家属',
                            PRIMARY KEY (`id`) USING BTREE,
                            KEY `nurselevel_customer_fk` (`levelId`) USING BTREE,
                            KEY `user_customer_fk` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('27', '0', '刘茂元', '92', '0', '610404194103010017', '1001', '606', '2023-11-17 00:00:00', '2025-11-30 00:00:00', '13767675481', '6', '123', '123', '1947-07-19', '183', '12', 'O', '@/assets/tou.png', '-1', '2', '刘其元');
INSERT INTO `customer` VALUES ('28', '1', '高廉武', '83', '0', '610404194103010017', '1002', '606', '2023-11-17 00:00:00', '2024-11-30 00:00:00', '13767675481', '9', '', null, '1941-03-01', '183', '12', 'A', '@/assets/tou.png', '5', null, '高校蓝');
INSERT INTO `customer` VALUES ('29', '0', '刘玉珍', '72', '1', '610402194112240335', '2002', '606', '2023-11-17 00:00:00', '2025-11-27 00:00:00', '13534786754', '35', '', null, '1951-12-24', '183', '12', 'B', '@/assets/tou.png', '-1', null, '刘宇');
INSERT INTO `customer` VALUES ('30', '0', '石秀英', '73', '1', '610422195102220029', '2018', '606', '2023-11-17 00:00:00', '2025-12-31 00:00:00', '13867541321', '52', '', null, '1951-02-22', '183', '12', 'O', '@/assets/tou.png', '5', null, '石杰颖');
INSERT INTO `customer` VALUES ('31', '1', '何风云', '62', '1', '610402196111166507', '2017', '606', '2023-11-17 00:00:00', '2025-11-29 00:00:00', '18765652312', '51', '', null, '1961-11-16', '183', '12', 'O', '@/assets/tou.png', '3', null, '何方');
INSERT INTO `customer` VALUES ('32', '0', '毛兰香', '73', '1', '61040419501227002x', '1005', '606', '2023-11-17 00:00:00', '2027-11-12 00:00:00', '15816164587', '13', '', null, '1950-12-27', '183', '12', 'O', '@/assets/tou.png', '-1', null, '毛参');
INSERT INTO `customer` VALUES ('33', '0', '李艾青', '83', '0', '622801194104150240', '2014', '606', '2023-11-17 00:00:00', '2024-11-29 00:00:00', '18176572345', '48', '', null, '1941-04-14', '183', '12', 'B', '@/assets/tou.png', '-1', null, '李天');
INSERT INTO `customer` VALUES ('34', '0', '刘海云', '64', '0', '510522196005042710', '1006', '606', '2023-11-17 00:00:00', '2025-01-30 00:00:00', '18798986545', '15', '', null, '1960-05-04', '183', '12', 'O', '@/assets/tou.png', '-1', null, '刘高');
INSERT INTO `customer` VALUES ('35', '1', '王金堂', '58', '0', '610402196511056517', '1017', '606', '2023-11-17 00:00:00', '2024-11-30 00:00:00', '13423786754', '32', '', null, '1965-11-05', '183', '12', 'AB', '@/assets/tou.png', '3', '4', '王田雨');
INSERT INTO `customer` VALUES ('36', '1', '刘玉亭', '72', '0', '610123195204158510', '1010', '606', '2023-11-17 00:00:00', '2025-11-26 00:00:00', '18767560987', '22', '', null, '1952-04-15', '183', '12', 'O', '@/assets/tou.png', '5', '1', '刘晓');
INSERT INTO `customer` VALUES ('37', '1', '陈泉', '68', '0', '61040219560313030x', '1012', '606', '2023-11-17 00:00:00', '2025-11-20 00:00:00', '18567546712', '26', '', null, '1956-03-13', '183', '12', 'O', '@/assets/tou.png', '-1', null, '陈荣光');
INSERT INTO `customer` VALUES ('38', '0', '苗月兰', '67', '1', '622725195701081429', '2005', '606', '2023-11-17 00:00:00', '2025-11-29 00:00:00', '18367541987', '38', '', null, '1957-01-08', '183', '12', 'A', '@/assets/tou.png', '3', null, '苗存义');
INSERT INTO `customer` VALUES ('39', '0', '张帆', '77', '0', '610402194701286518', '1014', '606', '2023-11-17 00:00:00', '2024-11-30 00:00:00', '17745189064', '28', '', null, '1947-01-28', '183', '12', 'O', '@/assets/tou.png', '2', '3', '张明');
INSERT INTO `customer` VALUES ('40', '0', '孙瑞英', '68', '1', '610404195603010027', '2012', '606', '2023-11-17 00:00:00', '2025-11-29 00:00:00', '18167564213', '46', '', null, '1956-03-01', '183', '12', 'O', '@/assets/tou.png', '2', '2', '孙石');
INSERT INTO `customer` VALUES ('41', '0', '许三多', '0', '0', '210330198902011510', '1004', '606', '2024-01-03 00:00:00', '2024-02-03 00:00:00', '13398760987', '11', '过敏', null, '2024-01-03', '183', '12', 'O', '@/assets/tou.png', '2', '1', '许多');
INSERT INTO `customer` VALUES ('43', '0', 'z', '123', '1', '30133222222', '', '', '2024-08-30 00:00:00', '2024-09-19 00:00:00', '15877778889', '13', 'asd', '', '2015-08-12', '183', '12', 'p', '@/assets/tou.png', '3', '2', '');

-- ----------------------------
-- Table structure for customer_nurse_item
-- ----------------------------
DROP TABLE IF EXISTS `customer_nurse_item`;
CREATE TABLE `customer_nurse_item` (
                                       `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                       `itemId` int NOT NULL COMMENT '护理项目编号',
                                       `customerId` int NOT NULL COMMENT '客户编号',
                                       `levelId` int DEFAULT NULL COMMENT '护理级别编号',
                                       `nurseNumber` int NOT NULL COMMENT '购买数量',
                                       `isDeleted` int DEFAULT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
                                       `buyTime` date NOT NULL COMMENT '服务购买日期',
                                       `maturityTime` date NOT NULL COMMENT '服务到期日',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of customer_nurse_item
-- ----------------------------
INSERT INTO `customer_nurse_item` VALUES ('58', '1', '40', '2', '-3', '0', '2023-10-17', '2024-01-17');
INSERT INTO `customer_nurse_item` VALUES ('59', '2', '40', '2', '56', '0', '2023-10-17', '2023-11-16');
INSERT INTO `customer_nurse_item` VALUES ('60', '3', '40', '2', '1', '0', '2023-10-17', '2024-01-17');
INSERT INTO `customer_nurse_item` VALUES ('61', '4', '40', '2', '80', '0', '2023-10-17', '2023-11-28');
INSERT INTO `customer_nurse_item` VALUES ('62', '1', '39', '3', '44', '0', '2023-10-17', '2024-01-17');
INSERT INTO `customer_nurse_item` VALUES ('63', '2', '39', '3', '100', '0', '2023-10-17', '2024-01-17');
INSERT INTO `customer_nurse_item` VALUES ('64', '3', '39', '3', '76', '0', '2023-10-17', '2024-01-17');
INSERT INTO `customer_nurse_item` VALUES ('65', '5', '39', '3', '80', '0', '2023-10-17', '2024-01-17');
INSERT INTO `customer_nurse_item` VALUES ('66', '6', '39', '3', '70', '0', '2023-10-17', '2024-01-17');
INSERT INTO `customer_nurse_item` VALUES ('67', '1', '36', null, '40', '0', '2023-10-21', '2024-01-21');
INSERT INTO `customer_nurse_item` VALUES ('68', '3', '36', null, '70', '0', '2023-10-21', '2024-01-21');
INSERT INTO `customer_nurse_item` VALUES ('69', '4', '36', null, '70', '0', '2023-10-21', '2024-01-21');
INSERT INTO `customer_nurse_item` VALUES ('70', '4', '36', null, '19', '0', '2023-10-21', '2024-01-21');
INSERT INTO `customer_nurse_item` VALUES ('71', '20', '40', null, '17', '0', '2023-10-23', '2024-01-23');
INSERT INTO `customer_nurse_item` VALUES ('72', '17', '40', null, '16', '0', '2023-10-23', '2024-01-23');
INSERT INTO `customer_nurse_item` VALUES ('73', '10', '40', null, '10', '0', '2023-10-23', '2024-01-23');
INSERT INTO `customer_nurse_item` VALUES ('74', '12', '40', null, '17', '0', '2023-10-23', '2024-01-23');
INSERT INTO `customer_nurse_item` VALUES ('75', '1', '35', '4', '98', '0', '2023-10-24', '2024-01-24');
INSERT INTO `customer_nurse_item` VALUES ('76', '2', '35', '4', '99', '0', '2023-10-24', '2024-01-24');
INSERT INTO `customer_nurse_item` VALUES ('77', '3', '35', '4', '100', '0', '2023-10-24', '2024-01-24');
INSERT INTO `customer_nurse_item` VALUES ('78', '5', '35', '4', '100', '0', '2023-10-24', '2024-01-24');
INSERT INTO `customer_nurse_item` VALUES ('79', '1', '41', '1', '0', '0', '2023-12-03', '2024-02-01');
INSERT INTO `customer_nurse_item` VALUES ('80', '2', '41', '1', '1', '0', '2023-12-03', '2024-03-03');
INSERT INTO `customer_nurse_item` VALUES ('81', '4', '41', '1', '1', '0', '2023-12-03', '2024-03-03');
INSERT INTO `customer_nurse_item` VALUES ('82', '5', '41', '1', '1', '0', '2023-12-03', '2024-03-03');
INSERT INTO `customer_nurse_item` VALUES ('83', '6', '41', '1', '1', '0', '2023-12-03', '2024-03-03');
INSERT INTO `customer_nurse_item` VALUES ('84', '20', '41', null, '1', '0', '2023-12-03', '2024-01-05');
INSERT INTO `customer_nurse_item` VALUES ('85', '1', '36', '1', '1', '0', '2023-12-15', '2024-03-15');
INSERT INTO `customer_nurse_item` VALUES ('86', '2', '36', '1', '1', '0', '2023-12-15', '2024-03-15');
INSERT INTO `customer_nurse_item` VALUES ('87', '4', '36', '1', '1', '0', '2023-12-15', '2024-03-15');
INSERT INTO `customer_nurse_item` VALUES ('88', '5', '36', '1', '1', '0', '2023-12-15', '2024-03-15');
INSERT INTO `customer_nurse_item` VALUES ('89', '6', '36', '1', '1', '0', '2023-12-15', '2024-01-17');
INSERT INTO `customer_nurse_item` VALUES ('90', '3', '41', null, '1', '0', '2023-12-15', '2024-01-17');

-- ----------------------------
-- Table structure for customer_preference
-- ----------------------------
DROP TABLE IF EXISTS `customer_preference`;
CREATE TABLE `customer_preference` (
                                       `id` int NOT NULL AUTO_INCREMENT COMMENT '喜好ID',
                                       `customerId` int DEFAULT NULL COMMENT '顾客ID',
                                       `preferences` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '饮食喜好',
                                       `attention` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '注意事项',
                                       `remark` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
                                       `isDeleted` int DEFAULT '0',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of customer_preference
-- ----------------------------
INSERT INTO `customer_preference` VALUES ('1', '29', '不吃辣，爱吃青菜，不爱吃肉', '风湿', '不辣多肉', '0');
INSERT INTO `customer_preference` VALUES ('2', '30', '爱吃甜品，不爱吃青菜，口味清淡', '糖尿病，轻度脂肪肝', '少糖，多青菜', '0');
INSERT INTO `customer_preference` VALUES ('3', '31', '爱吃辣，不吃内脏，口味清淡', '支气管炎', '多糖，少盐', '0');
INSERT INTO `customer_preference` VALUES ('9', '41', '多糖', '不建议摄入过多糖分', '尽量少糖', '0');
INSERT INTO `customer_preference` VALUES ('11', '41', '多糖', '不建议摄入过多糖分', '尽量少糖', '1');
INSERT INTO `customer_preference` VALUES ('12', '41', '多糖', '不建议摄入过多糖分', '尽量少糖', '1');
INSERT INTO `customer_preference` VALUES ('13', '27', 'asdsa', 'asds', 'asdsa', '1');
INSERT INTO `customer_preference` VALUES ('14', '32', 'qew', 'qwe', 'qwe', '1');
INSERT INTO `customer_preference` VALUES ('15', '40', '1', '1', '1', '1');
INSERT INTO `customer_preference` VALUES ('16', '27', 'qe', 'qw', 'qw', '1');
INSERT INTO `customer_preference` VALUES ('17', '34', null, null, null, '1');
INSERT INTO `customer_preference` VALUES ('18', '39', null, null, null, '1');
INSERT INTO `customer_preference` VALUES ('19', '33', 'qwe2', 'qwe', 'qwe1', '0');
INSERT INTO `customer_preference` VALUES ('20', '40', null, null, null, '1');
INSERT INTO `customer_preference` VALUES ('21', '32', null, null, null, '0');
INSERT INTO `customer_preference` VALUES ('22', '-1', 'asd', 'asd', 'asd', '0');
INSERT INTO `customer_preference` VALUES ('23', '27', 'asd', 'asd', 'asd', '1');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
                        `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `menuIndex` int DEFAULT NULL COMMENT '菜单级别',
                        `title` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单名称',
                        `icon` varchar(40) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '图标',
                        `path` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '路径',
                        `parentId` int NOT NULL COMMENT '副菜单 id',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `createBy` int NOT NULL COMMENT '创建者',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `updateBy` int DEFAULT NULL COMMENT '更新者',
                        `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '1', '床位管理', 'OfficeBuilding', '/bed', '0', '2024-08-24 16:20:57', '0', '2024-08-25 23:32:51', null, '0');
INSERT INTO `menu` VALUES ('2', '2', '客户管理', 'User', '/customer', '0', '2024-08-24 16:21:28', '0', '2024-08-25 23:32:51', null, '0');
INSERT INTO `menu` VALUES ('3', '3', '护理管理', 'Menu', '/nurse', '0', '2024-08-24 16:22:00', '0', '2024-08-29 09:22:55', null, '0');
INSERT INTO `menu` VALUES ('4', '4', '健康管家', 'FirstAidKit', '/health', '0', '2024-08-24 16:22:23', '0', '2024-08-25 23:32:51', null, '0');
INSERT INTO `menu` VALUES('5','5','膳食管理','Bowl','/food','0','2025-01-03','0','2025-01-03 22:11:11',null,'0');
INSERT INTO `menu` VALUES ('6', '6', '系统管理', 'Tools', '/user', '0', '2024-08-24 16:22:45', '0', '2024-08-25 23:32:51', null, '0');
INSERT INTO `menu` VALUES ('7', null, '膳食管理', 'Bowl', '/food/preference', '5', '2024-08-24 16:40:52', '0', '2024-08-24 16:40:52', null, '0');
INSERT INTO `menu` VALUES ('8', null, '膳食日历', 'Bowl', '/food/calendar', '5', '2024-08-24 16:41:25', '0', '2024-08-24 16:41:25', null, '0');
INSERT INTO `menu` VALUES ('9', null, '床位示意图', 'Picture', '/bed/bedMap', '1', '2024-08-24 16:23:20', '0', '2024-08-24 16:34:38', null, '0');
INSERT INTO `menu` VALUES ('10', null, '床位管理', 'Collection', '/bed/bedManage', '1', '2024-08-24 16:24:41', '0', '2024-08-24 16:24:41', null, '0');
INSERT INTO `menu` VALUES ('11', null, '入住登记', 'DArrowRight', '/customer/checkIn', '2', '2024-08-24 16:25:35', '0', '2024-08-24 16:25:35', null, '0');
INSERT INTO `menu` VALUES ('12', null, '退住登记', 'DArrowLeft', '/customer/checkOut', '2', '2024-08-24 16:26:58', '0', '2024-08-24 16:26:58', null, '0');
INSERT INTO `menu` VALUES ('13', null, '外出登记', 'Location', '/customer/outRecords', '2', '2024-08-24 16:27:34', '0', '2024-08-24 16:27:34', null, '0');
INSERT INTO `menu` VALUES ('14', null, '护理级别', 'Menu', '/nurse/nurseLevel', '3', '2024-08-24 16:28:27', '0', '2024-08-24 16:30:01', null, '0');
INSERT INTO `menu` VALUES ('15', null, '护理项目', 'Menu', '/nurse/nurseItem', '3', '2024-08-24 16:29:54', '0', '2024-08-24 16:29:54', null, '0');
INSERT INTO `menu` VALUES ('16', null, '客户护理设置', 'Setting', '/nurse/nurseSetting', '3', '2024-08-24 16:30:39', '0', '2024-08-24 16:30:39', null, '0');
INSERT INTO `menu` VALUES ('17', null, '护理记录', 'ScaleToOriginal', '/nurse/nurseRecords', '3', '2024-08-24 16:31:22', '0', '2024-08-24 16:31:22', null, '0');
INSERT INTO `menu` VALUES ('18', null, '设置服务对象', 'Setting', '/health/serviceCustomerS', '4', '2024-08-24 16:33:00', '0', '2024-08-24 16:33:00', null, '0');
INSERT INTO `menu` VALUES ('19', '2', '健康管家', 'FirstAidKit', '/health', '0', '2024-08-24 16:33:51', '0', '2024-08-25 23:32:51', null, '0');
INSERT INTO `menu` VALUES ('20', null, '日常护理', 'Menu', '/health/dailyNurse', '16', '2024-08-24 16:34:34', '0', '2024-08-24 16:34:34', null, '0');
INSERT INTO `menu` VALUES ('21', null, '服务关注', 'View', '/health/serviceCare', '4', '2024-08-24 16:36:27', '0', '2024-08-24 16:36:27', null, '0');
INSERT INTO `menu` VALUES ('22', null, '系统管理', 'Tools', '/user/listUser', '6', '2024-08-24 16:37:02', '0', '2024-08-24 16:37:02', null, '0');
INSERT INTO `menu` VALUES ('23', '1', '客户管理', 'User', '/customer', '0', '2024-08-24 16:37:28', '0', '2024-08-25 23:32:51', null, '0');
INSERT INTO `menu` VALUES ('24', null, '外出登记', 'Location', '/customer/outRecords', '20', '2024-08-24 16:38:43', '0', '2024-08-24 16:38:43', null, '0');
INSERT INTO `menu` VALUES ('25', null, '退住登记', 'Menu', '/customer/checkOut', '20', '2024-08-24 16:39:23', '0', '2024-08-24 16:39:23', null, '0');
INSERT INTO `menu` VALUES ('26', null, '护理记录', 'Menu', '/nurse/nurseRecordsToUser', '16', '2024-08-24 16:40:17', '0', '2024-08-24 16:40:17', null, '0');


-- ----------------------------
-- Table structure for nurse_content
-- ----------------------------
DROP TABLE IF EXISTS `nurse_content`;
CREATE TABLE `nurse_content` (
                                 `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `serialNumber` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '编号',
                                 `nursingName` varchar(55) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '名称',
                                 `servicePrice` varchar(55) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '价格',
                                 `message` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT ' 描述',
                                 `status` int NOT NULL COMMENT '状态 1：启用；2：停用',
                                 `executionCycle` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '执行周期',
                                 `executionTimes` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '执行次数',
                                 `isDeleted` int DEFAULT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nurse_content
-- ----------------------------
INSERT INTO `nurse_content` VALUES ('1', 'HLXM0001', '吸氧', '60元/次', 'asd', '1', '每天', '2', '0');
INSERT INTO `nurse_content` VALUES ('2', 'HLXM0002', '协助服药', '免费', '', '1', '每天', '3', '0');
INSERT INTO `nurse_content` VALUES ('3', 'HLXM0003', '测量血压', '免费', '', '1', '每天', '1', '0');
INSERT INTO `nurse_content` VALUES ('4', 'HLXM0004', '协助穿衣', '10元/次', null, '1', '每天', '2', '0');
INSERT INTO `nurse_content` VALUES ('5', 'HLXM0005', '床上洗头', '20元/次', null, '1', '每周', '3', '0');
INSERT INTO `nurse_content` VALUES ('6', 'HLXM0006', '健身', '10元/小时', '', '1', '每天', '1', '0');
INSERT INTO `nurse_content` VALUES ('7', 'HLXM0007', '清洁床铺', '10元/次', '', '1', '每周', '2', '0');
INSERT INTO `nurse_content` VALUES ('8', 'HLXM0008', '如厕服务', '20元/次', '', '1', '每天', '2', '0');
INSERT INTO `nurse_content` VALUES ('9', 'HLXM0009', '口腔清洁', '50元/次', '', '1', '每月', '2', '0');
INSERT INTO `nurse_content` VALUES ('10', 'HLXM0010', '假牙清洁', '20元/次', '', '1', '每周', '2', '0');
INSERT INTO `nurse_content` VALUES ('12', 'HLXM0012', '擦拭身体', '20元/次', '', '1', '每天', '1', '0');
INSERT INTO `nurse_content` VALUES ('14', 'HLXM0014', '体温测量', '免费', '', '1', '每天', '1', '0');
INSERT INTO `nurse_content` VALUES ('15', 'HLXM0015', '协助饮食', '50元/次', '', '1', '每天', '3', '0');
INSERT INTO `nurse_content` VALUES ('16', 'HLXM0016', '血压测量', '免费', '', '2', '每天', '1', '1');
INSERT INTO `nurse_content` VALUES ('17', 'HLXM0017', '剪指甲', '12/次', '', '1', '每月', '2', '0');
INSERT INTO `nurse_content` VALUES ('20', 'HLXM0019', '协助外出', '100/次', '', '1', '每月', '4', '0');
INSERT INTO `nurse_content` VALUES ('21', 'HLXM0020', '瑜伽辅导', '80/次', '', '1', '每周', '2', '0');
INSERT INTO `nurse_content` VALUES ('22', '123213', '123', '123', '123', '2', '每天', '2', '1');
INSERT INTO `nurse_content` VALUES ('23', 'asd', 'asd', 'asd', 'asd', '2', 'asd', '2', '1');
INSERT INTO `nurse_content` VALUES ('24', 'asd', 'asd', 'asd', 'asd', '2', 'asd', '1', '1');
INSERT INTO `nurse_content` VALUES ('25', 'HLM0011', 'aaa', '160元/次', '嗡嗡嗡', '2', '每天', '0', '0');

-- ----------------------------
-- Table structure for nurse_level
-- ----------------------------
DROP TABLE IF EXISTS `nurse_level`;
CREATE TABLE `nurse_level` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `levelName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '护理级别',
                               `levelStatus` int DEFAULT NULL COMMENT '级别状态 1：启用；2：停用',
                               `isDeleted` int DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nurse_level
-- ----------------------------
INSERT INTO `nurse_level` VALUES ('1', '一级', '1', '0');
INSERT INTO `nurse_level` VALUES ('2', '二级', '1', '0');
INSERT INTO `nurse_level` VALUES ('3', '三级', '1', '0');
INSERT INTO `nurse_level` VALUES ('4', '四级', '1', '0');
INSERT INTO `nurse_level` VALUES ('5', '五级', '1', '0');
INSERT INTO `nurse_level` VALUES ('7', '六级', '1', '0');
INSERT INTO `nurse_level` VALUES ('8', 'x', '2', '0');
INSERT INTO `nurse_level` VALUES ('9', '八级', '1', '0');
INSERT INTO `nurse_level` VALUES ('10', '十一阿哥', '2', '0');
INSERT INTO `nurse_level` VALUES ('11', '茱莉', '2', '1');
INSERT INTO `nurse_level` VALUES ('12', 'a手动', '2', '1');
INSERT INTO `nurse_level` VALUES ('13', '阿德撒旦', '2', '1');

-- ----------------------------
-- Table structure for nurse_level_item
-- ----------------------------
DROP TABLE IF EXISTS `nurse_level_item`;
CREATE TABLE `nurse_level_item` (
                                    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `levelId` int NOT NULL COMMENT '关联护理级别',
                                    `itemId` int NOT NULL COMMENT '关联护理项目',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    KEY `nurse_level_id_fk` (`levelId`) USING BTREE,
                                    KEY `nurse_item_id_fk` (`itemId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nurse_level_item
-- ----------------------------
INSERT INTO `nurse_level_item` VALUES ('25', '1', '4');
INSERT INTO `nurse_level_item` VALUES ('28', '2', '1');
INSERT INTO `nurse_level_item` VALUES ('29', '2', '2');
INSERT INTO `nurse_level_item` VALUES ('39', '3', '2');
INSERT INTO `nurse_level_item` VALUES ('40', '3', '3');
INSERT INTO `nurse_level_item` VALUES ('41', '3', '5');
INSERT INTO `nurse_level_item` VALUES ('53', '4', '1');
INSERT INTO `nurse_level_item` VALUES ('54', '4', '2');
INSERT INTO `nurse_level_item` VALUES ('55', '4', '3');
INSERT INTO `nurse_level_item` VALUES ('63', '1', '5');
INSERT INTO `nurse_level_item` VALUES ('71', '4', '5');
INSERT INTO `nurse_level_item` VALUES ('72', '3', '1');
INSERT INTO `nurse_level_item` VALUES ('73', '3', '6');
INSERT INTO `nurse_level_item` VALUES ('74', '1', '6');
INSERT INTO `nurse_level_item` VALUES ('76', '8', '2');
INSERT INTO `nurse_level_item` VALUES ('77', '8', '3');
INSERT INTO `nurse_level_item` VALUES ('78', '8', '14');
INSERT INTO `nurse_level_item` VALUES ('79', '9', '2');
INSERT INTO `nurse_level_item` VALUES ('80', '9', '4');
INSERT INTO `nurse_level_item` VALUES ('82', '9', '1');
INSERT INTO `nurse_level_item` VALUES ('83', '2', '3');
INSERT INTO `nurse_level_item` VALUES ('87', '2', '5');
INSERT INTO `nurse_level_item` VALUES ('91', '1', '1');

-- ----------------------------
-- Table structure for nurse_record
-- ----------------------------
DROP TABLE IF EXISTS `nurse_record`;
CREATE TABLE `nurse_record` (
                                `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `isDeleted` int NOT NULL COMMENT '逻辑删除标记（0：显示；1：隐藏）',
                                `customerId` int NOT NULL COMMENT '客户ID',
                                `itemId` int NOT NULL COMMENT '护理项目ID',
                                `nursingTime` datetime NOT NULL COMMENT '护理时间',
                                `nursingContent` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '护理内容',
                                `nursingCount` int NOT NULL COMMENT '护理数量',
                                `userId` int NOT NULL COMMENT '护理人员ID',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nurse_record
-- ----------------------------
INSERT INTO `nurse_record` VALUES ('9', '0', '40', '3', '2023-11-24 13:36:56', '日常血压测量', '1', '2');
INSERT INTO `nurse_record` VALUES ('10', '0', '40', '17', '2023-11-24 13:37:18', '指甲修剪金和保养', '1', '2');
INSERT INTO `nurse_record` VALUES ('11', '0', '40', '20', '2023-11-24 13:37:55', '陪同外出购物', '1', '2');
INSERT INTO `nurse_record` VALUES ('12', '0', '35', '2', '2023-11-24 13:38:37', '按照医嘱进行喂药', '1', '3');
INSERT INTO `nurse_record` VALUES ('13', '0', '35', '1', '2023-11-24 13:39:22', '仪器型号：XY09,吸氧服务', '1', '3');
INSERT INTO `nurse_record` VALUES ('14', '0', '39', '1', '2023-11-24 18:05:24', '吸氧护理', '1', '2');
INSERT INTO `nurse_record` VALUES ('15', '0', '40', '3', '2024-01-03 00:00:00', '测量血压', '1', '2');
INSERT INTO `nurse_record` VALUES ('16', '0', '41', '1', '2024-01-16 00:00:00', '', '1', '2');

-- ----------------------------
-- Table structure for outward
-- ----------------------------
DROP TABLE IF EXISTS `outward`;
CREATE TABLE `outward` (
                           `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `remarks` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
                           `isDeleted` int NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
                           `customerId` int NOT NULL COMMENT '客户ID',
                           `outgoingReason` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '外出事由',
                           `outgoingTime` datetime NOT NULL COMMENT '外出时间',
                           `expectedReturnTime` datetime NOT NULL COMMENT '预计回院时间',
                           `actualrReturnTime` datetime DEFAULT NULL COMMENT '实际回院时间',
                           `escorted` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '陪同人',
                           `relation` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '与老人关系',
                           `escortedTel` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '陪同人电话',
                           `auditStatus` int NOT NULL COMMENT '审批状态  0-已提交 1-同意  2-拒绝',
                           `auditPerson` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '审批人',
                           `auditTime` datetime DEFAULT NULL COMMENT '审批时间',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of outward
-- ----------------------------
INSERT INTO `outward` VALUES ('1', null, '0', '40', '想家', '2023-12-17 00:00:00', '2023-12-20 00:00:00', null, '女儿', '母女', '13312341234', '1', '1', '2023-12-18 00:00:00');
INSERT INTO `outward` VALUES ('2', null, '0', '33', '出门', '2023-12-17 00:00:00', '2023-12-19 00:00:00', null, '儿子', null, '13812341234', '2', null, '2024-08-31 00:00:00');
INSERT INTO `outward` VALUES ('3', null, '0', '33', '出门', '2023-12-17 00:00:00', '2023-12-20 00:00:00', '2023-12-29 00:00:00', '儿子', null, '13812341234', '0', null, null);
INSERT INTO `outward` VALUES ('4', null, '1', '33', '出门', '2023-12-18 00:00:00', '2023-12-20 00:00:00', null, '儿子', null, '13312341234', '0', null, null);
INSERT INTO `outward` VALUES ('5', null, '0', '40', '外出业务', '2024-01-03 00:00:00', '2024-01-06 00:00:00', '2024-01-07 00:00:00', '儿子', null, '13819870987', '1', '1', '2024-01-03 00:00:00');
INSERT INTO `outward` VALUES ('6', null, '0', '39', '回家', '2024-01-15 00:00:00', '2024-01-17 00:00:00', null, '儿子', null, '13312341234', '0', '', null);
INSERT INTO `outward` VALUES ('7', 'a', '1', '33', '1', '2024-11-03 00:00:00', '2024-11-03 00:00:00', '2024-11-03 00:00:00', 'a', 'a', '111', '0', '', null);
INSERT INTO `outward` VALUES ('8', '111', '1', '27', 'asd', '2002-12-03 00:00:00', '2002-12-03 00:00:00', '2002-12-03 00:00:00', '2002-12-03', '2002-12-03', '2002-12-03', '0', '', null);
INSERT INTO `outward` VALUES ('9', '111', '1', '27', 'asd', '2002-12-03 00:00:00', '2002-12-03 00:00:00', '2002-12-03 00:00:00', '2002-12-03', '2002-12-03', '2002-12-03', '0', '', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `roleName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
                        `roleCode` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色编码',
                        `description` varchar(510) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '描述',
                        `menuId` int DEFAULT NULL COMMENT '菜单 id',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `createBy` int NOT NULL COMMENT '创建者',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `updateBy` int DEFAULT NULL COMMENT '更新者',
                        `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '1', '管理员', null, '2024-08-24 16:49:10', '0', '2024-08-24 16:49:10', null, '0');
INSERT INTO `role` VALUES ('2', '健康管家', '2', '健康管家', null, '2024-08-24 16:49:33', '0', '2024-08-24 16:49:33', null, '0');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
                             `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `roleId` int NOT NULL COMMENT '角色 id',
                             `menuId` int NOT NULL COMMENT '菜单 id',
                             `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `createBy` int NOT NULL COMMENT '创建者',
                             `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `updateBy` int DEFAULT NULL COMMENT '更新者',
                             `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '1', '2024-08-24 16:07:36', '0', '2024-08-24 16:07:36', null, '0');
INSERT INTO `role_menu` VALUES ('2', '1', '2', '2024-08-24 16:10:42', '0', '2024-08-24 16:10:42', null, '0');
INSERT INTO `role_menu` VALUES ('3', '1', '3', '2024-08-24 16:43:53', '0', '2024-08-24 16:43:53', null, '0');
INSERT INTO `role_menu` VALUES ('4', '1', '4', '2024-08-24 16:44:00', '0', '2024-08-24 16:44:00', null, '0');
INSERT INTO `role_menu` VALUES ('5', '1', '5', '2024-08-24 16:44:06', '0', '2024-08-24 16:44:06', null, '0');
INSERT INTO `role_menu` VALUES ('6', '1', '6', '2024-08-24 16:44:15', '0', '2024-08-24 16:44:15', null, '0');
INSERT INTO `role_menu` VALUES ('7', '2', '20', '2024-08-24 16:44:25', '0', '2024-09-09 10:47:07', null, '0');
INSERT INTO `role_menu` VALUES ('8', '2', '16', '2024-08-24 16:44:36', '0', '2024-09-09 10:48:23', null, '0');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
                        `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `roomFloor` varchar(20) NOT NULL COMMENT '房间楼层',
                        `roomNo` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '房间号',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `createBy` int DEFAULT NULL COMMENT '创建者',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `updateBy` int DEFAULT NULL COMMENT '更新者',
                        `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '一楼', '1001', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('2', '一楼', '1002', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('3', '一楼', '1003', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('4', '一楼', '1004', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('5', '一楼', '1005', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('6', '一楼', '1006', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('7', '一楼', '1007', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('8', '一楼', '1008', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('9', '一楼', '1009', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('10', '一楼', '1010', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('11', '一楼', '1011', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('12', '一楼', '1012', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('13', '一楼', '1013', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('14', '一楼', '1014', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('15', '一楼', '1015', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('16', '一楼', '1016', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('17', '一楼', '1017', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('18', '一楼', '1018', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('19', '二楼', '2001', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('20', '二楼', '2002', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('21', '二楼', '2003', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('22', '二楼', '2004', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('23', '二楼', '2005', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('24', '二楼', '2006', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('25', '二楼', '2007', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('26', '二楼', '2008', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('27', '二楼', '2009', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('28', '二楼', '2010', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('29', '二楼', '2011', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('30', '二楼', '2012', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('31', '二楼', '2013', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('32', '二楼', '2014', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('33', '二楼', '2015', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('34', '二楼', '2016', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('35', '二楼', '2017', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');
INSERT INTO `room` VALUES ('36', '二楼', '2018', '2024-08-26 11:26:38', null, '2024-08-26 11:26:38', null, '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `createBy` int DEFAULT NULL COMMENT '创建者',
                        `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        `updateBy` int DEFAULT NULL COMMENT '更新者',
                        `isDelete` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                        `nickname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '真实姓名',
                        `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '系统账号',
                        `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
                        `sex` int NOT NULL DEFAULT '0' COMMENT '性别（0：女  1：男）',
                        `email` varchar(254) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '邮箱',
                        `phoneNumber` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电话号码',
                        `roleId` int DEFAULT NULL COMMENT '系统角色编号（1-管理员 2-健康管家）',
                        `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '2023-11-01 00:00:00', '0', '2024-09-06 00:00:00', '0', '0', 'admin', 'admin', 'admin', '1', 'zhangxing@s.cq', '13545453412', '1', 'bl7aajxk40ecupdsr011oe7e015p5yfd.jpg');
INSERT INTO `user` VALUES ('2', '2023-11-02 00:00:00', '0', '2024-09-06 00:00:00', '0', '0', '吴伟', 'wuwei', 'wuwei', '1', 'wuwei@s.cq', '13517178987', '2', 'mu0pnpysn3ofwrh1wm1hs7g9817idrmc.jpg');
INSERT INTO `user` VALUES ('3', '2023-11-20 00:00:00', '0', '2024-09-06 00:00:00', '0', '0', '陈小明', 'chenxiaoming', 'chenxiaoming', '0', 'chenxiaoming@s.cq', '18767890987', '2', '7jhvsj46s8rw3cvb0y8eb2x58y9loujq.jpg');
INSERT INTO `user` VALUES ('4', '2023-11-20 00:00:00', '0', '2024-09-06 00:00:00', '0', '0', '张兴', 'zhangxing', 'zhangxing', '1', 'zhangxing@s.cq', '18878675643', '2', 'dqnvd99xm9xzhc2p3fv9c8dhsve4avgz.jpeg');
INSERT INTO `user` VALUES ('5', '2023-11-20 00:00:00', '0', '2024-09-06 00:00:00', '0', '0', '王年华', 'wangnianhua', 'wangnianhua', '0', 'wangnianhua@s.cq', '13412128976', '2', 'zvjlqv9cem6m0xfdoolx7c6egxvw587u.jpeg');
INSERT INTO `user` VALUES ('6', '2023-11-20 00:00:00', '0', '2024-09-06 00:00:00', '0', '0', '吴优', 'wuyou', 'wuyou', '0', 'wuyou@s.cq', '13567452789', '2', 'ofixw7u7zeigpoulw9bzortfedhx8opb.jpg');
INSERT INTO `user` VALUES ('7', '2024-08-23 15:49:18', null, '2024-09-06 16:57:57', null, '0', null, 'aaaa', '123', '0', null, null, null, '0b50c728d0564f6dba2cd4f00791b415.jpg');
INSERT INTO `user` VALUES ('8', '2024-09-07 22:11:56', null, '2024-09-07 22:26:54', null, '1', '耿志超', 'zrr', '1231123123', '1', '2295700936@qq.com', '15831290872', '2', '');
INSERT INTO `user` VALUES ('9', '2024-09-07 22:12:38', null, '2024-09-07 22:26:45', null, '1', '耿志超', 'zrr', '123123123', '1', '2295700936@qq.com', '15831290872', '2', '');
INSERT INTO `user` VALUES ('10', '2024-09-07 22:13:31', null, '2024-09-07 22:13:31', null, '0', '耿志超', 'zrr', '123123123', '1', '2295700936@qq.com', '15831290872', '2', 'xo5iv025mdvjturfu2c2q9rf5ie6hb5c.jpg');
INSERT INTO `user` VALUES ('11', '2024-09-07 22:19:04', null, '2024-09-07 22:19:04', null, '0', '耿志超', 'zrr', '123123123', '0', '2295700936@qq.com', '15831290872', '2', 'd13769m8i9jrnvqdoalx64dznhkrnq36.png');


-- ----------------------------
-- Table structure for dietary
-- ----------------------------
DROP TABLE IF EXISTS `dietary`;
CREATE TABLE `dietary`  (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '膳食ID',
                            `isDeleted` int NULL DEFAULT NULL COMMENT '删除标志',
                            `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '膳食类型',
                            `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '膳食名称',
                            `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
                            `Islamic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '清真',
                            `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片',
                            `createOn` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dietary
-- ----------------------------
INSERT INTO `dietary` VALUES (1, 0, '主食', '面包', 15.00, '0', 'bread.jpg', '2024-09-09 14:52:32');
INSERT INTO `dietary` VALUES (2, 0, '大荤', '白灼虾', 20.00, '0', 'shrimp.jpg', '2024-09-09 14:52:32');
INSERT INTO `dietary` VALUES (3, 0, '小荤', '宫保鸡丁', 20.00, '0', 'chicken.jpg', '2024-09-09 14:52:32');
INSERT INTO `dietary` VALUES (4, 0, '素材', '白灼芥兰', 5.00, '0', 'arabidopsis.jpg', '2024-09-09 14:52:32');
INSERT INTO `dietary` VALUES (5, 0, '汤', '素烩汤', 5.00, '0', 'chowder_soup.jpg', '2024-09-09 14:52:32');
INSERT INTO `dietary` VALUES (6, 0, '主食', '小米粥', 3.00, '0', 'congee.jpg', '2024-09-09 14:52:32');
INSERT INTO `dietary` VALUES (7, 0, '大荤', '红烧牛肉', 25.00, '1', 'beef.jpg', '2024-09-09 14:52:32');
INSERT INTO `dietary` VALUES (8, 0, '主食', '燕麦粥', 5.00, '0', 'oatmeal.jpg', '2024-09-09 14:52:32');
INSERT INTO `dietary` VALUES (9, 0, '主食', '欢喜坨', 10.00, '0', 'hxt.jpg', '2024-09-09 14:52:32');

-- ----------------------------
-- Table structure for dietary_calerdar
-- ----------------------------
DROP TABLE IF EXISTS `dietary_calerdar`;
CREATE TABLE `dietary_calerdar`  (
                                     `id` int NOT NULL AUTO_INCREMENT COMMENT '膳食日历ID',
                                     `dietaryId` int NOT NULL COMMENT '膳食编号',
                                     `dietaryType` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '早餐中餐晚餐',
                                     `taste` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '口味（多糖、多盐、少糖、少盐）',
                                     `createOn` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                                     `customerId` int NOT NULL COMMENT '客户id',
                                     `date` date NOT NULL COMMENT '膳食时间time',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dietary_calerdar
-- ----------------------------
INSERT INTO `dietary_calerdar` VALUES (11, 1, '早餐', '多糖', NULL, 1, '2024-09-01');
INSERT INTO `dietary_calerdar` VALUES (12, 2, '午餐', '少盐', NULL, 1, '2024-09-01');
INSERT INTO `dietary_calerdar` VALUES (13, 3, '晚餐', '多盐', NULL, 1, '2024-09-01');
INSERT INTO `dietary_calerdar` VALUES (14, 4, '早餐', '少盐', NULL, 1, '2024-09-02');
INSERT INTO `dietary_calerdar` VALUES (15, 5, '午餐', '多糖', NULL, 1, '2024-09-02');
INSERT INTO `dietary_calerdar` VALUES (16, 6, '晚餐', '少盐', NULL, 1, '2024-09-02');
INSERT INTO `dietary_calerdar` VALUES (17, 7, '早餐', '多糖', NULL, 1, '2024-09-03');
INSERT INTO `dietary_calerdar` VALUES (18, 8, '午餐', '少糖', NULL, 1, '2024-09-03');
INSERT INTO `dietary_calerdar` VALUES (19, 2, '晚餐', '少糖', NULL, 1, '2024-09-03');
INSERT INTO `dietary_calerdar` VALUES (20, 1, '早餐', '少盐', NULL, 1, '2024-09-04');
INSERT INTO `dietary_calerdar` VALUES (21, 1, '午餐', '多糖', NULL, 1, '2024-09-04');
INSERT INTO `dietary_calerdar` VALUES (22, 1, '晚餐', '少糖', NULL, 1, '2024-09-04');
INSERT INTO `dietary_calerdar` VALUES (23, 1, '早餐', '多盐', NULL, 1, '2024-09-05');
INSERT INTO `dietary_calerdar` VALUES (24, 1, '午餐', '少盐', NULL, 1, '2024-09-05');
INSERT INTO `dietary_calerdar` VALUES (25, 1, '晚餐', '多糖', NULL, 1, '2024-09-05');
INSERT INTO `dietary_calerdar` VALUES (26, 1, '早餐', '少糖', NULL, 1, '2024-09-06');
INSERT INTO `dietary_calerdar` VALUES (27, 1, '午餐', '多盐', NULL, 1, '2024-09-06');
INSERT INTO `dietary_calerdar` VALUES (28, 1, '晚餐', '少盐', NULL, 1, '2024-09-06');
INSERT INTO `dietary_calerdar` VALUES (29, 1, '早餐', '多糖', NULL, 1, '2024-09-07');
INSERT INTO `dietary_calerdar` VALUES (30, 2, '午餐', '少糖', NULL, 1, '2024-09-07');
INSERT INTO `dietary_calerdar` VALUES (31, 6, '早餐', '少糖', '2024-09-09 19:31:19', 1, '2024-09-18');
INSERT INTO `dietary_calerdar` VALUES (32, 5, '早餐', '多盐', '2024-09-09 19:33:26', 1, '2024-09-18');
INSERT INTO `dietary_calerdar` VALUES (33, 4, '午餐', '少盐', '2024-09-09 19:34:00', 1, '2024-09-17');
INSERT INTO `dietary_calerdar` VALUES (34, 5, '午餐', '少糖', '2024-09-09 19:34:08', 1, '2024-09-18');
INSERT INTO `dietary_calerdar` VALUES (35, 4, '午餐', '少盐', '2024-09-09 19:34:55', 1, '2024-09-18');
INSERT INTO `dietary_calerdar` VALUES (36, 4, '午餐', '少盐', '2024-09-09 19:35:07', 1, '2024-09-19');
INSERT INTO `dietary_calerdar` VALUES (37, 5, '早餐', '少糖', '2024-09-09 19:38:07', 1, '2024-09-13');
INSERT INTO `dietary_calerdar` VALUES (38, 4, '早餐', '少盐', '2024-09-09 19:38:37', 1, '2024-09-16');
INSERT INTO `dietary_calerdar` VALUES (39, 8, '晚餐', '少糖', '2024-09-09 19:39:28', 1, '2024-09-09');
INSERT INTO `dietary_calerdar` VALUES (40, 3, '午餐', '少糖', '2024-09-09 19:40:13', 1, '2024-09-10');
INSERT INTO `dietary_calerdar` VALUES (41, 2, '午餐', '多糖', '2024-09-09 19:40:19', 1, '2024-09-11');
INSERT INTO `dietary_calerdar` VALUES (43, 1, '早餐', '少糖', '2024-09-09 19:45:53', 1, '2024-09-17');
INSERT INTO `dietary_calerdar` VALUES (44, 2, '早餐', '少糖', '2024-09-09 20:28:59', 2, '2024-09-02');
INSERT INTO `dietary_calerdar` VALUES (45, 4, '早餐', '少盐', '2024-09-09 20:29:04', 2, '2024-09-03');
INSERT INTO `dietary_calerdar` VALUES (46, 2, '早餐', '多糖', '2024-09-09 20:36:34', 3, '2024-09-01');
INSERT INTO `dietary_calerdar` VALUES (47, 2, '早餐', '多盐', '2024-09-09 20:44:45', 2, '2024-09-01');
INSERT INTO `dietary_calerdar` VALUES (48, 6, '晚餐', '少盐', '2024-09-09 20:44:51', 2, '2024-09-01');
INSERT INTO `dietary_calerdar` VALUES (49, 1, '午餐', '多糖', '2024-09-09 21:17:46', 2, '2024-09-02');
INSERT INTO `dietary_calerdar` VALUES (50, 3, '午餐', '多盐', '2024-09-09 21:18:56', 2, '2024-09-04');
INSERT INTO `dietary_calerdar` VALUES (51, 2, '午餐', '多盐', '2024-09-09 21:19:42', 2, '2024-09-05');
INSERT INTO `dietary_calerdar` VALUES (52, 1, '早餐', '少盐', '2024-09-09 21:21:04', 1, '2024-09-09');
INSERT INTO `dietary_calerdar` VALUES (53, 3, '午餐', '多盐', '2024-09-09 21:21:16', 1, '2024-09-09');
INSERT INTO `dietary_calerdar` VALUES (54, 3, '早餐', '少盐', '2024-09-09 21:22:38', 1, '2024-09-10');
INSERT INTO `dietary_calerdar` VALUES (55, 1, '晚餐', '多糖', '2024-09-09 21:24:08', 1, '2024-09-16');