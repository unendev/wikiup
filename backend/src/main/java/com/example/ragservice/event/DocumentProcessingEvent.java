package com.example.ragservice.event;

import com.example.ragservice.model.Document;

/**
 * 文档处理事件
 * 在文档处理过程中触发
 */
public class DocumentProcessingEvent extends DocumentEvent {
    
    public static final String EVENT_TYPE = "DOCUMENT_PROCESSING";
    
    private final float progress;
    private final String stage;
    
    public DocumentProcessingEvent(Object source, Document document, float progress, String stage) {
        super(source, document, EVENT_TYPE);
        this.progress = progress;
        this.stage = stage;
    }
    
    public float getProgress() {
        return progress;
    }
    
    public String getStage() {
        return stage;
    }
} 