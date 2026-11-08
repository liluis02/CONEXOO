
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: conexoo
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `candidatos`
--

DROP TABLE IF EXISTS `candidatos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidatos` (
  `id_candidato` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `cidade` varchar(100) DEFAULT NULL,
  `estado` char(2) DEFAULT NULL,
  `descricao` text,
  `cargo_desejado` varchar(150) DEFAULT NULL,
  `nivel_profissional` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_candidato`),
  UNIQUE KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `candidatos_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidatos`
--

LOCK TABLES `candidatos` WRITE;
/*!40000 ALTER TABLE `candidatos` DISABLE KEYS */;
INSERT INTO `candidatos` VALUES (1,11,'09876543211','31999184138','Martinho campos','BA','Perfeito em td, sou foda','Programador','Júnior'),(3,12,'91927197917','32999617612','Belo Horizonte','RJ','Sou foda em limpar o chão','Faxineiro','Sênior');
/*!40000 ALTER TABLE `candidatos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `experiencias`
--

DROP TABLE IF EXISTS `experiencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `experiencias` (
  `id_experiencia` int NOT NULL AUTO_INCREMENT,
  `id_candidato` int NOT NULL,
  `nome_empresa` varchar(150) NOT NULL,
  `cargo` varchar(150) NOT NULL,
  `descricao` text,
  `data_contratacao` date NOT NULL,
  `data_demissao` date DEFAULT NULL,
  PRIMARY KEY (`id_experiencia`),
  KEY `id_candidato` (`id_candidato`),
  CONSTRAINT `experiencias_ibfk_1` FOREIGN KEY (`id_candidato`) REFERENCES `candidatos` (`id_candidato`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `experiencias`
--

LOCK TABLES `experiencias` WRITE;
/*!40000 ALTER TABLE `experiencias` DISABLE KEYS */;
INSERT INTO `experiencias` VALUES (4,3,'Apple','Faxineiro premium da apple','Mt legal e foda, muitos chãos limpos','2000-01-01','2010-02-02'),(5,1,'Apple','Desenvolvedor','Fui tão foda que me demitiram para que eu pudesse mostrar minhas habilidades superiores em outra empresa','2018-04-02','2026-02-04');
/*!40000 ALTER TABLE `experiencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habilidades`
--

DROP TABLE IF EXISTS `habilidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habilidades` (
  `id_habilidade` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `categoria` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_habilidade`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habilidades`
--

LOCK TABLES `habilidades` WRITE;
/*!40000 ALTER TABLE `habilidades` DISABLE KEYS */;
INSERT INTO `habilidades` VALUES (1,'Java','Linguagem de Programação'),(2,'Python','Linguagem de Programação'),(3,'JavaScript','Linguagem de Programação'),(4,'C++','Linguagem de Programação'),(5,'C#','Linguagem de Programação'),(6,'SQL','Banco de Dados'),(7,'MySQL','Banco de Dados'),(8,'HTML','Desenvolvimento Web'),(9,'CSS','Desenvolvimento Web'),(10,'React','Framework/Biblioteca'),(11,'Spring Boot','Framework/Biblioteca'),(12,'Git','Controle de Versão'),(13,'Docker','DevOps'),(14,'AWS','Computação em Nuvem');
/*!40000 ALTER TABLE `habilidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habilidades_candidato`
--

DROP TABLE IF EXISTS `habilidades_candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habilidades_candidato` (
  `id_candidato` int NOT NULL,
  `id_habilidade` int NOT NULL,
  `nivel` varchar(50) NOT NULL,
  PRIMARY KEY (`id_candidato`,`id_habilidade`),
  KEY `id_habilidade` (`id_habilidade`),
  CONSTRAINT `habilidades_candidato_ibfk_1` FOREIGN KEY (`id_candidato`) REFERENCES `candidatos` (`id_candidato`) ON DELETE CASCADE,
  CONSTRAINT `habilidades_candidato_ibfk_2` FOREIGN KEY (`id_habilidade`) REFERENCES `habilidades` (`id_habilidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habilidades_candidato`
--

LOCK TABLES `habilidades_candidato` WRITE;
/*!40000 ALTER TABLE `habilidades_candidato` DISABLE KEYS */;
INSERT INTO `habilidades_candidato` VALUES (1,1,'Básico'),(1,4,'Avançado'),(1,7,'Avançado'),(3,2,'Básico'),(3,3,'Intermediário'),(3,4,'Avançado');
/*!40000 ALTER TABLE `habilidades_candidato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `tipo_perfil` enum('PROFISSIONAL','EMPRESA') NOT NULL,
  `area_atuacao` varchar(100) DEFAULT NULL,
  `nome_empresa` varchar(150) DEFAULT NULL,
  `codigo_recuperacao` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (7,'Luis','g@gmail.com','123456789','EMPRESA',NULL,'catatal','AFJ4Y85W'),(8,'hhhh','hhh','hhhhjjjjjjjjj','PROFISSIONAL','Segurança',NULL,'3LVIXE4L'),(9,'sssdd','g@mail.com','12345678','EMPRESA',NULL,'asass','UAZ7HFBR'),(10,'Luis Araujo','f@gmail.com','12345678','PROFISSIONAL','Dados',NULL,'8HUTOUYE'),(11,'Maria da Conceição','p@gmail.com','12345678','PROFISSIONAL','Segurança',NULL,'9DL2W36Z'),(12,'Luis Filipe','a@gmail.com','qwertyui','PROFISSIONAL','Segurança',NULL,'7JDMUYII');
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

CREATE TABLE empresas (
    id_empresa INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    cnpj VARCHAR(18) UNIQUE,
    telefone VARCHAR(20),
    cidade VARCHAR(100),
    estado CHAR(2),
    descricao TEXT,
    area_atuacao VARCHAR(100),
    porte VARCHAR(50),
    PRIMARY KEY (id_empresa),
    UNIQUE KEY (id_usuario),
    CONSTRAINT empresas_ibfk_1
    FOREIGN KEY (id_usuario)
    REFERENCES usuarios(id)
    ON DELETE CASCADE
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

-- Dump completed on 2026-11-04 21:10:00
