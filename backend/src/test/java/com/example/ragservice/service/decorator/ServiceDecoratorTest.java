package com.example.ragservice.service.decorator;

import com.example.ragservice.service.EmbeddingService;
import com.example.ragservice.service.EmbeddingServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * 服务装饰器测试类
 */
@SpringBootTest
@TestPropertySource(properties = {
    "spring.main.allow-bean-definition-overriding=true"
})
@ActiveProfiles("test")
public class ServiceDecoratorTest {

    @Mock
    private EmbeddingServiceInterface mockEmbeddingService;

    private CachingEmbeddingServiceDecorator cachingDecorator;
    
    private LoggingEmbeddingServiceDecorator loggingDecorator;
    
    @BeforeEach
    public void setup() {
        // 创建模拟的嵌入服务
        when(mockEmbeddingService.embed(anyString())).thenReturn(new float[]{0.1f, 0.2f, 0.3f});
        
        // 创建装饰器实例
        cachingDecorator = new CachingEmbeddingServiceDecorator(mockEmbeddingService);
        loggingDecorator = new LoggingEmbeddingServiceDecorator(mockEmbeddingService);
    }
    
    @Test
    public void testCachingDecorator() {
        // 第一次调用应该调用底层服务
        float[] result1 = cachingDecorator.embed("test");
        
        // 第二次调用应该使用缓存
        float[] result2 = cachingDecorator.embed("test");
        
        // 验证结果
        assertNotNull(result1);
        assertNotNull(result2);
        assertArrayEquals(result1, result2);
        
        // 验证底层服务只被调用一次
        verify(mockEmbeddingService, times(1)).embed("test");
    }
    
    @Test
    public void testLoggingDecorator() {
        // 调用装饰器
        float[] result = loggingDecorator.embed("test");
        
        // 验证结果
        assertNotNull(result);
        
        // 验证底层服务被调用
        verify(mockEmbeddingService, times(1)).embed("test");
    }
    
    @Test
    public void testDecoratorChaining() {
        // 创建装饰器链：日志装饰器 -> 缓存装饰器 -> 嵌入服务
        LoggingEmbeddingServiceDecorator chainedDecorator = 
            new LoggingEmbeddingServiceDecorator(cachingDecorator);
        
        // 第一次调用
        float[] result1 = chainedDecorator.embed("test");
        
        // 第二次调用
        float[] result2 = chainedDecorator.embed("test");
        
        // 验证结果
        assertNotNull(result1);
        assertNotNull(result2);
        assertArrayEquals(result1, result2);
        
        // 验证底层服务只被调用一次（因为有缓存）
        verify(mockEmbeddingService, times(1)).embed("test");
    }
} 