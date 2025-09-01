package com.mall.user.service;

import com.mall.api.user.dto.request.*;
import com.mall.api.user.dto.response.UserLoginVO;
import com.mall.api.user.dto.response.UserVO;

/**
 * User Authentication Service Interface
 * 
 * @author mall
 * @date 2024-01-01
 */
public interface UserAuthService {

    /**
     * 用户注册
     * 
     * @param request 注册请求
     * @return 用户信息
     */
    UserVO register(UserRegisterRequest request);

    /**
     * 用户登录
     * 
     * @param request 登录请求
     * @return 登录响应（包含token）
     */
    UserLoginVO login(UserLoginRequest request);

    /**
     * 用户登出
     * 
     * @param token 用户token
     */
    void logout(String token);

    /**
     * 刷新Token
     * 
     * @param refreshToken 刷新token
     * @return 新的access token
     */
    String refreshToken(String refreshToken);

    /**
     * 获取当前用户
     * 
     * @param token 用户token
     * @return 用户信息
     */
    UserVO getCurrentUser(String token);

    /**
     * 修改密码
     * 
     * @param request 修改密码请求
     */
    void changePassword(PasswordChangeRequest request);

    /**
     * 重置密码
     * 
     * @param request 重置密码请求
     */
    void resetPassword(PasswordResetRequest request);

    /**
     * 发送短信验证码
     * 
     * @param phone 手机号
     */
    void sendSmsCode(String phone);

    /**
     * 验证短信验证码
     * 
     * @param phone 手机号
     * @param code 验证码
     * @return 是否验证成功
     */
    boolean verifySmsCode(String phone, String code);

    /**
     * 第三方登录
     * 
     * @param request 第三方登录请求
     * @return 登录响应
     */
    UserLoginVO thirdPartyLogin(ThirdPartyLoginRequest request);

    /**
     * 验证Token
     * 
     * @param token token
     * @return 用户信息
     */
    UserVO validateToken(String token);
}