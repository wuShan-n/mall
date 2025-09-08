package com.mall.order.application.validation;

import com.mall.api.order.dto.request.OrderCreateRequest;
import com.mall.api.order.dto.request.OrderItemRequest;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.order.application.facade.OrderExternalServiceFacade;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 订单验证服务
 * 统一处理订单相关的业务验证逻辑
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderValidationService {
    
    private final OrderExternalServiceFacade externalServiceFacade;
    
    /**
     * 验证订单创建请求
     */
    public OrderCreationValidationResult validateOrderCreation(Long userId, OrderCreateRequest request) {
        log.debug("Validating order creation for user: {}", userId);
        
        // 1. 验证用户信息
        UserVO userInfo = validateUser(userId);
        
        // 2. 验证收货地址
        AddressVO address = validateAddress(request.getAddressId());
        
        // 3. 验证订单项
        List<SkuVO> skuList = validateOrderItems(request.getOrderItems());
        
        // 4. 验证库存
        validateStock(request.getOrderItems(), skuList);
        
        return new OrderCreationValidationResult(userInfo, address, skuList);
    }
    
    /**
     * 验证用户是否可以操作订单
     */
    public void validateUserOrderOwnership(Order order, Long userId) {
        if (!order.getUserId().getValue().equals(userId)) {
            throw new IllegalArgumentException("无权操作此订单");
        }
    }
    
    /**
     * 验证订单是否可以取消
     */
    public void validateOrderCanBeCancelled(Order order) {
        if (!order.getStatus().canCancel()) {
            throw new IllegalStateException("订单当前状态不允许取消");
        }
    }
    
    /**
     * 验证订单是否可以发货
     */
    public void validateOrderCanBeShipped(Order order) {
        if (!order.getStatus().canShip()) {
            throw new IllegalStateException("订单当前状态不允许发货");
        }
    }
    
    /**
     * 验证订单是否可以确认收货
     */
    public void validateOrderCanBeReceived(Order order) {
        if (!order.getStatus().canReceive()) {
            throw new IllegalStateException("订单当前状态不允许确认收货");
        }
    }
    
    /**
     * 验证用户信息
     */
    private UserVO validateUser(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        
        UserVO userInfo = externalServiceFacade.getUserInfo(userId);
        if (userInfo == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        
        if (userInfo.getStatus() != 0) {  // 0-Normal, 1-Disabled
            throw new IllegalArgumentException("用户状态异常，无法下单");
        }
        
        return userInfo;
    }
    
    /**
     * 验证收货地址
     */
    private AddressVO validateAddress(Long addressId) {
        if (addressId == null || addressId <= 0) {
            throw new IllegalArgumentException("收货地址ID不能为空");
        }
        
        AddressVO address = externalServiceFacade.getUserAddress(addressId);
        if (address == null) {
            throw new IllegalArgumentException("收货地址不存在");
        }
        
        return address;
    }
    
    /**
     * 验证订单项
     */
    private List<SkuVO> validateOrderItems(List<OrderItemRequest> orderItems) {
        if (orderItems == null || orderItems.isEmpty()) {
            throw new IllegalArgumentException("订单项不能为空");
        }
        
        // 提取所有skuId
        List<Long> skuIds = orderItems.stream()
                .map(OrderItemRequest::getSkuId)
                .collect(Collectors.toList());
        
        // 批量获取SKU信息
        List<SkuVO> skuList = externalServiceFacade.getSkuInfoBatch(skuIds);
        
        // 构建SKU映射表
        Map<Long, SkuVO> skuMap = skuList.stream()
                .collect(Collectors.toMap(SkuVO::getId, Function.identity()));
        
        // 验证每个订单项
        for (OrderItemRequest item : orderItems) {
            validateOrderItem(item, skuMap.get(item.getSkuId()));
        }
        
        return skuList;
    }
    
    /**
     * 验证单个订单项
     */
    private void validateOrderItem(OrderItemRequest item, SkuVO sku) {
        if (item.getSkuId() == null || item.getSkuId() <= 0) {
            throw new IllegalArgumentException("商品SKU ID不能为空");
        }
        
        if (item.getQuantity() == null || item.getQuantity() <= 0) {
            throw new IllegalArgumentException("商品数量必须大于0");
        }
        
        if (sku == null) {
            throw new IllegalArgumentException("商品不存在: " + item.getSkuId());
        }
        
        if (!"ON_SALE".equals(sku.getStatus())) {
            throw new IllegalArgumentException("商品已下架: " + sku.getSkuName());
        }
        
        if (sku.getPrice() == null || sku.getPrice().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("商品价格异常: " + sku.getSkuName());
        }
    }
    
    /**
     * 验证库存
     */
    private void validateStock(List<OrderItemRequest> orderItems, List<SkuVO> skuList) {
        Map<Long, SkuVO> skuMap = skuList.stream()
                .collect(Collectors.toMap(SkuVO::getId, Function.identity()));
        
        for (OrderItemRequest item : orderItems) {
            boolean hasStock = externalServiceFacade.checkStock(item.getSkuId(), item.getQuantity());
            if (!hasStock) {
                SkuVO sku = skuMap.get(item.getSkuId());
                throw new IllegalStateException("商品库存不足: " + (sku != null ? sku.getSkuName() : item.getSkuId()));
            }
        }
    }
    
    /**
     * 订单创建验证结果
     */
    public static class OrderCreationValidationResult {
        private final UserVO userInfo;
        private final AddressVO address;
        private final List<SkuVO> skuList;
        
        public OrderCreationValidationResult(UserVO userInfo, AddressVO address, List<SkuVO> skuList) {
            this.userInfo = userInfo;
            this.address = address;
            this.skuList = skuList;
        }
        
        public UserVO getUserInfo() {
            return userInfo;
        }
        
        public AddressVO getAddress() {
            return address;
        }
        
        public List<SkuVO> getSkuList() {
            return skuList;
        }
        
        public Map<Long, SkuVO> getSkuMap() {
            return skuList.stream()
                    .collect(Collectors.toMap(SkuVO::getId, Function.identity()));
        }
    }
}