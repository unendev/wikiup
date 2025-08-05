package com.example.ragservice.dto.request;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * 文档请求DTO
 */
public class DocumentRequest {
    
    @NotBlank(message = "文档标题不能为空")
    private String title;
    
    @NotBlank(message = "文档内容不能为空")
    private String content;
    
    private String source;
    private String path;
    private Map<String, String> metadata;
    
    public DocumentRequest() {
        this.metadata = new HashMap<>();
    }
    
    /**
     * 添加元数据
     */
    public void addMetadata(String key, String value) {
        if (this.metadata == null) {
            this.metadata = new HashMap<>();
        }
        this.metadata.put(key, value);
    }
    
    // Getters and Setters
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
    
    public Map<String, String> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
} 