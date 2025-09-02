//package com.mall.product.listener;
//
//import com.mall.product.config.RabbitMQConfig;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Product Event Publisher
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class ProductEventPublisher {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    /**
//     * Publish stock lock event
//     */
//    public void publishStockLockEvent(Long skuId, Integer quantity, String orderNo) {
//        Map<String, Object> message = new HashMap<>();
//        message.put("skuId", skuId);
//        message.put("quantity", quantity);
//        message.put("orderNo", orderNo);
//        message.put("timestamp", System.currentTimeMillis());
//
//        rabbitTemplate.convertAndSend(
//            RabbitMQConfig.STOCK_EXCHANGE,
//            RabbitMQConfig.STOCK_LOCK_ROUTING_KEY,
//            message
//        );
//
//        log.info("Published stock lock event for SKU: {}, quantity: {}", skuId, quantity);
//    }
//
//    /**
//     * Publish product update event
//     */
//    public void publishProductUpdateEvent(Long productId, String action) {
//        Map<String, Object> message = new HashMap<>();
//        message.put("productId", productId);
//        message.put("action", action);
//        message.put("timestamp", System.currentTimeMillis());
//
//        rabbitTemplate.convertAndSend(
//            RabbitMQConfig.STOCK_EXCHANGE,
//            RabbitMQConfig.PRODUCT_UPDATE_ROUTING_KEY,
//            message
//        );
//
//        log.info("Published product update event for product: {}, action: {}", productId, action);
//    }
//}