package com.example.ragservice.security.service;

import com.example.ragservice.security.model.Resource;
import com.example.ragservice.security.model.ResourceType;

import java.util.List;
import java.util.Optional;

/**
 * 资源服务接口
 * 提供资源管理相关的功能
 */
public interface ResourceService {
    
    /**
     * 根据ID查找资源
     * 
     * @param id 资源ID
     * @return 资源对象（可能为空）
     */
    Optional<Resource> findById(Long id);
    
    /**
     * 根据类型查找资源
     * 
     * @param type 资源类型
     * @return 资源列表
     */
    List<Resource> findByType(ResourceType type);
    
    /**
     * 根据路径查找资源
     * 
     * @param path 资源路径
     * @return 资源对象（可能为空）
     */
    Optional<Resource> findByPath(String path);
    
    /**
     * 保存资源
     * 
     * @param resource 资源对象
     * @return 保存后的资源对象
     */
    Resource save(Resource resource);
    
    /**
     * 删除资源
     * 
     * @param id 资源ID
     */
    void delete(Long id);
} 