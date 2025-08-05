package com.example.ragservice.security.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求DTO
 */
@Data
public class LoginRequest {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
    
    /**
     * 记住我功能，默认为false
     */
    private boolean rememberMe = false;
} 