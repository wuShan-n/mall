package com.mall.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.api.product.dto.response.SkuVO;
import com.mall.product.entity.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Product SKU Mapper
 */
@Mapper
public interface ProductSkuMapper extends BaseMapper<ProductSku> {
    
    /**
     * Get SKUs by SPU ID
     */
    List<SkuVO> selectSkusBySpuId(@Param("spuId") Long spuId);
    
    /**
     * Get SKUs by IDs
     */
    List<SkuVO> selectSkusByIds(@Param("skuIds") List<Long> skuIds);
    
    /**
     * Lock stock (pessimistic lock)
     */
    @Update("UPDATE pms_sku SET lock_stock = lock_stock + #{quantity} " +
            "WHERE id = #{skuId} AND stock - lock_stock >= #{quantity} AND deleted = 0")
    int lockStock(@Param("skuId") Long skuId, @Param("quantity") Integer quantity);
    
    /**
     * Unlock stock
     */
    @Update("UPDATE pms_sku SET lock_stock = lock_stock - #{quantity} " +
            "WHERE id = #{skuId} AND lock_stock >= #{quantity} AND deleted = 0")
    int unlockStock(@Param("skuId") Long skuId, @Param("quantity") Integer quantity);
    
    /**
     * Deduct stock
     */
    @Update("UPDATE pms_sku SET stock = stock - #{quantity}, lock_stock = lock_stock - #{quantity}, " +
            "sale_count = sale_count + #{quantity} " +
            "WHERE id = #{skuId} AND lock_stock >= #{quantity} AND deleted = 0")
    int deductStock(@Param("skuId") Long skuId, @Param("quantity") Integer quantity);
    
    /**
     * Add stock
     */
    @Update("UPDATE pms_sku SET stock = stock + #{quantity} " +
            "WHERE id = #{skuId} AND deleted = 0")
    int addStock(@Param("skuId") Long skuId, @Param("quantity") Integer quantity);
    
    /**
     * Check stock availability
     */
    Integer checkStock(@Param("skuId") Long skuId, @Param("quantity") Integer quantity);
}