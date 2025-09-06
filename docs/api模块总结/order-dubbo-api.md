# 订单服务 Dubbo API 文档

## 服务概述

订单服务(order-service)提供订单管理、退款管理、统计查询等核心功能的Dubbo RPC接口。

**服务名称**: `order-service`  
**API版本**: `1.0.0`  
**包路径**: `com.mall.api.order.dubbo`

## 服务接口清单

### 1. OrderDubboService - 订单管理服务

**接口路径**: `com.mall.api.order.dubbo.OrderDubboService`

#### 1.1 创建订单
```java
Result<OrderVO> createOrder(Long userId, OrderCreateRequest request);
```

**功能描述**: 创建订单，包括商品信息、地址信息、优惠券、积分等  
**参数说明**:
- `userId`: 用户ID
- `request`: 订单创建请求
  - `orderItems`: 订单商品列表
    - `skuId`: SKU ID
    - `quantity`: 数量
    - `cartId`: 购物车ID(可选)
  - `addressId`: 收货地址ID
  - `couponId`: 优惠券ID(可选)
  - `usePoints`: 使用积分(可选)
  - `paymentType`: 支付方式
  - `sourceType`: 订单来源 (1-PC, 2-APP, 3-小程序)
  - `remark`: 订单备注
  - `invoiceRequired`: 是否需要发票
  - `invoiceTitle`: 发票抬头
  - `invoiceTaxNumber`: 发票税号

**返回结果**: 订单信息对象

**调用示例**:
```java
@Reference
private OrderDubboService orderDubboService;

OrderCreateRequest request = new OrderCreateRequest();
List<OrderItemRequest> items = new ArrayList<>();
OrderItemRequest item = new OrderItemRequest();
item.setSkuId(1L);
item.setQuantity(2);
items.add(item);
request.setOrderItems(items);
request.setAddressId(1L);
request.setPaymentType(1); // 支付宝
request.setSourceType(2);  // APP
Result<OrderVO> result = orderDubboService.createOrder(1L, request);
```

#### 1.2 获取订单结算信息
```java
Result<OrderSettlementVO> getSettlement(Long userId, OrderSettlementRequest request);
```

**功能描述**: 获取订单结算信息，计算价格、运费、优惠等  
**参数说明**:
- `userId`: 用户ID
- `request`: 结算请求
  - `items`: 商品列表
  - `addressId`: 收货地址ID
  - `couponId`: 优惠券ID
  - `usePoints`: 使用积分

**返回结果**: 结算信息，包含商品清单、价格明细、可用优惠券等

#### 1.3 支付订单
```java
Result<OrderPaymentVO> payOrder(OrderPaymentRequest request);
```

**功能描述**: 发起订单支付  
**参数说明**:
- `request`: 支付请求
  - `orderNo`: 订单号
  - `paymentType`: 支付方式
  - `returnUrl`: 返回URL
  - `notifyUrl`: 通知URL

#### 1.4 支付回调
```java
Result<Void> paymentCallback(String orderNo, String transactionId);
```

**功能描述**: 处理支付成功回调

#### 1.5 取消订单
```java
Result<Void> cancelOrder(Long userId, OrderCancelRequest request);
```

**功能描述**: 取消订单  
**参数说明**:
- `userId`: 用户ID
- `request`: 取消请求
  - `orderNo`: 订单号
  - `reason`: 取消原因

#### 1.6 发货订单
```java
Result<Void> shipOrder(OrderShipRequest request);
```

**功能描述**: 订单发货  
**参数说明**:
- `request`: 发货请求
  - `orderNo`: 订单号
  - `deliveryCompany`: 物流公司
  - `deliveryNo`: 物流单号

#### 1.7 确认收货
```java
Result<Void> confirmReceipt(Long userId, String orderNo);
```

**功能描述**: 用户确认收货

#### 1.8 完成订单
```java
Result<Void> completeOrder(String orderNo);
```

