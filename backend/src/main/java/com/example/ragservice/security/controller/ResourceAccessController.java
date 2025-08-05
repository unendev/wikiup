package com.example.ragservice.security.controller;

import com.example.ragservice.security.model.Resource;
import com.example.ragservice.security.model.ResourceType;
import com.example.ragservice.security.model.User;
import com.example.ragservice.security.service.PermissionService;
import com.example.ragservice.security.service.ResourceService;
import com.example.ragservice.security.service.UserDetailsImpl;
import com.example.ragservice.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 资源访问控制器
 * 演示基于资源的访问控制
 */
@RestController
@RequestMapping("/api/resources")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResourceAccessController {

    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private PermissionService permissionService;
    
    @Autowired
    private UserService userService;
    
    /**
     * 获取所有资源
     */
    @GetMapping
    public ResponseEntity<List<Resource>> getAllResources(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 获取当前用户
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new AccessDeniedException("用户不存在"));
        
        // 检查用户是否拥有读取资源的权限
        if (!permissionService.hasPermission(user, "user:read")) {
            throw new AccessDeniedException("没有权限访问资源");
        }
        
        // 获取所有文档资源
        List<Resource> resources = resourceService.findByType(ResourceType.DOCUMENT);
        return ResponseEntity.ok(resources);
    }
    
    /**
     * 获取资源详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        
        // 获取当前用户
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new AccessDeniedException("用户不存在"));
        
        // 获取资源
        Resource resource = resourceService.findById(id)
                .orElseThrow(() -> new AccessDeniedException("资源不存在"));
        
        // 检查用户是否拥有读取资源的权限
        if (!permissionService.hasPermission(user, resource, "read")) {
            throw new AccessDeniedException("没有权限访问该资源");
        }
        
        return ResponseEntity.ok(resource);
    }
    
    /**
     * 创建资源
     */
    @PostMapping
    public ResponseEntity<Resource> createResource(
            @RequestBody Resource resource,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        
        // 获取当前用户
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new AccessDeniedException("用户不存在"));
        
        // 检查用户是否拥有创建资源的权限
        if (!permissionService.hasPermission(user, "doc:write")) {
            throw new AccessDeniedException("没有权限创建资源");
        }
        
        // 设置资源拥有者
        resource.setOwnerId(user.getId());
        
        // 保存资源
        Resource savedResource = resourceService.save(resource);
        return ResponseEntity.ok(savedResource);
    }
    
    /**
     * 更新资源
     */
    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(
            @PathVariable Long id,
            @RequestBody Resource resource,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        
        // 获取当前用户
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new AccessDeniedException("用户不存在"));
        
        // 获取资源
        Resource existingResource = resourceService.findById(id)
                .orElseThrow(() -> new AccessDeniedException("资源不存在"));
        
        // 检查用户是否拥有更新资源的权限
        if (!permissionService.hasPermission(user, existingResource, "write")) {
            throw new AccessDeniedException("没有权限更新该资源");
        }
        
        // 更新资源
        resource.setId(id);
        resource.setOwnerId(existingResource.getOwnerId());
        Resource updatedResource = resourceService.save(resource);
        return ResponseEntity.ok(updatedResource);
    }
    
    /**
     * 删除资源
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        
        // 获取当前用户
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new AccessDeniedException("用户不存在"));
        
        // 获取资源
        Resource resource = resourceService.findById(id)
                .orElseThrow(() -> new AccessDeniedException("资源不存在"));
        
        // 检查用户是否拥有删除资源的权限
        if (!permissionService.hasPermission(user, resource, "delete")) {
            throw new AccessDeniedException("没有权限删除该资源");
        }
        
        // 删除资源
        resourceService.delete(id);
        return ResponseEntity.ok().build();
    }
} 