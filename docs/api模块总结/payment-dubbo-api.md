# 支付服务 Dubbo API 文档

## 服务概述

支付服务(payment-service)提供支付管理、退款管理、查询统计等核心功能的Dubbo RPC接口。

**服务名称**: `payment-service`  
**API版本**: `1.0.0`  
**包路径**: `com.mall.api.payment.dubbo`

## 服务接口清单

### 1. PaymentDubboService - 支付管理服务

**接口路径**: `com.mall.api.payment.dubbo.PaymentDubboService`

#### 1.1 创建支付订单
```java
Result<PaymentResultVO> createPayment(PaymentCreateRequest request);
```

**功能描述**: 创建支付订单，生成支付链接或二维码  
**参数说明**:
- `request`: 支付创建请求
  - `orderNo`: 业务订单号
  - `userId`: 用户ID
  - `paymentType`: 支付类型 (1-支付宝, 2-微信, 3-银联)
  - `paymentAmount`: 支付金额
  - `currency`: 币种 (默认CNY)
  - `subject`: 支付主题
  - `description`: 支付描述
  - `clientIp`: 客户端IP
  - `returnUrl`: 同步返回URL
  - `notifyUrl`: 异步通知URL
  - `expireTime`: 支付过期时间
  - `extraParams`: 扩展参数

**返回结果**: 支付结果对象，包含支付URL、二维码等信息

**调用示例**:
```java
@Reference
private PaymentDubboService paymentDubboService;

PaymentCreateRequest request = new PaymentCreateRequest();
request.setOrderNo("ORD202401010001");
request.setUserId(1L);
request.setPaymentType(1); // 支付宝
request.setPaymentAmount(new BigDecimal("100.00"));
request.setSubject("订单支付");
request.setDescription("商品订单支付");
request.setReturnUrl("https://example.com/return");
request.setNotifyUrl("https://example.com/notify");
Result<PaymentResultVO> result = paymentDubboService.createPayment(request);
```

#### 1.2 处理支付回调
```java
Result<Void> processCallback(PaymentCallbackRequest request);
```

**功能描述**: 处理第三方支付平台的支付回调  
**参数说明**:
- `request`: 支付回调请求
  - `paymentNo`: 支付单号
  - `transactionId`: 第三方交易号
  - `paymentType`: 支付类型
  - `status`: 支付状态
  - `paymentAmount`: 支付金额
  - `actualAmount`: 实际支付金额
  - `paymentTime`: 支付时间
  - `signature`: 签名
  - `callbackParams`: 回调参数

#### 1.3 处理支付通知
```java
Result<Void> processNotify(PaymentNotifyRequest request);
```

**功能描述**: 处理第三方支付平台的异步通知

#### 1.4 查询支付信息
```java
Result<PaymentVO> queryPaymentByNo(String paymentNo);
Result<PaymentVO> queryPaymentByOrderNo(String orderNo);
Result<PageResult<PaymentVO>> queryPayments(PaymentQueryRequest request);
```

**功能描述**: 根据支付单号、订单号或条件查询支付信息

#### 1.5 支付状态管理
```java
Result<Void> cancelPayment(String paymentNo);
Result<Void> closePayment(String paymentNo);
Result<PaymentVO> checkPaymentStatus(String paymentNo);
```

**功能描述**: 取消支付、关闭支付、检查支付状态

#### 1.6 获取支付URL
```java
Result<String> getPaymentUrl(String paymentNo);
```

**功能描述**: 获取支付链接

#### 1.7 计算支付费用
```java
Result<BigDecimal> calculatePaymentFee(Integer paymentType, BigDecimal amount);
```

**功能描述**: 计算支付手续费

#### 1.8 验证支付签名
```java
Result<Boolean> verifySignature(PaymentCallbackRequest request);
```

**功能描述**: 验证第三方支付平台的签名

---

### 2. PaymentQueryDubboService - 支付查询服务

**接口路径**: `com.mall.api.payment.dubbo.PaymentQueryDubboService`

