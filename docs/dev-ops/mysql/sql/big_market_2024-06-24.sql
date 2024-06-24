# ************************************************************
# Sequel Ace SQL dump
# Version 20067
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: localhost (MySQL 8.0.32)
# Database: big_market
# Generation Time: 2024-06-24 01:47:30 +0000
# ************************************************************


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
  `award_key` varchar(32) NOT NULL,
  `award_config` varchar(32) NOT NULL,
  `award_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `award` WRITE;
/*!40000 ALTER TABLE `award` DISABLE KEYS */;

INSERT INTO `award` (`id`, `award_id`, `award_key`, `award_config`, `award_desc`, `create_time`, `update_time`)
VALUES
	(1,101,'user_credit_random','1,100','award user random credits','2024-06-24 09:38:53','2024-06-24 09:43:27'),
	(2,102,'openai_use_count','5','add voucher','2024-06-24 09:43:56','2024-06-24 09:43:56'),
	(3,103,'openai_use_count','10','add voucher','2024-06-24 09:43:56','2024-06-24 09:43:56'),
	(4,104,'openai_use_count','20','add voucher','2024-06-24 09:43:56','2024-06-24 09:43:56'),
	(5,105,'openai_model','gpt-4','add gpt-4 model','2024-06-24 09:43:56','2024-06-24 09:43:56'),
	(6,106,'openai_model','dall-e-2','add dall-e-2 model','2024-06-24 09:43:56','2024-06-24 09:43:56'),
	(7,107,'openai_model','dall-e-3','add dall-e-3 model','2024-06-24 09:43:56','2024-06-24 09:46:18'),
	(8,108,'openai_use_count','100','add voucher','2024-06-24 09:43:56','2024-06-24 09:43:56'),
	(9,109,'openai_model','gpt-4, dall-e-2, dall-e-3','add models','2024-06-24 09:43:56','2024-06-24 09:47:05');

/*!40000 ALTER TABLE `award` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table strategy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `strategy`;

CREATE TABLE `strategy` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` int NOT NULL,
  `strategy_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `strategy` WRITE;
/*!40000 ALTER TABLE `strategy` DISABLE KEYS */;

INSERT INTO `strategy` (`id`, `strategy_id`, `strategy_desc`, `create_time`, `update_time`)
VALUES
	(1,10001,'Strategy A','2024-06-23 17:45:53','2024-06-23 17:45:53');

/*!40000 ALTER TABLE `strategy` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table strategy_award
# ------------------------------------------------------------

DROP TABLE IF EXISTS `strategy_award`;

CREATE TABLE `strategy_award` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` int NOT NULL,
  `award_id` int NOT NULL,
  `award_title` varchar(128) NOT NULL,
  `award_subtitle` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `award_count` int NOT NULL DEFAULT '0',
  `award_count_surplus` int NOT NULL DEFAULT '0',
  `award_rate` decimal(6,4) NOT NULL COMMENT 'Probability of winning prizes',
  `rule_models` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Sync strategy rule to this table, for convience use',
  `sort` int NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `strategy_award` WRITE;
/*!40000 ALTER TABLE `strategy_award` DISABLE KEYS */;

INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `rule_models`, `sort`, `create_time`, `update_time`)
VALUES
	(1,10001,101,'Random Credit',NULL,80000,80000,80.0000,'rule_random, rule_luck_award',1,'2024-06-23 17:47:00','2024-06-24 09:41:12'),
	(2,10001,102,'5 Times Voucher',NULL,10000,10000,10.0000,'rule_luck_award',2,'2024-06-23 17:47:00','2024-06-23 17:49:38'),
	(3,10001,103,'10 Times Voucher',NULL,5000,5000,5.0000,'rule_luck_award',3,'2024-06-23 17:47:00','2024-06-23 17:50:10'),
	(4,10001,104,'20 Times Voucher',NULL,4000,4000,4.0000,'rule_luck_award',4,'2024-06-23 17:47:00','2024-06-23 17:50:54'),
	(5,10001,105,'Add GPT- 4 model',NULL,600,600,0.6000,'rule_luck_award',5,'2024-06-23 19:02:03','2024-06-23 19:02:03'),
	(6,10001,106,'Add dall-e-2 model',NULL,200,200,0.2000,'rule_luck_award',6,'2024-06-23 19:02:03','2024-06-23 19:02:03'),
	(7,10001,107,'Add dall-e-3 model','Unlock after 1 time play',200,200,0.2000,'rule_luck_award, rule_lock',7,'2024-06-23 19:02:03','2024-06-23 19:04:07'),
	(8,10001,108,'100 Times Voucher','Unlock after 2 times play',199,199,0.1999,'rule_luck_award, rule_lock',8,'2024-06-23 19:02:03','2024-06-23 19:04:07'),
	(9,10001,109,'Unlock All Models','Unlock after 6 times play',1,1,0.0001,'rule_luck_award, rule_lock',9,'2024-06-23 19:02:03','2024-06-23 19:06:27');

