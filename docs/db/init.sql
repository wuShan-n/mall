-- Mall Test Data SQL
-- Generated on: 2025-09-08 14:43:40
-- Warning: This will insert test data into your database

SET FOREIGN_KEY_CHECKS = 0;


-- User Service Data
USE mall_user;

-- Users
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (1, 'user_000001', 'encrypted_password_hash', '17022768040', 'user_hsahxthv@qq.com', '王强', 1, 1, 2, '2024-11-07 23:54:06');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (2, 'user_000002', 'encrypted_password_hash', '14713332041', 'user_7ckw5ngc@163.com', '陈芳', 0, 1, 1, '2025-02-03 05:45:39');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (3, 'user_000003', 'encrypted_password_hash', '14633229661', 'user_0fn9xuy4@qq.com', '刘伟', 1, 1, 2, '2024-12-21 14:15:13');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (4, 'user_000004', 'encrypted_password_hash', '13957248611', 'user_0t0pvn9e@outlook.com', '李伟', 0, 1, 1, '2025-05-10 06:56:55');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (5, 'user_000005', 'encrypted_password_hash', '14653542596', 'user_e511mkfa@gmail.com', '周娜', 2, 1, 1, '2025-05-09 10:56:52');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (6, 'user_000006', 'encrypted_password_hash', '15661757503', 'user_a753lc58@qq.com', '黄芳', 2, 1, 1, '2024-10-28 11:25:22');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (7, 'user_000007', 'encrypted_password_hash', '17915699767', 'user_oyn6qica@126.com', '刘勇', 0, 1, 1, '2024-10-06 04:25:52');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (8, 'user_000008', 'encrypted_password_hash', '17000352457', 'user_5uirodxm@outlook.com', '黄伟', 2, 1, 3, '2025-05-17 13:33:34');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (9, 'user_000009', 'encrypted_password_hash', '13438214422', 'user_7p5tb948@163.com', '王强', 1, 1, 2, '2024-11-30 14:59:49');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (10, 'user_000010', 'encrypted_password_hash', '19910397267', 'user_2xrghc0c@gmail.com', '张伟', 2, 1, 2, '2025-03-22 21:37:58');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (11, 'user_000011', 'encrypted_password_hash', '19505800849', 'user_uubcxulj@gmail.com', '刘静', 1, 1, 1, '2025-05-27 11:20:03');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (12, 'user_000012', 'encrypted_password_hash', '17499065055', 'user_emcikptk@126.com', '张洋', 1, 1, 3, '2024-10-18 20:13:26');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (13, 'user_000013', 'encrypted_password_hash', '19111526726', 'user_jr64dpja@163.com', '陈娜', 2, 1, 2, '2025-04-10 20:45:43');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (14, 'user_000014', 'encrypted_password_hash', '17519610354', 'user_bn78bmyy@gmail.com', '周强', 2, 1, 3, '2024-11-07 15:16:09');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (15, 'user_000015', 'encrypted_password_hash', '19217141974', 'user_y0ifznbq@163.com', '黄丽', 1, 1, 1, '2024-12-04 04:31:33');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (16, 'user_000016', 'encrypted_password_hash', '13138926395', 'user_7e8g8jdp@gmail.com', '赵勇', 2, 1, 1, '2025-02-05 05:00:33');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (17, 'user_000017', 'encrypted_password_hash', '19104586971', 'user_p87wxe6s@outlook.com', '杨强', 2, 1, 2, '2025-04-11 22:13:35');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (18, 'user_000018', 'encrypted_password_hash', '13496963502', 'user_puxqphr6@163.com', '李静', 2, 1, 3, '2025-05-12 10:09:50');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (19, 'user_000019', 'encrypted_password_hash', '17092381096', 'user_1q5uznof@qq.com', '李强', 0, 1, 1, '2025-07-17 21:34:29');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (20, 'user_000020', 'encrypted_password_hash', '19502848695', 'user_86wzs3t6@outlook.com', '黄静', 0, 1, 3, '2024-12-25 07:27:24');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (21, 'user_000021', 'encrypted_password_hash', '18562022351', 'user_iyzcotoh@outlook.com', '赵勇', 2, 1, 1, '2025-08-07 08:02:56');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (22, 'user_000022', 'encrypted_password_hash', '19549238857', 'user_26rjrny2@163.com', '黄娜', 2, 1, 3, '2024-09-19 02:04:23');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (23, 'user_000023', 'encrypted_password_hash', '19560481234', 'user_m5k8p4q0@qq.com', '杨敏', 2, 1, 1, '2025-07-09 00:20:51');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (24, 'user_000024', 'encrypted_password_hash', '19002862419', 'user_qjnv8z2f@gmail.com', '李勇', 0, 1, 2, '2025-04-20 05:32:32');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (25, 'user_000025', 'encrypted_password_hash', '17937066178', 'user_ve92mpns@gmail.com', '张强', 1, 1, 1, '2025-02-23 23:27:31');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (26, 'user_000026', 'encrypted_password_hash', '19687479285', 'user_rp0j43d5@163.com', '黄勇', 2, 1, 3, '2025-02-02 19:09:50');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (27, 'user_000027', 'encrypted_password_hash', '15434322585', 'user_sgiotirz@outlook.com', '张芳', 1, 1, 1, '2025-02-12 16:01:10');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (28, 'user_000028', 'encrypted_password_hash', '17354547372', 'user_jeget1gh@outlook.com', '陈勇', 2, 1, 3, '2024-12-27 12:28:00');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (29, 'user_000029', 'encrypted_password_hash', '19823371002', 'user_7w1dakrm@qq.com', '陈磊', 0, 1, 1, '2025-02-11 05:35:30');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (30, 'user_000030', 'encrypted_password_hash', '16561890254', 'user_vw1n6kvp@126.com', '吴伟', 2, 1, 3, '2024-10-17 03:39:06');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (31, 'user_000031', 'encrypted_password_hash', '19226110045', 'user_kizzqy72@126.com', '刘强', 0, 1, 3, '2024-12-04 22:06:13');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (32, 'user_000032', 'encrypted_password_hash', '17980137521', 'user_yz7srcbp@gmail.com', '吴静', 0, 1, 1, '2024-12-06 12:27:28');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (33, 'user_000033', 'encrypted_password_hash', '19859962074', 'user_x6gvwrdm@gmail.com', '杨芳', 0, 1, 2, '2025-02-15 11:32:17');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (34, 'user_000034', 'encrypted_password_hash', '17298543394', 'user_s9xtogn1@outlook.com', '张敏', 1, 1, 3, '2024-10-29 11:51:34');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (35, 'user_000035', 'encrypted_password_hash', '19461962155', 'user_d96qe3rz@126.com', '陈强', 1, 1, 2, '2024-12-12 06:27:52');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (36, 'user_000036', 'encrypted_password_hash', '16115710783', 'user_2ja04ux5@outlook.com', '周磊', 1, 1, 2, '2025-04-10 22:58:15');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (37, 'user_000037', 'encrypted_password_hash', '18398923843', 'user_r6n92xf8@163.com', '周勇', 1, 1, 1, '2025-08-02 19:03:57');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (38, 'user_000038', 'encrypted_password_hash', '16547461493', 'user_yx444nlz@outlook.com', '周伟', 2, 1, 1, '2024-12-08 18:56:42');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (39, 'user_000039', 'encrypted_password_hash', '17997406846', 'user_6b2knftu@163.com', '王娜', 0, 1, 3, '2025-02-04 04:17:56');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (40, 'user_000040', 'encrypted_password_hash', '17291422089', 'user_yfpmvxpj@gmail.com', '刘强', 1, 1, 1, '2024-12-09 17:38:42');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (41, 'user_000041', 'encrypted_password_hash', '15326083087', 'user_960361i2@163.com', '刘勇', 1, 1, 3, '2025-06-29 01:13:07');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (42, 'user_000042', 'encrypted_password_hash', '18686963457', 'user_dk0get8t@gmail.com', '王静', 1, 1, 2, '2025-06-25 13:09:54');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (43, 'user_000043', 'encrypted_password_hash', '19840726638', 'user_udojen0j@outlook.com', '杨芳', 2, 1, 1, '2025-03-10 17:12:38');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (44, 'user_000044', 'encrypted_password_hash', '13859208934', 'user_f8krd5eq@126.com', '赵勇', 2, 1, 3, '2024-11-07 14:59:08');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (45, 'user_000045', 'encrypted_password_hash', '19686926242', 'user_n9n6xcj8@outlook.com', '李敏', 2, 1, 3, '2025-04-28 12:31:53');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (46, 'user_000046', 'encrypted_password_hash', '19039728558', 'user_i12vj99t@163.com', '李伟', 0, 1, 1, '2025-01-25 17:47:16');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (47, 'user_000047', 'encrypted_password_hash', '18231075747', 'user_sds8b25s@outlook.com', '张伟', 1, 1, 2, '2025-02-21 03:53:09');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (48, 'user_000048', 'encrypted_password_hash', '17490060265', 'user_l9tqvdz4@126.com', '刘强', 1, 1, 1, '2025-02-16 07:44:59');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (49, 'user_000049', 'encrypted_password_hash', '15440463190', 'user_3dd03uvu@gmail.com', '李强', 1, 1, 3, '2025-06-26 23:53:01');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (50, 'user_000050', 'encrypted_password_hash', '15902315148', 'user_muekipu1@126.com', '吴洋', 0, 1, 3, '2025-05-22 02:09:33');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (51, 'user_000051', 'encrypted_password_hash', '18217330854', 'user_fwb0hpmn@gmail.com', '吴勇', 0, 1, 1, '2024-11-08 00:50:15');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (52, 'user_000052', 'encrypted_password_hash', '14662644271', 'user_pkyrybov@qq.com', '刘静', 0, 1, 1, '2025-07-02 09:13:24');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (53, 'user_000053', 'encrypted_password_hash', '14307137588', 'user_c5ba75uu@outlook.com', '陈芳', 1, 1, 1, '2025-01-12 17:47:11');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (54, 'user_000054', 'encrypted_password_hash', '14946341440', 'user_50h2cwtg@gmail.com', '杨娜', 0, 1, 1, '2024-11-04 11:41:12');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (55, 'user_000055', 'encrypted_password_hash', '14117096455', 'user_y5x9l8lp@outlook.com', '黄静', 1, 1, 3, '2024-09-30 10:17:23');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (56, 'user_000056', 'encrypted_password_hash', '15040326905', 'user_nu80b6ug@126.com', '黄洋', 0, 1, 1, '2025-03-02 13:54:27');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (57, 'user_000057', 'encrypted_password_hash', '13837512445', 'user_fnkomv2x@qq.com', '杨芳', 2, 1, 3, '2025-02-05 17:46:02');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (58, 'user_000058', 'encrypted_password_hash', '14769835139', 'user_zov7ln23@126.com', '李强', 2, 1, 2, '2024-09-15 16:38:55');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (59, 'user_000059', 'encrypted_password_hash', '15129421081', 'user_145bm1en@163.com', '王勇', 0, 1, 3, '2025-02-25 14:08:22');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (60, 'user_000060', 'encrypted_password_hash', '13379677229', 'user_26gyup12@126.com', '周强', 0, 1, 1, '2025-07-16 19:00:18');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (61, 'user_000061', 'encrypted_password_hash', '17804397510', 'user_fqs3d635@qq.com', '赵伟', 1, 1, 3, '2025-01-31 20:18:05');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (62, 'user_000062', 'encrypted_password_hash', '14930986712', 'user_3zezqacj@163.com', '周勇', 2, 1, 2, '2024-10-21 19:03:51');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (63, 'user_000063', 'encrypted_password_hash', '18212660845', 'user_wuzkamug@qq.com', '周丽', 0, 1, 3, '2025-04-07 21:56:21');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (64, 'user_000064', 'encrypted_password_hash', '16953469207', 'user_m0w10lbt@163.com', '黄磊', 2, 1, 1, '2025-07-20 18:26:03');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (65, 'user_000065', 'encrypted_password_hash', '13790278333', 'user_3rbxk5bx@gmail.com', '李丽', 1, 1, 2, '2025-06-24 06:51:27');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (66, 'user_000066', 'encrypted_password_hash', '16931637204', 'user_p9gkdlkq@outlook.com', '王磊', 1, 1, 2, '2024-09-25 01:30:23');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (67, 'user_000067', 'encrypted_password_hash', '18964670645', 'user_0fb4ypei@gmail.com', '杨强', 2, 1, 1, '2025-05-01 14:37:13');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (68, 'user_000068', 'encrypted_password_hash', '14434013329', 'user_ags6e1zh@126.com', '陈磊', 0, 1, 1, '2024-10-31 07:48:29');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (69, 'user_000069', 'encrypted_password_hash', '18484068330', 'user_t64z7w2s@qq.com', '刘娜', 0, 1, 2, '2024-09-18 19:42:45');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (70, 'user_000070', 'encrypted_password_hash', '15549180374', 'user_oouelcqs@163.com', '周勇', 0, 1, 1, '2025-02-23 15:01:20');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (71, 'user_000071', 'encrypted_password_hash', '16885319295', 'user_kch8jwwd@163.com', '吴勇', 0, 1, 1, '2025-05-22 10:33:47');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (72, 'user_000072', 'encrypted_password_hash', '17939080972', 'user_huwb6txk@163.com', '赵静', 1, 1, 1, '2025-06-03 01:04:17');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (73, 'user_000073', 'encrypted_password_hash', '13442637789', 'user_enulqjid@gmail.com', '赵勇', 1, 1, 1, '2024-11-09 15:14:11');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (74, 'user_000074', 'encrypted_password_hash', '18971194840', 'user_oqivbkv4@outlook.com', '陈洋', 0, 1, 1, '2025-02-22 23:15:56');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (75, 'user_000075', 'encrypted_password_hash', '13234005323', 'user_qt3wvedn@gmail.com', '周丽', 0, 1, 1, '2025-04-30 14:05:22');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (76, 'user_000076', 'encrypted_password_hash', '16501344653', 'user_wf90km3t@126.com', '陈磊', 0, 1, 2, '2025-01-15 06:06:16');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (77, 'user_000077', 'encrypted_password_hash', '17957760497', 'user_1uzvgww4@163.com', '刘伟', 2, 1, 3, '2024-10-21 07:01:41');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (78, 'user_000078', 'encrypted_password_hash', '14444256487', 'user_zsu9rn8q@126.com', '杨洋', 1, 1, 3, '2025-07-05 10:11:38');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (79, 'user_000079', 'encrypted_password_hash', '19682492299', 'user_1zyrru7z@gmail.com', '李勇', 2, 1, 3, '2025-02-03 01:44:02');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (80, 'user_000080', 'encrypted_password_hash', '19337109096', 'user_0h3jujd0@163.com', '刘伟', 2, 1, 3, '2024-12-05 06:08:23');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (81, 'user_000081', 'encrypted_password_hash', '17047170594', 'user_75rh8lx6@qq.com', '吴敏', 2, 1, 3, '2025-06-16 10:40:22');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (82, 'user_000082', 'encrypted_password_hash', '18094890871', 'user_05kbkv7m@outlook.com', '周洋', 1, 1, 1, '2025-08-05 03:10:46');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (83, 'user_000083', 'encrypted_password_hash', '16283767032', 'user_ygvwoplo@qq.com', '王娜', 1, 1, 2, '2024-12-13 22:41:39');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (84, 'user_000084', 'encrypted_password_hash', '13342355232', 'user_yqfy751y@gmail.com', '杨勇', 0, 1, 1, '2024-10-30 11:40:44');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (85, 'user_000085', 'encrypted_password_hash', '16493797887', 'user_yv2stn7e@126.com', '周敏', 2, 1, 3, '2024-11-05 19:27:11');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (86, 'user_000086', 'encrypted_password_hash', '14846748401', 'user_ouuylaja@163.com', '吴芳', 0, 1, 2, '2025-01-20 09:58:10');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (87, 'user_000087', 'encrypted_password_hash', '17508942116', 'user_08n6q7nc@126.com', '王丽', 0, 1, 1, '2025-04-05 06:20:23');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (88, 'user_000088', 'encrypted_password_hash', '15198794743', 'user_iqfg51bx@126.com', '张敏', 0, 1, 1, '2024-10-17 19:09:11');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (89, 'user_000089', 'encrypted_password_hash', '17468204577', 'user_f9i6uu13@163.com', '陈娜', 2, 1, 3, '2024-12-03 10:59:43');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (90, 'user_000090', 'encrypted_password_hash', '15255716503', 'user_xnszokgt@163.com', '刘静', 2, 1, 1, '2025-07-18 13:06:21');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (91, 'user_000091', 'encrypted_password_hash', '16044532304', 'user_nt3bnvbc@126.com', '周敏', 1, 1, 1, '2025-01-15 22:10:48');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (92, 'user_000092', 'encrypted_password_hash', '15921777967', 'user_oilkqxlm@outlook.com', '黄芳', 2, 1, 2, '2025-07-01 09:37:27');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (93, 'user_000093', 'encrypted_password_hash', '18934521295', 'user_pfln4rxy@gmail.com', '黄磊', 1, 1, 1, '2025-06-20 04:39:36');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (94, 'user_000094', 'encrypted_password_hash', '18174956008', 'user_8y1o4iza@126.com', '黄丽', 0, 1, 3, '2025-05-14 06:47:20');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (95, 'user_000095', 'encrypted_password_hash', '17644050529', 'user_a3y36ddf@163.com', '吴洋', 1, 1, 2, '2024-12-21 10:22:29');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (96, 'user_000096', 'encrypted_password_hash', '18094262809', 'user_xbonrwzc@outlook.com', '杨勇', 0, 1, 3, '2024-09-25 03:20:20');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (97, 'user_000097', 'encrypted_password_hash', '14965296256', 'user_g4ahf337@gmail.com', '王勇', 0, 1, 3, '2025-07-29 18:14:05');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (98, 'user_000098', 'encrypted_password_hash', '18653776073', 'user_dicvwv8l@gmail.com', '黄磊', 0, 1, 2, '2025-04-11 09:24:46');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (99, 'user_000099', 'encrypted_password_hash', '19466067701', 'user_zw0ldalm@gmail.com', '李娜', 0, 1, 1, '2025-02-22 23:44:52');
INSERT INTO user (id, username, password, phone, email, nickname, gender, status, register_source, create_time)
VALUES (100, 'user_000100', 'encrypted_password_hash', '16540305332', 'user_nd669ugn@163.com', '陈静', 1, 1, 2, '2024-11-04 06:56:20');

-- Member Levels
INSERT INTO member_level (id, level_name, min_growth, max_growth, discount, description, privileges)
VALUES (1, '普通会员', 0, 999, 1.0, '基础会员', '{"discount": 1.0, "free_shipping": false}');
INSERT INTO member_level (id, level_name, min_growth, max_growth, discount, description, privileges)
VALUES (2, '银牌会员', 1000, 4999, 0.95, '银牌特权', '{"discount": 0.95, "free_shipping": false}');
INSERT INTO member_level (id, level_name, min_growth, max_growth, discount, description, privileges)
VALUES (3, '金牌会员', 5000, 9999, 0.9, '金牌特权', '{"discount": 0.9, "free_shipping": true}');
INSERT INTO member_level (id, level_name, min_growth, max_growth, discount, description, privileges)
VALUES (4, '钻石会员', 10000, 999999, 0.85, '钻石特权', '{"discount": 0.85, "free_shipping": true}');

-- User Members
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (1, 'M00000001', 1, 7121, 2260, 421.1, 24211.57, 9);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (2, 'M00000002', 1, 4040, 4061, 593.81, 9777.41, 13);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (3, 'M00000003', 1, 110, 3241, 332.22, 31009.61, 42);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (4, 'M00000004', 1, 7055, 4869, 136.12, 16033.26, 88);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (5, 'M00000005', 1, 5147, 1455, 964.57, 48436.26, 37);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (6, 'M00000006', 2, 8044, 4717, 781.97, 16294.31, 35);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (7, 'M00000007', 3, 6437, 2990, 113.89, 28192.53, 75);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (8, 'M00000008', 4, 2947, 4506, 26.92, 36360.67, 90);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (9, 'M00000009', 1, 4773, 566, 818.97, 39782.84, 52);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (10, 'M00000010', 2, 2280, 2480, 242.02, 32933.15, 91);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (11, 'M00000011', 1, 6176, 605, 449.07, 29896.69, 74);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (12, 'M00000012', 1, 8300, 3426, 543.65, 39536.05, 46);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (13, 'M00000013', 2, 8796, 4864, 638.18, 4249.1, 98);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (14, 'M00000014', 1, 5816, 1362, 647.0, 2214.83, 82);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (15, 'M00000015', 2, 6547, 2716, 965.72, 21522.78, 1);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (16, 'M00000016', 1, 3637, 4183, 746.85, 27899.67, 88);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (17, 'M00000017', 2, 7303, 3064, 391.13, 38617.11, 75);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (18, 'M00000018', 2, 2469, 2825, 997.7, 24138.55, 37);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (19, 'M00000019', 1, 1406, 950, 831.32, 36507.54, 44);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (20, 'M00000020', 1, 7460, 1694, 521.11, 17415.01, 12);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (21, 'M00000021', 1, 7412, 2612, 67.17, 2234.15, 90);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (22, 'M00000022', 1, 5620, 895, 677.14, 8217.7, 94);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (23, 'M00000023', 1, 2858, 4521, 160.14, 27992.45, 59);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (24, 'M00000024', 1, 6636, 1511, 186.39, 32865.4, 50);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (25, 'M00000025', 1, 3232, 3682, 592.2, 19441.79, 90);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (26, 'M00000026', 1, 4560, 512, 577.35, 39975.12, 68);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (27, 'M00000027', 1, 5340, 1614, 457.55, 13117.67, 62);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (28, 'M00000028', 2, 5134, 4890, 388.64, 19627.2, 14);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (29, 'M00000029', 1, 7501, 1413, 813.74, 35299.42, 38);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (30, 'M00000030', 3, 9669, 694, 672.11, 15659.29, 30);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (31, 'M00000031', 1, 2999, 3058, 693.44, 25541.24, 53);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (32, 'M00000032', 2, 9417, 3143, 423.04, 24333.51, 68);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (33, 'M00000033', 2, 2838, 4545, 166.87, 14441.74, 23);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (34, 'M00000034', 1, 7389, 439, 865.52, 47592.1, 62);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (35, 'M00000035', 1, 6290, 4600, 505.44, 24748.49, 87);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (36, 'M00000036', 1, 7272, 4003, 167.25, 28269.89, 97);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (37, 'M00000037', 1, 535, 2251, 224.84, 14433.52, 99);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (38, 'M00000038', 1, 8115, 4503, 510.81, 28635.7, 34);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (39, 'M00000039', 2, 6010, 4440, 822.61, 2080.53, 92);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (40, 'M00000040', 1, 3580, 3482, 102.42, 41503.54, 96);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (41, 'M00000041', 1, 528, 3684, 262.82, 43109.98, 11);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (42, 'M00000042', 1, 1923, 1941, 209.88, 36880.65, 88);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (43, 'M00000043', 1, 9998, 3507, 165.98, 6961.75, 26);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (44, 'M00000044', 3, 972, 4670, 350.82, 14054.25, 68);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (45, 'M00000045', 1, 4794, 2370, 572.74, 47619.87, 65);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (46, 'M00000046', 4, 1560, 1110, 962.77, 39284.55, 7);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (47, 'M00000047', 4, 2078, 1087, 249.68, 35569.74, 31);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (48, 'M00000048', 2, 6434, 4009, 142.17, 31620.1, 80);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (49, 'M00000049', 1, 7405, 618, 633.63, 44006.25, 11);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (50, 'M00000050', 1, 4558, 3023, 454.7, 24402.22, 74);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (51, 'M00000051', 1, 1526, 3755, 634.84, 34864.45, 8);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (52, 'M00000052', 2, 6516, 1784, 968.13, 41242.38, 63);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (53, 'M00000053', 1, 4646, 2766, 547.31, 6529.71, 62);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (54, 'M00000054', 2, 793, 376, 99.57, 39194.49, 58);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (55, 'M00000055', 1, 2588, 3615, 456.52, 49591.91, 54);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (56, 'M00000056', 1, 2161, 2472, 158.98, 46636.28, 35);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (57, 'M00000057', 1, 5905, 2051, 82.42, 33225.44, 83);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (58, 'M00000058', 1, 6493, 2502, 725.99, 37761.61, 54);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (59, 'M00000059', 2, 1559, 8, 213.42, 3896.94, 75);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (60, 'M00000060', 1, 7291, 76, 8.06, 17178.71, 15);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (61, 'M00000061', 4, 6919, 1082, 477.7, 11447.34, 11);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (62, 'M00000062', 2, 1678, 845, 313.35, 44049.45, 17);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (63, 'M00000063', 1, 2181, 1170, 68.41, 28164.56, 78);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (64, 'M00000064', 2, 7210, 2876, 722.43, 31404.0, 19);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (65, 'M00000065', 3, 7248, 1779, 86.69, 5023.26, 96);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (66, 'M00000066', 1, 6291, 2879, 963.25, 15725.18, 17);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (67, 'M00000067', 1, 1380, 2032, 553.4, 30000.39, 77);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (68, 'M00000068', 1, 439, 2471, 205.45, 30346.34, 24);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (69, 'M00000069', 2, 4842, 447, 783.8, 39688.9, 30);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (70, 'M00000070', 1, 1847, 1974, 499.58, 29696.49, 67);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (71, 'M00000071', 3, 197, 2958, 933.92, 6599.14, 72);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (72, 'M00000072', 1, 8951, 1415, 762.73, 47722.65, 98);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (73, 'M00000073', 1, 9623, 553, 13.79, 10787.15, 7);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (74, 'M00000074', 3, 6520, 4150, 286.49, 35631.45, 98);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (75, 'M00000075', 1, 6590, 675, 640.54, 26882.77, 19);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (76, 'M00000076', 1, 5077, 647, 511.3, 10159.87, 19);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (77, 'M00000077', 2, 6401, 4804, 634.29, 41726.32, 87);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (78, 'M00000078', 2, 5081, 3583, 729.96, 42323.41, 7);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (79, 'M00000079', 1, 7118, 950, 453.37, 30441.62, 39);
INSERT INTO user_member (user_id, member_no, level_id, points, growth_value, balance, total_amount, total_orders)
VALUES (80, 'M00000080', 2, 2877, 978, 12.66, 6860.59, 1);

