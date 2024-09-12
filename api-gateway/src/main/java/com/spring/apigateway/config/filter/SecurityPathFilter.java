package com.spring.apigateway.config.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.spring.apigateway.config.component.FormatMono;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityPathFilter implements GlobalFilter, Ordered {
	FormatMono formatMono;

	@NonFinal
	@Value("${app.filter-order.security}")
	int order;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		if (exchange.getRequest().getPath().value().contains("/internal")) {
			return formatMono.endpointNotAllow(exchange);
		}
		return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return order;
	}
}
