package com.example.ragservice.security.service;

import com.example.ragservice.security.config.RoleConfig;
import com.example.ragservice.security.model.Resource;
import com.example.ragservice.security.model.ResourceType;
import com.example.ragservice.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限服务实现类
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RoleConfig roleConfig;

    @Override
    public boolean hasPermission(User user, Resource resource, String action) {
        // 检查用户是否是资源的拥有者
        if (isOwner(user, resource)) {
            return true;
        }
        
        // 构造权限标识符
        String permission = getPermissionIdentifier(resource.getType(), action);
        
        // 检查用户是否拥有权限
        return hasPermission(user, permission);
    }

    @Override
    public boolean hasPermission(User user, String permission) {
        // 检查用户的每个角色
        return user.getRoles().stream()
                .anyMatch(role -> roleConfig.hasPermission(role, permission));
    }

    @Override
    public boolean isOwner(User user, Resource resource) {
        // 如果资源没有所有者，则没有人是所有者
        if (resource.getOwnerId() == null) {
            return false;
        }
        
        // 检查用户ID是否与资源所有者ID匹配
        return user.getId().equals(resource.getOwnerId());
    }
    
    /**
     * 根据资源类型和操作构造权限标识符
     * 
     * @param resourceType 资源类型
     * @param action 操作
     * @return 权限标识符
     */
    private String getPermissionIdentifier(ResourceType resourceType, String action) {
        String resourcePrefix;
        
        switch (resourceType) {
            case DOCUMENT:
                resourcePrefix = "doc";
                break;
            case USER:
                resourcePrefix = "user";
                break;
            case SYSTEM:
                resourcePrefix = "system";
                break;
            case API:
                resourcePrefix = "api";
                break;
            default:
                resourcePrefix = "unknown";
        }
        
        return resourcePrefix + ":" + action;
    }
} 