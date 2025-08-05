package com.example.ragservice.service;

import com.example.ragservice.service.embedding.DJLEmbeddingModel;
import com.example.ragservice.service.embedding.EmbeddingModel;
import com.example.ragservice.service.embedding.EmbeddingModelFactory;
import com.example.ragservice.service.embedding.HttpEmbeddingModel;
import com.example.ragservice.service.embedding.LocalEmbeddingModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 嵌入模型工厂测试类
 */
@SpringBootTest
@TestPropertySource(properties = {
    "model.url=djl://test-model",
    "spring.main.allow-bean-definition-overriding=true"
})
@ActiveProfiles("test")
public class EmbeddingModelFactoryTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        public String testBean() {
            return "testBean";
        }
    }

    @Autowired
    private EmbeddingModelFactory embeddingModelFactory;

    @Test
    public void testGetDefaultModel() {
        // 获取默认模型
        EmbeddingModel model = embeddingModelFactory.getDefaultModel();
        
        // 验证默认模型不为空
        assertNotNull(model);
        
        // 验证默认模型是DJL模型
        assertTrue(model instanceof DJLEmbeddingModel);
        
        // 验证模型URL
        assertEquals("djl://test-model", model.getModelUrl());
    }
    
    @Test
    public void testRegisterAndGetModel() {
        // 注册不同类型的模型
        EmbeddingModel httpModel = embeddingModelFactory.registerModel("https://api.example.com/embed");
        EmbeddingModel localModel = embeddingModelFactory.registerModel("file:///path/to/model");
        EmbeddingModel djlModel = embeddingModelFactory.registerModel("djl://another-model");
        
        // 验证模型类型
        assertTrue(httpModel instanceof HttpEmbeddingModel);
        assertTrue(localModel instanceof LocalEmbeddingModel);
        assertTrue(djlModel instanceof DJLEmbeddingModel);
        
        // 通过ID获取模型
        EmbeddingModel retrievedHttpModel = embeddingModelFactory.getModel(httpModel.getModelId());
        EmbeddingModel retrievedLocalModel = embeddingModelFactory.getModel(localModel.getModelId());
        EmbeddingModel retrievedDjlModel = embeddingModelFactory.getModel(djlModel.getModelId());
        
        // 验证获取的模型与注册的模型相同
        assertEquals(httpModel.getModelId(), retrievedHttpModel.getModelId());
        assertEquals(localModel.getModelId(), retrievedLocalModel.getModelId());
        assertEquals(djlModel.getModelId(), retrievedDjlModel.getModelId());
    }
    
    @Test
    public void testGetNonExistentModel() {
        // 获取不存在的模型应该返回默认模型
        EmbeddingModel defaultModel = embeddingModelFactory.getDefaultModel();
        EmbeddingModel nonExistentModel = embeddingModelFactory.getModel("non-existent-id");
        
        // 验证返回的是默认模型
        assertEquals(defaultModel.getModelId(), nonExistentModel.getModelId());
    }
    
    @Test
    public void testGetAllModels() {
        // 注册一个新模型
        embeddingModelFactory.registerModel("https://api.example.com/embed2");
        
        // 获取所有模型
        Map<String, EmbeddingModel> allModels = embeddingModelFactory.getAllModels();
        
        // 验证模型数量至少为2（默认模型 + 新注册的模型）
        assertTrue(allModels.size() >= 2);
    }
} 