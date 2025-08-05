package com.example.ragservice.service.embedding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ai.djl.Application;
import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;

import java.io.IOException;
import java.util.UUID;

/**
 * 基于DJL的嵌入模型实现
 */
public class DJLEmbeddingModel implements EmbeddingModel {
    private static final Logger logger = LoggerFactory.getLogger(DJLEmbeddingModel.class);
    
    private final String modelId;
    private final String modelUrl;
    private final String modelName;
    
    private ZooModel<String, float[]> model;
    private Predictor<String, float[]> predictor;
    private boolean loaded;
    private int dimensions;
    
    public DJLEmbeddingModel(String modelUrl) {
        this.modelId = extractModelId(modelUrl);
        this.modelUrl = modelUrl;
        this.modelName = extractModelName(modelUrl);
        this.loaded = false;
    }
    
    private String extractModelId(String modelUrl) {
        if (modelUrl == null) {
            return UUID.randomUUID().toString();
        }
        
        String[] parts = modelUrl.split("/");
        if (parts.length > 0) {
            return parts[parts.length - 1];
        }
        
        return UUID.randomUUID().toString();
    }
    
    private String extractModelName(String modelUrl) {
        if (modelUrl == null) {
            return "Unknown Model";
        }
        
        String[] parts = modelUrl.split("/");
        if (parts.length > 0) {
            return parts[parts.length - 1].replace("-", " ");
        }
        
        return "Unknown Model";
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
        if (!loaded) {
            load();
        }
        return dimensions;
    }

    @Override
    public float[] embed(String text) {
        if (!loaded) {
            load();
        }
        
        try {
            float[] embedding = predictor.predict(text);
            if (dimensions == 0) {
                dimensions = embedding.length;
            }
            return embedding;
        } catch (TranslateException e) {
            logger.error("Error embedding text", e);
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
        return loaded && model != null && predictor != null;
    }

    @Override
    public void load() {
        if (loaded) {
            return;
        }
        
        try {
            logger.info("Loading embedding model from {}", modelUrl);
            Criteria<String, float[]> criteria = Criteria.builder()
                    .optApplication(Application.NLP.TEXT_EMBEDDING)
                    .setTypes(String.class, float[].class)
                    .optModelUrls(modelUrl)
                    .optProgress(new ProgressBar())
                    .build();
            
            this.model = criteria.loadModel();
            this.predictor = model.newPredictor();
            this.loaded = true;
            logger.info("Embedding model loaded successfully");
            
            // 测试并获取维度信息
            float[] testEmbedding = embed("Test");
            this.dimensions = testEmbedding.length;
            logger.info("Model dimensions: {}", dimensions);
            
        } catch (ModelNotFoundException | MalformedModelException | IOException e) {
            logger.error("Error loading embedding model", e);
            throw new RuntimeException("Failed to load embedding model", e);
        }
    }

    @Override
    public void unload() {
        if (!loaded) {
            return;
        }

        if (predictor != null) {
            predictor.close();
            predictor = null;
        }
        
        if (model != null) {
            // 直接关闭模型，不捕获异常
            // ZooModel的close方法实际上不会抛出IOException
            model.close();
            model = null;
        }
        
        loaded = false;
        logger.info("Embedding model unloaded");
    }
} 