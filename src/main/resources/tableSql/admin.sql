/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50643
Source Host           : localhost:3306
Source Database       : jfinal3

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-03-25 11:06:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) DEFAULT NULL,
  `admin_password` varchar(255) DEFAULT NULL,
  `admin_logintime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
