package com.example.ragservice.config;

import com.example.ragservice.security.JwtAuthenticationFilter;
import com.example.ragservice.security.handler.CustomAuthenticationFailureHandler;
import com.example.ragservice.security.handler.CustomAuthenticationSuccessHandler;
import com.example.ragservice.security.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * Spring Security配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(name = "spring.security.enabled", havingValue = "true", matchIfMissing = true)
@Profile("!test")
public class SecurityConfig {

    // 锁定时间常量（分钟）
    private static final int BLOCK_DURATION_MINUTES = 30;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;
    
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    private PasswordEncoder passwordEncoder;
    
    @PostConstruct
    public void init() {
        this.passwordEncoder = new BCryptPasswordEncoder(12); // 增加bcrypt强度
    }
    
    /**
     * 认证提供者
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider() {
            @Override
            protected void additionalAuthenticationChecks(UserDetails userDetails, 
                                                         org.springframework.security.authentication.UsernamePasswordAuthenticationToken authentication) {
                // 检查用户是否被阻止登录
                if (loginAttemptService.isBlocked(userDetails.getUsername())) {
                    throw new BadCredentialsException("登录失败次数过多，账户已被锁定" + 
                            BLOCK_DURATION_MINUTES + "分钟");
                }
                
                // 调用父类方法，执行默认的认证检查
                super.additionalAuthenticationChecks(userDetails, authentication);
            }
        };
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setHideUserNotFoundExceptions(true); // 防止用户枚举攻击
        return authProvider;
    }

    /**
     * CORS配置
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // 生产环境应该限制为特定域名
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    /**
     * 配置不需要安全过滤的路径
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/favicon.ico");
    }

    /**
     * 配置HTTP安全规则
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 禁用CSRF保护，因为我们使用JWT进行身份验证
            .csrf().disable()
            
            // 配置CORS
            .cors().configurationSource(corsConfigurationSource())
            .and()
            
            // 设置会话管理为无状态（RESTful API）
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            
            // 配置安全HTTP头
            .headers()
                .contentSecurityPolicy("default-src 'self'; script-src 'self'; object-src 'none'; frame-ancestors 'none'")
                .and()
                .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN)
                .and()
                .frameOptions().deny() // 防止网站被嵌入到iframe中
                .xssProtection().block(true) // 启用XSS保护
                .and()
                .and()
            
            // 配置表单登录
            .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
            .and()
            
            // 配置请求授权规则
            .authorizeRequests()
            // 允许所有人访问登录接口和健康检查接口
            .antMatchers("/api/auth/**", "/health/**").permitAll()
            // 允许所有人访问公共测试接口
            .antMatchers("/api/test/all").permitAll()
            // 允许Websocket连接
            .antMatchers("/api/v1/qa/ask").permitAll()
            // 对于其他请求，需要认证
            .anyRequest().authenticated();
            
        // 添加JWT过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
    }
    
    /**
     * 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder(12); // 增加bcrypt强度
        }
        return passwordEncoder;
    }
    
    /**
     * 认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
} 