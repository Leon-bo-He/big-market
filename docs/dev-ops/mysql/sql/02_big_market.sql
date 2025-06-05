# ************************************************************
# Sequel Ace SQL dump
# Version 20067
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 8.0.32)
# Database: big_market
# Generation Time: 2025-06-05 18:31:57 +0000
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
	(1,101,'user_credit_random','1,100','award user random credits between 1 to 100','2025-06-04 09:15:33','2025-06-04 10:20:58'),
	(2,102,'udemy_course_voucher','5','Access 5 tech courses of your choice on Udemy.','2025-06-04 09:15:33','2025-06-04 11:01:20'),
	(3,103,'aws_credits','100','$100 AWS Credits','2025-06-04 09:15:33','2025-06-04 10:21:57'),
	(4,104,'copilot_subscription','1','1-Year GitHub Copilot Subscription','2025-06-04 09:15:33','2025-06-04 10:24:31'),
	(5,105,'swag_pack','1','T-shirt, laptop stickers, water bottle, socks','2025-06-04 09:15:33','2025-06-04 10:25:29'),
	(6,106,'mechanical_keyboard','1','Keychron K8 Pro','2025-06-04 09:15:33','2025-06-04 10:26:18'),
	(7,107,'headphones','1','Sony WH-1000XM6','2025-06-04 09:15:33','2025-06-04 10:27:19'),
	(8,108,'standing_desk_setup','1','Ergonomic standing desk + monitor arm + mat bundle.','2025-06-04 09:15:33','2025-06-04 10:28:38'),
	(9,109,'apple_macbook','1','MacBook Pro 16\" (M3 Pro)','2025-06-04 09:15:33','2025-06-04 10:28:18'),
	(10,110,'product_design_sprint','1','1-week product design sprint with a senior designer','2025-06-04 10:32:03','2025-06-04 10:32:23'),
	(11,111,'cloud_infra_audit','1','Cloud infrastructure audit for AWS or GCP','2025-06-04 10:32:03','2025-06-04 10:32:24'),
	(12,112,'marketing_strategy_session','1','2-hour session with a SaaS growth marketing expert','2025-06-04 10:32:03','2025-06-04 10:32:25'),
	(13,113,'startup_legal_office_hours','1','Legal advisory session for IP, contracts, or compliance','2025-06-04 10:32:03','2025-06-04 10:32:27'),
	(14,114,'devops_health_check','1','DevOps pipeline health check and optimization tips','2025-06-04 10:32:03','2025-06-04 10:32:28'),
	(15,115,'crm_subscription_pro','1','1-month pro-tier CRM tool subscription','2025-06-04 10:32:03','2025-06-04 10:32:29');

/*!40000 ALTER TABLE `award` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table daily_behavior_rebate
# ------------------------------------------------------------

DROP TABLE IF EXISTS `daily_behavior_rebate`;

CREATE TABLE `daily_behavior_rebate` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `behavior_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'CHECK_IN; PAYMENT',
  `rebate_desc` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rebate_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU; INTEGRAL - user points',
  `rebate_config` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `state` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'OPEN, CLOSE',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_behavior_type` (`behavior_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `daily_behavior_rebate` WRITE;
/*!40000 ALTER TABLE `daily_behavior_rebate` DISABLE KEYS */;

INSERT INTO `daily_behavior_rebate` (`id`, `behavior_type`, `rebate_desc`, `rebate_type`, `rebate_config`, `state`, `create_time`, `update_time`)
VALUES
	(1,'CHECK_IN','CheckIn rebate - sku quota','SKU','9014','OPEN','2025-06-04 09:15:33','2025-06-04 10:42:50'),
	(2,'CHECK_IN','CheckIn rebate - user credits','INTEGRAL','10','OPEN','2025-06-04 09:15:33','2025-06-04 09:17:16');

/*!40000 ALTER TABLE `daily_behavior_rebate` ENABLE KEYS */;
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
	(1,100301,'9 rewards strategy','9 rewards strategy','2025-05-22 08:23:50','2035-05-22 08:23:50',100001,'OPEN','2025-06-04 09:15:33','2025-06-04 10:34:10'),
	(2,100302,'6 rewards strategy','6 rewards strategy','2025-05-22 08:23:50','2035-05-22 08:23:50',100002,'OPEN','2025-06-04 09:15:33','2025-06-04 10:34:13');

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
	(1,11101,100,100,100,'2025-06-04 09:15:33','2025-06-04 10:40:15'),
	(2,11102,70,70,70,'2025-06-04 09:15:33','2025-06-04 10:37:24'),
	(3,11103,30,30,30,'2025-06-04 09:15:33','2025-06-04 10:37:58'),
	(4,11104,10,10,10,'2025-06-04 09:15:33','2025-06-04 10:38:02'),
	(5,11105,5,5,5,'2025-06-04 09:15:33','2025-06-04 10:38:07');

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
  `product_amount` decimal(10,2) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_sku` (`sku`),
  KEY `idx_activity_id_activity_count_id` (`activity_id`,`activity_count_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `raffle_activity_sku` WRITE;
