package com.example.ragservice.model;

import javax.persistence.*;

/**
 * 嵌入向量领域模型
 */
@Entity
@Table(name = "embedding", indexes = {
    @Index(name = "idx_object_id", columnList = "object_id"),
    @Index(name = "idx_object_type", columnList = "object_type")
})
public class Embedding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "object_id", nullable = false, length = 100)
    private String objectId;  // 关联对象ID（文档或块）
    
    @Column(name = "object_type", nullable = false, length = 50)
    private String objectType;  // 关联对象类型
    
    @Transient
    private float[] vector;
    
    @Column(name = "vector_data", columnDefinition = "TEXT")
    private String vectorData;  // 用于存储序列化的vector数组
    
    @Column(name = "dimensions")
    private int dimensions;
    
    @Column(name = "model", length = 100)
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
                chunk.getChunkId(), 
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
                document.getDocId(),
                "document",
                vector,
                model
        );
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        if (vector == null && vectorData != null && !vectorData.isEmpty()) {
            // 从vectorData反序列化
            String[] parts = vectorData.split(",");
            vector = new float[parts.length];
            for (int i = 0; i < parts.length; i++) {
                vector[i] = Float.parseFloat(parts[i]);
            }
        }
        return vector;
    }

    public void setVector(float[] vector) {
        this.vector = vector;
        // 序列化到vectorData
        if (vector != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < vector.length; i++) {
                if (i > 0) sb.append(",");
                sb.append(vector[i]);
            }
            this.vectorData = sb.toString();
        }
        this.dimensions = vector != null ? vector.length : 0;
    }

    public String getVectorData() {
        return vectorData;
    }

    public void setVectorData(String vectorData) {
        this.vectorData = vectorData;
        this.vector = null; // 清除缓存，下次获取时重新解析
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