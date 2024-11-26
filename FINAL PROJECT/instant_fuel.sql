/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 10.4.24-MariaDB : Database - instant_fuel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`instant_fuel` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `instant_fuel`;

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `b_id` int(100) NOT NULL AUTO_INCREMENT,
  `u_id` varchar(100) DEFAULT NULL,
  `card_no` varchar(100) DEFAULT NULL,
  `cvv_no` varchar(100) DEFAULT NULL,
  `pin` varchar(100) DEFAULT NULL,
  `balance` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `bank` */

insert  into `bank`(`b_id`,`u_id`,`card_no`,`cvv_no`,`pin`,`balance`,`status`) values (9,'11','853535827227','565','5522','48900.0','1'),(10,'13','123456789','123','1234','10000','1');

/*Table structure for table `customer_reg` */

DROP TABLE IF EXISTS `customer_reg`;

CREATE TABLE `customer_reg` (
  `c_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `dateofjoin` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT 'APPROVED',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `customer_reg` */

insert  into `customer_reg`(`c_id`,`name`,`address`,`phone`,`email`,`dateofjoin`,`status`) values (9,'Akhil','Pathanamthitta','9865485569','akhil@gmail.com','12/04/2021 at 02:38','APPROVED'),(10,'Athul','Ernakulam','9888665888','athul@gmail.com','12/04/2021 at 03:14','APPROVED'),(11,'Chandu','Fort Kochi','9687546446','chandhu@gmail.com','01/03/2022 at 04:35','APPROVED'),(12,'Anandhu','Elamkulam','8655434548','anadhu@gmail.com','03/03/2022 at 10:49','APPROVED'),(13,'aji','kaloor','9633961778','a@gmail.com','11/04/2022 at 09:48','APPROVED');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(20) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`fid`,`uid`,`subject`,`description`,`status`,`type`) values (14,34,'Fuel','Complaint','0','FUEL_STATION'),(15,11,'User','Something','0','CUSTOMER');

/*Table structure for table `fuel_request` */

DROP TABLE IF EXISTS `fuel_request`;

CREATE TABLE `fuel_request` (
  `c_rqst_id` int(20) NOT NULL AUTO_INCREMENT,
  `c_id` int(20) DEFAULT NULL COMMENT 'foriegn key customer',
  `fs_id` varchar(100) DEFAULT NULL,
  `fuel` varchar(100) DEFAULT NULL,
  `rqst_time` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT 'NO RQST',
  `station_rate` varchar(100) DEFAULT 'NOT_UPDATED',
  `rqst_lat` varchar(100) DEFAULT NULL,
  `rqst_long` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`c_rqst_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;

/*Data for the table `fuel_request` */

insert  into `fuel_request`(`c_rqst_id`,`c_id`,`fs_id`,`fuel`,`rqst_time`,`status`,`station_rate`,`rqst_lat`,`rqst_long`) values (72,9,'28','2','12/04/2021 at 04:47','APPROVED','NOT_UPDATED','9.979617','76.2762942'),(73,9,'29','2','12/04/2021 at 04:47','APPROVED','NOT_UPDATED','9.979617','76.2762942'),(74,9,'28','3','12/04/2021 at 07:40','APPROVED','NOT_UPDATED','9.979617','76.2762942'),(75,9,'29','2','12/04/2021 at 09:16','APPROVED','NOT_UPDATED','9.979617','76.2762942'),(76,10,'29','1','12/04/2021 at 09:22','REQUESTED','NOT_UPDATED','9.979617','76.2762942'),(77,10,'28','1.5','11/06/2021 at 08:48','APPROVED','NOT_UPDATED','9.979617','76.2762942'),(78,10,'29','2','11/06/2021 at 08:48','APPROVED','NOT_UPDATED','9.979617','76.2762942'),(79,10,'28','3','11/06/2021 at 08:50','APPROVED','NOT_UPDATED','9.979617','76.2762942'),(80,11,'34','5','01/03/2022 at 08:07','PAID','110','9.979617','76.2762942'),(81,11,'34','2','01/03/2022 at 08:08','REQUESTED','NOT_UPDATED','9.979617','76.2762942'),(82,11,'34','3','01/03/2022 at 08:08','REQUESTED','NOT_UPDATED','9.979617','76.2762942'),(83,12,'34','6','03/03/2022 at 02:29','APPROVED','99','9.979617','76.2762942'),(84,13,'34','2','11/04/2022 at 09:50','PAID','100','9.9796485','76.2762411'),(85,13,'36','3','12/04/2022 at 08:50','PAID','110','9.9796379','76.276256'),(86,13,'36','8','09/06/2022 at 10:11','PAID','115','9.9930474','76.29663');

/*Table structure for table `fuel_station_reg` */

DROP TABLE IF EXISTS `fuel_station_reg`;

CREATE TABLE `fuel_station_reg` (
  `fs_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `owner_name` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `dateofjoin` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT 'PENDING',
  `f_lat` varchar(100) DEFAULT NULL,
  `f_long` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`fs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

/*Data for the table `fuel_station_reg` */

insert  into `fuel_station_reg`(`fs_id`,`name`,`owner_name`,`address`,`phone`,`district`,`email`,`dateofjoin`,`status`,`f_lat`,`f_long`) values (34,'BPCL','Vishnu','Broadway, Church St, Marine Drive, Ernakulam, Kerala 682031, India','9539464779','Ernakulam','vishnu@gmail.com','28/02/2022 at 09:06','APPROVED','9.981954562665269','76.27642158418894'),(35,'HP','Raja Ravi Varma','X7GH+334, Marine Drive, Kochi, Kerala 682031, India','9685464767','Ernakulam','hp@gmail.com','03/03/2022 at 10:36','PENDING','9.975109427110036','76.2779051810503'),(36,'Indian Oil','Prashob','V7JQ+RRX, Kumbalangi, Kochi, Kerala 682007, India','9854644878','Ernakulam','prashob@gmail.com','04/03/2022 at 07:22','APPROVED','9.880674188074481','76.28947891294958');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `l_id` int(20) NOT NULL AUTO_INCREMENT,
  `reg_id` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT '0',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`l_id`,`reg_id`,`email`,`password`,`type`,`status`) values (1,'1','admin@gmail.com','admin','ADMIN','1'),(26,'28','prakashan@gmail.com','123456','SELLER','1'),(27,'9','akhil@gmail.com','123456','CUSTOMER','1'),(28,'29','hussain@gmail.com','123456','SELLER','1'),(29,'10','athul@gmail.com','123456','CUSTOMER','1'),(30,'34','vishnu@gmail.com','123456','FUEL_STATION','1'),(31,'11','chandhu@gmail.com','123456','CUSTOMER','1'),(32,'35','hp@gmail.com','123456','FUEL_STATION','0'),(33,'12','anadhu@gmail.com','123456','CUSTOMER','1'),(34,'36','prashob@gmail.com','123456','FUEL_STATION','1'),(35,'37','ar@gmail.com','123456','SERVICE_CENTER','1'),(36,'13','a@gmail.com','123','CUSTOMER','1'),(37,'38','sc@gmail.com','123','SERVICE_CENTER','1'),(39,'37','car@gmail.com','123','SHOP','1');

/*Table structure for table `mechanic` */

DROP TABLE IF EXISTS `mechanic`;

CREATE TABLE `mechanic` (
  `mid` int(55) NOT NULL AUTO_INCREMENT,
  `service_center` varchar(55) DEFAULT NULL,
  `name` varchar(55) DEFAULT NULL,
  `phone` varchar(55) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  `Adhar` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `mechanic` */

insert  into `mechanic`(`mid`,`service_center`,`name`,`phone`,`email`,`Adhar`) values (1,'38','Raju','9632518478','rajur@gmail.com','123'),(2,'37','Hari','7852369899','h@gmail.com','123'),(3,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
