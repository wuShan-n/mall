package com.mall.gateway.utils;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Sa-Token 权限认证接口实现
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        try {
            String key = "mall:user:permission:" + loginId;
            String permissionJson = redisTemplate.opsForValue().get(key).block(Duration.ofSeconds(1));
            
            if (permissionJson != null) {
                return JSON.parseArray(permissionJson, String.class);
            }
            
            // 如果Redis中没有，可以通过RPC调用用户服务获取
            // 这里简化处理，返回默认权限
            return getDefaultPermissions(loginId);
        } catch (Exception e) {
            log.error("Failed to get permissions for user: {}", loginId, e);
            return new ArrayList<>();
        }
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        try {
            String key = "mall:user:role:" + loginId;
            String roleJson = redisTemplate.opsForValue().get(key).block(Duration.ofSeconds(1));
            
            if (roleJson != null) {
                return JSON.parseArray(roleJson, String.class);
            }
            
            // 如果Redis中没有，可以通过RPC调用用户服务获取
            // 这里简化处理，返回默认角色
            return getDefaultRoles(loginId);
        } catch (Exception e) {
            log.error("Failed to get roles for user: {}", loginId, e);
            return new ArrayList<>();
        }
    }

    /**
     * 获取默认权限（实际应该调用用户服务）
     */
    private List<String> getDefaultPermissions(Object loginId) {
        List<String> permissions = new ArrayList<>();
        
        // 基础用户权限
        permissions.add("user:view");
        permissions.add("user:update:self");
        permissions.add("order:view:self");
        permissions.add("order:create");
        permissions.add("product:view");
        
        // 管理员额外权限
        if ("1".equals(loginId.toString())) {
            permissions.add("user:update");
            permissions.add("user:delete");
            permissions.add("order:view");
            permissions.add("order:delete");
            permissions.add("product:add");
            permissions.add("product:update");
            permissions.add("product:delete");
        }
        
        return permissions;
    }

    /**
     * 获取默认角色（实际应该调用用户服务）
     */
    private List<String> getDefaultRoles(Object loginId) {
        List<String> roles = new ArrayList<>();
        
        // 基础用户角色
        roles.add("USER");
        
        // 管理员角色
        if ("1".equals(loginId.toString())) {
            roles.add("ADMIN");
        }
        
        return roles;
    }
}