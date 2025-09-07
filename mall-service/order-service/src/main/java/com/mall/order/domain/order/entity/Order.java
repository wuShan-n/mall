package com.mall.order.domain.order.entity;


import com.mall.order.domain.order.valueobject.*;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单聚合根
 * DDD核心：聚合根负责维护自身的业务规则和不变性约束
 */
@Data
@Builder
public class Order {
    
    private Long id;
    private OrderNo orderNo;
    private UserId userId;
    private OrderStatus status;
    
    // 金额信息
    private Money totalAmount;
    private Money productAmount;
    private Money freightAmount;
    private Money discountAmount;
    private Money payAmount;
    
    // 支付信息
    private PaymentType paymentType;
    private LocalDateTime paymentTime;
    private String paymentNo;
    
    // 收货地址（值对象）
    private OrderAddress address;
    
    // 订单项（实体集合）
    private List<OrderItem> orderItems;
    
    // 领域事件
    private List<Object> domainEvents;
    
    // 其他信息
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    /**
     * 创建订单
     */
    public static Order create(UserId userId, List<OrderItem> items, OrderAddress address, PaymentType paymentType) {
        // 业务规则验证
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("订单项不能为空");
        }
        
        // 计算金额
        BigDecimal productAmount = items.stream()
            .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        Order order = Order.builder()
            .orderNo(OrderNo.generate())
            .userId(userId)
            .status(OrderStatus.PENDING_PAYMENT)
            .orderItems(new ArrayList<>(items))
            .address(address)
            .paymentType(paymentType)
            .productAmount(new Money(productAmount))
            .totalAmount(new Money(productAmount))
            .payAmount(new Money(productAmount))
            .freightAmount(new Money(BigDecimal.ZERO))
            .discountAmount(new Money(BigDecimal.ZERO))
            .domainEvents(new ArrayList<>())
            .createTime(LocalDateTime.now())
            .build();
        
        // 发布领域事件
//        order.addDomainEvent(new OrderCreatedEvent(order));
        
        return order;
    }
    
    /**
     * 支付订单
     */
    public void pay(String paymentNo) {
        // 状态检查
        if (!this.status.canPay()) {
            throw new IllegalStateException("订单状态不允许支付: " + this.status);
        }
        
        this.paymentNo = paymentNo;
        this.paymentTime = LocalDateTime.now();
        this.status = OrderStatus.PENDING_DELIVERY;
        this.updateTime = LocalDateTime.now();
        
        // 发布支付成功事件
//        this.addDomainEvent(new OrderPaidEvent(this));
    }
    
    /**
     * 取消订单
     */
    public void cancel(String reason) {
        // 状态检查
        if (!this.status.canCancel()) {
            throw new IllegalStateException("订单状态不允许取消: " + this.status);
        }
        
        this.status = OrderStatus.CANCELLED;
        this.updateTime = LocalDateTime.now();
        
        // 发布订单取消事件
//        this.addDomainEvent(new OrderCancelledEvent(this, reason));
    }
    
    /**
     * 发货
     */
    public void ship(String deliveryCompany, String deliveryNo) {
        if (this.status != OrderStatus.PENDING_DELIVERY) {
            throw new IllegalStateException("订单状态不允许发货: " + this.status);
        }
        
        this.status = OrderStatus.DELIVERED;
        this.updateTime = LocalDateTime.now();
    }
    
    /**
     * 确认收货
     */
    public void confirmReceipt() {
        if (this.status != OrderStatus.DELIVERED) {
            throw new IllegalStateException("订单状态不允许确认收货: " + this.status);
        }
        
        this.status = OrderStatus.RECEIVED;
        this.updateTime = LocalDateTime.now();
    }
    
    /**
     * 完成订单
     */
    public void complete() {
        if (this.status != OrderStatus.RECEIVED) {
            throw new IllegalStateException("订单状态不允许完成: " + this.status);
        }
        
        this.status = OrderStatus.COMPLETED;
        this.updateTime = LocalDateTime.now();
    }
    
    /**
     * 应用优惠
     */
    public void applyDiscount(Money discountAmount) {
        if (discountAmount.isGreaterThan(this.productAmount)) {
            throw new IllegalArgumentException("优惠金额不能大于商品金额");
        }
        
        this.discountAmount = discountAmount;
        this.payAmount = this.totalAmount.subtract(discountAmount);
        this.updateTime = LocalDateTime.now();
    }
    
    /**
     * 添加领域事件
     */
    private void addDomainEvent(Object event) {
        if (this.domainEvents == null) {
            this.domainEvents = new ArrayList<>();
        }
        this.domainEvents.add(event);
    }
    
    /**
     * 清除领域事件
     */
    public void clearDomainEvents() {
        if (this.domainEvents != null) {
            this.domainEvents.clear();
        }
    }
}