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
-- Table structure for table `magic_items`
--

DROP TABLE IF EXISTS `magic_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `magic_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `indexname` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `descr` text NOT NULL,
  `rarity` enum('Common','Uncommon','Rare','Very Rare','Legendary','Varies','Artifact') DEFAULT NULL,
  `url` tinytext NOT NULL,
  `equipment_category_fk` int DEFAULT NULL,
  `source_name_fk` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_source_name` (`source_name_fk`),
  KEY `fk_equipment_category` (`equipment_category_fk`),
  CONSTRAINT `fk_equipment_category` FOREIGN KEY (`equipment_category_fk`) REFERENCES `equipment_category` (`id`),
  CONSTRAINT `fk_source_name` FOREIGN KEY (`source_name_fk`) REFERENCES `source_book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magic_items`
--

LOCK TABLES `magic_items` WRITE;
/*!40000 ALTER TABLE `magic_items` DISABLE KEYS */;
INSERT INTO `magic_items` VALUES (1,'armor','Armor, +1, +2, or +3','Armor (light, medium, or heavy), rare (+1), very rare (+2), or legendary (+3)\nYou have a bonus to AC while wearing this armor. The bonus is determined by its rarity.','Varies','/api/magic-items/armor',1,2),(2,'armor-of-invulnerability','Armor of Invulnerability','Armor (plate), legendary (requires attunement)\nYou have resistance to nonmagical damage while you wear this armor. Additionally, you can use an action to make yourself immune to nonmagical damage for 10 minutes or until you are no longer wearing the armor. Once this special action is used, it can\'t be used again until the next dawn.','Legendary','/api/magic-items/armor-of-invulnerability',1,2),(3,'bag-of-holding','Bag of Holding','Wondrous item, uncommon\nThis bag has an interior space considerably larger than its outside dimensions, roughly 2 feet in diameter at the mouth and 4 feet deep. The bag can hold up to 500 pounds, not exceeding a volume of 64 cubic feet. The bag weighs 15 pounds, regardless of its contents. Retrieving an item from the bag requires an action. If the bag is overloaded, pierced, or torn, it ruptures and is destroyed, and its contents are scattered in the Astral Plane. If the bag is turned inside out, its contents spill forth, unharmed, but the bag must be put right before it can be used again. Breathing creatures inside the bag can survive up to a number of minutes equal to 10 divided by the number of creatures (minimum 1 minute), after which time they begin to suffocate. Placing a bag of holding inside an extradimensional space created by a Handy Haversack, Portable Hole, or similar item instantly destroys both items and opens a gate to the Astral Plane. The gate originates where the one item was placed inside the other. Any creature within 10 feet of the gate is sucked through it to a random location on the Astral Plane. The gate then closes. The gate is one-way only and can\'t be reopened.','Uncommon','/api/magic-items/bag-of-holding',39,2),(4,'adamantine-armor','Adamantine Armor','Armor (medium or heavy, but not hide), uncommon\nThis suit of armor is reinforced with adamantine, one of the hardest substances in existence. While you\'re wearing it, any critical hit against you becomes a normal hit.','Uncommon','/api/magic-items/adamantine-armor',1,2),(5,'boots-of-speed','Boots of Speed','Wondrous item, rare (requires attunement)\nWhile you wear these boots, you can use a bonus action and click the boots\' heels together. If you do, the boots double your walking speed, and any creature that makes an opportunity attack against you has disadvantage on the attack roll. If you click your heels together again, you end the effect. When the boots\' property has been used for a total of 10 minutes, the magic ceases to function until you finish a long rest.','Rare','/api/magic-items/boots-of-speed',39,2),(6,'dragon-slayer','Dragon Slayer','Weapon (any sword), rare\nYou gain a +1 bonus to attack and damage rolls made with this magic weapon.\nWhen you hit a dragon with this weapon, the dragon takes an extra 3d6 damage of the weapon\'s type. For the purpose of this weapon, dragon refers to any creature with the dragon type, including dragon turtles and wyverns.','Rare','/api/magic-items/dragon-slayer',15,2),(7,'elven-chain','Elven Chain','Armor (chain shirt), rare\nYou gain a +1 bonus to AC while you wear this armor. You are considered proficient with this armor even if you lack proficiency with medium armor.','Rare','/api/magic-items/elven-chain',1,2);
/*!40000 ALTER TABLE `magic_items` ENABLE KEYS */;
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
