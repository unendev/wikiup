package com.example.ragservice.repository;

import com.example.ragservice.model.Chunk;
import com.example.ragservice.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA文本块仓库接口
 */
@Repository
public interface JpaChunkRepository extends JpaRepository<Chunk, Long> {
    
    /**
     * 根据块ID查找
     */
    Optional<Chunk> findByChunkId(String chunkId);
    
    /**
     * 根据文档查找所有块
     */
    List<Chunk> findByDocument(Document document);
    
    /**
     * 根据文档ID查找所有块
     */
    List<Chunk> findByDocumentId(Long documentId);
    
    /**
     * 根据文档和索引查找块
     */
    Optional<Chunk> findByDocumentAndIndex(Document document, int index);
    
    /**
     * 删除文档的所有块
     */
    void deleteByDocument(Document document);
    
    /**
     * 查询已嵌入的块
     */
    List<Chunk> findByEmbedded(boolean embedded);
}

