package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
/**
 * 多字段搜索请求
 */
@Data
@Schema(description = "多字段搜索请求")
public class MultiFieldSearchRequest {
    @Schema(description = "搜索字段配置")
    private List<FieldConfig> fields;
    
    @Schema(description = "搜索关键词")
    private String keyword;
    
    @Schema(description = "匹配模式")
    private MatchMode matchMode = MatchMode.BEST_FIELDS;
    
    @Schema(description = "最小匹配度")
    private Float minScore;
    
    private Integer pageNum = 1;
    private Integer pageSize = 20;
    
    @Data
    public static class FieldConfig {
        private String fieldName;
        private Float boost;
        private String analyzer;
    }
    
    public enum MatchMode {
        BEST_FIELDS,    // 最佳字段匹配
        MOST_FIELDS,    // 多数字段匹配
        CROSS_FIELDS,   // 跨字段匹配
        PHRASE,         // 短语匹配
        PHRASE_PREFIX   // 短语前缀匹配
    }
}
