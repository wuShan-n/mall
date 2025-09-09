package com.mall.api.product.dubbo;

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
 * Product Dubbo RPC service interface
 */
public interface ProductDubboService {
    
    /**
     * Create SPU
     */
    Result<SpuVO> createSpu(SpuCreateRequest request);
    
    /**
     * Update SPU
     */
    Result<SpuVO> updateSpu(Long spuId, SpuCreateRequest request);
    
    /**
     * Delete SPU
     */
    Result<Void> deleteSpu(Long spuId);
    
    /**
     * Get SPU by ID
     */
    Result<SpuVO> getSpuById(Long spuId);
    
    /**
     * Get SPU detail
     */
    Result<SpuDetailVO> getSpuDetail(Long spuId);
    
    /**
     * Query products with pagination
     */
    Result<PageResult<SpuVO>> queryProducts(ProductQueryRequest request);
    
    /**
     * Update product status
     */
    Result<Void> updateProductStatus(Long spuId, Integer status);
    
    /**
     * Batch update product status
     */
    Result<Void> batchUpdateProductStatus(List<Long> spuIds, Integer status);
    
    /**
     * Get SKU by ID
     */
    Result<SkuVO> getSkuById(Long skuId);
    
    /**
     * Get SKUs by SPU ID
     */
    Result<List<SkuVO>> getSkusBySpuId(Long spuId);
    
    /**
     * Get SKUs by IDs
     */
    Result<List<SkuVO>> getSkusByIds(List<Long> skuIds);

    /**
     * Get product statistics
     */
    Result<ProductStatisticsVO> getProductStatistics();
}