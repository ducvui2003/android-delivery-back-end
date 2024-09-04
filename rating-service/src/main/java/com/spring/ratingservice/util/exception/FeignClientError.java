package com.spring.ratingservice.util.exception;

import lombok.Getter;

@Getter
public class FeignClientError extends RuntimeException {
    private final int status;

    public FeignClientError(int statusCode, String message) {
        super(message);
        this.status = statusCode;
    }
}
