package com.example.ragservice.endpoint;

import com.example.ragservice.service.VectorDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    
    private final VectorDBService vectorDBService;
    private final AtomicBoolean isLoading = new AtomicBoolean(false);
    private final AtomicReference<String> loadingStatus = new AtomicReference<>("未加载");
    
    @Autowired
    public AdminController(VectorDBService vectorDBService) {
        this.vectorDBService = vectorDBService;
    }
    
    @PostMapping("/kb/load")
    public ResponseEntity<?> loadKnowledgeBase() {
        // 如果已经在加载中，返回错误
        if (isLoading.get()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "知识库正在加载中，请稍后再试");
            return ResponseEntity.badRequest().body(response);
        }
        
        // 设置加载状态
        isLoading.set(true);
        loadingStatus.set("加载中");
        
        // 异步加载知识库
        CompletableFuture.runAsync(() -> {
            try {
                vectorDBService.loadDocuments();
                loadingStatus.set("加载完成");
            } catch (Exception e) {
                logger.error("Failed to load knowledge base", e);
                loadingStatus.set("加载失败: " + e.getMessage());
            } finally {
                isLoading.set(false);
            }
        });
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "知识库加载已开始，请通过状态API查询进度");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/kb/reload-and-save")
    public ResponseEntity<?> reloadAndSaveKnowledgeBase() {
        // 如果已经在加载中，返回错误
        if (isLoading.get()) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "知识库正在处理中，请稍后再试");
            return ResponseEntity.badRequest().body(response);
        }
        
        // 设置加载状态
        isLoading.set(true);
        loadingStatus.set("重新处理并保存知识库中");
        
        // 异步重新加载并保存知识库
        CompletableFuture.runAsync(() -> {
            try {
                // 重新加载并保存到磁盘
                logger.info("开始重新处理知识库并保存到磁盘");
                vectorDBService.reloadAndSave();
                loadingStatus.set("重新处理并保存完成");
                logger.info("知识库重新处理并保存到磁盘完成");
            } catch (Exception e) {
                logger.error("Failed to reload and save knowledge base", e);
                loadingStatus.set("重新处理失败: " + e.getMessage());
            } finally {
                isLoading.set(false);
            }
        });
        
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "知识库重新处理已开始，请通过状态API查询进度");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/kb/status")
    public ResponseEntity<?> getKnowledgeBaseStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("isLoading", isLoading.get());
        status.put("status", loadingStatus.get());
        
        // 添加向量数据库信息
        status.put("vectorCount", vectorDBService.getVectorCount());
        status.put("similarityThreshold", vectorDBService.getSimilarityThreshold());
        
        return ResponseEntity.ok(status);
    }
    
    @PutMapping("/kb/threshold")
    public ResponseEntity<?> updateSimilarityThreshold(@RequestBody Map<String, Object> request) {
        try {
            double threshold = Double.parseDouble(request.get("threshold").toString());
            if (threshold < 0 || threshold > 1) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "相似度阈值必须在0到1之间");
                return ResponseEntity.badRequest().body(response);
            }
            
            vectorDBService.setSimilarityThreshold(threshold);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "相似度阈值已更新为 " + threshold);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "更新失败: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
} 