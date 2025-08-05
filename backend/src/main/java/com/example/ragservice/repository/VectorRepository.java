package com.example.ragservice.repository;

import com.example.ragservice.model.Embedding;

import java.util.List;
import java.util.Optional;

/**
 * 向量仓库接口
 */
public interface VectorRepository {
    
    /**
     * 保存嵌入向量
     * @param embedding 嵌入向量对象
     * @return 保存后的嵌入向量（含ID）
     */
    Embedding save(Embedding embedding);
    
    /**
     * 批量保存嵌入向量
     * @param embeddings 嵌入向量列表
     * @return 保存后的嵌入向量列表
     */
    List<Embedding> saveAll(List<Embedding> embeddings);
    
    /**
     * 根据ID查找嵌入向量
     * @param id 向量ID
     * @return 嵌入向量对象（可选）
     */
    Optional<Embedding> findById(String id);
    
    /**
     * 根据关联对象ID查找嵌入向量
     * @param objectId 关联对象ID
     * @param objectType 对象类型
     * @return 嵌入向量对象（可选）
     */
    Optional<Embedding> findByObjectId(String objectId, String objectType);
    
    /**
     * 执行向量相似度搜索
     * @param queryVector 查询向量
     * @param limit 返回数量
     * @param threshold 相似度阈值
     * @return 相似度最高的向量列表
     */
    List<SearchResult> search(float[] queryVector, int limit, double threshold);
    
    /**
     * 删除嵌入向量
     * @param id 向量ID
     */
    void deleteById(String id);
    
    /**
     * 删除关联对象的所有向量
     * @param objectId 关联对象ID
     * @param objectType 对象类型
     */
    void deleteByObjectId(String objectId, String objectType);
    
    /**
     * 判断向量是否存在
     * @param id 向量ID
     * @return 是否存在
     */
    boolean existsById(String id);
    
    /**
     * 获取向量总数
     * @return 向量数量
     */
    long count();
    
    /**
     * 搜索结果类
     */
    class SearchResult {
        private final Embedding embedding;
        private final double score;
        
        public SearchResult(Embedding embedding, double score) {
            this.embedding = embedding;
            this.score = score;
        }
        
        public Embedding getEmbedding() {
            return embedding;
        }
        
        public double getScore() {
            return score;
        }
        
        public String getObjectId() {
            return embedding.getObjectId();
        }
        
        public String getObjectType() {
            return embedding.getObjectType();
        }
    }
} 