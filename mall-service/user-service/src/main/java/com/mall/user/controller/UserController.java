package com.mall.user.controller;

import com.mall.api.user.dto.request.*;
import com.mall.api.user.dto.response.UserLoginVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.common.annotation.Log;
import com.mall.common.annotation.NoRepeatSubmit;
import com.mall.common.annotation.RateLimit;
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
    @NoRepeatSubmit(lockTime = 3000)
    @RateLimit(key = "user:register", time = 60, count = 5)
    @Log(module = "用户", type = Log.OperationType.CREATE, desc = "用户注册")
    public Result<UserVO> register(@Valid @RequestBody UserRegisterRequest request) {
        log.info("User register request: {}", request.getUsername());
        return Result.success(userAuthService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录获取token")
    @RateLimit(key = "user:login", time = 60, count = 10)
    @Log(module = "用户", type = Log.OperationType.LOGIN, desc = "用户登录")
    public Result<UserLoginVO> login(@Valid @RequestBody UserLoginRequest request) {
        log.info("User login request: {}", request.getAccount());
        return Result.success(userAuthService.login(request));
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出", description = "退出登录")
    @Log(module = "用户", type = Log.OperationType.LOGOUT, desc = "用户登出")
    public Result<Void> logout(@RequestHeader("Authorization") String token) {
        log.info("User logout with token: {}", token);
        userAuthService.logout(token);
        return Result.success();
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "刷新Token", description = "使用refreshToken刷新accessToken")
    public Result<String> refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return Result.success(userAuthService.refreshToken(refreshToken));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "获取用户信息", description = "根据用户ID获取用户信息")
    public Result<UserVO> getUserById(@PathVariable @Parameter(description = "用户ID") Long userId) {
        log.debug("Get user by id: {}", userId);
        return Result.success(userManagementService.getUserById(userId));
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "根据用户名获取用户", description = "根据用户名获取用户信息")
    public Result<UserVO> getUserByUsername(@PathVariable String username) {
        log.debug("Get user by username: {}", username);
        return Result.success(userManagementService.getUserByUsername(username));
    }

    @GetMapping("/current")
    @Operation(summary = "获取当前用户信息", description = "获取当前登录用户的信息")
    public Result<UserVO> getCurrentUser(@RequestHeader("Authorization") String token) {
        return Result.success(userAuthService.getCurrentUser(token));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户信息", description = "更新用户基本信息")
    @NoRepeatSubmit
    @Log(module = "用户", type = Log.OperationType.UPDATE, desc = "更新用户信息")
    public Result<UserVO> updateUser(@Valid @RequestBody UserUpdateRequest request) {
        log.info("Update user: {}", request.getId());
        return Result.success(userManagementService.updateUser(request));
    }

    @PostMapping("/change-password")
    @Operation(summary = "修改密码", description = "用户修改密码")
    @NoRepeatSubmit
    @Log(module = "用户", type = Log.OperationType.UPDATE, desc = "修改密码")
    public Result<Void> changePassword(@Valid @RequestBody PasswordChangeRequest request) {
        userAuthService.changePassword(request);
        return Result.success();
    }

    @PostMapping("/reset-password")
    @Operation(summary = "重置密码", description = "通过手机验证码重置密码")
    @NoRepeatSubmit
    @RateLimit(key = "user:reset-password", time = 60, count = 3)
    @Log(module = "用户", type = Log.OperationType.UPDATE, desc = "重置密码")
    public Result<Void> resetPassword(@Valid @RequestBody PasswordResetRequest request) {
        userAuthService.resetPassword(request);
        return Result.success();
    }

    @PostMapping("/query")
    @Operation(summary = "查询用户列表", description = "分页查询用户列表")
    public Result<PageResult<UserVO>> queryUsers(@Valid @RequestBody UserQueryRequest request) {
        log.debug("Query users with request: {}", request);
        return Result.success(userManagementService.queryUsers(request));
    }

    @GetMapping("/check/username/{username}")
    @Operation(summary = "检查用户名是否存在", description = "检查用户名是否已被注册")
    public Result<Boolean> checkUsernameExists(@PathVariable String username) {
        return Result.success(userManagementService.checkUsernameExists(username));
    }

    @GetMapping("/check/phone/{phone}")
    @Operation(summary = "检查手机号是否存在", description = "检查手机号是否已被注册")
    public Result<Boolean> checkPhoneExists(@PathVariable String phone) {
        return Result.success(userManagementService.checkPhoneExists(phone));
    }

    @GetMapping("/check/email/{email}")
    @Operation(summary = "检查邮箱是否存在", description = "检查邮箱是否已被注册")
    public Result<Boolean> checkEmailExists(@PathVariable String email) {
        return Result.success(userManagementService.checkEmailExists(email));
    }

    @PostMapping("/send-sms-code")
    @Operation(summary = "发送短信验证码", description = "发送短信验证码到手机")
    @RateLimit(key = "user:send-sms", time = 60, count = 1, limitType = RateLimit.LimitType.IP)
    public Result<Void> sendSmsCode(@RequestParam("phone") String phone) {
        userAuthService.sendSmsCode(phone);
        return Result.success();
    }

    @PostMapping("/third-party/login")
    @Operation(summary = "第三方登录", description = "支持微信、支付宝等第三方登录")
    @NoRepeatSubmit
    public Result<UserLoginVO> thirdPartyLogin(@Valid @RequestBody ThirdPartyLoginRequest request) {
        log.info("Third party login: type={}", request.getLoginType());
        return Result.success(userAuthService.thirdPartyLogin(request));
    }

    @PostMapping("/enable/{userId}")
    @Operation(summary = "启用用户", description = "启用指定用户账号")
    @Log(module = "用户", type = Log.OperationType.UPDATE, desc = "启用用户")
    public Result<Void> enableUser(@PathVariable Long userId) {
        userManagementService.enableUser(userId);
        return Result.success();
    }

    @PostMapping("/disable/{userId}")
    @Operation(summary = "禁用用户", description = "禁用指定用户账号")
    @Log(module = "用户", type = Log.OperationType.UPDATE, desc = "禁用用户")
    public Result<Void> disableUser(@PathVariable Long userId) {
        userManagementService.disableUser(userId);
        return Result.success();
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "删除用户", description = "逻辑删除用户")
    @Log(module = "用户", type = Log.OperationType.DELETE, desc = "删除用户")
    public Result<Void> deleteUser(@PathVariable Long userId) {
        userManagementService.deleteUser(userId);
        return Result.success();
    }
}