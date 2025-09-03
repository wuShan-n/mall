package com.mall.payment.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.payment.constant.PaymentConstants;
import com.mall.api.payment.dto.request.PaymentCallbackRequest;
import com.mall.api.payment.dto.request.PaymentCreateRequest;
import com.mall.api.payment.dto.request.PaymentNotifyRequest;
import com.mall.api.payment.dto.request.PaymentQueryRequest;
import com.mall.api.payment.dto.response.PaymentResultVO;
import com.mall.api.payment.dto.response.PaymentVO;
import com.mall.api.payment.dubbo.PaymentDubboService;
import com.mall.api.payment.enums.PaymentStatusEnum;
import com.mall.api.payment.enums.PaymentTypeEnum;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.common.result.ResultCode;
import com.mall.common.utils.BeanUtils;
import com.mall.common.utils.IdGenerator;
import com.mall.payment.converter.PaymentConverter;
import com.mall.payment.entity.PaymentOrder;
import com.mall.payment.handler.PaymentHandler;
import com.mall.payment.handler.PaymentHandlerFactory;
import com.mall.payment.service.PaymentOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * Payment Dubbo service implementation
 */
@Slf4j
@RequiredArgsConstructor
@DubboService(version = PaymentConstants.API_VERSION)
public class PaymentDubboServiceImpl implements PaymentDubboService {
    
    private final PaymentOrderService paymentOrderService;
    private final PaymentHandlerFactory paymentHandlerFactory;
    private final PaymentConverter paymentConverter;
    private final RedissonClient redissonClient;
    private final RabbitTemplate rabbitTemplate;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<PaymentResultVO> createPayment(PaymentCreateRequest request) {
        log.info("Creating payment order: {}", request);
        
        // Check if order already has payment
        LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaymentOrder::getOrderNo, request.getOrderNo())
                   .eq(PaymentOrder::getStatus, PaymentStatusEnum.PENDING.getCode());
        
        PaymentOrder existingOrder = paymentOrderService.getOne(queryWrapper);
        if (existingOrder != null) {
            // Return existing payment
            PaymentHandler handler = paymentHandlerFactory.getHandler(existingOrder.getPaymentType());
            return Result.success(handler.getPaymentUrl(existingOrder));
        }
        
        // Create new payment order
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setPaymentNo(generatePaymentNo());
        paymentOrder.setOrderNo(request.getOrderNo());
        paymentOrder.setUserId(request.getUserId());
        paymentOrder.setPaymentType(request.getPaymentType());
        paymentOrder.setPaymentAmount(request.getPaymentAmount());
        paymentOrder.setCurrency(request.getCurrency());
        paymentOrder.setStatus(PaymentStatusEnum.PENDING.getCode());
        paymentOrder.setExpireTime(request.getExpireTime() != null ? 
            request.getExpireTime() : LocalDateTime.now().plusMinutes(PaymentConstants.PAYMENT_TIMEOUT_MINUTES));
        paymentOrder.setCreateTime(LocalDateTime.now());
        
        paymentOrderService.save(paymentOrder);
        
        // Get payment handler and create payment
        PaymentHandler handler = paymentHandlerFactory.getHandler(request.getPaymentType());
        PaymentResultVO result = handler.createPayment(paymentOrder, request);
        
