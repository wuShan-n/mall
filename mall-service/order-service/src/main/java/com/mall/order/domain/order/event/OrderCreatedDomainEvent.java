package com.mall.order.domain.order.event;

import com.mall.order.domain.common.DomainEvent;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单创建领域事件
 */
public class OrderCreatedDomainEvent extends DomainEvent {
    
    private final Long orderId;
    private final String orderNo;
    private final Long userId;
    private final BigDecimal totalAmount;
    private final List<OrderItemInfo> orderItems;
    private final LocalDateTime createTime;
    
    public OrderCreatedDomainEvent(Order order) {
        super();
        this.orderId = order.getId();
        this.orderNo = order.getOrderNo().getValue();
        this.userId = order.getUserId().getValue();
        this.totalAmount = order.getTotalAmount().getAmount();
        this.orderItems = order.getOrderItems().stream()
            .map(OrderItemInfo::new)
            .collect(Collectors.toList());
        this.createTime = order.getCreateTime();
    }
    
    public Long getOrderId() {
        return orderId;
    }
    
    public String getOrderNo() {
        return orderNo;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public List<OrderItemInfo> getOrderItems() {
        return orderItems;
    }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    public static class OrderItemInfo {
        private final Long skuId;
        private final String skuName;
        private final Integer quantity;
        private final BigDecimal price;
        
        public OrderItemInfo(OrderItem item) {
            this.skuId = item.getSkuId();
            this.skuName = item.getSkuName();
            this.quantity = item.getQuantity();
            this.price = item.getPrice();
        }
        
        public Long getSkuId() {
            return skuId;
        }
        
        public String getSkuName() {
            return skuName;
        }
        
        public Integer getQuantity() {
            return quantity;
        }
        
        public BigDecimal getPrice() {
            return price;
        }
    }
}