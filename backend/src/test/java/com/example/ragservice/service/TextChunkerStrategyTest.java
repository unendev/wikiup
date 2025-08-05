package com.example.ragservice.service;

import com.example.ragservice.model.Chunk;
import com.example.ragservice.service.chunking.ChunkerFactory;
import com.example.ragservice.service.chunking.FixedSizeChunker;
import com.example.ragservice.service.chunking.SemanticChunker;
import com.example.ragservice.service.chunking.SlidingWindowChunker;
import com.example.ragservice.service.chunking.TextChunker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 文本分块策略测试类
 */
@SpringBootTest
@TestPropertySource(properties = {
    "spring.main.allow-bean-definition-overriding=true"
})
@ActiveProfiles("test")
public class TextChunkerStrategyTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public String testBean() {
            return "testBean";
        }
    }

    @Autowired
    private ChunkerFactory chunkerFactory;

    private final String testDocument = 
        "# 威尔逊\n\n" +
        "威尔逊是《饥荒》中的第一个可游玩角色，也是游戏的代言人。\n\n" +
        "## 背景\n\n" +
        "威尔逊·帕西瓦尔·海格斯贝瑞三世是一位失败的科学家。在一次实验失败后，神秘的声音（麦斯威尔）通过收音机向他提供了科学知识。\n\n" +
        "## 特点\n\n" +
        "1. 可以制作科学机器\n" +
        "2. 可以生长胡须，胡须可以用来保暖或者制作肉假人\n" +
        "3. 初始没有特殊能力\n\n" +
        "## 策略\n\n" +
        "威尔逊适合新手玩家，因为他没有特殊的优势或劣势。玩家可以专注于学习游戏的基本机制。\n\n" +
        "在冬季，威尔逊的胡须可以提供保暖效果，这是他的一个小优势。";

    @Test
    public void testFixedSizeChunker() {
        // 获取固定大小分块器
        TextChunker chunker = chunkerFactory.getChunker("fixed-size");
        
        // 验证是否为正确的分块器类型
        assertTrue(chunker instanceof FixedSizeChunker);
        
        // 使用分块器处理文档
        String documentId = UUID.randomUUID().toString();
        List<Chunk> chunks = chunker.chunk(testDocument, documentId);
        
        // 验证分块结果
        assertFalse(chunks.isEmpty());
        
        // 验证分块大小不超过配置的最大值
        for (Chunk chunk : chunks) {
            assertTrue(chunk.getText().length() <= 500);
        }
        
        // 验证分块的总字符数与原文档相近（考虑到重叠部分）
        int totalLength = chunks.stream().mapToInt(c -> c.getText().length()).sum();
        int expectedMinLength = testDocument.length(); // 至少包含原文档的所有字符
        assertTrue(totalLength >= expectedMinLength);
    }
    
    @Test
    public void testSemanticChunker() {
        // 获取语义分块器
        TextChunker chunker = chunkerFactory.getChunker("semantic");
        
        // 验证是否为正确的分块器类型
        assertTrue(chunker instanceof SemanticChunker);
        
        // 使用分块器处理文档
        String documentId = UUID.randomUUID().toString();
        List<Chunk> chunks = chunker.chunk(testDocument, documentId);
        
        // 验证分块结果
        assertFalse(chunks.isEmpty());
        
        // 验证分块大小不超过配置的最大值
        for (Chunk chunk : chunks) {
            assertTrue(chunk.getText().length() <= 500);
        }
        
        // 验证分块是否尊重语义边界（如段落、标题）
        // 检查第一个分块是否包含标题
        assertTrue(chunks.get(0).getText().contains("# 威尔逊"));
    }
    
    @Test
    public void testGetAllChunkers() {
        // 获取所有可用的分块器
        Map<String, TextChunker> chunkers = chunkerFactory.getAllChunkers();
        
        // 验证至少有三种分块器
        assertTrue(chunkers.size() >= 3);
        
        // 验证包含三种基本分块器类型
        boolean hasFixedSize = false;
        boolean hasSemantic = false;
        boolean hasSlidingWindow = false;
        
        for (TextChunker chunker : chunkers.values()) {
            if (chunker instanceof FixedSizeChunker) hasFixedSize = true;
            if (chunker instanceof SemanticChunker) hasSemantic = true;
            if (chunker instanceof SlidingWindowChunker) hasSlidingWindow = true;
        }
        
        assertTrue(hasFixedSize);
        assertTrue(hasSemantic);
        assertTrue(hasSlidingWindow);
    }
    
    @Test
    public void testChunkPreservesImportantContent() {
        // 使用默认分块器
        TextChunker chunker = chunkerFactory.getDefaultChunker();
        
        // 使用分块器处理文档
        String documentId = UUID.randomUUID().toString();
        List<Chunk> chunks = chunker.chunk(testDocument, documentId);
        
        // 验证分块结果
        assertFalse(chunks.isEmpty());
        
        // 验证重要内容被保留在某个分块中
        boolean containsTitle = false;
        boolean containsBackground = false;
        boolean containsFeatures = false;
        
        for (Chunk chunk : chunks) {
            if (chunk.getText().contains("威尔逊")) containsTitle = true;
            if (chunk.getText().contains("失败的科学家")) containsBackground = true;
            if (chunk.getText().contains("生长胡须")) containsFeatures = true;
        }
        
        assertTrue(containsTitle);
        assertTrue(containsBackground);
        assertTrue(containsFeatures);
        
        // 验证所有分块合并后包含原文档的所有内容
        String allChunksText = chunks.stream()
            .map(Chunk::getText)
            .collect(Collectors.joining(" "));
            
        // 检查原文档中的关键词是否都在合并后的文本中
        assertTrue(allChunksText.contains("威尔逊"));
        assertTrue(allChunksText.contains("帕西瓦尔"));
        assertTrue(allChunksText.contains("科学家"));
        assertTrue(allChunksText.contains("胡须"));
        assertTrue(allChunksText.contains("保暖"));
        assertTrue(allChunksText.contains("肉假人"));
    }
} 