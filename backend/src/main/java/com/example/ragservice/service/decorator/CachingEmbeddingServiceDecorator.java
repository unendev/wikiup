package com.example.ragservice.service.decorator;

import com.example.ragservice.service.EmbeddingServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 嵌入服务缓存装饰器
 * 为嵌入服务添加缓存功能
 */
@Component
public class CachingEmbeddingServiceDecorator extends AbstractServiceDecorator<EmbeddingServiceInterface> 
        implements EmbeddingServiceInterface {
    
    private static final Logger logger = LoggerFactory.getLogger(CachingEmbeddingServiceDecorator.class);
    
    private final Map<String, float[]> cache = new ConcurrentHashMap<>();
    private int cacheHits = 0;
    private int cacheMisses = 0;
    
    public CachingEmbeddingServiceDecorator(EmbeddingServiceInterface embeddingService) {
        super(embeddingService, "CachingEmbeddingService", "Embedding service with in-memory cache");
    }
    
    @Override
    public float[] embed(String text) {
        if (!enabled) {
            return decoratedService.embed(text);
        }
        
        // 简单的缓存key
        String cacheKey = text.trim();
        
        // 从缓存中查找
        float[] cachedEmbedding = cache.get(cacheKey);
        if (cachedEmbedding != null) {
            cacheHits++;
            if (cacheHits % 100 == 0) {
                logger.debug("Cache hit rate: {}%", getCacheHitRate());
            }
            return cachedEmbedding;
        }
        
        // 缓存未命中，调用原始服务
        cacheMisses++;
        float[] embedding = decoratedService.embed(text);
        
        // 将结果存入缓存
        if (embedding != null) {
            cache.put(cacheKey, embedding);
        }
        
        return embedding;
    }
    
    @Override
    public float[][] embedBatch(String[] texts) {
        if (!enabled) {
            return decoratedService.embedBatch(texts);
        }
        
        if (texts == null || texts.length == 0) {
            return new float[0][];
        }
        
        // 为每个文本单独调用embed方法，以利用缓存
        float[][] embeddings = new float[texts.length][];
        for (int i = 0; i < texts.length; i++) {
            embeddings[i] = embed(texts[i]);
        }
        
        return embeddings;
    }
    
    /**
     * 清除缓存
     */
    public void clearCache() {
        int size = cache.size();
        cache.clear();
        cacheHits = 0;
        cacheMisses = 0;
        logger.info("Cache cleared. Previously contained {} entries.", size);
    }
    
    /**
     * 获取缓存大小
     * @return 缓存大小
     */
    public int getCacheSize() {
        return cache.size();
    }
    
    /**
     * 获取缓存命中率
     * @return 缓存命中率（百分比）
     */
    public double getCacheHitRate() {
        int total = cacheHits + cacheMisses;
        return total > 0 ? (double) cacheHits / total * 100 : 0.0;
    }
    
    /**
     * 获取缓存命中次数
     * @return 缓存命中次数
     */
    public int getCacheHits() {
        return cacheHits;
    }
    
    /**
     * 获取缓存未命中次数
     * @return 缓存未命中次数
     */
    public int getCacheMisses() {
        return cacheMisses;
    }
} 