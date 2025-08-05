
package com.example.ragservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@Profile("!test") // 在非测试环境中加载
@ConditionalOnProperty(name = "spring.websocket.enabled", havingValue = "true", matchIfMissing = true)
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
} 