package com.spring.delivery.domain.request.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;

public record RequestDiscountCreated(
        @NotNull(message = "Discount is required")
        Double discount,
        @NotNull(message = "Expired datetime is required")
        @Future(message = "Expiration date must be in the future")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]", timezone = "Asia/Ho_Chi_Minh")
        LocalDateTime expired
) {
}
