-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: team
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `legal-name` varchar(70) DEFAULT NULL COMMENT 'legal name of the organization',
  `address` varchar(50) DEFAULT NULL COMMENT 'legal address',
  `telephone` varchar(20) DEFAULT NULL COMMENT 'contact number',
  `email` varchar(30) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `fk_customer_user1_idx` (`user_id`),
  CONSTRAINT `fk_customer_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='project customer';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Британская Империя','Вестминстер, Лондон SW1A 1AA, Великобритания','+44 303 123 7300','bempire@gmail.com',3),(2,'Российская Империя','Москва, Россия, 103073','+7 495 697-03-49','rempire@mail.ru',2),(3,'Собственные ресурсы',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `developer`
--

DROP TABLE IF EXISTS `developer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qualification` enum('SENIOR','MIDDLE','JUNIOR') DEFAULT 'JUNIOR' COMMENT 'The level of qualification and subject area.',
  `task_id` int(11) NOT NULL DEFAULT '1',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`task_id`,`user_id`),
  KEY `fk_developer_task1_idx` (`task_id`),
  KEY `fk_developer_user1_idx` (`user_id`),
  CONSTRAINT `fk_developer_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developer`
--

LOCK TABLES `developer` WRITE;
/*!40000 ALTER TABLE `developer` DISABLE KEYS */;
INSERT INTO `developer` VALUES (1,'JUNIOR',1,5),(2,'JUNIOR',2,6),(3,'JUNIOR',3,7),(4,'JUNIOR',3,8),(5,'JUNIOR',4,9),(6,'JUNIOR',4,10),(7,'MIDDLE',2,11),(8,'MIDDLE',1,12),(9,'MIDDLE',4,13),(10,'SENIOR',4,14);
/*!40000 ALTER TABLE `developer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `fk_manager_user1_idx` (`user_id`),
  CONSTRAINT `fk_manager_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='person in charge of project implementation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'bonaparte@team.org',1),(2,'gro@team.org',15);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL COMMENT 'deadline for completing this project',
  `cost` decimal(8,2) unsigned DEFAULT NULL COMMENT 'total cost of this project (BYN)',
  `customer_id` int(11) NOT NULL DEFAULT '3',
  `cipher` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`,`customer_id`),
  KEY `fk_project_customer1_idx` (`customer_id`),
  CONSTRAINT `fk_project_customer1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='The cumulative table of all projects executed by the contractor.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,NULL,NULL,3,'SPARE'),(2,'2018-10-20',55000.00,1,'BRIT1'),(3,'2019-01-01',150000.00,2,'RUSS1'),(6,'2018-11-21',1000.00,3,'PIU21'),(7,'2018-10-01',1000.00,3,'PIU34'),(8,'1990-01-01',10000.00,3,'PIU35'),(9,'2018-10-01',10000.00,3,'PIU36');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_manager`
--

DROP TABLE IF EXISTS `project_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_manager` (
  `project_id` int(11) NOT NULL,
  `manager_id` int(11) NOT NULL,
  PRIMARY KEY (`project_id`,`manager_id`),
  KEY `fk_project_has_manager_manager1_idx` (`manager_id`),
  KEY `fk_project_has_manager_project1_idx` (`project_id`),
  CONSTRAINT `fk_project_has_manager_manager1` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_has_manager_project1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table of responsibilities';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_manager`
--

LOCK TABLES `project_manager` WRITE;
/*!40000 ALTER TABLE `project_manager` DISABLE KEYS */;
INSERT INTO `project_manager` VALUES (2,1),(3,2);
/*!40000 ALTER TABLE `project_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` tinyint(3) unsigned DEFAULT NULL COMMENT 'The number of executors.',
  `info` varchar(100) DEFAULT NULL COMMENT 'Short requirements in domain specialists.\nFor example: frontend junior\n',
  `project_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`,`project_id`),
  KEY `fk_task_project1_idx` (`project_id`),
  CONSTRAINT `fk_task_project1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Tasks require a certain number of developers of the relevant qualifications specified in the info.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,NULL,'отдых',1),(2,2,'ПО наведения артиллерии',2),(3,2,'Интерфейс морских карт',2),(4,2,'Умный контроль системы охлаждения',3),(5,2,'ИИ маршрутизатора',3);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) NOT NULL,
  `password` varchar(32) NOT NULL,
  `role` enum('MANAGER','CUSTOMER','DEVELOPER') NOT NULL DEFAULT 'DEVELOPER',
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','MANAGER','Наполеон','Бонапарт'),(2,'test','91ec1f9324753048c0096d036a694f86','CUSTOMER','Александр','Суворов'),(3,'gornel','b4ab4d1cb1edaaad10efad887a8511a8','CUSTOMER','Горацио','Нельсон'),(5,'andre','2f70d9672845af45f7b101a02d2e7102','DEVELOPER','Андре','Массена'),(6,'luideze','4d92ad8478296939b8d1bfe0fb8878d7','DEVELOPER','Луи','Дезе'),(7,'ojeroPie','575f97251e44f963fba8e3f435caae2f','DEVELOPER','Пьер','Ожеро'),(8,'davunikola','82bade68bf701b9eea5e957d883196ec','DEVELOPER','Никола','Даву'),(9,'jeanKleb','8fb8b83280075af634947ecdd7e26483','DEVELOPER','Жан','Клебер'),(10,'alexbert1','77db497b6cd1b839c0a18015297d751d','DEVELOPER','Александр','Бертье'),(11,'kairo18','b555bb7d9c1b1b53100b969c27fe249f','DEVELOPER','Юзеф','Сулковский'),(12,'josephLA','edf0be2de43ea8bd144cbd46242d52d7','DEVELOPER','Жозеф','Лагранж'),(13,'duma33','9df16f0c5bfab5ef375c4b177149822a','DEVELOPER','Гийом','Дюма'),(14,'murate','3d9b8e1f4be2ca3ce1b75d04270e0cef','DEVELOPER','Иоахим','Мюрат'),(15,'groa','0ba1ec3efff81420d283f6d4e2395ca2','MANAGER','Антуан','Гро');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-01  2:34:37
