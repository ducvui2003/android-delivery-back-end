package com.spring.apigateway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.spring.apigateway.repository.http.IdentityClient;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebClientConfig {
	@Value("${app.uri.identity-service}")
	String identityServiceUri;

	@Bean
	public WebClient webClientIdentityService() {
		return WebClient.builder().baseUrl(identityServiceUri).build();
	}

	@Bean
	IdentityClient identityClient(@Qualifier("webClientIdentityService") WebClient webClientIdentityService) {
		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(
						WebClientAdapter.create(webClientIdentityService))
				.build();
		return httpServiceProxyFactory.createClient(IdentityClient.class);
	}
}
