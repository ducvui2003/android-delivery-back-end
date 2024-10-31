package com.spring.delivery.util.enums;

import lombok.Getter;

@Getter
public enum Unit {
    GRAM("g"),
    MILLILITER("ml"),
    PIECE("piece");

    private String name;

    Unit(String name) {
        this.name = name;
    }
}
