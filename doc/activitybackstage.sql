/*
 Navicat Premium Data Transfer

 Source Server         : 120.77.219.167_3306
 Source Server Type    : MariaDB
 Source Server Version : 50552
 Source Host           : 120.77.219.167:3306
 Source Schema         : activitybackstage

 Target Server Type    : MariaDB
 Target Server Version : 50552
 File Encoding         : 65001

 Date: 21/10/2017 14:56:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` bigint(20) NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `participants` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userId` bigint(20) NULL DEFAULT NULL,
  `userKind` int(11) NULL DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userType` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (2, '“不忘初心，方得始终。”当历史的长河流淌到今天，96岁的中国共产党意气风发，睿智清醒。习近平总书记在十九大报告中作出了“中国特色社会主义进入了新时代，这是我国发展新的历史方位”的重大判断。响应新时代呼唤，共创新征程辉煌，各地干部群众反响强烈，激情满怀。', 1508567293718, '/usr/local/activitys/img/7044b7af-8249-4f2e-a9d2-32fac8fe9fac.jpg;', '西南大学计算机学院报告厅', '西南大学计算机学院全体团员', 2, '2017-10-21', '十九大时光：明天的日子一定会更好', 3, 1, 'A01', 2);
INSERT INTO `activity` VALUES (3, '要瞄准世界科技前沿，强化基础研究，实现前瞻性基础研究、引领性原创成果重大突破。加强应用基础研究，拓展实施国家重大科技项目，突出关键共性技术、前沿引领技术、现代工程技术、颠覆性技术创新，为建设科技强国、质量强国、航天强国、网络强国、交通强国、数字中国、智慧社会提供有力支撑。\r\n\r\n东气有个“争气炉”，说的是30万千瓦火电技术。当时，没有图纸，我们就在厂房内部，从一张白纸开始，实现了30万千瓦火电机组自主化制造，所以每个人都叫它“争气炉”。', 1508567648966, '/usr/local/activitys/img/34d108a9-602d-4928-80b3-608167bbc359.jpg;', '北京市昌平区', '东方锅炉集团', 2, '2017-10-21', '创新驱动，让中国智造领跑世界', 53, 2, 'B01', 2);
INSERT INTO `activity` VALUES (5, '人民政协是具有中国特色的制度安排，是社会主义协商民主的重要渠道和专门协商机构。协商民主，从十八大报告首提、十八届三中全会部署，到习近平总书记在庆祝中国人民政治协商会议成立65周年大会上的重要讲话中着重论述，再到中共中央办公厅印发《关于加强人民政协协商民主建设的实施意见》，内容不断丰富和拓展。', 1508567842023, '/usr/local/activitys/img/d846e527-fa84-4deb-a44b-08e1efca4d87.jpg;', '上海市', '李昌禹  郭舒然  季健明  张  垚  朱  虹  禹丽敏', 2, '2017-10-21', '协商民主，更广泛更活跃', 124, 3, 'C01', 2);
INSERT INTO `activity` VALUES (202, '十九大报告中，习近平总书记对于发展社会主义协商民主的论述高屋建瓴，为未来政协工作指明了方向。”云南省怒江傈僳族自治州政协主席李坤珍代表说，“十九大报告还有这样一句论述，‘扩大人民有序政治参与，保证人民依法实行民主选举、民主协商、民主决策、民主管理、民主监督’。其中，‘民主协商’是新增的，这表明协商民主被提升到一个新高度。', 1508568762005, '/usr/local/activitys/img/417bb050-83f1-4ef3-aa74-69d81221edfc.jpg;', '西南大学8教302', '14软工二班全体', 2, '2017-10-21', '全班观看习大大讲话', 201, 3, '14se2swujx', 4);

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (203);
INSERT INTO `hibernate_sequence` VALUES (203);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `memberNum` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `numCheck` int(11) NULL DEFAULT NULL,
  `numDelete` int(11) NULL DEFAULT NULL,
  `numNotPass` int(11) NULL DEFAULT NULL,
  `numPass` int(11) NULL DEFAULT NULL,
  `parentId` bigint(20) NULL DEFAULT NULL,
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pparentId` bigint(20) NULL DEFAULT NULL,
  `secretaryName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `secretaryTel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userKind` int(11) NULL DEFAULT NULL,
  `userType` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'catw', NULL, 'ChongQing TuanWei', 0, 0, 0, 0, NULL, '123456', NULL, NULL, NULL, NULL, 1);
INSERT INTO `user` VALUES (3, 'A01', NULL, '万州区', 0, 0, 0, 1, NULL, 'cqtwA01', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (4, 'A02', NULL, '黔江区', 0, 0, 0, 0, NULL, 'cqtwA02', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (5, 'A03', NULL, '涪陵区', 0, 0, 0, 0, NULL, 'cqtwA03', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (6, 'A04', NULL, '渝中区', 0, 0, 0, 0, NULL, 'cqtwA04', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (7, 'A05', NULL, '大渡口区', 0, 0, 0, 0, NULL, 'cqtwA05', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (8, 'A06', NULL, '江北区', 0, 0, 0, 0, NULL, 'cqtwA06', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (9, 'A07', NULL, '沙坪坝区', 0, 0, 0, 0, NULL, 'cqtwA07', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (10, 'A08', NULL, '九龙坡区', 0, 0, 0, 0, NULL, 'cqtwA08', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (11, 'A09', NULL, '南岸区', 0, 0, 0, 0, NULL, 'cqtwA09', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (12, 'A10', NULL, '北碚区', 0, 0, 0, 0, NULL, 'cqtwA10', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (13, 'A11', NULL, '渝北区', 0, 0, 0, 0, NULL, 'cqtwA11', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (14, 'A12', NULL, '巴南区', 0, 0, 0, 0, NULL, 'cqtwA12', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (15, 'A13', NULL, '长寿区', 0, 0, 0, 0, NULL, 'cqtwA13', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (16, 'A14', NULL, '江津区', 0, 0, 0, 0, NULL, 'cqtwA14', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (17, 'A15', NULL, '合川区', 0, 0, 0, 0, NULL, 'cqtwA15', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (18, 'A16', NULL, '永川区', 0, 0, 0, 0, NULL, 'cqtwA16', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (19, 'A17', NULL, '南川区', 0, 0, 0, 0, NULL, 'cqtwA17', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (20, 'A18', NULL, '綦江区', 0, 0, 0, 0, NULL, 'cqtwA18', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (21, 'A19', NULL, '大足区', 0, 0, 0, 0, NULL, 'cqtwA19', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (22, 'A20', NULL, '璧山区', 0, 0, 0, 0, NULL, 'cqtwA20', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (23, 'A21', NULL, '铜梁区', 0, 0, 0, 0, NULL, 'cqtwA21', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (24, 'A22', NULL, '潼南区', 0, 0, 0, 0, NULL, 'cqtwA22', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (25, 'A23', NULL, '荣昌区', 0, 0, 0, 0, NULL, 'cqtwA23', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (26, 'A24', NULL, '开州区', 0, 0, 0, 0, NULL, 'cqtwA24', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (27, 'A25', NULL, '梁平区', 0, 0, 0, 0, NULL, 'cqtwA25', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (28, 'A26', NULL, '武隆区', 0, 0, 0, 0, NULL, 'cqtwA26', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (29, 'A27', NULL, '城口县', 0, 0, 0, 0, NULL, 'cqtwA27', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (30, 'A28', NULL, '丰都县', 0, 0, 0, 0, NULL, 'cqtwA28', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (31, 'A29', NULL, '垫江县', 0, 0, 0, 0, NULL, 'cqtwA29', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (32, 'A30', NULL, '忠县', 0, 0, 0, 0, NULL, 'cqtwA30', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (33, 'A31', NULL, '云阳县', 0, 0, 0, 0, NULL, 'cqtwA31', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (34, 'A32', NULL, '奉节县', 0, 0, 0, 0, NULL, 'cqtwA32', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (35, 'A33', NULL, '巫山县', 0, 0, 0, 0, NULL, 'cqtwA33', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (36, 'A34', NULL, '巫溪县', 0, 0, 0, 0, NULL, 'cqtwA34', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (37, 'A35', NULL, '石柱县', 0, 0, 0, 0, NULL, 'cqtwA35', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (38, 'A36', NULL, '秀山县', 0, 0, 0, 0, NULL, 'cqtwA36', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (39, 'A37', NULL, '酉阳县', 0, 0, 0, 0, NULL, 'cqtwA37', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (40, 'A38', NULL, '彭水县', 0, 0, 0, 0, NULL, 'cqtwA38', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (41, 'A39', NULL, '万盛经开区', 0, 0, 0, 0, NULL, 'cqtwA39', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (42, 'A40', NULL, '驻北京团工委', 0, 0, 0, 0, NULL, 'cqtwA40', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (43, 'A41', NULL, '驻广东团工委', 0, 0, 0, 0, NULL, 'cqtwA41', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (44, 'A42', NULL, '驻上海团工委', 0, 0, 0, 0, NULL, 'cqtwA42', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (45, 'A43', NULL, '驻江苏团工委', 0, 0, 0, 0, NULL, 'cqtwA43', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (46, 'A44', NULL, '驻浙江团工委', 0, 0, 0, 0, NULL, 'cqtwA44', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (47, 'A45', NULL, '驻福建团工委', 0, 0, 0, 0, NULL, 'cqtwA45', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (48, 'A46', NULL, '驻新疆团工委', 0, 0, 0, 0, NULL, 'cqtwA46', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (49, 'A47', NULL, '河南驻渝团工委', 0, 0, 0, 0, NULL, 'cqtwA47', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (50, 'A48', NULL, '四川驻渝团工委', 0, 0, 0, 0, NULL, 'cqtwA48', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (51, 'A49', NULL, '贵州驻渝团工委', 0, 0, 0, 0, NULL, 'cqtwA49', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (52, 'A50', NULL, '山东驻渝团工委', 0, 0, 0, 0, NULL, 'cqtwA50', NULL, NULL, NULL, 1, 2);
INSERT INTO `user` VALUES (53, 'B01', NULL, '市国资委(不含重点联系团组织)', 0, 0, 0, 1, NULL, 'cqtwB01', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (54, 'B02', NULL, '市直机关（含市国土房管局、市工商局、市地税局、市质监局、市食药监局、市审计局各1名代表）', 0, 0, 0, 0, NULL, 'cqtwB02', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (55, 'B03', NULL, '两江新区', 0, 0, 0, 0, NULL, 'cqtwB03', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (56, 'B04', NULL, '市科委', 0, 0, 0, 0, NULL, 'cqtwB04', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (57, 'B05', NULL, '市公安局（含武警重庆市消防总队、武警重庆市警卫局、武警重庆市边防总队各1名代表）', 0, 0, 0, 0, NULL, 'cqtwB05', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (58, 'B06', NULL, '市国安局', 0, 0, 0, 0, NULL, 'cqtwB06', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (59, 'B07', NULL, '市民政局', 0, 0, 0, 0, NULL, 'cqtwB07', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (60, 'B08', NULL, '市司法局', 0, 0, 0, 0, NULL, 'cqtwB08', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (61, 'B09', NULL, '市城管委', 0, 0, 0, 0, NULL, 'cqtwB09', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (62, 'B10', NULL, '市交委', 0, 0, 0, 0, NULL, 'cqtwB10', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (63, 'B11', NULL, '市水利局', 0, 0, 0, 0, NULL, 'cqtwB11', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (64, 'B12', NULL, '市农委', 0, 0, 0, 0, NULL, 'cqtwB12', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (65, 'B13', NULL, '市商务委', 0, 0, 0, 0, NULL, 'cqtwB13', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (66, 'B14', NULL, '市文化委', 0, 0, 0, 0, NULL, 'cqtwB14', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (67, 'B15', NULL, '市卫计委', 0, 0, 0, 0, NULL, 'cqtwB15', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (68, 'B16', NULL, '市供销社', 0, 0, 0, 0, NULL, 'cqtwB16', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (69, 'B17', NULL, '重庆日报', 0, 0, 0, 0, NULL, 'cqtwB17', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (70, 'B18', NULL, '广电集团', 0, 0, 0, 0, NULL, 'cqtwB18', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (71, 'B19', NULL, '新华书店集团', 0, 0, 0, 0, NULL, 'cqtwB19', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (72, 'B20', NULL, '市设计院', 0, 0, 0, 0, NULL, 'cqtwB20', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (73, 'B21', NULL, '中机中联公司', 0, 0, 0, 0, NULL, 'cqtwB21', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (74, 'B22', NULL, '中煤重庆设计院', 0, 0, 0, 0, NULL, 'cqtwB22', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (75, 'B23', NULL, '庆铃集团', 0, 0, 0, 0, NULL, 'cqtwB23', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (76, 'B24', NULL, '重钢集团', 0, 0, 0, 0, NULL, 'cqtwB24', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (77, 'B25', NULL, '化医集团', 0, 0, 0, 0, NULL, 'cqtwB25', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (78, 'B26', NULL, '轻纺集团', 0, 0, 0, 0, NULL, 'cqtwB26', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (79, 'B27', NULL, '机电集团', 0, 0, 0, 0, NULL, 'cqtwB27', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (80, 'B28', NULL, '能源集团', 0, 0, 0, 0, NULL, 'cqtwB28', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (81, 'B29', NULL, '四联集团', 0, 0, 0, 0, NULL, 'cqtwB29', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (82, 'B30', NULL, '建工集团', 0, 0, 0, 0, NULL, 'cqtwB30', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (83, 'B31', NULL, '保税港区', 0, 0, 0, 0, NULL, 'cqtwB31', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (84, 'B32', NULL, '水务集团', 0, 0, 0, 0, NULL, 'cqtwB32', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (85, 'B33', NULL, '旅投集团', 0, 0, 0, 0, NULL, 'cqtwB33', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (86, 'B34', NULL, '西永微电园', 0, 0, 0, 0, NULL, 'cqtwB34', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (87, 'B35', NULL, '重庆银行', 0, 0, 0, 0, NULL, 'cqtwB35', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (88, 'B36', NULL, '港务集团', 0, 0, 0, 0, NULL, 'cqtwB36', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (89, 'B37', NULL, '交运集团', 0, 0, 0, 0, NULL, 'cqtwB37', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (90, 'B38', NULL, '商社集团', 0, 0, 0, 0, NULL, 'cqtwB38', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (91, 'B39', NULL, '电力公司', 0, 0, 0, 0, NULL, 'cqtwB39', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (92, 'B40', NULL, '烟草公司', 0, 0, 0, 0, NULL, 'cqtwB40', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (93, 'B41', NULL, '邮政公司', 0, 0, 0, 0, NULL, 'cqtwB41', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (94, 'B42', NULL, '电信公司', 0, 0, 0, 0, NULL, 'cqtwB42', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (95, 'B43', NULL, '移动公司', 0, 0, 0, 0, NULL, 'cqtwB43', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (96, 'B44', NULL, '联通公司', 0, 0, 0, 0, NULL, 'cqtwB44', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (97, 'B45', NULL, '船舶工业', 0, 0, 0, 0, NULL, 'cqtwB45', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (98, 'B46', NULL, '长江轮船', 0, 0, 0, 0, NULL, 'cqtwB46', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (99, 'B47', NULL, '中冶赛迪', 0, 0, 0, 0, NULL, 'cqtwB47', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (100, 'B48', NULL, '西南铝', 0, 0, 0, 0, NULL, 'cqtwB48', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (101, 'B49', NULL, '华能珞璜', 0, 0, 0, 0, NULL, 'cqtwB49', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (102, 'B50', NULL, '川维厂', 0, 0, 0, 0, NULL, 'cqtwB50', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (103, 'B51', NULL, '中冶建工', 0, 0, 0, 0, NULL, 'cqtwB51', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (104, 'B52', NULL, '中石油重庆分公司', 0, 0, 0, 0, NULL, 'cqtwB52', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (105, 'B53', NULL, '公交集团', 0, 0, 0, 0, NULL, 'cqtwB53', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (106, 'B54', NULL, '重庆航空', 0, 0, 0, 0, NULL, 'cqtwB54', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (107, 'B55', NULL, '机场集团', 0, 0, 0, 0, NULL, 'cqtwB55', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (108, 'B56', NULL, '重庆铁路联合团委', 0, 0, 0, 0, NULL, 'cqtwB56', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (109, 'B57', NULL, '长航代表处', 0, 0, 0, 0, NULL, 'cqtwB57', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (110, 'B58', NULL, '嘉陵股份', 0, 0, 0, 0, NULL, 'cqtwB58', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (111, 'B59', NULL, '嘉陵特装', 0, 0, 0, 0, NULL, 'cqtwB59', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (112, 'B60', NULL, '长安工业', 0, 0, 0, 0, NULL, 'cqtwB60', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (113, 'B61', NULL, '长安汽车', 0, 0, 0, 0, NULL, 'cqtwB61', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (114, 'B62', NULL, '建设工业', 0, 0, 0, 0, NULL, 'cqtwB62', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (115, 'B63', NULL, '建设机电', 0, 0, 0, 0, NULL, 'cqtwB63', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (116, 'B64', NULL, '中央在渝企业团工委', 0, 0, 0, 0, NULL, 'cqtwB64', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (117, 'B65', NULL, '富士康', 0, 0, 0, 0, NULL, 'cqtwB65', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (118, 'B66', NULL, '力帆集团', 0, 0, 0, 0, NULL, 'cqtwB66', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (119, 'B67', NULL, '金科地产', 0, 0, 0, 0, NULL, 'cqtwB67', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (120, 'B68', NULL, '中科控股', 0, 0, 0, 0, NULL, 'cqtwB68', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (121, 'B69', NULL, '陶然居集团', 0, 0, 0, 0, NULL, 'cqtwB69', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (122, 'B70', NULL, '世纪金源', 0, 0, 0, 0, NULL, 'cqtwB70', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (123, 'B71', NULL, '猪八戒网络', 0, 0, 0, 0, NULL, 'cqtwB71', NULL, NULL, NULL, 2, 2);
INSERT INTO `user` VALUES (124, 'C01', NULL, '重庆大学', 0, 0, 0, 1, NULL, 'cqtwC01', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (125, 'C02', NULL, '西南大学', 0, 0, 0, 1, NULL, 'cqtwC02', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (126, 'C03', NULL, '西南政法大学', 0, 0, 0, 0, NULL, 'cqtwC03', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (127, 'C04', NULL, '重庆医科大学', 0, 0, 0, 0, NULL, 'cqtwC04', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (128, 'C05', NULL, '重庆师范大学', 0, 0, 0, 0, NULL, 'cqtwC05', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (129, 'C06', NULL, '重庆邮电大学', 0, 0, 0, 0, NULL, 'cqtwC06', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (130, 'C07', NULL, '重庆交通大学', 0, 0, 0, 0, NULL, 'cqtwC07', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (131, 'C08', NULL, '重庆工商大学', 0, 0, 0, 0, NULL, 'cqtwC08', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (132, 'C09', NULL, '四川外国语大学', 0, 0, 0, 0, NULL, 'cqtwC09', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (133, 'C10', NULL, '四川美术学院', 0, 0, 0, 0, NULL, 'cqtwC10', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (134, 'C11', NULL, '重庆理工大学', 0, 0, 0, 0, NULL, 'cqtwC11', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (135, 'C12', NULL, '重庆三峡学院', 0, 0, 0, 0, NULL, 'cqtwC12', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (136, 'C13', NULL, '重庆文理学院', 0, 0, 0, 0, NULL, 'cqtwC13', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (137, 'C14', NULL, '长江师范学院', 0, 0, 0, 0, NULL, 'cqtwC14', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (138, 'C15', NULL, '重庆科技学院', 0, 0, 0, 0, NULL, 'cqtwC15', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (139, 'C16', NULL, '重庆第二师范学院', 0, 0, 0, 0, NULL, 'cqtwC16', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (140, 'C17', NULL, '市经信委', 0, 0, 0, 0, NULL, 'cqtwC17', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (141, 'C18', NULL, '三峡医药高专', 0, 0, 0, 0, NULL, 'cqtwC18', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (142, 'C19', NULL, '航天职业技术学院', 0, 0, 0, 0, NULL, 'cqtwC19', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (143, 'C20', NULL, '电子工程职业学院', 0, 0, 0, 0, NULL, 'cqtwC20', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (144, 'C21', NULL, '工业职业技术学院', 0, 0, 0, 0, NULL, 'cqtwC21', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (145, 'C22', NULL, '城市管理职业学院', 0, 0, 0, 0, NULL, 'cqtwC22', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (146, 'C23', NULL, '工程职业技术学院', 0, 0, 0, 0, NULL, 'cqtwC23', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (147, 'C24', NULL, '三峡职业学院', 0, 0, 0, 0, NULL, 'cqtwC24', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (148, 'C25', NULL, '工贸职业技术学院', 0, 0, 0, 0, NULL, 'cqtwC25', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (149, 'C26', NULL, '工商职业学院', 0, 0, 0, 0, NULL, 'cqtwC26', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (150, 'C27', NULL, '青年职业技术学院', 0, 0, 0, 0, NULL, 'cqtwC27', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (151, 'C28', NULL, '化工职业学院', 0, 0, 0, 0, NULL, 'cqtwC28', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (152, 'C29', NULL, '传媒职业学院', 0, 0, 0, 0, NULL, 'cqtwC29', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (153, 'C30', NULL, '信息技术职业学院', 0, 0, 0, 0, NULL, 'cqtwC30', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (154, 'C31', NULL, '海联职业技术学院', 0, 0, 0, 0, NULL, 'cqtwC31', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (155, 'C32', NULL, '科创职业学院', 0, 0, 0, 0, NULL, 'cqtwC32', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (156, 'C33', NULL, '应用技术职业学院', 0, 0, 0, 0, NULL, 'cqtwC33', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (157, 'C34', NULL, '电讯职业学院', 0, 0, 0, 0, NULL, 'cqtwC34', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (158, 'C35', NULL, '能源职业学院', 0, 0, 0, 0, NULL, 'cqtwC35', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (159, 'C36', NULL, '交通职业学院', 0, 0, 0, 0, NULL, 'cqtwC36', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (160, 'C37', NULL, '公共运输职业学院', 0, 0, 0, 0, NULL, 'cqtwC37', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (161, 'C38', NULL, '艺术工程职业学院', 0, 0, 0, 0, NULL, 'cqtwC38', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (162, 'C39', NULL, '电信职业学院', 0, 0, 0, 0, NULL, 'cqtwC39', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (163, 'C40', NULL, '经贸职业学院', 0, 0, 0, 0, NULL, 'cqtwC40', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (164, 'C41', NULL, '城市职业学院', 0, 0, 0, 0, NULL, 'cqtwC41', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (165, 'C42', NULL, '重庆市工业学校', 0, 0, 0, 0, NULL, 'cqtwC42', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (166, 'C43', NULL, '重庆市工业高级技工学校', 0, 0, 0, 0, NULL, 'cqtwC43', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (167, 'C44', NULL, '重庆一中', 0, 0, 0, 0, NULL, 'cqtwC44', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (168, 'C45', NULL, '南开中学', 0, 0, 0, 0, NULL, 'cqtwC45', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (169, 'C46', NULL, '重庆八中', 0, 0, 0, 0, NULL, 'cqtwC46', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (170, 'C47', NULL, '巴蜀中学', 0, 0, 0, 0, NULL, 'cqtwC47', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (171, 'C48', NULL, '育才中学', 0, 0, 0, 0, NULL, 'cqtwC48', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (172, 'C49', NULL, '川外附中', 0, 0, 0, 0, NULL, 'cqtwC49', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (173, 'C50', NULL, '教育管理学校', 0, 0, 0, 0, NULL, 'cqtwC50', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (174, 'C51', NULL, '市教科院巴蜀学校', 0, 0, 0, 0, NULL, 'cqtwC51', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (175, 'C52', NULL, '重庆人文科技学院', 0, 0, 0, 0, NULL, 'cqtwC52', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (176, 'C53', NULL, '重师涉外商贸学院', 0, 0, 0, 0, NULL, 'cqtwC53', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (177, 'C54', NULL, '重庆工商大学融智学院', 0, 0, 0, 0, NULL, 'cqtwC54', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (178, 'C55', NULL, '重庆工商大学派斯学院', 0, 0, 0, 0, NULL, 'cqtwC55', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (179, 'C56', NULL, '川外南方翻译学院', 0, 0, 0, 0, NULL, 'cqtwC56', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (180, 'C57', NULL, '重邮移通学院', 0, 0, 0, 0, NULL, 'cqtwC57', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (181, 'C58', NULL, '重庆大学城市科技学院', 0, 0, 0, 0, NULL, 'cqtwC58', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (182, 'C59', NULL, '电力高专', 0, 0, 0, 0, NULL, 'cqtwC59', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (183, 'C60', NULL, '医药高专', 0, 0, 0, 0, NULL, 'cqtwC60', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (184, 'C61', NULL, '警察学院', 0, 0, 0, 0, NULL, 'cqtwC61', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (185, 'C62', NULL, '机电职业技术学院', 0, 0, 0, 0, NULL, 'cqtwC62', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (186, 'C63', NULL, '水利电力职业技术学院', 0, 0, 0, 0, NULL, 'cqtwC63', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (187, 'C64', NULL, '财经职业学院', 0, 0, 0, 0, NULL, 'cqtwC64', NULL, NULL, NULL, 3, 2);
INSERT INTO `user` VALUES (188