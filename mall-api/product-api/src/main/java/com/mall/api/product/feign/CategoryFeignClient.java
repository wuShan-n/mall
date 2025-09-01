package com.mall.api.product.feign;

import com.mall.api.product.constant.ProductConstants;
import com.mall.api.product.dto.request.CategoryCreateRequest;
import com.mall.api.product.dto.response.CategoryVO;
import com.mall.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category Feign client for HTTP calls
 */
@FeignClient(name = ProductConstants.SERVICE_NAME, path = ProductConstants.API_PREFIX + "/category")
public interface CategoryFeignClient {

    @PostMapping("/create")
    Result<CategoryVO> createCategory(@RequestBody CategoryCreateRequest request);

    @PutMapping("/{categoryId}")
    Result<CategoryVO> updateCategory(@PathVariable("categoryId") Long categoryId,
            @RequestBody CategoryCreateRequest request);

    @DeleteMapping("/{categoryId}")
    Result<Void> deleteCategory(@PathVariable("categoryId") Long categoryId);

    @GetMapping("/{categoryId}")
    Result<CategoryVO> getCategoryById(@PathVariable("categoryId") Long categoryId);

    @GetMapping("/tree")
    Result<List<CategoryVO>> getCategoryTree();

    @GetMapping("/parent/{parentId}")
    Result<List<CategoryVO>> getCategoriesByParentId(@PathVariable("parentId") Long parentId);

    @GetMapping("/path/{categoryId}")
    Result<List<CategoryVO>> getCategoryPath(@PathVariable("categoryId") Long categoryId);

    @PutMapping("/{categoryId}/status/{status}")
    Result<Void> updateCategoryStatus(@PathVariable("categoryId") Long categoryId,
            @PathVariable("status") Integer status);
}
