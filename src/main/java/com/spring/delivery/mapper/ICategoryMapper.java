/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 2:27 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.mapper;

import com.spring.delivery.document.Category;
import com.spring.delivery.domain.request.product.RequestCategoryCreatedAndUpdated;
import com.spring.delivery.domain.response.product.CategoryDTO;
import com.spring.delivery.util.FirebaseUrlUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {
    @Mapping(target = "urlImage", source = "urlImage", qualifiedByName = "mapImageUrl")
    CategoryDTO toCategoryDTO(Category product);

    Category toCategory(RequestCategoryCreatedAndUpdated request);

    @Named("mapImageUrl")
    default String mapImageUrl(String firebaseUrl) {
        return FirebaseUrlUtil.getPublicUrl(firebaseUrl);
    }
}