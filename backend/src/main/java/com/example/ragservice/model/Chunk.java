package com.example.ragservice.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 文档分块领域模型
 */
@Entity
@Table(name = "chunk", indexes = {
    @Index(name = "idx_document_id", columnList = "document_id"),
    @Index(name = "idx_chunk_id", columnList = "chunk_id")
})
public class Chunk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "chunk_id", unique = true, length = 100)
    private String chunkId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;
    
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;
    
    @Column(name = "chunk_index")
    private int index;
    
    @ElementCollection
    @CollectionTable(name = "chunk_metadata", joinColumns = @JoinColumn(name = "chunk_id"))
    @MapKeyColumn(name = "meta_key")
    @Column(name = "meta_value", length = 1000)
    private Map<String, String> metadata;
    
    @Column(columnDefinition = "TEXT")
    private String embeddingData;  // 用于存储序列化的embedding数组
    
    @Transient
    private float[] embedding;
    
    @Column(name = "embedded")
    private boolean embedded;
    
    public Chunk() {
        this.metadata = new HashMap<>();
        this.embedded = false;
    }
    
    /**
     * 创建新的文档块
     * @param document 文档对象
     * @param text 文本内容
     * @param index 索引顺序
     * @return 文档块对象
     */
    public static Chunk of(Document document, String text, int index) {
        Chunk chunk = new Chunk();
        chunk.setDocument(document);
        chunk.setText(text);
        chunk.setIndex(index);
        return chunk;
    }
    
    /**
     * 添加元数据
     */
    public void addMetadata(String key, String value) {
        this.metadata.put(key, value);
    }
    
    /**
     * 从文档继承元数据
     */
    public void inheritMetadataFrom(Document document) {
        if (document.getMetadata() != null) {
            this.metadata.putAll(document.getMetadata());
        }
        this.metadata.put("source", document.getSource());
        this.metadata.put("title", document.getTitle());
        this.metadata.put("path", document.getPath());
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChunkId() {
        return chunkId;
    }

    public void setChunkId(String chunkId) {
        this.chunkId = chunkId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
    public String getDocumentId() {
        return document != null ? document.getDocId() : null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public float[] getEmbedding() {
        if (embedding == null && embeddingData != null && !embeddingData.isEmpty()) {
            // 从embeddingData反序列化
            String[] parts = embeddingData.split(",");
            embedding = new float[parts.length];
            for (int i = 0; i < parts.length; i++) {
                embedding[i] = Float.parseFloat(parts[i]);
            }
        }
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
        // 序列化到embeddingData
        if (embedding != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < embedding.length; i++) {
                if (i > 0) sb.append(",");
                sb.append(embedding[i]);
            }
            this.embeddingData = sb.toString();
        }
        this.embedded = true;
    }

    public String getEmbeddingData() {
        return embeddingData;
    }

    public void setEmbeddingData(String embeddingData) {
        this.embeddingData = embeddingData;
        this.embedding = null; // 清除缓存，下次获取时重新解析
    }

    public boolean isEmbedded() {
        return embedded;
    }

    public void setEmbedded(boolean embedded) {
        this.embedded = embedded;
    }
} 