package com.mall.user.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.user.domain.model.User;
import com.mall.user.domain.repository.UserRepository;
import com.mall.user.infrastructure.converter.UserConverter;
import com.mall.user.infrastructure.mapper.UserMapper;
import com.mall.user.infrastructure.mapper.UserProfileMapper;
import com.mall.user.infrastructure.po.UserPO;
import com.mall.user.infrastructure.po.UserProfilePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户仓储实现
 *
 * @author mall
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private final UserConverter userConverter;

    @Override
    public Optional<User> findById(Long id) {
        UserPO userPO = userMapper.selectById(id);
        if (userPO == null) {
            return Optional.empty();
        }

        // 查询用户详情
        UserProfilePO profilePO = userProfileMapper.selectOne(
                new LambdaQueryWrapper<UserProfilePO>()
                        .eq(UserProfilePO::getUserId, id)
        );

        User user = userConverter.toDomain(userPO, profilePO);
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        UserPO userPO = userMapper.selectOne(
                new LambdaQueryWrapper<UserPO>()
                        .eq(UserPO::getUsername, username)
        );
        if (userPO == null) {
            return Optional.empty();
        }

        // 查询用户详情
        UserProfilePO profilePO = userProfileMapper.selectOne(
                new LambdaQueryWrapper<UserProfilePO>()
                        .eq(UserProfilePO::getUserId, userPO.getId())
        );

        User user = userConverter.toDomain(userPO, profilePO);
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        UserPO userPO = userMapper.selectOne(
                new LambdaQueryWrapper<UserPO>()
                        .eq(UserPO::getEmail, email)
        );
        if (userPO == null) {
            return Optional.empty();
        }

        // 查询用户详情
        UserProfilePO profilePO = userProfileMapper.selectOne(
                new LambdaQueryWrapper<UserProfilePO>()
                        .eq(UserProfilePO::getUserId, userPO.getId())
        );

        User user = userConverter.toDomain(userPO, profilePO);
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        UserPO userPO = userMapper.selectOne(
                new LambdaQueryWrapper<UserPO>()
                        .eq(UserPO::getMobile, phone)
        );
        if (userPO == null) {
            return Optional.empty();
        }

        // 查询用户详情
        UserProfilePO profilePO = userProfileMapper.selectOne(
                new LambdaQueryWrapper<UserProfilePO>()
                        .eq(UserProfilePO::getUserId, userPO.getId())
        );

        User user = userConverter.toDomain(userPO, profilePO);
        return Optional.of(user);
    }

    @Override
    public User save(User user) {
        UserPO userPO = userConverter.toPO(user);

        if (user.getId() == null) {
            // 新增用户
            userMapper.insert(userPO);
            user.setId(userPO.getId());

            // 创建用户详情记录
            UserProfilePO profilePO = userConverter.toProfilePO(user);
            profilePO.setUserId(userPO.getId());
            userProfileMapper.insert(profilePO);
        } else {
            // 更新用户
            userMapper.updateById(userPO);

            // 更新用户详情
            UserProfilePO existingProfile = userProfileMapper.selectOne(
                    new LambdaQueryWrapper<UserProfilePO>()
                            .eq(UserProfilePO::getUserId, user.getId())
            );

            if (existingProfile != null) {
                UserProfilePO profilePO = userConverter.toProfilePO(user);
                profilePO.setId(existingProfile.getId());
                profilePO.setUserId(user.getId());
                userProfileMapper.updateById(profilePO);
            } else {
                UserProfilePO profilePO = userConverter.toProfilePO(user);
                profilePO.setUserId(user.getId());
                userProfileMapper.insert(profilePO);
            }
        }

        return user;
    }

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
        // 用户详情表有外键约束，会级联删除或者使用软删除
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMapper.selectCount(
                new LambdaQueryWrapper<UserPO>()
                        .eq(UserPO::getUsername, username)
        ) > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMapper.selectCount(
                new LambdaQueryWrapper<UserPO>()
                        .eq(UserPO::getEmail, email)
        ) > 0;
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userMapper.selectCount(
                new LambdaQueryWrapper<UserPO>()
                        .eq(UserPO::getMobile, phone)
        ) > 0;
    }
}
