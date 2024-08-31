package com.spring.ratingservice.util.constraint;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);
    private final int value;

    public static Rating fromValue(int value) {
        for (Rating rating : Rating.values()) {
            if (rating.value == value) {
                return rating;
            }
        }
        throw new IllegalArgumentException("Unknown rating value: " + value);
    }
}
