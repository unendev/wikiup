package com.example.ragservice.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * 文档领域模型
 */
public class Document {
    private String id;
    private String title;
    private String content;
    private String source;
    private String path;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DocumentStatus status;
    private List<Chunk> chunks;
    private Map<String, String> metadata;
    
    // 嵌入状态
    private boolean embedded;

    public Document() {
        this.chunks = new ArrayList<>();
        this.metadata = new HashMap<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.status = DocumentStatus.CREATED;
        this.embedded = false;
    }
    
    /**
     * 添加文档块
     */
    public void addChunk(Chunk chunk) {
        this.chunks.add(chunk);
    }
    
    /**
     * 添加元数据
     */
    public void addMetadata(String key, String value) {
        this.metadata.put(key, value);
    }
    
    /**
     * 文档状态枚举
     */
    public enum DocumentStatus {
        CREATED,      // 创建完成
        CHUNKED,      // 分块完成
        PROCESSING,   // 处理中
        EMBEDDED,     // 嵌入向量完成
        INDEXED,      // 已索引
        ERROR         // 处理错误
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public void setChunks(List<Chunk> chunks) {
        this.chunks = chunks;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public boolean isEmbedded() {
        return embedded;
    }

    public void setEmbedded(boolean embedded) {
        this.embedded = embedded;
    }
} 