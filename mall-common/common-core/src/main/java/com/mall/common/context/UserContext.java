package com.mall.common.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * User context information
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContext implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * User ID
     */
    private Long userId;
    
    /**
     * Username
     */
    private String username;
    
    /**
     * Nickname
     */
    private String nickname;
    
    /**
     * Tenant ID (for multi-tenant systems)
     */
    private Long tenantId;
    
    /**
     * Roles
     */
    private String roles;
    
    /**
     * Token
     */
    private String token;
    
    /**
     * Client IP
     */
    private String clientIp;
    
    /**
     * Additional attributes
     */
    private Map<String, Object> attributes;
}