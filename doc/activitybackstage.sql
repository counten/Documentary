/*
Navicat MariaDB Data Transfer

Source Server         : aliyun
Source Server Version : 50552
Source Host           : 120.77.219.167:3306
Source Database       : activitybackstage

Target Server Type    : MariaDB
Target Server Version : 50552
File Encoding         : 65001

Date: 2017-10-24 19:03:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` bigint(20) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `participants` varchar(255) DEFAULT NULL,
  `participantsNum` bigint(20) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `userAccount` varchar(255) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userKind` int(11) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userType` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('216');
INSERT INTO `hibernate_sequence` VALUES ('216');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `account` varchar(255) DEFAULT NULL,
  `memberNum` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `numCheck` int(11) DEFAULT NULL,
  `numDelete` int(11) DEFAULT NULL,
  `numNotPass` int(11) DEFAULT NULL,
  `numPass` int(11) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `pparentId` bigint(20) DEFAULT NULL,
  `secretaryName` varchar(255) DEFAULT NULL,
  `secretaryTel` varchar(255) DEFAULT NULL,
  `userKind` int(11) DEFAULT NULL,
  `userType` int(11) DEFAULT NULL,
  `participantsNum` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'cqtw', null, '重庆市团委', '0', '0', '0', '0', null, '123456', null, null, null, null, '1', '0');
INSERT INTO `user` VALUES ('3', 'A01', null, '万州区', '0', '0', '0', '0', null, 'cqtwA01', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('4', 'A02', null, '黔江区', '0', '0', '0', '0', null, 'cqtwA02', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('5', 'A03', null, '涪陵区', '0', '0', '0', '0', null, 'cqtwA03', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('6', 'A04', null, '渝中区', '0', '0', '0', '0', null, 'cqtwA04', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('7', 'A05', null, '大渡口区', '0', '0', '0', '0', null, 'cqtwA05', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('8', 'A06', null, '江北区', '0', '0', '0', '0', null, 'cqtwA06', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('9', 'A07', null, '沙坪坝区', '0', '0', '0', '0', null, 'cqtwA07', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('10', 'A08', null, '九龙坡区', '0', '0', '0', '0', null, 'cqtwA08', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('11', 'A09', null, '南岸区', '0', '0', '0', '0', null, 'cqtwA09', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('12', 'A10', null, '北碚区', '0', '0', '0', '0', null, 'cqtwA10', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('13', 'A11', null, '渝北区', '0', '0', '0', '0', null, 'cqtwA11', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('14', 'A12', null, '巴南区', '0', '0', '0', '0', null, 'cqtwA12', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('15', 'A13', null, '长寿区', '0', '0', '0', '0', null, 'cqtwA13', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('16', 'A14', null, '江津区', '0', '0', '0', '0', null, 'cqtwA14', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('17', 'A15', null, '合川区', '0', '0', '0', '0', null, 'cqtwA15', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('18', 'A16', null, '永川区', '0', '0', '0', '0', null, 'cqtwA16', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('19', 'A17', null, '南川区', '0', '0', '0', '0', null, 'cqtwA17', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('20', 'A18', null, '綦江区', '0', '0', '0', '0', null, 'cqtwA18', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('21', 'A19', null, '大足区', '0', '0', '0', '0', null, 'cqtwA19', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('22', 'A20', null, '璧山区', '0', '0', '0', '0', null, 'cqtwA20', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('23', 'A21', null, '铜梁区', '0', '0', '0', '0', null, 'cqtwA21', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('24', 'A22', null, '潼南区', '0', '0', '0', '0', null, 'cqtwA22', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('25', 'A23', null, '荣昌区', '0', '0', '0', '0', null, 'cqtwA23', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('26', 'A24', null, '开州区', '0', '0', '0', '0', null, 'cqtwA24', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('27', 'A25', null, '梁平区', '0', '0', '0', '0', null, 'cqtwA25', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('28', 'A26', null, '武隆区', '0', '0', '0', '0', null, 'cqtwA26', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('29', 'A27', null, '城口县', '0', '0', '0', '0', null, 'cqtwA27', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('30', 'A28', null, '丰都县', '0', '0', '0', '0', null, 'cqtwA28', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('31', 'A29', null, '垫江县', '0', '0', '0', '0', null, 'cqtwA29', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('32', 'A30', null, '忠县', '0', '0', '0', '0', null, 'cqtwA30', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('33', 'A31', null, '云阳县', '0', '0', '0', '0', null, 'cqtwA31', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('34', 'A32', null, '奉节县', '0', '0', '0', '0', null, 'cqtwA32', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('35', 'A33', null, '巫山县', '0', '0', '0', '0', null, 'cqtwA33', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('36', 'A34', null, '巫溪县', '0', '0', '0', '0', null, 'cqtwA34', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('37', 'A35', null, '石柱县', '0', '0', '0', '0', null, 'cqtwA35', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('38', 'A36', null, '秀山县', '0', '0', '0', '0', null, 'cqtwA36', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('39', 'A37', null, '酉阳县', '0', '0', '0', '0', null, 'cqtwA37', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('40', 'A38', null, '彭水县', '0', '0', '0', '0', null, 'cqtwA38', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('41', 'A39', null, '万盛经开区', '0', '0', '0', '0', null, 'cqtwA39', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('42', 'A40', null, '驻北京团工委', '0', '0', '0', '0', null, 'cqtwA40', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('43', 'A41', null, '驻广东团工委', '0', '0', '0', '0', null, 'cqtwA41', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('44', 'A42', null, '驻上海团工委', '0', '0', '0', '0', null, 'cqtwA42', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('45', 'A43', null, '驻江苏团工委', '0', '0', '0', '0', null, 'cqtwA43', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('46', 'A44', null, '驻浙江团工委', '0', '0', '0', '0', null, 'cqtwA44', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('47', 'A45', null, '驻福建团工委', '0', '0', '0', '0', null, 'cqtwA45', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('48', 'A46', null, '驻新疆团工委', '0', '0', '0', '0', null, 'cqtwA46', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('49', 'A47', null, '河南驻渝团工委', '0', '0', '0', '0', null, 'cqtwA47', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('50', 'A48', null, '四川驻渝团工委', '0', '0', '0', '0', null, 'cqtwA48', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('51', 'A49', null, '贵州驻渝团工委', '0', '0', '0', '0', null, 'cqtwA49', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('52', 'A50', null, '山东驻渝团工委', '0', '0', '0', '0', null, 'cqtwA50', null, null, null, '1', '2', '0');
INSERT INTO `user` VALUES ('53', 'B01', null, '市国资委', '0', '0', '0', '0', null, 'cqtwB01', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('54', 'B02', null, '市直机关', '0', '0', '0', '0', null, 'cqtwB02', null, '', '', '2', '2', '0');
INSERT INTO `user` VALUES ('55', 'B03', null, '两江新区', '0', '0', '0', '0', null, 'cqtwB03', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('56', 'B04', null, '市科委', '0', '0', '0', '0', null, 'cqtwB04', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('57', 'B05', null, '市公安局', '0', '0', '0', '0', null, 'cqtwB05', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('58', 'B06', null, '市国安局', '0', '0', '0', '0', null, 'cqtwB06', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('59', 'B07', null, '市民政局', '0', '0', '0', '0', null, 'cqtwB07', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('60', 'B08', null, '市司法局', '0', '0', '0', '0', null, 'cqtwB08', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('61', 'B09', null, '市城管委', '0', '0', '0', '0', null, 'cqtwB09', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('62', 'B10', null, '市交委', '0', '0', '0', '0', null, 'cqtwB10', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('63', 'B11', null, '市水利局', '0', '0', '0', '0', null, 'cqtwB11', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('64', 'B12', null, '市农委', '0', '0', '0', '0', null, 'cqtwB12', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('65', 'B13', null, '市商务委', '0', '0', '0', '0', null, 'cqtwB13', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('66', 'B14', null, '市文化委', '0', '0', '0', '0', null, 'cqtwB14', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('67', 'B15', null, '市卫计委', '0', '0', '0', '0', null, 'cqtwB15', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('68', 'B16', null, '市供销社', '0', '0', '0', '0', null, 'cqtwB16', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('69', 'B17', null, '重庆日报', '0', '0', '0', '0', null, 'cqtwB17', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('70', 'B18', null, '广电集团', '0', '0', '0', '0', null, 'cqtwB18', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('71', 'B19', null, '新华书店集团', '0', '0', '0', '0', null, 'cqtwB19', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('72', 'B20', null, '市设计院', '0', '0', '0', '0', null, 'cqtwB20', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('73', 'B21', null, '中机中联公司', '0', '0', '0', '0', null, 'cqtwB21', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('74', 'B22', null, '中煤重庆设计院', '0', '0', '0', '0', null, 'cqtwB22', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('75', 'B23', null, '庆铃集团', '0', '0', '0', '0', null, 'cqtwB23', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('76', 'B24', null, '重钢集团', '0', '0', '0', '0', null, 'cqtwB24', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('77', 'B25', null, '化医集团', '0', '0', '0', '0', null, 'cqtwB25', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('78', 'B26', null, '轻纺集团', '0', '0', '0', '0', null, 'cqtwB26', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('79', 'B27', null, '机电集团', '0', '0', '0', '0', null, 'cqtwB27', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('80', 'B28', null, '能源集团', '0', '0', '0', '0', null, 'cqtwB28', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('81', 'B29', null, '四联集团', '0', '0', '0', '0', null, 'cqtwB29', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('82', 'B30', null, '建工集团', '0', '0', '0', '0', null, 'cqtwB30', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('83', 'B31', null, '保税港区', '0', '0', '0', '0', null, 'cqtwB31', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('84', 'B32', null, '水务集团', '0', '0', '0', '0', null, 'cqtwB32', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('85', 'B33', null, '旅投集团', '0', '0', '0', '0', null, 'cqtwB33', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('86', 'B34', null, '西永微电园', '0', '0', '0', '0', null, 'cqtwB34', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('87', 'B35', null, '重庆银行', '0', '0', '0', '0', null, 'cqtwB35', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('88', 'B36', null, '港务集团', '0', '0', '0', '0', null, 'cqtwB36', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('89', 'B37', null, '交运集团', '0', '0', '0', '0', null, 'cqtwB37', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('90', 'B38', null, '商社集团', '0', '0', '0', '0', null, 'cqtwB38', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('91', 'B39', null, '电力公司', '0', '0', '0', '0', null, 'cqtwB39', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('92', 'B40', null, '烟草公司', '0', '0', '0', '0', null, 'cqtwB40', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('93', 'B41', null, '邮政公司', '0', '0', '0', '0', null, 'cqtwB41', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('94', 'B42', null, '电信公司', '0', '0', '0', '0', null, 'cqtwB42', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('95', 'B43', null, '移动公司', '0', '0', '0', '0', null, 'cqtwB43', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('96', 'B44', null, '联通公司', '0', '0', '0', '0', null, 'cqtwB44', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('97', 'B45', null, '船舶工业', '0', '0', '0', '0', null, 'cqtwB45', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('98', 'B46', null, '长江轮船', '0', '0', '0', '0', null, 'cqtwB46', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('99', 'B47', null, '中冶赛迪', '0', '0', '0', '0', null, 'cqtwB47', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('100', 'B48', null, '西南铝', '0', '0', '0', '0', null, 'cqtwB48', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('101', 'B49', null, '华能珞璜', '0', '0', '0', '0', null, 'cqtwB49', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('102', 'B50', null, '川维厂', '0', '0', '0', '0', null, 'cqtwB50', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('103', 'B51', null, '中冶建工', '0', '0', '0', '0', null, 'cqtwB51', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('104', 'B52', null, '中石油重庆分公司', '0', '0', '0', '0', null, 'cqtwB52', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('105', 'B53', null, '公交集团', '0', '0', '0', '0', null, 'cqtwB53', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('106', 'B54', null, '重庆航空', '0', '0', '0', '0', null, 'cqtwB54', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('107', 'B55', null, '机场集团', '0', '0', '0', '0', null, 'cqtwB55', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('108', 'B56', null, '重庆铁路联合团委', '0', '0', '0', '0', null, 'cqtwB56', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('109', 'B57', null, '长航代表处', '0', '0', '0', '0', null, 'cqtwB57', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('110', 'B58', null, '嘉陵股份', '0', '0', '0', '0', null, 'cqtwB58', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('111', 'B59', null, '嘉陵特装', '0', '0', '0', '0', null, 'cqtwB59', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('112', 'B60', null, '长安工业', '0', '0', '0', '0', null, 'cqtwB60', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('113', 'B61', null, '长安汽车', '0', '0', '0', '0', null, 'cqtwB61', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('114', 'B62', null, '建设工业', '0', '0', '0', '0', null, 'cqtwB62', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('115', 'B63', null, '建设机电', '0', '0', '0', '0', null, 'cqtwB63', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('116', 'B64', null, '中央在渝企业团工委', '0', '0', '0', '0', null, 'cqtwB64', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('117', 'B65', null, '富士康', '0', '0', '0', '0', null, 'cqtwB65', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('118', 'B66', null, '力帆集团', '0', '0', '0', '0', null, 'cqtwB66', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('119', 'B67', null, '金科地产', '0', '0', '0', '0', null, 'cqtwB67', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('120', 'B68', null, '中科控股', '0', '0', '0', '0', null, 'cqtwB68', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('121', 'B69', null, '陶然居集团', '0', '0', '0', '0', null, 'cqtwB69', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('122', 'B70', null, '世纪金源', '0', '0', '0', '0', null, 'cqtwB70', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('123', 'B71', null, '猪八戒网络', '0', '0', '0', '0', null, 'cqtwB71', null, null, null, '2', '2', '0');
INSERT INTO `user` VALUES ('124', 'C01', null, '重庆大学', '0', '0', '0', '0', null, 'cqtwC01', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('125', 'C02', null, '西南大学', '0', '0', '0', '0', null, 'cqtwC02', null, '', '', '3', '2', '0');
INSERT INTO `user` VALUES ('126', 'C03', null, '西南政法大学', '0', '0', '0', '0', null, 'cqtwC03', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('127', 'C04', null, '重庆医科大学', '0', '0', '0', '0', null, 'cqtwC04', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('128', 'C05', null, '重庆师范大学', '0', '0', '0', '0', null, 'cqtwC05', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('129', 'C06', null, '重庆邮电大学', '0', '0', '0', '0', null, 'cqtwC06', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('130', 'C07', null, '重庆交通大学', '0', '0', '0', '0', null, 'cqtwC07', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('131', 'C08', null, '重庆工商大学', '0', '0', '0', '0', null, 'cqtwC08', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('132', 'C09', null, '四川外国语大学', '0', '0', '0', '0', null, 'cqtwC09', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('133', 'C10', null, '四川美术学院', '0', '0', '0', '0', null, 'cqtwC10', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('134', 'C11', null, '重庆理工大学', '0', '0', '0', '0', null, 'cqtwC11', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('135', 'C12', null, '重庆三峡学院', '0', '0', '0', '0', null, 'cqtwC12', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('136', 'C13', null, '重庆文理学院', '0', '0', '0', '0', null, 'cqtwC13', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('137', 'C14', null, '长江师范学院', '0', '0', '0', '0', null, 'cqtwC14', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('138', 'C15', null, '重庆科技学院', '0', '0', '0', '0', null, 'cqtwC15', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('139', 'C16', null, '重庆第二师范学院', '0', '0', '0', '0', null, 'cqtwC16', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('140', 'C17', null, '市经信委', '0', '0', '0', '0', null, 'cqtwC17', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('141', 'C18', null, '三峡医药高专', '0', '0', '0', '0', null, 'cqtwC18', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('142', 'C19', null, '航天职业技术学院', '0', '0', '0', '0', null, 'cqtwC19', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('143', 'C20', null, '电子工程职业学院', '0', '0', '0', '0', null, 'cqtwC20', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('144', 'C21', null, '工业职业技术学院', '0', '0', '0', '0', null, 'cqtwC21', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('145', 'C22', null, '城市管理职业学院', '0', '0', '0', '0', null, 'cqtwC22', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('146', 'C23', null, '工程职业技术学院', '0', '0', '0', '0', null, 'cqtwC23', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('147', 'C24', null, '三峡职业学院', '0', '0', '0', '0', null, 'cqtwC24', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('148', 'C25', null, '工贸职业技术学院', '0', '0', '0', '0', null, 'cqtwC25', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('149', 'C26', null, '工商职业学院', '0', '0', '0', '0', null, 'cqtwC26', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('150', 'C27', null, '青年职业技术学院', '0', '0', '0', '0', null, 'cqtwC27', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('151', 'C28', null, '化工职业学院', '0', '0', '0', '0', null, 'cqtwC28', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('152', 'C29', null, '传媒职业学院', '0', '0', '0', '0', null, 'cqtwC29', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('153', 'C30', null, '信息技术职业学院', '0', '0', '0', '0', null, 'cqtwC30', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('154', 'C31', null, '海联职业技术学院', '0', '0', '0', '0', null, 'cqtwC31', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('155', 'C32', null, '科创职业学院', '0', '0', '0', '0', null, 'cqtwC32', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('156', 'C33', null, '应用技术职业学院', '0', '0', '0', '0', null, 'cqtwC33', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('157', 'C34', null, '电讯职业学院', '0', '0', '0', '0', null, 'cqtwC34', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('158', 'C35', null, '能源职业学院', '0', '0', '0', '0', null, 'cqtwC35', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('159', 'C36', null, '交通职业学院', '0', '0', '0', '0', null, 'cqtwC36', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('160', 'C37', null, '公共运输职业学院', '0', '0', '0', '0', null, 'cqtwC37', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('161', 'C38', null, '艺术工程职业学院', '0', '0', '0', '0', null, 'cqtwC38', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('162', 'C39', null, '电信职业学院', '0', '0', '0', '0', null, 'cqtwC39', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('163', 'C40', null, '经贸职业学院', '0', '0', '0', '0', null, 'cqtwC40', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('164', 'C41', null, '城市职业学院', '0', '0', '0', '0', null, 'cqtwC41', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('165', 'C42', null, '重庆市工业学校', '0', '0', '0', '0', null, 'cqtwC42', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('166', 'C43', null, '重庆市工业高级技工学校', '0', '0', '0', '0', null, 'cqtwC43', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('167', 'C44', null, '重庆一中', '0', '0', '0', '0', null, 'cqtwC44', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('168', 'C45', null, '南开中学', '0', '0', '0', '0', null, 'cqtwC45', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('169', 'C46', null, '重庆八中', '0', '0', '0', '0', null, 'cqtwC46', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('170', 'C47', null, '巴蜀中学', '0', '0', '0', '0', null, 'cqtwC47', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('171', 'C48', null, '育才中学', '0', '0', '0', '0', null, 'cqtwC48', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('172', 'C49', null, '川外附中', '0', '0', '0', '0', null, 'cqtwC49', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('173', 'C50', null, '教育管理学校', '0', '0', '0', '0', null, 'cqtwC50', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('174', 'C51', null, '市教科院巴蜀学校', '0', '0', '0', '0', null, 'cqtwC51', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('175', 'C52', null, '重庆人文科技学院', '0', '0', '0', '0', null, 'cqtwC52', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('176', 'C53', null, '重师涉外商贸学院', '0', '0', '0', '0', null, 'cqtwC53', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('177', 'C54', null, '重庆工商大学融智学院', '0', '0', '0', '0', null, 'cqtwC54', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('178', 'C55', null, '重庆工商大学派斯学院', '0', '0', '0', '0', null, 'cqtwC55', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('179', 'C56', null, '川外南方翻译学院', '0', '0', '0', '0', null, 'cqtwC56', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('180', 'C57', null, '重邮移通学院', '0', '0', '0', '0', null, 'cqtwC57', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('181', 'C58', null, '重庆大学城市科技学院', '0', '0', '0', '0', null, 'cqtwC58', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('182', 'C59', null, '电力高专', '0', '0', '0', '0', null, 'cqtwC59', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('183', 'C60', null, '医药高专', '0', '0', '0', '0', null, 'cqtwC60', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('184', 'C61', null, '警察学院', '0', '0', '0', '0', null, 'cqtwC61', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('185', 'C62', null, '机电职业技术学院', '0', '0', '0', '0', null, 'cqtwC62', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('186', 'C63', null, '水利电力职业技术学院', '0', '0', '0', '0', null, 'cqtwC63', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('187', 'C64', null, '财经职业学院', '0', '0', '0', '0', null, 'cqtwC64', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('188', 'C65', null, '建筑工程职业学院', '0', '0', '0', '0', null, 'cqtwC65', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('189', 'C66', null, '商务职业学院', '0', '0', '0', '0', null, 'cqtwC66', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('190', 'C67', null, '旅游职业学院', '0', '0', '0', '0', null, 'cqtwC67', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('191', 'C68', null, '安全技术职业学院', '0', '0', '0', '0', null, 'cqtwC68', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('192', 'C69', null, '房地产职业学院', '0', '0', '0', '0', null, 'cqtwC69', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('193', 'C70', null, '重庆工程学院', '0', '0', '0', '0', null, 'cqtwC70', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('194', 'C71', null, '轻工职业学院', '0', '0', '0', '0', null, 'cqtwC71', null, null, null, '3', '2', '0');
INSERT INTO `user` VALUES ('195', 'C72', null, '行知计师学院', '0', '0', '0', '0', null, 'cqtwC72', null, null, null, '3', '2', '0');
