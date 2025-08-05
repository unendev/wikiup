package com.example.ragservice.service.chunking;

import com.example.ragservice.model.Chunk;
import com.example.ragservice.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 滑动窗口分块策略
 * 使用固定步长滑动窗口进行文本分块
 */
@Component
public class SlidingWindowChunker implements TextChunker {
    private static final Logger logger = LoggerFactory.getLogger(SlidingWindowChunker.class);
    
    private final int windowSize;
    private final int stepSize;
    private final int overlapSize;
    
    public SlidingWindowChunker(
            @Value("${text.chunk.size:500}") int windowSize,
            @Value("${text.chunk.step:300}") int stepSize,
            @Value("${text.chunk.overlap:0}") int overlapSize) {
        this.windowSize = windowSize;
        this.stepSize = stepSize > 0 ? stepSize : windowSize / 2;
        this.overlapSize = overlapSize;
        logger.info("Initialized SlidingWindowChunker with windowSize={}, stepSize={}, overlap={}", 
                windowSize, this.stepSize, overlapSize);
    }
    
    @Override
    public List<Chunk> chunk(Document document) {
        if (document == null || document.getContent() == null) {
            return new ArrayList<>();
        }
        
        List<Chunk> chunks = chunk(document.getContent(), document.getId());
        
        // 从文档继承元数据
        for (Chunk chunk : chunks) {
            chunk.inheritMetadataFrom(document);
        }
        
        return chunks;
    }
    
    @Override
    public List<Chunk> chunk(String text, String documentId) {
        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Chunk> chunks = new ArrayList<>();
        int textLength = text.length();
        
        // 如果文本小于窗口大小，直接返回一个块
        if (textLength <= windowSize) {
            Chunk singleChunk = Chunk.of(documentId, text, 0);
            chunks.add(singleChunk);
            return chunks;
        }
        
        // 滑动窗口分块
        int start = 0;
        int chunkIndex = 0;
        
        while (start < textLength) {
            int end = Math.min(start + windowSize, textLength);
            
            String chunkText = text.substring(start, end);
            Chunk chunk = Chunk.of(documentId, chunkText, chunkIndex);
            chunk.addMetadata("start_char", String.valueOf(start));
            chunk.addMetadata("end_char", String.valueOf(end));
            
            chunks.add(chunk);
            chunkIndex++;
            
            // 移动窗口
            start += stepSize;
            
            // 如果指定了重叠大小，确保至少有这么多重叠
            if (overlapSize > 0 && stepSize > windowSize - overlapSize) {
                start = end - overlapSize;
            }
            
            // 避免零进展
            if (start >= textLength) {
                break;
            }
        }
        
        logger.debug("Split text of length {} into {} chunks using sliding window", textLength, chunks.size());
        return chunks;
    }
    
    @Override
    public String getName() {
        return "滑动窗口分块";
    }
    
    @Override
    public String getId() {
        return "sliding_window";
    }
    
    @Override
    public int getChunkSize() {
        return windowSize;
    }
    
    @Override
    public int getOverlapSize() {
        return overlapSize;
    }
} 