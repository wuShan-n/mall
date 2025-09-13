package com.mall.search.strategy.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.strategy.SearchStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 品牌专区策略 - 品牌店铺或品牌专场
 */
@Slf4j
@Component("brandSearchStrategy")
class BrandSearchStrategy implements SearchStrategy {

    @Override
    public ProductSearchRequest.SearchScene getSupportedScene() {
        return ProductSearchRequest.SearchScene.BRAND;
    }

    @Override
    public Map<String, Float> getFieldBoosts() {
        Map<String, Float> boosts = new HashMap<>();
        boosts.put("productName", 2.5f);
        boosts.put("brandName", 3.0f); // 品牌名称权重最高
        boosts.put("keywords", 2.0f);
        boosts.put("tags", 1.0f);
        boosts.put("categoryName", 0.5f);
        return boosts;
    }
    
    @Override
    public void adjustQuery(BoolQuery.Builder boolQuery, ProductSearchRequest request) {
        log.debug("Using brand search strategy");
        
        // 品牌专区可能需要展示该品牌的热门系列
        if (request.getBrandIds() != null && !request.getBrandIds().isEmpty()) {
            // 提升品牌相关性
            boolQuery.should(Query.of(q -> q
                .terms(t -> t
                    .field("brandId")
                    .terms(TermsQueryField.of(tf -> tf
                        .value(request.getBrandIds().stream()
                            .map(FieldValue::of)
                            .collect(Collectors.toList()))
                    ))
                    .boost(2.0f)
                )
            ));
        }
    }
    
    @Override
    public double getSalesBoostFactor() {
        return 1.0;
    }
    
    @Override
    public double getSalesWeight() {
        return 1.5; // 品牌专区适度考虑销量
    }
    
    @Override
    public double getScoreBoostFactor() {
        return 0.25;
    }
    
    @Override
    public double getScoreWeight() {
        return 1.8;
    }
    
    @Override
    public double getStockBoostWeight() {
        return 1.3;
    }
    
    @Override
    public double getNewProductWeight() {
        return 2.0; // 品牌专区强调新品
    }
    
    @Override
    public double getPopularityWeight() {
        return 1.2;
    }
    
    @Override
    public boolean needRandomScore() {
        return true; // 适度打散，展示更多商品
    }
}