package com.example.ragservice.service.embedding;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 嵌入模型工厂类
 * 负责根据配置创建和管理不同的嵌入模型
 */
@Component
public class EmbeddingModelFactory {
    
    @Value("${model.url}")
    private String modelUrl;
    
    private Map<String, EmbeddingModel> models;
    private EmbeddingModel defaultModel;
    
    @PostConstruct
    public void init() {
        models = new HashMap<>();
        
        // 创建默认模型
        defaultModel = createModel(modelUrl);
        models.put(defaultModel.getModelId(), defaultModel);
    }
    
    /**
     * 获取默认嵌入模型
     * @return 默认嵌入模型
     */
    public EmbeddingModel getDefaultModel() {
        return defaultModel;
    }
    
    /**
     * 根据模型ID获取嵌入模型
     * @param modelId 模型ID
     * @return 嵌入模型
     */
    public EmbeddingModel getModel(String modelId) {
        return models.getOrDefault(modelId, defaultModel);
    }
    
    /**
     * 注册新的嵌入模型
     * @param modelUrl 模型URL
     * @return 创建的嵌入模型
     */
    public EmbeddingModel registerModel(String modelUrl) {
        EmbeddingModel model = createModel(modelUrl);
        models.put(model.getModelId(), model);
        return model;
    }
    
    /**
     * 根据模型URL创建嵌入模型
     * @param modelUrl 模型URL
     * @return 嵌入模型
     */
    private EmbeddingModel createModel(String modelUrl) {
        if (modelUrl.startsWith("djl://")) {
            return new DJLEmbeddingModel(modelUrl);
        } else if (modelUrl.startsWith("http://") || modelUrl.startsWith("https://")) {
            return new HttpEmbeddingModel(modelUrl);
        } else {
            return new LocalEmbeddingModel(modelUrl);
        }
    }
    
    /**
     * 获取所有可用的模型
     * @return 模型映射
     */
    public Map<String, EmbeddingModel> getAllModels() {
        return new HashMap<>(models);
    }
} 