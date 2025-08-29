package com.eco.starter.filter;

import cn.hutool.core.util.StrUtil;
import com.eco.starter.config.EcoProperties;
import com.eco.starter.wrapper.XssHttpServletRequestWrapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * XSS过滤器
 */
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "eco.xss", name = "enabled", havingValue = "true", matchIfMissing = true)
public class XssFilter implements Filter {

    private final EcoProperties ecoProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestUri = httpRequest.getRequestURI();

        // 检查是否排除
        List<String> excludeUrls = ecoProperties.getXss().getExcludeUrls();
        if (excludeUrls != null && !excludeUrls.isEmpty()) {
            for (String excludeUrl : excludeUrls) {
                if (StrUtil.contains(requestUri, excludeUrl)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        // XSS过滤
        chain.doFilter(new XssHttpServletRequestWrapper(httpRequest), response);
    }
}
