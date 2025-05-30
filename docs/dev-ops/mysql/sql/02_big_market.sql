# ************************************************************
# Sequel Ace SQL dump
# Version 20067
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 8.0.32)
# Database: big_market
# Generation Time: 2025-05-29 19:02:17 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

USE `big_market`;

# Dump of table award
# ------------------------------------------------------------

DROP TABLE IF EXISTS `award`;

CREATE TABLE `award` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `award_id` int NOT NULL COMMENT 'inner use',
  `award_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'prize docking tag, each is a corresponding award-giving strategy',
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
	(1,101,'user_credit_random','1,100','award user random points','2023-12-09 11:07:06','2025-05-20 20:32:02'),
	(2,102,'openai_use_count','5','add voucher','2023-12-09 11:07:06','2025-05-20 20:32:20'),
	(3,103,'openai_use_count','10','add voucher','2023-12-09 11:07:06','2025-05-20 20:32:20'),
	(4,104,'openai_use_count','20','add voucher','2023-12-09 11:07:06','2025-05-20 20:32:21'),
	(5,105,'openai_model','gpt-4','unlock gpt-4 model','2023-12-09 11:07:06','2025-05-20 20:32:37'),
	(6,106,'openai_model','dall-e-2','unlock dall-e-2 model','2023-12-09 11:07:06','2025-05-20 20:32:42'),
	(7,107,'openai_model','dall-e-3','unlock dall-e-3 model','2023-12-09 11:07:06','2025-05-20 20:32:44'),
	(8,108,'openai_use_count','100','add voucher','2023-12-09 11:07:06','2025-05-20 20:32:50'),
	(9,109,'openai_model','gpt-4,dall-e-2,dall-e-3','unlock models','2023-12-09 11:07:06','2025-05-20 20:32:55'),
	(10,100,'user_credit_blacklist','1','award blacklist user 1 point','2024-01-06 12:30:40','2025-05-20 20:33:05');

/*!40000 ALTER TABLE `award` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table raffle_activity
# ------------------------------------------------------------

DROP TABLE IF EXISTS `raffle_activity`;

CREATE TABLE `raffle_activity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `activity_id` bigint NOT NULL,
  `activity_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `activity_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `begin_date_time` datetime NOT NULL COMMENT 'activity start data time',
  `end_date_time` datetime NOT NULL COMMENT 'activity end data time',
  `strategy_id` bigint NOT NULL,
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '[CREATE, OPEN, CLOSE]',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_activity_id` (`activity_id`),
  KEY `idx_begin_date_time` (`begin_date_time`),
  KEY `idx_end_date_time` (`end_date_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `raffle_activity` WRITE;
/*!40000 ALTER TABLE `raffle_activity` DISABLE KEYS */;

INSERT INTO `raffle_activity` (`id`, `activity_id`, `activity_name`, `activity_desc`, `begin_date_time`, `end_date_time`, `strategy_id`, `state`, `create_time`, `update_time`)
VALUES
	(1,100301,'test activity','test activity','2025-05-22 08:23:50','2035-05-22 08:23:50',100006,'OPEN','2025-05-22 08:23:50','2025-05-24 00:03:40');

/*!40000 ALTER TABLE `raffle_activity` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table raffle_activity_count
# ------------------------------------------------------------

DROP TABLE IF EXISTS `raffle_activity_count`;

CREATE TABLE `raffle_activity_count` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `activity_count_id` bigint NOT NULL,
  `total_count` int NOT NULL COMMENT 'total activity limit',
  `day_count` int NOT NULL COMMENT 'daily activity limit',
  `month_count` int NOT NULL COMMENT 'monthly activity limit',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_activity_count_id` (`activity_count_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `raffle_activity_count` WRITE;
/*!40000 ALTER TABLE `raffle_activity_count` DISABLE KEYS */;

INSERT INTO `raffle_activity_count` (`id`, `activity_count_id`, `total_count`, `day_count`, `month_count`, `create_time`, `update_time`)
VALUES
	(1,11101,100,2,60,'2025-05-22 08:24:26','2025-05-22 19:34:51');

/*!40000 ALTER TABLE `raffle_activity_count` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table raffle_activity_sku
# ------------------------------------------------------------

DROP TABLE IF EXISTS `raffle_activity_sku`;

CREATE TABLE `raffle_activity_sku` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `sku` bigint NOT NULL,
  `activity_id` bigint NOT NULL,
  `activity_count_id` bigint NOT NULL,
  `stock_count` int NOT NULL,
  `stock_count_surplus` int NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_sku` (`sku`),
  KEY `idx_activity_id_activity_count_id` (`activity_id`,`activity_count_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `raffle_activity_sku` WRITE;
/*!40000 ALTER TABLE `raffle_activity_sku` DISABLE KEYS */;

