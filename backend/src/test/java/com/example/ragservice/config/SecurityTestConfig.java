package com.example.ragservice.config;

import com.example.ragservice.security.JwtTokenUtil;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
// import com.example.ragservice.security.CustomPermissionEvaluator;

/**
 * 安全测试环境配置类
 * 提供测试环境需要的各种安全相关的Bean，用于单元测试和集成测试
 */
@Configuration
@Profile("test")
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityTestConfig { // removed extends GlobalMethodSecurityConfiguration

    /**
     * 为测试环境提供PasswordEncoder实例
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 为测试环境提供AuthenticationManager的Mock实现
     * 使用@Primary注解，确保在测试环境中优先使用此Mock Bean
     */
    @Bean
    @Primary
    public AuthenticationManager authenticationManager() throws Exception {
        return Mockito.mock(AuthenticationManager.class);
    }

    /**
     * 为测试环境提供WebSecurityCustomizer，禁用安全过滤链
     * 这在需要完全禁用Spring Security进行测试时很有用
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/**");
    }

    /**
     * 为测试环境提供MethodSecurityExpressionHandler实例
     */
    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(Mockito.mock(org.springframework.security.access.PermissionEvaluator.class));
        return expressionHandler;
    }
    
    /**
     * 为测试环境提供JwtTokenUtil的Mock实现
     */
    @Bean
    @Primary
    public JwtTokenUtil jwtTokenUtil() {
        return Mockito.mock(JwtTokenUtil.class);
    }
} 