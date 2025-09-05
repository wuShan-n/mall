package com.mall.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.user.dto.request.UserQueryRequest;
import com.mall.api.user.dto.request.UserUpdateRequest;
import com.mall.api.user.dto.response.UserVO;
import com.mall.api.user.enums.UserStatusEnum;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.PageResult;
import com.mall.common.result.ResultCode;
import com.mall.common.utils.Assert;
import com.mall.user.converter.UserConverter;
import com.mall.user.entity.User;
import com.mall.user.mapper.UserMapper;
import com.mall.user.service.UserManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User Management Service Implementation
 * 
 * @author mall
 * @date 2024-01-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserManagementServiceImpl implements UserManagementService {

    private final UserMapper userMapper;
    private final UserConverter userConverter;

    @Override
    public UserVO getUserById(Long userId) {
        log.debug("Get user by id: {}", userId);

        User user = userMapper.selectById(userId);
        Assert.notNull(user, ResultCode.USER_NOT_FOUND);

        return userConverter.toVO(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserVO updateUser(UserUpdateRequest request) {
        log.info("Update user: {}", request.getId());
        
        User user = userMapper.selectById(request.getId());
        Assert.notNull(user, ResultCode.USER_NOT_FOUND);
        
        // 检查手机号是否被其他用户使用
        if (StrUtil.isNotBlank(request.getPhone()) && !request.getPhone().equals(user.getPhone())) {
            Long count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getPhone, request.getPhone())
                    .ne(User::getId, request.getId()));
            Assert.isTrue(count == 0, "手机号已被其他用户使用");
        }
        
        // 检查邮箱是否被其他用户使用
        if (StrUtil.isNotBlank(request.getEmail()) && !request.getEmail().equals(user.getEmail())) {
            Long count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                    .eq(User::getEmail, request.getEmail())
                    .ne(User::getId, request.getId()));
            Assert.isTrue(count == 0, "邮箱已被其他用户使用");
        }
        
        // 更新用户信息
        userConverter.updateEntity(request, user);
        userMapper.updateById(user);
        
        log.info("User updated successfully: userId={}", user.getId());
        
        return userConverter.toVO(user);
    }

    @Override
    public PageResult<UserVO> queryUsers(UserQueryRequest request) {
        log.debug("Query users with request: {}", request);
        
        Page<User> page = new Page<>(request.getCurrent(), request.getSize());
        
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        
        // 构建查询条件
        if (StrUtil.isNotBlank(request.getUsername())) {
            wrapper.like(User::getUsername, request.getUsername());
        }
        if (StrUtil.isNotBlank(request.getNickname())) {
            wrapper.like(User::getNickname, request.getNickname());
        }
        if (StrUtil.isNotBlank(request.getPhone())) {
            wrapper.like(User::getPhone, request.getPhone());
        }
        if (StrUtil.isNotBlank(request.getEmail())) {
            wrapper.like(User::getEmail, request.getEmail());
        }
        if (request.getStatus() != null) {
            wrapper.eq(User::getStatus, request.getStatus());
        }
        if (request.getGender() != null) {
            wrapper.eq(User::getGender, request.getGender());
        }
        if (request.getRegisterStartTime() != null) {
            wrapper.ge(User::getCreateTime, request.getRegisterStartTime());
        }
        if (request.getRegisterEndTime() != null) {
            wrapper.le(User::getCreateTime, request.getRegisterEndTime());
        }
        
        // 排序
        if (StrUtil.isNotBlank(request.getOrderBy())) {
            boolean isAsc = "asc".equalsIgnoreCase(request.getOrderDirection());
            wrapper.orderBy(true, isAsc, User::getCreateTime);
        } else {
            wrapper.orderByDesc(User::getCreateTime);
        }
        
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        
        List<UserVO> voList = userConverter.toVOList(userPage.getRecords());
        
        return PageResult.of(userPage.getCurrent(), userPage.getSize(), 
                userPage.getTotal(), voList);
    }

    @Override
    public boolean checkUsernameExists(String username) {
        Long count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        return count > 0;
    }

    @Override
    public boolean checkPhoneExists(String phone) {
        Long count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getPhone, phone));
        return count > 0;
    }

    @Override
    public boolean checkEmailExists(String email) {
        Long count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, email));
        return count > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableUser(Long userId) {
        log.info("Enable user: {}", userId);
        
        User user = userMapper.selectById(userId);
        Assert.notNull(user, ResultCode.USER_NOT_FOUND);
        
        if (UserStatusEnum.NORMAL.getCode().equals(user.getStatus())) {
            throw new BusinessException("用户已经是启用状态");
        }
        
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setStatus(UserStatusEnum.NORMAL.getCode());
        userMapper.updateById(updateUser);
        
        log.info("User enabled successfully: userId={}", userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableUser(Long userId) {
        log.info("Disable user: {}", userId);
        
        User user = userMapper.selectById(userId);
        Assert.notNull(user, ResultCode.USER_NOT_FOUND);
        
        if (UserStatusEnum.DISABLED.getCode().equals(user.getStatus())) {
            throw new BusinessException("用户已经是禁用状态");
        }
        
        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setStatus(UserStatusEnum.DISABLED.getCode());
        userMapper.updateById(updateUser);
        
        log.info("User disabled successfully: userId={}", userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long userId) {
        log.info("Delete user: {}", userId);
        
        User user = userMapper.selectById(userId);
        Assert.notNull(user, ResultCode.USER_NOT_FOUND);
        
        // 逻辑删除
        userMapper.deleteById(userId);
        
        log.info("User deleted successfully: userId={}", userId);
    }
}