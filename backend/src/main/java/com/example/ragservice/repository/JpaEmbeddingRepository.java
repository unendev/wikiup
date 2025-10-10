package com.example.ragservice.repository;

import com.example.ragservice.model.Embedding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA嵌入向量仓库接口
 */
@Repository
public interface JpaEmbeddingRepository extends JpaRepository<Embedding, Long> {
    
    /**
     * 根据对象ID和类型查找嵌入向量
     */
    Optional<Embedding> findByObjectIdAndObjectType(String objectId, String objectType);
    
    /**
     * 根据对象类型查找所有嵌入向量
     */
    List<Embedding> findByObjectType(String objectType);
    
    /**
     * 根据对象ID查找嵌入向量
     */
    List<Embedding> findByObjectId(String objectId);
    
    /**
     * 根据模型名称查找嵌入向量
     */
    List<Embedding> findByModel(String model);
    
    /**
     * 删除指定对象的嵌入向量
     */
    void deleteByObjectIdAndObjectType(String objectId, String objectType);
}

