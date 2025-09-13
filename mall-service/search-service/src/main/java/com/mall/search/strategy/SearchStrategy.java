package com.mall.search.strategy;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import com.mall.search.dto.request.ProductSearchRequest;

import java.util.Map;

/**
 * 搜索策略接口
 * 针对不同的搜索场景提供不同的查询策略
 */
public interface SearchStrategy {

    /**
     * 获取支持的搜索场景
     */
    ProductSearchRequest.SearchScene getSupportedScene();
    
    /**
     * 获取字段权重配置
     * @return 字段名称和对应的权重
     */
    Map<String, Float> getFieldBoosts();
    
    /**
     * 调整查询条件
     * @param boolQuery Bool查询构建器
     * @param request 搜索请求
     */
    void adjustQuery(BoolQuery.Builder boolQuery, ProductSearchRequest request);
    
    /**
     * 获取销量提升因子
     */
    double getSalesBoostFactor();
    
    /**
     * 获取销量权重
     */
    double getSalesWeight();
    
    /**
     * 获取评分提升因子
     */
    double getScoreBoostFactor();
    
    /**
     * 获取评分权重
     */
    double getScoreWeight();
    
    /**
     * 获取库存提升权重
     */
    double getStockBoostWeight();
    
    /**
     * 获取新品权重
     */
    double getNewProductWeight();
    
    /**
     * 获取人气权重
     */
    double getPopularityWeight();
    
    /**
     * 是否需要随机打散
     */
    boolean needRandomScore();
}