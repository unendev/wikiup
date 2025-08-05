package com.example.ragservice.event;

import com.example.ragservice.model.Document;

/**
 * 文档创建事件
 * 在文档被创建时触发
 */
public class DocumentCreatedEvent extends DocumentEvent {
    
    public static final String EVENT_TYPE = "DOCUMENT_CREATED";
    
    public DocumentCreatedEvent(Object source, Document document) {
        super(source, document, EVENT_TYPE);
    }
} 