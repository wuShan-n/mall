# 库存服务 Dubbo API 文档

## 服务概述

库存服务(inventory-service)提供库存管理、仓库管理、库存查询等核心功能的Dubbo RPC接口。

**服务名称**: `inventory-service`  
**API版本**: `1.0.0`  
**包路径**: `com.mall.api.inventory.dubbo`

## 服务接口清单

### 1. InventoryDubboService - 库存管理服务

**接口路径**: `com.mall.api.inventory.dubbo.InventoryDubboService`

#### 1.1 库存更新
```java
Result<StockVO> updateStock(StockUpdateRequest request);
Result<List<StockVO>> batchUpdateStock(BatchStockUpdateRequest request);
```

**功能描述**: 单个或批量更新库存(增加/扣减)  
**参数说明**:
- `request`: 库存更新请求
  - `skuId`: SKU ID
  - `quantity`: 数量
  - `operationType`: 操作类型 (1-增加, 2-扣减, 3-锁定, 4-解锁)
  - `warehouseId`: 仓库ID
  - `businessType`: 业务类型 (PURCHASE/SALE/RETURN等)
  - `businessNo`: 业务单号
  - `remark`: 备注
  - `operatorId`: 操作人ID
  - `operatorName`: 操作人姓名

**调用示例**:
```java
@Reference
private InventoryDubboService inventoryDubboService;

StockUpdateRequest request = new StockUpdateRequest();
request.setSkuId(1L);
request.setQuantity(100);
request.setOperationType(1); // 增加库存
request.setBusinessType("PURCHASE");
request.setBusinessNo("PO202401010001");
Result<StockVO> result = inventoryDubboService.updateStock(request);
```

#### 1.2 库存锁定
```java
Result<StockLockVO> lockStock(StockLockRequest request);
Result<List<StockLockVO>> batchLockStock(List<StockLockRequest> requests);
```

**功能描述**: 单个或批量锁定库存(用于订单预占)  
**参数说明**:
- `request`: 库存锁定请求
  - `skuId`: SKU ID
  - `quantity`: 锁定数量
  - `orderNo`: 订单号
  - `warehouseId`: 仓库ID (可选)
  - `expireTime`: 锁定过期时间
  - `userId`: 用户ID

#### 1.3 库存解锁
```java
Result<Void> unlockStock(String orderNo);
Result<Void> unlockStockById(Long lockId);
```

**功能描述**: 根据订单号或锁定ID解锁库存

#### 1.4 扣减锁定库存
```java
Result<Void> deductLockedStock(String orderNo);
```

**功能描述**: 订单支付成功后扣减已锁定的库存

#### 1.5 退库存
```java
Result<Void> returnStock(Long skuId, Integer quantity, String orderNo);
```

**功能描述**: 退货/取消订单时返还库存

#### 1.6 库存查验
```java
Result<Boolean> checkStock(Long skuId, Integer quantity);
Result<Boolean> checkStockWithWarehouse(Long skuId, Integer quantity, Long warehouseId);
Result<Map<Long, Boolean>> batchCheckStock(Map<Long, Integer> skuQuantityMap);
```

**功能描述**: 检查库存是否充足

#### 1.7 库存查询
```java
Result<StockVO> getStockBySkuId(Long skuId);
Result<StockVO> getStockBySkuAndWarehouse(Long skuId, Long warehouseId);
Result<List<StockVO>> getStocksBySkuIds(List<Long> skuIds);
Result<Integer> getAvailableStock(Long skuId);
```

**功能描述**: 查询库存信息

#### 1.8 库存调拨
```java
Result<Void> transferStock(StockTransferRequest request);
```

**功能描述**: 仓库间库存调拨  
**参数说明**:
- `request`: 库存调拨请求
  - `sourceWarehouseId`: 源仓库ID
  - `targetWarehouseId`: 目标仓库ID
  - `items`: 调拨商品列表
  - `reason`: 调拨原因
  - `operatorId`: 操作人ID
  - `operatorName`: 操作人姓名

#### 1.9 库存盘点
```java
Result<Void> inventoryCheck(InventoryCheckRequest request);
```

**功能描述**: 库存盘点和调整  
**参数说明**:
- `request`: 盘点请求
  - `warehouseId`: 仓库ID
  - `items`: 盘点商品列表
  - `reason`: 盘点原因
  - `operatorId`: 操作人ID
  - `operatorName`: 操作人姓名

#### 1.10 锁定记录查询
```java
Result<List<StockLockVO>> getStockLocksByOrderNo(String orderNo);
```

