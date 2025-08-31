package com.mall.api.product.dubbo;

import com.mall.api.product.dto.response.AttributeVO;
import com.mall.common.result.Result;
import java.util.List;

/**
 * Attribute Dubbo RPC service interface
 */
public interface AttributeDubboService {
    
    /**
     * Get attributes by category ID
     */
    Result<List<AttributeVO>> getAttributesByCategoryId(Long categoryId);
    
    /**
     * Get attributes by category ID and type
     */
    Result<List<AttributeVO>> getAttributesByCategoryIdAndType(Long categoryId, Integer attrType);
    
    /**
     * Get attribute by ID
     */
    Result<AttributeVO> getAttributeById(Long attributeId);
    
    /**
     * Create attribute
     */
    Result<AttributeVO> createAttribute(AttributeVO attribute);
    
    /**
     * Update attribute
     */
    Result<AttributeVO> updateAttribute(Long attributeId, AttributeVO attribute);
    
    /**
     * Delete attribute
     */
    Result<Void> deleteAttribute(Long attributeId);
}
