package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

/**
 * 热搜词VO
 */
@Value
@Builder
@Schema(description = "热搜词")
public class HotKeywordVO {
    @Schema(description = "关键词")

    String keyword;

    @Schema(description = "搜索次数")

    Long searchCount;

    @Schema(description = "热度值")

    Integer hotValue;

    @Schema(description = "排名")

    Integer rank;

    @Schema(description = "趋势")

    String trend; // UP, DOWN, STABLE, NEW

    @Schema(description = "变化率")

    Float changeRate;

    @Schema(description = "标签")

    String tag;

    @Schema(description = "图标")

    String icon;
}