package com.spring.delivery.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(int statusCode, String error, Object message, T data, StackTraceElement[] stackTrace) {}
