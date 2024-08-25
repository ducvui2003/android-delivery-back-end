package com.spring.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RequestVerify(@NotBlank @JsonProperty("otp") String otp) {}
