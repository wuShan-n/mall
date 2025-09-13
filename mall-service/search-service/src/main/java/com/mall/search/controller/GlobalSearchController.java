package com.mall.search.controller;

import com.mall.common.result.Result;
import com.mall.search.dto.request.GlobalSearchResult;
import com.mall.search.dto.response.HotKeywordVO;
import com.mall.search.service.GlobalSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * =====================================================
 * 4. 聚合搜索接口 - 全局搜索
 * =====================================================
 */
//@RestController
//@RequestMapping("/api/search/global")
@Tag(name = "全局搜索接口")
public class GlobalSearchController {

    @Resource
    private GlobalSearchService globalSearchService;

    @GetMapping("/search")
    @Operation(summary = "全局搜索")
    public Result<GlobalSearchResult> globalSearch(
            @RequestParam @NotBlank String keyword,
            @RequestParam(defaultValue = "5") Integer size) {
        return Result.success(globalSearchService.search(keyword, size));
    }

    @GetMapping("/hot-keywords")
    @Operation(summary = "热搜词")
    public Result<List<HotKeywordVO>> getHotKeywords(
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(globalSearchService.getHotKeywords(size));
    }

    @GetMapping("/search-history/{userId}")
    @Operation(summary = "搜索历史")
    public Result<List<String>> getSearchHistory(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(globalSearchService.getSearchHistory(userId, size));
    }
}