package com.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.api.product.dto.response.AttributeVO;
import com.mall.product.entity.ProductAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Product Attribute Mapper
 */
@Mapper
public interface ProductAttributeMapper extends BaseMapper<ProductAttribute> {
    
    /**
     * Get attributes by category ID
     */
    List<AttributeVO> selectAttributesByCategoryId(@Param("categoryId") Long categoryId);
    
    /**
     * Get attributes by category ID and type
     */
    List<AttributeVO> selectAttributesByCategoryIdAndType(
            @Param("categoryId") Long categoryId, 
            @Param("attrType") Integer attrType);
}