#### 2.1 支付统计
```java
Result<PaymentStatisticsVO> getPaymentStatistics();
Result<PaymentStatisticsVO> getPaymentStatisticsByDateRange(LocalDate startDate, LocalDate endDate);
Result<PaymentStatisticsVO> getUserPaymentStatistics(Long userId);
```

**功能描述**: 获取支付统计数据

#### 2.2 每日/月度统计
```java
Result<Map<String, BigDecimal>> getDailyPaymentStatistics(LocalDate date);
Result<Map<String, BigDecimal>> getMonthlyPaymentStatistics(Integer year, Integer month);
```

**功能描述**: 获取每日或月度支付统计

#### 2.3 支付方式分布
```java
Result<Map<String, Long>> getPaymentTypeDistribution();
```

**功能描述**: 获取支付方式分布统计

#### 2.4 支付用户排行
```java
Result<List<Map<String, Object>>> getTopPaymentUsers(Integer limit);
```

**功能描述**: 获取支付用户排行榜

#### 2.5 交易记录
```java
Result<List<TransactionVO>> getTransactionRecords(Long userId, Integer type);
Result<TransactionVO> getTransactionDetail(String transactionNo);
```

**功能描述**: 获取用户交易记录和交易详情

---

### 3. RefundDubboService - 退款管理服务

**接口路径**: `com.mall.api.payment.dubbo.RefundDubboService`

#### 3.1 申请退款
```java
Result<RefundResultVO> applyRefund(RefundApplyRequest request);
```

**功能描述**: 申请退款  
**参数说明**:
- `request`: 退款申请请求
  - `paymentNo`: 原支付单号
  - `orderNo`: 业务订单号
  - `userId`: 用户ID
  - `refundAmount`: 退款金额
  - `refundReason`: 退款原因
  - `description`: 退款描述
  - `notifyUrl`: 退款通知URL
  - `operatorId`: 操作人ID
  - `operatorName`: 操作人姓名

**调用示例**:
```java
RefundApplyRequest request = new RefundApplyRequest();
request.setPaymentNo("PAY202401010001");
request.setOrderNo("ORD202401010001");
request.setUserId(1L);
request.setRefundAmount(new BigDecimal("50.00"));
request.setRefundReason("商品质量问题");
request.setDescription("商品有瑕疵，申请退款");
Result<RefundResultVO> result = refundDubboService.applyRefund(request);
```

#### 3.2 处理退款回调
```java
Result<Void> processRefundCallback(RefundCallbackRequest request);
```

**功能描述**: 处理第三方平台的退款回调

#### 3.3 查询退款信息
```java
Result<RefundVO> queryRefundByNo(String refundNo);
Result<List<RefundVO>> queryRefundsByPaymentNo(String paymentNo);
Result<List<RefundVO>> queryRefundsByOrderNo(String orderNo);
Result<PageResult<RefundVO>> queryRefunds(RefundQueryRequest request);
```

**功能描述**: 查询退款信息

#### 3.4 退款操作
```java
Result<Void> cancelRefund(String refundNo);
Result<RefundResultVO> retryRefund(String refundNo);
Result<RefundVO> checkRefundStatus(String refundNo);
Result<RefundDetailVO> getRefundDetail(String refundNo);
```

**功能描述**: 取消退款、重试退款、检查退款状态、获取退款详情

## 数据模型

### PaymentResultVO - 支付结果
```java
{
    "paymentNo": "PAY202401010001",          // 支付单号
    "paymentUrl": "https://payment.alipay.com/...", // 支付URL
    "paymentForm": "<form>...</form>",       // 支付表单HTML
    "qrCodeUrl": "https://example.com/qr.png", // 二维码URL
    "paymentParams": "...",                  // 支付参数
    "needRedirect": true,                    // 是否需要跳转
    "paymentType": 1                         // 支付类型
}
```

