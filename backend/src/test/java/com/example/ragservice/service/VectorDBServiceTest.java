package com.example.ragservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import ai.djl.translate.TranslateException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class VectorDBServiceTest {

    @Mock
    private EmbeddingService embeddingService;

    @InjectMocks
    private VectorDBService vectorDBService;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        
        // 配置临时目录
        String tempDirPath = tempDir.toString();
        String vectorDbFilePath = tempDir.resolve("vector_db.dat").toString();
        
        // 设置相关属性
        ReflectionTestUtils.setField(vectorDBService, "knowledgeBasePath", tempDirPath);
        ReflectionTestUtils.setField(vectorDBService, "vectorDbFile", vectorDbFilePath);
        ReflectionTestUtils.setField(vectorDBService, "similarityThreshold", 0.2);
        ReflectionTestUtils.setField(vectorDBService, "chunkSize", 500);
        ReflectionTestUtils.setField(vectorDBService, "chunkOverlap", 100);
        ReflectionTestUtils.setField(vectorDBService, "useVectorDbDisk", true);
        
        // 模拟嵌入向量
        when(embeddingService.embed(anyString())).thenReturn(new float[]{0.1f, 0.2f, 0.3f});
    }

    @Test
    void testProcessFile() throws IOException, TranslateException {
        // 创建测试文件
        Path testFilePath = tempDir.resolve("test.md");
        String content = "# 测试文档\n\n## 第一节\n\n这是第一节的内容。\n\n## 第二节\n\n这是第二节的内容。\n\n";
        Files.write(testFilePath, content.getBytes(StandardCharsets.UTF_8));
        
        // 处理文件
        vectorDBService.loadDocuments();
        
        // 验证向量数量
        assertTrue(vectorDBService.getVectorCount() > 0, "应该生成至少一个向量");
        
        // 验证是否调用了嵌入服务
        verify(embeddingService, atLeast(1)).embed(anyString());
    }
    
    @Test
    void testVectorSerializationDirectly() throws IOException, ClassNotFoundException {
        // 创建向量条目
        List<VectorDBService.VectorEntry> vectors = new ArrayList<>();
        
        // 创建测试数据
        Map<String, String> metadata1 = new HashMap<>();
        metadata1.put("source", "test1.md");
        metadata1.put("title", "测试文档1");
        metadata1.put("section", "第一节");
        
        Map<String, String> metadata2 = new HashMap<>();
        metadata2.put("source", "test2.md");
        metadata2.put("title", "测试文档2");
        metadata2.put("section", "第二节");
        
        vectors.add(new VectorDBService.VectorEntry("id1", "这是测试文本1", new float[]{0.1f, 0.2f, 0.3f}, metadata1));
        vectors.add(new VectorDBService.VectorEntry("id2", "这是测试文本2", new float[]{0.4f, 0.5f, 0.6f}, metadata2));
        
        // 将向量写入临时文件
        File tempFile = tempDir.resolve("test_vector.dat").toFile();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile))) {
            // 写入向量数量
            oos.writeInt(vectors.size());
            
            // 写入所有向量
            for (VectorDBService.VectorEntry entry : vectors) {
                oos.writeObject(entry);
            }
        }
        
        // 从文件读取向量
        List<VectorDBService.VectorEntry> loadedVectors = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile))) {
            // 读取向量数量
            int size = ois.readInt();
            assertEquals(vectors.size(), size, "向量数量应该相同");
            
            // 读取所有向量
            for (int i = 0; i < size; i++) {
                VectorDBService.VectorEntry entry = (VectorDBService.VectorEntry) ois.readObject();
                loadedVectors.add(entry);
            }
        }
        
        // 验证结果
        assertEquals(vectors.size(), loadedVectors.size(), "加载的向量数量应该相同");
        assertEquals(vectors.get(0).id(), loadedVectors.get(0).id(), "第一个向量ID应该相同");
        assertEquals(vectors.get(0).text(), loadedVectors.get(0).text(), "第一个向量文本应该相同");
        assertEquals(vectors.get(0).metadata().get("title"), loadedVectors.get(0).metadata().get("title"), "第一个向量元数据应该相同");
    }
    
    @Test
    void testSaveAndLoadVectorDb() throws IOException, TranslateException {
        // 创建测试文件
        Path testFilePath = tempDir.resolve("test.md");
        String content = "# 测试文档\n\n## 第一节\n\n这是第一节的内容。\n\n## 第二节\n\n这是第二节的内容。\n\n";
        Files.write(testFilePath, content.getBytes(StandardCharsets.UTF_8));
        
        // 处理文件
        vectorDBService.loadDocuments();
        int originalCount = vectorDBService.getVectorCount();
        assertTrue(originalCount > 0, "应该生成至少一个向量");
        
        // 保存到磁盘
        vectorDBService.saveVectorDbToDisk();
        
        // 清空向量库
        ReflectionTestUtils.setField(vectorDBService, "vectorDb", new java.util.ArrayList<>());
        assertEquals(0, vectorDBService.getVectorCount(), "向量库应该被清空");
        
        // 从磁盘加载
        boolean loadResult = vectorDBService.loadVectorDbFromDisk();
        assertTrue(loadResult, "应该成功加载向量库");
        assertEquals(originalCount, vectorDBService.getVectorCount(), "加载后向量数量应该与原始数量相同");
    }
    
    @Test
    void testOptimizedTextChunking() throws IOException, TranslateException {
        // 创建具有复杂结构的测试文件
        Path testFilePath = tempDir.resolve("complex.md");
        StringBuilder longContent = new StringBuilder("很长");
        for (int i = 0; i < 100; i++) {
            longContent.append("很长");
        }
        
        String content = 
            "# 复杂文档\n\n" +
            "这是简介部分。\n\n" +
            "## 第一章\n\n" +
            "这是第一章的内容，包含多个段落。\n\n" +
            "继续第一章的更多内容。这部分很长，应该被分割。" + longContent.toString() + "\n\n" +
            "### 第一章第一节\n\n" +
            "这是第一章第一节的内容。\n\n" +
            "## 第二章\n\n" +
            "这是第二章的简短内容。\n\n";
        Files.write(testFilePath, content.getBytes(StandardCharsets.UTF_8));
        
        // 处理文件
        ReflectionTestUtils.setField(vectorDBService, "chunkSize", 200);  // 设置较小的块大小以强制分块
        vectorDBService.loadDocuments();
        
        // 使用反射获取向量库
        @SuppressWarnings("unchecked")
        List<Object> vectorDb = (List<Object>) ReflectionTestUtils.getField(vectorDBService, "vectorDb");
        assertNotNull(vectorDb);
        
        // 验证分块结果
        assertTrue(vectorDb.size() > 3, "应该生成多个块");
        
        // 测试搜索功能
        float[] queryVector = new float[]{0.1f, 0.2f, 0.3f};
        List<VectorDBService.SearchResult> results = vectorDBService.search(queryVector, 5);
        assertNotNull(results);
    }
    
    @Test
    void testSplitTextIntoChunks() throws Exception {
        String text = 
            "# 测试文档\n\n" +
            "## 第一节\n\n" +
            "这是第一节的内容，包含一些文本。\n\n" +
            "## 第二节\n\n" +
            "这是第二节的内容。\n\n" +
            "### 子节\n\n" +
            "这是子节的内容。";
        
        // 使用反射调用私有方法
        @SuppressWarnings("unchecked")
        List<String> chunks = (List<String>) ReflectionTestUtils.invokeMethod(
            vectorDBService, 
            "splitTextIntoChunks", 
            text
        );
        
        assertNotNull(chunks);
        assertTrue(chunks.size() >= 2, "应该至少分成两个块");
        
        // 检查第一个块是否包含"第一节"
        String firstChunk = chunks.get(0);
        assertTrue(firstChunk.contains("# 测试文档") || firstChunk.contains("## 第一节"), 
                   "第一个块应该包含文档标题或第一节标题");
    }
} 