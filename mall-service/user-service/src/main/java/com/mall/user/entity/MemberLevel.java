package com.mall.user.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberLevel {
    @Schema(description="主键")
    private Integer id;
    @Schema(description="等级名称")
    private String levelName;
    @Schema(description="最小成长值")
    private Integer minGrowth;
    @Schema(description="最大成长值")
    private Integer maxGrowth;
    @Schema(description="折扣率")
    private BigDecimal discount;
    @Schema(description="等级图标")
    private String icon;
    @Schema(description="等级说明")
    private String description;
    @Schema(description="特权说明")
    private Object privileges;
    @Schema(description="状态")
    private Integer status;
    @Schema(description="创建时间")
    private LocalDateTime createTime;
}
