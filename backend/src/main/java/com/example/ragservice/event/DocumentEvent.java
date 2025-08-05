package com.example.ragservice.event;

import com.example.ragservice.model.Document;
import org.springframework.context.ApplicationEvent;

/**
 * 文档事件基类
 * 作为所有文档相关事件的父类
 */
public abstract class DocumentEvent extends ApplicationEvent {
    
    private final Document document;
    private final String eventType;
    
    public DocumentEvent(Object source, Document document, String eventType) {
        super(source);
        this.document = document;
        this.eventType = eventType;
    }
    
    public Document getDocument() {
        return document;
    }
    
    public String getEventType() {
        return eventType;
    }
} 