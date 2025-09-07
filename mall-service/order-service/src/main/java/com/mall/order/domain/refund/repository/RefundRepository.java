package com.mall.order.domain.refund.repository;

import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.domain.order.valueobject.UserId;
import com.mall.order.domain.refund.entity.Refund;
import com.mall.order.domain.refund.valueobject.RefundNo;
import com.mall.order.domain.refund.valueobject.RefundStatus;

import java.util.List;
import java.util.Optional;

/**
 * 退款仓储接口
 */
public interface RefundRepository {
    
    /**
     * 保存退款
     */
    Refund save(Refund refund);
    
    /**
     * 更新退款
     */
    Refund update(Refund refund);
    
    /**
     * 根据退款号查找退款
     */
    Optional<Refund> findByRefundNo(RefundNo refundNo);
    
    /**
     * 根据ID查找退款
     */
    Optional<Refund> findById(Long id);
    
    /**
     * 根据订单号查找退款
     */
    List<Refund> findByOrderNo(OrderNo orderNo);
    
    /**
     * 根据用户ID查找退款
     */
    List<Refund> findByUserId(UserId userId);
    
    /**
     * 根据用户ID和状态查找退款
     */
    List<Refund> findByUserIdAndStatus(UserId userId, RefundStatus status);
    
    /**
     * 检查订单是否有进行中的退款
     */
    boolean hasActiveRefund(OrderNo orderNo);
}