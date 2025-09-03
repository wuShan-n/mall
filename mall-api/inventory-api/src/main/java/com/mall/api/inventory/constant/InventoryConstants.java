package com.mall.api.inventory.constant;

/**
 * Inventory service constants
 */
public interface InventoryConstants {
    
    /**
     * Service name for Dubbo/Feign
     */
    String SERVICE_NAME = "inventory-service";
    
    /**
     * API version
     */
    String API_VERSION = "1.0.0";
    
    /**
     * API path prefix
     */
    String API_PREFIX = "/api/inventory";
    
    /**
     * Stock operation type constants
     */
    interface OperationType {
        Integer ADD = 1;       // Add stock
        Integer DEDUCT = 2;    // Deduct stock  
        Integer LOCK = 3;      // Lock stock
        Integer UNLOCK = 4;    // Unlock stock
    }
    
    /**
     * Stock record source constants
     */
    interface RecordSource {
        String PURCHASE = "PURCHASE";           // Purchase order
        String SALE = "SALE";                   // Sales order
        String RETURN = "RETURN";               // Return order
        String ADJUST = "ADJUST";               // Manual adjustment
        String TRANSFER = "TRANSFER";           // Transfer between warehouses
        String DAMAGE = "DAMAGE";               // Damage/Loss
        String INVENTORY_CHECK = "INVENTORY";   // Inventory check
    }
    
    /**
     * Warehouse status constants
     */
    interface WarehouseStatus {
        Integer ACTIVE = 1;
        Integer INACTIVE = 0;
    }
}