        log.info("Payment order created successfully: {}", paymentOrder.getPaymentNo());
        return Result.success(result);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> processCallback(PaymentCallbackRequest request) {
        log.info("Processing payment callback: {}", request.getPaymentNo());
        
        String lockKey = "payment:callback:" + request.getPaymentNo();
        RLock lock = redissonClient.getLock(lockKey);
        
        try {
            // Try to acquire lock with timeout
            if (!lock.tryLock(5, 10, TimeUnit.SECONDS)) {
                log.warn("Failed to acquire lock for payment callback: {}", request.getPaymentNo());
                return Result.failed("Payment callback is being processed");
            }
            
            // Get payment order
            LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(PaymentOrder::getPaymentNo, request.getPaymentNo());
            PaymentOrder paymentOrder = paymentOrderService.getOne(queryWrapper);
            
            if (paymentOrder == null) {
                log.error("Payment order not found: {}", request.getPaymentNo());
                return Result.failed("Payment order not found");
            }
            
            // Check if already processed
            if (!PaymentStatusEnum.PENDING.getCode().equals(paymentOrder.getStatus())) {
                log.info("Payment order already processed: {}", request.getPaymentNo());
                return Result.success();
            }
            
            // Update payment order
            paymentOrder.setStatus(PaymentStatusEnum.PAID.getCode());
            paymentOrder.setThirdPartyNo(request.getTransactionId());
            paymentOrder.setPaymentTime(request.getPaymentTime());
            paymentOrder.setCallbackTime(LocalDateTime.now());
            paymentOrder.setCallbackContent(request.toString());
            paymentOrder.setUpdateTime(LocalDateTime.now());
            
            paymentOrderService.updateById(paymentOrder);
            
            // Send payment success event
            sendPaymentSuccessEvent(paymentOrder);
            
            log.info("Payment callback processed successfully: {}", request.getPaymentNo());
            return Result.success();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Interrupted while processing payment callback", e);
            return Result.failed("Processing interrupted");
        } catch (Exception e) {
            log.error("Error processing payment callback", e);
            throw e;
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
    
    @Override
    public Result<Void> processNotify(PaymentNotifyRequest request) {
        log.info("Processing payment notify: {}", request);
        // Similar to processCallback but for async notifications
        // Implementation would be similar to processCallback
        return Result.success();
    }
    
    @Override
    public Result<PaymentVO> queryPaymentByNo(String paymentNo) {
        LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaymentOrder::getPaymentNo, paymentNo);
        PaymentOrder paymentOrder = paymentOrderService.getOne(queryWrapper);
        
        if (paymentOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Payment order not found");
        }
        
        return Result.success(paymentConverter.toVO(paymentOrder));
    }
    
    @Override
    public Result<PaymentVO> queryPaymentByOrderNo(String orderNo) {
        LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaymentOrder::getOrderNo, orderNo)
                   .orderByDesc(PaymentOrder::getCreateTime)
                   .last("LIMIT 1");
        
        PaymentOrder paymentOrder = paymentOrderService.getOne(queryWrapper);
        
        if (paymentOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Payment order not found");
        }
        
        return Result.success(paymentConverter.toVO(paymentOrder));
    }
    
    @Override
    public Result<PageResult<PaymentVO>> queryPayments(PaymentQueryRequest request) {
        Page<PaymentOrder> page = new Page<>(request.getCurrent(), request.getSize());
        
        LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
        
        // Build query conditions
        if (request.getPaymentNo() != null) {
            queryWrapper.eq(PaymentOrder::getPaymentNo, request.getPaymentNo());
        }
        if (request.getOrderNo() != null) {
            queryWrapper.eq(PaymentOrder::getOrderNo, request.getOrderNo());
        }
        if (request.getUserId() != null) {
            queryWrapper.eq(PaymentOrder::getUserId, request.getUserId());
        }
        if (request.getPaymentType() != null) {
            queryWrapper.eq(PaymentOrder::getPaymentType, request.getPaymentType());
        }
        if (request.getStatus() != null) {
            queryWrapper.eq(PaymentOrder::getStatus, request.getStatus());
        }
        if (request.getStartTime() != null) {
            queryWrapper.ge(PaymentOrder::getCreateTime, request.getStartTime());
        }
        if (request.getEndTime() != null) {
            queryWrapper.le(PaymentOrder::getCreateTime, request.getEndTime());
        }
        
        queryWrapper.orderByDesc(PaymentOrder::getCreateTime);
        
        Page<PaymentOrder> resultPage = paymentOrderService.page(page, queryWrapper);
        
        return Result.success(PageResult.of(
            resultPage.getCurrent(),
            resultPage.getSize(),
            resultPage.getTotal(),
            paymentConverter.toVOList(resultPage.getRecords())
        ));
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> cancelPayment(String paymentNo) {
        LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaymentOrder::getPaymentNo, paymentNo);
        PaymentOrder paymentOrder = paymentOrderService.getOne(queryWrapper);
        
        if (paymentOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Payment order not found");
        }
        
        if (!PaymentStatusEnum.PENDING.getCode().equals(paymentOrder.getStatus())) {
            return Result.failed("Only pending payment can be cancelled");
        }
        
        // Update status to cancelled
        LambdaUpdateWrapper<PaymentOrder> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(PaymentOrder::getPaymentNo, paymentNo)
                     .set(PaymentOrder::getStatus, PaymentStatusEnum.CANCELLED.getCode())
                     .set(PaymentOrder::getUpdateTime, LocalDateTime.now());
        
        paymentOrderService.update(updateWrapper);
        
        return Result.success();
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> closePayment(String paymentNo) {
        LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaymentOrder::getPaymentNo, paymentNo);
        PaymentOrder paymentOrder = paymentOrderService.getOne(queryWrapper);
        
        if (paymentOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Payment order not found");
        }
        
        if (!PaymentStatusEnum.PENDING.getCode().equals(paymentOrder.getStatus())) {
            return Result.failed("Only pending payment can be closed");
        }
        
        // Close payment with third party
        PaymentHandler handler = paymentHandlerFactory.getHandler(paymentOrder.getPaymentType());
        handler.closePayment(paymentOrder);
        
        // Update status to expired
        LambdaUpdateWrapper<PaymentOrder> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(PaymentOrder::getPaymentNo, paymentNo)
                     .set(PaymentOrder::getStatus, PaymentStatusEnum.EXPIRED.getCode())
                     .set(PaymentOrder::getUpdateTime, LocalDateTime.now());
        
        paymentOrderService.update(updateWrapper);
        
        return Result.success();
    }
    
