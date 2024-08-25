package com.spring.delivery.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RequestLogin(
		@NotBlank(message = "Region is required")
		String region,

		@NotBlank
		String phoneNumber,

		@NotBlank(message = "Password is required")
		String password
) {

}
