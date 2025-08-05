package com.example.ragservice.dto.response;

import com.example.ragservice.model.Document;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 文档响应DTO
 */
public class DocumentResponse {
    private String id;
    private String title;
    private String source;
    private String path;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private int chunkCount;
    private boolean embedded;
    private Map<String, String> metadata;
    
    public DocumentResponse() {
    }
    
    /**
     * 从文档模型创建响应DTO
     */
    public static DocumentResponse fromDocument(Document document) {
        if (document == null) {
            return null;
        }
        
        DocumentResponse response = new DocumentResponse();
        response.setId(document.getId());
        response.setTitle(document.getTitle());
        response.setSource(document.getSource());
        response.setPath(document.getPath());
        response.setCreatedAt(document.getCreatedAt());
        response.setUpdatedAt(document.getUpdatedAt());
        response.setStatus(document.getStatus().name());
        response.setChunkCount(document.getChunks() != null ? document.getChunks().size() : 0);
        response.setEmbedded(document.isEmbedded());
        response.setMetadata(document.getMetadata());
        
        return response;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getChunkCount() {
        return chunkCount;
    }

    public void setChunkCount(int chunkCount) {
        this.chunkCount = chunkCount;
    }

    public boolean isEmbedded() {
        return embedded;
    }

    public void setEmbedded(boolean embedded) {
        this.embedded = embedded;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
} 