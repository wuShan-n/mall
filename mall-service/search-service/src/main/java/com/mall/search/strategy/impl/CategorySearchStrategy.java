package com.mall.search.strategy.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.strategy.SearchStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
/**
 * 分类浏览策略 - 用户在分类页浏览商品
 */
@Slf4j
@Component("categorySearchStrategy")
class CategorySearchStrategy implements SearchStrategy {

    @Override
    public ProductSearchRequest.SearchScene getSupportedScene() {
        return ProductSearchRequest.SearchScene.CATEGORY;
    }

    @Override
    public Map<String, Float> getFieldBoosts() {
        Map<String, Float> boosts = new HashMap<>();
        // 分类浏览时降低商品名称权重，提高分类相关性
        boosts.put("productName", 2.0f);
        boosts.put("keywords", 1.5f);
        boosts.put("categoryName", 2.0f); // 提高分类权重
        boosts.put("tags", 1.5f);
        boosts.put("brandName", 1.0f);
        return boosts;
    }
    
    @Override
    public void adjustQuery(BoolQuery.Builder boolQuery, ProductSearchRequest request) {
        // 分类浏览时强调热销商品
        log.debug("Using category search strategy");
    }
    
    @Override
    public double getSalesBoostFactor() {
        return 1.5; // 分类浏览更看重销量
    }
    
    @Override
    public double getSalesWeight() {
        return 3.0; // 提高销量权重
    }
    
    @Override
    public double getScoreBoostFactor() {
        return 0.3;
    }
    
    @Override
    public double getScoreWeight() {
        return 2.0; // 用户评分很重要
    }
    
    @Override
    public double getStockBoostWeight() {
        return 1.1;
    }
    
    @Override
    public double getNewProductWeight() {
        return 1.0; // 分类浏览不特别强调新品
    }
    
    @Override
    public double getPopularityWeight() {
        return 1.5; // 强调人气
    }
    
    @Override
    public boolean needRandomScore() {
        return false; // 分类浏览结果相对固定
    }
}