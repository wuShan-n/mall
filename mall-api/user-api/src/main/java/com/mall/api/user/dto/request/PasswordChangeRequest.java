package com.mall.api.user.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Password change request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Password change request")
public class PasswordChangeRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Old password cannot be empty")
    @Schema(description = "Old password", example = "123456")
    private String oldPassword;
    
    @NotBlank(message = "New password cannot be empty")
    @Size(min = 6, max = 20, message = "Password length must be between 6 and 20")
    @Schema(description = "New password", example = "newpass123")
    private String newPassword;
    
    @NotBlank(message = "Confirm password cannot be empty")
    @Schema(description = "Confirm new password", example = "newpass123")
    private String confirmPassword;
}