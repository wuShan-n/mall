package com.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.api.product.dto.response.CategoryVO;
import com.mall.product.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Product Category Mapper
 */
@Mapper
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    
    /**
     * Get category tree
     */
    List<CategoryVO> selectCategoryTree(@Param("parentId") Long parentId);
    
    /**
     * Get category path from leaf to root
     */
    List<CategoryVO> selectCategoryPath(@Param("categoryId") Long categoryId);
    
    /**
     * Check if category has children
     */
    Integer hasChildren(@Param("categoryId") Long categoryId);
    
    /**
     * Check if category has products
     */
    Integer hasProducts(@Param("categoryId") Long categoryId);
}
