package com.mall.search.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.CompletionSuggestOption;
import co.elastic.clients.elasticsearch.core.search.FieldSuggester;
import co.elastic.clients.elasticsearch.core.search.Suggester;
import co.elastic.clients.elasticsearch.core.search.CompletionSuggester;
import com.mall.search.service.SuggestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 搜索建议服务实现 - 使用ES Completion Suggester
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SuggestServiceImpl implements SuggestService {

    private final ElasticsearchClient elasticsearchClient;
    private static final String PRODUCT_INDEX = "mall_product_v1";

    @Override
    public List<String> suggest(String keyword, Integer size) {
        try {
            // 构建 CompletionSuggester
            CompletionSuggester completionSuggester = CompletionSuggester.of(cs -> cs
                    .field("suggest")
                    .size(size != null ? size : 10)
                    .skipDuplicates(true)
            );

            // 构建 FieldSuggester
            FieldSuggester fieldSuggester = FieldSuggester.of(fs -> fs
                    .text(keyword)
                    .completion(completionSuggester)
            );

            // 构建 Suggester
            Suggester suggester = Suggester.of(s -> s
                    .suggesters(Map.of("product_suggest", fieldSuggester))
            );

            // 构建搜索请求
            SearchRequest request = SearchRequest.of(sr -> sr
                    .index(PRODUCT_INDEX)
                    .size(0)
                    .suggest(suggester)
            );

            SearchResponse<Void> response = elasticsearchClient.search(request, Void.class);

            // 解析建议结果
            return response.suggest()
                    .get("product_suggest")
                    .stream()
                    .flatMap(suggest -> suggest.completion().options().stream())
                    .map(CompletionSuggestOption::text)
                    .distinct()
                    .limit(size != null ? size : 10)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Error getting suggestions for: {}", keyword, e);
            return Collections.emptyList();
        }
    }
}
