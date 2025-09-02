package com.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mall.api.product.dto.request.ProductQueryRequest;
import com.mall.api.product.dto.response.SpuVO;
import com.mall.product.entity.ProductSpu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Product SPU Mapper
 */
@Mapper
public interface ProductSpuMapper extends BaseMapper<ProductSpu> {
    
    /**
     * Query products with complex conditions
     */
    IPage<SpuVO> selectProductPage(IPage<?> page, @Param("query") ProductQueryRequest query);
    
    /**
     * Get SPU detail with category and brand info
     */
    SpuVO selectSpuDetail(@Param("spuId") Long spuId);
    
    /**
     * Batch update product status
     */
    int batchUpdateStatus(@Param("spuIds") List<Long> spuIds, @Param("status") Integer status);
    
    /**
     * Increase view count
     */
    int increaseViewCount(@Param("spuId") Long spuId);
    
    /**
     * Update sales count
     */
    int updateSaleCount(@Param("spuId") Long spuId, @Param("count") Integer count);
}