INSERT INTO `raffle_activity_sku` (`id`, `sku`, `activity_id`, `activity_count_id`, `stock_count`, `stock_count_surplus`, `create_time`, `update_time`)
VALUES
	(1,9011,100301,11101,20,9,'2024-03-16 11:41:09','2025-05-28 11:15:55');

/*!40000 ALTER TABLE `raffle_activity_sku` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table rule_tree
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rule_tree`;

CREATE TABLE `rule_tree` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `tree_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tree_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tree_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tree_root_rule_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'rule_tree start node key',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_tree_id` (`tree_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `rule_tree` WRITE;
/*!40000 ALTER TABLE `rule_tree` DISABLE KEYS */;

INSERT INTO `rule_tree` (`id`, `tree_id`, `tree_name`, `tree_desc`, `tree_root_rule_key`, `create_time`, `update_time`)
VALUES
	(1,'tree_lock_1','rule_tree','rule_tree','rule_lock','2024-01-27 10:01:59','2025-05-20 20:34:24'),
	(2,'tree_luck_award','rule_tree_fallback_prize','rule_tree_fallback_prize','rule_stock','2024-02-15 07:35:06','2025-05-20 20:34:34'),
	(3,'tree_lock_2','rule_tree','rule_tree','rule_lock','2024-01-27 10:01:59','2025-05-20 20:34:25'),
	(4,'tree_lock_3','rule_tree','rule_tree','rule_lock','2024-01-27 10:01:59','2025-05-20 20:34:25');

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
	(1,'tree_lock_1','rule_lock','unlock certain award as the user completes N times of lottery','1','2024-01-27 10:03:09','2025-05-20 20:35:34'),
	(2,'tree_lock_1','rule_luck_award','random points - fallback prize','101:1,100','2024-01-27 10:03:09','2025-05-20 20:35:50'),
	(3,'tree_lock_1','rule_stock','stock deduction rule',NULL,'2024-01-27 10:04:43','2025-05-20 20:36:22'),
	(4,'tree_luck_award','rule_stock','stock deduction rule',NULL,'2024-02-15 07:35:55','2025-05-20 20:36:23'),
	(5,'tree_luck_award','rule_luck_award','random points - fallback prize','101:1,100','2024-02-15 07:35:55','2025-05-20 20:35:51'),
	(6,'tree_lock_2','rule_lock','unlock certain award as the user completes N times of lottery','2','2024-01-27 10:03:09','2025-05-20 20:35:34'),
	(7,'tree_lock_2','rule_luck_award','random points - fallback prize','101:1,100','2024-01-27 10:03:09','2025-05-20 20:35:51'),
	(8,'tree_lock_2','rule_stock','stock deduction rule',NULL,'2024-01-27 10:04:43','2025-05-20 20:36:24'),
	(9,'tree_lock_3','rule_lock','unlock certain award as the user completes N times of lottery','3','2024-01-27 10:03:09','2025-05-29 11:59:29'),
	(10,'tree_lock_3','rule_luck_award','random points - fallback prize','101:1,100','2024-01-27 10:03:09','2025-05-20 20:35:51'),
	(11,'tree_lock_3','rule_stock','stock deduction rule',NULL,'2024-01-27 10:04:43','2025-05-20 20:36:24');

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
	(1,'tree_lock_1','rule_lock','rule_stock','EQUAL','ALLOW','2025-05-29 12:01:14','2025-05-29 12:01:14'),
	(2,'tree_lock_1','rule_lock','rule_luck_award','EQUAL','TAKE_OVER','2025-05-29 12:01:18','2025-05-29 12:01:18'),
	(3,'tree_lock_1','rule_stock','rule_luck_award','EQUAL','ALLOW','2025-05-29 12:01:20','2025-05-29 12:01:20'),
	(4,'tree_luck_award','rule_stock','rule_luck_award','EQUAL','ALLOW','2024-02-15 07:37:31','2024-02-15 07:39:28'),
	(5,'tree_lock_2','rule_lock','rule_stock','EQUAL','ALLOW','2025-05-29 12:01:25','2025-05-29 12:01:25'),
	(6,'tree_lock_2','rule_lock','rule_luck_award','EQUAL','TAKE_OVER','2025-05-29 12:01:28','2025-05-29 12:01:28'),
	(7,'tree_lock_2','rule_stock','rule_luck_award','EQUAL','ALLOW','2025-05-29 12:01:30','2025-05-29 12:01:30'),
	(8,'tree_lock_3','rule_lock','rule_luck_award','EQUAL','ALLOW','2025-05-29 12:00:44','2024-02-15 07:55:08'),
	(9,'tree_lock_3','rule_lock','rule_luck_award','EQUAL','TAKE_OVER','2025-05-29 12:01:09','2024-02-15 07:55:11'),
	(10,'tree_lock_3','rule_stock','rule_luck_award','EQUAL','ALLOW','2025-05-29 12:01:30','2025-05-29 12:01:30');

