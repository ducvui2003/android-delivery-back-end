package com.spring.ratingservice.util.exception;


import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private AppErrorCode errorCode;

    public AppException(String message) {
        super(message);
    }

    public AppException(AppErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
