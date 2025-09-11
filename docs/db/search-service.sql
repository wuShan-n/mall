-- 创建数据库
CREATE DATABASE IF NOT EXISTS `mall_search` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `mall_search`;

-- 搜索历史表
DROP TABLE IF EXISTS `search_history`;
CREATE TABLE `search_history` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `user_id` bigint NOT NULL COMMENT '用户ID',
                                  `keyword` varchar(100) NOT NULL COMMENT '搜索关键词',
                                  `search_type` varchar(20) DEFAULT 'product' COMMENT '搜索类型: product-商品/store-店铺/brand-品牌',
                                  `result_count` int DEFAULT 0 COMMENT '搜索结果数量',
                                  `click_count` int DEFAULT 0 COMMENT '点击次数',
                                  `search_source` varchar(20) DEFAULT NULL COMMENT '搜索来源: WEB/APP/MINI_PROGRAM',
                                  `device_type` varchar(50) DEFAULT NULL COMMENT '设备类型',
                                  `ip_address` varchar(50) DEFAULT NULL COMMENT 'IP地址',
                                  `session_id` varchar(100) DEFAULT NULL COMMENT '会话ID',
                                  `tracking_id` varchar(100) DEFAULT NULL COMMENT '搜索跟踪ID',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `deleted` tinyint DEFAULT 0 COMMENT '删除标志: 0-否, 1-是',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_user_id` (`user_id`),
                                  KEY `idx_keyword` (`keyword`),
                                  KEY `idx_create_time` (`create_time`),
                                  KEY `idx_tracking_id` (`tracking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='搜索历史表';

-- 热门关键词表
DROP TABLE IF EXISTS `hot_keyword`;
CREATE TABLE `hot_keyword` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `keyword` varchar(100) NOT NULL COMMENT '关键词',
                               `category_id` bigint DEFAULT NULL COMMENT '分类ID',
                               `category_name` varchar(50) DEFAULT NULL COMMENT '分类名称',
                               `search_count` bigint DEFAULT 0 COMMENT '搜索次数',
                               `click_count` bigint DEFAULT 0 COMMENT '点击次数',
                               `conversion_count` bigint DEFAULT 0 COMMENT '转化次数',
                               `rank` int DEFAULT NULL COMMENT '当前排名',
                               `previous_rank` int DEFAULT NULL COMMENT '上一次排名',
                               `is_hot` tinyint DEFAULT 0 COMMENT '是否热门: 0-否, 1-是',
                               `is_new` tinyint DEFAULT 0 COMMENT '是否新品: 0-否, 1-是',
                               `icon` varchar(10) DEFAULT NULL COMMENT '图标',
                               `color` varchar(10) DEFAULT NULL COMMENT '显示颜色',
                               `status` tinyint DEFAULT 1 COMMENT '状态: 0-禁用, 1-启用',
                               `statistics_date` date DEFAULT NULL COMMENT '统计日期',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `deleted` tinyint DEFAULT 0 COMMENT '删除标志: 0-否, 1-是',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_keyword_category_date` (`keyword`, `category_id`, `statistics_date`),
                               KEY `idx_category_id` (`category_id`),
                               KEY `idx_search_count` (`search_count`),
                               KEY `idx_statistics_date` (`statistics_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='热门关键词表';

