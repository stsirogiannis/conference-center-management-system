-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 23, 2025 at 08:26 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `psifiakaDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `Client`
--

CREATE TABLE `Client` (
  `Client_ID` varchar(10) NOT NULL,
  `Name` varchar(10) NOT NULL,
  `Email` varchar(10) NOT NULL,
  `Phone_Number` varchar(10) NOT NULL,
  `PaymDet_Payment_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Client`
--

INSERT INTO `Client` (`Client_ID`, `Name`, `Email`, `Phone_Number`, `PaymDet_Payment_ID`) VALUES
('CL001', 'Giannis', 'g@ex.com', '6970000001', 'P001'),
('CL002', 'ETAIR', 'in@et.gr', '2109999999', 'P002'),
('CL003', 'Maria', 'ma@ex.com', '6900000003', 'P003'),
('CL004', 'ad', 'a@a.gr', '6987654321', 'P004');

-- --------------------------------------------------------

--
-- Table structure for table `ConfCentre`
--

CREATE TABLE `ConfCentre` (
  `Unique_Code` varchar(10) NOT NULL,
  `Name` varchar(10) NOT NULL,
  `Address` varchar(10) NOT NULL,
  `City` varchar(10) NOT NULL,
  `Phone_Number` varchar(10) NOT NULL,
  `Email` varchar(10) NOT NULL,
  `Services` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ConfCentre`
--

INSERT INTO `ConfCentre` (`Unique_Code`, `Name`, `Address`, `City`, `Phone_Number`, `Email`, `Services`) VALUES
('ATH001', 'ACF', 'Sofias 2', 'Athens', '2101234567', 'ath@cf.gr', 'WiFi'),
('IOA', 'IOANN', 'kapn 2', 'Ioann', '6978765432', 'a@a.gr', 'ok'),
('SKG001', 'TCF', 'Iou 3', 'Thess', '2310123456', 'skg@cf.gr', 'WiFi');

-- --------------------------------------------------------

--
-- Table structure for table `ConfRoom`
--