### PaymentVO - 支付信息
```java
{
    "id": 1,                                 // 支付ID
    "paymentNo": "PAY202401010001",          // 支付单号
    "orderNo": "ORD202401010001",            // 订单号
    "userId": 1,                             // 用户ID
    "paymentType": 1,                        // 支付类型
    "paymentTypeName": "支付宝",              // 支付类型名称
    "paymentAmount": "100.00",               // 支付金额
    "currency": "CNY",                       // 币种
    "status": 1,                             // 状态
    "statusName": "已支付",                   // 状态名称
    "thirdPartyNo": "2024010122001",         // 第三方交易号
    "paymentTime": "2024-01-01 10:00:00",    // 支付时间
    "expireTime": "2024-01-01 10:30:00",     // 过期时间
    "callbackTime": "2024-01-01 10:00:30",  // 回调时间
    "createTime": "2024-01-01 09:00:00"      // 创建时间
}
```

### RefundVO - 退款信息
```java
{
    "id": 1,                                 // 退款ID
    "refundNo": "REF202401010001",           // 退款单号
    "paymentNo": "PAY202401010001",          // 支付单号
    "orderNo": "ORD202401010001",            // 订单号
    "userId": 1,                             // 用户ID
    "refundAmount": "50.00",                 // 退款金额
    "refundReason": "商品质量问题",           // 退款原因
    "status": 1,                             // 状态
    "statusName": "已退款",                   // 状态名称
    "thirdPartyRefundNo": "REF2024010122001", // 第三方退款号
    "refundTime": "2024-01-02 10:00:00",     // 退款时间
    "createTime": "2024-01-01 15:00:00"      // 创建时间
}
```

### PaymentStatisticsVO - 支付统计
```java
{
    "totalPaymentCount": 10000,              // 总支付笔数
    "totalPaymentAmount": "1000000.00",      // 总支付金额
    "todayPaymentCount": 100,                // 今日支付笔数
    "todayPaymentAmount": "10000.00",        // 今日支付金额
    "totalRefundCount": 100,                 // 总退款笔数
    "totalRefundAmount": "10000.00",         // 总退款金额
    "successRate": "0.98",                   // 支付成功率
    "avgPaymentAmount": "100.00",            // 平均支付金额
    "paymentTypeDistribution": {             // 支付方式分布
        "alipay": 5000,
        "wechat": 3000,
        "unionpay": 2000
    },
    "dailyStatistics": {                     // 每日统计
        "2024-01-01": "50000.00",
        "2024-01-02": "60000.00"
    }
}
```

### TransactionVO - 交易记录
```java
{
    "id": 1,                                 // 交易ID
    "transactionNo": "TXN202401010001",      // 交易流水号
    "userId": 1,                             // 用户ID
    "transactionType": 1,                    // 交易类型
    "transactionTypeName": "支付",            // 交易类型名称
    "amount": "100.00",                      // 交易金额
    "balanceBefore": "1000.00",              // 交易前余额
    "balanceAfter": "900.00",                // 交易后余额
    "businessType": "ORDER_PAYMENT",         // 业务类型
    "businessNo": "ORD202401010001",         // 业务单号
    "remark": "订单支付",                     // 备注
    "createTime": "2024-01-01 10:00:00"      // 创建时间
}
```

## 枚举常量

### 支付类型
```java
public enum PaymentTypeEnum {
    ALIPAY(1, "支付宝", "alipay"),
    WECHAT(2, "微信支付", "wechat"),
    UNIONPAY(3, "银联支付", "unionpay"),
    BALANCE(4, "余额支付", "balance"),
    BANK_TRANSFER(5, "银行转账", "bank"),
    CREDIT_CARD(6, "信用卡", "credit"),
    PAYPAL(7, "PayPal", "paypal"),
    APPLE_PAY(8, "Apple Pay", "apple");
}
```

### 支付状态
```java
public enum PaymentStatusEnum {
    PENDING(0, "待支付"),
    PAID(1, "已支付"),
    FAILED(2, "支付失败"),
    CANCELLED(3, "已取消"),
    EXPIRED(4, "已过期"),
    REFUNDED(5, "已退款"),
    PARTIAL_REFUNDED(6, "部分退款");
}
```

### 退款状态
```java
public enum RefundStatusEnum {
    PENDING(0, "待处理"),
    PROCESSING(1, "处理中"),
    SUCCESS(2, "已退款"),
    FAILED(3, "退款失败"),
    CANCELLED(4, "已取消");
}
```

