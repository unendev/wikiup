package com.example.ragservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

@Service
public class LLMService {
    private static final Logger logger = LoggerFactory.getLogger(LLMService.class);
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Value("${llm.api.key}")
    private String apiKey;

    @Value("${llm.api.url:https://api.deepseek.com/v1/chat/completions}")
    private String apiUrl;

    @Value("${llm.model:deepseek-chat}")
    private String model;

    @Value("${llm.temperature:0.7}")
    private double temperature;

    @Value("${llm.max_tokens:2000}")
    private int maxTokens;
    
    @Value("${llm.mock.enabled:false}")
    private boolean mockEnabled;

    public LLMService() {
        this.webClient = WebClient.builder().build();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * 使用提供的上下文和问题生成回答
     * @param context 上下文信息
     * @param question 用户问题
     * @return 生成的回答
     */
    public Mono<String> generateAnswer(List<String> context, String question) {
        // 如果启用了模拟模式，返回模拟回答
        if (mockEnabled) {
            logger.info("Using mock LLM response, context size: {}", context.size());
            return Mono.just(generateMockAnswer(context, question));
        }
        
        String prompt = buildPrompt(context, question);
        return callLLM(prompt);
    }

    /**
     * 使用流式API处理响应
     * @param context 上下文信息
     * @param question 用户问题
     * @param onChunk 处理每个响应块的回调
     * @param onComplete 处理完成时的回调
     * @param onError 处理错误的回调
     */
    public void generateAnswerStream(
            List<String> context,
            String question,
            Consumer<String> onChunk,
            Runnable onComplete,
            Consumer<Throwable> onError
    ) {
        // 如果启用了模拟模式，使用模拟流式回答
        if (mockEnabled) {
            logger.info("Using mock LLM streaming response, context size: {}", context.size());
            String mockAnswer = generateMockAnswer(context, question);
            
            // 模拟流式响应，将回答分成多个部分
            String[] parts = mockAnswer.split("\\. ");
            for (String part : parts) {
                onChunk.accept(part + ". ");
                try {
                    Thread.sleep(300); // 模拟延迟
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            onComplete.run();
            return;
        }
        
        String prompt = buildPrompt(context, question);
        callLLMStream(prompt)
                .subscribe(
                        onChunk,
                        onError,
                        onComplete
                );
    }
    
    /**
     * 生成模拟回答
     */
    private String generateMockAnswer(List<String> context, String question) {
        if (context.isEmpty()) {
            return "抱歉，我没有找到相关信息。";
        }
        
        // 构建一个基于上下文的简单回答
        StringBuilder answer = new StringBuilder();
        answer.append("根据我的知识，");
        
        // 从上下文中提取关键信息
        for (String ctx : context) {
            // 提取内容部分
            int contentIndex = ctx.indexOf("内容：");
            if (contentIndex != -1) {
                String content = ctx.substring(contentIndex + 3).trim();
                // 只取内容的前150个字符作为回答的一部分
                int endIndex = Math.min(content.length(), 150);
                String contentSnippet = content.substring(0, endIndex);
                
                // 如果内容包含问题中的关键词，优先使用
                if (contentSnippet.contains(question)) {
                    answer.append(contentSnippet);
                    break;
                }
                
                // 否则添加到答案中
                answer.append(contentSnippet);
                answer.append("。 ");
            }
        }
        
        // 添加一个结尾
        answer.append("希望这些信息对您有所帮助。");
        
        return answer.toString();
    }

    /**
     * 构建提示词
     */
    private String buildPrompt(List<String> context, String question) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("你是一个《饥荒》游戏知识助手，请基于以下信息回答用户的问题。\n\n");
        promptBuilder.append("参考信息：\n");
        
        // 添加上下文
        for (String ctx : context) {
            promptBuilder.append("---\n").append(ctx).append("\n");
        }
        
        promptBuilder.append("---\n\n");
        promptBuilder.append("用户问题：").append(question).append("\n\n");
        promptBuilder.append("请根据提供的参考信息回答用户问题。如果参考信息不足以回答问题，请明确说明。回答应该简洁、准确，并使用中文。");
        
        return promptBuilder.toString();
    }

    /**
     * 调用LLM API获取回答
     */
    private Mono<String> callLLM(String prompt) {
        ObjectNode requestBody = createRequestBody(prompt, false);

        return webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(response -> {
                    logger.debug("Response from LLM API: {}", response);
                    return response.path("choices").get(0).path("message").path("content").asText();
                })
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
                        .filter(throwable -> throwable instanceof WebClientResponseException &&
                                ((WebClientResponseException) throwable).getStatusCode().is5xxServerError()))
                .doOnError(e -> logger.error("Error calling LLM API: {}", e.getMessage()));
    }

    /**
     * 调用LLM流式API获取回答
     */
    private Flux<String> callLLMStream(String prompt) {
        ObjectNode requestBody = createRequestBody(prompt, true);

        return webClient.post()
                .uri(apiUrl)
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToFlux(String.class)
                .map(chunk -> {
                    // 处理SSE格式的响应
                    if (chunk.startsWith("data: ")) {
                        chunk = chunk.substring(6);
                    }
                    if ("[DONE]".equals(chunk)) {
                        return "";
                    }
                    try {
                        JsonNode jsonNode = objectMapper.readTree(chunk);
                        logger.debug("Streaming chunk: {}", jsonNode);
                        return jsonNode.path("choices").get(0).path("delta").path("content").asText();
                    } catch (Exception e) {
                        logger.error("Error parsing LLM API response: {}", e.getMessage());
                        return "";
                    }
                })
                .filter(text -> !text.isEmpty())
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
                        .filter(throwable -> throwable instanceof WebClientResponseException &&
                                ((WebClientResponseException) throwable).getStatusCode().is5xxServerError()))
                .doOnError(e -> logger.error("Error calling LLM stream API: {}", e.getMessage()));
    }

    /**
     * 创建请求体
     */
    private ObjectNode createRequestBody(String prompt, boolean stream) {
        ObjectNode requestBody = objectMapper.createObjectNode();
        requestBody.put("model", model);
        requestBody.put("temperature", temperature);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("stream", stream);

        ArrayNode messages = objectMapper.createArrayNode();
        ObjectNode systemMessage = objectMapper.createObjectNode();
        systemMessage.put("role", "system");
        systemMessage.put("content", "你是一个《饥荒》游戏知识助手，请用中文回答用户的问题。");
        
        ObjectNode userMessage = objectMapper.createObjectNode();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);

        messages.add(systemMessage);
        messages.add(userMessage);
        requestBody.set("messages", messages);

        logger.debug("Request body: {}", requestBody);
        return requestBody;
    }
} 