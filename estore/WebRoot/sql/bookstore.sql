/*
SQLyog Ultimate v8.32 
MySQL - 5.1.32-community-log : Database - bookstore
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `adminuser` */

CREATE TABLE `adminuser` (
  `auid` char(32) NOT NULL,
  `adminname` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`auid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `adminuser` */

insert  into `adminuser`(`auid`,`adminname`,`password`) values ('au1','zhangSan','123');
insert  into `adminuser`(`auid`,`adminname`,`password`) values ('au2','liSi','123');
insert  into `adminuser`(`auid`,`adminname`,`password`) values ('au3','wangWu','123');

/*Table structure for table `book` */

CREATE TABLE `book` (
  `bid` char(32) NOT NULL,
  `bname` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `cid` char(32) DEFAULT NULL,
  `isdel` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`bid`),
  KEY `cid` (`cid`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('5652c363f7d44ca5aac42d861697faec','Java讲义','78.00','李刚','book_img/5652c363f7d44ca5aac42d861697faec.jpg','c1',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('a0c8967477dc43f7a489405701748805','疯狂java讲义','76.30','李刚刚','book_img/a0c8967477dc43f7a489405701748805.jpg','0658b1e7deab4d2bb6b1a8e1ef746c3a',1);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('ada25630de72485ba58a316031443a8c','疯狂Java讲义','76.30','李刚刚','book_img/ada25630de72485ba58a316031443a8c.jpg','c1',1);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('b1','Java编程思想（第4版）','75.60','qdmmy6','book_img/9317290-1_l.jpg','c1',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('b2','Java核心技术卷1','68.50','qdmmy6','book_img/20285763-1_l.jpg','c1',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('b3','Java就业培训教程','39.90','张孝祥','book_img/8758723-1_l.jpg','c1',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('b4','Head First java','47.50','（美）塞若','book_img/9265169-1_l.jpg','c1',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('b5','JavaWeb开发详解','83.30','孙鑫','book_img/22788412-1_l.jpg','c2',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('b6','Struts2深入详解','63.20','孙鑫','book_img/20385925-1_l.jpg','c2',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('b7','精通Hibernate','30.00','孙卫琴','book_img/8991366-1_l.jpg','c2',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('b8','精通Spring2.x','63.20','陈华雄','book_img/20029394-1_l.jpg','c2',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('b9','Javascript权威指南','93.60','（美）弗兰纳根','book_img/22722790-1_l.jpg','c3',0);
insert  into `book`(`bid`,`bname`,`price`,`author`,`image`,`cid`,`isdel`) values ('bc57600bc7f94dcda62165947f60c23f','JavaScript基础与案例','31.90','张孝祥','book_img/bc57600bc7f94dcda62165947f60c23f.jpg','c3',0);

/*Table structure for table `category` */

CREATE TABLE `category` (
  `cid` char(32) NOT NULL,
  `cname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`cid`,`cname`) values ('0658b1e7deab4d2bb6b1a8e1ef746c3a','struts');
insert  into `category`(`cid`,`cname`) values ('13d4ff8d108c4126ae168f421a44283d','hibernate');
insert  into `category`(`cid`,`cname`) values ('27f03dddc4e3439ba076d29455e7adfd','.net');
insert  into `category`(`cid`,`cname`) values ('c1','Java SE');
insert  into `category`(`cid`,`cname`) values ('c2','Java EE');
insert  into `category`(`cid`,`cname`) values ('c3','Javascript');
insert  into `category`(`cid`,`cname`) values ('c42903dfd2a34d88b2e2f14202eec38d','spring');

/*Table structure for table `orderitem` */

