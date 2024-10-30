package com.spring.delivery.util.enums.converter;

import com.spring.delivery.util.enums.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Rating attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return Rating.fromValue(dbData);
    }
}