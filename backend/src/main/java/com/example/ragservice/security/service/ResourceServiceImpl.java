package com.example.ragservice.security.service;

import com.example.ragservice.security.model.Resource;
import com.example.ragservice.security.model.ResourceType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 资源服务实现类（内存实现）
 * 注意：这是一个临时实现，后续应该替换为数据库实现
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    
    // 使用ConcurrentHashMap存储资源数据
    private final Map<Long, Resource> resourceMap = new ConcurrentHashMap<>();
    
    // 用于生成资源ID
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * 构造函数，初始化默认资源
     */
    public ResourceServiceImpl() {
        // 创建一些默认资源
        Resource documentResource = Resource.builder()
                .id(idGenerator.getAndIncrement())
                .type(ResourceType.DOCUMENT)
                .name("文档资源")
                .path("/api/docs")
                .description("文档资源")
                .requiresAuth(true)
                .build();
        
        Resource userResource = Resource.builder()
                .id(idGenerator.getAndIncrement())
                .type(ResourceType.USER)
                .name("用户资源")
                .path("/api/users")
                .description("用户资源")
                .requiresAuth(true)
                .build();
        
        Resource systemResource = Resource.builder()
                .id(idGenerator.getAndIncrement())
                .type(ResourceType.SYSTEM)
                .name("系统资源")
                .path("/api/system")
                .description("系统资源")
                .requiresAuth(true)
                .build();
        
        resourceMap.put(documentResource.getId(), documentResource);
        resourceMap.put(userResource.getId(), userResource);
        resourceMap.put(systemResource.getId(), systemResource);
    }

    @Override
    public Optional<Resource> findById(Long id) {
        return Optional.ofNullable(resourceMap.get(id));
    }

    @Override
    public List<Resource> findByType(ResourceType type) {
        return resourceMap.values().stream()
                .filter(resource -> resource.getType() == type)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Resource> findByPath(String path) {
        return resourceMap.values().stream()
                .filter(resource -> resource.getPath().equals(path))
                .findFirst();
    }

    @Override
    public Resource save(Resource resource) {
        if (resource.getId() == null) {
            resource.setId(idGenerator.getAndIncrement());
        }
        resourceMap.put(resource.getId(), resource);
        return resource;
    }

    @Override
    public void delete(Long id) {
        resourceMap.remove(id);
    }
} 