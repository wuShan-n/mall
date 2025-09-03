package com.mall.api.inventory.dubbo;

import com.mall.api.inventory.dto.request.StockQueryRequest;
import com.mall.api.inventory.dto.response.InventoryStatisticsVO;
import com.mall.api.inventory.dto.response.StockRecordVO;
import com.mall.api.inventory.dto.response.StockVO;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Inventory query Dubbo RPC service interface
 */
public interface InventoryQueryDubboService {
    
    /**
     * Query stocks with pagination
     */
    Result<PageResult<StockVO>> queryStocks(StockQueryRequest request);
    
    /**
     * Get stock records by SKU ID
     */
    Result<List<StockRecordVO>> getStockRecordsBySkuId(Long skuId, PageRequest pageRequest);
    
    /**
     * Get stock records by warehouse
     */
    Result<List<StockRecordVO>> getStockRecordsByWarehouse(Long warehouseId, PageRequest pageRequest);
    
    /**
     * Get stock records by business number
     */
    Result<List<StockRecordVO>> getStockRecordsByBusinessNo(String businessNo);
    
    /**
     * Get inventory statistics
     */
    Result<InventoryStatisticsVO> getInventoryStatistics();
    
    /**
     * Get warehouse inventory statistics
     */
    Result<InventoryStatisticsVO> getWarehouseStatistics(Long warehouseId);
    
    /**
     * Get stock movement report
     */
    Result<Map<String, Object>> getStockMovementReport(LocalDate startDate, LocalDate endDate);
    
    /**
     * Get low stock SKUs
     */
    Result<List<StockVO>> getLowStockSkus(Integer limit);
    
    /**
     * Get out of stock SKUs
     */
    Result<List<StockVO>> getOutOfStockSkus(Integer limit);
    
    /**
     * Get overstock SKUs
     */
    Result<List<StockVO>> getOverstockSkus(Integer limit);
    
    /**
     * Get stock value report
     */
    Result<Map<String, Object>> getStockValueReport();
    
    /**
     * Get stock aging report
     */
    Result<Map<String, Object>> getStockAgingReport();
}