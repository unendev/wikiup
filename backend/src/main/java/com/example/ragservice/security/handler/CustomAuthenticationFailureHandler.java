package com.example.ragservice.security.handler;

import com.example.ragservice.security.service.LoginAttemptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义认证失败处理器
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    // 锁定时间常量（分钟）
    private static final int BLOCK_DURATION_MINUTES = 30;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, 
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        // 设置响应状态为401（未授权）
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        // 获取用户名
        String username = request.getParameter("username");
        
        // 记录登录失败
        if (username != null && !username.isEmpty()) {
            loginAttemptService.loginFailed(username);
        }
        
        // 构建错误消息
        String errorMessage;
        
        if (exception instanceof BadCredentialsException) {
            if (username != null && !username.isEmpty()) {
                int remainingAttempts = loginAttemptService.getRemainingAttempts(username);
                if (remainingAttempts > 0) {
                    errorMessage = "用户名或密码不正确，还剩" + remainingAttempts + "次尝试机会";
                } else {
                    errorMessage = "登录失败次数过多，账户已被锁定" + BLOCK_DURATION_MINUTES + "分钟";
                }
            } else {
                errorMessage = "用户名或密码不正确";
            }
        } else if (exception instanceof DisabledException) {
            errorMessage = "账号已禁用";
        } else if (exception instanceof LockedException) {
            errorMessage = "账号已锁定";
        } else {
            errorMessage = "认证失败: " + exception.getMessage();
        }
        
        // 构建响应
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", false);
        responseMap.put("message", errorMessage);
        
        // 写入响应
        response.getWriter().write(objectMapper.writeValueAsString(responseMap));
    }
} 