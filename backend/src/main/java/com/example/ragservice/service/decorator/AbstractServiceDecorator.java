package com.example.ragservice.service.decorator;

/**
 * 抽象服务装饰器实现
 * @param <T> 被装饰的服务类型
 */
public abstract class AbstractServiceDecorator<T> implements ServiceDecorator<T> {
    
    protected T decoratedService;
    protected boolean enabled = true;
    protected final String name;
    protected final String description;
    
    public AbstractServiceDecorator(T decoratedService, String name, String description) {
        this.decoratedService = decoratedService;
        this.name = name;
        this.description = description;
    }
    
    @Override
    public T getDecoratedService() {
        return decoratedService;
    }
    
    @Override
    public void setDecoratedService(T service) {
        this.decoratedService = service;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
} 