package com.mall.api.user.dto.request;

import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Address create request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Address create request")
public class AddressCreateRequest extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "Receiver name cannot be empty")
    @Schema(description = "Receiver name", example = "John Doe")
    private String receiverName;
    
    @NotBlank(message = "Receiver phone cannot be empty")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "Invalid phone number format")
    @Schema(description = "Receiver phone", example = "13800138000")
    private String receiverPhone;
    
    @NotBlank(message = "Province code cannot be empty")
    @Schema(description = "Province code", example = "110000")
    private String provinceCode;
    
    @NotBlank(message = "Province cannot be empty")
    @Schema(description = "Province", example = "Beijing")
    private String province;
    
    @NotBlank(message = "City code cannot be empty")
    @Schema(description = "City code", example = "110100")
    private String cityCode;
    
    @NotBlank(message = "City cannot be empty")
    @Schema(description = "City", example = "Beijing")
    private String city;
    
    @NotBlank(message = "District code cannot be empty")
    @Schema(description = "District code", example = "110101")
    private String districtCode;
    
    @NotBlank(message = "District cannot be empty")
    @Schema(description = "District", example = "Dongcheng")
    private String district;
    
    @NotBlank(message = "Detail address cannot be empty")
    @Schema(description = "Detail address", example = "No.1 Street")
    private String detailAddress;
    
    @Schema(description = "Postal code", example = "100000")
    private String postalCode;
    
    @Schema(description = "Address tag", example = "Home")
    private String addressTag;
    
    @Schema(description = "Is default address", example = "true")
    private Boolean isDefault = false;
}
