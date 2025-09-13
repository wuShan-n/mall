package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

/**
 * 热词DTO
 */
@Value
@Builder
@Schema(description = "热词DTO")
public class HotWordDTO {
    String keyword;
    Integer weight;
    String category;
}