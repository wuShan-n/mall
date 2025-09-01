package com.mall.api.user.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mall.common.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Address view object
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Address information")
public class AddressVO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "Address ID", example = "1")
    private Long id;
    
    @Schema(description = "User ID", example = "1")
    private Long userId;
    
    @Schema(description = "Receiver name", example = "John Doe")
    private String receiverName;
    
    @Schema(description = "Receiver phone", example = "13800138000")
    private String receiverPhone;
    
    @Schema(description = "Province code", example = "110000")
    private String provinceCode;
    
    @Schema(description = "Province", example = "Beijing")
    private String province;
    
    @Schema(description = "City code", example = "110100")
    private String cityCode;
    
    @Schema(description = "City", example = "Beijing")
    private String city;
    
    @Schema(description = "District code", example = "110101")
    private String districtCode;
    
    @Schema(description = "District", example = "Dongcheng")
    private String district;
    
    @Schema(description = "Detail address", example = "No.1 Street")
    private String detailAddress;
    
    @Schema(description = "Postal code", example = "100000")
    private String postalCode;
    
    @Schema(description = "Address tag", example = "Home")
    private String addressTag;
    
    @Schema(description = "Is default address", example = "true")
    private Boolean isDefault;
    
    @Schema(description = "Full address", example = "Beijing Beijing Dongcheng No.1 Street")
    private String fullAddress;
    
    @Schema(description = "Create time", example = "2024-01-01 00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
