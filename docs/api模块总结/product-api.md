我已经为您创建了完整的product-api接口定义模块。这个模块包含了商品服务所需的所有接口定义和数据传输对象。
📁 项目结构概览
1. POM配置文件

定义了模块依赖，继承自mall-api父模块

2. 常量定义 (ProductConstants.java)

服务名称、API版本、路径前缀
商品状态、审核状态、属性类型等常量

3. 请求DTOs (Request)

CategoryCreateRequest - 分类创建/更新
BrandCreateRequest - 品牌创建/更新
SpuCreateRequest - SPU创建（包含SKU列表和属性）
SkuCreateRequest - SKU创建
ProductQueryRequest - 商品查询（支持多条件筛选）
StockUpdateRequest - 库存更新
ProductAttributeValue - 商品属性值

4. 响应DTOs (Response)

CategoryVO - 分类信息（支持树形结构）
BrandVO - 品牌信息
SpuVO - SPU基本信息
SpuDetailVO - SPU详细信息（包含SKU列表）
SkuVO - SKU信息
AttributeVO - 属性信息
SpecificationVO - 规格信息
ProductStatisticsVO - 商品统计数据

5. Dubbo接口

CategoryDubboService - 分类服务RPC接口
BrandDubboService - 品牌服务RPC接口
ProductDubboService - 商品服务RPC接口（核心）
AttributeDubboService - 属性服务RPC接口

6. Feign客户端

CategoryFeignClient - 分类服务HTTP客户端
ProductFeignClient - 商品服务HTTP客户端（包含SPU/SKU/库存操作）

7. 枚举类

ProductStatusEnum - 商品状态枚举
AuditStatusEnum - 审核状态枚举
StockOperationEnum - 库存操作枚举

🎯 核心功能特性
商品管理

SPU/SKU模型：支持多规格商品管理
分类体系：多级分类树形结构
品牌管理：独立的品牌维护
属性管理：灵活的商品属性和规格配置

库存管理

库存操作：入库、出库、锁定、释放
库存预警：低库存提醒
并发控制：乐观锁版本控制

查询功能

多条件筛选：价格区间、分类、品牌、状态等
分页查询：支持排序和分页
统计数据：商品统计信息

设计特点

双协议支持：Dubbo（内部RPC）+ Feign（HTTP REST）
参数验证：完整的请求参数校验
API文档：Swagger注解支持
扩展性强：预留了多种扩展字段

这个模块作为商品服务的API契约，为其他服务提供了标准化的商品相关接口调用方式。