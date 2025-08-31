package com.mall.api.user.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

/**
 * User statistics view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "User statistics")
public class UserStatisticsVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Total users", example = "10000")
    private Long totalUsers;
    
    @Schema(description = "Today new users", example = "100")
    private Long todayNewUsers;
    
    @Schema(description = "Yesterday new users", example = "95")
    private Long yesterdayNewUsers;
    
    @Schema(description = "This month new users", example = "3000")
    private Long monthNewUsers;
    
    @Schema(description = "Active users (last 7 days)", example = "5000")
    private Long activeUsers;
    
    @Schema(description = "Total members", example = "8000")
    private Long totalMembers;
    
    @Schema(description = "VIP members", example = "1000")
    private Long vipMembers;
    
    @Schema(description = "Average consumption per user", example = "500.00")
    private BigDecimal avgConsumption;
}