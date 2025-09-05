package com.mall.payment.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.payment.constant.PaymentConstants;
import com.mall.api.payment.dto.request.RefundApplyRequest;
import com.mall.api.payment.dto.request.RefundCallbackRequest;
import com.mall.api.payment.dto.request.RefundQueryRequest;
import com.mall.api.payment.dto.response.RefundDetailVO;
import com.mall.api.payment.dto.response.RefundResultVO;
import com.mall.api.payment.dto.response.RefundVO;
import com.mall.api.payment.dubbo.RefundDubboService;
import com.mall.api.payment.enums.RefundStatusEnum;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.common.result.ResultCode;
import com.mall.common.utils.IdGenerator;
import com.mall.payment.converter.PaymentConverter;
import com.mall.payment.entity.PaymentOrder;
import com.mall.payment.entity.RefundOrder;
import com.mall.payment.handler.PaymentHandler;
import com.mall.payment.handler.PaymentHandlerFactory;
import com.mall.payment.service.PaymentOrderService;
import com.mall.payment.service.RefundOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Refund Dubbo service implementation
 */
@Slf4j
@RequiredArgsConstructor
@DubboService(version = PaymentConstants.API_VERSION)
public class RefundDubboServiceImpl implements RefundDubboService {
    
    private final RefundOrderService refundOrderService;
    private final PaymentOrderService paymentOrderService;
    private final PaymentHandlerFactory paymentHandlerFactory;
    private final PaymentConverter paymentConverter;
    private final RedissonClient redissonClient;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<RefundResultVO> applyRefund(RefundApplyRequest request) {
        log.info("Applying refund for payment: {}", request.getPaymentNo());
        
        // Get payment order
        LambdaQueryWrapper<PaymentOrder> paymentQuery = new LambdaQueryWrapper<>();
        paymentQuery.eq(PaymentOrder::getPaymentNo, request.getPaymentNo());
        PaymentOrder paymentOrder = paymentOrderService.getOne(paymentQuery);
        
        if (paymentOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Payment order not found");
        }
        
        // Check payment status
        if (!paymentOrder.getStatus().equals(1)) { // 1 = PAID
            return Result.failed("Only paid orders can be refunded");
        }
        
        // Check existing refunds
        LambdaQueryWrapper<RefundOrder> refundQuery = new LambdaQueryWrapper<>();
        refundQuery.eq(RefundOrder::getPaymentNo, request.getPaymentNo());
        List<RefundOrder> existingRefunds = refundOrderService.list(refundQuery);
        
        // Calculate total refunded amount
        BigDecimal totalRefunded = existingRefunds.stream()
                .filter(r -> !r.getStatus().equals(RefundStatusEnum.FAILED.getCode()) 
                        && !r.getStatus().equals(RefundStatusEnum.CANCELLED.getCode()))
                .map(RefundOrder::getRefundAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Check if refund amount exceeds payment amount
        if (totalRefunded.add(request.getRefundAmount()).compareTo(paymentOrder.getPaymentAmount()) > 0) {
            return Result.failed("Refund amount exceeds payment amount");
        }
        
        // Create refund order
        RefundOrder refundOrder = new RefundOrder();
        refundOrder.setRefundNo(generateRefundNo());
        refundOrder.setPaymentNo(request.getPaymentNo());
        refundOrder.setOrderNo(request.getOrderNo());
        refundOrder.setUserId(request.getUserId());
        refundOrder.setRefundAmount(request.getRefundAmount());
        refundOrder.setRefundReason(request.getRefundReason());
        refundOrder.setStatus(RefundStatusEnum.PENDING.getCode());
        refundOrder.setCreateTime(LocalDateTime.now());
        
        refundOrderService.save(refundOrder);
        
        // Process refund with payment handler
        PaymentHandler handler = paymentHandlerFactory.getHandler(paymentOrder.getPaymentType());
        RefundResultVO result = handler.applyRefund(refundOrder, request);
        
        // Update refund status based on result
        if (result.getSuccess()) {
            refundOrder.setStatus(RefundStatusEnum.PROCESSING.getCode());
            refundOrder.setThirdPartyRefundNo(result.getThirdPartyRefundNo());
            refundOrder.setUpdateTime(LocalDateTime.now());
            refundOrderService.updateById(refundOrder);
        }
        
        log.info("Refund applied successfully: {}", refundOrder.getRefundNo());
        return Result.success(result);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> processRefundCallback(RefundCallbackRequest request) {
        log.info("Processing refund callback: {}", request.getRefundNo());
        
        String lockKey = "refund:callback:" + request.getRefundNo();
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            if (!lock.tryLock(5, 10, TimeUnit.SECONDS)) {
                log.warn("Failed to acquire lock for refund callback: {}", request.getRefundNo());
                return Result.failed("Refund callback is being processed");
            }
            
            // Get refund order
            LambdaQueryWrapper<RefundOrder> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(RefundOrder::getRefundNo, request.getRefundNo());
            RefundOrder refundOrder = refundOrderService.getOne(queryWrapper);
            
            if (refundOrder == null) {
                log.error("Refund order not found: {}", request.getRefundNo());
                return Result.failed("Refund order not found");
            }
            
            // Update refund status
            if ("SUCCESS".equals(request.getStatus())) {
                refundOrder.setStatus(RefundStatusEnum.SUCCESS.getCode());
                refundOrder.setRefundTime(request.getRefundTime());
                
                // Update payment order status if fully refunded
                updatePaymentOrderStatus(refundOrder);
            } else {
                refundOrder.setStatus(RefundStatusEnum.FAILED.getCode());
            }
            
            refundOrder.setThirdPartyRefundNo(request.getThirdPartyRefundNo());
            refundOrder.setUpdateTime(LocalDateTime.now());
            refundOrderService.updateById(refundOrder);
            
            log.info("Refund callback processed successfully: {}", request.getRefundNo());
            return Result.success();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Interrupted while processing refund callback", e);
            return Result.failed("Processing interrupted");
        } catch (Exception e) {
            log.error("Error processing refund callback", e);
            throw e;
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
    
    @Override
    public Result<RefundVO> queryRefundByNo(String refundNo) {
        LambdaQueryWrapper<RefundOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RefundOrder::getRefundNo, refundNo);
        RefundOrder refundOrder = refundOrderService.getOne(queryWrapper);
        
        if (refundOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Refund order not found");
        }
        
        return Result.success(paymentConverter.toRefundVO(refundOrder));
    }
    
    @Override
    public Result<List<RefundVO>> queryRefundsByPaymentNo(String paymentNo) {
        LambdaQueryWrapper<RefundOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RefundOrder::getPaymentNo, paymentNo)
                   .orderByDesc(RefundOrder::getCreateTime);
        
        List<RefundOrder> refundOrders = refundOrderService.list(queryWrapper);
        return Result.success(paymentConverter.toRefundVOList(refundOrders));
    }
    
    @Override
    public Result<List<RefundVO>> queryRefundsByOrderNo(String orderNo) {
        LambdaQueryWrapper<RefundOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RefundOrder::getOrderNo, orderNo)
                   .orderByDesc(RefundOrder::getCreateTime);
        
        List<RefundOrder> refundOrders = refundOrderService.list(queryWrapper);
        return Result.success(paymentConverter.toRefundVOList(refundOrders));
    }
    
