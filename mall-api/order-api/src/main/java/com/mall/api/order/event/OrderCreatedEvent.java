package com.mall.api.order.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Order created event
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Order ID
     */
    private Long orderId;
    
    /**
     * Order number
     */
    private String orderNo;
    
    /**
     * User ID
     */
    private Long userId;
    
    /**
     * Total amount
     */
    private BigDecimal totalAmount;
    
    /**
     * Order items
     */
    private List<OrderItemInfo> orderItems;
    
    /**
     * Create time
     */
    private LocalDateTime createTime;
    
    /**
     * Event time
     */
    private LocalDateTime eventTime;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemInfo implements Serializable {
        private Long skuId;
        private String productName;
        private Integer quantity;
        private BigDecimal price;
    }
}
