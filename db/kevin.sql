-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 22, 2020 at 03:57 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kevin`
--

-- --------------------------------------------------------

--
-- Table structure for table `auction_item`
--

CREATE TABLE `auction_item` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `StartPrice` double NOT NULL,
  `Category` varchar(100) NOT NULL,
  `Color` varchar(10) NOT NULL,
  `Size` varchar(100) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `ImagePath` varchar(1000) NOT NULL,
  `DurationDays` int(11) NOT NULL,
  `DurationHours` int(11) NOT NULL,
  `DurationMinutes` int(11) NOT NULL,
  `BidCount` int(11) NOT NULL,
  `Sold` tinyint(1) NOT NULL,
  `HighestBid` int(11) DEFAULT NULL,
  `Buyer` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auction_item`
--

INSERT INTO `auction_item` (`Id`, `Name`, `StartPrice`, `Category`, `Color`, `Size`, `Quantity`, `Description`, `ImagePath`, `DurationDays`, `DurationHours`, `DurationMinutes`, `BidCount`, `Sold`, `HighestBid`, `Buyer`) VALUES
(21, 'Denim', 10000, 'Jeans', '#0ba3f4', 'Small', 8, 'New', 'uploaded/pexels-photo-1082528.jpeg', 1, 0, 0, 0, 0, NULL, NULL),
(22, 'Ankle Heel', 1500, 'Shoes', '#0000ff', 'Medium', 2, 'Striped', 'uploaded/sandals-blue-shoes-strap-shoe-40377.jpeg', 0, 0, 1, 0, 0, NULL, NULL),
(24, 'Sneakers', 6000, 'Shoes', '#0000ff', 'Large', 5, 'Canvas', 'uploaded/pexels-photo (1).jpg', 0, 1, 0, 0, 0, NULL, NULL),
(27, 'Hand Bag', 7500, 'Bag', '#c0c0c0', 'Extra Large', 3, 'Wolf', 'uploaded/pexels-photo-45981.jpeg', 0, 0, 30, 1, 0, 7500, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `bid`
--

CREATE TABLE `bid` (
  `Id` int(11) NOT NULL,
  `Bid` double NOT NULL,
  `ItemId` int(11) NOT NULL,
  `UserId` varchar(50) NOT NULL,
  `BidDate` datetime NOT NULL,
  `BidExpireDate` datetime NOT NULL,
  `CreditCard` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bid`
--

INSERT INTO `bid` (`Id`, `Bid`, `ItemId`, `UserId`, `BidDate`, `BidExpireDate`, `CreditCard`) VALUES
(1, 7500, 27, 'zenj86@gmail.com', '2018-10-06 11:20:25', '2018-10-06 11:50:25', 1623875903452013);

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(100) NOT NULL,
  `ItemId` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `Category` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`Category`) VALUES
('Bag'),
('Cap'),
('Hats'),
('Jacket'),
('Jeans'),
('Shirts'),
('Shoes');

-- --------------------------------------------------------

--
-- Table structure for table `color`
--

CREATE TABLE `color` (
  `Value` varchar(10) NOT NULL,
  `Name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `color`
--

INSERT INTO `color` (`Value`, `Name`) VALUES
('#00ff80', 'Beige'),
('#000000', 'Black'),
('#0000ff', 'Blue'),
('#0ba3f4', 'Light Blue'),
('#c0c0c0', 'Light Grey'),
('#ff80c0', 'Pink'),
('#ff0000', 'Red');

-- --------------------------------------------------------

--
-- Table structure for table `credit_card`
--

CREATE TABLE `credit_card` (
  `CardNo` bigint(20) NOT NULL,
  `Network` varchar(30) NOT NULL,
  `Cvv` int(11) NOT NULL,
  `ExpireDate` datetime NOT NULL,
  `CreditLimit` double NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(500) NOT NULL,
  `Country` varchar(100) NOT NULL,
  `UserId` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credit_card`
--

INSERT INTO `credit_card` (`CardNo`, `Network`, `Cvv`, `ExpireDate`, `CreditLimit`, `Name`, `Address`, `Country`, `UserId`) VALUES
(1587320497523018, 'Visa', 478, '2019-04-13 00:00:00', 1750, 'Sanjana', 'Galle', 'Sri Lanka', 'zenj86@gmail.com'),
(1623875903452013, 'MasterCard', 453, '2022-07-14 00:00:00', 75000, 'Uvindu', 'Galle', 'Sri Lanka', 'zenj86@gmail.com'),
(5769034213975046, 'Visa', 750, '2021-07-23 00:00:00', 35000, 'John', 'New York', 'United States', 'jc@gmail.com'),
(7862034891236820, 'AmericanExpress', 530, '2024-07-11 00:00:00', 15000, 'Anura', 'Bandarawela', 'Sri Lanka', 'aj@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `dispute`
--

CREATE TABLE `dispute` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(100) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `ImagePath` varchar(1000) NOT NULL,
  `Response` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Price` double NOT NULL,
  `Category` varchar(100) NOT NULL,
  `Color` varchar(100) NOT NULL,
  `Size` varchar(100) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `ImagePath` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`Id`, `Name`, `Price`, `Category`, `Color`, `Size`, `Quantity`, `Description`, `ImagePath`) VALUES
(6, 'Heels', 2800, 'Shoes', '#ff0000', 'Medium', 3, 'High Red', 'uploaded/pexels-photo-336372.jpeg'),
(7, 'Loafers', 3200, 'Shoes', '#ff80c0', 'Medium', 0, 'Comfy', 'uploaded/pexels-photo-267320.jpeg'),
(8, 'Wool Hat', 1300, 'Hats', '#00ff80', 'Medium', 7, '100% Wool', 'uploaded/fashion-hat-straw-hat.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `MessageId` int(11) NOT NULL,
  `UserId` varchar(100) NOT NULL,
  `DateTime` datetime NOT NULL,
  `Admin` tinyint(1) NOT NULL DEFAULT 0,
  `Message` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `AdminSeen` tinyint(1) NOT NULL,
  `UserSeen` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`MessageId`, `UserId`, `DateTime`, `Admin`, `Message`, `AdminSeen`, `UserSeen`) VALUES
(103, 'zenj86@gmail.com', '2018-09-23 13:58:24', 0, 'Hello', 1, 1),
(104, 'zenj86@gmail.com', '2018-09-23 13:58:30', 1, 'hhjjjgg', 1, 1),
(105, 'zenj86@gmail.com', '2018-09-23 13:58:45', 1, 'kjjljkl', 1, 1),
(106, 'zenj86@gmail.com', '2018-09-23 13:58:59', 0, 'Hshskak', 1, 1),
(107, 'zenj86@gmail.com', '2018-09-23 13:59:06', 0, 'Jjsjs', 1, 1),
(108, 'zenj86@gmail.com', '2018-09-23 13:59:21', 0, 'Jzjhs ', 1, 1),
(109, 'zenj86@gmail.com', '2018-10-01 21:44:07', 0, 'hello', 1, 1),
(110, 'zenj86@gmail.com', '2018-10-01 21:44:23', 1, 'hello', 1, 1),
(111, 'zenj86@gmail.com', '2018-10-01 21:44:48', 0, 'ddgdfg', 1, 1),
(112, 'madusankan909@gmail.com', '2020-10-18 12:45:09', 0, 'test', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `package`
--

CREATE TABLE `package` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `DiscountPercentage` double NOT NULL,
  `Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `package`
--

INSERT INTO `package` (`Id`, `Name`, `DiscountPercentage`, `Price`) VALUES
(4, 'Gold', 10, 15000),
(5, 'Silver', 7, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(100) NOT NULL,
  `CreditCard` bigint(20) NOT NULL,
  `Price` double NOT NULL,
  `Date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`Id`, `UserId`, `CreditCard`, `Price`, `Date`) VALUES
(6, 'zenj86@gmail.com', 1623875903452013, 15000, '2018-09-19 20:03:20'),
(7, 'zenj86@gmail.com', 1623875903452013, 15200, '2018-09-19 20:04:34'),
(8, 'zenj86@gmail.com', 1623875903452013, 1134.9, '2018-09-19 21:15:10'),
(9, 'jc@gmail.com', 5769034213975046, 10000, '2018-09-20 09:42:28'),
(10, 'jc@gmail.com', 5769034213975046, 4949.46, '2018-09-20 09:43:28'),
(11, 'zenj86@gmail.com', 1623875903452013, 7500, '2018-09-20 16:17:57'),
(12, 'aj@gmail.com', 7862034891236820, 8640, '2018-09-20 19:19:53'),
(13, 'zenj86@gmail.com', 1623875903452013, 2520, '2018-09-22 15:40:33'),
(14, 'zenj86@gmail.com', 1587320497523018, 1500, '2018-09-22 15:47:23'),
(15, 'zenj86@gmail.com', 1623875903452013, 1575, '2018-09-22 15:47:51'),
(16, 'jc@gmail.com', 5769034213975046, 1501.95, '2018-09-23 09:40:45'),
(17, 'zenj86@gmail.com', 1623875903452013, 100000, '2018-09-23 11:07:42'),
(18, 'zenj86@gmail.com', 1587320497523018, 275000, '2018-09-23 11:29:15'),
(19, 'zenj86@gmail.com', 1623875903452013, 4360.5, '2018-09-23 13:19:11'),
(20, 'zenj86@gmail.com', 1623875903452013, 25000, '2018-10-01 20:35:45'),
(21, 'jc@gmail.com', 5769034213975046, 15000, '2018-10-02 08:57:20'),
(22, 'jc@gmail.com', 5769034213975046, 4360.5, '2018-10-02 08:57:49'),
(23, 'zenj86@gmail.com', 1623875903452013, 3404.7000000000003, '2018-10-07 09:18:00');

-- --------------------------------------------------------

--
-- Table structure for table `promo`
--

CREATE TABLE `promo` (
  `Id` int(11) NOT NULL,
  `ItemId` int(11) NOT NULL,
  `Percentage` double NOT NULL,
  `Name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `promo`
--

INSERT INTO `promo` (`Id`, `ItemId`, `Percentage`, `Name`) VALUES
(21, 7, 5, 'Slip Ins'),
(22, 8, 3, 'Wool Hat');

-- --------------------------------------------------------

--
-- Table structure for table `refund`
--

CREATE TABLE `refund` (
  `Id` int(11) NOT NULL,
  `PaymentId` int(11) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `UserId` varchar(100) NOT NULL,
  `Status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `refund`
--

INSERT INTO `refund` (`Id`, `PaymentId`, `Description`, `UserId`, `Status`) VALUES
(3, 8, 'Not Delivered', 'zenj86@gmail.com', 2);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `UserId` varchar(100) NOT NULL,
  `Rating` int(11) NOT NULL,
  `DateTime` datetime NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `Reply` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`UserId`, `Rating`, `DateTime`, `Description`, `Reply`) VALUES
('aj@gmail.com', 3, '2018-10-02 10:07:27', 'High quality products, but delivery service could use some improvements.', NULL),
('cj@gmail.com', 5, '2018-10-02 10:12:33', 'Nice service and everything is high quality.', 'Thank you for the feedback.'),
('jc@gmail.com', 4, '2018-10-02 08:52:21', 'Good service and nice discounts.', NULL),
('jd@gmail.com', 1, '2018-10-02 10:13:04', 'Deliveries are always late.', NULL),
('zenj86@gmail.com', 3, '2018-10-02 13:00:53', 'Application is easy to use and very responsive, but delivery is slow.', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `search`
--

CREATE TABLE `search` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(100) DEFAULT NULL,
  `DateTime` datetime NOT NULL,
  `Query` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `search`
--

INSERT INTO `search` (`Id`, `UserId`, `DateTime`, `Query`) VALUES
(2, NULL, '2018-10-02 12:55:40', 'cap'),
(7, NULL, '2018-10-07 07:31:05', 'hat'),
(11, 'zenj86@gmail.com', '2018-10-07 10:09:06', 'hello');

-- --------------------------------------------------------

--
-- Table structure for table `size`
--

CREATE TABLE `size` (
  `Size` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `size`
--

INSERT INTO `size` (`Size`) VALUES
('Extra Large'),
('Large'),
('Medium'),
('Small');

-- --------------------------------------------------------

--
-- Table structure for table `special_order`
--

CREATE TABLE `special_order` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(100) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `ImagePath` varchar(1000) NOT NULL,
  `Accepted` tinyint(1) NOT NULL,
  `Cost` double NOT NULL,
  `Payed` tinyint(1) NOT NULL,
  `CreditCard` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `special_order`
--

INSERT INTO `special_order` (`Id`, `UserId`, `Description`, `Quantity`, `ImagePath`, `Accepted`, `Cost`, `Payed`, `CreditCard`) VALUES
(7, 'zenj86@gmail.com', 'Trousers', 50, 'orders/pexels-photo-934070.jpeg', 1, 100000, 1, NULL),
(10, 'zenj86@gmail.com', 'Sleeveless Jackets', 25, 'orders/pexels-photo-404159.jpeg', 1, 275000, 1, NULL),
(12, 'zenj86@gmail.com', 'sdsdfs', 25, 'orders/pexels-photo-1021142.jpeg', 1, 3000, 0, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Telephone` varchar(10) NOT NULL,
  `Address` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `track`
--

CREATE TABLE `track` (
  `Id` int(11) NOT NULL,
  `ItemId` int(11) NOT NULL,
  `UserId` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `track`
--

INSERT INTO `track` (`Id`, `ItemId`, `UserId`) VALUES
(1, 21, 'zenj86@gmail.com'),
(2, 22, 'zenj86@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Email` varchar(100) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Telephone` varchar(10) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `PostalNumber` varchar(20) NOT NULL,
  `Street` varchar(50) NOT NULL,
  `City` varchar(50) NOT NULL,
  `District` varchar(50) NOT NULL,
  `Package` int(11) DEFAULT NULL,
  `CreditCard` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Email`, `FirstName`, `LastName`, `Telephone`, `Password`, `PostalNumber`, `Street`, `City`, `District`, `Package`, `CreditCard`) VALUES
('aj@gmail.com', 'Anura', 'Jayawardana', '0715295324', 'ab12AB!@', '26/B', 'KingsSt', 'Bandarawela', 'Badulla', NULL, NULL),
('cj@gmail.com', 'Carl', 'Johnson', '1234567890', 'ab12AB!@', '56/3', 'Groove', 'LosSantos', 'California', 0, 0),
('jc@gmail.com', 'John', 'Cena', '5678023452', 'ab12AB!@', '89/D', 'BroomeSt', 'NewYork', 'NYC', 4, 5769034213975046),
('jd@gmail.com', 'John', 'Doe', '1325687410', 'ab12AB!@', '12P', 'PryeSt', 'Detroit', 'Michigan', 0, 0),
('madusankan909@gmail.com', 'Nimesh', 'Madusanka', '0771316633', 'Kk123456@', '00000', 'Vimalasara', 'Galle', 'Galle', NULL, NULL),
('zenj86@gmail.com', 'Uvindu', 'Sanjana', '0771003087', 'ab12AB!@', '186/A', 'Panwiladuwawatta', 'Karapitiya', 'Galle', 4, 1623875903452013);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `auction_item`
--
ALTER TABLE `auction_item`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_auction_item_category` (`Category`),
  ADD KEY `fk_auction_item_color` (`Color`),
  ADD KEY `fk_auction_item_size` (`Size`);

--
-- Indexes for table `bid`
--
ALTER TABLE `bid`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_bid_auction` (`ItemId`),
  ADD KEY `fk_bid_user` (`UserId`),
  ADD KEY `fk_bid_credit_card` (`CreditCard`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_cart_user` (`UserId`),
  ADD KEY `fk_cart_item` (`ItemId`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`Category`);

--
-- Indexes for table `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`Value`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Indexes for table `credit_card`
--
ALTER TABLE `credit_card`
  ADD PRIMARY KEY (`CardNo`),
  ADD KEY `fk_credit_card_user` (`UserId`);

--
-- Indexes for table `dispute`
--
ALTER TABLE `dispute`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_dispute_user` (`UserId`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_item_size` (`Size`) USING BTREE,
  ADD KEY `fk_item_category` (`Category`),
  ADD KEY `fk_item_color` (`Color`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`MessageId`),
  ADD KEY `fk_message_user` (`UserId`);

--
-- Indexes for table `package`
--
ALTER TABLE `package`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_payment_user` (`UserId`),
  ADD KEY `fk_payment_credit_card` (`CreditCard`);

--
-- Indexes for table `promo`
--
ALTER TABLE `promo`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `ItemId` (`ItemId`);

--
-- Indexes for table `refund`
--
ALTER TABLE `refund`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_refund_payment` (`PaymentId`),
  ADD KEY `fk_refund_user` (`UserId`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`UserId`);

--
-- Indexes for table `search`
--
ALTER TABLE `search`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_search_user` (`UserId`);

--
-- Indexes for table `size`
--
ALTER TABLE `size`
  ADD PRIMARY KEY (`Size`);

--
-- Indexes for table `special_order`
--
ALTER TABLE `special_order`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_special_order_user` (`UserId`),
  ADD KEY `fk_special_order_credit_card` (`CreditCard`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `track`
--
ALTER TABLE `track`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `fk_track_user` (`UserId`),
  ADD KEY `ItemId` (`ItemId`) USING BTREE;

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `auction_item`
--
ALTER TABLE `auction_item`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `bid`
--
ALTER TABLE `bid`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `dispute`
--
ALTER TABLE `dispute`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `MessageId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- AUTO_INCREMENT for table `package`
--
ALTER TABLE `package`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `promo`
--
ALTER TABLE `promo`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `refund`
--
ALTER TABLE `refund`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `search`
--
ALTER TABLE `search`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `special_order`
--
ALTER TABLE `special_order`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `track`
--
ALTER TABLE `track`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `auction_item`
--
ALTER TABLE `auction_item`
  ADD CONSTRAINT `fk_auction_item_category` FOREIGN KEY (`Category`) REFERENCES `category` (`Category`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_auction_item_color` FOREIGN KEY (`Color`) REFERENCES `color` (`Value`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_auction_item_size` FOREIGN KEY (`Size`) REFERENCES `size` (`Size`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `bid`
--
ALTER TABLE `bid`
  ADD CONSTRAINT `fk_bid_auction` FOREIGN KEY (`ItemId`) REFERENCES `auction_item` (`Id`),
  ADD CONSTRAINT `fk_bid_credit_card` FOREIGN KEY (`CreditCard`) REFERENCES `credit_card` (`CardNo`),
  ADD CONSTRAINT `fk_bid_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON UPDATE CASCADE;

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `fk_cart_item` FOREIGN KEY (`ItemId`) REFERENCES `item` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_cart_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `credit_card`
--
ALTER TABLE `credit_card`
  ADD CONSTRAINT `fk_credit_card_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `dispute`
--
ALTER TABLE `dispute`
  ADD CONSTRAINT `fk_dispute_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `dk_item_size` FOREIGN KEY (`Size`) REFERENCES `size` (`Size`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_item_category` FOREIGN KEY (`Category`) REFERENCES `category` (`Category`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_item_color` FOREIGN KEY (`Color`) REFERENCES `color` (`Value`) ON UPDATE NO ACTION;

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `fk_message_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_payment_credit_card` FOREIGN KEY (`CreditCard`) REFERENCES `credit_card` (`CardNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_payment_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `promo`
--
ALTER TABLE `promo`
  ADD CONSTRAINT `fk_promo_item` FOREIGN KEY (`ItemId`) REFERENCES `item` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `refund`
--
ALTER TABLE `refund`
  ADD CONSTRAINT `fk_refund_payment` FOREIGN KEY (`PaymentId`) REFERENCES `payment` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_refund_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fk_review` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `search`
--
ALTER TABLE `search`
  ADD CONSTRAINT `fk_search_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `special_order`
--
ALTER TABLE `special_order`
  ADD CONSTRAINT `fk_special_order_credit_card` FOREIGN KEY (`CreditCard`) REFERENCES `credit_card` (`CardNo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_special_order_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `track`
--
ALTER TABLE `track`
  ADD CONSTRAINT `fk_track_auction` FOREIGN KEY (`ItemId`) REFERENCES `auction_item` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_track_user` FOREIGN KEY (`UserId`) REFERENCES `user` (`Email`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
