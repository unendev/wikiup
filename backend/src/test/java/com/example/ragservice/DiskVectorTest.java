package com.example.ragservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import com.example.ragservice.service.VectorDBService;
import com.example.ragservice.service.EmbeddingService;

import ai.djl.translate.TranslateException;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
    "djl.offline=true",
    "djl.engine.default=PyTorch",
    "vector.db.use.disk=true"
})
public class DiskVectorTest {
    
    @Autowired
    private VectorDBService vectorDBService;
    
    @MockBean
    private EmbeddingService embeddingService;
    
    @Test
    public void testVectorDiskPersistence(@TempDir Path tempDir) throws IOException, TranslateException {
        // 模拟嵌入服务
        when(embeddingService.embed(anyString())).thenReturn(new float[]{0.1f, 0.2f, 0.3f, 0.4f, 0.5f});
        
        // 创建临时测试文件夹和文件
        Path testDir = tempDir.resolve("dst");
        Files.createDirectories(testDir);
        
        // 创建测试文件
        String uniqueId = UUID.randomUUID().toString().substring(0, 8);
        String fileName = "test-" + uniqueId + ".md";
        Path testFile = testDir.resolve(fileName);
        
        // 写入一些内容到测试文件
        String content = 
            "# 测试文档 " + uniqueId + "\n\n" +
            "这是一个测试文档。\n\n" +
            "## 第一节\n\n" +
            "这是第一节的内容。\n\n" +
            "## 第二节\n\n" +
            "这是第二节的内容。";
        Files.write(testFile, content.getBytes(StandardCharsets.UTF_8));
        
        // 创建data目录，因为VectorDBService.saveVectorDbToDisk()方法将文件保存到data/vector_db.dat
        Path dataDir = Paths.get("data");
        Files.createDirectories(dataDir);
        Path vectorDbFile = dataDir.resolve("vector_db.dat");
        
        // 如果文件已存在，先删除
        Files.deleteIfExists(vectorDbFile);
        
        // 保存当前系统属性
        String oldKnowledgeBasePath = System.getProperty("knowledge.base.path");
        String oldVectorDbFile = System.getProperty("vector.db.file");
        String oldVectorDbUseDisk = System.getProperty("vector.db.use.disk");
        
        // 添加调试信息
        System.out.println("临时目录路径: " + tempDir.toString());
        System.out.println("测试目录路径: " + testDir.toString());
        System.out.println("向量数据库文件路径: " + vectorDbFile.toString());
        
        try {
            // 设置测试环境
            System.setProperty("knowledge.base.path", testDir.toString());
            System.setProperty("vector.db.file", vectorDbFile.toString());
            System.setProperty("vector.db.use.disk", "true");
            
            // 添加调试信息
            System.out.println("设置系统属性:");
            System.out.println("knowledge.base.path: " + System.getProperty("knowledge.base.path"));
            System.out.println("vector.db.file: " + System.getProperty("vector.db.file"));
            System.out.println("vector.db.use.disk: " + System.getProperty("vector.db.use.disk"));
            
            // 第一次加载文档并保存到磁盘
            vectorDBService.loadDocuments();
            int originalCount = vectorDBService.getVectorCount();
            assertTrue(originalCount > 0, "应该有向量条目生成");
            
            // 保存到磁盘
            vectorDBService.saveVectorDbToDisk();
            
            // 添加调试信息
            System.out.println("检查向量数据库文件是否存在: " + vectorDbFile.toString());
            System.out.println("文件存在: " + Files.exists(vectorDbFile));
            
            // 验证文件是否创建
            assertTrue(Files.exists(vectorDbFile), "向量数据库文件应该被创建");
            
            // 打印文件大小
            long fileSize = Files.size(vectorDbFile);
            System.out.println("向量数据库文件大小: " + fileSize + " 字节");
            
            // 清空向量数据库，然后尝试重新加载
            // 我们需要创建一个新的VectorDBService实例，或者通过反射清空现有的vectorDb列表
            // 这里我们直接调用loadVectorDbFromDisk方法，它会先清空vectorDb
            boolean loadResult = vectorDBService.loadVectorDbFromDisk();
            assertTrue(loadResult, "应该成功加载向量数据库");
            assertEquals(originalCount, vectorDBService.getVectorCount(), "加载后向量数量应该与原始数量相同");
        } finally {
            // 恢复系统属性
            if (oldKnowledgeBasePath != null) {
                System.setProperty("knowledge.base.path", oldKnowledgeBasePath);
            } else {
                System.clearProperty("knowledge.base.path");
            }
            
            if (oldVectorDbFile != null) {
                System.setProperty("vector.db.file", oldVectorDbFile);
            } else {
                System.clearProperty("vector.db.file");
            }
            
            if (oldVectorDbUseDisk != null) {
                System.setProperty("vector.db.use.disk", oldVectorDbUseDisk);
            } else {
                System.clearProperty("vector.db.use.disk");
            }
            
            // 清理测试资源
            Files.deleteIfExists(vectorDbFile);
        }
    }
} 