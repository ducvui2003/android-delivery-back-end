/**
 * Author: Nguyen Dinh Lam
 * Email: kiminonawa1305@gmail.com
 * Phone number: +84 855354919
 * Create at: 5:07 PM - 31/10/2024
 * User: lam-nguyen
 **/

package com.spring.delivery.mapper;

import com.spring.delivery.document.ProductOption;
import com.spring.delivery.domain.request.product.RequestOptionCreated;
import com.spring.delivery.domain.response.product.ProductOptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductOptionMapper {
    ProductOptionDTO toProductOptionDTO(ProductOption option);

    ProductOption toProductOption(RequestOptionCreated request);
}
