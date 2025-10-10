package com.example.ragservice.repository;

import com.example.ragservice.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA文档仓库接口
 */
@Repository
public interface JpaDocumentRepository extends JpaRepository<Document, Long> {
    
    /**
     * 根据文档ID查找
     */
    Optional<Document> findByDocId(String docId);
    
    /**
     * 根据来源查询文档
     */
    List<Document> findBySource(String source);
    
    /**
     * 查询指定状态的文档
     */
    List<Document> findByStatus(Document.DocumentStatus status);
    
    /**
     * 根据文档ID判断是否存在
     */
    boolean existsByDocId(String docId);
    
    /**
     * 根据文档ID删除
     */
    void deleteByDocId(String docId);
    
    /**
     * 根据标题模糊查询
     */
    List<Document> findByTitleContaining(String keyword);
    
    /**
     * 根据路径查询
     */
    Optional<Document> findByPath(String path);
}