/*!40000 ALTER TABLE `strategy_award` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table strategy_rule
# ------------------------------------------------------------

DROP TABLE IF EXISTS `strategy_rule`;

CREATE TABLE `strategy_rule` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` int NOT NULL,
  `award_id` int DEFAULT NULL COMMENT 'This will be null if we are under strategy type',
  `rule_type` int NOT NULL DEFAULT '0' COMMENT '1 for strategy, 2 for strategy_award',
  `rule_model` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'rule_random -> random value; rule_lock -> unlock after playing; rule_luck_award -> consulation prize',
  `rule_value` varchar(64) NOT NULL,
  `rule_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `strategy_rule` WRITE;
/*!40000 ALTER TABLE `strategy_rule` DISABLE KEYS */;

INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`)
VALUES
	(1,10001,101,2,'rule_random','1,1000','random credit strategy','2024-06-23 19:35:07','2024-06-24 09:40:07'),
	(2,10001,107,2,'rule_lock','1','unlock after 1 time play','2024-06-23 19:35:35','2024-06-23 19:35:35'),
	(3,10001,108,2,'rule_lock','2','unlock after 2 times play','2024-06-23 19:35:35','2024-06-23 19:36:03'),
	(4,10001,109,2,'rule_lock','6','unlock after 6 times play','2024-06-23 19:35:35','2024-06-23 19:36:31'),
	(5,10001,107,2,'rule_luck_award','1,100','random credit less than 100 - consulation prize','2024-06-23 19:38:06','2024-06-24 09:40:01'),
	(6,10001,108,2,'rule_luck_award','1,100','random credit less than 100 - consulation prize','2024-06-23 19:38:06','2024-06-24 09:40:14'),
	(7,10001,101,2,'rule_luck_award','1,10','random credit less than 10 - consulation prize','2024-06-23 19:38:06','2024-06-24 09:40:19'),
	(8,10001,102,2,'rule_luck_award','1,20','random credit less than 20 - consulation prize','2024-06-23 19:38:06','2024-06-24 09:40:23'),
	(9,10001,103,2,'rule_luck_award','1,30','random credit less than 30 - consulation prize','2024-06-23 19:38:06','2024-06-24 09:40:27'),
	(10,10001,104,2,'rule_luck_award','1,40','random credit less than 40 - consulation prize','2024-06-23 19:38:06','2024-06-24 09:40:32'),
	(11,10001,105,2,'rule_luck_award','1,50','random credit less than 50 - consulation prize','2024-06-23 19:38:06','2024-06-24 09:40:39'),
	(12,10001,106,2,'rule_luck_award','1,60','random credit less than 60 - consulation prize','2024-06-23 19:38:06','2024-06-24 09:40:35'),
	(13,10001,NULL,1,'rule_weight','6000,102,103,104,105,106,107,108,109','If used 6000 credit, must choose one from these awards','2024-06-23 19:43:50','2024-06-24 09:40:45'),
	(14,10001,NULL,1,'rule_blacklist','1','Blacklist user, 1 credit for each round','2024-06-23 19:44:52','2024-06-24 09:40:50');

/*!40000 ALTER TABLE `strategy_rule` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
