# 用户服务 Dubbo API 文档

## 服务概述

用户服务(user-service)提供用户管理、地址管理、会员管理等核心功能的Dubbo RPC接口。

**服务名称**: `user-service`  
**API版本**: `1.0.0`  
**包路径**: `com.mall.api.user.dubbo`

## 服务接口清单

### 1. UserDubboService - 用户基础服务

**接口路径**: `com.mall.api.user.dubbo.UserDubboService`

#### 1.1 用户注册
```java
Result<UserVO> register(UserRegisterRequest request);
```

**功能描述**: 用户注册  
**参数说明**:
- `request`: 用户注册请求对象
  - `username`: 用户名 (4-20位字母数字下划线)
  - `password`: 密码 (6-20位)
  - `phone`: 手机号 (可选)
  - `email`: 邮箱 (可选)
  - `nickname`: 昵称 (可选)
  - `verifyCode`: 验证码 (可选)
  - `registerSource`: 注册来源 (1-PC, 2-APP, 3-小程序)

**返回结果**: 用户信息对象

**调用示例**:
```java
@Reference
private UserDubboService userDubboService;

UserRegisterRequest request = new UserRegisterRequest();
request.setUsername("john_doe");
request.setPassword("123456");
request.setPhone("13800138000");
Result<UserVO> result = userDubboService.register(request);
```

#### 1.2 用户登录
```java
Result<UserLoginVO> login(UserLoginRequest request);
```

**功能描述**: 用户登录认证  
**参数说明**:
- `request`: 用户登录请求对象
  - `account`: 账号 (用户名/手机号/邮箱)
  - `password`: 密码
  - `loginType`: 登录类型 (1-密码, 2-短信验证码)
  - `verifyCode`: 验证码 (短信登录时必填)
  - `rememberMe`: 记住我
  - `clientIp`: 客户端IP
  - `userAgent`: 用户代理

**返回结果**: 登录成功信息(包含Token)

#### 1.3 用户登出
```java
Result<Void> logout(String token);
```

**功能描述**: 用户登出  
**参数说明**:
- `token`: 访问令牌

#### 1.4 获取用户信息
```java
Result<UserVO> getUserById(Long userId);
```

**功能描述**: 根据用户ID获取用户信息  
**参数说明**:
- `userId`: 用户ID

**返回结果**: 用户信息对象

#### 1.5 更新用户信息
```java
Result<UserVO> updateUser(UserUpdateRequest request);
```

**功能描述**: 更新用户信息  
**参数说明**:
- `request`: 用户更新请求对象
  - `id`: 用户ID
  - `nickname`: 昵称
  - `avatar`: 头像URL
  - `gender`: 性别 (0-未知, 1-男, 2-女)
  - `birthday`: 生日
  - `phone`: 手机号
  - `email`: 邮箱

#### 1.6 查询用户列表
```java
Result<PageResult<UserVO>> queryUsers(UserQueryRequest request);
```

**功能描述**: 分页查询用户列表  
**参数说明**:
- `request`: 查询条件
  - `username`: 用户名 (模糊匹配)
  - `nickname`: 昵称 (模糊匹配)
  - `phone`: 手机号 (模糊匹配)
  - `email`: 邮箱 (模糊匹配)
  - `status`: 状态
  - `gender`: 性别
  - `registerStartTime`: 注册开始时间
  - `registerEndTime`: 注册结束时间
  - `pageNum`: 页码
  - `pageSize`: 页大小

#### 1.7 校验用户名是否存在
```java
Result<Boolean> checkUsernameExists(String username);
```

#### 1.8 校验手机号是否存在
```java
Result<Boolean> checkPhoneExists(String phone);
```

#### 1.9 校验邮箱是否存在
```java
Result<Boolean> checkEmailExists(String email);
```

---

### 2. AddressDubboService - 地址管理服务

**接口路径**: `com.mall.api.user.dubbo.AddressDubboService`

#### 2.1 创建地址
```java
Result<AddressVO> createAddress(Long userId, AddressCreateRequest request);
```

**功能描述**: 创建用户收货地址  
**参数说明**:
- `userId`: 用户ID
- `request`: 地址创建请求
  - `receiverName`: 收货人姓名
  - `receiverPhone`: 收货人电话
  - `provinceCode`: 省份编码
  - `province`: 省份
  - `cityCode`: 城市编码
  - `city`: 城市
  - `districtCode`: 区县编码
  - `district`: 区县
  - `detailAddress`: 详细地址
  - `postalCode`: 邮政编码
  - `addressTag`: 地址标签 (如:家,公司)
  - `isDefault`: 是否默认地址

#### 2.2 更新地址
```java
Result<AddressVO> updateAddress(Long addressId, AddressCreateRequest request);
```

#### 2.3 删除地址
```java
Result<Void> deleteAddress(Long userId, Long addressId);
```

#### 2.4 获取地址详情
```java
Result<AddressVO> getAddressById(Long addressId);
```

#### 2.5 获取用户地址列表
```java
Result<List<AddressVO>> getUserAddresses(Long userId);
```

#### 2.6 获取默认地址
```java
Result<AddressVO> getDefaultAddress(Long userId);
```

#### 2.7 设置默认地址
```java
Result<Void> setDefaultAddress(Long userId, Long addressId);
```

---

### 3. MemberDubboService - 会员服务

**接口路径**: `com.mall.api.user.dubbo.MemberDubboService`

#### 3.1 获取会员信息
```java
Result<MemberVO> getMemberByUserId(Long userId);
```

