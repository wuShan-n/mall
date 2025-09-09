package com.mall.user.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPointRecord {
    @Schema(description="主键")
    private Long id;
    @Schema(description="用户ID")
    private Long userId;
    @Schema(description="积分变化值")
    private Integer points;
    @Schema(description="变化后余额")
    private Integer balance;
    @Schema(description="类型:1-获得,2-消费")
    private Integer type;
    @Schema(description="来源")
    private String source;
    @Schema(description="来源ID")
    private String sourceId;
    @Schema(description="备注")
    private String remark;
    @Schema(description="创建时间")
    private LocalDateTime createTime;
}
