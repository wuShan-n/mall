package com.mall.inventory.provider;

import com.mall.api.inventory.dto.request.*;
import com.mall.api.inventory.dto.response.StockLockVO;
import com.mall.api.inventory.dto.response.StockVO;
import com.mall.api.inventory.dubbo.InventoryDubboService;
import com.mall.common.result.Result;
import com.mall.inventory.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;
import java.util.Map;

/**
 * 库存Dubbo服务实现
 *
 * @author Mall Team
 * @version 1.0.0
 */
@Slf4j
@DubboService(
        version = "1.0.0",
        timeout = 10000,
        retries = 0,
        loadbalance = "random",
        actives = 100
)
@RequiredArgsConstructor
public class InventoryDubboServiceImpl implements InventoryDubboService {

    private final StockService stockService;

    @Override
    public Result<StockVO> updateStock(StockUpdateRequest request) {
        try {
            log.debug("Updating stock: {}", request);
            StockVO result = stockService.updateStock(request);
            log.info("Stock updated successfully: SKU={}, Quantity={}, Operation={}",
                    request.getSkuId(), request.getQuantity(), request.getOperationType());
            return Result.success(result);
        } catch (Exception e) {
            log.error("Failed to update stock: {}", request, e);
            return Result.failed("Stock update failed: " + e.getMessage());
        }
    }

    @Override
    public Result<List<StockVO>> batchUpdateStock(BatchStockUpdateRequest request) {
        try {
            log.debug("Batch updating stocks, count: {}", request.getStockUpdates().size());
            List<StockVO> results = stockService.batchUpdateStock(request);
            log.info("Batch stock update completed, success count: {}", results.size());
            return Result.success(results);
        } catch (Exception e) {
            log.error("Failed to batch update stock", e);
            return Result.failed("Batch stock update failed: " + e.getMessage());
        }
    }

    @Override
    public Result<StockLockVO> lockStock(StockLockRequest request) {
        try {
            log.debug("Locking stock: {}", request);
            StockLockVO result = stockService.lockStock(request);
            log.info("Stock locked successfully: SKU={}, Quantity={}, OrderNo={}",
                    request.getSkuId(), request.getQuantity(), request.getOrderNo());
            return Result.success(result);
        } catch (Exception e) {
            log.error("Failed to lock stock: {}", request, e);
            return Result.failed("Stock lock failed: " + e.getMessage());
        }
    }

