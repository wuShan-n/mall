package com.mall.order.domain.refund.entity;

import com.mall.order.domain.order.valueobject.Money;
import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.domain.order.valueobject.UserId;
import com.mall.order.domain.refund.valueobject.RefundNo;
import com.mall.order.domain.refund.valueobject.RefundStatus;
import com.mall.order.domain.refund.valueobject.RefundType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 退款聚合根
 */
@Getter
@Setter
@Builder
public class Refund {
    
    private Long id;
    private RefundNo refundNo;
    private OrderNo orderNo;
    private Long orderId;
    private UserId userId;
    
    // 退款信息
    private RefundType refundType;
    private Money refundAmount;
    private String refundReason;
    private String description;
    private List<String> proofImages;
    
    // 状态信息
    private RefundStatus status;
    private String handleNote;
    private String handleMan;
    private LocalDateTime handleTime;
    
    // 时间信息
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime refundTime;
    
    // 领域事件
    private List<Object> domainEvents;
    
    /**
     * 创建退款
     */
    public static Refund create(UserId userId, OrderNo orderNo, RefundType refundType,
                                Money refundAmount, String refundReason, String description) {
        
        Refund refund = Refund.builder()
            .refundNo(RefundNo.generate())
            .orderNo(orderNo)
            .userId(userId)
            .refundType(refundType)
            .refundAmount(refundAmount)
            .refundReason(refundReason)
            .description(description)
            .status(RefundStatus.PENDING)
            .createTime(LocalDateTime.now())
            .domainEvents(new ArrayList<>())
            .build();
        
        // 发布退款创建事件
        // refund.addDomainEvent(new RefundCreatedEvent(refund));
        
        return refund;
    }
    
    /**
     * 审批通过
     */
    public void approve(String handleNote, String handleMan) {
        if (this.status != RefundStatus.PENDING && this.status != RefundStatus.PROCESSING) {
            throw new IllegalStateException("退款状态不允许审批: " + this.status);
        }
        
        this.status = RefundStatus.APPROVED;
        this.handleNote = handleNote;
        this.handleMan = handleMan;
        this.handleTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        
        // 发布退款审批通过事件
        // this.addDomainEvent(new RefundApprovedEvent(this));
    }
    
    /**
     * 拒绝退款
     */
    public void reject(String handleNote, String handleMan) {
        if (this.status != RefundStatus.PENDING && this.status != RefundStatus.PROCESSING) {
            throw new IllegalStateException("退款状态不允许拒绝: " + this.status);
        }
        
        this.status = RefundStatus.REJECTED;
        this.handleNote = handleNote;
        this.handleMan = handleMan;
        this.handleTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        
        // 发布退款拒绝事件
        // this.addDomainEvent(new RefundRejectedEvent(this));
    }
    
    /**
     * 完成退款
     */
    public void complete() {
        if (this.status != RefundStatus.APPROVED) {
            throw new IllegalStateException("退款状态不允许完成: " + this.status);
        }
        
        this.status = RefundStatus.REFUNDED;
        this.refundTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        
        // 发布退款完成事件
        // this.addDomainEvent(new RefundCompletedEvent(this));
    }
    
    /**
     * 取消退款
     */
    public void cancel() {
        if (this.status != RefundStatus.PENDING) {
            throw new IllegalStateException("退款状态不允许取消: " + this.status);
        }
        
        this.status = RefundStatus.CANCELLED;
        this.updateTime = LocalDateTime.now();
        
        // 发布退款取消事件
        // this.addDomainEvent(new RefundCancelledEvent(this));
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