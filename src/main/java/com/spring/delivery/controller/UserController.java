package com.spring.delivery.controller;

import com.spring.delivery.domain.ApiResponse;
import com.spring.delivery.domain.request.ChangePasswordRequest;
import com.spring.delivery.domain.request.RequestUpdateProfile;
import com.spring.delivery.domain.response.ProfileResponse;
import com.spring.delivery.service.profile.IProfileService;
import com.spring.delivery.util.anotation.ApiMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/user")
public class UserController {
    IProfileService profileService;

    @ApiMessage("Update Profile")
    @PutMapping("/update-profile")
    public ResponseEntity<ApiResponse<ProfileResponse>> updateProfile(@RequestBody Map<String, Object> updateRequest) {
        log.info("Invoked function updateProfile in Controller");
        profileService.updateProfile(updateRequest);
        return ResponseEntity.ok().build();
    }

    @ApiMessage("Change password")
    @PutMapping("/change-password")
    public ResponseEntity<ApiResponse<ProfileResponse>> changePassword(@RequestBody ChangePasswordRequest changePassword) {
        log.info("Invoked function changePassword in Controller");
        profileService.changePassword(changePassword);
        return ResponseEntity.ok().build();
    }
}
