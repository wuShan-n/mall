-- 创建订单数据库
CREATE DATABASE IF NOT EXISTS `mall_order` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `mall_order`;

-- 订单主表
CREATE TABLE `oms_order` (
                             `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                             `order_no` VARCHAR(32) NOT NULL COMMENT '订单编号',
                             `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                             `username` VARCHAR(64) DEFAULT NULL COMMENT '用户名',

    -- 金额信息
                             `total_amount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '订单总金额',
                             `product_amount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '商品总金额',
                             `freight_amount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '运费金额',
                             `discount_amount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '优惠金额',
                             `coupon_discount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '优惠券抵扣',
                             `points_discount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '积分抵扣',
                             `promotion_discount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '促销优惠',
                             `pay_amount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '应付金额',

    -- 支付信息
                             `payment_type` TINYINT(4) DEFAULT NULL COMMENT '支付方式：1->支付宝；2->微信',
                             `payment_time` DATETIME DEFAULT NULL COMMENT '支付时间',
                             `payment_no` VARCHAR(64) DEFAULT NULL COMMENT '支付流水号',

    -- 状态信息
                             `status` INT(1) DEFAULT '0' COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->已取消；6->售后中',
                             `source_type` INT(1) DEFAULT NULL COMMENT '订单来源：1->PC订单；2->app订单；3->小程序订单',
                             `confirm_status` INT(1) DEFAULT '0' COMMENT '确认收货状态：0->未确认；1->已确认',
                             `delete_status` INT(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除',

    -- 物流信息
                             `delivery_company` VARCHAR(64) DEFAULT NULL COMMENT '物流公司',
                             `delivery_no` VARCHAR(64) DEFAULT NULL COMMENT '物流单号',
                             `delivery_time` DATETIME DEFAULT NULL COMMENT '发货时间',
                             `receive_time` DATETIME DEFAULT NULL COMMENT '确认收货时间',

    -- 收货人信息
                             `receiver_name` VARCHAR(100) NOT NULL COMMENT '收货人姓名',
                             `receiver_phone` VARCHAR(32) NOT NULL COMMENT '收货人电话',
                             `receiver_province` VARCHAR(32) DEFAULT NULL COMMENT '省份',
                             `receiver_city` VARCHAR(32) DEFAULT NULL COMMENT '城市',
                             `receiver_district` VARCHAR(32) DEFAULT NULL COMMENT '区',
                             `receiver_address` VARCHAR(200) DEFAULT NULL COMMENT '详细地址',
                             `receiver_postal_code` VARCHAR(32) DEFAULT NULL COMMENT '邮编',

    -- 其他信息
                             `remark` VARCHAR(500) DEFAULT NULL COMMENT '订单备注',
                             `coupon_id` BIGINT(20) DEFAULT NULL COMMENT '优惠券ID',
                             `use_points` INT DEFAULT '0' COMMENT '使用的积分',
                             `promotion_info` VARCHAR(100) DEFAULT NULL COMMENT '活动信息',
                             `invoice_type` INT(1) DEFAULT NULL COMMENT '发票类型：0->不开发票；1->电子发票；2->纸质发票',
                             `invoice_header` VARCHAR(200) DEFAULT NULL COMMENT '发票抬头',
                             `invoice_content` VARCHAR(200) DEFAULT NULL COMMENT '发票内容',
                             `invoice_receiver_phone` VARCHAR(32) DEFAULT NULL COMMENT '收票人电话',
                             `invoice_receiver_email` VARCHAR(64) DEFAULT NULL COMMENT '收票人邮箱',

    -- 时间信息
                             `auto_confirm_day` INT(11) DEFAULT NULL COMMENT '自动确认时间（天）',
                             `expire_time` DATETIME DEFAULT NULL COMMENT '订单过期时间',
                             `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_order_no` (`order_no`),
                             KEY `idx_user_id` (`user_id`),
                             KEY `idx_status` (`status`),
                             KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 订单商品详情表
CREATE TABLE `oms_order_item` (
                                  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                  `order_id` BIGINT(20) NOT NULL COMMENT '订单ID',
                                  `order_no` VARCHAR(32) DEFAULT NULL COMMENT '订单编号',

    -- 商品信息
                                  `spu_id` BIGINT(20) DEFAULT NULL COMMENT 'SPU ID',
                                  `sku_id` BIGINT(20) DEFAULT NULL COMMENT 'SKU ID',
                                  `product_name` VARCHAR(200) DEFAULT NULL COMMENT '商品名称',
                                  `product_image` VARCHAR(500) DEFAULT NULL COMMENT '商品图片',
                                  `product_specs` VARCHAR(500) DEFAULT NULL COMMENT '商品规格',
                                  `product_code` VARCHAR(64) DEFAULT NULL COMMENT '商品编码',
                                  `product_category_id` BIGINT(20) DEFAULT NULL COMMENT '商品分类ID',
                                  `product_brand` VARCHAR(200) DEFAULT NULL COMMENT '品牌',

    -- 价格信息
                                  `price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '销售价格',
                                  `quantity` INT(11) DEFAULT '1' COMMENT '购买数量',
                                  `total_amount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '商品总价',
                                  `discount_amount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '优惠金额',
                                  `real_amount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '实际金额',

    -- 促销信息
                                  `promotion_name` VARCHAR(100) DEFAULT NULL COMMENT '促销名称',
                                  `gift_growth` INT(11) DEFAULT '0' COMMENT '赠送的成长值',
                                  `gift_point` INT(11) DEFAULT '0' COMMENT '赠送的积分',

    -- 售后信息
                                  `refund_status` INT(1) DEFAULT '0' COMMENT '退款状态：0->未退款；1->退款中；2->已退款',
                                  `refund_amount` DECIMAL(10,2) DEFAULT '0.00' COMMENT '退款金额',
                                  `comment_status` INT(1) DEFAULT '0' COMMENT '评价状态：0->未评价；1->已评价',

                                  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

                                  PRIMARY KEY (`id`),
                                  KEY `idx_order_id` (`order_id`),
                                  KEY `idx_order_no` (`order_no`),
                                  KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品详情表';

-- 订单操作记录表
CREATE TABLE `oms_order_operate_history` (
                                             `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                             `order_id` BIGINT(20) DEFAULT NULL COMMENT '订单ID',
                                             `order_no` VARCHAR(32) DEFAULT NULL COMMENT '订单编号',
                                             `operate_type` VARCHAR(32) DEFAULT NULL COMMENT '操作类型',
                                             `operate_man` VARCHAR(100) DEFAULT NULL COMMENT '操作人',
                                             `order_status` INT(1) DEFAULT NULL COMMENT '订单状态',
                                             `note` VARCHAR(500) DEFAULT NULL COMMENT '备注',
                                             `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
                                             PRIMARY KEY (`id`),
                                             KEY `idx_order_id` (`order_id`),
                                             KEY `idx_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单操作记录表';

-- 退货申请表
CREATE TABLE `oms_order_refund` (
                                    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                    `refund_no` VARCHAR(32) NOT NULL COMMENT '退款编号',
                                    `order_id` BIGINT(20) NOT NULL COMMENT '订单ID',
                                    `order_no` VARCHAR(32) DEFAULT NULL COMMENT '订单编号',
                                    `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户ID',

    -- 退款信息
                                    `refund_type` INT(1) DEFAULT NULL COMMENT '退款类型：1->仅退款；2->退货退款',
                                    `refund_amount` DECIMAL(10,2) DEFAULT NULL COMMENT '退款金额',
                                    `refund_reason` VARCHAR(200) DEFAULT NULL COMMENT '退款原因',
                                    `description` VARCHAR(500) DEFAULT NULL COMMENT '问题描述',
                                    `proof_pics` VARCHAR(1000) DEFAULT NULL COMMENT '凭证图片，以逗号隔开',

    -- 状态信息
                                    `status` INT(1) DEFAULT NULL COMMENT '申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝',
                                    `handle_time` DATETIME DEFAULT NULL COMMENT '处理时间',
                                    `handle_note` VARCHAR(500) DEFAULT NULL COMMENT '处理备注',
                                    `handle_man` VARCHAR(100) DEFAULT NULL COMMENT '处理人',

    -- 物流信息（退货）
                                    `return_delivery_company` VARCHAR(64) DEFAULT NULL COMMENT '退货物流公司',
                                    `return_delivery_no` VARCHAR(64) DEFAULT NULL COMMENT '退货物流单号',

    -- 收货信息（商家）
                                    `receive_man` VARCHAR(100) DEFAULT NULL COMMENT '收货人',
                                    `receive_time` DATETIME DEFAULT NULL COMMENT '收货时间',
                                    `receive_note` VARCHAR(500) DEFAULT NULL COMMENT '收货备注',

                                    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
                                    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_refund_no` (`refund_no`),
                                    KEY `idx_order_id` (`order_id`),
                                    KEY `idx_order_no` (`order_no`),
                                    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货申请表';

-- 退货原因表
CREATE TABLE `oms_order_refund_reason` (
                                           `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                           `name` VARCHAR(100) DEFAULT NULL COMMENT '退货原因',
                                           `sort` INT(11) DEFAULT NULL COMMENT '排序',
                                           `status` INT(1) DEFAULT NULL COMMENT '状态：0->不启用；1->启用',
                                           `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退货原因表';

-- 初始化退货原因数据
INSERT INTO `oms_order_refund_reason` (`name`, `sort`, `status`) VALUES
                                                                     ('质量问题', 1, 1),
                                                                     ('商品与描述不符', 2, 1),
                                                                     ('发错货品', 3, 1),
                                                                     ('商品损坏', 4, 1),
                                                                     ('不想要了', 5, 1),
                                                                     ('其他', 100, 1);