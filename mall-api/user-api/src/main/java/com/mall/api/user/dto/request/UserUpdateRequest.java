package com.mall.api.user.dto.request;

import com.mall.common.base.BaseDTO;
import com.mall.common.validation.ValidationGroups;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

/**
 * User update request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "User update request")
public class UserUpdateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "User ID cannot be null", groups = ValidationGroups.Update.class)
    @Schema(description = "User ID", example = "1")
    private Long id;
    
    @Schema(description = "Nickname", example = "John Doe")
    private String nickname;
    
    @Schema(description = "Avatar URL", example = "https://example.com/avatar.jpg")
    private String avatar;
    
    @Schema(description = "Gender: 0-Unknown, 1-Male, 2-Female", example = "1")
    private Integer gender;
    
    @Schema(description = "Birthday", example = "1990-01-01")
    private LocalDate birthday;
    
    @Schema(description = "Phone number", example = "13800138000")
    private String phone;
    
    @Schema(description = "Email", example = "user@example.com")
    private String email;
}