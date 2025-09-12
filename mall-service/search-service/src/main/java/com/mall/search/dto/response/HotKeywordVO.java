package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 热搜词VO
 */
@Data
@Builder
@Schema(description = "热搜词")
public class HotKeywordVO {
    @Schema(description = "关键词")
    private String keyword;
    
    @Schema(description = "搜索次数")
    private Long searchCount;
    
    @Schema(description = "热度值")
    private Integer hotValue;
    
    @Schema(description = "排名")
    private Integer rank;
    
    @Schema(description = "趋势")
    private String trend; // UP, DOWN, STABLE, NEW
    
    @Schema(description = "变化率")
    private Float changeRate;
    
    @Schema(description = "标签")
    private String tag;
    
    @Schema(description = "图标")
    private String icon;
}