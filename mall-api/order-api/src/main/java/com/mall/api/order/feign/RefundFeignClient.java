package com.mall.api.order.feign;

import com.mall.api.order.constant.OrderConstants;
import com.mall.api.order.dto.request.*;
import com.mall.api.order.dto.response.*;
import com.mall.common.result.Result;
import com.mall.common.result.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Refund Feign client for HTTP calls
 */
@FeignClient(name = OrderConstants.SERVICE_NAME, path = OrderConstants.API_PREFIX + "/refund")
public interface RefundFeignClient {
    
    @PostMapping("/create")
    Result<RefundVO> createRefund(@RequestHeader("userId") Long userId,
                                 @RequestBody RefundCreateRequest request);
    
    @PostMapping("/{refundNo}/cancel")
    Result<Void> cancelRefund(@RequestHeader("userId") Long userId,
                             @PathVariable("refundNo") String refundNo);
    
    @PostMapping("/process")
    Result<Void> processRefund(@RequestBody RefundProcessRequest request);
    
    @PostMapping("/{refundNo}/approve")
    Result<Void> approveRefund(@PathVariable("refundNo") String refundNo,
                              @RequestParam("handleNote") String handleNote);
    
    @PostMapping("/{refundNo}/reject")
    Result<Void> rejectRefund(@PathVariable("refundNo") String refundNo,
                             @RequestParam("handleNote") String handleNote);
    
    @PostMapping("/{refundNo}/complete")
    Result<Void> completeRefund(@PathVariable("refundNo") String refundNo);
    
    @GetMapping("/{refundNo}")
    Result<RefundVO> getRefundByNo(@PathVariable("refundNo") String refundNo);
    
    @GetMapping("/{refundNo}/detail")
    Result<RefundDetailVO> getRefundDetail(@PathVariable("refundNo") String refundNo);
    
    @PostMapping("/query")
    Result<PageResult<RefundVO>> queryRefunds(@RequestBody RefundQueryRequest request);
    
    @GetMapping("/user/{userId}")
    Result<PageResult<RefundVO>> getUserRefunds(@PathVariable("userId") Long userId,
                                               @RequestParam(value = "status", required = false) Integer status,
                                               @RequestParam(value = "current", defaultValue = "1") Long current,
                                               @RequestParam(value = "size", defaultValue = "10") Long size);
    
    @GetMapping("/order/{orderNo}")
    Result<List<RefundVO>> getOrderRefunds(@PathVariable("orderNo") String orderNo);
    
    @GetMapping("/{refundNo}/status")
    Result<RefundVO> checkRefundStatus(@PathVariable("refundNo") String refundNo);
}