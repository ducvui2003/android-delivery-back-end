package com.spring.delivery.util.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.delivery.model.OrderItemOption;
import com.spring.delivery.model.OrderPromotion;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class JsonOrderItemOptionConverter implements AttributeConverter<List<OrderItemOption>, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<OrderItemOption> attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error serializing JSON", e);
        }
    }

    @Override
    public List<OrderItemOption> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, OrderItemOption.class));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error deserializing JSON", e);
        }
    }
}
