-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: j9a207.p.ssafy.io    Database: memorit
-- ------------------------------------------------------
-- Server version	11.1.2-MariaDB-1:11.1.2+maria~ubu2204

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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `article_id` bigint(20) NOT NULL,
  `user_id` binary(16) DEFAULT NULL,
  `friend_id` binary(16) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `type` varchar(256) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `detail` text DEFAULT NULL,
  `img` varchar(1024) DEFAULT NULL,
  `boolean` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base`
--

DROP TABLE IF EXISTS `base`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base` (
  `updated_at` date DEFAULT NULL,
  `created_at` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base`
--

LOCK TABLES `base` WRITE;
/*!40000 ALTER TABLE `base` DISABLE KEYS */;
/*!40000 ALTER TABLE `base` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(128) NOT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (9,'ì—†ìŒ',_binary '™\×ô\İU$LT¥#!i?@'),(10,'ì‹¸í”¼',_binary '™\×ô\İU$LT¥#!i?@'),(11,'ì ˆì¹œ',_binary '™\×ô\İU$LT¥#!i?@');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend` (
  `user_id` binary(16) NOT NULL,
  `friend_id` binary(16) NOT NULL,
  `name` varchar(40) NOT NULL,
  `profile_img` varchar(1024) DEFAULT NULL,
  `category` varchar(256) DEFAULT NULL,
  `received_count` int(11) DEFAULT 0,
  `sent_count` int(11) DEFAULT NULL,
  `received_money` int(11) DEFAULT NULL,
  `sent_money` int(11) DEFAULT NULL,
  `recent_date` date DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`user_id`,`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
INSERT INTO `friend` VALUES (_binary '™\×ô\İU$LT¥#!i?@',_binary '‡š\0¶–N›¤o\éø%\Ã¸','ìœ¤ì„ì¤€',NULL,NULL,0,1,0,100000,'2023-10-05','2023-10-05 14:59:28','2023-10-05 17:06:36'),(_binary '™\×ô\İU$LT¥#!i?@',_binary '\ßÿ¤=\ïBG¾\àlr·±½ı','ë¬µì°½ì„',NULL,'ì‹¸í”¼',3,4,500000,600000,'2023-10-06','2023-10-05 16:40:09','2023-10-05 17:37:27'),(_binary '™\×ô\İU$LT¥#!i?@',_binary '0Ff7\ÜòBËœ•È‚v\Ûhm','ê¹€ì°½ì„',NULL,NULL,0,0,0,0,NULL,'2023-10-05 16:39:54','2023-10-05 16:39:54'),(_binary '™\×ô\İU$LT¥#!i?@',_binary '8\Æ|\Í{O—±¶W\Ú;D¯Š','ì´ì°¬ë¯¼',NULL,'ì‹¸í”¼',1,0,30000,0,'2023-10-05','2023-10-05 08:01:20','2023-10-05 17:32:34'),(_binary '™\×ô\İU$LT¥#!i?@',_binary 'K\Ò&Sr\ÙDpµ)¬+ô7\Ï','ì œê°ˆì°½ì„',NULL,'ì ˆì¹œ',0,0,0,0,NULL,'2023-10-05 16:40:09','2023-10-05 16:46:19'),(_binary '™\×ô\İU$LT¥#!i?@',_binary '[‹S&¼öG\ç•gö\0uJ<','ë³€ì°½ì„',NULL,NULL,1,0,100000,0,'2023-10-14','2023-10-05 17:10:37','2023-10-05 17:10:54'),(_binary '™\×ô\İU$LT¥#!i?@',_binary 'jp˜+\"N×¶9w\Ôú','ì†¡ìˆ˜í˜„',NULL,NULL,0,0,0,0,NULL,'2023-10-05 14:59:28','2023-10-05 14:59:28'),(_binary '™\×ô\İU$LT¥#!i?@',_binary 'o·!\é=VBS¹Áhs\ÂR2','ìµœì§„ìš°',NULL,NULL,0,0,0,0,NULL,'2023-10-05 14:59:28','2023-10-05 14:59:28'),(_binary '™\×ô\İU$LT¥#!i?@',_binary 'p:ªM›+E7’sK I˜ö','ë°•ì°½ì„',NULL,NULL,0,0,0,0,NULL,'2023-10-05 16:39:54','2023-10-05 16:39:54'),(_binary '™\×ô\İU$LT¥#!i?@',_binary 's:\Ø0,xL\n”q^\×\r\\','ê¹€ì°½ê·¼',NULL,'ì ˆì¹œ',0,0,0,0,NULL,'2023-10-05 17:36:05','2023-10-05 17:36:05'),(_binary '™\×ô\İU$LT¥#!i?@',_binary 'zUR ûrKlbrm\ßoˆ','ì†¡ì°½ì„',NULL,'ì‹¸í”¼',0,0,0,0,NULL,'2023-10-05 17:11:39','2023-10-05 17:11:39'),(_binary '™\×ô\İU$LT¥#!i?@',_binary '„”J8f@b¾\î\ã»\ÏX','ë§¹ì°½ì„',NULL,NULL,1,0,1000000,0,'2023-10-11','2023-10-05 17:34:58','2023-10-05 17:35:14'),(_binary '™\×ô\İU$LT¥#!i?@',_binary '”©Ø¯­hO2µ\à­Xx>¾','ì „ì¤€ê·œ',NULL,'ì‹¸í”¼',0,0,0,0,NULL,'2023-10-05 08:02:35','2023-10-05 08:02:35'),(_binary '™\×ô\İU$LT¥#!i?@',_binary '£$¦¾VYAR„\Ë\'\èp\nB','ë°•ì¬í›ˆ',NULL,'ì—†ìŒ',0,0,0,0,NULL,'2023-10-05 08:02:02','2023-10-05 08:02:02'),(_binary '™\×ô\İU$LT¥#!i?@',_binary '\í\Ãõƒa(E»£=±+›W','ì˜¤ì°½ì„',NULL,NULL,0,0,0,0,NULL,'2023-10-05 16:39:54','2023-10-05 16:39:54');
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `friend_id` binary(16) NOT NULL,
  `is_given` bit(1) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `item` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` binary(16) NOT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (93,'2023-10-05 13:55:46','2023-10-05 13:55:46',30000,'2023-10-05','',_binary '8\Æ|\Í{O—±¶W\Ú;D¯Š',_binary '',NULL,NULL,'ìŠ¹ì§„',_binary '™\×ô\İU$LT¥#!i?@'),(94,'2023-10-05 16:41:15','2023-10-05 16:55:27',500000,'2023-10-06','ì°½ì„ì´ì˜ ê²°í˜¼ì‹\n',_binary '\ßÿ¤=\ïBG¾\àlr·±½ı',_binary '\0','https://mermorit.s3.ap-northeast-2.amazonaws.com/d8d71f61-6af1-4c70-b36e-4a701fd0f424202305081411219062_d.jpg',NULL,'ê²°í˜¼ì‹',_binary '™\×ô\İU$LT¥#!i?@'),(97,'2023-10-05 16:47:35','2023-10-05 16:47:35',500000,'2022-07-01','ëŒì”ì¹˜ì— ëˆ„ê°€ 50ë§Œì›ì´ë‚˜ ì£¼ë‹ˆ\nì •ë§ ê³ ë§™ë‹¤',_binary '\ßÿ¤=\ïBG¾\àlr·±½ı',_binary '','https://mermorit.s3.ap-northeast-2.amazonaws.com/3509566c-6174-4e0b-bbc2-3f0b064ec038202887_304005_3328.jpg',NULL,'ëŒì”ì¹˜',_binary '™\×ô\İU$LT¥#!i?@'),(98,'2023-10-05 16:48:36','2023-10-05 18:32:43',500000,'2023-08-18','ë‚´ ê²°í˜¼ì‹ì— ì°½ì„ì´ê°€ ì™€ì£¼ì–´ ë„ˆë¬´ ê³ ë§ˆì› ë‹¤',_binary '\ßÿ¤=\ïBG¾\àlr·±½ı',_binary '\0','https://mermorit.s3.ap-northeast-2.amazonaws.com/84b67acc-4b58-42d6-b72f-2239ee419cb3202305081411219062_d.jpg',NULL,' ê²°í˜¼ì‹',_binary '™\×ô\İU$LT¥#!i?@'),(99,'2023-10-05 16:49:44','2023-10-05 16:49:44',NULL,'2023-09-15','ì˜¤ëœ ì·¨ì¤€ ëì— ì·¨ë½€í•œ ë©‹ì§„ ì°½ì„ì´',_binary '\ßÿ¤=\ïBG¾\àlr·±½ı',_binary '\0',NULL,'ë©‹ì§„ ì •ì¥',' ì·¨ë½€',_binary '™\×ô\İU$LT¥#!i?@'),(100,'2023-10-05 16:50:33','2023-10-05 16:50:33',NULL,'2023-03-01','ì°½ì„ì•„ ì·¨ì¤€ìƒì´ë¼ ëˆë„ ì—†ì„í…ë°..\nì •ë§ ê³ ë§™ë‹¤..',_binary '\ßÿ¤=\ïBG¾\àlr·±½ı',_binary '',NULL,'ì ë‹¹íˆ ë©‹ì§„ ì •ì¥',' ì·¨ë½€',_binary '™\×ô\İU$LT¥#!i?@'),(101,'2023-10-05 16:51:51','2023-10-05 17:37:56',NULL,'2023-01-06','ì‹¸í”¼ì—ì„œ ì“°ë¼ê³  ë°›ì€ ì‚¼ì„±ë…¸íŠ¸ë¶',_binary '\ßÿ¤=\ïBG¾\àlr·±½ı',_binary '\0','https://mermorit.s3.ap-northeast-2.amazonaws.com/e30ae4d1-96f5-455a-a91c-5ad8a9283c4520190408-pr-odyssey-1.jpg','ë…¸íŠ¸ë¶',' ì‹¸í”¼ ì…ê³¼',_binary '™\×ô\İU$LT¥#!i?@'),(102,'2023-10-05 16:54:17','2023-10-05 16:54:17',NULL,'2023-09-01','ì°½ì„ì´ì˜ ê°•ì•„ì§€ê°€ í•˜ëŠ˜ë‚˜ë¼ë¡œ ê°”ë‹¤\ní˜ë“¤ ë•Œ ì˜†ì— ìˆì–´ì£¼ì§€ ëª»í•´ ë¯¸ì•ˆ',_binary '\ßÿ¤=\ïBG¾\àlr·±½ı',_binary '\0',NULL,'ì¶”ì–µì´ ë‹´ê¸´ ì•¨ë²”','ì¥ë¡€ì‹',_binary '™\×ô\İU$LT¥#!i?@'),(103,'2023-10-05 17:06:36','2023-10-05 17:06:36',100000,'2023-10-05','',_binary '‡š\0¶–N›¤o\éø%\Ã¸',_binary '\0',NULL,NULL,' ê²°í˜¼ì‹',_binary '™\×ô\İU$LT¥#!i?@'),(104,'2023-10-05 17:10:54','2023-10-05 17:10:54',100000,'2023-10-14','ì°½ì„ì´ì˜ ìƒì¼ì„ ë¬¼',_binary '[‹S&¼öG\ç•gö\0uJ<',_binary '',NULL,NULL,' ìƒì¼',_binary '™\×ô\İU$LT¥#!i?@'),(105,'2023-10-05 17:35:14','2023-10-05 17:35:14',1000000,'2023-10-11','ì°½ì„ì´ ìƒì¼ì„ ë¬¼',_binary '„”J8f@b¾\î\ã»\ÏX',_binary '',NULL,NULL,' ìƒì¼',_binary '™\×ô\İU$LT¥#!i?@');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historyType`
--

DROP TABLE IF EXISTS `historyType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historyType` (
  `type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(128) NOT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historyType`
--

LOCK TABLES `historyType` WRITE;
/*!40000 ALTER TABLE `historyType` DISABLE KEYS */;
INSERT INTO `historyType` VALUES (7,'ì‹¸íƒˆì‹',_binary '™\×ô\İU$LT¥#!i?@'),(8,'ã……ã„·ã…Œã……',_binary '™\×ô\İU$LT¥#!i?@'),(9,'ìŠ¹ì§„',_binary '™\×ô\İU$LT¥#!i?@'),(10,'ê²°í˜¼ì‹',_binary '™\×ô\İU$LT¥#!i?@'),(11,'ì…í•™ì‹',_binary '™\×ô\İU$LT¥#!i?@'),(12,' ìƒì¼',_binary '™\×ô\İU$LT¥#!i?@'),(13,'ëŒì”ì¹˜',_binary '™\×ô\İU$LT¥#!i?@'),(14,' ê²°í˜¼ì‹',_binary '™\×ô\İU$LT¥#!i?@'),(15,' ì·¨ë½€',_binary '™\×ô\İU$LT¥#!i?@'),(16,' ì‹¸í”¼ ì…ê³¼',_binary '™\×ô\İU$LT¥#!i?@'),(17,'ì¥ë¡€ì‹',_binary '™\×ô\İU$LT¥#!i?@');
/*!40000 ALTER TABLE `historyType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `providerId` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pl80uxdvpmu0d20yaancx337c` (`providerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` binary(16) NOT NULL,
  `nickname` varchar(16) DEFAULT NULL,
  `received_count` int(11) DEFAULT NULL,
  `sent_count` int(11) DEFAULT NULL,
  `received_money` int(11) DEFAULT NULL,
  `sent_money` int(11) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `provider_id` varchar(255) DEFAULT NULL,
  `withdrawal` bit(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercredential`
--

DROP TABLE IF EXISTS `usercredential`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usercredential` (
  `user_id` binary(16) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `refresh_token` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercredential`
--

LOCK TABLES `usercredential` WRITE;
/*!40000 ALTER TABLE `usercredential` DISABLE KEYS */;
/*!40000 ALTER TABLE `usercredential` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-06 11:07:14
