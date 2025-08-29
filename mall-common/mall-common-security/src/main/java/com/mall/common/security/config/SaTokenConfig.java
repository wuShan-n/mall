package com.mall.common.security.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaTokenConsts;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Sa-Token 配置类
 *
 * @author mall
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 拦截器，打开注解式鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 指定一条 match 规则
            SaRouter
                    .match("/**") // 拦截的路径
                    .notMatch("/user/login", "/user/register") // 排除掉的路径
                    .notMatch("/swagger-ui/**", "/v3/api-docs/**", "/doc.html", "/webjars/**") // 排除接口文档
                    .notMatch("/actuator/**") // 排除监控端点
                    .notMatch("/error") // 排除错误页面
                    .check(r -> StpUtil.checkLogin());        // 要执行的校验动作，可以写完整的校验流程
        })).addPathPatterns("/**");
    }

    /**
     * 重写 Sa-Token 框架内部算法策略
     */
    static {
        // 重写 Token 生成策略
        SaStrategy.instance.createToken = (loginId, loginType) -> {
            return SaTokenConsts.TOKEN_STYLE_RANDOM_32 + "_" + loginId + "_" + System.currentTimeMillis();
        };
    }
}
