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

-- 插入测试用户数据
INSERT INTO `users` (`username`, `email`, `mobile`, `password`, `status`, `user_type`, `register_source`)
VALUES
('admin', 'admin@example.com', '13800138000', 'c33367701511b4f6020ec61ded352059', 1, 3, 'WEB'),
('test', 'test@example.com', '13800138001', 'c33367701511b4f6020ec61ded352059', 1, 1, 'WEB');

-- 插入对应的用户详情
INSERT INTO `user_profiles` (`user_id`, `nickname`, `gender`, `country`)
VALUES
(1, '管理员', 1, 'China'),
(2, '测试用户', 1, 'China');
