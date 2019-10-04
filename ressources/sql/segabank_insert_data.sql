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

-- Listage des données de la table segabank.agence : ~3 rows (environ)
/*!40000 ALTER TABLE `agence` DISABLE KEYS */;
INSERT INTO `agence` (`id`, `code`, `adresse`) VALUES
	(1, 1, '17 Rue Racine 44000 NANTES'),
	(2, 2, '10 Rue de Rieux 44040 NANTES'),
	(3, 3, '39 Rue Croix des Petits Champs 750001 PARIS'),
	(4, 4, '4 Bis Cours Bayard');
/*!40000 ALTER TABLE `agence` ENABLE KEYS */;

-- Listage des données de la table segabank.compte : ~6 rows (environ)
/*!40000 ALTER TABLE `compte` DISABLE KEYS */;
INSERT INTO `compte` (`id`, `solde`, `id_agence`, `tauxInteret`, `decouvert`, `type`) VALUES
	(1, 1560, 2, 2, NULL, 'E'),
	(2, 328, 1, NULL, 200, 'S'),
	(3, 3245, 1, NULL, NULL, 'P'),
	(4, 561, 3, NULL, 80, 'S'),
	(5, 2865, 2, 1, NULL, 'E'),
	(6, 827, 1, NULL, NULL, 'P');
/*!40000 ALTER TABLE `compte` ENABLE KEYS */;

-- Listage des données de la table segabank.utilisateur : ~0 rows (environ)
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` (`id`, `nom`) VALUES
	(1, 'CAILLAUD'),
	(2, 'MAGONI');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;

-- Listage des données de la table segabank.utilisateur_compte : ~0 rows (environ)
/*!40000 ALTER TABLE `utilisateur_compte` DISABLE KEYS */;
/*!40000 ALTER TABLE `utilisateur_compte` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
