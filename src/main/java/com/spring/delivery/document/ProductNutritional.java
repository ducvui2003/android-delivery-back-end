/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 3:39 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.document;

import com.spring.delivery.util.enums.Unit;
import com.spring.delivery.util.enums.converter.UnitValueConverter;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.convert.ValueConverter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class ProductNutritional {
    String name;

    Double value;

    @ValueConverter(UnitValueConverter.class)
    Unit unit;
}