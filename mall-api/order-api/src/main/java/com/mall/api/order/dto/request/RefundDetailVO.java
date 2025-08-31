package com.mall.api.order.dto.request;

import com.mall.api.order.dto.response.OrderItemVO;
import com.mall.api.order.dto.response.OrderVO;
import com.mall.api.order.dto.response.RefundVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * Refund detail view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Refund detail information")
public class RefundDetailVO extends RefundVO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Order info")
    private OrderVO orderInfo;
    
    @Schema(description = "Refund items")
    private List<OrderItemVO> refundItems;
    
    @Schema(description = "Refund timeline")
    private List<RefundTimelineVO> timeline;
    
    @Schema(description = "Return address")
    private ReturnAddressVO returnAddress;
}