-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 12, 2014 at 11:49 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `prestige_villa_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE IF NOT EXISTS `payments` (
  `Res` varchar(10) NOT NULL,
  `Paid By` varchar(60) NOT NULL,
  `Recieved By` varchar(60) NOT NULL,
  `Date` varchar(30) NOT NULL,
  `Amount(=N=)` varchar(30) NOT NULL,
  PRIMARY KEY (`Res`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`Res`, `Paid By`, `Recieved By`, `Date`, `Amount(=N=)`) VALUES
('04290007', 'SALWAT ABDULLAHI', 'HABIB ADO', '10/06/2014', '111000'),
('04290012', 'GORGE WILLIAM', 'HABIB ADO', '08/06/2014', '111000');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE IF NOT EXISTS `reservations` (
  `Res` varchar(15) NOT NULL,
  `First Name` varchar(30) NOT NULL,
  `Last Name` varchar(30) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Phone No` varchar(20) NOT NULL,
  `Country` varchar(30) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `ID Type` varchar(30) NOT NULL,
  `ID No` varchar(30) NOT NULL,
  `Arrival` varchar(20) NOT NULL,
  `Departure` varchar(20) NOT NULL,
  `RDate` varchar(20) NOT NULL,
  `CancelDate` varchar(20) DEFAULT NULL,
  `Days` varchar(5) NOT NULL,
  `Room No` varchar(10) NOT NULL,
  `Room Type` varchar(30) NOT NULL,
  `Room Rate` varchar(10) NOT NULL,
  `Total Prize` varchar(20) NOT NULL,
  `Status` varchar(11) NOT NULL,
  PRIMARY KEY (`Res`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`Res`, `First Name`, `Last Name`, `Gender`, `Phone No`, `Country`, `Address`, `ID Type`, `ID No`, `Arrival`, `Departure`, `RDate`, `CancelDate`, `Days`, `Room No`, `Room Type`, `Room Rate`, `Total Prize`, `Status`) VALUES
