package com.example.ragservice.security.model;

/**
 * 权限常量类
 * 定义系统中的权限标识符
 */
public final class Permission {
    
    // 文档权限
    public static final String DOC_READ = "doc:read";
    public static final String DOC_WRITE = "doc:write";
    public static final String DOC_DELETE = "doc:delete";
    public static final String DOC_SEARCH = "doc:search";
    
    // 用户权限
    public static final String USER_READ = "user:read";
    public static final String USER_WRITE = "user:write";
    public static final String USER_DELETE = "user:delete";
    
    // 系统权限
    public static final String SYSTEM_CONFIG = "system:config";
    
    // 防止实例化
    private Permission() {
        throw new AssertionError("不能实例化Permission类");
    }
} 