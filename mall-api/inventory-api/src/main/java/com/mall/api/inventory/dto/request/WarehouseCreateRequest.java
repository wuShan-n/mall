package com.mall.api.inventory.dto.request;

import com.mall.common.base.BaseDTO;
import com.mall.common.validation.ValidationGroups;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Warehouse create request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Warehouse create request")
public class WarehouseCreateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Warehouse name cannot be empty", groups = ValidationGroups.Create.class)
    @Schema(description = "Warehouse name", example = "Beijing Main Warehouse")
    private String warehouseName;
    
    @NotBlank(message = "Warehouse code cannot be empty", groups = ValidationGroups.Create.class)
    @Schema(description = "Warehouse code", example = "WH001")
    private String warehouseCode;
    
    @Schema(description = "Warehouse type: 1-Main, 2-Branch, 3-Transit", example = "1")
    private Integer warehouseType;
    
    @Schema(description = "Contact person", example = "John Doe")
    private String contactPerson;
    
    @Schema(description = "Contact phone", example = "13800138000")
    private String contactPhone;
    
    @NotBlank(message = "Province cannot be empty", groups = ValidationGroups.Create.class)
    @Schema(description = "Province", example = "Beijing")
    private String province;
    
    @NotBlank(message = "City cannot be empty", groups = ValidationGroups.Create.class)
    @Schema(description = "City", example = "Beijing")
    private String city;
    
    @Schema(description = "District", example = "Chaoyang")
    private String district;
    
    @NotBlank(message = "Address cannot be empty", groups = ValidationGroups.Create.class)
    @Schema(description = "Detailed address", example = "No.1 Warehouse Road")
    private String address;
    
    @Schema(description = "Longitude", example = "116.404")
    private BigDecimal longitude;
    
    @Schema(description = "Latitude", example = "39.915")
    private BigDecimal latitude;
    
    @Schema(description = "Status: 1-Active, 0-Inactive", example = "1")
    private Integer status = 1;
    
    @Schema(description = "Remark", example = "Main distribution center")
    private String remark;
}
