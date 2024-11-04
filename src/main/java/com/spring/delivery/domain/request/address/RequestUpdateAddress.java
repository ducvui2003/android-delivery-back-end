package com.spring.delivery.domain.request.address;

import jakarta.validation.constraints.NotBlank;
import lombok.experimental.NonFinal;

public record RequestUpdateAddress(
        @NotBlank(message = "Id of address not blank") Long id,
        @NotBlank(message = "Address is required") String address
) {
}
