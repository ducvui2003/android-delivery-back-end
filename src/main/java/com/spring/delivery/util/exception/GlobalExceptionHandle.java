package com.spring.delivery.util.exception;

import java.io.IOException;
import java.util.List;

import com.spring.delivery.config.CustomAccessDeniedHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.spring.delivery.config.properties.ProfileProperties;
import com.spring.delivery.domain.ApiResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GlobalExceptionHandle {
    ProfileProperties profileProperties;
    CustomAccessDeniedHandler accessDeniedHandler;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception exception) {
        StackTraceElement[] stackTrace = getStackTraces(exception);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message(exception.getMessage())
                .error("Server Internal Error")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .stackTrace(stackTrace)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(JwtException exception) {
        StackTraceElement[] stackTrace = getStackTraces(exception);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message(exception.getMessage())
                .error("JWT Exception")
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .stackTrace(stackTrace)
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Delegate handling to the custom AccessDeniedHandler
        accessDeniedHandler.handle(request, response, ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> validateJWT(Exception exception) {
        StackTraceElement[] stackTrace = getStackTraces(exception);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message("Password is incorrect")
                .error("Bad Credentials")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .stackTrace(stackTrace)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleUsernameNotFound(UsernameNotFoundException exception) {
        StackTraceElement[] stackTrace = getStackTraces(exception);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message(exception.getMessage())
                .error("Username not found")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .stackTrace(stackTrace)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoResource(NoResourceFoundException exception) {
        StackTraceElement[] stackTrace = getStackTraces(exception);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message(exception.getMessage())
                .error("No resource found")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .stackTrace(stackTrace)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        StackTraceElement[] stackTrace = getStackTraces(exception);

        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .message(errors.size() == 1 ? errors.getFirst() : errors)
                .error("Validation error")
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .stackTrace(stackTrace)
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppExceptions(AppException exception) {
        int statusCode = exception.getErrorCode() == null
                ? HttpStatus.BAD_REQUEST.value()
                : exception.getErrorCode().getCode();
        StackTraceElement[] stackTrace = getStackTraces(exception);
        String error = exception.getErrorCode() == null
                ? exception.getMessage()
                : exception.getErrorCode().toString();
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .error(error)
                .message(exception.getMessage())
                .statusCode(statusCode)
                .stackTrace(stackTrace)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    private StackTraceElement[] getStackTraces(Exception exception) {
        if (!profileProperties.isDevEnvironment()) return new StackTraceElement[0];
        exception.printStackTrace();
        return exception.getStackTrace();
    }
}
