package com.example.ragservice.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 聊天响应DTO
 */
public class ChatResponse {
    private String answer;
    private List<SourceInfo> sources;
    private long processingTimeMs;
    
    public ChatResponse() {
        this.sources = new ArrayList<>();
    }
    
    public ChatResponse(String answer) {
        this.answer = answer;
        this.sources = new ArrayList<>();
    }
    
    /**
     * 添加来源信息
     */
    public void addSource(String title, String source, String path) {
        addSource(title, source, path, null);
    }
    
    /**
     * 添加来源信息（包含评分）
     */
    public void addSource(String title, String source, String path, Double score) {
        SourceInfo sourceInfo = new SourceInfo();
        sourceInfo.setTitle(title);
        sourceInfo.setSource(source);
        sourceInfo.setPath(path);
        sourceInfo.setScore(score);
        this.sources.add(sourceInfo);
    }
    
    /**
     * 从元数据映射添加来源信息
     */
    public void addSource(Map<String, String> metadata) {
        addSource(metadata, null);
    }
    
    /**
     * 从元数据映射添加来源信息（包含评分）
     */
    public void addSource(Map<String, String> metadata, Double score) {
        if (metadata == null) {
            return;
        }
        
        String title = metadata.getOrDefault("title", "未知标题");
        String source = metadata.getOrDefault("source", "未知来源");
        String path = metadata.getOrDefault("path", "");
        
        addSource(title, source, path, score);
    }
    
    /**
     * 静态构造方法
     */
    public static ChatResponse of(String answer, List<Map<String, String>> sourceMetadataList, long processingTimeMs) {
        ChatResponse response = new ChatResponse(answer);
        response.setProcessingTimeMs(processingTimeMs);
        
        if (sourceMetadataList != null) {
            for (Map<String, String> metadata : sourceMetadataList) {
                response.addSource(metadata);
            }
        }
        
        return response;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public List<SourceInfo> getSources() {
        return sources;
    }
    
    public void setSources(List<SourceInfo> sources) {
        this.sources = sources;
    }
    
    public long getProcessingTimeMs() {
        return processingTimeMs;
    }
    
    public void setProcessingTimeMs(long processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }
    
    /**
     * 源文档信息
     */
    public static class SourceInfo {
        private String title;
        private String source;
        private String path;
        private Double score;  // 相似度评分
        
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
        
        public Double getScore() {
            return score;
        }
        
        public void setScore(Double score) {
            this.score = score;
        }
    }
} 