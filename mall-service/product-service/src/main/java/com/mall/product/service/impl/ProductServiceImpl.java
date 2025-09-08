// ===== ProductServiceImpl.java =====
package com.mall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.api.product.dto.request.ProductQueryRequest;
import com.mall.api.product.dto.request.SkuCreateRequest;
import com.mall.api.product.dto.request.SpuCreateRequest;
import com.mall.api.product.dto.response.*;
import com.mall.api.product.enums.ProductStatusEnum;
import com.mall.common.result.PageResult;
import com.mall.common.result.Result;
import com.mall.common.result.ResultCode;
import com.mall.common.utils.Assert;
import com.mall.product.entity.ProductSku;
import com.mall.product.entity.ProductSpu;
import com.mall.product.entity.ProductSpuAttribute;
import com.mall.product.mapper.ProductSkuMapper;
import com.mall.product.mapper.ProductSpuAttributeMapper;
import com.mall.product.mapper.ProductSpuMapper;
import com.mall.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Product Service Implementation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final String PRODUCT_CACHE_KEY = "product:spu:";
    private static final String STOCK_LOCK_KEY = "stock:lock:";
    private final ProductSpuMapper spuMapper;
    private final ProductSkuMapper skuMapper;
    private final ProductSpuAttributeMapper spuAttributeMapper;

    private final RedissonClient redissonClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<SpuVO> createSpu(SpuCreateRequest request) {
        // 1. Check if product code exists
        Long count = spuMapper.selectCount(new LambdaQueryWrapper<ProductSpu>()
                .eq(ProductSpu::getProductCode, request.getProductCode()));
        Assert.isTrue(count == 0, "Product code already exists");

        // 2. Create SPU
        ProductSpu spu = new ProductSpu();
        BeanUtil.copyProperties(request, spu);
        spu.setImages(JSON.toJSONString(request.getImages()));
        spu.setSaleCount(0);
        spu.setViewCount(0);
        spu.setCommentCount(0);
        spu.setScore(BigDecimal.ZERO);
        spuMapper.insert(spu);

        // 3. Create SKUs
        if (CollUtil.isNotEmpty(request.getSkuList())) {
            for (SkuCreateRequest skuRequest : request.getSkuList()) {
                ProductSku sku = new ProductSku();
                BeanUtil.copyProperties(skuRequest, sku);
                sku.setSpuId(spu.getId());
                sku.setSpecs(JSON.toJSONString(skuRequest.getSpecs()));
                sku.setSaleCount(0);
                sku.setLockStock(0);
                skuMapper.insert(sku);
            }
        }

        // 4. Save attributes
        if (CollUtil.isNotEmpty(request.getAttributes())) {
            List<ProductSpuAttribute> attributes = request.getAttributes().stream()
                    .map(attr -> {
                        ProductSpuAttribute spuAttr = new ProductSpuAttribute();
                        spuAttr.setSpuId(spu.getId());
                        spuAttr.setAttributeId(attr.getAttributeId());
                        spuAttr.setAttributeName(attr.getAttributeName());
                        spuAttr.setAttributeValue(attr.getAttributeValue());
                        return spuAttr;
                    }).toList();
            spuAttributeMapper.batchInsert(attributes);
        }

        SpuVO vo = new SpuVO();
        BeanUtil.copyProperties(spu, vo);
        return Result.success(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<SpuVO> updateSpu(Long spuId, SpuCreateRequest request) {
        // 1. Check if SPU exists
        ProductSpu spu = spuMapper.selectById(spuId);
        Assert.notNull(spu, "Product not found");

        // 2. Update SPU
        BeanUtil.copyProperties(request, spu, "id", "createTime", "createBy");
        spu.setImages(JSON.toJSONString(request.getImages()));
        spuMapper.updateById(spu);

        // 3. Update SKUs if provided
        if (CollUtil.isNotEmpty(request.getSkuList())) {
            // Delete old SKUs
            skuMapper.delete(new LambdaQueryWrapper<ProductSku>()
                    .eq(ProductSku::getSpuId, spuId));

            // Insert new SKUs
            for (SkuCreateRequest skuRequest : request.getSkuList()) {
                ProductSku sku = new ProductSku();
                BeanUtil.copyProperties(skuRequest, sku);
                sku.setSpuId(spuId);
                sku.setSpecs(JSON.toJSONString(skuRequest.getSpecs()));
                sku.setSaleCount(0);
                sku.setLockStock(0);
                skuMapper.insert(sku);
            }
        }

        // 4. Update attributes
        if (CollUtil.isNotEmpty(request.getAttributes())) {
            // Delete old attributes
            spuAttributeMapper.delete(new LambdaQueryWrapper<ProductSpuAttribute>()
                    .eq(ProductSpuAttribute::getSpuId, spuId));

            // Insert new attributes
            List<ProductSpuAttribute> attributes = request.getAttributes().stream()
                    .map(attr -> {
                        ProductSpuAttribute spuAttr = new ProductSpuAttribute();
                        spuAttr.setSpuId(spuId);
                        spuAttr.setAttributeId(attr.getAttributeId());
                        spuAttr.setAttributeName(attr.getAttributeName());
                        spuAttr.setAttributeValue(attr.getAttributeValue());
                        return spuAttr;
                    }).collect(Collectors.toList());
            spuAttributeMapper.batchInsert(attributes);
        }

        SpuVO vo = new SpuVO();
        BeanUtil.copyProperties(spu, vo);
        return Result.success(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "product", key = "#spuId")
    public Result<Void> deleteSpu(Long spuId) {
        // Check if product has orders
        // This would typically check with order service

        int result = spuMapper.deleteById(spuId);
        Assert.isTrue(result > 0, "Failed to delete product");

        return Result.success();
    }

    @Override
    @Cacheable(value = "product", key = "#spuId", unless = "#result == null")
    public Result<SpuVO> getSpuById(Long spuId) {
        SpuVO spu = spuMapper.selectSpuDetail(spuId);
        if (spu == null) {
            return Result.failed(ResultCode.PRODUCT_NOT_FOUND);
        }

        // Increase view count asynchronously
        spuMapper.increaseViewCount(spuId);

        return Result.success(spu);
    }

    @Override
    public Result<SpuDetailVO> getSpuDetail(Long spuId) {
        // 1. Get SPU basic info
        SpuVO spu = spuMapper.selectSpuDetail(spuId);
        if (spu == null) {
            return Result.failed(ResultCode.PRODUCT_NOT_FOUND);
        }

        SpuDetailVO detail = new SpuDetailVO();
        BeanUtil.copyProperties(spu, detail);

        // 2. Get SKU list
        List<SkuVO> skuList = skuMapper.selectSkusBySpuId(spuId);
        detail.setSkuList(skuList);

        // 3. Get attributes
        List<AttributeVO> attributes = spuAttributeMapper.selectProductAttributes(spuId);
        detail.setAttributes(attributes);

        // 4. Build specifications
        if (CollUtil.isNotEmpty(skuList)) {
            Map<String, Set<String>> specMap = new HashMap<>();
            for (SkuVO sku : skuList) {
                if (StrUtil.isNotBlank(sku.getSpecs().toString())) {
                    Map<String, String> specs = JSON.parseObject(sku.getSpecs().toString(), Map.class);
                    specs.forEach((key, value) ->
                            specMap.computeIfAbsent(key, k -> new HashSet<>()).add(value)
                    );
                }
            }

            List<SpecificationVO> specifications = specMap.entrySet().stream()
                    .map(entry -> {
                        SpecificationVO spec = new SpecificationVO();
                        spec.setSpecName(entry.getKey());
                        spec.setSpecValues(new ArrayList<>(entry.getValue()));
                        return spec;
                    }).collect(Collectors.toList());
            detail.setSpecifications(specifications);
        }

        // 5. Get detail HTML
        ProductSpu spuEntity = spuMapper.selectById(spuId);
        detail.setDetailHtml(spuEntity.getDetailHtml());
        detail.setDetailMobileHtml(spuEntity.getDetailMobileHtml());

        return Result.success(detail);
    }

    @Override
    public Result<PageResult<SpuVO>> queryProducts(ProductQueryRequest request) {
        IPage<SpuVO> page = new Page<>(request.getCurrent(), request.getSize());
        IPage<SpuVO> result = spuMapper.selectProductPage(page, request);

        return Result.success(PageResult.of(
                result.getCurrent(),
                result.getSize(),
                result.getTotal(),
                result.getRecords()
        ));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "product", key = "#spuId")
    public Result<Void> updateProductStatus(Long spuId, Integer status) {
        ProductSpu spu = new ProductSpu();
        spu.setId(spuId);
        spu.setStatus(status);
        int result = spuMapper.updateById(spu);

        Assert.isTrue(result > 0, "Failed to update product status");
        return Result.success();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> batchUpdateProductStatus(List<Long> spuIds, Integer status) {
        if (CollUtil.isEmpty(spuIds)) {
            return Result.failed("Product IDs cannot be empty");
        }

        int result = spuMapper.batchUpdateStatus(spuIds, status);
        Assert.isTrue(result > 0, "Failed to update product status");

        // Clear cache
        spuIds.forEach(id -> evictProductCache(id));

        return Result.success();
    }

    @Override
    public Result<SkuVO> getSkuById(Long skuId) {
        ProductSku sku = skuMapper.selectById(skuId);
        if (sku == null) {
            return Result.failed("SKU not found");
        }

        SkuVO vo = new SkuVO();
        BeanUtil.copyProperties(sku, vo,"specs");
        vo.setSpecs(BeanUtil.beanToMap(sku.getSpecs()));
        return Result.success(vo);
    }

    @Override
    public Result<List<SkuVO>> getSkusBySpuId(Long spuId) {
        List<SkuVO> skus = skuMapper.selectSkusBySpuId(spuId);
        return Result.success(skus);
    }

    @Override
    public Result<List<SkuVO>> getSkusByIds(List<Long> skuIds) {
        if (CollUtil.isEmpty(skuIds)) {
            return Result.success(new ArrayList<>());
        }

        List<SkuVO> skus = skuMapper.selectSkusByIds(skuIds);
        return Result.success(skus);
    }

    @Override
    public Result<ProductStatisticsVO> getProductStatistics() {
        ProductStatisticsVO stats = new ProductStatisticsVO();

        // Get total products
        stats.setTotalProducts(spuMapper.selectCount(null));

        // Get on/off shelf products
        stats.setOnShelfProducts(spuMapper.selectCount(new LambdaQueryWrapper<ProductSpu>()
                .eq(ProductSpu::getStatus, ProductStatusEnum.UP.getCode())));
        stats.setOffShelfProducts(spuMapper.selectCount(new LambdaQueryWrapper<ProductSpu>()
                .eq(ProductSpu::getStatus, ProductStatusEnum.DOWN.getCode())));

        // Get SKU statistics
        stats.setTotalSkus(skuMapper.selectCount(null));
        stats.setOutOfStockSkus(skuMapper.selectCount(new LambdaQueryWrapper<ProductSku>()
                .eq(ProductSku::getStock, 0)));
        stats.setLowStockSkus(skuMapper.selectCount(new LambdaQueryWrapper<ProductSku>()
                .apply("stock > 0 AND stock <= warn_stock")));

        // Today's new products
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        stats.setTodayNewProducts(spuMapper.selectCount(new LambdaQueryWrapper<ProductSpu>()
                .ge(ProductSpu::getCreateTime, todayStart)));

        // This month's new products
        LocalDateTime monthStart = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        stats.setMonthNewProducts(spuMapper.selectCount(new LambdaQueryWrapper<ProductSpu>()
                .ge(ProductSpu::getCreateTime, monthStart)));

        return Result.success(stats);
    }

    @CacheEvict(value = "product", key = "#spuId")
    public void evictProductCache(Long spuId) {
        log.debug("Evicting product cache for SPU: {}", spuId);
    }
}