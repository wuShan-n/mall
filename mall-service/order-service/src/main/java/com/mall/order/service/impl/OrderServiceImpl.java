package com.mall.order.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.api.inventory.feign.InventoryFeignClient;
import com.mall.api.order.constant.OrderConstants;
import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.api.order.enums.OrderStatusEnum;
import com.mall.api.order.enums.PaymentTypeEnum;
import com.mall.api.order.event.*;
import com.mall.api.payment.dto.request.PaymentCreateRequest;
import com.mall.api.payment.dto.response.PaymentResultVO;
import com.mall.api.payment.feign.PaymentFeignClient;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.product.feign.ProductFeignClient;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.api.user.feign.AddressFeignClient;
import com.mall.api.user.feign.UserFeignClient;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.common.utils.BeanUtils;
import com.mall.order.entity.Order;
import com.mall.order.entity.OrderItem;
import com.mall.order.mapper.OrderMapper;
import com.mall.order.mq.OrderEventPublisher;
import com.mall.order.service.OrderItemService;
import com.mall.order.service.OrderOperateHistoryService;
import com.mall.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderItemService orderItemService;
    private final OrderOperateHistoryService operateHistoryService;
    private final ProductFeignClient productFeignClient;
    private final InventoryFeignClient inventoryFeignClient;
    private final UserFeignClient userFeignClient;
    private final AddressFeignClient addressFeignClient;
    private final PaymentFeignClient paymentFeignClient;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedissonClient redissonClient;
    private final OrderEventPublisher orderEventPublisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderVO createOrder(Long userId, OrderCreateRequest request) {
        log.info("Creating order for user: {}, request: {}", userId, request);

        // 1. 参数校验
        validateCreateRequest(request);

        // 2. 获取用户信息
        Result<com.mall.api.user.dto.response.UserVO> userResult = userFeignClient.getUserById(userId);
        if (!userResult.isSuccess()) {
            throw new BusinessException("Failed to get user info");
        }

        // 3. 获取收货地址
        Result<AddressVO> addressResult = addressFeignClient.getAddressById(request.getAddressId());
        if (!addressResult.isSuccess()) {
            throw new BusinessException("Failed to get address info");
        }
        AddressVO address = addressResult.getData();

        // 4. 获取商品信息并校验库存
        List<OrderItem> orderItems = buildOrderItems(request.getOrderItems());

        // 5. 锁定库存
        lockStock(orderItems);

        try {
            // 6. 计算订单金额
            OrderAmountVO amount = calculateOrderAmount(orderItems, request);

            // 7. 创建订单
            Order order = new Order();
            order.setOrderNo(generateOrderNo());
            order.setUserId(userId);
            order.setUsername(userResult.getData().getUsername());

            // 设置金额信息
            order.setTotalAmount(amount.getTotalAmount());
            order.setProductAmount(amount.getProductAmount());
            order.setFreightAmount(amount.getFreightAmount());
            order.setDiscountAmount(amount.getDiscountAmount());
            order.setCouponDiscount(amount.getCouponDiscount());
            order.setPointsDiscount(amount.getPointsDiscount());
            order.setPromotionDiscount(amount.getPromotionDiscount());
            order.setPayAmount(amount.getPayAmount());

            // 设置收货信息
            order.setReceiverName(address.getReceiverName());
            order.setReceiverPhone(address.getReceiverPhone());
            order.setReceiverProvince(address.getProvince());
            order.setReceiverCity(address.getCity());
            order.setReceiverDistrict(address.getDistrict());
            order.setReceiverAddress(address.getDetailAddress());
            order.setReceiverPostalCode(address.getPostalCode());

            // 设置其他信息
            order.setStatus(OrderStatusEnum.PENDING_PAYMENT.getCode());
            order.setSourceType(request.getSourceType());
            order.setPaymentType(request.getPaymentType());
            order.setRemark(request.getRemark());
            order.setCouponId(request.getCouponId());
            order.setUsePoints(request.getUsePoints());
            order.setAutoConfirmDay(15);
            order.setExpireTime(LocalDateTime.now().plusMinutes(OrderConstants.ORDER_TIMEOUT_MINUTES));

            // 发票信息
            if (Boolean.TRUE.equals(request.getInvoiceRequired())) {
                order.setInvoiceType(1);
                order.setInvoiceHeader(request.getInvoiceTitle());
                order.setInvoiceContent("商品明细");
                order.setInvoiceReceiverPhone(address.getReceiverPhone());
            }

            // 保存订单
            this.save(order);

            // 8. 保存订单项
            for (OrderItem item : orderItems) {
                item.setOrderId(order.getId());
                item.setOrderNo(order.getOrderNo());
            }
            orderItemService.saveBatch(orderItems);

            // 9. 扣减库存
            deductStock(orderItems, order.getOrderNo());

            // 10. 扣减优惠券
            if (request.getCouponId() != null) {
                // TODO: 调用营销服务扣减优惠券
            }

            // 11. 扣减积分
            if (request.getUsePoints() != null && request.getUsePoints() > 0) {
                // TODO: 调用用户服务扣减积分
            }

            // 12. 记录操作日志
            operateHistoryService.saveOperateHistory(
                    order.getId(),
                    order.getOrderNo(),
                    "CREATE",
                    userResult.getData().getUsername(),
                    order.getStatus(),
                    "创建订单"
            );

            // 13. 发送订单创建事件
            publishOrderCreatedEvent(order, orderItems);

            // 14. 构造返回结果
            OrderVO orderVO = convertToOrderVO(order);
            orderVO.setCanPay(true);
            orderVO.setCanCancel(true);

            return orderVO;

        } catch (Exception e) {
            // 回滚库存
            rollbackStock(orderItems);
            throw new BusinessException("Failed to create order: " + e.getMessage());
        }
    }


    @Override
    public OrderSettlementVO getSettlement(Long userId, OrderSettlementRequest request) {
        log.info("Getting settlement for user: {}", userId);

        // 获取商品信息
        List<OrderItemRequest> items = request.getItems();
        List<Long> skuIds = items.stream()
                .map(OrderItemRequest::getSkuId)
                .collect(Collectors.toList());

        Result<List<SkuVO>> skuResult = productFeignClient.getSkusByIds(skuIds);
        if (!skuResult.isSuccess()) {
            throw new BusinessException("Failed to get product info");
        }

        Map<Long, SkuVO> skuMap = skuResult.getData().stream()
                .collect(Collectors.toMap(SkuVO::getId, s -> s));

        // 构建结算项
        OrderSettlementVO settlement = new OrderSettlementVO();
        List<Object> settlementItems = new ArrayList<>();
        BigDecimal productAmount = BigDecimal.ZERO;

        for (OrderItemRequest item : items) {
            SkuVO sku = skuMap.get(item.getSkuId());
            if (sku == null) {
                throw new BusinessException("Product not found: " + item.getSkuId());
            }

            // TODO: 构建结算项VO
            BigDecimal itemAmount = sku.getPrice().multiply(new BigDecimal(item.getQuantity()));
            productAmount = productAmount.add(itemAmount);
        }

        settlement.setProductAmount(productAmount);
        settlement.setFreightAmount(new BigDecimal("10.00")); // 运费计算
        settlement.setTotalAmount(productAmount.add(settlement.getFreightAmount()));
        settlement.setPayAmount(settlement.getTotalAmount());

        // 获取用户地址
        if (request.getAddressId() != null) {
            Result<AddressVO> addressResult = addressFeignClient.getAddressById(request.getAddressId());
            if (addressResult.isSuccess()) {
                settlement.setDefaultAddress(addressResult.getData());
            }
        }

        // TODO: 获取可用优惠券、用户积分等

        return settlement;
    }

    @Override
    @Transactional
    public OrderPaymentVO payOrder(OrderPaymentRequest request) {
        log.info("Processing payment for order: {}", request.getOrderNo());

        Order order = baseMapper.selectByOrderNo(request.getOrderNo());
        if (order == null) {
            throw new BusinessException("Order not found");
        }

        if (!OrderStatusEnum.PENDING_PAYMENT.getCode().equals(order.getStatus())) {
            throw new BusinessException("Order cannot be paid");
        }

        // 调用支付服务创建支付单
        PaymentCreateRequest paymentRequest = new PaymentCreateRequest();
        paymentRequest.setOrderNo(order.getOrderNo());
        paymentRequest.setUserId(order.getUserId());
        paymentRequest.setPaymentType(request.getPaymentType());
        paymentRequest.setPaymentAmount(order.getPayAmount());
        paymentRequest.setSubject("订单支付");
        paymentRequest.setDescription("订单号：" + order.getOrderNo());
        paymentRequest.setReturnUrl(request.getReturnUrl());
        paymentRequest.setNotifyUrl(request.getNotifyUrl());
        paymentRequest.setExpireTime(order.getExpireTime());

        Result<PaymentResultVO> paymentResult = paymentFeignClient.createPayment(paymentRequest);
        if (!paymentResult.isSuccess()) {
            throw new BusinessException("Failed to create payment");
        }

        // 更新订单支付信息
        order.setPaymentType(request.getPaymentType());
        this.updateById(order);

        // 记录操作日志
        operateHistoryService.saveOperateHistory(
                order.getId(),
                order.getOrderNo(),
                "PAY",
                "System",
                order.getStatus(),
                "发起支付"
        );

        // 构造返回结果
        OrderPaymentVO paymentVO = new OrderPaymentVO();
        BeanUtils.copy(paymentResult.getData(), paymentVO);
        paymentVO.setOrderNo(order.getOrderNo());
        paymentVO.setPaymentAmount(order.getPayAmount());

        return paymentVO;
    }

    @Override
    @Transactional
    public void paymentCallback(String orderNo, String transactionId) {
        log.info("Payment callback for order: {}, transaction: {}", orderNo, transactionId);

        Order order = baseMapper.selectByOrderNo(orderNo);
        if (order == null) {
            throw new BusinessException("Order not found");
        }

        if (!OrderStatusEnum.PENDING_PAYMENT.getCode().equals(order.getStatus())) {
            log.warn("Order {} status is not pending payment", orderNo);
            return;
        }

        // 更新订单状态为已支付待发货
        order.setStatus(OrderStatusEnum.PENDING_DELIVERY.getCode());
        order.setPaymentTime(LocalDateTime.now());
        order.setPaymentNo(transactionId);
        this.updateById(order);

        // 记录操作日志
        operateHistoryService.saveOperateHistory(
                order.getId(),
                order.getOrderNo(),
                "PAID",
                "System",
                order.getStatus(),
                "支付成功，交易号：" + transactionId
        );

        // 发送订单支付成功事件
        publishOrderPaidEvent(order);
    }

    @Override
    @Transactional
    public void cancelOrder(Long userId, OrderCancelRequest request) {
        log.info("Cancelling order: {} for user: {}", request.getOrderNo(), userId);

        Order order = baseMapper.selectByOrderNo(request.getOrderNo());
        if (order == null) {
            throw new BusinessException("Order not found");
        }

        if (!order.getUserId().equals(userId)) {
            throw new BusinessException("No permission to cancel this order");
        }

        OrderStatusEnum status = OrderStatusEnum.of(order.getStatus());
        if (!status.canCancel()) {
            throw new BusinessException("Order cannot be cancelled in current status");
        }

        // 更新订单状态
        order.setStatus(OrderStatusEnum.CANCELLED.getCode());
        this.updateById(order);

        // 释放库存
        List<OrderItem> orderItems = orderItemService.getByOrderId(order.getId());
        rollbackStock(orderItems);

        // 退还优惠券
        if (order.getCouponId() != null) {
            // TODO: 调用营销服务退还优惠券
        }

        // 退还积分
        if (order.getUsePoints() != null && order.getUsePoints() > 0) {
            // TODO: 调用用户服务退还积分
        }

        // 记录操作日志
        operateHistoryService.saveOperateHistory(
                order.getId(),
                order.getOrderNo(),
                "CANCEL",
                "User",
                order.getStatus(),
                "取消原因：" + request.getReason()
        );

        // 发送订单取消事件
        publishOrderCancelledEvent(order, orderItems, request.getReason());
    }

    @Override
    @Transactional
    public void shipOrder(OrderShipRequest request) {
        log.info("Shipping order: {}", request.getOrderNo());

        Order order = baseMapper.selectByOrderNo(request.getOrderNo());
        if (order == null) {
            throw new BusinessException("Order not found");
        }

        if (!OrderStatusEnum.PENDING_DELIVERY.getCode().equals(order.getStatus())) {
            throw new BusinessException("Order cannot be shipped in current status");
        }

        // 更新订单状态和物流信息
        order.setStatus(OrderStatusEnum.DELIVERED.getCode());
        order.setDeliveryCompany(request.getDeliveryCompany());
        order.setDeliveryNo(request.getDeliveryNo());
        order.setDeliveryTime(LocalDateTime.now());
        this.updateById(order);

        // 记录操作日志
        operateHistoryService.saveOperateHistory(
                order.getId(),
                order.getOrderNo(),
                "SHIP",
                "Admin",
                order.getStatus(),
                String.format("发货，物流公司：%s，物流单号：%s",
                        request.getDeliveryCompany(), request.getDeliveryNo())
        );

        // 发送订单发货事件
        publishOrderShippedEvent(order);
    }

    @Override
    @Transactional
    public void confirmReceipt(Long userId, String orderNo) {
        log.info("Confirming receipt for order: {} by user: {}", orderNo, userId);

        Order order = baseMapper.selectByOrderNo(orderNo);
        if (order == null) {
            throw new BusinessException("Order not found");
        }

        if (!order.getUserId().equals(userId)) {
            throw new BusinessException("No permission to confirm this order");
        }

        if (!OrderStatusEnum.DELIVERED.getCode().equals(order.getStatus())) {
            throw new BusinessException("Order cannot be confirmed in current status");
        }

        // 更新订单状态
        order.setStatus(OrderStatusEnum.RECEIVED.getCode());
        order.setReceiveTime(LocalDateTime.now());
        order.setConfirmStatus(1);
        this.updateById(order);

        // 记录操作日志
        operateHistoryService.saveOperateHistory(
                order.getId(),
                order.getOrderNo(),
                "CONFIRM",
                "User",
                order.getStatus(),
                "确认收货"
        );

        // 7天后自动完成订单
        // TODO: 发送延迟消息
    }

    @Override
    @Transactional
    public void completeOrder(String orderNo) {
        log.info("Completing order: {}", orderNo);

        Order order = baseMapper.selectByOrderNo(orderNo);
        if (order == null) {
            throw new BusinessException("Order not found");
        }

        if (!OrderStatusEnum.RECEIVED.getCode().equals(order.getStatus())) {
            throw new BusinessException("Order cannot be completed in current status");
        }

        // 更新订单状态
        order.setStatus(OrderStatusEnum.COMPLETED.getCode());
        this.updateById(order);

        // 赠送积分和成长值
        List<OrderItem> orderItems = orderItemService.getByOrderId(order.getId());
        int totalPoints = orderItems.stream()
                .mapToInt(OrderItem::getGiftPoint)
                .sum();
        int totalGrowth = orderItems.stream()
                .mapToInt(OrderItem::getGiftGrowth)
                .sum();

        // TODO: 调用用户服务增加积分和成长值

        // 记录操作日志
        operateHistoryService.saveOperateHistory(
                order.getId(),
                order.getOrderNo(),
                "COMPLETE",
                "System",
                order.getStatus(),
                "订单完成"
        );

        // 发送订单完成事件
        publishOrderCompletedEvent(order, totalPoints, totalGrowth);
    }

    @Override
    public void deleteOrder(Long userId, String orderNo) {
        log.info("Deleting order: {} for user: {}", orderNo, userId);

        Order order = baseMapper.selectByOrderNo(orderNo);
        if (order == null) {
            throw new BusinessException("Order not found");
        }

        if (!order.getUserId().equals(userId)) {
            throw new BusinessException("No permission to delete this order");
        }

        OrderStatusEnum status = OrderStatusEnum.of(order.getStatus());
        if (status != OrderStatusEnum.COMPLETED && status != OrderStatusEnum.CANCELLED) {
            throw new BusinessException("Order cannot be deleted in current status");
        }

        // 逻辑删除
        order.setDeleteStatus(1);
        this.updateById(order);
    }

    @Override
    public OrderVO getOrderByNo(String orderNo) {
        Order order = baseMapper.selectByOrderNo(orderNo);
        if (order == null) {
            return null;
        }
        return convertToOrderVO(order);
    }

    @Override
    public OrderDetailVO getOrderDetail(String orderNo) {
        Order order = baseMapper.selectByOrderNo(orderNo);
        if (order == null) {
            throw new BusinessException("Order not found");
        }

        OrderDetailVO detail = new OrderDetailVO();
        BeanUtils.copy(convertToOrderVO(order), detail);

        // 获取订单项
        List<OrderItem> items = orderItemService.getByOrderNo(orderNo);
        detail.setOrderItems(convertToOrderItemVOs(items));

        // 获取操作记录构建时间线
        detail.setTimeline(buildOrderTimeline(order));

        return detail;
    }

    @Override
    public PageResult<OrderVO> queryOrders(OrderQueryRequest request) {
        Page<Order> page = new Page<>(request.getCurrent(), request.getSize());

        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(request.getOrderNo()), Order::getOrderNo, request.getOrderNo())
                .eq(request.getUserId() != null, Order::getUserId, request.getUserId())
                .eq(request.getStatus() != null, Order::getStatus, request.getStatus())
                .eq(request.getPaymentType() != null, Order::getPaymentType, request.getPaymentType())
                .like(StrUtil.isNotBlank(request.getReceiverName()), Order::getReceiverName, request.getReceiverName())
                .like(StrUtil.isNotBlank(request.getReceiverPhone()), Order::getReceiverPhone, request.getReceiverPhone())
                .between(request.getStartTime() != null && request.getEndTime() != null,
                        Order::getCreateTime, request.getStartTime(), request.getEndTime())
                .orderByDesc(Order::getCreateTime);

        Page<Order> orderPage = this.page(page, wrapper);

        List<OrderVO> orderVOs = orderPage.getRecords().stream()
                .map(this::convertToOrderVO)
                .collect(Collectors.toList());

        return PageResult.of(orderPage.getCurrent(), orderPage.getSize(),
                orderPage.getTotal(), orderVOs);
    }

    @Override
    public PageResult<OrderVO> getUserOrders(Long userId, Integer status, Long current, Long size) {
        Page<Order> page = new Page<>(current, size);

        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId, userId)
                .eq(Order::getDeleteStatus, 0)
                .eq(status != null, Order::getStatus, status)
                .orderByDesc(Order::getCreateTime);

        Page<Order> orderPage = this.page(page, wrapper);

        List<OrderVO> orderVOs = orderPage.getRecords().stream()
                .map(this::convertToOrderVO)
                .collect(Collectors.toList());

        return PageResult.of(orderPage.getCurrent(), orderPage.getSize(),
                orderPage.getTotal(), orderVOs);
    }

    @Override
    public Map<Integer, Long> getOrderCountByStatus(Long userId) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId, userId)
                .eq(Order::getDeleteStatus, 0);

        List<Order> orders = this.list(wrapper);

        return orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));
    }

    @Override
    public List<OrderItemVO> getOrderItems(String orderNo) {
        List<OrderItem> items = orderItemService.getByOrderNo(orderNo);
        return convertToOrderItemVOs(items);
    }

    @Override
    @Transactional
    public void checkOrderTimeout() {
        log.info("Checking order timeout...");

        List<Order> timeoutOrders = baseMapper.selectTimeoutOrders();

        for (Order order : timeoutOrders) {
            log.info("Order {} timeout, cancelling...", order.getOrderNo());

            OrderCancelRequest request = new OrderCancelRequest();
            request.setOrderNo(order.getOrderNo());
            request.setReason("订单超时未支付");

            try {
                cancelOrder(order.getUserId(), request);
            } catch (Exception e) {
                log.error("Failed to cancel timeout order: {}", order.getOrderNo(), e);
            }
        }
    }

    @Override
    @Transactional
    public void autoConfirmReceipt() {
        log.info("Auto confirming receipt...");

        List<Order> orders = baseMapper.selectAutoConfirmOrders();

        for (Order order : orders) {
            log.info("Auto confirming order: {}", order.getOrderNo());

            try {
                confirmReceipt(order.getUserId(), order.getOrderNo());
            } catch (Exception e) {
                log.error("Failed to auto confirm order: {}", order.getOrderNo(), e);
            }
        }
    }

    @Override
    @Transactional
    public void autoCompleteOrder() {
        log.info("Auto completing orders...");

        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getStatus, OrderStatusEnum.RECEIVED.getCode())
                .le(Order::getReceiveTime, LocalDateTime.now().minusDays(7));

        List<Order> orders = this.list(wrapper);

        for (Order order : orders) {
            log.info("Auto completing order: {}", order.getOrderNo());

            try {
                completeOrder(order.getOrderNo());
            } catch (Exception e) {
                log.error("Failed to auto complete order: {}", order.getOrderNo(), e);
            }
        }
    }

    @Override
    public OrderStatisticsVO getOrderStatistics() {
        // TODO: 实现订单统计
        return new OrderStatisticsVO();
    }

    @Override
    public OrderStatisticsVO getUserOrderStatistics(Long userId) {
        // TODO: 实现用户订单统计
        return new OrderStatisticsVO();
    }

