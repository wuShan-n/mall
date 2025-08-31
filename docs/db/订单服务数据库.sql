-- ----------------------------
-- Table structure for order_master
-- ----------------------------
CREATE TABLE `order_master` (
                                `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                                `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                                `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                                `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总额',
                                `product_amount` DECIMAL(10,2) NOT NULL COMMENT '商品总额',
                                `freight_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '运费',
                                `discount_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
                                `pay_amount` DECIMAL(10,2) NOT NULL COMMENT '应付金额',
                                `pay_type` TINYINT(1) DEFAULT NULL COMMENT '支付方式',
                                `source_type` TINYINT(1) NOT NULL COMMENT '订单来源',
                                `status` TINYINT(1) NOT NULL COMMENT '订单状态',
                                `delivery_company` VARCHAR(50) DEFAULT NULL COMMENT '物流公司',
                                `delivery_no` VARCHAR(50) DEFAULT NULL COMMENT '物流单号',
                                `receiver_name` VARCHAR(50) NOT NULL COMMENT '收货人',
                                `receiver_phone` VARCHAR(20) NOT NULL COMMENT '收货电话',
                                `receiver_province` VARCHAR(50) NOT NULL COMMENT '省份',
                                `receiver_city` VARCHAR(50) NOT NULL COMMENT '城市',
                                `receiver_district` VARCHAR(50) NOT NULL COMMENT '区县',
                                `receiver_address` VARCHAR(200) NOT NULL COMMENT '详细地址',
                                `remark` VARCHAR(500) DEFAULT NULL COMMENT '订单备注',
                                `confirm_status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '确认状态',
                                `payment_time` DATETIME DEFAULT NULL COMMENT '支付时间',
                                `delivery_time` DATETIME DEFAULT NULL COMMENT '发货时间',
                                `receive_time` DATETIME DEFAULT NULL COMMENT '收货时间',
                                `comment_time` DATETIME DEFAULT NULL COMMENT '评价时间',
                                `deleted` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
                                `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_order_no` (`order_no`),
                                KEY `idx_user_id` (`user_id`),
                                KEY `idx_status` (`status`),
                                KEY `idx_create_time` (`create_time`),
                                KEY `idx_payment_time` (`payment_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单主表';

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
CREATE TABLE `order_item` (
                              `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `order_id` BIGINT(20) NOT NULL COMMENT '订单ID',
                              `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                              `spu_id` BIGINT(20) NOT NULL COMMENT 'SPU ID',
                              `sku_id` BIGINT(20) NOT NULL COMMENT 'SKU ID',
                              `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
                              `product_image` VARCHAR(255) DEFAULT NULL COMMENT '商品图片',
                              `product_specs` VARCHAR(200) DEFAULT NULL COMMENT '商品规格',
                              `product_code` VARCHAR(50) NOT NULL COMMENT '商品编码',
                              `price` DECIMAL(10,2) NOT NULL COMMENT '单价',
                              `quantity` INT(11) NOT NULL COMMENT '数量',
                              `total_amount` DECIMAL(10,2) NOT NULL COMMENT '总金额',
                              `discount_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
                              `real_amount` DECIMAL(10,2) NOT NULL COMMENT '实付金额',
                              `gift_growth` INT(11) NOT NULL DEFAULT '0' COMMENT '赠送成长值',
                              `gift_point` INT(11) NOT NULL DEFAULT '0' COMMENT '赠送积分',
                              `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              PRIMARY KEY (`id`),
                              KEY `idx_order_id` (`order_id`),
                              KEY `idx_order_no` (`order_no`),
                              KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- ----------------------------
-- Table structure for order_payment
-- ----------------------------
CREATE TABLE `order_payment` (
                                 `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `order_id` BIGINT(20) NOT NULL COMMENT '订单ID',
                                 `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                                 `payment_no` VARCHAR(50) NOT NULL COMMENT '支付流水号',
                                 `payment_type` TINYINT(1) NOT NULL COMMENT '支付方式',
                                 `payment_amount` DECIMAL(10,2) NOT NULL COMMENT '支付金额',
                                 `payment_status` TINYINT(1) NOT NULL COMMENT '支付状态',
                                 `payment_time` DATETIME DEFAULT NULL COMMENT '支付时间',
                                 `callback_time` DATETIME DEFAULT NULL COMMENT '回调时间',
                                 `callback_content` TEXT COMMENT '回调内容',
                                 `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单支付信息表';

-- ----------------------------
-- Table structure for order_refund
-- ----------------------------
CREATE TABLE `order_refund` (
                                `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `refund_no` VARCHAR(32) NOT NULL COMMENT '退款单号',
                                `order_id` BIGINT(20) NOT NULL COMMENT '订单ID',
                                `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                                `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                                `refund_type` TINYINT(1) NOT NULL COMMENT '退款类型',
                                `refund_amount` DECIMAL(10,2) NOT NULL COMMENT '退款金额',
                                `refund_reason` VARCHAR(200) NOT NULL COMMENT '退款原因',
                                `description` VARCHAR(500) DEFAULT NULL COMMENT '详细说明',
                                `proof_images` JSON DEFAULT NULL COMMENT '凭证图片',
                                `status` TINYINT(1) NOT NULL COMMENT '状态',
                                `handle_note` VARCHAR(500) DEFAULT NULL COMMENT '处理备注',
                                `handle_time` DATETIME DEFAULT NULL COMMENT '处理时间',
                                `refund_time` DATETIME DEFAULT NULL COMMENT '退款时间',
                                `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退款表';