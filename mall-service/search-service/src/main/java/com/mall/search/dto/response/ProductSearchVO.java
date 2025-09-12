package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品搜索结果VO
 */
@Data
@Schema(description = "商品搜索结果")
public class ProductSearchVO {
    private Long spuId;
    private String productName;
    private String productCode;
    private String mainImage;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer totalStock;
    private Integer salesCount;
    private Integer commentCount;
    private Float averageScore;
    private List<String> tags;
    private String brandName;
    private String categoryName;
    private Boolean isNew;
    private Boolean isHot;
    private Float highlightScore; // 搜索相关度分数
    private String highlightName; // 高亮的商品名称
    private List<SkuSimpleVO> skus; // SKU简要信息
}
