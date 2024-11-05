/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 10:17 AM - 04/11/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.domain.response.product;

import com.spring.delivery.document.DiscountInfo;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardProductDTO {
    String id;
    String name;
    double price;
    DiscountInfo discountInfo;
    double avgRating;
    boolean isFavorite;
}
