package com.mall.order.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.api.order.constant.OrderConstants;
import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.RefundVO;
import com.mall.api.order.enums.OrderStatusEnum;
import com.mall.api.order.enums.RefundStatusEnum;
import com.mall.api.order.event.RefundApprovedEvent;
import com.mall.api.payment.dto.request.RefundApplyRequest;
import com.mall.api.payment.dto.response.RefundResultVO;
import com.mall.api.payment.feign.RefundFeignClient;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.common.utils.BeanUtils;
import com.mall.order.entity.Order;
import com.mall.order.entity.OrderRefund;
import com.mall.order.mapper.OrderMapper;
import com.mall.order.mapper.OrderRefundMapper;
import com.mall.order.mq.OrderEventPublisher;
import com.mall.order.service.OrderOperateHistoryService;
import com.mall.order.service.RefundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 退款服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RefundServiceImpl extends ServiceImpl<OrderRefundMapper, OrderRefund> implements RefundService {
    
    private final OrderMapper orderMapper;
    private final OrderOperateHistoryService operateHistoryService;
    private final RefundFeignClient refundFeignClient;
    private final OrderEventPublisher orderEventPublisher;
    
    @Override
    @Transactional
    public RefundVO createRefund(Long userId, RefundCreateRequest request) {
        log.info("Creating refund for order: {}, user: {}", request.getOrderNo(), userId);
        
        // 1. 查询订单
        Order order = orderMapper.selectByOrderNo(request.getOrderNo());
        if (order == null) {
            throw new BusinessException("Order not found");
        }
        
        if (!order.getUserId().equals(userId)) {
            throw new BusinessException("No permission to refund this order");
        }
        
        // 2. 检查订单状态是否可退款
        OrderStatusEnum status = OrderStatusEnum.of(order.getStatus());
        if (!status.canRefund()) {
            throw new BusinessException("Order cannot be refunded in current status");
        }
        
        // 3. 创建退款申请
        OrderRefund refund = new OrderRefund();
        refund.setRefundNo(generateRefundNo());
        refund.setOrderId(order.getId());
        refund.setOrderNo(order.getOrderNo());
        refund.setUserId(userId);
        refund.setRefundType(request.getRefundType());
        refund.setRefundAmount(request.getRefundAmount());
        refund.setRefundReason(request.getRefundReason());
        refund.setDescription(request.getDescription());
        refund.setProofPics(String.join(",", request.getProofImages()));
        refund.setStatus(RefundStatusEnum.PENDING.getCode());
        
        this.save(refund);
        
        // 4. 更新订单状态为售后中
        order.setStatus(OrderStatusEnum.REFUNDING.getCode());
        orderMapper.updateById(order);
        
        // 5. 记录操作日志
        operateHistoryService.saveOperateHistory(
            order.getId(),
            order.getOrderNo(),
            "REFUND_APPLY",
            "User",
            order.getStatus(),
            "申请退款，退款金额：" + request.getRefundAmount()
        );
        
        return convertToRefundVO(refund);
    }
    
    @Override
    @Transactional
    public void cancelRefund(Long userId, String refundNo) {
        log.info("Cancelling refund: {} for user: {}", refundNo, userId);
        
        OrderRefund refund = baseMapper.selectByRefundNo(refundNo);
        if (refund == null) {
            throw new BusinessException("Refund not found");
        }
        
        if (!refund.getUserId().equals(userId)) {
            throw new BusinessException("No permission to cancel this refund");
        }
        
        if (!RefundStatusEnum.PENDING.getCode().equals(refund.getStatus())) {
            throw new BusinessException("Refund cannot be cancelled in current status");
        }
        
        // 更新退款状态
        refund.setStatus(RefundStatusEnum.CANCELLED.getCode());
        this.updateById(refund);
        
        // 恢复订单状态
        Order order = orderMapper.selectById(refund.getOrderId());
        order.setStatus(OrderStatusEnum.COMPLETED.getCode());
        orderMapper.updateById(order);
        
        // 记录操作日志
        operateHistoryService.saveOperateHistory(
            order.getId(),
            order.getOrderNo(),
            "REFUND_CANCEL",
            "User",
            order.getStatus(),
            "取消退款申请"
        );
    }
    
    @Override
    @Transactional
    public void processRefund(RefundProcessRequest request) {
        log.info("Processing refund: {}", request.getRefundNo());
        
        if (request.getAction() == 1) {
            approveRefund(request.getRefundNo(), request.getHandleNote());
        } else {
            rejectRefund(request.getRefundNo(), request.getHandleNote());
        }
    }
    
    @Override
    @Transactional
    public void approveRefund(String refundNo, String handleNote) {
        log.info("Approving refund: {}", refundNo);
        
        OrderRefund refund = baseMapper.selectByRefundNo(refundNo);
        if (refund == null) {
            throw new BusinessException("Refund not found");
        }
        
        // 更新退款状态
        refund.setStatus(RefundStatusEnum.APPROVED.getCode());
        refund.setHandleTime(LocalDateTime.now());
        refund.setHandleNote(handleNote);
        refund.setHandleMan("Admin");
        this.updateById(refund);
        
        // 调用支付服务执行退款
        RefundApplyRequest applyRequest = new RefundApplyRequest();
        applyRequest.setOrderNo(refund.getOrderNo());
        applyRequest.setRefundAmount(refund.getRefundAmount());
        applyRequest.setRefundReason(refund.getRefundReason());
        
        Result<RefundResultVO> result = refundFeignClient.applyRefund(applyRequest);
        if (!result.isSuccess()) {
            throw new BusinessException("Failed to process refund: " + result.getMessage());
        }
        
        // 记录操作日志
        operateHistoryService.saveOperateHistory(
            refund.getOrderId(),
            refund.getOrderNo(),
            "REFUND_APPROVE",
            "Admin",
            null,
            "批准退款，退款金额：" + refund.getRefundAmount()
        );
        
        // 发布退款批准事件
        publishRefundApprovedEvent(refund);
    }
    
    @Override
    @Transactional
    public void rejectRefund(String refundNo, String handleNote) {
        log.info("Rejecting refund: {}", refundNo);
        
        OrderRefund refund = baseMapper.selectByRefundNo(refundNo);
        if (refund == null) {
            throw new BusinessException("Refund not found");
        }
        
        // 更新退款状态
        refund.setStatus(RefundStatusEnum.REJECTED.getCode());
        refund.setHandleTime(LocalDateTime.now());
        refund.setHandleNote(handleNote);
        refund.setHandleMan("Admin");
        this.updateById(refund);
        
        // 恢复订单状态
        Order order = orderMapper.selectById(refund.getOrderId());
        order.setStatus(OrderStatusEnum.COMPLETED.getCode());
        orderMapper.updateById(order);
        
        // 记录操作日志
        operateHistoryService.saveOperateHistory(
            refund.getOrderId(),
            refund.getOrderNo(),
            "REFUND_REJECT",
            "Admin",
            order.getStatus(),
            "拒绝退款：" + handleNote
        );
    }
    
    @Override
    @Transactional
    public void completeRefund(String refundNo) {
        log.info("Completing refund: {}", refundNo);
        
        OrderRefund refund = baseMapper.selectByRefundNo(refundNo);
        if (refund == null) {
            throw new BusinessException("Refund not found");
        }
        
        // 更新退款状态
        refund.setStatus(RefundStatusEnum.REFUNDED.getCode());
        this.updateById(refund);
        
        // 更新订单状态
        Order order = orderMapper.selectById(refund.getOrderId());
        order.setStatus(OrderStatusEnum.REFUNDED.getCode());
        orderMapper.updateById(order);
        
        // 记录操作日志
        operateHistoryService.saveOperateHistory(
            refund.getOrderId(),
            refund.getOrderNo(),
            "REFUND_COMPLETE",
            "System",
            order.getStatus(),
            "退款完成"
        );
    }
    
    @Override
    public RefundVO getRefundByNo(String refundNo) {
        OrderRefund refund = baseMapper.selectByRefundNo(refundNo);
        if (refund == null) {
            return null;
        }
        return convertToRefundVO(refund);
    }
    
    @Override
    public RefundDetailVO getRefundDetail(String refundNo) {
        OrderRefund refund = baseMapper.selectByRefundNo(refundNo);
        if (refund == null) {
            throw new BusinessException("Refund not found");
        }
        
        RefundDetailVO detail = new RefundDetailVO();
        BeanUtils.copy(convertToRefundVO(refund), detail);
        
        // TODO: 添加更多详情信息
        
        return detail;
    }
    
    @Override
    public PageResult<RefundVO> queryRefunds(RefundQueryRequest request) {
        Page<OrderRefund> page = new Page<>(request.getCurrent(), request.getSize());
        
        LambdaQueryWrapper<OrderRefund> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(request.getRefundNo()), OrderRefund::getRefundNo, request.getRefundNo())
               .eq(StrUtil.isNotBlank(request.getOrderNo()), OrderRefund::getOrderNo, request.getOrderNo())
               .eq(request.getUserId() != null, OrderRefund::getUserId, request.getUserId())
               .eq(request.getRefundType() != null, OrderRefund::getRefundType, request.getRefundType())
               .eq(request.getStatus() != null, OrderRefund::getStatus, request.getStatus())
               .between(request.getStartTime() != null && request.getEndTime() != null,
                       OrderRefund::getCreateTime, request.getStartTime(), request.getEndTime())
               .orderByDesc(OrderRefund::getCreateTime);
        
        Page<OrderRefund> refundPage = this.page(page, wrapper);
        
        List<RefundVO> refundVOs = refundPage.getRecords().stream()
            .map(this::convertToRefundVO)
            .collect(Collectors.toList());
        
        return PageResult.of(refundPage.getCurrent(), refundPage.getSize(),
                            refundPage.getTotal(), refundVOs);
    }
    
    @Override
    public PageResult<RefundVO> getUserRefunds(Long userId, Integer status, Long current, Long size) {
        Page<OrderRefund> page = new Page<>(current, size);
        
        LambdaQueryWrapper<OrderRefund> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderRefund::getUserId, userId)
               .eq(status != null, OrderRefund::getStatus, status)
               .orderByDesc(OrderRefund::getCreateTime);
        
        Page<OrderRefund> refundPage = this.page(page, wrapper);
        
        List<RefundVO> refundVOs = refundPage.getRecords().stream()
            .map(this::convertToRefundVO)
            .collect(Collectors.toList());
        
        return PageResult.of(refundPage.getCurrent(), refundPage.getSize(),
                            refundPage.getTotal(), refundVOs);
    }
    
    @Override
    public List<RefundVO> getOrderRefunds(String orderNo) {
        List<OrderRefund> refunds = baseMapper.selectByOrderNo(orderNo);
        return refunds.stream()
            .map(this::convertToRefundVO)
            .collect(Collectors.toList());
    }
    
    @Override
    public RefundVO checkRefundStatus(String refundNo) {
        return getRefundByNo(refundNo);
    }
    
    // 私有辅助方法
    
    private String generateRefundNo() {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String random = String.valueOf(System.nanoTime()).substring(7, 13);
        return OrderConstants.REFUND_NO_PREFIX + date + random;
    }
    
    private RefundVO convertToRefundVO(OrderRefund refund) {
        RefundVO vo = new RefundVO();
        BeanUtils.copy(refund, vo);
        
        // 设置状态名称
        RefundStatusEnum status = RefundStatusEnum.of(refund.getStatus());
        if (status != null) {
            vo.setStatusName(status.getDesc());
        }
        
        // 转换图片列表
        if (StrUtil.isNotBlank(refund.getProofPics())) {
            vo.setProofImages(List.of(refund.getProofPics().split(",")));
        }
        
        return vo;
    }
    
    private void publishRefundApprovedEvent(OrderRefund refund) {
        RefundApprovedEvent event = new RefundApprovedEvent();
        event.setRefundId(refund.getId());
        event.setRefundNo(refund.getRefundNo());
        event.setOrderId(refund.getOrderId());
        event.setOrderNo(refund.getOrderNo());
        event.setUserId(refund.getUserId());
        event.setRefundAmount(refund.getRefundAmount());
        event.setRefundType(refund.getRefundType());
        event.setApproveTime(refund.getHandleTime());
        event.setEventTime(LocalDateTime.now());
        
        orderEventPublisher.publishRefundApprovedEvent(event);
    }
}