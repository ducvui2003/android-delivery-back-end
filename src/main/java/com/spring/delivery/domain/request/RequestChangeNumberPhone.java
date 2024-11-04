package com.spring.delivery.domain.request;

import com.spring.delivery.util.anotation.NumberPhone;
import jakarta.validation.constraints.NotBlank;

public record RequestChangeNumberPhone(
        @NotBlank(message = "Phone number is required") @NumberPhone String phoneNumber
) {
}
