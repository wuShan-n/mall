package com.mall.api.user.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.List;

/**
 * Member level view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Member level information")
public class MemberLevelVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Level ID", example = "1")
    private Integer id;
    
    @Schema(description = "Level name", example = "Silver Member")
    private String levelName;
    
    @Schema(description = "Minimum growth value", example = "0")
    private Integer minGrowth;
    
    @Schema(description = "Maximum growth value", example = "999")
    private Integer maxGrowth;
    
    @Schema(description = "Discount rate", example = "0.95")
    private BigDecimal discount;
    
    @Schema(description = "Level icon", example = "https://example.com/silver.png")
    private String icon;
    
    @Schema(description = "Level description", example = "Silver member enjoys 5% discount")
    private String description;
    
    @Schema(description = "Privileges list")
    private List<String> privileges;
    
    @Schema(description = "Status: 0-Enable, 1-Disable", example = "0")
    private Integer status;
}
