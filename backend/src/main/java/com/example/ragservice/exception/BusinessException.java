package com.example.ragservice.exception;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private final String code;

    public BusinessException(String message) {
        this(message, "400");
    }

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        this(message, "400", cause);
    }

    public BusinessException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
} 