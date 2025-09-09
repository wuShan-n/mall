package com.mall.product.provider;

import com.mall.api.product.dto.response.AttributeVO;
import com.mall.api.product.dubbo.AttributeDubboService;
import com.mall.common.result.Result;
import com.mall.product.service.AttributeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * Attribute Dubbo Service Provider Implementation
 */
@Slf4j
@DubboService(version = "1.0.0", timeout = 3000, retries = 0)
@RequiredArgsConstructor
public class AttributeDubboServiceImpl implements AttributeDubboService {

    private final AttributeService attributeService;

    @Override
    public Result<List<AttributeVO>> getAttributesByCategoryId(Long categoryId) {
        log.debug("Dubbo RPC: getAttributesByCategoryId, categoryId: {}", categoryId);
        return attributeService.getAttributesByCategoryId(categoryId);
    }

    @Override
    public Result<List<AttributeVO>> getAttributesByCategoryIdAndType(Long categoryId, Integer attrType) {
        log.debug("Dubbo RPC: getAttributesByCategoryIdAndType, categoryId: {}, attrType: {}", 
                categoryId, attrType);
        return attributeService.getAttributesByCategoryIdAndType(categoryId, attrType);
    }

    @Override
    public Result<AttributeVO> getAttributeById(Long attributeId) {
        log.debug("Dubbo RPC: getAttributeById, attributeId: {}", attributeId);
        return attributeService.getAttributeById(attributeId);
    }

    @Override
    public Result<AttributeVO> createAttribute(AttributeVO attribute) {
        log.info("Dubbo RPC: createAttribute, name: {}", attribute.getAttrName());
        return attributeService.createAttribute(attribute);
    }

    @Override
    public Result<AttributeVO> updateAttribute(Long attributeId, AttributeVO attribute) {
        log.info("Dubbo RPC: updateAttribute, attributeId: {}", attributeId);
        return attributeService.updateAttribute(attributeId, attribute);
    }

    @Override
    public Result<Void> deleteAttribute(Long attributeId) {
        log.info("Dubbo RPC: deleteAttribute, attributeId: {}", attributeId);
        return attributeService.deleteAttribute(attributeId);
    }
}