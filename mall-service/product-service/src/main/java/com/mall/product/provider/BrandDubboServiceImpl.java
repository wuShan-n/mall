package com.mall.product.provider;

import com.mall.api.product.dubbo.BrandDubboService;
import com.mall.api.product.dto.request.BrandCreateRequest;
import com.mall.api.product.dto.response.BrandVO;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * Brand Dubbo Service Provider Implementation
 */
@Slf4j
@DubboService(version = "1.0.0", timeout = 3000, retries = 0)
@RequiredArgsConstructor
public class BrandDubboServiceImpl implements BrandDubboService {

    private final BrandService brandService;

    @Override
    public Result<BrandVO> createBrand(BrandCreateRequest request) {
        log.info("Dubbo RPC: createBrand, name: {}", request.getBrandName());
        return brandService.createBrand(request);
    }

    @Override
    public Result<BrandVO> updateBrand(Long brandId, BrandCreateRequest request) {
        log.info("Dubbo RPC: updateBrand, brandId: {}", brandId);
        return brandService.updateBrand(brandId, request);
    }

    @Override
    public Result<Void> deleteBrand(Long brandId) {
        log.info("Dubbo RPC: deleteBrand, brandId: {}", brandId);
        return brandService.deleteBrand(brandId);
    }

    @Override
    public Result<BrandVO> getBrandById(Long brandId) {
        log.debug("Dubbo RPC: getBrandById, brandId: {}", brandId);
        return brandService.getBrandById(brandId);
    }

    @Override
    public Result<List<BrandVO>> getAllBrands() {
        log.debug("Dubbo RPC: getAllBrands");
        return brandService.getAllBrands();
    }

    @Override
    public Result<PageResult<BrandVO>> queryBrands(PageRequest request) {
        log.debug("Dubbo RPC: queryBrands");
        return brandService.queryBrands(request);
    }

    @Override
    public Result<List<BrandVO>> getBrandsByCategoryId(Long categoryId) {
        log.debug("Dubbo RPC: getBrandsByCategoryId, categoryId: {}", categoryId);
        return brandService.getBrandsByCategoryId(categoryId);
    }

    @Override
    public Result<Void> updateBrandStatus(Long brandId, Integer status) {
        log.info("Dubbo RPC: updateBrandStatus, brandId: {}, status: {}", brandId, status);
        return brandService.updateBrandStatus(brandId, status);
    }
}