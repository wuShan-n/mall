package com.mall.search.service;

import com.mall.common.result.PageResult;
import com.mall.search.dto.request.OrderExportRequest;
import com.mall.search.dto.request.OrderSearchRequest;
import com.mall.search.dto.response.OrderVO;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 订单搜索服务接口
 */
public interface OrderSearchService {
    /**
     * 订单搜索
     */
    PageResult<OrderVO> search(OrderSearchRequest request);
    
    /**
     * 获取用户订单
     */
    PageResult<OrderVO> getUserOrders(Long userId, Integer status, Integer pageNum, Integer pageSize);
    
    /**
     * 导出订单
     */
    void export(OrderExportRequest request, HttpServletResponse response);
    
    /**
     * 更新订单索引
     */
    void updateOrderIndex(String orderNo);
}