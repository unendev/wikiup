package com.example.ragservice.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * 注册请求DTO
 */
@Data
public class SignupRequest {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 20, message = "用户名长度必须在3到20之间")
    private String username;

    @NotBlank(message = "邮箱不能为空")
    @Size(max = 50, message = "邮箱长度不能超过50")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 40, message = "密码长度必须在6到40之间")
    private String password;
    
    private Set<String> roles;
} 