# ************************************************************
# Sequel Ace SQL dump
# Version 20067
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 8.0.32)
# Database: big_market
# Generation Time: 2025-05-06 16:13:30 +0000
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
	(9,109,'openai_model','gpt-4, dall-e-2, dall-e-3','unlock models','2025-05-02 18:29:06','2025-05-02 18:29:06');

/*!40000 ALTER TABLE `award` ENABLE KEYS */;
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
	(2,100002,'Strategy A','rule_weight,rule_blacklist','2025-05-02 16:19:25','2025-05-05 08:33:54');

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
	(1,100001,101,'Random Points',NULL,80000,80000,0.3000,'rule_random, rule_luck_award',1,'2025-05-06 09:12:40','2025-05-06 09:12:40'),
	(2,100001,102,'5 Times Voucher',NULL,10000,10000,0.2000,'rule_luck_award',2,'2025-05-06 09:12:43','2025-05-06 09:12:43'),
	(3,100001,103,'10 Times Voucher',NULL,5000,5000,0.2000,'rule_luck_award',3,'2025-05-06 09:12:50','2025-05-06 09:12:50'),
	(4,100001,104,'20 Times Voucher',NULL,4000,4000,0.1000,'rule_luck_award',4,'2025-05-06 09:12:52','2025-05-06 09:12:52'),
	(5,100001,105,'Unlock GPT-4 Model',NULL,600,600,0.1000,'rule_luck_award',5,'2025-05-06 09:12:59','2025-05-06 09:12:59'),
	(6,100001,106,'Unlock dall-e-2 Model',NULL,200,200,0.0500,'rule_luck_award',6,'2025-05-06 09:13:09','2025-05-06 09:13:09'),
	(7,100001,107,'Unlock dall-e-3 Model','Unlock after 3 times play',200,200,0.0400,'rule_luck_award, rule_lock',7,'2025-05-06 09:13:12','2025-05-06 09:13:12'),
	(8,100001,108,'100 Times Voucher','Unlock after 5 times play',199,199,0.0099,'rule_luck_award, rule_lock',8,'2025-05-06 09:13:19','2025-05-06 09:13:19'),
	(9,100001,109,'Unlock All Models','Unlock after 8 times play',1,1,0.0001,'rule_luck_award, rule_lock',9,'2025-05-05 08:34:43','2025-05-05 08:34:43'),
	(10,100002,101,'Random Points',NULL,1,1,0.5000,'rule_random,rule_luck_award',1,'2025-05-05 08:37:00','2025-05-05 08:37:00'),
	(11,100002,102,'5 Times Voucher',NULL,1,1,0.1000,'rule_random,rule_luck_award',2,'2025-05-05 08:37:01','2025-05-05 08:37:01'),
	(12,100002,106,'add...',NULL,1,1,0.0100,'rule_random,rule_luck_award',3,'2025-05-05 08:37:01','2025-05-05 08:37:01');

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
  `rule_model` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'rule_random -> random value; rule_lock -> unlock after playing; rule_luck_award -> consolation prize',
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
	(5,100001,107,2,'rule_luck_award','1,100','random points less than 100 - consolation prize','2025-05-02 17:46:16','2025-05-05 08:37:48'),
	(6,100001,108,2,'rule_luck_award','1,100','random points less than 100 - consolation prize','2025-05-02 17:46:16','2025-05-05 08:37:49'),
	(7,100001,109,2,'rule_luck_award','1,100','random points less than 100 - consolation prize','2025-05-02 17:46:16','2025-05-05 08:37:50'),
	(8,100001,101,2,'rule_luck_award','1,10','random points less than 10 - consolation prize','2025-05-02 17:46:16','2025-05-05 08:37:50'),
	(9,100001,102,2,'rule_luck_award','1,20','random points less than 20 - consolation prize','2025-05-02 17:46:16','2025-05-05 08:37:51'),
	(10,100001,103,2,'rule_luck_award','1,30','random points less than 30 - consolation prize','2025-05-02 17:46:16','2025-05-05 08:37:52'),
	(11,100001,104,2,'rule_luck_award','1,40','random points less than 40 - consolation prize','2025-05-02 17:46:16','2025-05-05 08:37:53'),
	(12,100001,105,2,'rule_luck_award','1,50','random points less than 50 - consolation prize','2025-05-02 17:46:16','2025-05-05 08:37:57'),
	(13,100001,106,2,'rule_luck_award','1,60','random points less than 60 - consolation prize','2025-05-02 17:46:16','2025-05-05 08:37:58'),
	(14,100001,NULL,1,'rule_weight','4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109','choose from designed awards based on points used','2025-05-02 18:02:12','2025-05-05 20:21:53'),
	(15,100001,NULL,1,'rule_blacklist','1','Blacklist user, 1 point for each round','2025-05-02 18:02:12','2025-05-05 08:38:01');

/*!40000 ALTER TABLE `strategy_rule` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
