package com.spring.productservice.util.exception;


import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private AppErrorCode errorCode;

    public AppException(String message) {
        super(message);
    }

    public AppException(String statusCode, String message) {
        super(message);
    }

    public AppException(AppErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
