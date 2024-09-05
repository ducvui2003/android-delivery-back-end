package com.spring.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RequestLogout(
		@NotBlank(message = "Access token is required") @JsonProperty("access_token") String accessToken) {}
