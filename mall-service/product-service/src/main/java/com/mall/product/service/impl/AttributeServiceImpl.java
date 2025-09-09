package com.mall.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mall.api.product.dto.response.AttributeVO;
import com.mall.common.result.Result;
import com.mall.common.utils.Assert;
import com.mall.product.entity.ProductAttribute;
import com.mall.product.mapper.ProductAttributeMapper;
import com.mall.product.service.AttributeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Attribute Service Implementation
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {

    private final ProductAttributeMapper attributeMapper;

    @Override
    @Cacheable(value = "attribute", key = "'category:' + #categoryId")
    public Result<List<AttributeVO>> getAttributesByCategoryId(Long categoryId) {
        List<AttributeVO> attributes = attributeMapper.selectAttributesByCategoryId(categoryId);
        return Result.success(attributes);
    }

    @Override
    public Result<List<AttributeVO>> getAttributesByCategoryIdAndType(Long categoryId, Integer attrType) {
        List<AttributeVO> attributes = attributeMapper.selectAttributesByCategoryIdAndType(categoryId, attrType);
        return Result.success(attributes);
    }

    @Override
    public Result<AttributeVO> getAttributeById(Long attributeId) {
        ProductAttribute attribute = attributeMapper.selectById(attributeId);
        if (attribute == null) {
            return Result.failed("Attribute not found");
        }
        
        AttributeVO vo = new AttributeVO();
        BeanUtil.copyProperties(attribute, vo);
        return Result.success(vo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "attribute", allEntries = true)
    public Result<AttributeVO> createAttribute(AttributeVO attributeVO) {
        ProductAttribute attribute = new ProductAttribute();
        BeanUtil.copyProperties(attributeVO, attribute);
        attributeMapper.insert(attribute);
        
        attributeVO.setId(attribute.getId());
        return Result.success(attributeVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "attribute", allEntries = true)
    public Result<AttributeVO> updateAttribute(Long attributeId, AttributeVO attributeVO) {
        ProductAttribute attribute = attributeMapper.selectById(attributeId);
        Assert.notNull(attribute, "Attribute not found");
        
        BeanUtil.copyProperties(attributeVO, attribute, "id", "createTime", "createBy");
        attributeMapper.updateById(attribute);
        
        return Result.success(attributeVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "attribute", allEntries = true)
    public Result<Void> deleteAttribute(Long attributeId) {
        int result = attributeMapper.deleteById(attributeId);
        Assert.isTrue(result > 0, "Failed to delete attribute");
        
        return Result.success();
    }
}