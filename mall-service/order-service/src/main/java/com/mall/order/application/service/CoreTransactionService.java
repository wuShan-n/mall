package com.mall.order.application.service;

import com.mall.api.order.dto.request.OrderCreateRequest;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderAddress;
import com.mall.order.domain.order.entity.OrderItem;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.domain.order.service.InventoryDomainService;
import com.mall.order.domain.order.service.OrderFactory;
import com.mall.order.infrastructure.external.PaymentServiceClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 核心事务服务
 * 处理需要强一致性保障的核心业务
 * 使用Seata分布式事务保证跨服务数据一致性
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CoreTransactionService {

    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
    private final InventoryDomainService inventoryDomainService;
    private final PaymentServiceClient paymentServiceClient;

    /**
     * 核心事务处理：库存锁定、资金预冻结、订单核心创建
     * 使用Seata保证强一致性，任何一步失败都会回滚
     */
    @GlobalTransactional(name = "order-create-core-tx", rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    public Order executeCore(Long userId,
                             List<OrderItem> orderItems,
                             OrderAddress orderAddress,
                             OrderCreateRequest request) {

        log.info("Executing core transaction for user: {}", userId);

        try {
            // 1. 创建订单聚合根（仅核心信息）
            Order order = orderFactory.createOrder(
                    userId,
                    orderItems,
                    orderAddress,
                    request.getPaymentType(),
                    request.getRemark()
            );

            // 2. 锁定库存（分布式事务保证）
            lockInventoryForOrder(order);

            // 3. 资金预冻结（如果是预付费模式）
            freezeFundsIfNeeded(order, request);

            // 4. 保存订单核心信息
            order = orderRepository.save(order);

            log.info("Core transaction completed successfully for order: {}", order.getOrderNo());
            return order;

        } catch (Exception e) {
            log.error("Core transaction failed for user: {}", userId, e);
            throw e;
        }
    }

    /**
     * 为订单锁定库存
     */
    private void lockInventoryForOrder(Order order) {
        log.debug("Locking inventory for order: {}", order.getOrderNo());

        for (OrderItem item : order.getOrderItems()) {
            inventoryDomainService.lockStock(
                    item.getSkuId(),
                    item.getQuantity(),
                    order.getOrderNo()
            );
        }
    }

    /**
     * 资金预冻结（如果需要）
     */
    private void freezeFundsIfNeeded(Order order, OrderCreateRequest request) {
        // 对于某些支付类型（如钱包支付），需要预冻结资金
        if (needFreezeUserFunds(request.getPaymentType())) {
            log.debug("Freezing funds for order: {}, amount: {}",
                    order.getOrderNo(), order.getTotalAmount());

            // 调用支付服务冻结用户资金
            paymentServiceClient.createPayment(
                    order.getOrderNo().getValue(),
                    order.getTotalAmount().getAmount(),
                    request.getPaymentType()
            );
        }
    }

    /**
     * 判断是否需要预冻结资金
     */
    private boolean needFreezeUserFunds(Integer paymentType) {
        // 钱包支付等需要预冻结资金
        return paymentType == 3; // 假设3代表钱包支付
    }
}