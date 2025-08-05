package com.example.ragservice.service.chunking;

import com.example.ragservice.model.Chunk;
import com.example.ragservice.model.Document;

import java.util.List;

/**
 * 文本分块策略接口
 * 负责将文档内容分割成较小的文本块
 */
public interface TextChunker {
    
    /**
     * 对文档进行分块
     * @param document 文档对象
     * @return 文本块列表
     */
    List<Chunk> chunk(Document document);
    
    /**
     * 对文本进行分块
     * @param text 待分块文本
     * @param documentId 关联的文档ID
     * @return 文本块列表
     */
    List<Chunk> chunk(String text, String documentId);
    
    /**
     * 获取分块策略名称
     * @return 策略名称
     */
    String getName();
    
    /**
     * 获取分块策略ID
     * @return 策略ID
     */
    String getId();
    
    /**
     * 获取分块大小
     * @return 分块大小（字符数）
     */
    int getChunkSize();
    
    /**
     * 获取分块重叠大小
     * @return 重叠大小（字符数）
     */
    int getOverlapSize();
} 