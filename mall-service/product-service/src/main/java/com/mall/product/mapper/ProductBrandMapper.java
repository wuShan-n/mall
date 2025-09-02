package com.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.api.product.dto.response.BrandVO;
import com.mall.product.entity.ProductBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Product Brand Mapper
 */
@Mapper
public interface ProductBrandMapper extends BaseMapper<ProductBrand> {
    
    /**
     * Query brands with pagination
     */
    IPage<BrandVO> selectBrandPage(IPage<?> page, @Param("brandName") String brandName);
    
    /**
     * Get brands by category ID
     */
    List<BrandVO> selectBrandsByCategoryId(@Param("categoryId") Long categoryId);
    
    /**
     * Get brand with product count
     */
    BrandVO selectBrandWithCount(@Param("brandId") Long brandId);
}
