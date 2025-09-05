package com.mall.inventory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.inventory.dto.request.*;
import com.mall.api.inventory.dto.response.*;
import com.mall.api.inventory.enums.LockStatusEnum;
import com.mall.api.inventory.enums.StockOperationEnum;
import com.mall.api.inventory.enums.StockSourceEnum;
import com.mall.api.inventory.event.LowStockWarningEvent;
import com.mall.api.inventory.event.OutOfStockEvent;
import com.mall.api.inventory.event.StockChangedEvent;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.PageResult;
import com.mall.inventory.converter.StockConverter;
import com.mall.inventory.entity.Stock;
import com.mall.inventory.entity.StockLock;
import com.mall.inventory.entity.StockRecord;
import com.mall.inventory.entity.Warehouse;
import com.mall.inventory.mapper.StockLockMapper;
import com.mall.inventory.mapper.StockMapper;
import com.mall.inventory.mapper.StockRecordMapper;
import com.mall.inventory.mapper.WarehouseMapper;
import com.mall.inventory.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockMapper stockMapper;
    private final StockLockMapper stockLockMapper;
    private final StockRecordMapper stockRecordMapper;
    private final WarehouseMapper warehouseMapper;
    private final StockConverter stockConverter;
    private final RedissonClient redissonClient;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RabbitTemplate rabbitTemplate;

    private static final String STOCK_LOCK_KEY = "stock:lock:";
    private static final String STOCK_CACHE_KEY = "stock:cache:";
    private static final String LOW_STOCK_WARNING_EXCHANGE = "inventory.warning.exchange";
    private static final String STOCK_CHANGED_EXCHANGE = "inventory.changed.exchange";

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "stock", key = "#request.skuId")
    public StockVO updateStock(StockUpdateRequest request) {
        String lockKey = STOCK_LOCK_KEY + request.getSkuId() + ":" + request.getWarehouseId();
        RLock lock = redissonClient.getLock(lockKey);

        try {
            // 尝试获取锁，最多等待5秒，锁定10秒后自动释放
            if (!lock.tryLock(5, 10, TimeUnit.SECONDS)) {
                throw new BusinessException("Failed to acquire stock lock for SKU: " + request.getSkuId());
            }

            // 获取或创建库存记录
            Stock stock = getOrCreateStock(request.getSkuId(), request.getWarehouseId());

            // 记录变更前的库存
            Integer stockBefore = stock.getTotalStock();
            Integer availableBefore = stock.getAvailableStock();

            // 执行库存操作
            StockOperationEnum operation = StockOperationEnum.of(request.getOperationType());
            if (operation == null) {
                throw new BusinessException("Invalid operation type: " + request.getOperationType());
            }

            switch (operation) {
                case ADD:
                    handleAddStock(stock, request.getQuantity());
                    break;
                case DEDUCT:
                    handleDeductStock(stock, request.getQuantity());
                    break;
                case LOCK:
                    handleLockStock(stock, request.getQuantity());
                    break;
                case UNLOCK:
                    handleUnlockStock(stock, request.getQuantity());
                    break;
                case TRANSFER_IN:
                    handleTransferInStock(stock, request.getQuantity());
                    break;
                case TRANSFER_OUT:
                    handleTransferOutStock(stock, request.getQuantity());
                    break;
                case ADJUST_INCREASE:
                    handleAdjustIncreaseStock(stock, request.getQuantity());
                    break;
                case ADJUST_DECREASE:
                    handleAdjustDecreaseStock(stock, request.getQuantity());
                    break;
                default:
                    throw new BusinessException("Unsupported operation type: " + operation);
            }

            // 更新库存记录
            stock.setUpdateTime(LocalDateTime.now());
            stockMapper.updateById(stock);

            // 记录库存变更日志
            saveStockRecord(request, stockBefore, stock.getTotalStock(), availableBefore, stock.getAvailableStock());

            // 检查库存预警
            checkStockWarning(stock);

            // 发送库存变更事件
            publishStockChangedEvent(stock, operation, request);

            // 清除缓存
            clearStockCache(request.getSkuId());

            return stockConverter.toVO(stock);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BusinessException("Stock operation interrupted for SKU: " + request.getSkuId());
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StockVO> batchUpdateStock(BatchStockUpdateRequest request) {
        List<StockVO> results = new ArrayList<>();

        if (request.getTransactional()) {
            // 事务性批量更新 - 任何一个失败都回滚
            for (StockUpdateRequest updateRequest : request.getStockUpdates()) {
                results.add(updateStock(updateRequest));
            }
        } else {
            // 非事务性批量更新 - 独立处理每个更新
            List<CompletableFuture<StockVO>> futures = request.getStockUpdates().stream()
                    .map(updateRequest -> CompletableFuture.supplyAsync(() -> {
                        try {
                            return updateStock(updateRequest);
                        } catch (Exception e) {
                            log.error("Failed to update stock for SKU: {}, error: {}",
                                    updateRequest.getSkuId(), e.getMessage());
                            return null;
                        }
                    }))
                    .collect(Collectors.toList());

            // 等待所有异步操作完成
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

            // 收集结果
            futures.forEach(future -> {
                StockVO result = future.getNow(null);
                if (result != null) {
                    results.add(result);
                }
            });
        }

        return results;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StockLockVO lockStock(StockLockRequest request) {
        String lockKey = STOCK_LOCK_KEY + request.getSkuId() + ":" + request.getWarehouseId();
        RLock lock = redissonClient.getLock(lockKey);

        try {
            if (!lock.tryLock(5, 10, TimeUnit.SECONDS)) {
                throw new BusinessException("Failed to acquire lock for stock locking");
            }

            // 获取仓库ID，如果未指定则使用默认仓库
            Long warehouseId = request.getWarehouseId();
            if (warehouseId == null) {
                warehouseId = getDefaultWarehouseId();
            }

            // 获取库存记录
            Stock stock = getOrCreateStock(request.getSkuId(), warehouseId);

            // 检查可用库存
            if (stock.getAvailableStock() < request.getQuantity()) {
                throw new BusinessException(String.format(
                        "Insufficient stock for SKU %d, available: %d, requested: %d",
                        request.getSkuId(), stock.getAvailableStock(), request.getQuantity()
                ));
            }

            // 锁定库存
            stock.setAvailableStock(stock.getAvailableStock() - request.getQuantity());
            stock.setLockedStock(stock.getLockedStock() + request.getQuantity());
            stockMapper.updateById(stock);

            // 创建锁定记录
            StockLock stockLock = new StockLock();
            stockLock.setSkuId(request.getSkuId());
            stockLock.setWarehouseId(warehouseId);
            stockLock.setOrderNo(request.getOrderNo());
            stockLock.setUserId(request.getUserId());
            stockLock.setLockedQuantity(request.getQuantity());
            stockLock.setLockStatus(LockStatusEnum.LOCKED.getCode());
            stockLock.setLockTime(LocalDateTime.now());

            // 设置过期时间，默认30分钟
            LocalDateTime expireTime = request.getExpireTime();
            if (expireTime == null) {
                expireTime = LocalDateTime.now().plusMinutes(30);
            }
            stockLock.setExpireTime(expireTime);

            stockLockMapper.insert(stockLock);

            // 记录库存变更
            StockUpdateRequest updateRequest = new StockUpdateRequest();
            updateRequest.setSkuId(request.getSkuId());
            updateRequest.setWarehouseId(warehouseId);
            updateRequest.setQuantity(request.getQuantity());
            updateRequest.setOperationType(StockOperationEnum.LOCK.getCode());
            updateRequest.setBusinessType("ORDER_LOCK");
            updateRequest.setBusinessNo(request.getOrderNo());
            updateRequest.setRemark("Order stock lock");

            saveStockRecord(updateRequest, stock.getTotalStock(), stock.getTotalStock(),
                    stock.getAvailableStock() + request.getQuantity(), stock.getAvailableStock());

            log.info("Stock locked successfully: SKU={}, Quantity={}, OrderNo={}",
                    request.getSkuId(), request.getQuantity(), request.getOrderNo());

            return stockConverter.toLockVO(stockLock);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BusinessException("Stock lock operation interrupted");
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<StockLockVO> batchLockStock(List<StockLockRequest> requests) {
        List<StockLockVO> results = new ArrayList<>();

        // 按SKU分组，避免死锁
        Map<Long, List<StockLockRequest>> groupedRequests = requests.stream()
                .collect(Collectors.groupingBy(StockLockRequest::getSkuId));

        // 按SKU ID排序，避免死锁
        List<Long> sortedSkuIds = new ArrayList<>(groupedRequests.keySet());
        Collections.sort(sortedSkuIds);

        for (Long skuId : sortedSkuIds) {
            for (StockLockRequest request : groupedRequests.get(skuId)) {
                results.add(lockStock(request));
            }
        }

        return results;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unlockStock(String orderNo) {
        if (!StringUtils.hasText(orderNo)) {
            throw new BusinessException("Order number cannot be empty");
        }

        // 查询该订单的所有锁定记录
        List<StockLock> locks = stockLockMapper.selectList(
                new LambdaQueryWrapper<StockLock>()
                        .eq(StockLock::getOrderNo, orderNo)
                        .eq(StockLock::getLockStatus, LockStatusEnum.LOCKED.getCode())
        );

        if (CollectionUtils.isEmpty(locks)) {
            log.warn("No locked stock found for order: {}", orderNo);
            return;
        }

        // 逐个释放锁定的库存
        for (StockLock lock : locks) {
            try {
                unlockStockById(lock.getId());
            } catch (Exception e) {
                log.error("Failed to unlock stock for order: {}, lockId: {}", orderNo, lock.getId(), e);
                // 继续处理其他锁定记录
            }
        }

        log.info("Successfully unlocked {} stock records for order: {}", locks.size(), orderNo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void unlockStockById(Long lockId) {
        if (lockId == null) {
            throw new BusinessException("Lock ID cannot be null");
        }

        StockLock stockLock = stockLockMapper.selectById(lockId);
        if (stockLock == null) {
            log.warn("Stock lock not found: {}", lockId);
            return;
        }

        // 检查锁定状态
        if (!LockStatusEnum.LOCKED.getCode().equals(stockLock.getLockStatus())) {
            log.info("Stock lock is not in locked status: {}, status: {}", lockId, stockLock.getLockStatus());
            return;
        }

        String lockKey = STOCK_LOCK_KEY + stockLock.getSkuId() + ":" + stockLock.getWarehouseId();
        RLock lock = redissonClient.getLock(lockKey);

        try {
            if (!lock.tryLock(5, 10, TimeUnit.SECONDS)) {
                throw new BusinessException("Failed to acquire lock for stock unlocking");
            }

            // 获取库存记录
            Stock stock = stockMapper.selectOne(
                    new LambdaQueryWrapper<Stock>()
                            .eq(Stock::getSkuId, stockLock.getSkuId())
                            .eq(Stock::getWarehouseId, stockLock.getWarehouseId())
            );

            if (stock == null) {
                throw new BusinessException("Stock not found for SKU: " + stockLock.getSkuId());
            }

            // 释放库存
            stock.setAvailableStock(stock.getAvailableStock() + stockLock.getLockedQuantity());
            stock.setLockedStock(Math.max(0, stock.getLockedStock() - stockLock.getLockedQuantity()));
            stockMapper.updateById(stock);

            // 更新锁定记录状态
            stockLock.setLockStatus(LockStatusEnum.RELEASED.getCode());
            stockLock.setReleaseTime(LocalDateTime.now());
            stockLockMapper.updateById(stockLock);

            // 记录库存变更
            StockUpdateRequest updateRequest = new StockUpdateRequest();
            updateRequest.setSkuId(stockLock.getSkuId());
            updateRequest.setWarehouseId(stockLock.getWarehouseId());
            updateRequest.setQuantity(stockLock.getLockedQuantity());
            updateRequest.setOperationType(StockOperationEnum.UNLOCK.getCode());
            updateRequest.setBusinessType("ORDER_UNLOCK");
            updateRequest.setBusinessNo(stockLock.getOrderNo());
            updateRequest.setRemark("Stock unlock for order: " + stockLock.getOrderNo());

            saveStockRecord(updateRequest, stock.getTotalStock(), stock.getTotalStock(),
                    stock.getAvailableStock() - stockLock.getLockedQuantity(), stock.getAvailableStock());

            log.info("Stock unlocked successfully: lockId={}, SKU={}, Quantity={}",
                    lockId, stockLock.getSkuId(), stockLock.getLockedQuantity());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BusinessException("Stock unlock operation interrupted");
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deductLockedStock(String orderNo) {
        if (!StringUtils.hasText(orderNo)) {
            throw new BusinessException("Order number cannot be empty");
        }

        // 查询该订单的所有锁定记录
        List<StockLock> locks = stockLockMapper.selectList(
                new LambdaQueryWrapper<StockLock>()
                        .eq(StockLock::getOrderNo, orderNo)
                        .eq(StockLock::getLockStatus, LockStatusEnum.LOCKED.getCode())
        );

        if (CollectionUtils.isEmpty(locks)) {
            throw new BusinessException("No locked stock found for order: " + orderNo);
        }

        // 按SKU和仓库分组处理
        Map<String, List<StockLock>> groupedLocks = locks.stream()
                .collect(Collectors.groupingBy(lock -> lock.getSkuId() + "_" + lock.getWarehouseId()));

        for (Map.Entry<String, List<StockLock>> entry : groupedLocks.entrySet()) {
            List<StockLock> stockLocks = entry.getValue();
            StockLock firstLock = stockLocks.get(0);

            String lockKey = STOCK_LOCK_KEY + firstLock.getSkuId() + ":" + firstLock.getWarehouseId();
            RLock lock = redissonClient.getLock(lockKey);

            try {
                if (!lock.tryLock(5, 10, TimeUnit.SECONDS)) {
                    throw new BusinessException("Failed to acquire lock for stock deduction");
                }

                // 获取库存记录
                Stock stock = stockMapper.selectOne(
                        new LambdaQueryWrapper<Stock>()
                                .eq(Stock::getSkuId, firstLock.getSkuId())
                                .eq(Stock::getWarehouseId, firstLock.getWarehouseId())
                );

                if (stock == null) {
                    throw new BusinessException("Stock not found for SKU: " + firstLock.getSkuId());
                }

                // 计算总扣减数量
                int totalQuantity = stockLocks.stream()
                        .mapToInt(StockLock::getLockedQuantity)
                        .sum();

                // 扣减库存
                if (stock.getLockedStock() < totalQuantity) {
                    throw new BusinessException("Insufficient locked stock for deduction");
                }

                stock.setTotalStock(stock.getTotalStock() - totalQuantity);
                stock.setLockedStock(stock.getLockedStock() - totalQuantity);
                stockMapper.updateById(stock);

                // 更新锁定记录状态
                for (StockLock stockLock : stockLocks) {
                    stockLock.setLockStatus(LockStatusEnum.DEDUCTED.getCode());
                    stockLock.setReleaseTime(LocalDateTime.now());
                    stockLockMapper.updateById(stockLock);
                }

                // 记录库存变更
                StockUpdateRequest updateRequest = new StockUpdateRequest();
                updateRequest.setSkuId(firstLock.getSkuId());
                updateRequest.setWarehouseId(firstLock.getWarehouseId());
                updateRequest.setQuantity(totalQuantity);
                updateRequest.setOperationType(StockOperationEnum.DEDUCT.getCode());
                updateRequest.setBusinessType("ORDER_DEDUCT");
                updateRequest.setBusinessNo(orderNo);
                updateRequest.setRemark("Stock deduction for paid order: " + orderNo);

                saveStockRecord(updateRequest, stock.getTotalStock() + totalQuantity, stock.getTotalStock(),
                        stock.getAvailableStock(), stock.getAvailableStock());

                log.info("Stock deducted successfully: SKU={}, Quantity={}, OrderNo={}",
                        firstLock.getSkuId(), totalQuantity, orderNo);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new BusinessException("Stock deduction operation interrupted");
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnStock(Long skuId, Integer quantity, String orderNo) {
        if (skuId == null || quantity == null || quantity <= 0) {
            throw new BusinessException("Invalid parameters for stock return");
        }

        // 创建库存更新请求
        StockUpdateRequest request = new StockUpdateRequest();
        request.setSkuId(skuId);
        request.setQuantity(quantity);
        request.setOperationType(StockOperationEnum.ADD.getCode());
        request.setBusinessType(StockSourceEnum.RETURN.getCode());
        request.setBusinessNo(orderNo);
        request.setRemark("Stock return for order: " + orderNo);

        // 如果有订单号，尝试找到原仓库
        if (StringUtils.hasText(orderNo)) {
            List<StockRecord> records = stockRecordMapper.selectList(
                    new LambdaQueryWrapper<StockRecord>()
                            .eq(StockRecord::getBusinessNo, orderNo)
                            .eq(StockRecord::getSkuId, skuId)
                            .orderByDesc(StockRecord::getCreateTime)
                            .last("LIMIT 1")
            );

            if (!CollectionUtils.isEmpty(records)) {
                request.setWarehouseId(records.get(0).getWarehouseId());
            }
        }

        // 如果没有找到仓库，使用默认仓库
        if (request.getWarehouseId() == null) {
            request.setWarehouseId(getDefaultWarehouseId());
        }

        // 更新库存
        updateStock(request);

        log.info("Stock returned successfully: SKU={}, Quantity={}, OrderNo={}",
                skuId, quantity, orderNo);
    }

    @Override
    @Cacheable(value = "stock", key = "#skuId")
    public StockVO getStockBySkuId(Long skuId) {
        if (skuId == null) {
            return null;
        }

        // 查询所有仓库的库存
        List<Stock> stocks = stockMapper.selectList(
                new LambdaQueryWrapper<Stock>()
                        .eq(Stock::getSkuId, skuId)
                        .eq(Stock::getDeleted, 0)
        );

        if (CollectionUtils.isEmpty(stocks)) {
            return null;
        }

        // 如果只有一个仓库，直接返回
        if (stocks.size() == 1) {
            return stockConverter.toVO(stocks.get(0));
        }

        // 合并多个仓库的库存
        Stock mergedStock = new Stock();
        mergedStock.setSkuId(skuId);
        mergedStock.setTotalStock(0);
        mergedStock.setAvailableStock(0);
        mergedStock.setLockedStock(0);
        mergedStock.setInTransitStock(0);

        for (Stock stock : stocks) {
            mergedStock.setTotalStock(mergedStock.getTotalStock() + stock.getTotalStock());
            mergedStock.setAvailableStock(mergedStock.getAvailableStock() + stock.getAvailableStock());
            mergedStock.setLockedStock(mergedStock.getLockedStock() + stock.getLockedStock());
            mergedStock.setInTransitStock(mergedStock.getInTransitStock() + stock.getInTransitStock());

            // 使用最小的预警库存
            if (mergedStock.getWarnStock() == null ||
                    (stock.getWarnStock() != null && stock.getWarnStock() < mergedStock.getWarnStock())) {
                mergedStock.setWarnStock(stock.getWarnStock());
            }

            // 使用最小的安全库存
            if (mergedStock.getSafetyStock() == null ||
                    (stock.getSafetyStock() != null && stock.getSafetyStock() < mergedStock.getSafetyStock())) {
                mergedStock.setSafetyStock(stock.getSafetyStock());
            }
        }

        return stockConverter.toVO(mergedStock);
    }

    @Override
    public StockVO getStockBySkuAndWarehouse(Long skuId, Long warehouseId) {
        if (skuId == null || warehouseId == null) {
            return null;
        }

        Stock stock = stockMapper.selectOne(
                new LambdaQueryWrapper<Stock>()
                        .eq(Stock::getSkuId, skuId)
                        .eq(Stock::getWarehouseId, warehouseId)
                        .eq(Stock::getDeleted, 0)
        );

        return stock != null ? stockConverter.toVO(stock) : null;
    }

    @Override
    public List<StockVO> getStocksBySkuIds(List<Long> skuIds) {
        if (CollectionUtils.isEmpty(skuIds)) {
            return new ArrayList<>();
        }

        List<StockVO> results = new ArrayList<>();
        for (Long skuId : skuIds) {
            StockVO stock = getStockBySkuId(skuId);
            if (stock != null) {
                results.add(stock);
            }
        }

        return results;
    }

    @Override
    public Integer getAvailableStock(Long skuId) {
        StockVO stock = getStockBySkuId(skuId);
        return stock != null ? stock.getAvailableStock() : 0;
    }

    @Override
    public boolean checkStock(Long skuId, Integer quantity) {
        if (skuId == null || quantity == null || quantity <= 0) {
            return false;
        }

        Integer availableStock = getAvailableStock(skuId);
        return availableStock >= quantity;
    }

    @Override
    public boolean checkStockWithWarehouse(Long skuId, Integer quantity, Long warehouseId) {
        if (skuId == null || quantity == null || quantity <= 0 || warehouseId == null) {
            return false;
        }

        StockVO stock = getStockBySkuAndWarehouse(skuId, warehouseId);
        return stock != null && stock.getAvailableStock() >= quantity;
    }

    @Override
    public Map<Long, Boolean> batchCheckStock(Map<Long, Integer> skuQuantityMap) {
        Map<Long, Boolean> results = new HashMap<>();

        if (skuQuantityMap == null || skuQuantityMap.isEmpty()) {
            return results;
        }

        for (Map.Entry<Long, Integer> entry : skuQuantityMap.entrySet()) {
            results.put(entry.getKey(), checkStock(entry.getKey(), entry.getValue()));
        }

        return results;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transferStock(StockTransferRequest request) {
        if (request == null || request.getSourceWarehouseId() == null ||
                request.getTargetWarehouseId() == null || CollectionUtils.isEmpty(request.getItems())) {
            throw new BusinessException("Invalid transfer request");
        }

        if (request.getSourceWarehouseId().equals(request.getTargetWarehouseId())) {
            throw new BusinessException("Source and target warehouse cannot be the same");
        }

        for (StockTransferRequest.TransferItem item : request.getItems()) {
            // 从源仓库扣减库存
            StockUpdateRequest deductRequest = new StockUpdateRequest();
            deductRequest.setSkuId(item.getSkuId());
            deductRequest.setWarehouseId(request.getSourceWarehouseId());
            deductRequest.setQuantity(item.getQuantity());
            deductRequest.setOperationType(StockOperationEnum.TRANSFER_OUT.getCode());
            deductRequest.setBusinessType("TRANSFER");
            deductRequest.setBusinessNo("TRANSFER_" + System.currentTimeMillis());
            deductRequest.setRemark(request.getReason());
            deductRequest.setOperatorId(request.getOperatorId());
            deductRequest.setOperatorName(request.getOperatorName());

            updateStock(deductRequest);

            // 向目标仓库增加库存
            StockUpdateRequest addRequest = new StockUpdateRequest();
            addRequest.setSkuId(item.getSkuId());
            addRequest.setWarehouseId(request.getTargetWarehouseId());
            addRequest.setQuantity(item.getQuantity());
            addRequest.setOperationType(StockOperationEnum.TRANSFER_IN.getCode());
            addRequest.setBusinessType("TRANSFER");
            addRequest.setBusinessNo(deductRequest.getBusinessNo());
            addRequest.setRemark(request.getReason());
            addRequest.setOperatorId(request.getOperatorId());
            addRequest.setOperatorName(request.getOperatorName());

            updateStock(addRequest);
        }

        log.info("Stock transfer completed: from warehouse {} to warehouse {}, items: {}",
                request.getSourceWarehouseId(), request.getTargetWarehouseId(), request.getItems().size());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void inventoryCheck(InventoryCheckRequest request) {
        if (request == null || request.getWarehouseId() == null || CollectionUtils.isEmpty(request.getItems())) {
            throw new BusinessException("Invalid inventory check request");
        }

        for (InventoryCheckRequest.CheckItem item : request.getItems()) {
            if (item.getSystemStock().equals(item.getActualStock())) {
                // 库存一致，无需调整
                continue;
            }

            Stock stock = stockMapper.selectOne(
                    new LambdaQueryWrapper<Stock>()
                            .eq(Stock::getSkuId, item.getSkuId())
                            .eq(Stock::getWarehouseId, request.getWarehouseId())
            );

            if (stock == null) {
                // 创建新的库存记录
                stock = new Stock();
                stock.setSkuId(item.getSkuId());
                stock.setWarehouseId(request.getWarehouseId());
                stock.setTotalStock(item.getActualStock());
                stock.setAvailableStock(item.getActualStock());
                stock.setLockedStock(0);
                stock.setInTransitStock(0);
                stock.setStatus(1);
                stockMapper.insert(stock);
            } else {
                // 计算差异
                int difference = item.getActualStock() - item.getSystemStock();

                StockUpdateRequest updateRequest = new StockUpdateRequest();
                updateRequest.setSkuId(item.getSkuId());
                updateRequest.setWarehouseId(request.getWarehouseId());
                updateRequest.setQuantity(Math.abs(difference));

                if (difference > 0) {
                    updateRequest.setOperationType(StockOperationEnum.ADJUST_INCREASE.getCode());
                } else {
                    updateRequest.setOperationType(StockOperationEnum.ADJUST_DECREASE.getCode());
                }

                updateRequest.setBusinessType(StockSourceEnum.INVENTORY.getCode());
                updateRequest.setBusinessNo("INV_CHECK_" + System.currentTimeMillis());
                updateRequest.setRemark("Inventory check: " + item.getDifferenceReason());
                updateRequest.setOperatorId(request.getOperatorId());
                updateRequest.setOperatorName(request.getOperatorName());

                updateStock(updateRequest);
            }
        }

        log.info("Inventory check completed for warehouse: {}, checked items: {}",
                request.getWarehouseId(), request.getItems().size());
    }

    @Override
    public List<StockLockVO> getStockLocksByOrderNo(String orderNo) {
        if (!StringUtils.hasText(orderNo)) {
            return new ArrayList<>();
        }

        List<StockLock> locks = stockLockMapper.selectList(
                new LambdaQueryWrapper<StockLock>()
                        .eq(StockLock::getOrderNo, orderNo)
                        .orderByDesc(StockLock::getCreateTime)
        );

        return stockConverter.toLockVOList(locks);
    }

    @Override
    public Integer releaseExpiredLocks() {
        // 查询过期的锁定记录
        List<StockLock> expiredLocks = stockLockMapper.selectList(
                new LambdaQueryWrapper<StockLock>()
                        .eq(StockLock::getLockStatus, LockStatusEnum.LOCKED.getCode())
                        .lt(StockLock::getExpireTime, LocalDateTime.now())
                        .last("LIMIT 100")
        );

        if (CollectionUtils.isEmpty(expiredLocks)) {
            return 0;
        }

        int successCount = 0;
        for (StockLock lock : expiredLocks) {
            try {
                unlockStockById(lock.getId());
                successCount++;
                log.info("Released expired lock: id={}, SKU={}, orderNo={}",
                        lock.getId(), lock.getSkuId(), lock.getOrderNo());
            } catch (Exception e) {
                log.error("Failed to release expired lock: {}", lock.getId(), e);
            }
        }

        log.info("Released {} expired locks out of {} found", successCount, expiredLocks.size());
        return successCount;
    }

    @Override
    public PageResult<StockVO> queryStocks(StockQueryRequest request) {
        Page<Stock> page = new Page<>(request.getCurrent(), request.getSize());

        LambdaQueryWrapper<Stock> wrapper = new LambdaQueryWrapper<>();

        // 构建查询条件
        if (request.getSkuId() != null) {
            wrapper.eq(Stock::getSkuId, request.getSkuId());
        }

        if (StringUtils.hasText(request.getSkuCode())) {
            // 需要关联查询SKU表获取SKU编码，这里简化处理
            wrapper.eq(Stock::getSkuId, request.getSkuCode());
        }

        if (request.getWarehouseId() != null) {
            wrapper.eq(Stock::getWarehouseId, request.getWarehouseId());
        }

        if (request.getMinStock() != null) {
            wrapper.ge(Stock::getTotalStock, request.getMinStock());
        }

        if (request.getMaxStock() != null) {
            wrapper.le(Stock::getTotalStock, request.getMaxStock());
        }

        if (request.getHasStock() != null && request.getHasStock()) {
            wrapper.gt(Stock::getAvailableStock, 0);
        }

        if (request.getIsLowStock() != null && request.getIsLowStock()) {
            wrapper.apply("available_stock <= warn_stock");
        }

        if (request.getStartTime() != null) {
            wrapper.ge(Stock::getUpdateTime, request.getStartTime());
        }

        if (request.getEndTime() != null) {
            wrapper.le(Stock::getUpdateTime, request.getEndTime());
        }

        wrapper.eq(Stock::getDeleted, 0);

        // 排序
        if (StringUtils.hasText(request.getOrderBy())) {
            boolean isAsc = "asc".equalsIgnoreCase(request.getOrderDirection());
            switch (request.getOrderBy()) {
                case "totalStock":
                    wrapper.orderBy(true, isAsc, Stock::getTotalStock);
                    break;
                case "availableStock":
                    wrapper.orderBy(true, isAsc, Stock::getAvailableStock);
                    break;
                case "updateTime":
                    wrapper.orderBy(true, isAsc, Stock::getUpdateTime);
                    break;
                default:
                    wrapper.orderByDesc(Stock::getUpdateTime);
            }
        } else {
            wrapper.orderByDesc(Stock::getUpdateTime);
        }

        Page<Stock> result = stockMapper.selectPage(page, wrapper);
        List<StockVO> records = stockConverter.toVOList(result.getRecords());

        return PageResult.of(result.getCurrent(), result.getSize(), result.getTotal(), records);
    }

    // ==================== 私有方法 ====================

    private Stock getOrCreateStock(Long skuId, Long warehouseId) {
        Long whId = warehouseId != null ? warehouseId : getDefaultWarehouseId();

        Stock stock = stockMapper.selectOne(
                new LambdaQueryWrapper<Stock>()
                        .eq(Stock::getSkuId, skuId)
                        .eq(Stock::getWarehouseId, whId)
                        .eq(Stock::getDeleted, 0)
        );

        if (stock == null) {
            stock = new Stock();
            stock.setSkuId(skuId);
            stock.setWarehouseId(whId);
            stock.setTotalStock(0);
            stock.setAvailableStock(0);
            stock.setLockedStock(0);
            stock.setInTransitStock(0);
            stock.setWarnStock(10);
            stock.setSafetyStock(5);
            stock.setStatus(1);
            stock.setVersion(0);
            stockMapper.insert(stock);
        }

        return stock;
    }

    private Long getDefaultWarehouseId() {
        // 获取默认仓库
        Warehouse warehouse = warehouseMapper.selectOne(
                new LambdaQueryWrapper<Warehouse>()
                        .eq(Warehouse::getWarehouseType, 1)
                        .eq(Warehouse::getStatus, 1)
                        .eq(Warehouse::getDeleted, 0)
                        .orderByAsc(Warehouse::getId)
                        .last("LIMIT 1")
        );

        if (warehouse == null) {
            // 如果没有默认仓库，使用ID为1的仓库
            return 1L;
        }

        return warehouse.getId();
    }

    private void handleAddStock(Stock stock, Integer quantity) {
        stock.setTotalStock(stock.getTotalStock() + quantity);
        stock.setAvailableStock(stock.getAvailableStock() + quantity);
    }

    private void handleDeductStock(Stock stock, Integer quantity) {
        if (stock.getAvailableStock() < quantity) {
            throw new BusinessException("Insufficient available stock, available: " +
                    stock.getAvailableStock() + ", requested: " + quantity);
        }
        stock.setTotalStock(stock.getTotalStock() - quantity);
        stock.setAvailableStock(stock.getAvailableStock() - quantity);
    }

    private void handleLockStock(Stock stock, Integer quantity) {
        if (stock.getAvailableStock() < quantity) {
            throw new BusinessException("Insufficient available stock for lock");
        }
        stock.setAvailableStock(stock.getAvailableStock() - quantity);
        stock.setLockedStock(stock.getLockedStock() + quantity);
    }

    private void handleUnlockStock(Stock stock, Integer quantity) {
        if (stock.getLockedStock() < quantity) {
            throw new BusinessException("Insufficient locked stock for unlock");
        }
        stock.setAvailableStock(stock.getAvailableStock() + quantity);
        stock.setLockedStock(stock.getLockedStock() - quantity);
    }

    private void handleTransferInStock(Stock stock, Integer quantity) {
        stock.setTotalStock(stock.getTotalStock() + quantity);
        stock.setAvailableStock(stock.getAvailableStock() + quantity);
    }

    private void handleTransferOutStock(Stock stock, Integer quantity) {
        if (stock.getAvailableStock() < quantity) {
            throw new BusinessException("Insufficient available stock for transfer");
        }
        stock.setTotalStock(stock.getTotalStock() - quantity);
        stock.setAvailableStock(stock.getAvailableStock() - quantity);
    }

    private void handleAdjustIncreaseStock(Stock stock, Integer quantity) {
        stock.setTotalStock(stock.getTotalStock() + quantity);
        stock.setAvailableStock(stock.getAvailableStock() + quantity);
    }

    private void handleAdjustDecreaseStock(Stock stock, Integer quantity) {
        if (stock.getAvailableStock() < quantity) {
            // 盘点时发现实际库存少于系统库存，强制调整
            int diff = quantity - stock.getAvailableStock();
            stock.setAvailableStock(0);
            stock.setTotalStock(Math.max(0, stock.getTotalStock() - quantity));

            // 如果锁定库存也需要调整
            if (diff > 0 && stock.getLockedStock() > 0) {
                stock.setLockedStock(Math.max(0, stock.getLockedStock() - diff));
            }
        } else {
            stock.setTotalStock(stock.getTotalStock() - quantity);
            stock.setAvailableStock(stock.getAvailableStock() - quantity);
        }
    }

    private void saveStockRecord(StockUpdateRequest request, Integer stockBefore, Integer stockAfter,
                                 Integer availableBefore, Integer availableAfter) {
        StockRecord record = new StockRecord();
        record.setSkuId(request.getSkuId());
        record.setWarehouseId(request.getWarehouseId());
        record.setOperationType(request.getOperationType());
        record.setChangeQuantity(request.getQuantity());
        record.setStockBefore(stockBefore);
        record.setStockAfter(stockAfter);
        record.setBusinessType(request.getBusinessType());
        record.setBusinessNo(request.getBusinessNo());
        record.setRemark(request.getRemark());
        record.setOperatorId(request.getOperatorId());
        record.setOperatorName(request.getOperatorName());
        stockRecordMapper.insert(record);
    }

    private void checkStockWarning(Stock stock) {
        // 检查是否低库存
        if (stock.getWarnStock() != null && stock.getAvailableStock() <= stock.getWarnStock()) {
            // 发送低库存预警事件
            LowStockWarningEvent event = new LowStockWarningEvent();
            event.setSkuId(stock.getSkuId());
            event.setWarehouseId(stock.getWarehouseId());
            event.setCurrentStock(stock.getAvailableStock());
            event.setWarningLevel(stock.getWarnStock());
            event.setSafetyLevel(stock.getSafetyStock());
            event.setEventTime(LocalDateTime.now());

            try {
                rabbitTemplate.convertAndSend(LOW_STOCK_WARNING_EXCHANGE, "stock.warning.low", event);
                log.warn("Low stock warning sent: SKU={}, available={}, warning={}",
                        stock.getSkuId(), stock.getAvailableStock(), stock.getWarnStock());
            } catch (Exception e) {
                log.error("Failed to send low stock warning event", e);
            }
        }

        // 检查是否缺货
        if (stock.getAvailableStock() <= 0) {
            // 发送缺货事件
            OutOfStockEvent event = new OutOfStockEvent();
            event.setSkuId(stock.getSkuId());
            event.setWarehouseId(stock.getWarehouseId());
            event.setLastStockQuantity(stock.getTotalStock());
            event.setEventTime(LocalDateTime.now());

            try {
                rabbitTemplate.convertAndSend(LOW_STOCK_WARNING_EXCHANGE, "stock.warning.out", event);
                log.error("Out of stock warning sent: SKU={}, warehouse={}",
                        stock.getSkuId(), stock.getWarehouseId());
            } catch (Exception e) {
                log.error("Failed to send out of stock event", e);
            }
        }
    }

    private void publishStockChangedEvent(Stock stock, StockOperationEnum operation, StockUpdateRequest request) {
        StockChangedEvent event = new StockChangedEvent();
        event.setSkuId(stock.getSkuId());
        event.setWarehouseId(stock.getWarehouseId());
        event.setOperationType(operation.getCode());
        event.setChangeQuantity(request.getQuantity());
        event.setStockBefore(stock.getTotalStock() - request.getQuantity());
        event.setStockAfter(stock.getTotalStock());
        event.setBusinessType(request.getBusinessType());
        event.setBusinessNo(request.getBusinessNo());
        event.setEventTime(LocalDateTime.now());

        try {
            rabbitTemplate.convertAndSend(STOCK_CHANGED_EXCHANGE, "stock.changed", event);
        } catch (Exception e) {
            log.error("Failed to send stock changed event", e);
        }
    }

    private void clearStockCache(Long skuId) {
        try {
            String cacheKey = STOCK_CACHE_KEY + skuId;
            redisTemplate.delete(cacheKey);
        } catch (Exception e) {
            log.error("Failed to clear stock cache for SKU: {}", skuId, e);
        }
    }
}