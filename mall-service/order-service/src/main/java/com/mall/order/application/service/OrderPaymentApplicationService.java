package com.mall.order.application.service;

import com.mall.api.order.dto.request.OrderPaymentRequest;
import com.mall.api.order.dto.response.OrderPaymentVO;
import com.mall.order.application.facade.OrderExternalServiceFacade;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.domain.order.service.OrderDomainService;
import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.infrastructure.messaging.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单支付应用服务
 * 专门处理订单支付相关的用例
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPaymentApplicationService {
    
    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final DomainEventPublisher domainEventPublisher;
    private final OrderExternalServiceFacade externalServiceFacade;
    
    /**
     * 发起订单支付
     */
    @Transactional(readOnly = true)
    public OrderPaymentVO initiatePayment(OrderPaymentRequest request) {
        log.info("Initiating payment for order: {}", request.getOrderNo());
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(request.getOrderNo());
            
            // 2. 验证订单状态
            validateOrderCanBePaid(order);
            
            // 3. 创建支付订单
            com.mall.api.payment.dto.response.PaymentResultVO paymentResult = externalServiceFacade.createPayment(
                    request.getOrderNo(),
                    order.getPayAmount().getAmount(),
                    request.getPaymentType()
            );
            
            // 4. 返回支付信息
            OrderPaymentVO paymentVO = new OrderPaymentVO();
            paymentVO.setOrderNo(request.getOrderNo());
            paymentVO.setPaymentNo(paymentResult.getPaymentNo());
            paymentVO.setPaymentUrl(paymentResult.getPaymentUrl());
            paymentVO.setPaymentAmount(order.getPayAmount().getAmount());
            
            log.info("Payment initiated successfully for order: {}, paymentNo: {}", 
                    request.getOrderNo(), paymentResult.getPaymentNo());
            
            return paymentVO;
            
        } catch (Exception e) {
            log.error("Failed to initiate payment for order: {}", request.getOrderNo(), e);
            throw e;
        }
    }
    
    /**
     * 处理支付回调
     */
    @Transactional
    public void handlePaymentCallback(String orderNo, String transactionId) {
        log.info("Handling payment callback for order: {}, transaction: {}", orderNo, transactionId);
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(orderNo);
            
            // 2. 验证订单状态
            validateOrderCanBePaid(order);
            
            // 3. 更新订单状态为已支付
            order.pay(transactionId);
            
            // 4. 扣减库存
            orderDomainService.deductStock(order.getOrderNo());
            
            // 5. 保存订单状态变更
            orderRepository.update(order);
            
            // 6. 发布支付成功领域事件
            domainEventPublisher.publishEvents(order);
            
            log.info("Payment callback handled successfully for order: {}, transaction: {}", 
                    orderNo, transactionId);
            
        } catch (Exception e) {
            log.error("Failed to handle payment callback for order: {}, transaction: {}", 
                     orderNo, transactionId, e);
            throw e;
        }
    }
    
    /**
     * 支付失败处理
     */
    @Transactional
    public void handlePaymentFailure(String orderNo, String reason) {
        log.info("Handling payment failure for order: {}, reason: {}", orderNo, reason);
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(orderNo);
            
            // 2. 释放库存
            orderDomainService.releaseStock(order.getOrderNo());
            
            // 3. 记录支付失败日志
            log.warn("Payment failed for order: {}, reason: {}", orderNo, reason);
            
            // 4. 可以考虑发布支付失败事件用于后续处理
            // domainEventPublisher.publishPaymentFailedEvent(order, reason);
            
        } catch (Exception e) {
            log.error("Failed to handle payment failure for order: {}, reason: {}", 
                     orderNo, reason, e);
            throw e;
        }
    }
    
    /**
     * 查询支付状态
     */
    @Transactional(readOnly = true)
    public PaymentStatus queryPaymentStatus(String orderNo) {
        log.debug("Querying payment status for order: {}", orderNo);
        
        try {
            // 1. 查找订单
            Order order = getOrderByNo(orderNo);
            
            // 2. 根据订单状态返回支付状态
            return mapOrderStatusToPaymentStatus(order);
            
        } catch (Exception e) {
            log.error("Failed to query payment status for order: {}", orderNo, e);
            throw e;
        }
    }
    
    /**
     * 根据订单号查找订单
     */
    private Order getOrderByNo(String orderNo) {
        return orderRepository.findByOrderNo(new OrderNo(orderNo))
                .orElseThrow(() -> new IllegalArgumentException("订单不存在: " + orderNo));
    }
    
    /**
     * 验证订单是否可以支付
     */
    private void validateOrderCanBePaid(Order order) {
        if (!order.getStatus().canPay()) {
            throw new IllegalStateException("订单当前状态不允许支付: " + order.getStatus());
        }
    }
    
    /**
     * 将订单状态映射为支付状态
     */
    private PaymentStatus mapOrderStatusToPaymentStatus(Order order) {
        switch (order.getStatus()) {
            case PENDING_PAYMENT:
                return PaymentStatus.PENDING;
            case PAID:
            case PENDING_DELIVERY:
            case DELIVERED:
            case RECEIVED:
            case COMPLETED:
                return PaymentStatus.SUCCESS;
            case CANCELLED:
                return PaymentStatus.CANCELLED;
            default:
                return PaymentStatus.UNKNOWN;
        }
    }
    
    /**
     * 支付状态枚举
     */
    public enum PaymentStatus {
        PENDING("待支付"),
        SUCCESS("支付成功"),
        FAILED("支付失败"),
        CANCELLED("已取消"),
        UNKNOWN("未知状态");
        
        private final String description;
        
        PaymentStatus(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
}