package com.mall.user.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class User {
    @Schema(description="主键")
    private Long id;
    @Schema(description="用户名")
    private String username;
    @Schema(description="密码(加密)")
    private String password;
    @Schema(description="手机号")
    private String phone;
    @Schema(description="邮箱")
    private String email;
    @Schema(description="昵称")
    private String nickname;
    @Schema(description="头像URL")
    private String avatar;
    @Schema(description="性别:0-未知,1-男,2-女")
    private Integer gender;
    @Schema(description="生日")
    private LocalDate birthday;
    @Schema(description="状态:0-禁用,1-正常")
    private Integer status;
    @Schema(description="注册来源:1-PC,2-APP,3-小程序")
    private Integer registerSource;
    @Schema(description="最后登录时间")
    private LocalDateTime lastLoginTime;
    @Schema(description="最后登录IP")
    private String lastLoginIp;
    @Schema(description="删除标记")
    private Integer deleted;
    @Schema(description="创建时间")
    private LocalDateTime createTime;
    @Schema(description="更新时间")
    private LocalDateTime updateTime;
}
