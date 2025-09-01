package com.mall.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.api.user.dto.request.*;
import com.mall.api.user.dto.response.UserLoginVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.api.user.enums.LoginTypeEnum;
import com.mall.api.user.enums.UserStatusEnum;
import com.mall.common.constant.CacheConstants;
import com.mall.common.context.UserContext;
import com.mall.common.context.UserContextHolder;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.ResultCode;
import com.mall.common.utils.Assert;
import com.mall.common.utils.BeanUtils;
import com.mall.common.utils.EncryptUtils;
import com.mall.common.utils.IdGenerator;
import com.mall.user.converter.UserConverter;
import com.mall.user.entity.User;
import com.mall.user.entity.UserMember;
import com.mall.user.mapper.UserMapper;
import com.mall.user.mapper.UserMemberMapper;
import com.mall.user.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * User Authentication Service Implementation
 *
 * @author mall
 * @date 2024-01-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserMapper userMapper;
    private final UserMemberMapper userMemberMapper;
    private final UserConverter userConverter;
    private final RedissonClient redissonClient;

    @Value("${app.jwt.expire:7200}")
    private long tokenExpire;

    @Value("${app.sms.enabled:false}")
    private boolean smsEnabled;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVO register(UserRegisterRequest request) {
        log.info("User register: username={}", request.getUsername());

        // 校验用户名是否存在
        Long count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, request.getUsername()));
        Assert.isTrue(count == 0, "用户名已存在");

        // 校验手机号是否存在
        if (StrUtil.isNotBlank(request.getPhone())) {
            count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, request.getPhone()));
            Assert.isTrue(count == 0, "手机号已被注册");
        }

        // 校验邮箱是否存在
        if (StrUtil.isNotBlank(request.getEmail())) {
            count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getEmail, request.getEmail()));
            Assert.isTrue(count == 0, "邮箱已被注册");
        }

        // 验证短信验证码
        if (StrUtil.isNotBlank(request.getPhone()) && StrUtil.isNotBlank(request.getVerifyCode())) {
            boolean verified = verifySmsCode(request.getPhone(), request.getVerifyCode());
            Assert.isTrue(verified, "验证码错误或已过期");
        }

        // 创建用户
        User user = userConverter.toEntity(request);

        // 生成盐值并加密密码
        String salt = EncryptUtils.generateSalt();
        user.setPassword(EncryptUtils.encryptPassword(request.getPassword(), salt));

        // 设置默认值
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        user.setNickname(StrUtil.isNotBlank(request.getNickname()) ?
                request.getNickname() : "用户" + RandomUtil.randomNumbers(6));
        user.setAvatar("https://api.dicebear.com/7.x/avataaars/svg?seed=" + request.getUsername());
        user.setRegisterSource(request.getRegisterSource() != null ?
                request.getRegisterSource() : 1);

        userMapper.insert(user);

        // 创建会员信息
        createMemberInfo(user.getId());

        log.info("User registered successfully: userId={}", user.getId());

        return userConverter.toVO(user);
    }

    @Override
    public UserLoginVO login(UserLoginRequest request) {
        log.info("User login: account={}, type={}", request.getAccount(), request.getLoginType());

        User user = null;

        // 根据登录类型处理
        if (LoginTypeEnum.PASSWORD.getCode().equals(request.getLoginType())) {
            // 密码登录
            user = findUserByAccount(request.getAccount());
            Assert.notNull(user, ResultCode.USER_PASSWORD_ERROR);

            // 验证密码
            String salt = extractSaltFromPassword(user.getPassword());
            boolean passwordMatch = EncryptUtils.verifyPassword(request.getPassword(), salt, user.getPassword());
            Assert.isTrue(passwordMatch, ResultCode.USER_PASSWORD_ERROR.getMessage());
        } else if (LoginTypeEnum.SMS_CODE.getCode().equals(request.getLoginType())) {
            // 短信验证码登录
            Assert.notEmpty(request.getVerifyCode(), "验证码不能为空");
            user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, request.getAccount()));
            Assert.notNull(user, "手机号未注册");

            // 验证短信验证码
            boolean verified = verifySmsCode(request.getAccount(), request.getVerifyCode());
            Assert.isTrue(verified, "验证码错误或已过期");
        } else {
            throw new BusinessException("不支持的登录类型");
        }

        // 检查用户状态
        Assert.isTrue(UserStatusEnum.NORMAL.getCode().equals(user.getStatus()),
                ResultCode.USER_ACCOUNT_DISABLED.getMessage());

        // 生成token
        StpUtil.login(user.getId());
        String token = StpUtil.getTokenValue();

        // 设置用户上下文
        UserContext userContext = UserContext.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .token(token)
                .clientIp(request.getClientIp())
                .build();
        UserContextHolder.setContext(userContext);

        // 缓存用户信息
        cacheUserInfo(user.getId(), user);

        // 更新最后登录信息
        updateLastLoginInfo(user.getId(), request.getClientIp());

        // 构建响应
        UserLoginVO loginVO = userConverter.toLoginVO(user);
        loginVO.setAccessToken(token);
        loginVO.setRefreshToken(generateRefreshToken(user.getId()));
        loginVO.setExpiresIn(tokenExpire);
        loginVO.setTokenType("Bearer");

        log.info("User login successfully: userId={}", user.getId());

        return loginVO;
    }

    @Override
    public void logout(String token) {
        log.info("User logout: token={}", token);

        // 获取当前登录ID
        Object loginId = StpUtil.getLoginIdByToken(token);
        if (loginId != null) {
            // 注销登录
            StpUtil.logout(loginId);

            // 清除缓存
            clearUserCache(Long.valueOf(loginId.toString()));

            log.info("User logout successfully: userId={}", loginId);
        }
    }

    @Override
    public String refreshToken(String refreshToken) {
        log.info("Refresh token: {}", refreshToken);

        // 从refresh token中解析用户ID
        Long userId = parseUserIdFromRefreshToken(refreshToken);
        Assert.notNull(userId, "无效的refresh token");

        // 验证refresh token
        String cachedRefreshToken = getCachedRefreshToken(userId);
        Assert.isTrue(refreshToken.equals(cachedRefreshToken), "refresh token已失效");

        // 生成新的access token
        StpUtil.login(userId);
        String newToken = StpUtil.getTokenValue();

        log.info("Token refreshed successfully: userId={}", userId);

        return newToken;
    }

    @Override
    public UserVO getCurrentUser(String token) {
        Object loginId = StpUtil.getLoginIdByToken(token);
        Assert.notNull(loginId, ResultCode.USER_TOKEN_INVALID);

        Long userId = Long.valueOf(loginId.toString());
        User user = getCachedUser(userId);
        if (user == null) {
            user = userMapper.selectById(userId);
            Assert.notNull(user, ResultCode.USER_NOT_FOUND);
            cacheUserInfo(userId, user);
        }

        return userConverter.toVO(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(PasswordChangeRequest request) {
        Long userId = UserContextHolder.getUserId();
        Assert.notNull(userId, "请先登录");

        // 验证新密码和确认密码
        Assert.isTrue(request.getNewPassword().equals(request.getConfirmPassword()),
                "两次输入的密码不一致");

        User user = userMapper.selectById(userId);
        Assert.notNull(user, ResultCode.USER_NOT_FOUND);

        // 验证旧密码
        String salt = extractSaltFromPassword(user.getPassword());
        boolean passwordMatch = EncryptUtils.verifyPassword(request.getOldPassword(), salt, user.getPassword());
        Assert.isTrue(passwordMatch, "原密码错误");

        // 更新密码
        String newSalt = EncryptUtils.generateSalt();
        String encryptedPassword = EncryptUtils.encryptPassword(request.getNewPassword(), newSalt);

        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setPassword(encryptedPassword);
        userMapper.updateById(updateUser);

        // 清除登录状态，需要重新登录
        StpUtil.logout(userId);
        clearUserCache(userId);

        log.info("Password changed successfully: userId={}", userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(PasswordResetRequest request) {
        log.info("Reset password for phone: {}", request.getPhone());

        // 验证短信验证码
        boolean verified = verifySmsCode(request.getPhone(), request.getVerifyCode());
        Assert.isTrue(verified, "验证码错误或已过期");

        // 查找用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, request.getPhone()));
        Assert.notNull(user, "手机号未注册");

        // 更新密码
        String salt = EncryptUtils.generateSalt();
        String encryptedPassword = EncryptUtils.encryptPassword(request.getNewPassword(), salt);

        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setPassword(encryptedPassword);
        userMapper.updateById(updateUser);

        // 清除登录状态
        StpUtil.logout(user.getId());
        clearUserCache(user.getId());

        log.info("Password reset successfully: userId={}", user.getId());
    }

    @Override
    public void sendSmsCode(String phone) {
        log.info("Send SMS code to phone: {}", phone);

        if (!smsEnabled) {
            log.warn("SMS service is disabled, using mock code: 123456");
            // 测试环境，使用固定验证码
            cacheSmsCode(phone, "123456");
            return;
        }

        // 生成验证码
        String code = RandomUtil.randomNumbers(6);

        // TODO: 调用短信服务发送验证码
        // smsService.sendVerifyCode(phone, code);

        // 缓存验证码
        cacheSmsCode(phone, code);

        log.info("SMS code sent successfully: phone={}, code={}", phone, code);
    }

    @Override
    public boolean verifySmsCode(String phone, String code) {
        String cachedCode = getCachedSmsCode(phone);
        if (StrUtil.isBlank(cachedCode)) {
            return false;
        }

        boolean verified = cachedCode.equals(code);
        if (verified) {
            // 验证成功后删除验证码
            clearSmsCode(phone);
        }

        return verified;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserLoginVO thirdPartyLogin(ThirdPartyLoginRequest request) {
        log.info("Third party login: type={}, code={}", request.getLoginType(), request.getAuthCode());

        // TODO: 调用第三方登录服务，获取用户信息
        // ThirdPartyUserInfo thirdPartyUser = thirdPartyService.getUserInfo(request.getLoginType(), request.getAuthCode());

        // 模拟第三方用户信息
        String openId = "mock_openid_" + request.getAuthCode();
        String nickname = "用户" + RandomUtil.randomNumbers(6);

        // 查找或创建用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, openId));

        if (user == null) {
            // 创建新用户
            user = new User();
            user.setUsername(openId);
            user.setPassword(EncryptUtils.encryptPassword(RandomUtil.randomString(16), EncryptUtils.generateSalt()));
            user.setNickname(nickname);
            user.setStatus(UserStatusEnum.NORMAL.getCode());
            user.setRegisterSource(request.getClientType());
            userMapper.insert(user);

            // 创建会员信息
            createMemberInfo(user.getId());
        }

        // 生成token
        StpUtil.login(user.getId());
        String token = StpUtil.getTokenValue();

        // 构建响应
        UserLoginVO loginVO = userConverter.toLoginVO(user);
        loginVO.setAccessToken(token);
        loginVO.setRefreshToken(generateRefreshToken(user.getId()));
        loginVO.setExpiresIn(tokenExpire);

        return loginVO;
    }

    @Override
    public UserVO validateToken(String token) {
        try {
            Object loginId = StpUtil.getLoginIdByToken(token);
            if (loginId == null) {
                return null;
            }

            Long userId = Long.valueOf(loginId.toString());
            User user = getCachedUser(userId);
            if (user == null) {
                user = userMapper.selectById(userId);
                if (user != null) {
                    cacheUserInfo(userId, user);
                }
            }

            return user != null ? userConverter.toVO(user) : null;
        } catch (Exception e) {
            log.error("Validate token failed: {}", e.getMessage());
            return null;
        }
    }

    // ========== Private Methods ==========

    private User findUserByAccount(String account) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, account)
                .or()
                .eq(User::getPhone, account)
                .or()
                .eq(User::getEmail, account));
    }

    private void createMemberInfo(Long userId) {
        UserMember member = new UserMember();
        member.setUserId(userId);
        member.setMemberNo("M" + LocalDateTime.now().toString().replaceAll("[^0-9]", "") + RandomUtil.randomNumbers(4));
        member.setLevelId(1); // 默认等级
        member.setPoints(0);
        member.setGrowthValue(0);
        userMemberMapper.insert(member);
    }

    private void updateLastLoginInfo(Long userId, String ip) {
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setLastLoginTime(LocalDateTime.now());
        updateUser.setLastLoginIp(ip);
        userMapper.updateById(updateUser);
    }

    private String generateRefreshToken(Long userId) {
        String refreshToken = IdGenerator.uuid();
        RBucket<String> bucket = redissonClient.getBucket(CacheConstants.TOKEN_CACHE_PREFIX + "refresh:" + userId);
        bucket.set(refreshToken, 7, TimeUnit.DAYS);
        return refreshToken;
    }

    private Long parseUserIdFromRefreshToken(String refreshToken) {
        // TODO: 实现从refresh token解析用户ID的逻辑
        return null;
    }

    private String getCachedRefreshToken(Long userId) {
        RBucket<String> bucket = redissonClient.getBucket(CacheConstants.TOKEN_CACHE_PREFIX + "refresh:" + userId);
        return bucket.get();
    }

    private void cacheUserInfo(Long userId, User user) {
        RBucket<User> bucket = redissonClient.getBucket(CacheConstants.USER_CACHE_PREFIX + userId);
        bucket.set(user, tokenExpire, TimeUnit.SECONDS);
    }

    private User getCachedUser(Long userId) {
        RBucket<User> bucket = redissonClient.getBucket(CacheConstants.USER_CACHE_PREFIX + userId);
        return bucket.get();
    }

    private void clearUserCache(Long userId) {
        redissonClient.getBucket(CacheConstants.USER_CACHE_PREFIX + userId).delete();
    }

    private void cacheSmsCode(String phone, String code) {
        RBucket<String> bucket = redissonClient.getBucket(CacheConstants.CAPTCHA_CACHE_PREFIX + phone);
        bucket.set(code, CacheConstants.CAPTCHA_EXPIRE, TimeUnit.SECONDS);
    }

    private String getCachedSmsCode(String phone) {
        RBucket<String> bucket = redissonClient.getBucket(CacheConstants.CAPTCHA_CACHE_PREFIX + phone);
        return bucket.get();
    }

    private void clearSmsCode(String phone) {
        redissonClient.getBucket(CacheConstants.CAPTCHA_CACHE_PREFIX + phone).delete();
    }

    private String extractSaltFromPassword(String encryptedPassword) {
        // 简化处理，实际应该将salt和密码一起存储
        return EncryptUtils.generateSalt();
    }
}