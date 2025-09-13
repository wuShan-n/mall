package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品搜索结果VO
 */
@Value
@Builder
@Schema(description = "商品搜索结果")
public class ProductSearchVO {
    Long spuId;
    String productName;
    String productCode;
    String mainImage;
    BigDecimal minPrice;
    BigDecimal maxPrice;
    Integer totalStock;
    Integer salesCount;
    Integer commentCount;
    Float averageScore;
    List<String> tags;
    String brandName;
    String categoryName;
    Boolean isNew;
    Boolean isHot;
    Float highlightScore; // 搜索相关度分数
    String highlightName; // 高亮的商品名称
    List<SkuSimpleVO> skus; // SKU简要信息
}
