package com.spring.delivery.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.util.exception.AppException;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component("myStringHttpMessageConverter")
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CustomStringHttpMessageConverter extends AbstractHttpMessageConverter<ApiResponse<String>> {
	ObjectMapper objectMapper;

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return Collections.singletonList(MediaType.APPLICATION_JSON);
	}

	@Override
	protected boolean supports(@Nullable Class<?> clazz) {
		return String.class == clazz;
	}

	@Override
	protected ApiResponse<String> readInternal(
			@Nullable Class<? extends ApiResponse<String>> clazz, @Nullable HttpInputMessage inputMessage)
			throws HttpMessageNotReadableException {
		throw new AppException("Not support readInternal");
	}

	@Override
	protected void writeInternal(@Nullable ApiResponse<String> s, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		String str = this.objectMapper.writeValueAsString(s);
		StreamUtils.copy(str.getBytes(StandardCharsets.UTF_8), outputMessage.getBody());
	}
}
