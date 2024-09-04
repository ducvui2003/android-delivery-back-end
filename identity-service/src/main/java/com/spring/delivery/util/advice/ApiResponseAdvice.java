package com.spring.delivery.util.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.util.anotation.ApiMessage;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(
            @Nullable MethodParameter returnType, @Nullable Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            @Nullable MethodParameter returnType,
            @Nullable MediaType selectedContentType,
            @Nullable Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @Nullable ServerHttpRequest request,
            @Nullable ServerHttpResponse response) {

        String path = request.getURI().getPath();
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) return body;

        HttpServletResponse httpServletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int statusCode = httpServletResponse.getStatus();
        // Nếu là lỗi > 400 => return để cho Global Exception quản lý
        if (statusCode >= 400) return body;

        ApiMessage message = returnType.getMethodAnnotation(ApiMessage.class);
        String DEFAULT_MESSAGE = "Not message";
        return ApiResponse.builder()
                .statusCode(statusCode)
                .message(message != null ? message.value() : DEFAULT_MESSAGE)
                .data(body)
                .build();
    }
}
