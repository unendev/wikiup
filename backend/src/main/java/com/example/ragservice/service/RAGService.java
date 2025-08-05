package com.example.ragservice.service;

import com.example.ragservice.dto.response.ChatResponse;
import reactor.core.publisher.Mono;
import java.util.function.Consumer;

/**
 * RAG服务接口
 */
public interface RAGService {
    
    /**
     * 获取问题的回答
     * @param question 问题文本
     * @return 回答响应
     */
    Mono<ChatResponse> getAnswer(String question);
    
    /**
     * 流式获取问题的回答
     * @param question 问题文本
     * @param onChunk 文本块回调
     * @param onComplete 完成回调
     * @param onError 错误回调
     */
    void getAnswerStream(
            String question,
            Consumer<String> onChunk,
            Runnable onComplete,
            Consumer<Throwable> onError
    );
    
    /**
     * 设置查询的TopK参数
     * @param topK TopK参数
     */
    void setTopK(int topK);
    
    /**
     * 获取当前的TopK参数
     * @return TopK参数
     */
    int getTopK();
} 