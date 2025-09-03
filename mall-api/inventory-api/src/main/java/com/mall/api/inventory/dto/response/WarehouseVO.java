package com.mall.api.inventory.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Warehouse view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Warehouse information")
public class WarehouseVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Warehouse ID", example = "1")
    private Long id;
    
    @Schema(description = "Warehouse name", example = "Beijing Main Warehouse")
    private String warehouseName;
    
    @Schema(description = "Warehouse code", example = "WH001")
    private String warehouseCode;
    
    @Schema(description = "Warehouse type: 1-Main, 2-Branch, 3-Transit", example = "1")
    private Integer warehouseType;
    
    @Schema(description = "Warehouse type name", example = "Main Warehouse")
    private String warehouseTypeName;
    
    @Schema(description = "Contact person", example = "John Doe")
    private String contactPerson;
    
    @Schema(description = "Contact phone", example = "13800138000")
    private String contactPhone;
    
    @Schema(description = "Province", example = "Beijing")
    private String province;
    
    @Schema(description = "City", example = "Beijing")
    private String city;
    
    @Schema(description = "District", example = "Chaoyang")
    private String district;
    
    @Schema(description = "Detailed address", example = "No.1 Warehouse Road")
    private String address;
    
    @Schema(description = "Full address", example = "Beijing Beijing Chaoyang No.1 Warehouse Road")
    private String fullAddress;
    
    @Schema(description = "Longitude", example = "116.404")
    private BigDecimal longitude;
    
    @Schema(description = "Latitude", example = "39.915")
    private BigDecimal latitude;
    
    @Schema(description = "Status: 1-Active, 0-Inactive", example = "1")
    private Integer status;
    
    @Schema(description = "Total SKUs", example = "1000")
    private Integer totalSkus;
    
    @Schema(description = "Total stock value", example = "1000000.00")
    private BigDecimal totalStockValue;
    
    @Schema(description = "Remark", example = "Main distribution center")
    private String remark;
    
    @Schema(description = "Create time", example = "2024-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}