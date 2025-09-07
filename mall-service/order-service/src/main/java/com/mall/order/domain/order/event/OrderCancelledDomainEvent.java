package com.mall.order.domain.order.event;

import com.mall.order.domain.common.DomainEvent;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单取消领域事件
 */
public class OrderCancelledDomainEvent extends DomainEvent {
    
    private final Long orderId;
    private final String orderNo;
    private final Long userId;
    private final String reason;
    private final List<StockReleaseInfo> stockReleaseList;
    
    public OrderCancelledDomainEvent(Order order, String reason) {
        super();
        this.orderId = order.getId();
        this.orderNo = order.getOrderNo().getValue();
        this.userId = order.getUserId().getValue();
        this.reason = reason;
        this.stockReleaseList = order.getOrderItems().stream()
            .map(StockReleaseInfo::new)
            .collect(Collectors.toList());
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
    
    public String getReason() {
        return reason;
    }
    
    public List<StockReleaseInfo> getStockReleaseList() {
        return stockReleaseList;
    }
    
    public static class StockReleaseInfo {
        private final Long skuId;
        private final Integer quantity;
        
        public StockReleaseInfo(OrderItem item) {
            this.skuId = item.getSkuId();
            this.quantity = item.getQuantity();
        }
        
        public Long getSkuId() {
            return skuId;
        }
        
        public Integer getQuantity() {
            return quantity;
        }
    }
}