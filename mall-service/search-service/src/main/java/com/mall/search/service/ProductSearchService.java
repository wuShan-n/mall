package com.mall.search.service;

import com.mall.search.dto.request.FilterRequest;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.dto.request.SearchFilters;
import com.mall.search.dto.response.ProductSearchResponse;
import com.mall.search.dto.response.ProductSearchVO;
import com.mall.search.dto.response.ProductSimpleVO;

import java.util.List;

/**
 * =====================================================
 * 搜索服务接口定义
 * =====================================================
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
    
    /**
     * 批量获取商品信息
     */
    List<ProductSearchVO> getByIds(List<Long> spuIds);
    
    /**
     * 更新商品索引
     */
    void updateProduct(Long spuId);
    
    /**
     * 批量更新商品索引
     */
    void batchUpdateProducts(List<Long> spuIds);
    
    /**
     * 删除商品索引
     */
    void deleteProduct(Long spuId);
}