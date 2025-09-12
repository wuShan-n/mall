package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 筛选项请求
 */
@Data
@Schema(description = "筛选项请求")
public class FilterRequest {
    @Schema(description = "搜索关键词")
    private String keyword;
    
    @Schema(description = "分类ID")
    private Long categoryId;
    
    @Schema(description = "已选品牌")
    private List<Long> selectedBrandIds;
    
    @Schema(description = "已选属性")
    private List<SelectedAttribute> selectedAttributes;
    
    @Schema(description = "是否包含子分类")
    private Boolean includeSubCategories = true;
    
    @Data
    public static class SelectedAttribute {
        private String attrName;
        private List<String> attrValues;
    }
}