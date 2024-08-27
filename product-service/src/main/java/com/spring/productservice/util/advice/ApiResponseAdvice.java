package com.spring.productservice.util.advice;

import com.spring.productservice.domain.ApiResponse;
import com.spring.productservice.util.anotation.ApiMessage;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
