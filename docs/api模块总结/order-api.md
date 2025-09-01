完成！我已经为您创建了完整的 order-api 模块，包括：
📁 创建的主要文件结构：

pom.xml - Maven配置文件，定义了依赖关系
Constants & Enums - 订单服务常量和枚举类
Request DTOs - 完整的请求数据传输对象
Response DTOs - 完整的响应数据传输对象
Dubbo Service Interfaces - RPC服务接口定义
Feign Client Interfaces - HTTP服务接口定义
Event Classes - 订单相关事件类
Additional DTOs - 补充的DTO类

🔑 主要特性：
订单核心功能：

订单创建、结算、支付
订单查询、取消、删除
订单发货、收货、完成
订单统计分析

退款管理：

退款申请、处理、审批
退款查询、取消
退款统计

事件驱动：

订单创建事件
支付成功事件
订单取消事件
订单完成事件
退款审批事件

接口设计：

支持Dubbo RPC调用
支持Feign HTTP调用
完整的降级处理
统一的响应格式