package com.mall.search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Product document for Elasticsearch
 * 
 * @author mall
 */
@Data
@Document(indexName = "mall_product")
@Setting(shards = 3, replicas = 1)
public class ProductDocument {

    @Id
    private String id;

    @Field(type = FieldType.Long)
    private Long spuId;

    @Field(type = FieldType.Long)
    private Long skuId;

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

    @Field(type = FieldType.Long)
    private Long categoryId;

    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String categoryName;

    @Field(type = FieldType.Keyword)
    private String categoryPath;

    @Field(type = FieldType.Long)
    private Long brandId;

    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String brandName;

    @Field(type = FieldType.Keyword, index = false)
    private String mainImage;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Double)
    private BigDecimal originalPrice;

    @Field(type = FieldType.Integer)
    private Integer discountPercent;

    @Field(type = FieldType.Integer)
    private Integer salesCount;

    @Field(type = FieldType.Integer)
    private Integer commentCount;

    @Field(type = FieldType.Float)
    private Float score;

    @Field(type = FieldType.Keyword)
    private String stockStatus;

    @Field(type = FieldType.Integer)
    private Integer stock;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private List<String> tags;

    @Field(type = FieldType.Nested)
    private List<ProductAttribute> attributes;

    @Field(type = FieldType.Nested)
    private List<SkuOption> skuOptions;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String promotionInfo;

    @Field(type = FieldType.Boolean)
    private Boolean isNew;

    @Field(type = FieldType.Boolean)
    private Boolean isHot;

    @Field(type = FieldType.Boolean)
    private Boolean isBest;

    @Field(type = FieldType.Boolean)
    private Boolean hasDiscount;

    @Field(type = FieldType.Boolean)
    private Boolean freeShipping;

    @Field(type = FieldType.Integer)
    private Integer status;

    @Field(type = FieldType.Long)
    private Long storeId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String storeName;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String keywords;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String introduction;

    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * Product attribute
     */
    @Data
    public static class ProductAttribute {
        @Field(type = FieldType.Keyword)
        private String attrName;

        @Field(type = FieldType.Keyword)
        private String attrValue;

        @Field(type = FieldType.Keyword)
        private String attrGroup;
    }

    /**
     * SKU option
     */
    @Data
    public static class SkuOption {
        @Field(type = FieldType.Long)
        private Long skuId;

        @Field(type = FieldType.Text, analyzer = "ik_max_word")
        private String skuName;

        @Field(type = FieldType.Double)
        private BigDecimal price;

        @Field(type = FieldType.Integer)
        private Integer stock;

        @Field(type = FieldType.Object)
        private Map<String, String> specs;
    }

    /**
     * Geographic point
     */
    @Data
    public static class GeoPoint {
        private double lat;
        private double lon;

        public GeoPoint() {}

        public GeoPoint(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    }
}