package com.mall.order.domain.order.service;

import com.mall.order.application.dto.StockLockItem;
import com.mall.order.domain.order.entity.Order;
import com.mall.order.domain.order.entity.OrderItem;
import com.mall.order.domain.order.repository.OrderRepository;
import com.mall.order.domain.order.valueobject.Money;
import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.infrastructure.external.InventoryServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单领域服务
 * DDD核心：处理跨聚合的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class OrderDomainService {
    
    private final OrderRepository orderRepository;
    private final InventoryDomainService inventoryDomainService;
    private final InventoryServiceClient inventoryServiceClient;
    
    /**
     * 计算订单金额
     */
    public OrderAmountCalculation calculateOrderAmount(List<OrderItem> items, 
                                                       Money freightAmount,
                                                       Money couponDiscount,
                                                       Money pointsDiscount) {
        // 计算商品总金额
        BigDecimal productAmount = items.stream()
            .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        Money productMoney = new Money(productAmount);
        
        // 计算总金额 = 商品金额 + 运费
        Money totalAmount = productMoney.add(freightAmount);
        
        // 计算优惠总额
        Money totalDiscount = couponDiscount.add(pointsDiscount);
        
        // 计算应付金额 = 总金额 - 优惠金额
        Money payAmount = totalAmount.subtract(totalDiscount);
        
        return new OrderAmountCalculation(productMoney, freightAmount, 
                                         totalDiscount, totalAmount, payAmount);
    }
    
    /**
     * 锁定库存（批量优化）
     */
    public void lockStock(OrderNo orderNo, List<OrderItem> items) {
        // 构建批量锁定请求
        List<StockLockItem> stockLockItems = items.stream()
                .map(item -> StockLockItem.of(item.getSkuId(), item.getQuantity()))
                .collect(Collectors.toList());
        
        // 批量锁定库存
        Map<Long, Boolean> lockResults = inventoryServiceClient.lockStockBatch(orderNo.getValue(), stockLockItems);
        
        // 检查锁定结果
        for (OrderItem item : items) {
            Boolean lockSuccess = lockResults.get(item.getSkuId());
            if (lockSuccess == null || !lockSuccess) {
                throw new IllegalStateException("库存锁定失败: SKU " + item.getSkuId());
            }
        }
    }
    
    /**
     * 释放库存
     */
    public void releaseStock(OrderNo orderNo) {
        inventoryDomainService.releaseStock(orderNo);
    }
    
    /**
     * 扣减库存
     */
    public void deductStock(OrderNo orderNo) {
        inventoryDomainService.deductStock(orderNo);
    }
    
    /**
     * 验证订单是否可以取消
     */
    public boolean canCancelOrder(Order order) {
        return order.getStatus().canCancel();
    }
    
    /**
     * 验证订单是否可以退款
     */
    public boolean canRefundOrder(Order order) {
        return order.getStatus().canRefund();
    }
    
    /**
     * 订单金额计算结果
     */
    public static class OrderAmountCalculation {
        public final Money productAmount;
        public final Money freightAmount;
        public final Money discountAmount;
        public final Money totalAmount;
        public final Money payAmount;
        
        public OrderAmountCalculation(Money productAmount, Money freightAmount,
                                     Money discountAmount, Money totalAmount, Money payAmount) {
            this.productAmount = productAmount;
            this.freightAmount = freightAmount;
            this.discountAmount = discountAmount;
            this.totalAmount = totalAmount;
            this.payAmount = payAmount;
        }
    }
}