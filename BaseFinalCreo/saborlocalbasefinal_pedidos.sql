-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: saborlocalbasefinal
-- ------------------------------------------------------
-- Server version	9.4.0

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
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `idPedido` int NOT NULL AUTO_INCREMENT,
  `idCliente` int NOT NULL,
  `fechaPedido` varchar(50) DEFAULT NULL,
  `estado` varchar(50) NOT NULL DEFAULT 'Pendiente',
  `totalPedido` decimal(10,2) NOT NULL,
  `mensaje` varchar(255) DEFAULT NULL,
  `fechaEntregaEstimada` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `idCliente` (`idCliente`),
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (5,1,'','Pendiente',345.00,'aa','2025-08-16'),(6,1,'2025-08-14 23:56:18','Pendiente',385.00,'grg  rg r  w w','2025-08-16'),(7,1,'2025-08-14 23:57:05','Pendiente',350.00,'fef','2025-08-22'),(8,8,'2025-08-15 00:12:04','Pendiente',395.00,'aaaaa','2025-08-17'),(9,8,'2025-08-16 13:39:23','Pendiente',335.00,'a','2025-08-18'),(10,8,'2025-08-16 13:43:33','Pendiente',340.00,'s','2025-08-19'),(11,4,'2025-08-16 13:45:44','Pendiente',360.00,'a','2344-2-3'),(12,6,'2025-08-16 14:46:41','Pendiente',285.00,'aassasa','2025-08-19'),(13,4,'2025-08-16 19:09:44','Pendiente',345.00,'No se','2025-08-25'),(14,1,'2025-08-16 19:11:34','Pendiente',290.00,'A','2025-09-25'),(15,1,'2025-08-16 19:24:42','Pendiente',390.00,'aaaa','2025-08-19'),(16,8,'2025-08-16 19:30:20','Pendiente',420.00,'aaa','2025-08-16'),(17,8,'2025-08-16 19:32:21','Pendiente',360.00,'aaaa','2025-09-15'),(18,1,'2025-08-16 19:33:04','Pendiente',280.00,'aaaaaaaaaaaa','2025-09-25'),(19,1,'2025-08-17 14:44:22','Pendiente',280.00,'ppp','2025-08-19'),(20,8,'2025-08-17 18:23:07','Pendiente',340.00,'aaaaaaaaaaaaaaa','2025-09-28'),(21,6,'2025-08-18 22:34:47','Pendiente',395.00,'no se','2025-09-14'),(22,4,'2025-08-18 22:51:20','Pendiente',395.00,'aaaaaaaaaaaaa','2025-08-19');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-19  0:34:18
