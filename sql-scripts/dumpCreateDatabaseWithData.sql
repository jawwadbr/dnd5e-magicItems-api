CREATE DATABASE  IF NOT EXISTS `dnd5e_magic-items_database` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dnd5e_magic-items_database`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: dnd5e_magic-items_database
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
  `index_name` varchar(128) NOT NULL,
  `equip_name` tinytext NOT NULL,
  `url` tinytext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_name` (`index_name`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment_category`
--

LOCK TABLES `equipment_category` WRITE;
/*!40000 ALTER TABLE `equipment_category` DISABLE KEYS */;
INSERT INTO `equipment_category` VALUES (1,'armor','Armor','/api/equipment-categories/armor'),(2,'heavy-armor','Heavy Armor','/api/equipment-categories/heavy-armor'),(3,'light-armor','Light Armor','/api/equipment-categories/light-armor'),(4,'medium-armor','Medium Armor','/api/equipment-categories/medium-armor'),(5,'martial-melee-weapons','Martial Melee Weapons','/api/equipment-categories/martial-melee-weapons'),(6,'martial-ranged-weapons','Martial Ranged Weapons','/api/equipment-categories/martial-ranged-weapons'),(7,'martial-weapons','Martial Weapons','/api/equipment-categories/martial-weapons'),(8,'melee-weapons','Melee Weapons','/api/equipment-categories/melee-weapons'),(9,'ranged-weapons','Ranged Weapons','/api/equipment-categories/ranged-weapons'),(10,'simple-melee-weapons','Simple Melee Weapons','/api/equipment-categories/simple-melee-weapons'),(11,'simple-ranged-weapons','Simple Ranged Weapons','/api/equipment-categories/simple-ranged-weapons'),(12,'simple-weapons','Simple Weapons','/api/equipment-categories/simple-weapons'),(13,'staff','Staff','/api/equipment-categories/staff'),(14,'wand','Wand','/api/equipment-categories/wand'),(15,'weapon','Weapon','/api/equipment-categories/weapon'),(16,'ammunition','Ammunition','/api/equipment-categories/ammunition'),(17,'arcane-focus','Arcane Focus','/api/equipment-categories/arcane-focus'),(18,'druidic-focus','Druidic Focus','/api/equipment-categories/druidic-focus'),(19,'holy-symbols','Holy Symbols','/api/equipment-categories/holy-symbols'),(20,'potion','Potion','/api/equipment-categories/potion'),(21,'other-tools','Other Tools','/api/equipment-categories/other-tools'),(22,'shields','Shields','/api/equipment-categories/shields'),(23,'scroll','Scroll','/api/equipment-categories/scroll'),(24,'rod','Rod','/api/equipment-categories/rod'),(25,'ring','Ring','/api/equipment-categories/ring'),(26,'tools','Tools','/api/equipment-categories/tools'),(27,'standard-gear','Standard Gear','/api/equipment-categories/standard-gear'),(28,'adventuring-gear','Adventuring Gear','/api/equipment-categories/adventuring-gear'),(29,'artisans-tools','Artisan\'s Tools','/api/equipment-categories/artisans-tools'),(30,'equipment-packs','Equipment Packs','/api/equipment-categories/equipment-packs'),(31,'gaming-sets','Gaming Sets','/api/equipment-categories/gaming-sets'),(32,'kits','Kits','/api/equipment-categories/kits'),(33,'land-vehicles','Land Vehicles','/api/equipment-categories/land-vehicles'),(34,'tack-harness-and-drawn-vehicles','Tack, Harness, and Drawn Vehicles','/api/equipment-categories/tack-harness-and-drawn-vehicles'),(35,'musical-instruments','Musical Instruments','/api/equipment-categories/musical-instruments'),(36,'mounts-and-vehicles','Mounts and Vehicles','/api/equipment-categories/mounts-and-vehicles'),(37,'mounts-and-other-animals','Mounts and Other Animals','/api/equipment-categories/mounts-and-other-animals'),(38,'waterborne-vehicles','Waterborne Vehicles','/api/equipment-categories/waterborne-vehicles'),(39,'wondrous-items','Wondrous Items','/api/equipment-categories/wondrous-items');
/*!40000 ALTER TABLE `equipment_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `magic_item`
--

DROP TABLE IF EXISTS `magic_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `magic_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `index_name` varchar(128) NOT NULL,
  `item_name` varchar(128) NOT NULL,
  `top_descr` varchar(128) NOT NULL,
  `descr` text NOT NULL,
  `rarity` enum('Common','Uncommon','Rare','Very Rare','Legendary','Varies','Artifact') DEFAULT NULL,
  `url` tinytext NOT NULL,
  `equipment_category_fk` int DEFAULT NULL,
  `source_name_fk` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_index_name` (`index_name`),
  KEY `fk_source_name` (`source_name_fk`),
  KEY `fk_equipment_category` (`equipment_category_fk`),
  CONSTRAINT `fk_equipment_category` FOREIGN KEY (`equipment_category_fk`) REFERENCES `equipment_category` (`id`),
  CONSTRAINT `fk_source_name` FOREIGN KEY (`source_name_fk`) REFERENCES `source_book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magic_item`
--

LOCK TABLES `magic_item` WRITE;
/*!40000 ALTER TABLE `magic_item` DISABLE KEYS */;
INSERT INTO `magic_item` VALUES (1,'armor','Armor, +1, +2, or +3','Armor (light, medium, or heavy), rare (+1), very rare (+2), or legendary (+3)','You have a bonus to AC while wearing this armor. The bonus is determined by its rarity.','Varies','/api/magic-items/armor',1,1),(2,'armor-of-invulnerability','Armor of Invulnerability','Armor (plate), legendary (requires attunement)','You have resistance to nonmagical damage while you wear this armor. Additionally, you can use an action to make yourself immune to nonmagical damage for 10 minutes or until you are no longer wearing the armor. Once this special action is used, it can\'t be used again until the next dawn.','Legendary','/api/magic-items/armor-of-invulnerability',1,1),(3,'bag-of-holding','Bag of Holding','Wondrous item, uncommon','This bag has an interior space considerably larger than its outside dimensions, roughly 2 feet in diameter at the mouth and 4 feet deep. The bag can hold up to 500 pounds, not exceeding a volume of 64 cubic feet. The bag weighs 15 pounds, regardless of its contents. Retrieving an item from the bag requires an action. If the bag is overloaded, pierced, or torn, it ruptures and is destroyed, and its contents are scattered in the Astral Plane. If the bag is turned inside out, its contents spill forth, unharmed, but the bag must be put right before it can be used again. Breathing creatures inside the bag can survive up to a number of minutes equal to 10 divided by the number of creatures (minimum 1 minute), after which time they begin to suffocate. Placing a bag of holding inside an extradimensional space created by a Handy Haversack, Portable Hole, or similar item instantly destroys both item and opens a gate to the Astral Plane. The gate originates where the one item was placed inside the other. Any creature within 10 feet of the gate is sucked through it to a random location on the Astral Plane. The gate then closes. The gate is one-way only and can\'t be reopened.','Uncommon','/api/magic-items/bag-of-holding',39,1),(4,'adamantine-armor','Adamantine Armor','Armor (medium or heavy, but not hide), uncommon','This suit of armor is reinforced with adamantine, one of the hardest substances in existence. While you\'re wearing it, any critical hit against you becomes a normal hit.','Uncommon','/api/magic-items/adamantine-armor',1,1),(5,'boots-of-speed','Boots of Speed','Wondrous item, rare (requires attunement)','While you wear these boots, you can use a bonus action and click the boots\' heels together. If you do, the boots double your walking speed, and any creature that makes an opportunity attack against you has disadvantage on the attack roll. If you click your heels together again, you end the effect. When the boots\' property has been used for a total of 10 minutes, the magic ceases to function until you finish a long rest.','Rare','/api/magic-items/boots-of-speed',39,1),(6,'dragon-slayer','Dragon Slayer','Weapon (any sword), rare','You gain a +1 bonus to attack and damage rolls made with this magic weapon.\nWhen you hit a dragon with this weapon, the dragon takes an extra 3d6 damage of the weapon\'s type. For the purpose of this weapon, dragon refers to any creature with the dragon type, including dragon turtles and wyverns.','Rare','/api/magic-items/dragon-slayer',15,1),(7,'elven-chain','Elven Chain','Armor (chain shirt), rare','You gain a +1 bonus to AC while you wear this armor. You are considered proficient with this armor even if you lack proficiency with medium armor.','Rare','/api/magic-items/elven-chain',1,1);
/*!40000 ALTER TABLE `magic_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `source_book`
--

DROP TABLE IF EXISTS `source_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `source_book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `index_name` varchar(128) NOT NULL,
  `source_name` varchar(128) NOT NULL,
  `url` tinytext NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `source_name` (`source_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `source_book`
--

LOCK TABLES `source_book` WRITE;
/*!40000 ALTER TABLE `source_book` DISABLE KEYS */;
INSERT INTO `source_book` VALUES (1,'dungeon-masters-guide','Dungeon Master’s Guide','/api/source-books/dungeon-masters-guide'),(2,'players-handbook','Players Handbook','/api/source-books/players-handbook');
/*!40000 ALTER TABLE `source_book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28  3:03:03