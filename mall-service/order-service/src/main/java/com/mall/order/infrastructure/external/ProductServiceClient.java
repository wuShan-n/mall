package com.mall.order.infrastructure.external;

import com.mall.api.product.dto.response.SkuVO;
import com.mall.api.product.dto.response.SpuVO;
import com.mall.api.product.dubbo.ProductDubboService;
import com.mall.common.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 商品服务客户端
 */
@Slf4j
@Component
@RequiredArgsConstructor  
public class ProductServiceClient {
    
    @DubboReference(version = "1.0.0", check = false)
    private ProductDubboService productDubboService;
    
    /**
     * 获取SKU信息
     */
    public SkuVO getSkuById(Long skuId) {
        try {
            Result<SkuVO> result = productDubboService.getSkuById(skuId);
            if (!result.isSuccess()) {
                throw new RuntimeException("获取SKU信息失败: " + result.getMessage());
            }
            return result.getData();
        } catch (Exception e) {
            log.error("Call product service failed: {}", e.getMessage(), e);
            throw new RuntimeException("调用商品服务失败", e);
        }
    }
    
    /**
     * 批量获取SKU信息
     */
    public List<SkuVO> getSkusByIds(List<Long> skuIds) {
        try {
            Result<List<SkuVO>> result = productDubboService.getSkusByIds(skuIds);
            if (!result.isSuccess()) {
                throw new RuntimeException("批量获取SKU信息失败: " + result.getMessage());
            }
            return result.getData();
        } catch (Exception e) {
            log.error("Call product service failed: {}", e.getMessage(), e);
            throw new RuntimeException("调用商品服务失败", e);
        }
    }
}