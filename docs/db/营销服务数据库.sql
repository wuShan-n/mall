-- ----------------------------
-- Table structure for coupon
-- ----------------------------
CREATE TABLE `coupon` (
                          `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                          `coupon_name` VARCHAR(100) NOT NULL COMMENT '优惠券名称',
                          `coupon_code` VARCHAR(50) NOT NULL COMMENT '优惠券编码',
                          `type` TINYINT(1) NOT NULL COMMENT '类型:1-满减,2-折扣,3-现金',
                          `amount` DECIMAL(10,2) NOT NULL COMMENT '优惠金额/折扣',
                          `min_amount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '最低消费金额',
                          `total_quantity` INT(11) NOT NULL COMMENT '发行总量',
                          `receive_quantity` INT(11) NOT NULL DEFAULT '0' COMMENT '已领取数量',
                          `used_quantity` INT(11) NOT NULL DEFAULT '0' COMMENT '已使用数量',
                          `per_limit` INT(11) NOT NULL DEFAULT '1' COMMENT '每人限领数量',
                          `use_type` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '使用类型:0-全场,1-指定分类,2-指定商品',
                          `start_time` DATETIME NOT NULL COMMENT '开始时间',
                          `end_time` DATETIME NOT NULL COMMENT '结束时间',
                          `valid_type` TINYINT(1) NOT NULL COMMENT '有效期类型',
                          `valid_days` INT(11) DEFAULT NULL COMMENT '有效天数',
                          `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态',
                          `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `uk_coupon_code` (`coupon_code`),
                          KEY `idx_type` (`type`),
                          KEY `idx_status` (`status`),
                          KEY `idx_time` (`start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券表';

-- ----------------------------
-- Table structure for coupon_user
-- ----------------------------
CREATE TABLE `coupon_user` (
                               `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `coupon_id` BIGINT(20) NOT NULL COMMENT '优惠券ID',
                               `user_id` BIGINT(20) NOT NULL COMMENT '用户ID',
                               `coupon_code` VARCHAR(50) NOT NULL COMMENT '优惠券编码',
                               `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '状态:0-未使用,1-已使用,2-已过期',
                               `used_time` DATETIME DEFAULT NULL COMMENT '使用时间',
                               `order_no` VARCHAR(32) DEFAULT NULL COMMENT '使用的订单号',
                               `start_time` DATETIME NOT NULL COMMENT '生效时间',
                               `end_time` DATETIME NOT NULL COMMENT '失效时间',
                               `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
                               PRIMARY KEY (`id`),
                               KEY `idx_user_id` (`user_id`),
                               KEY `idx_coupon_id` (`coupon_id`),
                               KEY `idx_status` (`status`),
                               KEY `idx_end_time` (`end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户优惠券表';

-- ----------------------------
-- Table structure for seckill_activity
-- ----------------------------
CREATE TABLE `seckill_activity` (
                                    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `activity_name` VARCHAR(100) NOT NULL COMMENT '活动名称',
                                    `start_time` DATETIME NOT NULL COMMENT '开始时间',
                                    `end_time` DATETIME NOT NULL COMMENT '结束时间',
                                    `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '状态',
                                    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀活动表';

-- ----------------------------
-- Table structure for seckill_goods
-- ----------------------------
CREATE TABLE `seckill_goods` (
                                 `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `activity_id` BIGINT(20) NOT NULL COMMENT '活动ID',
                                 `sku_id` BIGINT(20) NOT NULL COMMENT 'SKU ID',
                                 `seckill_price` DECIMAL(10,2) NOT NULL COMMENT '秒杀价',
                                 `seckill_stock` INT(11) NOT NULL COMMENT '秒杀库存',
                                 `sold_quantity` INT(11) NOT NULL DEFAULT '0' COMMENT '已售数量',
                                 `limit_num` INT(11) NOT NULL DEFAULT '1' COMMENT '限购数量',
                                 `sort` INT(11) NOT NULL DEFAULT '0' COMMENT '排序',
                                 `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_activity_id` (`activity_id`),
                                 KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='秒杀商品表';