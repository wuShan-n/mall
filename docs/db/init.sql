-- 使用 mall_product 数据库
USE `mall_product`;

-- ----------------------------
-- 1. 补充更多品牌和分类
-- ----------------------------
INSERT INTO `product_brand` (`id`, `brand_name`, `brand_code`, `logo`, `description`, `sort`) VALUES
                                                                                                  (101, 'Dell', 'DELL', 'https://example.com/dell-logo.png', 'Dell Technologies', 5),
                                                                                                  (102, 'HP', 'HP', 'https://example.com/hp-logo.png', 'Hewlett-Packard', 6),
                                                                                                  (103, 'Levi''s', 'LEVIS', 'https://example.com/levis-logo.png', 'Levi Strauss & Co.', 7);

INSERT INTO `product_category` (`id`, `parent_id`, `category_name`, `category_code`, `level`, `sort`) VALUES
                                                                                                          (101, 2, 'iPhone Series', 'IPHONE', 3, 1),
                                                                                                          (102, 2, 'Galaxy Series', 'GALAXY', 3, 2),
                                                                                                          (103, 3, 'MacBook Pro', 'MBP', 3, 1),
                                                                                                          (104, 5, 'T-Shirts', 'M_TSHIRT', 3, 1),
                                                                                                          (105, 5, 'Jeans', 'M_JEANS', 3, 2);

-- ----------------------------
-- 2. 为分类定义商品属性 (规格和参数)
-- ----------------------------
-- 为 "Smartphones" (分类ID=2) 定义属性
INSERT INTO `product_attribute` (`id`, `category_id`, `attr_name`, `attr_type`, `input_type`, `values`) VALUES
                                                                                                            (201, 2, '颜色', 0, 1, '黑色,白色,金色,远峰蓝'),  -- 规格 (attr_type=0)
                                                                                                            (202, 2, '存储容量', 0, 1, '128GB,256GB,512GB,1TB'), -- 规格 (attr_type=0)
                                                                                                            (203, 2, '屏幕尺寸', 1, 0, NULL),                 -- 参数 (attr_type=1)
                                                                                                            (204, 2, '摄像头像素', 1, 0, NULL);                -- 参数 (attr_type=1)

-- 为 "Laptops" (分类ID=3) 定义属性
INSERT INTO `product_attribute` (`id`, `category_id`, `attr_name`, `attr_type`, `input_type`, `values`) VALUES
                                                                                                            (301, 3, '内存', 0, 1, '16GB,32GB,64GB'), -- 规格
                                                                                                            (302, 3, '硬盘容量', 0, 1, '512GB SSD,1TB SSD,2TB SSD'), -- 规格
                                                                                                            (303, 3, '颜色', 0, 1, '深空灰,银色'), -- 规格
                                                                                                            (304, 3, '处理器', 1, 0, NULL), -- 参数
                                                                                                            (305, 3, '显卡', 1, 0, NULL); -- 参数

-- 为 "T-Shirts" (分类ID=104) 定义属性
INSERT INTO `product_attribute` (`id`, `category_id`, `attr_name`, `attr_type`, `input_type`, `values`) VALUES
                                                                                                            (401, 104, '颜色', 0, 1, '红色,蓝色,白色,黑色'), -- 规格
                                                                                                            (402, 104, '尺码', 0, 1, 'S,M,L,XL,XXL'),  -- 规格
                                                                                                            (403, 104, '材质', 1, 0, NULL);  -- 参数


-- ----------------------------
-- 3. 创建商品SPU (Standard Product Unit)
-- ----------------------------
-- SPU 1: iPhone 16 Pro
INSERT INTO `product_spu` (`id`, `product_name`, `product_code`, `category_id`, `brand_id`, `main_image`, `introduction`, `keywords`, `status`, `audit_status`, `detail_html`) VALUES
    (1001, 'Apple iPhone 16 Pro', 'SPU_IP16P', 101, 1, 'https://example.com/iphone16pro.jpg', '全新一代 iPhone 16 Pro，更快的芯片，更强的摄像头。', 'iPhone,Apple,手机', 1, 1, '<div>iPhone 16 Pro 详细介绍...</div>');

