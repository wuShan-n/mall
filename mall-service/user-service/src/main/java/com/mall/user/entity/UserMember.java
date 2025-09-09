package com.mall.user.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserMember {
    @Schema(description="主键")
    private Long id;
    @Schema(description="用户ID")
    private Long userId;
    @Schema(description="会员编号")
    private String memberNo;
    @Schema(description="会员等级ID")
    private Integer levelId;
    @Schema(description="积分")
    private Integer points;
    @Schema(description="成长值")
    private Integer growthValue;
    @Schema(description="余额")
    private BigDecimal balance;
    @Schema(description="累计消费金额")
    private BigDecimal totalAmount;
    @Schema(description="累计订单数")
    private Integer totalOrders;
    @Schema(description="会员过期时间")
    private LocalDateTime expireTime;
    @Schema(description="创建时间")
    private LocalDateTime createTime;
    @Schema(description="更新时间")
    private LocalDateTime updateTime;
}
