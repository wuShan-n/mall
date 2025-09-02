package com.mall.product.service;

import com.mall.api.product.dto.response.AttributeVO;
import com.mall.common.result.Result;

import java.util.List;

/**
 * Attribute Service Interface
 */
public interface AttributeService {
    
    Result<List<AttributeVO>> getAttributesByCategoryId(Long categoryId);
    
    Result<List<AttributeVO>> getAttributesByCategoryIdAndType(Long categoryId, Integer attrType);
    
    Result<AttributeVO> getAttributeById(Long attributeId);
    
    Result<AttributeVO> createAttribute(AttributeVO attribute);
    
    Result<AttributeVO> updateAttribute(Long attributeId, AttributeVO attribute);
    
    Result<Void> deleteAttribute(Long attributeId);
}