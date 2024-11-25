/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 2:30 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.domain.response.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record CategoryDTO(String id,
                          String name,
                          String urlImage) {
}
