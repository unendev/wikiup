package com.example.ragservice.security.controller;

import com.example.ragservice.security.model.Permission;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 * 用于测试不同角色的访问权限
 */
@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
@Profile("!test")
public class TestController {
    
    /**
     * 公共接口，所有人都可以访问
     */
    @GetMapping("/all")
    public String allAccess() {
        return "公共内容";
    }
    
    /**
     * 用户接口，需要USER角色
     */
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public String userAccess() {
        return "用户内容";
    }
    
    /**
     * 管理员接口，需要ADMIN角色
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public String adminAccess() {
        return "管理员内容";
    }
    
    /**
     * 超级管理员接口，需要SUPER_ADMIN角色
     */
    @GetMapping("/super")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String superAdminAccess() {
        return "超级管理员内容";
    }
    
    /**
     * 基于权限的接口，需要doc:read权限
     */
    @GetMapping("/docs/read")
    @PreAuthorize("@permissionEvaluator.hasPermission(authentication, '" + Permission.DOC_READ + "')")
    public String readDocsAccess() {
        return "文档读取内容";
    }
    
    /**
     * 基于权限的接口，需要doc:write权限
     */
    @GetMapping("/docs/write")
    @PreAuthorize("@permissionEvaluator.hasPermission(authentication, '" + Permission.DOC_WRITE + "')")
    public String writeDocsAccess() {
        return "文档写入内容";
    }
    
    /**
     * 基于权限的接口，需要user:delete权限
     */
    @GetMapping("/users/delete")
    @PreAuthorize("@permissionEvaluator.hasPermission(authentication, '" + Permission.USER_DELETE + "')")
    public String deleteUserAccess() {
        return "用户删除内容";
    }
    
    /**
     * 基于复合权限的接口，需要同时拥有多个权限
     */
    @GetMapping("/compound")
    @PreAuthorize("@permissionEvaluator.hasPermission(authentication, '" + Permission.DOC_WRITE + "') and " +
                 "@permissionEvaluator.hasPermission(authentication, '" + Permission.USER_READ + "')")
    public String compoundPermissionAccess() {
        return "复合权限内容";
    }
} 