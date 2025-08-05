package com.example.ragservice.security.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 登录尝试服务
 * 用于限制登录失败次数，防止暴力破解
 */
@Service
public class LoginAttemptService {

    // 最大尝试次数
    private static final int MAX_ATTEMPT = 5;
    
    // 阻止时间（分钟）
    public static final int BLOCK_DURATION_MINUTES = 30;
    
    // 缓存，用于存储用户登录失败次数
    private LoadingCache<String, Integer> attemptsCache;
    
    /**
     * 构造函数
     * 初始化缓存
     */
    public LoginAttemptService() {
        attemptsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(BLOCK_DURATION_MINUTES, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }
    
    /**
     * 记录登录失败
     * 
     * @param username 用户名
     */
    public void loginFailed(String username) {
        int attempts;
        try {
            attempts = attemptsCache.get(username);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attemptsCache.put(username, ++attempts);
    }
    
    /**
     * 登录成功后重置尝试次数
     * 
     * @param username 用户名
     */
    public void loginSucceeded(String username) {
        attemptsCache.invalidate(username);
    }
    
    /**
     * 检查用户是否被阻止登录
     * 
     * @param username 用户名
     * @return 如果用户被阻止登录则返回true，否则返回false
     */
    public boolean isBlocked(String username) {
        try {
            return attemptsCache.get(username) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
    
    /**
     * 获取用户剩余尝试次数
     * 
     * @param username 用户名
     * @return 剩余尝试次数
     */
    public int getRemainingAttempts(String username) {
        try {
            return Math.max(MAX_ATTEMPT - attemptsCache.get(username), 0);
        } catch (ExecutionException e) {
            return MAX_ATTEMPT;
        }
    }
} 