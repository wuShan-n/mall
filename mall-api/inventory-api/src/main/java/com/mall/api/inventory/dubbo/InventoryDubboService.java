package com.mall.api.inventory.dubbo;

import com.mall.api.inventory.dto.request.*;
import com.mall.api.inventory.dto.response.StockLockVO;
import com.mall.api.inventory.dto.response.StockVO;
import com.mall.common.result.Result;

import java.util.List;
import java.util.Map;

/**
 * Inventory Dubbo RPC service interface
 */
public interface InventoryDubboService {
    
    /**
     * Update stock (add/deduct)
     */
    Result<StockVO> updateStock(StockUpdateRequest request);
    
    /**
     * Batch update stock
     */
    Result<List<StockVO>> batchUpdateStock(BatchStockUpdateRequest request);
    
    /**
     * Lock stock for order
     */
    Result<StockLockVO> lockStock(StockLockRequest request);
    
    /**
     * Batch lock stock
     */
    Result<List<StockLockVO>> batchLockStock(List<StockLockRequest> requests);
    
    /**
     * Unlock stock
     */
    Result<Void> unlockStock(String orderNo);
    
    /**
     * Unlock stock by lock ID
     */
    Result<Void> unlockStockById(Long lockId);
    
    /**
     * Deduct locked stock (confirm order)
     */
    Result<Void> deductLockedStock(String orderNo);
    
    /**
     * Return stock (for refund/cancel)
     */
    Result<Void> returnStock(Long skuId, Integer quantity, String orderNo);
    
    /**
     * Check stock availability
     */
    Result<Boolean> checkStock(Long skuId, Integer quantity);
    
    /**
     * Check stock availability with warehouse
     */
    Result<Boolean> checkStockWithWarehouse(Long skuId, Integer quantity, Long warehouseId);
    
    /**
     * Batch check stock availability
     */
    Result<Map<Long, Boolean>> batchCheckStock(Map<Long, Integer> skuQuantityMap);
    
    /**
     * Get stock by SKU ID
     */
    Result<StockVO> getStockBySkuId(Long skuId);
    
    /**
     * Get stock by SKU ID and warehouse
     */
    Result<StockVO> getStockBySkuAndWarehouse(Long skuId, Long warehouseId);
    
    /**
     * Get stocks by SKU IDs
     */
    Result<List<StockVO>> getStocksBySkuIds(List<Long> skuIds);
    
    /**
     * Get available stock quantity
     */
    Result<Integer> getAvailableStock(Long skuId);
    
    /**
     * Transfer stock between warehouses
     */
    Result<Void> transferStock(StockTransferRequest request);
    
    /**
     * Inventory check and adjustment
     */
    Result<Void> inventoryCheck(InventoryCheckRequest request);
    
    /**
     * Get stock lock records
     */
    Result<List<StockLockVO>> getStockLocksByOrderNo(String orderNo);
    
    /**
     * Release expired stock locks
     */
    Result<Integer> releaseExpiredLocks();
}