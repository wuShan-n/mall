package com.mall.gateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sa-Token 配置类
 */
@Configuration
public class SaTokenConfig {

    /**
     * 注册 Sa-Token 全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 开放地址
                .addExclude("/favicon.ico")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    // 登录认证：除白名单外，所有路由都需要登录
                    SaRouter.match("/**")
                            .notMatch(
                                    "/auth-service/login",
                                    "/auth-service/register",
                                    "/auth-service/captcha",
                                    "/user-service/login",
                                    "/user-service/register",
                                    "/user-service/send-sms-code",
                                    "/*/doc.html",
                                    "/*/swagger-ui.html",
                                    "/*/swagger-resources/**",
                                    "/*/v3/api-docs/**",
                                    "/*/webjars/**",
                                    "/actuator/**"
                            )
                            .check(r -> StpUtil.checkLogin());

                    // 角色认证：ADMIN角色才能访问admin路径
                    SaRouter.match("/admin/**", r -> StpUtil.checkRole("ADMIN"));

                    // 权限认证：不同接口需要不同权限
                    SaRouter.match("/api/user/delete/**", r -> StpUtil.checkPermission("user:delete"));
                    SaRouter.match("/api/user/update/**", r -> StpUtil.checkPermission("user:update"));
                    SaRouter.match("/api/order/delete/**", r -> StpUtil.checkPermission("order:delete"));
                    SaRouter.match("/api/product/add/**", r -> StpUtil.checkPermission("product:add"));
                })
                // 异常处理方法
                .setError(e -> {
                    return SaResult.error(e.getMessage());
                })
                // 前置函数：在每次认证函数之前执行
                .setBeforeAuth(obj -> {
                    // 设置跨域响应头
                    SaHolder.getResponse()
                            .setHeader("Access-Control-Allow-Origin", "*")
                            .setHeader("Access-Control-Allow-Methods", "*")
                            .setHeader("Access-Control-Allow-Headers", "*")
                            .setHeader("Access-Control-Max-Age", "3600");

                    // 如果是预检请求，直接返回
                    if (SaHolder.getRequest().getMethod().equals(SaHttpMethod.OPTIONS.name())) {
                        SaRouter.stop();
                    }
                });
    }

}