    @Override
    public Result<PageResult<RefundVO>> queryRefunds(RefundQueryRequest request) {
        Page<RefundOrder> page = new Page<>(request.getCurrent(), request.getSize());
        
        LambdaQueryWrapper<RefundOrder> queryWrapper = new LambdaQueryWrapper<>();
        
        // Build query conditions
        if (request.getRefundNo() != null) {
            queryWrapper.eq(RefundOrder::getRefundNo, request.getRefundNo());
        }
        if (request.getPaymentNo() != null) {
            queryWrapper.eq(RefundOrder::getPaymentNo, request.getPaymentNo());
        }
        if (request.getOrderNo() != null) {
            queryWrapper.eq(RefundOrder::getOrderNo, request.getOrderNo());
        }
        if (request.getUserId() != null) {
            queryWrapper.eq(RefundOrder::getUserId, request.getUserId());
        }
        if (request.getStatus() != null) {
            queryWrapper.eq(RefundOrder::getStatus, request.getStatus());
        }
        if (request.getStartTime() != null) {
            queryWrapper.ge(RefundOrder::getCreateTime, request.getStartTime());
        }
        if (request.getEndTime() != null) {
            queryWrapper.le(RefundOrder::getCreateTime, request.getEndTime());
        }
        
        queryWrapper.orderByDesc(RefundOrder::getCreateTime);
        
        Page<RefundOrder> resultPage = refundOrderService.page(page, queryWrapper);
        
        return Result.success(PageResult.of(
            resultPage.getCurrent(),
            resultPage.getSize(),
            resultPage.getTotal(),
            paymentConverter.toRefundVOList(resultPage.getRecords())
        ));
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> cancelRefund(String refundNo) {
        LambdaQueryWrapper<RefundOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RefundOrder::getRefundNo, refundNo);
        RefundOrder refundOrder = refundOrderService.getOne(queryWrapper);
        
        if (refundOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Refund order not found");
        }
        
        if (!RefundStatusEnum.PENDING.getCode().equals(refundOrder.getStatus()) 
                && !RefundStatusEnum.PROCESSING.getCode().equals(refundOrder.getStatus())) {
            return Result.failed("Only pending or processing refund can be cancelled");
        }
        
        refundOrder.setStatus(RefundStatusEnum.CANCELLED.getCode());
        refundOrder.setUpdateTime(LocalDateTime.now());
        refundOrderService.updateById(refundOrder);
        
        return Result.success();
    }
    
