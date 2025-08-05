package com.example.ragservice.service.decorator;

/**
 * 服务装饰器接口
 * @param <T> 被装饰的服务类型
 */
public interface ServiceDecorator<T> {
    
    /**
     * 获取被装饰的服务
     * @return 被装饰的服务实例
     */
    T getDecoratedService();
    
    /**
     * 设置被装饰的服务
     * @param service 服务实例
     */
    void setDecoratedService(T service);
    
    /**
     * 获取装饰器名称
     * @return 装饰器名称
     */
    String getName();
    
    /**
     * 获取装饰器描述
     * @return 装饰器描述
     */
    String getDescription();
    
    /**
     * 是否启用
     * @return 启用状态
     */
    boolean isEnabled();
    
    /**
     * 设置启用状态
     * @param enabled 启用状态
     */
    void setEnabled(boolean enabled);
} 