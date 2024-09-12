package com.spring.apigateway.service;

import org.springframework.stereotype.Service;

import com.spring.apigateway.domain.ApiResponse;
import com.spring.apigateway.domain.request.IntrospectRequest;
import com.spring.apigateway.repository.http.IdentityClient;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
	IdentityClient identityClient;

	public Mono<ApiResponse<Boolean>> introspect(String token) {
		return identityClient.introspect(
				IntrospectRequest.builder().accessToken(token).build());
	}
}