/*!40000 ALTER TABLE `rule_tree_node_line` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table strategy
# ------------------------------------------------------------

DROP TABLE IF EXISTS `strategy`;

CREATE TABLE `strategy` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` bigint NOT NULL,
  `strategy_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rule_models` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Sync strategy rule to this table, for convenience use',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_strategy_id` (`strategy_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `strategy` WRITE;
/*!40000 ALTER TABLE `strategy` DISABLE KEYS */;

INSERT INTO `strategy` (`id`, `strategy_id`, `strategy_desc`, `rule_models`, `create_time`, `update_time`)
VALUES
	(1,100001,'Strategy A','rule_blacklist,rule_weight','2023-12-09 09:37:19','2025-05-20 20:38:29'),
	(2,100003,'Strategy B - lock','rule_blacklist','2024-01-13 10:34:06','2025-05-20 20:38:40'),
	(3,100002,'Strategy C - probability total not 1',NULL,'2023-12-09 09:37:19','2025-05-20 20:39:10'),
	(4,100004,'Strategy D - random raffle',NULL,'2023-12-09 09:37:19','2025-05-20 20:39:22'),
	(5,100005,'Strategy E - test probability calculation',NULL,'2023-12-09 09:37:19','2025-05-20 20:39:42'),
	(6,100006,'Strategy F - strategy tree',NULL,'2024-02-03 09:53:40','2025-05-20 20:39:53');

/*!40000 ALTER TABLE `strategy` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table strategy_award
# ------------------------------------------------------------

DROP TABLE IF EXISTS `strategy_award`;

CREATE TABLE `strategy_award` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` bigint NOT NULL,
  `award_id` int NOT NULL,
  `award_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `award_subtitle` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `award_count` int NOT NULL DEFAULT '0' COMMENT 'award total inventory',
  `award_count_surplus` int NOT NULL DEFAULT '0' COMMENT 'award inventory surplus',
  `award_rate` decimal(6,4) NOT NULL COMMENT 'Probability of winning prizes',
  `rule_models` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'Sync strategy rule to this table, for convenience use',
  `sort` int NOT NULL DEFAULT '0' COMMENT 'award identifier in current strategy',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_strategy_id_award_id` (`strategy_id`,`award_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `strategy_award` WRITE;
/*!40000 ALTER TABLE `strategy_award` DISABLE KEYS */;

