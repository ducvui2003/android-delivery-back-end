/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 6:17 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.mapper;

import com.spring.delivery.document.Nutritional;
import com.spring.delivery.domain.request.product.RequestNutritionalCreated;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NutritionalMapper {
    List<Nutritional> toListNutritional(List<RequestNutritionalCreated> request);
}
