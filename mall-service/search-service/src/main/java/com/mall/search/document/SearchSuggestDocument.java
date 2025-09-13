package com.mall.search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * =====================================================
 * 4. 搜索建议索引 - 自动补全和热词
 * =====================================================
 */
@Data
@Document(indexName = "mall_suggest_v1")
@Setting(shards = 1, replicas = 2, refreshInterval = "30s")
public class SearchSuggestDocument {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String keyword;

    @CompletionField(maxInputLength = 100)
    private Completion suggest;

    @Field(type = FieldType.Integer)
    private Integer weight; // 权重

    @Field(type = FieldType.Integer)
    private Integer searchCount; // 搜索次数

    @Field(type = FieldType.Keyword)
    private String category; // 分类：PRODUCT, BRAND, CATEGORY, HOT

    @Field(type = FieldType.Keyword)
    private String source; // 来源：USER_SEARCH, PRODUCT_NAME, MANUAL

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime updateTime;

    @Data
    public static class Completion {
        private List<String> input;
        private Integer weight;
    }
}