CREATE DATABASE IF NOT EXISTS `sliat_system` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sliat_system`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: sliat_system
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `ad_status`
--

DROP TABLE IF EXISTS `ad_status`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ad_status`
(
    `id`     int NOT NULL AUTO_INCREMENT,
    `status` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ad_status`
--

LOCK TABLES `ad_status` WRITE;
/*!40000 ALTER TABLE `ad_status`
    DISABLE KEYS */;
INSERT INTO `ad_status`
VALUES (1, 'Active'),
       (2, 'Deactive');
/*!40000 ALTER TABLE `ad_status`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `name`        varchar(45) DEFAULT NULL,
    `number`      varchar(25) DEFAULT NULL,
    `sub_list_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_assignment_subject_list1_idx` (`sub_list_id`),
    CONSTRAINT `fk_assignment_subject_list1` FOREIGN KEY (`sub_list_id`) REFERENCES `subject_list` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `assignment`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment_result`
--

DROP TABLE IF EXISTS `assignment_result`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment_result`
(
    `id`            int NOT NULL AUTO_INCREMENT,
    `assignment_id` int NOT NULL,
    `student_id`    int NOT NULL,
    `marks`         varchar(10) DEFAULT NULL,
    `ears_id`       int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_assignment_result_student1_idx` (`student_id`),
    KEY `fk_assignment_result_assignment1_idx` (`assignment_id`),
    KEY `fk_assignment_result_ear_status1_idx` (`ears_id`),
    CONSTRAINT `fk_assignment_result_assignment1` FOREIGN KEY (`assignment_id`) REFERENCES `assignment` (`id`),
    CONSTRAINT `fk_assignment_result_ear_status1` FOREIGN KEY (`ears_id`) REFERENCES `ear_status` (`id`),
    CONSTRAINT `fk_assignment_result_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_result`
--

LOCK TABLES `assignment_result` WRITE;
/*!40000 ALTER TABLE `assignment_result`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `assignment_result`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch`
--

DROP TABLE IF EXISTS `batch`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `course_id`  int NOT NULL,
    `start_date` date        DEFAULT NULL,
    `number`     varchar(20) DEFAULT NULL,
    `bs_id`      int NOT NULL,
    `ads_id`     int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_batch_course1_idx` (`course_id`),
    KEY `fk_batch_batch_status1_idx` (`bs_id`),
    KEY `fk_batch_ad_status1_idx` (`ads_id`),
    CONSTRAINT `fk_batch_ad_status1` FOREIGN KEY (`ads_id`) REFERENCES `ad_status` (`id`),
    CONSTRAINT `fk_batch_batch_status1` FOREIGN KEY (`bs_id`) REFERENCES `batch_status` (`id`),
    CONSTRAINT `fk_batch_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch`
--

LOCK TABLES `batch` WRITE;
/*!40000 ALTER TABLE `batch`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `batch`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_list`
--

DROP TABLE IF EXISTS `batch_list`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_list`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `batch_id`   int NOT NULL,
    `student_id` int NOT NULL,
    `remark`     varchar(45) DEFAULT NULL,
    `ads_id`     int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_batch_list_batch1_idx` (`batch_id`),
    KEY `fk_batch_list_ad_status1_idx` (`ads_id`),
    KEY `fk_batch_list_student1_idx` (`student_id`),
    CONSTRAINT `fk_batch_list_ad_status1` FOREIGN KEY (`ads_id`) REFERENCES `ad_status` (`id`),
    CONSTRAINT `fk_batch_list_batch1` FOREIGN KEY (`batch_id`) REFERENCES `batch` (`id`),
    CONSTRAINT `fk_batch_list_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_list`
--

LOCK TABLES `batch_list` WRITE;
/*!40000 ALTER TABLE `batch_list`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `batch_list`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `batch_status`
--

DROP TABLE IF EXISTS `batch_status`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_status`
(
    `id`     int NOT NULL AUTO_INCREMENT,
    `status` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch_status`
--

LOCK TABLES `batch_status` WRITE;
/*!40000 ALTER TABLE `batch_status`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `batch_status`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `dep_id`         int NOT NULL,
    `type`           varchar(45) DEFAULT NULL,
    `level`          varchar(10) DEFAULT NULL,
    `duration_year`  varchar(10) DEFAULT NULL,
    `duration_month` varchar(10) DEFAULT NULL,
    `start_year`     varchar(10) DEFAULT NULL,
    `ads_id`         int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_course_department1_idx` (`dep_id`),
    KEY `fk_course_ad_status1_idx` (`ads_id`),
    CONSTRAINT `fk_course_ad_status1` FOREIGN KEY (`ads_id`) REFERENCES `ad_status` (`id`),
    CONSTRAINT `fk_course_department1` FOREIGN KEY (`dep_id`) REFERENCES `department` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `course`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department`
(
    `id`     int NOT NULL AUTO_INCREMENT,
    `name`   varchar(45) DEFAULT NULL,
    `ads_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_department_ad_status1_idx` (`ads_id`),
    CONSTRAINT `fk_department_ad_status1` FOREIGN KEY (`ads_id`) REFERENCES `ad_status` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `department`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ear_status`
--

DROP TABLE IF EXISTS `ear_status`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ear_status`
(
    `id`     int NOT NULL AUTO_INCREMENT,
    `status` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ear_status`
--

LOCK TABLES `ear_status` WRITE;
/*!40000 ALTER TABLE `ear_status`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `ear_status`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `name`        varchar(45) DEFAULT NULL,
    `number`      varchar(25) DEFAULT NULL,
    `sub_list_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_exam_subject_list1_idx` (`sub_list_id`),
    CONSTRAINT `fk_exam_subject_list1` FOREIGN KEY (`sub_list_id`) REFERENCES `subject_list` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `exam`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_result`
--

DROP TABLE IF EXISTS `exam_result`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam_result`
(
    `id`         int NOT NULL AUTO_INCREMENT,
    `exam_id`    int NOT NULL,
    `student_id` int NOT NULL,
    `grade`      varchar(10) DEFAULT NULL,
    `ears_id`    int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_exam_result_student1_idx` (`student_id`),
    KEY `fk_exam_result_exam1_idx` (`exam_id`),
    KEY `fk_exam_result_ear_status1_idx` (`ears_id`),
    CONSTRAINT `fk_exam_result_ear_status1` FOREIGN KEY (`ears_id`) REFERENCES `ear_status` (`id`),
    CONSTRAINT `fk_exam_result_exam1` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`),
    CONSTRAINT `fk_exam_result_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_result`
--

LOCK TABLES `exam_result` WRITE;
/*!40000 ALTER TABLE `exam_result`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_result`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `newview`
--

DROP TABLE IF EXISTS `newview`;
/*!50001 DROP VIEW IF EXISTS `newview`*/;
SET @saved_cs_client = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `newview` AS
SELECT 1 AS `id`,
       1 AS `nic_number`,
       1 AS `user_name`
*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester`
(
    `id`     int NOT NULL AUTO_INCREMENT,
    `number` varchar(20) DEFAULT NULL,
    `year`   varchar(10) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `semester`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `fname`          varchar(45)  DEFAULT NULL,
    `mname`          varchar(45)  DEFAULT NULL,
    `lname`          varchar(45)  DEFAULT NULL,
    `nic_number`     varchar(20)  DEFAULT NULL,
    `coctact_number` varchar(15)  DEFAULT NULL,
    `email`          varchar(45)  DEFAULT NULL,
    `address`        varchar(200) DEFAULT NULL,
    `gender`         varchar(10)  DEFAULT NULL,
    `ads_id`         int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_student_ad_status1_idx` (`ads_id`),
    CONSTRAINT `fk_student_ad_status1` FOREIGN KEY (`ads_id`) REFERENCES `ad_status` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `student`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject`
(
    `id`     int NOT NULL AUTO_INCREMENT,
    `name`   varchar(45) DEFAULT NULL,
    `number` varchar(10) DEFAULT NULL,
    `ads_id` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_subject_ad_status1_idx` (`ads_id`),
    CONSTRAINT `fk_subject_ad_status1` FOREIGN KEY (`ads_id`) REFERENCES `ad_status` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `subject`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_list`
--

DROP TABLE IF EXISTS `subject_list`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_list`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `course_id`   int NOT NULL,
    `semester_id` int NOT NULL,
    `subject_id`  int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_subject_list_subject1_idx` (`subject_id`),
    KEY `fk_subject_list_course1_idx` (`course_id`),
    KEY `fk_subject_list_semester1_idx` (`semester_id`),
    CONSTRAINT `fk_subject_list_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
    CONSTRAINT `fk_subject_list_semester1` FOREIGN KEY (`semester_id`) REFERENCES `semester` (`id`),
    CONSTRAINT `fk_subject_list_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_list`
--

LOCK TABLES `subject_list` WRITE;
/*!40000 ALTER TABLE `subject_list`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_list`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `full_name`      varchar(100) DEFAULT NULL,
    `nic_number`     varchar(15)  DEFAULT NULL,
    `contact_number` varchar(15)  DEFAULT NULL,
    `email`          varchar(100) DEFAULT NULL,
    `user_name`      varchar(45)  DEFAULT NULL,
    `password`       varchar(45)  DEFAULT NULL,
    `ut_id`          int NOT NULL,
    `ads_id`         int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_user_user_type_idx` (`ut_id`),
    KEY `fk_user_ad_status1_idx` (`ads_id`),
    CONSTRAINT `fk_user_ad_status1` FOREIGN KEY (`ads_id`) REFERENCES `ad_status` (`id`),
    CONSTRAINT `fk_user_user_type` FOREIGN KEY (`ut_id`) REFERENCES `user_type` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 'Buddhika Kumarasinghe', '943293007V', '0711998310', 'kbuddhika89@gmail.com', 'bambara', '9432', 1, 1);
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_type`
(
    `id`   int NOT NULL AUTO_INCREMENT,
    `type` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type`
    DISABLE KEYS */;
INSERT INTO `user_type`
VALUES (1, 'Super Admin'),
       (2, 'Admin'),
       (3, 'Guest');
/*!40000 ALTER TABLE `user_type`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sliat_system'
--

--
-- Dumping routines for database 'sliat_system'
--

--
-- Final view structure for view `newview`
--

/*!50001 DROP VIEW IF EXISTS `newview`*/;
/*!50001 SET @saved_cs_client = @@character_set_client */;
/*!50001 SET @saved_cs_results = @@character_set_results */;
/*!50001 SET @saved_col_connection = @@collation_connection */;
/*!50001 SET character_set_client = utf8mb4 */;
/*!50001 SET character_set_results = utf8mb4 */;
/*!50001 SET collation_connection = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM = UNDEFINED */ /*!50013 DEFINER =`root`@`localhost` SQL SECURITY DEFINER */ /*!50001 VIEW `newview` AS
select `user`.`id` AS `id`, `user`.`nic_number` AS `nic_number`, `user`.`user_name` AS `user_name`
from `user`
*/;
/*!50001 SET character_set_client = @saved_cs_client */;
/*!50001 SET character_set_results = @saved_cs_results */;
/*!50001 SET collation_connection = @saved_col_connection */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2020-10-19 14:35:41
