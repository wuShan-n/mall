package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 热词DTO
 */
@Data
@Schema(description = "热词DTO")
public class HotWordDTO {
    private String keyword;
    private Integer weight;
    private String category;
}