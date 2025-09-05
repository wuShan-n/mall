package com.mall.inventory.controller;

import com.mall.api.inventory.dto.request.*;
import com.mall.api.inventory.dto.response.*;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.inventory.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory/stock")
@RequiredArgsConstructor
@Tag(name = "Stock Management", description = "Stock management APIs")
public class StockController {
    
    private final StockService stockService;
    
    @PostMapping("/update")
    @Operation(summary = "Update stock")
    public Result<StockVO> updateStock(@Valid @RequestBody StockUpdateRequest request) {
        return Result.success(stockService.updateStock(request));
    }
    
    @PostMapping("/batch-update")
    @Operation(summary = "Batch update stock")
    public Result<List<StockVO>> batchUpdateStock(@Valid @RequestBody BatchStockUpdateRequest request) {
        return Result.success(stockService.batchUpdateStock(request));
    }
    
    @PostMapping("/lock")
    @Operation(summary = "Lock stock")
    public Result<StockLockVO> lockStock(@Valid @RequestBody StockLockRequest request) {
        return Result.success(stockService.lockStock(request));
    }
    
    @PostMapping("/batch-lock")
    @Operation(summary = "Batch lock stock")
    public Result<List<StockLockVO>> batchLockStock(@Valid @RequestBody List<StockLockRequest> requests) {
        return Result.success(stockService.batchLockStock(requests));
    }
    
    @PostMapping("/unlock/{orderNo}")
    @Operation(summary = "Unlock stock by order number")
    public Result<Void> unlockStock(@PathVariable String orderNo) {
        stockService.unlockStock(orderNo);
        return Result.success();
    }
    
    @PostMapping("/unlock/id/{lockId}")
    @Operation(summary = "Unlock stock by lock ID")
    public Result<Void> unlockStockById(@PathVariable Long lockId) {
        stockService.unlockStockById(lockId);
        return Result.success();
    }
    
    @PostMapping("/deduct/{orderNo}")
    @Operation(summary = "Deduct locked stock")
    public Result<Void> deductLockedStock(@PathVariable String orderNo) {
        stockService.deductLockedStock(orderNo);
        return Result.success();
    }
    
    @PostMapping("/return")
    @Operation(summary = "Return stock")
    public Result<Void> returnStock(@RequestParam Long skuId, 
                                    @RequestParam Integer quantity,
                                    @RequestParam String orderNo) {
        stockService.returnStock(skuId, quantity, orderNo);
        return Result.success();
    }
    
    @GetMapping("/check")
    @Operation(summary = "Check stock availability")
    public Result<Boolean> checkStock(@RequestParam Long skuId, @RequestParam Integer quantity) {
        return Result.success(stockService.checkStock(skuId, quantity));
    }
    
    @PostMapping("/batch-check")
    @Operation(summary = "Batch check stock")
    public Result<Map<Long, Boolean>> batchCheckStock(@RequestBody Map<Long, Integer> skuQuantityMap) {
        return Result.success(stockService.batchCheckStock(skuQuantityMap));
    }
    
    @GetMapping("/sku/{skuId}")
    @Operation(summary = "Get stock by SKU ID")
    public Result<StockVO> getStockBySkuId(@PathVariable Long skuId) {
        return Result.success(stockService.getStockBySkuId(skuId));
    }
    
    @PostMapping("/sku/list")
    @Operation(summary = "Get stocks by SKU IDs")
    public Result<List<StockVO>> getStocksBySkuIds(@RequestBody List<Long> skuIds) {
        return Result.success(stockService.getStocksBySkuIds(skuIds));
    }
    
    @GetMapping("/available/{skuId}")
    @Operation(summary = "Get available stock")
    public Result<Integer> getAvailableStock(@PathVariable Long skuId) {
        return Result.success(stockService.getAvailableStock(skuId));
    }
    
    @PostMapping("/transfer")
    @Operation(summary = "Transfer stock between warehouses")
    public Result<Void> transferStock(@Valid @RequestBody StockTransferRequest request) {
        stockService.transferStock(request);
        return Result.success();
    }
    
    @PostMapping("/inventory-check")
    @Operation(summary = "Perform inventory check")
    public Result<Void> inventoryCheck(@Valid @RequestBody InventoryCheckRequest request) {
        stockService.inventoryCheck(request);
        return Result.success();
    }
    
    @GetMapping("/locks/{orderNo}")
    @Operation(summary = "Get stock locks by order number")
    public Result<List<StockLockVO>> getStockLocksByOrderNo(@PathVariable String orderNo) {
        return Result.success(stockService.getStockLocksByOrderNo(orderNo));
    }
    
    @PostMapping("/release-expired")
    @Operation(summary = "Release expired stock locks")
    public Result<Integer> releaseExpiredLocks() {
        return Result.success(stockService.releaseExpiredLocks());
    }
    
    @PostMapping("/query")
    @Operation(summary = "Query stocks with pagination")
    public Result<PageResult<StockVO>> queryStocks(@Valid @RequestBody StockQueryRequest request) {
        return Result.success(stockService.queryStocks(request));
    }
}