package com.spring.delivery.domain.request;

import lombok.Builder;

@Builder
public record RequestLoginGoogleWebByAccessToken(String accessToken) {}
