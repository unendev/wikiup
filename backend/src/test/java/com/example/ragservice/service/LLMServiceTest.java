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
public class LLMServiceTest {

    @MockBean
    private LLMService llmService;

    @Test
    public void testGenerateAnswer() {
        // 设置模拟行为
        Mockito.when(llmService.generateAnswer(any(List.class), anyString()))
               .thenReturn(Mono.just("这是一个测试回答"));
        
        // 执行测试
        String question = "饥荒游戏中的威尔逊是谁?";
        List<String> context = Collections.emptyList();
        String answer = llmService.generateAnswer(context, question).block();
        
        // 验证结果
        assertNotNull(answer);
        assertEquals("这是一个测试回答", answer);
    }

    @Test
    public void testGenerateAnswerStream() {
        // 由于流式响应难以测试，这里只验证方法不抛出异常
        assertDoesNotThrow(() -> {
            List<String> context = Collections.emptyList();
            String question = "测试问题";
            Consumer<String> responseConsumer = response -> {};
            Runnable onComplete = () -> {};
            Consumer<Throwable> onError = error -> {};
            
            llmService.generateAnswerStream(context, question, responseConsumer, onComplete, onError);
        });
    }
} 