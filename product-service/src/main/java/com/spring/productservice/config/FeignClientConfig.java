package com.spring.productservice.config;

import com.spring.productservice.util.exception.FeignClientErrorHandle;
import feign.codec.ErrorDecoder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeignClientConfig {

    FeignClientErrorHandle feignClientErrorHandle;

    public FeignClientConfig(FeignClientErrorHandle feignClientErrorHandle) {
        this.feignClientErrorHandle = feignClientErrorHandle;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return feignClientErrorHandle;
    }
}
