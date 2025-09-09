package com.mall.api.product.feign;

import com.mall.api.product.constant.ProductConstants;
import com.mall.api.product.dto.request.ProductQueryRequest;
import com.mall.api.product.dto.request.SpuCreateRequest;
import com.mall.api.product.dto.response.ProductStatisticsVO;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.product.dto.response.SpuDetailVO;
import com.mall.api.product.dto.response.SpuVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
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

    @GetMapping("/statistics")
    Result<ProductStatisticsVO> getProductStatistics();
}
