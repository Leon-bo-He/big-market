# ************************************************************
# Sequel Ace SQL dump
# Version 20067
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 8.0.32)
# Database: big_market
# Generation Time: 2025-05-13 15:32:24 +0000
# ************************************************************

USE `big_market`;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table award
# ------------------------------------------------------------

DROP TABLE IF EXISTS `award`;

CREATE TABLE `award` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `award_id` int NOT NULL,
  `award_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `award_config` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `award_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `award` WRITE;
/*!40000 ALTER TABLE `award` DISABLE KEYS */;

INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`)
VALUES
	(1,101,'user_point_random','1,100','award user random points','2025-05-02 18:26:16','2025-05-02 18:26:16'),
	(2,102,'openai_use_count','3','add voucher','2025-05-02 18:26:46','2025-05-02 18:26:46'),
	(3,103,'openai_use_count','5','add voucher','2025-05-02 18:26:46','2025-05-02 18:26:55'),
	(4,104,'openai_use_count','8','add voucher','2025-05-02 18:26:46','2025-05-02 18:26:55'),
	(5,105,'openai_model','gpt-4','unlock gpt-4 model','2025-05-02 18:27:33','2025-05-02 18:27:33'),
	(6,106,'openai_model','dall-e-2','unlock dall-e-2 model','2025-05-02 18:27:33','2025-05-02 18:27:54'),
	(7,107,'openai_model','dall-e-3','unlock dall-e-3 model','2025-05-02 18:27:33','2025-05-02 18:28:08'),
	(8,108,'openai_use_count','100','add voucher','2025-05-02 18:28:29','2025-05-02 18:28:29'),
	(9,109,'openai_model','gpt-4, dall-e-2, dall-e-3','unlock models','2025-05-02 18:29:06','2025-05-02 18:29:06'),
	(10,100,'user_credit_blacklist','1','award blacklist user 1 point','2025-05-06 14:22:47','2025-05-06 14:22:47');

/*!40000 ALTER TABLE `award` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table rule_tree
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rule_tree`;

CREATE TABLE `rule_tree` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `tree_id` varchar(32) NOT NULL,
  `tree_name` varchar(64) NOT NULL,
  `tree_desc` varchar(128) DEFAULT NULL,
  `tree_node_rule_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'rule_tree start node key value',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `rule_tree` WRITE;
/*!40000 ALTER TABLE `rule_tree` DISABLE KEYS */;

INSERT INTO `rule_tree` (`id`, `tree_id`, `tree_name`, `tree_desc`, `tree_node_rule_key`, `create_time`, `update_time`)
VALUES
	(1,'tree_lock','rule_tree','rule_tree','rule_lock','2025-05-13 08:20:48','2025-05-13 08:20:48');

/*!40000 ALTER TABLE `rule_tree` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table rule_tree_node
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rule_tree_node`;

CREATE TABLE `rule_tree_node` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `tree_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rule_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rule_desc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rule_value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `rule_tree_node` WRITE;
/*!40000 ALTER TABLE `rule_tree_node` DISABLE KEYS */;

INSERT INTO `rule_tree_node` (`id`, `tree_id`, `rule_key`, `rule_desc`, `rule_value`, `create_time`, `update_time`)
VALUES
	(1,'tree_lock','rule_lock','unlock certain award as the user completes N times of lottery','1','2025-05-13 08:24:59','2025-05-13 08:24:59'),
	(2,'tree_lock','rule_luck_award','random points - fallback prize','1,100','2025-05-13 08:25:05','2025-05-13 08:25:05'),
	(3,'tree_lock','rule_stock','stock deducation rule',NULL,'2025-05-13 08:25:07','2025-05-13 08:25:07');

/*!40000 ALTER TABLE `rule_tree_node` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table rule_tree_node_line
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rule_tree_node_line`;

CREATE TABLE `rule_tree_node_line` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `tree_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rule_node_from` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rule_node_to` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rule_limit_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '1:=;2:>;3:<;4:>=;5<=;6:enum;',
  `rule_limit_value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `rule_tree_node_line` WRITE;
/*!40000 ALTER TABLE `rule_tree_node_line` DISABLE KEYS */;

