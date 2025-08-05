package com.example.ragservice.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 聊天请求DTO
 */
public class ChatRequest {
    
    @NotBlank(message = "问题不能为空")
    @Size(min = 1, max = 1000, message = "问题长度必须在1-1000之间")
    private String question;
    
    private boolean stream = false;
    
    public ChatRequest() {
    }
    
    public ChatRequest(String question) {
        this.question = question;
    }
    
    public ChatRequest(String question, boolean stream) {
        this.question = question;
        this.stream = stream;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public boolean isStream() {
        return stream;
    }
    
    public void setStream(boolean stream) {
        this.stream = stream;
    }
    
    @Override
    public String toString() {
        return "ChatRequest{" +
                "question='" + question + '\'' +
                ", stream=" + stream +
                '}';
    }
} 