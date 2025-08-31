package com.mall.api.product.dto.response;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * SPU view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "SPU information")
public class SpuVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "SPU ID", example = "1")
    private Long id;
    
    @Schema(description = "Product name", example = "iPhone 15 Pro")
    private String productName;
    
    @Schema(description = "Product code", example = "IP15PRO")
    private String productCode;
    
    @Schema(description = "Category ID", example = "1")
    private Long categoryId;
    
    @Schema(description = "Category name", example = "Smartphones")
    private String categoryName;
    
    @Schema(description = "Brand ID", example = "1")
    private Long brandId;
    
    @Schema(description = "Brand name", example = "Apple")
    private String brandName;
    
    @Schema(description = "Main image URL", example = "https://example.com/main.jpg")
    private String mainImage;
    
    @Schema(description = "Image list")
    private List<String> images;
    
    @Schema(description = "Video URL", example = "https://example.com/video.mp4")
    private String videoUrl;
    
    @Schema(description = "Unit", example = "piece")
    private String unit;
    
    @Schema(description = "Weight in kg", example = "0.5")
    private BigDecimal weight;
    
    @Schema(description = "Introduction")
    private String introduction;
    
    @Schema(description = "Keywords", example = "phone,smartphone,apple")
    private String keywords;
    
    @Schema(description = "Tags", example = "new,hot")
    private String tags;
    
    @Schema(description = "Sale count", example = "1000")
    private Integer saleCount;
    
    @Schema(description = "View count", example = "10000")
    private Integer viewCount;
    
    @Schema(description = "Comment count", example = "500")
    private Integer commentCount;
    
    @Schema(description = "Score", example = "4.8")
    private BigDecimal score;
    
    @Schema(description = "Is new product", example = "true")
    private Boolean isNew;
    
    @Schema(description = "Is hot sale", example = "true")
    private Boolean isHot;
    
    @Schema(description = "Is best product", example = "false")
    private Boolean isBest;
    
    @Schema(description = "Status: 0-Off shelf, 1-On shelf", example = "1")
    private Integer status;
    
    @Schema(description = "Audit status: 0-Pending, 1-Approved, 2-Rejected", example = "1")
    private Integer auditStatus;
    
    @Schema(description = "Sort order", example = "0")
    private Integer sort;
    
    @Schema(description = "Minimum price", example = "9999.00")
    private BigDecimal minPrice;
    
    @Schema(description = "Maximum price", example = "12999.00")
    private BigDecimal maxPrice;
    
    @Schema(description = "Create time", example = "2024-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
