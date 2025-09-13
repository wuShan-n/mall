package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品搜索请求参数
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "商品搜索请求")
public class ProductSearchRequest {

    @Schema(description = "搜索关键词")
    private String keyword;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "分类路径")
    private List<Long> categoryPath;

    @Schema(description = "品牌ID列表")
    private List<Long> brandIds;

    @Schema(description = "价格区间")
    private PriceRange priceRange;

    @Schema(description = "商品属性筛选")
    private List<AttributeFilter> attributes;

    @Schema(description = "标签筛选")
    private List<String> tags;

    @Schema(description = "是否有货")
    private Boolean hasStock;

    @Schema(description = "是否新品")
    private Boolean isNew;

    @Schema(description = "是否热销")
    private Boolean isHot;

    @Schema(description = "是否精品")
    private Boolean isBest;

    @Schema(description = "排序字段")
    private SortField sortField = SortField.DEFAULT;

    @Schema(description = "排序方向")
    private SortDirection sortDirection = SortDirection.DESC;

    @Schema(description = "页码")
    @Min(1)
    private Integer pageNum = 1;

    @Schema(description = "每页大小")
    @Min(1)
    @Max(100)
    private Integer pageSize = 20;

    @Schema(description = "是否需要聚合数据")
    private Boolean needAggregation = false;

    @Schema(description = "搜索场景")
    private SearchScene scene = SearchScene.NORMAL;

    @Getter
    public enum SortField {
        DEFAULT("_score"),      // 默认相关性
        PRICE("minPrice"),       // 价格
        SALES("salesCount"),     // 销量
        COMMENT("commentCount"), // 评论数
        SCORE("averageScore"),   // 评分
        NEW("createTime"),       // 新品
        POPULARITY("popularityScore"); // 人气

        private final String field;

        SortField(String field) {
            this.field = field;
        }


    }

    public enum SortDirection {
        ASC, DESC
    }

    public enum SearchScene {
        NORMAL,     // 普通搜索
        CATEGORY,   // 分类浏览
        BRAND,      // 品牌专区
        PROMOTION   // 促销活动
    }

    @Data
    public static class PriceRange {
        @Schema(description = "最低价格")
        private BigDecimal minPrice;

        @Schema(description = "最高价格")
        private BigDecimal maxPrice;
    }

    @Data
    public static class AttributeFilter {
        @Schema(description = "属性名")
        private String attrName;

        @Schema(description = "属性值列表")
        private List<String> attrValues;
    }
}