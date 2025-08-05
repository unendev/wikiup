package com.example.ragservice.exception;

import com.example.ragservice.dto.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleBusinessException(BusinessException e) {
        logger.warn("业务异常: {}", e.getMessage());
        return ApiResponse.error(e.getMessage(), e.getCode());
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError() != null ? 
                e.getBindingResult().getFieldError().getDefaultMessage() : "参数验证失败";
        logger.warn("参数验证失败: {}", message);
        return ApiResponse.error(message, "400");
    }

    /**
     * 处理参数绑定异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleBindException(BindException e) {
        String message = e.getBindingResult().getFieldError() != null ? 
                e.getBindingResult().getFieldError().getDefaultMessage() : "参数绑定失败";
        logger.warn("参数绑定失败: {}", message);
        return ApiResponse.error(message, "400");
    }

    /**
     * 处理约束违反异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleConstraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().iterator().next().getMessage();
        logger.warn("参数约束违反: {}", message);
        return ApiResponse.error(message, "400");
    }

    /**
     * 处理HTTP消息不可读异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.warn("HTTP消息不可读: {}", e.getMessage());
        return ApiResponse.error("请求内容不可读或格式错误", "400");
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleException(Exception e) {
        logger.error("未预期的异常", e);
        return ApiResponse.error("服务器内部错误", "500");
    }
} 