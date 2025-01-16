package com.spring.delivery.util.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.delivery.model.OrderPromotion;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonOrderPromotionConverter implements AttributeConverter<OrderPromotion, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(OrderPromotion attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON to String", e);
        }
    }

    @Override
    public OrderPromotion convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, OrderPromotion.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting String to JSON", e);
        }
    }
}
