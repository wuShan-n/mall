-- 2.1 用户基础表设计
-- 用户表是整个系统的起点，它不仅存储认证信息，还是其他所有用户相关数据的锚点。

-- 用户主表：存储核心认证信息
CREATE TABLE `users` (
                         `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                         `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                         `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
                         `mobile` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
                         `password` VARCHAR(255) NOT NULL COMMENT '密码（BCrypt加密）',
                         `salt` VARCHAR(32) DEFAULT NULL COMMENT '密码盐（如果需要额外的盐）',
                         `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-正常，2-锁定',
                         `user_type` TINYINT NOT NULL DEFAULT 1 COMMENT '用户类型：1-普通用户，2-商家，3-管理员',
                         `register_source` VARCHAR(20) DEFAULT 'WEB' COMMENT '注册来源：WEB/APP/WECHAT',
                         `register_ip` VARCHAR(45) DEFAULT NULL COMMENT '注册IP',
                         `last_login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
                         `last_login_ip` VARCHAR(45) DEFAULT NULL COMMENT '最后登录IP',
                         `login_count` INT DEFAULT 0 COMMENT '登录次数',
                         `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         `deleted_at` DATETIME DEFAULT NULL COMMENT '删除时间（软删除）',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `uk_username` (`username`),
                         UNIQUE KEY `uk_email` (`email`),
                         UNIQUE KEY `uk_mobile` (`mobile`),
                         KEY `idx_status` (`status`),
                         KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 用户详细信息表：与用户表一对一关系
CREATE TABLE `user_profiles` (
                                 `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                 `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                 `nickname` VARCHAR(50) DEFAULT NULL COMMENT '昵称',
                                 `real_name` VARCHAR(50) DEFAULT NULL COMMENT '真实姓名',
                                 `id_card` VARCHAR(255) DEFAULT NULL COMMENT '身份证号（加密存储）',
                                 `gender` TINYINT DEFAULT 0 COMMENT '性别：0-未知，1-男，2-女',
                                 `birthday` DATE DEFAULT NULL COMMENT '生日',
                                 `avatar_url` VARCHAR(500) DEFAULT NULL COMMENT '头像URL',
                                 `country` VARCHAR(50) DEFAULT 'China' COMMENT '国家',
                                 `province` VARCHAR(50) DEFAULT NULL COMMENT '省份',
                                 `city` VARCHAR(50) DEFAULT NULL COMMENT '城市',
                                 `district` VARCHAR(50) DEFAULT NULL COMMENT '区县',
                                 `address` VARCHAR(255) DEFAULT NULL COMMENT '详细地址',
                                 `bio` VARCHAR(500) DEFAULT NULL COMMENT '个人简介',
                                 `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `uk_user_id` (`user_id`),
                                 KEY `idx_real_name` (`real_name`),
                                 CONSTRAINT `fk_user_profiles_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户详情表';

-- 用户等级表：定义会员等级体系
CREATE TABLE `user_levels` (
                               `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                               `level_name` VARCHAR(50) NOT NULL COMMENT '等级名称',
                               `level_value` INT NOT NULL COMMENT '等级值',
                               `min_points` INT NOT NULL COMMENT '最低积分要求',
                               `discount_rate` DECIMAL(3,2) DEFAULT 1.00 COMMENT '折扣率',
                               `icon_url` VARCHAR(500) DEFAULT NULL COMMENT '等级图标',
                               `benefits` JSON DEFAULT NULL COMMENT '等级权益描述',
                               `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_level_value` (`level_value`),
                               KEY `idx_min_points` (`min_points`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户等级配置表';

-- 用户积分表：记录用户积分变动
CREATE TABLE `user_points` (
                               `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                               `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                               `points` INT NOT NULL DEFAULT 0 COMMENT '当前积分',
                               `total_earned` INT NOT NULL DEFAULT 0 COMMENT '累计获得积分',
                               `total_used` INT NOT NULL DEFAULT 0 COMMENT '累计使用积分',
                               `level_id` INT UNSIGNED DEFAULT NULL COMMENT '当前等级',
                               `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_user_id` (`user_id`),
                               KEY `idx_points` (`points`),
                               KEY `idx_level_id` (`level_id`),
                               CONSTRAINT `fk_user_points_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                               CONSTRAINT `fk_user_points_level` FOREIGN KEY (`level_id`) REFERENCES `user_levels` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户积分表';

-- 积分变动记录表：积分流水
CREATE TABLE `point_transactions` (
                                      `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                      `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                      `points` INT NOT NULL COMMENT '积分变动数量（正为增加，负为减少）',
                                      `balance` INT NOT NULL COMMENT '变动后余额',
                                      `type` VARCHAR(30) NOT NULL COMMENT '类型：PURCHASE/REFUND/GIFT/EXPIRE',
                                      `reference_type` VARCHAR(30) DEFAULT NULL COMMENT '关联类型：ORDER/RETURN/PROMOTION',
                                      `reference_id` BIGINT DEFAULT NULL COMMENT '关联ID',
                                      `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
                                      `expired_at` DATETIME DEFAULT NULL COMMENT '积分过期时间',
                                      `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      PRIMARY KEY (`id`),
                                      KEY `idx_user_id` (`user_id`),
                                      KEY `idx_type` (`type`),
                                      KEY `idx_reference` (`reference_type`, `reference_id`),
                                      KEY `idx_created_at` (`created_at`),
                                      CONSTRAINT `fk_point_transactions_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='积分流水表';

-- 收货地址表
CREATE TABLE `user_addresses` (
                                  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                  `receiver_name` VARCHAR(50) NOT NULL COMMENT '收件人姓名',
                                  `receiver_mobile` VARCHAR(20) NOT NULL COMMENT '收件人电话',
                                  `province` VARCHAR(50) NOT NULL COMMENT '省份',
                                  `city` VARCHAR(50) NOT NULL COMMENT '城市',
                                  `district` VARCHAR(50) NOT NULL COMMENT '区县',
                                  `address` VARCHAR(255) NOT NULL COMMENT '详细地址',
                                  `zip_code` VARCHAR(20) DEFAULT NULL COMMENT '邮政编码',
                                  `tag` VARCHAR(20) DEFAULT NULL COMMENT '地址标签：家/公司/其他',
                                  `is_default` BOOLEAN DEFAULT FALSE COMMENT '是否默认地址',
                                  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  KEY `idx_user_id` (`user_id`),
                                  KEY `idx_is_default` (`is_default`),
                                  CONSTRAINT `fk_user_addresses_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收货地址表';

-- 2.2 用户认证与安全表设计
-- 安全是电商系统的生命线，需要记录所有认证相关的活动。

-- OAuth第三方登录表
CREATE TABLE `user_oauth` (
                              `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                              `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                              `oauth_type` VARCHAR(20) NOT NULL COMMENT '第三方类型：WECHAT/ALIPAY/QQ',
                              `oauth_id` VARCHAR(100) NOT NULL COMMENT '第三方用户ID',
                              `union_id` VARCHAR(100) DEFAULT NULL COMMENT '联合ID（如微信UnionId）',
                              `credential` TEXT DEFAULT NULL COMMENT '凭证信息（加密存储）',
                              `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `uk_oauth` (`oauth_type`, `oauth_id`),
                              KEY `idx_user_id` (`user_id`),
                              KEY `idx_union_id` (`union_id`),
                              CONSTRAINT `fk_user_oauth_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='第三方登录关联表';

-- 用户登录日志表
CREATE TABLE `user_login_logs` (
                                   `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                   `user_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '用户ID（登录失败时可能为空）',
                                   `username` VARCHAR(100) DEFAULT NULL COMMENT '尝试登录的用户名',
                                   `login_type` VARCHAR(20) NOT NULL COMMENT '登录方式：PASSWORD/SMS/OAUTH',
                                   `login_ip` VARCHAR(45) NOT NULL COMMENT '登录IP',
                                   `login_location` VARCHAR(100) DEFAULT NULL COMMENT '登录地点',
                                   `user_agent` VARCHAR(500) DEFAULT NULL COMMENT '用户代理',
                                   `device_type` VARCHAR(20) DEFAULT NULL COMMENT '设备类型：PC/MOBILE/TABLET',
                                   `os` VARCHAR(50) DEFAULT NULL COMMENT '操作系统',
                                   `browser` VARCHAR(50) DEFAULT NULL COMMENT '浏览器',
                                   `status` TINYINT NOT NULL COMMENT '状态：1-成功，0-失败',
                                   `failure_reason` VARCHAR(100) DEFAULT NULL COMMENT '失败原因',
                                   `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   PRIMARY KEY (`id`),
                                   KEY `idx_user_id` (`user_id`),
                                   KEY `idx_username` (`username`),
                                   KEY `idx_login_ip` (`login_ip`),
                                   KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录日志表';

-- 用户会话表（用于单点登录和会话管理）
CREATE TABLE `user_sessions` (
                                 `id` VARCHAR(128) NOT NULL COMMENT '会话ID',
                                 `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                 `login_time` DATETIME NOT NULL COMMENT '登录时间',
                                 `login_ip` VARCHAR(45) NOT NULL COMMENT '登录IP',
                                 `last_access_time` DATETIME NOT NULL COMMENT '最后访问时间',
                                 `expires_at` DATETIME NOT NULL COMMENT '过期时间',
                                 `is_active` BOOLEAN DEFAULT TRUE COMMENT '是否活跃',
                                 `device_id` VARCHAR(100) DEFAULT NULL COMMENT '设备ID',
                                 `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 PRIMARY KEY (`id`),
                                 KEY `idx_user_id` (`user_id`),
                                 KEY `idx_expires_at` (`expires_at`),
                                 KEY `idx_is_active` (`is_active`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户会话表';