#### 1.11 释放过期锁定
```java
Result<Integer> releaseExpiredLocks();
```

**功能描述**: 定时释放过期的库存锁定

---

### 2. InventoryQueryDubboService - 库存查询服务

**接口路径**: `com.mall.api.inventory.dubbo.InventoryQueryDubboService`

#### 2.1 库存查询
```java
Result<PageResult<StockVO>> queryStocks(StockQueryRequest request);
```

**功能描述**: 分页查询库存列表  
**参数说明**:
- `request`: 查询条件
  - `skuId`: SKU ID
  - `skuCode`: SKU编码
  - `warehouseId`: 仓库ID
  - `minStock`: 最小库存
  - `maxStock`: 最大库存
  - `hasStock`: 是否有库存
  - `isLowStock`: 是否低库存
  - `startTime`: 开始时间
  - `endTime`: 结束时间

#### 2.2 库存操作记录
```java
Result<List<StockRecordVO>> getStockRecordsBySkuId(Long skuId, PageRequest pageRequest);
Result<List<StockRecordVO>> getStockRecordsByWarehouse(Long warehouseId, PageRequest pageRequest);
Result<List<StockRecordVO>> getStockRecordsByBusinessNo(String businessNo);
```

**功能描述**: 查询库存操作记录

#### 2.3 库存统计
```java
Result<InventoryStatisticsVO> getInventoryStatistics();
Result<InventoryStatisticsVO> getWarehouseStatistics(Long warehouseId);
```

**功能描述**: 获取库存统计数据

#### 2.4 库存报表
```java
Result<Map<String, Object>> getStockMovementReport(LocalDate startDate, LocalDate endDate);
Result<Map<String, Object>> getStockValueReport();
Result<Map<String, Object>> getStockAgingReport();
```

**功能描述**: 获取库存流水、价值、账龄报表

#### 2.5 异常库存查询
```java
Result<List<StockVO>> getLowStockSkus(Integer limit);
Result<List<StockVO>> getOutOfStockSkus(Integer limit);
Result<List<StockVO>> getOverstockSkus(Integer limit);
```

**功能描述**: 查询低库存、缺货、超库存的SKU

---

### 3. WarehouseDubboService - 仓库管理服务

**接口路径**: `com.mall.api.inventory.dubbo.WarehouseDubboService`

#### 3.1 创建仓库
```java
Result<WarehouseVO> createWarehouse(WarehouseCreateRequest request);
```

**功能描述**: 创建仓库  
**参数说明**:
- `request`: 仓库创建请求
  - `warehouseName`: 仓库名称
  - `warehouseCode`: 仓库编码
  - `warehouseType`: 仓库类型 (1-主仓, 2-分仓, 3-中转仓)
  - `contactPerson`: 联系人
  - `contactPhone`: 联系电话
  - `province`: 省份
  - `city`: 城市
  - `district`: 区县
  - `address`: 详细地址
  - `longitude`: 经度
  - `latitude`: 纬度
  - `status`: 状态
  - `remark`: 备注

#### 3.2 更新仓库
```java
Result<WarehouseVO> updateWarehouse(Long warehouseId, WarehouseCreateRequest request);
```

#### 3.3 删除仓库
```java
Result<Void> deleteWarehouse(Long warehouseId);
```

#### 3.4 查询仓库
```java
Result<WarehouseVO> getWarehouseById(Long warehouseId);
Result<WarehouseVO> getWarehouseByCode(String warehouseCode);
Result<List<WarehouseVO>> getAllWarehouses();
Result<List<WarehouseVO>> getActiveWarehouses();
Result<PageResult<WarehouseVO>> queryWarehouses(PageRequest request);
```

#### 3.5 仓库状态管理
```java
Result<Void> updateWarehouseStatus(Long warehouseId, Integer status);
```

#### 3.6 默认仓库
```java
Result<WarehouseVO> getDefaultWarehouse();
```

#### 3.7 就近仓库
```java
Result<WarehouseVO> getNearestWarehouse(String province, String city);
```

**功能描述**: 根据地理位置获取最近的仓库

## 数据模型

