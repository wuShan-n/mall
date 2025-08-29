-- 9.1 索引策略
-- 索引设计的核心原则是平衡查询性能和写入性能。以下是关键表的索引策略：

-- 订单表的复合索引优化
ALTER TABLE `orders`
    ADD INDEX `idx_user_status_time` (`user_id`, `status`, `created_at`),
ADD INDEX `idx_payment_method_time` (`payment_method`, `payment_time`);

-- 商品表的全文索引（用于搜索）
ALTER TABLE `products`
    ADD FULLTEXT INDEX `ft_search` (`product_name`, `keywords`, `description`);

-- 库存表的唯一索引防止重复
ALTER TABLE `inventory`
    ADD UNIQUE INDEX `uk_sku_warehouse` (`sku_id`, `warehouse_id`);

-- 用户行为表的时间分区索引
ALTER TABLE `user_behavior_logs`
    ADD INDEX `idx_user_behavior_time` (`user_id`, `behavior_type`, `created_at`);


-- 9.2 分库分表策略
-- 当数据量达到一定规模时，需要进行水平拆分：

-- 订单表分表策略（按用户ID取模）
-- orders_0, orders_1, orders_2, ... orders_63
-- 分表规则：user_id % 64

-- 用户行为日志表分区策略（按时间范围）
-- 自动按月分区，保留最近12个月的数据

-- 示例：创建分区表
CREATE TABLE `orders_0` LIKE `orders`;
CREATE TABLE `orders_1` LIKE `orders`;
-- ... 继续创建到 orders_63


-- 9.3 读写分离配置
sql-- 主库用于写操作
-- 从库用于读操作，特别是报表查询

-- 创建只读用户（从库）
CREATE USER 'readonly'@'%' IDENTIFIED BY 'password';
GRANT SELECT ON ecommerce.* TO 'readonly'@'%';

-- 创建读写用户（主库）
CREATE USER 'readwrite'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON ecommerce.* TO 'readwrite'@'%';
