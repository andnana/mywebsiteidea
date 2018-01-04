/*
Navicat MySQL Data Transfer

Source Server         : mywebsite
Source Server Version : 50547
Source Host           : localhost:3306
Source Database       : mywebsite

Target Server Type    : MYSQL
Target Server Version : 50547
File Encoding         : 65001

Date: 2018-01-04 09:00:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('13', 'cplusplus分类');
INSERT INTO `category` VALUES ('12', 'php分类');
INSERT INTO `category` VALUES ('11', 'python分类');
INSERT INTO `category` VALUES ('10', 'java分类');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '财务部');
INSERT INTO `dept` VALUES ('2', '后勤部');
INSERT INTO `dept` VALUES ('3', '生产部');
INSERT INTO `dept` VALUES ('4', 'IT部');

-- ----------------------------
-- Table structure for info
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `pic_name` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `info_date` datetime NOT NULL,
  `cate_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('40', 'java编程语言', '截图未命名111.jpg', '<p>我用java编程语言写程序，目前用java程序语言的springmvc mybatis spring框架做web程序的开发。<img src=\"http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/6a/laugh.gif\"><br></p><p><br></p>', '2018-01-02 14:55:47', '10');
INSERT INTO `info` VALUES ('39', 'java', '截图未命名222.jpg', '<p>java编程语言，我做java编程语言写程序，<br></p>', '2018-01-02 14:47:51', '10');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('1', 'java工程师');
INSERT INTO `job` VALUES ('2', '.net工程师');
INSERT INTO `job` VALUES ('3', 'php工程师');
INSERT INTO `job` VALUES ('4', 'ruby工程师');
INSERT INTO `job` VALUES ('15', 'python工程师');
INSERT INTO `job` VALUES ('16', 'c工程师');
INSERT INTO `job` VALUES ('17', 'ios工程师');
INSERT INTO `job` VALUES ('18', 'android工程师');
INSERT INTO `job` VALUES ('19', '算法工程师');
INSERT INTO `job` VALUES ('20', 'oracle数据库管理员');
INSERT INTO `job` VALUES ('27', 'ruby程序员');

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES ('1', 'java');
INSERT INTO `label` VALUES ('3', 'web开发');
INSERT INTO `label` VALUES ('6', '安卓手机开发');
INSERT INTO `label` VALUES ('5', '时尚服装');
INSERT INTO `label` VALUES ('7', '苹果手机开发');

-- ----------------------------
-- Table structure for labelinfo
-- ----------------------------
DROP TABLE IF EXISTS `labelinfo`;
CREATE TABLE `labelinfo` (
  `label_id` int(11) NOT NULL,
  `info_id` int(11) NOT NULL,
  KEY `label_idx` (`label_id`),
  KEY `info_idx` (`info_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of labelinfo
-- ----------------------------
INSERT INTO `labelinfo` VALUES ('1', '40');
INSERT INTO `labelinfo` VALUES ('1', '39');
INSERT INTO `labelinfo` VALUES ('3', '40');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '',
  `password` varchar(100) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL COMMENT '生日 ',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `username` (`username`),
  KEY `dept_user` (`dept_id`),
  CONSTRAINT `dept_user` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16', 'gsdafdfsa', 'fgfadsffg', '2', 'adsffdhads@163.com', 'F', null);
INSERT INTO `user` VALUES ('18', 'fgfthfhgr', 'sdsaffgfg', '1', 'fgfgrtweqr@163.com', 'F', null);
INSERT INTO `user` VALUES ('19', 'fgfdgfs', 'dfgdfsgfdg', '3', 'gffgfgfg@163.com', 'M', null);
INSERT INTO `user` VALUES ('20', 'dfads', 'dsfsdaf', '2', 'fdsgsfdgsdff@163.com', 'M', null);
INSERT INTO `user` VALUES ('22', 'gffd', 'dfgdfg', '1', 'dfsgfdsg@163.com', 'M', null);
INSERT INTO `user` VALUES ('23', 'dsfgdas', 'dsafadsf', '1', 'sadfadsf@163.com', 'M', null);
INSERT INTO `user` VALUES ('24', 'dsfdasf', 'fdgfsd', '2', 'fgfdsg@163.com', 'F', null);
INSERT INTO `user` VALUES ('25', 'fsgfdsg', 'fdsgfdsg', '3', 'fdsgdfsgf@163.com', 'F', null);
INSERT INTO `user` VALUES ('26', 'fgfsdg', 'fgfsdg', '4', 'fdsgfdsg@163.com', 'M', null);
INSERT INTO `user` VALUES ('27', 'fdsgfdsg', 'fdgfdsg', '2', 'fdgfdsg@163.com', 'M', null);
INSERT INTO `user` VALUES ('28', 'fgfg', 'fdgsffds', '1', 'dfsgdsaf@163.com', 'M', null);
INSERT INTO `user` VALUES ('29', 'trtfy', 'rtyert', '1', 'yrtey@163.com', 'M', null);
