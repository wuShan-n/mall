package com.eco.common.core.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页响应结果")
public class PageResp<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "当前页")
    private Long current;

    @Schema(description = "每页大小")
    private Long size;

    @Schema(description = "总记录数")
    private Long total;

    @Schema(description = "总页数")
    private Long pages;

    @Schema(description = "数据列表")
    private List<T> records;

    public static <T> PageResp<T> of(Long current, Long size, Long total, List<T> records) {
        Long pages = total % size == 0 ? total / size : total / size + 1;
        return PageResp.<T>builder()
            .current(current)
            .size(size)
            .total(total)
            .pages(pages)
            .records(records)
            .build();
    }
}
