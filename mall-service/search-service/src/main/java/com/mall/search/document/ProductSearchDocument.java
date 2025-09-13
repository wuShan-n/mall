package com.mall.search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * =====================================================
 * 1. 主商品搜索索引 - C端商品搜索核心索引
 * =====================================================
 */
@Data
@Document(indexName = "mall_product_v1")
@Setting(shards = 3, replicas = 1, refreshInterval = "1s")
public class ProductSearchDocument {

    @Id
    private String id; // 使用SPU ID

    @Version
    private Long version;

    // 基础信息
    @Field(type = FieldType.Long)
    private Long spuId;

    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword),
                    @InnerField(suffix = "pinyin", type = FieldType.Text, analyzer = "pinyin")
            }
    )
    private String productName;

    @Field(type = FieldType.Keyword)
    private String productCode;

    // 分类和品牌
    @Field(type = FieldType.Long)
    private Long categoryId;

    @Field(type = FieldType.Long)
    private List<Long> categoryPath; // [1, 12, 123]

    @Field(type = FieldType.Keyword)
    private String categoryName;

    @Field(type = FieldType.Long)
    private Long brandId;

    @Field(type = FieldType.Keyword)
    private String brandName;

    // 价格区间（从所有SKU计算）
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal minPrice;

    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal maxPrice;

    // 库存信息
    @Field(type = FieldType.Integer)
    private Integer totalStock;

    @Field(type = FieldType.Boolean)
    private Boolean hasStock;

    // SKU嵌套信息
    @Field(type = FieldType.Nested)
    private List<SkuInfo> skus;

    // 销售数据
    @Field(type = FieldType.Integer)
    private Integer salesCount;

    @Field(type = FieldType.Integer)
    private Integer commentCount;

    @Field(type = FieldType.Half_Float)
    private Float averageScore;

    // 搜索优化
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String keywords;

    @Field(type = FieldType.Keyword)
    private List<String> tags;

    // 状态标记
    @Field(type = FieldType.Boolean)
    private Boolean isNew;

    @Field(type = FieldType.Boolean)
    private Boolean isHot;

    @Field(type = FieldType.Boolean)
    private Boolean isBest;

    @Field(type = FieldType.Integer)
    private Integer status;

    // 排序字段
    @Field(type = FieldType.Float)
    private Float popularityScore;

    @Field(type = FieldType.Integer)
    private Integer sortOrder;

    // 时间
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createTime;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime updateTime;

    // 图片
    @Field(type = FieldType.Keyword, index = false)
    private String mainImage;

    @Data
    public static class SkuInfo {
        @Field(type = FieldType.Long)
        private Long skuId;

        @Field(type = FieldType.Keyword)
        private String skuCode;

        @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
        private BigDecimal price;

        @Field(type = FieldType.Integer)
        private Integer stock;

        @Field(type = FieldType.Object)
        private Map<String, String> specs;

        @Field(type = FieldType.Keyword, index = false)
        private String image;
    }
}
