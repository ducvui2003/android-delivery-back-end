package com.spring.delivery.service.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.delivery.domain.request.ChangePasswordRequest;
import com.spring.delivery.domain.request.RequestUpdateProfile;
import com.spring.delivery.domain.response.ProfileResponse;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.mysql.UserRepository;
import com.spring.delivery.util.MyPhoneNumberUtil;
import com.spring.delivery.util.PropertyNameNullUtil;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileServiceImpl implements IProfileService {
    UserRepository userRepository;
    ObjectMapper objectMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public ProfileResponse updateProfile(Map<String, Object> updateRequest) {
        User user = userRepository.findByEmail(SecurityUtil.getCurrentUserLogin().get())
                .orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        String region = updateRequest.getOrDefault("region", "VN").toString();
        RequestUpdateProfile requestUpdateProfile = objectMapper.convertValue(updateRequest, RequestUpdateProfile.class);
        BeanUtils.copyProperties(requestUpdateProfile, user, PropertyNameNullUtil.getNullPropertyNames(requestUpdateProfile));
        if (!region.isEmpty()) {
            user.setPhoneNumber(MyPhoneNumberUtil.formatPhoneNumber(region, user.getPhoneNumber()));
        }
        userRepository.save(user);
        return new ProfileResponse(user.getPhoneNumber(), user.getFullName(), user.getEmail());
    }

    @Override
    public void changePassword(ChangePasswordRequest changePassword) {
        User user = userRepository.findByEmail(SecurityUtil.getCurrentUserLogin().get())
                .orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        if (!passwordEncoder.matches(changePassword.currentPassword(), user.getPassword())) {
            throw new AppException(AppErrorCode.INVALID_PASSWORD);
        }
        if (passwordEncoder.matches(changePassword.newPassword(), user.getPassword())) {
            throw new AppException(AppErrorCode.INVALID_PASSWORD);
        }
        user.setPassword(passwordEncoder.encode(changePassword.newPassword()));
        userRepository.save(user);
    }


}
