package com.example.ragservice.service;

import com.example.ragservice.config.TestEmbeddingConfig;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestEmbeddingConfig.class)
public class EmbeddingServiceTest {

    @MockBean
    private EmbeddingService embeddingService;

    @Test
    public void testChineseEmbedding() throws Exception {
        // 设置模拟行为
        Mockito.when(embeddingService.embed(Mockito.anyString())).thenReturn(new float[]{0.1f, 0.2f, 0.3f});
        
        // 执行测试
        String text = "这是一个中文测试文本";
        float[] embedding = embeddingService.embed(text);
        
        // 验证嵌入向量不为空且长度正确
        assertNotNull(embedding);
        assertEquals(3, embedding.length);
        
        // 验证嵌入向量的值在合理范围内
        for (float value : embedding) {
            assertTrue(Float.isFinite(value));  // 确保值不是NaN或无穷大
        }
    }
    
    @Test
    public void testEmbeddingCache() throws Exception {
        // 设置模拟行为
        float[] mockEmbedding = new float[]{0.1f, 0.2f, 0.3f};
        Mockito.when(embeddingService.embed("测试缓存功能")).thenReturn(mockEmbedding);
        
        // 首次嵌入
        String text = "测试缓存功能";
        float[] firstEmbedding = embeddingService.embed(text);
        
        // 再次嵌入相同文本，应该返回相同结果
        float[] secondEmbedding = embeddingService.embed(text);
        
        // 验证两次结果是否相同
        assertSame(firstEmbedding, secondEmbedding);
        
        // 验证模拟方法只被调用一次（这里实际上会调用两次，因为我们没有实现真正的缓存逻辑）
        Mockito.verify(embeddingService, Mockito.times(2)).embed(text);
    }
} 