package com.mall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.product.dto.request.BrandCreateRequest;
import com.mall.api.product.dto.response.BrandVO;
import com.mall.common.base.PageRequest;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.common.utils.Assert;
import com.mall.product.entity.ProductBrand;
import com.mall.product.mapper.ProductBrandMapper;
import com.mall.product.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Brand Service Implementation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final ProductBrandMapper brandMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "brand", allEntries = true)
    public Result<BrandVO> createBrand(BrandCreateRequest request) {
        // Check if brand code exists
        Long count = brandMapper.selectCount(new LambdaQueryWrapper<ProductBrand>()
                .eq(ProductBrand::getBrandCode, request.getBrandCode()));
        Assert.isTrue(count == 0, "Brand code already exists");

        ProductBrand brand = new ProductBrand();
        BeanUtil.copyProperties(request, brand);
        brandMapper.insert(brand);

        BrandVO vo = brandMapper.selectBrandWithCount(brand.getId());
        return Result.success(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "brand", allEntries = true)
    public Result<BrandVO> updateBrand(Long brandId, BrandCreateRequest request) {
        ProductBrand brand = brandMapper.selectById(brandId);
        Assert.notNull(brand, "Brand not found");

        BeanUtil.copyProperties(request, brand, "id", "createTime", "createBy");
        brandMapper.updateById(brand);

        BrandVO vo = brandMapper.selectBrandWithCount(brandId);
        return Result.success(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "brand", allEntries = true)
    public Result<Void> deleteBrand(Long brandId) {
        // Check if brand has products
        BrandVO brand = brandMapper.selectBrandWithCount(brandId);
        Assert.notNull(brand, "Brand not found");
        Assert.isTrue(brand.getProductCount() == 0, "Brand has products, cannot delete");

        int result = brandMapper.deleteById(brandId);
        Assert.isTrue(result > 0, "Failed to delete brand");

        return Result.success();
    }

    @Override
    public Result<BrandVO> getBrandById(Long brandId) {
        BrandVO brand = brandMapper.selectBrandWithCount(brandId);
        if (brand == null) {
            return Result.failed("Brand not found");
        }
        return Result.success(brand);
    }

    @Override
    @Cacheable(value = "brand", key = "'all'" )
    public Result<List<BrandVO>> getAllBrands() {
        List<ProductBrand> brands = brandMapper.selectList(new LambdaQueryWrapper<ProductBrand>()
                .eq(ProductBrand::getStatus, 1)
                .orderByAsc(ProductBrand::getSort));
        
        List<BrandVO> voList = BeanUtil.copyToList(brands, BrandVO.class);
        return Result.success(voList);
    }

    @Override
    public Result<PageResult<BrandVO>> queryBrands(PageRequest request) {
        IPage<BrandVO> page = new Page<>(request.getCurrent(), request.getSize());
        IPage<BrandVO> result = brandMapper.selectBrandPage(page, null);
        
        return Result.success(PageResult.of(
                result.getCurrent(),
                result.getSize(),
                result.getTotal(),
                result.getRecords()
        ));
    }

    @Override
    public Result<List<BrandVO>> getBrandsByCategoryId(Long categoryId) {
        List<BrandVO> brands = brandMapper.selectBrandsByCategoryId(categoryId);
        return Result.success(brands);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "brand", allEntries = true)
    public Result<Void> updateBrandStatus(Long brandId, Integer status) {
        ProductBrand brand = new ProductBrand();
        brand.setId(brandId);
        brand.setStatus(status);
        int result = brandMapper.updateById(brand);
        
        Assert.isTrue(result > 0, "Failed to update brand status");
        return Result.success();
    }
}