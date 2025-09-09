package com.mall.product.controller;

import com.mall.api.product.dto.request.ProductQueryRequest;
import com.mall.api.product.dto.request.SpuCreateRequest;
import com.mall.api.product.dto.response.ProductStatisticsVO;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.product.dto.response.SpuDetailVO;
import com.mall.api.product.dto.response.SpuVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Product Controller
 */
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Product Management", description = "Product CRUD and inventory operations")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/spu/create")
    @Operation(summary = "Create SPU", description = "Create a new product SPU with SKUs")
    public Result<SpuVO> createSpu(@Valid @RequestBody SpuCreateRequest request) {
        return productService.createSpu(request);
    }

    @PutMapping("/spu/{spuId}")
    @Operation(summary = "Update SPU", description = "Update product SPU information")
    public Result<SpuVO> updateSpu(
            @Parameter(description = "SPU ID") @PathVariable Long spuId,
            @Valid @RequestBody SpuCreateRequest request) {
        return productService.updateSpu(spuId, request);
    }

    @DeleteMapping("/spu/{spuId}")
    @Operation(summary = "Delete SPU", description = "Delete a product SPU")
    public Result<Void> deleteSpu(@Parameter(description = "SPU ID") @PathVariable Long spuId) {
        return productService.deleteSpu(spuId);
    }

    @GetMapping("/spu/{spuId}")
    @Operation(summary = "Get SPU by ID", description = "Get product SPU basic information")
    public Result<SpuVO> getSpuById(@Parameter(description = "SPU ID") @PathVariable Long spuId) {
        return productService.getSpuById(spuId);
    }

    @GetMapping("/spu/{spuId}/detail")
    @Operation(summary = "Get SPU detail", description = "Get product SPU detail with SKUs and attributes")
    public Result<SpuDetailVO> getSpuDetail(@Parameter(description = "SPU ID") @PathVariable Long spuId) {
        return productService.getSpuDetail(spuId);
    }

    @PostMapping("/spu/query")
    @Operation(summary = "Query products", description = "Query products with pagination and filters")
    public Result<PageResult<SpuVO>> queryProducts(@Valid @RequestBody ProductQueryRequest request) {
        return productService.queryProducts(request);
    }

    @PutMapping("/spu/{spuId}/status/{status}")
    @Operation(summary = "Update product status", description = "Update product on/off shelf status")
    public Result<Void> updateProductStatus(
            @Parameter(description = "SPU ID") @PathVariable Long spuId,
            @Parameter(description = "Status: 0-Off shelf, 1-On shelf") @PathVariable Integer status) {
        return productService.updateProductStatus(spuId, status);
    }

    @PutMapping("/spu/batch/status")
    @Operation(summary = "Batch update product status", description = "Batch update product status")
    public Result<Void> batchUpdateProductStatus(
            @Parameter(description = "SPU IDs") @RequestParam List<Long> spuIds,
            @Parameter(description = "Status") @RequestParam Integer status) {
        return productService.batchUpdateProductStatus(spuIds, status);
    }

    @GetMapping("/sku/{skuId}")
    @Operation(summary = "Get SKU by ID", description = "Get product SKU information")
    public Result<SkuVO> getSkuById(@Parameter(description = "SKU ID") @PathVariable Long skuId) {
        return productService.getSkuById(skuId);
    }

    @GetMapping("/sku/spu/{spuId}")
    @Operation(summary = "Get SKUs by SPU ID", description = "Get all SKUs of a product")
    public Result<List<SkuVO>> getSkusBySpuId(@Parameter(description = "SPU ID") @PathVariable Long spuId) {
        return productService.getSkusBySpuId(spuId);
    }

    @PostMapping("/sku/list")
    @Operation(summary = "Get SKUs by IDs", description = "Batch get SKU information")
    public Result<List<SkuVO>> getSkusByIds(@RequestBody List<Long> skuIds) {
        return productService.getSkusByIds(skuIds);
    }

    @GetMapping("/statistics")
    @Operation(summary = "Get product statistics", description = "Get overall product statistics")
    public Result<ProductStatisticsVO> getProductStatistics() {
        return productService.getProductStatistics();
    }
}