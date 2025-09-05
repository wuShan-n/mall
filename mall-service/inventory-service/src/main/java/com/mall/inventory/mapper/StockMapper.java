package com.mall.inventory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.inventory.entity.Stock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StockMapper extends BaseMapper<Stock> {
    
    /**
     * 批量更新库存
     */
    int batchUpdateStock(@Param("list") List<Stock> stocks);
    
    /**
     * 查询低库存SKU
     */
    List<Stock> selectLowStockSkus(@Param("limit") Integer limit);
    
    /**
     * 查询缺货SKU
     */
    List<Stock> selectOutOfStockSkus(@Param("limit") Integer limit);
}
