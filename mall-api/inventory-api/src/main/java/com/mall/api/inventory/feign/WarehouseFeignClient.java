package com.mall.api.inventory.feign;

import com.mall.api.inventory.constant.InventoryConstants;
import com.mall.api.inventory.dto.request.WarehouseCreateRequest;
import com.mall.api.inventory.dto.response.WarehouseVO;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Warehouse Feign client for HTTP calls
 */
@FeignClient(name = InventoryConstants.SERVICE_NAME, path = InventoryConstants.API_PREFIX + "/warehouse")
public interface WarehouseFeignClient {
    
    @PostMapping("/create")
    Result<WarehouseVO> createWarehouse(@RequestBody WarehouseCreateRequest request);
    
    @PutMapping("/{warehouseId}")
    Result<WarehouseVO> updateWarehouse(@PathVariable("warehouseId") Long warehouseId,
                                        @RequestBody WarehouseCreateRequest request);
    
    @DeleteMapping("/{warehouseId}")
    Result<Void> deleteWarehouse(@PathVariable("warehouseId") Long warehouseId);
    
    @GetMapping("/{warehouseId}")
    Result<WarehouseVO> getWarehouseById(@PathVariable("warehouseId") Long warehouseId);
    
    @GetMapping("/code/{warehouseCode}")
    Result<WarehouseVO> getWarehouseByCode(@PathVariable("warehouseCode") String warehouseCode);
    
    @GetMapping("/all")
    Result<List<WarehouseVO>> getAllWarehouses();
    
    @GetMapping("/active")
    Result<List<WarehouseVO>> getActiveWarehouses();
    
    @PostMapping("/query")
    Result<PageResult<WarehouseVO>> queryWarehouses(@RequestBody PageRequest request);
    
    @PutMapping("/{warehouseId}/status/{status}")
    Result<Void> updateWarehouseStatus(@PathVariable("warehouseId") Long warehouseId,
                                       @PathVariable("status") Integer status);
    
    @GetMapping("/default")
    Result<WarehouseVO> getDefaultWarehouse();
    
    @GetMapping("/nearest")
    Result<WarehouseVO> getNearestWarehouse(@RequestParam("province") String province,
                                            @RequestParam("city") String city);
}
