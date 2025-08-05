package com.example.ragservice.security.config;

import com.example.ragservice.security.model.Permission;
import com.example.ragservice.security.model.Role;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 角色配置类
 * 定义系统中各角色拥有的权限
 */
@Configuration
public class RoleConfig {
    
    /**
     * 角色权限映射表
     * 定义每个角色拥有的权限集合
     */
    private final Map<Role, Set<String>> rolePermissions = new HashMap<>();
    
    /**
     * 构造函数
     * 初始化角色权限映射
     */
    public RoleConfig() {
        // 普通用户权限
        Set<String> userPermissions = new HashSet<>(Arrays.asList(
            Permission.DOC_READ,
            Permission.DOC_SEARCH
        ));
        
        // 管理员权限
        Set<String> adminPermissions = new HashSet<>(Arrays.asList(
            Permission.DOC_READ,
            Permission.DOC_WRITE,
            Permission.DOC_SEARCH,
            Permission.DOC_DELETE,
            Permission.USER_READ,
            Permission.USER_WRITE
        ));
        
        // 超级管理员权限
        Set<String> superAdminPermissions = new HashSet<>(Arrays.asList(
            Permission.DOC_READ,
            Permission.DOC_WRITE,
            Permission.DOC_SEARCH,
            Permission.DOC_DELETE,
            Permission.USER_READ,
            Permission.USER_WRITE,
            Permission.USER_DELETE,
            Permission.SYSTEM_CONFIG
        ));
        
        // 配置角色权限映射
        rolePermissions.put(Role.ROLE_USER, Collections.unmodifiableSet(userPermissions));
        rolePermissions.put(Role.ROLE_ADMIN, Collections.unmodifiableSet(adminPermissions));
        rolePermissions.put(Role.ROLE_SUPER_ADMIN, Collections.unmodifiableSet(superAdminPermissions));
    }
    
    /**
     * 获取角色的权限集合
     * 
     * @param role 角色
     * @return 该角色拥有的权限集合
     */
    public Set<String> getPermissions(Role role) {
        return rolePermissions.getOrDefault(role, Collections.emptySet());
    }
    
    /**
     * 检查角色是否拥有指定权限
     * 
     * @param role 角色
     * @param permission 权限
     * @return 如果角色拥有该权限则返回true，否则返回false
     */
    public boolean hasPermission(Role role, String permission) {
        return rolePermissions.getOrDefault(role, Collections.emptySet()).contains(permission);
    }
} 