package com.spring.delivery.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RequestResentVerify(@NotBlank @Email String email) {}
