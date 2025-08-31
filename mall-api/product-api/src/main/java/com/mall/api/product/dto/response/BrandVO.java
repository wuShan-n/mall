package com.mall.api.product.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * Brand view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Brand information")
public class BrandVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Brand ID", example = "1")
    private Long id;
    
    @Schema(description = "Brand name", example = "Apple")
    private String brandName;
    
    @Schema(description = "Brand code", example = "APPLE")
    private String brandCode;
    
    @Schema(description = "Logo URL", example = "https://example.com/logo.png")
    private String logo;
    
    @Schema(description = "Description", example = "Apple Inc.")
    private String description;
    
    @Schema(description = "Sort order", example = "0")
    private Integer sort;
    
    @Schema(description = "Status: 0-Disable, 1-Enable", example = "1")
    private Integer status;
    
    @Schema(description = "Product count", example = "100")
    private Integer productCount;
    
    @Schema(description = "Create time", example = "2024-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}