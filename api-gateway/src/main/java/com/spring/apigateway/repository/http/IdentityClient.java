package com.spring.apigateway.repository.http;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import com.spring.apigateway.domain.ApiResponse;
import com.spring.apigateway.domain.request.IntrospectRequest;

import reactor.core.publisher.Mono;

public interface IdentityClient {
	@PostExchange(value = "/v1/internal/token", contentType = MediaType.APPLICATION_JSON_VALUE)
	Mono<ApiResponse<Boolean>> introspect(@RequestBody IntrospectRequest request);
}
