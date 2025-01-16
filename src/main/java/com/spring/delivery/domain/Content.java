package com.spring.delivery.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.enums.ContentType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Content(
        Long id,
        ContentType type,
        String data
) {
}