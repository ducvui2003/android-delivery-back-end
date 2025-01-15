package com.spring.delivery.domain.request;

public record ChangePasswordRequest(String currentPassword, String newPassword) {
}
