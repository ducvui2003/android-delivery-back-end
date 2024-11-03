/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 2:25 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.domain.response.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.delivery.document.DiscountInfo;
import com.spring.delivery.document.Nutritional;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductDTO(
        String id,

        String name,

        String image,

        String description,

        Integer quantity,

        Double price,

        CategoryDTO category,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<ProductOptionDTO> options,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<Nutritional> nutritional,

        DiscountInfo discountInfo
) {
}
