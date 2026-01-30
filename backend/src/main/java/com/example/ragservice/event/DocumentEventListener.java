package com.example.ragservice.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 文档事件监听器
 * 监听和处理文档相关的所有事件
 */
@Component
public class DocumentEventListener {
    private static final Logger logger = LoggerFactory.getLogger(DocumentEventListener.class);
    
    // 使用ConcurrentHashMap存储每个文档的处理进度
    private final Map<String, DocumentProcessStatus> documentProcessStatusMap = new ConcurrentHashMap<>();
    
    @EventListener
    public void handleDocumentCreatedEvent(DocumentCreatedEvent event) {
        String documentId = event.getDocument().getDocId();
        String documentTitle = event.getDocument().getTitle();
        
        logger.info("Document created: {} ({})", documentTitle, documentId);
        
        // 初始化文档处理状态
        DocumentProcessStatus status = new DocumentProcessStatus();
        status.setStage("created");
        status.setProgress(0.0f);
        status.setSuccess(true);
        status.setMessage("Document created successfully");
        
        documentProcessStatusMap.put(documentId, status);
    }
    
    @EventListener
    public void handleDocumentProcessingEvent(DocumentProcessingEvent event) {
        String documentId = event.getDocument().getDocId();
        String documentTitle = event.getDocument().getTitle();
        String stage = event.getStage();
        float progress = event.getProgress();
        
        logger.info("Document processing: {} ({}), stage: {}, progress: {}%", 
                documentTitle, documentId, stage, Math.round(progress * 100));
        
        // 更新文档处理状态
        documentProcessStatusMap.computeIfAbsent(documentId, id -> new DocumentProcessStatus())
                .setStage(stage)
                .setProgress(progress);
    }
    
    @EventListener
    public void handleDocumentCompletedEvent(DocumentCompletedEvent event) {
        String documentId = event.getDocument().getDocId();
        String documentTitle = event.getDocument().getTitle();
        boolean success = event.isSuccess();
        String message = event.getMessage();
        
        logger.info("Document processing completed: {} ({}), success: {}, message: {}", 
                documentTitle, documentId, success, message);
        
        // 更新文档处理状态
        documentProcessStatusMap.computeIfAbsent(documentId, id -> new DocumentProcessStatus())
                .setStage("completed")
                .setProgress(1.0f)
                .setSuccess(success)
                .setMessage(message);
    }
    
    @EventListener
    public void handleDocumentErrorEvent(DocumentErrorEvent event) {
        String documentId = event.getDocument().getDocId();
        String documentTitle = event.getDocument().getTitle();
        String errorCode = event.getErrorCode();
        String errorMessage = event.getErrorMessage();
        Throwable error = event.getError();
        
        logger.error("Document processing error: {} ({}), code: {}, message: {}", 
                documentTitle, documentId, errorCode, errorMessage, error);
        
        // 更新文档处理状态
        documentProcessStatusMap.computeIfAbsent(documentId, id -> new DocumentProcessStatus())
                .setStage("error")
                .setSuccess(false)
                .setMessage(errorMessage);
    }
    
    /**
     * 获取文档处理状态
     * @param documentId 文档ID
     * @return 处理状态
     */
    public DocumentProcessStatus getDocumentProcessStatus(String documentId) {
        return documentProcessStatusMap.getOrDefault(documentId, new DocumentProcessStatus());
    }
    
    /**
     * 清除文档处理状态
     * @param documentId 文档ID
     */
    public void clearDocumentProcessStatus(String documentId) {
        documentProcessStatusMap.remove(documentId);
    }
    
    /**
     * 文档处理状态类
     */
    public static class DocumentProcessStatus {
        private final AtomicReference<String> stage = new AtomicReference<>("created");
        private volatile float progress = 0.0f;
        private volatile boolean success = true;
        private volatile String message = "";
        
        public String getStage() {
            return stage.get();
        }
        
        public DocumentProcessStatus setStage(String stage) {
            this.stage.set(stage);
            return this;
        }
        
        public float getProgress() {
            return progress;
        }
        
        public DocumentProcessStatus setProgress(float progress) {
            this.progress = progress;
            return this;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        public DocumentProcessStatus setSuccess(boolean success) {
            this.success = success;
            return this;
        }
        
        public String getMessage() {
            return message;
        }
        
        public DocumentProcessStatus setMessage(String message) {
            this.message = message;
            return this;
        }
    }
} 