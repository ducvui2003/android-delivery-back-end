/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:39 PM - 30/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.util.enums.converter;

import com.spring.delivery.util.enums.Unit;
import org.springframework.data.convert.PropertyValueConverter;
import org.springframework.data.convert.ValueConversionContext;

public class UnitValueConverter implements PropertyValueConverter<Unit, String, ValueConversionContext<?>> {

    @Override
    public Unit read(String value, ValueConversionContext context) {
        return Unit.getEntries().stream().filter(unit -> unit.getValue().equals(value)).findFirst().get();
    }

    @Override
    public String write(Unit unit, ValueConversionContext context) {
        return unit.getValue();
    }
}