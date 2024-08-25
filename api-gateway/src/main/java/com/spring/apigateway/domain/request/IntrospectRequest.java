package com.spring.apigateway.domain.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IntrospectRequest {
	String accessToken;
}
