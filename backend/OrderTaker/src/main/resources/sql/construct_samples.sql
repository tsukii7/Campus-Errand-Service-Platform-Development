-- ----------------------------
-- Table structure for administer
-- ----------------------------
TRUNCATE TABLE `administer`;

INSERT INTO order_taker.administer (id, admin_name, password) VALUES (1, 'admin', '7c4a8d09ca3762af61e59520943dc26494f8941b');


-- ----------------------------
-- Table structure for blacklist
-- ----------------------------
TRUNCATE TABLE `blacklist`;

INSERT INTO order_taker.blacklist (id, courier_id, created_time, deleted) VALUES (1, 2, '2020-05-30 12:00:00', 1);
INSERT INTO order_taker.blacklist (id, courier_id, created_time, deleted) VALUES (2, 3, '2021-05-30 12:00:00', 1);
INSERT INTO order_taker.blacklist (id, courier_id, created_time, deleted) VALUES (3, 3, '2022-05-30 12:00:00', 1);
INSERT INTO order_taker.blacklist (id, courier_id, created_time, deleted) VALUES (4, 3, '2023-05-30 12:00:00', 0);


-- ----------------------------
-- Table structure for client
-- ----------------------------
TRUNCATE TABLE `client`;

INSERT INTO order_taker.client (id, client_name, avatar_url, email, password) VALUES (1, 'client1', 'https://i.postimg.cc/j2TkYgy8/1.jpg', '111@qq.com', '6216f8a75fd5bb3d5f22b6f9958cdede3fc086c2');
INSERT INTO order_taker.client (id, client_name, avatar_url, email, password) VALUES (2, 'client2', 'https://i.postimg.cc/FzxCryZh/2.jpg', '222@qq.com', '1c6637a8f2e1f75e06ff9984894d6bd16a3a36a9');
INSERT INTO order_taker.client (id, client_name, avatar_url, email, password) VALUES (3, 'client3', 'https://i.postimg.cc/SND1HGRr/3.jpg', '333@qq.com', '43814346e21444aaf4f70841bf7ed5ae93f55a9d');


-- ----------------------------
-- Table structure for courier
-- ----------------------------
TRUNCATE TABLE `courier`;

INSERT INTO order_taker.courier (id, courier_name, avatar_url, email, password, rating, block_times) VALUES (1, 'courier1', 'https://i.postimg.cc/Gmf7mhkT/4.jpg', '111@courier.com', '6216f8a75fd5bb3d5f22b6f9958cdede3fc086c2', 5, 0);
INSERT INTO order_taker.courier (id, courier_name, avatar_url, email, password, rating, block_times) VALUES (2, 'courier2', 'https://i.postimg.cc/W35pk90h/default.png', '222@courier.com', '1c6637a8f2e1f75e06ff9984894d6bd16a3a36a9', 3, 1);
INSERT INTO order_taker.courier (id, courier_name, avatar_url, email, password, rating, block_times) VALUES (3, 'courier3', 'https://i.postimg.cc/4y9DxgMH/admin.jpg', '333@courier.com', '43814346e21444aaf4f70841bf7ed5ae93f55a9d', 4, 3);


-- ----------------------------
-- Table structure for delivery_address
-- ----------------------------
TRUNCATE TABLE `delivery_address`;

INSERT INTO order_taker.delivery_address (id, client_id, address, phone_number, receiver) VALUES (1, 1, '二期16栋', '13800000001', '莉莉丝');
INSERT INTO order_taker.delivery_address (id, client_id, address, phone_number, receiver) VALUES (2, 1, '商学院', '13800000001', '莉莉宝');
INSERT INTO order_taker.delivery_address (id, client_id, address, phone_number, receiver) VALUES (3, 2, '湖畔2栋', '13800000002', '亚伯');
INSERT INTO order_taker.delivery_address (id, client_id, address, phone_number, receiver) VALUES (4, 3, '荔园1栋', '13800000003', '以撒');


-- ----------------------------
-- Table structure for message
-- ----------------------------
TRUNCATE TABLE `message`;

