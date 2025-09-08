package com.mall.order.application.facade;

import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.user.dto.response.AddressVO;
import com.mall.api.user.dto.response.UserVO;
import com.mall.order.infrastructure.external.InventoryServiceClient;
import com.mall.order.infrastructure.external.PaymentServiceClient;
import com.mall.order.infrastructure.external.ProductServiceClient;
import com.mall.order.infrastructure.external.UserServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单外部服务门面
 * 统一管理对外部服务的调用，提供统一的异常处理和日志记录
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderExternalServiceFacade {
    
    private final UserServiceClient userServiceClient;
    private final ProductServiceClient productServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final PaymentServiceClient paymentServiceClient;
    
    /**
     * 获取用户信息
     */
    public UserVO getUserInfo(Long userId) {
        try {
            log.debug("Getting user info for userId: {}", userId);
            return userServiceClient.getUserById(userId);
        } catch (Exception e) {
            log.error("Failed to get user info for userId: {}", userId, e);
            throw new IllegalStateException("获取用户信息失败", e);
        }
    }
    
    /**
     * 获取收货地址
     */
    public AddressVO getUserAddress(Long addressId) {
        try {
            log.debug("Getting address info for addressId: {}", addressId);
            return userServiceClient.getAddressById(addressId);
        } catch (Exception e) {
            log.error("Failed to get address info for addressId: {}", addressId, e);
            throw new IllegalStateException("获取收货地址失败", e);
        }
    }
    
    /**
     * 获取SKU信息
     */
    public SkuVO getSkuInfo(Long skuId) {
        try {
            log.debug("Getting SKU info for skuId: {}", skuId);
            return productServiceClient.getSkuById(skuId);
        } catch (Exception e) {
            log.error("Failed to get SKU info for skuId: {}", skuId, e);
            throw new IllegalStateException("获取商品信息失败", e);
        }
    }
    
    /**
     * 批量获取SKU信息
     */
    public List<SkuVO> getSkuInfoBatch(List<Long> skuIds) {
        try {
            log.debug("Getting SKU info batch for skuIds: {}", skuIds);
            return productServiceClient.getSkusByIds(skuIds);
        } catch (Exception e) {
            log.error("Failed to get SKU info batch for skuIds: {}", skuIds, e);
            throw new IllegalStateException("批量获取商品信息失败", e);
        }
    }
    
    /**
     * 检查库存
     */
    public boolean checkStock(Long skuId, Integer quantity) {
        try {
            log.debug("Checking stock for skuId: {}, quantity: {}", skuId, quantity);
            return inventoryServiceClient.checkStock(skuId, quantity);
        } catch (Exception e) {
            log.error("Failed to check stock for skuId: {}, quantity: {}", skuId, quantity, e);
            throw new IllegalStateException("检查库存失败", e);
        }
    }
    
    /**
     * 批量检查库存
     */
    public boolean checkStockBatch(List<Long> skuIds, List<Integer> quantities) {
        try {
            log.debug("Checking stock batch for skuIds: {}, quantities: {}", skuIds, quantities);
            // 由于没有批量接口，改为逐个检查
            for (int i = 0; i < skuIds.size(); i++) {
                if (!checkStock(skuIds.get(i), quantities.get(i))) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            log.error("Failed to check stock batch for skuIds: {}, quantities: {}", skuIds, quantities, e);
            throw new IllegalStateException("批量检查库存失败", e);
        }
    }
    
    /**
     * 创建支付订单
     */
    public com.mall.api.payment.dto.response.PaymentResultVO createPayment(String orderNo, BigDecimal amount, Integer paymentType) {
        try {
            log.debug("Creating payment for orderNo: {}, amount: {}, paymentType: {}", 
                     orderNo, amount, paymentType);
            return paymentServiceClient.createPayment(orderNo, amount, paymentType);
        } catch (Exception e) {
            log.error("Failed to create payment for orderNo: {}, amount: {}, paymentType: {}", 
                     orderNo, amount, paymentType, e);
            throw new IllegalStateException("创建支付订单失败", e);
        }
    }
}