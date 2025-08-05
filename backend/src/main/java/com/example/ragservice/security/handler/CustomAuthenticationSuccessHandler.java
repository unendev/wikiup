package com.example.ragservice.security.handler;

import com.example.ragservice.security.JwtTokenUtil;
import com.example.ragservice.security.service.LoginAttemptService;
import com.example.ragservice.security.service.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义认证成功处理器
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, 
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        // 获取用户详情
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        // 获取用户名
        String username = userDetails.getUsername();
        
        // 重置登录失败计数
        loginAttemptService.loginSucceeded(username);
        
        // 获取记住我参数
        boolean rememberMe = "true".equalsIgnoreCase(request.getParameter("remember-me"));
        
        // 生成JWT令牌
        String jwt = jwtTokenUtil.generateToken(userDetails, rememberMe);
        
        // 获取用户角色
        String roles = userDetails.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.joining(","));
        
        // 构建响应
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("token", jwt);
        responseMap.put("id", userDetails.getId());
        responseMap.put("username", userDetails.getUsername());
        responseMap.put("email", userDetails.getEmail());
        responseMap.put("roles", roles);
        responseMap.put("success", true);
        responseMap.put("message", "登录成功");
        
        // 设置响应
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(responseMap));
    }
} 