    @Override
    public Result<RefundResultVO> retryRefund(String refundNo) {
        // Implementation for retrying failed refunds
        return Result.success();
    }
    
    @Override
    public Result<RefundVO> checkRefundStatus(String refundNo) {
        LambdaQueryWrapper<RefundOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RefundOrder::getRefundNo, refundNo);
        RefundOrder refundOrder = refundOrderService.getOne(queryWrapper);
        
        if (refundOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Refund order not found");
        }
        
        // Query refund status from third party if still processing
        if (RefundStatusEnum.PROCESSING.getCode().equals(refundOrder.getStatus())) {
            // Get payment order to determine payment type
            LambdaQueryWrapper<PaymentOrder> paymentQuery = new LambdaQueryWrapper<>();
            paymentQuery.eq(PaymentOrder::getPaymentNo, refundOrder.getPaymentNo());
            PaymentOrder paymentOrder = paymentOrderService.getOne(paymentQuery);
            
            if (paymentOrder != null) {
                PaymentHandler handler = paymentHandlerFactory.getHandler(paymentOrder.getPaymentType());
                RefundResultVO result = handler.queryRefundStatus(refundOrder);
                
                if (result.getSuccess()) {
                    refundOrder.setStatus(RefundStatusEnum.SUCCESS.getCode());
                    refundOrder.setRefundTime(LocalDateTime.now());
                    refundOrder.setUpdateTime(LocalDateTime.now());
                    refundOrderService.updateById(refundOrder);
                }
            }
        }
        
        return Result.success(paymentConverter.toRefundVO(refundOrder));
    }
    
    @Override
    public Result<RefundDetailVO> getRefundDetail(String refundNo) {
        // Implementation for getting detailed refund information
        return Result.success();
    }
    
    private String generateRefundNo() {
        return PaymentConstants.REFUND_NO_PREFIX + IdGenerator.nextIdStr();
    }
    
    private void updatePaymentOrderStatus(RefundOrder refundOrder) {
        // Check if payment is fully refunded
        LambdaQueryWrapper<RefundOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RefundOrder::getPaymentNo, refundOrder.getPaymentNo())
                   .eq(RefundOrder::getStatus, RefundStatusEnum.SUCCESS.getCode());
        
        List<RefundOrder> successfulRefunds = refundOrderService.list(queryWrapper);
        
        BigDecimal totalRefunded = successfulRefunds.stream()
                .map(RefundOrder::getRefundAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        // Get payment order
        LambdaQueryWrapper<PaymentOrder> paymentQuery = new LambdaQueryWrapper<>();
        paymentQuery.eq(PaymentOrder::getPaymentNo, refundOrder.getPaymentNo());
        PaymentOrder paymentOrder = paymentOrderService.getOne(paymentQuery);
        
        if (paymentOrder != null) {
            if (totalRefunded.compareTo(paymentOrder.getPaymentAmount()) >= 0) {
                // Fully refunded
                paymentOrder.setStatus(5); // REFUNDED
            } else {
                // Partially refunded
                paymentOrder.setStatus(6); // PARTIAL_REFUNDED
            }
            paymentOrder.setUpdateTime(LocalDateTime.now());
            paymentOrderService.updateById(paymentOrder);
        }
    }
}