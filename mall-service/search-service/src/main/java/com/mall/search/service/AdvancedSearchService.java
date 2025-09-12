package com.mall.search.service;

import com.mall.search.dto.request.MultiFieldSearchRequest;
import com.mall.search.dto.request.NearbySearchRequest;
import com.mall.search.dto.request.ScrollSearchRequest;
import com.mall.search.dto.request.SimilarSearchRequest;
import com.mall.search.dto.response.NearbyProductVO;
import com.mall.search.dto.response.ProductSearchResponse;
import com.mall.search.dto.response.ProductSearchVO;
import com.mall.search.dto.response.ScrollSearchResponse;

import java.util.List;

/**
 * 高级搜索服务接口
 */
public interface AdvancedSearchService {
    /**
     * 多字段搜索
     */
    ProductSearchResponse multiFieldSearch(MultiFieldSearchRequest request);
    
    /**
     * 附近商品搜索
     */
    List<NearbyProductVO> searchNearby(NearbySearchRequest request);
    
    /**
     * 相似商品搜索
     */
    List<ProductSearchVO> searchSimilar(SimilarSearchRequest request);
    
    /**
     * 滚动搜索
     */
    ScrollSearchResponse scrollSearch(ScrollSearchRequest request);
}
