/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 12:46 PM - 05/11/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.domain.request.product;

public record RequestSearchProduct(
        String name,
        String category,
        Boolean isBestSeller,
        Boolean isNew,
        Integer page
) {
}
