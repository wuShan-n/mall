package com.mall.user.infrastructure.converter;

import com.mall.user.domain.model.User;
import com.mall.user.infrastructure.po.UserPO;
import com.mall.user.infrastructure.po.UserProfilePO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 用户对象转换器
 *
 * @author mall
 */
@Component
public class UserConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * PO转领域对象
     */
    public User toDomain(UserPO userPO, UserProfilePO profilePO) {
        if (userPO == null) {
            return null;
        }

        User user = new User();
        user.setId(userPO.getId());
        user.setUsername(userPO.getUsername());
        user.setPassword(userPO.getPassword());
        user.setEmail(userPO.getEmail());
        user.setPhone(userPO.getMobile());
        user.setStatus(userPO.getStatus());
        user.setLastLoginTime(userPO.getLastLoginTime());
        user.setLastLoginIp(userPO.getLastLoginIp());
        user.setCreateTime(userPO.getCreatedAt());
        user.setUpdateTime(userPO.getUpdatedAt());

        // 设置用户详情信息
        if (profilePO != null) {
            user.setNickname(profilePO.getNickname());
            user.setAvatar(profilePO.getAvatarUrl());
            user.setGender(profilePO.getGender());
            if (profilePO.getBirthday() != null) {
                user.setBirthday(profilePO.getBirthday().format(DATE_FORMATTER));
            }
        }

        return user;
    }

    /**
     * 领域对象转PO
     */
    public UserPO toPO(User user) {
        if (user == null) {
            return null;
        }

        UserPO userPO = new UserPO();
        userPO.setId(user.getId());
        userPO.setUsername(user.getUsername());
        userPO.setPassword(user.getPassword());
        userPO.setEmail(user.getEmail());
        userPO.setMobile(user.getPhone());
        userPO.setStatus(user.getStatus());
        userPO.setUserType(1); // 默认普通用户
        userPO.setRegisterSource("WEB"); // 默认网页注册
        userPO.setLastLoginTime(user.getLastLoginTime());
        userPO.setLastLoginIp(user.getLastLoginIp());
        userPO.setLoginCount(0); // 默认登录次数
        userPO.setCreatedAt(user.getCreateTime());
        userPO.setUpdatedAt(user.getUpdateTime());

        return userPO;
    }

    /**
     * 领域对象转用户详情PO
     */
    public UserProfilePO toProfilePO(User user) {
        if (user == null) {
            return null;
        }

        UserProfilePO profilePO = new UserProfilePO();
        profilePO.setUserId(user.getId());
        profilePO.setNickname(user.getNickname());
        profilePO.setAvatarUrl(user.getAvatar());
        profilePO.setGender(user.getGender());

        if (user.getBirthday() != null && !user.getBirthday().isEmpty()) {
            try {
                profilePO.setBirthday(LocalDate.parse(user.getBirthday(), DATE_FORMATTER));
            } catch (Exception e) {
                // 日期解析失败，忽略
            }
        }

        profilePO.setCountry("China"); // 默认中国

        return profilePO;
    }
}
