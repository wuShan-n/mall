-- 创建订单数据库
CREATE DATABASE IF NOT EXISTS `mall_inventory` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `mall_inventory`;

-- 库存表
CREATE TABLE `inv_stock` (
                             `id` bigint NOT NULL COMMENT '主键ID',
                             `sku_id` bigint NOT NULL COMMENT 'SKU ID',
                             `warehouse_id` bigint NOT NULL COMMENT '仓库ID',
                             `total_stock` int NOT NULL DEFAULT '0' COMMENT '总库存',
                             `available_stock` int NOT NULL DEFAULT '0' COMMENT '可用库存',
                             `locked_stock` int NOT NULL DEFAULT '0' COMMENT '锁定库存',
                             `in_transit_stock` int NOT NULL DEFAULT '0' COMMENT '在途库存',
                             `warn_stock` int DEFAULT '10' COMMENT '预警库存',
                             `safety_stock` int DEFAULT '5' COMMENT '安全库存',
                             `status` tinyint DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
                             `version` int DEFAULT '0' COMMENT '版本号',
                             `deleted` tinyint DEFAULT '0' COMMENT '删除标记',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_sku_warehouse` (`sku_id`,`warehouse_id`),
                             KEY `idx_warehouse_id` (`warehouse_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

-- 库存锁定记录表
CREATE TABLE `inv_stock_lock` (
                                  `id` bigint NOT NULL COMMENT '主键ID',
                                  `sku_id` bigint NOT NULL COMMENT 'SKU ID',
                                  `warehouse_id` bigint NOT NULL COMMENT '仓库ID',
                                  `order_no` varchar(50) NOT NULL COMMENT '订单号',
                                  `user_id` bigint NOT NULL COMMENT '用户ID',
                                  `locked_quantity` int NOT NULL COMMENT '锁定数量',
                                  `lock_status` tinyint NOT NULL COMMENT '锁定状态 1-已锁定 2-已释放 3-已扣减',
                                  `lock_time` datetime NOT NULL COMMENT '锁定时间',
                                  `expire_time` datetime NOT NULL COMMENT '过期时间',
                                  `release_time` datetime DEFAULT NULL COMMENT '释放时间',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_order_no` (`order_no`),
                                  KEY `idx_sku_id` (`sku_id`),
                                  KEY `idx_expire_time` (`expire_time`),
                                  KEY `idx_lock_status` (`lock_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存锁定记录表';

-- 库存变更记录表
CREATE TABLE `inv_stock_record` (
                                    `id` bigint NOT NULL COMMENT '主键ID',
                                    `sku_id` bigint NOT NULL COMMENT 'SKU ID',
                                    `warehouse_id` bigint DEFAULT NULL COMMENT '仓库ID',
                                    `operation_type` tinyint NOT NULL COMMENT '操作类型 1-入库 2-出库 3-锁定 4-解锁',
                                    `change_quantity` int NOT NULL COMMENT '变更数量',
                                    `stock_before` int NOT NULL COMMENT '变更前库存',
                                    `stock_after` int NOT NULL COMMENT '变更后库存',
                                    `business_type` varchar(50) DEFAULT NULL COMMENT '业务类型',
                                    `business_no` varchar(50) DEFAULT NULL COMMENT '业务单号',
                                    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                    `operator_id` bigint DEFAULT NULL COMMENT '操作人ID',
                                    `operator_name` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    PRIMARY KEY (`id`),
                                    KEY `idx_sku_id` (`sku_id`),
                                    KEY `idx_business_no` (`business_no`),
                                    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存变更记录表';

-- 仓库表
CREATE TABLE `inv_warehouse` (
                                 `id` bigint NOT NULL COMMENT '主键ID',
                                 `warehouse_name` varchar(100) NOT NULL COMMENT '仓库名称',
                                 `warehouse_code` varchar(50) NOT NULL COMMENT '仓库编码',
                                 `warehouse_type` tinyint DEFAULT '1' COMMENT '仓库类型 1-主仓 2-分仓 3-中转仓',
                                 `contact_person` varchar(50) DEFAULT NULL COMMENT '联系人',
                                 `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
                                 `province` varchar(50) NOT NULL COMMENT '省份',
                                 `city` varchar(50) NOT NULL COMMENT '城市',
                                 `district` varchar(50) DEFAULT NULL COMMENT '区县',
                                 `address` varchar(200) NOT NULL COMMENT '详细地址',
                                 `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
                                 `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
                                 `status` tinyint DEFAULT '1' COMMENT '状态 0-禁用 1-启用',
                                 `remark` varchar(500) DEFAULT NULL COMMENT '备注',
                                 `deleted` tinyint DEFAULT '0' COMMENT '删除标记',
                                 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库表';