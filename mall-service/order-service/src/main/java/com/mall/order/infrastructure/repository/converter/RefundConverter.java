package com.mall.order.infrastructure.repository.converter;

import com.mall.order.domain.refund.entity.Refund;
import com.mall.order.infrastructure.persistence.po.RefundPO;
import org.springframework.stereotype.Component;

/**
 * 退款转换器
 */
@Component
public class RefundConverter {
    
    public RefundPO toPO(Refund refund) {
        // TODO: 实现转换逻辑
        return new RefundPO();
    }
    
    public Refund toDomain(RefundPO po) {
        // TODO: 实现转换逻辑
        return null;
    }
}