INSERT INTO `rule_tree_node_line` (`id`, `tree_id`, `rule_node_from`, `rule_node_to`, `rule_limit_type`, `rule_limit_value`, `create_time`, `update_time`)
VALUES
	(1,'tree_lock','rule_lock','rule_stock','EQUAL','ALLOW','2025-05-13 08:28:07','2025-05-13 08:28:07'),
	(2,'tree_lock','rule_lock','rule_luck_award','EQUAL','TAKE_OVER','2025-05-13 08:28:11','2025-05-13 08:28:11'),
	(3,'tree_lock','rule_stock','rule_luck_award','EQUAL','TAKE_OVER','2025-05-13 08:28:14','2025-05-13 08:28:14');

/*!40000 ALTER TABLE `rule_tree_node_line` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table strategy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `strategy`;

CREATE TABLE `strategy` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` int NOT NULL,
  `strategy_desc` varchar(128) NOT NULL,
  `rule_models` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Sync strategy rule to this table, for convenience use',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `strategy` WRITE;
/*!40000 ALTER TABLE `strategy` DISABLE KEYS */;

INSERT INTO `strategy` (`id`, `strategy_id`, `strategy_desc`, `rule_models`, `create_time`, `update_time`)
VALUES
	(1,100001,'Strategy A','rule_weight,rule_blacklist','2025-05-02 16:19:25','2025-05-05 08:33:54'),
	(2,100002,'Strategy B',NULL,'2025-05-02 16:19:25','2025-05-11 12:59:06'),
	(3,100003,'Strategy C',NULL,'2025-05-02 16:19:25','2025-05-11 12:59:08');

/*!40000 ALTER TABLE `strategy` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table strategy_award
# ------------------------------------------------------------

DROP TABLE IF EXISTS `strategy_award`;

CREATE TABLE `strategy_award` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` int NOT NULL,
  `award_id` int NOT NULL,
  `award_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `award_subtitle` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `award_count` int NOT NULL,
  `award_count_surplus` int NOT NULL,
  `award_rate` decimal(6,4) NOT NULL COMMENT 'Probability of winning prizes',
  `rule_models` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Sync strategy rule to this table, for convenience use',
  `sort` int NOT NULL DEFAULT '0' COMMENT 'award sort number',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `strategy_award` WRITE;
/*!40000 ALTER TABLE `strategy_award` DISABLE KEYS */;

INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `rule_models`, `sort`, `create_time`, `update_time`)
VALUES
	(1,100001,101,'Random Points',NULL,80000,80000,0.3000,'rule_random,rule_luck_award',1,'2025-05-11 13:35:13','2025-05-11 13:35:13'),
	(2,100001,102,'5 Times Voucher',NULL,10000,10000,0.2000,'rule_luck_award',2,'2025-05-06 09:12:43','2025-05-06 09:12:43'),
	(3,100001,103,'10 Times Voucher',NULL,5000,5000,0.2000,'rule_luck_award',3,'2025-05-06 09:12:50','2025-05-06 09:12:50'),
	(4,100001,104,'20 Times Voucher',NULL,4000,4000,0.1000,'rule_luck_award',4,'2025-05-06 09:12:52','2025-05-06 09:12:52'),
	(5,100001,105,'Unlock GPT-4 Model',NULL,600,600,0.1000,'rule_luck_award',5,'2025-05-06 09:12:59','2025-05-06 09:12:59'),
	(6,100001,106,'Unlock dall-e-2 Model',NULL,200,200,0.0500,'rule_luck_award',6,'2025-05-06 09:17:26','2025-05-06 09:17:26'),
	(7,100001,107,'Unlock dall-e-3 Model','Unlock after 3 times play',200,200,0.0400,'rule_luck_award,rule_lock',7,'2025-05-11 13:35:08','2025-05-11 13:35:08'),
	(8,100001,108,'100 Times Voucher','Unlock after 5 times play',199,199,0.0099,'rule_luck_award,rule_lock',8,'2025-05-11 13:35:16','2025-05-11 13:35:16'),
	(9,100001,109,'Unlock All Models','Unlock after 8 times play',1,1,0.0001,'rule_luck_award,rule_lock',9,'2025-05-11 13:35:19','2025-05-11 13:35:19'),
	(10,100002,101,'Random Points',NULL,1,1,0.5000,'rule_random,rule_luck_award',1,'2025-05-05 08:37:00','2025-05-05 08:37:00'),
	(11,100002,102,'5 Times Voucher',NULL,1,1,0.1000,'rule_random,rule_luck_award',2,'2025-05-05 08:37:01','2025-05-05 08:37:01'),
	(12,100002,106,'add...',NULL,1,1,0.0100,'rule_random,rule_luck_award',3,'2025-05-05 08:37:01','2025-05-05 08:37:01'),
	(14,100003,107,'Unlock dall-e-3 Model','Unlock after 3 times play',200,200,0.0400,'rule_luck_award,rule_lock',7,'2025-05-11 13:34:56','2025-05-11 13:34:56'),
	(15,100003,108,'100 Times Voucher','Unlock after 5 times play',199,199,0.0099,'rule_luck_award,rule_lock',8,'2025-05-11 13:34:59','2025-05-11 13:34:59'),
	(16,100003,109,'Unlock All Models','Unlock after 8 times play',1,1,0.0001,'rule_luck_award,rule_lock',9,'2025-05-11 13:35:04','2025-05-11 13:35:04');

