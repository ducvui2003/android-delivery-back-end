package com.spring.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record RequestAuthorizationCode(
		String code,
		@JsonProperty("client_id") String clientId,
		@JsonProperty("client_secret") String clientSecret,
		@JsonProperty("redirect_uri") String redirectUri,
		@JsonProperty("grant_type") String grantType) {}