-- User Addresses
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (1, 1, '黄丽', '16771172870', 'P001', '河南', 'C001', '杭州市', 'D001', '雁塔区', '66号路92号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (2, 2, '李磊', '16345321572', 'P002', '湖北', 'C002', '北京市', 'D002', '朝阳区', '880号街32号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (3, 2, '张磊', '15919087622', 'P003', '山东', 'C003', '杭州市', 'D003', '海淀区', '586号路16号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (4, 2, '周伟', '14899042835', 'P004', '北京', 'C004', '南京市', 'D004', '天河区', '299号巷39号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (5, 3, '吴敏', '16547197204', 'P005', '江苏', 'C005', '西安市', 'D005', '江宁区', '652号巷2号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (6, 3, '杨伟', '18373294991', 'P006', '浙江', 'C006', '上海市', 'D006', '朝阳区', '944号街67号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (7, 3, '赵丽', '16755635061', 'P007', '浙江', 'C007', '杭州市', 'D007', '江宁区', '949号路90号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (8, 4, '杨丽', '16291711431', 'P008', '山东', 'C008', '深圳市', 'D008', '洪山区', '316号路34号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (9, 4, '李芳', '14686083023', 'P009', '湖北', 'C009', '北京市', 'D009', '余杭区', '267号街4号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (10, 4, '赵磊', '16501710721', 'P010', '河南', 'C010', '南京市', 'D010', '海淀区', '597号巷34号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (11, 5, '张芳', '14680881909', 'P011', '河南', 'C011', '成都市', 'D011', '洪山区', '31号路88号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (12, 6, '王丽', '14128062245', 'P012', '北京', 'C012', '上海市', 'D012', '余杭区', '393号街54号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (13, 6, '刘洋', '14950374735', 'P013', '山东', 'C013', '成都市', 'D013', '余杭区', '185号街41号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (14, 7, '刘伟', '19709579916', 'P014', '浙江', 'C014', '西安市', 'D014', '朝阳区', '783号街74号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (15, 7, '赵丽', '19912373573', 'P015', '湖南', 'C015', '重庆市', 'D015', '浦东新区', '577号街64号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (16, 7, '周静', '14006043683', 'P016', '广东', 'C016', '南京市', 'D016', '朝阳区', '23号街76号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (17, 8, '王芳', '18893931311', 'P017', '河南', 'C017', '武汉市', 'D017', '江宁区', '179号巷72号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (18, 8, '吴丽', '14513617993', 'P018', '湖南', 'C018', '北京市', 'D018', '浦东新区', '617号路79号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (19, 8, '刘勇', '13430336986', 'P019', '山东', 'C019', '武汉市', 'D019', '朝阳区', '272号街32号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (20, 9, '李丽', '14103798435', 'P020', '上海', 'C020', '武汉市', 'D020', '渝北区', '667号巷29号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (21, 9, '王磊', '13823901375', 'P021', '上海', 'C021', '南京市', 'D021', '渝北区', '629号路91号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (22, 9, '张强', '19181121677', 'P022', '浙江', 'C022', '杭州市', 'D022', '余杭区', '244号街7号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (23, 10, '吴洋', '18134266101', 'P023', '浙江', 'C023', '上海市', 'D023', '洪山区', '865号街40号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (24, 10, '赵磊', '16605183424', 'P024', '江苏', 'C024', '杭州市', 'D024', '渝北区', '64号街97号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (25, 11, '李芳', '17847203596', 'P025', '四川', 'C025', '重庆市', 'D025', '天河区', '488号路11号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (26, 11, '赵伟', '16986617199', 'P026', '江苏', 'C026', '广州市', 'D026', '朝阳区', '434号街88号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (27, 11, '赵敏', '19682990333', 'P027', '浙江', 'C027', '西安市', 'D027', '洪山区', '940号街90号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (28, 12, '吴洋', '18115591177', 'P028', '湖北', 'C028', '南京市', 'D028', '武侯区', '271号路84号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (29, 12, '黄勇', '17021660832', 'P029', '四川', 'C029', '杭州市', 'D029', '江宁区', '21号路39号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (30, 12, '陈静', '18869237737', 'P030', '湖南', 'C030', '上海市', 'D030', '天河区', '453号街53号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (31, 13, '黄洋', '19851351880', 'P031', '江苏', 'C031', '深圳市', 'D031', '朝阳区', '256号街59号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (32, 13, '张丽', '17285610351', 'P032', '四川', 'C032', '重庆市', 'D032', '雁塔区', '359号路21号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (33, 13, '陈芳', '14315986624', 'P033', '北京', 'C033', '北京市', 'D033', '洪山区', '934号街83号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (34, 14, '吴伟', '14697728842', 'P034', '浙江', 'C034', '西安市', 'D034', '渝北区', '407号巷14号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (35, 15, '周丽', '16526432326', 'P035', '浙江', 'C035', '深圳市', 'D035', '浦东新区', '530号街8号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (36, 16, '赵丽', '17769243075', 'P036', '江苏', 'C036', '南京市', 'D036', '天河区', '703号巷82号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (37, 16, '黄丽', '16888101287', 'P037', '广东', 'C037', '南京市', 'D037', '海淀区', '228号路29号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (38, 16, '陈强', '18322848590', 'P038', '江苏', 'C038', '西安市', 'D038', '海淀区', '889号巷49号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (39, 17, '陈强', '13812459437', 'P039', '上海', 'C039', '武汉市', 'D039', '江宁区', '647号街41号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (40, 17, '张伟', '16275177144', 'P040', '河南', 'C040', '武汉市', 'D040', '武侯区', '5号街69号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (41, 17, '刘丽', '19875007283', 'P041', '上海', 'C041', '成都市', 'D041', '雁塔区', '603号路22号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (42, 18, '张静', '19455461947', 'P042', '广东', 'C042', '成都市', 'D042', '朝阳区', '234号街94号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (43, 19, '刘伟', '17136082509', 'P043', '上海', 'C043', '北京市', 'D043', '朝阳区', '707号街54号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (44, 20, '赵芳', '16416636626', 'P044', '广东', 'C044', '成都市', 'D044', '海淀区', '524号巷80号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (45, 20, '李静', '13111252720', 'P045', '四川', 'C045', '深圳市', 'D045', '洪山区', '339号街1号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (46, 21, '李芳', '18610571229', 'P046', '浙江', 'C046', '杭州市', 'D046', '洪山区', '52号街23号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (47, 22, '陈丽', '18370708613', 'P047', '四川', 'C047', '重庆市', 'D047', '余杭区', '900号巷12号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (48, 23, '杨娜', '13448644811', 'P048', '北京', 'C048', '广州市', 'D048', '海淀区', '427号巷62号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (49, 23, '吴磊', '14330689191', 'P049', '河南', 'C049', '武汉市', 'D049', '朝阳区', '65号路84号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (50, 24, '张洋', '18559635706', 'P050', '江苏', 'C050', '武汉市', 'D050', '江宁区', '680号巷83号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (51, 24, '赵娜', '18590618880', 'P051', '四川', 'C051', '南京市', 'D051', '天河区', '339号路7号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (52, 25, '刘洋', '17424848414', 'P052', '北京', 'C052', '上海市', 'D052', '武侯区', '423号街72号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (53, 25, '周伟', '18796856438', 'P053', '江苏', 'C053', '武汉市', 'D053', '江宁区', '574号巷77号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (54, 25, '黄丽', '18172389257', 'P054', '北京', 'C054', '广州市', 'D054', '洪山区', '144号巷96号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (55, 26, '赵芳', '14118281370', 'P055', '湖南', 'C055', '上海市', 'D055', '洪山区', '469号巷76号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (56, 26, '周娜', '19075459796', 'P056', '广东', 'C056', '广州市', 'D056', '海淀区', '397号巷77号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (57, 26, '吴丽', '16399729941', 'P057', '河南', 'C057', '重庆市', 'D057', '雁塔区', '307号巷71号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (58, 27, '周敏', '14623696956', 'P058', '山东', 'C058', '上海市', 'D058', '江宁区', '576号路75号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (59, 27, '吴强', '17858401300', 'P059', '浙江', 'C059', '广州市', 'D059', '余杭区', '700号路19号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (60, 28, '陈敏', '18081878752', 'P060', '浙江', 'C060', '北京市', 'D060', '武侯区', '397号路88号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (61, 29, '周伟', '13267889767', 'P061', '湖北', 'C061', '重庆市', 'D061', '天河区', '795号巷25号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (62, 29, '陈磊', '14611534515', 'P062', '浙江', 'C062', '北京市', 'D062', '江宁区', '478号巷20号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (63, 30, '王丽', '17768375075', 'P063', '广东', 'C063', '西安市', 'D063', '海淀区', '806号巷21号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (64, 31, '周洋', '15386329645', 'P064', '北京', 'C064', '上海市', 'D064', '江宁区', '457号街83号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (65, 31, '吴勇', '14083712705', 'P065', '山东', 'C065', '西安市', 'D065', '雁塔区', '457号路6号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (66, 32, '刘丽', '16919500678', 'P066', '河南', 'C066', '上海市', 'D066', '天河区', '551号巷95号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (67, 32, '赵芳', '18143007552', 'P067', '浙江', 'C067', '成都市', 'D067', '武侯区', '462号巷52号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (68, 33, '杨伟', '15107070835', 'P068', '浙江', 'C068', '杭州市', 'D068', '渝北区', '89号路98号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (69, 33, '黄强', '15645137591', 'P069', '山东', 'C069', '北京市', 'D069', '雁塔区', '896号巷19号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (70, 34, '张伟', '17350277298', 'P070', '广东', 'C070', '广州市', 'D070', '浦东新区', '227号巷33号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (71, 35, '李娜', '17507956062', 'P071', '江苏', 'C071', '重庆市', 'D071', '洪山区', '975号路46号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (72, 36, '刘芳', '14014366158', 'P072', '河南', 'C072', '广州市', 'D072', '朝阳区', '321号街29号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (73, 37, '赵静', '18178433516', 'P073', '浙江', 'C073', '广州市', 'D073', '雁塔区', '202号路94号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (74, 37, '李芳', '19868572028', 'P074', '湖北', 'C074', '武汉市', 'D074', '浦东新区', '375号路29号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (75, 37, '吴静', '13503029967', 'P075', '广东', 'C075', '武汉市', 'D075', '浦东新区', '184号巷73号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (76, 38, '王磊', '19072226573', 'P076', '四川', 'C076', '北京市', 'D076', '朝阳区', '665号路14号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (77, 38, '周静', '18251636853', 'P077', '江苏', 'C077', '西安市', 'D077', '武侯区', '362号路63号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (78, 38, '黄勇', '13842521168', 'P078', '江苏', 'C078', '成都市', 'D078', '江宁区', '738号街62号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (79, 39, '黄磊', '14180051605', 'P079', '北京', 'C079', '广州市', 'D079', '余杭区', '945号街67号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (80, 39, '赵勇', '19687892644', 'P080', '江苏', 'C080', '成都市', 'D080', '渝北区', '147号巷99号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (81, 39, '王勇', '17273452312', 'P081', '湖北', 'C081', '成都市', 'D081', '浦东新区', '621号街23号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (82, 40, '吴勇', '13751416887', 'P082', '河南', 'C082', '西安市', 'D082', '武侯区', '380号街90号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (83, 40, '周强', '14873577601', 'P083', '四川', 'C083', '深圳市', 'D083', '海淀区', '291号巷70号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (84, 41, '陈敏', '13705532841', 'P084', '河南', 'C084', '重庆市', 'D084', '余杭区', '268号巷55号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (85, 41, '吴丽', '17932551549', 'P085', '湖南', 'C085', '南京市', 'D085', '武侯区', '754号街85号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (86, 42, '刘磊', '18915786603', 'P086', '北京', 'C086', '上海市', 'D086', '浦东新区', '483号巷56号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (87, 43, '李静', '14003965759', 'P087', '广东', 'C087', '武汉市', 'D087', '洪山区', '11号街85号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (88, 44, '陈敏', '13552102019', 'P088', '河南', 'C088', '南京市', 'D088', '武侯区', '416号巷26号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (89, 44, '周伟', '18002893926', 'P089', '江苏', 'C089', '武汉市', 'D089', '武侯区', '589号街34号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (90, 44, '王强', '16553152572', 'P090', '四川', 'C090', '成都市', 'D090', '渝北区', '496号街97号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (91, 45, '黄磊', '19842372784', 'P091', '山东', 'C091', '西安市', 'D091', '雁塔区', '10号巷34号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (92, 45, '刘静', '13680943829', 'P092', '江苏', 'C092', '武汉市', 'D092', '天河区', '241号巷31号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (93, 46, '刘敏', '14261196667', 'P093', '湖北', 'C093', '北京市', 'D093', '洪山区', '381号巷41号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (94, 46, '黄勇', '19499225074', 'P094', '湖北', 'C094', '北京市', 'D094', '雁塔区', '356号路88号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (95, 47, '黄芳', '17460253625', 'P095', '江苏', 'C095', '杭州市', 'D095', '洪山区', '982号巷44号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (96, 48, '王磊', '15473445968', 'P096', '浙江', 'C096', '深圳市', 'D096', '雁塔区', '443号路26号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (97, 48, '王娜', '16041865342', 'P097', '湖南', 'C097', '北京市', 'D097', '武侯区', '102号巷21号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (98, 49, '刘静', '16643359201', 'P098', '湖南', 'C098', '深圳市', 'D098', '渝北区', '590号巷84号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (99, 50, '王静', '15647832611', 'P099', '山东', 'C099', '成都市', 'D099', '渝北区', '586号路10号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (100, 50, '李娜', '15367638797', 'P100', '湖北', 'C100', '武汉市', 'D100', '武侯区', '172号街3号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (101, 50, '王敏', '17970110850', 'P101', '河南', 'C101', '西安市', 'D101', '海淀区', '107号路80号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (102, 51, '李强', '15226180683', 'P102', '四川', 'C102', '武汉市', 'D102', '雁塔区', '958号路71号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (103, 52, '王洋', '14566556335', 'P103', '上海', 'C103', '武汉市', 'D103', '余杭区', '375号路34号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (104, 52, '王静', '13571256472', 'P104', '河南', 'C104', '杭州市', 'D104', '渝北区', '237号街71号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (105, 53, '刘强', '19427682065', 'P105', '湖南', 'C105', '杭州市', 'D105', '余杭区', '665号巷69号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (106, 53, '陈强', '14011447431', 'P106', '湖北', 'C106', '北京市', 'D106', '浦东新区', '996号街37号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (107, 54, '王磊', '18214337947', 'P107', '山东', 'C107', '上海市', 'D107', '浦东新区', '58号路100号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (108, 54, '赵芳', '16182309739', 'P108', '江苏', 'C108', '北京市', 'D108', '天河区', '472号巷99号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (109, 55, '周敏', '16633609526', 'P109', '江苏', 'C109', '南京市', 'D109', '朝阳区', '885号巷28号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (110, 56, '刘强', '15121700745', 'P110', '山东', 'C110', '上海市', 'D110', '天河区', '162号路12号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (111, 57, '吴丽', '19960677129', 'P111', '山东', 'C111', '武汉市', 'D111', '天河区', '22号巷49号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (112, 57, '杨娜', '14075545534', 'P112', '湖南', 'C112', '西安市', 'D112', '浦东新区', '71号巷20号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (113, 57, '黄静', '18547578636', 'P113', '山东', 'C113', '上海市', 'D113', '雁塔区', '839号路25号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (114, 58, '黄洋', '14243060910', 'P114', '上海', 'C114', '武汉市', 'D114', '天河区', '102号街74号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (115, 58, '赵磊', '16723609248', 'P115', '上海', 'C115', '武汉市', 'D115', '朝阳区', '370号巷96号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (116, 58, '赵丽', '18065780251', 'P116', '江苏', 'C116', '西安市', 'D116', '武侯区', '195号街68号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (117, 59, '刘勇', '17174163468', 'P117', '广东', 'C117', '广州市', 'D117', '雁塔区', '412号巷44号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (118, 59, '王洋', '19433933747', 'P118', '湖南', 'C118', '深圳市', 'D118', '渝北区', '559号街49号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (119, 59, '周静', '15490020180', 'P119', '江苏', 'C119', '深圳市', 'D119', '江宁区', '23号巷36号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (120, 60, '周洋', '16504128223', 'P120', '湖南', 'C120', '成都市', 'D120', '渝北区', '940号街94号', 1);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (121, 60, '李丽', '18186595375', 'P121', '上海', 'C121', '北京市', 'D121', '江宁区', '357号路54号', 0);
INSERT INTO user_address (id, user_id, receiver_name, receiver_phone, province_code, province, city_code, city, district_code, district, detail_address, is_default)
VALUES (122, 60, '李敏', '18896605353', 'P122', '江苏', 'C122', '广州市', 'D122', '朝阳区', '690号巷51号', 0);

-- Product Service Data
USE mall_product;

-- Product SPUs
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (1, 'Apple Watch 10', 'SPU000001', 2, 2, 'https://example.com/product_1.jpg', 442, 4847, 402, 4.1, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (2, 'Adidas Ultraboost 2', 'SPU000002', 6, 1, 'https://example.com/product_2.jpg', 561, 6640, 134, 3.6, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (3, 'MacBook Pro 3', 'SPU000003', 6, 2, 'https://example.com/product_3.jpg', 499, 6576, 333, 4.4, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (4, 'Dell XPS 2', 'SPU000004', 5, 4, 'https://example.com/product_4.jpg', 331, 9537, 45, 3.7, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (5, 'Sony WH-1000XM5 6', 'SPU000005', 5, 2, 'https://example.com/product_5.jpg', 328, 5744, 304, 4.5, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (6, 'MacBook Pro 6', 'SPU000006', 6, 2, 'https://example.com/product_6.jpg', 451, 835, 473, 4.7, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (7, 'Adidas Ultraboost 3', 'SPU000007', 2, 1, 'https://example.com/product_7.jpg', 757, 993, 124, 4.9, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (8, 'Nike Air Max 1', 'SPU000008', 5, 1, 'https://example.com/product_8.jpg', 312, 7473, 241, 3.5, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (9, 'iPad Pro 4', 'SPU000009', 3, 3, 'https://example.com/product_9.jpg', 561, 8013, 318, 4.6, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (10, 'iPhone 15 Pro 7', 'SPU000010', 2, 3, 'https://example.com/product_10.jpg', 174, 1084, 145, 3.8, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (11, 'iPhone 15 Pro 8', 'SPU000011', 2, 1, 'https://example.com/product_11.jpg', 397, 4545, 339, 4.8, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (12, 'Samsung Galaxy S24 1', 'SPU000012', 5, 4, 'https://example.com/product_12.jpg', 236, 6337, 260, 5.0, 0);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (13, 'Adidas Ultraboost 8', 'SPU000013', 2, 2, 'https://example.com/product_13.jpg', 203, 8302, 494, 4.2, 0);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (14, 'iPhone 15 Pro 4', 'SPU000014', 5, 3, 'https://example.com/product_14.jpg', 501, 6545, 381, 3.7, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (15, 'Adidas Ultraboost 6', 'SPU000015', 5, 2, 'https://example.com/product_15.jpg', 513, 4619, 415, 3.6, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (16, 'Sony WH-1000XM5 1', 'SPU000016', 5, 2, 'https://example.com/product_16.jpg', 155, 5699, 459, 4.1, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (17, 'Samsung Galaxy S24 7', 'SPU000017', 3, 4, 'https://example.com/product_17.jpg', 246, 9486, 78, 4.1, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (18, 'Surface Laptop 6', 'SPU000018', 6, 2, 'https://example.com/product_18.jpg', 211, 2370, 297, 4.3, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (19, 'MacBook Pro 5', 'SPU000019', 3, 4, 'https://example.com/product_19.jpg', 930, 2478, 335, 4.9, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (20, 'Adidas Ultraboost 2', 'SPU000020', 2, 2, 'https://example.com/product_20.jpg', 325, 2484, 372, 3.5, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (21, 'Sony WH-1000XM5 7', 'SPU000021', 5, 4, 'https://example.com/product_21.jpg', 763, 4585, 77, 4.7, 0);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (22, 'MacBook Pro 3', 'SPU000022', 2, 3, 'https://example.com/product_22.jpg', 620, 9390, 313, 3.8, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (23, 'Dell XPS 1', 'SPU000023', 5, 1, 'https://example.com/product_23.jpg', 704, 7908, 68, 4.3, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (24, 'Adidas Ultraboost 3', 'SPU000024', 6, 4, 'https://example.com/product_24.jpg', 485, 2353, 83, 3.8, 0);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (25, 'Sony WH-1000XM5 1', 'SPU000025', 5, 4, 'https://example.com/product_25.jpg', 227, 3423, 387, 3.8, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (26, 'Apple Watch 10', 'SPU000026', 6, 3, 'https://example.com/product_26.jpg', 887, 7596, 454, 3.9, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (27, 'iPhone 15 Pro 10', 'SPU000027', 6, 3, 'https://example.com/product_27.jpg', 403, 9470, 304, 4.3, 0);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (28, 'iPad Pro 2', 'SPU000028', 6, 4, 'https://example.com/product_28.jpg', 846, 794, 449, 5.0, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (29, 'iPhone 15 Pro 9', 'SPU000029', 6, 1, 'https://example.com/product_29.jpg', 194, 4870, 225, 4.1, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (30, 'Nike Air Max 8', 'SPU000030', 3, 4, 'https://example.com/product_30.jpg', 301, 993, 353, 4.3, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (31, 'Apple Watch 8', 'SPU000031', 2, 1, 'https://example.com/product_31.jpg', 184, 9691, 480, 4.8, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (32, 'Sony WH-1000XM5 7', 'SPU000032', 5, 4, 'https://example.com/product_32.jpg', 25, 3030, 232, 4.4, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (33, 'Apple Watch 2', 'SPU000033', 5, 3, 'https://example.com/product_33.jpg', 708, 2786, 367, 4.6, 0);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (34, 'Samsung Galaxy S24 8', 'SPU000034', 5, 1, 'https://example.com/product_34.jpg', 536, 6392, 372, 4.7, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (35, 'Sony WH-1000XM5 9', 'SPU000035', 6, 1, 'https://example.com/product_35.jpg', 786, 2899, 467, 4.0, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (36, 'MacBook Pro 3', 'SPU000036', 3, 1, 'https://example.com/product_36.jpg', 429, 5147, 158, 4.6, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (37, 'Nike Air Max 3', 'SPU000037', 2, 2, 'https://example.com/product_37.jpg', 983, 3423, 1, 4.4, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (38, 'iPhone 15 Pro 8', 'SPU000038', 6, 4, 'https://example.com/product_38.jpg', 556, 194, 311, 4.8, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (39, 'Dell XPS 10', 'SPU000039', 6, 4, 'https://example.com/product_39.jpg', 419, 7339, 40, 4.4, 0);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (40, 'Dell XPS 9', 'SPU000040', 5, 2, 'https://example.com/product_40.jpg', 50, 3128, 75, 3.6, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (41, 'iPhone 15 Pro 3', 'SPU000041', 2, 3, 'https://example.com/product_41.jpg', 339, 1536, 25, 3.7, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (42, 'MacBook Pro 5', 'SPU000042', 3, 4, 'https://example.com/product_42.jpg', 767, 9585, 184, 4.9, 0);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (43, 'Nike Air Max 6', 'SPU000043', 5, 4, 'https://example.com/product_43.jpg', 839, 1208, 388, 4.1, 0);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (44, 'Nike Air Max 8', 'SPU000044', 3, 1, 'https://example.com/product_44.jpg', 711, 4596, 168, 4.3, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (45, 'Dell XPS 10', 'SPU000045', 2, 1, 'https://example.com/product_45.jpg', 585, 8766, 44, 4.2, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (46, 'Sony WH-1000XM5 10', 'SPU000046', 5, 3, 'https://example.com/product_46.jpg', 538, 9523, 319, 4.0, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (47, 'Surface Laptop 10', 'SPU000047', 6, 4, 'https://example.com/product_47.jpg', 504, 3047, 452, 4.4, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (48, 'Dell XPS 1', 'SPU000048', 2, 1, 'https://example.com/product_48.jpg', 476, 2032, 377, 4.8, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (49, 'Sony WH-1000XM5 1', 'SPU000049', 5, 3, 'https://example.com/product_49.jpg', 573, 3578, 5, 4.5, 1);
INSERT INTO product_spu (id, product_name, product_code, category_id, brand_id, main_image, sale_count, view_count, comment_count, score, status)
VALUES (50, 'Nike Air Max 2', 'SPU000050', 5, 3, 'https://example.com/product_50.jpg', 518, 9111, 165, 4.7, 0);

-- Product SKUs
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (1, 1, 'SKU Product 1-1', 'SKU00000001', 1713.39, 2381.2, 1173.33, 29, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (2, 1, 'SKU Product 1-2', 'SKU00000002', 1659.27, 2049.49, 1164.38, 938, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (3, 1, 'SKU Product 1-3', 'SKU00000003', 3231.8, 4177.42, 2224.0, 913, '{"color": "Blue", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (4, 2, 'SKU Product 2-1', 'SKU00000004', 4639.67, 5207.61, 3045.12, 578, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (5, 2, 'SKU Product 2-2', 'SKU00000005', 1049.23, 1441.85, 776.28, 909, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (6, 2, 'SKU Product 2-3', 'SKU00000006', 4848.37, 5877.12, 2469.53, 384, '{"color": "White", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (7, 2, 'SKU Product 2-4', 'SKU00000007', 945.02, 1083.69, 694.31, 315, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (8, 3, 'SKU Product 3-1', 'SKU00000008', 2075.21, 2695.33, 1185.55, 462, '{"color": "Blue", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (9, 3, 'SKU Product 3-2', 'SKU00000009', 2630.25, 3077.41, 1610.41, 38, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (10, 3, 'SKU Product 3-3', 'SKU00000010', 2224.43, 2469.06, 1434.31, 512, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (11, 4, 'SKU Product 4-1', 'SKU00000011', 4618.95, 6434.09, 3035.06, 794, '{"color": "Blue", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (12, 4, 'SKU Product 4-2', 'SKU00000012', 3849.71, 4385.98, 3059.3, 528, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (13, 4, 'SKU Product 4-3', 'SKU00000013', 3625.61, 4654.37, 2589.06, 534, '{"color": "Black", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (14, 5, 'SKU Product 5-1', 'SKU00000014', 4662.36, 6409.91, 3162.41, 89, '{"color": "Blue", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (15, 5, 'SKU Product 5-2', 'SKU00000015', 4447.04, 5075.14, 3245.69, 763, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (16, 5, 'SKU Product 5-3', 'SKU00000016', 2892.02, 3542.31, 2220.03, 328, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (17, 6, 'SKU Product 6-1', 'SKU00000017', 1390.48, 1707.04, 879.25, 120, '{"color": "Blue", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (18, 6, 'SKU Product 6-2', 'SKU00000018', 795.91, 911.94, 626.77, 573, '{"color": "Black", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (19, 6, 'SKU Product 6-3', 'SKU00000019', 2717.22, 3586.04, 1579.56, 965, '{"color": "Blue", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (20, 7, 'SKU Product 7-1', 'SKU00000020', 365.5, 491.71, 219.89, 454, '{"color": "Black", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (21, 7, 'SKU Product 7-2', 'SKU00000021', 154.67, 226.02, 79.68, 392, '{"color": "Black", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (22, 8, 'SKU Product 8-1', 'SKU00000022', 1965.66, 2716.29, 1508.41, 644, '{"color": "Red", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (23, 8, 'SKU Product 8-2', 'SKU00000023', 1569.08, 2239.04, 1120.12, 686, '{"color": "Black", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (24, 8, 'SKU Product 8-3', 'SKU00000024', 2843.27, 3233.27, 2106.02, 715, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (25, 8, 'SKU Product 8-4', 'SKU00000025', 2283.37, 3136.87, 1628.19, 476, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (26, 9, 'SKU Product 9-1', 'SKU00000026', 727.17, 1060.42, 419.88, 372, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (27, 9, 'SKU Product 9-2', 'SKU00000027', 3501.08, 4782.73, 2579.01, 903, '{"color": "Blue", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (28, 10, 'SKU Product 10-1', 'SKU00000028', 161.02, 207.74, 97.49, 472, '{"color": "White", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (29, 10, 'SKU Product 10-2', 'SKU00000029', 3842.03, 5155.14, 2067.85, 105, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (30, 10, 'SKU Product 10-3', 'SKU00000030', 1636.09, 2224.72, 1206.99, 545, '{"color": "Red", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (31, 10, 'SKU Product 10-4', 'SKU00000031', 2956.77, 3534.61, 1694.55, 162, '{"color": "Red", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (32, 11, 'SKU Product 11-1', 'SKU00000032', 1710.16, 2437.95, 1158.28, 803, '{"color": "Red", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (33, 12, 'SKU Product 12-1', 'SKU00000033', 873.7, 1195.08, 630.65, 728, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (34, 12, 'SKU Product 12-2', 'SKU00000034', 2964.89, 3810.37, 1976.18, 841, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (35, 13, 'SKU Product 13-1', 'SKU00000035', 4779.86, 5390.93, 2545.87, 833, '{"color": "Red", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (36, 13, 'SKU Product 13-2', 'SKU00000036', 2476.53, 3378.15, 1305.53, 836, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (37, 13, 'SKU Product 13-3', 'SKU00000037', 3787.82, 5167.92, 2408.65, 298, '{"color": "Red", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (38, 14, 'SKU Product 14-1', 'SKU00000038', 1036.25, 1503.57, 611.31, 813, '{"color": "Blue", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (39, 14, 'SKU Product 14-2', 'SKU00000039', 3591.78, 5265.72, 2862.96, 596, '{"color": "Black", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (40, 15, 'SKU Product 15-1', 'SKU00000040', 4264.0, 4796.85, 3284.66, 991, '{"color": "Black", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (41, 15, 'SKU Product 15-2', 'SKU00000041', 3519.74, 5027.25, 2139.2, 881, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (42, 16, 'SKU Product 16-1', 'SKU00000042', 2164.26, 2891.59, 1488.07, 728, '{"color": "Red", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (43, 16, 'SKU Product 16-2', 'SKU00000043', 1914.01, 2522.76, 1337.41, 486, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (44, 17, 'SKU Product 17-1', 'SKU00000044', 4218.21, 5247.94, 2637.1, 295, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (45, 17, 'SKU Product 17-2', 'SKU00000045', 67.9, 92.3, 36.88, 906, '{"color": "Red", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (46, 17, 'SKU Product 17-3', 'SKU00000046', 4875.13, 5627.41, 2638.25, 825, '{"color": "Red", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (47, 17, 'SKU Product 17-4', 'SKU00000047', 4468.98, 6158.95, 3481.75, 638, '{"color": "White", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (48, 18, 'SKU Product 18-1', 'SKU00000048', 2764.73, 3538.91, 1677.06, 190, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (49, 18, 'SKU Product 18-2', 'SKU00000049', 2893.91, 4163.53, 1652.49, 513, '{"color": "Blue", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (50, 18, 'SKU Product 18-3', 'SKU00000050', 4633.56, 6165.14, 2363.29, 38, '{"color": "Red", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (51, 18, 'SKU Product 18-4', 'SKU00000051', 574.69, 742.85, 406.39, 145, '{"color": "Blue", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (52, 19, 'SKU Product 19-1', 'SKU00000052', 2621.55, 3523.44, 1503.64, 645, '{"color": "Black", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (53, 20, 'SKU Product 20-1', 'SKU00000053', 4518.89, 5937.88, 2274.06, 464, '{"color": "Red", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (54, 21, 'SKU Product 21-1', 'SKU00000054', 1771.04, 2640.81, 1042.05, 171, '{"color": "Black", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (55, 21, 'SKU Product 21-2', 'SKU00000055', 3637.59, 4821.56, 1885.82, 484, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (56, 21, 'SKU Product 21-3', 'SKU00000056', 3285.04, 3996.91, 2369.22, 27, '{"color": "Blue", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (57, 21, 'SKU Product 21-4', 'SKU00000057', 421.73, 549.19, 318.59, 284, '{"color": "Blue", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (58, 22, 'SKU Product 22-1', 'SKU00000058', 1383.33, 1663.84, 999.36, 618, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (59, 22, 'SKU Product 22-2', 'SKU00000059', 3299.19, 4117.08, 1823.2, 801, '{"color": "White", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (60, 22, 'SKU Product 22-3', 'SKU00000060', 912.24, 1195.91, 465.18, 86, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (61, 23, 'SKU Product 23-1', 'SKU00000061', 3766.28, 4765.02, 2316.07, 676, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (62, 23, 'SKU Product 23-2', 'SKU00000062', 1062.18, 1480.41, 641.05, 657, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (63, 23, 'SKU Product 23-3', 'SKU00000063', 272.09, 349.8, 174.92, 487, '{"color": "White", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (64, 23, 'SKU Product 23-4', 'SKU00000064', 3420.71, 4296.51, 1979.86, 543, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (65, 24, 'SKU Product 24-1', 'SKU00000065', 4636.84, 5932.97, 3262.24, 984, '{"color": "Black", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (66, 24, 'SKU Product 24-2', 'SKU00000066', 4589.38, 6218.47, 3611.18, 442, '{"color": "Blue", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (67, 24, 'SKU Product 24-3', 'SKU00000067', 1358.93, 1930.94, 783.56, 598, '{"color": "Red", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (68, 25, 'SKU Product 25-1', 'SKU00000068', 3674.22, 4906.88, 2051.97, 345, '{"color": "White", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (69, 25, 'SKU Product 25-2', 'SKU00000069', 2742.59, 3754.94, 2055.59, 12, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (70, 26, 'SKU Product 26-1', 'SKU00000070', 3430.64, 4256.07, 2461.74, 257, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (71, 27, 'SKU Product 27-1', 'SKU00000071', 3100.79, 3481.42, 2103.08, 915, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (72, 28, 'SKU Product 28-1', 'SKU00000072', 441.07, 591.86, 270.28, 782, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (73, 29, 'SKU Product 29-1', 'SKU00000073', 1274.15, 1639.55, 994.93, 654, '{"color": "Red", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (74, 29, 'SKU Product 29-2', 'SKU00000074', 3849.1, 5299.78, 2695.42, 788, '{"color": "Blue", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (75, 30, 'SKU Product 30-1', 'SKU00000075', 3905.85, 4729.65, 2026.03, 397, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (76, 30, 'SKU Product 30-2', 'SKU00000076', 1298.61, 1850.68, 718.62, 71, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (77, 31, 'SKU Product 31-1', 'SKU00000077', 900.43, 1167.7, 489.08, 787, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (78, 31, 'SKU Product 31-2', 'SKU00000078', 3073.69, 3511.69, 1944.03, 265, '{"color": "Black", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (79, 31, 'SKU Product 31-3', 'SKU00000079', 373.87, 496.7, 215.04, 456, '{"color": "Black", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (80, 31, 'SKU Product 31-4', 'SKU00000080', 752.78, 1020.87, 587.07, 680, '{"color": "Red", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (81, 32, 'SKU Product 32-1', 'SKU00000081', 3503.28, 5121.26, 2355.58, 527, '{"color": "Red", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (82, 32, 'SKU Product 32-2', 'SKU00000082', 2185.93, 3142.54, 1605.09, 804, '{"color": "Black", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (83, 33, 'SKU Product 33-1', 'SKU00000083', 1928.28, 2652.74, 1415.26, 124, '{"color": "Blue", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (84, 34, 'SKU Product 34-1', 'SKU00000084', 2009.07, 2718.09, 1582.44, 92, '{"color": "Blue", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (85, 34, 'SKU Product 34-2', 'SKU00000085', 411.48, 528.28, 300.36, 663, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (86, 34, 'SKU Product 34-3', 'SKU00000086', 2776.83, 4129.16, 1938.84, 306, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (87, 35, 'SKU Product 35-1', 'SKU00000087', 2437.65, 3544.55, 1539.12, 194, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (88, 35, 'SKU Product 35-2', 'SKU00000088', 973.53, 1351.16, 659.94, 500, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (89, 35, 'SKU Product 35-3', 'SKU00000089', 3965.77, 5382.99, 2577.37, 988, '{"color": "Red", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (90, 36, 'SKU Product 36-1', 'SKU00000090', 1703.03, 2103.39, 1219.89, 415, '{"color": "Black", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (91, 37, 'SKU Product 37-1', 'SKU00000091', 4701.05, 6807.5, 3107.25, 758, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (92, 37, 'SKU Product 37-2', 'SKU00000092', 194.02, 224.41, 135.07, 482, '{"color": "Black", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (93, 37, 'SKU Product 37-3', 'SKU00000093', 3509.47, 4043.66, 1936.09, 51, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (94, 37, 'SKU Product 37-4', 'SKU00000094', 2286.36, 2671.1, 1548.58, 837, '{"color": "Black", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (95, 38, 'SKU Product 38-1', 'SKU00000095', 2978.94, 4010.4, 1545.87, 207, '{"color": "Red", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (96, 38, 'SKU Product 38-2', 'SKU00000096', 4059.49, 6060.32, 2855.93, 332, '{"color": "Black", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (97, 38, 'SKU Product 38-3', 'SKU00000097', 1614.73, 2399.29, 910.66, 46, '{"color": "White", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (98, 38, 'SKU Product 38-4', 'SKU00000098', 325.48, 393.69, 236.71, 172, '{"color": "Blue", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (99, 39, 'SKU Product 39-1', 'SKU00000099', 1225.79, 1633.73, 661.94, 810, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (100, 39, 'SKU Product 39-2', 'SKU00000100', 88.0, 113.94, 44.17, 183, '{"color": "Black", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (101, 39, 'SKU Product 39-3', 'SKU00000101', 2086.95, 2969.66, 1527.16, 708, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (102, 39, 'SKU Product 39-4', 'SKU00000102', 724.01, 874.88, 459.84, 244, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (103, 40, 'SKU Product 40-1', 'SKU00000103', 3588.82, 4826.32, 1889.18, 846, '{"color": "Blue", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (104, 40, 'SKU Product 40-2', 'SKU00000104', 2099.74, 2778.13, 1170.75, 542, '{"color": "Black", "size": "L"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (105, 41, 'SKU Product 41-1', 'SKU00000105', 2326.44, 3142.06, 1308.47, 54, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (106, 41, 'SKU Product 41-2', 'SKU00000106', 3985.1, 5475.92, 3150.51, 485, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (107, 42, 'SKU Product 42-1', 'SKU00000107', 3354.01, 3747.26, 2325.87, 291, '{"color": "White", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (108, 42, 'SKU Product 42-2', 'SKU00000108', 1264.4, 1702.8, 969.82, 464, '{"color": "Black", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (109, 43, 'SKU Product 43-1', 'SKU00000109', 4625.06, 5412.96, 3402.41, 496, '{"color": "Black", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (110, 44, 'SKU Product 44-1', 'SKU00000110', 3609.8, 4826.6, 2157.91, 203, '{"color": "Blue", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (111, 44, 'SKU Product 44-2', 'SKU00000111', 3897.09, 4795.19, 2063.19, 755, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (112, 45, 'SKU Product 45-1', 'SKU00000112', 4457.91, 5034.82, 3011.44, 55, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (113, 45, 'SKU Product 45-2', 'SKU00000113', 1088.66, 1462.91, 602.48, 382, '{"color": "White", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (114, 45, 'SKU Product 45-3', 'SKU00000114', 3601.91, 4872.69, 2855.11, 207, '{"color": "Blue", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (115, 45, 'SKU Product 45-4', 'SKU00000115', 170.15, 225.87, 99.4, 113, '{"color": "Red", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (116, 46, 'SKU Product 46-1', 'SKU00000116', 864.29, 1069.5, 661.98, 618, '{"color": "White", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (117, 46, 'SKU Product 46-2', 'SKU00000117', 2894.83, 3824.37, 1646.9, 421, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (118, 46, 'SKU Product 46-3', 'SKU00000118', 2961.99, 4249.85, 1729.07, 747, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (119, 46, 'SKU Product 46-4', 'SKU00000119', 1246.68, 1587.9, 706.52, 949, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (120, 47, 'SKU Product 47-1', 'SKU00000120', 4901.12, 5469.76, 3142.58, 787, '{"color": "Black", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (121, 47, 'SKU Product 47-2', 'SKU00000121', 2660.3, 3436.33, 1967.04, 841, '{"color": "Black", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (122, 47, 'SKU Product 47-3', 'SKU00000122', 2481.47, 2812.09, 1801.36, 709, '{"color": "Red", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (123, 47, 'SKU Product 47-4', 'SKU00000123', 672.23, 818.99, 466.72, 92, '{"color": "Black", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (124, 48, 'SKU Product 48-1', 'SKU00000124', 817.4, 1049.31, 548.57, 597, '{"color": "Black", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (125, 48, 'SKU Product 48-2', 'SKU00000125', 2324.17, 2685.46, 1652.35, 189, '{"color": "Blue", "size": "XL"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (126, 48, 'SKU Product 48-3', 'SKU00000126', 3522.47, 3876.64, 2424.13, 394, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (127, 49, 'SKU Product 49-1', 'SKU00000127', 2135.25, 2579.2, 1673.67, 555, '{"color": "White", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (128, 49, 'SKU Product 49-2', 'SKU00000128', 3106.07, 3833.75, 2421.72, 527, '{"color": "Blue", "size": "S"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (129, 49, 'SKU Product 49-3', 'SKU00000129', 563.72, 702.53, 386.57, 374, '{"color": "Blue", "size": "M"}');
INSERT INTO product_sku (id, spu_id, sku_name, sku_code, price, original_price, cost_price, stock, specs)
VALUES (130, 50, 'SKU Product 50-1', 'SKU00000130', 2808.18, 3957.93, 1520.69, 342, '{"color": "White", "size": "S"}');

-- Inventory Service Data
USE mall_inventory;

-- Warehouses
INSERT INTO inv_warehouse (id, warehouse_name, warehouse_code, warehouse_type, contact_person, contact_phone, province, city, district, address)
VALUES (1, '武汉市仓库1', 'WH0001', 3, '吴敏', '18738540345', '上海', '武汉市', '雁塔区', '雁塔区695号');
INSERT INTO inv_warehouse (id, warehouse_name, warehouse_code, warehouse_type, contact_person, contact_phone, province, city, district, address)
VALUES (2, '西安市仓库2', 'WH0002', 2, '黄勇', '15669818031', '上海', '上海市', '浦东新区', '浦东新区283号');
INSERT INTO inv_warehouse (id, warehouse_name, warehouse_code, warehouse_type, contact_person, contact_phone, province, city, district, address)
VALUES (3, '上海市仓库3', 'WH0003', 3, '王磊', '14290469447', '北京', '杭州市', '天河区', '天河区65号');
INSERT INTO inv_warehouse (id, warehouse_name, warehouse_code, warehouse_type, contact_person, contact_phone, province, city, district, address)
VALUES (4, '北京市仓库4', 'WH0004', 1, '周丽', '15363568431', '浙江', '广州市', '余杭区', '余杭区233号');
INSERT INTO inv_warehouse (id, warehouse_name, warehouse_code, warehouse_type, contact_person, contact_phone, province, city, district, address)
VALUES (5, '武汉市仓库5', 'WH0005', 2, '陈伟', '14296151179', '广东', '广州市', '天河区', '天河区120号');

-- Inventory Stock
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (1, 1, 1, 229, 201, 28);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (2, 1, 3, 366, 343, 23);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (3, 1, 4, 288, 275, 13);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (4, 2, 5, 424, 402, 22);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (5, 2, 1, 251, 228, 23);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (6, 2, 3, 559, 525, 34);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (7, 3, 2, 125, 100, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (8, 3, 3, 611, 562, 49);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (9, 3, 1, 248, 219, 29);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (10, 4, 3, 856, 822, 34);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (11, 4, 2, 619, 598, 21);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (12, 4, 1, 762, 717, 45);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (13, 5, 3, 872, 836, 36);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (14, 5, 4, 782, 773, 9);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (15, 5, 2, 918, 882, 36);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (16, 6, 4, 215, 169, 46);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (17, 6, 3, 590, 588, 2);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (18, 6, 5, 242, 199, 43);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (19, 7, 5, 125, 100, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (20, 7, 1, 732, 718, 14);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (21, 7, 2, 556, 534, 22);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (22, 8, 1, 722, 681, 41);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (23, 8, 3, 299, 262, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (24, 8, 5, 639, 625, 14);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (25, 9, 3, 185, 151, 34);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (26, 9, 2, 969, 941, 28);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (27, 9, 1, 793, 751, 42);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (28, 10, 1, 602, 582, 20);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (29, 10, 4, 717, 679, 38);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (30, 10, 5, 623, 620, 3);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (31, 11, 3, 833, 806, 27);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (32, 11, 4, 478, 454, 24);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (33, 11, 1, 481, 481, 0);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (34, 12, 3, 415, 387, 28);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (35, 12, 5, 995, 960, 35);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (36, 12, 2, 189, 176, 13);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (37, 13, 4, 395, 385, 10);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (38, 13, 5, 925, 879, 46);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (39, 13, 2, 534, 488, 46);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (40, 14, 4, 712, 707, 5);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (41, 14, 2, 247, 234, 13);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (42, 14, 3, 410, 380, 30);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (43, 15, 5, 704, 656, 48);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (44, 15, 3, 484, 479, 5);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (45, 15, 1, 106, 80, 26);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (46, 16, 3, 669, 640, 29);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (47, 16, 4, 827, 791, 36);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (48, 16, 1, 873, 865, 8);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (49, 17, 2, 396, 361, 35);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (50, 17, 5, 602, 600, 2);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (51, 17, 4, 942, 923, 19);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (52, 18, 5, 672, 625, 47);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (53, 18, 3, 574, 560, 14);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (54, 18, 2, 127, 77, 50);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (55, 19, 1, 407, 367, 40);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (56, 19, 2, 954, 914, 40);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (57, 19, 4, 583, 576, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (58, 20, 3, 603, 559, 44);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (59, 20, 4, 760, 745, 15);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (60, 20, 1, 129, 122, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (61, 21, 3, 713, 666, 47);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (62, 21, 4, 470, 433, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (63, 21, 1, 592, 556, 36);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (64, 22, 4, 802, 772, 30);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (65, 22, 3, 810, 803, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (66, 22, 5, 482, 450, 32);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (67, 23, 5, 266, 229, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (68, 23, 1, 159, 150, 9);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (69, 23, 2, 427, 398, 29);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (70, 24, 2, 154, 137, 17);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (71, 24, 3, 469, 443, 26);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (72, 24, 1, 751, 729, 22);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (73, 25, 1, 642, 602, 40);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (74, 25, 3, 178, 146, 32);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (75, 25, 2, 298, 249, 49);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (76, 26, 2, 870, 860, 10);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (77, 26, 1, 656, 636, 20);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (78, 26, 3, 993, 973, 20);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (79, 27, 3, 814, 770, 44);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (80, 27, 5, 471, 465, 6);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (81, 27, 4, 647, 610, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (82, 28, 3, 240, 207, 33);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (83, 28, 2, 388, 365, 23);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (84, 28, 1, 897, 866, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (85, 29, 4, 204, 191, 13);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (86, 29, 3, 258, 226, 32);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (87, 29, 1, 326, 314, 12);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (88, 30, 5, 571, 567, 4);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (89, 30, 1, 219, 211, 8);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (90, 30, 2, 117, 87, 30);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (91, 31, 3, 471, 463, 8);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (92, 31, 5, 936, 908, 28);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (93, 31, 4, 711, 678, 33);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (94, 32, 3, 411, 410, 1);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (95, 32, 2, 862, 855, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (96, 32, 5, 327, 319, 8);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (97, 33, 2, 228, 215, 13);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (98, 33, 5, 233, 228, 5);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (99, 33, 4, 535, 491, 44);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (100, 34, 1, 893, 863, 30);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (101, 34, 5, 875, 848, 27);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (102, 34, 4, 143, 131, 12);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (103, 35, 1, 625, 588, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (104, 35, 5, 160, 130, 30);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (105, 35, 3, 221, 205, 16);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (106, 36, 1, 783, 783, 0);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (107, 36, 5, 872, 866, 6);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (108, 36, 2, 901, 894, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (109, 37, 5, 304, 293, 11);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (110, 37, 2, 786, 764, 22);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (111, 37, 3, 129, 124, 5);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (112, 38, 2, 456, 434, 22);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (113, 38, 3, 264, 232, 32);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (114, 38, 4, 899, 853, 46);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (115, 39, 5, 683, 679, 4);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (116, 39, 2, 201, 164, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (117, 39, 3, 881, 881, 0);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (118, 40, 5, 296, 278, 18);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (119, 40, 2, 275, 227, 48);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (120, 40, 3, 650, 650, 0);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (121, 41, 1, 971, 926, 45);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (122, 41, 2, 586, 563, 23);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (123, 41, 3, 814, 777, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (124, 42, 2, 209, 198, 11);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (125, 42, 4, 954, 932, 22);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (126, 42, 3, 488, 461, 27);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (127, 43, 2, 215, 167, 48);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (128, 43, 5, 421, 414, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (129, 43, 4, 577, 570, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (130, 44, 5, 880, 857, 23);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (131, 44, 2, 821, 803, 18);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (132, 44, 4, 670, 624, 46);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (133, 45, 1, 605, 598, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (134, 45, 5, 494, 476, 18);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (135, 45, 2, 567, 529, 38);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (136, 46, 3, 667, 641, 26);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (137, 46, 4, 397, 370, 27);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (138, 46, 1, 293, 289, 4);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (139, 47, 4, 113, 70, 43);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (140, 47, 3, 603, 560, 43);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (141, 47, 1, 207, 182, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (142, 48, 4, 246, 221, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (143, 48, 1, 510, 466, 44);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (144, 48, 5, 704, 698, 6);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (145, 49, 2, 303, 282, 21);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (146, 49, 1, 305, 267, 38);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (147, 49, 5, 964, 939, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (148, 50, 1, 842, 811, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (149, 50, 2, 385, 337, 48);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (150, 50, 4, 830, 781, 49);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (151, 51, 5, 509, 487, 22);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (152, 51, 3, 587, 545, 42);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (153, 51, 4, 717, 692, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (154, 52, 4, 886, 857, 29);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (155, 52, 3, 210, 203, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (156, 52, 1, 356, 325, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (157, 53, 4, 237, 236, 1);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (158, 53, 2, 201, 154, 47);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (159, 53, 3, 154, 150, 4);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (160, 54, 4, 491, 453, 38);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (161, 54, 1, 795, 775, 20);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (162, 54, 5, 256, 237, 19);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (163, 55, 5, 776, 728, 48);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (164, 55, 1, 632, 583, 49);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (165, 55, 4, 604, 554, 50);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (166, 56, 4, 777, 746, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (167, 56, 2, 602, 570, 32);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (168, 56, 5, 367, 333, 34);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (169, 57, 3, 540, 533, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (170, 57, 1, 492, 464, 28);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (171, 57, 4, 422, 398, 24);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (172, 58, 4, 133, 122, 11);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (173, 58, 5, 670, 628, 42);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (174, 58, 1, 981, 932, 49);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (175, 59, 3, 541, 533, 8);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (176, 59, 2, 419, 374, 45);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (177, 59, 4, 498, 467, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (178, 60, 1, 667, 624, 43);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (179, 60, 4, 652, 616, 36);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (180, 60, 3, 581, 551, 30);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (181, 61, 5, 157, 132, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (182, 61, 2, 369, 338, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (183, 61, 3, 705, 681, 24);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (184, 62, 5, 428, 425, 3);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (185, 62, 3, 128, 91, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (186, 62, 2, 146, 138, 8);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (187, 63, 3, 697, 696, 1);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (188, 63, 5, 883, 843, 40);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (189, 63, 4, 449, 425, 24);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (190, 64, 1, 465, 439, 26);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (191, 64, 4, 459, 456, 3);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (192, 64, 2, 777, 762, 15);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (193, 65, 2, 419, 382, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (194, 65, 5, 186, 142, 44);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (195, 65, 3, 167, 148, 19);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (196, 66, 1, 120, 86, 34);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (197, 66, 3, 433, 431, 2);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (198, 66, 5, 571, 529, 42);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (199, 67, 4, 120, 76, 44);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (200, 67, 5, 580, 543, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (201, 67, 2, 122, 102, 20);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (202, 68, 4, 686, 680, 6);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (203, 68, 5, 927, 896, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (204, 68, 2, 202, 158, 44);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (205, 69, 2, 614, 566, 48);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (206, 69, 3, 783, 762, 21);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (207, 69, 5, 270, 231, 39);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (208, 70, 3, 306, 275, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (209, 70, 5, 750, 700, 50);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (210, 70, 2, 177, 143, 34);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (211, 71, 2, 148, 129, 19);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (212, 71, 3, 687, 662, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (213, 71, 5, 923, 909, 14);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (214, 72, 5, 964, 927, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (215, 72, 1, 197, 160, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (216, 72, 3, 444, 427, 17);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (217, 73, 1, 314, 292, 22);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (218, 73, 4, 914, 910, 4);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (219, 73, 2, 767, 748, 19);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (220, 74, 5, 779, 745, 34);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (221, 74, 3, 420, 408, 12);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (222, 74, 2, 524, 499, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (223, 75, 3, 881, 846, 35);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (224, 75, 4, 284, 283, 1);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (225, 75, 5, 742, 697, 45);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (226, 76, 5, 611, 598, 13);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (227, 76, 2, 776, 729, 47);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (228, 76, 3, 578, 561, 17);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (229, 77, 5, 844, 804, 40);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (230, 77, 4, 161, 128, 33);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (231, 77, 3, 305, 282, 23);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (232, 78, 4, 254, 207, 47);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (233, 78, 2, 801, 753, 48);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (234, 78, 1, 407, 380, 27);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (235, 79, 4, 749, 736, 13);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (236, 79, 3, 966, 940, 26);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (237, 79, 1, 373, 355, 18);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (238, 80, 2, 715, 700, 15);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (239, 80, 3, 515, 500, 15);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (240, 80, 4, 216, 207, 9);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (241, 81, 2, 916, 897, 19);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (242, 81, 1, 575, 544, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (243, 81, 4, 673, 646, 27);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (244, 82, 4, 263, 246, 17);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (245, 82, 1, 239, 238, 1);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (246, 82, 5, 364, 314, 50);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (247, 83, 3, 972, 970, 2);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (248, 83, 2, 839, 808, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (249, 83, 5, 901, 900, 1);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (250, 84, 1, 716, 697, 19);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (251, 84, 5, 987, 983, 4);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (252, 84, 2, 516, 493, 23);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (253, 85, 1, 962, 954, 8);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (254, 85, 4, 341, 334, 7);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (255, 85, 3, 684, 637, 47);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (256, 86, 5, 780, 743, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (257, 86, 1, 893, 860, 33);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (258, 86, 3, 357, 326, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (259, 87, 1, 225, 176, 49);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (260, 87, 2, 926, 906, 20);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (261, 87, 3, 437, 400, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (262, 88, 4, 323, 314, 9);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (263, 88, 2, 209, 198, 11);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (264, 88, 5, 319, 302, 17);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (265, 89, 4, 983, 946, 37);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (266, 89, 5, 157, 126, 31);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (267, 89, 2, 646, 623, 23);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (268, 90, 1, 533, 503, 30);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (269, 90, 3, 799, 784, 15);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (270, 90, 4, 384, 334, 50);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (271, 91, 2, 832, 809, 23);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (272, 91, 3, 941, 922, 19);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (273, 91, 5, 121, 80, 41);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (274, 92, 5, 778, 775, 3);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (275, 92, 1, 807, 757, 50);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (276, 92, 3, 787, 772, 15);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (277, 93, 3, 754, 710, 44);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (278, 93, 4, 471, 428, 43);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (279, 93, 2, 224, 190, 34);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (280, 94, 2, 108, 83, 25);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (281, 94, 4, 361, 342, 19);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (282, 94, 5, 192, 144, 48);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (283, 95, 2, 980, 970, 10);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (284, 95, 3, 549, 509, 40);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (285, 95, 1, 424, 375, 49);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (286, 96, 4, 987, 982, 5);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (287, 96, 1, 942, 893, 49);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (288, 96, 2, 603, 573, 30);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (289, 97, 4, 532, 526, 6);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (290, 97, 3, 944, 934, 10);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (291, 97, 5, 815, 799, 16);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (292, 98, 5, 530, 492, 38);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (293, 98, 1, 846, 811, 35);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (294, 98, 2, 855, 835, 20);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (295, 99, 4, 756, 715, 41);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (296, 99, 1, 288, 248, 40);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (297, 99, 5, 554, 504, 50);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (298, 100, 2, 304, 264, 40);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (299, 100, 1, 412, 392, 20);
INSERT INTO inv_stock (id, sku_id, warehouse_id, total_stock, available_stock, locked_stock)
VALUES (300, 100, 5, 689, 684, 5);

-- Order Service Data
USE mall_order;

-- Orders
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (1, 'ORD20250908000001', 15, 823.35, 797.53, 25.82, 232.0, 591.35, 3, 1, '2025-09-07 10:48:24', '赵丽', '16811532033', '河南', '重庆市', '天河区', '22号街72号', '2025-09-08 00:47:54');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (2, 'ORD20250908000002', 81, 1155.7, 1151.25, 4.45, 159.71, 995.99, 2, 1, '2025-08-12 00:43:44', '赵勇', '16203917494', '四川', '上海市', '天河区', '731号街25号', '2025-08-05 11:01:31');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (3, 'ORD20250908000003', 37, 3753.85, 3734.75, 19.1, 483.25, 3270.6, 1, 1, '2025-08-23 08:24:30', '刘强', '14709242560', '四川', '广州市', '江宁区', '998号巷51号', '2025-07-31 05:13:50');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (4, 'ORD20250908000004', 92, 1944.8000000000002, 1923.64, 21.16, 344.64, 1600.1600000000003, 0, NULL, NULL, '周伟', '13943668780', '湖北', '重庆市', '武侯区', '310号路86号', '2025-08-15 14:59:24');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (5, 'ORD20250908000005', 17, 4868.589999999999, 4840.61, 27.98, 842.97, 4025.619999999999, 2, 2, '2025-08-26 01:53:50', '黄娜', '13742394923', '河南', '杭州市', '余杭区', '628号路67号', '2025-08-10 00:05:40');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (6, 'ORD20250908000006', 56, 4480.21, 4464.43, 15.78, 1332.05, 3148.16, 3, 1, '2025-09-05 14:08:08', '黄伟', '14014141844', '湖北', '武汉市', '洪山区', '505号街93号', '2025-07-24 23:43:04');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (7, 'ORD20250908000007', 16, 1764.72, 1754.53, 10.19, 149.55, 1615.17, 3, 2, '2025-09-05 22:57:30', '吴敏', '17955407680', '上海', '成都市', '江宁区', '605号街42号', '2025-07-25 13:03:02');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (8, 'ORD20250908000008', 4, 456.03999999999996, 414.51, 41.53, 82.02, 374.02, 3, 2, '2025-09-07 11:01:33', '张丽', '19693779520', '浙江', '南京市', '朝阳区', '762号街71号', '2025-09-07 02:10:07');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (9, 'ORD20250908000009', 49, 484.59, 471.83, 12.76, 33.98, 450.60999999999996, 3, 2, '2025-08-13 22:38:22', '张敏', '14727614483', '湖南', '广州市', '江宁区', '682号街48号', '2025-08-29 09:17:46');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (10, 'ORD20250908000010', 26, 2497.49, 2453.22, 44.27, 350.64, 2146.85, 3, 2, '2025-08-13 23:50:39', '赵丽', '17440342614', '湖北', '上海市', '余杭区', '480号路11号', '2025-08-25 23:38:34');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (11, 'ORD20250908000011', 39, 757.9399999999999, 751.55, 6.39, 203.57, 554.3699999999999, 3, 2, '2025-08-23 22:59:19', '吴伟', '16955447835', '湖北', '北京市', '海淀区', '759号巷60号', '2025-07-28 01:06:52');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (12, 'ORD20250908000012', 24, 4218.38, 4216.12, 2.26, 526.49, 3691.8900000000003, 3, 2, '2025-09-07 02:52:30', '黄磊', '17298117681', '湖北', '北京市', '朝阳区', '405号街2号', '2025-07-27 06:54:00');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (13, 'ORD20250908000013', 84, 2425.42, 2390.83, 34.59, 623.13, 1802.29, 5, 2, '2025-08-24 07:30:55', '吴勇', '15663310109', '北京', '深圳市', '洪山区', '665号街84号', '2025-08-03 02:06:59');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (14, 'ORD20250908000014', 56, 4254.94, 4212.03, 42.91, 680.56, 3574.3799999999997, 0, NULL, NULL, '黄娜', '16011794852', '湖南', '成都市', '朝阳区', '342号街14号', '2025-08-21 12:51:59');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (15, 'ORD20250908000015', 54, 4281.450000000001, 4244.6, 36.85, 758.73, 3522.7200000000007, 3, 2, '2025-08-11 09:51:21', '黄强', '16566847543', '上海', '杭州市', '武侯区', '911号路15号', '2025-08-27 15:40:09');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (16, 'ORD20250908000016', 80, 4712.77, 4695.43, 17.34, 982.54, 3730.2300000000005, 0, NULL, NULL, '陈敏', '16455698748', '河南', '成都市', '江宁区', '816号路21号', '2025-07-30 17:33:31');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (17, 'ORD20250908000017', 37, 3460.89, 3456.58, 4.31, 500.74, 2960.1499999999996, 1, 1, '2025-09-08 00:28:35', '黄芳', '18942593261', '湖北', '北京市', '海淀区', '346号路15号', '2025-09-08 13:45:13');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (18, 'ORD20250908000018', 30, 623.4300000000001, 612.23, 11.2, 47.35, 576.08, 2, 2, '2025-08-11 21:06:34', '周静', '19886109797', '上海', '重庆市', '渝北区', '801号街72号', '2025-07-14 16:31:25');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (19, 'ORD20250908000019', 65, 567.5, 536.0, 31.5, 140.04, 427.46000000000004, 4, 1, '2025-08-23 19:04:32', '黄洋', '16813812970', '四川', '南京市', '余杭区', '518号街11号', '2025-08-05 10:35:20');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (20, 'ORD20250908000020', 48, 3599.6, 3578.85, 20.75, 675.8, 2923.8, 1, 2, '2025-08-20 16:34:44', '王强', '15448730108', '上海', '上海市', '江宁区', '323号路99号', '2025-07-26 13:37:55');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (21, 'ORD20250908000021', 56, 354.69, 316.73, 37.96, 62.73, 291.96, 3, 1, '2025-09-03 20:18:48', '陈强', '15653790259', '河南', '武汉市', '天河区', '909号巷80号', '2025-08-22 12:59:51');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (22, 'ORD20250908000022', 90, 1773.3899999999999, 1732.8, 40.59, 337.25, 1436.1399999999999, 3, 2, '2025-08-13 16:55:19', '张洋', '15771920557', '广东', '北京市', '朝阳区', '912号巷76号', '2025-08-04 17:31:10');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (23, 'ORD20250908000023', 31, 4178.0199999999995, 4162.82, 15.2, 527.94, 3650.0799999999995, 3, 1, '2025-08-27 17:16:46', '陈洋', '15061274989', '湖南', '西安市', '渝北区', '240号路39号', '2025-08-07 05:44:36');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (24, 'ORD20250908000024', 86, 3961.8399999999997, 3926.1, 35.74, 792.01, 3169.83, 3, 1, '2025-08-14 17:19:33', '杨洋', '17830780084', '四川', '上海市', '江宁区', '694号巷52号', '2025-09-05 17:25:30');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (25, 'ORD20250908000025', 72, 2619.2799999999997, 2599.54, 19.74, 205.64, 2413.64, 3, 2, '2025-08-30 23:27:29', '黄强', '18117178124', '广东', '西安市', '江宁区', '944号街78号', '2025-08-19 06:52:51');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (26, 'ORD20250908000026', 15, 641.71, 613.12, 28.59, 7.41, 634.3000000000001, 1, 2, '2025-08-24 09:59:26', '杨静', '15017493217', '北京', '上海市', '雁塔区', '358号街6号', '2025-08-20 03:44:54');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (27, 'ORD20250908000027', 62, 2897.85, 2869.56, 28.29, 775.03, 2122.8199999999997, 3, 2, '2025-09-08 00:25:50', '王娜', '16444837642', '河南', '南京市', '雁塔区', '47号巷57号', '2025-08-06 13:03:30');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (28, 'ORD20250908000028', 83, 460.03999999999996, 451.76, 8.28, 3.36, 456.67999999999995, 3, 2, '2025-09-05 20:23:30', '李洋', '13957957506', '湖北', '广州市', '朝阳区', '282号街32号', '2025-09-04 21:11:00');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (29, 'ORD20250908000029', 57, 459.07, 432.39, 26.68, 41.57, 417.5, 3, 2, '2025-08-10 17:49:53', '杨强', '14053095217', '广东', '杭州市', '武侯区', '695号街8号', '2025-09-08 06:33:33');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (30, 'ORD20250908000030', 42, 951.14, 915.47, 35.67, 81.85, 869.29, 0, NULL, NULL, '王强', '19298025255', '上海', '杭州市', '朝阳区', '326号路71号', '2025-08-19 03:17:04');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (31, 'ORD20250908000031', 9, 2446.39, 2426.02, 20.37, 170.12, 2276.27, 4, 2, '2025-09-05 12:16:20', '吴芳', '14573406293', '广东', '深圳市', '雁塔区', '976号路72号', '2025-08-23 11:21:46');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (32, 'ORD20250908000032', 6, 38.230000000000004, 15.0, 23.23, 3.73, 34.50000000000001, 2, 1, '2025-08-09 15:21:37', '张磊', '14269828215', '上海', '杭州市', '海淀区', '405号巷16号', '2025-07-24 04:08:11');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (33, 'ORD20250908000033', 93, 2800.7999999999997, 2786.83, 13.97, 361.14, 2439.66, 3, 2, '2025-08-26 05:55:36', '陈磊', '14632939196', '北京', '北京市', '浦东新区', '424号巷90号', '2025-08-27 02:07:33');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (34, 'ORD20250908000034', 98, 1974.09, 1970.03, 4.06, 518.15, 1455.94, 3, 1, '2025-08-31 05:23:46', '李丽', '19363889131', '北京', '深圳市', '朝阳区', '777号路88号', '2025-08-14 08:30:40');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (35, 'ORD20250908000035', 25, 489.6, 458.93, 30.67, 41.74, 447.86, 3, 1, '2025-09-02 14:03:23', '杨伟', '14972183854', '湖北', '深圳市', '渝北区', '689号巷78号', '2025-09-03 11:35:59');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (36, 'ORD20250908000036', 23, 342.56, 336.15, 6.41, 6.56, 336.0, 2, 1, '2025-09-04 00:09:36', '陈敏', '18096668046', '北京', '南京市', '武侯区', '865号路36号', '2025-07-22 10:21:02');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (37, 'ORD20250908000037', 88, 2621.07, 2596.29, 24.78, 402.88, 2218.19, 0, NULL, NULL, '刘伟', '14440105079', '河南', '上海市', '天河区', '202号街43号', '2025-08-09 18:57:28');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (38, 'ORD20250908000038', 9, 3138.55, 3133.71, 4.84, 451.22, 2687.33, 3, 2, '2025-08-21 18:28:33', '刘芳', '16988520540', '四川', '重庆市', '海淀区', '88号街5号', '2025-08-16 06:07:19');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (39, 'ORD20250908000039', 41, 4486.1900000000005, 4451.52, 34.67, 1045.19, 3441.0000000000005, 0, NULL, NULL, '黄伟', '15606523072', '湖南', '重庆市', '朝阳区', '465号路60号', '2025-08-21 04:17:11');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (40, 'ORD20250908000040', 80, 3116.94, 3099.96, 16.98, 871.94, 2245.0, 3, 2, '2025-09-03 17:19:28', '杨勇', '14150715624', '湖南', '武汉市', '浦东新区', '249号街6号', '2025-07-16 11:40:56');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (41, 'ORD20250908000041', 17, 1587.01, 1565.22, 21.79, 450.66, 1136.35, 3, 2, '2025-09-06 22:44:52', '周娜', '14333056058', '北京', '成都市', '江宁区', '201号街82号', '2025-07-30 03:42:01');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (42, 'ORD20250908000042', 9, 1747.73, 1714.34, 33.39, 357.33, 1390.4, 5, 2, '2025-09-06 16:37:30', '吴丽', '17414702022', '湖南', '深圳市', '天河区', '493号巷99号', '2025-08-11 19:12:50');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (43, 'ORD20250908000043', 95, 1905.82, 1885.8, 20.02, 467.35, 1438.4699999999998, 0, NULL, NULL, '张芳', '15514693912', '江苏', '杭州市', '浦东新区', '348号巷32号', '2025-07-27 22:45:34');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (44, 'ORD20250908000044', 26, 159.39, 117.25, 42.14, 32.03, 127.35999999999999, 0, NULL, NULL, '王强', '17832433157', '湖南', '成都市', '海淀区', '122号街17号', '2025-07-29 00:37:25');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (45, 'ORD20250908000045', 25, 2702.1699999999996, 2667.45, 34.72, 254.44, 2447.7299999999996, 2, 1, '2025-08-11 20:32:56', '吴勇', '14573078898', '江苏', '上海市', '洪山区', '810号巷79号', '2025-07-16 21:53:34');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (46, 'ORD20250908000046', 22, 2585.55, 2547.98, 37.57, 493.09, 2092.46, 3, 2, '2025-09-03 19:16:52', '黄娜', '15367424950', '江苏', '深圳市', '洪山区', '884号街51号', '2025-07-31 20:05:17');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (47, 'ORD20250908000047', 40, 2149.71, 2130.86, 18.85, 100.5, 2049.21, 3, 2, '2025-09-05 08:10:02', '李娜', '18252053443', '四川', '南京市', '余杭区', '392号街23号', '2025-08-22 11:56:11');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (48, 'ORD20250908000048', 30, 1339.26, 1335.47, 3.79, 361.17, 978.0899999999999, 2, 1, '2025-08-23 00:47:10', '刘芳', '13353627781', '湖南', '广州市', '雁塔区', '935号路2号', '2025-08-08 16:33:11');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (49, 'ORD20250908000049', 37, 4152.48, 4113.32, 39.16, 498.3, 3654.1799999999994, 2, 1, '2025-09-01 12:39:08', '周娜', '19272255483', '上海', '南京市', '天河区', '440号巷54号', '2025-07-30 23:05:44');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (50, 'ORD20250908000050', 57, 2283.28, 2255.36, 27.92, 253.53, 2029.7500000000002, 3, 1, '2025-08-24 10:44:41', '张磊', '18834647530', '北京', '广州市', '浦东新区', '976号路73号', '2025-09-07 03:49:33');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (51, 'ORD20250908000051', 78, 2893.19, 2873.65, 19.54, 328.73, 2564.46, 4, 1, '2025-09-04 13:27:15', '赵敏', '16396316186', '北京', '广州市', '天河区', '72号路37号', '2025-07-30 14:29:29');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (52, 'ORD20250908000052', 30, 1488.84, 1462.62, 26.22, 199.5, 1289.34, 2, 1, '2025-08-17 22:55:09', '杨强', '15914739283', '四川', '广州市', '渝北区', '589号路76号', '2025-07-30 07:41:19');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (53, 'ORD20250908000053', 65, 3609.64, 3586.69, 22.95, 217.03, 3392.6099999999997, 5, 2, '2025-08-12 17:15:29', '赵勇', '18598457284', '浙江', '成都市', '渝北区', '844号街54号', '2025-08-03 19:15:14');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (54, 'ORD20250908000054', 23, 4537.48, 4500.82, 36.66, 618.18, 3919.2999999999997, 4, 1, '2025-09-06 05:12:45', '李强', '17081856079', '四川', '成都市', '雁塔区', '125号路10号', '2025-07-20 19:55:07');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (55, 'ORD20250908000055', 65, 3851.3300000000004, 3835.03, 16.3, 518.81, 3332.5200000000004, 2, 1, '2025-09-01 01:11:30', '张芳', '17643310271', '四川', '武汉市', '洪山区', '70号街71号', '2025-08-03 03:18:46');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (56, 'ORD20250908000056', 92, 4757.6, 4750.59, 7.01, 1169.25, 3588.3500000000004, 3, 1, '2025-08-10 11:46:34', '吴芳', '14171602417', '湖北', '深圳市', '洪山区', '714号街82号', '2025-08-30 09:06:48');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (57, 'ORD20250908000057', 83, 3349.07, 3316.07, 33.0, 102.26, 3246.81, 3, 2, '2025-08-12 03:47:45', '吴伟', '13322910008', '上海', '深圳市', '海淀区', '817号巷22号', '2025-07-26 19:58:33');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (58, 'ORD20250908000058', 6, 2089.62, 2084.24, 5.38, 204.55, 1885.07, 3, 1, '2025-08-19 16:02:21', '周强', '19123143340', '浙江', '重庆市', '江宁区', '45号街28号', '2025-08-19 12:39:10');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (59, 'ORD20250908000059', 39, 3865.03, 3840.05, 24.98, 1141.04, 2723.9900000000002, 3, 1, '2025-08-21 20:15:33', '李勇', '15639711769', '四川', '上海市', '江宁区', '19号巷75号', '2025-08-23 04:46:03');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (60, 'ORD20250908000060', 49, 2597.11, 2560.02, 37.09, 386.63, 2210.48, 3, 1, '2025-08-20 21:43:59', '张勇', '18124201423', '北京', '深圳市', '渝北区', '969号路95号', '2025-08-11 12:37:50');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (61, 'ORD20250908000061', 18, 1430.03, 1397.85, 32.18, 318.92, 1111.11, 3, 2, '2025-08-23 14:48:08', '张敏', '18325962676', '河南', '西安市', '江宁区', '188号路81号', '2025-07-11 04:48:29');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (62, 'ORD20250908000062', 9, 4354.14, 4350.64, 3.5, 251.2, 4102.9400000000005, 3, 1, '2025-08-19 12:39:40', '王芳', '14572544114', '湖南', '深圳市', '余杭区', '293号巷58号', '2025-07-30 04:01:34');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (63, 'ORD20250908000063', 35, 4337.56, 4313.5, 24.06, 1230.66, 3106.9000000000005, 2, 2, '2025-08-17 18:11:21', '黄磊', '15641947409', '浙江', '重庆市', '雁塔区', '157号巷94号', '2025-07-27 16:08:12');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (64, 'ORD20250908000064', 33, 1923.74, 1908.45, 15.29, 162.16, 1761.58, 3, 2, '2025-08-13 02:32:36', '杨娜', '18190873443', '山东', '成都市', '洪山区', '316号巷76号', '2025-07-17 12:42:57');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (65, 'ORD20250908000065', 53, 4475.7699999999995, 4426.15, 49.62, 165.29, 4310.48, 4, 1, '2025-08-19 01:01:12', '赵静', '18737630342', '四川', '北京市', '江宁区', '647号巷87号', '2025-07-13 08:34:09');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (66, 'ORD20250908000066', 90, 3339.1099999999997, 3331.72, 7.39, 605.14, 2733.97, 4, 1, '2025-08-21 22:59:41', '刘勇', '17611073790', '江苏', '成都市', '浦东新区', '850号路33号', '2025-08-08 22:57:01');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (67, 'ORD20250908000067', 65, 4871.66, 4838.74, 32.92, 555.66, 4316.0, 2, 2, '2025-08-13 22:16:22', '李勇', '18317933980', '上海', '成都市', '雁塔区', '427号巷37号', '2025-08-21 10:54:47');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (68, 'ORD20250908000068', 22, 899.5600000000001, 890.24, 9.32, 35.88, 863.6800000000001, 3, 1, '2025-08-31 11:47:08', '吴敏', '14017294668', '广东', '上海市', '余杭区', '88号路16号', '2025-08-06 11:37:49');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (69, 'ORD20250908000069', 9, 1746.62, 1717.34, 29.28, 246.21, 1500.4099999999999, 3, 1, '2025-08-10 01:48:13', '张静', '18059050697', '四川', '武汉市', '江宁区', '619号路44号', '2025-08-15 14:43:11');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (70, 'ORD20250908000070', 88, 4501.83, 4451.86, 49.97, 1090.95, 3410.88, 4, 2, '2025-08-27 23:38:14', '吴伟', '19880848901', '湖北', '杭州市', '天河区', '378号路80号', '2025-08-25 18:01:21');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (71, 'ORD20250908000071', 66, 2080.9, 2071.5, 9.4, 612.86, 1468.04, 5, 2, '2025-08-10 10:06:12', '杨伟', '19762477399', '四川', '上海市', '武侯区', '301号巷25号', '2025-07-24 12:19:05');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (72, 'ORD20250908000072', 67, 4421.65, 4382.41, 39.24, 942.22, 3479.4299999999994, 0, NULL, NULL, '黄芳', '15658141622', '上海', '上海市', '武侯区', '733号街21号', '2025-08-11 07:31:47');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (73, 'ORD20250908000073', 50, 3844.9, 3833.88, 11.02, 296.32, 3548.58, 3, 1, '2025-08-16 04:53:43', '周磊', '13689766377', '河南', '西安市', '武侯区', '828号街29号', '2025-08-04 18:27:37');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (74, 'ORD20250908000074', 15, 1467.54, 1461.97, 5.57, 149.23, 1318.31, 3, 1, '2025-08-15 06:00:04', '吴丽', '13337351347', '广东', '西安市', '渝北区', '605号街14号', '2025-08-17 23:52:20');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (75, 'ORD20250908000075', 2, 2719.11, 2696.08, 23.03, 117.04, 2602.07, 4, 1, '2025-09-02 21:04:30', '黄娜', '17168753919', '湖南', '武汉市', '天河区', '507号路91号', '2025-08-16 04:31:21');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (76, 'ORD20250908000076', 8, 4922.5599999999995, 4905.9, 16.66, 1218.46, 3704.0999999999995, 1, 1, '2025-08-12 03:58:19', '王磊', '18289103443', '河南', '成都市', '海淀区', '541号巷37号', '2025-08-07 00:41:01');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (77, 'ORD20250908000077', 90, 4651.150000000001, 4616.09, 35.06, 758.9, 3892.2500000000005, 0, NULL, NULL, '杨勇', '19093654482', '江苏', '北京市', '江宁区', '744号巷14号', '2025-07-24 22:40:24');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (78, 'ORD20250908000078', 49, 4773.849999999999, 4752.49, 21.36, 1199.99, 3573.8599999999997, 4, 1, '2025-08-26 21:00:46', '黄娜', '19153417213', '浙江', '重庆市', '浦东新区', '669号巷49号', '2025-07-19 07:32:12');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (79, 'ORD20250908000079', 100, 2364.64, 2323.73, 40.91, 266.37, 2098.27, 3, 2, '2025-08-27 10:41:24', '张芳', '13169259460', '湖南', '广州市', '武侯区', '496号巷90号', '2025-08-21 17:13:14');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (80, 'ORD20250908000080', 39, 2866.37, 2861.4, 4.97, 54.51, 2811.8599999999997, 4, 1, '2025-08-29 06:58:19', '张强', '16584557717', '上海', '广州市', '渝北区', '712号街1号', '2025-09-02 11:19:23');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (81, 'ORD20250908000081', 58, 4010.71, 4004.56, 6.15, 154.76, 3855.95, 1, 1, '2025-08-13 21:02:47', '周静', '18095354903', '湖北', '杭州市', '雁塔区', '456号街74号', '2025-08-27 05:32:32');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (82, 'ORD20250908000082', 89, 1617.09, 1571.55, 45.54, 469.95, 1147.1399999999999, 3, 1, '2025-08-14 10:04:54', '张敏', '13182155274', '湖北', '杭州市', '余杭区', '391号巷21号', '2025-08-18 10:56:01');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (83, 'ORD20250908000083', 59, 438.15, 425.44, 12.71, 54.29, 383.85999999999996, 3, 1, '2025-08-13 00:56:01', '陈敏', '18565183739', '江苏', '上海市', '余杭区', '128号巷15号', '2025-09-01 20:31:10');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (84, 'ORD20250908000084', 53, 371.15, 339.14, 32.01, 36.91, 334.24, 2, 2, '2025-08-16 17:12:50', '刘静', '13393191738', '四川', '上海市', '雁塔区', '358号巷74号', '2025-08-06 23:49:39');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (85, 'ORD20250908000085', 71, 1367.51, 1362.56, 4.95, 19.61, 1347.9, 2, 2, '2025-08-18 09:13:35', '杨洋', '16845267615', '浙江', '北京市', '江宁区', '725号街87号', '2025-09-07 17:06:30');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (86, 'ORD20250908000086', 54, 3546.0299999999997, 3536.87, 9.16, 1046.47, 2499.5599999999995, 3, 2, '2025-08-23 00:05:05', '赵洋', '19883894300', '江苏', '上海市', '浦东新区', '590号街11号', '2025-08-07 08:48:30');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (87, 'ORD20250908000087', 71, 4689.07, 4648.65, 40.42, 14.91, 4674.16, 4, 1, '2025-09-01 21:34:02', '黄洋', '17651018045', '山东', '深圳市', '雁塔区', '701号巷6号', '2025-09-07 14:00:38');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (88, 'ORD20250908000088', 58, 4857.32, 4823.59, 33.73, 1276.22, 3581.0999999999995, 3, 1, '2025-08-18 02:24:03', '李丽', '18594331925', '上海', '杭州市', '余杭区', '495号路34号', '2025-07-31 07:12:26');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (89, 'ORD20250908000089', 1, 3721.19, 3692.75, 28.44, 240.91, 3480.28, 2, 1, '2025-09-01 02:13:41', '吴磊', '13042137404', '江苏', '上海市', '雁塔区', '527号街53号', '2025-07-22 15:18:07');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (90, 'ORD20250908000090', 63, 82.94999999999999, 44.48, 38.47, 3.34, 79.60999999999999, 3, 2, '2025-08-24 03:25:12', '杨强', '19529641708', '江苏', '上海市', '天河区', '292号街18号', '2025-08-17 22:05:11');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (91, 'ORD20250908000091', 54, 3767.23, 3722.18, 45.05, 179.01, 3588.2200000000003, 1, 2, '2025-08-22 12:24:24', '李强', '16461773778', '广东', '上海市', '海淀区', '812号街17号', '2025-07-25 17:10:00');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (92, 'ORD20250908000092', 45, 2543.37, 2505.0, 38.37, 177.73, 2365.64, 3, 2, '2025-08-30 11:19:25', '吴伟', '16247215278', '江苏', '成都市', '天河区', '486号巷14号', '2025-08-28 09:15:02');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (93, 'ORD20250908000093', 79, 963.76, 914.13, 49.63, 162.13, 801.63, 3, 1, '2025-08-15 11:04:30', '王强', '13994600436', '江苏', '深圳市', '江宁区', '437号路8号', '2025-08-29 02:47:46');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (94, 'ORD20250908000094', 55, 3238.14, 3222.93, 15.21, 260.33, 2977.81, 2, 2, '2025-08-29 21:49:10', '刘丽', '19106322642', '四川', '南京市', '江宁区', '312号街13号', '2025-07-11 22:33:41');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (95, 'ORD20250908000095', 88, 2806.42, 2763.32, 43.1, 274.57, 2531.85, 3, 2, '2025-09-07 04:51:32', '周勇', '15623817159', '江苏', '深圳市', '洪山区', '948号街10号', '2025-09-01 16:20:57');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (96, 'ORD20250908000096', 80, 1757.33, 1721.06, 36.27, 510.97, 1246.36, 1, 1, '2025-08-11 17:46:17', '王敏', '15787853361', '河南', '上海市', '洪山区', '430号巷65号', '2025-07-12 17:41:59');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (97, 'ORD20250908000097', 22, 3034.6200000000003, 2999.78, 34.84, 116.03, 2918.59, 0, NULL, NULL, '吴伟', '18673689173', '河南', '成都市', '渝北区', '279号街24号', '2025-07-10 23:07:03');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (98, 'ORD20250908000098', 21, 2893.38, 2863.77, 29.61, 7.61, 2885.77, 4, 2, '2025-09-03 01:43:04', '周静', '15275102018', '广东', '杭州市', '朝阳区', '658号街27号', '2025-08-31 12:25:27');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (99, 'ORD20250908000099', 15, 34.36, 16.99, 17.37, 3.08, 31.28, 2, 1, '2025-09-05 06:40:46', '陈娜', '15630882919', '湖北', '重庆市', '海淀区', '740号巷89号', '2025-08-16 16:48:28');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (100, 'ORD20250908000100', 84, 1325.46, 1294.15, 31.31, 234.63, 1090.83, 1, 2, '2025-08-20 07:47:27', '杨静', '14683474144', '四川', '广州市', '浦东新区', '194号巷63号', '2025-07-28 03:06:20');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (101, 'ORD20250908000101', 83, 1432.91, 1417.4, 15.51, 376.43, 1056.48, 3, 1, '2025-08-12 23:38:51', '王芳', '15464717003', '上海', '北京市', '朝阳区', '136号街99号', '2025-07-18 17:31:10');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (102, 'ORD20250908000102', 35, 753.12, 721.75, 31.37, 2.92, 750.2, 5, 1, '2025-09-04 22:38:05', '杨芳', '14454131066', '上海', '北京市', '朝阳区', '106号巷38号', '2025-07-20 13:43:26');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (103, 'ORD20250908000103', 66, 4454.18, 4433.42, 20.76, 1.61, 4452.570000000001, 1, 1, '2025-08-30 08:22:35', '赵娜', '15296679492', '浙江', '南京市', '海淀区', '631号路16号', '2025-08-14 14:44:18');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (104, 'ORD20250908000104', 11, 4509.209999999999, 4478.98, 30.23, 239.27, 4269.939999999999, 3, 1, '2025-08-30 06:10:24', '王静', '17282432977', '广东', '广州市', '江宁区', '152号路58号', '2025-08-26 01:33:18');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (105, 'ORD20250908000105', 9, 596.0600000000001, 574.69, 21.37, 125.29, 470.77000000000004, 1, 2, '2025-08-15 19:19:03', '王强', '15449661993', '湖北', '武汉市', '雁塔区', '547号路69号', '2025-08-31 16:11:36');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (106, 'ORD20250908000106', 30, 1958.34, 1929.29, 29.05, 244.16, 1714.1799999999998, 3, 1, '2025-08-13 20:22:53', '刘丽', '13905990388', '广东', '杭州市', '洪山区', '148号巷54号', '2025-08-03 03:47:15');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (107, 'ORD20250908000107', 27, 4143.62, 4140.83, 2.79, 171.18, 3972.44, 5, 1, '2025-08-27 22:30:05', '张强', '15760880300', '湖南', '成都市', '浦东新区', '784号街61号', '2025-07-24 05:12:47');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (108, 'ORD20250908000108', 96, 2689.42, 2641.98, 47.44, 372.67, 2316.75, 3, 2, '2025-08-10 06:07:04', '陈芳', '14807508685', '浙江', '重庆市', '雁塔区', '314号巷30号', '2025-08-20 17:57:03');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (109, 'ORD20250908000109', 21, 1371.02, 1366.42, 4.6, 154.77, 1216.25, 5, 1, '2025-08-29 01:13:49', '吴伟', '14175884368', '广东', '北京市', '江宁区', '663号路25号', '2025-08-05 19:41:09');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (110, 'ORD20250908000110', 14, 549.1, 530.96, 18.14, 124.05, 425.05, 2, 1, '2025-08-23 07:31:21', '王洋', '13209129205', '北京', '重庆市', '洪山区', '47号街41号', '2025-07-19 15:55:29');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (111, 'ORD20250908000111', 40, 70.14, 52.31, 17.83, 4.43, 65.71000000000001, 2, 1, '2025-08-24 21:06:53', '赵丽', '19704718153', '湖南', '北京市', '余杭区', '166号街88号', '2025-08-24 13:50:36');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (112, 'ORD20250908000112', 11, 4431.83, 4427.17, 4.66, 954.23, 3477.6, 4, 1, '2025-08-17 10:21:40', '周洋', '19657412690', '河南', '深圳市', '天河区', '626号巷10号', '2025-07-25 09:50:55');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (113, 'ORD20250908000113', 73, 3167.55, 3142.67, 24.88, 758.7, 2408.8500000000004, 1, 2, '2025-09-05 04:11:14', '李磊', '13391230172', '四川', '杭州市', '洪山区', '666号街16号', '2025-09-03 19:58:21');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (114, 'ORD20250908000114', 63, 250.51999999999998, 207.57, 42.95, 24.97, 225.54999999999998, 4, 1, '2025-08-31 00:19:45', '杨洋', '18936528666', '江苏', '西安市', '朝阳区', '159号街38号', '2025-08-16 02:27:20');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (115, 'ORD20250908000115', 21, 2897.66, 2854.97, 42.69, 191.04, 2706.62, 4, 1, '2025-08-26 13:16:11', '黄磊', '17940799713', '山东', '南京市', '渝北区', '498号街90号', '2025-08-25 20:23:58');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (116, 'ORD20250908000116', 15, 1401.0, 1399.09, 1.91, 65.37, 1335.63, 3, 1, '2025-08-25 03:46:20', '吴伟', '19708792207', '河南', '杭州市', '余杭区', '651号街11号', '2025-07-24 09:00:03');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (117, 'ORD20250908000117', 9, 1380.8799999999999, 1376.79, 4.09, 360.9, 1019.9799999999999, 3, 2, '2025-09-04 02:08:18', '刘静', '14084921945', '广东', '成都市', '江宁区', '935号巷74号', '2025-08-17 09:29:05');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (118, 'ORD20250908000118', 2, 2853.62, 2808.93, 44.69, 167.32, 2686.2999999999997, 3, 1, '2025-08-25 07:54:12', '杨伟', '14418194504', '广东', '武汉市', '渝北区', '100号街28号', '2025-07-28 20:00:00');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (119, 'ORD20250908000119', 54, 3523.99, 3485.27, 38.72, 628.33, 2895.66, 1, 1, '2025-08-18 21:26:38', '吴伟', '15333814943', '湖北', '成都市', '雁塔区', '998号路15号', '2025-07-27 16:24:13');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (120, 'ORD20250908000120', 59, 2156.4700000000003, 2147.63, 8.84, 383.28, 1773.1900000000003, 3, 2, '2025-08-23 10:48:33', '赵静', '17990080091', '湖南', '西安市', '海淀区', '649号路65号', '2025-09-05 01:06:00');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (121, 'ORD20250908000121', 5, 3384.46, 3341.87, 42.59, 212.41, 3172.05, 3, 2, '2025-08-15 04:07:52', '刘磊', '19374967397', '湖北', '杭州市', '余杭区', '901号巷34号', '2025-08-09 11:00:50');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (122, 'ORD20250908000122', 54, 3369.98, 3369.77, 0.21, 919.0, 2450.98, 0, NULL, NULL, '张娜', '18857315453', '湖南', '成都市', '朝阳区', '821号巷55号', '2025-07-23 03:01:03');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (123, 'ORD20250908000123', 82, 1994.5600000000002, 1958.15, 36.41, 236.81, 1757.7500000000002, 2, 1, '2025-08-30 20:20:15', '刘芳', '17115379813', '上海', '上海市', '朝阳区', '448号街81号', '2025-09-06 05:11:22');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (124, 'ORD20250908000124', 20, 184.19, 148.81, 35.38, 41.1, 143.09, 0, NULL, NULL, '赵伟', '18146328252', '河南', '杭州市', '朝阳区', '282号街19号', '2025-07-12 20:38:59');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (125, 'ORD20250908000125', 82, 4967.490000000001, 4924.14, 43.35, 163.87, 4803.620000000001, 4, 1, '2025-08-14 19:28:52', '王静', '14108921744', '上海', '深圳市', '武侯区', '385号路82号', '2025-08-23 09:21:03');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (126, 'ORD20250908000126', 21, 907.74, 907.23, 0.51, 48.63, 859.11, 3, 1, '2025-08-15 07:17:02', '杨丽', '16651791005', '广东', '深圳市', '江宁区', '118号街4号', '2025-08-11 09:57:02');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (127, 'ORD20250908000127', 18, 1143.0, 1106.61, 36.39, 312.01, 830.99, 2, 2, '2025-08-19 06:51:22', '李丽', '17648499218', '浙江', '成都市', '海淀区', '598号巷40号', '2025-07-26 16:43:39');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (128, 'ORD20250908000128', 27, 4793.660000000001, 4769.27, 24.39, 1263.18, 3530.4800000000005, 2, 2, '2025-08-25 02:05:48', '吴芳', '17453601055', '浙江', '重庆市', '雁塔区', '361号路4号', '2025-07-21 17:52:53');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (129, 'ORD20250908000129', 24, 4068.58, 4047.11, 21.47, 225.73, 3842.85, 1, 1, '2025-08-27 15:44:36', '杨娜', '18130347853', '湖南', '深圳市', '雁塔区', '223号街72号', '2025-08-06 14:37:39');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (130, 'ORD20250908000130', 92, 3087.61, 3043.44, 44.17, 785.13, 2302.48, 0, NULL, NULL, '赵强', '15318494113', '河南', '深圳市', '渝北区', '22号路3号', '2025-09-04 12:20:34');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (131, 'ORD20250908000131', 1, 4573.320000000001, 4559.1, 14.22, 1151.14, 3422.1800000000003, 2, 1, '2025-08-25 23:48:55', '张芳', '16843330740', '浙江', '武汉市', '洪山区', '870号巷16号', '2025-08-15 21:59:31');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (132, 'ORD20250908000132', 80, 533.8900000000001, 522.69, 11.2, 129.44, 404.4500000000001, 1, 2, '2025-08-30 18:20:04', '刘娜', '13788506080', '北京', '杭州市', '海淀区', '271号路89号', '2025-09-08 02:32:24');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (133, 'ORD20250908000133', 49, 1365.3799999999999, 1356.86, 8.52, 56.08, 1309.3, 3, 1, '2025-08-25 02:46:38', '王勇', '19944155693', '四川', '深圳市', '海淀区', '271号路92号', '2025-07-18 15:39:08');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (134, 'ORD20250908000134', 46, 2840.6299999999997, 2796.99, 43.64, 553.37, 2287.2599999999998, 3, 1, '2025-08-13 09:37:25', '李磊', '13028112558', '浙江', '武汉市', '浦东新区', '869号巷14号', '2025-07-23 05:47:26');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (135, 'ORD20250908000135', 29, 4603.599999999999, 4584.32, 19.28, 622.23, 3981.3699999999994, 3, 1, '2025-08-29 01:00:01', '刘芳', '14138618728', '广东', '杭州市', '朝阳区', '488号巷68号', '2025-08-23 13:27:27');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (136, 'ORD20250908000136', 46, 1381.59, 1335.24, 46.35, 33.67, 1347.9199999999998, 2, 1, '2025-08-28 21:10:56', '周强', '16020674245', '湖北', '重庆市', '洪山区', '532号路14号', '2025-07-15 10:56:12');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (137, 'ORD20250908000137', 83, 1257.78, 1252.23, 5.55, 43.27, 1214.51, 4, 2, '2025-08-21 04:54:41', '李洋', '13267317822', '江苏', '南京市', '余杭区', '723号巷37号', '2025-07-10 21:58:02');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (138, 'ORD20250908000138', 13, 978.93, 933.54, 45.39, 174.23, 804.6999999999999, 3, 1, '2025-08-17 21:03:47', '杨芳', '14349640981', '北京', '重庆市', '江宁区', '993号路75号', '2025-07-10 17:04:22');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (139, 'ORD20250908000139', 63, 4390.99, 4367.36, 23.63, 59.19, 4331.8, 0, NULL, NULL, '陈敏', '16110374792', '四川', '杭州市', '江宁区', '470号街46号', '2025-08-08 20:25:15');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (140, 'ORD20250908000140', 91, 1701.25, 1675.36, 25.89, 333.7, 1367.55, 4, 2, '2025-08-15 02:06:14', '陈洋', '18098614279', '山东', '深圳市', '海淀区', '199号路82号', '2025-07-21 02:13:36');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (141, 'ORD20250908000141', 36, 4420.889999999999, 4386.95, 33.94, 1183.45, 3237.4399999999996, 2, 1, '2025-08-22 11:50:07', '杨丽', '15493053644', '山东', '杭州市', '海淀区', '993号街64号', '2025-07-24 21:34:05');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (142, 'ORD20250908000142', 60, 3433.97, 3407.81, 26.16, 296.26, 3137.71, 3, 1, '2025-08-24 08:30:01', '黄静', '13242814038', '北京', '杭州市', '浦东新区', '359号路65号', '2025-09-03 01:22:04');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (143, 'ORD20250908000143', 20, 4804.55, 4768.89, 35.66, 204.08, 4600.47, 3, 2, '2025-09-07 00:33:50', '李静', '14438649023', '浙江', '广州市', '渝北区', '447号巷42号', '2025-09-04 17:57:19');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (144, 'ORD20250908000144', 18, 3429.75, 3419.77, 9.98, 139.21, 3290.54, 3, 2, '2025-08-24 10:04:26', '李静', '16653841439', '山东', '武汉市', '江宁区', '438号路54号', '2025-08-19 10:41:58');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (145, 'ORD20250908000145', 60, 176.04, 148.09, 27.95, 42.54, 133.5, 2, 1, '2025-09-08 11:29:51', '陈敏', '17368808164', '湖北', '武汉市', '朝阳区', '87号街35号', '2025-07-21 00:10:11');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (146, 'ORD20250908000146', 43, 3016.86, 2975.76, 41.1, 504.79, 2512.07, 3, 1, '2025-08-15 12:13:07', '吴洋', '18021742683', '浙江', '北京市', '洪山区', '639号巷26号', '2025-07-21 18:01:07');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (147, 'ORD20250908000147', 9, 40.370000000000005, 10.18, 30.19, 0.37, 40.00000000000001, 0, NULL, NULL, '刘芳', '16840149885', '湖南', '武汉市', '渝北区', '127号路19号', '2025-07-12 17:11:39');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (148, 'ORD20250908000148', 24, 754.24, 717.01, 37.23, 51.77, 702.47, 3, 1, '2025-09-01 17:29:33', '黄静', '15153237481', '浙江', '武汉市', '海淀区', '578号路2号', '2025-09-04 23:28:46');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (149, 'ORD20250908000149', 39, 212.60000000000002, 180.08, 32.52, 35.5, 177.10000000000002, 2, 2, '2025-08-16 09:01:41', '黄磊', '14938537306', '山东', '重庆市', '江宁区', '471号巷18号', '2025-08-06 15:18:26');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (150, 'ORD20250908000150', 98, 3341.81, 3295.64, 46.17, 67.44, 3274.37, 3, 2, '2025-08-27 21:24:30', '黄勇', '17597513936', '河南', '上海市', '朝阳区', '31号巷21号', '2025-08-02 02:38:16');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (151, 'ORD20250908000151', 13, 3354.62, 3329.65, 24.97, 678.57, 2676.0499999999997, 2, 1, '2025-08-21 02:24:24', '李娜', '18399020743', '北京', '广州市', '江宁区', '111号巷58号', '2025-08-16 06:40:01');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (152, 'ORD20250908000152', 18, 4973.16, 4944.47, 28.69, 296.27, 4676.889999999999, 2, 2, '2025-08-23 12:02:45', '刘敏', '18457009001', '北京', '南京市', '武侯区', '582号巷10号', '2025-08-12 16:38:33');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (153, 'ORD20250908000153', 24, 3462.87, 3414.98, 47.89, 746.12, 2716.75, 3, 1, '2025-08-19 16:27:12', '吴磊', '16388846851', '湖北', '杭州市', '海淀区', '121号路30号', '2025-07-15 14:40:57');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (154, 'ORD20250908000154', 77, 2708.4100000000003, 2707.28, 1.13, 667.07, 2041.3400000000001, 2, 2, '2025-08-28 13:36:27', '杨芳', '19125256365', '上海', '重庆市', '洪山区', '960号路84号', '2025-07-13 12:34:53');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (155, 'ORD20250908000155', 5, 1709.8899999999999, 1696.85, 13.04, 170.3, 1539.59, 3, 1, '2025-08-19 12:40:36', '周丽', '19682099770', '上海', '成都市', '渝北区', '758号巷11号', '2025-09-02 03:38:30');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (156, 'ORD20250908000156', 62, 4747.07, 4733.54, 13.53, 59.27, 4687.799999999999, 0, NULL, NULL, '周敏', '17438900785', '河南', '北京市', '雁塔区', '494号街50号', '2025-08-16 05:43:56');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (157, 'ORD20250908000157', 90, 670.0, 634.93, 35.07, 104.68, 565.3199999999999, 1, 1, '2025-09-08 12:00:56', '杨丽', '14402527875', '北京', '深圳市', '朝阳区', '79号街17号', '2025-08-22 04:38:13');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (158, 'ORD20250908000158', 24, 1435.8600000000001, 1420.15, 15.71, 256.66, 1179.2, 4, 2, '2025-08-15 21:51:29', '吴强', '16102547392', '浙江', '南京市', '雁塔区', '300号巷32号', '2025-08-30 18:21:49');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (159, 'ORD20250908000159', 35, 1628.1799999999998, 1601.11, 27.07, 50.14, 1578.0399999999997, 2, 2, '2025-09-02 05:12:02', '陈芳', '14192723819', '四川', '武汉市', '海淀区', '22号路5号', '2025-08-05 20:34:08');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (160, 'ORD20250908000160', 36, 2960.34, 2945.32, 15.02, 852.76, 2107.58, 0, NULL, NULL, '吴强', '16830214035', '山东', '北京市', '渝北区', '773号路34号', '2025-09-05 23:45:40');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (161, 'ORD20250908000161', 28, 3805.7400000000002, 3788.69, 17.05, 942.52, 2863.2200000000003, 2, 2, '2025-08-12 09:16:32', '李洋', '15768945551', '河南', '武汉市', '天河区', '136号街36号', '2025-08-06 06:06:33');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (162, 'ORD20250908000162', 11, 5032.61, 4994.04, 38.57, 443.21, 4589.4, 3, 1, '2025-09-08 11:53:32', '陈丽', '14267036884', '北京', '武汉市', '朝阳区', '478号街46号', '2025-08-26 05:41:35');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (163, 'ORD20250908000163', 82, 4234.070000000001, 4223.18, 10.89, 74.22, 4159.85, 4, 2, '2025-09-08 05:48:43', '周丽', '19427242186', '江苏', '上海市', '朝阳区', '189号巷65号', '2025-08-22 08:43:18');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (164, 'ORD20250908000164', 75, 1621.17, 1593.5, 27.67, 108.97, 1512.2, 5, 2, '2025-08-22 02:40:22', '张丽', '16021510258', '河南', '上海市', '武侯区', '897号路19号', '2025-07-13 00:16:13');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (165, 'ORD20250908000165', 42, 3056.7700000000004, 3038.76, 18.01, 888.93, 2167.8400000000006, 4, 2, '2025-09-01 11:06:56', '陈勇', '13321512377', '湖南', '南京市', '天河区', '388号巷5号', '2025-07-25 04:25:37');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (166, 'ORD20250908000166', 49, 3847.89, 3815.43, 32.46, 1093.33, 2754.56, 5, 1, '2025-09-06 01:28:09', '周勇', '17751876653', '江苏', '重庆市', '余杭区', '933号街43号', '2025-08-03 05:07:46');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (167, 'ORD20250908000167', 99, 4899.14, 4887.6, 11.54, 461.61, 4437.530000000001, 5, 1, '2025-08-31 05:11:56', '赵丽', '13503427289', '湖北', '深圳市', '武侯区', '691号巷86号', '2025-07-24 00:37:03');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (168, 'ORD20250908000168', 78, 1323.1299999999999, 1308.79, 14.34, 123.23, 1199.8999999999999, 0, NULL, NULL, '赵洋', '19567880125', '四川', '上海市', '浦东新区', '891号街56号', '2025-07-12 22:29:31');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (169, 'ORD20250908000169', 89, 846.66, 843.01, 3.65, 130.22, 716.4399999999999, 3, 2, '2025-08-30 02:48:40', '杨娜', '15571885690', '山东', '武汉市', '武侯区', '646号路95号', '2025-08-21 21:57:33');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (170, 'ORD20250908000170', 38, 3825.73, 3790.72, 35.01, 834.08, 2991.65, 3, 1, '2025-08-17 00:07:09', '张勇', '17643172724', '北京', '重庆市', '雁塔区', '919号巷75号', '2025-08-28 22:59:04');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (171, 'ORD20250908000171', 55, 1452.63, 1433.43, 19.2, 325.66, 1126.97, 5, 1, '2025-08-19 20:11:18', '赵洋', '14833124219', '浙江', '重庆市', '海淀区', '947号街86号', '2025-08-06 15:58:47');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (172, 'ORD20250908000172', 19, 516.53, 488.92, 27.61, 74.61, 441.91999999999996, 1, 1, '2025-09-04 19:40:30', '周敏', '13802957582', '上海', '上海市', '洪山区', '911号路64号', '2025-08-05 03:41:38');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (173, 'ORD20250908000173', 12, 4373.5199999999995, 4323.87, 49.65, 949.05, 3424.4699999999993, 1, 2, '2025-08-13 10:50:51', '刘芳', '15526577394', '湖北', '南京市', '天河区', '862号巷85号', '2025-07-14 01:45:57');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (174, 'ORD20250908000174', 82, 3152.4500000000003, 3140.15, 12.3, 552.36, 2600.09, 4, 1, '2025-08-19 02:42:34', '陈磊', '14872189870', '上海', '武汉市', '雁塔区', '626号街91号', '2025-07-27 21:45:57');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (175, 'ORD20250908000175', 5, 2109.77, 2096.62, 13.15, 239.2, 1870.57, 3, 2, '2025-09-02 02:54:00', '周娜', '19886465522', '浙江', '成都市', '洪山区', '334号街85号', '2025-08-20 20:44:08');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (176, 'ORD20250908000176', 15, 937.0300000000001, 889.21, 47.82, 176.23, 760.8000000000001, 2, 2, '2025-08-15 19:55:33', '黄磊', '17602284536', '河南', '成都市', '洪山区', '436号路34号', '2025-07-15 22:01:57');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (177, 'ORD20250908000177', 35, 2443.62, 2432.67, 10.95, 464.35, 1979.27, 0, NULL, NULL, '黄强', '14857196159', '北京', '成都市', '海淀区', '800号巷43号', '2025-08-13 20:34:47');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (178, 'ORD20250908000178', 99, 3835.57, 3832.4, 3.17, 56.58, 3778.9900000000002, 1, 1, '2025-08-17 05:38:15', '周强', '19289327900', '湖北', '上海市', '雁塔区', '408号路6号', '2025-07-25 20:26:14');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (179, 'ORD20250908000179', 37, 1525.82, 1514.32, 11.5, 350.9, 1174.92, 1, 2, '2025-08-15 14:46:55', '赵强', '13720077408', '湖南', '广州市', '海淀区', '754号巷51号', '2025-07-29 11:26:32');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (180, 'ORD20250908000180', 78, 4337.16, 4297.92, 39.24, 548.18, 3788.98, 3, 2, '2025-08-22 22:01:07', '黄敏', '13953424227', '湖北', '深圳市', '武侯区', '18号路94号', '2025-08-17 19:59:15');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (181, 'ORD20250908000181', 48, 3851.6, 3815.87, 35.73, 517.74, 3333.8599999999997, 3, 1, '2025-08-28 21:40:20', '刘磊', '15563890184', '江苏', '上海市', '浦东新区', '413号巷61号', '2025-08-30 10:21:04');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (182, 'ORD20250908000182', 93, 4237.88, 4199.85, 38.03, 74.25, 4163.63, 3, 2, '2025-08-24 17:35:56', '杨娜', '13658225449', '江苏', '南京市', '洪山区', '726号路66号', '2025-09-02 11:31:20');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (183, 'ORD20250908000183', 95, 4967.92, 4963.85, 4.07, 156.43, 4811.49, 3, 2, '2025-08-15 19:01:21', '张娜', '16118184651', '湖北', '成都市', '武侯区', '10号路16号', '2025-08-21 21:28:05');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (184, 'ORD20250908000184', 38, 3646.53, 3602.52, 44.01, 293.0, 3353.53, 2, 2, '2025-09-05 23:36:52', '王娜', '15689364237', '广东', '广州市', '雁塔区', '733号路2号', '2025-08-10 10:59:40');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (185, 'ORD20250908000185', 54, 927.86, 904.34, 23.52, 269.49, 658.37, 0, NULL, NULL, '刘敏', '17530413526', '江苏', '杭州市', '余杭区', '841号路75号', '2025-08-09 07:10:39');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (186, 'ORD20250908000186', 48, 3146.42, 3126.26, 20.16, 238.92, 2907.5, 3, 1, '2025-08-13 08:59:38', '吴娜', '13215156358', '湖南', '杭州市', '渝北区', '685号路27号', '2025-07-26 22:25:39');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (187, 'ORD20250908000187', 1, 3968.4399999999996, 3918.99, 49.45, 107.39, 3861.0499999999997, 3, 1, '2025-08-16 13:43:23', '王磊', '19380546351', '湖北', '成都市', '武侯区', '793号路91号', '2025-08-25 16:20:29');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (188, 'ORD20250908000188', 46, 1591.79, 1565.69, 26.1, 249.27, 1342.52, 4, 1, '2025-08-17 16:16:02', '陈娜', '18547654303', '四川', '南京市', '武侯区', '219号巷72号', '2025-07-29 03:45:10');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (189, 'ORD20250908000189', 49, 1923.95, 1877.29, 46.66, 560.62, 1363.33, 1, 2, '2025-09-04 03:33:54', '刘伟', '17399502663', '浙江', '杭州市', '朝阳区', '995号巷4号', '2025-08-25 07:47:34');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (190, 'ORD20250908000190', 78, 3189.4, 3164.88, 24.52, 449.52, 2739.88, 4, 1, '2025-08-11 11:13:35', '杨伟', '17771808147', '山东', '上海市', '雁塔区', '285号街84号', '2025-09-08 05:27:37');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (191, 'ORD20250908000191', 95, 135.07, 93.21, 41.86, 23.72, 111.35, 2, 1, '2025-09-07 02:27:16', '杨洋', '13646017121', '北京', '杭州市', '渝北区', '204号巷65号', '2025-08-19 08:21:10');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (192, 'ORD20250908000192', 23, 3222.26, 3180.15, 42.11, 897.84, 2324.42, 2, 2, '2025-08-15 07:52:30', '杨磊', '16654107159', '广东', '北京市', '天河区', '654号路59号', '2025-07-18 12:06:43');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (193, 'ORD20250908000193', 93, 4732.14, 4716.75, 15.39, 963.48, 3768.6600000000003, 4, 1, '2025-08-16 01:22:30', '陈伟', '15225482192', '四川', '杭州市', '海淀区', '502号路75号', '2025-08-14 22:00:28');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (194, 'ORD20250908000194', 45, 3890.28, 3864.13, 26.15, 961.59, 2928.69, 3, 1, '2025-08-11 09:31:21', '吴强', '14594662395', '浙江', '上海市', '雁塔区', '792号街34号', '2025-07-31 19:17:54');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (195, 'ORD20250908000195', 60, 1357.7199999999998, 1311.62, 46.1, 203.44, 1154.2799999999997, 3, 2, '2025-08-16 09:37:27', '黄勇', '15569313914', '山东', '上海市', '浦东新区', '936号路40号', '2025-07-26 09:57:09');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (196, 'ORD20250908000196', 60, 2776.02, 2738.42, 37.6, 360.66, 2415.36, 3, 2, '2025-08-28 23:59:06', '黄敏', '14336398795', '河南', '成都市', '余杭区', '977号巷52号', '2025-09-06 04:41:42');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (197, 'ORD20250908000197', 4, 836.8100000000001, 828.49, 8.32, 74.71, 762.1, 3, 1, '2025-09-06 04:28:55', '王娜', '17700713346', '江苏', '广州市', '余杭区', '315号街24号', '2025-08-02 13:04:31');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (198, 'ORD20250908000198', 27, 2224.59, 2188.38, 36.21, 101.19, 2123.4, 3, 2, '2025-08-09 20:29:19', '黄娜', '15466968770', '广东', '南京市', '浦东新区', '410号路37号', '2025-08-20 20:05:59');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (199, 'ORD20250908000199', 77, 1978.8899999999999, 1975.31, 3.58, 289.37, 1689.52, 3, 1, '2025-08-20 09:01:12', '吴娜', '18338679611', '江苏', '杭州市', '武侯区', '96号路50号', '2025-07-28 01:52:06');
INSERT INTO oms_order (id, order_no, user_id, total_amount, product_amount, freight_amount, discount_amount, pay_amount, status, payment_type, payment_time, receiver_name, receiver_phone, receiver_province, receiver_city, receiver_district, receiver_address, create_time)
VALUES (200, 'ORD20250908000200', 55, 777.78, 762.23, 15.55, 154.41, 623.37, 3, 2, '2025-08-30 02:05:12', '王娜', '15518376481', '广东', '重庆市', '雁塔区', '394号巷59号', '2025-07-11 17:43:52');

-- Order Items
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (1, 1, 'ORD20250908000001', 28, 103, 'Product 28', 1545.01, 3, 4635.03);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (2, 1, 'ORD20250908000001', 17, 109, 'Product 17', 1486.71, 1, 1486.71);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (3, 1, 'ORD20250908000001', 33, 116, 'Product 33', 751.92, 4, 3007.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (4, 1, 'ORD20250908000001', 35, 86, 'Product 35', 1943.82, 1, 1943.82);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (5, 2, 'ORD20250908000002', 35, 109, 'Product 35', 1521.63, 1, 1521.63);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (6, 2, 'ORD20250908000002', 1, 39, 'Product 1', 680.86, 5, 3404.3);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (7, 3, 'ORD20250908000003', 33, 51, 'Product 33', 1627.54, 4, 6510.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (8, 3, 'ORD20250908000003', 4, 112, 'Product 4', 934.88, 1, 934.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (9, 3, 'ORD20250908000003', 32, 32, 'Product 32', 925.9, 1, 925.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (10, 4, 'ORD20250908000004', 17, 95, 'Product 17', 857.15, 3, 2571.45);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (11, 4, 'ORD20250908000004', 35, 7, 'Product 35', 541.8, 3, 1625.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (12, 4, 'ORD20250908000004', 27, 92, 'Product 27', 44.86, 3, 134.58);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (13, 4, 'ORD20250908000004', 41, 77, 'Product 41', 80.87, 1, 80.87);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (14, 4, 'ORD20250908000004', 47, 92, 'Product 47', 82.86, 4, 331.44);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (15, 5, 'ORD20250908000005', 6, 45, 'Product 6', 1074.62, 2, 2149.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (16, 5, 'ORD20250908000005', 41, 38, 'Product 41', 991.97, 3, 2975.91);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (17, 6, 'ORD20250908000006', 45, 118, 'Product 45', 1238.31, 5, 6191.55);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (18, 6, 'ORD20250908000006', 3, 118, 'Product 3', 162.31, 3, 486.93);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (19, 6, 'ORD20250908000006', 10, 10, 'Product 10', 1222.61, 1, 1222.61);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (20, 7, 'ORD20250908000007', 22, 21, 'Product 22', 1855.61, 3, 5566.83);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (21, 7, 'ORD20250908000007', 26, 109, 'Product 26', 54.54, 3, 163.62);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (22, 7, 'ORD20250908000007', 28, 21, 'Product 28', 1930.67, 2, 3861.34);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (23, 7, 'ORD20250908000007', 20, 67, 'Product 20', 1235.01, 5, 6175.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (24, 8, 'ORD20250908000008', 24, 13, 'Product 24', 1150.94, 2, 2301.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (25, 8, 'ORD20250908000008', 18, 112, 'Product 18', 1496.27, 2, 2992.54);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (26, 9, 'ORD20250908000009', 43, 129, 'Product 43', 101.03, 4, 404.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (27, 9, 'ORD20250908000009', 44, 47, 'Product 44', 576.98, 1, 576.98);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (28, 9, 'ORD20250908000009', 14, 105, 'Product 14', 47.25, 4, 189.0);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (29, 10, 'ORD20250908000010', 18, 71, 'Product 18', 930.26, 3, 2790.78);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (30, 10, 'ORD20250908000010', 15, 25, 'Product 15', 457.4, 3, 1372.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (31, 11, 'ORD20250908000011', 40, 17, 'Product 40', 582.78, 5, 2913.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (32, 11, 'ORD20250908000011', 38, 118, 'Product 38', 847.32, 5, 4236.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (33, 12, 'ORD20250908000012', 31, 100, 'Product 31', 1860.45, 1, 1860.45);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (34, 12, 'ORD20250908000012', 22, 88, 'Product 22', 646.09, 4, 2584.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (35, 12, 'ORD20250908000012', 30, 39, 'Product 30', 1710.17, 4, 6840.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (36, 13, 'ORD20250908000013', 44, 44, 'Product 44', 786.22, 4, 3144.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (37, 13, 'ORD20250908000013', 43, 9, 'Product 43', 1706.06, 1, 1706.06);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (38, 14, 'ORD20250908000014', 6, 89, 'Product 6', 423.03, 5, 2115.15);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (39, 14, 'ORD20250908000014', 20, 95, 'Product 20', 590.73, 3, 1772.19);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (40, 14, 'ORD20250908000014', 44, 116, 'Product 44', 940.09, 2, 1880.18);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (41, 14, 'ORD20250908000014', 40, 43, 'Product 40', 709.74, 4, 2838.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (42, 15, 'ORD20250908000015', 49, 6, 'Product 49', 1946.98, 5, 9734.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (43, 15, 'ORD20250908000015', 16, 126, 'Product 16', 756.82, 5, 3784.1);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (44, 15, 'ORD20250908000015', 25, 34, 'Product 25', 1218.17, 1, 1218.17);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (45, 15, 'ORD20250908000015', 26, 25, 'Product 26', 444.43, 1, 444.43);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (46, 16, 'ORD20250908000016', 12, 75, 'Product 12', 1731.14, 4, 6924.56);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (47, 16, 'ORD20250908000016', 23, 105, 'Product 23', 1505.05, 4, 6020.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (48, 16, 'ORD20250908000016', 21, 72, 'Product 21', 1543.29, 1, 1543.29);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (49, 16, 'ORD20250908000016', 33, 82, 'Product 33', 1075.9, 4, 4303.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (50, 16, 'ORD20250908000016', 26, 58, 'Product 26', 994.72, 4, 3978.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (51, 17, 'ORD20250908000017', 11, 116, 'Product 11', 1848.58, 2, 3697.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (52, 17, 'ORD20250908000017', 19, 103, 'Product 19', 16.64, 3, 49.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (53, 17, 'ORD20250908000017', 42, 119, 'Product 42', 352.82, 1, 352.82);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (54, 18, 'ORD20250908000018', 27, 108, 'Product 27', 826.48, 1, 826.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (55, 18, 'ORD20250908000018', 9, 58, 'Product 9', 1223.32, 5, 6116.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (56, 18, 'ORD20250908000018', 39, 21, 'Product 39', 1145.46, 4, 4581.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (57, 18, 'ORD20250908000018', 21, 6, 'Product 21', 1223.53, 2, 2447.06);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (58, 19, 'ORD20250908000019', 44, 121, 'Product 44', 191.02, 3, 573.06);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (59, 19, 'ORD20250908000019', 48, 6, 'Product 48', 1206.67, 4, 4826.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (60, 19, 'ORD20250908000019', 11, 50, 'Product 11', 920.96, 2, 1841.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (61, 19, 'ORD20250908000019', 31, 45, 'Product 31', 1874.91, 2, 3749.82);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (62, 19, 'ORD20250908000019', 9, 83, 'Product 9', 593.03, 1, 593.03);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (63, 20, 'ORD20250908000020', 30, 119, 'Product 30', 497.64, 5, 2488.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (64, 20, 'ORD20250908000020', 16, 114, 'Product 16', 1595.54, 4, 6382.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (65, 21, 'ORD20250908000021', 39, 29, 'Product 39', 1677.37, 2, 3354.74);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (66, 21, 'ORD20250908000021', 30, 63, 'Product 30', 1446.87, 1, 1446.87);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (67, 21, 'ORD20250908000021', 48, 2, 'Product 48', 74.32, 4, 297.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (68, 21, 'ORD20250908000021', 25, 121, 'Product 25', 150.03, 4, 600.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (69, 21, 'ORD20250908000021', 46, 67, 'Product 46', 1079.31, 5, 5396.55);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (70, 22, 'ORD20250908000022', 29, 7, 'Product 29', 966.81, 1, 966.81);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (71, 22, 'ORD20250908000022', 26, 75, 'Product 26', 1658.33, 1, 1658.33);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (72, 22, 'ORD20250908000022', 26, 94, 'Product 26', 1436.08, 5, 7180.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (73, 22, 'ORD20250908000022', 28, 113, 'Product 28', 1576.97, 1, 1576.97);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (74, 22, 'ORD20250908000022', 17, 38, 'Product 17', 1518.3, 5, 7591.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (75, 23, 'ORD20250908000023', 24, 44, 'Product 24', 831.66, 3, 2494.98);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (76, 24, 'ORD20250908000024', 31, 63, 'Product 31', 428.07, 1, 428.07);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (77, 24, 'ORD20250908000024', 42, 85, 'Product 42', 1390.49, 4, 5561.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (78, 25, 'ORD20250908000025', 49, 63, 'Product 49', 527.4, 3, 1582.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (79, 25, 'ORD20250908000025', 22, 114, 'Product 22', 1576.87, 3, 4730.61);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (80, 25, 'ORD20250908000025', 39, 71, 'Product 39', 81.13, 2, 162.26);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (81, 25, 'ORD20250908000025', 34, 38, 'Product 34', 583.98, 2, 1167.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (82, 26, 'ORD20250908000026', 46, 93, 'Product 46', 589.52, 2, 1179.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (83, 26, 'ORD20250908000026', 31, 79, 'Product 31', 1429.03, 3, 4287.09);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (84, 26, 'ORD20250908000026', 48, 40, 'Product 48', 1612.35, 2, 3224.7);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (85, 27, 'ORD20250908000027', 25, 75, 'Product 25', 522.04, 2, 1044.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (86, 27, 'ORD20250908000027', 14, 8, 'Product 14', 1170.63, 2, 2341.26);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (87, 28, 'ORD20250908000028', 17, 4, 'Product 17', 734.68, 5, 3673.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (88, 28, 'ORD20250908000028', 31, 104, 'Product 31', 1240.74, 2, 2481.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (89, 28, 'ORD20250908000028', 42, 89, 'Product 42', 320.19, 5, 1600.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (90, 28, 'ORD20250908000028', 34, 81, 'Product 34', 754.98, 4, 3019.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (91, 28, 'ORD20250908000028', 34, 44, 'Product 34', 969.28, 4, 3877.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (92, 29, 'ORD20250908000029', 43, 4, 'Product 43', 1894.43, 3, 5683.29);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (93, 29, 'ORD20250908000029', 20, 58, 'Product 20', 420.79, 2, 841.58);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (94, 29, 'ORD20250908000029', 39, 72, 'Product 39', 1558.25, 1, 1558.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (95, 29, 'ORD20250908000029', 23, 51, 'Product 23', 1002.59, 5, 5012.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (96, 30, 'ORD20250908000030', 48, 59, 'Product 48', 413.03, 1, 413.03);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (97, 31, 'ORD20250908000031', 22, 26, 'Product 22', 1019.76, 3, 3059.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (98, 31, 'ORD20250908000031', 4, 21, 'Product 4', 898.8, 5, 4494.0);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (99, 31, 'ORD20250908000031', 16, 64, 'Product 16', 519.58, 2, 1039.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (100, 31, 'ORD20250908000031', 15, 63, 'Product 15', 1382.38, 4, 5529.52);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (101, 32, 'ORD20250908000032', 39, 11, 'Product 39', 538.87, 3, 1616.61);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (102, 32, 'ORD20250908000032', 48, 24, 'Product 48', 388.13, 1, 388.13);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (103, 33, 'ORD20250908000033', 4, 78, 'Product 4', 423.39, 2, 846.78);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (104, 33, 'ORD20250908000033', 30, 123, 'Product 30', 205.56, 1, 205.56);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (105, 34, 'ORD20250908000034', 13, 19, 'Product 13', 961.82, 4, 3847.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (106, 34, 'ORD20250908000034', 31, 22, 'Product 31', 402.82, 5, 2014.1);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (107, 34, 'ORD20250908000034', 39, 56, 'Product 39', 979.59, 5, 4897.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (108, 35, 'ORD20250908000035', 8, 111, 'Product 8', 1442.76, 4, 5771.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (109, 36, 'ORD20250908000036', 1, 117, 'Product 1', 510.98, 4, 2043.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (110, 36, 'ORD20250908000036', 32, 70, 'Product 32', 1265.95, 3, 3797.85);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (111, 37, 'ORD20250908000037', 33, 124, 'Product 33', 574.67, 3, 1724.01);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (112, 37, 'ORD20250908000037', 35, 68, 'Product 35', 505.55, 3, 1516.65);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (113, 37, 'ORD20250908000037', 20, 129, 'Product 20', 1255.58, 1, 1255.58);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (114, 37, 'ORD20250908000037', 32, 47, 'Product 32', 22.97, 5, 114.85);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (115, 37, 'ORD20250908000037', 32, 121, 'Product 32', 334.63, 1, 334.63);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (116, 38, 'ORD20250908000038', 7, 78, 'Product 7', 1851.34, 4, 7405.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (117, 38, 'ORD20250908000038', 41, 88, 'Product 41', 1377.05, 2, 2754.1);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (118, 38, 'ORD20250908000038', 50, 50, 'Product 50', 226.34, 2, 452.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (119, 38, 'ORD20250908000038', 20, 55, 'Product 20', 1008.54, 3, 3025.62);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (120, 39, 'ORD20250908000039', 7, 121, 'Product 7', 1372.65, 5, 6863.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (121, 39, 'ORD20250908000039', 37, 54, 'Product 37', 1347.06, 3, 4041.18);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (122, 39, 'ORD20250908000039', 13, 41, 'Product 13', 1535.13, 2, 3070.26);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (123, 39, 'ORD20250908000039', 40, 41, 'Product 40', 1328.56, 3, 3985.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (124, 39, 'ORD20250908000039', 42, 122, 'Product 42', 1194.51, 4, 4778.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (125, 40, 'ORD20250908000040', 17, 59, 'Product 17', 949.24, 3, 2847.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (126, 40, 'ORD20250908000040', 10, 95, 'Product 10', 619.4, 3, 1858.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (127, 40, 'ORD20250908000040', 29, 81, 'Product 29', 622.95, 5, 3114.75);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (128, 41, 'ORD20250908000041', 1, 107, 'Product 1', 1526.82, 4, 6107.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (129, 41, 'ORD20250908000041', 27, 15, 'Product 27', 713.69, 2, 1427.38);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (130, 41, 'ORD20250908000041', 50, 117, 'Product 50', 555.67, 2, 1111.34);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (131, 41, 'ORD20250908000041', 43, 54, 'Product 43', 1436.18, 3, 4308.54);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (132, 42, 'ORD20250908000042', 21, 7, 'Product 21', 860.72, 4, 3442.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (133, 42, 'ORD20250908000042', 36, 84, 'Product 36', 1124.34, 2, 2248.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (134, 42, 'ORD20250908000042', 40, 56, 'Product 40', 1010.86, 3, 3032.58);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (135, 43, 'ORD20250908000043', 16, 20, 'Product 16', 329.23, 4, 1316.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (136, 44, 'ORD20250908000044', 3, 121, 'Product 3', 1564.64, 4, 6258.56);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (137, 44, 'ORD20250908000044', 16, 125, 'Product 16', 567.48, 5, 2837.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (138, 44, 'ORD20250908000044', 4, 97, 'Product 4', 211.87, 3, 635.61);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (139, 44, 'ORD20250908000044', 35, 71, 'Product 35', 745.2, 3, 2235.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (140, 44, 'ORD20250908000044', 31, 29, 'Product 31', 277.23, 1, 277.23);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (141, 45, 'ORD20250908000045', 10, 106, 'Product 10', 1749.16, 5, 8745.8);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (142, 45, 'ORD20250908000045', 37, 77, 'Product 37', 407.99, 1, 407.99);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (143, 45, 'ORD20250908000045', 16, 113, 'Product 16', 662.41, 5, 3312.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (144, 46, 'ORD20250908000046', 45, 75, 'Product 45', 53.49, 1, 53.49);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (145, 46, 'ORD20250908000046', 24, 81, 'Product 24', 1811.16, 4, 7244.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (146, 46, 'ORD20250908000046', 12, 55, 'Product 12', 649.32, 2, 1298.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (147, 47, 'ORD20250908000047', 22, 93, 'Product 22', 405.98, 3, 1217.94);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (148, 47, 'ORD20250908000047', 39, 66, 'Product 39', 1497.82, 1, 1497.82);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (149, 47, 'ORD20250908000047', 8, 36, 'Product 8', 1288.06, 4, 5152.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (150, 47, 'ORD20250908000047', 32, 102, 'Product 32', 1598.21, 5, 7991.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (151, 48, 'ORD20250908000048', 2, 96, 'Product 2', 1135.77, 1, 1135.77);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (152, 49, 'ORD20250908000049', 35, 84, 'Product 35', 989.1, 5, 4945.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (153, 49, 'ORD20250908000049', 41, 86, 'Product 41', 143.55, 1, 143.55);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (154, 49, 'ORD20250908000049', 36, 24, 'Product 36', 1563.37, 5, 7816.85);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (155, 49, 'ORD20250908000049', 33, 41, 'Product 33', 1694.14, 1, 1694.14);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (156, 49, 'ORD20250908000049', 40, 120, 'Product 40', 1303.85, 2, 2607.7);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (157, 50, 'ORD20250908000050', 18, 111, 'Product 18', 936.77, 2, 1873.54);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (158, 51, 'ORD20250908000051', 12, 19, 'Product 12', 1299.2, 3, 3897.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (159, 51, 'ORD20250908000051', 34, 117, 'Product 34', 1564.41, 4, 6257.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (160, 51, 'ORD20250908000051', 26, 6, 'Product 26', 1153.73, 1, 1153.73);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (161, 52, 'ORD20250908000052', 2, 100, 'Product 2', 26.99, 3, 80.97);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (162, 52, 'ORD20250908000052', 10, 110, 'Product 10', 525.12, 2, 1050.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (163, 52, 'ORD20250908000052', 35, 84, 'Product 35', 920.93, 2, 1841.86);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (164, 52, 'ORD20250908000052', 46, 91, 'Product 46', 1735.81, 2, 3471.62);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (165, 52, 'ORD20250908000052', 50, 49, 'Product 50', 305.08, 3, 915.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (166, 53, 'ORD20250908000053', 2, 26, 'Product 2', 1071.51, 1, 1071.51);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (167, 53, 'ORD20250908000053', 35, 33, 'Product 35', 703.55, 1, 703.55);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (168, 54, 'ORD20250908000054', 15, 81, 'Product 15', 1319.75, 2, 2639.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (169, 54, 'ORD20250908000054', 11, 78, 'Product 11', 854.32, 3, 2562.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (170, 54, 'ORD20250908000054', 14, 60, 'Product 14', 1546.97, 1, 1546.97);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (171, 55, 'ORD20250908000055', 8, 113, 'Product 8', 1669.97, 4, 6679.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (172, 55, 'ORD20250908000055', 29, 7, 'Product 29', 1736.39, 2, 3472.78);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (173, 56, 'ORD20250908000056', 29, 13, 'Product 29', 882.71, 2, 1765.42);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (174, 56, 'ORD20250908000056', 50, 53, 'Product 50', 767.2, 1, 767.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (175, 56, 'ORD20250908000056', 12, 24, 'Product 12', 1018.34, 5, 5091.7);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (176, 56, 'ORD20250908000056', 11, 32, 'Product 11', 1515.65, 1, 1515.65);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (177, 57, 'ORD20250908000057', 48, 92, 'Product 48', 1990.76, 4, 7963.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (178, 57, 'ORD20250908000057', 14, 49, 'Product 14', 1970.45, 5, 9852.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (179, 57, 'ORD20250908000057', 31, 93, 'Product 31', 518.02, 1, 518.02);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (180, 57, 'ORD20250908000057', 2, 117, 'Product 2', 24.44, 4, 97.76);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (181, 58, 'ORD20250908000058', 23, 26, 'Product 23', 727.18, 3, 2181.54);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (182, 58, 'ORD20250908000058', 28, 17, 'Product 28', 1892.42, 3, 5677.26);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (183, 58, 'ORD20250908000058', 45, 59, 'Product 45', 178.06, 4, 712.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (184, 58, 'ORD20250908000058', 2, 121, 'Product 2', 1903.11, 3, 5709.33);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (185, 59, 'ORD20250908000059', 10, 12, 'Product 10', 1696.36, 2, 3392.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (186, 59, 'ORD20250908000059', 9, 3, 'Product 9', 1042.06, 2, 2084.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (187, 59, 'ORD20250908000059', 49, 121, 'Product 49', 87.59, 5, 437.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (188, 60, 'ORD20250908000060', 24, 76, 'Product 24', 1238.93, 2, 2477.86);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (189, 60, 'ORD20250908000060', 12, 108, 'Product 12', 357.53, 5, 1787.65);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (190, 61, 'ORD20250908000061', 24, 47, 'Product 24', 1759.36, 1, 1759.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (191, 61, 'ORD20250908000061', 2, 42, 'Product 2', 1000.14, 3, 3000.42);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (192, 62, 'ORD20250908000062', 17, 96, 'Product 17', 1247.61, 1, 1247.61);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (193, 62, 'ORD20250908000062', 22, 7, 'Product 22', 66.71, 4, 266.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (194, 62, 'ORD20250908000062', 48, 9, 'Product 48', 1745.34, 4, 6981.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (195, 62, 'ORD20250908000062', 35, 12, 'Product 35', 760.3, 5, 3801.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (196, 63, 'ORD20250908000063', 11, 44, 'Product 11', 209.23, 3, 627.69);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (197, 63, 'ORD20250908000063', 6, 58, 'Product 6', 845.57, 1, 845.57);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (198, 63, 'ORD20250908000063', 31, 29, 'Product 31', 934.22, 4, 3736.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (199, 63, 'ORD20250908000063', 9, 113, 'Product 9', 1715.26, 1, 1715.26);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (200, 63, 'ORD20250908000063', 31, 80, 'Product 31', 944.28, 3, 2832.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (201, 64, 'ORD20250908000064', 43, 83, 'Product 43', 1084.57, 5, 5422.85);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (202, 64, 'ORD20250908000064', 31, 25, 'Product 31', 990.68, 5, 4953.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (203, 65, 'ORD20250908000065', 15, 33, 'Product 15', 1034.32, 2, 2068.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (204, 65, 'ORD20250908000065', 43, 58, 'Product 43', 173.56, 1, 173.56);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (205, 66, 'ORD20250908000066', 40, 73, 'Product 40', 219.4, 2, 438.8);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (206, 66, 'ORD20250908000066', 12, 65, 'Product 12', 1462.76, 3, 4388.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (207, 66, 'ORD20250908000066', 46, 85, 'Product 46', 1948.17, 5, 9740.85);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (208, 66, 'ORD20250908000066', 49, 69, 'Product 49', 1800.07, 3, 5400.21);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (209, 67, 'ORD20250908000067', 43, 96, 'Product 43', 298.99, 1, 298.99);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (210, 67, 'ORD20250908000067', 41, 99, 'Product 41', 105.56, 2, 211.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (211, 67, 'ORD20250908000067', 1, 47, 'Product 1', 294.48, 1, 294.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (212, 67, 'ORD20250908000067', 11, 61, 'Product 11', 799.56, 1, 799.56);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (213, 68, 'ORD20250908000068', 31, 124, 'Product 31', 1716.78, 2, 3433.56);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (214, 68, 'ORD20250908000068', 29, 104, 'Product 29', 1985.47, 4, 7941.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (215, 68, 'ORD20250908000068', 35, 68, 'Product 35', 885.33, 3, 2655.99);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (216, 68, 'ORD20250908000068', 22, 87, 'Product 22', 1993.4, 2, 3986.8);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (217, 68, 'ORD20250908000068', 22, 66, 'Product 22', 1615.91, 4, 6463.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (218, 69, 'ORD20250908000069', 43, 117, 'Product 43', 202.04, 3, 606.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (219, 69, 'ORD20250908000069', 5, 68, 'Product 5', 1566.09, 4, 6264.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (220, 69, 'ORD20250908000069', 35, 7, 'Product 35', 261.19, 2, 522.38);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (221, 69, 'ORD20250908000069', 8, 118, 'Product 8', 249.35, 3, 748.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (222, 69, 'ORD20250908000069', 23, 33, 'Product 23', 620.99, 4, 2483.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (223, 70, 'ORD20250908000070', 33, 22, 'Product 33', 373.4, 5, 1867.0);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (224, 70, 'ORD20250908000070', 10, 50, 'Product 10', 1681.63, 2, 3363.26);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (225, 70, 'ORD20250908000070', 13, 5, 'Product 13', 1469.49, 3, 4408.47);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (226, 70, 'ORD20250908000070', 37, 39, 'Product 37', 1721.25, 5, 8606.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (227, 70, 'ORD20250908000070', 35, 13, 'Product 35', 731.69, 2, 1463.38);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (228, 71, 'ORD20250908000071', 24, 43, 'Product 24', 325.91, 2, 651.82);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (229, 71, 'ORD20250908000071', 49, 58, 'Product 49', 1435.01, 5, 7175.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (230, 71, 'ORD20250908000071', 13, 109, 'Product 13', 564.06, 4, 2256.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (231, 71, 'ORD20250908000071', 25, 76, 'Product 25', 1802.9, 4, 7211.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (232, 71, 'ORD20250908000071', 30, 122, 'Product 30', 650.62, 3, 1951.86);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (233, 72, 'ORD20250908000072', 17, 20, 'Product 17', 1419.6, 3, 4258.8);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (234, 73, 'ORD20250908000073', 4, 18, 'Product 4', 1512.79, 2, 3025.58);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (235, 73, 'ORD20250908000073', 31, 37, 'Product 31', 1646.66, 2, 3293.32);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (236, 74, 'ORD20250908000074', 42, 41, 'Product 42', 843.83, 5, 4219.15);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (237, 74, 'ORD20250908000074', 16, 50, 'Product 16', 1670.31, 1, 1670.31);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (238, 74, 'ORD20250908000074', 18, 58, 'Product 18', 289.48, 4, 1157.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (239, 74, 'ORD20250908000074', 47, 32, 'Product 47', 1481.91, 2, 2963.82);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (240, 75, 'ORD20250908000075', 10, 114, 'Product 10', 1347.75, 3, 4043.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (241, 75, 'ORD20250908000075', 41, 3, 'Product 41', 243.41, 3, 730.23);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (242, 76, 'ORD20250908000076', 50, 27, 'Product 50', 1260.81, 4, 5043.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (243, 76, 'ORD20250908000076', 43, 60, 'Product 43', 882.16, 1, 882.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (244, 76, 'ORD20250908000076', 10, 117, 'Product 10', 150.1, 3, 450.3);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (245, 76, 'ORD20250908000076', 3, 70, 'Product 3', 769.19, 3, 2307.57);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (246, 76, 'ORD20250908000076', 41, 29, 'Product 41', 938.39, 5, 4691.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (247, 77, 'ORD20250908000077', 49, 125, 'Product 49', 589.3, 5, 2946.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (248, 77, 'ORD20250908000077', 6, 27, 'Product 6', 1995.23, 5, 9976.15);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (249, 78, 'ORD20250908000078', 16, 93, 'Product 16', 1740.03, 4, 6960.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (250, 78, 'ORD20250908000078', 27, 111, 'Product 27', 122.74, 1, 122.74);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (251, 78, 'ORD20250908000078', 47, 72, 'Product 47', 1467.56, 3, 4402.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (252, 78, 'ORD20250908000078', 27, 85, 'Product 27', 1337.29, 3, 4011.87);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (253, 78, 'ORD20250908000078', 6, 92, 'Product 6', 35.72, 4, 142.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (254, 79, 'ORD20250908000079', 15, 127, 'Product 15', 1186.26, 3, 3558.78);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (255, 79, 'ORD20250908000079', 50, 27, 'Product 50', 1654.26, 4, 6617.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (256, 80, 'ORD20250908000080', 39, 97, 'Product 39', 1042.74, 4, 4170.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (257, 80, 'ORD20250908000080', 39, 11, 'Product 39', 224.52, 1, 224.52);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (258, 80, 'ORD20250908000080', 8, 25, 'Product 8', 1933.79, 2, 3867.58);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (259, 80, 'ORD20250908000080', 38, 93, 'Product 38', 1348.49, 5, 6742.45);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (260, 80, 'ORD20250908000080', 26, 108, 'Product 26', 1439.4, 1, 1439.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (261, 81, 'ORD20250908000081', 30, 113, 'Product 30', 1150.96, 5, 5754.8);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (262, 81, 'ORD20250908000081', 26, 10, 'Product 26', 1628.19, 4, 6512.76);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (263, 81, 'ORD20250908000081', 14, 7, 'Product 14', 1138.19, 1, 1138.19);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (264, 82, 'ORD20250908000082', 19, 26, 'Product 19', 1842.13, 1, 1842.13);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (265, 82, 'ORD20250908000082', 17, 117, 'Product 17', 723.59, 3, 2170.77);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (266, 82, 'ORD20250908000082', 16, 91, 'Product 16', 560.5, 1, 560.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (267, 83, 'ORD20250908000083', 45, 118, 'Product 45', 1982.04, 3, 5946.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (268, 83, 'ORD20250908000083', 14, 42, 'Product 14', 1121.37, 5, 5606.85);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (269, 83, 'ORD20250908000083', 48, 83, 'Product 48', 808.63, 3, 2425.89);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (270, 84, 'ORD20250908000084', 6, 128, 'Product 6', 674.19, 5, 3370.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (271, 84, 'ORD20250908000084', 21, 59, 'Product 21', 1659.08, 1, 1659.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (272, 85, 'ORD20250908000085', 21, 104, 'Product 21', 1247.38, 2, 2494.76);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (273, 86, 'ORD20250908000086', 17, 52, 'Product 17', 1713.39, 4, 6853.56);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (274, 86, 'ORD20250908000086', 22, 90, 'Product 22', 1613.74, 3, 4841.22);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (275, 86, 'ORD20250908000086', 11, 41, 'Product 11', 652.29, 1, 652.29);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (276, 86, 'ORD20250908000086', 38, 49, 'Product 38', 627.95, 5, 3139.75);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (277, 87, 'ORD20250908000087', 49, 76, 'Product 49', 1124.24, 2, 2248.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (278, 87, 'ORD20250908000087', 40, 102, 'Product 40', 1394.32, 2, 2788.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (279, 87, 'ORD20250908000087', 6, 24, 'Product 6', 1426.21, 4, 5704.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (280, 87, 'ORD20250908000087', 10, 68, 'Product 10', 1434.62, 5, 7173.1);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (281, 87, 'ORD20250908000087', 26, 91, 'Product 26', 274.73, 4, 1098.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (282, 88, 'ORD20250908000088', 40, 76, 'Product 40', 1144.51, 1, 1144.51);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (283, 89, 'ORD20250908000089', 29, 58, 'Product 29', 1654.01, 2, 3308.02);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (284, 89, 'ORD20250908000089', 43, 40, 'Product 43', 1985.43, 1, 1985.43);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (285, 89, 'ORD20250908000089', 15, 127, 'Product 15', 1668.16, 2, 3336.32);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (286, 90, 'ORD20250908000090', 29, 58, 'Product 29', 1299.31, 2, 2598.62);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (287, 90, 'ORD20250908000090', 1, 38, 'Product 1', 600.62, 1, 600.62);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (288, 91, 'ORD20250908000091', 29, 124, 'Product 29', 834.91, 5, 4174.55);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (289, 91, 'ORD20250908000091', 27, 50, 'Product 27', 380.26, 5, 1901.3);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (290, 91, 'ORD20250908000091', 41, 103, 'Product 41', 1155.16, 4, 4620.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (291, 91, 'ORD20250908000091', 46, 67, 'Product 46', 1595.63, 3, 4786.89);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (292, 91, 'ORD20250908000091', 28, 68, 'Product 28', 746.75, 2, 1493.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (293, 92, 'ORD20250908000092', 5, 92, 'Product 5', 1916.6, 2, 3833.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (294, 92, 'ORD20250908000092', 45, 83, 'Product 45', 501.32, 5, 2506.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (295, 92, 'ORD20250908000092', 7, 33, 'Product 7', 1962.21, 5, 9811.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (296, 93, 'ORD20250908000093', 7, 81, 'Product 7', 1616.43, 5, 8082.15);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (297, 93, 'ORD20250908000093', 47, 59, 'Product 47', 703.2, 5, 3516.0);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (298, 93, 'ORD20250908000093', 30, 58, 'Product 30', 545.58, 1, 545.58);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (299, 93, 'ORD20250908000093', 40, 118, 'Product 40', 881.36, 5, 4406.8);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (300, 94, 'ORD20250908000094', 11, 17, 'Product 11', 54.01, 4, 216.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (301, 94, 'ORD20250908000094', 23, 15, 'Product 23', 958.49, 2, 1916.98);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (302, 94, 'ORD20250908000094', 44, 17, 'Product 44', 715.28, 3, 2145.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (303, 94, 'ORD20250908000094', 47, 65, 'Product 47', 64.88, 5, 324.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (304, 95, 'ORD20250908000095', 46, 111, 'Product 46', 1531.09, 2, 3062.18);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (305, 95, 'ORD20250908000095', 14, 102, 'Product 14', 1326.98, 3, 3980.94);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (306, 95, 'ORD20250908000095', 22, 78, 'Product 22', 472.8, 5, 2364.0);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (307, 95, 'ORD20250908000095', 10, 105, 'Product 10', 1534.94, 5, 7674.7);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (308, 96, 'ORD20250908000096', 40, 2, 'Product 40', 115.41, 3, 346.23);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (309, 96, 'ORD20250908000096', 50, 31, 'Product 50', 1954.93, 5, 9774.65);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (310, 96, 'ORD20250908000096', 34, 17, 'Product 34', 833.29, 2, 1666.58);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (311, 97, 'ORD20250908000097', 39, 64, 'Product 39', 1549.73, 2, 3099.46);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (312, 97, 'ORD20250908000097', 2, 47, 'Product 2', 1302.32, 4, 5209.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (313, 98, 'ORD20250908000098', 6, 85, 'Product 6', 197.38, 4, 789.52);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (314, 99, 'ORD20250908000099', 27, 100, 'Product 27', 768.77, 4, 3075.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (315, 99, 'ORD20250908000099', 22, 36, 'Product 22', 374.15, 3, 1122.45);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (316, 99, 'ORD20250908000099', 12, 91, 'Product 12', 1417.45, 3, 4252.35);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (317, 99, 'ORD20250908000099', 6, 128, 'Product 6', 377.29, 4, 1509.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (318, 99, 'ORD20250908000099', 15, 52, 'Product 15', 708.89, 1, 708.89);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (319, 100, 'ORD20250908000100', 49, 116, 'Product 49', 197.12, 3, 591.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (320, 100, 'ORD20250908000100', 39, 6, 'Product 39', 131.19, 5, 655.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (321, 100, 'ORD20250908000100', 44, 16, 'Product 44', 752.56, 2, 1505.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (322, 101, 'ORD20250908000101', 1, 4, 'Product 1', 660.45, 3, 1981.35);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (323, 102, 'ORD20250908000102', 5, 96, 'Product 5', 1911.87, 2, 3823.74);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (324, 103, 'ORD20250908000103', 12, 128, 'Product 12', 1075.08, 3, 3225.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (325, 103, 'ORD20250908000103', 44, 25, 'Product 44', 1826.1, 5, 9130.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (326, 103, 'ORD20250908000103', 37, 96, 'Product 37', 362.09, 4, 1448.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (327, 103, 'ORD20250908000103', 33, 46, 'Product 33', 306.21, 4, 1224.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (328, 103, 'ORD20250908000103', 40, 8, 'Product 40', 87.16, 5, 435.8);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (329, 104, 'ORD20250908000104', 19, 80, 'Product 19', 224.47, 2, 448.94);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (330, 105, 'ORD20250908000105', 7, 43, 'Product 7', 1000.33, 5, 5001.65);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (331, 105, 'ORD20250908000105', 40, 40, 'Product 40', 1840.8, 4, 7363.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (332, 105, 'ORD20250908000105', 29, 100, 'Product 29', 1888.58, 5, 9442.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (333, 105, 'ORD20250908000105', 22, 17, 'Product 22', 499.98, 2, 999.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (334, 105, 'ORD20250908000105', 32, 62, 'Product 32', 384.8, 4, 1539.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (335, 106, 'ORD20250908000106', 4, 92, 'Product 4', 1834.05, 4, 7336.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (336, 107, 'ORD20250908000107', 34, 26, 'Product 34', 191.0, 3, 573.0);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (337, 107, 'ORD20250908000107', 49, 101, 'Product 49', 1097.3, 2, 2194.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (338, 107, 'ORD20250908000107', 21, 73, 'Product 21', 279.6, 2, 559.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (339, 107, 'ORD20250908000107', 41, 75, 'Product 41', 858.38, 4, 3433.52);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (340, 107, 'ORD20250908000107', 6, 97, 'Product 6', 986.03, 4, 3944.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (341, 108, 'ORD20250908000108', 36, 83, 'Product 36', 973.37, 4, 3893.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (342, 109, 'ORD20250908000109', 50, 128, 'Product 50', 581.52, 1, 581.52);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (343, 109, 'ORD20250908000109', 13, 11, 'Product 13', 1894.09, 3, 5682.27);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (344, 109, 'ORD20250908000109', 42, 45, 'Product 42', 634.17, 1, 634.17);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (345, 110, 'ORD20250908000110', 4, 8, 'Product 4', 1933.87, 4, 7735.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (346, 110, 'ORD20250908000110', 23, 49, 'Product 23', 1928.73, 1, 1928.73);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (347, 110, 'ORD20250908000110', 48, 112, 'Product 48', 1021.67, 2, 2043.34);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (348, 110, 'ORD20250908000110', 44, 95, 'Product 44', 1401.37, 2, 2802.74);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (349, 110, 'ORD20250908000110', 30, 26, 'Product 30', 1900.43, 2, 3800.86);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (350, 111, 'ORD20250908000111', 46, 19, 'Product 46', 415.18, 2, 830.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (351, 111, 'ORD20250908000111', 3, 4, 'Product 3', 1844.95, 2, 3689.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (352, 111, 'ORD20250908000111', 19, 30, 'Product 19', 19.43, 2, 38.86);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (353, 112, 'ORD20250908000112', 25, 46, 'Product 25', 1654.88, 3, 4964.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (354, 112, 'ORD20250908000112', 3, 103, 'Product 3', 320.3, 5, 1601.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (355, 112, 'ORD20250908000112', 40, 64, 'Product 40', 1340.38, 4, 5361.52);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (356, 113, 'ORD20250908000113', 14, 80, 'Product 14', 1881.14, 3, 5643.42);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (357, 113, 'ORD20250908000113', 45, 105, 'Product 45', 30.35, 4, 121.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (358, 113, 'ORD20250908000113', 18, 97, 'Product 18', 1686.84, 2, 3373.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (359, 113, 'ORD20250908000113', 17, 125, 'Product 17', 1204.26, 4, 4817.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (360, 113, 'ORD20250908000113', 45, 11, 'Product 45', 1536.58, 2, 3073.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (361, 114, 'ORD20250908000114', 41, 19, 'Product 41', 237.62, 2, 475.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (362, 114, 'ORD20250908000114', 5, 126, 'Product 5', 374.88, 3, 1124.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (363, 115, 'ORD20250908000115', 44, 66, 'Product 44', 1432.9, 4, 5731.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (364, 115, 'ORD20250908000115', 35, 73, 'Product 35', 580.01, 5, 2900.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (365, 116, 'ORD20250908000116', 49, 28, 'Product 49', 247.61, 2, 495.22);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (366, 116, 'ORD20250908000116', 36, 23, 'Product 36', 1694.44, 2, 3388.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (367, 116, 'ORD20250908000116', 34, 33, 'Product 34', 1991.16, 4, 7964.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (368, 117, 'ORD20250908000117', 8, 28, 'Product 8', 465.0, 4, 1860.0);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (369, 117, 'ORD20250908000117', 6, 48, 'Product 6', 1527.46, 1, 1527.46);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (370, 117, 'ORD20250908000117', 28, 62, 'Product 28', 1234.49, 4, 4937.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (371, 117, 'ORD20250908000117', 29, 37, 'Product 29', 1198.03, 1, 1198.03);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (372, 118, 'ORD20250908000118', 50, 49, 'Product 50', 428.76, 3, 1286.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (373, 118, 'ORD20250908000118', 37, 12, 'Product 37', 38.28, 2, 76.56);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (374, 118, 'ORD20250908000118', 17, 88, 'Product 17', 1844.46, 3, 5533.38);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (375, 118, 'ORD20250908000118', 13, 119, 'Product 13', 1804.11, 1, 1804.11);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (376, 118, 'ORD20250908000118', 25, 101, 'Product 25', 314.66, 3, 943.98);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (377, 119, 'ORD20250908000119', 15, 17, 'Product 15', 376.09, 2, 752.18);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (378, 119, 'ORD20250908000119', 43, 86, 'Product 43', 1445.15, 1, 1445.15);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (379, 119, 'ORD20250908000119', 5, 113, 'Product 5', 1688.41, 5, 8442.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (380, 119, 'ORD20250908000119', 36, 74, 'Product 36', 330.59, 5, 1652.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (381, 119, 'ORD20250908000119', 4, 82, 'Product 4', 231.62, 1, 231.62);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (382, 120, 'ORD20250908000120', 40, 5, 'Product 40', 1443.76, 3, 4331.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (383, 120, 'ORD20250908000120', 3, 95, 'Product 3', 295.54, 1, 295.54);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (384, 120, 'ORD20250908000120', 1, 60, 'Product 1', 768.61, 5, 3843.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (385, 120, 'ORD20250908000120', 25, 15, 'Product 25', 1539.7, 1, 1539.7);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (386, 121, 'ORD20250908000121', 37, 10, 'Product 37', 1095.71, 1, 1095.71);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (387, 121, 'ORD20250908000121', 37, 122, 'Product 37', 1293.13, 3, 3879.39);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (388, 122, 'ORD20250908000122', 29, 100, 'Product 29', 1821.38, 2, 3642.76);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (389, 122, 'ORD20250908000122', 40, 126, 'Product 40', 507.58, 4, 2030.32);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (390, 122, 'ORD20250908000122', 10, 123, 'Product 10', 913.9, 3, 2741.7);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (391, 122, 'ORD20250908000122', 10, 58, 'Product 10', 580.86, 2, 1161.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (392, 122, 'ORD20250908000122', 17, 100, 'Product 17', 1765.06, 3, 5295.18);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (393, 123, 'ORD20250908000123', 20, 111, 'Product 20', 296.53, 4, 1186.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (394, 124, 'ORD20250908000124', 26, 59, 'Product 26', 258.8, 3, 776.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (395, 124, 'ORD20250908000124', 26, 54, 'Product 26', 848.07, 4, 3392.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (396, 124, 'ORD20250908000124', 47, 118, 'Product 47', 910.1, 4, 3640.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (397, 124, 'ORD20250908000124', 24, 119, 'Product 24', 682.22, 4, 2728.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (398, 125, 'ORD20250908000125', 16, 53, 'Product 16', 441.75, 2, 883.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (399, 125, 'ORD20250908000125', 47, 50, 'Product 47', 1322.06, 5, 6610.3);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (400, 126, 'ORD20250908000126', 36, 119, 'Product 36', 1726.25, 4, 6905.0);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (401, 126, 'ORD20250908000126', 42, 96, 'Product 42', 620.73, 4, 2482.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (402, 126, 'ORD20250908000126', 31, 37, 'Product 31', 226.21, 4, 904.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (403, 127, 'ORD20250908000127', 4, 106, 'Product 4', 406.83, 3, 1220.49);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (404, 127, 'ORD20250908000127', 43, 41, 'Product 43', 774.74, 5, 3873.7);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (405, 127, 'ORD20250908000127', 12, 111, 'Product 12', 1886.97, 1, 1886.97);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (406, 128, 'ORD20250908000128', 37, 99, 'Product 37', 1337.32, 3, 4011.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (407, 128, 'ORD20250908000128', 42, 40, 'Product 42', 809.73, 2, 1619.46);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (408, 129, 'ORD20250908000129', 21, 106, 'Product 21', 942.47, 2, 1884.94);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (409, 129, 'ORD20250908000129', 21, 125, 'Product 21', 1224.61, 5, 6123.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (410, 130, 'ORD20250908000130', 44, 10, 'Product 44', 203.16, 1, 203.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (411, 130, 'ORD20250908000130', 2, 22, 'Product 2', 27.25, 1, 27.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (412, 130, 'ORD20250908000130', 6, 85, 'Product 6', 633.45, 3, 1900.35);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (413, 130, 'ORD20250908000130', 48, 52, 'Product 48', 309.7, 2, 619.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (414, 130, 'ORD20250908000130', 5, 79, 'Product 5', 732.25, 3, 2196.75);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (415, 131, 'ORD20250908000131', 31, 115, 'Product 31', 683.86, 2, 1367.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (416, 131, 'ORD20250908000131', 20, 75, 'Product 20', 211.98, 5, 1059.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (417, 131, 'ORD20250908000131', 10, 41, 'Product 10', 334.88, 1, 334.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (418, 131, 'ORD20250908000131', 11, 105, 'Product 11', 1127.71, 4, 4510.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (419, 131, 'ORD20250908000131', 45, 72, 'Product 45', 1697.51, 2, 3395.02);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (420, 132, 'ORD20250908000132', 29, 38, 'Product 29', 203.17, 1, 203.17);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (421, 133, 'ORD20250908000133', 31, 98, 'Product 31', 764.95, 2, 1529.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (422, 133, 'ORD20250908000133', 38, 6, 'Product 38', 1379.36, 3, 4138.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (423, 134, 'ORD20250908000134', 29, 47, 'Product 29', 1789.04, 4, 7156.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (424, 134, 'ORD20250908000134', 22, 89, 'Product 22', 1975.75, 3, 5927.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (425, 134, 'ORD20250908000134', 12, 18, 'Product 12', 278.54, 3, 835.62);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (426, 134, 'ORD20250908000134', 10, 101, 'Product 10', 230.83, 2, 461.66);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (427, 135, 'ORD20250908000135', 46, 75, 'Product 46', 1587.64, 1, 1587.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (428, 135, 'ORD20250908000135', 39, 91, 'Product 39', 1967.31, 4, 7869.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (429, 135, 'ORD20250908000135', 40, 5, 'Product 40', 1783.18, 4, 7132.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (430, 136, 'ORD20250908000136', 17, 94, 'Product 17', 392.13, 3, 1176.39);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (431, 136, 'ORD20250908000136', 32, 7, 'Product 32', 643.95, 2, 1287.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (432, 137, 'ORD20250908000137', 45, 62, 'Product 45', 620.21, 4, 2480.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (433, 138, 'ORD20250908000138', 3, 87, 'Product 3', 552.94, 4, 2211.76);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (434, 138, 'ORD20250908000138', 22, 21, 'Product 22', 1107.04, 2, 2214.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (435, 138, 'ORD20250908000138', 48, 3, 'Product 48', 1151.57, 3, 3454.71);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (436, 138, 'ORD20250908000138', 13, 98, 'Product 13', 1810.02, 4, 7240.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (437, 139, 'ORD20250908000139', 45, 10, 'Product 45', 1347.09, 4, 5388.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (438, 139, 'ORD20250908000139', 24, 86, 'Product 24', 1608.26, 4, 6433.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (439, 139, 'ORD20250908000139', 46, 105, 'Product 46', 690.84, 3, 2072.52);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (440, 139, 'ORD20250908000139', 36, 67, 'Product 36', 393.88, 3, 1181.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (441, 140, 'ORD20250908000140', 29, 22, 'Product 29', 1838.36, 1, 1838.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (442, 140, 'ORD20250908000140', 33, 82, 'Product 33', 1792.68, 1, 1792.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (443, 140, 'ORD20250908000140', 49, 33, 'Product 49', 223.03, 1, 223.03);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (444, 141, 'ORD20250908000141', 5, 96, 'Product 5', 316.47, 2, 632.94);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (445, 141, 'ORD20250908000141', 5, 23, 'Product 5', 29.19, 1, 29.19);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (446, 142, 'ORD20250908000142', 10, 19, 'Product 10', 1169.68, 4, 4678.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (447, 142, 'ORD20250908000142', 43, 85, 'Product 43', 220.61, 1, 220.61);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (448, 142, 'ORD20250908000142', 46, 15, 'Product 46', 286.04, 1, 286.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (449, 143, 'ORD20250908000143', 46, 24, 'Product 46', 1903.27, 1, 1903.27);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (450, 143, 'ORD20250908000143', 44, 111, 'Product 44', 473.82, 1, 473.82);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (451, 144, 'ORD20250908000144', 21, 100, 'Product 21', 1254.65, 2, 2509.3);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (452, 145, 'ORD20250908000145', 46, 51, 'Product 46', 1975.98, 5, 9879.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (453, 145, 'ORD20250908000145', 31, 48, 'Product 31', 583.25, 5, 2916.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (454, 145, 'ORD20250908000145', 29, 16, 'Product 29', 378.01, 1, 378.01);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (455, 145, 'ORD20250908000145', 12, 21, 'Product 12', 1948.64, 3, 5845.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (456, 146, 'ORD20250908000146', 45, 49, 'Product 45', 256.78, 1, 256.78);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (457, 146, 'ORD20250908000146', 12, 49, 'Product 12', 1698.75, 2, 3397.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (458, 147, 'ORD20250908000147', 41, 102, 'Product 41', 1673.72, 3, 5021.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (459, 147, 'ORD20250908000147', 33, 106, 'Product 33', 1284.78, 2, 2569.56);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (460, 148, 'ORD20250908000148', 23, 65, 'Product 23', 1473.53, 5, 7367.65);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (461, 148, 'ORD20250908000148', 42, 2, 'Product 42', 1782.21, 3, 5346.63);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (462, 148, 'ORD20250908000148', 49, 73, 'Product 49', 330.92, 1, 330.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (463, 148, 'ORD20250908000148', 32, 17, 'Product 32', 695.0, 1, 695.0);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (464, 149, 'ORD20250908000149', 6, 111, 'Product 6', 1351.33, 4, 5405.32);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (465, 149, 'ORD20250908000149', 48, 4, 'Product 48', 1352.26, 3, 4056.78);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (466, 149, 'ORD20250908000149', 45, 93, 'Product 45', 1365.35, 1, 1365.35);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (467, 149, 'ORD20250908000149', 47, 68, 'Product 47', 1681.05, 4, 6724.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (468, 149, 'ORD20250908000149', 48, 92, 'Product 48', 1623.82, 3, 4871.46);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (469, 150, 'ORD20250908000150', 49, 77, 'Product 49', 983.28, 5, 4916.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (470, 150, 'ORD20250908000150', 15, 49, 'Product 15', 1178.59, 1, 1178.59);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (471, 150, 'ORD20250908000150', 27, 91, 'Product 27', 935.24, 4, 3740.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (472, 150, 'ORD20250908000150', 26, 84, 'Product 26', 932.61, 4, 3730.44);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (473, 151, 'ORD20250908000151', 10, 40, 'Product 10', 1478.34, 4, 5913.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (474, 151, 'ORD20250908000151', 2, 108, 'Product 2', 1747.99, 3, 5243.97);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (475, 151, 'ORD20250908000151', 41, 98, 'Product 41', 307.27, 3, 921.81);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (476, 151, 'ORD20250908000151', 28, 4, 'Product 28', 1584.84, 2, 3169.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (477, 152, 'ORD20250908000152', 14, 48, 'Product 14', 198.74, 4, 794.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (478, 152, 'ORD20250908000152', 10, 121, 'Product 10', 549.07, 2, 1098.14);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (479, 153, 'ORD20250908000153', 26, 128, 'Product 26', 325.05, 5, 1625.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (480, 154, 'ORD20250908000154', 13, 59, 'Product 13', 978.2, 4, 3912.8);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (481, 154, 'ORD20250908000154', 28, 111, 'Product 28', 1895.19, 2, 3790.38);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (482, 154, 'ORD20250908000154', 43, 125, 'Product 43', 128.92, 1, 128.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (483, 155, 'ORD20250908000155', 46, 83, 'Product 46', 958.63, 3, 2875.89);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (484, 156, 'ORD20250908000156', 34, 61, 'Product 34', 1887.24, 2, 3774.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (485, 156, 'ORD20250908000156', 32, 107, 'Product 32', 35.81, 3, 107.43);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (486, 157, 'ORD20250908000157', 41, 87, 'Product 41', 628.12, 5, 3140.6);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (487, 157, 'ORD20250908000157', 40, 110, 'Product 40', 252.48, 2, 504.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (488, 158, 'ORD20250908000158', 22, 73, 'Product 22', 1452.27, 2, 2904.54);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (489, 158, 'ORD20250908000158', 37, 95, 'Product 37', 1941.16, 1, 1941.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (490, 158, 'ORD20250908000158', 29, 5, 'Product 29', 746.32, 2, 1492.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (491, 158, 'ORD20250908000158', 21, 54, 'Product 21', 1379.68, 3, 4139.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (492, 158, 'ORD20250908000158', 18, 122, 'Product 18', 1114.44, 5, 5572.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (493, 159, 'ORD20250908000159', 9, 50, 'Product 9', 1931.29, 5, 9656.45);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (494, 159, 'ORD20250908000159', 46, 59, 'Product 46', 1189.74, 2, 2379.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (495, 159, 'ORD20250908000159', 32, 64, 'Product 32', 1130.96, 1, 1130.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (496, 160, 'ORD20250908000160', 25, 25, 'Product 25', 1502.88, 5, 7514.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (497, 160, 'ORD20250908000160', 6, 83, 'Product 6', 1063.18, 4, 4252.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (498, 160, 'ORD20250908000160', 5, 69, 'Product 5', 904.48, 2, 1808.96);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (499, 161, 'ORD20250908000161', 24, 37, 'Product 24', 1697.07, 1, 1697.07);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (500, 161, 'ORD20250908000161', 16, 122, 'Product 16', 454.43, 1, 454.43);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (501, 161, 'ORD20250908000161', 23, 84, 'Product 23', 1651.43, 4, 6605.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (502, 162, 'ORD20250908000162', 18, 38, 'Product 18', 1913.77, 4, 7655.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (503, 162, 'ORD20250908000162', 39, 81, 'Product 39', 1767.59, 2, 3535.18);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (504, 162, 'ORD20250908000162', 39, 49, 'Product 39', 1214.11, 3, 3642.33);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (505, 162, 'ORD20250908000162', 48, 16, 'Product 48', 1770.7, 5, 8853.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (506, 162, 'ORD20250908000162', 19, 130, 'Product 19', 503.97, 4, 2015.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (507, 163, 'ORD20250908000163', 20, 3, 'Product 20', 529.37, 2, 1058.74);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (508, 163, 'ORD20250908000163', 2, 76, 'Product 2', 67.35, 4, 269.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (509, 163, 'ORD20250908000163', 24, 21, 'Product 24', 935.23, 2, 1870.46);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (510, 163, 'ORD20250908000163', 41, 115, 'Product 41', 770.22, 2, 1540.44);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (511, 164, 'ORD20250908000164', 49, 114, 'Product 49', 635.03, 3, 1905.09);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (512, 164, 'ORD20250908000164', 18, 117, 'Product 18', 403.58, 5, 2017.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (513, 164, 'ORD20250908000164', 19, 117, 'Product 19', 334.17, 2, 668.34);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (514, 164, 'ORD20250908000164', 2, 83, 'Product 2', 256.59, 3, 769.77);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (515, 164, 'ORD20250908000164', 24, 123, 'Product 24', 292.09, 4, 1168.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (516, 165, 'ORD20250908000165', 41, 7, 'Product 41', 874.18, 3, 2622.54);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (517, 165, 'ORD20250908000165', 12, 102, 'Product 12', 659.95, 1, 659.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (518, 165, 'ORD20250908000165', 21, 73, 'Product 21', 1135.58, 5, 5677.9);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (519, 165, 'ORD20250908000165', 33, 11, 'Product 33', 121.16, 2, 242.32);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (520, 165, 'ORD20250908000165', 5, 128, 'Product 5', 1854.63, 2, 3709.26);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (521, 166, 'ORD20250908000166', 34, 27, 'Product 34', 335.13, 2, 670.26);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (522, 166, 'ORD20250908000166', 49, 24, 'Product 49', 727.03, 3, 2181.09);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (523, 167, 'ORD20250908000167', 27, 21, 'Product 27', 693.71, 1, 693.71);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (524, 167, 'ORD20250908000167', 26, 127, 'Product 26', 1094.77, 5, 5473.85);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (525, 167, 'ORD20250908000167', 42, 107, 'Product 42', 1581.56, 2, 3163.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (526, 168, 'ORD20250908000168', 14, 23, 'Product 14', 902.51, 1, 902.51);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (527, 168, 'ORD20250908000168', 15, 67, 'Product 15', 486.14, 5, 2430.7);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (528, 168, 'ORD20250908000168', 4, 7, 'Product 4', 1289.38, 2, 2578.76);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (529, 168, 'ORD20250908000168', 3, 123, 'Product 3', 93.93, 2, 187.86);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (530, 168, 'ORD20250908000168', 9, 40, 'Product 9', 1006.16, 3, 3018.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (531, 169, 'ORD20250908000169', 2, 102, 'Product 2', 1025.39, 3, 3076.17);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (532, 169, 'ORD20250908000169', 2, 127, 'Product 2', 1342.59, 4, 5370.36);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (533, 169, 'ORD20250908000169', 3, 18, 'Product 3', 408.25, 1, 408.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (534, 169, 'ORD20250908000169', 15, 54, 'Product 15', 1169.65, 1, 1169.65);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (535, 170, 'ORD20250908000170', 38, 21, 'Product 38', 1764.48, 5, 8822.4);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (536, 170, 'ORD20250908000170', 26, 43, 'Product 26', 433.43, 5, 2167.15);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (537, 170, 'ORD20250908000170', 41, 2, 'Product 41', 1491.07, 3, 4473.21);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (538, 171, 'ORD20250908000171', 33, 96, 'Product 33', 782.96, 2, 1565.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (539, 171, 'ORD20250908000171', 22, 66, 'Product 22', 399.59, 3, 1198.77);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (540, 171, 'ORD20250908000171', 8, 101, 'Product 8', 1304.42, 4, 5217.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (541, 172, 'ORD20250908000172', 39, 115, 'Product 39', 1935.79, 5, 9678.95);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (542, 173, 'ORD20250908000173', 12, 53, 'Product 12', 234.26, 4, 937.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (543, 173, 'ORD20250908000173', 48, 28, 'Product 48', 1866.84, 2, 3733.68);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (544, 173, 'ORD20250908000173', 49, 52, 'Product 49', 1144.71, 3, 3434.13);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (545, 173, 'ORD20250908000173', 38, 55, 'Product 38', 1637.54, 3, 4912.62);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (546, 174, 'ORD20250908000174', 42, 14, 'Product 42', 1255.97, 5, 6279.85);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (547, 174, 'ORD20250908000174', 14, 65, 'Product 14', 1027.09, 3, 3081.27);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (548, 174, 'ORD20250908000174', 14, 4, 'Product 14', 1660.88, 3, 4982.64);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (549, 174, 'ORD20250908000174', 32, 53, 'Product 32', 1894.12, 4, 7576.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (550, 175, 'ORD20250908000175', 26, 118, 'Product 26', 1207.61, 2, 2415.22);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (551, 175, 'ORD20250908000175', 31, 126, 'Product 31', 1126.05, 3, 3378.15);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (552, 175, 'ORD20250908000175', 5, 64, 'Product 5', 1236.58, 1, 1236.58);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (553, 176, 'ORD20250908000176', 1, 13, 'Product 1', 1894.11, 1, 1894.11);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (554, 177, 'ORD20250908000177', 25, 114, 'Product 25', 1460.21, 2, 2920.42);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (555, 177, 'ORD20250908000177', 4, 25, 'Product 4', 742.42, 2, 1484.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (556, 177, 'ORD20250908000177', 48, 109, 'Product 48', 1754.12, 1, 1754.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (557, 178, 'ORD20250908000178', 42, 107, 'Product 42', 54.57, 1, 54.57);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (558, 178, 'ORD20250908000178', 20, 31, 'Product 20', 292.26, 4, 1169.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (559, 178, 'ORD20250908000178', 8, 53, 'Product 8', 1839.62, 1, 1839.62);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (560, 179, 'ORD20250908000179', 1, 45, 'Product 1', 234.05, 2, 468.1);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (561, 179, 'ORD20250908000179', 38, 117, 'Product 38', 1392.89, 3, 4178.67);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (562, 179, 'ORD20250908000179', 2, 70, 'Product 2', 1408.72, 4, 5634.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (563, 179, 'ORD20250908000179', 47, 17, 'Product 47', 1386.69, 2, 2773.38);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (564, 180, 'ORD20250908000180', 44, 102, 'Product 44', 761.08, 1, 761.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (565, 181, 'ORD20250908000181', 30, 111, 'Product 30', 830.72, 1, 830.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (566, 181, 'ORD20250908000181', 43, 12, 'Product 43', 135.39, 3, 406.17);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (567, 181, 'ORD20250908000181', 47, 73, 'Product 47', 963.55, 3, 2890.65);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (568, 181, 'ORD20250908000181', 43, 7, 'Product 43', 551.21, 2, 1102.42);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (569, 182, 'ORD20250908000182', 1, 115, 'Product 1', 1991.72, 1, 1991.72);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (570, 182, 'ORD20250908000182', 36, 112, 'Product 36', 1904.77, 4, 7619.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (571, 183, 'ORD20250908000183', 19, 13, 'Product 19', 734.43, 5, 3672.15);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (572, 183, 'ORD20250908000183', 30, 95, 'Product 30', 1643.22, 5, 8216.1);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (573, 184, 'ORD20250908000184', 43, 96, 'Product 43', 1804.62, 5, 9023.1);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (574, 184, 'ORD20250908000184', 21, 38, 'Product 21', 1473.04, 3, 4419.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (575, 185, 'ORD20250908000185', 38, 45, 'Product 38', 809.54, 4, 3238.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (576, 185, 'ORD20250908000185', 50, 89, 'Product 50', 591.97, 4, 2367.88);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (577, 185, 'ORD20250908000185', 24, 105, 'Product 24', 1923.16, 1, 1923.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (578, 185, 'ORD20250908000185', 9, 65, 'Product 9', 672.28, 3, 2016.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (579, 186, 'ORD20250908000186', 49, 116, 'Product 49', 1083.03, 2, 2166.06);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (580, 186, 'ORD20250908000186', 22, 11, 'Product 22', 1017.64, 2, 2035.28);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (581, 186, 'ORD20250908000186', 35, 88, 'Product 35', 1269.07, 2, 2538.14);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (582, 186, 'ORD20250908000186', 14, 36, 'Product 14', 1651.12, 4, 6604.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (583, 187, 'ORD20250908000187', 8, 108, 'Product 8', 1981.1, 5, 9905.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (584, 188, 'ORD20250908000188', 37, 27, 'Product 37', 18.04, 5, 90.2);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (585, 189, 'ORD20250908000189', 44, 59, 'Product 44', 1422.41, 3, 4267.23);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (586, 189, 'ORD20250908000189', 8, 126, 'Product 8', 657.72, 3, 1973.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (587, 190, 'ORD20250908000190', 34, 102, 'Product 34', 1317.21, 4, 5268.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (588, 190, 'ORD20250908000190', 37, 57, 'Product 37', 708.76, 4, 2835.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (589, 190, 'ORD20250908000190', 15, 28, 'Product 15', 1284.53, 4, 5138.12);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (590, 190, 'ORD20250908000190', 45, 40, 'Product 45', 1791.52, 2, 3583.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (591, 190, 'ORD20250908000190', 33, 1, 'Product 33', 1601.87, 4, 6407.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (592, 191, 'ORD20250908000191', 45, 19, 'Product 45', 1900.72, 2, 3801.44);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (593, 191, 'ORD20250908000191', 20, 104, 'Product 20', 1652.21, 5, 8261.05);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (594, 192, 'ORD20250908000192', 2, 49, 'Product 2', 42.28, 3, 126.84);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (595, 193, 'ORD20250908000193', 12, 54, 'Product 12', 1657.04, 1, 1657.04);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (596, 194, 'ORD20250908000194', 29, 65, 'Product 29', 1902.52, 4, 7610.08);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (597, 195, 'ORD20250908000195', 1, 104, 'Product 1', 1398.66, 3, 4195.98);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (598, 195, 'ORD20250908000195', 25, 88, 'Product 25', 756.08, 3, 2268.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (599, 195, 'ORD20250908000195', 40, 110, 'Product 40', 1183.48, 4, 4733.92);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (600, 196, 'ORD20250908000196', 44, 11, 'Product 44', 987.75, 1, 987.75);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (601, 196, 'ORD20250908000196', 21, 128, 'Product 21', 709.48, 3, 2128.44);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (602, 196, 'ORD20250908000196', 25, 122, 'Product 25', 1508.31, 1, 1508.31);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (603, 196, 'ORD20250908000196', 45, 109, 'Product 45', 1178.38, 3, 3535.14);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (604, 196, 'ORD20250908000196', 2, 126, 'Product 2', 766.13, 4, 3064.52);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (605, 197, 'ORD20250908000197', 17, 122, 'Product 17', 1092.03, 1, 1092.03);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (606, 198, 'ORD20250908000198', 30, 25, 'Product 30', 765.9, 3, 2297.7);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (607, 198, 'ORD20250908000198', 39, 62, 'Product 39', 1968.16, 1, 1968.16);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (608, 199, 'ORD20250908000199', 36, 80, 'Product 36', 1556.56, 4, 6226.24);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (609, 199, 'ORD20250908000199', 40, 56, 'Product 40', 827.11, 1, 827.11);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (610, 199, 'ORD20250908000199', 38, 33, 'Product 38', 1726.81, 1, 1726.81);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (611, 200, 'ORD20250908000200', 5, 114, 'Product 5', 1009.12, 4, 4036.48);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (612, 200, 'ORD20250908000200', 45, 71, 'Product 45', 1134.45, 5, 5672.25);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (613, 200, 'ORD20250908000200', 17, 13, 'Product 17', 1175.47, 5, 5877.35);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (614, 200, 'ORD20250908000200', 12, 126, 'Product 12', 1805.5, 3, 5416.5);
INSERT INTO oms_order_item (id, order_id, order_no, spu_id, sku_id, product_name, price, quantity, total_amount)
VALUES (615, 200, 'ORD20250908000200', 3, 42, 'Product 3', 587.67, 3, 1763.01);

-- Payment Service Data
USE mall_payment;

-- Payment Orders
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (1, 'PAY20250908000001', 'ORD20250908000001', 55, 1, 3437.17, 1, '2025-08-19 06:43:22', '2025-08-19 08:43:22');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (2, 'PAY20250908000002', 'ORD20250908000002', 62, 2, 1252.72, 2, '2025-08-18 04:31:06', '2025-08-18 06:31:06');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (3, 'PAY20250908000003', 'ORD20250908000003', 99, 2, 228.93, 1, '2025-08-20 11:02:02', '2025-08-20 13:02:02');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (4, 'PAY20250908000004', 'ORD20250908000004', 44, 2, 1151.38, 2, '2025-08-15 05:58:54', '2025-08-15 07:58:54');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (5, 'PAY20250908000005', 'ORD20250908000005', 13, 1, 704.63, 1, '2025-08-30 21:08:53', '2025-08-30 23:08:53');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (6, 'PAY20250908000006', 'ORD20250908000006', 64, 2, 3973.74, 2, '2025-08-10 14:39:20', '2025-08-10 16:39:20');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (7, 'PAY20250908000007', 'ORD20250908000007', 91, 2, 3177.81, 1, '2025-08-29 22:27:38', '2025-08-30 00:27:38');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (8, 'PAY20250908000008', 'ORD20250908000008', 61, 1, 1401.72, 2, '2025-08-12 20:43:00', '2025-08-12 22:43:00');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (9, 'PAY20250908000009', 'ORD20250908000009', 9, 1, 1321.25, 1, '2025-09-01 15:42:23', '2025-09-01 17:42:23');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (10, 'PAY20250908000010', 'ORD20250908000010', 52, 1, 4534.02, 1, '2025-08-15 11:42:56', '2025-08-15 13:42:56');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (11, 'PAY20250908000011', 'ORD20250908000011', 4, 1, 441.02, 1, '2025-08-19 03:41:12', '2025-08-19 05:41:12');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (12, 'PAY20250908000012', 'ORD20250908000012', 58, 2, 2791.27, 2, '2025-08-12 20:34:38', '2025-08-12 22:34:38');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (13, 'PAY20250908000013', 'ORD20250908000013', 85, 1, 2914.78, 1, '2025-09-05 18:47:11', '2025-09-05 20:47:11');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (14, 'PAY20250908000014', 'ORD20250908000014', 6, 1, 1020.21, 1, '2025-08-16 01:56:03', '2025-08-16 03:56:03');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (15, 'PAY20250908000015', 'ORD20250908000015', 96, 2, 65.86, 1, '2025-09-03 09:08:57', '2025-09-03 11:08:57');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (16, 'PAY20250908000016', 'ORD20250908000016', 2, 1, 2627.57, 1, '2025-09-07 00:58:10', '2025-09-07 02:58:10');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (17, 'PAY20250908000017', 'ORD20250908000017', 68, 1, 1523.69, 1, '2025-08-25 03:00:22', '2025-08-25 05:00:22');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (18, 'PAY20250908000018', 'ORD20250908000018', 32, 2, 4008.11, 1, '2025-09-01 15:51:45', '2025-09-01 17:51:45');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (19, 'PAY20250908000019', 'ORD20250908000019', 62, 2, 1078.47, 1, '2025-08-28 10:36:52', '2025-08-28 12:36:52');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (20, 'PAY20250908000020', 'ORD20250908000020', 71, 1, 739.1, 1, '2025-09-03 08:46:00', '2025-09-03 10:46:00');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (21, 'PAY20250908000021', 'ORD20250908000021', 19, 1, 2393.67, 1, '2025-09-06 02:20:56', '2025-09-06 04:20:56');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (22, 'PAY20250908000022', 'ORD20250908000022', 22, 1, 3138.44, 2, '2025-08-27 04:09:54', '2025-08-27 06:09:54');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (23, 'PAY20250908000023', 'ORD20250908000023', 29, 2, 2037.89, 2, '2025-08-11 16:43:12', '2025-08-11 18:43:12');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (24, 'PAY20250908000024', 'ORD20250908000024', 42, 2, 148.05, 1, '2025-08-28 14:00:54', '2025-08-28 16:00:54');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (25, 'PAY20250908000025', 'ORD20250908000025', 58, 1, 3745.56, 1, '2025-09-01 21:13:45', '2025-09-01 23:13:45');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (26, 'PAY20250908000026', 'ORD20250908000026', 79, 2, 3233.74, 1, '2025-08-21 07:54:35', '2025-08-21 09:54:35');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (27, 'PAY20250908000027', 'ORD20250908000027', 77, 2, 2490.7, 1, '2025-08-30 14:54:29', '2025-08-30 16:54:29');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (28, 'PAY20250908000028', 'ORD20250908000028', 77, 1, 2092.41, 1, '2025-08-16 16:19:18', '2025-08-16 18:19:18');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (29, 'PAY20250908000029', 'ORD20250908000029', 99, 1, 1853.91, 2, '2025-08-23 05:55:20', '2025-08-23 07:55:20');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (30, 'PAY20250908000030', 'ORD20250908000030', 60, 2, 1524.45, 1, '2025-08-24 13:44:20', '2025-08-24 15:44:20');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (31, 'PAY20250908000031', 'ORD20250908000031', 61, 2, 1936.61, 1, '2025-09-01 09:32:04', '2025-09-01 11:32:04');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (32, 'PAY20250908000032', 'ORD20250908000032', 44, 1, 3460.21, 1, '2025-09-04 11:24:49', '2025-09-04 13:24:49');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (33, 'PAY20250908000033', 'ORD20250908000033', 67, 1, 1265.45, 1, '2025-08-22 18:45:29', '2025-08-22 20:45:29');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (34, 'PAY20250908000034', 'ORD20250908000034', 37, 1, 1572.13, 1, '2025-08-21 05:39:58', '2025-08-21 07:39:58');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (35, 'PAY20250908000035', 'ORD20250908000035', 73, 1, 1749.08, 1, '2025-08-18 22:14:42', '2025-08-19 00:14:42');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (36, 'PAY20250908000036', 'ORD20250908000036', 43, 2, 1399.83, 2, '2025-08-18 18:10:28', '2025-08-18 20:10:28');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (37, 'PAY20250908000037', 'ORD20250908000037', 99, 1, 4013.2, 1, '2025-08-18 20:03:15', '2025-08-18 22:03:15');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (38, 'PAY20250908000038', 'ORD20250908000038', 88, 2, 3565.98, 1, '2025-08-19 12:56:46', '2025-08-19 14:56:46');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (39, 'PAY20250908000039', 'ORD20250908000039', 60, 1, 594.88, 1, '2025-08-21 14:32:06', '2025-08-21 16:32:06');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (40, 'PAY20250908000040', 'ORD20250908000040', 27, 2, 923.56, 2, '2025-08-27 19:37:58', '2025-08-27 21:37:58');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (41, 'PAY20250908000041', 'ORD20250908000041', 32, 2, 2426.87, 1, '2025-09-06 04:42:59', '2025-09-06 06:42:59');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (42, 'PAY20250908000042', 'ORD20250908000042', 59, 2, 740.24, 1, '2025-08-21 20:57:11', '2025-08-21 22:57:11');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (43, 'PAY20250908000043', 'ORD20250908000043', 16, 2, 4067.44, 2, '2025-08-09 18:32:03', '2025-08-09 20:32:03');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (44, 'PAY20250908000044', 'ORD20250908000044', 9, 1, 4061.84, 1, '2025-09-02 15:30:33', '2025-09-02 17:30:33');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (45, 'PAY20250908000045', 'ORD20250908000045', 59, 2, 2560.48, 2, '2025-08-19 06:48:03', '2025-08-19 08:48:03');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (46, 'PAY20250908000046', 'ORD20250908000046', 35, 1, 4908.07, 2, '2025-09-07 12:16:10', '2025-09-07 14:16:10');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (47, 'PAY20250908000047', 'ORD20250908000047', 14, 2, 2191.49, 1, '2025-08-15 09:53:27', '2025-08-15 11:53:27');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (48, 'PAY20250908000048', 'ORD20250908000048', 18, 1, 623.35, 1, '2025-08-11 14:32:11', '2025-08-11 16:32:11');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (49, 'PAY20250908000049', 'ORD20250908000049', 9, 2, 546.23, 1, '2025-08-15 19:11:07', '2025-08-15 21:11:07');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (50, 'PAY20250908000050', 'ORD20250908000050', 13, 2, 1032.41, 1, '2025-08-10 02:12:39', '2025-08-10 04:12:39');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (51, 'PAY20250908000051', 'ORD20250908000051', 15, 2, 4884.41, 1, '2025-08-23 09:49:51', '2025-08-23 11:49:51');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (52, 'PAY20250908000052', 'ORD20250908000052', 56, 1, 1160.14, 1, '2025-09-05 10:34:18', '2025-09-05 12:34:18');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (53, 'PAY20250908000053', 'ORD20250908000053', 46, 1, 767.0, 1, '2025-08-20 06:53:51', '2025-08-20 08:53:51');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (54, 'PAY20250908000054', 'ORD20250908000054', 22, 2, 1023.67, 1, '2025-08-17 09:36:08', '2025-08-17 11:36:08');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (55, 'PAY20250908000055', 'ORD20250908000055', 12, 2, 4049.66, 1, '2025-08-14 06:12:26', '2025-08-14 08:12:26');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (56, 'PAY20250908000056', 'ORD20250908000056', 32, 2, 1112.66, 2, '2025-09-08 04:58:17', '2025-09-08 06:58:17');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (57, 'PAY20250908000057', 'ORD20250908000057', 22, 1, 4588.95, 1, '2025-08-14 19:50:33', '2025-08-14 21:50:33');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (58, 'PAY20250908000058', 'ORD20250908000058', 1, 1, 3359.1, 2, '2025-09-06 18:03:16', '2025-09-06 20:03:16');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (59, 'PAY20250908000059', 'ORD20250908000059', 77, 2, 2236.36, 1, '2025-08-14 11:39:48', '2025-08-14 13:39:48');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (60, 'PAY20250908000060', 'ORD20250908000060', 11, 2, 3565.0, 2, '2025-08-16 21:56:22', '2025-08-16 23:56:22');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (61, 'PAY20250908000061', 'ORD20250908000061', 95, 2, 4254.43, 2, '2025-08-10 17:33:20', '2025-08-10 19:33:20');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (62, 'PAY20250908000062', 'ORD20250908000062', 70, 2, 1033.59, 2, '2025-08-30 19:27:44', '2025-08-30 21:27:44');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (63, 'PAY20250908000063', 'ORD20250908000063', 68, 2, 1617.18, 2, '2025-09-03 06:04:38', '2025-09-03 08:04:38');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (64, 'PAY20250908000064', 'ORD20250908000064', 79, 2, 4192.71, 1, '2025-08-27 17:15:32', '2025-08-27 19:15:32');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (65, 'PAY20250908000065', 'ORD20250908000065', 19, 1, 3008.76, 2, '2025-08-17 18:54:23', '2025-08-17 20:54:23');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (66, 'PAY20250908000066', 'ORD20250908000066', 52, 1, 1097.46, 1, '2025-08-29 06:42:22', '2025-08-29 08:42:22');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (67, 'PAY20250908000067', 'ORD20250908000067', 36, 1, 3577.52, 1, '2025-08-28 00:48:43', '2025-08-28 02:48:43');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (68, 'PAY20250908000068', 'ORD20250908000068', 77, 2, 4803.67, 1, '2025-08-12 18:06:10', '2025-08-12 20:06:10');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (69, 'PAY20250908000069', 'ORD20250908000069', 58, 1, 1950.46, 1, '2025-08-27 01:35:58', '2025-08-27 03:35:58');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (70, 'PAY20250908000070', 'ORD20250908000070', 49, 1, 2459.88, 2, '2025-08-30 04:53:02', '2025-08-30 06:53:02');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (71, 'PAY20250908000071', 'ORD20250908000071', 12, 2, 1282.08, 1, '2025-08-22 16:16:37', '2025-08-22 18:16:37');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (72, 'PAY20250908000072', 'ORD20250908000072', 47, 2, 2397.87, 1, '2025-08-20 06:02:26', '2025-08-20 08:02:26');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (73, 'PAY20250908000073', 'ORD20250908000073', 75, 1, 4231.05, 2, '2025-08-27 07:29:05', '2025-08-27 09:29:05');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (74, 'PAY20250908000074', 'ORD20250908000074', 7, 1, 458.96, 1, '2025-08-14 02:21:16', '2025-08-14 04:21:16');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (75, 'PAY20250908000075', 'ORD20250908000075', 79, 1, 1259.45, 1, '2025-08-31 14:37:42', '2025-08-31 16:37:42');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (76, 'PAY20250908000076', 'ORD20250908000076', 20, 2, 716.75, 2, '2025-08-23 03:13:10', '2025-08-23 05:13:10');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (77, 'PAY20250908000077', 'ORD20250908000077', 24, 1, 2053.23, 1, '2025-09-03 21:09:48', '2025-09-03 23:09:48');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (78, 'PAY20250908000078', 'ORD20250908000078', 9, 1, 4360.33, 1, '2025-09-06 09:51:56', '2025-09-06 11:51:56');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (79, 'PAY20250908000079', 'ORD20250908000079', 84, 1, 1448.33, 2, '2025-08-30 03:51:57', '2025-08-30 05:51:57');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (80, 'PAY20250908000080', 'ORD20250908000080', 22, 1, 95.74, 1, '2025-09-07 20:50:51', '2025-09-07 22:50:51');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (81, 'PAY20250908000081', 'ORD20250908000081', 92, 1, 346.24, 1, '2025-08-22 16:11:12', '2025-08-22 18:11:12');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (82, 'PAY20250908000082', 'ORD20250908000082', 23, 1, 3897.51, 1, '2025-08-18 07:11:21', '2025-08-18 09:11:21');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (83, 'PAY20250908000083', 'ORD20250908000083', 20, 1, 1235.78, 1, '2025-08-25 09:56:13', '2025-08-25 11:56:13');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (84, 'PAY20250908000084', 'ORD20250908000084', 22, 2, 2571.1, 1, '2025-09-02 22:20:01', '2025-09-03 00:20:01');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (85, 'PAY20250908000085', 'ORD20250908000085', 65, 1, 1975.09, 1, '2025-08-11 23:03:11', '2025-08-12 01:03:11');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (86, 'PAY20250908000086', 'ORD20250908000086', 61, 1, 4234.22, 1, '2025-09-08 04:13:41', '2025-09-08 06:13:41');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (87, 'PAY20250908000087', 'ORD20250908000087', 48, 2, 2317.4, 2, '2025-08-18 02:47:11', '2025-08-18 04:47:11');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (88, 'PAY20250908000088', 'ORD20250908000088', 91, 1, 3570.01, 1, '2025-09-08 00:46:53', '2025-09-08 02:46:53');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (89, 'PAY20250908000089', 'ORD20250908000089', 97, 1, 344.7, 2, '2025-08-16 05:04:03', '2025-08-16 07:04:03');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (90, 'PAY20250908000090', 'ORD20250908000090', 1, 2, 2309.26, 2, '2025-08-28 18:57:21', '2025-08-28 20:57:21');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (91, 'PAY20250908000091', 'ORD20250908000091', 71, 2, 4445.08, 2, '2025-09-03 20:10:15', '2025-09-03 22:10:15');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (92, 'PAY20250908000092', 'ORD20250908000092', 46, 2, 3760.64, 2, '2025-08-17 17:10:30', '2025-08-17 19:10:30');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (93, 'PAY20250908000093', 'ORD20250908000093', 65, 2, 639.94, 1, '2025-08-19 15:46:32', '2025-08-19 17:46:32');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (94, 'PAY20250908000094', 'ORD20250908000094', 10, 2, 4211.31, 1, '2025-09-07 22:53:59', '2025-09-08 00:53:59');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (95, 'PAY20250908000095', 'ORD20250908000095', 53, 2, 1885.96, 1, '2025-08-10 18:32:42', '2025-08-10 20:32:42');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (96, 'PAY20250908000096', 'ORD20250908000096', 41, 2, 1001.53, 1, '2025-08-20 01:53:07', '2025-08-20 03:53:07');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (97, 'PAY20250908000097', 'ORD20250908000097', 56, 1, 2255.2, 1, '2025-08-24 06:33:02', '2025-08-24 08:33:02');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (98, 'PAY20250908000098', 'ORD20250908000098', 51, 1, 720.15, 1, '2025-08-30 22:13:48', '2025-08-31 00:13:48');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (99, 'PAY20250908000099', 'ORD20250908000099', 33, 2, 2002.2, 2, '2025-08-27 15:38:05', '2025-08-27 17:38:05');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (100, 'PAY20250908000100', 'ORD20250908000100', 27, 2, 660.77, 1, '2025-08-21 12:35:59', '2025-08-21 14:35:59');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (101, 'PAY20250908000101', 'ORD20250908000101', 19, 1, 1484.14, 1, '2025-08-26 15:09:14', '2025-08-26 17:09:14');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (102, 'PAY20250908000102', 'ORD20250908000102', 60, 2, 4436.36, 2, '2025-08-13 09:44:53', '2025-08-13 11:44:53');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (103, 'PAY20250908000103', 'ORD20250908000103', 77, 2, 1780.32, 2, '2025-08-11 13:56:31', '2025-08-11 15:56:31');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (104, 'PAY20250908000104', 'ORD20250908000104', 62, 1, 1768.89, 1, '2025-08-24 16:18:07', '2025-08-24 18:18:07');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (105, 'PAY20250908000105', 'ORD20250908000105', 65, 1, 65.17, 1, '2025-08-16 16:45:32', '2025-08-16 18:45:32');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (106, 'PAY20250908000106', 'ORD20250908000106', 96, 2, 308.84, 1, '2025-09-03 21:50:34', '2025-09-03 23:50:34');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (107, 'PAY20250908000107', 'ORD20250908000107', 48, 1, 2247.31, 1, '2025-09-08 08:50:18', '2025-09-08 10:50:18');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (108, 'PAY20250908000108', 'ORD20250908000108', 59, 2, 3112.65, 1, '2025-08-27 11:26:27', '2025-08-27 13:26:27');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (109, 'PAY20250908000109', 'ORD20250908000109', 7, 1, 4418.83, 2, '2025-08-30 15:41:05', '2025-08-30 17:41:05');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (110, 'PAY20250908000110', 'ORD20250908000110', 98, 1, 3334.94, 1, '2025-08-17 01:22:12', '2025-08-17 03:22:12');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (111, 'PAY20250908000111', 'ORD20250908000111', 49, 2, 3306.48, 2, '2025-08-22 13:55:43', '2025-08-22 15:55:43');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (112, 'PAY20250908000112', 'ORD20250908000112', 19, 1, 3812.92, 1, '2025-08-14 03:18:05', '2025-08-14 05:18:05');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (113, 'PAY20250908000113', 'ORD20250908000113', 79, 2, 1386.04, 1, '2025-08-11 15:32:06', '2025-08-11 17:32:06');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (114, 'PAY20250908000114', 'ORD20250908000114', 6, 2, 70.45, 1, '2025-08-19 01:19:44', '2025-08-19 03:19:44');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (115, 'PAY20250908000115', 'ORD20250908000115', 54, 1, 2093.77, 1, '2025-08-30 08:06:56', '2025-08-30 10:06:56');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (116, 'PAY20250908000116', 'ORD20250908000116', 71, 2, 4952.23, 1, '2025-08-09 15:23:18', '2025-08-09 17:23:18');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (117, 'PAY20250908000117', 'ORD20250908000117', 9, 1, 4295.61, 1, '2025-09-02 01:04:21', '2025-09-02 03:04:21');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (118, 'PAY20250908000118', 'ORD20250908000118', 9, 1, 2346.85, 1, '2025-09-02 13:40:34', '2025-09-02 15:40:34');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (119, 'PAY20250908000119', 'ORD20250908000119', 15, 1, 3350.39, 1, '2025-09-08 13:20:59', '2025-09-08 15:20:59');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (120, 'PAY20250908000120', 'ORD20250908000120', 94, 2, 4254.46, 1, '2025-09-06 15:01:55', '2025-09-06 17:01:55');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (121, 'PAY20250908000121', 'ORD20250908000121', 72, 2, 3161.31, 1, '2025-09-04 04:23:18', '2025-09-04 06:23:18');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (122, 'PAY20250908000122', 'ORD20250908000122', 20, 2, 4032.88, 1, '2025-08-20 14:16:06', '2025-08-20 16:16:06');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (123, 'PAY20250908000123', 'ORD20250908000123', 3, 2, 2422.15, 1, '2025-09-03 07:33:48', '2025-09-03 09:33:48');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (124, 'PAY20250908000124', 'ORD20250908000124', 39, 2, 4864.08, 2, '2025-08-12 02:28:51', '2025-08-12 04:28:51');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (125, 'PAY20250908000125', 'ORD20250908000125', 76, 2, 4010.35, 1, '2025-08-14 18:48:19', '2025-08-14 20:48:19');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (126, 'PAY20250908000126', 'ORD20250908000126', 19, 1, 4224.0, 1, '2025-08-21 16:31:42', '2025-08-21 18:31:42');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (127, 'PAY20250908000127', 'ORD20250908000127', 60, 1, 4631.0, 1, '2025-08-29 15:29:38', '2025-08-29 17:29:38');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (128, 'PAY20250908000128', 'ORD20250908000128', 96, 2, 2102.26, 1, '2025-08-24 03:11:11', '2025-08-24 05:11:11');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (129, 'PAY20250908000129', 'ORD20250908000129', 11, 1, 820.6, 2, '2025-08-12 19:59:48', '2025-08-12 21:59:48');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (130, 'PAY20250908000130', 'ORD20250908000130', 100, 2, 4631.36, 1, '2025-08-30 18:15:57', '2025-08-30 20:15:57');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (131, 'PAY20250908000131', 'ORD20250908000131', 52, 2, 778.69, 2, '2025-08-11 02:22:26', '2025-08-11 04:22:26');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (132, 'PAY20250908000132', 'ORD20250908000132', 43, 2, 599.95, 1, '2025-09-02 05:33:52', '2025-09-02 07:33:52');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (133, 'PAY20250908000133', 'ORD20250908000133', 63, 2, 2946.23, 2, '2025-08-30 11:50:13', '2025-08-30 13:50:13');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (134, 'PAY20250908000134', 'ORD20250908000134', 31, 2, 1223.75, 1, '2025-08-25 22:19:32', '2025-08-26 00:19:32');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (135, 'PAY20250908000135', 'ORD20250908000135', 21, 2, 2971.3, 1, '2025-08-14 12:00:17', '2025-08-14 14:00:17');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (136, 'PAY20250908000136', 'ORD20250908000136', 11, 1, 3983.01, 1, '2025-08-16 21:32:39', '2025-08-16 23:32:39');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (137, 'PAY20250908000137', 'ORD20250908000137', 70, 2, 2468.32, 1, '2025-08-16 23:41:23', '2025-08-17 01:41:23');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (138, 'PAY20250908000138', 'ORD20250908000138', 7, 1, 2361.33, 1, '2025-08-16 02:14:49', '2025-08-16 04:14:49');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (139, 'PAY20250908000139', 'ORD20250908000139', 38, 2, 3396.91, 1, '2025-09-06 09:09:10', '2025-09-06 11:09:10');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (140, 'PAY20250908000140', 'ORD20250908000140', 12, 1, 834.46, 1, '2025-08-25 15:22:14', '2025-08-25 17:22:14');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (141, 'PAY20250908000141', 'ORD20250908000141', 13, 1, 4981.61, 1, '2025-08-11 12:45:52', '2025-08-11 14:45:52');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (142, 'PAY20250908000142', 'ORD20250908000142', 79, 1, 4108.93, 1, '2025-08-17 20:42:56', '2025-08-17 22:42:56');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (143, 'PAY20250908000143', 'ORD20250908000143', 51, 2, 546.62, 1, '2025-09-08 11:45:41', '2025-09-08 13:45:41');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (144, 'PAY20250908000144', 'ORD20250908000144', 68, 1, 4160.69, 1, '2025-08-16 08:13:37', '2025-08-16 10:13:37');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (145, 'PAY20250908000145', 'ORD20250908000145', 9, 2, 3034.78, 1, '2025-08-17 06:06:18', '2025-08-17 08:06:18');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (146, 'PAY20250908000146', 'ORD20250908000146', 83, 1, 2487.11, 1, '2025-08-23 09:15:31', '2025-08-23 11:15:31');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (147, 'PAY20250908000147', 'ORD20250908000147', 5, 2, 4262.26, 1, '2025-09-01 13:25:01', '2025-09-01 15:25:01');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (148, 'PAY20250908000148', 'ORD20250908000148', 58, 2, 4759.01, 1, '2025-08-23 20:51:31', '2025-08-23 22:51:31');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (149, 'PAY20250908000149', 'ORD20250908000149', 84, 2, 2095.67, 1, '2025-08-24 15:07:07', '2025-08-24 17:07:07');
INSERT INTO payment_order (id, payment_no, order_no, user_id, payment_type, payment_amount, status, payment_time, expire_time)
VALUES (150, 'PAY20250908000150', 'ORD20250908000150', 86, 2, 1060.63, 2, '2025-08-16 20:00:00', '2025-08-16 22:00:00');

-- Refund Orders
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (1, 'REF20250908000001', 'PAY20250908000040', 'ORD20250908000001', 83, 671.59, '其他', 3);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (2, 'REF20250908000002', 'PAY20250908000010', 'ORD20250908000002', 39, 938.06, '发错货', 2);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (3, 'REF20250908000003', 'PAY20250908000021', 'ORD20250908000003', 84, 43.5, '发错货', 2);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (4, 'REF20250908000004', 'PAY20250908000016', 'ORD20250908000004', 25, 351.63, '商品质量问题', 0);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (5, 'REF20250908000005', 'PAY20250908000031', 'ORD20250908000005', 33, 395.66, '商品描述不符', 2);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (6, 'REF20250908000006', 'PAY20250908000038', 'ORD20250908000006', 85, 966.89, '不想要了', 2);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (7, 'REF20250908000007', 'PAY20250908000028', 'ORD20250908000007', 18, 817.62, '商品描述不符', 2);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (8, 'REF20250908000008', 'PAY20250908000115', 'ORD20250908000008', 97, 570.9, '发错货', 0);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (9, 'REF20250908000009', 'PAY20250908000033', 'ORD20250908000009', 22, 954.75, '商品描述不符', 0);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (10, 'REF20250908000010', 'PAY20250908000039', 'ORD20250908000010', 63, 876.77, '不想要了', 1);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (11, 'REF20250908000011', 'PAY20250908000019', 'ORD20250908000011', 6, 885.38, '发错货', 3);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (12, 'REF20250908000012', 'PAY20250908000073', 'ORD20250908000012', 78, 500.4, '不想要了', 1);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (13, 'REF20250908000013', 'PAY20250908000086', 'ORD20250908000013', 93, 188.71, '商品质量问题', 0);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (14, 'REF20250908000014', 'PAY20250908000082', 'ORD20250908000014', 81, 367.12, '不想要了', 3);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (15, 'REF20250908000015', 'PAY20250908000149', 'ORD20250908000015', 48, 343.81, '商品质量问题', 2);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (16, 'REF20250908000016', 'PAY20250908000062', 'ORD20250908000016', 99, 826.08, '其他', 1);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (17, 'REF20250908000017', 'PAY20250908000124', 'ORD20250908000017', 28, 156.33, '其他', 2);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (18, 'REF20250908000018', 'PAY20250908000112', 'ORD20250908000018', 68, 396.78, '其他', 3);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (19, 'REF20250908000019', 'PAY20250908000105', 'ORD20250908000019', 79, 927.31, '发错货', 2);
INSERT INTO refund_order (id, refund_no, payment_no, order_no, user_id, refund_amount, refund_reason, status)
VALUES (20, 'REF20250908000020', 'PAY20250908000032', 'ORD20250908000020', 99, 950.32, '商品质量问题', 2);

SET FOREIGN_KEY_CHECKS = 1;

-- Test data generation completed