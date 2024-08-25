package com.spring.delivery.util.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
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
