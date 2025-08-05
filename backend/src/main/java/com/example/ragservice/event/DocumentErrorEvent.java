package com.example.ragservice.event;

import com.example.ragservice.model.Document;

/**
 * 文档处理错误事件
 * 在文档处理出错时触发
 */
public class DocumentErrorEvent extends DocumentEvent {
    
    public static final String EVENT_TYPE = "DOCUMENT_ERROR";
    
    private final String errorCode;
    private final String errorMessage;
    private final Throwable error;
    
    public DocumentErrorEvent(Object source, Document document, String errorCode, String errorMessage, Throwable error) {
        super(source, document, EVENT_TYPE);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.error = error;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public Throwable getError() {
        return error;
    }
} 