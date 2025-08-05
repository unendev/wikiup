package com.example.ragservice.security.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试环境的测试控制器
 * 专门用于测试环境，移除了所有的安全注解
 */
@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
@Profile("test")
public class TestController {
    
    /**
     * 公共接口，所有人都可以访问
     */
    @GetMapping("/all")
    public String allAccess() {
        return "公共内容";
    }
    
    /**
     * 用户接口，无需权限验证
     */
    @GetMapping("/user")
    public String userAccess() {
        return "用户内容";
    }
    
    /**
     * 管理员接口，无需权限验证
     */
    @GetMapping("/admin")
    public String adminAccess() {
        return "管理员内容";
    }
    
    /**
     * 超级管理员接口，无需权限验证
     */
    @GetMapping("/super")
    public String superAdminAccess() {
        return "超级管理员内容";
    }
    
    /**
     * 基于权限的接口，无需权限验证
     */
    @GetMapping("/docs/read")
    public String readDocsAccess() {
        return "文档读取内容";
    }
    
    /**
     * 基于权限的接口，无需权限验证
     */
    @GetMapping("/docs/write")
    public String writeDocsAccess() {
        return "文档写入内容";
    }
    
    /**
     * 基于权限的接口，无需权限验证
     */
    @GetMapping("/users/delete")
    public String deleteUserAccess() {
        return "用户删除内容";
    }
    
    /**
     * 基于复合权限的接口，无需权限验证
     */
    @GetMapping("/compound")
    public String compoundPermissionAccess() {
        return "复合权限内容";
    }
} 