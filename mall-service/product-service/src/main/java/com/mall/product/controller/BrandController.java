package com.mall.product.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mall.api.product.dto.request.BrandCreateRequest;
import com.mall.api.product.dto.response.BrandVO;
import com.mall.common.annotation.Log;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.product.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Brand Controller
 */
@RestController
@RequestMapping("/api/product/brand")
@RequiredArgsConstructor
@Tag(name = "Brand Management", description = "Product brand operations")
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/create")
    @Operation(summary = "Create brand", description = "Create a new product brand")
//    @SaCheckPermission("brand:add")
    @Log(module = "Brand", type = Log.OperationType.CREATE, desc = "Create brand")
    public Result<BrandVO> createBrand(@Valid @RequestBody BrandCreateRequest request) {
        return brandService.createBrand(request);
    }

    @PutMapping("/{brandId}")
    @Operation(summary = "Update brand", description = "Update brand information")
//    @SaCheckPermission("brand:update")
    @Log(module = "Brand", type = Log.OperationType.UPDATE, desc = "Update brand")
    public Result<BrandVO> updateBrand(
            @Parameter(description = "Brand ID") @PathVariable("brandId") Long brandId,
            @Valid @RequestBody BrandCreateRequest request) {
        return brandService.updateBrand(brandId, request);
    }

    @DeleteMapping("/{brandId}")
    @Operation(summary = "Delete brand", description = "Delete a product brand")
//    @SaCheckPermission("brand:delete")
    @Log(module = "Brand", type = Log.OperationType.DELETE, desc = "Delete brand")
    public Result<Void> deleteBrand(@Parameter(description = "Brand ID") @PathVariable("brandId") Long brandId) {
        return brandService.deleteBrand(brandId);
    }

    @GetMapping("/{brandId}")
    @Operation(summary = "Get brand by ID", description = "Get brand information")
    public Result<BrandVO> getBrandById(@Parameter(description = "Brand ID") @PathVariable("brandId") Long brandId) {
        return brandService.getBrandById(brandId);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all brands", description = "Get all available brands")
    public Result<List<BrandVO>> getAllBrands() {
        return brandService.getAllBrands();
    }

    @PostMapping("/query")
    @Operation(summary = "Query brands", description = "Query brands with pagination")
    public Result<PageResult<BrandVO>> queryBrands(@Valid @RequestBody PageRequest request) {
        return brandService.queryBrands(request);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get brands by category", description = "Get brands associated with category")
    public Result<List<BrandVO>> getBrandsByCategoryId(
            @Parameter(description = "Category ID") @PathVariable("categoryId") Long categoryId) {
        return brandService.getBrandsByCategoryId(categoryId);
    }

    @PutMapping("/{brandId}/status/{status}")
    @Operation(summary = "Update brand status", description = "Enable or disable brand")
//    @SaCheckPermission("brand:update")
    @Log(module = "Brand", type = Log.OperationType.UPDATE, desc = "Update brand status")
    public Result<Void> updateBrandStatus(
            @Parameter(description = "Brand ID") @PathVariable Long brandId,
            @Parameter(description = "Status: 0-Disable, 1-Enable") @PathVariable("status") Integer status) {
        return brandService.updateBrandStatus(brandId, status);
    }
}