package com.mall.user.interfaces.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.mall.common.core.result.Result;
import com.mall.user.api.dto.*;
import com.mall.user.application.service.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author mall
 */
@Tag(name = "用户管理", description = "用户注册、登录、信息管理等接口")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    @Operation(summary = "用户注册", description = "创建新用户账号")
    @PostMapping("/register")
    public Result<Void> register(@Valid @RequestBody UserRegisterRequest request) {
        userApplicationService.register(request);
        return Result.success();
    }

    @Operation(summary = "用户登录", description = "用户身份认证")
    @PostMapping("/login")
    public Result<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        UserLoginResponse response = userApplicationService.login(request);
        return Result.success(response);
    }

    @Operation(summary = "用户登出", description = "退出登录状态")
    @PostMapping("/logout")
    @SaCheckLogin
    public Result<Void> logout() {
        userApplicationService.logout();
        return Result.success();
    }

    @Operation(summary = "获取当前用户信息", description = "获取登录用户的详细信息")
    @GetMapping("/me")
    @SaCheckLogin
    public Result<UserInfoResponse> getCurrentUserInfo() {
        UserInfoResponse response = userApplicationService.getCurrentUserInfo();
        return Result.success(response);
    }

    @Operation(summary = "根据ID获取用户信息", description = "获取指定用户的详细信息")
    @GetMapping("/{userId}")
    @SaCheckLogin
    public Result<UserInfoResponse> getUserInfo(@PathVariable("userId") Long userId) {
        UserInfoResponse response = userApplicationService.getUserInfo(userId);
        return Result.success(response);
    }
}
