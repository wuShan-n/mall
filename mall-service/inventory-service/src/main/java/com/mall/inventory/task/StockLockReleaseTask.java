// StockLockReleaseTask.java
package com.mall.inventory.task;

import com.mall.inventory.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockLockReleaseTask {
    
    private final StockService stockService;
    
    /**
     * 每5分钟执行一次，释放过期的库存锁定
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void releaseExpiredLocks() {
        try {
            log.info("Starting to release expired stock locks...");
            Integer count = stockService.releaseExpiredLocks();
            log.info("Released {} expired stock locks", count);
        } catch (Exception e) {
            log.error("Failed to release expired stock locks", e);
        }
    }
}