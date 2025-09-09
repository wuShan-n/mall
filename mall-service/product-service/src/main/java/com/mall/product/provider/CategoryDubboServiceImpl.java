package com.mall.product.provider;

import com.mall.api.product.dto.request.CategoryCreateRequest;
import com.mall.api.product.dto.response.CategoryVO;
import com.mall.api.product.dubbo.CategoryDubboService;
import com.mall.common.result.Result;
import com.mall.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * Category Dubbo Service Provider Implementation
 */
@Slf4j
@DubboService(version = "1.0.0", timeout = 3000, retries = 0)
@RequiredArgsConstructor
public class CategoryDubboServiceImpl implements CategoryDubboService {

    private final CategoryService categoryService;

    @Override
    public Result<CategoryVO> createCategory(CategoryCreateRequest request) {
        log.info("Dubbo RPC: createCategory, name: {}", request.getCategoryName());
        return categoryService.createCategory(request);
    }

    @Override
    public Result<CategoryVO> updateCategory(Long categoryId, CategoryCreateRequest request) {
        log.info("Dubbo RPC: updateCategory, categoryId: {}", categoryId);
        return categoryService.updateCategory(categoryId, request);
    }

    @Override
    public Result<Void> deleteCategory(Long categoryId) {
        log.info("Dubbo RPC: deleteCategory, categoryId: {}", categoryId);
        return categoryService.deleteCategory(categoryId);
    }

    @Override
    public Result<CategoryVO> getCategoryById(Long categoryId) {
        log.debug("Dubbo RPC: getCategoryById, categoryId: {}", categoryId);
        return categoryService.getCategoryById(categoryId);
    }

    @Override
    public Result<List<CategoryVO>> getCategoryTree() {
        log.debug("Dubbo RPC: getCategoryTree");
        return categoryService.getCategoryTree();
    }

    @Override
    public Result<List<CategoryVO>> getCategoriesByParentId(Long parentId) {
        log.debug("Dubbo RPC: getCategoriesByParentId, parentId: {}", parentId);
        return categoryService.getCategoriesByParentId(parentId);
    }

    @Override
    public Result<List<CategoryVO>> getCategoryPath(Long categoryId) {
        log.debug("Dubbo RPC: getCategoryPath, categoryId: {}", categoryId);
        return categoryService.getCategoryPath(categoryId);
    }

    @Override
    public Result<Void> updateCategoryStatus(Long categoryId, Integer status) {
        log.info("Dubbo RPC: updateCategoryStatus, categoryId: {}, status: {}", categoryId, status);
        return categoryService.updateCategoryStatus(categoryId, status);
    }
}
