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
-- Table structure for table `catalogos`
--

DROP TABLE IF EXISTS `catalogos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalogos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo_pan` varchar(50) DEFAULT NULL,
  `sabor` varchar(50) DEFAULT NULL,
  `cubierta` varchar(50) DEFAULT NULL,
  `forma` varchar(50) DEFAULT NULL,
  `ocasion` varchar(50) DEFAULT NULL,
  `tamanio` varchar(50) DEFAULT NULL,
  `decoracion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogos`
--

LOCK TABLES `catalogos` WRITE;
/*!40000 ALTER TABLE `catalogos` DISABLE KEYS */;
INSERT INTO `catalogos` VALUES (1,'Vainilla','Chocolate','Crema Batida','Redondo','Cumpleaños','1 kg','Globos'),(2,'Chocolate','Vainilla','Fondant','Cuadrado','Aniversario','2 kg','Corazones'),(3,'Red Velvet','Queso Crema','Merengue','Dos pisos','Boda','5 kg','Flores');
/*!40000 ALTER TABLE `catalogos` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Luis Felipe','Cázarez','Márquez','Guerrero 197','Masculino','2002-08-16','7641039250',22,1);
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
  KEY `idPastel` (`idPastel`),
  KEY `idTamanio` (`idTamanio`),
  KEY `fk_detallepedido_pedidos` (`idPedido`),
  CONSTRAINT `detallepedido_ibfk_2` FOREIGN KEY (`idPastel`) REFERENCES `pasteles` (`idPastel`),
  CONSTRAINT `detallepedido_ibfk_3` FOREIGN KEY (`idTamanio`) REFERENCES `tamanios` (`idTamanio`),
  CONSTRAINT `fk_detallepedido_pedidos` FOREIGN KEY (`idPedido`) REFERENCES `pedidos` (`idPedido`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepedido`
--

LOCK TABLES `detallepedido` WRITE;
/*!40000 ALTER TABLE `detallepedido` DISABLE KEYS */;
INSERT INTO `detallepedido` VALUES (2,2,5,3,410.00),(3,3,3,2,280.00);
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
INSERT INTO `inventario` VALUES (1,'Harina de trigo','Base de la masa','24',NULL),(2,'Azúcar','Base de la masa','10',NULL),(3,'Huevos','Base de la masa','30',NULL),(4,'Mantequilla o margarina','Base de la masa','29',NULL),(5,'Aceite vegetal','Base de la masa','50',NULL),(6,'Leche (normal, evaporada o condensada)','Base de la masa','20',NULL),(7,'Yogurt natural','Base de la masa','8',NULL),(8,'Polvo para hornear o bicarbonato de sodio','Saborizante de masa','17',NULL),(9,'Sal','Saborizante de masa','8',NULL),(10,'Agua','Saborizante de masa','16',NULL),(11,'Cacao en polvo','Saborizante de masa','8',NULL),(12,'Chocolate derretido','Saborizante de masa','6',NULL),(13,'Café soluble','Saborizante de masa','7',NULL),(14,'Colorantes alimentarios','Saborizante de masa','9',NULL),(15,'Licores (como ron, brandy o amaretto)','Saborizante de masa','12',NULL),(16,'Fresas','Fruta o conserva','4',NULL),(17,'Manzana','Fruta o conserva','4',NULL),(18,'Piña','Fruta o conserva','5',NULL),(19,'Plátano','Fruta o conserva','3',NULL),(20,'Durazno (en almíbar)','Fruta o conserva','2',NULL),(21,'Frambuesa','Fruta o conserva','5',NULL),(22,'Zarzamora','Fruta o conserva','6',NULL),(23,'Cereza (natural o en almíbar)','Fruta o conserva','1',NULL),(24,'Nuez','Fruto seco','8',NULL),(25,'Almendra','Fruto seco','1',NULL),(26,'Avellana','Fruto seco','1',NULL),(27,'Pistache','Fruto seco','1',NULL),(28,'Semillas de amapola','Semilla','2',NULL),(29,'Canela','Especia','8',NULL),(30,'Nuez moscada','Especia','4',NULL),(31,'Clavo de olor','Especia','1',NULL),(32,'Cardamomo','Especia','3',NULL),(33,'Extracto de vainilla','Saborizante','3',NULL),(34,'Ralladura de limón','Saborizante','5',NULL),(35,'Ralladura de naranja','Saborizante','5',NULL),(36,'Crema pastelera','Relleno/cobertura','2',NULL),(37,'Crema batida','Relleno/cobertura','4',NULL),(38,'Ganache de chocolate','Relleno/cobertura','1',NULL),(39,'Merengue','Relleno/cobertura','4',NULL),(40,'Fondant','Relleno/cobertura','1',NULL),(41,'Chantilly','Relleno/cobertura','10',NULL),(42,'Dulce de leche','Relleno/cobertura','3',NULL),(43,'Glaseado de mantequilla (buttercream)','Relleno/cobertura','3',NULL),(44,'Fécula de maíz','Proceso especial','6',NULL),(45,'Grenetina','Proceso especial','1',NULL),(46,'Jarabe de maíz','Proceso especial','8',NULL),(47,'Jarabe','Proceso especial','10',NULL);
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
  `tamanio` varchar(50) DEFAULT NULL,
  `decoracion` varchar(255) DEFAULT NULL,
  `ingredientes` varchar(255) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`IdPedido`),
  KEY `IdCliente` (`IdCliente`),
  CONSTRAINT `pedido_personalizado_ibfk_1` FOREIGN KEY (`IdCliente`) REFERENCES `clientes` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_personalizado`
--

LOCK TABLES `pedido_personalizado` WRITE;
/*!40000 ALTER TABLE `pedido_personalizado` DISABLE KEYS */;
INSERT INTO `pedido_personalizado` VALUES (5,1,'Cumpleaños','Vainilla','Chocolate','Crema Batida','Redondo','1.0','Globos','Harina de trigo - 1\n','En proceso');
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
  KEY `fk_pedidos_clientes` (`idCliente`),
  CONSTRAINT `fk_pedidos_clientes` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`) ON DELETE CASCADE,
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`idCliente`) REFERENCES `clientes` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (2,1,'2025-08-19 19:52:21','Proceso',410.00,'Pumpeaños','2025-08-19'),(3,1,'2025-08-19 20:39:04','Pendiente',330.00,'Si se','2025-08-25');
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

-- Dump completed on 2025-08-19 21:04:56
