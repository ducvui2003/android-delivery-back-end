package com.spring.delivery.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.delivery.domain.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;


@Component("accessDeniedHandler")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        String errorMessage = Optional.ofNullable(accessDeniedException.getCause()).map(Throwable::getMessage).orElse(accessDeniedException.getMessage());
        ApiResponse<Object> res = ApiResponse.builder().statusCode(HttpStatus.FORBIDDEN.value()).error(errorMessage).message("Forbidden").build();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        mapper.writeValue(response.getWriter(), res);
    }
}
