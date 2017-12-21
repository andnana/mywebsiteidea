# Host: localhost  (Version 5.5.47)
# Date: 2017-12-21 16:06:48
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "dept"
#

DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

#
# Data for table "dept"
#

INSERT INTO `dept` VALUES (1,'财务部'),(2,'后勤部'),(3,'生产部'),(4,'IT部');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '',
  `password` varchar(100) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `username` (`username`),
  KEY `dept_user` (`dept_id`),
  CONSTRAINT `dept_user` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'username456','123123',2,'dsdgf456@163.com','F'),(2,'username123','123123',1,'fdsg123@163.com','M'),(4,'renjie','123',1,'ffhgdf@163.com','F'),(6,'xiaoming','sdfsadf',3,'rrre@163.com','M');
