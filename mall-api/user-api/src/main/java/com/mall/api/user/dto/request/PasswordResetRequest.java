package com.mall.api.user.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Password reset request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Password reset request")
public class PasswordResetRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "Invalid phone number format")
    @Schema(description = "Phone number", example = "13800138000")
    private String phone;
    
    @NotBlank(message = "Verification code cannot be empty")
    @Schema(description = "SMS verification code", example = "123456")
    private String verifyCode;
    
    @NotBlank(message = "New password cannot be empty")
    @Size(min = 6, max = 20, message = "Password length must be between 6 and 20")
    @Schema(description = "New password", example = "newpass123")
    private String newPassword;
}