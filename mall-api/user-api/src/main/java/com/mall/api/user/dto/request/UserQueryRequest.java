package com.mall.api.user.dto.request;

import com.mall.common.base.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * User query request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "User query request")
public class UserQueryRequest extends PageRequest {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Username", example = "user")
    private String username;
    
    @Schema(description = "Nickname", example = "John")
    private String nickname;
    
    @Schema(description = "Phone number", example = "138")
    private String phone;
    
    @Schema(description = "Email", example = "@example.com")
    private String email;
    
    @Schema(description = "Status: 0-Normal, 1-Disabled", example = "0")
    private Integer status;
    
    @Schema(description = "Gender: 0-Unknown, 1-Male, 2-Female", example = "1")
    private Integer gender;
    
    @Schema(description = "Register start time", example = "2024-01-01 00:00:00")
    private LocalDateTime registerStartTime;
    
    @Schema(description = "Register end time", example = "2024-12-31 23:59:59")
    private LocalDateTime registerEndTime;
}