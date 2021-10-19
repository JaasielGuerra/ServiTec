-- MySQL dump 10.17  Distrib 10.3.25-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: db_servitec
-- ------------------------------------------------------
-- Server version	10.3.25-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `caja`
--

LOCK TABLES `caja` WRITE;
/*!40000 ALTER TABLE `caja` DISABLE KEYS */;
INSERT INTO `caja` VALUES (1,'demo caja',1,1),(2,'demo',1,1),(3,'demo',1,1),(4,'demo caja',1,1),(5,'demo caja',1,1),(6,'demo caja',1,1),(7,'demo caja',1,1),(8,'demo caja',1,1),(9,'demo caja',1,1),(10,'demo caja',1,1),(11,'demo caja',1,1),(12,'demo caja',1,1),(13,'demo caja',1,1);
/*!40000 ALTER TABLE `caja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `categoria_servicio`
--

LOCK TABLES `categoria_servicio` WRITE;
/*!40000 ALTER TABLE `categoria_servicio` DISABLE KEYS */;
INSERT INTO `categoria_servicio` VALUES (1,'Categoria actualiza',0),(2,'Categoria demo',0),(3,'Categoria actualiza',0),(4,'Categoria demo',0),(5,'Categoria demo',0),(6,'Categoria demo',1),(7,'Categoria demo',1),(8,'Categoria actualizadaaa',1),(9,'Categoria actualizadaaa',1),(10,'Categoria actualizadaaa',1),(11,'Categoria actualizadaaa',1),(12,'Categoria actualiza',1),(13,'Categoria actualiza',1),(14,'Categoria actualiza',1),(15,'Categoria actualiza',1),(16,'Categoria actualiza',1),(17,'Categoria actualiza',1),(18,'angular 1',1),(19,'a',1),(20,'ASAA',1),(21,'DEMO',1),(22,'ASD',1),(23,'demo',1),(24,'asd',1),(25,'asd',1),(26,'ASD',1),(27,'A',1),(28,'A',1),(29,'a',1),(30,'a',1),(31,'a',1),(32,'as',1),(33,'a',1),(34,'a',1),(35,'aa',1),(36,'aa',1),(37,'as',1),(38,'as',1),(39,'demo',1),(40,'demo',1),(41,'demo',1),(42,'Categoria demo',1),(43,'Categoria demo',1);
/*!40000 ALTER TABLE `categoria_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'jg17','jaasiel','124343',NULL,'demo',1,'2021-10-18','23:49:53',1),(2,'jg18','jaasiel','124343',NULL,'demo',1,'2021-10-19','00:21:56',1),(3,'jg19','jaasiel','124343',NULL,'demo',1,'2021-10-19','00:22:02',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estado_orden`
--

LOCK TABLES `estado_orden` WRITE;
/*!40000 ALTER TABLE `estado_orden` DISABLE KEYS */;
INSERT INTO `estado_orden` VALUES (1,'Reservada',1),(2,'Atendiendo',1),(3,'Pendiente',1),(4,'Finalizada',1),(5,'Cobrada y entregada',1),(6,'Solo entregada',1);
/*!40000 ALTER TABLE `estado_orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estante`
--

LOCK TABLES `estante` WRITE;
/*!40000 ALTER TABLE `estante` DISABLE KEYS */;
INSERT INTO `estante` VALUES (1,'demo ubicacin',2,1),(2,'demo ubicacin',2,1),(3,'demo ubicacin',2,1);
/*!40000 ALTER TABLE `estante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `fotos_diagnostico`
--

LOCK TABLES `fotos_diagnostico` WRITE;
/*!40000 ALTER TABLE `fotos_diagnostico` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotos_diagnostico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `inventario_repuesto`
--

LOCK TABLES `inventario_repuesto` WRITE;
/*!40000 ALTER TABLE `inventario_repuesto` DISABLE KEYS */;
INSERT INTO `inventario_repuesto` VALUES (1,'resitencia ceramica',10,50,1,'2021-10-18','1970-01-01',2,2,4,1);
/*!40000 ALTER TABLE `inventario_repuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `motivo_orden`
--

LOCK TABLES `motivo_orden` WRITE;
/*!40000 ALTER TABLE `motivo_orden` DISABLE KEYS */;
INSERT INTO `motivo_orden` VALUES (1,'demo',1);
/*!40000 ALTER TABLE `motivo_orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `orden`
--

LOCK TABLES `orden` WRITE;
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prioridad`
--

LOCK TABLES `prioridad` WRITE;
/*!40000 ALTER TABLE `prioridad` DISABLE KEYS */;
INSERT INTO `prioridad` VALUES (1,'Poco urgente',1),(2,'Medio urgente',1),(3,'Muy urgente',1);
/*!40000 ALTER TABLE `prioridad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `repuesto_empleado`
--

LOCK TABLES `repuesto_empleado` WRITE;
/*!40000 ALTER TABLE `repuesto_empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `repuesto_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'SUPER ADMIN',1,1,1,1,1,1,1,1,1,1);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,'servicio editado por jaasiel',100,200,300,0,'2021-09-29','18:00:00',1,1),(2,'Servicio demo 2',100,200,250,0,'2021-09-29','18:00:00',1,1),(3,'demo 3',100,200,300,1,'2021-09-30','18:00:00',1,1),(4,'demo 3',100,200,300,1,'2021-09-30','12:08:04',1,1),(5,'demo prueba fecha',100,200,300,1,'2021-09-30','12:08:29',1,1),(6,'demo 3',100,200,300,1,'2021-09-30','12:10:24',1,1),(7,'demo 3',100,200,300,1,'2021-09-30','12:10:32',1,1),(8,'demo 3',100,200,300,1,'2021-09-30','12:10:33',1,1),(9,'demo 7',100,200,300,1,'2021-09-30','12:11:16',1,1),(10,'demo 7',100,200,300,1,'2021-09-30','12:12:14',1,1),(11,'demo 7',100,200,300,1,'2021-09-30','12:12:18',1,1),(12,'demo 7',100,200,300,1,'2021-09-30','12:23:14',1,1),(13,'demo 7',100,200,300,1,'2021-09-30','12:23:21',1,1),(16,'demo 7',100,200,300,0,'2021-09-30','12:25:18',1,1),(17,'demo 3',100,200,300,1,'2021-09-30','12:41:44',1,1),(18,'demo 3',100,200,300,1,'2021-09-30','12:42:05',1,1),(19,'demo 3',100,200,300,1,'2021-09-30','12:43:31',1,1),(20,'demo 3',100,200,300,1,'2021-09-30','12:43:34',1,1),(21,'demo 3',100,200,300,1,'2021-09-30','12:43:40',5,1),(22,'demo 3',100,200,300,1,'2021-09-30','12:47:35',3,1),(23,'demo 3',100,200,300,1,'2021-09-30','12:47:37',3,1),(24,'demo 3',100,200,300,1,'2021-09-30','12:47:50',3,1),(25,'demo',1,2,3,1,'2021-10-06','00:17:49',3,1),(26,'angular',12,10,12,1,'2021-10-06','00:18:06',3,1),(27,'servicios demo',30,12,434,1,'2021-10-06','22:15:23',9,1),(28,'24',23,23,23,1,'2021-10-06','22:22:19',6,1),(29,'we',1,1,1,1,'2021-10-06','22:56:21',6,1);
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `servicio_aplicado`
--

LOCK TABLES `servicio_aplicado` WRITE;
/*!40000 ALTER TABLE `servicio_aplicado` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicio_aplicado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `servicio_servicio_aplicados`
--

LOCK TABLES `servicio_servicio_aplicados` WRITE;
/*!40000 ALTER TABLE `servicio_servicio_aplicados` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicio_servicio_aplicados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tecnico`
--

LOCK TABLES `tecnico` WRITE;
/*!40000 ALTER TABLE `tecnico` DISABLE KEYS */;
INSERT INTO `tecnico` VALUES (1,'Tecnico de prueba',1,'2021-10-15','22:50:50',1);
/*!40000 ALTER TABLE `tecnico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ubicacion`
--

LOCK TABLES `ubicacion` WRITE;
/*!40000 ALTER TABLE `ubicacion` DISABLE KEYS */;
INSERT INTO `ubicacion` VALUES (1,'demo ubicacin',1),(2,'demo ubicacin',1),(3,'demo ubicacin',1);
/*!40000 ALTER TABLE `ubicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Jaasiel Guerra','jaasiel17','123',1,'2021-09-29','12:00:00',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-19  0:25:14
