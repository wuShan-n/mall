package com.mall.order.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.order.domain.order.valueobject.OrderNo;
import com.mall.order.domain.order.valueobject.UserId;
import com.mall.order.domain.refund.entity.Refund;
import com.mall.order.domain.refund.repository.RefundRepository;
import com.mall.order.domain.refund.valueobject.RefundNo;
import com.mall.order.domain.refund.valueobject.RefundStatus;
import com.mall.order.infrastructure.persistence.mapper.RefundMapper;
import com.mall.order.infrastructure.persistence.po.RefundPO;
import com.mall.order.infrastructure.repository.converter.RefundConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 退款仓储实现
 */
@Repository
@RequiredArgsConstructor
public class RefundRepositoryImpl implements RefundRepository {
    
    private final RefundMapper refundMapper;
    private final RefundConverter refundConverter;
    
    @Override
    public Refund save(Refund refund) {
        RefundPO refundPO = refundConverter.toPO(refund);
        refundMapper.insert(refundPO);
        refund.setId(refundPO.getId());
        return refund;
    }
    
    @Override
    public Refund update(Refund refund) {
        RefundPO refundPO = refundConverter.toPO(refund);
        refundPO.setId(refund.getId());
        refundMapper.updateById(refundPO);
        return refund;
    }
    
    @Override
    public Optional<Refund> findByRefundNo(RefundNo refundNo) {
        LambdaQueryWrapper<RefundPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RefundPO::getRefundNo, refundNo.getValue());
        
        RefundPO refundPO = refundMapper.selectOne(wrapper);
        if (refundPO == null) {
            return Optional.empty();
        }
        
        Refund refund = refundConverter.toDomain(refundPO);
        return Optional.of(refund);
    }
    
    @Override
    public Optional<Refund> findById(Long id) {
        RefundPO refundPO = refundMapper.selectById(id);
        if (refundPO == null) {
            return Optional.empty();
        }
        
        Refund refund = refundConverter.toDomain(refundPO);
        return Optional.of(refund);
    }
    
    @Override
    public List<Refund> findByOrderNo(OrderNo orderNo) {
        LambdaQueryWrapper<RefundPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RefundPO::getOrderNo, orderNo.getValue());
        
        List<RefundPO> refundPOs = refundMapper.selectList(wrapper);
        return refundPOs.stream()
            .map(refundConverter::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Refund> findByUserId(UserId userId) {
        LambdaQueryWrapper<RefundPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RefundPO::getUserId, userId.getValue())
               .orderByDesc(RefundPO::getCreateTime);
        
        List<RefundPO> refundPOs = refundMapper.selectList(wrapper);
        return refundPOs.stream()
            .map(refundConverter::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<Refund> findByUserIdAndStatus(UserId userId, RefundStatus status) {
        LambdaQueryWrapper<RefundPO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RefundPO::getUserId, userId.getValue())
               .eq(RefundPO::getStatus, status.getCode())
               .orderByDesc(RefundPO::getCreateTime);
        
        List<RefundPO> refundPOs = refundMapper.selectList(wrapper);
        return refundPOs.stream()
            .map(refundConverter::toDomain)
            .collect(Collectors.toList());
    }
    
    @Override
    public boolean hasActiveRefund(OrderNo orderNo) {
        Integer count = refundMapper.countActiveRefund(orderNo.getValue());
        return count != null && count > 0;
    }
}