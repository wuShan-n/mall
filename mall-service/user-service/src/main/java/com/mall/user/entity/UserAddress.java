package com.mall.user.entity;


import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserAddress {
    @Schema(description="主键")
    private Long id;
    @Schema(description="用户ID")
    private Long userId;
    @Schema(description="收货人姓名")
    private String receiverName;
    @Schema(description="收货人电话")
    private String receiverPhone;
    @Schema(description="省份编码")
    private String provinceCode;
    @Schema(description="省份")
    private String province;
    @Schema(description="城市编码")
    private String cityCode;
    @Schema(description="城市")
    private String city;
    @Schema(description="区县编码")
    private String districtCode;
    @Schema(description="区县")
    private String district;
    @Schema(description="详细地址")
    private String detailAddress;
    @Schema(description="邮政编码")
    private String postalCode;
    @Schema(description="地址标签")
    private String addressTag;
    @Schema(description="是否默认")
    private Integer isDefault;
    @Schema(description="删除标记")
    private Integer deleted;
    @Schema(description="创建时间")
    private LocalDateTime createTime;
    @Schema(description="更新时间")
    private LocalDateTime updateTime;
}