**功能描述**: 根据用户ID获取会员信息

#### 3.2 积分操作
```java
Result<Integer> addPoints(Long userId, Integer points, String source, String sourceId);
Result<Integer> deductPoints(Long userId, Integer points, String source, String sourceId);
```

**功能描述**: 增加/扣减用户积分  
**参数说明**:
- `userId`: 用户ID
- `points`: 积分数量
- `source`: 积分来源
- `sourceId`: 来源业务ID

#### 3.3 成长值操作
```java
Result<Integer> addGrowthValue(Long userId, Integer growthValue);
```

**功能描述**: 增加用户成长值

#### 3.4 余额操作
```java
Result<BigDecimal> rechargeBalance(Long userId, BigDecimal amount);
Result<BigDecimal> deductBalance(Long userId, BigDecimal amount);
```

**功能描述**: 充值/扣减用户余额

#### 3.5 更新会员等级
```java
Result<MemberVO> updateMemberLevel(Long userId);
```

**功能描述**: 根据成长值自动更新会员等级

#### 3.6 获取会员折扣
```java
Result<BigDecimal> getMemberDiscount(Long userId);
```

**功能描述**: 获取用户当前会员等级对应的折扣率

## 数据模型

### UserVO - 用户信息
```java
{
    "id": 1,                              // 用户ID
    "username": "john_doe",               // 用户名
    "nickname": "John",                   // 昵称
    "phone": "138****8000",              // 手机号(脱敏)
    "email": "j***@example.com",         // 邮箱(脱敏)
    "avatar": "https://...",             // 头像URL
    "gender": 1,                         // 性别
    "birthday": "1990-01-01",            // 生日
    "status": 0,                         // 状态
    "registerSource": 1,                 // 注册来源
    "lastLoginTime": "2024-01-01 12:00:00", // 最后登录时间
    "lastLoginIp": "192.168.1.1",       // 最后登录IP
    "createTime": "2024-01-01 00:00:00"  // 创建时间
}
```

### AddressVO - 地址信息
```java
{
    "id": 1,                             // 地址ID
    "userId": 1,                         // 用户ID
    "receiverName": "John Doe",          // 收货人姓名
    "receiverPhone": "13800138000",      // 收货人电话
    "provinceCode": "110000",            // 省份编码
    "province": "北京",                   // 省份
    "cityCode": "110100",                // 城市编码
    "city": "北京",                       // 城市
    "districtCode": "110101",            // 区县编码
    "district": "东城区",                 // 区县
    "detailAddress": "某某街道1号",        // 详细地址
    "postalCode": "100000",              // 邮政编码
    "addressTag": "家",                   // 地址标签
    "isDefault": true,                   // 是否默认
    "fullAddress": "北京北京东城区某某街道1号", // 完整地址
    "createTime": "2024-01-01 00:00:00"  // 创建时间
}
```

### MemberVO - 会员信息
```java
{
    "id": 1,                             // 会员ID
    "userId": 1,                         // 用户ID
    "memberNo": "M202401010001",         // 会员编号
    "levelId": 1,                        // 等级ID
    "levelName": "Silver",               // 等级名称
    "points": 1000,                      // 积分
    "growthValue": 500,                  // 成长值
    "balance": "100.00",                 // 余额
    "totalAmount": "5000.00",            // 累计消费金额
    "totalOrders": 20,                   // 累计订单数
    "expireTime": "2025-01-01 00:00:00", // 会员到期时间
    "discount": "0.95",                  // 折扣率
    "privileges": "..."                  // 会员特权
}
```

## 错误码说明

| 错误码 | 错误信息 | 说明 |
|-------|---------|------|
| 10001 | 用户名已存在 | 注册时用户名重复 |
| 10002 | 手机号已存在 | 注册时手机号重复 |
| 10003 | 邮箱已存在 | 注册时邮箱重复 |
| 10004 | 用户名或密码错误 | 登录认证失败 |
| 10005 | 用户不存在 | 查询用户时用户不存在 |
| 10006 | 用户已被禁用 | 用户状态异常 |
| 10007 | 验证码错误 | 短信验证码错误 |
| 10008 | 地址不存在 | 查询地址时地址不存在 |
| 10009 | 积分不足 | 扣减积分时余额不足 |
| 10010 | 余额不足 | 扣减余额时余额不足 |

## 使用示例

### Spring Boot 项目集成

1. **添加依赖**
```xml
<dependency>
    <groupId>com.mall</groupId>
    <artifactId>user-api</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

2. **注入服务**
```java
@Service
public class UserServiceImpl {
    
    @Reference
    private UserDubboService userDubboService;
    
    @Reference
    private AddressDubboService addressDubboService;
    
    @Reference
    private MemberDubboService memberDubboService;
    
    // 业务方法
}
```

3. **调用示例**
```java
// 用户注册
UserRegisterRequest registerRequest = new UserRegisterRequest();
registerRequest.setUsername("testuser");
registerRequest.setPassword("123456");
Result<UserVO> registerResult = userDubboService.register(registerRequest);

// 用户登录
UserLoginRequest loginRequest = new UserLoginRequest();
loginRequest.setAccount("testuser");
loginRequest.setPassword("123456");
Result<UserLoginVO> loginResult = userDubboService.login(loginRequest);

// 获取用户地址
Result<List<AddressVO>> addressResult = addressDubboService.getUserAddresses(1L);

// 获取会员信息
Result<MemberVO> memberResult = memberDubboService.getMemberByUserId(1L);
``` 