**功能描述**: 系统完成订单(确认收货后自动调用)

#### 1.9 删除订单
```java
Result<Void> deleteOrder(Long userId, String orderNo);
```

**功能描述**: 删除订单(仅软删除)

#### 1.10 查询订单
```java
Result<OrderVO> getOrderByNo(String orderNo);
Result<OrderDetailVO> getOrderDetail(String orderNo);
Result<PageResult<OrderVO>> queryOrders(OrderQueryRequest request);
Result<PageResult<OrderVO>> getUserOrders(Long userId, Integer status, PageRequest pageRequest);
```

**功能描述**: 查询订单信息

#### 1.11 获取订单状态统计
```java
Result<Map<Integer, Long>> getOrderCountByStatus(Long userId);
```

**功能描述**: 获取用户各状态订单数量

#### 1.12 获取订单商品
```java
Result<List<OrderItemVO>> getOrderItems(String orderNo);
```

**功能描述**: 获取订单商品列表

#### 1.13 定时任务
```java
Result<Void> checkOrderTimeout();
Result<Void> autoConfirmReceipt();
Result<Void> autoCompleteOrder();
```

**功能描述**: 检查订单超时、自动确认收货、自动完成订单

#### 1.14 订单统计
```java
Result<OrderStatisticsVO> getOrderStatistics();
Result<OrderStatisticsVO> getUserOrderStatistics(Long userId);
```

**功能描述**: 获取订单统计数据

---

### 2. OrderStatisticsDubboService - 订单统计服务

**接口路径**: `com.mall.api.order.dubbo.OrderStatisticsDubboService`

#### 2.1 总体统计
```java
Result<OrderStatisticsVO> getOverallStatistics();
Result<OrderStatisticsVO> getStatisticsByDateRange(LocalDate startDate, LocalDate endDate);
```

**功能描述**: 获取总体统计数据

#### 2.2 每日/月度统计
```java
Result<Map<String, OrderStatisticsVO>> getDailyStatistics(LocalDate startDate, LocalDate endDate);
Result<Map<String, OrderStatisticsVO>> getMonthlyStatistics(Integer year);
```

**功能描述**: 获取每日或月度统计数据

#### 2.3 商品销售排行
```java
Result<List<ProductSalesVO>> getTopSellingProducts(Integer limit);
```

**功能描述**: 获取热销商品排行

#### 2.4 客户排行
```java
Result<List<CustomerOrderVO>> getTopCustomers(Integer limit);
```

**功能描述**: 获取优质客户排行

#### 2.5 分类销售统计
```java
Result<Map<String, BigDecimal>> getSalesByCategory();
```

**功能描述**: 获取按分类的销售统计

#### 2.6 订单来源分布
```java
Result<Map<String, Long>> getOrderSourceDistribution();
```

**功能描述**: 获取订单来源分布

#### 2.7 支付方式分布
```java
Result<Map<String, Long>> getPaymentTypeDistribution();
```

**功能描述**: 获取支付方式分布

#### 2.8 退款统计
```java
Result<RefundStatisticsVO> getRefundStatistics();
```

**功能描述**: 获取退款统计数据

---

### 3. RefundDubboService - 退款管理服务

**接口路径**: `com.mall.api.order.dubbo.RefundDubboService`

#### 3.1 创建退款
```java
Result<RefundVO> createRefund(Long userId, RefundCreateRequest request);
```

**功能描述**: 创建退款申请  
**参数说明**:
- `userId`: 用户ID
- `request`: 退款创建请求
  - `orderNo`: 订单号
  - `orderItemIds`: 退款商品ID列表
  - `refundType`: 退款类型 (1-仅退款, 2-退货退款)
  - `refundAmount`: 退款金额
  - `refundReason`: 退款原因
  - `description`: 退款描述
  - `proofImages`: 证明图片

