
package com.example.ragservice.endpoint;

import com.example.ragservice.config.WebSocketConfig;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.example.ragservice.service.RAGService;
import com.example.ragservice.dto.response.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.security.Principal;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Component
@ServerEndpoint(value = "/api/v1/qa/ask", configurator = WebSocketConfig.SecurityAwareServerEndpointConfigurator.class)
public class ChatEndpoint {

    private static final Logger log = LoggerFactory.getLogger(ChatEndpoint.class);

    private static final CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static RAGService ragService;

    @Autowired
    public void setRagService(RAGService ragService) {
        ChatEndpoint.ragService = ragService;
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        Principal principal = session.getUserPrincipal();
        if (principal != null) {
            log.info("New session opened for user {}: {}", principal.getName(), session.getId());
        } else {
            log.info("New anonymous session opened: {}", session.getId());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Principal principal = session.getUserPrincipal();
        if (principal == null) {
            log.warn("Received message from unauthenticated session {}: {}", session.getId(), message);
            // Optionally, close the session or send an error
            session.getBasicRemote().sendText("{\"type\":\"error\",\"message\":\"Authentication required.\"}");
            session.close();
            return;
        }
        log.info("Message from user '{}' (session {}): {}", principal.getName(), session.getId(), message);
        
        try {
            // 解析消息，检查是否请求流式响应
            JsonNode jsonMessage;
            try {
                jsonMessage = objectMapper.readTree(message);
            } catch (Exception e) {
                // 如果不是JSON格式，则将整个消息作为问题
                jsonMessage = objectMapper.createObjectNode()
                    .put("question", message)
                    .put("stream", false);
            }
            
            String question = jsonMessage.has("question") ? 
                jsonMessage.get("question").asText() : message;
            boolean isStream = jsonMessage.has("stream") && 
                jsonMessage.get("stream").asBoolean();
            
            if (isStream) {
                // 使用流式处理
                ragService.getAnswerStream(
                    question,
                    principal,
                    // 每个响应块的处理
                    chunk -> {
                        try {
                            String jsonResponse = objectMapper.writeValueAsString(
                                objectMapper.createObjectNode()
                                    .put("type", "chunk")
                                    .put("content", chunk)
                            );
                            session.getBasicRemote().sendText(jsonResponse);
                        } catch (IOException e) {
                            log.error("Error sending chunk", e);
                        }
                    },
                    // 完成时的处理（接收来源信息）
                    sources -> {
                        try {
                            // 创建包含完成信息和数据源的响应
                            ObjectNode responseJson = objectMapper.createObjectNode()
                                .put("type", "done");
                            
                            // 添加来源数组
                            ArrayNode sourcesArray = responseJson.putArray("sources");
                            for (ChatResponse.SourceInfo source : sources) {
                                ObjectNode sourceNode = objectMapper.createObjectNode();
                                sourceNode.put("title", source.getTitle());
                                sourceNode.put("source", source.getSource());
                                sourceNode.put("path", source.getPath());
                                if (source.getScore() != null) {
                                    sourceNode.put("score", source.getScore());
                                }
                                if (source.getContent() != null) {
                                    sourceNode.put("content", source.getContent());
                                }
                                sourcesArray.add(sourceNode);
                            }
                            
                            String jsonResponse = objectMapper.writeValueAsString(responseJson);
                            session.getBasicRemote().sendText(jsonResponse);
                        } catch (IOException e) {
                            log.error("Error sending completion message with sources", e);
                        }
                    },
                    // 错误处理
                    error -> {
                        try {
                            String jsonResponse = objectMapper.writeValueAsString(
                                objectMapper.createObjectNode()
                                    .put("type", "error")
                                    .put("message", error.getMessage())
                            );
                            session.getBasicRemote().sendText(jsonResponse);
                        } catch (IOException e) {
                            log.error("Error sending error message", e);
                        }
                    }
                );
            } else {
                // 使用非流式处理
                ragService.getAnswer(question, principal).subscribe(answer -> {
                    try {
                        // 创建响应JSON，包含答案和来源信息
                        ObjectNode responseJson = objectMapper.createObjectNode()
                            .put("type", "answer")
                            .put("content", answer.getAnswer());
                        
                        // 添加来源数组
                        ArrayNode sourcesArray = responseJson.putArray("sources");
                        for (ChatResponse.SourceInfo source : answer.getSources()) {
                            ObjectNode sourceNode = objectMapper.createObjectNode();
                            sourceNode.put("title", source.getTitle());
                            sourceNode.put("source", source.getSource());
                            sourceNode.put("path", source.getPath());
                            if (source.getScore() != null) {
                                sourceNode.put("score", source.getScore());
                            }
                            if (source.getContent() != null) {
                                sourceNode.put("content", source.getContent());
                            }
                            sourcesArray.add(sourceNode);
                        }
                        
                        String jsonResponse = objectMapper.writeValueAsString(responseJson);
                        session.getBasicRemote().sendText(jsonResponse);
                    } catch (IOException e) {
                        log.error("Error sending message", e);
                    }
                }, error -> {
                    log.error("Error while processing message", error);
                    try {
                        String jsonResponse = objectMapper.writeValueAsString(
                            objectMapper.createObjectNode()
                                .put("type", "error")
                                .put("message", error.getMessage())
                        );
                        session.getBasicRemote().sendText(jsonResponse);
                    } catch (IOException e) {
                        log.error("Error sending error message", e);
                    }
                });
            }
        } catch (Exception e) {
            log.error("Error while processing message", e);
            try {
                String jsonResponse = objectMapper.writeValueAsString(
                    objectMapper.createObjectNode()
                        .put("type", "error")
                        .put("message", e.getMessage())
                );
                session.getBasicRemote().sendText(jsonResponse);
            } catch (IOException ioe) {
                log.error("Error sending error message", ioe);
                session.getBasicRemote().sendText("Error: " + e.getMessage());
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        log.info("Session closed: {}", session.getId());
    }
}
