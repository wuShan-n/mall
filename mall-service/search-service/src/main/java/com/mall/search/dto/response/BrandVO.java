package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 品牌VO
 */
@Data
@Builder
@Schema(description = "品牌信息")
public class BrandVO {
    @Schema(description = "品牌ID")
    private Long brandId;
    
    @Schema(description = "品牌名称")
    private String brandName;
    
    @Schema(description = "品牌Logo")
    private String brandLogo;
    
    @Schema(description = "品牌描述")
    private String description;
    
    @Schema(description = "商品数量")
    private Integer productCount;
}
