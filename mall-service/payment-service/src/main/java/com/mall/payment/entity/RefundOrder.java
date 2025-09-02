package com.mall.payment.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RefundOrder {
    @Schema(description="主键")
    private Long id;
    @Schema(description="退款单号")
    private String refundNo;
    @Schema(description="原支付单号")
    private String paymentNo;
    @Schema(description="业务订单号")
    private String orderNo;
    @Schema(description="用户ID")
    private Long userId;
    @Schema(description="退款金额")
    private BigDecimal refundAmount;
    @Schema(description="退款原因")
    private String refundReason;
    @Schema(description="状态")
    private Integer status;
    @Schema(description="第三方退款单号")
    private String thirdPartyRefundNo;
    @Schema(description="退款时间")
    private LocalDateTime refundTime;
    @Schema(description="创建时间")
    private LocalDateTime createTime;
    @Schema(description="更新时间")
    private LocalDateTime updateTime;
}
