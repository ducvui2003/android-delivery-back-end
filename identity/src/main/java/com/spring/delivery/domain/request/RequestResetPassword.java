package com.spring.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.delivery.util.anotation.PasswordValid;

import jakarta.validation.constraints.NotBlank;

public record RequestResetPassword(
		@NotBlank String otp, @NotBlank @PasswordValid @JsonProperty("password") String newPassword) {}
