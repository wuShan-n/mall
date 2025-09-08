package com.mall.order.infrastructure.external;

import cn.dev33.satoken.stp.StpUtil;
import com.mall.api.inventory.dto.request.StockLockRequest;
import com.mall.api.inventory.dto.response.StockLockVO;
import com.mall.api.inventory.dubbo.InventoryDubboService;
import com.mall.common.result.Result;
import com.mall.order.domain.order.service.InventoryDomainService;
import com.mall.order.domain.order.valueobject.OrderNo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 库存服务客户端
 * 同时实现领域服务接口
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryServiceClient implements InventoryDomainService {
    
    @DubboReference(version = "1.0.0", check = false)
    private InventoryDubboService inventoryDubboService;
    
    /**
     * 检查库存
     */
    public boolean checkStock(Long skuId, Integer quantity) {
        try {
            Result<Boolean> result = inventoryDubboService.checkStock(skuId, quantity);
            if (!result.isSuccess()) {
                log.warn("Check stock failed: {}", result.getMessage());
                return false;
            }
            return result.getData();
        } catch (Exception e) {
            log.error("Call inventory service failed: {}", e.getMessage(), e);
            return false;
        }
    }
    
    @Override
    public void lockStock(Long skuId, Integer quantity, OrderNo orderNo) {
        try {
            StockLockRequest request = new StockLockRequest();
            request.setSkuId(skuId);
            request.setQuantity(quantity);
            request.setOrderNo(orderNo.getValue());
            request.setExpireTime(LocalDateTime.now().plusMinutes(30));
            
            Result<StockLockVO> result = inventoryDubboService.lockStock(request);
            if (!result.isSuccess()) {
                throw new RuntimeException("锁定库存失败: " + result.getMessage());
            }
        } catch (Exception e) {
            log.error("Lock stock failed: {}", e.getMessage(), e);
            throw new RuntimeException("锁定库存失败", e);
        }
    }
    
    @Override
    public void releaseStock(OrderNo orderNo) {
        try {
            Result<Void> result = inventoryDubboService.unlockStock(orderNo.getValue());
            if (!result.isSuccess()) {
                throw new RuntimeException("释放库存失败: " + result.getMessage());
            }
        } catch (Exception e) {
            log.error("Release stock failed: {}", e.getMessage(), e);
            throw new RuntimeException("释放库存失败", e);
        }
    }
    
    @Override
    public void deductStock(OrderNo orderNo) {
        try {
            Result<Void> result = inventoryDubboService.deductLockedStock(orderNo.getValue());
            if (!result.isSuccess()) {
                throw new RuntimeException("扣减库存失败: " + result.getMessage());
            }
        } catch (Exception e) {
            log.error("Deduct stock failed: {}", e.getMessage(), e);
            throw new RuntimeException("扣减库存失败", e);
        }
    }
}