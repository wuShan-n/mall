package com.mall.payment.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentOrder {
    @Schema(description="主键")
    private Long id;
    @Schema(description="支付单号")
    private String paymentNo;
    @Schema(description="业务订单号")
    private String orderNo;
    @Schema(description="用户ID")
    private Long userId;
    @Schema(description="支付类型:1-支付宝,2-微信")
    private Integer paymentType;
    @Schema(description="支付金额")
    private BigDecimal paymentAmount;
    @Schema(description="币种")
    private String currency;
    @Schema(description="状态:0-待支付,1-已支付,2-已退款")
    private Integer status;
    @Schema(description="第三方流水号")
    private String thirdPartyNo;
    @Schema(description="支付时间")
    private LocalDateTime paymentTime;
    @Schema(description="过期时间")
    private LocalDateTime expireTime;
    @Schema(description="回调时间")
    private LocalDateTime callbackTime;
    @Schema(description="回调内容")
    private String callbackContent;
    @Schema(description="创建时间")
    private LocalDateTime createTime;
    @Schema(description="更新时间")
    private LocalDateTime updateTime;
}