**调用示例**:
```java
RefundCreateRequest request = new RefundCreateRequest();
request.setOrderNo("ORD202401010001");
request.setRefundType(1);
request.setRefundAmount(new BigDecimal("100.00"));
request.setRefundReason("商品有瑕疵");
request.setDescription("收到商品后发现有划痕");
Result<RefundVO> result = refundDubboService.createRefund(1L, request);
```

#### 3.2 取消退款
```java
Result<Void> cancelRefund(Long userId, String refundNo);
```

**功能描述**: 用户取消退款申请

#### 3.3 处理退款
```java
Result<Void> processRefund(RefundProcessRequest request);
```

**功能描述**: 客服处理退款申请  
**参数说明**:
- `request`: 退款处理请求
  - `refundNo`: 退款单号
  - `action`: 处理动作 (1-同意, 2-拒绝)
  - `handleNote`: 处理说明
  - `handlerId`: 处理人ID
  - `handlerName`: 处理人姓名

#### 3.4 审批退款
```java
Result<Void> approveRefund(String refundNo, String handleNote);
Result<Void> rejectRefund(String refundNo, String handleNote);
```

**功能描述**: 同意或拒绝退款

#### 3.5 完成退款
```java
Result<Void> completeRefund(String refundNo);
```

**功能描述**: 完成退款(退款成功后调用)

#### 3.6 查询退款
```java
Result<RefundVO> getRefundByNo(String refundNo);
Result<RefundDetailVO> getRefundDetail(String refundNo);
Result<PageResult<RefundVO>> queryRefunds(RefundQueryRequest request);
Result<PageResult<RefundVO>> getUserRefunds(Long userId, Integer status, PageRequest pageRequest);
Result<List<RefundVO>> getOrderRefunds(String orderNo);
```

**功能描述**: 查询退款信息

#### 3.7 检查退款状态
```java
Result<RefundVO> checkRefundStatus(String refundNo);
```

**功能描述**: 检查退款状态

## 数据模型

### OrderVO - 订单信息
```java
{
    "id": 1,                                 // 订单ID
    "orderNo": "ORD202401010001",            // 订单号
    "userId": 1,                             // 用户ID
    "username": "user123",                   // 用户名
    "totalAmount": "1000.00",                // 订单总金额
    "productAmount": "900.00",               // 商品金额
    "freightAmount": "10.00",                // 运费
    "discountAmount": "50.00",               // 优惠金额
    "payAmount": "960.00",                   // 实付金额
    "payType": 1,                            // 支付方式
    "payTypeName": "支付宝",                  // 支付方式名称
    "sourceType": 2,                         // 订单来源
    "sourceTypeName": "手机APP",              // 订单来源名称
    "status": 1,                             // 订单状态
    "statusName": "待发货",                   // 订单状态名称
    "deliveryCompany": "顺丰速运",            // 物流公司
    "deliveryNo": "SF123456789",             // 物流单号
    "receiverName": "张三",                   // 收货人姓名
    "receiverPhone": "13800138000",          // 收货人电话
    "receiverProvince": "北京",               // 收货省份
    "receiverCity": "北京",                   // 收货城市
    "receiverDistrict": "朝阳区",             // 收货区县
    "receiverAddress": "某某街道1号",          // 收货地址
    "remark": "请上午送达",                   // 订单备注
    "confirmStatus": 0,                      // 确认状态
    "paymentTime": "2024-01-01 10:00:00",    // 支付时间
    "deliveryTime": "2024-01-02 10:00:00",   // 发货时间
    "receiveTime": "2024-01-05 10:00:00",    // 收货时间
    "commentTime": "2024-01-06 10:00:00",    // 评价时间
    "createTime": "2024-01-01 09:00:00",     // 创建时间
    "itemCount": 3,                          // 商品总数
    "canCancel": true,                       // 可否取消
    "canPay": false,                         // 可否支付
    "canRefund": true,                       // 可否退款
    "canReceive": false,                     // 可否收货
    "canComment": false                      // 可否评价
}
```