-- SPU 2: MacBook Pro 14 inch
INSERT INTO `product_spu` (`id`, `product_name`, `product_code`, `category_id`, `brand_id`, `main_image`, `introduction`, `keywords`, `status`, `audit_status`, `detail_html`) VALUES
    (1002, 'Apple MacBook Pro 14 inch M5', 'SPU_MBP14_M5', 103, 1, 'https://example.com/mbp14.jpg', '搭载M5芯片的MacBook Pro，为专业人士打造。', 'MacBook,Apple,笔记本电脑', 1, 1, '<div>MacBook Pro 14 M5 详细介绍...</div>');

-- SPU 3: Nike Sport T-Shirt
INSERT INTO `product_spu` (`id`, `product_name`, `product_code`, `category_id`, `brand_id`, `main_image`, `introduction`, `keywords`, `status`, `audit_status`, `detail_html`) VALUES
    (1003, 'Nike Sport T-Shirt', 'SPU_NK_TS01', 104, 3, 'https://example.com/nike-tshirt.jpg', '透气舒适的运动T恤，适合各种运动场景。', 'Nike,T恤,运动', 1, 1, '<div>Nike 运动T恤详细介绍...</div>');


-- ----------------------------
-- 4. 为SPU设置固定参数 (对应 attr_type=1)
-- ----------------------------
-- iPhone 16 Pro 的参数
INSERT INTO `product_spu_attribute` (`id`, `spu_id`, `attribute_id`, `attribute_name`, `attribute_value`) VALUES
                                                                                                              (10011, 1001, 203, '屏幕尺寸', '6.1英寸'),
                                                                                                              (10012, 1001, 204, '摄像头像素', '4800万');

-- MacBook Pro 14 inch 的参数
INSERT INTO `product_spu_attribute` (`id`, `spu_id`, `attribute_id`, `attribute_name`, `attribute_value`) VALUES
                                                                                                              (10021, 1002, 304, '处理器', 'Apple M5 Pro'),
                                                                                                              (10022, 1002, 305, '显卡', '集成18核GPU');

-- Nike Sport T-Shirt 的参数
INSERT INTO `product_spu_attribute` (`id`, `spu_id`, `attribute_id`, `attribute_name`, `attribute_value`) VALUES
    (10031, 1003, 403, '材质', '95%棉, 5%氨纶');


-- ----------------------------
-- 5. 创建商品SKU (Stock Keeping Unit), 这是SPU的具体售卖规格
-- ----------------------------
-- iPhone 16 Pro (SPU_ID=1001) 的 SKU
INSERT INTO `product_sku` (`id`, `spu_id`, `sku_name`, `sku_code`, `price`, `original_price`, `stock`, `specs`, `image`) VALUES
                                                                                                                             (100101, 1001, 'iPhone 16 Pro 远峰蓝 256GB', 'SKU_IP16P_BLUE_256', 8999.00, 8999.00, 100, '{"颜色": "远峰蓝", "存储容量": "256GB"}', 'https://example.com/iphone16pro-blue.jpg'),
                                                                                                                             (100102, 1001, 'iPhone 16 Pro 远峰蓝 512GB', 'SKU_IP16P_BLUE_512', 9999.00, 9999.00, 80, '{"颜色": "远峰蓝", "存储容量": "512GB"}', 'https://example.com/iphone16pro-blue.jpg'),
                                                                                                                             (100103, 1001, 'iPhone 16 Pro 金色 256GB', 'SKU_IP16P_GOLD_256', 8999.00, 8999.00, 120, '{"颜色": "金色", "存储容量": "256GB"}', 'https://example.com/iphone16pro-gold.jpg'),
                                                                                                                             (100104, 1001, 'iPhone 16 Pro 金色 512GB', 'SKU_IP16P_GOLD_512', 9999.00, 9999.00, 50, '{"颜色": "金色", "存储容量": "512GB"}', 'https://example.com/iphone16pro-gold.jpg');

