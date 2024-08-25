package com.spring.notificationservice.util.constrant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum TEMPLATE {
    VERIFY_EMAIL(1),
    RESET_PASSWORD(2),
    ;

    private final Integer id;
}
