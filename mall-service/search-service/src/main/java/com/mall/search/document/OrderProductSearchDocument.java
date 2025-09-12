package com.mall.search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * =====================================================
 * 3. 订单商品搜索索引 - 订单管理和历史查询
 * =====================================================
 */
@Data
@Document(indexName = "mall_order_product_#{T(java.time.LocalDate).now().format(T(java.time.format.DateTimeFormatter).ofPattern('yyyyMM'))}")
@Setting(shards = 2, replicas = 1, refreshInterval = "5s")
public class OrderProductSearchDocument {
    
    @Id
    private String id; // order_id + sku_id
    
    // 订单信息
    @Field(type = FieldType.Keyword)
    private String orderNo;
    
    @Field(type = FieldType.Long)
    private Long orderId;
    
    @Field(type = FieldType.Long)
    private Long userId;
    
    @Field(type = FieldType.Keyword)
    private String username;
    
    // 商品快照信息
    @Field(type = FieldType.Long)
    private Long spuId;
    
    @Field(type = FieldType.Long)
    private Long skuId;
    
    @MultiField(
        mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"),
        otherFields = @InnerField(suffix = "keyword", type = FieldType.Keyword)
    )
    private String productName;
    
    @Field(type = FieldType.Keyword)
    private String productCode;
    
    @Field(type = FieldType.Keyword)
    private String productSpecs;
    
    @Field(type = FieldType.Long)
    private Long categoryId;
    
    @Field(type = FieldType.Keyword)
    private String categoryName;
    
    @Field(type = FieldType.Keyword)
    private String brandName;
    
    // 价格信息（成交价）
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal price;
    
    @Field(type = FieldType.Integer)
    private Integer quantity;
    
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal totalAmount;
    
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal discountAmount;
    
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal realAmount;
    
    // 订单状态
    @Field(type = FieldType.Integer)
    private Integer orderStatus;
    
    @Field(type = FieldType.Keyword)
    private String orderStatusText;
    
    @Field(type = FieldType.Integer)
    private Integer refundStatus;
    
    @Field(type = FieldType.Integer)
    private Integer commentStatus;
    
    // 支付信息
    @Field(type = FieldType.Keyword)
    private String paymentType;
    
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime paymentTime;
    
    // 物流信息
    @Field(type = FieldType.Keyword)
    private String deliveryCompany;
    
    @Field(type = FieldType.Keyword)
    private String deliveryNo;
    
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime deliveryTime;
    
    // 收货地址
    @Field(type = FieldType.Keyword)
    private String receiverProvince;
    
    @Field(type = FieldType.Keyword)
    private String receiverCity;
    
    @Field(type = FieldType.Text)
    private String receiverAddress;
    
    // 时间
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime orderCreateTime;
    
    // 商品图片
    @Field(type = FieldType.Keyword, index = false)
    private String productImage;
}