-- 搜索点击跟踪表
DROP TABLE IF EXISTS `search_click_tracking`;
CREATE TABLE `search_click_tracking` (
                                         `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                         `tracking_id` varchar(100) NOT NULL COMMENT '搜索跟踪ID',
                                         `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                                         `keyword` varchar(100) DEFAULT NULL COMMENT '搜索关键词',
                                         `product_id` bigint NOT NULL COMMENT '商品ID',
                                         `position` int NOT NULL COMMENT '在搜索结果中的位置',
                                         `page` int DEFAULT 1 COMMENT '页码',
                                         `click_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点击时间',
                                         `session_id` varchar(100) DEFAULT NULL COMMENT '会话ID',
                                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         PRIMARY KEY (`id`),
                                         KEY `idx_tracking_id` (`tracking_id`),
                                         KEY `idx_user_id` (`user_id`),
                                         KEY `idx_product_id` (`product_id`),
                                         KEY `idx_click_time` (`click_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='搜索点击跟踪表';

-- 搜索转化跟踪表
DROP TABLE IF EXISTS `search_conversion_tracking`;
CREATE TABLE `search_conversion_tracking` (
                                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                              `tracking_id` varchar(100) NOT NULL COMMENT '搜索跟踪ID',
                                              `user_id` bigint DEFAULT NULL COMMENT '用户ID',
                                              `order_id` bigint NOT NULL COMMENT '订单ID',
                                              `order_amount` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
                                              `product_count` int DEFAULT NULL COMMENT '商品数量',
                                              `conversion_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '转化时间',
                                              `session_id` varchar(100) DEFAULT NULL COMMENT '会话ID',
                                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                              PRIMARY KEY (`id`),
                                              KEY `idx_tracking_id` (`tracking_id`),
                                              KEY `idx_user_id` (`user_id`),
                                              KEY `idx_order_id` (`order_id`),
                                              KEY `idx_conversion_time` (`conversion_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='搜索转化跟踪表';

-- 搜索统计表
DROP TABLE IF EXISTS `search_statistics`;
CREATE TABLE `search_statistics` (
                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `statistics_date` date NOT NULL COMMENT '统计日期',
                                     `total_searches` bigint DEFAULT 0 COMMENT '总搜索次数',
                                     `unique_users` bigint DEFAULT 0 COMMENT '独立用户数',
                                     `no_result_searches` bigint DEFAULT 0 COMMENT '无结果搜索次数',
                                     `click_count` bigint DEFAULT 0 COMMENT '总点击次数',
                                     `conversion_count` bigint DEFAULT 0 COMMENT '总转化次数',
                                     `click_rate` decimal(5,2) DEFAULT 0.00 COMMENT '点击率',
                                     `conversion_rate` decimal(5,2) DEFAULT 0.00 COMMENT '转化率',
                                     `avg_search_depth` decimal(5,2) DEFAULT 0.00 COMMENT '平均搜索深度',
                                     `top_keywords` json DEFAULT NULL COMMENT '热门关键词JSON',
                                     `top_no_result_keywords` json DEFAULT NULL COMMENT '无结果关键词JSON',
                                     `hourly_distribution` json DEFAULT NULL COMMENT '小时分布JSON',
                                     `device_distribution` json DEFAULT NULL COMMENT '设备分布JSON',
                                     `source_distribution` json DEFAULT NULL COMMENT '来源分布JSON',
                                     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `uk_statistics_date` (`statistics_date`),
                                     KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='搜索统计表';

-- 同义词词典表
DROP TABLE IF EXISTS `search_synonym`;
CREATE TABLE `search_synonym` (
                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `word` varchar(50) NOT NULL COMMENT '原始词',
                                  `synonym` varchar(50) NOT NULL COMMENT '同义词',
                                  `status` tinyint DEFAULT 1 COMMENT '状态: 0-禁用, 1-启用',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `deleted` tinyint DEFAULT 0 COMMENT '删除标志: 0-否, 1-是',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_word` (`word`),
                                  KEY `idx_synonym` (`synonym`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='搜索同义词词典';

-- 停用词词典表
DROP TABLE IF EXISTS `search_stopword`;
CREATE TABLE `search_stopword` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `word` varchar(50) NOT NULL COMMENT '停用词',
                                   `status` tinyint DEFAULT 1 COMMENT '状态: 0-禁用, 1-启用',
                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `deleted` tinyint DEFAULT 0 COMMENT '删除标志: 0-否, 1-是',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `uk_word` (`word`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='搜索停用词词典';

-- 插入热门关键词示例数据
INSERT INTO `hot_keyword` (`keyword`, `category_id`, `category_name`, `search_count`, `click_count`, `rank`, `is_hot`, `statistics_date`) VALUES
                                                                                                                                              ('iPhone 15', 1, '智能手机', 10000, 8000, 1, 1, CURDATE()),
                                                                                                                                              ('三星 Galaxy', 1, '智能手机', 8000, 6000, 2, 1, CURDATE()),
                                                                                                                                              ('MacBook Pro', 2, '笔记本电脑', 7000, 5500, 3, 1, CURDATE()),
                                                                                                                                              ('AirPods', 3, '耳机', 6000, 4800, 4, 1, CURDATE()),
                                                                                                                                              ('iPad', 4, '平板电脑', 5000, 4000, 5, 0, CURDATE()),
                                                                                                                                              ('Apple Watch', 5, '智能手表', 4500, 3600, 6, 0, CURDATE()),
                                                                                                                                              ('索尼 WH-1000XM5', 3, '耳机', 4000, 3200, 7, 0, CURDATE()),
                                                                                                                                              ('戴尔 XPS', 2, '笔记本电脑', 3500, 2800, 8, 0, CURDATE()),
                                                                                                                                              ('任天堂 Switch', 6, '游戏机', 3000, 2400, 9, 0, CURDATE()),
                                                                                                                                              ('Kindle', 7, '电子阅读器', 2500, 2000, 10, 0, CURDATE());

-- 插入同义词示例数据
INSERT INTO `search_synonym` (`word`, `synonym`) VALUES
                                                     ('手机', '智能手机'),
                                                     ('手机', '移动电话'),
                                                     ('笔记本', '手提电脑'),
                                                     ('笔记本', '电脑'),
                                                     ('耳机', '耳麦'),
                                                     ('耳机', '头戴式耳机'),
                                                     ('电视', '电视机'),
                                                     ('冰箱', '电冰箱'),
                                                     ('空调', '空气调节器'),
                                                     ('洗衣机', '洗衣设备');

-- 插入停用词示例数据
INSERT INTO `search_stopword` (`word`) VALUES
                                           ('的'),
                                           ('一个'),
                                           ('一件'),
                                           ('和'),
                                           ('或'),
                                           ('但是'),
                                           ('在'),
                                           ('上'),
                                           ('于'),
                                           ('到'),
                                           ('为'),
                                           ('之'),
                                           ('与'),
                                           ('通过'),
                                           ('从'),
                                           ('是'),
                                           ('也是'),
                                           ('曾是'),
                                           ('曾经是'),
                                           ('曾经');