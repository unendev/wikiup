package com.example.ragservice.service.impl;

import com.example.ragservice.model.Chunk;
import com.example.ragservice.model.Document;
import com.example.ragservice.repository.JpaChunkRepository;
import com.example.ragservice.repository.JpaDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 基于JPA的文档服务实现示例
 * 
 * 这是一个示例实现，展示如何使用JPA Repository操作MySQL数据库
 * 可以根据实际需求进行修改和扩展
 */
@Service("jpaDocumentService")
@Transactional
public class JpaDocumentServiceImpl {
    
    @Autowired
    private JpaDocumentRepository documentRepository;
    
    @Autowired
    private JpaChunkRepository chunkRepository;
    
    /**
     * 保存文档
     */
    public Document saveDocument(Document document) {
        // 如果没有业务ID，自动生成一个
        if (document.getDocId() == null || document.getDocId().isEmpty()) {
            document.setDocId(UUID.randomUUID().toString());
        }
        
        // 保存文档（会自动生成数据库ID）
        return documentRepository.save(document);
    }
    
    /**
     * 根据业务ID查找文档
     */
    public Optional<Document> findByDocId(String docId) {
        return documentRepository.findByDocId(docId);
    }
    
    /**
     * 根据数据库ID查找文档
     */
    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }
    
    /**
     * 查询所有文档
     */
    public List<Document> findAllDocuments() {
        return documentRepository.findAll();
    }
    
    /**
     * 根据状态查询文档
     */
    public List<Document> findByStatus(Document.DocumentStatus status) {
        return documentRepository.findByStatus(status);
    }
    
    /**
     * 保存文档块
     */
    public Chunk saveChunk(Chunk chunk) {
        // 如果没有业务ID，自动生成一个
        if (chunk.getChunkId() == null || chunk.getChunkId().isEmpty()) {
            chunk.setChunkId(UUID.randomUUID().toString());
        }
        
        return chunkRepository.save(chunk);
    }
    
    /**
     * 批量保存文档块
     */
    public List<Chunk> saveAllChunks(List<Chunk> chunks) {
        // 为没有业务ID的块生成ID
        for (Chunk chunk : chunks) {
            if (chunk.getChunkId() == null || chunk.getChunkId().isEmpty()) {
                chunk.setChunkId(UUID.randomUUID().toString());
            }
        }
        
        return chunkRepository.saveAll(chunks);
    }
    
    /**
     * 获取文档的所有块
     */
    public List<Chunk> getDocumentChunks(Document document) {
        return chunkRepository.findByDocument(document);
    }
    
    /**
     * 删除文档及其所有块
     */
    public void deleteDocument(String docId) {
        Optional<Document> docOpt = documentRepository.findByDocId(docId);
        if (docOpt.isPresent()) {
            Document document = docOpt.get();
            // 由于配置了cascade = CascadeType.ALL，删除文档会自动删除关联的块
            documentRepository.delete(document);
        }
    }
    
    /**
     * 更新文档状态
     */
    public void updateDocumentStatus(String docId, Document.DocumentStatus status) {
        Optional<Document> docOpt = documentRepository.findByDocId(docId);
        if (docOpt.isPresent()) {
            Document document = docOpt.get();
            document.setStatus(status);
            documentRepository.save(document);
        }
    }
    
    /**
     * 搜索文档（根据标题）
     */
    public List<Document> searchByTitle(String keyword) {
        return documentRepository.findByTitleContaining(keyword);
    }
    
    /**
     * 统计文档数量
     */
    public long countDocuments() {
        return documentRepository.count();
    }
    
    /**
     * 统计块数量
     */
    public long countChunks() {
        return chunkRepository.count();
    }
}

