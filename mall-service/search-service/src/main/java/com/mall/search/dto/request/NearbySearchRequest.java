package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "附近搜索请求")
public class NearbySearchRequest {

    @Schema(description = "中心点经度")
    @NotNull
    private Double longitude;

    @Schema(description = "中心点纬度")
    @NotNull
    private Double latitude;

    @Schema(description = "搜索半径(km)")
    private Double distance = 5.0;

    @Schema(description = "商品关键词")
    private String keyword;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "每页大小")
    private Integer size = 20;
}