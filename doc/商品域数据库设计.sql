-- 3.1 商品基础表设计
-- 商品数据模型是电商系统最复杂的部分之一，需要支持灵活的属性管理和SKU组合。

-- 商品分类表
CREATE TABLE `categories` (
                              `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                              `parent_id` BIGINT UNSIGNED DEFAULT 0 COMMENT '父分类ID，0表示顶级分类',
                              `category_name` VARCHAR(50) NOT NULL COMMENT '分类名称',
                              `category_path` VARCHAR(255) DEFAULT NULL COMMENT '分类路径，如：/1/2/3/',
                              `level` TINYINT NOT NULL DEFAULT 1 COMMENT '层级',
                              `sort_order` INT DEFAULT 0 COMMENT '排序值',
                              `icon_url` VARCHAR(500) DEFAULT NULL COMMENT '分类图标',
                              `is_show` BOOLEAN DEFAULT TRUE COMMENT '是否显示',
                              `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`),
                              KEY `idx_parent_id` (`parent_id`),
                              KEY `idx_category_path` (`category_path`),
                              KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- 品牌表
CREATE TABLE `brands` (
                          `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                          `brand_name` VARCHAR(50) NOT NULL COMMENT '品牌名称',
                          `brand_name_en` VARCHAR(50) DEFAULT NULL COMMENT '品牌英文名',
                          `logo_url` VARCHAR(500) DEFAULT NULL COMMENT '品牌logo',
                          `description` TEXT DEFAULT NULL COMMENT '品牌描述',
                          `sort_order` INT DEFAULT 0 COMMENT '排序值',
                          `is_show` BOOLEAN DEFAULT TRUE COMMENT '是否显示',
                          `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`),
                          KEY `idx_brand_name` (`brand_name`),
                          KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='品牌表';

