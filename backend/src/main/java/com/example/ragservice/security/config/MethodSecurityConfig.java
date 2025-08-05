package com.example.ragservice.security.config;

import com.example.ragservice.security.CustomMethodSecurityExpressionRoot;
import com.example.ragservice.security.PermissionEvaluator;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * 方法安全配置类
 * 配置自定义方法安全表达式处理器
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ConditionalOnProperty(name = "method.security.enabled", havingValue = "true", matchIfMissing = true)
@Profile("!test")
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Autowired
    private PermissionEvaluator permissionEvaluator;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new CustomMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new org.springframework.security.access.PermissionEvaluator() {
            @Override
            public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
                return permissionEvaluator.hasPermission(authentication, permission.toString());
            }

            @Override
            public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
                return permissionEvaluator.hasPermission(authentication, permission.toString());
            }
        });
        return expressionHandler;
    }

    /**
     * 自定义方法安全表达式处理器
     */
    private static class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
        
        @Autowired
        private PermissionEvaluator permissionEvaluator;

        @Override
        protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
            CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication);
            root.setPermissionEvaluator(permissionEvaluator);
            
            // 设置返回对象和过滤对象（如果有参数）
            if (invocation.getArguments() != null && invocation.getArguments().length > 0) {
                root.setFilterObject(invocation.getArguments()[0]);
            }
            root.setReturnObject(null);
            
            return root;
        }
    }
} 