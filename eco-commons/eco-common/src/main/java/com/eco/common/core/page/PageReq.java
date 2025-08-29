package com.eco.common.core.page;

import com.eco.common.core.constant.CommonConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求参数
 */
@Data
@Schema(description = "分页请求参数")
public class PageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "当前页", defaultValue = "1")
    private Long current = CommonConstant.DEFAULT_PAGE;

    @Schema(description = "每页大小", defaultValue = "10")
    private Long size = CommonConstant.DEFAULT_PAGE_SIZE;

    @Schema(description = "排序字段")
    private String orderBy;

    @Schema(description = "排序方式", defaultValue = "asc")
    private String order = CommonConstant.ASC;

    public Long getOffset() {
        return (current - 1) * size;
    }
}
