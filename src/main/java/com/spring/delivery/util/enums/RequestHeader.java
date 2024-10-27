package com.spring.delivery.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum RequestHeader {
    PHONE_NUMBER("phoneNumber"),

    EMAIL("email");

    private String name;
}
