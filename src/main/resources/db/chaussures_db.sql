-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: chaussures_db
-- ------------------------------------------------------
-- Server version	10.4.13-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `chaussures_db`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `chaussures_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `chaussures_db`;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK3k4cplvh82srueuttfkwnylq0` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user`
--

INSERT INTO `app_user` VALUES (1,'$2a$10$.w7exWeVUvetVe03SkQSaOG.SNHOvFCGBManyROZjtq29sKZhC7qG','admin'),(2,'$2a$10$vXeS.LFbdg.zSvKq1kjf8O4tCGWxWzXagFHz7LCF5/DQHhsmbp3c6','imene');

--
-- Table structure for table `app_user_role`
--

DROP TABLE IF EXISTS `app_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  KEY `FKfnlxi1bmv5ao8u3nf30ymq7xa` (`user_id`),
  CONSTRAINT `FKfnlxi1bmv5ao8u3nf30ymq7xa` FOREIGN KEY (`user_id`) REFERENCES `app_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user_role`
--

INSERT INTO `app_user_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');

--
-- Table structure for table `chaussure`
--

DROP TABLE IF EXISTS `chaussure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chaussure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prix` double NOT NULL,
  `taille` int(11) NOT NULL,
  `id_model` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr4ole8yaib7ejosojxhfe1qts` (`id_model`),
  CONSTRAINT `FKr4ole8yaib7ejosojxhfe1qts` FOREIGN KEY (`id_model`) REFERENCES `model_de_chaussure` (`id_model`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chaussure`
--

INSERT INTO `chaussure` VALUES (1,'Air Max',320,42,1),(2,'Stan Smith',250,41,2),(3,'Timberland',480,44,4);

--
-- Table structure for table `model_de_chaussure`
--

DROP TABLE IF EXISTS `model_de_chaussure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `model_de_chaussure` (
  `id_model` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom_model` varchar(255) NOT NULL,
  PRIMARY KEY (`id_model`),
  UNIQUE KEY `UKtgrdob10n0uk331diitllj2wq` (`nom_model`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_de_chaussure`
--

INSERT INTO `model_de_chaussure` VALUES (2,'Baskets'),(4,'Bottes'),(5,'Mocassins'),(3,'Sandales'),(1,'Sneakers'),(6,'Talons');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-21 17:09:31
