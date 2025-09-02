package com.mall.product.service;

import com.mall.api.product.dto.request.CategoryCreateRequest;
import com.mall.api.product.dto.response.CategoryVO;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Category Service Interface
 */
public interface CategoryService {
    
    Result<CategoryVO> createCategory(CategoryCreateRequest request);
    
    Result<CategoryVO> updateCategory(Long categoryId, CategoryCreateRequest request);
    
    Result<Void> deleteCategory(Long categoryId);
    
    Result<CategoryVO> getCategoryById(Long categoryId);
    
    Result<List<CategoryVO>> getCategoryTree();
    
    Result<List<CategoryVO>> getCategoriesByParentId(Long parentId);
    
    Result<List<CategoryVO>> getCategoryPath(Long categoryId);
    
    Result<Void> updateCategoryStatus(Long categoryId, Integer status);
}