### StockVO - 库存信息
```java
{
    "id": 1,                            // 库存ID
    "skuId": 1,                         // SKU ID
    "skuCode": "SKU001",                // SKU编码
    "skuName": "iPhone 15 Pro 256GB",  // SKU名称
    "warehouseId": 1,                   // 仓库ID
    "warehouseName": "北京主仓",         // 仓库名称
    "totalStock": 1000,                 // 总库存
    "availableStock": 950,              // 可用库存
    "lockedStock": 50,                  // 锁定库存
    "inTransitStock": 100,              // 在途库存
    "warnStock": 100,                   // 预警库存
    "safetyStock": 50,                  // 安全库存
    "isAvailable": true,                // 是否可用
    "isLowStock": false,                // 是否低库存
    "updateTime": "2024-01-01 10:00:00" // 更新时间
}
```

### StockLockVO - 库存锁定信息
```java
{
    "id": 1,                            // 锁定ID
    "skuId": 1,                         // SKU ID
    "warehouseId": 1,                   // 仓库ID
    "orderNo": "ORD202401010001",       // 订单号
    "userId": 1,                        // 用户ID
    "lockedQuantity": 5,                // 锁定数量
    "lockStatus": 1,                    // 锁定状态 (1-已锁定, 2-已释放, 3-已扣减)
    "lockTime": "2024-01-01 10:00:00",  // 锁定时间
    "expireTime": "2024-01-01 10:30:00", // 过期时间
    "releaseTime": "2024-01-01 10:15:00" // 释放时间
}
```

### StockRecordVO - 库存操作记录
```java
{
    "id": 1,                            // 记录ID
    "skuId": 1,                         // SKU ID
    "skuCode": "SKU001",                // SKU编码
    "skuName": "iPhone 15 Pro",        // SKU名称
    "warehouseId": 1,                   // 仓库ID
    "warehouseName": "北京主仓",         // 仓库名称
    "operationType": 1,                 // 操作类型
    "operationTypeName": "入库",        // 操作类型名称
    "changeQuantity": 100,              // 变化数量
    "stockBefore": 900,                 // 操作前库存
    "stockAfter": 1000,                 // 操作后库存
    "businessType": "PURCHASE",         // 业务类型
    "businessNo": "PO202401010001",     // 业务单号
    "remark": "采购入库",               // 备注
    "operatorId": 1,                    // 操作人ID
    "operatorName": "Admin",            // 操作人姓名
    "operationTime": "2024-01-01 10:00:00" // 操作时间
}
```

### WarehouseVO - 仓库信息
```java
{
    "id": 1,                            // 仓库ID
    "warehouseName": "北京主仓",         // 仓库名称
    "warehouseCode": "WH001",           // 仓库编码
    "warehouseType": 1,                 // 仓库类型
    "warehouseTypeName": "主仓库",       // 仓库类型名称
    "contactPerson": "张三",            // 联系人
    "contactPhone": "13800138000",      // 联系电话
    "province": "北京",                 // 省份
    "city": "北京",                     // 城市
    "district": "朝阳区",               // 区县
    "address": "仓库路1号",              // 详细地址
    "fullAddress": "北京北京朝阳区仓库路1号", // 完整地址
    "longitude": "116.404",             // 经度
    "latitude": "39.915",               // 纬度
    "status": 1,                        // 状态
    "totalSkus": 1000,                  // SKU总数
    "totalStockValue": "1000000.00",    // 库存总价值
    "remark": "主要配送中心",            // 备注
    "createTime": "2024-01-01 00:00:00" // 创建时间
}
```

### InventoryStatisticsVO - 库存统计
```java
{
    "totalWarehouses": 10,              // 仓库总数
    "activeWarehouses": 8,              // 活跃仓库数
    "totalSkus": 10000,                 // SKU总数
    "totalStockQuantity": 1000000,      // 库存总数量
    "totalStockValue": "50000000.00",   // 库存总价值
    "availableStockQuantity": 950000,   // 可用库存数量
    "lockedStockQuantity": 50000,       // 锁定库存数量
    "outOfStockSkus": 100,              // 缺货SKU数
    "lowStockSkus": 500,                // 低库存SKU数
    "overstockSkus": 200                // 超库存SKU数
}
```

## 枚举常量

### 库存操作类型
```java
public enum StockOperationEnum {
    ADD(1, "增加库存"),
    DEDUCT(2, "扣减库存"),
    LOCK(3, "锁定库存"),
    UNLOCK(4, "解锁库存"),
    TRANSFER_IN(5, "调入"),
    TRANSFER_OUT(6, "调出"),
    ADJUST_INCREASE(7, "盘盈"),
    ADJUST_DECREASE(8, "盘亏");
}
```

