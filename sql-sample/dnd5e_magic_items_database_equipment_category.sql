CREATE DATABASE  IF NOT EXISTS `dnd5e_magic_items_database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dnd5e_magic_items_database`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: dnd5e_magic_items_database
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `equipment_category`
--

DROP TABLE IF EXISTS `equipment_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `indexname` tinytext NOT NULL,
  `name` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment_category`
--

LOCK TABLES `equipment_category` WRITE;
/*!40000 ALTER TABLE `equipment_category` DISABLE KEYS */;
INSERT INTO `equipment_category` VALUES (1,'armor','Armor'),(2,'heavy-armor','Heavy Armor'),(3,'light-armor','Light Armor'),(4,'medium-armor','Medium Armor'),(5,'martial-melee-weapons','Martial Melee Weapons'),(6,'martial-ranged-weapons','Martial Ranged Weapons'),(7,'martial-weapons','Martial Weapons'),(8,'melee-weapons','Melee Weapons'),(9,'ranged-weapons','Ranged Weapons'),(10,'simple-melee-weapons','Simple Melee Weapons'),(11,'simple-ranged-weapons','Simple Ranged Weapons'),(12,'simple-weapons','Simple Weapons'),(13,'staff','Staff'),(14,'wand','Wand'),(15,'weapon','Weapon'),(16,'ammunition','Ammunition'),(17,'arcane-focus','Arcane Focus'),(18,'druidic-focus','Druidic Focus'),(19,'holy-symbols','Holy Symbols'),(20,'potion','Potion'),(21,'other-tools','Other Tools'),(22,'shields','Shields'),(23,'scroll','Scroll'),(24,'rod','Rod'),(25,'ring','Ring'),(26,'tools','Tools'),(27,'standard-gear','Standard Gear'),(28,'adventuring-gear','Adventuring Gear'),(29,'artisans-tools','Artisan\'s Tools'),(30,'equipment-packs','Equipment Packs'),(31,'gaming-sets','Gaming Sets'),(32,'kits','Kits'),(33,'land-vehicles','Land Vehicles'),(34,'tack-harness-and-drawn-vehicles','Tack, Harness, and Drawn Vehicles'),(35,'musical-instruments','Musical Instruments'),(36,'mounts-and-vehicles','Mounts and Vehicles'),(37,'mounts-and-other-animals','Mounts and Other Animals'),(38,'waterborne-vehicles','Waterborne Vehicles'),(39,'wondrous-items','Wondrous Items');
/*!40000 ALTER TABLE `equipment_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-07  4:57:13
