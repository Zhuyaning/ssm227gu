-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: ssm227gu
-- ------------------------------------------------------
-- Server version	5.7.31

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `address` varchar(200) NOT NULL COMMENT '地址',
  `name` varchar(200) NOT NULL COMMENT '收货人',
  `phone` varchar(200) NOT NULL COMMENT '电话',
  `isdefault` varchar(200) NOT NULL COMMENT '是否默认地址[是/否]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1619002946171 DEFAULT CHARSET=utf8 COMMENT='地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'2021-04-21 10:51:42',1,'宇宙银河系金星1号','金某','13823888881','是'),(2,'2021-04-21 10:51:42',2,'宇宙银河系木星1号','木某','13823888882','是'),(3,'2021-04-21 10:51:42',3,'宇宙银河系水星1号','水某','13823888883','是'),(4,'2021-04-21 10:51:42',4,'宇宙银河系火星1号','火某','13823888884','是'),(5,'2021-04-21 10:51:42',5,'宇宙银河系土星1号','土某','13823888885','是'),(6,'2021-04-21 10:51:42',6,'宇宙银河系月球1号','月某','13823888886','是'),(1619002946170,'2021-04-21 11:02:26',1619002889516,'广东省梅州市平远县大柘镇高涧坑水库','胡经','13513512522','是');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tablename` varchar(200) DEFAULT 'shangpinxinxi' COMMENT '商品表名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `picture` varchar(200) DEFAULT NULL COMMENT '图片',
  `buynumber` int(11) NOT NULL COMMENT '购买数量',
  `price` float DEFAULT NULL COMMENT '单价',
  `discountprice` float DEFAULT NULL COMMENT '会员价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1619002902274 DEFAULT CHARSET=utf8 COMMENT='购物车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','http://localhost:8080/ssm227gu/upload/picture1.jpg'),(2,'picture2','http://localhost:8080/ssm227gu/upload/picture2.jpg'),(3,'picture3','http://localhost:8080/ssm227gu/upload/picture3.jpg'),(6,'homepage','http://localhost:8080/ssm227gu/upload/1619002825158.jpg');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussshangpinxinxi`
--

DROP TABLE IF EXISTS `discussshangpinxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussshangpinxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `nickname` varchar(200) DEFAULT NULL COMMENT '用户名',
  `content` longtext NOT NULL COMMENT '评论内容',
  `reply` longtext COMMENT '回复内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1619002906157 DEFAULT CHARSET=utf8 COMMENT='商品信息评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussshangpinxinxi`
--

