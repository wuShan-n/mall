package com.mall.search.controller;

import com.mall.common.result.Result;
import com.mall.search.dto.request.FilterRequest;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.dto.request.SearchFilters;
import com.mall.search.dto.response.ProductSearchResponse;
import com.mall.search.dto.response.ProductSimpleVO;
import com.mall.search.service.ProductSearchService;
import com.mall.search.service.SuggestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * =====================================================
 * 1. 商品搜索接口 - C端核心搜索
 * =====================================================
 */
@RestController
@RequestMapping("/api/search/product")
@Tag(name = "商品搜索接口")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;
    private final SuggestService suggestService;
    /**
     * 商品搜索主接口
     */
    @PostMapping("/search")
    @Operation(summary = "商品搜索")
    public Result<ProductSearchResponse> searchProducts(@RequestBody @Valid ProductSearchRequest request) {
        // 实现逻辑
        return Result.success(productSearchService.search(request));
    }
    
    /**
     * 搜索建议/自动补全
     */
    @GetMapping("/suggest")
    @Operation(summary = "搜索建议")
    public Result<List<String>> suggest(
            @RequestParam @NotBlank String keyword,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(suggestService.suggest(keyword, size));
    }
    
    /**
     * 获取筛选项（聚合）
     */
    @PostMapping("/filters")
    @Operation(summary = "获取筛选项")
    public Result<SearchFilters> getFilters(@RequestBody FilterRequest request) {
        return Result.success(productSearchService.getFilters(request));
    }
    
    /**
     * 商品详情页推荐
     */
    @GetMapping("/related/{spuId}")
    @Operation(summary = "相关商品推荐")
    public Result<List<ProductSimpleVO>> getRelatedProducts(
            @PathVariable Long spuId,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(productSearchService.getRelatedProducts(spuId, size));
    }
}