package com.mall.api.user.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Third party login request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Third party login request")
public class ThirdPartyLoginRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Login type cannot be null")
    @Schema(description = "Login type: 1-WeChat, 2-Alipay, 3-QQ", example = "1")
    private Integer loginType;
    
    @NotBlank(message = "Auth code cannot be empty")
    @Schema(description = "Authorization code", example = "abc123")
    private String authCode;
    
    @Schema(description = "State parameter", example = "state123")
    private String state;
    
    @Schema(description = "Client type: 1-PC, 2-APP, 3-Mini Program", example = "2")
    private Integer clientType;
}