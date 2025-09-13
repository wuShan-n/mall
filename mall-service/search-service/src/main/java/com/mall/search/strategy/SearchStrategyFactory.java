package com.mall.search.strategy;

import com.mall.search.dto.request.ProductSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 搜索策略工厂
 */
@Slf4j
@Component
public class SearchStrategyFactory {

    private final Map<ProductSearchRequest.SearchScene, SearchStrategy> strategyMap;

    // Spring 会自动注入所有 SearchStrategy 接口的实现类
    public SearchStrategyFactory(List<SearchStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        SearchStrategy::getSupportedScene,
                        Function.identity()
                ));
        log.info("SearchStrategyFactory initialized with {} strategies", strategyMap.size());
    }

    /**
     * 获取搜索策略
     */
    public SearchStrategy getStrategy(ProductSearchRequest.SearchScene scene) {
        return strategyMap.get(scene);
    }
}