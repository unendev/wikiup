package com.example.ragservice.service.embedding;

/**
 * 嵌入模型接口
 * 负责将文本转换为嵌入向量
 */
public interface EmbeddingModel {
    
    /**
     * 获取模型ID
     * @return 模型ID
     */
    String getModelId();
    
    /**
     * 获取模型名称
     * @return 模型名称
     */
    String getModelName();
    
    /**
     * 获取模型URL
     * @return 模型URL
     */
    String getModelUrl();
    
    /**
     * 获取向量维度
     * @return 向量维度
     */
    int getDimensions();
    
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
    float[][] embedBatch(String[] texts);
    
    /**
     * 计算两个向量的余弦相似度
     * @param vector1 向量1
     * @param vector2 向量2
     * @return 余弦相似度（-1到1之间）
     */
    default double cosineSimilarity(float[] vector1, float[] vector2) {
        if (vector1 == null || vector2 == null || vector1.length != vector2.length) {
            return 0.0;
        }
        
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        
        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            normA += Math.pow(vector1[i], 2);
            normB += Math.pow(vector2[i], 2);
        }
        
        if (normA == 0 || normB == 0) {
            return 0.0;
        }
        
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
    
    /**
     * 检查模型是否已加载
     * @return 是否已加载
     */
    boolean isLoaded();
    
    /**
     * 加载模型
     */
    void load();
    
    /**
     * 卸载模型
     */
    void unload();
} 