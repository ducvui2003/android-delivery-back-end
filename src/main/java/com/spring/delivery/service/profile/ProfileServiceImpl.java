package com.spring.delivery.service.profile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.delivery.domain.request.RequestUpdateProfile;
import com.spring.delivery.domain.request.address.RequestAddress;
import com.spring.delivery.mapper.UserMapper;
import com.spring.delivery.model.Address;
import com.spring.delivery.model.User;
import com.spring.delivery.repository.mysql.IAddressRepository;
import com.spring.delivery.repository.mysql.UserRepository;
import com.spring.delivery.service.address.IAddressService;
import com.spring.delivery.util.MyPhoneNumberUtil;
import com.spring.delivery.util.SecurityUtil;
import com.spring.delivery.util.exception.AppErrorCode;
import com.spring.delivery.util.exception.AppException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileServiceImpl implements IProfileService {
    UserRepository userRepository;
    IAddressRepository addressRepository;
    UserMapper userMapper;
    ObjectMapper objectMapper;
    IAddressService addressService;

    @Override
    public void updateProfile(Map<String, Object> updateRequest) {
        String region = updateRequest.get("region").toString();
        User user = userRepository.findByEmail(SecurityUtil.getCurrentUserLogin().get())
                .orElseThrow(() -> new AppException(AppErrorCode.USER_NOT_FOUND));
        if (updateRequest.containsKey("address")) {
            RequestAddress requestAddress = objectMapper.convertValue(
                    Map.of("address", updateRequest.get("address")),
                    RequestAddress.class
            );
            addressService.addAddress(requestAddress);
        }
        RequestUpdateProfile requestUpdateProfile = objectMapper.convertValue(updateRequest, RequestUpdateProfile.class);
        BeanUtils.copyProperties(requestUpdateProfile, user, getNullPropertyNames(requestUpdateProfile));
        user.setPhoneNumber(MyPhoneNumberUtil.formatPhoneNumber(region, user.getPhoneNumber()));
        userRepository.save(user);
    }

    //lấy tên các thuộc tính null của object
    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<>();

        for (var propertyDescriptor : wrappedSource.getPropertyDescriptors()) {
            String propertyName = propertyDescriptor.getName();
            if (wrappedSource.getPropertyValue(propertyName) == null) {
                emptyNames.add(propertyName);
            }
        }
        return emptyNames.toArray(new String[0]);
    }
}
