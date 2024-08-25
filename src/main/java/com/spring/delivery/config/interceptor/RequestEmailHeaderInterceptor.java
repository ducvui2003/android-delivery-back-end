package com.spring.delivery.config.interceptor;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.delivery.service.authentication.AuthenticationService;
import com.spring.delivery.util.enums.RequestHeader;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;

import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestEmailHeaderInterceptor implements HandlerInterceptor {
	AuthenticationService authenticationService;

	@Override
	public boolean preHandle(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable Object handler) {
		Optional<String> emailOptional = Optional.ofNullable(request.getHeader(RequestHeader.EMAIL.getName()));
		if (emailOptional.isEmpty()) throw new AppException(AppErrorCode.HEADER_MISSING);

		return true;
	}
}
