package com.spring.delivery.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestForgetPassword(
		@NotBlank(message = "Email not blank") @Email(message = "Email format not valid") String email) {}
