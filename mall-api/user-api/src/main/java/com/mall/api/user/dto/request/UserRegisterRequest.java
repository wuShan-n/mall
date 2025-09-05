package com.mall.api.user.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User register request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "User register request")
public class UserRegisterRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 4, max = 20, message = "Username length must be between 4 and 20")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers and underscore")
    @Schema(description = "Username", example = "user123")
    private String username;
    
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 20, message = "Password length must be between 6 and 20")
    @Schema(description = "Password", example = "123456")
    private String password;
    
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "Invalid phone number format")
    @Schema(description = "Phone number", example = "13800138000")
    private String phone;
    
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    @Schema(description = "Email", example = "user@example.com")
    private String email;
    
    @Schema(description = "Nickname", example = "John Doe")
    private String nickname;
    
    @Schema(description = "Verification code", example = "123456")
    private String verifyCode;
    
    @Schema(description = "Register source: 1-PC, 2-APP, 3-Mini Program", example = "1")
    private Integer registerSource;
}