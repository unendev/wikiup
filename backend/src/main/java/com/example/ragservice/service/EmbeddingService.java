package com.example.ragservice.service;

import com.example.ragservice.service.embedding.EmbeddingModel;
import com.example.ragservice.service.embedding.EmbeddingModelFactory;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmbeddingService implements EmbeddingServiceInterface {

    @Value("${model.url}")
    private String modelUrl;
    
    @Autowired
    private EmbeddingModelFactory embeddingModelFactory;

    private EmbeddingModel embeddingModel;
    
    // 添加缓存
    private final Map<String, float[]> embeddingCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        this.embeddingModel = embeddingModelFactory.getDefaultModel();
    }

    @PreDestroy
    public void destroy() {
        if (embeddingModel != null) {
            embeddingModel.unload();
        }
    }

    @Override
    public float[] embed(String text) {
        // 检查缓存中是否已有该文本的嵌入向量
        if (embeddingCache.containsKey(text)) {
            return embeddingCache.get(text);
        }
        
        // 如果缓存中没有，则计算嵌入向量并存入缓存
        float[] embedding = embeddingModel.embed(text);
        embeddingCache.put(text, embedding);
        return embedding;
    }
    
    // 清除缓存的方法
    public void clearCache() {
        embeddingCache.clear();
    }
    
    // 获取缓存大小的方法
    public int getCacheSize() {
        return embeddingCache.size();
    }
} 