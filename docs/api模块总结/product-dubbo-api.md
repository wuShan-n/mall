# 商品服务 Dubbo API 文档

## 服务概述

商品服务(product-service)提供商品管理、分类管理、品牌管理、属性管理等核心功能的Dubbo RPC接口。

**服务名称**: `product-service`  
**API版本**: `1.0.0`  
**包路径**: `com.mall.api.product.dubbo`

## 服务接口清单

### 1. ProductDubboService - 商品管理服务

**接口路径**: `com.mall.api.product.dubbo.ProductDubboService`

#### 1.1 创建SPU
```java
Result<SpuVO> createSpu(SpuCreateRequest request);
```

**功能描述**: 创建商品SPU(Standard Product Unit)  
**参数说明**:
- `request`: SPU创建请求对象
  - `productName`: 商品名称
  - `productCode`: 商品编码
  - `categoryId`: 分类ID
  - `brandId`: 品牌ID
  - `mainImage`: 主图URL
  - `images`: 图片列表
  - `videoUrl`: 视频URL
  - `unit`: 单位
  - `weight`: 重量
  - `introduction`: 商品介绍
  - `keywords`: 关键词
  - `tags`: 标签
  - `isNew`: 是否新品
  - `isHot`: 是否热销
  - `isBest`: 是否精品
  - `status`: 状态 (0-下架, 1-上架)
  - `sort`: 排序
  - `skuList`: SKU列表
  - `attributes`: 商品属性

**返回结果**: SPU信息对象

**调用示例**:
```java
@Reference
private ProductDubboService productDubboService;

SpuCreateRequest request = new SpuCreateRequest();
request.setProductName("iPhone 15 Pro");
request.setProductCode("IP15PRO");
request.setCategoryId(1L);
request.setBrandId(1L);
request.setMainImage("https://example.com/main.jpg");
Result<SpuVO> result = productDubboService.createSpu(request);
```

#### 1.2 更新SPU
```java
Result<SpuVO> updateSpu(Long spuId, SpuCreateRequest request);
```

**功能描述**: 更新商品SPU信息

#### 1.3 删除SPU
```java
Result<Void> deleteSpu(Long spuId);
```

**功能描述**: 删除商品SPU

#### 1.4 获取SPU详情
```java
Result<SpuVO> getSpuById(Long spuId);
Result<SpuDetailVO> getSpuDetail(Long spuId);
```

**功能描述**: 获取SPU基本信息或详细信息(包含SKU列表)

#### 1.5 查询商品列表
```java
Result<PageResult<SpuVO>> queryProducts(ProductQueryRequest request);
```

**功能描述**: 分页查询商品列表  
**参数说明**:
- `request`: 查询条件
  - `productName`: 商品名称 (模糊匹配)
  - `productCode`: 商品编码
  - `categoryId`: 分类ID
  - `brandId`: 品牌ID
  - `minPrice`: 最低价格
  - `maxPrice`: 最高价格
  - `status`: 状态
  - `auditStatus`: 审核状态
  - `isNew`: 是否新品
  - `isHot`: 是否热销
  - `isBest`: 是否精品
  - `keywords`: 关键词
  - `startTime`: 开始时间
  - `endTime`: 结束时间

#### 1.6 商品状态管理
```java
Result<Void> updateProductStatus(Long spuId, Integer status);
Result<Void> batchUpdateProductStatus(List<Long> spuIds, Integer status);
```

**功能描述**: 单个或批量更新商品状态

#### 1.7 SKU管理
```java
Result<SkuVO> getSkuById(Long skuId);
Result<List<SkuVO>> getSkusBySpuId(Long spuId);
Result<List<SkuVO>> getSkusByIds(List<Long> skuIds);
```

**功能描述**: SKU信息查询

#### 1.8 商品统计
```java
Result<ProductStatisticsVO> getProductStatistics();
```

**功能描述**: 获取商品统计数据

---

### 2. CategoryDubboService - 分类管理服务

**接口路径**: `com.mall.api.product.dubbo.CategoryDubboService`

#### 2.1 创建分类
```java
Result<CategoryVO> createCategory(CategoryCreateRequest request);
```

**功能描述**: 创建商品分类  
**参数说明**:
- `request`: 分类创建请求
  - `parentId`: 父分类ID (0为根分类)
  - `categoryName`: 分类名称
  - `categoryCode`: 分类编码
  - `level`: 分类层级
  - `sort`: 排序
  - `icon`: 图标URL
  - `description`: 描述
  - `status`: 状态

#### 2.2 更新分类
```java
Result<CategoryVO> updateCategory(Long categoryId, CategoryCreateRequest request);
```

#### 2.3 删除分类
```java
Result<Void> deleteCategory(Long categoryId);
```

#### 2.4 获取分类信息
```java
Result<CategoryVO> getCategoryById(Long categoryId);
Result<List<CategoryVO>> getCategoryTree();
Result<List<CategoryVO>> getCategoriesByParentId(Long parentId);
Result<List<CategoryVO>> getCategoryPath(Long categoryId);
```

**功能描述**: 获取分类信息、分类树、子分类、分类路径

