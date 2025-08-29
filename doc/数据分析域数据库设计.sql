-- 8.1 用户行为分析表
-- 用户行为数据是个性化推荐和运营决策的重要基础。

-- 用户行为日志表（可考虑使用时序数据库）
CREATE TABLE `user_behavior_logs` (
                                      `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                      `user_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '用户ID（游客为NULL）',
                                      `session_id` VARCHAR(100) NOT NULL COMMENT '会话ID',
                                      `behavior_type` VARCHAR(30) NOT NULL COMMENT '行为类型：VIEW/CLICK/CART/ORDER/SEARCH',
                                      `target_type` VARCHAR(30) NOT NULL COMMENT '目标类型：PRODUCT/CATEGORY/PROMOTION',
                                      `target_id` BIGINT NOT NULL COMMENT '目标ID',
                                      `target_detail` JSON DEFAULT NULL COMMENT '目标详情',
                                      `duration` INT DEFAULT NULL COMMENT '停留时长（秒）',
                                      `device_type` VARCHAR(20) DEFAULT NULL COMMENT '设备类型',
                                      `os` VARCHAR(50) DEFAULT NULL COMMENT '操作系统',
                                      `browser` VARCHAR(50) DEFAULT NULL COMMENT '浏览器',
                                      `ip_address` VARCHAR(45) DEFAULT NULL COMMENT 'IP地址',
                                      `location` VARCHAR(100) DEFAULT NULL COMMENT '地理位置',
                                      `referer` VARCHAR(500) DEFAULT NULL COMMENT '来源页面',
                                      `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                      PRIMARY KEY (`id`),
                                      KEY `idx_user_id` (`user_id`),
                                      KEY `idx_session_id` (`session_id`),
                                      KEY `idx_behavior_type` (`behavior_type`),
                                      KEY `idx_target` (`target_type`, `target_id`),
                                      KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户行为日志表'
PARTITION BY RANGE (TO_DAYS(created_at)) (
    PARTITION p202401 VALUES LESS THAN (TO_DAYS('2024-02-01')),
    PARTITION p202402 VALUES LESS THAN (TO_DAYS('2024-03-01')),
    PARTITION p202403 VALUES LESS THAN (TO_DAYS('2024-04-01'))
);

-- 搜索记录表
CREATE TABLE `search_logs` (
                               `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                               `user_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '用户ID',
                               `keyword` VARCHAR(200) NOT NULL COMMENT '搜索关键词',
                               `result_count` INT DEFAULT 0 COMMENT '结果数量',
                               `click_position` INT DEFAULT NULL COMMENT '点击位置',
                               `click_product_id` BIGINT DEFAULT NULL COMMENT '点击的商品ID',
                               `search_source` VARCHAR(30) DEFAULT NULL COMMENT '搜索来源',
                               `ip_address` VARCHAR(45) DEFAULT NULL COMMENT 'IP地址',
                               `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                               PRIMARY KEY (`id`),
                               KEY `idx_user_id` (`user_id`),
                               KEY `idx_keyword` (`keyword`),
                               KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='搜索记录表';

-- 用户画像表
CREATE TABLE `user_profiles_analysis` (
                                          `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                          `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                          `age_group` VARCHAR(20) DEFAULT NULL COMMENT '年龄段',
                                          `gender` TINYINT DEFAULT NULL COMMENT '性别',
                                          `consumption_level` VARCHAR(20) DEFAULT NULL COMMENT '消费水平：HIGH/MEDIUM/LOW',
                                          `preferred_categories` JSON DEFAULT NULL COMMENT '偏好分类',
                                          `preferred_brands` JSON DEFAULT NULL COMMENT '偏好品牌',
                                          `purchase_frequency` DECIMAL(5,2) DEFAULT NULL COMMENT '购买频率（次/月）',
                                          `avg_order_value` DECIMAL(10,2) DEFAULT NULL COMMENT '平均客单价',
                                          `total_orders` INT DEFAULT 0 COMMENT '总订单数',
                                          `total_spent` DECIMAL(12,2) DEFAULT 0.00 COMMENT '总消费金额',
                                          `last_purchase_time` DATETIME DEFAULT NULL COMMENT '最后购买时间',
                                          `rfm_score` VARCHAR(10) DEFAULT NULL COMMENT 'RFM分值',
                                          `churn_probability` DECIMAL(3,2) DEFAULT NULL COMMENT '流失概率',
                                          `tags` JSON DEFAULT NULL COMMENT '用户标签',
                                          `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                          PRIMARY KEY (`id`),
                                          UNIQUE KEY `uk_user_id` (`user_id`),
                                          KEY `idx_consumption_level` (`consumption_level`),
                                          KEY `idx_rfm_score` (`rfm_score`),
                                          CONSTRAINT `fk_user_profiles_analysis_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户画像分析表';
