package com.mall.search.service;

import com.mall.search.dto.request.FilterRequest;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.dto.response.ProductSearchResponse;
import com.mall.search.dto.response.ProductSimpleVO;
import com.mall.search.dto.response.SearchFilters;

import java.util.List;

/**
 * 商品搜索服务接口定义
 */
public interface ProductSearchService {

    /**
     * 商品搜索
     */
    ProductSearchResponse search(ProductSearchRequest request);

    /**
     * 获取搜索筛选项
     */
    SearchFilters getFilters(FilterRequest request);

    /**
     * 相关商品推荐
     */
    List<ProductSimpleVO> getRelatedProducts(Long spuId, Integer size);

}