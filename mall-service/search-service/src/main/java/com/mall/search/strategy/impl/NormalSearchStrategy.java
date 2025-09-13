package com.mall.search.strategy.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.strategy.SearchStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认搜索策略 - 普通搜索场景
 */
@Slf4j
@Component("normalSearchStrategy")
public class NormalSearchStrategy implements SearchStrategy {

    @Override
    public ProductSearchRequest.SearchScene getSupportedScene() {
        return ProductSearchRequest.SearchScene.NORMAL;
    }

    @Override
    public Map<String, Float> getFieldBoosts() {
        Map<String, Float> boosts = new HashMap<>();
        boosts.put("productName", 3.0f);
        boosts.put("productName.pinyin", 1.5f);
        boosts.put("keywords", 2.0f);
        boosts.put("tags", 1.0f);
        boosts.put("brandName", 1.0f);
        boosts.put("categoryName", 0.8f);
        return boosts;
    }

    @Override
    public void adjustQuery(BoolQuery.Builder boolQuery, ProductSearchRequest request) {
        // 普通搜索不需要特殊调整
        log.debug("Using normal search strategy");
    }

    @Override
    public double getSalesBoostFactor() {
        return 1.2;
    }

    @Override
    public double getSalesWeight() {
        return 2.0;
    }

    @Override
    public double getScoreBoostFactor() {
        return 0.2;
    }

    @Override
    public double getScoreWeight() {
        return 1.5;
    }

    @Override
    public double getStockBoostWeight() {
        return 1.2;
    }

    @Override
    public double getNewProductWeight() {
        return 1.1;
    }

    @Override
    public double getPopularityWeight() {
        return 1.0;
    }

    @Override
    public boolean needRandomScore() {
        return true; // 需要一定的随机性避免结果固化
    }
}