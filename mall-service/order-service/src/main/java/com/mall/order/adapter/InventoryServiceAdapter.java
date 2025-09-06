package com.mall.order.adapter;

import com.mall.api.inventory.dubbo.InventoryDubboService;
import com.mall.api.inventory.dubbo.InventoryQueryDubboService;
import com.mall.api.inventory.dto.request.StockLockRequest;
import com.mall.api.inventory.dto.response.StockLockVO;
import com.mall.api.inventory.dto.response.StockVO;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.Result;
import com.mall.order.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 库存服务适配器 封装库存相关的Dubbo调用，保持订单服务的独立性
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryServiceAdapter {

    @DubboReference(version = "1.0.0", group = "mall")
    private InventoryDubboService inventoryDubboService;

    @DubboReference(version = "1.0.0", group = "mall")
    private InventoryQueryDubboService inventoryQueryDubboService;

    /**
     * 批量检查库存
     */
    public void checkStockAvailability(List<OrderItem> items) {
        Map<Long, Integer> skuQuantityMap = items.stream()
                .collect(Collectors.toMap(
                        OrderItem::getSkuId,
                        OrderItem::getQuantity,
                        Integer::sum // 如果同一SKU有多个条目，则累加数量
                ));

        Result<Map<Long, Boolean>> result = inventoryDubboService.batchCheckStock(skuQuantityMap);
        if (!result.isSuccess()) {
            throw new BusinessException("库存检查失败: " + result.getMessage());
        }

        // 检查是否有库存不足的商品
        Map<Long, Boolean> stockCheckResult = result.getData();
        List<Long> insufficientSkus = stockCheckResult.entrySet().stream()
                .filter(entry -> !entry.getValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (!insufficientSkus.isEmpty()) {
            throw new BusinessException("以下商品库存不足: " + insufficientSkus);
        }
    }

    /**
     * 批量锁定库存
     */
    public List<StockLockVO> batchLockStock(List<OrderItem> items, String orderNo, Long userId) {
        List<StockLockRequest> requests = items.stream().map(item -> {
            StockLockRequest request = new StockLockRequest();
            request.setSkuId(item.getSkuId());
            request.setQuantity(item.getQuantity());
            request.setOrderNo(orderNo);
            request.setUserId(userId);
            // 设置30分钟过期时间
            request.setExpireTime(LocalDateTime.now().plusMinutes(30));
            return request;
        }).collect(Collectors.toList());

        Result<List<StockLockVO>> result = inventoryDubboService.batchLockStock(requests);
        if (!result.isSuccess()) {
            throw new BusinessException("库存锁定失败: " + result.getMessage());
        }

        log.info("成功锁定库存: orderNo={}, lockCount={}", orderNo, result.getData().size());
        return result.getData();
    }

    /**
     * 扣减已锁定的库存
     */
    public void deductLockedStock(String orderNo) {
        Result<Void> result = inventoryDubboService.deductLockedStock(orderNo);
        if (!result.isSuccess()) {
            throw new BusinessException("库存扣减失败: " + result.getMessage());
        }
        log.info("成功扣减锁定库存: orderNo={}", orderNo);
    }

    /**
     * 释放锁定的库存
     */
    public void unlockStock(String orderNo) {
        try {
            Result<Void> result = inventoryDubboService.unlockStock(orderNo);
            if (!result.isSuccess()) {
                log.error("库存解锁失败: orderNo={}, error={}", orderNo, result.getMessage());
            } else {
                log.info("成功释放库存锁定: orderNo={}", orderNo);
            }
        } catch (Exception e) {
            log.error("库存解锁异常: orderNo={}", orderNo, e);
        }
    }

    /**
     * 退还库存（用于退货）
     */
    public void returnStock(List<OrderItem> items, String orderNo) {
        for (OrderItem item : items) {
            try {
                Result<Void> result = inventoryDubboService.returnStock(
                        item.getSkuId(),
                        item.getQuantity(),
                        orderNo
                );
                if (!result.isSuccess()) {
                    log.error("库存退还失败: skuId={}, quantity={}, orderNo={}, error={}",
                            item.getSkuId(), item.getQuantity(), orderNo, result.getMessage());
                }
            } catch (Exception e) {
                log.error("库存退还异常: skuId={}, orderNo={}", item.getSkuId(), orderNo, e);
            }
        }
        log.info("完成库存退还: orderNo={}", orderNo);
    }
//
//    /**
//     * 获取商品库存信息
//     */
//    public Map<Long, StockVO> getStockInfo(List<Long> skuIds) {
//        Result<List<StockVO>> result = inventoryQueryDubboService.getStocksBySkuIds(skuIds);
//        if (!result.isSuccess()) {
//            log.error("获取库存信息失败: skuIds={}, error={}", skuIds, result.getMessage());
//            return Map.of();
//        }
//
//        return result.getData().stream()
//                .collect(Collectors.toMap(StockVO::getSkuId, stock -> stock));
//    }
//
//    /**
//     * 获取单个商品的可用库存
//     */
//    public Integer getAvailableStock(Long skuId) {
//        Result<Integer> result = inventoryQueryDubboService.getAvailableStock(skuId);
//        if (!result.isSuccess()) {
//            log.error("获取可用库存失败: skuId={}, error={}", skuId, result.getMessage());
//            return 0;
//        }
//        return result.getData();
//    }
}
