package com.example.ragservice.security.controller;

import com.example.ragservice.security.dto.JwtResponse;
import com.example.ragservice.security.dto.LoginRequest;
import com.example.ragservice.security.dto.MessageResponse;
import com.example.ragservice.security.dto.SignupRequest;
import com.example.ragservice.security.JwtTokenUtil;
import com.example.ragservice.security.model.Role;
import com.example.ragservice.security.model.User;
import com.example.ragservice.security.service.UserDetailsImpl;
import com.example.ragservice.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证控制器
 * 处理用户登录和注册
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 用户登录
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // 认证用户
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        // 设置认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // 生成JWT令牌
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtTokenUtil.generateToken(userDetails, loginRequest.isRememberMe());

        // 获取用户角色
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // 返回JWT响应
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /**
     * 用户注册
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // 检查用户名是否已存在
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("错误：用户名已被使用!"));
        }

        // 检查邮箱是否已存在
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("错误：邮箱已被使用!"));
        }

        // 验证密码强度
        if (signUpRequest.getPassword().length() < 6) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("错误：密码长度必须至少为6个字符!"));
        }

        // 创建新用户
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .enabled(true)
                .build();

        // 设置用户角色
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            // 默认角色为用户
            roles.add(Role.ROLE_USER);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        roles.add(Role.ROLE_ADMIN);
                        break;
                    case "super_admin":
                        roles.add(Role.ROLE_SUPER_ADMIN);
                        break;
                    default:
                        roles.add(Role.ROLE_USER);
                }
            });
        }

        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("用户注册成功!"));
    }
    
    /**
     * 用户登出
     */
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        // 清除认证上下文
        SecurityContextHolder.clearContext();
        
        // 返回成功消息
        return ResponseEntity.ok(new MessageResponse("用户已成功登出!"));
    }
    
    /**
     * 获取当前用户信息
     */
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("未登录!"));
        }
        
        // 获取用户角色
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        
        // 构建用户信息响应
        JwtResponse userInfo = new JwtResponse(
                null,  // 不返回token
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
        
        return ResponseEntity.ok(userInfo);
    }
    
    /**
     * 刷新令牌
     */
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("未登录!"));
        }
        
        // 生成新的JWT令牌
        String jwt = jwtTokenUtil.generateToken(userDetails);
        
        // 获取用户角色
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        
        // 返回JWT响应
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    
    /**
     * 修改密码
     */
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        
        if (userDetails == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("未登录!"));
        }
        
        // 验证旧密码
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), oldPassword));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("旧密码不正确!"));
        }
        
        // 验证新密码强度
        if (newPassword.length() < 6) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("错误：新密码长度必须至少为6个字符!"));
        }
        
        // 更新密码
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("未找到用户!"));
        
        user.setPassword(passwordEncoder.encode(newPassword));
        userService.save(user);
        
        return ResponseEntity.ok(new MessageResponse("密码修改成功!"));
    }
} 