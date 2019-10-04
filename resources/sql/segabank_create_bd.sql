-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.7.11 - MySQL Community Server (GPL)
-- SE du serveur:                Win32
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Listage de la structure de la base pour segabank
DROP DATABASE IF EXISTS `segabank`;
CREATE DATABASE IF NOT EXISTS `segabank` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `segabank`;

-- Listage de la structure de la table segabank. agence
CREATE TABLE IF NOT EXISTS `agence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) NOT NULL,
  `adresse` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table segabank. compteepargne
CREATE TABLE IF NOT EXISTS `compteepargne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `solde` int(11) NOT NULL,
  `tauxInteret` int(11) NOT NULL,
  `id_agence` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CPTEPARGNE_AGENCE` (`id_agence`),
  CONSTRAINT `FK_CPTEPARGNE_AGENCE` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table segabank. comptepayant
CREATE TABLE IF NOT EXISTS `comptepayant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `solde` int(11) NOT NULL,
  `interetBanque` int(11) NOT NULL,
  `id_agence` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_CPTPAYANT_AGENCE` (`id_agence`),
  CONSTRAINT `FK_CPTPAYANT_AGENCE` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table segabank. comptesimple
CREATE TABLE IF NOT EXISTS `comptesimple` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `solde` int(11) NOT NULL,
  `decouvert` int(11) NOT NULL,
  `id_agence` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_CPTSIMPLE_AGENCE` (`id_agence`),
  CONSTRAINT `FK_CPTSIMPLE_AGENCE` FOREIGN KEY (`id_agence`) REFERENCES `agence` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table segabank. utilisateur
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table segabank. utilisateur_compte
CREATE TABLE IF NOT EXISTS `utilisateur_compte` (
  `id_utilisateur` int(11) NOT NULL,
  `id_compte` int(11) NOT NULL,
  KEY `FK_UTILISATEUR` (`id_utilisateur`),
  KEY `FK_CPTSIMPLE` (`id_compte`),
  CONSTRAINT `FK_UTILISATEUR` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Les données exportées n'étaient pas sélectionnées.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
