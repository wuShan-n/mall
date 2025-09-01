package com.mall.api.user.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User login request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "User login request")
public class UserLoginRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Account cannot be empty")
    @Schema(description = "Account (username/phone/email)", example = "user123")
    private String account;
    
    @NotBlank(message = "Password cannot be empty")
    @Schema(description = "Password", example = "123456")
    private String password;
    
    @Schema(description = "Login type: 1-Password, 2-SMS Code", example = "1")
    private Integer loginType = 1;
    
    @Schema(description = "Verification code", example = "123456")
    private String verifyCode;
    
    @Schema(description = "Remember me", example = "true")
    private Boolean rememberMe = false;
    
    @Schema(description = "Client IP", example = "192.168.1.1")
    private String clientIp;
    
    @Schema(description = "User agent", example = "Mozilla/5.0...")
    private String userAgent;
}
