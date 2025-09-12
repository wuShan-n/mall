package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 分类VO
 */
@Data
@Builder
@Schema(description = "分类信息")
public class CategoryVO {
    @Schema(description = "分类ID")
    private Long categoryId;
    
    @Schema(description = "分类名称")
    private String categoryName;
    
    @Schema(description = "分类图标")
    private String icon;
    
    @Schema(description = "分类层级")
    private Integer level;
    
    @Schema(description = "父分类ID")
    private Long parentId;
    
    @Schema(description = "商品数量")
    private Integer productCount;
}