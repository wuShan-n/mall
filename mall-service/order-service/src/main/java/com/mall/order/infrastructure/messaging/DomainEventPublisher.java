package com.mall.order.infrastructure.messaging;

import com.mall.order.domain.common.AggregateRoot;
import com.mall.order.domain.common.DomainEvent;
import com.mall.order.domain.order.event.OrderCancelledDomainEvent;
import com.mall.order.domain.order.event.OrderCreatedDomainEvent;
import com.mall.order.domain.order.event.OrderPaidDomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 领域事件发布器
 * 负责将领域事件转换为外部集成事件并发布
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DomainEventPublisher {
    
    private final OrderEventPublisher orderEventPublisher;
    
    /**
     * 发布聚合根中的所有领域事件
     */
    public void publishEvents(AggregateRoot aggregate) {
        List<DomainEvent> events = aggregate.getDomainEvents();
        
        for (DomainEvent event : events) {
            publishEvent(event);
        }
        
        // 发布完成后清除事件
        aggregate.clearDomainEvents();
    }
    
    /**
     * 发布单个领域事件
     */
    private void publishEvent(DomainEvent event) {
        log.info("Publishing domain event: {}", event.getEventType());
        
        try {
            // 根据事件类型转换并发布
            if (event instanceof OrderCreatedDomainEvent) {
                handleOrderCreatedEvent((OrderCreatedDomainEvent) event);
            } else if (event instanceof OrderPaidDomainEvent) {
                handleOrderPaidEvent((OrderPaidDomainEvent) event);
            } else if (event instanceof OrderCancelledDomainEvent) {
                handleOrderCancelledEvent((OrderCancelledDomainEvent) event);
            } else {
                log.warn("Unknown domain event type: {}", event.getEventType());
            }
        } catch (Exception e) {
            log.error("Failed to publish domain event: {}", event.getEventType(), e);
            // 这里可以考虑事件重试机制或将失败事件存储起来
            throw e;
        }
    }
    
    private void handleOrderCreatedEvent(OrderCreatedDomainEvent domainEvent) {
        // 转换为外部集成事件
        var integrationEvent = new com.mall.api.order.event.OrderCreatedEvent(
            domainEvent.getOrderId(),
            domainEvent.getOrderNo(),
            domainEvent.getUserId(),
            domainEvent.getTotalAmount(),
            domainEvent.getOrderItems().stream()
                .map(item -> new com.mall.api.order.event.OrderCreatedEvent.OrderItemInfo(
                    item.getSkuId(),
                    item.getSkuName(),
                    item.getQuantity(),
                    item.getPrice()
                ))
                .collect(java.util.stream.Collectors.toList()),
            domainEvent.getCreateTime(),
            domainEvent.getEventTime()
        );
        
        orderEventPublisher.publishOrderCreated(integrationEvent);
    }
    
    private void handleOrderPaidEvent(OrderPaidDomainEvent domainEvent) {
        // 转换为外部集成事件
        var integrationEvent = new com.mall.api.order.event.OrderPaidEvent(
            domainEvent.getOrderId(),
            domainEvent.getOrderNo(),
            domainEvent.getUserId(),
            domainEvent.getPayAmount(),
            domainEvent.getPaymentType(),
            domainEvent.getTransactionId(),
            domainEvent.getPaymentTime(),
            domainEvent.getEventTime()
        );
        
        orderEventPublisher.publishOrderPaid(integrationEvent);
    }
    
    private void handleOrderCancelledEvent(OrderCancelledDomainEvent domainEvent) {
        // 转换为外部集成事件
        var integrationEvent = new com.mall.api.order.event.OrderCancelledEvent(
            domainEvent.getOrderId(),
            domainEvent.getOrderNo(),
            domainEvent.getUserId(),
            domainEvent.getReason(),
            1, // 用户取消
            domainEvent.getStockReleaseList().stream()
                .map(stock -> new com.mall.api.order.event.OrderCancelledEvent.StockReleaseInfo(
                    stock.getSkuId(),
                    stock.getQuantity()
                ))
                .collect(java.util.stream.Collectors.toList()),
            0, // TODO: 计算退还积分
            null, // TODO: 退还优惠券
            domainEvent.getEventTime(),
            domainEvent.getEventTime()
        );
        
        orderEventPublisher.publishOrderCancelled(integrationEvent);
    }
}