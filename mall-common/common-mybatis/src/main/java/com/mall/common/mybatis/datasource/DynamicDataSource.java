package com.mall.common.mybatis.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Dynamic DataSource for read/write separation
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();
    
    private final AtomicInteger counter = new AtomicInteger(0);
    
    private List<String> slaveDataSourceKeys;
    
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = getDataSourceType();
        
        if (dataSourceType == DataSourceType.SLAVE && slaveDataSourceKeys != null && !slaveDataSourceKeys.isEmpty()) {
            // Round-robin for slave data sources
            int index = counter.getAndIncrement() % slaveDataSourceKeys.size();
            String slaveKey = slaveDataSourceKeys.get(index);
            log.debug("Using slave data source: {}", slaveKey);
            return slaveKey;
        }
        
        log.debug("Using master data source");
        return DataSourceType.MASTER.name();
    }
    
    /**
     * Set data source type
     */
    public static void setDataSourceType(DataSourceType dataSourceType) {
        contextHolder.set(dataSourceType);
    }
    
    /**
     * Get data source type
     */
    public static DataSourceType getDataSourceType() {
        DataSourceType type = contextHolder.get();
        return type == null ? DataSourceType.MASTER : type;
    }
    
    /**
     * Clear data source type
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
    
    /**
     * Set slave data source keys
     */
    public void setSlaveDataSourceKeys(List<String> slaveDataSourceKeys) {
        this.slaveDataSourceKeys = slaveDataSourceKeys;
    }
    
    /**
     * Data source type enum
     */
    public enum DataSourceType {
        MASTER, SLAVE
    }
}