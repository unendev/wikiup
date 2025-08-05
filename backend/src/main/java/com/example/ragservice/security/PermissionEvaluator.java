package com.example.ragservice.security;

import com.example.ragservice.security.config.RoleConfig;
import com.example.ragservice.security.model.Role;
import com.example.ragservice.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * 自定义权限评估器
 * 用于评估用户是否拥有指定权限
 */
@Component
public class PermissionEvaluator {

    @Autowired
    private RoleConfig roleConfig;

    /**
     * 检查当前用户是否拥有指定权限
     * 
     * @param authentication 认证信息
     * @param permission 权限
     * @return 如果用户拥有该权限则返回true，否则返回false
     */
    public boolean hasPermission(Authentication authentication, String permission) {
        if (authentication == null || permission == null || permission.isEmpty()) {
            return false;
        }

        // 获取用户角色
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            try {
                Role role = Role.valueOf(authority.getAuthority());
                if (roleConfig.hasPermission(role, permission)) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                // 角色不匹配，忽略
            }
        }

        return false;
    }
} 