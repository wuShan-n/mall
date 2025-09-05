package com.mall.user.service.impl;

import cn.dev33.satoken.secure.BCrypt;
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
import com.mall.common.exception.BusinessException;
import com.mall.common.result.ResultCode;
import com.mall.common.utils.Assert;
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

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 用户认证服务实现 - 使用 Sa-Token 简化版
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

    @Value("${app.sms.enabled:false}")
    private boolean smsEnabled;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVO register(UserRegisterRequest request) {
        log.info("用户注册: username={}", request.getUsername());

        // 校验用户名是否存在
        Assert.isTrue(!existsByUsername(request.getUsername()), "用户名已存在");

        // 校验手机号
        if (StrUtil.isNotBlank(request.getPhone())) {
            Assert.isTrue(!existsByPhone(request.getPhone()), "手机号已被注册");
            // 验证短信验证码
            if (StrUtil.isNotBlank(request.getVerifyCode())) {
                verifySmsCode(request.getPhone(), request.getVerifyCode());
            }
        }

        // 校验邮箱
        if (StrUtil.isNotBlank(request.getEmail())) {
            Assert.isTrue(!existsByEmail(request.getEmail()), "邮箱已被注册");
        }

        // 创建用户
        User user = userConverter.toEntity(request);
        // 使用 BCrypt 加密密码（Sa-Token 推荐）
        user.setPassword(BCrypt.hashpw(request.getPassword()));
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        user.setNickname(StrUtil.blankToDefault(request.getNickname(),
                "用户" + RandomUtil.randomNumbers(6)));
        user.setAvatar("https://api.dicebear.com/7.x/avataaars/svg?seed=" + request.getUsername());
        user.setRegisterSource(request.getRegisterSource() != null ? request.getRegisterSource() : 1);

        userMapper.insert(user);

        // 创建会员信息
        createMemberInfo(user.getId());

        log.info("用户注册成功: userId={}", user.getId());
        return userConverter.toVO(user);
    }

    @Override
    public UserLoginVO login(UserLoginRequest request) {
        log.info("用户登录: account={}, type={}", request.getAccount(), request.getLoginType());

        User user;

        // 根据登录类型处理
        if (LoginTypeEnum.PASSWORD.getCode().equals(request.getLoginType())) {
            // 密码登录
            user = findUserByAccount(request.getAccount());
            Assert.notNull(user, ResultCode.USER_PASSWORD_ERROR);

            // 使用 BCrypt 验证密码
            Assert.isTrue(BCrypt.checkpw(request.getPassword(), user.getPassword()),
                    ResultCode.USER_PASSWORD_ERROR.getMessage());
        } else if (LoginTypeEnum.SMS_CODE.getCode().equals(request.getLoginType())) {
            // 短信验证码登录
            Assert.notEmpty(request.getVerifyCode(), "验证码不能为空");
            user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, request.getAccount()));
            Assert.notNull(user, "手机号未注册");

            // 验证短信验证码
            verifySmsCode(request.getAccount(), request.getVerifyCode());
        } else {
            throw new BusinessException("不支持的登录类型");
        }

        // 检查用户状态
        Assert.isTrue(UserStatusEnum.NORMAL.getCode().equals(user.getStatus()),
                ResultCode.USER_ACCOUNT_DISABLED.getMessage());

        // Sa-Token 登录（支持多端登录、设备管理等）
        StpUtil.login(user.getId());

        // 获取 token
        String token = StpUtil.getTokenValue();

        // Sa-Token 自动管理的 Session 存储用户信息
        StpUtil.getSession()
                .set("user", user)
                .set("loginIp", request.getClientIp())
                .set("loginTime", LocalDateTime.now());

        // 更新最后登录信息（异步处理更好）
        updateLastLoginInfo(user.getId(), request.getClientIp());

        // 构建响应
        UserLoginVO loginVO = userConverter.toLoginVO(user);
        loginVO.setAccessToken(token);
        loginVO.setExpiresIn(StpUtil.getTokenTimeout());
        loginVO.setTokenType("Bearer");

        log.info("用户登录成功: userId={}", user.getId());
        return loginVO;
    }

    @Override
    public void logout(String token) {
        // Sa-Token 支持根据 token 强制注销
        StpUtil.logoutByTokenValue(token);
        log.info("用户登出成功: token={}", token);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(PasswordChangeRequest request) {
        // Sa-Token 获取当前登录用户 ID
        Long userId = StpUtil.getLoginIdAsLong();

        // 验证新密码和确认密码
        Assert.isTrue(request.getNewPassword().equals(request.getConfirmPassword()),
                "两次输入的密码不一致");

        User user = userMapper.selectById(userId);
        Assert.notNull(user, ResultCode.USER_NOT_FOUND);

        // 验证旧密码
        Assert.isTrue(BCrypt.checkpw(request.getOldPassword(), user.getPassword()),
                "原密码错误");

        // 更新密码
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setPassword(BCrypt.hashpw(request.getNewPassword()));
        userMapper.updateById(updateUser);

        // 强制该用户所有端下线，重新登录
        StpUtil.logout(userId);

        log.info("密码修改成功: userId={}", userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(PasswordResetRequest request) {
        log.info("重置密码: phone={}", request.getPhone());

        // 验证短信验证码
        verifySmsCode(request.getPhone(), request.getVerifyCode());

        // 查找用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, request.getPhone()));
        Assert.notNull(user, "手机号未注册");

        // 更新密码
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setPassword(BCrypt.hashpw(request.getNewPassword()));
        userMapper.updateById(updateUser);

        // 强制下线
        StpUtil.logout(user.getId());

        log.info("密码重置成功: userId={}", user.getId());
    }

    @Override
    public void sendSmsCode(String phone) {
        log.info("发送短信验证码: phone={}", phone);

        if (!smsEnabled) {
            log.warn("短信服务未启用，使用固定验证码: 123456");
            cacheSmsCode(phone, "123456");
            return;
        }

        // 生成验证码
        String code = RandomUtil.randomNumbers(6);

        // TODO: 调用短信服务发送验证码
        // smsService.sendVerifyCode(phone, code);

        // 缓存验证码
        cacheSmsCode(phone, code);

        log.info("短信验证码发送成功: phone={}", phone);
    }

    @Override
    public boolean verifySmsCode(String phone, String code) {
        String cachedCode = getCachedSmsCode(phone);
        Assert.notEmpty(cachedCode, "验证码已过期");
        Assert.isTrue(cachedCode.equals(code), "验证码错误");

        clearSmsCode(phone);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserLoginVO thirdPartyLogin(ThirdPartyLoginRequest request) {
        log.info("第三方登录: type={}, code={}", request.getLoginType(), request.getAuthCode());

        // TODO: 调用第三方登录服务，获取用户信息
        String openId = "mock_openid_" + request.getAuthCode();
        String nickname = "用户" + RandomUtil.randomNumbers(6);

        // 查找或创建用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, openId));

        if (user == null) {
            // 创建新用户
            user = new User();
            user.setUsername(openId);
            user.setPassword(BCrypt.hashpw(RandomUtil.randomString(16)));
            user.setNickname(nickname);
            user.setStatus(UserStatusEnum.NORMAL.getCode());
            user.setRegisterSource(request.getClientType());
            userMapper.insert(user);

            // 创建会员信息
            createMemberInfo(user.getId());
        }

        // Sa-Token 登录
        StpUtil.login(user.getId());

        // 构建响应
        UserLoginVO loginVO = userConverter.toLoginVO(user);
        loginVO.setAccessToken(StpUtil.getTokenValue());
        loginVO.setExpiresIn(StpUtil.getTokenTimeout());

        return loginVO;
    }



    // ========== 私有方法 ==========

    private boolean existsByUsername(String username) {
        return userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)) > 0;
    }

    private boolean existsByPhone(String phone) {
        return userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, phone)) > 0;
    }

    private boolean existsByEmail(String email) {
        return userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, email)) > 0;
    }

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
        member.setMemberNo("M" + System.currentTimeMillis() + RandomUtil.randomNumbers(4));
        member.setLevelId(1);
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

    private void cacheSmsCode(String phone, String code) {
        RBucket<String> bucket = redissonClient.getBucket(CacheConstants.CAPTCHA_CACHE_PREFIX + phone);
        bucket.set(code, Duration.ofMinutes(5));
    }

    private String getCachedSmsCode(String phone) {
        RBucket<String> bucket = redissonClient.getBucket(CacheConstants.CAPTCHA_CACHE_PREFIX + phone);
        return bucket.get();
    }

    private void clearSmsCode(String phone) {
        redissonClient.getBucket(CacheConstants.CAPTCHA_CACHE_PREFIX + phone).delete();
    }
}