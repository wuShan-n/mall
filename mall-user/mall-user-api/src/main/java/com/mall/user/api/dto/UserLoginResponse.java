package com.mall.user.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录响应
 *
 * @author mall
 */
@Data
@Schema(description = "用户登录响应")
public class UserLoginResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "访问令牌", example = "satoken_xxxxx")
    private String token;

    @Schema(description = "令牌类型", example = "Bearer")
    private String tokenType = "Bearer";

    @Schema(description = "过期时间(秒)", example = "7200")
    private Long expiresIn;

    @Schema(description = "用户信息")
    private UserInfoResponse userInfo;
}
