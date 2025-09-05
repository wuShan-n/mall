package com.mall.api.user.dubbo;

import com.mall.api.user.dto.request.UserLoginRequest;
import com.mall.api.user.dto.request.UserQueryRequest;
import com.mall.api.user.dto.request.UserRegisterRequest;
import com.mall.api.user.dto.request.UserUpdateRequest;
import com.mall.api.user.dto.response.UserLoginVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;

/**
 * User Dubbo RPC service interface
 */
public interface UserDubboService {
    
    /**
     * Register user
     */
    Result<UserVO> register(UserRegisterRequest request);
    
    /**
     * User login
     */
    Result<UserLoginVO> login(UserLoginRequest request);
    
    /**
     * User logout
     */
    Result<Void> logout(String token);
    
    /**
     * Get user by ID
     */
    Result<UserVO> getUserById(Long userId);

    /**
     * Update user information
     */
    Result<UserVO> updateUser(UserUpdateRequest request);
    
    /**
     * Query users with pagination
     */
    Result<PageResult<UserVO>> queryUsers(UserQueryRequest request);

    /**
     * Check if username exists
     */
    Result<Boolean> checkUsernameExists(String username);

    /**
     * Check if phone exists
     */
    Result<Boolean> checkPhoneExists(String phone);

    /**
     * Check if email exists
     */
    Result<Boolean> checkEmailExists(String email);

}
