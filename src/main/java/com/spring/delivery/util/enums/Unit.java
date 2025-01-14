/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:27 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.util.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public enum Unit {
    @JsonProperty("g")
    GRAM("g"),
    @JsonProperty("ml")
    MILLILITER("ml"),
    @JsonProperty("piece")
    PIECE("piece");

    @Getter
    private final String value;

    Unit(String value) {
        this.value = value;
    }
}