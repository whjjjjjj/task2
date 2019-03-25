/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50643
Source Host           : localhost:3306
Source Database       : jfinal3

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-03-25 11:06:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for robot
-- ----------------------------
DROP TABLE IF EXISTS `robot`;
CREATE TABLE `robot` (
  `robot_id` int(11) NOT NULL AUTO_INCREMENT,
  `robot_name` varchar(255) NOT NULL,
  `robot_url` varchar(255) NOT NULL,
  PRIMARY KEY (`robot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
