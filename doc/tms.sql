-- MySQL dump 10.13  Distrib 5.1.51, for Win32 (ia32)
--
-- Host: localhost    Database: tms
-- ------------------------------------------------------
-- Server version	5.1.51-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_app_dict_data`
--

DROP TABLE IF EXISTS `t_app_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_app_dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `dict_cd` varchar(50) DEFAULT NULL,
  `dict_name` varchar(255) DEFAULT NULL,
  `disp_order_no` decimal(12,0) DEFAULT NULL,
  `i18n` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `dict_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD7E2756A5D86820A` (`dict_type_id`),
  CONSTRAINT `FKD7E2756A5D86820A` FOREIGN KEY (`dict_type_id`) REFERENCES `t_app_dict_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_app_dict_data`
--

LOCK TABLES `t_app_dict_data` WRITE;
/*!40000 ALTER TABLE `t_app_dict_data` DISABLE KEYS */;
INSERT INTO `t_app_dict_data` (`id`, `created_date`, `updated_date`, `dict_cd`, `dict_name`, `disp_order_no`, `i18n`, `remark`, `dict_type_id`) VALUES (1,'2013-03-30 15:02:01','2013-03-30 15:02:01','11','11','1','','',1),(2,'2013-03-30 15:02:01','2013-03-30 15:02:01','22','22','2','','',1),(3,'2013-03-30 15:02:01','2013-03-30 15:02:01','33','33','3','','',1);
/*!40000 ALTER TABLE `t_app_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_app_dict_type`
--

DROP TABLE IF EXISTS `t_app_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_app_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `dict_type_cd` varchar(255) DEFAULT NULL,
  `dict_type_name` varchar(255) DEFAULT NULL,
  `disp_order_no` decimal(12,0) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_app_dict_type`
--

LOCK TABLES `t_app_dict_type` WRITE;
/*!40000 ALTER TABLE `t_app_dict_type` DISABLE KEYS */;
INSERT INTO `t_app_dict_type` (`id`, `created_date`, `updated_date`, `dict_type_cd`, `dict_type_name`, `disp_order_no`, `remark`) VALUES (1,'2013-03-30 15:02:01','2013-03-30 15:02:26','cata_111','111','222','33');
/*!40000 ALTER TABLE `t_app_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_dept`
--

DROP TABLE IF EXISTS `t_sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  `disp_order_no` decimal(12,0) DEFAULT NULL,
  `enable_flg` bit(1) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2B4CA3E2D6B950AC` (`parent_id`),
  CONSTRAINT `FK2B4CA3E2D6B950AC` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_dept`
--

LOCK TABLES `t_sys_dept` WRITE;
/*!40000 ALTER TABLE `t_sys_dept` DISABLE KEYS */;
INSERT INTO `t_sys_dept` (`id`, `created_date`, `updated_date`, `dept_name`, `disp_order_no`, `enable_flg`, `parent_id`, `remark`) VALUES (1,'2013-03-28 23:21:52','2013-03-30 00:14:51','普通用户','2','',NULL,'33'),(3,'2013-03-28 23:24:40','2013-04-04 21:03:06','技术管理部','1','',4,'33'),(4,'2013-03-28 23:26:00','2013-03-30 00:14:32','系统用户','1','',NULL,'');
/*!40000 ALTER TABLE `t_sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_menu`
--

DROP TABLE IF EXISTS `t_sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `enable_flg` bit(1) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2B50BAFCD6BD67C6` (`parent_id`),
  CONSTRAINT `FK2B50BAFCD6BD67C6` FOREIGN KEY (`parent_id`) REFERENCES `t_sys_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_menu`
--

LOCK TABLES `t_sys_menu` WRITE;
/*!40000 ALTER TABLE `t_sys_menu` DISABLE KEYS */;
INSERT INTO `t_sys_menu` (`id`, `created_date`, `updated_date`, `enable_flg`, `menu_name`, `remark`, `url`, `parent_id`) VALUES (1,NULL,NULL,'','系统管理','系统管理备注','',NULL),(2,NULL,'2013-04-04 21:10:37','','资源管理','资源管理备注','/admin/resource',4),(3,NULL,'2013-04-04 21:28:29','','菜单管理','菜单管理备注','/admin/menu',1),(4,NULL,NULL,'','组织管理','组织管理备注','',NULL),(5,NULL,'2013-03-30 14:46:44','','人员管理','机构管理备注','/admin/user',1);
/*!40000 ALTER TABLE `t_sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_menu_perm_rela`
--

DROP TABLE IF EXISTS `t_sys_menu_perm_rela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_menu_perm_rela` (
  `menu_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FKD14F215493FC22F1` (`menu_id`),
  KEY `FKD14F2154BFC13FB1` (`permission_id`),
  CONSTRAINT `FKD14F215493FC22F1` FOREIGN KEY (`menu_id`) REFERENCES `t_sys_menu` (`id`),
  CONSTRAINT `FKD14F2154BFC13FB1` FOREIGN KEY (`permission_id`) REFERENCES `t_sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_menu_perm_rela`
--

LOCK TABLES `t_sys_menu_perm_rela` WRITE;
/*!40000 ALTER TABLE `t_sys_menu_perm_rela` DISABLE KEYS */;
INSERT INTO `t_sys_menu_perm_rela` (`menu_id`, `permission_id`) VALUES (2,1),(3,4);
/*!40000 ALTER TABLE `t_sys_menu_perm_rela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_permission`
--

DROP TABLE IF EXISTS `t_sys_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `perm_cd` varchar(255) DEFAULT NULL,
  `perm_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_permission`
--

LOCK TABLES `t_sys_permission` WRITE;
/*!40000 ALTER TABLE `t_sys_permission` DISABLE KEYS */;
INSERT INTO `t_sys_permission` (`id`, `created_date`, `updated_date`, `perm_cd`, `perm_name`, `remark`) VALUES (1,NULL,NULL,'user:create','新增用户','3333'),(2,NULL,NULL,'user:delete','删除用户',''),(3,NULL,NULL,'user:update','修改用户',''),(4,NULL,NULL,'admin:manager','后台管理员','后台管理权限');
/*!40000 ALTER TABLE `t_sys_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_resource`
--

DROP TABLE IF EXISTS `t_sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `enable_flg` bit(1) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_resource`
--

LOCK TABLES `t_sys_resource` WRITE;
/*!40000 ALTER TABLE `t_sys_resource` DISABLE KEYS */;
INSERT INTO `t_sys_resource` (`id`, `created_date`, `updated_date`, `enable_flg`, `remark`, `url`) VALUES (3,NULL,'2013-03-24 21:10:15','','后台管理地址ss','/admin/**'),(4,'2013-03-24 21:04:49','2013-03-24 21:11:19','','测试ssss','/user/**'),(5,'2013-03-24 21:10:29','2013-03-24 21:10:29','','2','2');
/*!40000 ALTER TABLE `t_sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_resource_perm_rela`
--

DROP TABLE IF EXISTS `t_sys_resource_perm_rela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_resource_perm_rela` (
  `resource_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FK19B08AC3BFC13FB1` (`permission_id`),
  KEY `FK19B08AC32BFC4D11` (`resource_id`),
  CONSTRAINT `FK19B08AC32BFC4D11` FOREIGN KEY (`resource_id`) REFERENCES `t_sys_resource` (`id`),
  CONSTRAINT `FK19B08AC3BFC13FB1` FOREIGN KEY (`permission_id`) REFERENCES `t_sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_resource_perm_rela`
--

LOCK TABLES `t_sys_resource_perm_rela` WRITE;
/*!40000 ALTER TABLE `t_sys_resource_perm_rela` DISABLE KEYS */;
INSERT INTO `t_sys_resource_perm_rela` (`resource_id`, `permission_id`) VALUES (3,4);
/*!40000 ALTER TABLE `t_sys_resource_perm_rela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role`
--

DROP TABLE IF EXISTS `t_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `role_cd` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role`
--

LOCK TABLES `t_sys_role` WRITE;
/*!40000 ALTER TABLE `t_sys_role` DISABLE KEYS */;
INSERT INTO `t_sys_role` (`id`, `created_date`, `updated_date`, `remark`, `role_cd`, `role_name`) VALUES (1,NULL,NULL,'asdadddddddddddddddddddddddddddddddddddddddddddddddddddddddddddaaaaaaaaaaaaaaaa','admin','管理员'),(2,NULL,NULL,'','user','普通用户'),(3,NULL,NULL,'','superadmin','超级管理员');
/*!40000 ALTER TABLE `t_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role_perm_rela`
--

DROP TABLE IF EXISTS `t_sys_role_perm_rela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_role_perm_rela` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  KEY `FK2E13DBABAD6AB791` (`role_id`),
  KEY `FK2E13DBABBFC13FB1` (`permission_id`),
  CONSTRAINT `FK2E13DBABAD6AB791` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`),
  CONSTRAINT `FK2E13DBABBFC13FB1` FOREIGN KEY (`permission_id`) REFERENCES `t_sys_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role_perm_rela`
--

LOCK TABLES `t_sys_role_perm_rela` WRITE;
/*!40000 ALTER TABLE `t_sys_role_perm_rela` DISABLE KEYS */;
INSERT INTO `t_sys_role_perm_rela` (`role_id`, `permission_id`) VALUES (1,4),(1,1),(3,1),(3,2),(3,4),(3,3),(1,3),(2,2),(2,3),(2,4);
/*!40000 ALTER TABLE `t_sys_role_perm_rela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role_user_rela`
--

DROP TABLE IF EXISTS `t_sys_role_user_rela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_role_user_rela` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK1A91B3B0AD6AB791` (`role_id`),
  KEY `FK1A91B3B052957B71` (`user_id`),
  CONSTRAINT `FK1A91B3B052957B71` FOREIGN KEY (`user_id`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `FK1A91B3B0AD6AB791` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role_user_rela`
--

LOCK TABLES `t_sys_role_user_rela` WRITE;
/*!40000 ALTER TABLE `t_sys_role_user_rela` DISABLE KEYS */;
INSERT INTO `t_sys_role_user_rela` (`user_id`, `role_id`) VALUES (1,1),(2,2),(5,2),(4,2),(4,3),(6,1);
/*!40000 ALTER TABLE `t_sys_role_user_rela` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user`
--

DROP TABLE IF EXISTS `t_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `enable_flg` bit(1) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  `disp_order_no` decimal(12,0) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2B549168B7FBB131` (`dept_id`),
  CONSTRAINT `FK2B549168B7FBB131` FOREIGN KEY (`dept_id`) REFERENCES `t_sys_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user`
--

LOCK TABLES `t_sys_user` WRITE;
/*!40000 ALTER TABLE `t_sys_user` DISABLE KEYS */;
INSERT INTO `t_sys_user` (`id`, `created_date`, `updated_date`, `enable_flg`, `login_name`, `name`, `password`, `register_date`, `salt`, `dept_id`, `disp_order_no`, `remark`) VALUES (1,NULL,'2013-05-12 12:52:14','','admin','Admin','b7518c48df37003f1427d8604a05fa0502592dbe','2012-06-04 01:00:00','3c6936104a285212',4,NULL,'22'),(2,NULL,'2013-03-30 00:09:33','','user','Calvin','99d8c09ac579f626ae771a84b3424d78939f84c7','2012-06-04 02:00:00','bd4615b609d074e8',4,NULL,''),(4,'2013-03-29 00:30:46','2013-04-04 21:28:53','','1111','333','b68710b758a8d7a1de7059872391e872fd0a2fec',NULL,'8e566c2eb04c3b10',3,'2',''),(5,'2013-03-29 00:46:11','2013-03-29 00:46:11','','ddd','aaa','5dc99e2ded4235fd398977fdda93301e8a36f482',NULL,'d1b3993ce99b5207',3,'1','aaa'),(6,'2013-03-29 00:47:30','2013-03-29 00:47:38','','444','444','b001c730982c0bed4fc7ffea7a2b6be3e3291dc7',NULL,'5c14d01ce499ca0c',3,'444','eee');
/*!40000 ALTER TABLE `t_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_task`
--

DROP TABLE IF EXISTS `t_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCB63167052957B71` (`user_id`),
  CONSTRAINT `FKCB63167052957B71` FOREIGN KEY (`user_id`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_task`
--

LOCK TABLES `t_task` WRITE;
/*!40000 ALTER TABLE `t_task` DISABLE KEYS */;
INSERT INTO `t_task` (`id`, `created_date`, `updated_date`, `description`, `title`, `user_id`) VALUES (1,NULL,NULL,'http://www.playframework.org/','Study PlayFramework 2.0',2),(2,NULL,NULL,'http://www.grails.org/','Study Grails 2.0',2),(3,NULL,NULL,'http://www.springfuse.com/','Try SpringFuse',2),(4,NULL,NULL,'http://www.springsource.org/spring-roo','Try Spring Roo',2),(5,NULL,NULL,'As soon as posibble.','Release SpringSide 4.0',2),(6,'2013-03-30 14:55:56','2013-03-30 14:55:56','23123','1231',NULL);
/*!40000 ALTER TABLE `t_task` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-13 23:57:20
