-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                        `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                        `password` VARCHAR(100) NOT NULL COMMENT '密码(加密)',
                        `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
                        `email` VARCHAR(50) DEFAULT NULL COMMENT '邮箱',
                        `nickname` VARCHAR(50) DEFAULT NULL COMMENT '昵称',
                        `avatar` VARCHAR(255) DEFAULT NULL COMMENT '头像URL',
                        `gender` TINYINT(1) DEFAULT '0' COMMENT '性别:0-未知,1-男,2-女',
                        `birthday` DATE DEFAULT NULL COMMENT '生日',
                        `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态:0-禁用,1-正常',
                        `register_source` TINYINT(1) DEFAULT '1' COMMENT '注册来源:1-PC,2-APP,3-小程序',
                        `last_login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
                        `last_login_ip` VARCHAR(50) DEFAULT NULL COMMENT '最后登录IP',
                        `deleted` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
                        `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_username` (`username`),
                        UNIQUE KEY `uk_phone` (`phone`),
                        KEY `idx_email` (`email`),
                        KEY `idx_status` (`status`),
                        KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户基础信息表';

-- ----------------------------
-- Table structure for user_member
-- ----------------------------
CREATE TABLE `user_member` (
                               `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                               `member_no` VARCHAR(32) NOT NULL COMMENT '会员编号',
                               `level_id` INT(11) NOT NULL DEFAULT '1' COMMENT '会员等级ID',
                               `points` INT(11) NOT NULL DEFAULT '0' COMMENT '积分',
                               `growth_value` INT(11) NOT NULL DEFAULT '0' COMMENT '成长值',
                               `balance` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
                               `total_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '累计消费金额',
                               `total_orders` INT(11) NOT NULL DEFAULT '0' COMMENT '累计订单数',
                               `expire_time` DATETIME DEFAULT NULL COMMENT '会员过期时间',
                               `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_user_id` (`user_id`),
                               UNIQUE KEY `uk_member_no` (`member_no`),
                               KEY `idx_level_id` (`level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员信息表';

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
CREATE TABLE `user_address` (
                                `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                                `receiver_name` VARCHAR(50) NOT NULL COMMENT '收货人姓名',
                                `receiver_phone` VARCHAR(20) NOT NULL COMMENT '收货人电话',
                                `province_code` VARCHAR(20) NOT NULL COMMENT '省份编码',
                                `province` VARCHAR(50) NOT NULL COMMENT '省份',
                                `city_code` VARCHAR(20) NOT NULL COMMENT '城市编码',
                                `city` VARCHAR(50) NOT NULL COMMENT '城市',
                                `district_code` VARCHAR(20) NOT NULL COMMENT '区县编码',
                                `district` VARCHAR(50) NOT NULL COMMENT '区县',
                                `detail_address` VARCHAR(200) NOT NULL COMMENT '详细地址',
                                `postal_code` VARCHAR(10) DEFAULT NULL COMMENT '邮政编码',
                                `address_tag` VARCHAR(20) DEFAULT NULL COMMENT '地址标签',
                                `is_default` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否默认',
                                `deleted` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
                                `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                KEY `idx_user_id` (`user_id`),
                                KEY `idx_is_default` (`is_default`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址表';

-- ----------------------------
-- Table structure for member_level
-- ----------------------------
CREATE TABLE `member_level` (
                                `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `level_name` VARCHAR(50) NOT NULL COMMENT '等级名称',
                                `min_growth` INT(11) NOT NULL COMMENT '最小成长值',
                                `max_growth` INT(11) NOT NULL COMMENT '最大成长值',
                                `discount` DECIMAL(3,2) NOT NULL DEFAULT '1.00' COMMENT '折扣率',
                                `icon` VARCHAR(255) DEFAULT NULL COMMENT '等级图标',
                                `description` VARCHAR(500) DEFAULT NULL COMMENT '等级说明',
                                `privileges` JSON DEFAULT NULL COMMENT '特权说明',
                                `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态',
                                `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员等级表';

-- ----------------------------
-- Table structure for user_point_record
-- ----------------------------
CREATE TABLE `user_point_record` (
                                     `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                                     `points` INT(11) NOT NULL COMMENT '积分变化值',
                                     `balance` INT(11) NOT NULL COMMENT '变化后余额',
                                     `type` TINYINT(1) NOT NULL COMMENT '类型:1-获得,2-消费',
                                     `source` VARCHAR(50) NOT NULL COMMENT '来源',
                                     `source_id` VARCHAR(50) DEFAULT NULL COMMENT '来源ID',
                                     `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注',
                                     `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     PRIMARY KEY (`id`),
                                     KEY `idx_user_id` (`user_id`),
                                     KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分记录表';