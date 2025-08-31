package com.mall.api.user.feign.fallback;

import com.mall.api.user.dto.request.*;
import com.mall.api.user.dto.response.*;
import com.mall.api.user.feign.UserFeignClient;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * User Feign client fallback factory
 */
@Slf4j
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeignClient> {
    
    @Override
    public UserFeignClient create(Throwable cause) {
        log.error("User service call failed", cause);
        
        return new UserFeignClient() {
            @Override
            public Result<UserVO> register(UserRegisterRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<UserLoginVO> login(UserLoginRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<Void> logout(String token) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<UserVO> getUserById(Long userId) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<UserVO> getUserByUsername(String username) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<UserVO> updateUser(UserUpdateRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<PageResult<UserVO>> queryUsers(UserQueryRequest request) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<Boolean> checkUsernameExists(String username) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<Boolean> checkPhoneExists(String phone) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<Boolean> checkEmailExists(String email) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<UserVO> validateToken(String token) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
            
            @Override
            public Result<String> refreshToken(String refreshToken) {
                return Result.failed(ResultCode.SERVICE_UNAVAILABLE, "User service unavailable");
            }
        };
    }
}