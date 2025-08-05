package com.example.ragservice.service.chunking;

import com.example.ragservice.model.Chunk;
import com.example.ragservice.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 固定大小文本分块策略
 * 按照固定字符数对文本进行分块
 */
@Component
public class FixedSizeChunker implements TextChunker {
    private static final Logger logger = LoggerFactory.getLogger(FixedSizeChunker.class);
    
    private final int chunkSize;
    private final int overlapSize;
    
    public FixedSizeChunker(
            @Value("${text.chunk.size:500}") int chunkSize,
            @Value("${text.chunk.overlap:100}") int overlapSize) {
        this.chunkSize = chunkSize;
        this.overlapSize = overlapSize;
        logger.info("Initialized FixedSizeChunker with size={}, overlap={}", chunkSize, overlapSize);
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
        
        // 如果文本小于分块大小，直接返回一个块
        if (textLength <= chunkSize) {
            Chunk singleChunk = Chunk.of(documentId, text, 0);
            chunks.add(singleChunk);
            return chunks;
        }
        
        // 分块逻辑
        int start = 0;
        int chunkIndex = 0;
        
        while (start < textLength) {
            int end = Math.min(start + chunkSize, textLength);
            
            // 如果不是最后一个块且未到达文本结尾，尝试在自然边界处分割
            if (end < textLength) {
                // 寻找段落分隔符、句号、问号、感叹号等自然边界
                int boundary = findNaturalBoundary(text, end - 30, end + 30);
                if (boundary > end - 30 && boundary < end + 30) {
                    end = boundary;
                }
            }
            
            String chunkText = text.substring(start, end);
            Chunk chunk = Chunk.of(documentId, chunkText, chunkIndex);
            chunk.addMetadata("start_char", String.valueOf(start));
            chunk.addMetadata("end_char", String.valueOf(end));
            
            chunks.add(chunk);
            chunkIndex++;
            
            // 计算下一个块的起始位置，考虑重叠
            start = end - overlapSize;
            
            // 确保起始位置有效
            if (start < 0) {
                start = 0;
            }
            
            // 避免零进展
            if (start >= end) {
                break;
            }
        }
        
        logger.debug("Split text of length {} into {} chunks", textLength, chunks.size());
        return chunks;
    }
    
    /**
     * 寻找自然文本边界
     * 优先寻找段落分隔符，其次是句号、问号、感叹号等
     */
    private int findNaturalBoundary(String text, int startPos, int endPos) {
        int textLength = text.length();
        startPos = Math.max(0, startPos);
        endPos = Math.min(textLength, endPos);
        
        // 寻找段落边界
        for (int i = startPos; i < endPos; i++) {
            char c = text.charAt(i);
            if (c == '\n' || c == '\r') {
                return i + 1; // 包括换行符
            }
        }
        
        // 寻找句子边界
        for (int i = startPos; i < endPos; i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '。' || c == '?' || c == '？' || c == '!' || c == '！') {
                // 如果后面还有字符，包括这个字符和后面的空格
                if (i + 1 < textLength && Character.isWhitespace(text.charAt(i + 1))) {
                    return i + 2;
                }
                return i + 1;
            }
        }
        
        // 如果没找到自然边界，寻找空格
        for (int i = endPos - 1; i >= startPos; i--) {
            if (Character.isWhitespace(text.charAt(i))) {
                return i + 1;
            }
        }
        
        // 如果还是没找到，直接返回中间点
        return (startPos + endPos) / 2;
    }
    
    @Override
    public String getName() {
        return "固定大小分块";
    }
    
    @Override
    public String getId() {
        return "fixed_size";
    }
    
    @Override
    public int getChunkSize() {
        return chunkSize;
    }
    
    @Override
    public int getOverlapSize() {
        return overlapSize;
    }
} 