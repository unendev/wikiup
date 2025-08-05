package com.example.ragservice.service.chunking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分块策略工厂类
 * 管理和提供不同的文本分块策略
 */
@Component
public class ChunkerFactory {
    private static final Logger logger = LoggerFactory.getLogger(ChunkerFactory.class);
    
    private final Map<String, TextChunker> chunkers = new HashMap<>();
    
    @Value("${text.chunker.default:fixed_size}")
    private String defaultChunkerId;
    
    @Autowired
    private FixedSizeChunker fixedSizeChunker;
    
    @Autowired
    private SemanticChunker semanticChunker;
    
    @Autowired
    private SlidingWindowChunker slidingWindowChunker;
    
    @PostConstruct
    public void init() {
        // 注册所有分块策略
        registerChunker(fixedSizeChunker);
        registerChunker(semanticChunker);
        registerChunker(slidingWindowChunker);
        
        logger.info("Initialized ChunkerFactory with {} chunkers", chunkers.size());
        logger.info("Default chunker: {}", getDefaultChunker().getName());
    }
    
    /**
     * 注册分块策略
     * @param chunker 分块策略
     */
    public void registerChunker(TextChunker chunker) {
        chunkers.put(chunker.getId(), chunker);
        logger.debug("Registered chunker: {}", chunker.getName());
    }
    
    /**
     * 根据ID获取分块策略
     * @param id 分块策略ID
     * @return 分块策略
     */
    public TextChunker getChunker(String id) {
        return chunkers.getOrDefault(id, getDefaultChunker());
    }
    
    /**
     * 获取默认分块策略
     * @return 默认分块策略
     */
    public TextChunker getDefaultChunker() {
        TextChunker defaultChunker = chunkers.get(defaultChunkerId);
        if (defaultChunker == null) {
            defaultChunker = fixedSizeChunker;
        }
        return defaultChunker;
    }
    
    /**
     * 获取所有可用的分块策略
     * @return 分块策略映射
     */
    public Map<String, TextChunker> getAllChunkers() {
        return new HashMap<>(chunkers);
    }
    
    /**
     * 获取所有可用的分块策略ID列表
     * @return 分块策略ID列表
     */
    public List<String> getAvailableChunkerIds() {
        return new ArrayList<>(chunkers.keySet());
    }
} 