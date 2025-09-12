package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 相似搜索请求
 */
@Data
@Schema(description = "相似搜索请求")
public class SimilarSearchRequest {
    @Schema(description = "基准商品ID")
    private Long spuId;
    
    @Schema(description = "相似度阈值")
    private Float minSimilarity = 0.7f;
    
    @Schema(description = "返回数量")
    private Integer size = 10;
    
    @Schema(description = "相似维度")
    private List<SimilarDimension> dimensions;
    
    public enum SimilarDimension {
        CATEGORY,   // 分类相似
        BRAND,      // 品牌相似
        PRICE,      // 价格相似
        ATTRIBUTE,  // 属性相似
        FEATURE     // 特征相似
    }
}
