package com.mall.api.product.dubbo;

import com.mall.api.product.dto.request.CategoryCreateRequest;
import com.mall.api.product.dto.response.CategoryVO;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Category Dubbo RPC service interface
 */
public interface CategoryDubboService {
    
    /**
     * Create category
     */
    Result<CategoryVO> createCategory(CategoryCreateRequest request);
    
    /**
     * Update category
     */
    Result<CategoryVO> updateCategory(Long categoryId, CategoryCreateRequest request);
    
    /**
     * Delete category
     */
    Result<Void> deleteCategory(Long categoryId);
    
    /**
     * Get category by ID
     */
    Result<CategoryVO> getCategoryById(Long categoryId);
    
    /**
     * Get category tree
     */
    Result<List<CategoryVO>> getCategoryTree();
    
    /**
     * Get categories by parent ID
     */
    Result<List<CategoryVO>> getCategoriesByParentId(Long parentId);
    
    /**
     * Get category path
     */
    Result<List<CategoryVO>> getCategoryPath(Long categoryId);
    
    /**
     * Enable/Disable category
     */
    Result<Void> updateCategoryStatus(Long categoryId, Integer status);
}