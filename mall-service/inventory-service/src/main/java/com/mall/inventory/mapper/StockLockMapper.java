package com.mall.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.inventory.entity.StockLock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockLockMapper extends BaseMapper<StockLock> {
    
    /**
     * 批量更新锁定状态
     */
    int batchUpdateStatus(@Param("orderNo") String orderNo, @Param("status") Integer status);
    
    /**
     * 查询过期的锁定记录
     */
    List<StockLock> selectExpiredLocks();
}