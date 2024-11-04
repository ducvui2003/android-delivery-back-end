package com.spring.delivery.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.util.enums.Unit;
import com.spring.delivery.util.enums.converter.UnitValueConverter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.convert.ValueConverter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Nutritional {
    String name;

    Double value;

    @ValueConverter(UnitValueConverter.class)
    Unit unit;
}
