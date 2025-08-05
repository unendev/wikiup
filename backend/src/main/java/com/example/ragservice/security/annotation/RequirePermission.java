package com.example.ragservice.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 * 用于简化基于权限的访问控制
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("@permissionEvaluator.hasPermission(authentication, #permission)")
public @interface RequirePermission {
    
    /**
     * 权限值
     * 例如: "doc:read", "user:write"等
     */
    String permission();
} 