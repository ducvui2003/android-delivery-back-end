package com.spring.notificationservice.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class FeignEmailConfig {
    @Value("${service.email.api-key}")
    String apiKey;

    @Bean
    public RequestInterceptor serviceARequestInterceptor() {
        return template -> template.header("api-key", apiKey);
    }
    
}
