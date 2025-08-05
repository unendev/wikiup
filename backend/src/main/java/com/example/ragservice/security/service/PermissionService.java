package com.example.ragservice.security.service;

import com.example.ragservice.security.model.Resource;
import com.example.ragservice.security.model.User;

/**
 * 权限服务接口
 * 提供权限检查相关的功能
 */
public interface PermissionService {
    
    /**
     * 检查用户是否拥有权限访问资源
     * 
     * @param user 用户
     * @param resource 资源
     * @param action 操作（如"read", "write", "delete"等）
     * @return 如果用户拥有权限则返回true，否则返回false
     */
    boolean hasPermission(User user, Resource resource, String action);
    
    /**
     * 检查用户是否拥有指定权限
     * 
     * @param user 用户
     * @param permission 权限
     * @return 如果用户拥有权限则返回true，否则返回false
     */
    boolean hasPermission(User user, String permission);
    
    /**
     * 检查用户是否是资源的拥有者
     * 
     * @param user 用户
     * @param resource 资源
     * @return 如果用户是资源的拥有者则返回true，否则返回false
     */
    boolean isOwner(User user, Resource resource);
} 