package com.example.ragservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.PostConstruct;

/**
 * JWT令牌工具类
 */
@Component
public class JwtTokenUtil {

    // 默认令牌有效期为24小时
    private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;
    
    // 记住我状态下令牌有效期为7天
    private static final long JWT_TOKEN_VALIDITY_REMEMBER_ME = 7 * 24 * 60 * 60 * 1000;
    
    // 令牌刷新期限（30天）
    private static final long JWT_REFRESH_LIMIT = 30 * 24 * 60 * 60 * 1000;

    @Value("${jwt.secret:}")
    private String configuredSecret;
    
    private String secret;
    
    @PostConstruct
    public void init() {
        // 如果配置的密钥为空或太短，则生成一个安全的随机密钥
        if (configuredSecret == null || configuredSecret.length() < 32) {
            byte[] keyBytes = new byte[64];
            new SecureRandom().nextBytes(keyBytes);
            secret = Base64.getEncoder().encodeToString(keyBytes);
            System.out.println("警告: 使用随机生成的JWT密钥。在生产环境中请配置固定的密钥。");
        } else {
            secret = configuredSecret;
        }
    }

    /**
     * 从令牌中获取用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 从令牌中获取过期日期
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }
    
    /**
     * 从令牌中获取发行日期
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }
    
    /**
     * 从令牌中获取JWT ID
     */
    public String getJtiFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    /**
     * 从令牌中获取特定的声明
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 获取令牌中的所有声明
     */
    private Claims getAllClaimsFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 检查令牌是否已过期
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    /**
     * 检查令牌是否可以刷新
     * 令牌可以刷新的条件：
     * 1. 令牌已过期
     * 2. 令牌的发行日期在刷新期限之内
     */
    public Boolean canTokenBeRefreshed(String token) {
        final Date issuedAt = getIssuedAtDateFromToken(token);
        return issuedAt.after(new Date(System.currentTimeMillis() - JWT_REFRESH_LIMIT));
    }
    
    /**
     * 刷新令牌
     * 使用相同的声明生成一个新的令牌
     */
    public String refreshToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY));
        
        // 更新jti以确保令牌唯一
        claims.setId(generateTokenId());
        
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 为用户生成令牌
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // 添加自定义声明
        claims.put("roles", userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername(), false);
    }
    
    /**
     * 为用户生成令牌（带记住我功能）
     */
    public String generateToken(UserDetails userDetails, boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        // 添加自定义声明
        claims.put("roles", userDetails.getAuthorities());
        claims.put("rememberMe", rememberMe);
        return doGenerateToken(claims, userDetails.getUsername(), rememberMe);
    }
    
    /**
     * 生成唯一令牌ID
     */
    private String generateTokenId() {
        byte[] randomBytes = new byte[16];
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }

    /**
     * 生成令牌
     * 1. 定义令牌的声明，如发行者、过期时间、主题和ID
     * 2. 使用HS512算法和密钥对令牌进行签名
     */
    private String doGenerateToken(Map<String, Object> claims, String subject, boolean rememberMe) {
        long expirationTime = rememberMe ? JWT_TOKEN_VALIDITY_REMEMBER_ME : JWT_TOKEN_VALIDITY;
        
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setId(generateTokenId()) // 添加唯一令牌ID
                .setIssuer("wikiup-api") // 添加发行者
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 验证令牌
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
} 