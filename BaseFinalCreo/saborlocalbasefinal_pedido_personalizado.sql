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
-- Table structure for table `pedido_personalizado`
--

DROP TABLE IF EXISTS `pedido_personalizado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_personalizado` (
  `IdPedido` int NOT NULL AUTO_INCREMENT,
  `IdCliente` int NOT NULL,
  `ocasion` varchar(50) DEFAULT NULL,
  `tipo_pan` varchar(50) DEFAULT NULL,
  `sabor` varchar(50) DEFAULT NULL,
  `cubierta` varchar(50) DEFAULT NULL,
  `forma` varchar(50) DEFAULT NULL,
  `tamanio` varchar(50) DEFAULT NULL,
  `decoracion` varchar(255) DEFAULT NULL,
  `ingredientes` varchar(255) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdPedido`),
  KEY `IdCliente` (`IdCliente`),
  CONSTRAINT `pedido_personalizado_ibfk_1` FOREIGN KEY (`IdCliente`) REFERENCES `clientes` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_personalizado`
--

LOCK TABLES `pedido_personalizado` WRITE;
/*!40000 ALTER TABLE `pedido_personalizado` DISABLE KEYS */;
INSERT INTO `pedido_personalizado` VALUES (7,1,'Cumpleaños','Vainilla','Chocolate','Crema Batida','Redondo','1.0','Globos','',NULL),(8,1,'Cumpleaños','Vainilla','Chocolate','Crema Batida','Redondo','1.0','Globos','',NULL),(9,1,'Cumpleaños','Chocolate','Chocolate','Crema Batida','Redondo','2.0','Globos','',NULL),(16,1,'Cumpleaños','Vainilla','Chocolate','Crema Batida','Redondo','1.0','Globos','',NULL),(17,1,'Cumpleaños','Vainilla','Chocolate','Fondant','Redondo','1.0','Globos','',NULL),(18,1,'Cumpleaños','Chocolate','Chocolate','Crema Batida','Redondo','1.0','Corazones','',NULL),(19,8,'Aniversario','Red Velvet','Queso Crema','Fondant','Cuadrado','5.0','Corazones','',NULL);
/*!40000 ALTER TABLE `pedido_personalizado` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-19  0:34:16
