-- 4.1 订单核心表设计
-- 订单系统是电商的心脏，它记录了每一笔交易的完整生命周期。订单表的设计需要考虑数据的完整性、查询性能和未来的扩展性。

-- 订单主表
CREATE TABLE `orders` (
                          `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                          `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                          `order_type` TINYINT DEFAULT 1 COMMENT '订单类型：1-普通订单，2-秒杀订单，3-团购订单',
                          `status` TINYINT NOT NULL DEFAULT 0 COMMENT '订单状态：0-待付款，1-待发货，2-待收货，3-已完成，4-已取消，5-售后中',
                          `payment_status` TINYINT DEFAULT 0 COMMENT '支付状态：0-未支付，1-已支付，2-已退款',
                          `shipping_status` TINYINT DEFAULT 0 COMMENT '发货状态：0-未发货，1-部分发货，2-已发货，3-已收货',
                          `refund_status` TINYINT DEFAULT 0 COMMENT '退款状态：0-未退款，1-部分退款，2-全部退款',

    -- 金额相关字段（使用DECIMAL保证精度）
                          `product_amount` DECIMAL(10,2) NOT NULL COMMENT '商品总金额',
                          `shipping_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '运费',
                          `discount_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '优惠金额',
                          `coupon_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '优惠券抵扣金额',
                          `points_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '积分抵扣金额',
                          `pay_amount` DECIMAL(10,2) NOT NULL COMMENT '应付金额',
                          `paid_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '实付金额',

    -- 收货信息（冗余存储，防止地址修改影响历史订单）
                          `receiver_name` VARCHAR(50) NOT NULL COMMENT '收件人姓名',
                          `receiver_mobile` VARCHAR(20) NOT NULL COMMENT '收件人电话',
                          `receiver_province` VARCHAR(50) NOT NULL COMMENT '省份',
                          `receiver_city` VARCHAR(50) NOT NULL COMMENT '城市',
                          `receiver_district` VARCHAR(50) DEFAULT NULL COMMENT '区县',
                          `receiver_address` VARCHAR(255) NOT NULL COMMENT '详细地址',
                          `receiver_zip` VARCHAR(20) DEFAULT NULL COMMENT '邮编',

    -- 支付信息
                          `payment_method` VARCHAR(30) DEFAULT NULL COMMENT '支付方式：ALIPAY/WECHAT/CARD',
                          `payment_time` DATETIME DEFAULT NULL COMMENT '支付时间',
                          `payment_no` VARCHAR(64) DEFAULT NULL COMMENT '支付流水号',

    -- 发货信息
                          `shipping_method` VARCHAR(30) DEFAULT NULL COMMENT '配送方式',
                          `shipping_company` VARCHAR(50) DEFAULT NULL COMMENT '快递公司',
                          `shipping_no` VARCHAR(50) DEFAULT NULL COMMENT '快递单号',
                          `shipping_time` DATETIME DEFAULT NULL COMMENT '发货时间',
                          `receive_time` DATETIME DEFAULT NULL COMMENT '收货时间',

    -- 其他信息
                          `source` VARCHAR(20) DEFAULT 'PC' COMMENT '订单来源：PC/H5/APP/MINI',
                          `user_remark` VARCHAR(500) DEFAULT NULL COMMENT '用户备注',
                          `merchant_remark` VARCHAR(500) DEFAULT NULL COMMENT '商家备注',
                          `cancel_reason` VARCHAR(255) DEFAULT NULL COMMENT '取消原因',
                          `cancel_time` DATETIME DEFAULT NULL COMMENT '取消时间',
                          `auto_confirm_days` INT DEFAULT 7 COMMENT '自动确认收货天数',
                          `points_earned` INT DEFAULT 0 COMMENT '获得积分',

    -- 时间戳
                          `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          `deleted_at` DATETIME DEFAULT NULL COMMENT '软删除时间',

                          PRIMARY KEY (`id`),
                          UNIQUE KEY `uk_order_no` (`order_no`),
                          KEY `idx_user_id` (`user_id`),
                          KEY `idx_status` (`status`),
                          KEY `idx_payment_status` (`payment_status`),
                          KEY `idx_created_at` (`created_at`),
                          KEY `idx_payment_time` (`payment_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单主表';

-- 订单商品表
CREATE TABLE `order_items` (
                               `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                               `order_id` BIGINT UNSIGNED NOT NULL COMMENT '订单ID',
                               `order_no` VARCHAR(32) NOT NULL COMMENT '订单号（冗余）',
                               `product_id` BIGINT UNSIGNED NOT NULL COMMENT '商品ID',
                               `sku_id` BIGINT UNSIGNED NOT NULL COMMENT 'SKU ID',

    -- 商品信息快照（防止商品信息变更影响历史订单）
                               `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
                               `sku_name` VARCHAR(200) DEFAULT NULL COMMENT 'SKU名称',
                               `product_image` VARCHAR(500) DEFAULT NULL COMMENT '商品图片',
                               `sku_spec` JSON DEFAULT NULL COMMENT 'SKU规格',

    -- 价格和数量
                               `price` DECIMAL(10,2) NOT NULL COMMENT '单价',
                               `quantity` INT NOT NULL COMMENT '数量',
                               `total_amount` DECIMAL(10,2) NOT NULL COMMENT '小计金额',
                               `discount_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '优惠金额',
                               `pay_amount` DECIMAL(10,2) NOT NULL COMMENT '实付金额',

    -- 售后信息
                               `refund_status` TINYINT DEFAULT 0 COMMENT '退款状态：0-未退款，1-退款中，2-已退款',
                               `refund_quantity` INT DEFAULT 0 COMMENT '退款数量',
                               `refund_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '退款金额',

    -- 评价信息
                               `is_reviewed` BOOLEAN DEFAULT FALSE COMMENT '是否已评价',
                               `review_time` DATETIME DEFAULT NULL COMMENT '评价时间',

                               `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                               PRIMARY KEY (`id`),
                               KEY `idx_order_id` (`order_id`),
                               KEY `idx_order_no` (`order_no`),
                               KEY `idx_product_id` (`product_id`),
                               KEY `idx_sku_id` (`sku_id`),
                               CONSTRAINT `fk_order_items_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品表';

-- 订单状态变更日志表
CREATE TABLE `order_status_logs` (
                                     `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                     `order_id` BIGINT UNSIGNED NOT NULL COMMENT '订单ID',
                                     `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                                     `from_status` TINYINT DEFAULT NULL COMMENT '原状态',
                                     `to_status` TINYINT NOT NULL COMMENT '新状态',
                                     `operator_type` VARCHAR(20) NOT NULL COMMENT '操作人类型：USER/ADMIN/SYSTEM',
                                     `operator_id` BIGINT DEFAULT NULL COMMENT '操作人ID',
                                     `operator_name` VARCHAR(50) DEFAULT NULL COMMENT '操作人姓名',
                                     `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
                                     `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                     PRIMARY KEY (`id`),
                                     KEY `idx_order_id` (`order_id`),
                                     KEY `idx_order_no` (`order_no`),
                                     KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单状态日志表';

-- 订单操作日志表（记录所有订单相关操作）
CREATE TABLE `order_operation_logs` (
                                        `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                        `order_id` BIGINT UNSIGNED NOT NULL COMMENT '订单ID',
                                        `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                                        `operation` VARCHAR(50) NOT NULL COMMENT '操作类型',
                                        `operator_type` VARCHAR(20) NOT NULL COMMENT '操作人类型',
                                        `operator_id` BIGINT DEFAULT NULL COMMENT '操作人ID',
                                        `operator_name` VARCHAR(50) DEFAULT NULL COMMENT '操作人姓名',
                                        `content` TEXT DEFAULT NULL COMMENT '操作内容（JSON）',
                                        `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                        PRIMARY KEY (`id`),
                                        KEY `idx_order_id` (`order_id`),
                                        KEY `idx_order_no` (`order_no`),
                                        KEY `idx_operation` (`operation`),
                                        KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单操作日志表';

-- 4.2 支付相关表设计
-- 支付系统需要记录每一笔资金流动，确保账目清晰可查。

-- 支付记录表
CREATE TABLE `payment_records` (
                                   `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                   `payment_no` VARCHAR(64) NOT NULL COMMENT '支付单号',
                                   `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                                   `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                   `amount` DECIMAL(10,2) NOT NULL COMMENT '支付金额',
                                   `payment_method` VARCHAR(30) NOT NULL COMMENT '支付方式',
                                   `payment_channel` VARCHAR(30) DEFAULT NULL COMMENT '支付渠道：ALIPAY_PC/WECHAT_NATIVE等',
                                   `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-待支付，1-支付中，2-支付成功，3-支付失败',

    -- 第三方支付信息
                                   `channel_trade_no` VARCHAR(100) DEFAULT NULL COMMENT '第三方交易号',
                                   `channel_user_id` VARCHAR(100) DEFAULT NULL COMMENT '第三方用户标识',
                                   `channel_response` TEXT DEFAULT NULL COMMENT '第三方响应数据',

    -- 支付时间信息
                                   `pay_time` DATETIME DEFAULT NULL COMMENT '支付时间',
                                   `expire_time` DATETIME DEFAULT NULL COMMENT '过期时间',
                                   `success_time` DATETIME DEFAULT NULL COMMENT '成功时间',

    -- 其他信息
                                   `client_ip` VARCHAR(45) DEFAULT NULL COMMENT '客户端IP',
                                   `device_info` VARCHAR(100) DEFAULT NULL COMMENT '设备信息',
                                   `error_code` VARCHAR(50) DEFAULT NULL COMMENT '错误码',
                                   `error_msg` VARCHAR(255) DEFAULT NULL COMMENT '错误信息',

                                   `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `uk_payment_no` (`payment_no`),
                                   KEY `idx_order_no` (`order_no`),
                                   KEY `idx_user_id` (`user_id`),
                                   KEY `idx_status` (`status`),
                                   KEY `idx_channel_trade_no` (`channel_trade_no`),
                                   KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付记录表';

-- 退款记录表
CREATE TABLE `refund_records` (
                                  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `refund_no` VARCHAR(64) NOT NULL COMMENT '退款单号',
                                  `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                                  `payment_no` VARCHAR(64) NOT NULL COMMENT '原支付单号',
                                  `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
                                  `refund_amount` DECIMAL(10,2) NOT NULL COMMENT '退款金额',
                                  `refund_reason` VARCHAR(255) NOT NULL COMMENT '退款原因',
                                  `refund_type` TINYINT DEFAULT 1 COMMENT '退款类型：1-仅退款，2-退货退款',
                                  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0-申请中，1-处理中，2-成功，3-失败，4-取消',

    -- 第三方退款信息
                                  `channel_refund_no` VARCHAR(100) DEFAULT NULL COMMENT '第三方退款号',
                                  `channel_response` TEXT DEFAULT NULL COMMENT '第三方响应',

    -- 时间信息
                                  `apply_time` DATETIME NOT NULL COMMENT '申请时间',
                                  `approve_time` DATETIME DEFAULT NULL COMMENT '审批时间',
                                  `refund_time` DATETIME DEFAULT NULL COMMENT '退款时间',

    -- 审批信息
                                  `approver_id` BIGINT DEFAULT NULL COMMENT '审批人ID',
                                  `approver_name` VARCHAR(50) DEFAULT NULL COMMENT '审批人姓名',
                                  `approve_remark` VARCHAR(500) DEFAULT NULL COMMENT '审批备注',

                                  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_refund_no` (`refund_no`),
                                  KEY `idx_order_no` (`order_no`),
                                  KEY `idx_payment_no` (`payment_no`),
                                  KEY `idx_user_id` (`user_id`),
                                  KEY `idx_status` (`status`),
                                  KEY `idx_apply_time` (`apply_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退款记录表';

-- 对账记录表
CREATE TABLE `reconciliation_records` (
                                          `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                          `reconciliation_date` DATE NOT NULL COMMENT '对账日期',
                                          `payment_channel` VARCHAR(30) NOT NULL COMMENT '支付渠道',
                                          `total_count` INT NOT NULL COMMENT '总笔数',
                                          `total_amount` DECIMAL(12,2) NOT NULL COMMENT '总金额',
                                          `success_count` INT NOT NULL COMMENT '成功笔数',
                                          `success_amount` DECIMAL(12,2) NOT NULL COMMENT '成功金额',
                                          `diff_count` INT DEFAULT 0 COMMENT '差异笔数',
                                          `diff_amount` DECIMAL(12,2) DEFAULT 0.00 COMMENT '差异金额',
                                          `status` TINYINT DEFAULT 0 COMMENT '状态：0-待对账，1-对账中，2-对账完成，3-对账异常',
                                          `file_url` VARCHAR(500) DEFAULT NULL COMMENT '对账文件地址',
                                          `result_detail` JSON DEFAULT NULL COMMENT '对账结果详情',
                                          `operator_id` BIGINT DEFAULT NULL COMMENT '操作人ID',
                                          `operator_name` VARCHAR(50) DEFAULT NULL COMMENT '操作人姓名',
                                          `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
                                          `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                          `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                                          PRIMARY KEY (`id`),
                                          UNIQUE KEY `uk_date_channel` (`reconciliation_date`, `payment_channel`),
                                          KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对账记录表';