-- MacBook Pro 14 inch (SPU_ID=1002) 的 SKU
INSERT INTO `product_sku` (`id`, `spu_id`, `sku_name`, `sku_code`, `price`, `original_price`, `stock`, `specs`, `image`) VALUES
                                                                                                                             (100201, 1002, 'MacBook Pro 14 M5 16GB 512GB SSD 深空灰', 'SKU_MBP14_16_512_GRAY', 19999.00, 19999.00, 60, '{"内存": "16GB", "硬盘容量": "512GB SSD", "颜色": "深空灰"}', 'https://example.com/mbp14-gray.jpg'),
                                                                                                                             (100202, 1002, 'MacBook Pro 14 M5 16GB 1TB SSD 深空灰', 'SKU_MBP14_16_1T_GRAY', 21999.00, 21999.00, 40, '{"内存": "16GB", "硬盘容量": "1TB SSD", "颜色": "深空灰"}', 'https://example.com/mbp14-gray.jpg'),
                                                                                                                             (100203, 1002, 'MacBook Pro 14 M5 32GB 1TB SSD 银色', 'SKU_MBP14_32_1T_SILVER', 24999.00, 24999.00, 20, '{"内存": "32GB", "硬盘容量": "1TB SSD", "颜色": "银色"}', 'https://example.com/mbp14-silver.jpg');

-- Nike Sport T-Shirt (SPU_ID=1003) 的 SKU
INSERT INTO `product_sku` (`id`, `spu_id`, `sku_name`, `sku_code`, `price`, `original_price`, `stock`, `specs`, `image`) VALUES
                                                                                                                             (100301, 1003, 'Nike Sport T-Shirt 红色 S', 'SKU_NK_TS01_RED_S', 299.00, 329.00, 200, '{"颜色": "红色", "尺码": "S"}', 'https://example.com/nike-tshirt-red.jpg'),
                                                                                                                             (100302, 1003, 'Nike Sport T-Shirt 红色 M', 'SKU_NK_TS01_RED_M', 299.00, 329.00, 300, '{"颜色": "红色", "尺码": "M"}', 'https://example.com/nike-tshirt-red.jpg'),
                                                                                                                             (100303, 1003, 'Nike Sport T-Shirt 黑色 M', 'SKU_NK_TS01_BLK_M', 299.00, 329.00, 500, '{"颜色": "黑色", "尺码": "M"}', 'https://example.com/nike-tshirt-black.jpg'),
                                                                                                                             (100304, 1003, 'Nike Sport T-Shirt 黑色 L', 'SKU_NK_TS01_BLK_L', 299.00, 329.00, 400, '{"颜色": "黑色", "尺码": "L"}', 'https://example.com/nike-tshirt-black.jpg');




-- 使用 mall_inventory 数据库
USE `mall_inventory`;

-- ----------------------------
-- 1. 插入仓库信息 (inv_warehouse)
-- ----------------------------
INSERT INTO `inv_warehouse` (`id`, `warehouse_name`, `warehouse_code`, `warehouse_type`, `contact_person`, `contact_phone`, `province`, `city`, `district`, `address`, `status`) VALUES
                                                                                                                                                                                     (101, '华东主仓', 'HD-SH-01', 1, '张三', '13800138001', '上海市', '上海市', '嘉定区', '某某公路1号', 1),
                                                                                                                                                                                     (102, '华南分仓', 'HN-GZ-01', 2, '李四', '13800138002', '广东省', '广州市', '白云区', '某某大道2号', 1),
                                                                                                                                                                                     (103, '华北中转仓', 'HB-BJ-01', 3, '王五', '13800138003', '北京市', '北京市', '顺义区', '某某路3号', 1);

-- ----------------------------
-- 2. 初始化各仓库的商品SKU库存 (inv_stock)
-- 假设SKU ID是基于之前product服务生成的
-- 我们将模拟部分商品在多个仓库都有库存
-- ----------------------------
-- iPhone 16 Pro 远峰蓝 256GB (SKU ID: 100101)
INSERT INTO `inv_stock` (`id`, `sku_id`, `warehouse_id`, `total_stock`, `available_stock`, `locked_stock`, `warn_stock`, `safety_stock`, `version`) VALUES
                                                                                                                                                        (201, 100101, 101, 100, 95, 5, 10, 5, 0), -- 华东仓有100件，锁定5件
                                                                                                                                                        (202, 100101, 102, 50, 50, 0, 10, 5, 0);  -- 华南仓有50件

-- iPhone 16 Pro 金色 256GB (SKU ID: 100103)
INSERT INTO `inv_stock` (`id`, `sku_id`, `warehouse_id`, `total_stock`, `available_stock`, `locked_stock`, `warn_stock`, `safety_stock`, `version`) VALUES
    (203, 100103, 101, 120, 120, 0, 10, 5, 0); -- 华东仓有120件