CREATE TABLE `orderitem` (
  `itemid` char(32) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `bid` char(32) DEFAULT NULL,
  `oid` char(32) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `bid` (`bid`),
  KEY `oid` (`oid`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`bid`) REFERENCES `book` (`bid`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderitem` */

insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('009b75f0af5f43fcbe50d0e6be84403d',1,'93.60','b9','a792a81331ba41539f8baa8602f9fc8f');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('0a7fbd0de6cd4175a04d99c45eb83fdf',1,'93.60','b9','1e887ec27b18477caeaf61394f63d71c');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('1ef8928ba1bd43df916850f70d74b57b',1,'47.50','b4','f2d74b37f83f4bd39f5466240274a923');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('1f2ac5e54d0249f9b5a19dcab4399c8c',1,'63.20','b8','fd03aa3519444c41b8c93dbf6a5d1677');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('510fb211bbb94eceab05e92b71312a06',1,'83.30','b5','25e57f927385432c8940878de4088b3b');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('587163010997414984b0da084a3ec8f6',1,'75.60','b1','f2d74b37f83f4bd39f5466240274a923');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('700bde72bc744986bcf2ce6ed0cc070e',2,'137.00','b2','d1b85bfc71564b18bf7802582a9fd934');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('74725fccdc434f8b89a67d947077887c',1,'39.90','b3','f2d74b37f83f4bd39f5466240274a923');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('7d5b46355df04cc4872675485c8a2e31',1,'78.00','5652c363f7d44ca5aac42d861697faec','1807e63742c44be7996e8216505c2f98');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('8f587ed23e234fc5ae9242dd69757524',2,'126.40','b6','8691b4150a0641e7a8729fd5e668820c');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('95c3010652c644e1973db88a69f2937c',2,'60.00','b7','fd03aa3519444c41b8c93dbf6a5d1677');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('9d3c7598becc4681b99201f482aae701',3,'119.70','b3','b363549ff6fb4a2b90b54ef58cc95748');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('aeff00f85ac6440c93419a43bf347ab4',1,'75.60','b1','2163bb668fd34833aa53068c997f5ca3');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('b0eeecc80e0e400baccce3a3633deecb',1,'83.30','b5','f2d74b37f83f4bd39f5466240274a923');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('b37e66bd0e984763952ebaa8683d97c0',1,'68.50','b2','f2d74b37f83f4bd39f5466240274a923');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('b45f87ef5d1d4219ade541e6689c7667',1,'30.00','b7','f2d74b37f83f4bd39f5466240274a923');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('b957162a97d246d39e9e03cc88a405a3',3,'189.60','b6','fd03aa3519444c41b8c93dbf6a5d1677');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('bd904c345d8c4a53be19a632ef59c7fa',2,'187.20','b9','b363549ff6fb4a2b90b54ef58cc95748');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('c54f2dffe9ce4337a5c20d8e93e72d0c',1,'63.20','b8','153839427aa94f359fe51932d9f9e383');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('cf8e76031270497aadfb8ca86cf70c4e',1,'93.60','b9','b553845a7b0e4e67abed47486144c9e8');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('d5e71f5272734b6ebca15cf7ed41d62b',2,'137.00','b2','1e887ec27b18477caeaf61394f63d71c');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('d6a1ecaecc524c8e8c5f7ec990fa5dd9',1,'31.90','bc57600bc7f94dcda62165947f60c23f','4861ad3d321f455ba58fb7d86c94cc9a');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('da1b6e12913040aea2e3dd8cb5a96f56',2,'126.40','b6','2163bb668fd34833aa53068c997f5ca3');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('ea681bbd0786475395ff1a731f7432b1',2,'95.00','b4','25e57f927385432c8940878de4088b3b');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('ebb610b0c7e4407bbdc94e395408e055',1,'68.50','b2','b553845a7b0e4e67abed47486144c9e8');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('f720a9d9afe94b60adcdffce991010e8',3,'189.60','b8','25e57f927385432c8940878de4088b3b');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('item1',2,'300.00','b1','o1');
insert  into `orderitem`(`itemid`,`count`,`subtotal`,`bid`,`oid`) values ('item2',3,'500.00','b2','o1');

/*Table structure for table `orders` */

CREATE TABLE `orders` (
  `oid` char(32) NOT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `ordertime` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `uid` char(32) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `uid` (`uid`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('153839427aa94f359fe51932d9f9e383','63.20','2013-06-04 15:02:31',2,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('1807e63742c44be7996e8216505c2f98','78.00','2013-06-06 08:44:34',2,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('1e887ec27b18477caeaf61394f63d71c','230.60','2013-06-04 14:59:14',1,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('2163bb668fd34833aa53068c997f5ca3','202.00','2013-06-04 14:49:29',1,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('25e57f927385432c8940878de4088b3b','367.90','2013-06-04 14:53:06',1,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('4861ad3d321f455ba58fb7d86c94cc9a','31.90','2013-06-06 08:59:56',3,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('8691b4150a0641e7a8729fd5e668820c','126.40','2013-06-04 15:56:53',4,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('a792a81331ba41539f8baa8602f9fc8f','93.60','2013-06-04 14:55:11',2,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('b363549ff6fb4a2b90b54ef58cc95748','306.90','2013-06-04 14:50:51',1,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('b553845a7b0e4e67abed47486144c9e8','162.10','2013-06-04 14:56:01',1,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('d1b85bfc71564b18bf7802582a9fd934','137.00','2013-06-04 15:01:01',4,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('f2d74b37f83f4bd39f5466240274a923','344.80','2013-06-04 17:50:35',1,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('fd03aa3519444c41b8c93dbf6a5d1677','312.80','2013-06-04 14:54:06',1,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');
insert  into `orders`(`oid`,`total`,`ordertime`,`state`,`address`,`uid`) values ('o1','100.00','2013-06-04 12:47:41',1,'北京市一大神','a9ef3cd19c22468c8fb8c4d296d5cff3');

/*Table structure for table `user` */

CREATE TABLE `user` (
  `uid` char(32) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `state` tinyint(1) DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`password`,`email`,`state`,`code`) values ('3f31c4d780f348d1a6f94431f206be83','ccc','ccc','itcast_cxf@qq.com',0,'79d720d5b05e4313b93428456635c1d71caa4215a37f48058741f1fec8adffd8');
insert  into `user`(`uid`,`username`,`password`,`email`,`state`,`code`) values ('5366fce331cd4adcabd8506186c73bb7','ddd','ddd','itcast_cxf@sohu.com',0,'51b9924bfcb04f509791eaf1186f945b822ef24866724f439a5ab5af6103e2f2');
insert  into `user`(`uid`,`username`,`password`,`email`,`state`,`code`) values ('a9ef3cd19c22468c8fb8c4d296d5cff3','aaa','aaa','itcast_cxf@163.com',1,'1d2b527b2d58459b9ab0c06f1f7b256a2f592a9679124d09b9f716faaefddd09');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
