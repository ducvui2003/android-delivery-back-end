package com.spring.delivery.util.enums.converter;

import com.spring.delivery.util.enums.AuthType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter(autoApply = true)
public class AuthTypeConverter implements AttributeConverter<AuthType, String> {

    @Override
    public String convertToDatabaseColumn(AuthType authType) {
        if (authType == null) {
            log.warn("AuthType is null");
            return null;
        }
        return authType.name().toLowerCase(); // Convert to lowercase string
    }

    @Override
    public AuthType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            log.warn("AuthType is null");
            return null;
        }
        return AuthType.valueOf(dbData.toUpperCase()); // Convert to uppercase enum
    }
}