-- MacBook Pro 14 M5 16GB 512GB SSD 深空灰 (SKU ID: 100201)
INSERT INTO `inv_stock` (`id`, `sku_id`, `warehouse_id`, `total_stock`, `available_stock`, `locked_stock`, `warn_stock`, `safety_stock`, `version`) VALUES
    (204, 100201, 101, 60, 58, 2, 5, 2, 0); -- 华东仓有60台，锁定2台

-- Nike Sport T-Shirt 黑色 L (SKU ID: 100304)
INSERT INTO `inv_stock` (`id`, `sku_id`, `warehouse_id`, `total_stock`, `available_stock`, `locked_stock`, `warn_stock`, `safety_stock`, `version`) VALUES
                                                                                                                                                        (205, 100304, 101, 300, 300, 0, 20, 10, 0), -- 华东仓
                                                                                                                                                        (206, 100304, 102, 400, 390, 10, 20, 10, 0);-- 华南仓有400件，锁定10件


-- ----------------------------
-- 3. 模拟订单，创建库存锁定记录 (inv_stock_lock)
-- 这里的记录需要和inv_stock表中的locked_stock数量对应
-- ----------------------------
-- 订单ORD001锁定了5件iPhone (SKU 100101), 2台MacBook (SKU 100201) 均在华东仓
INSERT INTO `inv_stock_lock` (`id`, `sku_id`, `warehouse_id`, `order_no`, `user_id`, `locked_quantity`, `lock_status`, `lock_time`, `expire_time`) VALUES
                                                                                                                                                       (301, 100101, 101, 'ORD001', 1, 5, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 MINUTE)),
                                                                                                                                                       (302, 100201, 101, 'ORD001', 1, 2, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 MINUTE));

-- 订单ORD002锁定了10件T恤 (SKU 100304) 在华南仓
INSERT INTO `inv_stock_lock` (`id`, `sku_id`, `warehouse_id`, `order_no`, `user_id`, `locked_quantity`, `lock_status`, `lock_time`, `expire_time`) VALUES
    (303, 100304, 102, 'ORD002', 2, 10, 1, NOW(), DATE_ADD(NOW(), INTERVAL 30 MINUTE));


-- ----------------------------
-- 4. 生成库存变更流水记录 (inv_stock_record)
-- 记录了库存从0到初始值，以及被订单锁定的过程
-- ----------------------------
-- 初始入库流水
INSERT INTO `inv_stock_record` (`id`, `sku_id`, `warehouse_id`, `operation_type`, `change_quantity`, `stock_before`, `stock_after`, `business_type`, `business_no`, `remark`, `operator_id`, `operator_name`) VALUES
                                                                                                                                                                                                                  (401, 100101, 101, 1, 100, 0, 100, '采购入库', 'PO20250901001', '新品上架', 10, 'Admin'),
                                                                                                                                                                                                                  (402, 100101, 102, 1, 50, 0, 50, '调拨入库', 'TR20250901002', '从华东仓调拨', 10, 'Admin'),
                                                                                                                                                                                                                  (403, 100103, 101, 1, 120, 0, 120, '采购入库', 'PO20250901003', '新品上架', 10, 'Admin'),
                                                                                                                                                                                                                  (404, 100201, 101, 1, 60, 0, 60, '采购入库', 'PO20250901004', '新品上架', 10, 'Admin'),
                                                                                                                                                                                                                  (405, 100304, 101, 1, 300, 0, 300, '采购入库', 'PO20250901005', '新品上架', 10, 'Admin'),
                                                                                                                                                                                                                  (406, 100304, 102, 1, 400, 0, 400, '采购入库', 'PO20250901006', '新品上架', 10, 'Admin');

-- 订单锁定流水
INSERT INTO `inv_stock_record` (`id`, `sku_id`, `warehouse_id`, `operation_type`, `change_quantity`, `stock_before`, `stock_after`, `business_type`, `business_no`, `remark`, `operator_id`, `operator_name`) VALUES
                                                                                                                                                                                                                  (407, 100101, 101, 3, 5, 100, 95, '订单锁定', 'ORD001', '用户1下单', 0, 'System'),
                                                                                                                                                                                                                  (408, 100201, 101, 3, 2, 60, 58, '订单锁定', 'ORD001', '用户1下单', 0, 'System');


                                                                                                                                                                                                                  -- 使用 mall_order 数据库
USE `mall_order`;

