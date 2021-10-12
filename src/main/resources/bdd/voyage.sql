-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Oct 12, 2021 at 02:56 PM
-- Server version: 5.7.32
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `voyage`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`, `role`) VALUES
(1, 'admin', '$2a$10$.HpL5s31dDEY1XzFcJoI0.rCK9E8qQwUYcodY.lewD3OTzsiXvHLS', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `nom_complet` varchar(255) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `adresse` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `nom_complet`, `telephone`, `email`, `adresse`) VALUES
(1, 'Alex Zokowski', '0102030406', 'alex.z@az.com', '1 boulevard de l\'Univers'),
(3, 'Laura Beduna', '2222222222', 'll.bb@bb.com', 'Boulevard des licornes');

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `etoiles` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephone` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `ville` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`id`, `nom`, `etoiles`, `adresse`, `telephone`, `email`, `ville`) VALUES
(1, 'Test', 5, 'testte', '0000000000', 'test.test@test.com', 'Test'),
(2, 'Test2', 3, 'testte', '0000000001', 'test.test@test.com', 'Test2'),
(4, 'Test3', 2, 'aaaaa', '2222222222', 'aa.zz@zz.com', 'Paris');

-- --------------------------------------------------------

--
-- Table structure for table `resa`
--

CREATE TABLE `resa` (
  `id` int(11) NOT NULL,
  `client` int(11) NOT NULL,
  `hotel` int(11) NOT NULL,
  `datedeb` datetime NOT NULL,
  `datefin` datetime NOT NULL,
  `num_chambre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `resa`
--

INSERT INTO `resa` (`id`, `client`, `hotel`, `datedeb`, `datefin`, `num_chambre`) VALUES
(2, 1, 2, '2021-10-08 00:00:00', '2021-10-10 00:00:00', 2),
(4, 1, 1, '2021-10-09 00:00:00', '2021-10-16 00:00:00', 3),
(10, 3, 2, '2021-09-27 00:00:00', '2021-10-31 00:00:00', 1),
(15, 3, 1, '2021-10-13 00:00:00', '2021-10-17 00:00:00', 5),
(51, 1, 1, '2021-10-09 00:00:00', '2021-10-16 00:00:00', 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `resa`
--
ALTER TABLE `resa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `client` (`client`),
  ADD KEY `hotel` (`hotel`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `resa`
--
ALTER TABLE `resa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `resa`
--
ALTER TABLE `resa`
  ADD CONSTRAINT `resa_ibfk_1` FOREIGN KEY (`client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `resa_ibfk_2` FOREIGN KEY (`hotel`) REFERENCES `hotel` (`id`);