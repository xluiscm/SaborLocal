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
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventario` (
  `idIngrediente` int NOT NULL AUTO_INCREMENT,
  `ingrediente` varchar(255) NOT NULL,
  `tipo_uso` varchar(100) NOT NULL,
  `cantidad` varchar(50) NOT NULL,
  `imagen_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idIngrediente`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario`
--

LOCK TABLES `inventario` WRITE;
/*!40000 ALTER TABLE `inventario` DISABLE KEYS */;
INSERT INTO `inventario` VALUES (1,'Harina de trigo','Base de la masa','25',NULL),(2,'Azúcar','Base de la masa','10',NULL),(3,'Huevos','Base de la masa','40',NULL),(4,'Mantequilla o margarina','Base de la masa','29',NULL),(5,'Aceite vegetal','Base de la masa','50',NULL),(6,'Leche (normal, evaporada o condensada)','Base de la masa','20',NULL),(7,'Yogurt natural','Base de la masa','10',NULL),(8,'Polvo para hornear o bicarbonato de sodio','Saborizante de masa','17',NULL),(9,'Sal','Saborizante de masa','8',NULL),(10,'Agua','Saborizante de masa','16',NULL),(11,'Cacao en polvo','Saborizante de masa','8',NULL),(12,'Chocolate derretido','Saborizante de masa','6',NULL),(13,'Café soluble','Saborizante de masa','7',NULL),(14,'Colorantes alimentarios','Saborizante de masa','9',NULL),(15,'Licores (como ron, brandy o amaretto)','Saborizante de masa','12',NULL),(16,'Fresas','Fruta o conserva','8',NULL),(17,'Manzana','Fruta o conserva','4',NULL),(18,'Piña','Fruta o conserva','10',NULL),(19,'Plátano','Fruta o conserva','3',NULL),(20,'Durazno (en almíbar)','Fruta o conserva','2',NULL),(21,'Frambuesa','Fruta o conserva','5',NULL),(22,'Zarzamora','Fruta o conserva','6',NULL),(23,'Cereza (natural o en almíbar)','Fruta o conserva','1',NULL),(24,'Nuez','Fruto seco','8',NULL),(25,'Almendra','Fruto seco','1',NULL),(26,'Avellana','Fruto seco','1',NULL),(27,'Pistache','Fruto seco','1',NULL),(28,'Semillas de amapola','Semilla','2',NULL),(29,'Canela','Especia','8',NULL),(30,'Nuez moscada','Especia','4',NULL),(31,'Clavo de olor','Especia','1',NULL),(32,'Cardamomo','Especia','3',NULL),(33,'Extracto de vainilla','Saborizante','3',NULL),(34,'Ralladura de limón','Saborizante','5',NULL),(35,'Ralladura de naranja','Saborizante','5',NULL),(36,'Crema pastelera','Relleno/cobertura','5',NULL),(37,'Crema batida','Relleno/cobertura','4',NULL),(38,'Ganache de chocolate','Relleno/cobertura','1',NULL),(39,'Merengue','Relleno/cobertura','4',NULL),(40,'Fondant','Relleno/cobertura','1',NULL),(41,'Chantilly','Relleno/cobertura','10',NULL),(42,'Dulce de leche','Relleno/cobertura','3',NULL),(43,'Glaseado de mantequilla (buttercream)','Relleno/cobertura','3',NULL),(44,'Fécula de maíz','Proceso especial','6',NULL),(45,'Grenetina','Proceso especial','1',NULL),(46,'Jarabe de maíz','Proceso especial','8',NULL),(47,'Jarabe','Proceso especial','10',NULL);
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
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
