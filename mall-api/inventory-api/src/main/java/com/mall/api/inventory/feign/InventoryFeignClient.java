package com.mall.api.inventory.feign;

import com.mall.api.inventory.constant.InventoryConstants;
import com.mall.api.inventory.dto.request.*;
import com.mall.api.inventory.dto.response.*;
import com.mall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Inventory Feign client for HTTP calls
 */
@FeignClient(name = InventoryConstants.SERVICE_NAME, path = InventoryConstants.API_PREFIX)
public interface InventoryFeignClient {
    
    @PostMapping("/stock/update")
    Result<StockVO> updateStock(@RequestBody StockUpdateRequest request);
    
    @PostMapping("/stock/batch-update")
    Result<List<StockVO>> batchUpdateStock(@RequestBody BatchStockUpdateRequest request);
    
    @PostMapping("/stock/lock")
    Result<StockLockVO> lockStock(@RequestBody StockLockRequest request);
    
    @PostMapping("/stock/batch-lock")
    Result<List<StockLockVO>> batchLockStock(@RequestBody List<StockLockRequest> requests);
    
    @PostMapping("/stock/unlock/{orderNo}")
    Result<Void> unlockStock(@PathVariable("orderNo") String orderNo);
    
    @PostMapping("/stock/unlock/id/{lockId}")
    Result<Void> unlockStockById(@PathVariable("lockId") Long lockId);
    
    @PostMapping("/stock/deduct/{orderNo}")
    Result<Void> deductLockedStock(@PathVariable("orderNo") String orderNo);
    
    @PostMapping("/stock/return")
    Result<Void> returnStock(@RequestParam("skuId") Long skuId,
                             @RequestParam("quantity") Integer quantity,
                             @RequestParam("orderNo") String orderNo);
    
    @GetMapping("/stock/check")
    Result<Boolean> checkStock(@RequestParam("skuId") Long skuId,
                               @RequestParam("quantity") Integer quantity);
    
    @GetMapping("/stock/check-warehouse")
    Result<Boolean> checkStockWithWarehouse(@RequestParam("skuId") Long skuId,
                                           @RequestParam("quantity") Integer quantity,
                                           @RequestParam("warehouseId") Long warehouseId);
    
    @PostMapping("/stock/batch-check")
    Result<Map<Long, Boolean>> batchCheckStock(@RequestBody Map<Long, Integer> skuQuantityMap);
    
    @GetMapping("/stock/sku/{skuId}")
    Result<StockVO> getStockBySkuId(@PathVariable("skuId") Long skuId);
    
    @GetMapping("/stock/sku/{skuId}/warehouse/{warehouseId}")
    Result<StockVO> getStockBySkuAndWarehouse(@PathVariable("skuId") Long skuId,
                                              @PathVariable("warehouseId") Long warehouseId);
    
    @PostMapping("/stock/sku/list")
    Result<List<StockVO>> getStocksBySkuIds(@RequestBody List<Long> skuIds);
    
    @GetMapping("/stock/available/{skuId}")
    Result<Integer> getAvailableStock(@PathVariable("skuId") Long skuId);
    
    @PostMapping("/stock/transfer")
    Result<Void> transferStock(@RequestBody StockTransferRequest request);
    
    @PostMapping("/stock/inventory-check")
    Result<Void> inventoryCheck(@RequestBody InventoryCheckRequest request);
    
    @GetMapping("/stock/locks/{orderNo}")
    Result<List<StockLockVO>> getStockLocksByOrderNo(@PathVariable("orderNo") String orderNo);
    
    @PostMapping("/stock/release-expired")
    Result<Integer> releaseExpiredLocks();
}
