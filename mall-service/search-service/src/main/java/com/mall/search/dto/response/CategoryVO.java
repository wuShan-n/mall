package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

/**
 * 分类VO
 */
@Value
@Builder
@Schema(description = "分类信息")
public class CategoryVO {

    @Schema(description = "分类ID")
    Long categoryId;

    @Schema(description = "分类名称")
    String categoryName;

    @Schema(description = "分类图标")
    String icon;

    @Schema(description = "分类层级")
    Integer level;

    @Schema(description = "父分类ID")
    Long parentId;

    @Schema(description = "商品数量")
    Integer productCount;
}