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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idCliente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidoPaterno` varchar(50) NOT NULL,
  `apellidoMaterno` varchar(50) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `genero` varchar(10) DEFAULT NULL,
  `fechaNacimiento` varchar(10) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `edad` int DEFAULT NULL,
  `idUsuario` int DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Luis Felipe','Cázarez','Márquez','Guerrero #197','Masculino','2002-08-16','764 103 9250',22,1),(4,'c','5656-++','98-++','56656 -++','Masculino','2332-12-12','44444',17,1),(6,'d','@@@@ _-   -+','@@ ______ 23232-+32','@@@___-+2324','Masculino','2343-12-21','222',17,1),(7,'a','@@@@ df;; \"\"\"#333','@@@@ _______-2323 q','@@@   ::: _   :121','Masculino','2345-12-12','2222',22,1),(8,'Lilianna','García','Hernández','Guerrero 197','Femenino','2004-03-21','7641034589',21,1);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallepedido`
--

DROP TABLE IF EXISTS `detallepedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallepedido` (
  `idDetallePedido` int NOT NULL AUTO_INCREMENT,
  `idPedido` int NOT NULL,
  `idPastel` int NOT NULL,
  `idTamanio` int NOT NULL,
  `precioUnitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idDetallePedido`),
  KEY `idPedido` (`idPedido`),
  KEY `idPastel` (`idPastel`),
  KEY `idTamanio` (`idTamanio`),
  CONSTRAINT `detallepedido_ibfk_1` FOREIGN KEY (`idPedido`) REFERENCES `pedidos` (`idPedido`),
  CONSTRAINT `detallepedido_ibfk_2` FOREIGN KEY (`idPastel`) REFERENCES `pasteles` (`idPastel`),
  CONSTRAINT `detallepedido_ibfk_3` FOREIGN KEY (`idTamanio`) REFERENCES `tamanios` (`idTamanio`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepedido`
--

LOCK TABLES `detallepedido` WRITE;
/*!40000 ALTER TABLE `detallepedido` DISABLE KEYS */;
INSERT INTO `detallepedido` VALUES (1,5,6,2,0.00),(2,6,8,3,0.00),(3,7,2,2,0.00),(4,8,6,3,0.00),(5,9,8,2,0.00),(6,10,4,2,0.00),(7,11,5,2,310.00),(8,12,8,1,285.00),(9,13,6,2,345.00),(10,14,4,1,290.00),(11,15,4,3,390.00),(12,16,1,3,420.00),(13,17,5,2,360.00),(14,18,3,1,280.00);
/*!40000 ALTER TABLE `detallepedido` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `inventario` VALUES (1,'Harina de trigo','Base de la masa','50 kg Disponibles',NULL),(2,'Azúcar','Base de la masa','10 kg Disponibles',NULL),(3,'Huevos','Base de la masa','40 kg Disponibles',NULL),(4,'Mantequilla o margarina','Base de la masa','29 kg Disponibles',NULL),(5,'Aceite vegetal','Base de la masa','50 L Disponibles',NULL),(6,'Leche (normal, evaporada o condensada)','Base de la masa','20 L Disponibles',NULL),(7,'Yogurt natural','Base de la masa','10 L Disponibles',NULL),(8,'Polvo para hornear o bicarbonato de sodio','Saborizante de masa','17 kg Disponibles',NULL),(9,'Sal','Saborizante de masa','8 kg Disponibles',NULL),(10,'Agua','Saborizante de masa','16 L Disponibles',NULL),(11,'Cacao en polvo','Saborizante de masa','8 kg Disponibles',NULL),(12,'Chocolate derretido','Saborizante de masa','6 kg Disponibles',NULL),(13,'Café soluble','Saborizante de masa','7 kg Disponibles',NULL),(14,'Colorantes alimentarios','Saborizante de masa','9 kg Disponibles',NULL),(15,'Licores (como ron, brandy o amaretto)','Saborizante de masa','12 L Disponibles',NULL),(16,'Fresas','Fruta o conserva','8 kg Disponibles',NULL),(17,'Manzana','Fruta o conserva','4 kg Disponibles',NULL),(18,'Piña','Fruta o conserva','10 kg Disponibles',NULL),(19,'Plátano','Fruta o conserva','3 kg Disponibles',NULL),(20,'Durazno (en almíbar)','Fruta o conserva','2 kg Disponibles',NULL),(21,'Frambuesa','Fruta o conserva','5 kg Disponibles',NULL),(22,'Zarzamora','Fruta o conserva','6 kg Disponibles',NULL),(23,'Cereza (natural o en almíbar)','Fruta o conserva','1.5 kg Disponibles',NULL),(24,'Nuez','Fruto seco','8 kg Disponibles',NULL),(25,'Almendra','Fruto seco','1/4 kg Disponibles',NULL),(26,'Avellana','Fruto seco','1/2 kg Disponibles',NULL),(27,'Pistache','Fruto seco','1/4 kg Disponibles',NULL),(28,'Semillas de amapola','Semilla','2.5 L Disponibles',NULL),(29,'Canela','Especia','8 kg Disponibles',NULL),(30,'Nuez moscada','Especia','4 kg Disponibles',NULL),(31,'Clavo de olor','Especia','1 kg Disponibles',NULL),(32,'Cardamomo','Especia','3 kg Disponibles',NULL),(33,'Extracto de vainilla','Saborizante','3.5 L Disponibles',NULL),(34,'Ralladura de limón','Saborizante','5 kg Disponibles',NULL),(35,'Ralladura de naranja','Saborizante','5 kg Disponibles',NULL),(36,'Crema pastelera','Relleno/cobertura','5 kg Disponibles',NULL),(37,'Crema batida','Relleno/cobertura','4 kg Disponibles',NULL),(38,'Ganache de chocolate','Relleno/cobertura','1 kg Disponibles',NULL),(39,'Merengue','Relleno/cobertura','4 kg Disponibles',NULL),(40,'Fondant','Relleno/cobertura','1 kg Disponibles',NULL),(41,'Chantilly','Relleno/cobertura','10 L Disponibles',NULL),(42,'Dulce de leche','Relleno/cobertura','3 kg Disponibles',NULL),(43,'Glaseado de mantequilla (buttercream)','Relleno/cobertura','3.5 kg Disponibles',NULL),(44,'Fécula de maíz','Proceso especial','6 kg Disponibles',NULL),(45,'Grenetina','Proceso especial','1 kg Disponibles',NULL),(46,'Jarabe de maíz','Proceso especial','8 Botellas Disponibles',NULL),(47,'Jarabe','Proceso especial','10 Botellas Disponibles',NULL);
/*!40000 ALTER TABLE `inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasteles`
--

DROP TABLE IF EXISTS `pasteles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pasteles` (
  `idPastel` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text,
  `precioBase` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idPastel`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasteles`
--

LOCK TABLES `pasteles` WRITE;
/*!40000 ALTER TABLE `pasteles` DISABLE KEYS */;
INSERT INTO `pasteles` VALUES (1,'Queso con Zarzamora','Pastel cremoso de queso con un toque agridulce de mermelada de zarzamora.',320.00),(2,'Napolitano','Pastel de tres sabores: vainilla, chocolate y fresa, con un glaseado combinado.',300.00),(3,'Chocolate','Clásico pastel de chocolate, ideal para los amantes del cacao intenso.',280.00),(4,'Durazno','Suave pastel de durazno, con trozos de fruta fresca y crema batida.',290.00),(5,'Moka','Pastel con un intenso sabor a café, cubierto con crema de moka y un decorado de chocolate.',310.00),(6,'Fresa','Pastel esponjoso de fresa, con relleno y cobertura de crema de fresas naturales.',295.00),(7,'Vainilla','El tradicional pastel de vainilla, simple pero delicioso, con un suave glaseado.',270.00),(8,'Griego Limon','Pastel ligero con sabor a yogur griego y un toque cítrico de limón.',285.00);
/*!40000 ALTER TABLE `pasteles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_inventario`
--

DROP TABLE IF EXISTS `pedido_inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_inventario` (
  `idPedidoInventario` int NOT NULL AUTO_INCREMENT,
  `IdPedido` int DEFAULT NULL,
  `idIngrediente` int NOT NULL,
  `cantidadUsada` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idPedidoInventario`),
  KEY `IdPedido` (`IdPedido`),
  KEY `idIngrediente` (`idIngrediente`),
  CONSTRAINT `pedido_inventario_ibfk_1` FOREIGN KEY (`IdPedido`) REFERENCES `pedido_personalizado` (`IdPedido`),
  CONSTRAINT `pedido_inventario_ibfk_2` FOREIGN KEY (`idIngrediente`) REFERENCES `inventario` (`idIngrediente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_inventario`
--

LOCK TABLES `pedido_inventario` WRITE;
/*!40000 ALTER TABLE `pedido_inventario` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_inventario` ENABLE KEYS */;
UNLOCK TABLES;

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
  `tamano` varchar(50) DEFAULT NULL,
  `decoracion` varchar(255) DEFAULT NULL,
  `ingredientes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IdPedido`),
  KEY `IdCliente` (`IdCliente`),
  CONSTRAINT `pedido_personalizado_ibfk_1` FOREIGN KEY (`IdCliente`) REFERENCES `clientes` (`idCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_personalizado`
--

LOCK TABLES `pedido_personalizado` WRITE;
/*!40000 ALTER TABLE `pedido_personalizado` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_personalizado` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (5,1,'','Pendiente',345.00,'aa','2025-08-16'),(6,1,'2025-08-14 23:56:18','Pendiente',385.00,'grg  rg r  w w','2025-08-16'),(7,1,'2025-08-14 23:57:05','Pendiente',350.00,'fef','2025-08-22'),(8,8,'2025-08-15 00:12:04','Pendiente',395.00,'aaaaa','2025-08-17'),(9,8,'2025-08-16 13:39:23','Pendiente',335.00,'a','2025-08-18'),(10,8,'2025-08-16 13:43:33','Pendiente',340.00,'s','2025-08-19'),(11,4,'2025-08-16 13:45:44','Pendiente',360.00,'a','2344-2-3'),(12,6,'2025-08-16 14:46:41','Pendiente',285.00,'aassasa','2025-08-19'),(13,4,'2025-08-16 19:09:44','Pendiente',345.00,'No se','2025-08-25'),(14,1,'2025-08-16 19:11:34','Pendiente',290.00,'A','2025-09-25'),(15,1,'2025-08-16 19:24:42','Pendiente',390.00,'aaaa','2025-08-19'),(16,8,'2025-08-16 19:30:20','Pendiente',420.00,'aaa','2025-08-16'),(17,8,'2025-08-16 19:32:21','Pendiente',360.00,'aaaa','2025-09-15'),(18,1,'2025-08-16 19:33:04','Pendiente',280.00,'aaaaaaaaaaaa','2025-09-25');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tamanios`
--

DROP TABLE IF EXISTS `tamanios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tamanios` (
  `idTamanio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `precioAdicional` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idTamanio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tamanios`
--

LOCK TABLES `tamanios` WRITE;
/*!40000 ALTER TABLE `tamanios` DISABLE KEYS */;
INSERT INTO `tamanios` VALUES (1,'Chico',0.00),(2,'Mediano',50.00),(3,'Grande',100.00);
/*!40000 ALTER TABLE `tamanios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nombreusuario` varchar(50) NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  `rolUsuario` varchar(20) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `nombreusuario` (`nombreusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Vendedor@saborlocal.com','SaborLocalV','Vendedor'),(2,'Pastelero@saborlocal.com','SaborLocalP','Pastelero');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-16 22:39:27
