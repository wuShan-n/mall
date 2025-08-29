-- 5.1 促销活动表设计
-- 营销系统需要支持灵活的促销规则配置和活动管理。

-- 促销活动表
CREATE TABLE `promotion_activities` (
                                        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                        `activity_name` VARCHAR(100) NOT NULL COMMENT '活动名称',
                                        `activity_type` VARCHAR(30) NOT NULL COMMENT '活动类型：DISCOUNT/FULL_REDUCTION/GIFT/SECKILL',
                                        `description` VARCHAR(500) DEFAULT NULL COMMENT '活动描述',
                                        `start_time` DATETIME NOT NULL COMMENT '开始时间',
                                        `end_time` DATETIME NOT NULL COMMENT '结束时间',
                                        `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用，2-已结束',

    -- 活动规则配置（JSON格式存储灵活的规则）
                                        `rules` JSON NOT NULL COMMENT '活动规则配置',
                                        `conditions` JSON DEFAULT NULL COMMENT '参与条件',

    -- 活动限制
                                        `user_limit` INT DEFAULT NULL COMMENT '每用户限制次数',
                                        `total_limit` INT DEFAULT NULL COMMENT '活动总限制次数',
                                        `used_count` INT DEFAULT 0 COMMENT '已使用次数',

    -- 适用范围
                                        `scope_type` TINYINT DEFAULT 1 COMMENT '适用范围：1-全场，2-指定分类，3-指定商品',
                                        `scope_values` JSON DEFAULT NULL COMMENT '适用范围值',

    -- 优先级和互斥
                                        `priority` INT DEFAULT 0 COMMENT '优先级（数值越大优先级越高）',
                                        `is_exclusive` BOOLEAN DEFAULT FALSE COMMENT '是否互斥',
                                        `exclusive_activities` JSON DEFAULT NULL COMMENT '互斥活动ID列表',

                                        `created_by` BIGINT DEFAULT NULL COMMENT '创建人',
                                        `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                        PRIMARY KEY (`id`),
                                        KEY `idx_activity_type` (`activity_type`),
                                        KEY `idx_status` (`status`),
                                        KEY `idx_time` (`start_time`, `end_time`),
                                        KEY `idx_priority` (`priority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='促销活动表';

-- 优惠券模板表
CREATE TABLE `coupon_templates` (
                                    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                    `template_name` VARCHAR(100) NOT NULL COMMENT '模板名称',
                                    `coupon_type` TINYINT NOT NULL COMMENT '优惠券类型：1-满减券，2-折扣券，3-现金券',
                                    `face_value` DECIMAL(10,2) DEFAULT NULL COMMENT '面值（现金券和满减券）',
                                    `discount` DECIMAL(3,2) DEFAULT NULL COMMENT '折扣（折扣券，如0.8表示8折）',
                                    `threshold` DECIMAL(10,2) DEFAULT 0.00 COMMENT '使用门槛',
                                    `max_discount` DECIMAL(10,2) DEFAULT NULL COMMENT '最大优惠金额（折扣券）',

    -- 发放配置
                                    `issue_type` TINYINT DEFAULT 1 COMMENT '发放方式：1-主动领取，2-自动发放，3-活动发放',
                                    `total_quantity` INT DEFAULT NULL COMMENT '发放总量',
                                    `issued_quantity` INT DEFAULT 0 COMMENT '已发放数量',
                                    `per_user_limit` INT DEFAULT 1 COMMENT '每人限领数量',

    -- 使用规则
                                    `valid_type` TINYINT DEFAULT 1 COMMENT '有效期类型：1-固定时间，2-领取后N天',
                                    `valid_start_time` DATETIME DEFAULT NULL COMMENT '有效开始时间',
                                    `valid_end_time` DATETIME DEFAULT NULL COMMENT '有效结束时间',
                                    `valid_days` INT DEFAULT NULL COMMENT '领取后有效天数',

    -- 适用范围
                                    `scope_type` TINYINT DEFAULT 1 COMMENT '适用范围：1-全场，2-指定分类，3-指定商品',
                                    `scope_values` JSON DEFAULT NULL COMMENT '适用范围值',

                                    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
                                    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                    PRIMARY KEY (`id`),
                                    KEY `idx_coupon_type` (`coupon_type`),
                                    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券模板表';

-- 用户优惠券表
CREATE TABLE `user_coupons` (
                                `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                `template_id` BIGINT UNSIGNED NOT NULL COMMENT '优惠券模板ID',
                                `coupon_code` VARCHAR(50) NOT NULL COMMENT '优惠券码',
                                `status` TINYINT DEFAULT 0 COMMENT '状态：0-未使用，1-已使用，2-已过期，3-已锁定',

    -- 优惠券信息快照
                                `coupon_name` VARCHAR(100) NOT NULL COMMENT '优惠券名称',
                                `coupon_type` TINYINT NOT NULL COMMENT '优惠券类型',
                                `face_value` DECIMAL(10,2) DEFAULT NULL COMMENT '面值',
                                `discount` DECIMAL(3,2) DEFAULT NULL COMMENT '折扣',
                                `threshold` DECIMAL(10,2) DEFAULT 0.00 COMMENT '使用门槛',

    -- 时间信息
                                `received_time` DATETIME NOT NULL COMMENT '领取时间',
                                `valid_start_time` DATETIME NOT NULL COMMENT '有效开始时间',
                                `valid_end_time` DATETIME NOT NULL COMMENT '有效结束时间',
                                `used_time` DATETIME DEFAULT NULL COMMENT '使用时间',

    -- 使用信息
                                `order_no` VARCHAR(32) DEFAULT NULL COMMENT '使用订单号',
                                `deduct_amount` DECIMAL(10,2) DEFAULT NULL COMMENT '抵扣金额',

                                `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_coupon_code` (`coupon_code`),
                                KEY `idx_user_id` (`user_id`),
                                KEY `idx_template_id` (`template_id`),
                                KEY `idx_status` (`status`),
                                KEY `idx_valid_time` (`valid_start_time`, `valid_end_time`),
                                CONSTRAINT `fk_user_coupons_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                                CONSTRAINT `fk_user_coupons_template` FOREIGN KEY (`template_id`) REFERENCES `coupon_templates` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

-- 秒杀活动表
CREATE TABLE `seckill_activities` (
                                      `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                      `activity_name` VARCHAR(100) NOT NULL COMMENT '活动名称',
                                      `start_time` DATETIME NOT NULL COMMENT '开始时间',
                                      `end_time` DATETIME NOT NULL COMMENT '结束时间',
                                      `status` TINYINT DEFAULT 0 COMMENT '状态：0-未开始，1-进行中，2-已结束',
                                      `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                      PRIMARY KEY (`id`),
                                      KEY `idx_status` (`status`),
                                      KEY `idx_time` (`start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀活动表';

-- 秒杀商品表
CREATE TABLE `seckill_products` (
                                    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                    `activity_id` BIGINT UNSIGNED NOT NULL COMMENT '活动ID',
                                    `product_id` BIGINT UNSIGNED NOT NULL COMMENT '商品ID',
                                    `sku_id` BIGINT UNSIGNED NOT NULL COMMENT 'SKU ID',
                                    `seckill_price` DECIMAL(10,2) NOT NULL COMMENT '秒杀价',
                                    `seckill_stock` INT NOT NULL COMMENT '秒杀库存',
                                    `sold_quantity` INT DEFAULT 0 COMMENT '已售数量',
                                    `per_user_limit` INT DEFAULT 1 COMMENT '每人限购数量',
                                    `sort_order` INT DEFAULT 0 COMMENT '排序',
                                    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                    PRIMARY KEY (`id`),
                                    KEY `idx_activity_id` (`activity_id`),
                                    KEY `idx_sku_id` (`sku_id`),
                                    CONSTRAINT `fk_seckill_products_activity` FOREIGN KEY (`activity_id`) REFERENCES `seckill_activities` (`id`),
                                    CONSTRAINT `fk_seckill_products_sku` FOREIGN KEY (`sku_id`) REFERENCES `product_skus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品表';
