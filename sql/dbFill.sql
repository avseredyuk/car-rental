-- MySQL dump 10.13  Distrib 5.6.33, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: lab3
-- ------------------------------------------------------
-- Server version	5.6.33-0ubuntu0.14.04.1

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
-- Current Database: `lab3`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lab3` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lab3`;

--
-- Table structure for table `automobiles`
--

DROP TABLE IF EXISTS `automobiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `automobiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manufacturer` text NOT NULL,
  `model` text NOT NULL,
  `yearOfProduction` int(11) NOT NULL,
  `place_id` int(11) NOT NULL,
  `category` text NOT NULL,
  `fuel_type` text NOT NULL,
  `transmission` text NOT NULL,
  `passenger_capacity` int(11) NOT NULL,
  `cargo_capacity` int(11) NOT NULL,
  `doors_count` int(11) NOT NULL,
  `price_per_day` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `automobiles_fk0` (`place_id`),
  CONSTRAINT `automobiles_fk0` FOREIGN KEY (`place_id`) REFERENCES `delivery_places` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `automobiles`
--

LOCK TABLES `automobiles` WRITE;
/*!40000 ALTER TABLE `automobiles` DISABLE KEYS */;
INSERT INTO `automobiles` VALUES (1,'Toyota','Yaris',2013,3,'HATCHBACK','GAS','AUTOMATIC',3,3,5,600),(2,'Nissan','Leaf',2012,3,'HATCHBACK','GAS','AUTOMATIC',3,3,5,550),(3,'Mitsubishi','Pajero',2014,2,'SUV','DIESEL','MANUAL',5,5,5,800),(4,'Toyota','Land Cruiser',2009,3,'SUV','DIESEL','MANUAL',5,6,5,850),(5,'Toyota','Sequoia',2008,2,'PICKUP','DIESEL','MANUAL',6,7,4,1100),(6,'Daewoo','Lanos',2007,3,'SEDAN','GAS','MANUAL',4,3,4,400),(7,'Bentley','Continental',2015,3,'SUPERCAR','GAS','MANUAL',1,1,2,2750),(8,'Nissan','Leaf Electro',2015,2,'SEDAN','ELECTRO','AUTOMATIC',3,3,5,700),(9,'Tesla','Model S',2015,2,'SUPERCAR','ELECTRO','AUTOMATIC',3,3,4,1200),(10,'Volkswagen','Passat',2011,2,'HATCHBACK','DIESEL','AUTOMATIC',4,4,5,750),(11,'Volkswagen','Jetta',2011,2,'SEDAN','GAS','MANUAL',4,4,4,650),(12,'Volkswagen','Touareg',2013,10,'SUV','DIESEL','MANUAL',5,5,5,1050);
/*!40000 ALTER TABLE `automobiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `damages`
--

DROP TABLE IF EXISTS `damages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `damages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `damageSum` int(11) NOT NULL,
  `description` text NOT NULL,
  `paid` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `damages`
--

LOCK TABLES `damages` WRITE;
/*!40000 ALTER TABLE `damages` DISABLE KEYS */;
/*!40000 ALTER TABLE `damages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_places`
--

DROP TABLE IF EXISTS `delivery_places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_places` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `address` text NOT NULL,
  `place_type` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_places`
--

LOCK TABLES `delivery_places` WRITE;
/*!40000 ALTER TABLE `delivery_places` DISABLE KEYS */;
INSERT INTO `delivery_places` VALUES (1,'Метро \"Берестейская\"','г. Киев, ст.м. Берестейская','PUBLIC_PLACE'),(2,'Центральный офис','г. Киев, ул. Такая-то, 17','OFFICE'),(3,'Отделение №2','г. Киев, ул. Совсем другая, 19','OFFICE'),(4,'Аэропорт \"Борисполь\", терминал D','г. Борисполь-7','PUBLIC_PLACE'),(5,'Железнодорожная станция \"Киев-Днепровский\"','г. Киев, просп. Освободителей, 15','PUBLIC_PLACE'),(6,'Южный вокзал','г. Киев, ул. Ползунова','PUBLIC_PLACE'),(7,'Киев-Пассажирский Железнодорожный вокзал','г. Киев, пл. Привокзальная, 1','PUBLIC_PLACE'),(8,'ТРЦ Gulliver','г. Киев, пл. Спортивная, 1а','PUBLIC_PLACE'),(9,'Метро \"Нивки\"','г. Киев, ст.м. Нивки','PUBLIC_PLACE'),(10,'Отделение №3','г. Киев, ул. Соколова, 23','OFFICE');
/*!40000 ALTER TABLE `delivery_places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place_from_id` int(11) NOT NULL,
  `place_to_id` int(11) NOT NULL,
  `automobileId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `damageId` int(11) DEFAULT NULL,
  `dateFrom` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dateTo` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `dateCreated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` text NOT NULL,
  `sum` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_fk0` (`automobileId`),
  KEY `orders_fk1` (`userId`),
  KEY `orders_fk2` (`damageId`),
  KEY `orders_fk3` (`place_from_id`),
  KEY `orders_fk4` (`place_to_id`),
  CONSTRAINT `orders_fk0` FOREIGN KEY (`automobileId`) REFERENCES `automobiles` (`id`),
  CONSTRAINT `orders_fk1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `orders_fk2` FOREIGN KEY (`damageId`) REFERENCES `damages` (`id`) ON DELETE CASCADE,
  CONSTRAINT `orders_fk3` FOREIGN KEY (`place_from_id`) REFERENCES `delivery_places` (`id`),
  CONSTRAINT `orders_fk4` FOREIGN KEY (`place_to_id`) REFERENCES `delivery_places` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,3,4,9,2,NULL,'2017-02-02 08:22:00','2017-02-03 08:22:00','2017-02-02 08:23:25','CREATED',1200);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `registered_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` text NOT NULL,
  `name` text NOT NULL,
  `surname` text NOT NULL,
  `role` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_uc1` (`login`),
  UNIQUE KEY `users_uc2` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','2017-02-02 07:40:39','8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918','admin','admin','ADMINISTRATOR'),(2,'user','user@mail.com','2017-02-02 08:23:23','04F8996DA763B7A969B1028EE3007569EAF3A635486DDAB211D512C85B9DF8FB','Сергей','Иванов','CLIENT');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-02 10:27:06
