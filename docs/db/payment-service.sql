-- 创建数据库
CREATE DATABASE IF NOT EXISTS `mall_payment` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `mall_payment`;
-- ----------------------------
-- Table structure for payment_order
-- ----------------------------
CREATE TABLE `payment_order` (
                                 `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `payment_no` VARCHAR(32) NOT NULL COMMENT '支付单号',
                                 `order_no` VARCHAR(32) NOT NULL COMMENT '业务订单号',
                                 `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                                 `payment_type` TINYINT(1) NOT NULL COMMENT '支付类型:1-支付宝,2-微信',
                                 `payment_amount` DECIMAL(10,2) NOT NULL COMMENT '支付金额',
                                 `currency` VARCHAR(10) NOT NULL DEFAULT 'CNY' COMMENT '币种',
                                 `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '状态:0-待支付,1-已支付,2-已退款',
                                 `third_party_no` VARCHAR(50) DEFAULT NULL COMMENT '第三方流水号',
                                 `payment_time` DATETIME DEFAULT NULL COMMENT '支付时间',
                                 `expire_time` DATETIME NOT NULL COMMENT '过期时间',
                                 `callback_time` DATETIME DEFAULT NULL COMMENT '回调时间',
                                 `callback_content` TEXT COMMENT '回调内容',
                                 `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `uk_payment_no` (`payment_no`),
                                 KEY `idx_order_no` (`order_no`),
                                 KEY `idx_user_id` (`user_id`),
                                 KEY `idx_status` (`status`),
                                 KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付订单表';

-- ----------------------------
-- Table structure for refund_order
-- ----------------------------
CREATE TABLE `refund_order` (
                                `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `refund_no` VARCHAR(32) NOT NULL COMMENT '退款单号',
                                `payment_no` VARCHAR(32) NOT NULL COMMENT '原支付单号',
                                `order_no` VARCHAR(32) NOT NULL COMMENT '业务订单号',
                                `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                                `refund_amount` DECIMAL(10,2) NOT NULL COMMENT '退款金额',
                                `refund_reason` VARCHAR(200) NOT NULL COMMENT '退款原因',
                                `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '状态',
                                `third_party_refund_no` VARCHAR(50) DEFAULT NULL COMMENT '第三方退款单号',
                                `refund_time` DATETIME DEFAULT NULL COMMENT '退款时间',
                                `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='退款订单表';