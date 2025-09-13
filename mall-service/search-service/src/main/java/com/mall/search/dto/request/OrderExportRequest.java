package com.mall.search.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 订单导出请求
 */
@Data
@Schema(description = "订单导出请求")
public class OrderExportRequest extends OrderSearchRequest {
    @Schema(description = "导出格式")
    private ExportFormat format = ExportFormat.EXCEL;

    @Schema(description = "导出字段")
    private List<String> fields;

    @Schema(description = "是否异步")
    private Boolean async = false;

    public enum ExportFormat {
        EXCEL, CSV, PDF
    }
}