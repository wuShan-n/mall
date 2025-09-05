package com.mall.user.provider;

import com.mall.api.user.dubbo.UserDubboService;
import com.mall.api.user.dto.request.*;
import com.mall.api.user.dto.response.UserLoginVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.user.service.UserAuthService;
import com.mall.user.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * User Dubbo Service Provider Implementation
 * 
 * @author mall
 * @date 2024-01-01
 */
@Slf4j
@DubboService(version = "1.0.0", timeout = 3000)
@RequiredArgsConstructor
public class UserDubboProviderImpl implements UserDubboService {

    private final UserAuthService userAuthService;
    private final UserManagementService userManagementService;

    @Override
    public Result<UserVO> register(UserRegisterRequest request) {
        try {
            log.info("Dubbo call - register user: {}", request.getUsername());
            UserVO userVO = userAuthService.register(request);
            return Result.success(userVO);
        } catch (Exception e) {
            log.error("Register user failed", e);
            return Result.failed(e.getMessage());
        }
    }

    @Override
    public Result<UserLoginVO> login(UserLoginRequest request) {
        try {
            log.info("Dubbo call - user login: {}", request.getAccount());
            UserLoginVO loginVO = userAuthService.login(request);
            return Result.success(loginVO);
        } catch (Exception e) {
            log.error("User login failed", e);
            return Result.failed(e.getMessage());
        }
    }

    @Override
    public Result<Void> logout(String token) {
        try {
            log.info("Dubbo call - user logout");
            userAuthService.logout(token);
            return Result.success();
        } catch (Exception e) {
            log.error("User logout failed", e);
            return Result.failed(e.getMessage());
        }
    }

    @Override
    public Result<UserVO> getUserById(Long userId) {
        try {
            log.debug("Dubbo call - get user by id: {}", userId);
            UserVO userVO = userManagementService.getUserById(userId);
            return Result.success(userVO);
        } catch (Exception e) {
            log.error("Get user by id failed", e);
            return Result.failed(e.getMessage());
        }
    }



    @Override
    public Result<UserVO> updateUser(UserUpdateRequest request) {
        try {
            log.info("Dubbo call - update user: {}", request.getId());
            UserVO userVO = userManagementService.updateUser(request);
            return Result.success(userVO);
        } catch (Exception e) {
            log.error("Update user failed", e);
            return Result.failed(e.getMessage());
        }
    }

    @Override
    public Result<PageResult<UserVO>> queryUsers(UserQueryRequest request) {
        try {
            log.debug("Dubbo call - query users");
            PageResult<UserVO> pageResult = userManagementService.queryUsers(request);
            return Result.success(pageResult);
        } catch (Exception e) {
            log.error("Query users failed", e);
            return Result.failed(e.getMessage());
        }
    }

    @Override
    public Result<Boolean> checkUsernameExists(String username) {
        try {
            boolean exists = userManagementService.checkUsernameExists(username);
            return Result.success(exists);
        } catch (Exception e) {
            log.error("Check username exists failed", e);
            return Result.failed(e.getMessage());
        }
    }

    @Override
    public Result<Boolean> checkPhoneExists(String phone) {
        try {
            boolean exists = userManagementService.checkPhoneExists(phone);
            return Result.success(exists);
        } catch (Exception e) {
            log.error("Check phone exists failed", e);
            return Result.failed(e.getMessage());
        }
    }

    @Override
    public Result<Boolean> checkEmailExists(String email) {
        try {
            boolean exists = userManagementService.checkEmailExists(email);
            return Result.success(exists);
        } catch (Exception e) {
            log.error("Check email exists failed", e);
            return Result.failed(e.getMessage());
        }
    }


}