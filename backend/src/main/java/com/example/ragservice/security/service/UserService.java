package com.example.ragservice.security.service;

import com.example.ragservice.security.model.User;

import java.util.Optional;

/**
 * 用户服务接口
 */
public interface UserService {
    
    /**
     * 根据用户名查找用户
     * 
     * @param username 用户名
     * @return 用户对象（可能为空）
     */
    Optional<User> findByUsername(String username);
    
    /**
     * 检查用户名是否已存在
     * 
     * @param username 用户名
     * @return 如果用户名已存在则返回true，否则返回false
     */
    boolean existsByUsername(String username);
    
    /**
     * 检查邮箱是否已存在
     * 
     * @param email 邮箱
     * @return 如果邮箱已存在则返回true，否则返回false
     */
    boolean existsByEmail(String email);
    
    /**
     * 保存用户
     * 
     * @param user 用户对象
     * @return 保存后的用户对象
     */
    User save(User user);
} 