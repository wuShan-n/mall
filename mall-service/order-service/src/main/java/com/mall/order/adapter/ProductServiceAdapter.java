package com.mall.order.adapter;

import com.mall.api.product.dubbo.ProductDubboService;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.product.dto.response.SpuVO;
import com.mall.common.exception.BusinessException;
import com.mall.common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品服务适配器 封装商品相关的Dubbo调用，保持订单服务的独立性
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductServiceAdapter {

    @DubboReference(version = "1.0.0", group = "mall")
    private ProductDubboService productDubboService;

    /**
     * 批量获取SKU信息
     */
    public Map<Long, SkuVO> getSkuInfoMap(List<Long> skuIds) {
        Result<List<SkuVO>> result = productDubboService.getSkusByIds(skuIds);
        if (!result.isSuccess()) {
            throw new BusinessException("获取商品信息失败: " + result.getMessage());
        }

        return result.getData().stream()
                .collect(Collectors.toMap(SkuVO::getId, sku -> sku));
    }

    /**
     * 获取单个SKU信息
     */
    public SkuVO getSkuInfo(Long skuId) {
        Result<SkuVO> result = productDubboService.getSkuById(skuId);
        if (!result.isSuccess()) {
            throw new BusinessException("获取商品信息失败: " + result.getMessage());
        }
        return result.getData();
    }

    /**
     * 获取SPU信息
     */
    public SpuVO getSpuInfo(Long spuId) {
        Result<SpuVO> result = productDubboService.getSpuById(spuId);
        if (!result.isSuccess()) {
            log.error("获取SPU信息失败: spuId={}, error={}", spuId, result.getMessage());
            return null;
        }
        return result.getData();
    }

    /**
     * 校验商品状态（批量）
     */
    public void validateProductStatus(List<Long> skuIds) {
        // 注意：根据API文档，product-api缺少批量校验商品状态的接口
        // 这里我们通过批量获取SKU信息来校验
        Map<Long, SkuVO> skuMap = getSkuInfoMap(skuIds);

        for (Long skuId : skuIds) {
            SkuVO sku = skuMap.get(skuId);
            if (sku == null) {
                throw new BusinessException("商品不存在: " + skuId);
            }

            // 检查SKU状态（假设1为上架状态）
            if (sku.getStatus() == null || !sku.getStatus().equals(1)) {
                throw new BusinessException("商品已下架: " + skuId);
            }
        }
    }

    /**
     * 计算运费（简化版本，需要product-api补充相关接口）
     */
    public BigDecimal calculateFreight(Long spuId, String provinceCode, String cityCode, Integer quantity) {
        // TODO: 等待product-api补充运费计算接口
        // Result<BigDecimal> result = productDubboService.calculateFreight(spuId, provinceCode, cityCode, quantity);

        // 目前使用简化逻辑
        if (provinceCode != null && provinceCode.startsWith("11")) { // 北京
            return new BigDecimal("5.00");
        } else if (provinceCode != null && (provinceCode.startsWith("31") || provinceCode.startsWith("32"))) { // 上海、江苏
            return new BigDecimal("8.00");
        } else {
            return new BigDecimal("10.00"); // 其他地区
        }
    }

    /**
     * 获取商品运费模板（需要product-api补充）
     */
    public String getFreightTemplate(Long spuId) {
        // TODO: 等待product-api补充运费模板接口
        // Result<FreightTemplateVO> result = productDubboService.getFreightTemplate(spuId);
        return "default"; // 默认运费模板
    }

    /**
     * 检查商品是否可以下单
     */
    public void checkProductAvailable(List<Long> skuIds) {
        Map<Long, SkuVO> skuMap = getSkuInfoMap(skuIds);

        for (Long skuId : skuIds) {
            SkuVO sku = skuMap.get(skuId);
            if (sku == null) {
                throw new BusinessException("商品不存在: " + skuId);
            }

            // 检查商品状态
            if (sku.getStatus() == null || !sku.getStatus().equals(1)) {
                throw new BusinessException("商品不可购买: " + sku.getSkuName());
            }

            // 检查价格
            if (sku.getPrice() == null || sku.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BusinessException("商品价格异常: " + sku.getSkuName());
            }
        }

        log.info("商品可用性检查通过: skuIds={}", skuIds);
    }

    /**
     * 获取商品的库存预警信息
     */
    public Integer getWarnStock(Long skuId) {
        SkuVO sku = getSkuInfo(skuId);
        return sku.getWarnStock() != null ? sku.getWarnStock() : 10; // 默认预警库存为10
    }
}
