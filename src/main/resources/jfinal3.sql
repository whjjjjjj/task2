/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50643
Source Host           : localhost:3306
Source Database       : jfinal3

Target Server Type    : MYSQL
Target Server Version : 50643
File Encoding         : 65001

Date: 2019-03-29 14:45:58
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `record_data` varchar(2550) DEFAULT NULL,
  `record_time` bigint(20) DEFAULT NULL,
  `robot_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `robot_id` (`robot_id`),
  CONSTRAINT `robot_id` FOREIGN KEY (`robot_id`) REFERENCES `robot` (`robot_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for robot
-- ----------------------------
DROP TABLE IF EXISTS `robot`;
CREATE TABLE `robot` (
  `robot_id` int(11) NOT NULL AUTO_INCREMENT,
  `robot_name` varchar(255) NOT NULL,
  `robot_url` varchar(255) NOT NULL,
  PRIMARY KEY (`robot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
