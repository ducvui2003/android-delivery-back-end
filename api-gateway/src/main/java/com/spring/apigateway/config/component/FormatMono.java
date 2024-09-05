package com.spring.apigateway.config.component;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.apigateway.domain.ApiResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FormatMono {
	ObjectMapper objectMapper;

	public Mono<Void> unAuthenticated(ServerWebExchange exchange) {
		// Create your custom response body
		ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
				.statusCode(HttpStatus.UNAUTHORIZED.value())
				.error("UNAUTHORIZED")
				.message("Token not valid")
				.build();

		// Serialize ApiResponse to JSON (using your preferred method)
		String responseBody = null;
		try {
			responseBody = objectMapper.writeValueAsString(apiResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			responseBody = "";
		}

		// Modify the ServerHttpResponse
		exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

		DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
		DataBuffer dataBuffer = bufferFactory.wrap(responseBody.getBytes());
		return exchange.getResponse().writeWith(Mono.just(dataBuffer));
	}

	public Mono<Void> endpointNotAllow(ServerWebExchange exchange) {
		// Create your custom response body
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.statusCode(HttpStatus.FORBIDDEN.value())
				.error("FORBIDDEN")
				.message("Endpoint not allowed")
				.build();

		// Serialize ApiResponse to JSON (using your preferred method)
		String responseBody = null;
		try {
			responseBody = objectMapper.writeValueAsString(apiResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			responseBody = "";
		}

		// Modify the ServerHttpResponse
		exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

		DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
		DataBuffer dataBuffer = bufferFactory.wrap(responseBody.getBytes());
		return exchange.getResponse().writeWith(Mono.just(dataBuffer));
	}
}
