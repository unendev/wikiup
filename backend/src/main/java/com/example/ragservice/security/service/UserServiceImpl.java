package com.example.ragservice.security.service;

import com.example.ragservice.security.model.Role;
import com.example.ragservice.security.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 用户服务实现类（内存实现）
 * 注意：这是一个临时实现，后续应该替换为数据库实现
 */
@Service
public class UserServiceImpl implements UserService {
    
    // 使用ConcurrentHashMap存储用户数据
    private final Map<String, User> userMap = new ConcurrentHashMap<>();
    
    // 用于生成用户ID
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * 构造函数，初始化默认管理员用户
     */
    public UserServiceImpl() {
        // 创建一个默认的管理员用户
        User admin = User.builder()
                .id(idGenerator.getAndIncrement())
                .username("admin")
                .password("$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG") // 'password'
                .email("admin@example.com")
                .enabled(true)
                .roles(new HashSet<>(Collections.singletonList(Role.ROLE_ADMIN)))
                .build();
        
        userMap.put(admin.getUsername(), admin);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userMap.get(username));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userMap.containsKey(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMap.values().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        userMap.put(user.getUsername(), user);
        return user;
    }
} 