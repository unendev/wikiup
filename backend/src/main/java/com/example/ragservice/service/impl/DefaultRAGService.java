package com.example.ragservice.service.impl;

import com.example.ragservice.dto.response.ChatResponse;
import com.example.ragservice.service.EmbeddingService;
import com.example.ragservice.service.LLMService;
import com.example.ragservice.service.RAGService;
import com.example.ragservice.service.VectorDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class DefaultRAGService implements RAGService {
    
    private static final Logger logger = LoggerFactory.getLogger(DefaultRAGService.class);
    
    @Autowired
    private VectorDBService vectorDBService;
    
    @Autowired
    private LLMService llmService;
    
    @Autowired
    private EmbeddingService embeddingService;
    
    private int topK = 5;
    
    @Override
    public Mono<ChatResponse> getAnswer(String question) {
        logger.info("Processing question: {}", question);
        
        return Mono.fromCallable(() -> {
            // 1. 生成问题的嵌入向量
            float[] queryVector = embeddingService.embed(question);
            
            // 2. 从向量数据库中检索最相关的文档
            List<VectorDBService.SearchResult> searchResults = vectorDBService.search(queryVector, topK);
            
            // 3. 从搜索结果中提取内容和元数据
            List<String> contexts = new ArrayList<>();
            List<Map<String, String>> sources = new ArrayList<>();
            
            for (VectorDBService.SearchResult result : searchResults) {
                contexts.add(result.text());
                sources.add(result.metadata());
            }
            
            // 4. 调用LLM生成回答
            long startTime = System.currentTimeMillis();
            String answer = "";
            
            if (!contexts.isEmpty()) {
                answer = llmService.generateAnswer(contexts, question).block();
            } else {
                answer = "抱歉，我没有找到相关的信息来回答您的问题。";
            }
            
            long processingTime = System.currentTimeMillis() - startTime;
            
            // 5. 构建响应
            return ChatResponse.of(answer, sources, processingTime);
        });
    }
    
    @Override
    public void getAnswerStream(String question, Consumer<String> onChunk, Runnable onComplete, Consumer<Throwable> onError) {
        try {
            logger.info("Processing streaming question: {}", question);
            
            // 1. 生成问题的嵌入向量
            float[] queryVector = embeddingService.embed(question);
            
            // 2. 从向量数据库中检索最相关的文档
            List<VectorDBService.SearchResult> searchResults = vectorDBService.search(queryVector, topK);
            
            // 3. 从搜索结果中提取内容
            List<String> contexts = new ArrayList<>();
            
            for (VectorDBService.SearchResult result : searchResults) {
                contexts.add(result.text());
            }
            
            // 4. 调用LLM生成流式回答
            llmService.generateAnswerStream(contexts, question, onChunk, onComplete, onError);
            
        } catch (Exception e) {
            logger.error("Error processing streaming question", e);
            onError.accept(e);
        }
    }
    
    @Override
    public void setTopK(int topK) {
        if (topK > 0) {
            this.topK = topK;
        }
    }
    
    @Override
    public int getTopK() {
        return topK;
    }
} 