INSERT INTO order_taker.message (id, client_id, courier_id, content, sender_id, time) VALUES (1, 1, 3, '到了吗？', 1, '2022-05-08 11:45:30');
INSERT INTO order_taker.message (id, client_id, courier_id, content, sender_id, time) VALUES (2, 1, 3, '到了吗？？', 1, '2022-05-08 11:46:30');
INSERT INTO order_taker.message (id, client_id, courier_id, content, sender_id, time) VALUES (3, 1, 3, '到了吗？？？', 1, '2022-05-08 11:47:30');
INSERT INTO order_taker.message (id, client_id, courier_id, content, sender_id, time) VALUES (4, 1, 3, '到了吗？？？？', 1, '2022-05-08 11:48:30');
INSERT INTO order_taker.message (id, client_id, courier_id, content, sender_id, time) VALUES (5, 1, 3, '到了吗？？？？？', 1, '2022-05-08 11:49:30');
INSERT INTO order_taker.message (id, client_id, courier_id, content, sender_id, time) VALUES (6, 1, 3, '到了', 3, '2022-05-08 11:50:30');

-- ----------------------------
-- Table structure for order
-- ----------------------------
TRUNCATE TABLE `order`;

-- -------takeout order--------

INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (1, 1, null, null, '六号门', 1, 1, 0, null, 0, '2023-05-01 12:00:00', 10, null, '麦当劳', '2020-05-01 11:30:00', 11, null, null, null);
INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (2, 1, 1, null, '一号门', 1, 2, 1, null, 0, '2023-05-02 12:00:00', 20, null, 'KFC', '2020-05-02 11:30:00', 22, null, null, null);
INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (3, 1, 1, null, '七号门', 1, 3, 2, null, 0, '2023-05-03 12:00:00', 30, '尽快送达', '尊宝披萨', '2020-05-03 11:30:00', 33, null, null, null);
INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (4, 1, 1, null, '六号门', 1, 4, 3, null, 0, '2020-05-04 12:00:00', 40, '放地上就行', '南里奥', '2020-05-04 11:30:00', 44, null, null, null);
INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (5, 1, 1, null, '六号门', 1, 5, 4, 1, 0, '2020-05-05 12:00:00', 50, null, '渝月', '2020-05-05 11:30:00', 55, null, null, null);
INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (6, 1, 2, 1, '六号门', 2, 6, 5, null, 0, '2020-05-06 12:00:00', 60, null, '德克士', '2020-05-06 11:30:00', 66, null, null, null);
INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (7, 1, null, null, '六号门', 2, 7, 6, 2, 0, '2021-05-07 12:00:00', 70, null, '蜜雪冰城', '2021-05-07 11:30:00', 77, null, null, null);
INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (8, 1, 3, 2, '5栋收发室', 2, 8, 5, 3, 0, '2022-05-08 12:00:00', 80, '送快点，上次太慢了', '喜姐炸串', '2022-05-08 11:30:00', 88, null, null, null);
INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (9, 1, 3, 3, '11栋101', 2, 9, 5, 4, 0, '2023-05-09 12:00:00', 90, '送快点！', '米其林', '2023-05-09 11:30:00', 99, null, null, null);
INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (12, 1, 1, null, '六号门', 1, 12, 3, null, 0, '2023-05-12 12:00:00', 120, null, null, '2023-05-12 11:30:00', 1212, null, null, null);

-- -------express order--------

INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (10, 2, 1, 4, '六号门', 3, 10, 5, null, 1, '2023-05-10 12:00:00', 100, null, null, null, null, '510-3-7001', '键盘大小', 1);

-- ------flash order------------

INSERT INTO order_taker.`order` (id, client_id, courier_id, payment_id, pick_up_address, delivery_address_id, order_tracking_id, current_status, review_id, service_type, expected_arrived_time, fee, description, restaurant, estimated_arrived_time, total_price, pick_up_code, size, weight) VALUES (11, 3, 1, 5, '六号门', 4, 11, 5, null, 2, '2023-05-11 12:00:00', 110, null, null, null, null, null, null, null);


