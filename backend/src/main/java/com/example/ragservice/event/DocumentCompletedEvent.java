package com.example.ragservice.event;

import com.example.ragservice.model.Document;

/**
 * 文档处理完成事件
 * 在文档处理完成时触发
 */
public class DocumentCompletedEvent extends DocumentEvent {
    
    public static final String EVENT_TYPE = "DOCUMENT_COMPLETED";
    
    private final int totalChunks;
    private final boolean success;
    private final String message;
    
    public DocumentCompletedEvent(Object source, Document document, int totalChunks, boolean success, String message) {
        super(source, document, EVENT_TYPE);
        this.totalChunks = totalChunks;
        this.success = success;
        this.message = message;
    }
    
    public int getTotalChunks() {
        return totalChunks;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
} 