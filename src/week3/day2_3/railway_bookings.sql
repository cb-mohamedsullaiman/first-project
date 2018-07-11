-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: localhost    Database: railway_bookings
-- ------------------------------------------------------
-- Server version	5.6.22

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
-- Current Database: `railway_bookings`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `railway_bookings` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `railway_bookings`;

--
-- Table structure for table `Bookings`
--

DROP TABLE IF EXISTS `Bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bookings` (
  `BookingRefNo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `RouteId` int(10) unsigned NOT NULL,
  `TrainNo` int(10) unsigned NOT NULL,
  `CoachCode` int(10) unsigned NOT NULL,
  `DateOfJourney` date NOT NULL,
  `DateOfBooking` date NOT NULL,
  `NoOfTickets` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`BookingRefNo`),
  KEY `RouteId` (`RouteId`),
  KEY `TrainNo` (`TrainNo`),
  KEY `CoachCode` (`CoachCode`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`RouteId`) REFERENCES `TrainRouteMaps` (`RouteId`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`TrainNo`) REFERENCES `TrainRouteMaps` (`TrainNo`),
  CONSTRAINT `bookings_ibfk_3` FOREIGN KEY (`CoachCode`) REFERENCES `Coaches` (`CoachCode`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bookings`
--

LOCK TABLES `Bookings` WRITE;
/*!40000 ALTER TABLE `Bookings` DISABLE KEYS */;
INSERT INTO `Bookings` VALUES (9,6,1,100021,'2018-02-20','2018-01-04',1),(10,7,1,100342,'2018-05-23','2018-04-23',3),(11,8,2,300223,'2018-07-24','2018-07-03',2),(12,9,3,100342,'2018-04-29','2018-04-23',5),(13,6,1,100321,'2018-07-27','2018-07-12',3),(14,7,1,300223,'2018-09-21','2018-09-11',1),(15,8,2,100321,'2005-04-06','2005-03-21',5),(16,9,3,300223,'2005-05-30','2005-02-21',2),(17,7,1,100021,'2007-02-25','2006-12-28',3),(18,9,3,300223,'2007-01-05','2006-12-25',5),(19,7,16198,200012,'2005-05-23','2005-02-28',4),(20,7,16198,100342,'2006-03-12','2005-11-14',1),(21,25,16198,300223,'2011-04-23','2011-02-05',6),(22,14,14198,200012,'2007-12-25','2007-10-14',2),(23,14,14198,300223,'2008-02-20','2008-01-04',2),(24,16,14198,100021,'2007-10-23','2007-04-23',7),(25,27,14198,200012,'2005-04-01','2005-01-04',90),(26,25,14198,300223,'2006-08-14','2006-05-29',90),(27,16,14198,100342,'2005-02-04','2005-01-02',90),(28,14,14198,200012,'2012-05-24','2012-03-12',90),(32,7,14198,300223,'2011-11-14','2011-10-02',90),(34,8,14198,100021,'2001-11-24','2001-10-02',90);
/*!40000 ALTER TABLE `Bookings` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger bookings_check before insert on Bookings for each row begin if(new.DateOfBooking>new.DateOfJourney) then signal sqlstate '02000' set message_text = "Warning: Journey date cannot be before booking date"; end if; if(new.NoOfTickets> (select NoOfSeats from TrainCoaches where TrainCoaches.CoachCode = new.CoachCode and TrainCoaches.TrainNo = new.TrainNo)) then signal SQLSTATE '02000' set message_text = "Warning: No of tickets for a coach cannot be greater than no of seats" ; end if; end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Coaches`
--

DROP TABLE IF EXISTS `Coaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Coaches` (
  `CoachCode` int(10) unsigned NOT NULL,
  `CostPerKm` float unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`CoachCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coaches`
--

LOCK TABLES `Coaches` WRITE;
/*!40000 ALTER TABLE `Coaches` DISABLE KEYS */;
INSERT INTO `Coaches` VALUES (100021,5.2),(100321,3.65),(100342,2.9),(200012,4.5),(300223,4.75);
/*!40000 ALTER TABLE `Coaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Routes`
--

DROP TABLE IF EXISTS `Routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Routes` (
  `RouteId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `OriginStationId` int(10) unsigned NOT NULL,
  `DestinationStationId` int(10) unsigned NOT NULL,
  `DistanceInKms` float unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`RouteId`),
  KEY `OriginStationId` (`OriginStationId`),
  KEY `DestinationStationId` (`DestinationStationId`),
  CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`OriginStationId`) REFERENCES `Stations` (`StationId`),
  CONSTRAINT `routes_ibfk_2` FOREIGN KEY (`DestinationStationId`) REFERENCES `Stations` (`StationId`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Routes`
--

LOCK TABLES `Routes` WRITE;
/*!40000 ALTER TABLE `Routes` DISABLE KEYS */;
INSERT INTO `Routes` VALUES (6,1,2,20.5),(7,1,3,35),(8,1,4,47),(9,1,5,60),(10,2,1,20.5),(11,2,3,15),(12,2,4,30),(13,2,5,50),(14,3,1,35),(15,3,2,15),(16,3,4,13),(17,3,5,30),(18,4,1,47),(19,4,2,30),(20,4,3,13),(21,4,5,20),(22,5,1,60),(23,5,2,50),(24,5,3,30),(25,5,4,20),(26,1,6,102),(27,6,2,93),(28,6,3,84),(29,3,6,52),(30,5,6,19),(31,2,7,201),(32,4,7,251),(33,6,7,311),(34,1,8,300),(35,5,8,342),(36,7,8,361),(37,2,9,200),(38,9,1,230),(39,3,9,219),(40,9,7,300);
/*!40000 ALTER TABLE `Routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stations`
--

DROP TABLE IF EXISTS `Stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stations` (
  `StationId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `StationCode` varchar(30) NOT NULL,
  PRIMARY KEY (`StationId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stations`
--

LOCK TABLES `Stations` WRITE;
/*!40000 ALTER TABLE `Stations` DISABLE KEYS */;
INSERT INTO `Stations` VALUES (1,'CHN-EGM-201'),(2,'MAD-092'),(3,'THRCNDR-283'),(4,'THRNLVI-241'),(5,'KNYKMRI-301'),(6,'Bangalore'),(7,'Chennai'),(8,'Calcutta'),(9,'Mysore');
/*!40000 ALTER TABLE `Stations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrainCoaches`
--

DROP TABLE IF EXISTS `TrainCoaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrainCoaches` (
  `TrainNo` int(10) unsigned NOT NULL,
  `CoachCode` int(10) unsigned NOT NULL,
  `NoOfSeats` int(11) NOT NULL DEFAULT '0',
  KEY `TrainNo` (`TrainNo`),
  KEY `CoachCode` (`CoachCode`),
  CONSTRAINT `traincoaches_ibfk_1` FOREIGN KEY (`TrainNo`) REFERENCES `Trains` (`TrainNo`),
  CONSTRAINT `traincoaches_ibfk_2` FOREIGN KEY (`CoachCode`) REFERENCES `Coaches` (`CoachCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrainCoaches`
--

LOCK TABLES `TrainCoaches` WRITE;
/*!40000 ALTER TABLE `TrainCoaches` DISABLE KEYS */;
INSERT INTO `TrainCoaches` VALUES (1,100021,90),(1,200012,94),(1,300223,45),(2,100321,90),(2,200012,45),(2,100321,60),(3,100321,75),(3,300223,75),(4,100342,60),(4,100321,75);
/*!40000 ALTER TABLE `TrainCoaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrainRouteMaps`
--

DROP TABLE IF EXISTS `TrainRouteMaps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrainRouteMaps` (
  `RouteId` int(10) unsigned NOT NULL,
  `TrainNo` int(10) unsigned NOT NULL,
  `ArrivalTime` time NOT NULL,
  `DepartureTime` time NOT NULL,
  `DurationInMins` int(11) DEFAULT NULL,
  PRIMARY KEY (`RouteId`,`TrainNo`),
  KEY `TrainNo` (`TrainNo`),
  CONSTRAINT `trainroutemaps_ibfk_1` FOREIGN KEY (`RouteId`) REFERENCES `Routes` (`RouteId`),
  CONSTRAINT `trainroutemaps_ibfk_2` FOREIGN KEY (`TrainNo`) REFERENCES `Trains` (`TrainNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrainRouteMaps`
--

LOCK TABLES `TrainRouteMaps` WRITE;
/*!40000 ALTER TABLE `TrainRouteMaps` DISABLE KEYS */;
INSERT INTO `TrainRouteMaps` VALUES (6,1,'08:02:04','04:05:02',237),(7,1,'13:04:06','02:01:09',663),(7,16198,'12:05:10','03:00:10',545),(8,2,'11:28:24','04:05:02',443),(9,3,'13:24:04','07:15:32',369),(14,14198,'15:24:20','12:20:10',184),(16,14198,'05:30:20','02:10:30',200),(25,16198,'13:12:30','07:10:11',362),(26,4,'14:05:30','10:23:34',222),(26,16198,'04:03:10','01:02:20',181),(27,14198,'23:12:20','17:20:10',352),(28,3,'22:10:34','10:23:45',707),(29,4,'10:20:34','02:03:20',497),(30,1,'14:23:45','03:05:23',678),(37,4,'22:11:20','11:20:20',651),(37,14198,'12:19:20','04:20:10',479),(38,16198,'20:20:10','10:20:19',600),(39,14198,'13:10:00','06:30:20',400),(40,4,'22:10:20','19:10:20',180),(40,14198,'12:20:23','07:13:40',307),(40,16198,'12:02:24','05:24:20',398);
/*!40000 ALTER TABLE `TrainRouteMaps` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger duration_update before insert on TrainRouteMaps for each row set New.DurationInMins = time_to_sec(timediff(NEW.ArrivalTime,NEW.DepartureTime))/60 */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Trains`
--

DROP TABLE IF EXISTS `Trains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trains` (
  `TrainNo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TrainName` varchar(30) NOT NULL,
  PRIMARY KEY (`TrainNo`)
) ENGINE=InnoDB AUTO_INCREMENT=16199 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trains`
--

LOCK TABLES `Trains` WRITE;
/*!40000 ALTER TABLE `Trains` DISABLE KEYS */;
INSERT INTO `Trains` VALUES (1,'Pothigai'),(2,'Pallavan'),(3,'Vaigai'),(4,'Chendur'),(5,'Bhimarav'),(6,'Bhuvaneshvar Express'),(14198,'Punjabi Express'),(16198,'Pune Express');
/*!40000 ALTER TABLE `Trains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `UserId` varchar(15) NOT NULL,
  `LoginId` varchar(15) DEFAULT NULL,
  `LPassword` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES ('salai','Salai84','salaikumar'),('sharanya.rk','sharanya','sharanya_rk'),('sudhan','sudhan81','sudhanak'),('Sullaiman','Sullai97','loveindia');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-10 10:21:42
