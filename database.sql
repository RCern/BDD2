-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2019 at 03:18 PM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `database`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(25) NOT NULL,
  `MDP` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `MDP`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `coordonnees`
--

CREATE TABLE `coordonnees` (
  `ID` int(11) NOT NULL,
  `Rue` varchar(100) NOT NULL,
  `CodePostal` varchar(5) NOT NULL,
  `Ville` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Telephone` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coordonnees`
--

INSERT INTO `coordonnees` (`ID`, `Rue`, `CodePostal`, `Ville`, `Email`, `Telephone`) VALUES
(1, '1 Rue de Rome', '78990', 'Elancourt', 'radu@cernaianu.fr', 6789999),
(2, '1', '55555', 'Massy', 'mathis@bicheyre.fr', 600000),
(3, 'hey', '78999', 'Chatillon', 'maman@djedir.fr', 669),
(4, '1', '2', '3', '4', 5),
(5, 'a', '1', 'a', 'b', 1),
(6, 'a', '45', 'aaa', 'aaa', 555);

-- --------------------------------------------------------

--
-- Table structure for table `cours`
--

CREATE TABLE `cours` (
  `Code` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Description` varchar(3000) DEFAULT NULL,
  `Annee` int(11) NOT NULL,
  `Coefficient` float NOT NULL,
  `Pourc_DE` int(11) NOT NULL,
  `Pourc_CE` int(11) NOT NULL,
  `Pourc_PRJ` int(11) NOT NULL,
  `Id_Prof` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cours`
--

INSERT INTO `cours` (`Code`, `Nom`, `Description`, `Annee`, `Coefficient`, `Pourc_DE`, `Pourc_CE`, `Pourc_PRJ`, `Id_Prof`) VALUES
(1, 'Maths', 'MPI', 2019, 2, 50, 20, 30, 1);

-- --------------------------------------------------------

--
-- Table structure for table `eleve`
--

CREATE TABLE `eleve` (
  `Matricule` int(11) NOT NULL,
  `Id_Gen` int(11) NOT NULL,
  `Id_Coor` int(11) NOT NULL,
  `Id_Iden` int(11) NOT NULL,
  `Id_Grp` int(11) NOT NULL,
  `Id_Resp` int(11) NOT NULL,
  `MDP` varchar(25) NOT NULL,
  `Promotion` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eleve`
--

INSERT INTO `eleve` (`Matricule`, `Id_Gen`, `Id_Coor`, `Id_Iden`, `Id_Grp`, `Id_Resp`, `MDP`, `Promotion`) VALUES
(3, 4, 4, 3, 1, 2, 'null', 'L3'),
(20160283, 1, 1, 1, 3, 1, 'mdp', 'L3'),
(20160284, 2, 2, 2, 2, 1, 'aaa', 'L3');

-- --------------------------------------------------------

--
-- Table structure for table `general`
--

CREATE TABLE `general` (
  `ID` int(11) NOT NULL,
  `Nom` varchar(30) NOT NULL,
  `Prenom` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `general`
--

INSERT INTO `general` (`ID`, `Nom`, `Prenom`) VALUES
(1, 'Radu', 'Cernaianu'),
(2, 'Bicheyre', 'Mathis'),
(3, 'Djedir', 'YAYA'),
(4, 'Djedir', 'Warren'),
(5, 'abc', 'def'),
(6, 'Velikson', 'Boris');

-- --------------------------------------------------------

--
-- Table structure for table `groupe`
--

CREATE TABLE `groupe` (
  `ID` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupe`
--

INSERT INTO `groupe` (`ID`, `Nom`) VALUES
(1, 'INT'),
(2, '7'),
(3, 'F');

-- --------------------------------------------------------

--
-- Table structure for table `groupecours`
--

CREATE TABLE `groupecours` (
  `ID_Grp` int(11) NOT NULL,
  `Code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupecours`
--

INSERT INTO `groupecours` (`ID_Grp`, `Code`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `identite`
--

CREATE TABLE `identite` (
  `ID` int(11) NOT NULL,
  `DOB` date NOT NULL,
  `VilleNaissance` varchar(50) NOT NULL,
  `PaysNaissance` varchar(50) NOT NULL,
  `Sexe` char(1) NOT NULL,
  `Photo` varchar(260) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `identite`
--

INSERT INTO `identite` (`ID`, `DOB`, `VilleNaissance`, `PaysNaissance`, `Sexe`, `Photo`) VALUES
(1, '1998-09-08', 'Bucarest', 'Roumanie', 'M', NULL),
(2, '1998-05-20', 'PAris', 'France', 'M', NULL),
(3, '1998-05-05', 'j', 'k', 'M', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE `notes` (
  `Matricule` int(11) NOT NULL,
  `Code` int(11) NOT NULL,
  `Note_DE` float DEFAULT NULL,
  `Note_CE` float DEFAULT NULL,
  `Note_PRJ` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notes`
--

INSERT INTO `notes` (`Matricule`, `Code`, `Note_DE`, `Note_CE`, `Note_PRJ`) VALUES
(20160283, 1, 12, 15, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `persresponsable`
--

CREATE TABLE `persresponsable` (
  `ID` int(11) NOT NULL,
  `Id_Gen` int(11) NOT NULL,
  `Id_Coor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `persresponsable`
--

INSERT INTO `persresponsable` (`ID`, `Id_Gen`, `Id_Coor`) VALUES
(1, 3, 3),
(2, 5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `professeur`
--

CREATE TABLE `professeur` (
  `Matricule` int(11) NOT NULL,
  `Id_Gen` int(11) NOT NULL,
  `Id_Coor` int(11) NOT NULL,
  `Id_Iden` int(11) NOT NULL,
  `MDP` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `professeur`
--

INSERT INTO `professeur` (`Matricule`, `Id_Gen`, `Id_Coor`, `Id_Iden`, `MDP`) VALUES
(1, 6, 6, 1, 'mdp');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `coordonnees`
--
ALTER TABLE `coordonnees`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`Code`),
  ADD KEY `Cours_Prof_FK` (`Id_Prof`);

--
-- Indexes for table `eleve`
--
ALTER TABLE `eleve`
  ADD PRIMARY KEY (`Matricule`),
  ADD KEY `Eleve_Gen_FK` (`Id_Gen`),
  ADD KEY `Eleve_Coor_FK` (`Id_Coor`),
  ADD KEY `Eleve_Iden_FK` (`Id_Iden`),
  ADD KEY `Eleve_Grp_FK` (`Id_Grp`),
  ADD KEY `Eleve_Resp_FK` (`Id_Resp`);

--
-- Indexes for table `general`
--
ALTER TABLE `general`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `groupecours`
--
ALTER TABLE `groupecours`
  ADD PRIMARY KEY (`ID_Grp`,`Code`),
  ADD KEY `GroupeCours_Cours_FK` (`Code`);

--
-- Indexes for table `identite`
--
ALTER TABLE `identite`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`Matricule`,`Code`),
  ADD KEY `Notes_Cours_FK` (`Code`);

--
-- Indexes for table `persresponsable`
--
ALTER TABLE `persresponsable`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Resp_Gen_FK` (`Id_Gen`),
  ADD KEY `Resp_Coor_FK` (`Id_Coor`);

--
-- Indexes for table `professeur`
--
ALTER TABLE `professeur`
  ADD PRIMARY KEY (`Matricule`),
  ADD KEY `Professeur_Gen_FK` (`Id_Gen`),
  ADD KEY `Professeur_Coor_FK` (`Id_Coor`),
  ADD KEY `Professeur_Iden_FK` (`Id_Iden`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `coordonnees`
--
ALTER TABLE `coordonnees`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `cours`
--
ALTER TABLE `cours`
  MODIFY `Code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `eleve`
--
ALTER TABLE `eleve`
  MODIFY `Matricule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20160285;

--
-- AUTO_INCREMENT for table `general`
--
ALTER TABLE `general`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `identite`
--
ALTER TABLE `identite`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `persresponsable`
--
ALTER TABLE `persresponsable`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `Matricule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cours`
--
ALTER TABLE `cours`
  ADD CONSTRAINT `Cours_Prof_FK` FOREIGN KEY (`Id_Prof`) REFERENCES `professeur` (`Matricule`) ON DELETE CASCADE;

--
-- Constraints for table `eleve`
--
ALTER TABLE `eleve`
  ADD CONSTRAINT `Eleve_Coor_FK` FOREIGN KEY (`Id_Coor`) REFERENCES `coordonnees` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `Eleve_Gen_FK` FOREIGN KEY (`Id_Gen`) REFERENCES `general` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `Eleve_Grp_FK` FOREIGN KEY (`Id_Grp`) REFERENCES `groupe` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `Eleve_Iden_FK` FOREIGN KEY (`Id_Iden`) REFERENCES `identite` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `Eleve_Resp_FK` FOREIGN KEY (`Id_Resp`) REFERENCES `persresponsable` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `groupecours`
--
ALTER TABLE `groupecours`
  ADD CONSTRAINT `GroupeCours_Cours_FK` FOREIGN KEY (`Code`) REFERENCES `cours` (`Code`) ON DELETE CASCADE,
  ADD CONSTRAINT `GroupeCours_Group_FK` FOREIGN KEY (`ID_Grp`) REFERENCES `groupe` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `Notes_Cours_FK` FOREIGN KEY (`Code`) REFERENCES `cours` (`Code`) ON DELETE CASCADE,
  ADD CONSTRAINT `Notes_Eleve_FK` FOREIGN KEY (`Matricule`) REFERENCES `eleve` (`Matricule`) ON DELETE CASCADE;

--
-- Constraints for table `persresponsable`
--
ALTER TABLE `persresponsable`
  ADD CONSTRAINT `Resp_Coor_FK` FOREIGN KEY (`Id_Coor`) REFERENCES `coordonnees` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `Resp_Gen_FK` FOREIGN KEY (`Id_Gen`) REFERENCES `general` (`ID`) ON DELETE CASCADE;

--
-- Constraints for table `professeur`
--
ALTER TABLE `professeur`
  ADD CONSTRAINT `Professeur_Coor_FK` FOREIGN KEY (`Id_Coor`) REFERENCES `coordonnees` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `Professeur_Gen_FK` FOREIGN KEY (`Id_Gen`) REFERENCES `general` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `Professeur_Iden_FK` FOREIGN KEY (`Id_Iden`) REFERENCES `identite` (`ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
