package com.example.ragservice.config;

import com.example.ragservice.service.EmbeddingService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import ai.djl.translate.TranslateException;

@TestConfiguration
@Profile("test")
public class TestEmbeddingConfig {
    
    /**
     * 提供一个测试用的EmbeddingService，避免在测试环境中加载真实的模型
     */
    @Bean
    @Primary
    public EmbeddingService embeddingService() throws Exception {
        EmbeddingService mockService = Mockito.mock(EmbeddingService.class);
        
        // 模拟embed方法返回一个简单的向量
        Mockito.when(mockService.embed(Mockito.anyString())).thenReturn(new float[]{0.1f, 0.2f, 0.3f});
        
        return mockService;
    }
} 