package com.mall.api.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * SPU detail view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "SPU detail information")
public class SpuDetailVO extends SpuVO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "SKU list")
    private List<SkuVO> skuList;
    
    @Schema(description = "Product attributes")
    private List<AttributeVO> attributes;
    
    @Schema(description = "Product specifications")
    private List<SpecificationVO> specifications;
    
    @Schema(description = "Product detail HTML")
    private String detailHtml;
    
    @Schema(description = "Product detail mobile HTML")
    private String detailMobileHtml;
}