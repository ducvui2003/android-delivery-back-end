package com.spring.delivery.config;

import com.spring.delivery.config.interceptor.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.delivery.config.properties.ApplicationProps;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class InterceptorConfig implements WebMvcConfigurer {
    ApplicationProps applicationProps;
    PostAuthenticationInterceptor postAuthenticationInterceptor;
    LimitForgotPasswordInterceptor limitForgotPasswordInterceptor;
    LimitVerifyInterceptor limitVerifyInterceptor;
    RequestPhoneHeaderInterceptor requestPhoneHeaderInterceptor;
    RequestEmailHeaderInterceptor requestEmailHeaderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(postAuthenticationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(applicationProps.getWhitelist());
//        PHONE
        registry.addInterceptor(requestPhoneHeaderInterceptor)
                .addPathPatterns("/api/v1/auth/verify", "/api/v1/auth/verify-resent");
        registry.addInterceptor(limitVerifyInterceptor)
                .addPathPatterns("/api/v1/auth/verify", "/api/v1/auth/verify-resent");
//        EMAIL
        registry.addInterceptor(requestEmailHeaderInterceptor)
                .addPathPatterns("/api/v1/auth/password/forgot", "/api/v1/auth/password/reset", "/api/v1/auth/password/change-password");
        registry.addInterceptor(limitForgotPasswordInterceptor)
                .addPathPatterns("/api/v1/auth/password/forgot", "/api/v1/auth/password/reset", "/api/v1/auth/password/change-password");
    }
}