-- ----------------------------
-- Table structure for order_tracking
-- ----------------------------
TRUNCATE TABLE `order_tracking`;

INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (1, 1, '2020-05-01 10:30:47', '2020-05-01 11:30:47', null, null, null, null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (2, 2, '2020-05-02 10:30:00', '2020-05-02 11:30:00', null, null, null, null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (3, 3, '2020-05-03 10:30:00', '2020-05-03 11:30:00', '2020-05-03 11:40:00', null, null, null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (4, 4, '2020-05-04 10:30:00', '2020-05-04 11:30:00', null, null, null, null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (5, 5, '2020-05-05 10:30:00', '2020-05-05 11:30:00', '2020-05-05 11:40:00', '2020-05-05 11:50:00', null, null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (6, 6, '2020-05-06 10:30:00', '2020-05-06 11:30:00', '2020-05-06 11:40:00', '2020-05-06 11:50:00', '2020-05-06 12:10:00', null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (7, 7, '2021-05-07 10:30:00', '2021-05-07 11:30:00', null, null, null, '2021-05-08 11:45:00');
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (8, 8, '2022-05-08 10:30:00', '2022-05-08 11:30:00', '2022-05-08 11:40:00', '2022-05-08 11:50:00', '2022-05-08 12:10:00', null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (9, 9, '2023-05-09 10:30:00', '2023-05-09 11:30:00', '2023-05-09 11:40:00', '2023-05-09 11:50:00', '2023-05-09 12:10:00', null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (10, 10, '2023-05-10 10:30:00', null, '2023-05-10 11:40:00', '2023-05-10 11:50:00', '2023-05-10 12:10:00', null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (11, 11, '2023-05-11 10:30:00', null, '2023-05-11 11:40:00', '2023-05-11 11:50:00', '2023-05-11 12:10:00', null);
INSERT INTO order_taker.order_tracking (id, order_id, created_time, placed_time, transited_time, arrived_time, confirmed_time, cancelled_time) VALUES (12, 12, '2023-05-12 10:30:00', '2023-05-12 11:30:00', '2023-05-12 11:40:00', null, null, null);


-- ----------------------------
-- Table structure for payment
-- ----------------------------
TRUNCATE TABLE `payment`;

INSERT INTO order_taker.payment (id, transaction_id, order_id, status, created_time, updated_time) VALUES (1, '11', 6, 0, '2020-05-06 12:15:00', null);
INSERT INTO order_taker.payment (id, transaction_id, order_id, status, created_time, updated_time) VALUES (2, '22', 8, 1, '2022-05-08 12:15:00', '2022-05-08 12:20:00');
INSERT INTO order_taker.payment (id, transaction_id, order_id, status, created_time, updated_time) VALUES (3, '33', 9, 1, '2023-05-09 12:15:00', '2023-05-09 12:20:00');
INSERT INTO order_taker.payment (id, transaction_id, order_id, status, created_time, updated_time) VALUES (4, '44', 10, 1, '2023-05-10 12:15:00', '2023-05-10 12:20:00');
INSERT INTO order_taker.payment (id, transaction_id, order_id, status, created_time, updated_time) VALUES (5, '55', 11, 2, '2023-05-11 12:15:00', '2023-05-11 12:20:00');


-- ----------------------------
-- Table structure for review
-- ----------------------------
TRUNCATE TABLE `review`;

INSERT INTO order_taker.review (id, order_id, rating, comment, client_id, courier_id, created_time) VALUES (1, 5, 5, '快1', 1, 1, '2020-05-05 13:00:00');
INSERT INTO order_taker.review (id, order_id, rating, comment, client_id, courier_id, created_time) VALUES (2, 7, 1, '很慢2', 1, 3, '2020-05-07 13:00:00');
INSERT INTO order_taker.review (id, order_id, rating, comment, client_id, courier_id, created_time) VALUES (3, 8, 1, '非常慢3', 1, 3, '2020-05-08 13:00:00');
INSERT INTO order_taker.review (id, order_id, rating, comment, client_id, courier_id, created_time) VALUES (4, 9, 1, '是真的慢4', 1, 3, '2020-05-09 13:00:00');
