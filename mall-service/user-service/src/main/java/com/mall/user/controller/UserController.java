package com.mall.user.controller;

import com.mall.api.user.dto.request.*;
import com.mall.api.user.dto.response.UserLoginVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.user.service.UserAuthService;
import com.mall.user.service.UserManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 * 
 * @author mall
 * @date 2024-01-01
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户相关接口")
@Validated
public class UserController {
    private final UserAuthService userAuthService;
    private final UserManagementService userManagementService;

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "新用户注册接口")
    public Result<UserVO> register(@Valid @RequestBody UserRegisterRequest request) {
        return Result.success(userAuthService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录获取token")
    public Result<UserLoginVO> login(@Valid @RequestBody UserLoginRequest request) {
        return Result.success(userAuthService.login(request));
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出", description = "退出登录")
    public Result<Void> logout(@RequestHeader("Authorization") String token) {
        userAuthService.logout(token);
        return Result.success();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "获取用户信息", description = "根据用户ID获取用户信息")
    public Result<UserVO> getUserById(@PathVariable("userId") @Parameter(description = "用户ID") Long userId) {
        return Result.success(userManagementService.getUserById(userId));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户信息", description = "更新用户基本信息")
    public Result<UserVO> updateUser(@Valid @RequestBody UserUpdateRequest request) {
        return Result.success(userManagementService.updateUser(request));
    }

    @PostMapping("/change-password")
    @Operation(summary = "修改密码", description = "用户修改密码")
    public Result<Void> changePassword(@Valid @RequestBody PasswordChangeRequest request) {
        userAuthService.changePassword(request);
        return Result.success();
    }

    @PostMapping("/reset-password")
    @Operation(summary = "重置密码", description = "通过手机验证码重置密码")
    public Result<Void> resetPassword(@Valid @RequestBody PasswordResetRequest request) {
        userAuthService.resetPassword(request);
        return Result.success();
    }

    @PostMapping("/query")
    @Operation(summary = "查询用户列表", description = "分页查询用户列表")
    public Result<PageResult<UserVO>> queryUsers(@Valid @RequestBody UserQueryRequest request) {
        return Result.success(userManagementService.queryUsers(request));
    }

    @GetMapping("/check/username/{username}")
    @Operation(summary = "检查用户名是否存在", description = "检查用户名是否已被注册")
    public Result<Boolean> checkUsernameExists(@PathVariable("username") String username) {
        return Result.success(userManagementService.checkUsernameExists(username));
    }

    @PostMapping("/send-sms-code")
    @Operation(summary = "发送短信验证码", description = "发送短信验证码到手机")
    public Result<Void> sendSmsCode(@RequestParam("phone") String phone) {
        userAuthService.sendSmsCode(phone);
        return Result.success();
    }

    @PostMapping("/third-party/login")
    @Operation(summary = "第三方登录", description = "支持微信、支付宝等第三方登录")
    public Result<UserLoginVO> thirdPartyLogin(@Valid @RequestBody ThirdPartyLoginRequest request) {
        return Result.success(userAuthService.thirdPartyLogin(request));
    }

    @PostMapping("/enable/{userId}")
    @Operation(summary = "启用用户", description = "启用指定用户账号")
    public Result<Void> enableUser(@PathVariable("userId") Long userId) {
        userManagementService.enableUser(userId);
        return Result.success();
    }

    @PostMapping("/disable/{userId}")
    @Operation(summary = "禁用用户", description = "禁用指定用户账号")
    public Result<Void> disableUser(@PathVariable("userId") Long userId) {
        userManagementService.disableUser(userId);
        return Result.success();
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "删除用户", description = "逻辑删除用户")
    public Result<Void> deleteUser(@PathVariable("userId") Long userId) {
        userManagementService.deleteUser(userId);
        return Result.success();
    }
}