-- ----------------------------
-- 场景1: 一个完整的、已完成的订单
-- ----------------------------
-- 订单主表 (oms_order)
INSERT INTO `oms_order` (
    `id`, `order_no`, `user_id`, `username`, `total_amount`, `product_amount`, `freight_amount`, `discount_amount`, `pay_amount`,
    `payment_type`, `payment_time`, `payment_no`, `status`, `source_type`, `confirm_status`,
    `delivery_company`, `delivery_no`, `delivery_time`, `receive_time`,
    `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
    `remark`, `create_time`, `update_time`
) VALUES (
             1001, 'ORD20250901001', 1, 'customer_a', 9298.00, 9298.00, 0.00, 50.00, 9248.00,
             1, '2025-09-01 10:05:00', 'PAY20250901001', 3, 1, 1,
             '顺丰速运', 'SF123456789', '2025-09-01 18:30:00', '2025-09-03 14:00:00',
             '张三', '13800138001', '上海市', '上海市', '浦东新区', '世纪大道88号',
             '请尽快发货', '2025-09-01 10:00:00', '2025-09-03 14:00:00'
         );

-- 订单商品详情表 (oms_order_item)
INSERT INTO `oms_order_item` (
    `id`, `order_id`, `order_no`, `spu_id`, `sku_id`, `product_name`, `product_image`, `product_specs`,
    `price`, `quantity`, `total_amount`, `real_amount`
) VALUES
      (10011, 1001, 'ORD20250901001', 1001, 100101, 'Apple iPhone 16 Pro 远峰蓝 256GB', 'https://example.com/iphone16pro-blue.jpg', '{"颜色": "远峰蓝", "存储容量": "256GB"}', 8999.00, 1, 8999.00, 8999.00),
      (10012, 1001, 'ORD20250901001', 1003, 100301, 'Nike Sport T-Shirt 红色 S', 'https://example.com/nike-tshirt-red.jpg', '{"颜色": "红色", "尺码": "S"}', 299.00, 1, 299.00, 249.00); -- 假设这件商品优惠了50元

-- 订单操作记录表 (oms_order_operate_history)
INSERT INTO `oms_order_operate_history` (`order_id`, `order_no`, `operate_type`, `operate_man`, `order_status`, `note`) VALUES
                                                                                                                            (1001, 'ORD20250901001', '创建订单', 'customer_a', 0, '用户提交订单'),
                                                                                                                            (1001, 'ORD20250901001', '支付订单', 'customer_a', 1, '用户完成支付'),
                                                                                                                            (1001, 'ORD20250901001', '后台发货', 'Admin', 2, '订单已从上海仓发出'),
                                                                                                                            (1001, 'ORD20250901001', '用户确认收货', 'customer_a', 3, '用户已签收');


-- ----------------------------
-- 场景2: 一个已支付、待发货的订单
-- ----------------------------
INSERT INTO `oms_order` (
    `id`, `order_no`, `user_id`, `username`, `total_amount`, `product_amount`, `freight_amount`, `pay_amount`,
    `payment_type`, `payment_time`, `payment_no`, `status`, `source_type`, `confirm_status`,
    `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`, `create_time`
) VALUES (
             1002, 'ORD20250902001', 2, 'customer_b', 19999.00, 19999.00, 0.00, 19999.00,
             2, '2025-09-02 11:30:15', 'PAY20250902001', 1, 2, 0,
             '李四', '13900139002', '广东省', '广州市', '天河区', '珠江新城100号', '2025-09-02 11:25:00'
         );

INSERT INTO `oms_order_item` (`id`, `order_id`, `order_no`, `spu_id`, `sku_id`, `product_name`, `product_image`, `product_specs`, `price`, `quantity`, `total_amount`, `real_amount`) VALUES
    (10021, 1002, 'ORD20250902001', 1002, 100201, 'MacBook Pro 14 M5 16GB 512GB SSD 深空灰', 'https://example.com/mbp14-gray.jpg', '{"内存": "16GB", "硬盘容量": "512GB SSD", "颜色": "深空灰"}', 19999.00, 1, 19999.00, 19999.00);

INSERT INTO `oms_order_operate_history` (`order_id`, `order_no`, `operate_type`, `operate_man`, `order_status`, `note`) VALUES
                                                                                                                            (1002, 'ORD20250902001', '创建订单', 'customer_b', 0, '用户提交订单'),
                                                                                                                            (1002, 'ORD20250902001', '支付订单', 'customer_b', 1, '用户完成支付');


-- ----------------------------
-- 场景3: 一个超时未支付、已关闭的订单
-- ----------------------------
INSERT INTO `oms_order` (
    `id`, `order_no`, `user_id`, `username`, `total_amount`, `product_amount`, `freight_amount`, `pay_amount`,
    `status`, `source_type`, `confirm_status`,
    `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
    `expire_time`, `create_time`
) VALUES (
             1003, 'ORD20250830001', 3, 'customer_c', 299.00, 299.00, 0.00, 299.00,
             4, 3, 0,
             '王五', '13700137003', '北京市', '北京市', '朝阳区', '三里屯路20号',
             '2025-08-30 15:30:00', '2025-08-30 15:00:00'
         );

