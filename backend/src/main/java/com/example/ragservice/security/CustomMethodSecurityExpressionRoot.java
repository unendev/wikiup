package com.example.ragservice.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * 自定义方法安全表达式操作类
 * 扩展Spring Security的安全表达式，添加自定义权限检查
 */
public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private PermissionEvaluator permissionEvaluator;

    /**
     * 构造函数
     * 
     * @param authentication 认证信息
     */
    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    /**
     * 设置权限评估器
     * 
     * @param permissionEvaluator 权限评估器
     */
    public void setPermissionEvaluator(PermissionEvaluator permissionEvaluator) {
        this.permissionEvaluator = permissionEvaluator;
    }

    /**
     * 检查当前用户是否拥有指定权限
     * 
     * @param permission 权限
     * @return 如果用户拥有该权限则返回true，否则返回false
     */
    public boolean hasPermission(String permission) {
        return permissionEvaluator.hasPermission(this.getAuthentication(), permission);
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
} 