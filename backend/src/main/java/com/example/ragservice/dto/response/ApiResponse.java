package com.example.ragservice.dto.response;

import java.time.LocalDateTime;

/**
 * 标准API响应格式
 * @param <T> 响应数据类型
 */
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String code;
    private LocalDateTime timestamp;

    private ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 创建成功响应
     * @param data 响应数据
     * @param <T> 数据类型
     * @return API响应对象
     */
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.message = "操作成功";
        response.data = data;
        response.code = "200";
        return response;
    }

    /**
     * 创建成功响应（带消息）
     * @param data 响应数据
     * @param message 成功消息
     * @param <T> 数据类型
     * @return API响应对象
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = true;
        response.message = message;
        response.data = data;
        response.code = "200";
        return response;
    }

    /**
     * 创建错误响应
     * @param message 错误消息
     * @param <T> 数据类型
     * @return API响应对象
     */
    public static <T> ApiResponse<T> error(String message) {
        return error(message, "500");
    }

    /**
     * 创建错误响应（带错误码）
     * @param message 错误消息
     * @param code 错误代码
     * @param <T> 数据类型
     * @return API响应对象
     */
    public static <T> ApiResponse<T> error(String message, String code) {
        ApiResponse<T> response = new ApiResponse<>();
        response.success = false;
        response.message = message;
        response.code = code;
        return response;
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
} 