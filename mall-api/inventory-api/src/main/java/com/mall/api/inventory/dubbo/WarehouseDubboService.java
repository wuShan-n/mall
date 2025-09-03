package com.mall.api.inventory.dubbo;

import com.mall.api.inventory.dto.request.WarehouseCreateRequest;
import com.mall.api.inventory.dto.response.WarehouseVO;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Warehouse Dubbo RPC service interface
 */
public interface WarehouseDubboService {
    
    /**
     * Create warehouse
     */
    Result<WarehouseVO> createWarehouse(WarehouseCreateRequest request);
    
    /**
     * Update warehouse
     */
    Result<WarehouseVO> updateWarehouse(Long warehouseId, WarehouseCreateRequest request);
    
    /**
     * Delete warehouse
     */
    Result<Void> deleteWarehouse(Long warehouseId);
    
    /**
     * Get warehouse by ID
     */
    Result<WarehouseVO> getWarehouseById(Long warehouseId);
    
    /**
     * Get warehouse by code
     */
    Result<WarehouseVO> getWarehouseByCode(String warehouseCode);
    
    /**
     * Get all warehouses
     */
    Result<List<WarehouseVO>> getAllWarehouses();
    
    /**
     * Get active warehouses
     */
    Result<List<WarehouseVO>> getActiveWarehouses();
    
    /**
     * Query warehouses with pagination
     */
    Result<PageResult<WarehouseVO>> queryWarehouses(PageRequest request);
    
    /**
     * Update warehouse status
     */
    Result<Void> updateWarehouseStatus(Long warehouseId, Integer status);
    
    /**
     * Get default warehouse
     */
    Result<WarehouseVO> getDefaultWarehouse();
    
    /**
     * Get nearest warehouse by location
     */
    Result<WarehouseVO> getNearestWarehouse(String province, String city);
}
