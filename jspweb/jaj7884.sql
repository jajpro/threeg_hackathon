-- phpMyAdmin SQL Dump
-- version 2.11.5.1
-- http://www.phpmyadmin.net
--
-- 호스트: localhost
-- 처리한 시간: 16-07-02 01:46 
-- 서버 버전: 5.1.45
-- PHP 버전: 5.2.17p1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- 데이터베이스: `jaj7884`
--

-- --------------------------------------------------------

--
-- 테이블 구조 `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `receipt_id` int(11) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- 테이블의 덤프 데이터 `item`
--

INSERT INTO `item` (`item_id`, `product_id`, `amount`, `receipt_id`) VALUES
(1, 3, 4, 1002),
(2, 4, 1, 2003),
(3, 4, 1, 3003),
(4, 5, 1, 4003),
(5, 4, 2, 5003),
(6, 3, 3, 6002),
(7, 3, 1, 7002),
(8, 1, 3, 8002),
(9, 2, 2, 8002),
(10, 3, 1, 8002);

-- --------------------------------------------------------

--
-- 테이블 구조 `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `product_price` int(11) NOT NULL,
  `product_category` varchar(50) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- 테이블의 덤프 데이터 `product`
--

INSERT INTO `product` (`product_id`, `store_id`, `product_name`, `product_price`, `product_category`) VALUES
(1, 2, '치즈버거', 2300, '버거'),
(2, 2, '불고기버거', 3400, '버거'),
(3, 2, '새우버거', 4500, '버거'),
(4, 3, '치즈버거', 2700, '버거'),
(5, 3, '맥더블', 3900, '버거');

-- --------------------------------------------------------

--
-- 테이블 구조 `receipt`
--

CREATE TABLE IF NOT EXISTS `receipt` (
  `receipt_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `card_company` varchar(50) NOT NULL,
  `card_number` int(11) NOT NULL,
  `purchase_date` date NOT NULL,
  PRIMARY KEY (`receipt_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `receipt`
--

INSERT INTO `receipt` (`receipt_id`, `store_id`, `card_company`, `card_number`, `purchase_date`) VALUES
(1002, 2, '농협은행', 1234, '2016-06-30'),
(2003, 3, '신한카드', 4321, '2016-06-30'),
(3003, 3, '농협카드', 2222, '2016-07-01'),
(4003, 3, '농협카드', 2222, '2016-07-01'),
(5003, 3, '농협카드', 2222, '2016-07-01'),
(6002, 2, '신한카드', 7777, '2016-07-01'),
(7002, 2, '하나카드', 5555, '2016-07-01'),
(8002, 2, '농협카드', 4321, '2016-07-01');

-- --------------------------------------------------------

--
-- 테이블 구조 `receipt_user`
--

CREATE TABLE IF NOT EXISTS `receipt_user` (
  `receipt_id` int(11) DEFAULT NULL,
  `nfc_id` varchar(50) NOT NULL,
  UNIQUE KEY `receipt_id` (`receipt_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 테이블의 덤프 데이터 `receipt_user`
--

INSERT INTO `receipt_user` (`receipt_id`, `nfc_id`) VALUES
(1002, '7884'),
(2003, '78844'),
(3003, '2222'),
(4003, '2222'),
(5003, '2222'),
(6002, '7777'),
(7002, '5555'),
(8002, '4567');

-- --------------------------------------------------------

--
-- 테이블 구조 `store`
--

CREATE TABLE IF NOT EXISTS `store` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT,
  `id` char(10) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `store_name` varchar(50) NOT NULL,
  `store_address` varchar(50) NOT NULL,
  PRIMARY KEY (`store_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 테이블의 덤프 데이터 `store`
--

INSERT INTO `store` (`store_id`, `id`, `pwd`, `store_name`, `store_address`) VALUES
(1, 'admin', '1234', '상호명', '주소'),
(2, 'f1', '1234', '롯데리아', '서울 동작구 장승배기로 171 IVY빌딩 1층'),
(3, 'f2', '1234', '맥도날드', '서울 송파구 도곡로 434');
