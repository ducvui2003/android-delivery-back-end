package com.spring.delivery.config.interceptor;

import com.spring.delivery.util.enums.RequestHeader;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestEmailHeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<String> emailOptional = Optional.ofNullable(request.getHeader(RequestHeader.EMAIL.getName()));
        if (emailOptional.isEmpty()) throw new AppException(AppErrorCode.HEADER_MISSING);
        return true;
    }
}
