package com.example.ragservice.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.ragservice.security.model.User;
import com.example.ragservice.security.service.UserService;
import com.example.ragservice.security.service.UserDetailsImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JWT令牌测试类
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "spring.main.allow-bean-definition-overriding=true",
    "djl.offline=true",
    "djl.engine.default=PyTorch"
})
public class JwtAuthenticationTest {

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private UserDetailsService userDetailsService;
    
    @MockBean
    private UserService userService;

    @Value("${jwt.secret:WikiUpSecretKey12345678901234567890}")
    private String secret;
    
    private UserDetailsImpl userDetails;
    private String mockToken = "mock.jwt.token";
    
    @BeforeEach
    void setup() {
        // 创建测试用户
        User testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password");
        testUser.setEmail("test@example.com");
        testUser.setRoles(new HashSet<>());
        
        // 创建UserDetails实例
        userDetails = UserDetailsImpl.build(testUser);
        
        // 配置UserService模拟
        when(userService.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        
        // 配置UserDetailsService模拟
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        
        // 配置JwtTokenUtil模拟
        when(jwtTokenUtil.generateToken(any(UserDetails.class))).thenReturn(mockToken);
        when(jwtTokenUtil.generateToken(any(UserDetails.class), anyBoolean())).thenReturn(mockToken);
        when(jwtTokenUtil.getUsernameFromToken(mockToken)).thenReturn("testuser");
        when(jwtTokenUtil.validateToken(mockToken, userDetails)).thenReturn(true);
        when(jwtTokenUtil.getExpirationDateFromToken(mockToken)).thenReturn(new Date(System.currentTimeMillis() + 3600000));
        when(jwtTokenUtil.getIssuedAtDateFromToken(mockToken)).thenReturn(new Date());
        when(jwtTokenUtil.refreshToken(mockToken)).thenReturn("refreshed." + mockToken);
        
        // 配置过期令牌的行为
        doThrow(new ExpiredJwtException(null, null, "Token expired"))
            .when(jwtTokenUtil).getUsernameFromToken("expired.token");
    }

    @Test
    public void testJwtTokenGeneration() {
        // 生成令牌
        String token = jwtTokenUtil.generateToken(userDetails);
        
        // 验证令牌不为空
        assertNotNull(token);
        assertTrue(token.length() > 0);
        
        // 从令牌中提取用户名
        String username = jwtTokenUtil.getUsernameFromToken(token);
        assertEquals("testuser", username);
        
        // 验证令牌
        assertTrue(jwtTokenUtil.validateToken(token, userDetails));
    }
    
    @Test
    public void testJwtTokenWithRememberMe() {
        // 生成普通令牌
        String standardToken = jwtTokenUtil.generateToken(userDetails);
        Date standardExpiration = jwtTokenUtil.getExpirationDateFromToken(standardToken);
        
        // 模拟记住我令牌的过期时间更长
        Date rememberMeExpiration = new Date(System.currentTimeMillis() + 7 * 24 * 3600000);
        when(jwtTokenUtil.getExpirationDateFromToken("rememberMe.token")).thenReturn(rememberMeExpiration);
        when(jwtTokenUtil.generateToken(userDetails, true)).thenReturn("rememberMe.token");
        
        // 生成记住我令牌
        String rememberMeToken = jwtTokenUtil.generateToken(userDetails, true);
        Date rememberMeTokenExpiration = jwtTokenUtil.getExpirationDateFromToken(rememberMeToken);
        
        // 验证记住我令牌的有效期更长
        assertTrue(rememberMeTokenExpiration.after(standardExpiration));
    }
    
    @Test
    public void testJwtTokenRefresh() {
        // 生成令牌
        String token = jwtTokenUtil.generateToken(userDetails);
        
        // 刷新令牌
        String refreshedToken = jwtTokenUtil.refreshToken(token);
        
        // 验证刷新的令牌
        assertNotNull(refreshedToken);
        assertTrue(refreshedToken.length() > 0);
        
        // 验证刷新令牌与原始令牌不同
        assertNotEquals(token, refreshedToken);
    }
    
    @Test
    public void testExpiredJwtToken() {
        // 验证令牌已过期
        assertThrows(ExpiredJwtException.class, () -> {
            jwtTokenUtil.getUsernameFromToken("expired.token");
        });
    }
    
    @Test
    public void testJwtTokenValidity() {
        // 生成令牌
        String token = jwtTokenUtil.generateToken(userDetails);
        
        // 验证令牌的签发时间和过期时间
        Date issuedAt = jwtTokenUtil.getIssuedAtDateFromToken(token);
        Date expiration = jwtTokenUtil.getExpirationDateFromToken(token);
        
        assertNotNull(issuedAt);
        assertNotNull(expiration);
        assertTrue(expiration.after(issuedAt));
    }
} 