-- SPU表（标准产品单元）
CREATE TABLE `products` (
                            `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                            `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
                            `product_code` VARCHAR(50) DEFAULT NULL COMMENT '商品编码',
                            `category_id` BIGINT UNSIGNED NOT NULL COMMENT '分类ID',
                            `brand_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '品牌ID',
                            `merchant_id` BIGINT UNSIGNED DEFAULT NULL COMMENT '商家ID',
                            `product_type` TINYINT DEFAULT 1 COMMENT '商品类型：1-实物，2-虚拟',
                            `status` TINYINT DEFAULT 0 COMMENT '状态：0-下架，1-上架，2-待审核',
                            `audit_status` TINYINT DEFAULT 0 COMMENT '审核状态：0-待审核，1-通过，2-拒绝',
                            `audit_reason` VARCHAR(255) DEFAULT NULL COMMENT '审核原因',
                            `keywords` VARCHAR(255) DEFAULT NULL COMMENT '关键词，用于搜索',
                            `selling_point` VARCHAR(255) DEFAULT NULL COMMENT '卖点',
                            `description` TEXT DEFAULT NULL COMMENT '商品详情（HTML）',
                            `main_image` VARCHAR(500) DEFAULT NULL COMMENT '主图',
                            `video_url` VARCHAR(500) DEFAULT NULL COMMENT '商品视频',
                            `unit` VARCHAR(20) DEFAULT NULL COMMENT '单位：个/件/千克',
                            `weight` DECIMAL(10,3) DEFAULT NULL COMMENT '重量（千克）',
                            `volume` DECIMAL(10,3) DEFAULT NULL COMMENT '体积（立方米）',
                            `sort_order` INT DEFAULT 0 COMMENT '排序值',
                            `sales_count` INT DEFAULT 0 COMMENT '销量',
                            `view_count` INT DEFAULT 0 COMMENT '浏览量',
                            `comment_count` INT DEFAULT 0 COMMENT '评论数',
                            `rating` DECIMAL(2,1) DEFAULT 5.0 COMMENT '评分',
                            `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `deleted_at` DATETIME DEFAULT NULL COMMENT '删除时间（软删除）',
                            PRIMARY KEY (`id`),
                            KEY `idx_product_name` (`product_name`),
                            KEY `idx_category_id` (`category_id`),
                            KEY `idx_brand_id` (`brand_id`),
                            KEY `idx_merchant_id` (`merchant_id`),
                            KEY `idx_status` (`status`),
                            KEY `idx_sales_count` (`sales_count`),
                            KEY `idx_created_at` (`created_at`),
                            FULLTEXT KEY `ft_keywords` (`keywords`),
                            CONSTRAINT `fk_products_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
                            CONSTRAINT `fk_products_brand` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品SPU表';

-- 商品图片表
CREATE TABLE `product_images` (
                                  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `product_id` BIGINT UNSIGNED NOT NULL COMMENT '商品ID',
                                  `sku_id` BIGINT UNSIGNED DEFAULT NULL COMMENT 'SKU ID（可选）',
                                  `image_url` VARCHAR(500) NOT NULL COMMENT '图片URL',
                                  `image_type` TINYINT DEFAULT 1 COMMENT '图片类型：1-主图，2-详情图',
                                  `sort_order` INT DEFAULT 0 COMMENT '排序值',
                                  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  KEY `idx_product_id` (`product_id`),
                                  KEY `idx_sku_id` (`sku_id`),
                                  KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品图片表';

-- SKU表（库存单元）
CREATE TABLE `product_skus` (
                                `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                `product_id` BIGINT UNSIGNED NOT NULL COMMENT '商品ID',
                                `sku_code` VARCHAR(50) NOT NULL COMMENT 'SKU编码',
                                `sku_name` VARCHAR(200) DEFAULT NULL COMMENT 'SKU名称',
                                `price` DECIMAL(10,2) NOT NULL COMMENT '销售价',
                                `market_price` DECIMAL(10,2) DEFAULT NULL COMMENT '市场价',
                                `cost_price` DECIMAL(10,2) DEFAULT NULL COMMENT '成本价',
                                `stock` INT NOT NULL DEFAULT 0 COMMENT '库存数量',
                                `locked_stock` INT DEFAULT 0 COMMENT '锁定库存',
                                `sales_count` INT DEFAULT 0 COMMENT '销量',
                                `warn_stock` INT DEFAULT 10 COMMENT '预警库存',
                                `weight` DECIMAL(10,3) DEFAULT NULL COMMENT '重量（千克）',
                                `volume` DECIMAL(10,3) DEFAULT NULL COMMENT '体积（立方米）',
                                `spec_values` JSON DEFAULT NULL COMMENT '规格值组合，如：{"颜色":"红色","尺码":"XL"}',
                                `is_default` BOOLEAN DEFAULT FALSE COMMENT '是否默认SKU',
                                `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
                                `version` INT DEFAULT 0 COMMENT '版本号（用于乐观锁）',
                                `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_sku_code` (`sku_code`),
                                KEY `idx_product_id` (`product_id`),
                                KEY `idx_price` (`price`),
                                KEY `idx_stock` (`stock`),
                                KEY `idx_sales_count` (`sales_count`),
                                CONSTRAINT `fk_product_skus_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品SKU表';

-- 商品属性键表
CREATE TABLE `product_attributes` (
                                      `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                      `category_id` BIGINT UNSIGNED NOT NULL COMMENT '分类ID',
                                      `attr_name` VARCHAR(50) NOT NULL COMMENT '属性名',
                                      `attr_type` TINYINT DEFAULT 1 COMMENT '属性类型：1-基础属性，2-销售属性',
                                      `input_type` TINYINT DEFAULT 1 COMMENT '输入类型：1-手动输入，2-单选，3-多选',
                                      `attr_values` JSON DEFAULT NULL COMMENT '可选值列表',
                                      `is_required` BOOLEAN DEFAULT FALSE COMMENT '是否必填',
                                      `is_search` BOOLEAN DEFAULT FALSE COMMENT '是否可搜索',
                                      `sort_order` INT DEFAULT 0 COMMENT '排序值',
                                      `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      PRIMARY KEY (`id`),
                                      KEY `idx_category_id` (`category_id`),
                                      KEY `idx_attr_type` (`attr_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品属性定义表';

-- 商品属性值表
CREATE TABLE `product_attribute_values` (
                                            `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                            `product_id` BIGINT UNSIGNED NOT NULL COMMENT '商品ID',
                                            `attribute_id` BIGINT UNSIGNED NOT NULL COMMENT '属性ID',
                                            `attribute_value` VARCHAR(255) NOT NULL COMMENT '属性值',
                                            `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                            PRIMARY KEY (`id`),
                                            KEY `idx_product_id` (`product_id`),
                                            KEY `idx_attribute_id` (`attribute_id`),
                                            CONSTRAINT `fk_product_attr_values_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
                                            CONSTRAINT `fk_product_attr_values_attr` FOREIGN KEY (`attribute_id`) REFERENCES `product_attributes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品属性值表';

-- 3.2 库存管理表设计
-- 库存管理需要支持多仓库、批次管理和库存变动追踪。

-- 仓库表
CREATE TABLE `warehouses` (
                              `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                              `warehouse_code` VARCHAR(50) NOT NULL COMMENT '仓库编码',
                              `warehouse_name` VARCHAR(100) NOT NULL COMMENT '仓库名称',
                              `warehouse_type` TINYINT DEFAULT 1 COMMENT '仓库类型：1-自营，2-第三方',
                              `province` VARCHAR(50) NOT NULL COMMENT '省份',
                              `city` VARCHAR(50) NOT NULL COMMENT '城市',
                              `district` VARCHAR(50) DEFAULT NULL COMMENT '区县',
                              `address` VARCHAR(255) NOT NULL COMMENT '详细地址',
                              `contact_name` VARCHAR(50) DEFAULT NULL COMMENT '联系人',
                              `contact_phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
                              `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
                              `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`id`),
                              UNIQUE KEY `uk_warehouse_code` (`warehouse_code`),
                              KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库表';

-- 库存表（支持多仓库）
CREATE TABLE `inventory` (
                             `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                             `sku_id` BIGINT UNSIGNED NOT NULL COMMENT 'SKU ID',
                             `warehouse_id` BIGINT UNSIGNED NOT NULL COMMENT '仓库ID',
                             `quantity` INT NOT NULL DEFAULT 0 COMMENT '可用库存',
                             `locked_quantity` INT DEFAULT 0 COMMENT '锁定库存',
                             `in_transit_quantity` INT DEFAULT 0 COMMENT '在途库存',
                             `version` INT DEFAULT 0 COMMENT '版本号（乐观锁）',
                             `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_sku_warehouse` (`sku_id`, `warehouse_id`),
                             KEY `idx_quantity` (`quantity`),
                             CONSTRAINT `fk_inventory_sku` FOREIGN KEY (`sku_id`) REFERENCES `product_skus` (`id`),
                             CONSTRAINT `fk_inventory_warehouse` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存表';

-- 库存变动记录表
CREATE TABLE `inventory_logs` (
                                  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `sku_id` BIGINT UNSIGNED NOT NULL COMMENT 'SKU ID',
                                  `warehouse_id` BIGINT UNSIGNED NOT NULL COMMENT '仓库ID',
                                  `change_type` VARCHAR(30) NOT NULL COMMENT '变动类型：IN/OUT/LOCK/UNLOCK/ADJUST',
                                  `change_quantity` INT NOT NULL COMMENT '变动数量（正负数）',
                                  `before_quantity` INT NOT NULL COMMENT '变动前库存',
                                  `after_quantity` INT NOT NULL COMMENT '变动后库存',
                                  `reference_type` VARCHAR(30) DEFAULT NULL COMMENT '关联类型：ORDER/RETURN/PURCHASE',
                                  `reference_no` VARCHAR(50) DEFAULT NULL COMMENT '关联单号',
                                  `operator_id` BIGINT DEFAULT NULL COMMENT '操作人ID',
                                  `operator_name` VARCHAR(50) DEFAULT NULL COMMENT '操作人姓名',
                                  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
                                  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  PRIMARY KEY (`id`),
                                  KEY `idx_sku_id` (`sku_id`),
                                  KEY `idx_warehouse_id` (`warehouse_id`),
                                  KEY `idx_change_type` (`change_type`),
                                  KEY `idx_reference` (`reference_type`, `reference_no`),
                                  KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='库存变动日志表';
