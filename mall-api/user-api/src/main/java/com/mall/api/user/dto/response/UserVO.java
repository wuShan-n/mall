package com.mall.api.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * User view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "User information")
public class UserVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "User ID", example = "1")
    private Long id;
    
    @Schema(description = "Username", example = "user123")
    private String username;
    
    @Schema(description = "Nickname", example = "John Doe")
    private String nickname;
    
    @Schema(description = "Phone number", example = "138****8000")
    private String phone;
    
    @Schema(description = "Email", example = "u***@example.com")
    private String email;
    
    @Schema(description = "Avatar URL", example = "https://example.com/avatar.jpg")
    private String avatar;
    
    @Schema(description = "Gender: 0-Unknown, 1-Male, 2-Female", example = "1")
    private Integer gender;
    
    @Schema(description = "Birthday", example = "1990-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    
    @Schema(description = "Status: 0-Normal, 1-Disabled", example = "0")
    private Integer status;
    
    @Schema(description = "Register source: 1-PC, 2-APP, 3-Mini Program", example = "1")
    private Integer registerSource;
    
    @Schema(description = "Last login time", example = "2024-01-01 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;
    
    @Schema(description = "Last login IP", example = "192.168.1.1")
    private String lastLoginIp;
    
    @Schema(description = "Create time", example = "2024-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
