package com.mall.user.interfaces.assembler;

import com.mall.user.api.dto.UserInfoResponse;
import com.mall.user.domain.model.User;
import org.springframework.stereotype.Component;

/**
 * 用户DTO转换器
 *
 * @author mall
 */
@Component
public class UserAssembler {

    /**
     * 领域对象转DTO
     */
    public UserInfoResponse toUserInfoResponse(User user) {
        if (user == null) {
            return null;
        }

        UserInfoResponse response = new UserInfoResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setNickname(user.getNickname());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setAvatar(user.getAvatar());
        response.setGender(user.getGender());
        response.setBirthday(user.getBirthday());
        response.setStatus(user.getStatus());
        response.setCreateTime(user.getCreateTime());
        response.setLastLoginTime(user.getLastLoginTime());

        return response;
    }
}
