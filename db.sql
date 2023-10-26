/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.28-MariaDB : Database - prosoftchat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftchat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `prosoftchat`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `AdminID` int(11) NOT NULL AUTO_INCREMENT,
  `KorisnickoIme` varchar(25) NOT NULL,
  `Sifra` varchar(25) NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `admin` */

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `korisnikID` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`korisnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`korisnikID`,`ime`,`prezime`,`username`,`password`) values 
(1,'ana','cvetkovic','ana','ana'),
(2,'milijana','pavlovic','mika','mika'),
(3,'jelena','filipovic','jela','jela'),
(4,'emilija','golubovic','ema','ema');

/*Table structure for table `poruka` */

DROP TABLE IF EXISTS `poruka`;

CREATE TABLE `poruka` (
  `porukaID` int(11) NOT NULL AUTO_INCREMENT,
  `odKoga` int(11) NOT NULL,
  `zaKoga` int(11) NOT NULL,
  `tekst` varchar(200) NOT NULL,
  `vreme` datetime DEFAULT NULL,
  PRIMARY KEY (`porukaID`),
  KEY `odKoga` (`odKoga`),
  KEY `zaKoga` (`zaKoga`),
  CONSTRAINT `poruka_ibfk_1` FOREIGN KEY (`odKoga`) REFERENCES `korisnik` (`korisnikID`),
  CONSTRAINT `poruka_ibfk_2` FOREIGN KEY (`zaKoga`) REFERENCES `korisnik` (`korisnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `poruka` */

