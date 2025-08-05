package com.example.ragservice.service.chunking;

import com.example.ragservice.model.Chunk;
import com.example.ragservice.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 语义分块策略
 * 根据文档语义结构（标题、段落等）进行智能分块
 */
@Component
public class SemanticChunker implements TextChunker {
    private static final Logger logger = LoggerFactory.getLogger(SemanticChunker.class);
    
    // 标题正则表达式
    private static final Pattern HEADER_PATTERN = 
        Pattern.compile("^(#+\\s+.+|\\d+\\.\\s+.+|第[一二三四五六七八九十]+[章节]|\\d+\\s*[章节])$", Pattern.MULTILINE);
    
    // 分隔符正则表达式
    private static final Pattern SEPARATOR_PATTERN = 
        Pattern.compile("^[-=*]{3,}$", Pattern.MULTILINE);
    
    private final int maxChunkSize;
    private final int overlapSize;
    
    public SemanticChunker(
            @Value("${text.chunk.size:500}") int maxChunkSize,
            @Value("${text.chunk.overlap:100}") int overlapSize) {
        this.maxChunkSize = maxChunkSize;
        this.overlapSize = overlapSize;
        logger.info("Initialized SemanticChunker with maxSize={}, overlap={}", maxChunkSize, overlapSize);
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
        
        // 首先，按章节、标题等语义边界分块
        List<TextSection> sections = extractSections(text);
        
        logger.debug("Extracted {} semantic sections", sections.size());
        
        // 对每个语义块进行进一步处理
        int chunkIndex = 0;
        for (TextSection section : sections) {
            String sectionText = section.text;
            String sectionType = section.type;
            
            // 如果语义块小于最大块大小，直接作为一个块
            if (sectionText.length() <= maxChunkSize) {
                Chunk chunk = Chunk.of(documentId, sectionText, chunkIndex++);
                chunk.addMetadata("section_type", sectionType);
                chunk.addMetadata("section_title", section.title);
                chunks.add(chunk);
            } else {
                // 否则，使用固定大小分块策略进一步分块
                FixedSizeChunker fixedChunker = new FixedSizeChunker(maxChunkSize, overlapSize);
                List<Chunk> subChunks = fixedChunker.chunk(sectionText, documentId);
                
                // 为所有子块添加语义信息
                for (Chunk subChunk : subChunks) {
                    subChunk.addMetadata("section_type", sectionType);
                    subChunk.addMetadata("section_title", section.title);
                    subChunk.setIndex(chunkIndex++);
                }
                
                chunks.addAll(subChunks);
            }
        }
        
        logger.debug("Split text into {} chunks using semantic chunking", chunks.size());
        return chunks;
    }
    
    /**
     * 从文本提取语义部分
     */
    private List<TextSection> extractSections(String text) {
        List<TextSection> sections = new ArrayList<>();
        String[] lines = text.split("\n");
        
        StringBuilder currentSection = new StringBuilder();
        String currentType = "text";
        String currentTitle = "";
        
        for (String line : lines) {
            // 检查是否是标题行
            if (HEADER_PATTERN.matcher(line).matches()) {
                // 保存当前部分（如果有）
                if (currentSection.length() > 0) {
                    sections.add(new TextSection(currentSection.toString(), currentType, currentTitle));
                    currentSection = new StringBuilder();
                }
                
                // 开始新的标题部分
                currentType = "header";
                currentTitle = line.replaceAll("^#+\\s+", "").trim();
                currentSection.append(line).append("\n");
            } 
            // 检查是否是分隔符
            else if (SEPARATOR_PATTERN.matcher(line).matches()) {
                // 保存当前部分（如果有）
                if (currentSection.length() > 0) {
                    sections.add(new TextSection(currentSection.toString(), currentType, currentTitle));
                    currentSection = new StringBuilder();
                }
                
                // 分隔符作为一个单独部分
                sections.add(new TextSection(line, "separator", ""));
                
                // 重置当前部分
                currentType = "text";
                currentTitle = "";
            }
            // 普通文本行
            else {
                currentSection.append(line).append("\n");
            }
        }
        
        // 添加最后一部分（如果有）
        if (currentSection.length() > 0) {
            sections.add(new TextSection(currentSection.toString(), currentType, currentTitle));
        }
        
        return sections;
    }
    
    /**
     * 语义文本部分
     */
    private static class TextSection {
        String text;
        String type;
        String title;
        
        TextSection(String text, String type, String title) {
            this.text = text;
            this.type = type;
            this.title = title;
        }
    }
    
    @Override
    public String getName() {
        return "语义分块";
    }
    
    @Override
    public String getId() {
        return "semantic";
    }
    
    @Override
    public int getChunkSize() {
        return maxChunkSize;
    }
    
    @Override
    public int getOverlapSize() {
        return overlapSize;
    }
} 