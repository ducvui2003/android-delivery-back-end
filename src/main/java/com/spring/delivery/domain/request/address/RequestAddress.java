package com.spring.delivery.domain.request.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record RequestAddress(
        @NotBlank @JsonProperty("address") String address) {
}
