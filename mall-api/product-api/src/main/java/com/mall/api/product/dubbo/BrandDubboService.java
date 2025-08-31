package com.mall.api.product.dubbo;

import com.mall.api.product.dto.request.BrandCreateRequest;
import com.mall.api.product.dto.response.BrandVO;
import com.mall.common.result.Result;
import com.mall.common.result.PageResult;
import com.mall.common.base.PageRequest;
import java.util.List;

/**
 * Brand Dubbo RPC service interface
 */
public interface BrandDubboService {
    
    /**
     * Create brand
     */
    Result<BrandVO> createBrand(BrandCreateRequest request);
    
    /**
     * Update brand
     */
    Result<BrandVO> updateBrand(Long brandId, BrandCreateRequest request);
    
    /**
     * Delete brand
     */
    Result<Void> deleteBrand(Long brandId);
    
    /**
     * Get brand by ID
     */
    Result<BrandVO> getBrandById(Long brandId);
    
    /**
     * Get all brands
     */
    Result<List<BrandVO>> getAllBrands();
    
    /**
     * Query brands with pagination
     */
    Result<PageResult<BrandVO>> queryBrands(PageRequest request);
    
    /**
     * Get brands by category ID
     */
    Result<List<BrandVO>> getBrandsByCategoryId(Long categoryId);
    
    /**
     * Enable/Disable brand
     */
    Result<Void> updateBrandStatus(Long brandId, Integer status);
}
