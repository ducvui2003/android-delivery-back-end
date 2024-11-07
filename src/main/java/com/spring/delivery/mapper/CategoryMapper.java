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
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(Category product);

    Category toCategory(RequestCategoryCreatedAndUpdated request);
}