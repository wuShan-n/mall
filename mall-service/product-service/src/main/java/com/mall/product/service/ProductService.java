package com.mall.product.service;

import com.mall.api.product.dto.request.ProductQueryRequest;
import com.mall.api.product.dto.request.SpuCreateRequest;
import com.mall.api.product.dto.response.ProductStatisticsVO;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.product.dto.response.SpuDetailVO;
import com.mall.api.product.dto.response.SpuVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Product Service Interface
 */
public interface ProductService {
    
    Result<SpuVO> createSpu(SpuCreateRequest request);
    
    Result<SpuVO> updateSpu(Long spuId, SpuCreateRequest request);
    
    Result<Void> deleteSpu(Long spuId);
    
    Result<SpuVO> getSpuById(Long spuId);
    
    Result<SpuDetailVO> getSpuDetail(Long spuId);
    
    Result<PageResult<SpuVO>> queryProducts(ProductQueryRequest request);
    
    Result<Void> updateProductStatus(Long spuId, Integer status);
    
    Result<Void> batchUpdateProductStatus(List<Long> spuIds, Integer status);
    
    Result<SkuVO> getSkuById(Long skuId);
    
    Result<List<SkuVO>> getSkusBySpuId(Long spuId);
    
    Result<List<SkuVO>> getSkusByIds(List<Long> skuIds);

    Result<ProductStatisticsVO> getProductStatistics();
}
