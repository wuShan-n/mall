package com.mall.search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * =====================================================
 * 5. 支付订单搜索索引 - 财务对账
 * =====================================================
 */
@Data
@Document(indexName = "mall_payment_v1")
@Setting(shards = 2, replicas = 1, refreshInterval = "5s")
public class PaymentSearchDocument {

    @Id
    private String id;

    // 支付信息
    @Field(type = FieldType.Keyword)
    private String paymentNo;

    @Field(type = FieldType.Keyword)
    private String orderNo;

    @Field(type = FieldType.Long)
    private Long userId;

    // 支付金额
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal paymentAmount;

    @Field(type = FieldType.Keyword)
    private String currency;

    // 支付方式
    @Field(type = FieldType.Keyword)
    private String paymentType; // ALIPAY, WECHAT

    @Field(type = FieldType.Keyword)
    private String thirdPartyNo;

    // 支付状态
    @Field(type = FieldType.Integer)
    private Integer status; // 0-待支付, 1-已支付, 2-已退款

    @Field(type = FieldType.Keyword)
    private String statusText;

    // 时间
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime createTime;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime paymentTime;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime expireTime;

    // 退款信息
    @Field(type = FieldType.Nested)
    private List<RefundInfo> refunds;

    @Data
    public static class RefundInfo {
        @Field(type = FieldType.Keyword)
        private String refundNo;

        @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
        private BigDecimal refundAmount;

        @Field(type = FieldType.Keyword)
        private String refundReason;

        @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
        private LocalDateTime refundTime;
    }
}