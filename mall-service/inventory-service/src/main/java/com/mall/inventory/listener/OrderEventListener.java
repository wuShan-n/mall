// OrderEventListener.java
package com.mall.inventory.listener;

import com.mall.api.inventory.dto.request.StockLockRequest;
import com.mall.inventory.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {
    
    private final StockService stockService;
    
    /**
     * 监听订单创建事件，锁定库存
     */
    @RabbitListener(queues = "inventory.order.created.queue")
    public void handleOrderCreated(Map<String, Object> message) {
        try {
            log.info("Received order created event: {}", message);
            
            String orderNo = (String) message.get("orderNo");
            Long userId = ((Number) message.get("userId")).longValue();
            
            // 处理订单项库存锁定
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> items = (List<Map<String, Object>>) message.get("items");
            
            for (Map<String, Object> item : items) {
                StockLockRequest request = new StockLockRequest();
                request.setOrderNo(orderNo);
                request.setUserId(userId);
                request.setSkuId(((Number) item.get("skuId")).longValue());
                request.setQuantity(((Number) item.get("quantity")).intValue());
                
                stockService.lockStock(request);
            }
            
            log.info("Stock locked for order: {}", orderNo);
            
        } catch (Exception e) {
            log.error("Failed to handle order created event", e);
            throw e;
        }
    }
    
    /**
     * 监听订单支付事件，扣减库存
     */
    @RabbitListener(queues = "inventory.order.paid.queue")
    public void handleOrderPaid(Map<String, Object> message) {
        try {
            log.info("Received order paid event: {}", message);
            
            String orderNo = (String) message.get("orderNo");
            stockService.deductLockedStock(orderNo);
            
            log.info("Stock deducted for order: {}", orderNo);
            
        } catch (Exception e) {
            log.error("Failed to handle order paid event", e);
            throw e;
        }
    }
    
    /**
     * 监听订单取消事件，释放库存
     */
    @RabbitListener(queues = "inventory.order.cancelled.queue")
    public void handleOrderCancelled(Map<String, Object> message) {
        try {
            log.info("Received order cancelled event: {}", message);
            
            String orderNo = (String) message.get("orderNo");
            stockService.unlockStock(orderNo);
            
            log.info("Stock unlocked for order: {}", orderNo);
            
        } catch (Exception e) {
            log.error("Failed to handle order cancelled event", e);
            throw e;
        }
    }
    
    /**
     * 监听订单退货事件，恢复库存
     */
    @RabbitListener(queues = "inventory.order.returned.queue")
    public void handleOrderReturned(Map<String, Object> message) {
        try {
            log.info("Received order returned event: {}", message);
            
            String orderNo = (String) message.get("orderNo");
            Long skuId = ((Number) message.get("skuId")).longValue();
            Integer quantity = ((Number) message.get("quantity")).intValue();
            
            stockService.returnStock(skuId, quantity, orderNo);
            
            log.info("Stock returned for order: {}", orderNo);
            
        } catch (Exception e) {
            log.error("Failed to handle order returned event", e);
            throw e;
        }
    }
}