/*!40000 ALTER TABLE `strategy_award` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table strategy_rule
# ------------------------------------------------------------

DROP TABLE IF EXISTS `strategy_rule`;

CREATE TABLE `strategy_rule` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` int NOT NULL,
  `award_id` int DEFAULT NULL,
  `rule_type` int NOT NULL DEFAULT '0' COMMENT '1 for strategy rule; 2 for award rule',
  `rule_model` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'rule_random -> random value; rule_lock -> unlock after playing; rule_luck_award -> fallback prize',
  `rule_value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rule_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `strategy_rule` WRITE;
/*!40000 ALTER TABLE `strategy_rule` DISABLE KEYS */;

INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`)
VALUES
	(1,100001,101,2,'rule_random','1,1000','Random point strategy(Deposit random value from rule_value to user account)','2025-05-02 16:46:06','2025-05-05 08:37:43'),
	(2,100001,107,2,'rule_lock','3','unlock after 3 times play','2025-05-02 16:46:06','2025-05-05 08:37:46'),
	(3,100001,108,2,'rule_lock','5','unlock after 5 times play','2025-05-02 16:46:06','2025-05-05 08:37:47'),
	(4,100001,109,2,'rule_lock','8','unlock after 8 times play','2025-05-02 16:46:06','2025-05-05 08:37:48'),
	(5,100001,107,2,'rule_luck_award','1,100','random points less than 100 - fallback prize','2025-05-02 17:46:16','2025-05-11 13:28:50'),
	(6,100001,108,2,'rule_luck_award','1,100','random points less than 100 - fallback prize','2025-05-02 17:46:16','2025-05-11 13:28:54'),
	(7,100001,109,2,'rule_luck_award','1,100','random points less than 100 - fallback prize','2025-05-02 17:46:16','2025-05-11 13:28:57'),
	(8,100001,101,2,'rule_luck_award','1,10','random points less than 10 - fallback prize','2025-05-02 17:46:16','2025-05-11 13:29:02'),
	(9,100001,102,2,'rule_luck_award','1,20','random points less than 20 - fallback prize','2025-05-02 17:46:16','2025-05-11 13:29:05'),
	(10,100001,103,2,'rule_luck_award','1,30','random points less than 30 - fallback prize','2025-05-02 17:46:16','2025-05-11 13:29:10'),
	(11,100001,104,2,'rule_luck_award','1,40','random points less than 40 - fallback prize','2025-05-02 17:46:16','2025-05-11 13:29:15'),
	(12,100001,105,2,'rule_luck_award','1,50','random points less than 50 - fallback prize','2025-05-02 17:46:16','2025-05-11 13:29:18'),
	(13,100001,106,2,'rule_luck_award','1,60','random points less than 60 - fallback prize','2025-05-02 17:46:16','2025-05-11 13:29:21'),
	(14,100001,NULL,1,'rule_weight','4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109','choose from designed awards based on points used','2025-05-02 18:02:12','2025-05-05 20:21:53'),
	(15,100001,NULL,1,'rule_blacklist','100:user001,user002,user003','Blacklist user, 1 point for each round','2025-05-02 18:02:12','2025-05-06 14:23:31'),
	(16,100003,107,2,'rule_lock','3','unlock after 3 times play','2025-05-02 16:46:06','2025-05-05 08:37:46'),
	(17,100003,108,2,'rule_lock','5','unlock after 5 times play','2025-05-02 16:46:06','2025-05-05 08:37:47'),
	(18,100003,109,2,'rule_lock','8','unlock after 8 times play','2025-05-02 16:46:06','2025-05-05 08:37:48');

/*!40000 ALTER TABLE `strategy_rule` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
