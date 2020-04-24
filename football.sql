/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : football

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 24/04/2020 13:59:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for battledetail
-- ----------------------------
DROP TABLE IF EXISTS `battledetail`;
CREATE TABLE `battledetail`  (
  `teamone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'A队',
  `teamtwo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'B队',
  `battletime` datetime(0) NULL DEFAULT NULL COMMENT '对战时间，YYYY-MM-DD HH:MM:SS',
  `battleside` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对战场地',
  `battleresult` int(0) NULL DEFAULT NULL COMMENT '1->teamone胜，0->平,-1->teamone负',
  `battlescore` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '对战得分'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of battledetail
-- ----------------------------
INSERT INTO `battledetail` VALUES ('德玛西亚', '艾欧尼亚', '2020-04-22 13:00:00', '悉尼足球场', NULL, '');
INSERT INTO `battledetail` VALUES ('德玛西亚', '战争学院', '2020-04-23 13:00:00', '悉尼足球场', NULL, NULL);
INSERT INTO `battledetail` VALUES ('艾欧尼亚', '战争学院', '2020-04-24 13:00:00', '巴西足球场', NULL, NULL);

-- ----------------------------
-- Table structure for footballplayer
-- ----------------------------
DROP TABLE IF EXISTS `footballplayer`;
CREATE TABLE `footballplayer`  (
  `playername` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '球员名称',
  `playerphoto` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '球员照片地址',
  `playerteamName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属球队的名字',
  `playerscore` int(0) NULL DEFAULT NULL COMMENT '球员得分',
  `playerfoul` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '犯规信息',
  `playerrank` int(0) NOT NULL COMMENT '球员排名',
  PRIMARY KEY (`playername`) USING BTREE,
  INDEX `playerteamName`(`playerteamName`) USING BTREE,
  CONSTRAINT `footballplayer_ibfk_1` FOREIGN KEY (`playerteamName`) REFERENCES `footballteam` (`teamname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of footballplayer
-- ----------------------------
INSERT INTO `footballplayer` VALUES ('凯', NULL, '战争学院', 6, '无', 1);
INSERT INTO `footballplayer` VALUES ('猴子', NULL, '战争学院', 1, '无', 4);
INSERT INTO `footballplayer` VALUES ('西施', NULL, '德玛西亚', 0, '无', 5);
INSERT INTO `footballplayer` VALUES ('马可波罗', NULL, '艾欧尼亚', 2, '无', 3);
INSERT INTO `footballplayer` VALUES ('鲁班七号', NULL, '艾欧尼亚', 4, '无', 2);

-- ----------------------------
-- Table structure for footballteam
-- ----------------------------
DROP TABLE IF EXISTS `footballteam`;
CREATE TABLE `footballteam`  (
  `teamname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '球队名称',
  `teamrank` int(0) NULL DEFAULT NULL COMMENT '球队排名',
  `winNum` int(0) NULL DEFAULT NULL COMMENT '球队胜场数',
  `loseNum` int(0) NULL DEFAULT NULL COMMENT '球队负场数',
  `drawNum` int(0) NULL DEFAULT NULL COMMENT '球队平局数',
  `goalNum` int(0) NULL DEFAULT NULL COMMENT '球队进球数',
  `goalLostNum` int(0) NULL DEFAULT NULL COMMENT '球队失球数',
  `teamscore` int(0) NULL DEFAULT NULL COMMENT '球队得分',
  PRIMARY KEY (`teamname`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of footballteam
-- ----------------------------
INSERT INTO `footballteam` VALUES ('德玛西亚', 2, 5, 1, 3, 14, 8, 18);
INSERT INTO `footballteam` VALUES ('战争学院', 3, 4, 2, 4, 8, 8, 16);
INSERT INTO `footballteam` VALUES ('艾欧尼亚', 1, 7, 2, 1, 23, 12, 22);

SET FOREIGN_KEY_CHECKS = 1;
