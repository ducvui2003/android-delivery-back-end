package com.spring.apigateway.config.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import com.spring.apigateway.config.component.FormatMono;
import com.spring.apigateway.service.IdentityService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationFilter implements GlobalFilter, Ordered {
	IdentityService identityService;
	FormatMono formatMono;

	@Value("${app.public-endpoint}")
	@NonFinal
	String[] whiteList;

	@NonFinal
	@Value("${app.filter-order.authentication}")
	int order;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		if (isWhitelisted(exchange.getRequest().getPath().value())) return chain.filter(exchange);
		// Get token from authorization header
		List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
		if (CollectionUtils.isEmpty(authHeader)) return formatMono.unAuthenticated(exchange);
		String accessToken = authHeader.get(0).replace("Bearer ", "");
		// Call identity service to validate token
		return identityService
				.introspect(accessToken)
				.flatMap(response -> {
					if (response.getStatusCode() == HttpStatus.UNAUTHORIZED.value()
							|| response.getStatusCode() == HttpStatus.FORBIDDEN.value())
						return formatMono.unAuthenticated(exchange);
					return chain.filter(exchange);
				})
				.onErrorResume(e -> formatMono.unAuthenticated(exchange));
	}

	@Override
	public int getOrder() {
		return order;
	}

	private boolean isWhitelisted(String path) {
		for (String whitelistPath : whiteList) {
			if (whitelistPath.contains("**")) {
				// Handle wildcard match
				String basePath = whitelistPath.replace("/**", "");
				if (path.startsWith(basePath)) {
					return true;
				}
			} else if (path.equals(whitelistPath)) {
				// Exact match
				return true;
			}
		}
		return false;
	}
}