    @Override
    public Result<List<StockLockVO>> batchLockStock(List<StockLockRequest> requests) {
        try {
            log.debug("Batch locking stocks, count: {}", requests.size());
            List<StockLockVO> results = stockService.batchLockStock(requests);
            log.info("Batch stock lock completed, success count: {}", results.size());
            return Result.success(results);
        } catch (Exception e) {
            log.error("Failed to batch lock stock", e);
            return Result.failed("Batch stock lock failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> unlockStock(String orderNo) {
        try {
            log.debug("Unlocking stock for order: {}", orderNo);
            stockService.unlockStock(orderNo);
            log.info("Stock unlocked successfully for order: {}", orderNo);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to unlock stock for order: {}", orderNo, e);
            return Result.failed("Stock unlock failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> unlockStockById(Long lockId) {
        try {
            log.debug("Unlocking stock by ID: {}", lockId);
            stockService.unlockStockById(lockId);
            log.info("Stock unlocked successfully by ID: {}", lockId);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to unlock stock by ID: {}", lockId, e);
            return Result.failed("Stock unlock failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> deductLockedStock(String orderNo) {
        try {
            log.debug("Deducting locked stock for order: {}", orderNo);
            stockService.deductLockedStock(orderNo);
            log.info("Locked stock deducted successfully for order: {}", orderNo);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to deduct locked stock for order: {}", orderNo, e);
            return Result.failed("Stock deduction failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> returnStock(Long skuId, Integer quantity, String orderNo) {
        try {
            log.debug("Returning stock: SKU={}, Quantity={}, OrderNo={}", skuId, quantity, orderNo);
            stockService.returnStock(skuId, quantity, orderNo);
            log.info("Stock returned successfully: SKU={}, Quantity={}, OrderNo={}",
                    skuId, quantity, orderNo);
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to return stock: SKU={}, OrderNo={}", skuId, orderNo, e);
            return Result.failed("Stock return failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Boolean> checkStock(Long skuId, Integer quantity) {
        try {
            boolean result = stockService.checkStock(skuId, quantity);
            log.debug("Stock check result: SKU={}, Quantity={}, Available={}",
                    skuId, quantity, result);
            return Result.success(result);
        } catch (Exception e) {
            log.error("Failed to check stock: SKU={}, Quantity={}", skuId, quantity, e);
            return Result.failed("Stock check failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Boolean> checkStockWithWarehouse(Long skuId, Integer quantity, Long warehouseId) {
        try {
            boolean result = stockService.checkStockWithWarehouse(skuId, quantity, warehouseId);
            log.debug("Stock check result: SKU={}, Quantity={}, Warehouse={}, Available={}",
                    skuId, quantity, warehouseId, result);
            return Result.success(result);
        } catch (Exception e) {
            log.error("Failed to check stock: SKU={}, Warehouse={}", skuId, warehouseId, e);
            return Result.failed("Stock check failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Map<Long, Boolean>> batchCheckStock(Map<Long, Integer> skuQuantityMap) {
        try {
            Map<Long, Boolean> results = stockService.batchCheckStock(skuQuantityMap);
            log.debug("Batch stock check completed, count: {}", results.size());
            return Result.success(results);
        } catch (Exception e) {
            log.error("Failed to batch check stock", e);
            return Result.failed("Batch stock check failed: " + e.getMessage());
        }
    }

    @Override
    public Result<StockVO> getStockBySkuId(Long skuId) {
        try {
            StockVO result = stockService.getStockBySkuId(skuId);
            if (result == null) {
                log.warn("Stock not found for SKU: {}", skuId);
                return Result.success(null);
            }
            return Result.success(result);
        } catch (Exception e) {
            log.error("Failed to get stock by SKU ID: {}", skuId, e);
            return Result.failed("Failed to get stock: " + e.getMessage());
        }
    }

    @Override
    public Result<StockVO> getStockBySkuAndWarehouse(Long skuId, Long warehouseId) {
        try {
            StockVO result = stockService.getStockBySkuAndWarehouse(skuId, warehouseId);
            if (result == null) {
                log.warn("Stock not found for SKU: {}, Warehouse: {}", skuId, warehouseId);
                return Result.success(null);
            }
            return Result.success(result);
        } catch (Exception e) {
            log.error("Failed to get stock: SKU={}, Warehouse={}", skuId, warehouseId, e);
            return Result.failed("Failed to get stock: " + e.getMessage());
        }
    }

    @Override
    public Result<List<StockVO>> getStocksBySkuIds(List<Long> skuIds) {
        try {
            List<StockVO> results = stockService.getStocksBySkuIds(skuIds);
            log.debug("Retrieved stocks for {} SKUs", skuIds.size());
            return Result.success(results);
        } catch (Exception e) {
            log.error("Failed to get stocks by SKU IDs", e);
            return Result.failed("Failed to get stocks: " + e.getMessage());
        }
    }

    @Override
    public Result<Integer> getAvailableStock(Long skuId) {
        try {
            Integer availableStock = stockService.getAvailableStock(skuId);
            log.debug("Available stock for SKU {}: {}", skuId, availableStock);
            return Result.success(availableStock);
        } catch (Exception e) {
            log.error("Failed to get available stock for SKU: {}", skuId, e);
            return Result.failed("Failed to get available stock: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> transferStock(StockTransferRequest request) {
        try {
            log.debug("Transferring stock: {}", request);
            stockService.transferStock(request);
            log.info("Stock transfer completed: from warehouse {} to warehouse {}",
                    request.getSourceWarehouseId(), request.getTargetWarehouseId());
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to transfer stock: {}", request, e);
            return Result.failed("Stock transfer failed: " + e.getMessage());
        }
    }

    @Override
    public Result<Void> inventoryCheck(InventoryCheckRequest request) {
        try {
            log.debug("Performing inventory check: warehouse={}, items={}",
                    request.getWarehouseId(), request.getItems().size());
            stockService.inventoryCheck(request);
            log.info("Inventory check completed for warehouse: {}", request.getWarehouseId());
            return Result.success();
        } catch (Exception e) {
            log.error("Failed to perform inventory check: {}", request, e);
            return Result.failed("Inventory check failed: " + e.getMessage());
        }
    }

    @Override
    public Result<List<StockLockVO>> getStockLocksByOrderNo(String orderNo) {
        try {
            List<StockLockVO> results = stockService.getStockLocksByOrderNo(orderNo);
            log.debug("Retrieved {} stock locks for order: {}", results.size(), orderNo);
            return Result.success(results);
        } catch (Exception e) {
            log.error("Failed to get stock locks for order: {}", orderNo, e);
            return Result.failed("Failed to get stock locks: " + e.getMessage());
        }
    }

    @Override
    public Result<Integer> releaseExpiredLocks() {
        try {
            log.info("Starting to release expired stock locks");
            Integer count = stockService.releaseExpiredLocks();
            log.info("Released {} expired stock locks", count);
            return Result.success(count);
        } catch (Exception e) {
            log.error("Failed to release expired locks", e);
            return Result.failed("Failed to release expired locks: " + e.getMessage());
        }
    }
}