INSERT INTO `oms_order_item` (`id`, `order_id`, `order_no`, `spu_id`, `sku_id`, `product_name`, `product_image`, `product_specs`, `price`, `quantity`, `total_amount`, `real_amount`) VALUES
    (10031, 1003, 'ORD20250830001', 1003, 100304, 'Nike Sport T-Shirt 黑色 L', 'https://example.com/nike-tshirt-black.jpg', '{"颜色": "黑色", "尺码": "L"}', 299.00, 1, 299.00, 299.00);

INSERT INTO `oms_order_operate_history` (`order_id`, `order_no`, `operate_type`, `operate_man`, `order_status`, `note`) VALUES
                                                                                                                            (1003, 'ORD20250830001', '创建订单', 'customer_c', 0, '用户提交订单'),
                                                                                                                            (1003, 'ORD20250830001', '系统自动关闭', 'System', 4, '订单超时未支付');


-- ----------------------------
-- 场景4: 一个已完成、且有退款申请的订单
-- ----------------------------
INSERT INTO `oms_order` (
    `id`, `order_no`, `user_id`, `username`, `total_amount`, `product_amount`, `freight_amount`, `pay_amount`,
    `payment_type`, `payment_time`, `payment_no`, `status`, `source_type`, `confirm_status`,
    `delivery_company`, `delivery_no`, `delivery_time`, `receive_time`,
    `receiver_name`, `receiver_phone`, `receiver_province`, `receiver_city`, `receiver_district`, `receiver_address`,
    `create_time`
) VALUES (
             1004, 'ORD20250825001', 1, 'customer_a', 9298.00, 9298.00, 0.00, 9298.00,
             1, '2025-08-25 09:05:00', 'PAY20250825001', 3, 1, 1,
             '京东物流', 'JD123456789', '2025-08-25 19:00:00', '2025-08-28 11:00:00',
             '张三', '13800138001', '上海市', '上海市', '浦东新区', '世纪大道88号', '2025-08-25 09:00:00'
         );

INSERT INTO `oms_order_item` (
    `id`, `order_id`, `order_no`, `spu_id`, `sku_id`, `product_name`, `product_image`, `product_specs`,
    `price`, `quantity`, `total_amount`, `real_amount`, `refund_status`
) VALUES
      (10041, 1004, 'ORD20250825001', 1001, 100101, 'Apple iPhone 16 Pro 远峰蓝 256GB', 'https://example.com/iphone16pro-blue.jpg', '{"颜色": "远峰蓝", "存储容量": "256GB"}', 8999.00, 1, 8999.00, 8999.00, 0),
      (10042, 1004, 'ORD20250825001', 1003, 100304, 'Nike Sport T-Shirt 黑色 L', 'https://example.com/nike-tshirt-black.jpg', '{"颜色": "黑色", "尺码": "L"}', 299.00, 1, 299.00, 299.00, 1); -- 此商品状态为退款中

-- 退货申请表 (oms_order_refund)
INSERT INTO `oms_order_refund` (
    `id`, `refund_no`, `order_id`, `order_no`, `user_id`, `refund_type`, `refund_amount`, `refund_reason`,
    `description`, `proof_pics`, `status`, `create_time`
) VALUES (
             2001, 'REF20250829001', 1004, 'ORD20250825001', 1, 1, 299.00, '不想要了',
             '买错了，想换个颜色', 'https://example.com/proof1.jpg,https://example.com/proof2.jpg', 0, '2025-08-29 10:00:00'
         );