INSERT INTO `strategy_award` (`id`, `strategy_id`, `award_id`, `award_title`, `award_subtitle`, `award_count`, `award_count_surplus`, `award_rate`, `rule_models`, `sort`, `create_time`, `update_time`)
VALUES
	(1,100001,101,'Random Points',NULL,80000,80000,0.3000,'tree_luck_award',1,'2023-12-09 09:38:31','2025-05-20 20:41:56'),
	(2,100001,102,'5 Times Voucher',NULL,10000,10000,0.2000,'tree_luck_award',2,'2023-12-09 09:39:18','2025-05-20 20:42:10'),
	(3,100001,103,'10 Times Voucher',NULL,5000,5000,0.2000,'tree_luck_award',3,'2023-12-09 09:42:36','2025-05-20 20:42:14'),
	(4,100001,104,'20 Times Voucher',NULL,4000,4000,0.1000,'tree_luck_award',4,'2023-12-09 09:43:15','2025-05-20 20:42:21'),
	(5,100001,105,'Unlock GPT-4 Model',NULL,600,600,0.1000,'tree_luck_award',5,'2023-12-09 09:43:47','2025-05-20 20:42:23'),
	(6,100001,106,'Unlock dall-e-2 Model',NULL,200,200,0.0500,'tree_luck_award',6,'2023-12-09 09:44:20','2025-05-20 20:42:36'),
	(7,100001,107,'Unlock dall-e-3 Model','Unlock after 1 times play',200,200,0.0400,'tree_luck_award',7,'2023-12-09 09:45:38','2025-05-20 20:42:59'),
	(8,100001,108,'100 Times Voucher','Unlock after 2 times play',199,199,0.0099,'tree_luck_award',8,'2023-12-09 09:46:02','2025-05-20 20:43:33'),
	(9,100001,109,'Unlock All Models','Unlock after 6 times play',1,1,0.0001,'tree_luck_award',9,'2023-12-09 09:46:39','2025-05-20 20:43:44'),
	(10,100002,101,'Random Points',NULL,1,1,0.5000,'tree_luck_award',1,'2023-12-09 09:46:39','2025-05-20 20:43:47'),
	(11,100002,102,'5 Times Voucher',NULL,1,1,0.1000,'tree_luck_award',2,'2023-12-09 09:46:39','2025-05-20 20:43:49'),
	(12,100002,106,'Unlock dall-e-2 Model',NULL,1,1,0.0100,'tree_luck_award',3,'2023-12-09 09:46:39','2025-05-20 20:43:57'),
	(13,100003,107,'Unlock dall-e-3 Model','Unlock after 1 times play',200,200,0.0400,'tree_luck_award',7,'2023-12-09 09:45:38','2025-05-20 20:44:00'),
	(14,100003,108,'100 Times Voucher','Unlock after 2 times play',199,199,0.0099,'tree_luck_award',8,'2023-12-09 09:46:02','2025-05-20 20:44:06'),
	(15,100003,109,'Unlock All Models','Unlock after 6 times play',1,1,0.0001,'tree_luck_award',9,'2023-12-09 09:46:39','2025-05-20 20:44:11'),
	(16,100004,109,'Unlock All Models','Unlock after 6 times play',1,1,1.0000,'tree_luck_award',9,'2023-12-09 09:46:39','2025-05-20 20:44:13'),
	(17,100005,101,'Random Points',NULL,80000,80000,0.0300,'tree_luck_award',1,'2023-12-09 09:38:31','2025-05-20 20:44:15'),
	(18,100005,102,'Random Points',NULL,80000,80000,0.0300,'tree_luck_award',1,'2023-12-09 09:38:31','2025-05-20 20:44:16'),
	(19,100005,103,'Random Points',NULL,80000,80000,0.0300,'tree_luck_award',1,'2023-12-09 09:38:31','2025-05-20 20:44:17'),
	(20,100005,104,'Random Points',NULL,80000,80000,0.0300,'tree_luck_award',1,'2023-12-09 09:38:31','2025-05-20 20:44:18'),
	(21,100005,105,'Random Points',NULL,80000,80000,0.0010,'tree_luck_award',1,'2023-12-09 09:38:31','2025-05-20 20:44:18'),
	(22,100006,101,'Random Points',NULL,100,85,0.0200,'tree_luck_award',1,'2023-12-09 09:38:31','2025-05-28 22:15:00'),
	(23,100006,102,'Prize 1',NULL,100,60,0.0300,'tree_luck_award',2,'2023-12-09 09:38:31','2025-05-29 11:58:01'),
	(24,100006,103,'Prize 2',NULL,100,69,0.0300,'tree_luck_award',3,'2023-12-09 09:38:31','2025-05-29 11:58:03'),
	(25,100006,104,'Prize 3',NULL,100,63,0.0300,'tree_luck_award',4,'2023-12-09 09:38:31','2025-05-29 11:58:05'),
	(26,100006,105,'Prize 4','Unlock after 3 times play',100,72,0.0300,'tree_lock_3',5,'2023-12-09 09:38:31','2025-05-29 11:57:27'),
	(27,100006,106,'Prize 5','Unlock after 2 times play',100,63,0.0300,'tree_lock_2',6,'2023-12-09 09:38:31','2025-05-29 11:58:07'),
	(28,100006,107,'Prize 6','Unlock after 1 times play',100,71,0.0300,'tree_lock_1',7,'2023-12-09 09:38:31','2025-05-29 11:58:09'),
	(29,100006,108,'Prize 7',NULL,100,69,0.0300,'tree_luck_award',8,'2023-12-09 09:38:31','2025-05-29 11:58:11');

/*!40000 ALTER TABLE `strategy_award` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table strategy_rule
# ------------------------------------------------------------

DROP TABLE IF EXISTS `strategy_rule`;

CREATE TABLE `strategy_rule` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `strategy_id` int NOT NULL,
  `award_id` int DEFAULT NULL COMMENT 'No award_id needed if rule_type is 1',
  `rule_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1 for strategy rule; 2 for award rule',
  `rule_model` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'rule_random -> random value; rule_lock -> unlock after playing; rule_luck_award -> fallback prize',
  `rule_value` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rule_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_strategy_id_award_id` (`strategy_id`,`award_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `strategy_rule` WRITE;
/*!40000 ALTER TABLE `strategy_rule` DISABLE KEYS */;

INSERT INTO `strategy_rule` (`id`, `strategy_id`, `award_id`, `rule_type`, `rule_model`, `rule_value`, `rule_desc`, `create_time`, `update_time`)
VALUES
	(13,100001,NULL,1,'rule_weight','4000:102,103,104,105 5000:102,103,104,105,106,107 6000:102,103,104,105,106,107,108,109','choose from designed awards based on points used','2023-12-09 10:30:43','2025-05-20 20:48:25'),
	(14,100001,NULL,1,'rule_blacklist','101:user001,user002,user003','Blacklist user, 1 point for each round','2023-12-09 12:59:45','2025-05-20 20:48:26');

/*!40000 ALTER TABLE `strategy_rule` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
