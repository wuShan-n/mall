package com.mall.search.controller;

import com.mall.common.result.Result;
import com.mall.search.dto.request.MultiFieldSearchRequest;
import com.mall.search.dto.request.NearbySearchRequest;
import com.mall.search.dto.request.ScrollSearchRequest;
import com.mall.search.dto.request.SimilarSearchRequest;
import com.mall.search.dto.response.NearbyProductVO;
import com.mall.search.dto.response.ProductSearchResponse;
import com.mall.search.dto.response.ProductSearchVO;
import com.mall.search.dto.response.ScrollSearchResponse;
import com.mall.search.service.AdvancedSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * =====================================================
 * 5. 高级搜索接口 - 复杂查询
 * =====================================================
 */
@RestController
@RequestMapping("/api/search/advanced")
@Tag(name = "高级搜索接口")
public class AdvancedSearchController {

    @Resource
    private AdvancedSearchService advancedSearchService;

    @PostMapping("/multi-field")
    @Operation(summary = "多字段搜索")
    public Result<ProductSearchResponse> multiFieldSearch(@RequestBody MultiFieldSearchRequest request) {
        return Result.success(advancedSearchService.multiFieldSearch(request));
    }

    @PostMapping("/nearby")
    @Operation(summary = "附近商品搜索")
    public Result<List<NearbyProductVO>> searchNearby(@RequestBody NearbySearchRequest request) {
        return Result.success(advancedSearchService.searchNearby(request));
    }

    @PostMapping("/similar")
    @Operation(summary = "相似商品搜索")
    public Result<List<ProductSearchVO>> searchSimilar(@RequestBody SimilarSearchRequest request) {
        return Result.success(advancedSearchService.searchSimilar(request));
    }

    @PostMapping("/scroll")
    @Operation(summary = "滚动搜索(用于导出)")
    public Result<ScrollSearchResponse> scrollSearch(@RequestBody ScrollSearchRequest request) {
        return Result.success(advancedSearchService.scrollSearch(request));
    }
}