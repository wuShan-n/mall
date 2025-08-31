-- ----------------------------
-- Table structure for inventory
-- ----------------------------
CREATE TABLE `inventory` (
                             `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `sku_id` BIGINT(20) NOT NULL COMMENT 'SKU ID',
                             `warehouse_id` BIGINT(20) NOT NULL DEFAULT '1' COMMENT '仓库ID',
                             `total_stock` INT(11) NOT NULL DEFAULT '0' COMMENT '总库存',
                             `available_stock` INT(11) NOT NULL DEFAULT '0' COMMENT '可用库存',
                             `lock_stock` INT(11) NOT NULL DEFAULT '0' COMMENT '锁定库存',
                             `sold_stock` INT(11) NOT NULL DEFAULT '0' COMMENT '已售库存',
                             `warn_stock` INT(11) NOT NULL DEFAULT '10' COMMENT '预警库存',
                             `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态',
                             `version` INT(11) NOT NULL DEFAULT '0' COMMENT '版本号(乐观锁)',
                             `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_sku_warehouse` (`sku_id`, `warehouse_id`),
                             KEY `idx_available_stock` (`available_stock`),
                             KEY `idx_warn_stock` (`warn_stock`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

-- ----------------------------
-- Table structure for inventory_lock
-- ----------------------------
CREATE TABLE `inventory_lock` (
                                  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
                                  `sku_id` BIGINT(20) NOT NULL COMMENT 'SKU ID',
                                  `warehouse_id` BIGINT(20) NOT NULL COMMENT '仓库ID',
                                  `lock_quantity` INT(11) NOT NULL COMMENT '锁定数量',
                                  `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态:1-锁定,2-释放,3-扣减',
                                  `expire_time` DATETIME NOT NULL COMMENT '过期时间',
                                  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  KEY `idx_order_no` (`order_no`),
                                  KEY `idx_sku_id` (`sku_id`),
                                  KEY `idx_status` (`status`),
                                  KEY `idx_expire_time` (`expire_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存锁定表';

-- ----------------------------
-- Table structure for inventory_record
-- ----------------------------
CREATE TABLE `inventory_record` (
                                    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `sku_id` BIGINT(20) NOT NULL COMMENT 'SKU ID',
                                    `warehouse_id` BIGINT(20) NOT NULL COMMENT '仓库ID',
                                    `type` TINYINT(1) NOT NULL COMMENT '类型:1-入库,2-出库,3-锁定,4-释放',
                                    `quantity` INT(11) NOT NULL COMMENT '变动数量',
                                    `before_stock` INT(11) NOT NULL COMMENT '变动前库存',
                                    `after_stock` INT(11) NOT NULL COMMENT '变动后库存',
                                    `business_type` VARCHAR(50) NOT NULL COMMENT '业务类型',
                                    `business_no` VARCHAR(50) NOT NULL COMMENT '业务单号',
                                    `operator_id` BIGINT(20) DEFAULT NULL COMMENT '操作人ID',
                                    `operator_name` VARCHAR(50) DEFAULT NULL COMMENT '操作人姓名',
                                    `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注',
                                    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    PRIMARY KEY (`id`),
                                    KEY `idx_sku_id` (`sku_id`),
                                    KEY `idx_business_no` (`business_no`),
                                    KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存流水表';