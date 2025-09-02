package com.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.api.product.dto.response.AttributeVO;
import com.mall.product.entity.ProductSpuAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Product SPU Attribute Mapper
 */
@Mapper
public interface ProductSpuAttributeMapper extends BaseMapper<ProductSpuAttribute> {
    
    /**
     * Get product attributes
     */
    List<AttributeVO> selectProductAttributes(@Param("spuId") Long spuId);
    
    /**
     * Batch insert attributes
     */
    int batchInsert(@Param("list") List<ProductSpuAttribute> list);
}