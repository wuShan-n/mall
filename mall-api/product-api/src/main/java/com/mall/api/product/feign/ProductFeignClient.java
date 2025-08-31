package com.mall.api.product.feign;

import com.mall.api.product.constant.ProductConstants;
import com.mall.api.product.dto.request.*;
import com.mall.api.product.dto.response.*;
import com.mall.common.result.Result;
import com.mall.common.result.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Product Feign client for HTTP calls
 */
@FeignClient(name = ProductConstants.SERVICE_NAME, path = ProductConstants.API_PREFIX)
public interface ProductFeignClient {
    
    @PostMapping("/spu/create")
    Result<SpuVO> createSpu(@RequestBody SpuCreateRequest request);
    
    @PutMapping("/spu/{spuId}")
    Result<SpuVO> updateSpu(@PathVariable("spuId") Long spuId,
                           @RequestBody SpuCreateRequest request);
    
    @DeleteMapping("/spu/{spuId}")
    Result<Void> deleteSpu(@PathVariable("spuId") Long spuId);
    
    @GetMapping("/spu/{spuId}")
    Result<SpuVO> getSpuById(@PathVariable("spuId") Long spuId);
    
    @GetMapping("/spu/{spuId}/detail")
    Result<SpuDetailVO> getSpuDetail(@PathVariable("spuId") Long spuId);
    
    @PostMapping("/spu/query")
    Result<PageResult<SpuVO>> queryProducts(@RequestBody ProductQueryRequest request);
    
    @PutMapping("/spu/{spuId}/status/{status}")
    Result<Void> updateProductStatus(@PathVariable("spuId") Long spuId,
                                    @PathVariable("status") Integer status);
    
    @PutMapping("/spu/batch/status")
    Result<Void> batchUpdateProductStatus(@RequestParam("spuIds") List<Long> spuIds,
                                         @RequestParam("status") Integer status);
    
    @GetMapping("/sku/{skuId}")
    Result<SkuVO> getSkuById(@PathVariable("skuId") Long skuId);
    
    @GetMapping("/sku/spu/{spuId}")
    Result<List<SkuVO>> getSkusBySpuId(@PathVariable("spuId") Long spuId);
    
    @PostMapping("/sku/list")
    Result<List<SkuVO>> getSkusByIds(@RequestBody List<Long> skuIds);
    
    @PostMapping("/sku/stock/update")
    Result<Void> updateSkuStock(@RequestBody StockUpdateRequest request);
    
    @PostMapping("/sku/stock/batch-update")
    Result<Void> batchUpdateSkuStock(@RequestBody List<StockUpdateRequest> requests);
    
    @PostMapping("/sku/stock/lock")
    Result<Void> lockStock(@RequestParam("skuId") Long skuId,
                          @RequestParam("quantity") Integer quantity,
                          @RequestParam("orderNo") String orderNo);
    
    @PostMapping("/sku/stock/unlock")
    Result<Void> unlockStock(@RequestParam("skuId") Long skuId,
                            @RequestParam("quantity") Integer quantity,
                            @RequestParam("orderNo") String orderNo);
    
    @PostMapping("/sku/stock/deduct")
    Result<Void> deductStock(@RequestParam("skuId") Long skuId,
                            @RequestParam("quantity") Integer quantity,
                            @RequestParam("orderNo") String orderNo);
    
    @GetMapping("/sku/stock/check")
    Result<Boolean> checkStock(@RequestParam("skuId") Long skuId,
                              @RequestParam("quantity") Integer quantity);
    
    @GetMapping("/statistics")
    Result<ProductStatisticsVO> getProductStatistics();
}