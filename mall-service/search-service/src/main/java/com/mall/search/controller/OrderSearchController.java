package com.mall.search.controller;

import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.search.dto.request.OrderExportRequest;
import com.mall.search.dto.request.OrderSearchRequest;
import com.mall.search.dto.response.OrderVO;
import com.mall.search.service.OrderSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * =====================================================
 * 3. 订单搜索接口 - 订单管理
 * =====================================================
 */
//@RestController
//@RequestMapping("/api/search/order")
@Tag(name = "订单搜索接口")
@RequiredArgsConstructor
public class OrderSearchController {

    private final OrderSearchService orderSearchService;

    @PostMapping("/search")
    @Operation(summary = "订单搜索")
    public Result<PageResult<OrderVO>> searchOrders(@RequestBody @Valid OrderSearchRequest request) {
        return Result.success(orderSearchService.search(request));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "用户订单列表")
    public Result<PageResult<OrderVO>> getUserOrders(
            @PathVariable Long userId,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return Result.success(orderSearchService.getUserOrders(userId, status, pageNum, pageSize));
    }

    @PostMapping("/export")
    @Operation(summary = "订单导出")
    public void exportOrders(@RequestBody OrderExportRequest request, HttpServletResponse response) {
        orderSearchService.export(request, response);
    }
}
