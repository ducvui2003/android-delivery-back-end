package com.spring.delivery.service.profile;

import com.spring.delivery.domain.request.ChangePasswordRequest;
import com.spring.delivery.domain.request.RequestUpdateProfile;
import com.spring.delivery.domain.response.ProfileResponse;

import java.util.Map;

public interface IProfileService {
    ProfileResponse updateProfile(Map<String, Object> updateRequest);

    void changePassword(ChangePasswordRequest changePassword);
}
