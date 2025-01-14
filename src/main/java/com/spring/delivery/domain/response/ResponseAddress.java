package com.spring.delivery.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAddress {
    Long id;
    String name;
    String address;
    boolean isDefault;
    Instant updatedAt;
}