### 退款原因
```java
public enum RefundReasonEnum {
    QUALITY_ISSUE(1, "商品质量问题"),
    NOT_AS_DESCRIBED(2, "商品不符描述"),
    WRONG_ITEM(3, "发错商品"),
    DAMAGED(4, "商品损坏"),
    NOT_RECEIVED(5, "未收到商品"),
    CANCEL_ORDER(6, "取消订单"),
    DUPLICATE_PAYMENT(7, "重复支付"),
    OTHER(99, "其他原因");
}
```

### 交易类型
```java
public enum TransactionTypeEnum {
    PAYMENT(1, "支付"),
    REFUND(2, "退款"),
    TRANSFER(3, "转账"),
    RECHARGE(4, "充值"),
    WITHDRAW(5, "提现"),
    FREEZE(6, "冻结"),
    UNFREEZE(7, "解冻");
}
```

## 错误码说明

| 错误码 | 错误信息 | 说明 |
|-------|---------|------|
| 40001 | 支付订单不存在 | 查询支付订单时不存在 |
| 40002 | 支付金额错误 | 支付金额不正确 |
| 40003 | 支付已超时 | 支付订单已过期 |
| 40004 | 支付状态异常 | 支付状态不允许当前操作 |
| 40005 | 签名验证失败 | 第三方支付签名验证失败 |
| 40006 | 退款金额超限 | 退款金额大于可退金额 |
| 40007 | 退款订单不存在 | 查询退款订单时不存在 |
| 40008 | 退款状态异常 | 退款状态不允许当前操作 |
| 40009 | 余额不足 | 用户余额不足 |
| 40010 | 支付方式不支持 | 不支持的支付方式 |
| 40011 | 支付渠道异常 | 第三方支付渠道异常 |
| 40012 | 重复支付 | 订单已支付，不能重复支付 |

## 使用示例

### Spring Boot 项目集成

1. **添加依赖**
```xml
<dependency>
    <groupId>com.mall</groupId>
    <artifactId>payment-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

2. **注入服务**
```java
@Service
public class PaymentServiceImpl {
    
    @Reference
    private PaymentDubboService paymentDubboService;
    
    @Reference
    private PaymentQueryDubboService paymentQueryDubboService;
    
    @Reference
    private RefundDubboService refundDubboService;
    
    // 业务方法
}
```

3. **调用示例**
```java
// 创建支付订单
PaymentCreateRequest paymentRequest = new PaymentCreateRequest();
paymentRequest.setOrderNo("ORD202401010001");
paymentRequest.setUserId(1L);
paymentRequest.setPaymentType(1); // 支付宝
paymentRequest.setPaymentAmount(new BigDecimal("100.00"));
paymentRequest.setSubject("订单支付");
Result<PaymentResultVO> paymentResult = paymentDubboService.createPayment(paymentRequest);

// 查询支付状态
Result<PaymentVO> statusResult = paymentDubboService.queryPaymentByOrderNo("ORD202401010001");

// 申请退款
RefundApplyRequest refundRequest = new RefundApplyRequest();
refundRequest.setPaymentNo("PAY202401010001");
refundRequest.setRefundAmount(new BigDecimal("50.00"));
refundRequest.setRefundReason("商品质量问题");
Result<RefundResultVO> refundResult = refundDubboService.applyRefund(refundRequest);

// 获取支付统计
Result<PaymentStatisticsVO> statisticsResult = paymentQueryDubboService.getPaymentStatistics();
```

## 业务流程

### 支付流程
1. 用户下单后调用创建支付接口
2. 系统生成支付订单和支付链接
3. 用户通过支付链接完成支付
4. 第三方平台异步通知支付结果
5. 系统处理支付回调，更新订单状态
6. 发送支付成功事件给相关服务

### 退款流程
1. 用户申请退款或系统自动退款
2. 创建退款订单
3. 调用第三方平台退款接口
4. 第三方平台处理退款并异步通知结果
5. 系统处理退款回调，更新退款状态
6. 发送退款成功事件给相关服务

### 对账流程
1. 定时下载第三方平台对账文件
2. 解析对账文件中的交易记录
3. 与系统内部交易记录进行比对
4. 处理差异记录并生成对账报告
5. 通知财务人员处理异常交易 