-- 创建数据库
CREATE DATABASE IF NOT EXISTS `mall_product` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `mall_product`;

-- 商品SPU表
CREATE TABLE `product_spu` (
                           `id` bigint NOT NULL COMMENT 'SPU ID',
                           `product_name` varchar(200) NOT NULL COMMENT '商品名称',
                           `product_code` varchar(100) NOT NULL COMMENT '商品编码',
                           `category_id` bigint DEFAULT NULL COMMENT '分类ID',
                           `brand_id` bigint DEFAULT NULL COMMENT '品牌ID',
                           `main_image` varchar(500) DEFAULT NULL COMMENT '主图URL',
                           `images` text COMMENT '图片列表JSON',
                           `video_url` varchar(500) DEFAULT NULL COMMENT '视频URL',
                           `unit` varchar(50) DEFAULT NULL COMMENT '单位',
                           `weight` decimal(10,2) DEFAULT NULL COMMENT '重量(kg)',
                           `introduction` text COMMENT '商品简介',
                           `keywords` varchar(500) DEFAULT NULL COMMENT '关键词',
                           `tags` varchar(500) DEFAULT NULL COMMENT '标签',
                           `sale_count` int DEFAULT '0' COMMENT '销量',
                           `view_count` int DEFAULT '0' COMMENT '浏览量',
                           `comment_count` int DEFAULT '0' COMMENT '评论数',
                           `score` decimal(3,1) DEFAULT '0.0' COMMENT '平均评分',
                           `is_new` tinyint(1) DEFAULT '0' COMMENT '是否新品',
                           `is_hot` tinyint(1) DEFAULT '0' COMMENT '是否热销',
                           `is_best` tinyint(1) DEFAULT '0' COMMENT '是否精品',
                           `status` tinyint DEFAULT '0' COMMENT '状态: 0-下架, 1-上架',
                           `audit_status` tinyint DEFAULT '0' COMMENT '审核状态: 0-待审核, 1-通过, 2-驳回',
                           `sort` int DEFAULT '0' COMMENT '排序',
                           `detail_html` longtext COMMENT 'PC端详情HTML',
                           `detail_mobile_html` longtext COMMENT '移动端详情HTML',
                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
                           `update_by` bigint DEFAULT NULL COMMENT '更新者ID',
                           `deleted` tinyint DEFAULT '0' COMMENT '删除标记',
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `uk_product_code` (`product_code`),
                           KEY `idx_category_id` (`category_id`),
                           KEY `idx_brand_id` (`brand_id`),
                           KEY `idx_status` (`status`),
                           KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品SPU表';

-- 商品SKU表
CREATE TABLE `product_sku` (
                           `id` bigint NOT NULL COMMENT 'SKU ID',
                           `spu_id` bigint NOT NULL COMMENT 'SPU ID',
                           `sku_name` varchar(200) NOT NULL COMMENT 'SKU名称',
                           `sku_code` varchar(100) NOT NULL COMMENT 'SKU编码',
                           `price` decimal(10,2) NOT NULL COMMENT '售价',
                           `original_price` decimal(10,2) DEFAULT NULL COMMENT '原价',
                           `cost_price` decimal(10,2) DEFAULT NULL COMMENT '成本价',
                           `stock` int NOT NULL DEFAULT '0' COMMENT '库存数量',
                           `lock_stock` int DEFAULT '0' COMMENT '锁定库存',
                           `warn_stock` int DEFAULT '10' COMMENT '预警库存',
                           `image` varchar(500) DEFAULT NULL COMMENT 'SKU图片',
                           `specs` json DEFAULT NULL COMMENT '规格值JSON',
                           `weight` decimal(10,2) DEFAULT NULL COMMENT '重量(kg)',
                           `volume` decimal(10,3) DEFAULT NULL COMMENT '体积(m³)',
                           `sale_count` int DEFAULT '0' COMMENT '销量',
                           `status` tinyint DEFAULT '1' COMMENT '状态: 0-禁用, 1-启用',
                           `sort` int DEFAULT '0' COMMENT '排序',
                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
                           `update_by` bigint DEFAULT NULL COMMENT '更新者ID',
                           `deleted` tinyint DEFAULT '0' COMMENT '删除标记',
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `uk_sku_code` (`sku_code`),
                           KEY `idx_spu_id` (`spu_id`),
                           KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品SKU表';

-- 商品分类表
CREATE TABLE `product_category` (
                                `id` bigint NOT NULL COMMENT '分类ID',
                                `parent_id` bigint DEFAULT '0' COMMENT '父分类ID',
                                `category_name` varchar(100) NOT NULL COMMENT '分类名称',
                                `category_code` varchar(50) NOT NULL COMMENT '分类编码',
                                `level` int NOT NULL COMMENT '分类层级',
                                `sort` int DEFAULT '0' COMMENT '排序',
                                `icon` varchar(500) DEFAULT NULL COMMENT '图标URL',
                                `description` varchar(500) DEFAULT NULL COMMENT '描述',
                                `status` tinyint DEFAULT '1' COMMENT '状态: 0-禁用, 1-启用',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
                                `update_by` bigint DEFAULT NULL COMMENT '更新者ID',
                                `deleted` tinyint DEFAULT '0' COMMENT '删除标记',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_category_code` (`category_code`),
                                KEY `idx_parent_id` (`parent_id`),
                                KEY `idx_level` (`level`),
                                KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品分类表';

-- 商品品牌表
CREATE TABLE `product_brand` (
                             `id` bigint NOT NULL COMMENT '品牌ID',
                             `brand_name` varchar(100) NOT NULL COMMENT '品牌名称',
                             `brand_code` varchar(50) NOT NULL COMMENT '品牌编码',
                             `logo` varchar(500) DEFAULT NULL COMMENT 'LOGO URL',
                             `description` varchar(500) DEFAULT NULL COMMENT '描述',
                             `sort` int DEFAULT '0' COMMENT '排序',
                             `status` tinyint DEFAULT '1' COMMENT '状态: 0-禁用, 1-启用',
                             `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
                             `update_by` bigint DEFAULT NULL COMMENT '更新者ID',
                             `deleted` tinyint DEFAULT '0' COMMENT '删除标记',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `uk_brand_code` (`brand_code`),
                             KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品品牌表';

-- 商品属性表
CREATE TABLE `product_attribute` (
                                 `id` bigint NOT NULL COMMENT '属性ID',
                                 `category_id` bigint NOT NULL COMMENT '分类ID',
                                 `attr_name` varchar(100) NOT NULL COMMENT '属性名',
                                 `attr_type` tinyint NOT NULL COMMENT '类型: 0-规格, 1-参数',
                                 `input_type` tinyint DEFAULT '0' COMMENT '录入方式: 0-手动, 1-选择',
                                 `values` varchar(1000) DEFAULT NULL COMMENT '可选值(逗号分隔)',
                                 `unit` varchar(50) DEFAULT NULL COMMENT '单位',
                                 `is_required` tinyint(1) DEFAULT '0' COMMENT '是否必填',
                                 `is_search` tinyint(1) DEFAULT '0' COMMENT '是否可搜索',
                                 `sort` int DEFAULT '0' COMMENT '排序',
                                 `status` tinyint DEFAULT '1' COMMENT '状态: 0-禁用, 1-启用',
                                 `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `create_by` bigint DEFAULT NULL COMMENT '创建者ID',
                                 `update_by` bigint DEFAULT NULL COMMENT '更新者ID',
                                 `deleted` tinyint DEFAULT '0' COMMENT '删除标记',
                                 PRIMARY KEY (`id`),
                                 KEY `idx_category_id` (`category_id`),
                                 KEY `idx_attr_type` (`attr_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品属性表';

-- 商品SPU属性值表
CREATE TABLE `product_spu_attribute` (
                                     `id` bigint NOT NULL COMMENT 'ID',
                                     `spu_id` bigint NOT NULL COMMENT 'SPU ID',
                                     `attribute_id` bigint NOT NULL COMMENT '属性ID',
                                     `attribute_name` varchar(100) NOT NULL COMMENT '属性名',
                                     `attribute_value` varchar(500) DEFAULT NULL COMMENT '属性值',
                                     `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     PRIMARY KEY (`id`),
                                     KEY `idx_spu_id` (`spu_id`),
                                     KEY `idx_attribute_id` (`attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品SPU属性值表';