    @Override
    public Result<PaymentVO> checkPaymentStatus(String paymentNo) {
        LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaymentOrder::getPaymentNo, paymentNo);
        PaymentOrder paymentOrder = paymentOrderService.getOne(queryWrapper);
        
        if (paymentOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Payment order not found");
        }
        
        // Query payment status from third party
        if (PaymentStatusEnum.PENDING.getCode().equals(paymentOrder.getStatus())) {
            PaymentHandler handler = paymentHandlerFactory.getHandler(paymentOrder.getPaymentType());
            PaymentStatusEnum status = handler.queryPaymentStatus(paymentOrder);
            
            if (status != null && !status.equals(PaymentStatusEnum.PENDING)) {
                // Update local status
                paymentOrder.setStatus(status.getCode());
                paymentOrder.setUpdateTime(LocalDateTime.now());
                paymentOrderService.updateById(paymentOrder);
            }
        }
        
        return Result.success(paymentConverter.toVO(paymentOrder));
    }
    
    @Override
    public Result<String> getPaymentUrl(String paymentNo) {
        LambdaQueryWrapper<PaymentOrder> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PaymentOrder::getPaymentNo, paymentNo);
        PaymentOrder paymentOrder = paymentOrderService.getOne(queryWrapper);
        
        if (paymentOrder == null) {
            return Result.failed(ResultCode.NOT_FOUND, "Payment order not found");
        }
        
        PaymentHandler handler = paymentHandlerFactory.getHandler(paymentOrder.getPaymentType());
        PaymentResultVO result = handler.getPaymentUrl(paymentOrder);
        
        return Result.success(result.getPaymentUrl());
    }
    
    @Override
    public Result<BigDecimal> calculatePaymentFee(Integer paymentType, BigDecimal amount) {
        PaymentHandler handler = paymentHandlerFactory.getHandler(paymentType);
        BigDecimal fee = handler.calculateFee(amount);
        return Result.success(fee);
    }
    
    @Override
    public Result<Boolean> verifySignature(PaymentCallbackRequest request) {
        PaymentHandler handler = paymentHandlerFactory.getHandler(request.getPaymentType());
        boolean valid = handler.verifySignature(request);
        return Result.success(valid);
    }
    
    private String generatePaymentNo() {
        return PaymentConstants.PAYMENT_NO_PREFIX + IdGenerator.nextIdStr();
    }
    
    private void sendPaymentSuccessEvent(PaymentOrder paymentOrder) {
        // Send message to RabbitMQ
        String exchange = "payment.exchange";
        String routingKey = "payment.success";
        
        rabbitTemplate.convertAndSend(exchange, routingKey, paymentOrder);
        log.info("Payment success event sent: {}", paymentOrder.getPaymentNo());
    }
}