package com.example.ragservice.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 文档分块领域模型
 */
public class Chunk {
    private String id;
    private String documentId;
    private String text;
    private int index;
    private Map<String, String> metadata;
    private float[] embedding;
    private boolean embedded;
    
    public Chunk() {
        this.metadata = new HashMap<>();
        this.embedded = false;
    }
    
    /**
     * 创建新的文档块
     * @param documentId 文档ID
     * @param text 文本内容
     * @param index 索引顺序
     * @return 文档块对象
     */
    public static Chunk of(String documentId, String text, int index) {
        Chunk chunk = new Chunk();
        chunk.setDocumentId(documentId);
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
        this.embedded = true;
    }

    public boolean isEmbedded() {
        return embedded;
    }

    public void setEmbedded(boolean embedded) {
        this.embedded = embedded;
    }
} 