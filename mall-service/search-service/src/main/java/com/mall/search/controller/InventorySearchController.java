package com.mall.search.controller;

import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.search.dto.request.InventorySearchRequest;
import com.mall.search.dto.request.InventoryStatRequest;
import com.mall.search.dto.response.InventoryStatistics;
import com.mall.search.dto.response.InventoryVO;
import com.mall.search.dto.response.InventoryWarningVO;
import com.mall.search.service.InventorySearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * =====================================================
 * 2. 库存搜索接口 - B端库存管理
 * =====================================================
 */
@RestController
@RequestMapping("/api/search/inventory")
@Tag(name = "库存搜索接口")
public class InventorySearchController {

    @Resource
    private InventorySearchService inventorySearchService;

    @PostMapping("/search")
    @Operation(summary = "库存搜索")
    public Result<PageResult<InventoryVO>> searchInventory(@RequestBody @Valid InventorySearchRequest request) {
        return Result.success(inventorySearchService.search(request));
    }

    @GetMapping("/warning")
    @Operation(summary = "库存预警列表")
    public Result<List<InventoryWarningVO>> getWarningList(
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(defaultValue = "50") Integer limit) {
        return Result.success(inventorySearchService.getWarningList(warehouseId, limit));
    }

    @PostMapping("/statistics")
    @Operation(summary = "库存统计")
    public Result<InventoryStatistics> getStatistics(@RequestBody InventoryStatRequest request) {
        return Result.success(inventorySearchService.getStatistics(request));
    }
}