/*!40000 ALTER TABLE `raffle_activity_sku` DISABLE KEYS */;

INSERT INTO `raffle_activity_sku` (`id`, `sku`, `activity_id`, `activity_count_id`, `stock_count`, `stock_count_surplus`, `product_amount`, `create_time`, `update_time`)
VALUES
	(1,9011,100301,11101,100000,100000,300.00,'2025-06-04 09:15:33','2025-06-04 10:40:27'),
	(2,9012,100301,11102,100000,100000,245.00,'2025-06-04 09:15:33','2025-06-04 10:39:21'),
	(3,9013,100301,11103,100000,100000,120.00,'2025-06-04 09:15:33','2025-06-05 11:29:59'),
	(4,9014,100301,11104,100000,100000,45.00,'2025-06-04 09:15:33','2025-06-05 11:30:01'),
	(5,9015,100301,11105,100000,100000,25.00,'2025-06-04 09:15:33','2025-06-04 10:38:33'),
	(6,9016,100302,11102,100000,100000,105.00,'2025-06-04 09:15:33','2025-06-04 10:48:44'),
	(7,9017,100302,11103,100000,100000,60.00,'2025-06-04 09:15:33','2025-06-04 10:48:44'),
	(8,9018,100302,11105,100000,100000,15.00,'2025-06-04 09:15:33','2025-06-04 10:48:46');

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
	(1,'tree_lock_1','rule_tree','rule_tree','rule_lock','2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(2,'tree_luck_award','rule_tree_fallback_prize','rule_tree_fallback_prize','rule_stock','2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(3,'tree_lock_5','rule_tree','rule_tree','rule_lock','2025-06-04 09:15:33','2025-06-04 10:44:58'),
	(4,'tree_lock_10','rule_tree','rule_tree','rule_lock','2025-06-04 09:15:33','2025-06-04 10:45:01');

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
	(1,'tree_lock_1','rule_lock','unlock certain award as the user completes N times of lottery','1','2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(2,'tree_lock_1','rule_luck_award','random points - fallback prize','101:1,100','2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(3,'tree_lock_1','rule_stock','stock deduction rule',NULL,'2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(4,'tree_luck_award','rule_stock','stock deduction rule',NULL,'2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(5,'tree_luck_award','rule_luck_award','random points - fallback prize','101:1,100','2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(6,'tree_lock_5','rule_lock','unlock certain award as the user completes N times of lottery','5','2025-06-04 09:15:33','2025-06-04 10:45:13'),
	(7,'tree_lock_5','rule_luck_award','random points - fallback prize','101:1,100','2025-06-04 09:15:33','2025-06-04 10:45:09'),
	(8,'tree_lock_5','rule_stock','stock deduction rule',NULL,'2025-06-04 09:15:33','2025-06-04 10:45:11'),
	(9,'tree_lock_10','rule_lock','unlock certain award as the user completes N times of lottery','10','2025-06-04 09:15:33','2025-06-04 10:45:55'),
	(10,'tree_lock_10','rule_luck_award','random points - fallback prize','101:1,100','2025-06-04 09:15:33','2025-06-04 10:45:50'),
	(11,'tree_lock_10','rule_stock','stock deduction rule',NULL,'2025-06-04 09:15:33','2025-06-04 10:45:52');

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
	(1,'tree_lock_1','rule_lock','rule_stock','EQUAL','ALLOW','2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(2,'tree_lock_1','rule_lock','rule_luck_award','EQUAL','TAKE_OVER','2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(3,'tree_lock_1','rule_stock','rule_luck_award','EQUAL','ALLOW','2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(4,'tree_luck_award','rule_stock','rule_luck_award','EQUAL','ALLOW','2025-06-04 09:15:33','2025-06-04 09:15:33'),
	(5,'tree_lock_5','rule_lock','rule_stock','EQUAL','ALLOW','2025-06-04 09:15:33','2025-06-04 10:46:11'),
	(6,'tree_lock_5','rule_lock','rule_luck_award','EQUAL','TAKE_OVER','2025-06-04 09:15:33','2025-06-04 10:46:13'),
	(7,'tree_lock_5','rule_stock','rule_luck_award','EQUAL','ALLOW','2025-06-04 09:15:33','2025-06-04 10:46:15'),
	(8,'tree_lock_10','rule_lock','rule_luck_award','EQUAL','ALLOW','2025-06-04 09:15:33','2025-06-04 10:46:17'),
	(9,'tree_lock_10','rule_lock','rule_luck_award','EQUAL','TAKE_OVER','2025-06-04 09:15:33','2025-06-04 10:46:20'),
	(10,'tree_lock_10','rule_stock','rule_luck_award','EQUAL','ALLOW','2025-06-04 09:15:33','2025-06-04 10:46:22');

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
	(1,100001,'Strategy A - 9 rewards','rule_blacklist,rule_weight','2025-06-04 09:15:33','2025-06-04 10:47:25'),
	(2,100002,'Strategy B - 6 rewards','rule_blacklist,rule_weight','2025-06-04 09:15:33','2025-06-04 10:48:33');

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
	(1,100001,101,'Random Credit Drop','Receive a surprise amount of bonus credits.',80000,80000,0.3000,'tree_luck_award',1,'2025-06-04 09:15:33','2025-06-05 11:31:10'),
	(2,100001,102,'Udemy Learning Pass','Access 5 tech courses of your choice on Udemy.',10000,10000,0.2000,'tree_luck_award',2,'2025-06-04 09:15:33','2025-06-04 11:02:24'),
	(3,100001,103,'AWS Cloud Credits','Boost your cloud app with free AWS credit.',5000,5000,0.2000,'tree_luck_award',3,'2025-06-04 09:15:33','2025-06-05 11:31:15'),
	(4,100001,104,'GitHub Copilot Access','1-year subscription to GitHub Copilot – your AI coding partner.',4000,4000,0.1000,'tree_luck_award',4,'2025-06-04 09:15:33','2025-06-04 11:02:29'),
	(5,100001,105,'Developer Swag Bundle','T-shirt, stickers, socks & more in a stylish dev pack.',600,600,0.1000,'tree_luck_award',5,'2025-06-04 09:15:33','2025-06-05 11:31:19'),
	(6,100001,106,'Pro Mechanical Keyboard','Wireless mechanical keyboard loved by coders.',200,200,0.0500,'tree_luck_award',6,'2025-06-04 09:15:33','2025-06-05 11:31:21'),
	(7,100001,107,'Noise-Canceling Headphones','Stay focused with premium over-ear noise-cancelers.',200,200,0.0400,'tree_lock_1',7,'2025-06-04 09:15:33','2025-06-04 11:02:40'),
	(8,100001,108,'Ergonomic Desk Setup','Adjustable standing desk with accessories.',199,199,0.0078,'tree_lock_5',8,'2025-06-04 09:15:33','2025-06-04 11:03:23'),
	(9,100001,109,'MacBook Pro','Top-tier performance MacBook for tech creators.',10,10,0.0022,'tree_lock_10',9,'2025-06-04 09:15:33','2025-06-04 11:03:26'),
	(10,100002,110,'UI/UX Design Sprint','1-week design consultation to improve your product UX.',80000,80000,0.2000,'tree_luck_award',1,'2025-06-04 11:04:49','2025-06-04 11:06:36'),
	(11,100002,111,'Cloud Infrastructure Review','Audit your AWS/GCP setup for performance & security.',80000,80000,0.2000,'tree_luck_award',2,'2025-06-04 11:04:49','2025-06-04 11:06:37'),
	(12,100002,112,'SaaS Growth Strategy Session','Get expert marketing advice tailored to your product.',80000,80000,0.2000,'tree_luck_award',3,'2025-06-04 11:04:49','2025-06-04 11:07:59'),
	(13,100002,113,'Startup Legal Consultation','One-on-one legal support for founders and startups.',80000,80000,0.2000,'tree_lock_1',4,'2025-06-04 11:04:49','2025-06-04 11:08:14'),
	(14,100002,114,'DevOps Pipeline Checkup','Optimize your CI/CD and deployment workflow.',40000,40000,0.1000,'tree_lock_5',5,'2025-06-04 11:04:49','2025-06-04 11:08:29'),
	(15,100002,115,'CRM Pro Plan Access','Free 1-month access to a top-tier CRM platform.',40000,40000,0.1000,'tree_lock_10',6,'2025-06-04 11:04:49','2025-06-04 11:08:43');

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
	(1,100001,NULL,1,'rule_weight','10:102,103,104,105,106,107 20:102,103,104,105,106,107,108 50:105,106,107,108,109','choose from designed awards based on points used','2025-06-04 09:15:33','2025-06-04 10:51:20'),
	(2,100001,NULL,1,'rule_blacklist','101:user001,user002,user003','Blacklist user, 1 point for each round','2025-06-04 09:15:33','2025-06-04 10:48:28'),
	(3,100002,NULL,1,'rule_weight','10:111,112,113 20:111,112,113,114 50:114,115','choose from designed awards based on points used','2025-06-04 09:15:33','2025-06-05 11:25:52'),
	(4,100002,NULL,1,'rule_blacklist','101:user001,user002,user003','Blacklist user, 1 point for each round','2025-06-04 09:15:33','2025-06-04 10:53:15');

/*!40000 ALTER TABLE `strategy_rule` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