-- 操作记录
INSERT INTO `oms_order_operate_history` (`order_id`, `order_no`, `operate_type`, `operate_man`, `order_status`, `note`) VALUES
                                                                                                                            (1004, 'ORD20250825001', '创建订单', 'customer_a', 0, '用户提交订单'),
                                                                                                                            (1004, 'ORD20250825001', '支付订单', 'customer_a', 1, '用户完成支付'),
                                                                                                                            (1004, 'ORD20250825001', '后台发货', 'Admin', 2, '订单已发出'),
                                                                                                                            (1004, 'ORD20250825001', '用户确认收货', 'customer_a', 3, '用户已签收'),
                                                                                                                            (1004, 'ORD20250825001', '发起售后', 'customer_a', 6, '用户针对商品[Nike Sport T-Shirt 黑色 L]发起退款');


-- 使用 mall_payment 数据库
USE `mall_payment`;

-- ----------------------------
-- 场景1: 对应已完成订单 (ORD20250901001) 的成功支付记录
-- ----------------------------
INSERT INTO `payment_order` (
    `id`, `payment_no`, `order_no`, `user_id`, `payment_type`, `payment_amount`, `status`,
    `third_party_no`, `payment_time`, `expire_time`, `callback_time`, `callback_content`, `create_time`
) VALUES (
             2001, 'PAY20250901001', 'ORD20250901001', 1, 1, 9248.00, 1,
             'ALIPAY_TRADE_NO_20250901001', '2025-09-01 10:05:00', '2025-09-01 10:30:00', '2025-09-01 10:05:30', '{"result_code":"SUCCESS", "message":"支付成功"}', '2025-09-01 10:00:00'
         );


-- ----------------------------
-- 场景2: 对应待发货订单 (ORD20250902001) 的成功支付记录
-- ----------------------------
INSERT INTO `payment_order` (
    `id`, `payment_no`, `order_no`, `user_id`, `payment_type`, `payment_amount`, `status`,
    `third_party_no`, `payment_time`, `expire_time`, `callback_time`, `callback_content`, `create_time`
) VALUES (
             2002, 'PAY20250902001', 'ORD20250902001', 2, 2, 19999.00, 1,
             'WXPAY_TRADE_NO_20250902001', '2025-09-02 11:30:15', '2025-09-02 11:55:00', '2025-09-02 11:30:45', '{"result_code":"SUCCESS", "message":"支付成功"}', '2025-09-02 11:25:00'
         );


-- ----------------------------
-- 场景3: 对应已关闭订单 (ORD20250830001) 的待支付记录
-- ----------------------------
INSERT INTO `payment_order` (
    `id`, `payment_no`, `order_no`, `user_id`, `payment_type`, `payment_amount`, `status`,
    `expire_time`, `create_time`
) VALUES (
             2003, 'PAY20250830001', 'ORD20250830001', 3, 1, 299.00, 0,
             '2025-08-30 15:30:00', '2025-08-30 15:00:00'
         );


-- ----------------------------
-- 场景4: 对应有退款申请的订单 (ORD20250825001) 的支付与退款记录
-- ----------------------------
-- 4.1 首先是一条已支付的记录，但状态最终会变为"已退款"
INSERT INTO `payment_order` (
    `id`, `payment_no`, `order_no`, `user_id`, `payment_type`, `payment_amount`, `status`,
    `third_party_no`, `payment_time`, `expire_time`, `callback_time`, `callback_content`, `create_time`
) VALUES (
             2004, 'PAY20250825001', 'ORD20250825001', 1, 1, 9298.00, 2, -- 状态为2 (已退款)
             'ALIPAY_TRADE_NO_20250825001', '2025-08-25 09:05:00', '2025-08-25 09:30:00', '2025-08-25 09:05:30', '{"result_code":"SUCCESS", "message":"支付成功"}', '2025-08-25 09:00:00'
         );

-- 4.2 然后是一条关联的退款单记录
INSERT INTO `refund_order` (
    `id`, `refund_no`, `payment_no`, `order_no`, `user_id`, `refund_amount`, `refund_reason`, `status`, `create_time`
) VALUES (
             3001, 'REF20250829001', 'PAY20250825001', 'ORD20250825001', 1, 299.00, '不想要了', 0, '2025-08-29 10:00:00' -- 状态为0 (待处理), 对应订单服务中的退款申请状态
         );