package com.mall.product.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mall.api.product.dto.request.CategoryCreateRequest;
import com.mall.api.product.dto.response.CategoryVO;
import com.mall.common.annotation.Log;
import com.mall.common.result.Result;
import com.mall.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category Controller
 */
@RestController
@RequestMapping("/api/product/category")
@RequiredArgsConstructor
@Tag(name = "Category Management", description = "Product category operations")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary = "Create category", description = "Create a new product category")
    @SaCheckPermission("category:add")
    @Log(module = "Category", type = Log.OperationType.CREATE, desc = "Create category")
    public Result<CategoryVO> createCategory(@Valid @RequestBody CategoryCreateRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{categoryId}")
    @Operation(summary = "Update category", description = "Update category information")
    @SaCheckPermission("category:update")
    @Log(module = "Category", type = Log.OperationType.UPDATE, desc = "Update category")
    public Result<CategoryVO> updateCategory(
            @Parameter(description = "Category ID") @PathVariable Long categoryId,
            @Valid @RequestBody CategoryCreateRequest request) {
        return categoryService.updateCategory(categoryId, request);
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Delete category", description = "Delete a product category")
    @SaCheckPermission("category:delete")
    @Log(module = "Category", type = Log.OperationType.DELETE, desc = "Delete category")
    public Result<Void> deleteCategory(@Parameter(description = "Category ID") @PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "Get category by ID", description = "Get category information")
    public Result<CategoryVO> getCategoryById(@Parameter(description = "Category ID") @PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/tree")
    @Operation(summary = "Get category tree", description = "Get hierarchical category tree")
    public Result<List<CategoryVO>> getCategoryTree() {
        return categoryService.getCategoryTree();
    }

    @GetMapping("/parent/{parentId}")
    @Operation(summary = "Get categories by parent", description = "Get child categories by parent ID")
    public Result<List<CategoryVO>> getCategoriesByParentId(
            @Parameter(description = "Parent ID, 0 for root") @PathVariable Long parentId) {
        return categoryService.getCategoriesByParentId(parentId);
    }

    @GetMapping("/path/{categoryId}")
    @Operation(summary = "Get category path", description = "Get category path from root to current")
    public Result<List<CategoryVO>> getCategoryPath(@Parameter(description = "Category ID") @PathVariable Long categoryId) {
        return categoryService.getCategoryPath(categoryId);
    }

    @PutMapping("/{categoryId}/status/{status}")
    @Operation(summary = "Update category status", description = "Enable or disable category")
    @SaCheckPermission("category:update")
    @Log(module = "Category", type = Log.OperationType.UPDATE, desc = "Update category status")
    public Result<Void> updateCategoryStatus(
            @Parameter(description = "Category ID") @PathVariable Long categoryId,
            @Parameter(description = "Status: 0-Disable, 1-Enable") @PathVariable Integer status) {
        return categoryService.updateCategoryStatus(categoryId, status);
    }
}