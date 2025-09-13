package com.mall.search.service;

import com.mall.search.dto.request.FilterRequest;
import com.mall.search.dto.response.SearchFilters;

/**
 * 筛选服务
 */
public interface FilterService {

    /**
     * 获取筛选项
     *
     * @param request 筛选请求
     * @return 筛选项数据
     */
    SearchFilters getFilters(FilterRequest request);
}