#### 2.5 分类状态管理
```java
Result<Void> updateCategoryStatus(Long categoryId, Integer status);
```

---

### 3. BrandDubboService - 品牌管理服务

**接口路径**: `com.mall.api.product.dubbo.BrandDubboService`

#### 3.1 创建品牌
```java
Result<BrandVO> createBrand(BrandCreateRequest request);
```

**功能描述**: 创建品牌  
**参数说明**:
- `request`: 品牌创建请求
  - `brandName`: 品牌名称
  - `brandCode`: 品牌编码
  - `logo`: 品牌Logo URL
  - `description`: 品牌描述
  - `sort`: 排序
  - `status`: 状态

#### 3.2 更新品牌
```java
Result<BrandVO> updateBrand(Long brandId, BrandCreateRequest request);
```

#### 3.3 删除品牌
```java
Result<Void> deleteBrand(Long brandId);
```

#### 3.4 获取品牌信息
```java
Result<BrandVO> getBrandById(Long brandId);
Result<List<BrandVO>> getAllBrands();
Result<PageResult<BrandVO>> queryBrands(PageRequest request);
Result<List<BrandVO>> getBrandsByCategoryId(Long categoryId);
```

#### 3.5 品牌状态管理
```java
Result<Void> updateBrandStatus(Long brandId, Integer status);
```

---

### 4. AttributeDubboService - 属性管理服务

**接口路径**: `com.mall.api.product.dubbo.AttributeDubboService`

#### 4.1 获取属性
```java
Result<List<AttributeVO>> getAttributesByCategoryId(Long categoryId);
Result<List<AttributeVO>> getAttributesByCategoryIdAndType(Long categoryId, Integer attrType);
Result<AttributeVO> getAttributeById(Long attributeId);
```

**功能描述**: 获取分类属性列表

#### 4.2 属性管理
```java
Result<AttributeVO> createAttribute(AttributeVO attribute);
Result<AttributeVO> updateAttribute(Long attributeId, AttributeVO attribute);
Result<Void> deleteAttribute(Long attributeId);
```

## 数据模型

### SpuVO - 商品SPU信息
```java
{
    "id": 1,                             // SPU ID
    "productName": "iPhone 15 Pro",     // 商品名称
    "productCode": "IP15PRO",           // 商品编码
    "categoryId": 1,                    // 分类ID
    "categoryName": "智能手机",          // 分类名称
    "brandId": 1,                       // 品牌ID
    "brandName": "Apple",               // 品牌名称
    "mainImage": "https://...",         // 主图URL
    "images": ["https://..."],          // 图片列表
    "videoUrl": "https://...",          // 视频URL
    "unit": "台",                       // 单位
    "weight": "0.5",                    // 重量(kg)
    "introduction": "...",              // 商品介绍
    "keywords": "手机,智能手机,苹果",    // 关键词
    "tags": "新品,热销",                // 标签
    "saleCount": 1000,                  // 销量
    "viewCount": 10000,                 // 浏览量
    "commentCount": 500,                // 评论数
    "score": "4.8",                     // 评分
    "isNew": true,                      // 是否新品
    "isHot": true,                      // 是否热销
    "isBest": false,                    // 是否精品
    "status": 1,                        // 状态
    "auditStatus": 1,                   // 审核状态
    "sort": 0,                          // 排序
    "minPrice": "9999.00",              // 最低价
    "maxPrice": "12999.00",             // 最高价
    "createTime": "2024-01-01 00:00:00" // 创建时间
}
```

### SkuVO - 商品SKU信息
```java
{
    "id": 1,                            // SKU ID
    "spuId": 1,                         // SPU ID
    "skuName": "iPhone 15 Pro 256GB Natural Titanium", // SKU名称
    "skuCode": "IP15PRO-256-NT",        // SKU编码
    "price": "9999.00",                 // 销售价
    "originalPrice": "10999.00",        // 原价
    "costPrice": "8000.00",             // 成本价
    "stock": 100,                       // 库存
    "warnStock": 10,                    // 预警库存
    "image": "https://...",             // SKU图片
    "specs": {                          // 规格值
        "color": "Natural Titanium",
        "storage": "256GB"
    },
    "weight": "0.5",                    // 重量
    "volume": "0.001",                  // 体积
    "saleCount": 50,                    // 销量
    "lockStock": 5,                     // 锁定库存
    "status": 1,                        // 状态
    "createTime": "2024-01-01 00:00:00" // 创建时间
}
```

### CategoryVO - 分类信息
```java
{
    "id": 1,                            // 分类ID
    "parentId": 0,                      // 父分类ID
    "categoryName": "电子产品",          // 分类名称
    "categoryCode": "ELEC",             // 分类编码
    "level": 1,                         // 层级
    "sort": 0,                          // 排序
    "icon": "https://...",              // 图标
    "description": "电子产品分类",       // 描述
    "status": 1,                        // 状态
    "children": [],                     // 子分类
    "createTime": "2024-01-01 00:00:00" // 创建时间
}
```

