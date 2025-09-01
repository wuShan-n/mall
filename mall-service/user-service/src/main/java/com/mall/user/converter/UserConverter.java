package com.mall.user.converter;

import com.mall.api.user.dto.request.UserRegisterRequest;
import com.mall.api.user.dto.request.UserUpdateRequest;
import com.mall.api.user.dto.response.UserLoginVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * User Object Converter
 * 
 * @author mall
 * @date 2024-01-01
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * 注册请求转实体
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true) // 密码需要加密处理
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lastLoginTime", ignore = true)
    @Mapping(target = "lastLoginIp", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    User toEntity(UserRegisterRequest request);

    /**
     * 更新请求转实体
     */
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "registerSource", ignore = true)
    @Mapping(target = "lastLoginTime", ignore = true)
    @Mapping(target = "lastLoginIp", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntity(UserUpdateRequest request, @MappingTarget User user);

    /**
     * 实体转VO
     */
    UserVO toVO(User user);

    /**
     * 实体列表转VO列表
     */
    List<UserVO> toVOList(List<User> users);

    /**
     * 实体转登录VO
     */
    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    @Mapping(target = "expiresIn", ignore = true)
    @Mapping(target = "tokenType", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    UserLoginVO toLoginVO(User user);
}