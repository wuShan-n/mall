package com.mall.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
/**
 * 收货信息
 */
@Data
@Builder
@Schema(description = "收货信息")
public class ReceiverInfo {
    @Schema(description = "收货人")
    private String name;
    
    @Schema(description = "手机号")
    private String phone;
    
    @Schema(description = "收货地址")
    private String address;
}