package com.mall.search.strategy.impl;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import com.mall.search.dto.request.ProductSearchRequest;
import com.mall.search.strategy.SearchStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/**
 * 促销活动策略 - 大促、秒杀等场景
 */
@Slf4j
@Component("promotionSearchStrategy")
class PromotionSearchStrategy implements SearchStrategy {

    @Override
    public ProductSearchRequest.SearchScene getSupportedScene() {
        return ProductSearchRequest.SearchScene.PROMOTION;
    }

    @Override
    public Map<String, Float> getFieldBoosts() {
        Map<String, Float> boosts = new HashMap<>();
        boosts.put("productName", 2.0f);
        boosts.put("keywords", 1.5f);
        boosts.put("tags", 3.0f); // 标签权重高（促销标签）
        boosts.put("brandName", 0.8f);
        boosts.put("categoryName", 0.5f);
        return boosts;
    }
    
    @Override
    public void adjustQuery(BoolQuery.Builder boolQuery, ProductSearchRequest request) {
        log.debug("Using promotion search strategy");
        
        // 促销活动强调有促销标签的商品
        boolQuery.should(Query.of(q -> q
            .terms(t -> t
                .field("tags")
                .terms(TermsQueryField.of(tf -> tf
                    .value(Arrays.asList(
                        FieldValue.of("促销"),
                        FieldValue.of("特价"),
                        FieldValue.of("秒杀"),
                        FieldValue.of("折扣"),
                        FieldValue.of("满减")
                    ))
                ))
                .boost(3.0f)
            )
        ));
        
        // 促销商品需要有库存
        boolQuery.filter(Query.of(q -> q
            .term(t -> t
                .field("hasStock")
                .value(true)
            )
        ));
    }
    
    @Override
    public double getSalesBoostFactor() {
        return 2.0; // 促销商品销量很重要
    }
    
    @Override
    public double getSalesWeight() {
        return 2.5;
    }
    
    @Override
    public double getScoreBoostFactor() {
        return 0.1; // 促销时评分权重降低
    }
    
    @Override
    public double getScoreWeight() {
        return 0.5;
    }
    
    @Override
    public double getStockBoostWeight() {
        return 2.0; // 强调有货
    }
    
    @Override
    public double getNewProductWeight() {
        return 0.5; // 促销不强调新品
    }
    
    @Override
    public double getPopularityWeight() {
        return 2.0; // 强调热门商品
    }
    
    @Override
    public boolean needRandomScore() {
        return false; // 促销商品排序相对固定
    }
}