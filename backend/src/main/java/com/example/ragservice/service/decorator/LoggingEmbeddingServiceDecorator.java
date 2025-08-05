package com.example.ragservice.service.decorator;

import com.example.ragservice.service.EmbeddingServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 嵌入服务日志装饰器
 * 为嵌入服务添加日志功能
 */
@Component
public class LoggingEmbeddingServiceDecorator extends AbstractServiceDecorator<EmbeddingServiceInterface>
        implements EmbeddingServiceInterface {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingEmbeddingServiceDecorator.class);
    
    private final AtomicInteger embedCount = new AtomicInteger(0);
    private final AtomicInteger batchCount = new AtomicInteger(0);
    private long totalProcessingTime = 0;
    
    public LoggingEmbeddingServiceDecorator(EmbeddingServiceInterface embeddingService) {
        super(embeddingService, "LoggingEmbeddingService", "Embedding service with logging");
    }
    
    @Override
    public float[] embed(String text) {
        if (!enabled) {
            return decoratedService.embed(text);
        }
        
        int count = embedCount.incrementAndGet();
        long startTime = System.currentTimeMillis();
        
        // 记录请求信息
        if (count % 100 == 0) {
            logger.info("Embedding request #{}: text length={}", count, text.length());
        }
        
        try {
            // 调用原始服务
            float[] embedding = decoratedService.embed(text);
            
            // 记录响应信息
            long endTime = System.currentTimeMillis();
            long processingTime = endTime - startTime;
            totalProcessingTime += processingTime;
            
            if (count % 100 == 0) {
                logger.info("Embedding response #{}: vector length={}, took {}ms, avg={}ms",
                        count, embedding.length, processingTime, getAverageProcessingTime());
            }
            
            return embedding;
        } catch (Exception e) {
            logger.error("Error generating embedding for text (length={}): {}",
                    text.length(), e.getMessage(), e);
            throw e;
        }
    }
    
    @Override
    public float[][] embedBatch(String[] texts) {
        if (!enabled) {
            return decoratedService.embedBatch(texts);
        }
        
        int count = batchCount.incrementAndGet();
        long startTime = System.currentTimeMillis();
        
        logger.info("Batch embedding request #{}: batch size={}", count, texts.length);
        
        try {
            // 调用原始服务
            float[][] embeddings = decoratedService.embedBatch(texts);
            
            // 记录响应信息
            long endTime = System.currentTimeMillis();
            long processingTime = endTime - startTime;
            
            logger.info("Batch embedding response #{}: {} vectors, took {}ms ({}ms per text)",
                    count, embeddings.length, processingTime, 
                    texts.length > 0 ? processingTime / texts.length : 0);
            
            return embeddings;
        } catch (Exception e) {
            logger.error("Error generating batch embeddings (size={}): {}",
                    texts.length, e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * 获取平均处理时间
     * @return 平均处理时间（毫秒）
     */
    public double getAverageProcessingTime() {
        int count = embedCount.get();
        return count > 0 ? (double) totalProcessingTime / count : 0.0;
    }
    
    /**
     * 获取嵌入请求计数
     * @return 请求计数
     */
    public int getEmbedCount() {
        return embedCount.get();
    }
    
    /**
     * 获取批量嵌入请求计数
     * @return 批量请求计数
     */
    public int getBatchCount() {
        return batchCount.get();
    }
    
    /**
     * 重置统计信息
     */
    public void resetStatistics() {
        embedCount.set(0);
        batchCount.set(0);
        totalProcessingTime = 0;
        logger.info("Embedding service statistics reset");
    }
} 