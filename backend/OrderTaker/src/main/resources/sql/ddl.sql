/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : order_taker

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 24/05/2023 13:05:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administer
-- ----------------------------
DROP TABLE IF EXISTS `administer`;
CREATE TABLE `administer` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `admin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                              `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `actable_uni_admin_name` (`admin_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for blacklist
-- ----------------------------
DROP TABLE IF EXISTS `blacklist`;
CREATE TABLE `blacklist` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `courier_id` bigint DEFAULT NULL,
                             `created_time` datetime DEFAULT NULL,
                             `deleted` int DEFAULT 0,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `client_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                          `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                          `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `actable_uni_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for courier
-- ----------------------------
DROP TABLE IF EXISTS `courier`;
CREATE TABLE `courier` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `courier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                           `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                           `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                           `rating` double(10,0) DEFAULT NULL,
                           `block_times` int DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `actable_uni_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for delivery_address
-- ----------------------------
DROP TABLE IF EXISTS `delivery_address`;
CREATE TABLE `delivery_address` (
                                    `id` bigint NOT NULL AUTO_INCREMENT,
                                    `client_id` bigint DEFAULT NULL,
                                    `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                    `phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                    `receiver` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `client_id` bigint DEFAULT NULL,
                           `courier_id` bigint DEFAULT NULL,
                           `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `sender_id` bigint DEFAULT NULL,
                           `time` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `client_id` bigint DEFAULT NULL,
                         `courier_id` bigint DEFAULT NULL,
                         `payment_id` bigint DEFAULT NULL,
                         `pick_up_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `delivery_address_id` bigint DEFAULT NULL,
                         `order_tracking_id` bigint DEFAULT NULL,
                         `current_status` int DEFAULT NULL,
                         `review_id` bigint DEFAULT NULL,
                         `service_type` int DEFAULT NULL,
                         `expected_arrived_time` datetime DEFAULT NULL,
                         `fee` int DEFAULT NULL,
                         `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `restaurant` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `estimated_arrived_time` datetime DEFAULT NULL,
                         `total_price` bigint DEFAULT NULL,
                         `pick_up_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `size` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `weight` double(20,0) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for order_tracking
-- ----------------------------
DROP TABLE IF EXISTS `order_tracking`;
CREATE TABLE `order_tracking` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `order_id` bigint DEFAULT NULL,
                                  `created_time` datetime DEFAULT NULL,
                                  `placed_time` datetime DEFAULT NULL,
                                  `transited_time` datetime DEFAULT NULL,
                                  `arrived_time` datetime DEFAULT NULL,
                                  `confirmed_time` datetime DEFAULT NULL,
                                  `cancelled_time` datetime DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `transaction_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `order_id` bigint DEFAULT NULL,
                           `status` int DEFAULT NULL,
                           `created_time` datetime DEFAULT NULL,
                           `updated_time` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `order_id` bigint DEFAULT NULL,
                          `rating` double(10,0) DEFAULT NULL,
                          `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `client_id` bigint DEFAULT NULL,
                          `courier_id` bigint DEFAULT NULL,
                          `created_time` datetime DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

SET FOREIGN_KEY_CHECKS = 1;
