package com.example.ragservice.event;

import com.example.ragservice.model.Document;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 文档事件测试类
 */
@SpringBootTest
@TestPropertySource(properties = {
    "spring.main.allow-bean-definition-overriding=true"
})
@ActiveProfiles("test")
public class DocumentEventTest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @MockBean
    private DocumentEventListener eventListener;
    
    @Captor
    private ArgumentCaptor<DocumentProcessingEvent> processingEventCaptor;
    
    @Test
    public void testDocumentCreatedEvent() {
        // 创建测试文档
        Document document = new Document();
        document.setId("test-doc-1");
        document.setTitle("测试文档");
        
        // 发布文档创建事件
        eventPublisher.publishEvent(new DocumentCreatedEvent(this, document));
        
        // 验证监听器接收到事件
        verify(eventListener).handleDocumentCreatedEvent(any(DocumentCreatedEvent.class));
    }
    
    @Test
    public void testDocumentProcessingEvent() {
        // 创建测试文档
        Document document = new Document();
        document.setId("test-doc-2");
        document.setTitle("测试文档");
        
        // 发布文档处理事件
        eventPublisher.publishEvent(new DocumentProcessingEvent(this, document, 50.0f, "CHUNKING"));
        
        // 验证监听器接收到事件
        verify(eventListener).handleDocumentProcessingEvent(any(DocumentProcessingEvent.class));
    }
    
    @Test
    public void testDocumentCompletedEvent() {
        // 创建测试文档
        Document document = new Document();
        document.setId("test-doc-3");
        document.setTitle("测试文档");
        
        // 发布文档完成事件
        eventPublisher.publishEvent(new DocumentCompletedEvent(this, document, 5, true, "处理成功"));
        
        // 验证监听器接收到事件
        verify(eventListener).handleDocumentCompletedEvent(any(DocumentCompletedEvent.class));
    }
    
    @Test
    public void testDocumentErrorEvent() {
        // 创建测试文档
        Document document = new Document();
        document.setId("test-doc-4");
        document.setTitle("测试文档");
        
        // 发布文档错误事件
        Exception testException = new RuntimeException("测试异常");
        eventPublisher.publishEvent(new DocumentErrorEvent(this, document, "E001", "处理失败", testException));
        
        // 验证监听器接收到事件
        verify(eventListener).handleDocumentErrorEvent(any(DocumentErrorEvent.class));
    }
    
    @Test
    public void testEventProperties() {
        // 创建测试文档
        Document document = new Document();
        document.setId("test-doc-5");
        document.setTitle("测试文档");
        
        // 发布文档处理事件
        DocumentProcessingEvent event = new DocumentProcessingEvent(this, document, 75.0f, "EMBEDDING");
        eventPublisher.publishEvent(event);
        
        // 捕获事件并验证属性
        verify(eventListener).handleDocumentProcessingEvent(processingEventCaptor.capture());
        DocumentProcessingEvent capturedEvent = processingEventCaptor.getValue();
        
        assertEquals(document, capturedEvent.getDocument());
        assertEquals("EMBEDDING", capturedEvent.getStage());
        assertEquals(75.0f, capturedEvent.getProgress());
        assertNotNull(capturedEvent.getTimestamp());
    }
} 