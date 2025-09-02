package com.mall.product.service;

import com.mall.api.product.dto.request.BrandCreateRequest;
import com.mall.api.product.dto.response.BrandVO;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Brand Service Interface
 */
public interface BrandService {
    
    Result<BrandVO> createBrand(BrandCreateRequest request);
    
    Result<BrandVO> updateBrand(Long brandId, BrandCreateRequest request);
    
    Result<Void> deleteBrand(Long brandId);
    
    Result<BrandVO> getBrandById(Long brandId);
    
    Result<List<BrandVO>> getAllBrands();
    
    Result<PageResult<BrandVO>> queryBrands(PageRequest request);
    
    Result<List<BrandVO>> getBrandsByCategoryId(Long categoryId);
    
    Result<Void> updateBrandStatus(Long brandId, Integer status);
}
