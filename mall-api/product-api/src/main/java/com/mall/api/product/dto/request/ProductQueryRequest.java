package com.mall.api.product.dto.request;

import com.mall.common.base.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Product query request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Product query request")
public class ProductQueryRequest extends PageRequest {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Product name", example = "iPhone")
    private String productName;
    
    @Schema(description = "Product code", example = "IP")
    private String productCode;
    
    @Schema(description = "Category ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "Brand ID", example = "1")
    private Long brandId;
    
    @Schema(description = "Minimum price", example = "0.00")
    private BigDecimal minPrice;
    
    @Schema(description = "Maximum price", example = "10000.00")
    private BigDecimal maxPrice;
    
    @Schema(description = "Status: 0-Off shelf, 1-On shelf", example = "1")
    private Integer status;
    
    @Schema(description = "Audit status: 0-Pending, 1-Approved, 2-Rejected", example = "1")
    private Integer auditStatus;
    
    @Schema(description = "Is new product", example = "true")
    private Boolean isNew;
    
    @Schema(description = "Is hot sale", example = "true")
    private Boolean isHot;
    
    @Schema(description = "Is best product", example = "true")
    private Boolean isBest;
    
    @Schema(description = "Keywords", example = "phone")
    private String keywords;
    
    @Schema(description = "Start time", example = "2024-01-01 00:00:00")
    private LocalDateTime startTime;
    
    @Schema(description = "End time", example = "2024-12-31 23:59:59")
    private LocalDateTime endTime;
}
