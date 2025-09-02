package com.mall.product.tesk;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.product.entity.StockRecord;
import com.mall.product.mapper.StockRecordMapper;
import com.mall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Stock Schedule Task
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StockScheduleTask {

    private final StockRecordMapper stockRecordMapper;
    private final ProductService productService;

    /**
     * Auto unlock expired stock locks (every 30 minutes)
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void autoUnlockExpiredStocks() {
        log.info("Start auto unlock expired stocks task");
        
        try {
            // Find expired lock records (locked for more than 30 minutes)
            LocalDateTime expireTime = LocalDateTime.now().minusMinutes(30);
            List<StockRecord> expiredRecords = stockRecordMapper.selectList(
                new LambdaQueryWrapper<StockRecord>()
                    .eq(StockRecord::getOperationType, 3) // Lock operation
                    .le(StockRecord::getCreateTime, expireTime)
                    .last("LIMIT 100")
            );
            
            for (StockRecord record : expiredRecords) {
                try {
                    productService.unlockStock(
                        record.getSkuId(),
                        record.getQuantity(),
                        record.getBusinessNo()
                    );
                    log.info("Auto unlocked stock for SKU: {}, quantity: {}", 
                        record.getSkuId(), record.getQuantity());
                } catch (Exception e) {
                    log.error("Failed to auto unlock stock for SKU: {}", record.getSkuId(), e);
                }
            }
            
            log.info("Completed auto unlock task, processed {} records", expiredRecords.size());
            
        } catch (Exception e) {
            log.error("Error in auto unlock task: ", e);
        }
    }

    /**
     * Clean old stock records (daily at 2 AM)
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void cleanOldStockRecords() {
        log.info("Start cleaning old stock records");
        
        try {
            // Delete records older than 90 days
            LocalDateTime cutoffTime = LocalDateTime.now().minusDays(90);
            int deleted = stockRecordMapper.delete(
                new LambdaQueryWrapper<StockRecord>()
                    .le(StockRecord::getCreateTime, cutoffTime)
            );
            
            log.info("Deleted {} old stock records", deleted);
            
        } catch (Exception e) {
            log.error("Error cleaning old stock records: ", e);
        }
    }
}