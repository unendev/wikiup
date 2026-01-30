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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
    public Mono<ChatResponse> getAnswer(String question, Principal principal) {
        String username = (principal != null) ? principal.getName() : "anonymous";
        logger.info("Processing question from user '{}': {}", username, question);
        
        return Mono.fromCallable(() -> {
            // 1. 生成问题的嵌入向量
            float[] queryVector = embeddingService.embed(question);
            
            // 2. 从向量数据库中检索最相关的文档
            List<VectorDBService.SearchResult> searchResults = vectorDBService.search(queryVector, topK);
            
            // 3. 从搜索结果中提取内容
            List<String> contexts = new ArrayList<>();
            
            for (VectorDBService.SearchResult result : searchResults) {
                contexts.add(result.text());
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
            
            // 5. 构建响应（包含评分信息和内容）
            ChatResponse response = new ChatResponse(answer);
            response.setProcessingTimeMs(processingTime);
            
            for (VectorDBService.SearchResult result : searchResults) {
                response.addSource(result.metadata(), result.score(), result.text());
            }
            
            // 调试：打印sources信息
            logger.info("Response sources count: {}", response.getSources().size());
            for (ChatResponse.SourceInfo source : response.getSources()) {
                logger.info("Source - title: {}, content length: {}, score: {}", 
                    source.getTitle(), 
                    source.getContent() != null ? source.getContent().length() : 0,
                    source.getScore());
            }
            
            return response;
        });
    }
    
    @Override
    public void getAnswerStream(String question, Principal principal, Consumer<String> onChunk, Consumer<List<ChatResponse.SourceInfo>> onComplete, Consumer<Throwable> onError) {
        try {
            String username = (principal != null) ? principal.getName() : "anonymous";
            logger.info("Processing streaming question from user '{}': {}", username, question);
            
            // 1. 生成问题的嵌入向量
            float[] queryVector = embeddingService.embed(question);
            
            // 2. 从向量数据库中检索最相关的文档
            List<VectorDBService.SearchResult> searchResults = vectorDBService.search(queryVector, topK);
            
            // 3. 从搜索结果中提取内容
            List<String> contexts = new ArrayList<>();
            
            for (VectorDBService.SearchResult result : searchResults) {
                contexts.add(result.text());
            }
            
            // 4. 构建来源信息列表
            List<ChatResponse.SourceInfo> sources = new ArrayList<>();
            for (VectorDBService.SearchResult result : searchResults) {
                ChatResponse.SourceInfo sourceInfo = new ChatResponse.SourceInfo();
                sourceInfo.setTitle(result.metadata().getOrDefault("title", "未知标题"));
                sourceInfo.setSource(result.metadata().getOrDefault("source", "未知来源"));
                sourceInfo.setPath(result.metadata().getOrDefault("path", ""));
                sourceInfo.setScore(result.score());
                sourceInfo.setContent(result.text());
                sources.add(sourceInfo);
            }
            
            // 5. 调用LLM生成流式回答，完成时传递来源信息
            llmService.generateAnswerStream(contexts, question, onChunk, 
                () -> onComplete.accept(sources), 
                onError);
            
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