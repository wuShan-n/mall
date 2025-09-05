package com.mall.inventory.service;

import com.mall.api.inventory.dto.request.*;
import com.mall.api.inventory.dto.response.*;
import com.mall.common.result.PageResult;
import java.util.List;
import java.util.Map;

public interface StockService {
    StockVO updateStock(StockUpdateRequest request);
    List<StockVO> batchUpdateStock(BatchStockUpdateRequest request);
    StockLockVO lockStock(StockLockRequest request);
    List<StockLockVO> batchLockStock(List<StockLockRequest> requests);
    void unlockStock(String orderNo);
    void unlockStockById(Long lockId);
    void deductLockedStock(String orderNo);
    void returnStock(Long skuId, Integer quantity, String orderNo);
    boolean checkStock(Long skuId, Integer quantity);
    boolean checkStockWithWarehouse(Long skuId, Integer quantity, Long warehouseId);
    Map<Long, Boolean> batchCheckStock(Map<Long, Integer> skuQuantityMap);
    StockVO getStockBySkuId(Long skuId);
    StockVO getStockBySkuAndWarehouse(Long skuId, Long warehouseId);
    List<StockVO> getStocksBySkuIds(List<Long> skuIds);
    Integer getAvailableStock(Long skuId);
    void transferStock(StockTransferRequest request);
    void inventoryCheck(InventoryCheckRequest request);
    List<StockLockVO> getStockLocksByOrderNo(String orderNo);
    Integer releaseExpiredLocks();
    PageResult<StockVO> queryStocks(StockQueryRequest request);
}