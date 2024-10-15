-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 15 Octobre 2024 à 04:08
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `userquizz`
--

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `id_question` int(100) NOT NULL AUTO_INCREMENT,
  `question` text NOT NULL,
  `reponse` text NOT NULL,
  `opt1` text NOT NULL,
  `opt2` text NOT NULL,
  `opt3` text NOT NULL,
  `domaine` text NOT NULL,
  `niveau` text NOT NULL,
  PRIMARY KEY (`id_question`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=66 ;

--
-- Contenu de la table `question`
--

INSERT INTO `question` (`id_question`, `question`, `reponse`, `opt1`, `opt2`, `opt3`, `domaine`, `niveau`) VALUES
(1, 'Lequel de ces langages est le plus utilisé pour le développement front-end ?', 'JavaScript', 'Python', 'JavaScript', 'Perl', 'Informatique', 'Expert'),
(2, 'Lequel des langages suivants est principalement utilisé pour les contrats intelligents sur Ethereum ?', 'Solidity', 'JavaScript', 'Ruby', 'Solidity', 'Informatique', 'Expert'),
(3, 'HTTPS est un protocole sécurisé pour ...', 'Transférer des pages Web', 'Envoyer des emails', 'Transférer des pages Web', 'Chiffrer des fichiers', 'Informatique', 'Expert'),
(4, 'Quel outil est utilisé pour contrôler les versions de code source ?', 'Git', 'Git', 'Docker', 'MySQL', 'Informatique', 'Expert'),
(5, 'Quelle technologie est à la base des cryptomonnaies comme le Bitcoin ?', 'Blockchain', 'Intelligence Artificielle', 'Base de données', 'Blockchain', 'Informatique', 'Expert'),
(9, 'Quel langage est utilisé pour créer des applications Android ?', 'Java', 'Java', 'HTML', 'PHP', 'Informatique', 'Débutant'),
(10, 'Lequel de ces algorithmes est utilisé pour le chiffrement ?', 'AES', 'AES', 'LIFO', 'None', 'Informatique', 'Professionnel'),
(11, 'Que signifie ''IDE'' dans le développement logiciel ?', 'Integrated Development Environment', 'Interactive Data Environment', 'Integrated Development Environment', 'None', 'Informatique', 'Professionnel'),
(12, 'Quel terme décrit une attaque visant à submerger un système avec du trafic ?', 'DDoS', 'Phishing', 'Malware', 'DDoS', 'Informatique', 'Débutant'),
(13, 'Quel est le rôle d''un pare-feu ?', 'Filtrer le trafic réseau', 'Analyser les fichiers', 'Filtrer le trafic réseau', 'Monitorer l''utilisation du CPU', 'Informatique', 'Débutant'),
(14, 'Qu''est-ce qu''une ''vulnérabilité zéro-day'' ?', 'Une faille non corrigée et inconnue', 'Un virus qui se propage rapidement', 'Une technique de phishing', 'Une faille non corrigée et inconnue', 'Informatique', 'Professionnel'),
(15, 'Dans une architecture Web3, qu''est-ce qui est souvent utilisé pour garantir la sécurité des données ?', 'Cryptographie', 'Compilateur', 'Cloud computing', 'Cryptographie', 'Informatique', 'Professionnel'),
(16, 'Lequel de ces outils est utilisé pour le déploiement de conteneurs ?', 'Docker', 'Git', 'Docker', 'VS Code', 'Informatique', 'Professionnel'),
(17, 'Quelle commande Linux permet de lister les fichiers dans un répertoire ?', 'ls', 'cd', 'ls', 'touch', 'Informatique', 'Débutant'),
(18, 'Lequel des nombres suivants est un nombre premier ?', '17', '18', '20', '17', 'Mathématique', 'Débutant'),
(19, 'Quel est le résultat de 5 + 3 * 2 ?', '11', '16', '13', '11', 'Mathématique', 'Débutant'),
(20, 'Quelle est la dérivée de la fonction f(x) = x^2 ?', '2x', 'x^3', 'x', '2x', 'Mathématique', 'Professionnel'),
(21, 'Quel est le périmètre d''un cercle de rayon r ?', '2?r', '?r^2', 'r^2', '2?r', 'Mathématique', 'Débutant'),
(22, 'Dans un triangle rectangle, le carré de l''hypoténuse est égal à la somme des carrés des ...', 'deux autres côtés', 'angles', 'longueurs des côtés adjacents', 'deux autres côtés', 'Mathématique', 'Expert'),
(23, 'Quelle est la solution de l''équation 2x + 3 = 7 ?', 'x = 2', 'x = 5', 'x = 1', 'x = 2', 'Mathématique', 'Professionnel'),
(24, 'Combien y a-t-il de degrés dans un angle droit ?', '90', '180', '45', '90', 'Mathématique', 'Débutant'),
(25, 'Quel est le résultat de l''intégrale de 1/x dx ?', 'ln|x| + C', 'x^2/2 + C', '1/x + C', 'ln|x| + C', 'Mathématique', 'Professionnel'),
(26, 'Que représente le discriminant dans une équation quadratique ?', 'Il détermine le nombre de solutions réelles', 'Il calcule la pente de la parabole', 'Il trouve le sommet de la parabole', 'Il détermine le nombre de solutions réelles', 'Mathématique', 'Expert'),
(27, 'Combien de côtés a un hexagone ?', '6', '8', '5', '6', 'Mathématique', 'Professionnel'),
(28, 'Qu''est-ce que la matrice identité ?', 'Une matrice carrée avec des 1 sur la diagonale principale et des 0 ailleurs', 'Une matrice où tous les éléments sont égaux à 1', 'Une matrice où tous les éléments sont égaux à 0', 'Une matrice carrée avec des 1 sur la diagonale principale et des 0 ailleurs', 'Mathématique', 'Professionnel'),
(29, 'Quel est le résultat de l''opération vectorielle u × v ?', 'Produit vectoriel', 'Produit scalaire', 'Produit cartésien', 'Produit vectoriel', 'Mathématique', 'Expert'),
(30, 'Quelle est la valeur de ? (pi) approximativement ?', '3.14', '3.41', '4.13', '3.14', 'Mathématique', 'Expert'),
(31, 'Comment appelle-t-on un polygone à 10 côtés ?', 'Décagone', 'Hexagone', 'Pentagone', 'Décagone', 'Mathématique', 'Débutant'),
(32, 'Quel est le résultat de la somme des angles d''un triangle ?', '180 degrés', '360 degrés', '90 degrés', '180 degrés', 'Mathématique', 'Expert'),
(33, 'En quelle année a eu lieu la Révolution française ?', '1789', '1776', '1815', '1789', 'Histoire', 'Débutant'),
(34, 'Qui était l''empereur de France pendant les guerres napoléoniennes ?', 'Napoléon Bonaparte', 'Louis XIV', 'Charlemagne', 'Napoléon Bonaparte', 'Histoire', 'Débutant'),
(35, 'Qui a découvert l''Amérique en 1492 ?', 'Christophe Colomb', 'Vasco de Gama', 'Ferdinand Magellan', 'Christophe Colomb', 'Histoire', 'Débutant'),
(36, 'Quand a eu lieu l''indépendance des États-Unis ?', '1776', '1789', '1812', '1776', 'Histoire', 'Débutant'),
(37, 'Qui a mené la lutte contre l''apartheid en Afrique du Sud ?', 'Nelson Mandela', 'Desmond Tutu', 'Robert Mugabe', 'Nelson Mandela', 'Histoire', 'Débutant'),
(38, 'Quel traité a mis fin à la Première Guerre mondiale ?', 'Traité de Versailles', 'Traité de Brest-Litovsk', 'Traité de Trianon', 'Traité de Versailles', 'Histoire', 'Professionnel'),
(39, 'Quel événement a marqué le début de la Seconde Guerre mondiale ?', 'L''invasion de la Pologne', 'L''attaque de Pearl Harbor', 'La bataille de Stalingrad', 'L''invasion de la Pologne', 'Histoire', 'Professionnel'),
(40, 'Quel roi de France a été surnommé ''le Roi Soleil'' ?', 'Louis XIV', 'Louis XVI', 'Charles V', 'Louis XIV', 'Histoire', 'Professionnel'),
(41, 'Quelle guerre a opposé l''Union soviétique et les États-Unis sans affrontement direct ?', 'La guerre froide', 'La guerre de Corée', 'La guerre du Vietnam', 'La guerre froide', 'Histoire', 'Professionnel'),
(42, 'Quelle femme est célèbre pour son rôle dans la guerre de Cent Ans en France ?', 'Jeanne d''Arc', 'Catherine de Médicis', 'Marie-Antoinette', 'Jeanne d''Arc', 'Histoire', 'Professionnel'),
(43, 'Quel empire était dirigé par Gengis Khan ?', 'Empire Mongol', 'Empire Ottoman', 'Empire Perse', 'Empire Mongol', 'Histoire', 'Expert'),
(44, 'Quel est le premier homme à avoir marché sur la Lune ?', 'Neil Armstrong', 'Buzz Aldrin', 'Youri Gagarine', 'Neil Armstrong', 'Histoire', 'Expert'),
(45, 'En quelle année le mur de Berlin est-il tombé ?', '1989', '1991', '1975', '1989', 'Histoire', 'Expert'),
(46, 'Quel pharaon a fait construire les grandes pyramides de Gizeh ?', 'Khéops', 'Ramsès II', 'Toutankhamon', 'Khéops', 'Histoire', 'Expert'),
(47, 'Quel traité a mis fin à la guerre d''Algérie ?', 'Les accords d''Évian', 'Le traité de Versailles', 'Le traité de Trianon', 'Les accords d''Évian', 'Histoire', 'Expert'),
(48, 'Quelle est la formule chimique de l''eau ?', 'H2O', 'O2', 'CO2', 'H2O', 'Science', 'Débutant'),
(49, 'Quel est l''organe principal de la circulation sanguine ?', 'Cœur', 'Poumon', 'Foie', 'Cœur', 'Science', 'Débutant'),
(50, 'Quelle est la vitesse de la lumière dans le vide ?', '299 792 458 m/s', '300 000 km/s', '150 000 km/s', '299 792 458 m/s', 'Science', 'Débutant'),
(51, 'Quel gaz constitue 78 % de l''atmosphère terrestre ?', 'Azote', 'Oxygène', 'Carbone', 'Azote', 'Science', 'Débutant'),
(52, 'Quel est l''élément chimique avec le symbole ''Fe'' ?', 'Fer', 'Fluor', 'Fermium', 'Fer', 'Science', 'Débutant'),
(53, 'Quelle partie de la cellule contient l''ADN ?', 'Noyau', 'Cytoplasme', 'Membrane', 'Noyau', 'Science', 'Professionnel'),
(54, 'Quel est l''unité de mesure de la force ?', 'Newton', 'Joule', 'Pascal', 'Newton', 'Science', 'Professionnel'),
(55, 'Quel est l''organe responsable de la respiration ?', 'Poumon', 'Cœur', 'Foie', 'Poumon', 'Science', 'Professionnel'),
(56, 'Quel est l''organe principal du système nerveux ?', 'Cerveau', 'Moelle épinière', 'Nerfs', 'Cerveau', 'Science', 'Professionnel'),
(57, 'Quelle est la première loi de Newton ?', 'Un corps au repos reste au repos', 'La force égale la masse multipliée par l''accélération', 'Pour chaque action, il y a une réaction égale et opposée', 'Un corps au repos reste au repos', 'Science', 'Professionnel'),
(58, 'Quel est le processus par lequel les plantes produisent leur nourriture ?', 'Photosynthèse', 'Respiration', 'Transpiration', 'Photosynthèse', 'Science', 'Expert'),
(59, 'Quel est le point de fusion du glace ?', '0 °C', '-32 °C', '100 °C', '0 °C', 'Science', 'Expert'),
(60, 'Quel est l''organe de la vue chez l''homme ?', 'Œil', 'Oreille', 'Peau', 'Œil', 'Science', 'Expert'),
(61, 'Quel type de roche se forme par refroidissement du magma ?', 'Roche ignée', 'Roche sédimentaire', 'Roche métamorphique', 'Roche ignée', 'Science', 'Expert'),
(62, 'Quel est le symbole chimique de l''or ?', 'Au', 'Ag', 'Fe', 'Au', 'Science', 'Expert'),
(63, 'Quel est l''organe responsable de la digestion ?', 'Estomac', 'Foie', 'Intestin', 'Estomac', 'Science', 'Expert'),
(64, 'OS veut dire ...', 'Operating System', 'Operation et Synthese', 'Optimisation du Systeme', 'Operating System', 'Informatique', 'Débutant'),
(65, 'Qui code?', 'Victor', 'V1', 'V2', 'V3', 'Informatique', 'Facile');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(60) unsigned NOT NULL AUTO_INCREMENT,
  `nom` text NOT NULL,
  `pass` text NOT NULL,
  `universite` text NOT NULL,
  `score` int(20) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id_user`, `nom`, `pass`, `universite`, `score`) VALUES
(1, 'Victor DAH', '12345678', 'Universite Virtuelle', 90),
(2, 'Ella DABIRE', '87654321', 'UV', 90);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