### OrderDetailVO - 订单详情
```java
{
    // 继承 OrderVO 的所有字段
    "orderItems": [                          // 订单商品列表
        {
            "id": 1,
            "spuId": 1,
            "skuId": 1,
            "productName": "iPhone 15 Pro",
            "productImage": "https://...",
            "productSpecs": "256GB 原色钛金属",
            "price": "9999.00",
            "quantity": 1,
            "totalAmount": "9999.00",
            "discountAmount": "100.00",
            "realAmount": "9899.00",
            "canRefund": true,
            "refundStatus": 0,
            "commentStatus": 0
        }
    ],
    "paymentInfo": {                         // 支付信息
        "paymentNo": "PAY202401010001",
        "paymentType": 1,
        "paymentTypeName": "支付宝",
        "paymentAmount": "960.00",
        "paymentStatus": 1,
        "paymentTime": "2024-01-01 10:00:00",
        "transactionId": "2024010122001"
    },
    "timeline": [                            // 订单时间线
        {
            "title": "订单创建",
            "description": "您的订单已创建成功",
            "time": "2024-01-01 09:00:00",
            "status": "completed",
            "icon": "check-circle"
        }
    ],
    "invoice": {                             // 发票信息
        "invoiceTitle": "北京某某公司",
        "invoiceTaxNumber": "123456789",
        "invoiceContent": "商品明细",
        "invoiceEmail": "invoice@example.com"
    },
    "coupon": {                              // 优惠券信息
        "couponId": 1,
        "couponName": "新用户专享券",
        "discountAmount": "50.00",
        "minPurchaseAmount": "500.00"
    },
    "pointsUsed": 100,                       // 使用积分
    "pointsAmount": "10.00"                  // 积分抵扣金额
}
```

### OrderSettlementVO - 订单结算信息
```java
{
    "items": [                               // 结算商品列表
        {
            "skuId": 1,
            "productName": "iPhone 15 Pro",
            "productImage": "https://...",
            "productSpecs": "256GB 原色钛金属",
            "price": "9999.00",
            "quantity": 1,
            "totalAmount": "9999.00",
            "stock": 100
        }
    ],
    "totalAmount": "1000.00",                // 商品总金额
    "productAmount": "900.00",               // 商品金额
    "freightAmount": "10.00",                // 运费
    "discountAmount": "50.00",               // 优惠金额
    "couponDiscount": "30.00",               // 优惠券折扣
    "pointsDiscount": "20.00",               // 积分折扣
    "payAmount": "960.00",                   // 应付金额
    "availableCoupons": [                    // 可用优惠券
        {
            "couponId": 1,
            "couponName": "新用户专享券",
            "discountAmount": "50.00",
            "minPurchaseAmount": "500.00",
            "expireTime": "2024-12-31 23:59:59"
        }
    ],
    "addresses": [                           // 用户地址列表
        {
            "id": 1,
            "receiverName": "张三",
            "receiverPhone": "13800138000",
            "province": "北京",
            "city": "北京",
            "district": "朝阳区",
            "detailAddress": "某某街道1号",
            "isDefault": true
        }
    ],
    "defaultAddress": {                      // 默认地址
        "id": 1,
        "receiverName": "张三",
        "fullAddress": "北京北京朝阳区某某街道1号"
    },
    "userPoints": 1000,                      // 用户积分
    "maxUsablePoints": 500,                  // 最大可用积分
    "pointsExchangeRate": "0.01"             // 积分兑换比率
}
```

