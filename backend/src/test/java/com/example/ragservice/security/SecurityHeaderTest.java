package com.example.ragservice.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = "spring.main.allow-bean-definition-overriding=true")
public class SecurityHeaderTest {

    @Test
    public void testSecurityHeaders() {
        // 测试内容
    }
    
    @Test
    public void testCorsHeaders() {
        // 测试内容
    }
} 