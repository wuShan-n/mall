package com.mall.inventory.service;

import com.mall.api.inventory.dto.request.WarehouseCreateRequest;
import com.mall.api.inventory.dto.response.WarehouseVO;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;

import java.util.List;

public interface WarehouseService {
    WarehouseVO createWarehouse(WarehouseCreateRequest request);
    WarehouseVO updateWarehouse(Long warehouseId, WarehouseCreateRequest request);
    void deleteWarehouse(Long warehouseId);
    WarehouseVO getWarehouseById(Long warehouseId);
    WarehouseVO getWarehouseByCode(String warehouseCode);
    List<WarehouseVO> getAllWarehouses();
    List<WarehouseVO> getActiveWarehouses();
    PageResult<WarehouseVO> queryWarehouses(PageRequest request);
    void updateWarehouseStatus(Long warehouseId, Integer status);
    WarehouseVO getDefaultWarehouse();
    WarehouseVO getNearestWarehouse(String province, String city);
}