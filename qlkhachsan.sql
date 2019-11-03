-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: qlkhachsan
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chitiethoadon` (
  `maPhong` int(11) NOT NULL,
  `maHD` int(11) NOT NULL,
  `giaTien` varchar(45) NOT NULL,
  `ngayDen` date NOT NULL,
  `ngayDi` date NOT NULL,
  PRIMARY KEY (`maPhong`,`maHD`),
  KEY `fk_maHD_idx` (`maHD`),
  CONSTRAINT `fk_maHD` FOREIGN KEY (`maHD`) REFERENCES `hoadon` (`maHD`),
  CONSTRAINT `fk_maPhong` FOREIGN KEY (`maPhong`) REFERENCES `phong` (`maPhong`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethoadon`
--

LOCK TABLES `chitiethoadon` WRITE;
/*!40000 ALTER TABLE `chitiethoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `chitiethoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hoadon` (
  `maHD` int(11) NOT NULL,
  `maNV` int(11) DEFAULT NULL,
  `maKH` int(11) DEFAULT NULL,
  `ngayDat` datetime DEFAULT NULL,
  `tongTien` double DEFAULT NULL,
  PRIMARY KEY (`maHD`),
  KEY `fk_maNV_idx` (`maNV`),
  KEY `K_maKH_idx` (`maKH`),
  CONSTRAINT `K_maKH` FOREIGN KEY (`maKH`) REFERENCES `khachhang` (`maKH`),
  CONSTRAINT `fk_maNV` FOREIGN KEY (`maNV`) REFERENCES `nhanvien` (`idnhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khachhang`
--

DROP TABLE IF EXISTS `khachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `khachhang` (
  `maKH` int(11) NOT NULL,
  `tenKH` varchar(50) DEFAULT NULL,
  `ngaySinh` datetime DEFAULT NULL,
  `gioiTinh` varchar(45) DEFAULT NULL,
  `sdt` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`maKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khachhang`
--

LOCK TABLES `khachhang` WRITE;
/*!40000 ALTER TABLE `khachhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `khachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaiphong`
--

DROP TABLE IF EXISTS `loaiphong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `loaiphong` (
  `idLoaiP` int(11) NOT NULL,
  `tenLoaiP` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idLoaiP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaiphong`
--

LOCK TABLES `loaiphong` WRITE;
/*!40000 ALTER TABLE `loaiphong` DISABLE KEYS */;
/*!40000 ALTER TABLE `loaiphong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nhanvien` (
  `idnhanVien` int(11) NOT NULL,
  `hoNV` varchar(50) DEFAULT NULL,
  `tenNV` varchar(50) DEFAULT NULL,
  `gioiTinh` varchar(45) DEFAULT NULL,
  `queQuan` varchar(100) DEFAULT NULL,
  `ngaySinh` date DEFAULT NULL,
  `ngayVaoLam` date DEFAULT NULL,
  `chucVu` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idnhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'Mai ','Tường Vi','Nữ ','Bình Định','1999-06-18','2010-05-07','Quản lý'),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong`
--

DROP TABLE IF EXISTS `phong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `phong` (
  `maPhong` int(11) NOT NULL,
  `sucChua` int(11) DEFAULT NULL,
  `idLoaiP` int(11) DEFAULT NULL,
  `giaP` double DEFAULT NULL,
  `tinhTrangP` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`maPhong`),
  KEY `fk_idLoaiP_idx` (`idLoaiP`),
  CONSTRAINT `fk_idLoaiP` FOREIGN KEY (`idLoaiP`) REFERENCES `loaiphong` (`idLoaiP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong`
--

LOCK TABLES `phong` WRITE;
/*!40000 ALTER TABLE `phong` DISABLE KEYS */;
/*!40000 ALTER TABLE `phong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `taikhoan` (
  `idNhanVien` int(11) NOT NULL,
  `taiKhoan` varchar(10) NOT NULL,
  `matKhau` varchar(45) NOT NULL,
  PRIMARY KEY (`idNhanVien`),
  CONSTRAINT `fk_idNhanVien` FOREIGN KEY (`idNhanVien`) REFERENCES `nhanvien` (`idnhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
INSERT INTO `taikhoan` VALUES (1,'maituongvi','123456'),(2,'admin','654321');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-03 10:02:56
