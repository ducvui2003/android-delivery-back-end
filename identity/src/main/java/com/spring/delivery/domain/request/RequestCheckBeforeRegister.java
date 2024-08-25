package com.spring.delivery.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RequestCheckBeforeRegister(
		@NotBlank(message = "Region is required")
		String region,

		@NotBlank(message = "Phone number is required")
		String phoneNumber,

		@NotBlank(message = "Email is required")
		@Email(message = "Please provide a valid phoneNumber address")
		String email
) {

}
