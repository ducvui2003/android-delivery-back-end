package com.spring.notificationservice.util.constrant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum TEMPLATE {
    RESET_PASSWORD(1),
    WELCOME(6);

    private final Integer id;
}