### 库存来源类型
```java
public enum StockSourceEnum {
    PURCHASE("PURCHASE", "采购入库"),
    SALE("SALE", "销售出库"),
    RETURN("RETURN", "退货入库"),
    REFUND("REFUND", "退款出库"),
    TRANSFER("TRANSFER", "调拨"),
    ADJUST("ADJUST", "盘点调整"),
    DAMAGE("DAMAGE", "报损"),
    INVENTORY("INVENTORY", "盘点"),
    PRODUCTION("PRODUCTION", "生产入库"),
    SAMPLE("SAMPLE", "样品出库");
}
```

### 锁定状态
```java
public enum LockStatusEnum {
    LOCKED(1, "已锁定"),
    RELEASED(2, "已释放"),
    DEDUCTED(3, "已扣减"),
    EXPIRED(4, "已过期");
}
```

### 仓库类型
```java
public enum WarehouseTypeEnum {
    MAIN(1, "主仓库"),
    BRANCH(2, "分仓库"),
    TRANSIT(3, "中转仓"),
    VIRTUAL(4, "虚拟仓");
}
```

## 错误码说明

| 错误码 | 错误信息 | 说明 |
|-------|---------|------|
| 30001 | 库存不足 | 扣减库存时数量不够 |
| 30002 | SKU不存在 | 操作的SKU不存在 |
| 30003 | 仓库不存在 | 操作的仓库不存在 |
| 30004 | 库存锁定失败 | 可用库存不足以锁定 |
| 30005 | 库存锁定不存在 | 要操作的锁定记录不存在 |
| 30006 | 库存锁定已过期 | 锁定记录已过期 |
| 30007 | 库存锁定已释放 | 锁定记录已被释放 |
| 30008 | 库存锁定已扣减 | 锁定记录已被扣减 |
| 30009 | 仓库编码已存在 | 创建仓库时编码重复 |
| 30010 | 仓库状态异常 | 仓库未激活或已停用 |
| 30011 | 调拨仓库相同 | 源仓库与目标仓库相同 |
| 30012 | 调拨数量超限 | 调拨数量大于可用库存 |

## 使用示例

### Spring Boot 项目集成

1. **添加依赖**
```xml
<dependency>
    <groupId>com.mall</groupId>
    <artifactId>inventory-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

2. **注入服务**
```java
@Service
public class InventoryServiceImpl {
    
    @Reference
    private InventoryDubboService inventoryDubboService;
    
    @Reference
    private InventoryQueryDubboService inventoryQueryDubboService;
    
    @Reference
    private WarehouseDubboService warehouseDubboService;
    
    // 业务方法
}
```

3. **调用示例**
```java
// 采购入库
StockUpdateRequest stockRequest = new StockUpdateRequest();
stockRequest.setSkuId(1L);
stockRequest.setQuantity(100);
stockRequest.setOperationType(1); // 增加
stockRequest.setBusinessType("PURCHASE");
stockRequest.setBusinessNo("PO202401010001");
Result<StockVO> stockResult = inventoryDubboService.updateStock(stockRequest);

// 订单锁定库存
StockLockRequest lockRequest = new StockLockRequest();
lockRequest.setSkuId(1L);
lockRequest.setQuantity(5);
lockRequest.setOrderNo("ORD202401010001");
lockRequest.setExpireTime(LocalDateTime.now().plusMinutes(30));
Result<StockLockVO> lockResult = inventoryDubboService.lockStock(lockRequest);

// 检查库存
Result<Boolean> checkResult = inventoryDubboService.checkStock(1L, 10);

// 查询库存
Result<StockVO> getResult = inventoryDubboService.getStockBySkuId(1L);

// 获取仓库列表
Result<List<WarehouseVO>> warehouseResult = warehouseDubboService.getActiveWarehouses();
```

## 业务流程

### 采购入库流程
1. 采购订单生成
2. 货物到仓验收
3. 库存增加操作
4. 生成入库记录
5. 更新库存状态

### 销售出库流程
1. 客户下单
2. 锁定库存
3. 订单支付
4. 扣减锁定库存
5. 生成出库单
6. 实物出库

### 库存调拨流程
1. 创建调拨申请
2. 审核调拨申请
3. 源仓库扣减库存
4. 目标仓库增加库存
5. 生成调拨记录
6. 更新库存状态

### 库存盘点流程
1. 制定盘点计划
2. 执行库存盘点
3. 记录实际库存
4. 计算盘点差异
5. 审核盘点结果
6. 调整库存数据

### 库存预警流程
1. 系统定时检查库存
2. 识别低库存商品
3. 发送预警通知
4. 生成补货建议
5. 执行补货操作 