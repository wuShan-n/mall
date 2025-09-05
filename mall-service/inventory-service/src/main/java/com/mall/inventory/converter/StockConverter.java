package com.mall.inventory.converter;

import com.mall.api.inventory.dto.response.StockLockVO;
import com.mall.api.inventory.dto.response.StockRecordVO;
import com.mall.api.inventory.dto.response.StockVO;
import com.mall.inventory.entity.Stock;
import com.mall.inventory.entity.StockLock;
import com.mall.inventory.entity.StockRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockConverter {
    
    @Mapping(target = "isAvailable", expression = "java(stock.getAvailableStock() > 0)")
    @Mapping(target = "isLowStock", expression = "java(stock.getAvailableStock() <= stock.getWarnStock())")
    StockVO toVO(Stock stock);
    
    List<StockVO> toVOList(List<Stock> stocks);
    
    StockLockVO toLockVO(StockLock stockLock);
    
    List<StockLockVO> toLockVOList(List<StockLock> stockLocks);
    
    StockRecordVO toRecordVO(StockRecord stockRecord);
    
    List<StockRecordVO> toRecordVOList(List<StockRecord> stockRecords);
}