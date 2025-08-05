package com.example.ragservice.service;

/**
 * 嵌入服务接口
 */
public interface EmbeddingServiceInterface {
    
    /**
     * 对文本生成嵌入向量
     * @param text 输入文本
     * @return 嵌入向量
     */
    float[] embed(String text);
    
    /**
     * 对多个文本生成嵌入向量
     * @param texts 输入文本数组
     * @return 嵌入向量数组
     */
    default float[][] embedBatch(String[] texts) {
        if (texts == null || texts.length == 0) {
            return new float[0][];
        }
        
        float[][] embeddings = new float[texts.length][];
        for (int i = 0; i < texts.length; i++) {
            embeddings[i] = embed(texts[i]);
        }
        
        return embeddings;
    }
} 