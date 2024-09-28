package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.domain.request.RequestChangePassword;
import com.spring.delivery.domain.request.RequestResetPassword;
import com.spring.delivery.service.authentication.PasswordService;
import com.spring.delivery.util.anotation.ApiMessage;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/password")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PasswordController {
    PasswordService passwordService;

    @ApiMessage("Request OTP Forgot Password")
    @PostMapping("/forgot")
    public ResponseEntity<ApiResponse<Void>> forgotPassword(@RequestHeader(value = "email") String email) {
        passwordService.sendOtp(email);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Reset Password")
    @PostMapping("/reset")
    public ResponseEntity<ApiResponse<Void>> resetPassword(@RequestHeader(value = "email") String email, @Valid @RequestBody RequestResetPassword request) {
        passwordService.resetPassword(request.otp(), email, request.newPassword());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SHIPPER')")
    @ApiMessage("Change Password")
    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(@Valid @RequestBody RequestChangePassword requestChangePassword) {
        passwordService.changePassword(requestChangePassword.email(), requestChangePassword.oldPassword(), requestChangePassword.newPassword());
        return ResponseEntity.ok().build();
    }
}
