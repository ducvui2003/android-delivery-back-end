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
import com.spring.delivery.domain.response.review.ProductReviewResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    String id;
    String name;
    String image;
    String description;
    Integer quantity;
    Double price;
    CategoryDTO category;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<ProductOptionDTO> options;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<Nutritional> nutritional;

    DiscountInfo discountInfo;

    ProductReviewResponse rating;

    boolean isFavorite;
}
