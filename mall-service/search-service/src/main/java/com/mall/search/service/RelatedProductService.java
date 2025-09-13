package com.mall.search.service;

import com.mall.search.dto.response.ProductSimpleVO;

import java.util.List;

/**
 * 相关商品推荐服务
 */
public interface RelatedProductService {

    /**
     * 获取相关商品
     *
     * @param spuId 商品SPU ID
     * @param size 返回数量
     * @return 相关商品列表
     */
    List<ProductSimpleVO> getRelatedProducts(Long spuId, Integer size);
}
