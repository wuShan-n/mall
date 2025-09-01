-- ----------------------------
-- Table structure for product_category
-- ----------------------------
CREATE TABLE `product_category` (
                                    `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `parent_id` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '父分类ID',
                                    `category_name` VARCHAR(50) NOT NULL COMMENT '分类名称',
                                    `category_code` VARCHAR(50) NOT NULL COMMENT '分类编码',
                                    `level` TINYINT(1) NOT NULL COMMENT '层级',
                                    `sort` INT(11) NOT NULL DEFAULT '0' COMMENT '排序',
                                    `icon` VARCHAR(255) DEFAULT NULL COMMENT '图标',
                                    `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
                                    `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态',
                                    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `uk_category_code` (`category_code`),
                                    KEY `idx_parent_id` (`parent_id`),
                                    KEY `idx_status_sort` (`status`, `sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
CREATE TABLE `product_brand` (
                                 `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `brand_name` VARCHAR(50) NOT NULL COMMENT '品牌名称',
                                 `brand_code` VARCHAR(50) NOT NULL COMMENT '品牌编码',
                                 `logo` VARCHAR(255) DEFAULT NULL COMMENT 'LOGO',
                                 `description` VARCHAR(500) DEFAULT NULL COMMENT '描述',
                                 `sort` INT(11) NOT NULL DEFAULT '0' COMMENT '排序',
                                 `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态',
                                 `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='品牌表';

-- ----------------------------
-- Table structure for product_spu
-- ----------------------------
CREATE TABLE `product_spu` (
                               `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `product_name` VARCHAR(200) NOT NULL COMMENT '商品名称',
                               `product_code` VARCHAR(50) NOT NULL COMMENT '商品编码',
                               `category_id` BIGINT(20) NOT NULL COMMENT '分类ID',
                               `brand_id` BIGINT(20) DEFAULT NULL COMMENT '品牌ID',
                               `main_image` VARCHAR(255) NOT NULL COMMENT '主图',
                               `images` JSON DEFAULT NULL COMMENT '图片列表',
                               `video_url` VARCHAR(255) DEFAULT NULL COMMENT '视频URL',
                               `unit` VARCHAR(20) DEFAULT NULL COMMENT '单位',
                               `weight` DECIMAL(10,2) DEFAULT NULL COMMENT '重量(kg)',
                               `introduction` TEXT COMMENT '商品简介',
                               `keywords` VARCHAR(200) DEFAULT NULL COMMENT '关键词',
                               `tags` VARCHAR(200) DEFAULT NULL COMMENT '标签',
                               `sale_count` INT(11) NOT NULL DEFAULT '0' COMMENT '销量',
                               `view_count` INT(11) NOT NULL DEFAULT '0' COMMENT '浏览量',
                               `comment_count` INT(11) NOT NULL DEFAULT '0' COMMENT '评论数',
                               `score` DECIMAL(2,1) DEFAULT '5.0' COMMENT '评分',
                               `is_new` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否新品',
                               `is_hot` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否热销',
                               `is_best` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否精品',
                               `status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '状态:0-下架,1-上架',
                               `audit_status` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '审核状态',
                               `sort` INT(11) NOT NULL DEFAULT '0' COMMENT '排序',
                               `deleted` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
                               `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_product_code` (`product_code`),
                               KEY `idx_category_id` (`category_id`),
                               KEY `idx_brand_id` (`brand_id`),
                               KEY `idx_status` (`status`),
                               KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品SPU表';

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
CREATE TABLE `product_sku` (
                               `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `spu_id` BIGINT(20) NOT NULL COMMENT 'SPU ID',
                               `sku_name` VARCHAR(200) NOT NULL COMMENT 'SKU名称',
                               `sku_code` VARCHAR(50) NOT NULL COMMENT 'SKU编码',
                               `price` DECIMAL(10,2) NOT NULL COMMENT '售价',
                               `original_price` DECIMAL(10,2) DEFAULT NULL COMMENT '原价',
                               `cost_price` DECIMAL(10,2) DEFAULT NULL COMMENT '成本价',
                               `stock` INT(11) NOT NULL DEFAULT '0' COMMENT '库存',
                               `warn_stock` INT(11) DEFAULT '10' COMMENT '预警库存',
                               `image` VARCHAR(255) DEFAULT NULL COMMENT 'SKU图片',
                               `specs` JSON DEFAULT NULL COMMENT '规格参数',
                               `weight` DECIMAL(10,2) DEFAULT NULL COMMENT '重量',
                               `volume` DECIMAL(10,2) DEFAULT NULL COMMENT '体积',
                               `sale_count` INT(11) NOT NULL DEFAULT '0' COMMENT '销量',
                               `lock_stock` INT(11) NOT NULL DEFAULT '0' COMMENT '锁定库存',
                               `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态',
                               `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               PRIMARY KEY (`id`),
                               UNIQUE KEY `uk_sku_code` (`sku_code`),
                               KEY `idx_spu_id` (`spu_id`),
                               KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品SKU表';

-- ----------------------------
-- Table structure for product_attribute
-- ----------------------------
CREATE TABLE `product_attribute` (
                                     `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                     `category_id` BIGINT(20) NOT NULL COMMENT '分类ID',
                                     `attr_name` VARCHAR(50) NOT NULL COMMENT '属性名',
                                     `attr_type` TINYINT(1) NOT NULL COMMENT '类型:0-规格,1-参数',
                                     `input_type` TINYINT(1) NOT NULL COMMENT '录入方式',
                                     `values` VARCHAR(500) DEFAULT NULL COMMENT '可选值列表',
                                     `unit` VARCHAR(20) DEFAULT NULL COMMENT '单位',
                                     `is_required` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否必填',
                                     `is_search` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否可搜索',
                                     `sort` INT(11) NOT NULL DEFAULT '0' COMMENT '排序',
                                     `status` TINYINT(1) NOT NULL DEFAULT '1' COMMENT '状态',
                                     `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品属性表';