# ************************************************************
# Sequel Pro SQL dump
# Versión 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 204.44.192.59 (MySQL 5.7.36)
# Base de datos: zaval846_bdt196
# Tiempo de Generación: 2021-11-09 01:41:28 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Volcado de tabla rol
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
  `rol_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(20) NOT NULL DEFAULT '',
  `desc_rol` text,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;

INSERT INTO `rol` (`rol_id`, `nombre_rol`, `desc_rol`)
VALUES
	(1,'Admin','Administrador supremo de la aplicación con permisos para todo'),
	(2,'Colaborador','Usuario administrativo de la aplicación con permisos de gestión de stock de productos y seguimiento de pedidos'),
	(3,'Cliente','Usuario de la aplicación móvil');

/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla usuario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `usuario_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rol_id` int(11) unsigned NOT NULL,
  `email_auth` varchar(120) NOT NULL DEFAULT '',
  `telefono_auth` varchar(10) NOT NULL DEFAULT '',
  `pass_auth` varchar(32) NOT NULL DEFAULT '',
  `fecha_registro` datetime NOT NULL,
  `estatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE KEY `email_auth` (`email_auth`),
  UNIQUE KEY `telefono_auth` (`telefono_auth`),
  KEY `rol_id` (`rol_id`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`rol_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='—ESTATUS—\n1 = Usuario activo\n2 = Usuario deshabilitado\n3 = Usuario eliminado';

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;

INSERT INTO `usuario` (`usuario_id`, `rol_id`, `email_auth`, `telefono_auth`, `pass_auth`, `fecha_registro`, `estatus`)
VALUES
	(1,1,'admin','4421234567','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 10:20:00',1),
	(2,2,'colab','4427654321','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 10:25:00',1),
	(3,3,'yel@gmail.com','4421000001','81dc9bdb52d04dc20036dbd8313ed055','2021-11-08 08:58:36',1),
	(4,3,'2020371017@uteq.edu.mx','4421419819','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 08:58:55',1),
	(5,3,'2020371044@uteq.edu.mx','4681373750','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 08:59:31',1),
	(6,3,'abalderastrejo@gmail.com','4427775614','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 08:59:41',1),
	(7,3,'raul.zavaletazea@uteq.edu.mx','4421298927','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 09:00:24',1),
	(8,3,'alessandrocgs2@gmail.com','4423801291','827ccb0eea8a706c4c34a16891f84e7b','2021-11-08 09:00:42',1),
	(9,3,'rodrigoalonso360@gmail.com','4141195374','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 09:01:51',1),
	(10,3,'angierayas29@gmail.com','4151007510','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 09:02:40',1),
	(11,3,'ricval196@gmail.com','4426690750','4ced1ec09bbf9acbf76fcf3a8ff14094','2021-11-08 09:04:28',1),
	(12,3,'2020371104@uteq.edu.mx','4423630331','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 09:11:24',1),
	(13,3,'gallardopenalozaalan@gmail.com','7811046276','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 09:14:19',1),
	(15,3,'jorgergzj17@gmail.com','4191377583','e807f1fcf82d132f9bb018ca6738a19f','2021-11-08 09:15:06',1),
	(16,3,'chrmel196@gmail.com','4426006031','8707c3930013d4cd939ac1f097fc13f1','2021-11-08 09:15:22',1);

/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
