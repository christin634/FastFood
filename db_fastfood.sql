/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : db_fastfood

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 30/10/2022 21:06:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clerk
-- ----------------------------
DROP TABLE IF EXISTS `clerk`;
CREATE TABLE `clerk`  (
  `number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工编号',
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `age` int(0) NOT NULL COMMENT '年龄',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `phonenumber` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话号码',
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of clerk
-- ----------------------------
INSERT INTO `clerk` VALUES ('9527', '张三', 22, '男', '13745681201');
INSERT INTO `clerk` VALUES ('9528', '李四', 26, '男', '15674125441');
INSERT INTO `clerk` VALUES ('9529', '王五', 25, '男', '14512345671');
INSERT INTO `clerk` VALUES ('9600', '赵六', 27, '女', '15245127426');

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号，自增主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '食物名称',
  `type` int(0) NOT NULL COMMENT '食物类型{1:汉堡，2:鸡，3:小吃，4:饮品}',
  `brand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌：KFC/Wallace',
  `price` float(10, 2) NOT NULL COMMENT '单价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (1, '香辣鸡腿堡', 1, 'Wallace', 13.90);
INSERT INTO `food` VALUES (2, '风味鸡柳堡', 1, 'Wallace', 13.90);
INSERT INTO `food` VALUES (3, '板烧牛排堡', 1, 'Wallace', 15.90);
INSERT INTO `food` VALUES (4, '香辣鸡腿堡', 1, 'KFC', 20.00);
INSERT INTO `food` VALUES (5, '黄金SPA鸡排堡', 1, 'KFC', 21.00);
INSERT INTO `food` VALUES (6, '芝士安格斯牛堡', 1, 'KFC', 32.00);
INSERT INTO `food` VALUES (7, '香辣鸡翅', 2, 'KFC', 6.00);
INSERT INTO `food` VALUES (8, '黄金鸡块', 2, 'KFC', 2.50);
INSERT INTO `food` VALUES (9, '劲爆鸡米花(大)', 2, 'KFC', 15.50);
INSERT INTO `food` VALUES (10, '劲爆鸡米花(小)', 2, 'KFC', 13.00);
INSERT INTO `food` VALUES (11, '吮指原味鸡', 2, 'KFC', 13.00);
INSERT INTO `food` VALUES (12, '黄金脆皮鸡', 2, 'KFC', 14.00);
INSERT INTO `food` VALUES (13, '脆皮炸鸡', 2, 'Wallace', 24.90);
INSERT INTO `food` VALUES (14, '香辣鸡翅', 2, 'Wallace', 13.90);
INSERT INTO `food` VALUES (15, '鸡米花', 2, 'Wallace', 13.90);
INSERT INTO `food` VALUES (16, '辣子鸡块', 2, 'Wallace', 13.90);
INSERT INTO `food` VALUES (17, '元气鸡排', 2, 'Wallace', 13.90);
INSERT INTO `food` VALUES (18, '果珍', 4, 'Wallace', 9.90);
INSERT INTO `food` VALUES (19, '可乐', 4, 'Wallace', 9.90);
INSERT INTO `food` VALUES (20, '可乐', 4, 'KFC', 10.00);
INSERT INTO `food` VALUES (22, '拿铁', 4, 'KFC', 17.00);
INSERT INTO `food` VALUES (23, '薯条', 3, 'Wallace', 11.90);
INSERT INTO `food` VALUES (24, '薯条', 3, 'KFC', 15.00);
INSERT INTO `food` VALUES (25, '鸡肉卷', 2, 'Wallace', 13.00);
INSERT INTO `food` VALUES (26, '蛋挞', 3, 'KFC', 6.00);

-- ----------------------------
-- Table structure for meal_order
-- ----------------------------
DROP TABLE IF EXISTS `meal_order`;
CREATE TABLE `meal_order`  (
  `number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单编号，由系统生成',
  `content` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单内容',
  `date` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '日期',
  `price` float(10, 2) NOT NULL COMMENT '实际总价(打折以后)',
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meal_order
-- ----------------------------
INSERT INTO `meal_order` VALUES ('1665840878047', '|KFC-香辣鸡腿堡*1|KFC-黄金SPA鸡排堡*2|KFC-芝士安格斯牛堡*2|', '2022-10-15 21:34', 122.00);
INSERT INTO `meal_order` VALUES ('1665840892300', '|KFC-黄金SPA鸡排堡*1|KFC-香辣鸡腿堡*1|KFC-黄金鸡块*1|Wallace-脆皮炸鸡*2|Wallace-香辣鸡翅*3|KFC-芝士安格斯牛堡*1|', '2022-10-15 21:34', 165.00);
INSERT INTO `meal_order` VALUES ('1665840975868', '|KFC-黄金SPA鸡排堡*1|KFC-拿铁*1|', '2022-10-15 21:36', 36.00);
INSERT INTO `meal_order` VALUES ('1665841032920', '|KFC-黄金SPA鸡排堡*1|KFC-黄金鸡块*2|KFC-蛋挞*1|KFC-拿铁*1|', '2022-10-15 21:37', 47.00);
INSERT INTO `meal_order` VALUES ('1665906526667', '|KFC-香辣鸡腿堡*1|KFC-芝士安格斯牛堡*1|Wallace-可乐*1|Wallace-果珍*1|KFC-劲爆鸡米花(大)*1|', '2022-10-16 15:48:46', 87.30);
INSERT INTO `meal_order` VALUES ('1665998045498', '|KFC-黄金SPA鸡排堡*1|KFC-劲爆鸡米花(大)*1|KFC-可乐*1|', '2022-10-17 17:14:05', 44.50);
INSERT INTO `meal_order` VALUES ('1666181304445', '|KFC-香辣鸡翅*1|KFC-劲爆鸡米花(小)*1|KFC-黄金SPA鸡排堡*1|KFC-拿铁*1|', '2022-10-19 20:08:24', 44.00);
INSERT INTO `meal_order` VALUES ('1666233565118', '|KFC-吮指原味鸡*1|KFC-黄金脆皮鸡*1|KFC-拿铁*1|Wallace-香辣鸡翅*1|Wallace-辣子鸡块*1|', '2022-10-20 10:39:25', 57.44);
INSERT INTO `meal_order` VALUES ('1666964265552', '|KFC-拿铁*1|KFC-劲爆鸡米花(大)*1|Wallace-薯条*1|KFC-芝士安格斯牛堡*1|', '2022-10-28 21:37:45', 76.40);
INSERT INTO `meal_order` VALUES ('1666964292646', '|KFC-芝士安格斯牛堡*1|KFC-劲爆鸡米花(小)*1|KFC-拿铁*1|Wallace-薯条*1|', '2022-10-28 21:38:12', 59.12);

-- ----------------------------
-- Table structure for membership
-- ----------------------------
DROP TABLE IF EXISTS `membership`;
CREATE TABLE `membership`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话号码',
  `cardnum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '会员卡号',
  `discount` float(10, 2) NOT NULL DEFAULT 0.00 COMMENT '折扣',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of membership
-- ----------------------------
INSERT INTO `membership` VALUES (1, '13412312311', '11111111', 0.80);
INSERT INTO `membership` VALUES (2, '12345678910', '23411234', 0.70);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编号',
  `username` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `type` int(0) NOT NULL COMMENT '用户类型{1:管理员,2:员工}',
  PRIMARY KEY (`number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0001', 'admin', 'admin', 1);
INSERT INTO `user` VALUES ('9527', 'zs', '111111', 2);
INSERT INTO `user` VALUES ('9528', '李四', '123456', 2);
INSERT INTO `user` VALUES ('9529', '王五', '111111', 2);
INSERT INTO `user` VALUES ('9600', '赵六', '123456', 2);

SET FOREIGN_KEY_CHECKS = 1;
