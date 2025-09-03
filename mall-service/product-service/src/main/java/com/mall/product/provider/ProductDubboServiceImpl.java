package com.mall.product.provider;

import com.mall.api.product.dubbo.ProductDubboService;
import com.mall.api.product.dto.request.ProductQueryRequest;
import com.mall.api.product.dto.request.SpuCreateRequest;
import com.mall.api.product.dto.request.StockUpdateRequest;
import com.mall.api.product.dto.response.ProductStatisticsVO;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.product.dto.response.SpuDetailVO;
import com.mall.api.product.dto.response.SpuVO;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * Product Dubbo Service Provider Implementation
 */
@Slf4j
@DubboService(version = "1.0.0", timeout = 6000, retries = 0)
@RequiredArgsConstructor
public class ProductDubboServiceImpl implements ProductDubboService {

    private final ProductService productService;

    @Override
    public Result<SpuVO> createSpu(SpuCreateRequest request) {
        log.info("Dubbo RPC: createSpu, request: {}", request.getProductCode());
        return productService.createSpu(request);
    }

    @Override
    public Result<SpuVO> updateSpu(Long spuId, SpuCreateRequest request) {
        log.info("Dubbo RPC: updateSpu, spuId: {}", spuId);
        return productService.updateSpu(spuId, request);
    }

    @Override
    public Result<Void> deleteSpu(Long spuId) {
        log.info("Dubbo RPC: deleteSpu, spuId: {}", spuId);
        return productService.deleteSpu(spuId);
    }

    @Override
    public Result<SpuVO> getSpuById(Long spuId) {
        log.debug("Dubbo RPC: getSpuById, spuId: {}", spuId);
        return productService.getSpuById(spuId);
    }

    @Override
    public Result<SpuDetailVO> getSpuDetail(Long spuId) {
        log.debug("Dubbo RPC: getSpuDetail, spuId: {}", spuId);
        return productService.getSpuDetail(spuId);
    }

    @Override
    public Result<PageResult<SpuVO>> queryProducts(ProductQueryRequest request) {
        log.debug("Dubbo RPC: queryProducts");
        return productService.queryProducts(request);
    }

    @Override
    public Result<Void> updateProductStatus(Long spuId, Integer status) {
        log.info("Dubbo RPC: updateProductStatus, spuId: {}, status: {}", spuId, status);
        return productService.updateProductStatus(spuId, status);
    }

    @Override
    public Result<Void> batchUpdateProductStatus(List<Long> spuIds, Integer status) {
        log.info("Dubbo RPC: batchUpdateProductStatus, count: {}, status: {}", spuIds.size(), status);
        return productService.batchUpdateProductStatus(spuIds, status);
    }

    @Override
    public Result<SkuVO> getSkuById(Long skuId) {
        log.debug("Dubbo RPC: getSkuById, skuId: {}", skuId);
        return productService.getSkuById(skuId);
    }

    @Override
    public Result<List<SkuVO>> getSkusBySpuId(Long spuId) {
        log.debug("Dubbo RPC: getSkusBySpuId, spuId: {}", spuId);
        return productService.getSkusBySpuId(spuId);
    }

    @Override
    public Result<List<SkuVO>> getSkusByIds(List<Long> skuIds) {
        log.debug("Dubbo RPC: getSkusByIds, count: {}", skuIds.size());
        return productService.getSkusByIds(skuIds);
    }

    @Override
    public Result<ProductStatisticsVO> getProductStatistics() {
        log.debug("Dubbo RPC: getProductStatistics");
        return productService.getProductStatistics();
    }
}