('03170004', 'ABBA', 'AHMAD', 'Male', '+632521100210', 'Belarus', 'MINSK, BELARUS', 'Drivers Licence', '45213774DE', '06/06/2014', '10/06/2014', '17/11/2013', NULL, '4', '010D', 'Diplomatic', '37000', '148000', 'Checked'),
('04290002', 'UMAR', 'HARUNA', 'Male', '+320125485745', 'Nigeria', 'SOKOTO, NIGERIA', 'School ID', '2012KLJHGF1', '08/06/2014', '10/06/2014', '29/12/2013', NULL, '2', '006D', 'Diplomatic', '37000', '740000', 'Checked'),
('04290003', 'GADDAFI', 'TUKUR', 'Male', '+652154875214', 'England', 'LODON, UK', 'Drivers Licence', '41DSASE745', '09/06/2014', '10/06/2014', '29/12/2013', NULL, '1', '007D', 'Diplomatic', '37000', '37000', 'Checked'),
('04290004', 'FANDY', 'HASSAN', 'Female', '+632514528754', 'Hungry', 'BUDAPEST, HUNGARY', 'Other', '25KAGSHE745', '08/06/2014', '10/06/2014', '29/12/2013', NULL, '2', '003E', 'Executive', '20000', '40000', 'Checked'),
('04290005', 'NA''IMA', 'ABDULKADIR', 'Female', '+632512547857', 'Belarus', '12 UPPER MINSK, BELARUS', 'Drivers Licence', 'WE78545D54', '08/06/2014', '10/06/2014', '06/06/2014', NULL, '2', '001E', 'Executive', '20000', '40000', 'Checked'),
('04290006', 'LAURAH', 'LAWAL', 'Female', '+632512548785', 'Ghana', 'ACCRA, GHANA', 'Nation ID', '5235LAJKSHT', '08/06/2014', '10/06/2014', '06/06/2014', NULL, '2', '007S', 'Super Executive', '25000', '50000', 'Checked'),
('04290007', 'SALWAT', 'ABDULLAHI', 'Female', '+25352102154', 'NIGER', 'ZINDERR, NIGER', 'Drivers Licence', '542452HTHNK', '10/06/2014', '13/06/2014', '08/06/2014', NULL, '3', '002D', 'Diplomatic', '37000', '111000', 'Checked'),
('04290008', 'USAMAN', 'IBRAHIM', 'Male', '+632154254785', 'China', 'SHENYANG, CHINA', 'Drivers Licence', '542452KAHTS', '10/06/2014', '12/06/2014', '08/06/2014', NULL, '2', '001S', 'Super Executive', '25000', '50000', 'Waiting'),
('04290009', 'LAYUSA', 'UMAR', 'Female', '+632564258742', 'Belarus', '67 MINSK', 'Drivers Licence', '52365248JK', '10/06/2014', '12/06/2014', '08/06/2014', NULL, '2', '002S', 'Super Executive', '25000', '50000', 'Waiting'),
('04290010', 'AWWAL', 'ISMA''IL', 'Male', '+362542154875', 'England', 'LIVERPOOL, UK', 'Drivers Licence', '524145KRGN', '10/06/2014', '15/06/2014', '08/06/2014', NULL, '5', '003S', 'Super Executive', '25000', '125000', 'Waiting'),
('04290011', 'HADIZA', 'MUKHTAR', 'Female', '+632512547856', 'China', 'BEIGING, CHINA', 'Drivers Licence', '858QD1HDTH', '10/06/2014', '11/06/2014', '08/06/2014', NULL, '1', '004S', 'Super Executive', '25000', '25000', 'Waiting'),
('04290012', 'HABIB', 'ADO', 'Male', '+632512548752', 'Nigeria', '23 BUK ROAD, KANO', 'Drivers Licence', '52354H745L', '10/06/2014', '13/06/2014', '08/06/2014', NULL, '3', '001D', 'Diplomatic', '37000', '111000', 'Waiting');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE IF NOT EXISTS `rooms` (
  `Room No` varchar(20) NOT NULL,
  `Room Type` varchar(20) NOT NULL,
  `Prize (=N=)` varchar(20) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `Available Services` varchar(200) NOT NULL,
  PRIMARY KEY (`Room No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`Room No`, `Room Type`, `Prize (=N=)`, `Status`, `Available Services`) VALUES
('001D', 'Diplomatic', '37000', 'Reserved', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('001E', 'Executive', '20000', 'Occupied', '1 Room, Bathroom, AC, Fridge, DSTV'),
('001S', 'Super Executive', '25000', 'Reserved', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('002D', 'Diplomatic', '37000', 'Occupied', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('002E', 'Executive', '20000', 'Available', '1 Room, Bathroom, AC, Fridge, DSTV'),
('002S', 'Super Executive', '25000', 'Reserved', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('003D', 'Diplomatic', '37000', 'Available', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('003E', 'Executive', '20000', 'Occupied', '1 Room, Bathroom, AC, Fridge, DSTV'),
('003S', 'Super Executive', '25000', 'Reserved', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('004D', 'Diplomatic', '37000', 'Available', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('004E', 'Executive', '20000', 'Available', '1 Room, Bathroom, AC, Fridge, DSTV'),
('004S', 'Super Executive', '25000', 'Reserved', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('005D', 'Diplomatic', '37000', 'Available', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('005E', 'Executive', '20000', 'Available', '1 Room, Bathroom, AC, Fridge, DSTV'),
('005S', 'Super Executive', '25000', 'Available', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('006D', 'Diplomatic', '37000', 'Occupied', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('006E', 'Executive', '20000', 'Available', '1 Room, Bathroom, AC, Fridge, DSTV'),
('006S', 'Super Executive', '25000', 'Available', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('007D', 'Diplomatic', '37000', 'Occupied', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('007E', 'Executive', '20000', 'Available', '1 Room, Bathroom, AC, Fridge, DSTV'),
('007S', 'Super Executive', '25000', 'Occupied', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('008D', 'Diplomatic', '37000', 'Available', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('008E', 'Executive', '20000', 'Available', '1 Room, Bathroom, AC, Fridge, DSTV'),
('008S', 'Super Executive', '25000', 'Available', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('009D', 'Diplomatic', '37000', 'Available', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('009E', 'Executive', '20000', 'Available', '1 Room, Bathroom, AC, Fridge, DSTV'),
('009S', 'Super Executive', '25000', 'Available', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('010D', 'Diplomatic', '37000', 'Occupied', '2 Rooms, Kitchen,  Living Room, Bathroom, Dinning, AC, Fridge, DSTV'),
('010E', 'Executive', '20000', 'Available', '1 Room, Bathroom, AC, Fridge, DSTV'),
('010S', 'Super Executive', '25000', 'Available', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV'),
('011E', 'Executive', '20000', 'Available', '1 Room, Bathroom, AC, Fridge, DSTV'),
('011S', 'Super Executive', '25000', 'Available', '1 Room, Living Room, Bathroom, AC, Fridge, DSTV');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `Staff ID` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `First Name` varchar(50) NOT NULL,
  `Last Name` varchar(50) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Phone No` varchar(20) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `User Type` varchar(20) NOT NULL,
  `Work Days` varchar(20) NOT NULL,
  PRIMARY KEY (`Staff ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`Staff ID`, `Password`, `First Name`, `Last Name`, `Address`, `Phone No`, `Gender`, `User Type`, `Work Days`) VALUES
('0001', 'habib', 'HABIB', 'ADO', '15 BUK ROAD, KANO', '+2348065845490', 'Male', 'Manager', 'Mon, Thu, Sun'),
('0002', 'zainab', 'AMINAH', 'MUKTAR', '334 SULTAN ROAD', '+8899555444544', 'Male', 'Manager', 'Wed, Thu, Fri'),
('0003', '9JY5GHGU', 'MUSA', 'ISA', '124 CHINA TOWN', '+6652221455555', 'Male', 'Manager', 'Wed, Thu, Sun'),
('0004', '123456', 'SANDRA', 'SMITH', '44 KANO CENTRAL', '+5652445554444', 'Femele', 'Receptionist', 'Fri, Sat, Sun'),
('0005', 'sDR58Ybn', 'AMANDA', 'CLARK', '87 KANO SOUTH', '+9987855544455', 'Femele', 'Receptionist', 'Sat, Sun, Mon'),
('0006', '45RTYve7', 'MUKTAR', 'ADNAN', '854 KANO POLY QTR.', '+3565511155875', 'Male', 'Receptionist', 'Wed, Thu, Sun'),
('0007', 'rCV5vtRc', 'MARYAM', 'MUKTAR', '554 ALU AVE', '+9634556988572', 'Femele', 'Receptionist', 'Fri, Sat, Sun'),
('0008', 'PKNT8YTx', 'FAITH', 'MOSES', '454 SOUTH GRA', '+6235487989871', 'Femele', 'Receptionist', 'Sat, Sun, Mon'),
('0009', 'hNYTxBue', 'GODWILL', 'ESEIGIE', '564 NORTH WEST RD.', '+6655255884442', 'Male', 'Receptionist', 'Wed, Thu, Sun'),
('0010', '6tgHTBCu', 'GOODLUCK', 'JONATHAN', '8 K/MAZUGAL', '+5523544585547', 'Male', 'Receptionist', 'Mon, Tue, Sat'),
('0011', 'habibado', 'HABIB', 'ADO', '78 POZNAN, POLAND', '+2215236545655', 'Male', 'Receptionist', 'All Days'),
('0012', 'RvqNDYul', 'EMMA', 'WILL', '4 NORTH WEST DR. KANO', '+2135246598523', 'Female', 'Manager', 'All Days'),
('0013', 'tdQTdnQa', 'AMANDA', 'CLARK', '44 K/M KANO, NIGERIA', '+2312546854344', 'Female', 'Receptionist', 'Mon, Fri, Sun'),
('0014', 'avH77EvC', 'OLIVER', 'QUEEN', 'NO. 16 KANO POLY', '+3276365243677', 'Male', 'Receptionist', 'Wed, Thu, Sun'),
('0015', 'usmanee', 'USMAN', 'IBRAHIM', 'KANO, NIGERIA', '+3265325412547', 'Male', 'Manager', 'Mon, Fri, Sat'),
('0016', 'K6RNTr7J', 'MAMY', 'UMAR', '12 BUK RD. KANO', '+3263542154875', 'Female', 'Receptionist', 'Tue, Thu, Sat'),
('0017', 'T5ZAbsYe', 'KHADIJAH', 'AHMAD', '7 STATE RD. KANO', '+3201254875452', 'Female', 'Manager', 'All Days'),
('0018', '2Mhy2XUn', 'BASHIR', 'MUSA', 'DORAYI  KARAMA, KANO', '+6563251458754', 'Male', 'Manager', 'Mon, Fri, Sun'),
('0019', 'duXmjs4g', 'ILYASU', 'MOHD', '34 KANO POLY QTRS', '+3625412548754', 'Male', 'Manager', 'All Days'),
('0020', 'hg5cJgJ9', 'FATIMA', 'ABBAS', '3 FCE KANO, NIGERIA', '+6325125421254', 'Female', 'Manager', 'Mon, Fri, Sat'),
('0021', 'mdTrYhqY', 'AHMAD', 'SAMINU', '12 PH, NIGERIA', '+3632541254799', 'Female', 'Receptionist', 'Mon, Thu, Sat'),
('0022', 'suEQW9UP', 'AMIR', 'MUKTAR', 'DAMBATTA, KANO', '+6325125487563', 'Male', 'Manager', 'All Days'),
('0023', '3YzHw8fQ', 'IHSAN', 'ABDU', 'VIENNA, AUSTRIA', '+6352154525478', 'Female', 'Receptionist', 'Fri, Sat, Sun'),
('0024', 'RyQJRJu2', 'AMINA', 'ABDUL', 'SULTAN ROAD, KANO', '+32365212542', 'Female', 'Manager', 'Mon, Fri, Sat');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
