package com.example.ragservice.repository;

import com.example.ragservice.model.Document;

import java.util.List;
import java.util.Optional;

/**
 * 文档仓库接口
 */
public interface DocumentRepository {
    
    /**
     * 保存文档
     * @param document 文档对象
     * @return 保存后的文档（含ID）
     */
    Document save(Document document);
    
    /**
     * 根据ID查找文档
     * @param id 文档ID
     * @return 文档对象（可选）
     */
    Optional<Document> findById(String id);
    
    /**
     * 查询所有文档
     * @return 文档列表
     */
    List<Document> findAll();
    
    /**
     * 根据来源查询文档
     * @param source 来源
     * @return 文档列表
     */
    List<Document> findBySource(String source);
    
    /**
     * 查询指定状态的文档
     * @param status 文档状态
     * @return 文档列表
     */
    List<Document> findByStatus(Document.DocumentStatus status);
    
    /**
     * 删除文档
     * @param id 文档ID
     */
    void deleteById(String id);
    
    /**
     * 判断文档是否存在
     * @param id 文档ID
     * @return 是否存在
     */
    boolean existsById(String id);
    
    /**
     * 获取文档总数
     * @return 文档数量
     */
    long count();
} 