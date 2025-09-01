我已经为您创建了完整的user-api接口定义模块，包含以下主要组件：
📁 项目结构
1. POM配置文件

定义了模块依赖，包括common-core、Feign、Dubbo、验证框架等

2. 常量定义 (UserConstants.java)

服务名称、API版本、路径前缀
用户状态、注册来源、性别等常量

3. 请求DTOs (Request)

UserRegisterRequest - 用户注册请求
UserLoginRequest - 用户登录请求
UserUpdateRequest - 用户信息更新
UserQueryRequest - 用户查询（分页）
AddressCreateRequest - 地址创建
PasswordChangeRequest - 密码修改
PasswordResetRequest - 密码重置
ThirdPartyLoginRequest - 第三方登录
PointRecordQueryRequest - 积分记录查询

4. 响应DTOs (Response)

UserVO - 用户信息视图对象
UserLoginVO - 登录响应（包含token）
MemberVO - 会员信息
AddressVO - 地址信息
UserStatisticsVO - 用户统计
PointRecordVO - 积分记录
MemberLevelVO - 会员等级

5. 枚举类 (Enums)

UserStatusEnum - 用户状态枚举
LoginTypeEnum - 登录类型枚举
PointSourceEnum - 积分来源枚举
RegisterSourceEnum - 注册来源枚举

6. Dubbo接口

UserDubboService - 用户服务RPC接口
MemberDubboService - 会员服务RPC接口
AddressDubboService - 地址服务RPC接口

7. Feign客户端

UserFeignClient - 用户服务HTTP客户端
MemberFeignClient - 会员服务HTTP客户端
AddressFeignClient - 地址服务HTTP客户端
UserFeignFallbackFactory - 降级处理

🎯 设计特点

双协议支持：同时提供Dubbo（RPC）和Feign（HTTP）两种调用方式
参数验证：使用Jakarta Validation进行请求参数校验
数据脱敏：使用@Sensitive注解标记敏感字段
API文档：集成Swagger注解生成API文档
服务降级：提供Feign的FallbackFactory实现
分层清晰：严格按照DTO模式设计，请求响应分离

这个模块作为用户服务的API契约，可以被其他服务依赖，实现服务间的解耦和标准化调用。