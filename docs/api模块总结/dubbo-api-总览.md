# Mall微服务Dubbo API文档总览

## 项目概述

本文档描述了Mall电商微服务平台中各个服务模块提供的Dubbo RPC接口。Dubbo作为内部服务间通信的主要方式，提供了高性能、可靠的远程调用能力。

## 服务架构

```
mall-microservice/
├── mall-api/                    # API接口定义
│   ├── user-api/                # 用户服务接口
│   ├── product-api/             # 商品服务接口
│   ├── order-api/               # 订单服务接口
│   ├── inventory-api/           # 库存服务接口
│   └── payment-api/             # 支付服务接口
├── mall-service/                # 微服务实现
│   ├── user-service/            # 用户服务
│   ├── product-service/         # 商品服务
│   ├── order-service/           # 订单服务
│   ├── inventory-service/       # 库存服务
│   └── payment-service/         # 支付服务
```

## Dubbo服务清单

### 1. 用户服务 (user-service)

| 服务接口 | 说明 | 版本 |
|---------|------|------|
| UserDubboService | 用户基础服务 | 1.0.0 |
| AddressDubboService | 地址管理服务 | 1.0.0 |
| MemberDubboService | 会员服务 | 1.0.0 |

### 2. 商品服务 (product-service)

| 服务接口 | 说明 | 版本 |
|---------|------|------|
| ProductDubboService | 商品管理服务 | 1.0.0 |
| CategoryDubboService | 分类管理服务 | 1.0.0 |
| BrandDubboService | 品牌管理服务 | 1.0.0 |
| AttributeDubboService | 属性管理服务 | 1.0.0 |

### 3. 库存服务 (inventory-service)

| 服务接口 | 说明 | 版本 |
|---------|------|------|
| InventoryDubboService | 库存管理服务 | 1.0.0 |
| InventoryQueryDubboService | 库存查询服务 | 1.0.0 |
| WarehouseDubboService | 仓库管理服务 | 1.0.0 |

### 4. 订单服务 (order-service)

| 服务接口 | 说明 | 版本 |
|---------|------|------|
| OrderDubboService | 订单管理服务 | 1.0.0 |
| OrderStatisticsDubboService | 订单统计服务 | 1.0.0 |
| RefundDubboService | 退款管理服务 | 1.0.0 |

### 5. 支付服务 (payment-service)

| 服务接口 | 说明 | 版本 |
|---------|------|------|
| PaymentDubboService | 支付管理服务 | 1.0.0 |
| PaymentQueryDubboService | 支付查询服务 | 1.0.0 |
| RefundDubboService | 退款管理服务 | 1.0.0 |

## 统一规范

### 命名规范
- 服务接口：`{模块名}DubboService`
- 包路径：`com.mall.api.{模块}.dubbo`
- 方法返回：统一使用 `Result<T>` 包装

### 异常处理
- 所有接口统一使用 `Result` 封装返回结果
- 业务异常通过 `Result.fail()` 返回
- 系统异常通过全局异常处理器处理

### 版本管理
- 所有服务接口版本号：1.0.0
- 向后兼容原则，新增方法不影响旧版本
- 重大变更需要升级版本号

## 服务注册与发现

所有Dubbo服务通过Nacos注册中心进行服务注册与发现：

- **注册中心地址**: `localhost:8848`
- **服务分组**: `mall`
- **协议**: `dubbo://`
- **序列化**: `hessian2`

## 监控与治理

- **管控台**: Dubbo Admin
- **链路追踪**: Zipkin
- **服务熔断**: Sentinel
- **负载均衡**: Random（默认）

## 核心业务流程

### 电商交易核心流程
```
用户注册/登录 → 浏览商品 → 加入购物车 → 下单结算 → 支付 → 发货 → 收货 → 评价
     ↓              ↓           ↓          ↓       ↓      ↓      ↓      ↓
  UserService   ProductService  OrderService  OrderService PaymentService OrderService OrderService OrderService
```

### 服务间调用关系
- **订单服务** ↔ **商品服务**: 查询商品信息、检查库存
- **订单服务** ↔ **库存服务**: 锁定/扣减库存、释放库存
- **订单服务** ↔ **支付服务**: 创建支付、处理支付回调
- **订单服务** ↔ **用户服务**: 获取用户信息、地址信息、积分操作
- **支付服务** ↔ **用户服务**: 余额支付、账户余额操作

## 详细API文档

- [用户服务Dubbo API](./user-dubbo-api.md)
- [商品服务Dubbo API](./product-dubbo-api.md)
- [库存服务Dubbo API](./inventory-dubbo-api.md)
- [订单服务Dubbo API](./order-dubbo-api.md)
- [支付服务Dubbo API](./payment-dubbo-api.md) 