package com.mall.user.service;

import com.mall.api.user.dto.request.UserQueryRequest;
import com.mall.api.user.dto.request.UserUpdateRequest;
import com.mall.api.user.dto.response.UserVO;
import com.mall.common.result.PageResult;

/**
 * User Management Service Interface
 * 
 * @author mall
 * @date 2024-01-01
 */
public interface UserManagementService {

    /**
     * 根据ID获取用户
     */
    UserVO getUserById(Long userId);



    /**
     * 更新用户信息
     */
    UserVO updateUser(UserUpdateRequest request);

    /**
     * 分页查询用户
     */
    PageResult<UserVO> queryUsers(UserQueryRequest request);

    /**
     * 检查用户名是否存在
     */
    boolean checkUsernameExists(String username);

    /**
     * 检查手机号是否存在
     */
    boolean checkPhoneExists(String phone);

    /**
     * 检查邮箱是否存在
     */
    boolean checkEmailExists(String email);

    /**
     * 启用用户
     */
    void enableUser(Long userId);

    /**
     * 禁用用户
     */
    void disableUser(Long userId);

    /**
     * 删除用户
     */
    void deleteUser(Long userId);
}