CREATE TABLE `ConfRoom` (
  `Room_Code` varchar(10) NOT NULL,
  `Name` varchar(10) NOT NULL,
  `Max_Capacity` varchar(10) NOT NULL,
  `Seating_Type` varchar(10) NOT NULL,
  `Equipment` varchar(10) NOT NULL,
  `WiFi_Availability` varchar(10) NOT NULL,
  `Hourly_Rent` varchar(10) NOT NULL,
  `Availability` varchar(10) NOT NULL,
  `ConfCentre_Unique_Code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ConfRoom`
--

INSERT INTO `ConfRoom` (`Room_Code`, `Name`, `Max_Capacity`, `Seating_Type`, `Equipment`, `WiFi_Availability`, `Hourly_Rent`, `Availability`, `ConfCentre_Unique_Code`) VALUES
('R001', 'HallA', '100', 'Theater', 'Projector', 'Yes', '120', 'Yes', 'ATH001'),
('R002', 'HallB', '50', 'Round', 'Board', 'No', '80', 'Yes', 'ATH001'),
('R003', 'HallC', '150', 'Theater', 'SoundSys', 'Yes', '150', 'Yes', 'SKG001'),
('RM01', 'BackH', '100', 'leather', 'drums', 'yes', '999', 'no', 'IOA');

-- --------------------------------------------------------

--
-- Table structure for table `PaymDet`
--

CREATE TABLE `PaymDet` (
  `Payment_ID` varchar(10) NOT NULL,
  `Amount` varchar(10) NOT NULL,
  `Method` varchar(10) NOT NULL,
  `Date` varchar(10) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `Client_Client_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `PaymDet`
--

INSERT INTO `PaymDet` (`Payment_ID`, `Amount`, `Method`, `Date`, `Status`, `Client_Client_ID`) VALUES
('P001', '480', 'Card', '2025-05-21', 'Pending', 'CL001'),
('P002', '240', 'Cash', '2025-05-22', 'Pending', 'CL002'),
('P003', '300', 'Card', '2025-05-23', 'Cancelled', 'CL003'),
('P004', '120', 'cash', '2025-05-23', 'confirmed', 'CL004');

-- --------------------------------------------------------

--
-- Table structure for table `RoomRes`
--

CREATE TABLE `RoomRes` (
  `Reservation_ID` varchar(10) NOT NULL,
  `Start_Date` varchar(10) NOT NULL,
  `End_Date` varchar(10) NOT NULL,
  `Start_Time` varchar(10) NOT NULL,
  `End_Time` varchar(10) NOT NULL,
  `Preferred_City` varchar(10) NOT NULL,
  `Preferred_ConferenceCentre` varchar(10) DEFAULT NULL,
  `Participants` varchar(10) DEFAULT NULL,
  `Preferred_Equipment` varchar(10) DEFAULT NULL,
  `Invoice_Required` varchar(10) DEFAULT NULL,
  `Reservation_Status` varchar(10) NOT NULL,
  `ConfRoom_Room_Code` varchar(10) NOT NULL,
  `PaymDet_Payment_ID` varchar(10) NOT NULL,
  `Client_Client_ID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `RoomRes`
--

INSERT INTO `RoomRes` (`Reservation_ID`, `Start_Date`, `End_Date`, `Start_Time`, `End_Time`, `Preferred_City`, `Preferred_ConferenceCentre`, `Participants`, `Preferred_Equipment`, `Invoice_Required`, `Reservation_Status`, `ConfRoom_Room_Code`, `PaymDet_Payment_ID`, `Client_Client_ID`) VALUES
('RES001', '2025-05-24', '2025-05-24', '09:00', '13:00', 'Athens', 'CC001', '90', 'Projector', 'Yes', 'Pending', 'R001', 'P001', 'CL001'),
('RES002', '2025-05-25', '2025-05-25', '15:00', '18:00', 'Athens', 'CC002', '40', 'Board', 'No', 'Pending', 'R002', 'P002', 'CL002');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Client`
--
ALTER TABLE `Client`
  ADD PRIMARY KEY (`Client_ID`),
  ADD UNIQUE KEY `Client__IDX` (`PaymDet_Payment_ID`);

--
-- Indexes for table `ConfCentre`
--
ALTER TABLE `ConfCentre`
  ADD PRIMARY KEY (`Unique_Code`);

--
-- Indexes for table `ConfRoom`
--
ALTER TABLE `ConfRoom`
  ADD PRIMARY KEY (`Room_Code`),
  ADD KEY `ConfRoom_ConfCentre_FK` (`ConfCentre_Unique_Code`);

--
-- Indexes for table `PaymDet`
--
ALTER TABLE `PaymDet`
  ADD PRIMARY KEY (`Payment_ID`),
  ADD UNIQUE KEY `PaymDet__IDX` (`Client_Client_ID`);

--
-- Indexes for table `RoomRes`
--
ALTER TABLE `RoomRes`
  ADD PRIMARY KEY (`Reservation_ID`),
  ADD UNIQUE KEY `RoomRes__IDX` (`PaymDet_Payment_ID`),
  ADD KEY `RoomRes_Client_FK` (`Client_Client_ID`),
  ADD KEY `RoomRes_ConfRoom_FK` (`ConfRoom_Room_Code`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Client`
--
ALTER TABLE `Client`
  ADD CONSTRAINT `Client_PaymDet_FK` FOREIGN KEY (`PaymDet_Payment_ID`) REFERENCES `PaymDet` (`Payment_ID`);

--
-- Constraints for table `ConfRoom`
--
ALTER TABLE `ConfRoom`
  ADD CONSTRAINT `ConfRoom_ConfCentre_FK` FOREIGN KEY (`ConfCentre_Unique_Code`) REFERENCES `ConfCentre` (`Unique_Code`);

--
-- Constraints for table `PaymDet`
--
ALTER TABLE `PaymDet`
  ADD CONSTRAINT `PaymDet_Client_FK` FOREIGN KEY (`Client_Client_ID`) REFERENCES `Client` (`Client_ID`);

--
-- Constraints for table `RoomRes`
--
ALTER TABLE `RoomRes`
  ADD CONSTRAINT `RoomRes_Client_FK` FOREIGN KEY (`Client_Client_ID`) REFERENCES `Client` (`Client_ID`),
  ADD CONSTRAINT `RoomRes_ConfRoom_FK` FOREIGN KEY (`ConfRoom_Room_Code`) REFERENCES `ConfRoom` (`Room_Code`),
  ADD CONSTRAINT `RoomRes_PaymDet_FK` FOREIGN KEY (`PaymDet_Payment_ID`) REFERENCES `PaymDet` (`Payment_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