// ==================== 私有辅助方法 ====================

    private void validateCreateRequest(OrderCreateRequest request) {
        if (request.getOrderItems() == null || request.getOrderItems().isEmpty()) {
            throw new BusinessException("Order items cannot be empty");
        }
        if (request.getAddressId() == null) {
            throw new BusinessException("Address cannot be empty");
        }
    }

    private String generateOrderNo() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = String.valueOf(System.nanoTime()).substring(7, 13);
        return OrderConstants.ORDER_NO_PREFIX + date + random;
    }

    private List<OrderItem> buildOrderItems(List<OrderItemRequest> requests) {
        List<Long> skuIds = requests.stream()
                .map(OrderItemRequest::getSkuId)
                .collect(Collectors.toList());

        Result<List<SkuVO>> skuResult = productFeignClient.getSkusByIds(skuIds);
        if (!skuResult.isSuccess()) {
            throw new BusinessException("Failed to get product info");
        }

        Map<Long, SkuVO> skuMap = skuResult.getData().stream()
                .collect(Collectors.toMap(SkuVO::getId, s -> s));

        List<OrderItem> items = new ArrayList<>();
        for (OrderItemRequest request : requests) {
            SkuVO sku = skuMap.get(request.getSkuId());
            if (sku == null) {
                throw new BusinessException("Product not found: " + request.getSkuId());
            }

            OrderItem item = new OrderItem();
            item.setSkuId(sku.getId());
            item.setSpuId(sku.getSpuId());
            item.setProductName(sku.getSkuName());
            item.setProductImage(sku.getImage());
            item.setProductSpecs(sku.getSpecs() != null ? sku.getSpecs().toString() : "");
            item.setProductCode(sku.getSkuCode());
            item.setPrice(sku.getPrice());
            item.setQuantity(request.getQuantity());

            BigDecimal totalAmount = sku.getPrice().multiply(new BigDecimal(request.getQuantity()));
            item.setTotalAmount(totalAmount);
            item.setRealAmount(totalAmount);

            items.add(item);
        }

        return items;
    }

    private void lockStock(List<OrderItem> items) {
//        for (OrderItem item : items) {
//            Result<Void> result = inventoryFeignClient.lockStock();
//            if (!result.isSuccess()) {
//                throw new BusinessException("Failed to lock stock for SKU: " + item.getSkuId());
//            }
//        }
    }

    private void deductStock(List<OrderItem> items, String orderNo) {
//        for (OrderItem item : items) {
//            Result<Void> result = inventoryFeignClient.deductStock(
//                    item.getSkuId(),
//                    item.getQuantity(),
//                    orderNo
//            );
//            if (!result.isSuccess()) {
//                throw new BusinessException("Failed to deduct stock for SKU: " + item.getSkuId());
//            }
//        }
    }

    private void rollbackStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            try {
//                inventoryFeignClient.unlockStock();
            } catch (Exception e) {
                log.error("Failed to rollback stock for SKU: {}", item.getSkuId(), e);
            }
        }
    }

    private OrderAmountVO calculateOrderAmount(List<OrderItem> items, OrderCreateRequest request) {
        OrderAmountVO amount = new OrderAmountVO();

        // 计算商品总额
        BigDecimal productAmount = items.stream()
                .map(OrderItem::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        amount.setProductAmount(productAmount);

        // 运费（简化处理）
        amount.setFreightAmount(new BigDecimal("10.00"));

        // 优惠券抵扣
        if (request.getCouponId() != null) {
            // TODO: 调用营销服务计算优惠券抵扣
            amount.setCouponDiscount(new BigDecimal("0.00"));
        } else {
            amount.setCouponDiscount(BigDecimal.ZERO);
        }

        // 积分抵扣
        if (request.getUsePoints() != null && request.getUsePoints() > 0) {
            // 假设100积分抵扣1元
            BigDecimal pointsDiscount = new BigDecimal(request.getUsePoints()).divide(new BigDecimal(100));
            amount.setPointsDiscount(pointsDiscount);
        } else {
            amount.setPointsDiscount(BigDecimal.ZERO);
        }

        // 总优惠
        amount.setDiscountAmount(amount.getCouponDiscount().add(amount.getPointsDiscount()));

        // 总金额
        amount.setTotalAmount(productAmount.add(amount.getFreightAmount()));

        // 应付金额
        amount.setPayAmount(amount.getTotalAmount().subtract(amount.getDiscountAmount()));

        return amount;
    }

    private OrderVO convertToOrderVO(Order order) {
        OrderVO vo = new OrderVO();
        BeanUtils.copy(order, vo);

        // 设置状态名称
        OrderStatusEnum status = OrderStatusEnum.of(order.getStatus());
        if (status != null) {
            vo.setStatusName(status.getDesc());
            vo.setCanCancel(status.canCancel());
            vo.setCanPay(status.canPay());
            vo.setCanRefund(status.canRefund());
            vo.setCanReceive(status.canReceive());
        }

        // 设置支付类型名称
        PaymentTypeEnum paymentType = PaymentTypeEnum.of(order.getPaymentType());
        if (paymentType != null) {
            vo.setPayTypeName(paymentType.getDesc());
        }

        // 获取订单项数量
        List<OrderItem> items = orderItemService.getByOrderId(order.getId());
        vo.setItemCount(items.size());

        return vo;
    }

    private List<OrderItemVO> convertToOrderItemVOs(List<OrderItem> items) {
        return items.stream().map(item -> {
            OrderItemVO vo = new OrderItemVO();
            BeanUtils.copy(item, vo);
            return vo;
        }).collect(Collectors.toList());
    }

    private List<OrderTimelineVO> buildOrderTimeline(Order order) {
        List<OrderTimelineVO> timeline = new ArrayList<>();

        // 创建订单
        OrderTimelineVO create = new OrderTimelineVO();
        create.setTitle("订单创建");
        create.setDescription("您的订单已创建成功");
        create.setTime(order.getCreateTime());
        create.setStatus("completed");
        timeline.add(create);

        // 支付
        if (order.getPaymentTime() != null) {
            OrderTimelineVO pay = new OrderTimelineVO();
            pay.setTitle("订单支付");
            pay.setDescription("您的订单已支付成功");
            pay.setTime(order.getPaymentTime());
            pay.setStatus("completed");
            timeline.add(pay);
        }

        // 发货
        if (order.getDeliveryTime() != null) {
            OrderTimelineVO ship = new OrderTimelineVO();
            ship.setTitle("订单发货");
            ship.setDescription(String.format("您的订单已发货，%s：%s",
                    order.getDeliveryCompany(), order.getDeliveryNo()));
            ship.setTime(order.getDeliveryTime());
            ship.setStatus("completed");
            timeline.add(ship);
        }

        // 收货
        if (order.getReceiveTime() != null) {
            OrderTimelineVO receive = new OrderTimelineVO();
            receive.setTitle("确认收货");
            receive.setDescription("您已确认收货");
            receive.setTime(order.getReceiveTime());
            receive.setStatus("completed");
            timeline.add(receive);
        }

        return timeline;
    }

// ==================== 事件发布方法 ====================

    private void publishOrderCreatedEvent(Order order, List<OrderItem> items) {
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setOrderId(order.getId());
        event.setOrderNo(order.getOrderNo());
        event.setUserId(order.getUserId());
        event.setTotalAmount(order.getTotalAmount());
        event.setCreateTime(order.getCreateTime());
        event.setEventTime(LocalDateTime.now());

        List<OrderCreatedEvent.OrderItemInfo> itemInfos = items.stream().map(item -> {
            OrderCreatedEvent.OrderItemInfo info = new OrderCreatedEvent.OrderItemInfo();
            info.setSkuId(item.getSkuId());
            info.setProductName(item.getProductName());
            info.setQuantity(item.getQuantity());
            info.setPrice(item.getPrice());
            return info;
        }).collect(Collectors.toList());
        event.setOrderItems(itemInfos);

        orderEventPublisher.publishOrderCreatedEvent(event);
    }

    private void publishOrderPaidEvent(Order order) {
        OrderPaidEvent event = new OrderPaidEvent();
        event.setOrderId(order.getId());
        event.setOrderNo(order.getOrderNo());
        event.setUserId(order.getUserId());
        event.setPaymentAmount(order.getPayAmount());
        event.setPaymentType(order.getPaymentType());
        event.setTransactionId(order.getPaymentNo());
        event.setPaymentTime(order.getPaymentTime());
        event.setEventTime(LocalDateTime.now());

        orderEventPublisher.publishOrderPaidEvent(event);
    }

    private void publishOrderCancelledEvent(Order order, List<OrderItem> items, String reason) {
        OrderCancelledEvent event = new OrderCancelledEvent();
        event.setOrderId(order.getId());
        event.setOrderNo(order.getOrderNo());
        event.setUserId(order.getUserId());
        event.setCancelReason(reason);
        event.setCancelType(1); // 用户取消
        event.setReturnPoints(order.getUsePoints());
        event.setReturnCouponId(order.getCouponId());
        event.setCancelTime(LocalDateTime.now());
        event.setEventTime(LocalDateTime.now());

        List<OrderCancelledEvent.StockReleaseInfo> stockReleaseList = items.stream().map(item -> {
            OrderCancelledEvent.StockReleaseInfo info = new OrderCancelledEvent.StockReleaseInfo();
            info.setSkuId(item.getSkuId());
            info.setQuantity(item.getQuantity());
            return info;
        }).collect(Collectors.toList());
        event.setStockReleaseList(stockReleaseList);

        orderEventPublisher.publishOrderCancelledEvent(event);
    }

    private void publishOrderShippedEvent(Order order) {
        OrderShippedEvent event = new OrderShippedEvent();
        event.setOrderId(order.getId());
        event.setOrderNo(order.getOrderNo());
        event.setUserId(order.getUserId());
        event.setDeliveryCompany(order.getDeliveryCompany());
        event.setDeliveryNo(order.getDeliveryNo());
        event.setShipTime(order.getDeliveryTime());
        event.setEventTime(LocalDateTime.now());

        orderEventPublisher.publishOrderShippedEvent(event);
    }

    private void publishOrderCompletedEvent(Order order, int points, int growth) {
        OrderCompletedEvent event = new OrderCompletedEvent();
        event.setOrderId(order.getId());
        event.setOrderNo(order.getOrderNo());
        event.setUserId(order.getUserId());
        event.setTotalAmount(order.getTotalAmount());
        event.setAwardPoints(points);
        event.setAwardGrowth(growth);
        event.setCompleteTime(LocalDateTime.now());
        event.setEventTime(LocalDateTime.now());

        orderEventPublisher.publishOrderCompletedEvent(event);
    }
}