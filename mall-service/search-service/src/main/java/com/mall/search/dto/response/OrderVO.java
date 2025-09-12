package com.mall.search.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
/**
 * 订单VO
 */
@Data
@Builder
@Schema(description = "订单信息")
public class OrderVO {
    @Schema(description = "订单ID")
    private Long orderId;
    
    @Schema(description = "订单号")
    private String orderNo;
    
    @Schema(description = "用户ID")
    private Long userId;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "订单商品")
    private List<OrderItemVO> items;
    
    @Schema(description = "订单总额")
    private BigDecimal totalAmount;
    
    @Schema(description = "实付金额")
    private BigDecimal payAmount;
    
    @Schema(description = "订单状态")
    private Integer status;
    
    @Schema(description = "状态描述")
    private String statusText;
    
    @Schema(description = "支付方式")
    private String paymentType;
    
    @Schema(description = "支付时间")
    private LocalDateTime paymentTime;
    
    @Schema(description = "下单时间")
    private LocalDateTime createTime;
    
    @Schema(description = "收货信息")
    private ReceiverInfo receiverInfo;
}
