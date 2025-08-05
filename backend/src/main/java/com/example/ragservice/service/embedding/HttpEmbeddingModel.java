package com.example.ragservice.service.embedding;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 基于HTTP的嵌入模型实现
 * 通过RESTful API调用外部嵌入服务
 */
public class HttpEmbeddingModel implements EmbeddingModel {
    private static final Logger logger = LoggerFactory.getLogger(HttpEmbeddingModel.class);
    
    private final String modelId;
    private final String modelUrl;
    private final String modelName;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    private boolean loaded;
    private int dimensions;
    
    public HttpEmbeddingModel(String modelUrl) {
        this.modelId = "http-" + UUID.randomUUID().toString().substring(0, 8);
        this.modelUrl = modelUrl;
        this.modelName = "HTTP Embedding Model";
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.loaded = true; // HTTP模型不需要显式加载
    }

    @Override
    public String getModelId() {
        return modelId;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public String getModelUrl() {
        return modelUrl;
    }

    @Override
    public int getDimensions() {
        return dimensions;
    }

    @Override
    public float[] embed(String text) {
        if (text == null || text.isEmpty()) {
            return new float[0];
        }
        
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("text", text);
            
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
            
            Map<String, Object> response = restTemplate.postForObject(
                    modelUrl,
                    request,
                    Map.class
            );
            
            if (response != null && response.containsKey("embedding")) {
                Object embeddingObj = response.get("embedding");
                if (embeddingObj instanceof float[]) {
                    float[] embedding = (float[]) embeddingObj;
                    if (dimensions == 0) {
                        dimensions = embedding.length;
                    }
                    return embedding;
                } else if (embeddingObj instanceof double[]) {
                    double[] doubleEmbedding = (double[]) embeddingObj;
                    float[] embedding = new float[doubleEmbedding.length];
                    for (int i = 0; i < doubleEmbedding.length; i++) {
                        embedding[i] = (float) doubleEmbedding[i];
                    }
                    if (dimensions == 0) {
                        dimensions = embedding.length;
                    }
                    return embedding;
                } else if (embeddingObj instanceof java.util.List) {
                    @SuppressWarnings("unchecked")
                    java.util.List<Number> listEmbedding = (java.util.List<Number>) embeddingObj;
                    float[] embedding = new float[listEmbedding.size()];
                    for (int i = 0; i < listEmbedding.size(); i++) {
                        embedding[i] = listEmbedding.get(i).floatValue();
                    }
                    if (dimensions == 0) {
                        dimensions = embedding.length;
                    }
                    return embedding;
                }
            }
            
            logger.error("Invalid response format from embedding service: {}", response);
            throw new RuntimeException("Invalid response from embedding service");
        } catch (Exception e) {
            logger.error("Error calling embedding service", e);
            throw new RuntimeException("Error generating embedding", e);
        }
    }

    @Override
    public float[][] embedBatch(String[] texts) {
        if (texts == null || texts.length == 0) {
            return new float[0][];
        }
        
        float[][] embeddings = new float[texts.length][];
        for (int i = 0; i < texts.length; i++) {
            embeddings[i] = embed(texts[i]);
        }
        
        return embeddings;
    }

    @Override
    public boolean isLoaded() {
        return loaded;
    }

    @Override
    public void load() {
        // HTTP模型不需要显式加载
        this.loaded = true;
    }

    @Override
    public void unload() {
        // HTTP模型不需要显式卸载
    }
} 