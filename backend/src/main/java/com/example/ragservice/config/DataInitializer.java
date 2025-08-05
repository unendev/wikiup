package com.example.ragservice.config;

import com.example.ragservice.security.model.Role;
import com.example.ragservice.security.model.User;
import com.example.ragservice.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

/**
 * 数据初始化器
 * 用于在应用启动时初始化一些测试数据
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 初始化测试用户
        createTestUsers();
    }

    /**
     * 创建测试用户
     */
    private void createTestUsers() {
        // 创建普通用户
        if (!userService.existsByUsername("testuser")) {
            User user = User.builder()
                    .username("testuser")
                    .password(passwordEncoder.encode("password123"))
                    .email("test@example.com")
                    .enabled(true)
                    .roles(new HashSet<>(Collections.singletonList(Role.ROLE_USER)))
                    .build();
            userService.save(user);
        }

        // 创建管理员用户
        if (!userService.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .email("admin@example.com")
                    .enabled(true)
                    .roles(new HashSet<>(Collections.singletonList(Role.ROLE_ADMIN)))
                    .build();
            userService.save(admin);
        }

        // 创建超级管理员用户
        if (!userService.existsByUsername("superadmin")) {
            User superAdmin = User.builder()
                    .username("superadmin")
                    .password(passwordEncoder.encode("super123"))
                    .email("super@example.com")
                    .enabled(true)
                    .roles(new HashSet<>(Collections.singletonList(Role.ROLE_SUPER_ADMIN)))
                    .build();
            userService.save(superAdmin);
        }
    }
} 