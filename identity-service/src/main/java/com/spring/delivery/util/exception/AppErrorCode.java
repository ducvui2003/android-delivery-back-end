package com.spring.delivery.util.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum AppErrorCode {
    EXIST(HttpServletResponse.SC_CONFLICT, "Data is already exist"),
    ACCESS_TOKEN_EXPIRED(HttpServletResponse.SC_REQUEST_TIMEOUT, "Access authCode is expired"),
    ACCESS_TOKEN_NOT_FOUND(HttpServletResponse.SC_UNAUTHORIZED, "Access authCode not found"),
    USER_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "User not found"),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"),
    GOOGLE_AUTHENTICATION_FAILED(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Google authentication failed"),
    NOT_VERIFIED(HttpServletResponse.SC_UNAUTHORIZED, "User not verified"),
    EMAIL_EXISTED(HttpServletResponse.SC_CONFLICT, "Email is already used"),
    OTP_NOT_MATCH(HttpServletResponse.SC_UNAUTHORIZED, "OTP not match"),
    REFRESH_TOKEN_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "Refresh token not found"),
    MAX_TRY(HttpServletResponse.SC_UNAUTHORIZED, "Max try"),
    HEADER_MISSING(HttpServletResponse.SC_BAD_REQUEST, "Header is missing"),
    PASSWORD_NOT_MATCH(HttpServletResponse.SC_BAD_REQUEST, "Password not match"),
    VERIFY_EMAIL_ALREADY(HttpServletResponse.SC_BAD_REQUEST, "Email is already verified"),
    PHONE_NUMBER_EXISTED(HttpServletResponse.SC_CONFLICT, "Phone number is already used"),
    INVALID_TOKEN(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token"),
    PHONE_NUMBER_INVALID(HttpServletResponse.SC_BAD_REQUEST, "Phone number is invalid"),
    ERROR_OAUTH2(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error occurred during OAuth2 authentication");

    private int code;
    private String message;

}
