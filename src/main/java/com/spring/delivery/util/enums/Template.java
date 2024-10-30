package com.spring.delivery.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Template {
    WELL_COME(6),
    VERIFY_EMAIL(9),
    RESET_PASSWORD(1);
    private final Integer id;
}