### BrandVO - 品牌信息
```java
{
    "id": 1,                            // 品牌ID
    "brandName": "Apple",               // 品牌名称
    "brandCode": "APPLE",               // 品牌编码
    "logo": "https://...",              // Logo URL
    "description": "苹果公司",          // 描述
    "sort": 0,                          // 排序
    "status": 1,                        // 状态
    "productCount": 100,                // 商品数量
    "createTime": "2024-01-01 00:00:00" // 创建时间
}
```

### AttributeVO - 属性信息
```java
{
    "id": 1,                            // 属性ID
    "categoryId": 1,                    // 分类ID
    "attrName": "屏幕尺寸",              // 属性名称
    "attrType": 1,                      // 属性类型 (0-规格, 1-参数)
    "inputType": 1,                     // 输入类型 (0-手工, 1-选择)
    "values": "5.8,6.1,6.7",           // 可选值
    "unit": "英寸",                     // 单位
    "isRequired": true,                 // 是否必填
    "isSearch": true,                   // 是否可搜索
    "sort": 0,                          // 排序
    "status": 1,                        // 状态
    "attributeValue": "6.1英寸"         // 属性值
}
```

### ProductStatisticsVO - 商品统计
```java
{
    "totalProducts": 10000,             // 商品总数
    "onShelfProducts": 8000,            // 上架商品数
    "offShelfProducts": 2000,           // 下架商品数
    "totalSkus": 50000,                 // SKU总数
    "outOfStockSkus": 100,              // 缺货SKU数
    "lowStockSkus": 500,                // 低库存SKU数
    "totalCategories": 100,             // 分类总数
    "totalBrands": 50,                  // 品牌总数
    "todayNewProducts": 10,             // 今日新增商品
    "monthNewProducts": 300,            // 本月新增商品
    "totalInventoryValue": "1000000.00", // 总库存价值
    "avgProductRating": "4.5"           // 平均商品评分
}
```

## 错误码说明

| 错误码 | 错误信息 | 说明 |
|-------|---------|------|
| 20001 | 商品不存在 | 查询商品时商品不存在 |
| 20002 | 商品编码已存在 | 创建商品时编码重复 |
| 20003 | SKU不存在 | 查询SKU时SKU不存在 |
| 20004 | SKU编码已存在 | 创建SKU时编码重复 |
| 20005 | 分类不存在 | 查询分类时分类不存在 |
| 20006 | 分类编码已存在 | 创建分类时编码重复 |
| 20007 | 分类有子分类无法删除 | 删除分类时存在子分类 |
| 20008 | 分类下有商品无法删除 | 删除分类时关联商品 |
| 20009 | 品牌不存在 | 查询品牌时品牌不存在 |
| 20010 | 品牌编码已存在 | 创建品牌时编码重复 |
| 20011 | 品牌下有商品无法删除 | 删除品牌时关联商品 |
| 20012 | 属性不存在 | 查询属性时属性不存在 |
| 20013 | 商品已上架无法删除 | 删除上架商品 |
| 20014 | 库存不足 | 商品库存不足 |

## 使用示例

### Spring Boot 项目集成

1. **添加依赖**
```xml
<dependency>
    <groupId>com.mall</groupId>
    <artifactId>product-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

2. **注入服务**
```java
@Service
public class ProductServiceImpl {
    
    @Reference
    private ProductDubboService productDubboService;
    
    @Reference
    private CategoryDubboService categoryDubboService;
    
    @Reference
    private BrandDubboService brandDubboService;
    
    @Reference
    private AttributeDubboService attributeDubboService;
    
    // 业务方法
}
```

3. **调用示例**
```java
// 创建商品SPU
SpuCreateRequest spuRequest = new SpuCreateRequest();
spuRequest.setProductName("iPhone 15 Pro");
spuRequest.setProductCode("IP15PRO");
spuRequest.setCategoryId(1L);
spuRequest.setBrandId(1L);
Result<SpuVO> spuResult = productDubboService.createSpu(spuRequest);

// 查询商品列表
ProductQueryRequest queryRequest = new ProductQueryRequest();
queryRequest.setProductName("iPhone");
queryRequest.setPageNum(1);
queryRequest.setPageSize(20);
Result<PageResult<SpuVO>> queryResult = productDubboService.queryProducts(queryRequest);

// 获取分类树
Result<List<CategoryVO>> categoryResult = categoryDubboService.getCategoryTree();

// 获取品牌列表
Result<List<BrandVO>> brandResult = brandDubboService.getAllBrands();

// 获取分类属性
Result<List<AttributeVO>> attrResult = attributeDubboService.getAttributesByCategoryId(1L);
```

## 业务流程

### 商品发布流程
1. 创建/选择商品分类
2. 创建/选择商品品牌
3. 创建商品SPU基本信息
4. 添加商品SKU规格信息
5. 设置商品属性参数
6. 商品审核
7. 商品上架

### 库存管理流程
1. 商品SKU创建时设置初始库存
2. 订单创建时锁定库存
3. 订单支付成功扣减库存
4. 订单取消释放锁定库存
5. 库存预警通知
6. 库存盘点调整 