LOCK TABLES `discussshangpinxinxi` WRITE;
/*!40000 ALTER TABLE `discussshangpinxinxi` DISABLE KEYS */;
INSERT INTO `discussshangpinxinxi` VALUES (91,'2021-04-21 10:51:42',1,1,'用户名1','评论内容1','回复内容1'),(92,'2021-04-21 10:51:42',2,2,'用户名2','评论内容2','回复内容2'),(93,'2021-04-21 10:51:42',3,3,'用户名3','评论内容3','回复内容3'),(94,'2021-04-21 10:51:42',4,4,'用户名4','评论内容4','回复内容4'),(95,'2021-04-21 10:51:42',5,5,'用户名5','评论内容5','回复内容5'),(96,'2021-04-21 10:51:42',6,6,'用户名6','评论内容6','回复内容6'),(1619002906156,'2021-04-21 11:01:45',1619002815063,1619002889516,'111','1111',NULL);
/*!40000 ALTER TABLE `discussshangpinxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `introduction` longtext COMMENT '简介',
  `picture` varchar(200) NOT NULL COMMENT '图片',
  `content` longtext NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1619002852850 DEFAULT CHARSET=utf8 COMMENT='系统公告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (81,'2021-04-21 10:51:42','标题1','简介1','http://localhost:8080/ssm227gu/upload/news_picture1.jpg','内容1'),(82,'2021-04-21 10:51:42','标题2','简介2','http://localhost:8080/ssm227gu/upload/news_picture2.jpg','内容2'),(83,'2021-04-21 10:51:42','标题3','简介3','http://localhost:8080/ssm227gu/upload/news_picture3.jpg','内容3'),(84,'2021-04-21 10:51:42','标题4','简介4','http://localhost:8080/ssm227gu/upload/news_picture4.jpg','内容4'),(85,'2021-04-21 10:51:42','标题5','简介5','http://localhost:8080/ssm227gu/upload/news_picture5.jpg','内容5'),(86,'2021-04-21 10:51:42','标题6','简介6','http://localhost:8080/ssm227gu/upload/news_picture6.jpg','内容6'),(1619002852849,'2021-04-21 11:00:52','系统公告','线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v','http://localhost:8080/ssm227gu/upload/1619002839617.jpg','<p>线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v<img src=\"http://localhost:8080/ssm227gu/upload/1619002849726.jpg\">线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v</p>');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `orderid` varchar(200) NOT NULL COMMENT '订单编号',
  `tablename` varchar(200) DEFAULT 'shangpinxinxi' COMMENT '商品表名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `picture` varchar(200) DEFAULT NULL COMMENT '商品图片',
  `buynumber` int(11) NOT NULL COMMENT '购买数量',
  `price` float NOT NULL DEFAULT '0' COMMENT '价格/积分',
  `discountprice` float DEFAULT '0' COMMENT '折扣价格',
  `total` float NOT NULL DEFAULT '0' COMMENT '总价格/总积分',
  `discounttotal` float DEFAULT '0' COMMENT '折扣总价格',
  `type` int(11) DEFAULT '1' COMMENT '支付类型',
  `status` varchar(200) DEFAULT NULL COMMENT '状态',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `tel` varchar(200) DEFAULT NULL COMMENT '电话',
  `consignee` varchar(200) DEFAULT NULL COMMENT '收货人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderid` (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=1619003011270 DEFAULT CHARSET=utf8 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1619002960201,'2021-04-21 11:02:39','202142119114417918369','shangpinxinxi',1619002889516,32,'商品名称2','http://localhost:8080/ssm227gu/upload/shangpinxinxi_fengmian2.jpg',2,99.9,99.9,199.8,199.8,1,'已取消','广东省梅州市平远县大柘镇高涧坑水库','13513512522','胡经'),(1619002982051,'2021-04-21 11:03:02','20214211912639511027','shangpinxinxi',1619002889516,33,'商品名称3','http://localhost:8080/ssm227gu/upload/shangpinxinxi_fengmian3.jpg',3,99.9,99.9,299.7,299.7,1,'已退款','广东省梅州市平远县大柘镇高涧坑水库','13513512522','胡经'),(1619003011269,'2021-04-21 11:03:31','202142119123556747839','shangpinxinxi',1619002889516,1619002815063,'王者荣誉手办','http://localhost:8080/ssm227gu/upload/1619002755633.jpg',4,20,20,80,80,1,'已完成','广东省梅州市平远县大柘镇高涧坑水库','13513512522','胡经');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shangpinfenlei`
--

DROP TABLE IF EXISTS `shangpinfenlei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shangpinfenlei` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shangpinfenlei` varchar(200) NOT NULL COMMENT '商品分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `shangpinfenlei` (`shangpinfenlei`)
) ENGINE=InnoDB AUTO_INCREMENT=1619002702599 DEFAULT CHARSET=utf8 COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shangpinfenlei`
--

LOCK TABLES `shangpinfenlei` WRITE;
/*!40000 ALTER TABLE `shangpinfenlei` DISABLE KEYS */;
INSERT INTO `shangpinfenlei` VALUES (21,'2021-04-21 10:51:41','商品分类1'),(22,'2021-04-21 10:51:41','商品分类2'),(23,'2021-04-21 10:51:41','商品分类3'),(24,'2021-04-21 10:51:41','商品分类4'),(25,'2021-04-21 10:51:41','商品分类5'),(26,'2021-04-21 10:51:41','商品分类6'),(1619002687455,'2021-04-21 10:58:07','手办'),(1619002694784,'2021-04-21 10:58:14','卡片'),(1619002702598,'2021-04-21 10:58:22','书籍');
/*!40000 ALTER TABLE `shangpinfenlei` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shangpinxinxi`
--

DROP TABLE IF EXISTS `shangpinxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shangpinxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `shangpinbianhao` varchar(200) DEFAULT NULL COMMENT '商品编号',
  `shangpinmingcheng` varchar(200) DEFAULT NULL COMMENT '商品名称',
  `shangpinfenlei` varchar(200) DEFAULT NULL COMMENT '商品分类',
  `pinpai` varchar(200) DEFAULT NULL COMMENT '品牌',
  `guige` varchar(200) DEFAULT NULL COMMENT '规格',
  `xiangqing` longtext COMMENT '详情',
  `fengmian` varchar(200) DEFAULT NULL COMMENT '封面',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  `price` float NOT NULL COMMENT '价格',
  `onelimittimes` int(11) DEFAULT '-1' COMMENT '单限',
  `alllimittimes` int(11) DEFAULT '-1' COMMENT '库存',
  PRIMARY KEY (`id`),
  UNIQUE KEY `shangpinbianhao` (`shangpinbianhao`)
) ENGINE=InnoDB AUTO_INCREMENT=1619002815064 DEFAULT CHARSET=utf8 COMMENT='商品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shangpinxinxi`
--

LOCK TABLES `shangpinxinxi` WRITE;
/*!40000 ALTER TABLE `shangpinxinxi` DISABLE KEYS */;
INSERT INTO `shangpinxinxi` VALUES (31,'2021-04-21 10:51:41','商品编号1','商品名称1','商品分类1','品牌1','规格1','详情1','http://localhost:8080/ssm227gu/upload/shangpinxinxi_fengmian1.jpg','2021-04-21 18:51:41',1,99.9,1,99),(32,'2021-04-21 10:51:41','商品编号2','商品名称2','商品分类2','品牌2','规格2','详情2','http://localhost:8080/ssm227gu/upload/shangpinxinxi_fengmian2.jpg','2021-04-21 19:02:39',4,99.9,2,97),(33,'2021-04-21 10:51:41','商品编号3','商品名称3','商品分类3','品牌3','规格3','详情3','http://localhost:8080/ssm227gu/upload/shangpinxinxi_fengmian3.jpg','2021-04-21 19:03:43',6,99.9,3,96),(34,'2021-04-21 10:51:41','商品编号4','商品名称4','商品分类4','品牌4','规格4','详情4','http://localhost:8080/ssm227gu/upload/shangpinxinxi_fengmian4.jpg','2021-04-21 18:51:41',4,99.9,4,99),(35,'2021-04-21 10:51:41','商品编号5','商品名称5','商品分类5','品牌5','规格5','详情5','http://localhost:8080/ssm227gu/upload/shangpinxinxi_fengmian5.jpg','2021-04-21 18:51:41',5,99.9,5,99),(36,'2021-04-21 10:51:41','商品编号6','商品名称6','商品分类6','品牌6','规格6','详情6','http://localhost:8080/ssm227gu/upload/shangpinxinxi_fengmian6.jpg','2021-04-21 18:51:41',6,99.9,6,99),(1619002815063,'2021-04-21 11:00:14','1619003250908','王者荣誉手办','手办','美的','30*40','<p><strong style=\"background-color: rgb(73, 161, 234);\">线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v</strong><img src=\"http://localhost:8080/ssm227gu/upload/1619002803359.jpg\"><img src=\"http://localhost:8080/ssm227gu/upload/1619002808921.jpg\"><strong style=\"background-color: rgb(73, 161, 234);\">线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城线上动漫周边商城v线上动漫周边商城v</strong></p>','http://localhost:8080/ssm227gu/upload/1619002755633.jpg','2021-04-21 19:03:31',8,20,1000,996);
/*!40000 ALTER TABLE `shangpinxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) DEFAULT NULL COMMENT '收藏id',
  `tablename` varchar(200) DEFAULT NULL COMMENT '表名',
  `name` varchar(200) NOT NULL COMMENT '收藏名称',
  `picture` varchar(200) NOT NULL COMMENT '收藏图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1619002898864 DEFAULT CHARSET=utf8 COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
INSERT INTO `storeup` VALUES (1619002898863,'2021-04-21 11:01:38',1619002889516,1619002815063,'shangpinxinxi','王者荣誉手办','http://localhost:8080/ssm227gu/upload/1619002755633.jpg');
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,1,'abo','users','管理员','l6nhascb69eq3bbmlo2vt8u943hw6iy9','2021-04-21 10:57:53','2021-04-21 12:04:05'),(2,1619002889516,'111','yonghu','用户','81g79hksup2uqgah2fw5gndejbey7bnc','2021-04-21 11:01:35','2021-04-21 12:04:25');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'abo','abo','管理员','2021-04-21 10:51:42');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yonghu`
--

DROP TABLE IF EXISTS `yonghu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yonghu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yonghuzhanghao` varchar(200) NOT NULL COMMENT '用户账号',
  `yonghuxingming` varchar(200) NOT NULL COMMENT '用户姓名',
  `mima` varchar(200) NOT NULL COMMENT '密码',
  `xingbie` varchar(200) DEFAULT NULL COMMENT '性别',
  `nianling` int(11) DEFAULT NULL COMMENT '年龄',
  `lianxidianhua` varchar(200) DEFAULT NULL COMMENT '联系电话',
  `money` float DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `yonghuzhanghao` (`yonghuzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=1619002889517 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yonghu`
--

LOCK TABLES `yonghu` WRITE;
/*!40000 ALTER TABLE `yonghu` DISABLE KEYS */;
INSERT INTO `yonghu` VALUES (11,'2021-04-21 10:51:41','用户1','用户姓名1','123456','男',1,'13823888881',100),(12,'2021-04-21 10:51:41','用户2','用户姓名2','123456','男',2,'13823888882',100),(13,'2021-04-21 10:51:41','用户3','用户姓名3','123456','男',3,'13823888883',100),(14,'2021-04-21 10:51:41','用户4','用户姓名4','123456','男',4,'13823888884',100),(15,'2021-04-21 10:51:41','用户5','用户姓名5','123456','男',5,'13823888885',100),(16,'2021-04-21 10:51:41','用户6','用户姓名6','123456','男',6,'13823888886',100),(1619002889516,'2021-04-21 11:01:29','111','胡继','111','女',23,'13613513525',2142);
/*!40000 ALTER TABLE `yonghu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-24 10:49:55
