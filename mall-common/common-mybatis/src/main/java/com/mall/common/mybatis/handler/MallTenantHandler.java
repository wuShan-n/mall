package com.mall.common.mybatis.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.mall.common.context.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Multi-tenant handler for MyBatis-Plus
 */
@Slf4j
@Component
public class MallTenantHandler implements TenantLineHandler {
    
    @Value("${mybatis-plus.tenant.enabled:false}")
    private boolean tenantEnabled;
    
    @Value("${mybatis-plus.tenant.column:tenant_id}")
    private String tenantColumn;
    
    @Value("${mybatis-plus.tenant.ignore-tables:}")
    private String ignoreTables;
    
    /**
     * Get tenant ID
     */
    @Override
    public Expression getTenantId() {
        Long tenantId = UserContextHolder.getContext() != null ? 
            UserContextHolder.getContext().getTenantId() : null;
        
        if (tenantId == null) {
            log.debug("No tenant ID found in context, using NullValue");
            return new NullValue();
        }
        
        log.debug("Using tenant ID: {}", tenantId);
        return new LongValue(tenantId);
    }
    
    /**
     * Get tenant column name
     */
    @Override
    public String getTenantIdColumn() {
        return tenantColumn;
    }
    
    /**
     * Ignore tenant for specific tables
     */
    @Override
    public boolean ignoreTable(String tableName) {
        if (!tenantEnabled) {
            return true;
        }
        
        // System tables that should ignore tenant
        List<String> systemTables = Arrays.asList(
            "sys_config",
            "sys_dict",
            "sys_log",
            "member_level"
        );
        
        if (systemTables.contains(tableName)) {
            return true;
        }
        
        // Custom ignore tables from configuration
        if (ignoreTables != null && !ignoreTables.isEmpty()) {
            List<String> ignoreList = Arrays.asList(ignoreTables.split(","));
            return ignoreList.contains(tableName);
        }
        
        return false;
    }
}
