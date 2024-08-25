package com.spring.delivery.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.delivery.domain.ApiResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component("authenticationEntryPoint")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();

	ObjectMapper mapper;

	@Override
	public void commence(
			HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		this.delegate.commence(request, response, authException);
		response.setContentType("application/json;charset=UTF-8");

		String errorMessage = Optional.ofNullable(authException.getCause())
				.map(Throwable::getMessage)
				.orElse(authException.getMessage());
		ApiResponse<Object> res = ApiResponse.builder()
				.statusCode(HttpStatus.UNAUTHORIZED.value())
				.error(errorMessage)
				.message("Token not valid")
				.build();
		mapper.writeValue(response.getWriter(), res);
	}
}
