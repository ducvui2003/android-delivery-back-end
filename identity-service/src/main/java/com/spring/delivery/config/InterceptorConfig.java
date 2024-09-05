package com.spring.delivery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.delivery.config.interceptor.LimitForgotPasswordInterceptor;
import com.spring.delivery.config.interceptor.LimitVerifyInterceptor;
import com.spring.delivery.config.interceptor.PostAuthenticationInterceptor;
import com.spring.delivery.config.interceptor.RequestEmailHeaderInterceptor;
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
	RequestEmailHeaderInterceptor requestEmailHeaderInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(postAuthenticationInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(applicationProps.getWhitelist());
		registry.addInterceptor(requestEmailHeaderInterceptor)
				.addPathPatterns("/api/v1/auth/verify", "/api/v1/auth/verify-resent")
				.addPathPatterns("/api/v1/auth/password/forgot", "/api/v1/auth/password/reset", "/api/v1/auth/password/change-password");
		registry.addInterceptor(limitVerifyInterceptor)
				.addPathPatterns("/api/v1/auth/verify", "/api/v1/auth/verify-resent");
		registry.addInterceptor(limitForgotPasswordInterceptor)
				.addPathPatterns("/api/v1/auth/password/forgot", "/api/v1/auth/password/reset", "/api/v1/auth/password/change-password");
	}
}
