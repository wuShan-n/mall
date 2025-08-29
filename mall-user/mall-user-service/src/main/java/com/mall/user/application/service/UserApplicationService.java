package com.mall.user.application.service;

import cn.dev33.satoken.stp.StpUtil;
import com.mall.common.core.exception.BusinessException;
import com.mall.common.core.result.ResultCode;
import com.mall.user.api.dto.*;
import com.mall.user.domain.model.User;
import com.mall.user.domain.repository.UserRepository;
import com.mall.user.interfaces.assembler.UserAssembler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * 用户应用服务
 *
 * @author mall
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;
    private final UserAssembler userAssembler;

    /**
     * 用户注册
     */
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterRequest request) {
        // 校验密码一致性
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BusinessException(ResultCode.PARAM_ERROR, "两次输入的密码不一致");
        }

        // 检查用户名是否已存在
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException(ResultCode.USER_ALREADY_EXISTS, "用户名已存在");
        }

        // 检查邮箱是否已存在
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ResultCode.USER_ALREADY_EXISTS, "邮箱已存在");
        }

        // 检查手机号是否已存在（如果提供了手机号）
        if (request.getPhone() != null && userRepository.existsByPhone(request.getPhone())) {
            throw new BusinessException(ResultCode.USER_ALREADY_EXISTS, "手机号已存在");
        }

        // 密码加密
        String encryptedPassword = encryptPassword(request.getPassword());

        // 创建用户
        User user = User.create(
                request.getUsername(),
                encryptedPassword,
                request.getEmail(),
                request.getPhone(),
                request.getNickname()
        );

        // 保存用户
        userRepository.save(user);

        log.info("用户注册成功：{}", request.getUsername());
    }

    /**
     * 用户登录
     */
    public UserLoginResponse login(UserLoginRequest request) {
        // 查找用户
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BusinessException(ResultCode.USER_LOGIN_ERROR));

        // 检查用户状态
        if (!user.isEnabled()) {
            throw new BusinessException(ResultCode.USER_DISABLED);
        }

        // 验证密码
        String encryptedPassword = encryptPassword(request.getPassword());
        if (!encryptedPassword.equals(user.getPassword())) {
            throw new BusinessException(ResultCode.USER_LOGIN_ERROR);
        }

        // 执行登录
        long timeout = request.getRememberMe() ? 7 * 24 * 60 * 60 : 2 * 60 * 60; // 记住我7天，否则2小时
        StpUtil.login(user.getId(), timeout);

        // 更新最后登录信息
        user.updateLastLogin(getClientIp());
        userRepository.save(user);

        // 构建响应
        UserLoginResponse response = new UserLoginResponse();
        response.setToken(StpUtil.getTokenValue());
        response.setExpiresIn(timeout);
        response.setUserInfo(userAssembler.toUserInfoResponse(user));

        log.info("用户登录成功：{}", request.getUsername());
        return response;
    }

    /**
     * 用户登出
     */
    public void logout() {
        StpUtil.logout();
        log.info("用户登出成功：{}", StpUtil.getLoginIdDefaultNull());
    }

    /**
     * 获取当前用户信息
     */
    public UserInfoResponse getCurrentUserInfo() {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));

        return userAssembler.toUserInfoResponse(user);
    }

    /**
     * 根据ID获取用户信息
     */
    public UserInfoResponse getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ResultCode.USER_NOT_FOUND));

        return userAssembler.toUserInfoResponse(user);
    }

    /**
     * 密码加密
     */
    private String encryptPassword(String password) {
        return DigestUtils.md5DigestAsHex((password + "mall_salt").getBytes());
    }

    /**
     * 获取客户端IP（简化实现）
     */
    private String getClientIp() {
        // 这里应该从HttpServletRequest中获取真实IP
        // 简化实现，返回固定值
        return "127.0.0.1";
    }
}
