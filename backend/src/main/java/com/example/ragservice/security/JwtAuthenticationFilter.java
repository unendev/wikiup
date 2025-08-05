package com.example.ragservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT认证过滤器
 * 拦截每个请求，检查是否存在有效的JWT令牌
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 从请求中获取JWT令牌
            String jwt = parseJwt(request);
            if (jwt != null) {
                // 从令牌中获取用户名
                String username = jwtTokenUtil.getUsernameFromToken(jwt);

                // 如果用户名不为空且当前SecurityContext中没有认证信息
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 加载用户详情
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    // 验证令牌
                    if (jwtTokenUtil.validateToken(jwt, userDetails)) {
                        // 创建认证对象
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails, null, userDetails.getAuthorities());
                        
                        // 设置认证详情
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        // 将认证对象设置到SecurityContext中
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("无法设置用户认证: {}", e);
        }

        // 继续执行过滤器链
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求中解析JWT令牌
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
} 