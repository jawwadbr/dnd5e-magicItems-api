DROP TABLE IF EXISTS `magic_items`;
DROP TABLE IF EXISTS `source_book`;
DROP TABLE IF EXISTS `equipment_category`;

create table `source_book` (
	`id` int not null primary key auto_increment,
    `index_name` varchar(128) not null,
    `source_name` varchar(128) not null,
	`url` tinytext NOT NULL,
    UNIQUE KEY `source_name` (`source_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table `equipment_category` (
	`id` int not null primary key auto_increment,
	`index_name` varchar(128) NOT NULL,
    `equip_name` tinytext NOT NULL,
    `url` tinytext NOT NULL,
	UNIQUE KEY `unique_index_name` (`index_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table `magic_item` (
	`id` int not null auto_increment,
    `index_name` varchar(128) not null,
    `item_name` varchar(128) not null,
    `top_descr` varchar(128) not null,
    `descr` text not null,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

