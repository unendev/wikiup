package com.example.ragservice.model;

import java.util.Arrays;

/**
 * 嵌入向量领域模型
 */
public class Embedding {
    private String id;
    private String objectId;  // 关联对象ID（文档或块）
    private String objectType;  // 关联对象类型
    private float[] vector;
    private int dimensions;
    private String model;  // 使用的模型名称
    
    public Embedding() {
    }
    
    public Embedding(String objectId, String objectType, float[] vector, String model) {
        this.objectId = objectId;
        this.objectType = objectType;
        this.vector = vector;
        this.dimensions = vector != null ? vector.length : 0;
        this.model = model;
    }
    
    /**
     * 计算与另一个向量的余弦相似度
     */
    public double cosineSimilarity(float[] otherVector) {
        if (vector == null || otherVector == null || vector.length != otherVector.length) {
            return 0.0;
        }
        
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        
        for (int i = 0; i < vector.length; i++) {
            dotProduct += vector[i] * otherVector[i];
            normA += Math.pow(vector[i], 2);
            normB += Math.pow(otherVector[i], 2);
        }
        
        if (normA == 0 || normB == 0) {
            return 0.0;
        }
        
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
    
    /**
     * 计算与另一个嵌入向量的余弦相似度
     */
    public double cosineSimilarity(Embedding other) {
        return cosineSimilarity(other.getVector());
    }
    
    /**
     * 从Chunk对象创建嵌入向量
     */
    public static Embedding fromChunk(Chunk chunk, String model) {
        return new Embedding(
                chunk.getId(), 
                "chunk", 
                chunk.getEmbedding(), 
                model
        );
    }
    
    /**
     * 从Document对象创建嵌入向量
     */
    public static Embedding fromDocument(Document document, float[] vector, String model) {
        return new Embedding(
                document.getId(),
                "document",
                vector,
                model
        );
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public float[] getVector() {
        return vector;
    }

    public void setVector(float[] vector) {
        this.vector = vector;
        this.dimensions = vector != null ? vector.length : 0;
    }

    public int getDimensions() {
        return dimensions;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    @Override
    public String toString() {
        return "Embedding{" +
                "id='" + id + '\'' +
                ", objectId='" + objectId + '\'' +
                ", objectType='" + objectType + '\'' +
                ", vector=" + (vector != null ? "[" + vector.length + " dims]" : "null") +
                ", model='" + model + '\'' +
                '}';
    }
} 