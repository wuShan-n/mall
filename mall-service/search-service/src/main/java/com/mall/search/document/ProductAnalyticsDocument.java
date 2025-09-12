package com.mall.search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * =====================================================
 * 6. 商品分析索引 - 运营数据分析
 * =====================================================
 */
@Data
@Document(indexName = "mall_product_analytics_#{T(java.time.LocalDate).now().format(T(java.time.format.DateTimeFormatter).ofPattern('yyyyMMdd'))}")
@Setting(shards = 1, replicas = 0, refreshInterval = "30s")
public class ProductAnalyticsDocument {
    
    @Id
    private String id; // spu_id + date
    
    // 商品信息
    @Field(type = FieldType.Long)
    private Long spuId;
    
    @Field(type = FieldType.Keyword)
    private String productName;
    
    @Field(type = FieldType.Long)
    private Long categoryId;
    
    @Field(type = FieldType.Keyword)
    private String categoryName;
    
    @Field(type = FieldType.Long)
    private Long brandId;
    
    @Field(type = FieldType.Keyword)
    private String brandName;
    
    // 统计日期
    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDateTime statisticsDate;
    
    // 流量数据
    @Field(type = FieldType.Integer)
    private Integer pageViews; // PV
    
    @Field(type = FieldType.Integer)
    private Integer uniqueVisitors; // UV
    
    @Field(type = FieldType.Integer)
    private Integer searchCount; // 搜索次数
    
    @Field(type = FieldType.Integer)
    private Integer clickCount; // 点击次数
    
    @Field(type = FieldType.Float)
    private Float clickRate; // 点击率
    
    // 销售数据
    @Field(type = FieldType.Integer)
    private Integer orderCount; // 订单数
    
    @Field(type = FieldType.Integer)
    private Integer salesQuantity; // 销售数量
    
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal salesAmount; // 销售金额
    
    @Field(type = FieldType.Float)
    private Float conversionRate; // 转化率
    
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal avgOrderValue; // 客单价
    
    // 库存数据
    @Field(type = FieldType.Integer)
    private Integer stockIn; // 入库数量
    
    @Field(type = FieldType.Integer)
    private Integer stockOut; // 出库数量
    
    @Field(type = FieldType.Integer)
    private Integer currentStock; // 当前库存
    
    @Field(type = FieldType.Float)
    private Float turnoverRate; // 周转率
    
    // 用户行为
    @Field(type = FieldType.Integer)
    private Integer addToCartCount; // 加购数
    
    @Field(type = FieldType.Integer)
    private Integer favoriteCount; // 收藏数
    
    @Field(type = FieldType.Integer)
    private Integer shareCount; // 分享数
    
    @Field(type = FieldType.Integer)
    private Integer commentCount; // 评论数
    
    @Field(type = FieldType.Float)
    private Float avgScore; // 平均评分
    
    // 退款数据
    @Field(type = FieldType.Integer)
    private Integer refundCount; // 退款次数
    
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal refundAmount; // 退款金额
    
    @Field(type = FieldType.Float)
    private Float refundRate; // 退款率
    
    // 时间
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createTime;
}