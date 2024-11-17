package com.spring.delivery.domain.request;

import com.spring.delivery.util.anotation.NumberPhone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record RequestUpdateProfile(
        @NotBlank Long id,
        String fullName,
        @Email String email,
        @NumberPhone String phoneNumber,
        String address,
        String avatar,
        String sex,
        String birthday
) {
}
