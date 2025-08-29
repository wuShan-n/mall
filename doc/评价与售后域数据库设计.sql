-- 6.1 商品评价表设计
-- 评价系统不仅影响其他用户的购买决策，也是商品质量的重要反馈渠道。

-- 商品评价表
CREATE TABLE `product_reviews` (
                                   `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                   `order_id` BIGINT UNSIGNED NOT NULL COMMENT '订单ID',
                                   `order_item_id` BIGINT UNSIGNED NOT NULL COMMENT '订单项ID',
                                   `product_id` BIGINT UNSIGNED NOT NULL COMMENT '商品ID',
                                   `sku_id` BIGINT UNSIGNED NOT NULL COMMENT 'SKU ID',
                                   `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',

    -- 评价内容
                                   `rating` TINYINT NOT NULL COMMENT '评分：1-5星',
                                   `content` TEXT DEFAULT NULL COMMENT '评价内容',
                                   `images` JSON DEFAULT NULL COMMENT '评价图片列表',
                                   `video_url` VARCHAR(500) DEFAULT NULL COMMENT '评价视频',

    -- 评价维度（可选）
                                   `quality_rating` TINYINT DEFAULT NULL COMMENT '质量评分',
                                   `service_rating` TINYINT DEFAULT NULL COMMENT '服务评分',
                                   `logistics_rating` TINYINT DEFAULT NULL COMMENT '物流评分',

    -- 标签和特性
                                   `tags` JSON DEFAULT NULL COMMENT '评价标签',
                                   `is_anonymous` BOOLEAN DEFAULT FALSE COMMENT '是否匿名',
                                   `is_auto` BOOLEAN DEFAULT FALSE COMMENT '是否自动评价',
                                   `is_append` BOOLEAN DEFAULT FALSE COMMENT '是否追加评价',
                                   `parent_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '原评价ID（追评时）',

    -- 互动统计
                                   `helpful_count` INT DEFAULT 0 COMMENT '有用数',
                                   `reply_count` INT DEFAULT 0 COMMENT '回复数',

    -- 审核信息
                                   `audit_status` TINYINT DEFAULT 0 COMMENT '审核状态：0-待审核，1-通过，2-不通过',
                                   `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
                                   `audit_remark` VARCHAR(255) DEFAULT NULL COMMENT '审核备注',

                                   `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                   PRIMARY KEY (`id`),
                                   KEY `idx_product_id` (`product_id`),
                                   KEY `idx_sku_id` (`sku_id`),
                                   KEY `idx_user_id` (`user_id`),
                                   KEY `idx_rating` (`rating`),
                                   KEY `idx_audit_status` (`audit_status`),
                                   KEY `idx_created_at` (`created_at`),
                                   CONSTRAINT `fk_reviews_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
                                   CONSTRAINT `fk_reviews_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
                                   CONSTRAINT `fk_reviews_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品评价表';

-- 评价回复表
CREATE TABLE `review_replies` (
                                  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `review_id` BIGINT UNSIGNED NOT NULL COMMENT '评价ID',
                                  `reply_type` TINYINT NOT NULL COMMENT '回复类型：1-商家回复，2-用户回复',
                                  `replier_id` BIGINT UNSIGNED NOT NULL COMMENT '回复人ID',
                                  `replier_name` VARCHAR(50) NOT NULL COMMENT '回复人名称',
                                  `content` TEXT NOT NULL COMMENT '回复内容',
                                  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                  PRIMARY KEY (`id`),
                                  KEY `idx_review_id` (`review_id`),
                                  KEY `idx_replier_id` (`replier_id`),
                                  CONSTRAINT `fk_review_replies_review` FOREIGN KEY (`review_id`) REFERENCES `product_reviews` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评价回复表';

-- 6.2 售后服务表设计
--
-- 售后服务是电商系统中处理退换货的核心模块，需要记录完整的售后流程。


-- 售后申请表
CREATE TABLE `after_sales` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `service_no` VARCHAR(32) NOT NULL COMMENT '售后单号',
    `order_id` BIGINT UNSIGNED NOT NULL COMMENT '订单ID',
    `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
    `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',

    -- 售后类型和原因
    `service_type` TINYINT NOT NULL COMMENT '售后类型：1-仅退款，2-退货退款，3-换货',
    `reason_type` VARCHAR(50) NOT NULL COMMENT '原因类型：质量问题/不喜欢/尺码不合等',
    `reason_desc` VARCHAR(500) DEFAULT NULL COMMENT '原因描述',
    `proof_images` JSON DEFAULT NULL COMMENT '凭证图片',

    -- 状态管理
    `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-待审核，1-审核通过，2-审核拒绝，3-待寄回，4-待收货，5-已完成，6-已取消',
    `refund_status` TINYINT DEFAULT 0 COMMENT '退款状态：0-未退款，1-退款中，2-退款成功，3-退款失败',

    -- 金额信息
    `refund_amount` DECIMAL(10,2) NOT NULL COMMENT '申请退款金额',
    `actual_refund_amount` DECIMAL(10,2) DEFAULT NULL COMMENT '实际退款金额',

    -- 物流信息（退货需要）
    `return_logistics_company` VARCHAR(50) DEFAULT NULL COMMENT '退货快递公司',
    `return_logistics_no` VARCHAR(50) DEFAULT NULL COMMENT '退货快递单号',
    `return_address` VARCHAR(500) DEFAULT NULL COMMENT '退货地址',

    -- 处理信息
    `handler_id` BIGINT DEFAULT NULL COMMENT '处理人ID',
    `handler_name` VARCHAR(50) DEFAULT NULL COMMENT '处理人姓名',
    `handle_time` DATETIME DEFAULT NULL COMMENT '处理时间',
    `handle_remark` VARCHAR(500) DEFAULT NULL COMMENT '处理备注',

    -- 时间节点
    `apply_time` DATETIME NOT NULL COMMENT '申请时间',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `return_time` DATETIME DEFAULT NULL COMMENT '退货时间',
    `receive_time` DATETIME DEFAULT NULL COMMENT '收货时间',
    `complete_time` DATETIME DEFAULT NULL COMMENT '完成时间',

    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_service_no` (`service_no`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`),
    KEY `idx_apply_time` (`apply_time`),
    CONSTRAINT `fk_after_sales_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
    CONSTRAINT `fk_after_sales_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='售后申请表';

-- 售后商品明细表
CREATE TABLE `after_sales_items` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `after_sales_id` BIGINT UNSIGNED NOT NULL COMMENT '售后ID',
    `order_item_id` BIGINT UNSIGNED NOT NULL COMMENT '订单项ID',
    `product_id` BIGINT UNSIGNED NOT NULL COMMENT '商品ID',
    `sku_id` BIGINT UNSIGNED NOT NULL COMMENT 'SKU ID',
    `quantity` INT NOT NULL COMMENT '售后数量',
    `refund_amount` DECIMAL(10,2) NOT NULL COMMENT '退款金额',

    PRIMARY KEY (`id`),
    KEY `idx_after_sales_id` (`after_sales_id`),
    KEY `idx_order_item_id` (`order_item_id`),
    CONSTRAINT `fk_after_sales_items_as` FOREIGN KEY (`after_sales_id`) REFERENCES `after_sales` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='售后商品明细表';

-- 售后日志表
CREATE TABLE `after_sales_logs` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `after_sales_id` BIGINT UNSIGNED NOT NULL COMMENT '售后ID',
    `operation` VARCHAR(50) NOT NULL COMMENT '操作类型',
    `operator_type` VARCHAR(20) NOT NULL COMMENT '操作人类型：USER/ADMIN/SYSTEM',
    `operator_id` BIGINT DEFAULT NULL COMMENT '操作人ID',
    `operator_name` VARCHAR(50) DEFAULT NULL COMMENT '操作人姓名',
    `content` VARCHAR(500) DEFAULT NULL COMMENT '操作内容',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (`id`),
    KEY `idx_after_sales_id` (`after_sales_id`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='售后操作日志表';
