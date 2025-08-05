package com.example.ragservice.service.embedding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 基于本地文件的嵌入模型实现
 * 适用于加载预计算的词向量
 */
public class LocalEmbeddingModel implements EmbeddingModel {
    private static final Logger logger = LoggerFactory.getLogger(LocalEmbeddingModel.class);
    
    private final String modelId;
    private final String modelUrl;
    private final String modelName;
    
    private boolean loaded;
    private int dimensions;
    private Map<String, float[]> wordVectors;
    
    public LocalEmbeddingModel(String modelPath) {
        this.modelId = "local-" + UUID.randomUUID().toString().substring(0, 8);
        this.modelUrl = modelPath;
        this.modelName = extractModelName(modelPath);
        this.loaded = false;
        this.wordVectors = new HashMap<>();
    }
    
    private String extractModelName(String modelPath) {
        if (modelPath == null) {
            return "Local Embedding Model";
        }
        
        File file = new File(modelPath);
        return file.getName().replace(".txt", "").replace(".bin", "").replace("-", " ");
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
        
        if (text == null || text.isEmpty()) {
            return new float[dimensions];
        }
        
        // 简单的加权平均方法
        String[] words = text.toLowerCase().split("\\s+");
        float[] embedding = new float[dimensions];
        int count = 0;
        
        for (String word : words) {
            if (wordVectors.containsKey(word)) {
                float[] vector = wordVectors.get(word);
                for (int i = 0; i < dimensions; i++) {
                    embedding[i] += vector[i];
                }
                count++;
            }
        }
        
        // 如果没有匹配的词，返回零向量
        if (count == 0) {
            return embedding;
        }
        
        // 标准化
        for (int i = 0; i < dimensions; i++) {
            embedding[i] /= count;
        }
        
        return embedding;
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
        if (loaded) {
            return;
        }
        
        try {
            File file = Paths.get(modelUrl).toFile();
            if (!file.exists() || !file.isFile()) {
                logger.error("Model file not found: {}", modelUrl);
                throw new RuntimeException("Model file not found");
            }
            
            logger.info("Loading word vectors from {}", modelUrl);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                String[] header = line.split(" ");
                int vocabSize = Integer.parseInt(header[0]);
                this.dimensions = Integer.parseInt(header[1]);
                
                logger.info("Word vector file has {} words with {} dimensions", vocabSize, dimensions);
                
                // 读取词向量
                int count = 0;
                while ((line = reader.readLine()) != null && count < vocabSize) {
                    String[] tokens = line.split(" ", dimensions + 1);
                    String word = tokens[0].toLowerCase();
                    float[] vector = new float[dimensions];
                    
                    for (int i = 0; i < dimensions; i++) {
                        vector[i] = Float.parseFloat(tokens[i + 1]);
                    }
                    
                    wordVectors.put(word, vector);
                    count++;
                    
                    if (count % 10000 == 0) {
                        logger.info("Loaded {} word vectors", count);
                    }
                }
                
                logger.info("Successfully loaded {} word vectors with {} dimensions", count, dimensions);
                this.loaded = true;
            }
        } catch (IOException e) {
            logger.error("Error loading word vectors", e);
            throw new RuntimeException("Failed to load word vectors", e);
        }
    }

    @Override
    public void unload() {
        wordVectors.clear();
        loaded = false;
        logger.info("Word vectors unloaded");
    }
} 