insert  into `poruka`(`porukaID`,`odKoga`,`zaKoga`,`tekst`,`vreme`) values 
(20,1,1,'cao svima','2023-06-09 14:05:00'),
(21,1,2,'cao svima','2023-06-09 14:05:00'),
(22,1,3,'cao svima','2023-06-09 14:05:00'),
(23,1,2,'cao miko0','2023-06-09 14:05:10'),
(24,3,1,'kako ide','2023-06-09 14:06:00'),
(25,3,1,'kako ide anci','2023-06-09 14:06:50'),
(26,2,1,'i ja sam ovdeee','2023-06-09 14:07:00'),
(27,2,2,'i ja sam ovdeee','2023-06-09 14:07:00'),
(28,2,3,'i ja sam ovdeee','2023-06-09 14:07:00'),
(29,1,1,'izbavite me odavde','2023-06-09 14:10:00'),
(30,1,2,'izbavite me odavde','2023-06-09 14:10:00'),
(31,1,3,'izbavite me odavde','2023-06-09 14:10:00'),
(32,2,1,'hahaha nece niko da te izbavi','2023-06-09 14:10:30'),
(33,2,2,'hahaha nece niko da te izbavi','2023-06-09 14:10:30'),
(34,2,3,'hahaha nece niko da te izbavi','2023-06-09 00:00:00'),
(35,3,1,'dal vreme radi','2023-06-09 14:20:26'),
(36,3,1,'caoo svima','2023-06-10 12:17:17'),
(37,3,2,'caoo svima','2023-06-10 12:17:17'),
(38,3,3,'caoo svima','2023-06-10 12:17:17'),
(39,3,1,'hej','2023-06-10 12:17:30'),
(40,1,1,'cao sVIMA','2023-06-12 14:51:40'),
(41,1,3,'cao sVIMA','2023-06-12 14:51:40'),
(42,1,2,'cao sVIMA','2023-06-12 14:51:40'),
(43,1,3,'ejj jelo','2023-06-12 14:51:57'),
(44,1,1,'kljljk','2023-06-13 00:10:23'),
(45,1,2,'kljljk','2023-06-13 00:10:23'),
(46,1,1,'jkjjk','2023-06-13 00:10:37'),
(47,1,2,'jkjjk','2023-06-13 00:10:37'),
(48,1,1,'jkjjkjk','2023-06-13 00:14:02'),
(49,1,2,'jkjjkjk','2023-06-13 00:14:02'),
(50,1,1,'gegre','2023-06-13 00:14:11'),
(51,1,2,'gegre','2023-06-13 00:14:11'),
(52,1,1,'kllklkkl','2023-06-13 00:14:14'),
(53,1,2,'kllklkkl','2023-06-13 00:14:14'),
(54,1,1,'lkklkl','2023-06-13 00:14:16'),
(55,1,2,'lkklkl','2023-06-13 00:14:16'),
(56,1,1,'prva','2023-06-13 00:15:04'),
(57,1,2,'prva','2023-06-13 00:15:04'),
(58,1,1,'druga','2023-06-13 00:15:06'),
(59,1,2,'druga','2023-06-13 00:15:06'),
(60,1,1,'treca','2023-06-13 00:15:09'),
(61,1,2,'treca','2023-06-13 00:15:09'),
(62,1,1,'cetvrta','2023-06-13 00:15:13'),
(63,1,2,'cetvrta','2023-06-13 00:15:13'),
(64,1,1,'prva','2023-06-13 00:17:24'),
(65,1,2,'prva','2023-06-13 00:17:24'),
(66,1,1,'druga','2023-06-13 00:17:27'),
(67,1,2,'druga','2023-06-13 00:17:27'),
(68,1,1,'treca','2023-06-13 00:17:29'),
(69,1,2,'treca','2023-06-13 00:17:29'),
(70,1,1,'ce','2023-06-13 00:17:32'),
(71,1,2,'ce','2023-06-13 00:17:32'),
(72,1,1,'feef','2023-06-13 00:17:35'),
(73,1,2,'feef','2023-06-13 00:17:35'),
(74,2,1,'prva','2023-06-13 00:23:45'),
(75,2,2,'prva','2023-06-13 00:23:45'),
(76,2,1,'druga','2023-06-13 00:23:47'),
(77,2,2,'druga','2023-06-13 00:23:47'),
(78,2,1,'treca','2023-06-13 00:23:50'),
(79,2,2,'treca','2023-06-13 00:23:50'),
(80,2,1,'cet','2023-06-13 00:23:52'),
(81,2,2,'cet','2023-06-13 00:23:52'),
(82,2,1,'pet','2023-06-13 00:24:03'),
(83,2,2,'pet','2023-06-13 00:24:03'),
(84,2,1,'prva','2023-06-13 00:24:53'),
(85,2,2,'prva','2023-06-13 00:24:53'),
(86,2,1,'druga','2023-06-13 00:25:07'),
(87,2,2,'druga','2023-06-13 00:25:07'),
(88,2,1,'trec','2023-06-13 00:25:10'),
(89,2,2,'trec','2023-06-13 00:25:10'),
(90,2,1,'cet','2023-06-13 00:25:12'),
(91,2,2,'cet','2023-06-13 00:25:12'),
(92,1,1,'prvaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','2023-06-13 00:50:33'),
(93,1,2,'prvaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','2023-06-13 00:50:33'),
(94,1,2,'druga','2023-06-13 00:50:42'),
(95,1,1,'hehe','2023-06-13 00:50:45'),
(96,1,2,'hehe','2023-06-13 00:50:45'),
(97,1,1,'i sta sad','2023-06-13 00:50:50'),
(98,1,2,'i sta sad','2023-06-13 00:50:50'),
(99,1,1,'a sad','2023-06-13 00:50:55'),
(100,1,2,'a sad','2023-06-13 00:50:55'),
(101,1,2,'tjttt i sra dtugo da vam kazem ovo je topic','2023-06-13 00:51:05'),
(102,2,1,'caoo svima','2023-06-19 11:45:59'),
(103,2,2,'caoo svima','2023-06-19 11:45:59'),
(104,2,3,'caoo svima','2023-06-19 11:45:59'),
(105,2,1,'cao ana','2023-06-19 11:46:13'),
(106,3,1,'kjjjk','2023-06-19 11:46:26'),
(107,3,2,'kjjjk','2023-06-19 11:46:26'),
(108,3,3,'kjjjk','2023-06-19 11:46:26'),
(109,3,1,'jkkjk','2023-06-19 11:46:29'),
(110,3,2,'jkkjk','2023-06-19 11:46:29'),
(111,3,3,'jkkjk','2023-06-19 11:46:29');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
