package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

/**
 * 品牌VO
 */
@Value
@Builder
@Schema(description = "品牌信息")
public class BrandVO {

    @Schema(description = "品牌ID")
    Long brandId;

    @Schema(description = "品牌名称")
    String brandName;

    @Schema(description = "品牌Logo")
    String brandLogo;

    @Schema(description = "品牌描述")
    String description;

    @Schema(description = "商品数量")
    Integer productCount;
}
