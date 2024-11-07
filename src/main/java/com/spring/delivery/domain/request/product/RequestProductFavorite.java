/**
 * Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 2:28 PM - 05/11/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.domain.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProductFavorite(
        @NotNull(message = "Product favorite id not null")
        @NotBlank(message = "Product favorite id not blank")
        String productId
) {
}
