package com.example.ragservice.service;

import com.example.ragservice.model.Document;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * 文档服务接口
 */
public interface DocumentService {
    
    /**
     * 创建文档
     * @param document 文档对象
     * @return 保存的文档
     */
    Document createDocument(Document document);
    
    /**
     * 异步处理文档（分块并生成嵌入向量）
     * @param documentId 文档ID
     * @return 异步任务
     */
    CompletableFuture<Document> processDocumentAsync(String documentId);
    
    /**
     * 根据ID获取文档
     * @param id 文档ID
     * @return 文档对象（可选）
     */
    Optional<Document> getDocumentById(String id);
    
    /**
     * 获取所有文档
     * @return 文档列表
     */
    List<Document> getAllDocuments();
    
    /**
     * 根据来源获取文档
     * @param source 来源
     * @return 文档列表
     */
    List<Document> getDocumentsBySource(String source);
    
    /**
     * 根据状态获取文档
     * @param status 文档状态
     * @return 文档列表
     */
    List<Document> getDocumentsByStatus(Document.DocumentStatus status);
    
    /**
     * 更新文档
     * @param document 文档对象
     * @return 更新后的文档
     */
    Document updateDocument(Document document);
    
    /**
     * 删除文档
     * @param id 文档ID
     */
    void deleteDocument(String id);
    
    /**
     * 重新处理所有文档
     * @return 处理的文档数量
     */
    CompletableFuture<Integer> reprocessAllDocuments();
} 