### RefundVO - 退款信息
```java
{
    "id": 1,                                 // 退款ID
    "refundNo": "REF202401010001",           // 退款单号
    "orderId": 1,                            // 订单ID
    "orderNo": "ORD202401010001",            // 订单号
    "userId": 1,                             // 用户ID
    "refundType": 1,                         // 退款类型
    "refundTypeName": "仅退款",               // 退款类型名称
    "refundAmount": "100.00",                // 退款金额
    "refundReason": "商品有瑕疵",             // 退款原因
    "description": "收到商品后发现有划痕",     // 退款描述
    "proofImages": ["https://..."],          // 证明图片
    "status": 1,                             // 退款状态
    "statusName": "处理中",                   // 退款状态名称
    "handleNote": "同意退款",                 // 处理说明
    "handleTime": "2024-01-02 10:00:00",     // 处理时间
    "refundTime": "2024-01-03 10:00:00",     // 退款时间
    "createTime": "2024-01-01 15:00:00"      // 创建时间
}
```

### OrderStatisticsVO - 订单统计
```java
{
    "totalOrders": 10000,                    // 订单总数
    "todayOrders": 100,                      // 今日订单数
    "monthOrders": 3000,                     // 本月订单数
    "pendingPaymentOrders": 50,              // 待付款订单数
    "pendingDeliveryOrders": 200,            // 待发货订单数
    "deliveredOrders": 150,                  // 已发货订单数
    "completedOrders": 8000,                 // 已完成订单数
    "refundingOrders": 20,                   // 退款中订单数
    "totalSalesAmount": "1000000.00",        // 总销售额
    "todaySalesAmount": "10000.00",          // 今日销售额
    "monthSalesAmount": "300000.00",         // 本月销售额
    "avgOrderAmount": "100.00",              // 平均订单金额
    "refundRate": "0.02",                    // 退款率
    "cancelRate": "0.05"                     // 取消率
}
```

## 枚举常量

### 订单状态
```java
public enum OrderStatusEnum {
    PENDING_PAYMENT(0, "待付款"),
    PENDING_DELIVERY(1, "待发货"),
    DELIVERED(2, "已发货"),
    RECEIVED(3, "已收货"),
    COMPLETED(4, "已完成"),
    CANCELLED(5, "已取消"),
    REFUNDING(6, "退款中"),
    REFUNDED(7, "已退款"),
    CLOSED(8, "已关闭");
}
```

### 订单来源
```java
public enum OrderSourceEnum {
    PC(1, "PC网站"),
    APP(2, "手机APP"),
    MINI_PROGRAM(3, "小程序"),
    H5(4, "H5移动端");
}
```

### 支付方式
```java
public enum PaymentTypeEnum {
    ALIPAY(1, "支付宝"),
    WECHAT(2, "微信支付"),
    BALANCE(3, "余额支付"),
    BANK_CARD(4, "银行卡"),
    CREDIT_CARD(5, "信用卡"),
    CASH_ON_DELIVERY(6, "货到付款");
}
```

### 退款类型
```java
public enum RefundTypeEnum {
    REFUND_ONLY(1, "仅退款"),
    RETURN_AND_REFUND(2, "退货退款"),
    EXCHANGE(3, "换货");
}
```

### 退款状态
```java
public enum RefundStatusEnum {
    PENDING(0, "待处理"),
    PROCESSING(1, "处理中"),
    APPROVED(2, "已同意"),
    REJECTED(3, "已拒绝"),
    REFUNDED(4, "已退款"),
    CANCELLED(5, "已取消"),
    CLOSED(6, "已关闭");
}
```

### 物流状态
```java
public enum DeliveryStatusEnum {
    NOT_SHIPPED(0, "未发货"),
    SHIPPED(1, "已发货"),
    IN_TRANSIT(2, "运输中"),
    DELIVERED(3, "已送达"),
    SIGNED(4, "已签收"),
    REJECTED(5, "拒收"),
    LOST(6, "丢失");
}
```

## 错误码说明

