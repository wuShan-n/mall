package com.mall.search.service;

import com.mall.common.result.PageResult;
import com.mall.search.dto.request.InventorySearchRequest;
import com.mall.search.dto.request.InventoryStatRequest;
import com.mall.search.dto.response.InventoryStatistics;
import com.mall.search.dto.response.InventoryVO;
import com.mall.search.dto.response.InventoryWarningVO;

import java.util.List;

/**
 * 库存搜索服务接口
 */
public interface InventorySearchService {
    /**
     * 库存搜索
     */
    PageResult<InventoryVO> search(InventorySearchRequest request);

    /**
     * 获取库存预警列表
     */
    List<InventoryWarningVO> getWarningList(Long warehouseId, Integer limit);

    /**
     * 库存统计
     */
    InventoryStatistics getStatistics(InventoryStatRequest request);

    /**
     * 更新库存索引
     */
    void updateInventoryIndex(Long skuId, Long warehouseId);
}
