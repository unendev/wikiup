
package com.example.ragservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.security.Principal;

@Configuration
@Profile("!test") // 在非测试环境中加载
@ConditionalOnProperty(name = "spring.websocket.enabled", havingValue = "true", matchIfMissing = true)
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 自定义Configurator，用于在WebSocket握手期间传递认证信息
     */
    public static class SecurityAwareServerEndpointConfigurator extends ServerEndpointConfig.Configurator {
        @Override
        public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, javax.websocket.HandshakeResponse response) {
            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                sec.getUserProperties().put(Principal.class.getName(), principal);
            }
            super.modifyHandshake(sec, request, response);
        }
    }
} 