| 错误码 | 错误信息 | 说明 |
|-------|---------|------|
| 50001 | 订单不存在 | 查询订单时订单不存在 |
| 50002 | 商品库存不足 | 下单时库存不够 |
| 50003 | 订单状态异常 | 订单状态不允许当前操作 |
| 50004 | 订单已支付 | 重复支付 |
| 50005 | 订单已取消 | 操作已取消的订单 |
| 50006 | 订单已超时 | 操作已超时的订单 |
| 50007 | 收货地址不存在 | 使用的收货地址不存在 |
| 50008 | 优惠券不可用 | 优惠券已过期或不满足使用条件 |
| 50009 | 积分不足 | 用户积分不够 |
| 50010 | 退款金额错误 | 退款金额大于可退金额 |
| 50011 | 退款申请不存在 | 查询退款申请时不存在 |
| 50012 | 退款状态异常 | 退款状态不允许当前操作 |
| 50013 | 商品不可退款 | 商品不在退款范围内 |
| 50014 | 超过退款期限 | 超过了允许退款的时间 |

## 使用示例

### Spring Boot 项目集成

1. **添加依赖**
```xml
<dependency>
    <groupId>com.mall</groupId>
    <artifactId>order-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

2. **注入服务**
```java
@Service
public class OrderServiceImpl {
    
    @Reference
    private OrderDubboService orderDubboService;
    
    @Reference
    private OrderStatisticsDubboService orderStatisticsDubboService;
    
    @Reference
    private RefundDubboService refundDubboService;
    
    // 业务方法
}
```

3. **调用示例**
```java
// 获取订单结算信息
OrderSettlementRequest settlementRequest = new OrderSettlementRequest();
List<OrderItemRequest> items = new ArrayList<>();
OrderItemRequest item = new OrderItemRequest();
item.setSkuId(1L);
item.setQuantity(2);
items.add(item);
settlementRequest.setItems(items);
settlementRequest.setAddressId(1L);
Result<OrderSettlementVO> settlementResult = orderDubboService.getSettlement(1L, settlementRequest);

// 创建订单
OrderCreateRequest orderRequest = new OrderCreateRequest();
orderRequest.setOrderItems(items);
orderRequest.setAddressId(1L);
orderRequest.setPaymentType(1);
orderRequest.setSourceType(2);
Result<OrderVO> orderResult = orderDubboService.createOrder(1L, orderRequest);

// 查询用户订单
Result<PageResult<OrderVO>> userOrdersResult = orderDubboService.getUserOrders(1L, null, 
    PageRequest.of(1, 10));

// 申请退款
RefundCreateRequest refundRequest = new RefundCreateRequest();
refundRequest.setOrderNo("ORD202401010001");
refundRequest.setRefundType(1);
refundRequest.setRefundAmount(new BigDecimal("100.00"));
refundRequest.setRefundReason("商品有瑕疵");
Result<RefundVO> refundResult = refundDubboService.createRefund(1L, refundRequest);

// 获取订单统计
Result<OrderStatisticsVO> statisticsResult = orderStatisticsDubboService.getOverallStatistics();
```

## 业务流程

### 订单创建流程
1. 用户选择商品加入购物车
2. 进入结算页面，调用获取结算信息接口
3. 用户选择收货地址、优惠券等
4. 调用创建订单接口
5. 系统锁定库存，生成订单
6. 发送订单创建事件给相关服务

### 订单支付流程
1. 用户选择支付方式
2. 调用支付订单接口
3. 跳转到第三方支付页面
4. 用户完成支付
5. 第三方支付平台回调
6. 系统处理支付回调，更新订单状态
7. 发送支付成功事件

### 订单履约流程
1. 订单支付成功后进入待发货状态
2. 商家调用发货接口
3. 物流公司揽收，订单进入已发货状态
4. 用户收到货物，确认收货
5. 系统自动完成订单
6. 发送订单完成事件

### 退款处理流程
1. 用户申请退款，填写退款原因
2. 客服审核退款申请
3. 同意退款后调用支付服务处理退款
4. 退款成功后释放库存
5. 发送退款成功事件

### 订单超时处理
1. 定时任务检查未支付订单
2. 超时订单自动取消
3. 释放锁定库存
4. 发送订单取消事件 