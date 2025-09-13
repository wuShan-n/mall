package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单VO
 */
@Value
@Builder
@Schema(description = "订单信息")
public class OrderVO {
    @Schema(description = "订单ID")
    Long orderId;

    @Schema(description = "订单号")
    String orderNo;

    @Schema(description = "用户ID")
    Long userId;

    @Schema(description = "用户名")
    String username;

    @Schema(description = "订单商品")
    List<OrderItemVO> items;

    @Schema(description = "订单总额")
    BigDecimal totalAmount;

    @Schema(description = "实付金额")
    BigDecimal payAmount;

    @Schema(description = "订单状态")
    Integer status;

    @Schema(description = "状态描述")
    String statusText;

    @Schema(description = "支付方式")
    String paymentType;

    @Schema(description = "支付时间")
    LocalDateTime paymentTime;

    @Schema(description = "下单时间")
    LocalDateTime createTime;

    @Schema(description = "收货信息")
    ReceiverInfo receiverInfo;

    /**
     * 收货信息
     */
    @Value
    @Builder
    @Schema(description = "收货信息")
    public static class ReceiverInfo {
        @Schema(description = "收货人")
        String name;

        @Schema(description = "手机号")
        String phone;

        @Schema(description = "收货地址")
        String address;
    }
}
