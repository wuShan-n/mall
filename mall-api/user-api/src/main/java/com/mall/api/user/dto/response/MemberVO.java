package com.mall.api.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Member information view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Member information")
public class MemberVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Member ID", example = "1")
    private Long id;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Member number", example = "M202401010001")
    private String memberNo;
    
    @Schema(description = "Member level ID", example = "1")
    private Integer levelId;
    
    @Schema(description = "Member level name", example = "Silver")
    private String levelName;
    
    @Schema(description = "Points", example = "1000")
    private Integer points;
    
    @Schema(description = "Growth value", example = "500")
    private Integer growthValue;
    
    @Schema(description = "Balance", example = "100.00")
    private BigDecimal balance;
    
    @Schema(description = "Total consumption amount", example = "5000.00")
    private BigDecimal totalAmount;
    
    @Schema(description = "Total orders", example = "20")
    private Integer totalOrders;
    
    @Schema(description = "Member expire time", example = "2025-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;
    
    @Schema(description = "Member discount rate", example = "0.95")
    private BigDecimal discount;
    
    @Schema(description = "Member privileges")
    private String privileges;
}
