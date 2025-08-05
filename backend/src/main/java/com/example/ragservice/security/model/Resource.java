package com.example.ragservice.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源实体类
 * 代表系统中的资源（如文档、用户等）
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource {
    
    /**
     * 资源ID
     */
    private Long id;
    
    /**
     * 资源类型
     * 例如：DOCUMENT, USER, SYSTEM
     */
    private ResourceType type;
    
    /**
     * 资源名称
     */
    private String name;
    
    /**
     * 资源路径
     */
    private String path;
    
    /**
     * 资源描述
     */
    private String description;
    
    /**
     * 是否需要认证
     */
    private boolean requiresAuth;
    
    /**
     * 资源拥有者ID
     */
    private Long ownerId;
} 