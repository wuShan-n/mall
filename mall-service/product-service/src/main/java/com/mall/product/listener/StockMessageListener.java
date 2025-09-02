//package com.mall.product.listener;
//
//import com.mall.product.config.RabbitMQConfig;
//import com.mall.product.service.ProductService;
//import com.rabbitmq.client.Channel;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.Map;
//
///**
// * Stock Message Listener
// */
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class StockMessageListener {
//
//    private final ProductService productService;
//
//    @RabbitListener(queues = RabbitMQConfig.STOCK_UNLOCK_QUEUE)
//    public void handleStockUnlock(Map<String, Object> message, Channel channel, Message amqpMessage) {
//        try {
//            log.info("Received stock unlock message: {}", message);
//
//            Long skuId = Long.valueOf(message.get("skuId").toString());
//            Integer quantity = Integer.valueOf(message.get("quantity").toString());
//            String orderNo = message.get("orderNo").toString();
//
//            // Unlock stock
//            productService.unlockStock(skuId, quantity, orderNo);
//
//            // Acknowledge message
//            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
//            log.info("Stock unlocked successfully for SKU: {}, quantity: {}", skuId, quantity);
//
//        } catch (Exception e) {
//            log.error("Failed to unlock stock: ", e);
//            try {
//                // Reject message and requeue
//                channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, true);
//            } catch (IOException ioException) {
//                log.error("Failed to nack message: ", ioException);
//            }
//        }
//    }
//
//    @RabbitListener(queues = RabbitMQConfig.PRODUCT_UPDATE_QUEUE)
//    public void handleProductUpdate(Map<String, Object> message, Channel channel, Message amqpMessage) {
//        try {
//            log.info("Received product update message: {}", message);
//
//            String action = message.get("action").toString();
//            Long productId = Long.valueOf(message.get("productId").toString());
//
//            // Handle different update actions
//            switch (action) {
//                case "SYNC_ES":
//                    // Sync product to Elasticsearch
//                    syncProductToES(productId);
//                    break;
//                case "CLEAR_CACHE":
//                    // Clear product cache
//                    clearProductCache(productId);
//                    break;
//                default:
//                    log.warn("Unknown action: {}", action);
//            }
//
//            // Acknowledge message
//            channel.basicAck(amqpMessage.getMessageProperties().getDeliveryTag(), false);
//
//        } catch (Exception e) {
//            log.error("Failed to handle product update: ", e);
//            try {
//                // Reject message and don't requeue
//                channel.basicNack(amqpMessage.getMessageProperties().getDeliveryTag(), false, false);
//            } catch (IOException ioException) {
//                log.error("Failed to nack message: ", ioException);
//            }
//        }
//    }
//
//    private void syncProductToES(Long productId) {
//        // TODO: Implement Elasticsearch sync
//        log.info("Syncing product {} to Elasticsearch", productId);
//    }
//
//    private void clearProductCache(Long productId) {
//        // TODO: Implement cache clearing
//        log.info("Clearing cache for product {}", productId);
//    }
//}