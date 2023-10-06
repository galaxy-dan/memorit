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
INSERT INTO `category` VALUES (9,'없음',_binary '�\��\�U$LT�#!i?@'),(10,'싸피',_binary '�\��\�U$LT�#!i?@'),(11,'절친',_binary '�\��\�U$LT�#!i?@');
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
INSERT INTO `friend` VALUES (_binary '�\��\�U$LT�#!i?@',_binary '��\0��N��o\��%\��','윤석준',NULL,NULL,0,1,0,100000,'2023-10-05','2023-10-05 14:59:28','2023-10-05 17:06:36'),(_binary '�\��\�U$LT�#!i?@',_binary '\���=\�BG�\�lr����','묵창석',NULL,'싸피',3,4,500000,600000,'2023-10-06','2023-10-05 16:40:09','2023-10-05 17:37:27'),(_binary '�\��\�U$LT�#!i?@',_binary '0Ff7\��B˜�Ȃv\�hm','김창석',NULL,NULL,0,0,0,0,NULL,'2023-10-05 16:39:54','2023-10-05 16:39:54'),(_binary '�\��\�U$LT�#!i?@',_binary '8\�|\�{O���W\�;D��','이찬민',NULL,'싸피',1,0,30000,0,'2023-10-05','2023-10-05 08:01:20','2023-10-05 17:32:34'),(_binary '�\��\�U$LT�#!i?@',_binary 'K\�&Sr\�Dp�)�+�7\�','제갈창석',NULL,'절친',0,0,0,0,NULL,'2023-10-05 16:40:09','2023-10-05 16:46:19'),(_binary '�\��\�U$LT�#!i?@',_binary '[�S&��G\�g��\0uJ<','변창석',NULL,NULL,1,0,100000,0,'2023-10-14','2023-10-05 17:10:37','2023-10-05 17:10:54'),(_binary '�\��\�U$LT�#!i?@',_binary 'jp�+\"N׶9w\��','송수현',NULL,NULL,0,0,0,0,NULL,'2023-10-05 14:59:28','2023-10-05 14:59:28'),(_binary '�\��\�U$LT�#!i?@',_binary 'o�!\�=VBS��hs\�R2','최진우',NULL,NULL,0,0,0,0,NULL,'2023-10-05 14:59:28','2023-10-05 14:59:28'),(_binary '�\��\�U$LT�#!i?@',_binary 'p:�M�+E7�sK I��','박창석',NULL,NULL,0,0,0,0,NULL,'2023-10-05 16:39:54','2023-10-05 16:39:54'),(_binary '�\��\�U$LT�#!i?@',_binary 's:\�0,xL\n�q^\�\r\\','김창근',NULL,'절친',0,0,0,0,NULL,'2023-10-05 17:36:05','2023-10-05 17:36:05'),(_binary '�\��\�U$LT�#!i?@',_binary 'zUR �rKl�brm\�o�','송창석',NULL,'싸피',0,0,0,0,NULL,'2023-10-05 17:11:39','2023-10-05 17:11:39'),(_binary '�\��\�U$LT�#!i?@',_binary '���J8f@�b�\�\�\�X','맹창석',NULL,NULL,1,0,1000000,0,'2023-10-11','2023-10-05 17:34:58','2023-10-05 17:35:14'),(_binary '�\��\�U$LT�#!i?@',_binary '��د�hO2�\�Xx>�','전준규',NULL,'싸피',0,0,0,0,NULL,'2023-10-05 08:02:35','2023-10-05 08:02:35'),(_binary '�\��\�U$LT�#!i?@',_binary '�$��VYAR�\�\'\�p\nB','박재훈',NULL,'없음',0,0,0,0,NULL,'2023-10-05 08:02:02','2023-10-05 08:02:02'),(_binary '�\��\�U$LT�#!i?@',_binary '\�\���a(E��=�+��W','오창석',NULL,NULL,0,0,0,0,NULL,'2023-10-05 16:39:54','2023-10-05 16:39:54');
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
INSERT INTO `history` VALUES (93,'2023-10-05 13:55:46','2023-10-05 13:55:46',30000,'2023-10-05','',_binary '8\�|\�{O���W\�;D��',_binary '',NULL,NULL,'승진',_binary '�\��\�U$LT�#!i?@'),(94,'2023-10-05 16:41:15','2023-10-05 16:55:27',500000,'2023-10-06','창석이의 결혼식\n',_binary '\���=\�BG�\�lr����',_binary '\0','https://mermorit.s3.ap-northeast-2.amazonaws.com/d8d71f61-6af1-4c70-b36e-4a701fd0f424202305081411219062_d.jpg',NULL,'결혼식',_binary '�\��\�U$LT�#!i?@'),(97,'2023-10-05 16:47:35','2023-10-05 16:47:35',500000,'2022-07-01','돌잔치에 누가 50만원이나 주니\n정말 고맙다',_binary '\���=\�BG�\�lr����',_binary '','https://mermorit.s3.ap-northeast-2.amazonaws.com/3509566c-6174-4e0b-bbc2-3f0b064ec038202887_304005_3328.jpg',NULL,'돌잔치',_binary '�\��\�U$LT�#!i?@'),(98,'2023-10-05 16:48:36','2023-10-05 18:32:43',500000,'2023-08-18','내 결혼식에 창석이가 와주어 너무 고마웠다',_binary '\���=\�BG�\�lr����',_binary '\0','https://mermorit.s3.ap-northeast-2.amazonaws.com/84b67acc-4b58-42d6-b72f-2239ee419cb3202305081411219062_d.jpg',NULL,' 결혼식',_binary '�\��\�U$LT�#!i?@'),(99,'2023-10-05 16:49:44','2023-10-05 16:49:44',NULL,'2023-09-15','오랜 취준 끝에 취뽀한 멋진 창석이',_binary '\���=\�BG�\�lr����',_binary '\0',NULL,'멋진 정장',' 취뽀',_binary '�\��\�U$LT�#!i?@'),(100,'2023-10-05 16:50:33','2023-10-05 16:50:33',NULL,'2023-03-01','창석아 취준생이라 돈도 없을텐데..\n정말 고맙다..',_binary '\���=\�BG�\�lr����',_binary '',NULL,'적당히 멋진 정장',' 취뽀',_binary '�\��\�U$LT�#!i?@'),(101,'2023-10-05 16:51:51','2023-10-05 17:37:56',NULL,'2023-01-06','싸피에서 쓰라고 받은 삼성노트북',_binary '\���=\�BG�\�lr����',_binary '\0','https://mermorit.s3.ap-northeast-2.amazonaws.com/e30ae4d1-96f5-455a-a91c-5ad8a9283c4520190408-pr-odyssey-1.jpg','노트북',' 싸피 입과',_binary '�\��\�U$LT�#!i?@'),(102,'2023-10-05 16:54:17','2023-10-05 16:54:17',NULL,'2023-09-01','창석이의 강아지가 하늘나라로 갔다\n힘들 때 옆에 있어주지 못해 미안',_binary '\���=\�BG�\�lr����',_binary '\0',NULL,'추억이 담긴 앨범','장례식',_binary '�\��\�U$LT�#!i?@'),(103,'2023-10-05 17:06:36','2023-10-05 17:06:36',100000,'2023-10-05','',_binary '��\0��N��o\��%\��',_binary '\0',NULL,NULL,' 결혼식',_binary '�\��\�U$LT�#!i?@'),(104,'2023-10-05 17:10:54','2023-10-05 17:10:54',100000,'2023-10-14','창석이의 생일선물',_binary '[�S&��G\�g��\0uJ<',_binary '',NULL,NULL,' 생일',_binary '�\��\�U$LT�#!i?@'),(105,'2023-10-05 17:35:14','2023-10-05 17:35:14',1000000,'2023-10-11','창석이 생일선물',_binary '���J8f@�b�\�\�\�X',_binary '',NULL,NULL,' 생일',_binary '�\��\�U$LT�#!i?@');
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
INSERT INTO `historyType` VALUES (7,'싸탈식',_binary '�\��\�U$LT�#!i?@'),(8,'ㅅㄷㅌㅅ',_binary '�\��\�U$LT�#!i?@'),(9,'승진',_binary '�\��\�U$LT�#!i?@'),(10,'결혼식',_binary '�\��\�U$LT�#!i?@'),(11,'입학식',_binary '�\��\�U$LT�#!i?@'),(12,' 생일',_binary '�\��\�U$LT�#!i?@'),(13,'돌잔치',_binary '�\��\�U$LT�#!i?@'),(14,' 결혼식',_binary '�\��\�U$LT�#!i?@'),(15,' 취뽀',_binary '�\��\�U$LT�#!i?@'),(16,' 싸피 입과',_binary '�\��\�U$LT�#!i?@'),(17,'장례식',_binary '�\��\�U$LT�#!i?@');
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
