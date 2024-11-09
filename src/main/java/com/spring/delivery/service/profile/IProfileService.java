package com.spring.delivery.service.profile;

import com.spring.delivery.domain.request.RequestUpdateProfile;

import java.util.Map;

public interface IProfileService {
    void updateProfile(Map<String, Object> updateRequest);
}
