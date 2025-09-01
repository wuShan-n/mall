package com.mall.api.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User login response DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "User login response")
public class UserLoginVO extends UserVO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String accessToken;
    
    @Schema(description = "Refresh token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String refreshToken;
    
    @Schema(description = "Token expire time (seconds)", example = "7200")
    private Long expiresIn;
    
    @Schema(description = "Token type", example = "Bearer")
    private String tokenType = "Bearer";
    
    @Schema(description = "User roles", example = "user,member")
    private String roles;
    
    @Schema(description = "User permissions", example = "user:read,user:write")
    private String permissions;
}